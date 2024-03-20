package vip.openpark.freemarker.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author anthony
 * @version 2024/3/20 15:36
 */
@Slf4j
public class FreemarkerConfig {

    /**
     * 初始化 freemarker 配置
     * <a href="http://freemarker.foofun.cn/pgui_quickstart_createconfiguration.html">官方参考链接</a>
     *
     * @param templateRootPath 模版根目录
     * @return freemarker 配置
     */
    public static Configuration init(String templateRootPath) {
        Configuration configuration = new Configuration(Configuration.getVersion());
        try {
            // 指定模板文件的来源
            configuration.setDirectoryForTemplateLoading(new File(templateRootPath));
            // 设置默认的字符集编码
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            // 设置异常处理器
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (IOException e) {
            log.error("模板根路径获取失败!" + templateRootPath, e);
            throw new RuntimeException("模板根路径获取失败!" + templateRootPath, e);
        }
        return configuration;
    }

    /**
     * 将模版和数据进行合并，输出到指定的输出流中
     *
     * @param configuration freemarker 配置
     * @param templatePath  模板根目录（templateRootPath）下的具体模板路径
     * @param data          数据
     * @param charSet       字符集，默认 utf-8
     * @param outputStream  输出流
     */
    public static void mergeDataAndTemplate2OutputStream(Configuration configuration,
                                                         String templatePath,
                                                         Map<String, Object> data,
                                                         String charSet,
                                                         OutputStream outputStream) {
        if (null == charSet || charSet.isBlank()) {
            charSet = StandardCharsets.UTF_8.name();
        }

        try {
            // 获取具体的模版
            Template template = configuration.getTemplate(templatePath, charSet);
            // 获取输出流
            Writer writer = new OutputStreamWriter(outputStream);
            // 合并模版和数据
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("输出流关闭失败!", e);
                }
            }
        }
    }
}