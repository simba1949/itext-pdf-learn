package vip.openpark.freemarker;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import vip.openpark.freemarker.config.FreemarkerConfig;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * @author anthony
 * @version 2024/3/20 15:30
 */
@Slf4j
public class FreemarkerApplication {
    /**
     * freemarker 转换成 PDF，思路如下：
     * freemarker 生成的 html 内容 ————> byteArrayOutputStream ————> byteArrayInputStream ————> File targetFile
     */
    public static void main(String[] args) {
        // 获取当前模块的 classLoader
        ClassLoader classLoader = FreemarkerApplication.class.getClassLoader();
        // 获取当前模块下的 target/classes 资源文件路径
        String classesPath = Objects.requireNonNull(classLoader.getResource("")).getPath();

        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        OutputStream targetFileOutputStream = null;
        try {
            //------------以下是 freemarker 生成 html 内容，并将 html 内容转成输入输出流 -------------------
            // ByteArrayOutputStream 作为临时输出流，freemarker 生成的 html 内容写入到 ByteArrayOutputStream
            byteArrayOutputStream = new ByteArrayOutputStream();
            // freemarker 将数据和模板生成 html 内容
            Configuration configuration = FreemarkerConfig.init(classesPath + "template");
            FreemarkerConfig.mergeDataAndTemplate2OutputStream(configuration, "freemarker2Pdf.ftl", buildData(), "UTF-8", byteArrayOutputStream);
            // 将 ByteArrayOutputStream 读取到 ByteArrayInputStream
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            //------------------------以下是生成PDF---------------------------------------
            // 目标文件
            File targetFile = new File(classesPath + "StaticHtml2Pdf.pdf");
            targetFileOutputStream = Files.newOutputStream(targetFile.toPath());
            // 字体配置
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont("STSong-Light", "UniGB-UCS2-H");
            // 转换配置
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);
            // 将 html 转换成 PDF
            HtmlConverter.convertToPdf(byteArrayInputStream, targetFileOutputStream, converterProperties);
        } catch (IOException e) {
            log.info("目标文件路径转换成输出流异常", e);
        } finally {
            if (targetFileOutputStream != null) {
                try {
                    targetFileOutputStream.close();
                } catch (IOException e) {
                    log.error("生成PDF文件的输出流关闭异常", e);
                }
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    log.error("输入流关闭异常", e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    log.error("输出流关闭异常", e);
                }
            }
        }
    }

    /**
     * 构建数据
     *
     * @return freemarker 所需数据
     */
    private static Map<String, Object> buildData() {
        HashMap<String, Object> poetryMap = new HashMap<>();
        poetryMap.put("title", "行路难");
        poetryMap.put("poetName", "李白");

        List<String> contents = new ArrayList<>();
        contents.add("金樽清酒斗十千，玉盘珍羞直万钱。");
        contents.add("停杯投箸不能食，拔剑四顾心茫然。");
        contents.add("欲渡黄河冰塞川，将登太行雪满山。");
        contents.add("闲来垂钓碧溪上，忽复乘舟梦日边。");
        contents.add("行路难，行路难，多歧路，今安在？");
        contents.add("长风破浪会有时，直挂云帆济沧海。");

        poetryMap.put("contents", contents);
        return poetryMap;
    }
}