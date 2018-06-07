package cn.los.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.ObjectPool;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;

/**
 * @author LJR
 * @Description:  Ftp工具类
 * @date 2018年6月7日 下午12:41:21
 */
public class FtpUtil {

    /**
     * ftpClient连接池初始化标志
     */
    private static volatile boolean hasInit = false;
    /**
     * ftpClient连接池
     */
    private static ObjectPool<FTPClient> ftpClientPool;

    /**
     * 初始化ftpClientPool
     *
     * @param ftpClientPool
     */
    public static void init(ObjectPool<FTPClient> ftpClientPool) {
        if (!hasInit) {
            synchronized (FtpUtil.class) {
                if (!hasInit) {
                    FtpUtil.ftpClientPool = ftpClientPool;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 读取csv文件
     *
     * @param remoteFilePath 文件路径（path+fileName）
     * @param header 列头
     * @return
     * @throws IOException
     */
    public static List<CSVRecord> readCsvFile(String remoteFilePath, String... headers)
            throws IOException {
        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath))) {
            return CSVFormat.EXCEL.withHeader(headers).withSkipHeaderRecord(false)
                    .withIgnoreSurroundingSpaces().withIgnoreEmptyLines()
                    .parse(new InputStreamReader(in, "utf-8")).getRecords();
        } finally {
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 按行读取FTP文件
     *
     * @param remoteFilePath 文件路径（path+fileName）
     * @return
     * @throws IOException
     */
    public static List<String> readFileByLine(String remoteFilePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath));
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            return br.lines().map(line -> StringUtils.trimToEmpty(line))
                    .filter(line -> StringUtils.isNotEmpty(line)).collect(Collectors.toList());
        } finally {
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件
     *
     * @param remotePath 路径
     * @return FTPFile数组
     * @throws IOException
     */
    public static FTPFile[] retrieveFTPFiles(String remotePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        try {
            return ftpClient.listFiles(encodingPath(remotePath + "/"),
                    file -> file != null && file.getSize() > 0);
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件名称
     *
     * @param remotePath 路径
     * @return ftp文件名称列表
     * @throws IOException
     */
    public static List<String> retrieveFileNames(String remotePath) throws IOException {
        FTPFile[] ftpFiles = retrieveFTPFiles(remotePath);
        if (ArrayUtils.isEmpty(ftpFiles)) {
            return new ArrayList<>();
        }
        return Arrays.stream(ftpFiles).filter(Objects::nonNull)
                .map(FTPFile::getName).collect(Collectors.toList());
    }

    /**
     * 编码文件路径
     */
    private static String encodingPath(String path) throws UnsupportedEncodingException {
        // FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
        return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
    }

    /**
     * 获取ftpClient
     *
     * @return
     */
    private static FTPClient getFtpClient() {
        checkFtpClientPoolAvailable();
        FTPClient ftpClient = null;
        Exception ex = null;
        // 获取连接最多尝试3次
        for (int i = 0; i < 3; i++) {
            try {
                ftpClient = ftpClientPool.borrowObject();
                ftpClient.changeWorkingDirectory("/");
                break;
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ftpClient == null) {
            throw new RuntimeException("Could not get a ftpClient from the pool", ex);
        }
        return ftpClient;
    }

    /**
     * 释放ftpClient
     */
    private static void releaseFtpClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }

        try {
            ftpClientPool.returnObject(ftpClient);
        } catch (Exception e) {
            // destoryFtpClient
            if (ftpClient.isAvailable()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException io) {
                }
            }
        }
    }

    /**
     * 检查ftpClientPool是否可用
     */
    private static void checkFtpClientPoolAvailable() {
        Assert.state(hasInit, "FTP未启用或连接失败！");
    }

    /**
     * 上传Excel文件到FTP
     * @param workbook
     * @param remoteFilePath
     * @throws IOException
     */
    public static boolean uploadExcel2Ftp(Workbook workbook, String remoteFilePath)
            throws IOException {
        Assert.notNull(workbook, "workbook cannot be null.");
        Assert.hasText(remoteFilePath, "remoteFilePath cannot be null or blank.");
        FTPClient ftpClient = getFtpClient();
        try (OutputStream out = ftpClient.storeFileStream(encodingPath(remoteFilePath))) {
            workbook.write(out);
            workbook.close();
            return true;
        } finally {
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 从ftp下载excel文件
     * @param remoteFilePath
     * @param response
     * @throws IOException
     */
    public static void downloadExcel(String remoteFilePath, HttpServletResponse response)
            throws IOException {
        String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
        fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        FTPClient ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath));
                OutputStream out = response.getOutputStream()) {
            int size = 0;
            byte[] buf = new byte[10240];
            while ((size = in.read(buf)) > 0) {
                out.write(buf, 0, size);
                out.flush();
            }
        } finally {
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }

}