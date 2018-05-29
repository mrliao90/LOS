package cn.los.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.los.common.util.FileUtil;

@RestController
public class HelloWorldController {

    @RequestMapping(value = { "", "/" })
    public String SayHello() {
        return "Hello ,World";
    }

    // 返回前台文件流
    @RequestMapping(value = "downLoad")
    public ResponseEntity<byte[]> downLoad() {

        return renderFile("blackList.rt", new byte[] { 78, 79, 80 });

    }

    // 返回前台文件流
    @RequestMapping(value = "downLoad2")
    public ResponseEntity<byte[]> downLoad2() {

        try {

            // 项目服务器脚本文件路径
            File directory = new File("");// 参数为空
            String proRootPath = directory.getCanonicalPath();
            return renderFile("sys_user.sql", proRootPath + "\\db\\sys_user.sql");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 返回前台文件流
     *
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

}
