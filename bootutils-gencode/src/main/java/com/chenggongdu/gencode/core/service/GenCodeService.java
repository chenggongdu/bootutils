package com.chenggongdu.gencode.core.service;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.chenggongdu.gencode.core.config.GenCodeConfig;
import com.chenggongdu.gencode.core.utils.VelocityInitializer;
import com.chenggongdu.gencode.core.utils.VelocityUtils;
import com.chenggongdu.utils.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.List;

/**
 * GenCodeService
 *
 * @author chenggongdu
 */
public class GenCodeService {

    /**
     * 代码生成
     *
     * @param tableNames 要生成的表名称,多个用英文逗号分割
     */
    public void genCode(String tableNames) {
        if (StringUtil.isEmpty(tableNames)) {
            throw new RuntimeException("表名不能为空");
        }
        if (StringUtil.isEmpty(GenCodeConfig.getDbUrl())) {
            throw new RuntimeException("数据库连接地址不能为空");
        }
        String[] tableNameList = tableNames.split(StringUtil.SEPARATOR);

        // 1 配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(GenCodeConfig.getDbUrl(),
                GenCodeConfig.getDbUsername(), GenCodeConfig.getDbPassword()).build();

        for (String tableName : tableNameList) {
            // 2. 设置模版信息
            VelocityContext velocityContext = VelocityUtils.prepareContext(tableName, dataSourceConfig);
            velocityContext.put("entityPackage", velocityContext.get("packageName").toString()
                    .concat(GenCodeConfig.getEntityPath().replaceAll("//", ".")));

            // 3. 获取模版信息
            List<String> templates = VelocityUtils.getTemplateList();

            // 4. 初始化vm加载方法
            VelocityInitializer.initVelocity();

            // 5.渲染模板
            for (String templatePath : templates) {
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(templatePath, "UTF-8");
                tpl.merge(velocityContext, sw);
                FileUtil.writeUtf8String(sw.toString(), getGenPath(velocityContext, templatePath));
            }
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param velocityContext 模版上下文
     * @param templatePath    模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(VelocityContext velocityContext, String templatePath) {
        // 生产文件的项目相对位置
        StringBuilder projectPath = new StringBuilder();
        // 获取系统路径
        String systemPath = System.getProperty("user.dir");
        // 将反斜杠全部替换为双斜杠 并拼接项目路径
        projectPath
                .append(systemPath.replaceAll("\\\\", "//"))
                .append(GenCodeConfig.getFileName());
        return projectPath.append(VelocityUtils.getFileName(velocityContext, templatePath)).toString();
    }
}
