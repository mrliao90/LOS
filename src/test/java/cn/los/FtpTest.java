package cn.los;

import java.util.List;

import org.junit.Test;

import cn.los.common.util.FtpUtil;

public class FtpTest extends LosTests {

    @Test
    public void test() {
        try {
            List<String> names = FtpUtil.retrieveFileNames("/usr/local/logs");
            names.parallelStream().forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
