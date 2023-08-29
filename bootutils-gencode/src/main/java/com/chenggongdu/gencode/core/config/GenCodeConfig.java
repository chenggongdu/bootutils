package com.chenggongdu.gencode.core.config;


/**
 * 代码生成相关动态配置
 *
 * @author chenggongdu
 */
public class GenCodeConfig {

    /**
     * JDBC URL
     */
    private static String dbUrl;

    /**
     * 数据库用户名 默认root
     */
    private static String dbUsername = "root";

    /**
     * 数据库密码 默认root
     */
    private static String dbPassword = "root";

    /**
     * 作者 默认chenggongdu
     */
    private static String author = "chenggongdu";

    /**
     * 生成包路径 默认com.chenggongdu.demo
     */
    private static String packageName = "com.chenggongdu.demo";

    /**
     * 生成文件路径 默认//src//main//java//com//chenggongdu//demo
     */
    private static String fileName = "//src//main//java//com//chenggongdu//demo";

    /**
     * 生成entity路径 默认 com.chenggongdu.gencode.core.config.GenCodeConfig#fileName + //mapper//entity
     */
    private static String entityPath = "//mapper//entity";


    public static String getDbUrl() {
        return dbUrl;
    }

    public static void setDbUrl(String dbUrl) {
        GenCodeConfig.dbUrl = dbUrl;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static void setDbUsername(String dbUsername) {
        GenCodeConfig.dbUsername = dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static void setDbPassword(String dbPassword) {
        GenCodeConfig.dbPassword = dbPassword;
    }

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        GenCodeConfig.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        GenCodeConfig.packageName = packageName;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        GenCodeConfig.fileName = fileName;
    }

    public static String getEntityPath() {
        return entityPath;
    }

    public static void setEntityPath(String entityPath) {
        GenCodeConfig.entityPath = entityPath;
    }

}

