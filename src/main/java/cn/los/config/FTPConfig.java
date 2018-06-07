package cn.los.config;

import javax.annotation.PreDestroy;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import cn.los.common.util.FtpUtil;
import cn.los.config.properties.FtpProperties;

/**
 * @author LJR
 * @Description: FTP 配置类
 * @date 2018年6月7日 上午10:27:50
 */
@Configuration
@ConditionalOnClass({ GenericObjectPool.class, FTPClient.class })
@ConditionalOnProperty(value = "ftp.enabled", havingValue = "true")
public class FTPConfig {

    // 定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger log = LoggerFactory.getLogger(FTPConfig.class);

    private ObjectPool<FTPClient> pool;

    public FTPConfig(FtpProperties props) {
        // 默认最大连接数与最大空闲连接数都为8，最小空闲连接数为0
        // 其他未设置属性使用默认值，可根据需要添加相关配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        poolConfig.setSoftMinEvictableIdleTimeMillis(50000);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        pool = new GenericObjectPool<>(new FtpClientPooledObjectFactory(props), poolConfig);
        preLoadingFtpClient(props.getInitialSize(), poolConfig.getMaxIdle());
        // 初始化ftp工具类中的ftpClientPool
        FtpUtil.init(pool);
    }

    /**
     * 预先加载FTPClient连接到对象池中
     * @param initialSize 初始化连接数
     * @param maxIdle 最大空闲连接数
     */
    private void preLoadingFtpClient(Integer initialSize, int maxIdle) {
        if (initialSize == null || initialSize <= 0) {
            return;
        }

        int size = Math.min(initialSize.intValue(), maxIdle);
        for (int i = 0; i < size; i++) {
            try {
                pool.addObject();
            } catch (Exception e) {
                log.error("preLoadingFtpClient error...", e);
            }
        }
    }

    @PreDestroy
    public void destroy() {
        if (pool != null) {
            pool.close();
            log.info("销毁ftpClientPool...");
        }
    }

    /**
     * FtpClient对象工厂类
     */
    static class FtpClientPooledObjectFactory implements PooledObjectFactory<FTPClient> {

        private FtpProperties props;

        public FtpClientPooledObjectFactory(FtpProperties props) {
            this.props = props;
        }

        @Override
        public PooledObject<FTPClient> makeObject() throws Exception {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect(props.getHost(), props.getPort());
                ftpClient.login(props.getUsername(), props.getPassword());
                log.info("连接FTP服务器返回码{}", ftpClient.getReplyCode());
                ftpClient.setBufferSize(props.getBufferSize());
                ftpClient.setControlEncoding(props.getEncoding());
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                return new DefaultPooledObject<>(ftpClient);
            } catch (Exception e) {
                log.error("建立FTP连接失败", e);
                if (ftpClient.isAvailable()) {
                    ftpClient.disconnect();
                }
                ftpClient = null;
                throw new Exception("建立FTP连接失败", e);
            }
        }

        @Override
        public void destroyObject(PooledObject<FTPClient> p) throws Exception {
            FTPClient ftpClient = getObject(p);
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }

        @Override
        public boolean validateObject(PooledObject<FTPClient> p) {
            FTPClient ftpClient = getObject(p);
            if (ftpClient == null || !ftpClient.isConnected()) {
                return false;
            }
            try {
                ftpClient.changeWorkingDirectory("/");
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public void activateObject(PooledObject<FTPClient> p) throws Exception {
        }

        @Override
        public void passivateObject(PooledObject<FTPClient> p) throws Exception {
        }

        private FTPClient getObject(PooledObject<FTPClient> p) {
            if (p == null || p.getObject() == null) {
                return null;
            }
            return p.getObject();
        }

    }

}
