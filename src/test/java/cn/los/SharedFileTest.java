package cn.los;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

/**
 * @author LJR
 * @Description: 操作远程共享目录
 * @date 2018年6月7日 下午2:07:14
 */
public class SharedFileTest {
    public static void main(String[] args) {

        try {
            smbDownload("C:\\Users\\test\\Downloads", "pub/", "MonitoredDataDao.xml",
                    "192.168.1.6", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 需要下载对应的jcifs-1.3.18.jar

    /**
     * 方法说明：从远程共享目录下载文件
     * @param localDir         本地临时路径
     * @param removeDir        远程共享路径
     * @param _fileName        远程共享文件名
     * @param removeIp         远程共享目录IP
     * @param removeLoginUser  远程共享目录用户名
     * @param removeLoginPass  远程共享目录密码
     * @return
     * @throws Exception
     */
    public static int smbDownload(String localDir, String removeDir,
            String _fileName, String removeIp, String removeLoginUser,
            String removeLoginPass) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            // NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(
            // removeIp, removeLoginUser, removeLoginPass);
            // SmbFile remoteFile =new SmbFile(removeDir + _fileName, auth);
            SmbFile remoteFile = new SmbFile(
                    "http://192.168.1.6/" + removeDir + _fileName);
            if (!remoteFile.exists()) {
                return 0;
            }

            File dir = new File(localDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = _fileName.substring(_fileName.lastIndexOf("\\") + 1,
                    _fileName.length());
            File localFile = new File(localDir + fileName);
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != in) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return 1;
    }

    /**
     * 方法说明：上传文件到远程共享目录
     * @param localDir         本地临时路径(A:/测试/测试.xls)
     * @param removeDir        远程共享路径(smb://10.169.2.xx/测试/,特殊路径只能用/)
     * @param removeIp         远程共享目录IP(10.169.2.xx)
     * @param removeLoginUser  远程共享目录用户名(user)
     * @param removeLoginPass  远程共享目录密码(password)
     * @return
     * @throws Exception   0成功/-1失败
     */
    public static int smbUploading(String localDir, String removeDir,
            String removeIp, String removeLoginUser, String removeLoginPass) throws Exception {
        NtlmPasswordAuthentication auth = null;
        OutputStream out = null;
        int retVal = 0;
        try {
            File dir = new File(localDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            InetAddress ip = InetAddress.getByName(removeIp);
            UniAddress address = new UniAddress(ip);
            // 权限验证
            auth = new NtlmPasswordAuthentication(removeIp, removeLoginUser, removeLoginPass);
            SmbSession.logon(address, auth);

            // 远程路径判断文件文件路径是否合法
            SmbFile remoteFile = new SmbFile(removeDir + dir.getName(), auth);
            remoteFile.connect();
            if (remoteFile.isDirectory()) {
                retVal = -1;
            }

            // 向远程共享目录写入文件
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
            out.write(toByteArray(dir));
        } catch (UnknownHostException e) {
            retVal = -1;
            e.printStackTrace();
        } catch (MalformedURLException e) {
            retVal = -1;
            e.printStackTrace();
        } catch (SmbException e) {
            retVal = -1;
            e.printStackTrace();
        } catch (IOException e) {
            retVal = -1;
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return retVal;
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param file 文件
     * @return   字节数组
     * @throws IOException IO异常信息
     */
    @SuppressWarnings("resource")
    public static byte[] toByteArray(File file) throws IOException {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
                    fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
