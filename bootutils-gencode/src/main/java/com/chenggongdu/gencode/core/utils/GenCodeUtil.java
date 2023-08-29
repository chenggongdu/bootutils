package com.chenggongdu.gencode.core.utils;


import com.chenggongdu.gencode.core.service.GenCodeService;

/**
 * 代码生成工具类
 *
 * @author chenggongdu
 */
public class GenCodeUtil {

    /**
     * 代码生成
     *
     * @param tableNames 要生成的表名称,多个用英文逗号分割
     */
    public static void genCode(String tableNames) {
        new GenCodeService().genCode(tableNames);
    }
}
