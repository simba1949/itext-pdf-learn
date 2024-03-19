package vip.openpark.convert;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author anthony
 * @version 2024/3/19 16:22
 */
@Slf4j
public class StaticHtml2PdfApplication {
    public static void main(String[] args) {
        // 静态 html 文件
        File staticHtmlFile = new File("html-convert-pdf/src/main/resources/static/StaticHtml2Pdf.html");

        // 获取 target/classes 资源文件路径
        String folderPath = StaticHtml2PdfApplication.class.getClassLoader().getResource("").getPath();
        String filePath = folderPath + "StaticHtml2Pdf.pdf";
        log.info("PDF文件生成路径: {}", filePath);

        try {
            // 字体配置
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont("STSong-Light", "UniGB-UCS2-H");
            // 转换配置
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);
            // 转换
            HtmlConverter.convertToPdf(staticHtmlFile, new File(filePath), converterProperties);
        } catch (Exception e) {
            log.error("PDF文件生成失败", e);
        }
    }
}