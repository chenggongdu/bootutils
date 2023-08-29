package com.chenggongdu.gencode.core.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.chenggongdu.gencode.core.config.GenCodeConfig;
import com.chenggongdu.gencode.core.consts.GenCodeConstants;
import com.chenggongdu.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.chenggongdu.gencode.core.consts.GenCodeConstants.*;


/**
 * 模板处理工具类
 *
 * @author chenggongdu
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityUtils {

    /**
     * 设置模版变量信息
     *
     * @param tableName        表名
     * @param dataSourceConfig 数据源
     */
    public static VelocityContext prepareContext(String tableName, DataSourceConfig dataSourceConfig) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("table", tableName);
        velocityContext.put("package", GenCodeConfig.getPackageName());
        velocityContext.put("author", GenCodeConfig.getAuthor());

        List<Map<String, Object>> columns = new ArrayList<>();

        // 获取表信息sql
        String tableSql = "select table_name , table_comment from information_schema.tables " +
                "where table_schema = (select database()) and table_name = '" + tableName + "'";
        // 获取字段信息sql
        String columnSql = "select column_name , data_type , column_comment, column_key  from information_schema.columns " +
                "where table_name = '" + tableName + "' and table_schema = (select database()) and column_name != 'id_new'";
        // 利用现有的dataSourceConfig来获取数据库连接，查询表字段及备注等信息
        try (Connection conn = dataSourceConfig.getConn();
             PreparedStatement psTable = conn.prepareStatement(tableSql);
             ResultSet rsTable = psTable.executeQuery();
             PreparedStatement ps = conn.prepareStatement(columnSql);
             ResultSet rs = ps.executeQuery();
        ) {
            if (rsTable.next()) {
                String table_name = rsTable.getString("table_name");
                velocityContext.put("tableName", table_name);
                velocityContext.put("comment", rsTable.getString("table_comment"));

                // 类名 大驼峰
                velocityContext.put("upperClassName", StrUtil.upperFirst(StrUtil.toCamelCase(table_name)));
                // 对象名 小驼峰
                velocityContext.put("lowerClassName", StrUtil.lowerFirst(table_name));
            }
            while (rs.next()) {
                // 单独存储主键列
                if ("PRI".equals(rs.getString("column_key"))) {
                    velocityContext.put("pk_column_name", rs.getString("column_name"));
                    velocityContext.put("pk_data_type", rs.getString("data_type"));
                    velocityContext.put("pk_javaLowerAttrName", StrUtil.toCamelCase(rs.getString("column_name")));
                    velocityContext.put("pk_javaUpperAttrName", StrUtil.upperFirst(StrUtil.toCamelCase(rs.getString("column_name"))));
                    velocityContext.put("pk_javaAttrType", columnTypeToJavaType(rs.getString("data_type")));
                    velocityContext.put("pk_column_comment", rs.getString("column_comment"));
                    continue;
                }
                Map<String, Object> columnMap = new HashMap<>();
                // 列名字、数据类型、java属性名字、java属性类型、备注
                columnMap.put("column_name", rs.getString("column_name"));
                columnMap.put("data_type", rs.getString("data_type"));
                columnMap.put("javaLowerAttrName", StrUtil.toCamelCase(rs.getString("column_name")));
                columnMap.put("javaAttrType", columnTypeToJavaType(rs.getString("data_type")));
                columnMap.put("column_comment", rs.getString("column_comment"));
                columns.add(columnMap);
            }
        } catch (Exception e) {
            log.error("----------sql执行出错");
            e.printStackTrace();
            throw new RuntimeException("sql执行出错");
        }

        velocityContext.put("columns", columns);
        velocityContext.put("datetime", DateUtil.now());
        velocityContext.put("packageName", GenCodeConfig.getPackageName());
        // 设置importList
        velocityContext.put("importList", getImportList(columns));
        return velocityContext;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param columns 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<Map<String, Object>> columns) {
        HashSet<String> importList = new HashSet<>();
        for (Map<String, Object> columnMap : columns) {
            if (GenCodeConstants.TYPE_DATE.equals(columnMap.get("javaAttrType"))) {
                importList.add("java.util.Date");
            } else if (GenCodeConstants.TYPE_BIGDECIMAL.equals(columnMap.get("javaAttrType"))) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 数据库类型转换为java类型
     *
     * @param columnType 数据库类型
     * @return java类型
     */
    private static String columnTypeToJavaType(String columnType) {
        if (StrUtil.isNotEmpty(columnType)) {
            if (Arrays.asList(COLUMN_TYPE_STR).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TEXT).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TIME).contains(columnType)) {
                return TYPE_DATE;
            }
            if (Arrays.asList(COLUMN_TYPE_NUMBER).contains(columnType)) {
                return TYPE_INTEGER;
            }
            if (Arrays.asList(COLUMN_TYPE_BIGINT).contains(columnType)) {
                return TYPE_LONG;
            }
            if (Arrays.asList(COLUMN_TYPE_FLOAT).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DOUBLE).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DECIMAL).contains(columnType)) {
                return TYPE_BIGDECIMAL;
            }
        }
        return null;
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList() {
        return ListUtil.of("/templates/gen/entity.java.vm",
                "/templates/gen/service.java.vm",
                "/templates/gen/serviceImpl.java.vm",
                "/templates/gen/mapper.java.vm",
                "/templates/gen/mapper.xml.vm",
                "/templates/gen/controller.java.vm",
                "/templates/gen/bo.java.vm",
                "/templates/gen/vo.java.vm",
                "/templates/gen/convertor.java.vm");
    }

    /**
     * 获取文件名
     */
    public static String getFileName(VelocityContext velocityContext, String templatePath) {
        if (templatePath.contains("mapper.xml")) {
            return StringUtil.format("//mapper//xml//{}Mapper.xml", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("mapper.java")) {
            return StringUtil.format("//mapper//{}Mapper.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("entity")) {
            return StringUtil.format("{}//{}.java", GenCodeConfig.getEntityPath(), velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("bo")) {
            return StringUtil.format("//pojo//bo//{}Bo.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("vo")) {
            return StringUtil.format("//pojo//vo//{}Vo.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("convertor")) {
            return StringUtil.format("//convertors//{}Convertor.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("controller")) {
            return StringUtil.format("//controller//{}Controller.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("serviceImpl")) {
            return StringUtil.format("//service//impl//{}ServiceImpl.java", velocityContext.get("upperClassName"));
        }

        if (templatePath.contains("service")) {
            return StringUtil.format("//service//{}Service.java", velocityContext.get("upperClassName"));
        }

        throw new RuntimeException("不支持的模版格式");
    }
}
