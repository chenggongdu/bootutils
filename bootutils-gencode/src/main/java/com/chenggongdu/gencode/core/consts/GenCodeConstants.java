package com.chenggongdu.gencode.core.consts;

/**
 * 代码生成不可变常量
 *
 * @author chenggongdu
 */
public interface GenCodeConstants {
    /**
     * 数据库字符串类型
     */
    String[] COLUMN_TYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    /**
     * 数据库文本类型
     */
    String[] COLUMN_TYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     * 数据库时间类型
     */
    String[] COLUMN_TYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /**
     * 数据库数字类型
     */
    String[] COLUMN_TYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer", "bit"};

    /**
     * 数据库bigint类型
     */
    String[] COLUMN_TYPE_BIGINT = {"bigint"};
    /**
     * 数据库float类型
     */
    String[] COLUMN_TYPE_FLOAT = {"float"};
    /**
     * 数据库double类型
     */
    String[] COLUMN_TYPE_DOUBLE = {"double"};
    /**
     * 数据库decimal类型
     */
    String[] COLUMN_TYPE_DECIMAL = {"decimal"};

    /**
     * 字符串类型
     */
    String TYPE_STRING = "String";

    /**
     * 整型
     */
    String TYPE_INTEGER = "Integer";

    /**
     * 长整型
     */
    String TYPE_LONG = "Long";

    /**
     * 浮点型
     */
    String TYPE_DOUBLE = "Double";

    /**
     * 高精度计算类型
     */
    String TYPE_BIGDECIMAL = "BigDecimal";

    /**
     * 时间类型
     */
    String TYPE_DATE = "Date";
}
