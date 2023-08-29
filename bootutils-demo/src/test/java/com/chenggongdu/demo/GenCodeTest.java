package com.chenggongdu.demo;

import com.chenggongdu.gencode.core.config.GenCodeConfig;
import com.chenggongdu.gencode.core.service.GenCodeService;
import com.chenggongdu.gencode.core.utils.GenCodeUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = BootUtilsDemoApplication.class)
public class GenCodeTest {
    @Resource
    private GenCodeService genCodeService;


    @Test
    public void testGenCode() {
        GenCodeConfig.setDbUrl("jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        GenCodeConfig.setDbUsername("root");
        GenCodeConfig.setDbPassword("root");
        genCodeService.genCode("corpus");

    }

    public static void main(String[] args) {
        GenCodeConfig.setDbUrl("jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        GenCodeConfig.setDbUsername("root");
        GenCodeConfig.setDbPassword("root");
        GenCodeConfig.setFileName("//bootutils-demo//src//main//java//com//chenggongdu//demo");
        GenCodeUtil.genCode("corpus");
    }

}
