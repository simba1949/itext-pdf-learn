package vip.openpark.convert;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Objects;

/**
 * @author anthony
 * @version 2024/3/19 16:22
 */
@Slf4j
public class StaticHtml2PdfApplication {
    public static void main(String[] args) {
        // 获取当前模块的 classLoader
        ClassLoader classLoader = StaticHtml2PdfApplication.class.getClassLoader();
        // 获取当前模块下的 target/classes 资源文件路径
        String classesPath = Objects.requireNonNull(classLoader.getResource("")).getPath();

        // 源文件：静态 html 文件
        File staticHtmlFile = new File(classesPath + "static/StaticHtml2Pdf.html");
        // 目标文件
        File targetFile = new File(classesPath + "StaticHtml2Pdf.pdf");

        try {
            // 字体配置
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont("STSong-Light", "UniGB-UCS2-H");
            // 转换配置
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);
            // 转换
            HtmlConverter.convertToPdf(staticHtmlFile, targetFile, converterProperties);
        } catch (Exception e) {
            log.error("PDF文件生成失败", e);
        }
    }
}