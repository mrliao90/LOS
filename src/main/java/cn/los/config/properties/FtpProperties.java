package cn.los.config.properties;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FTP 配置
 *
 */
@Component
@ConfigurationProperties(prefix = FtpProperties.PREFIX)
public class FtpProperties {

    public static final String PREFIX = "ftp";

    private String host = "localhost";

    private int port = FTPClient.DEFAULT_PORT;

    private String username;

    private String password;

    private int bufferSize = 8096;

    private String encoding = "UTF-8";

    /**
     * 初始化连接数
     */
    private Integer initialSize = 0;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
