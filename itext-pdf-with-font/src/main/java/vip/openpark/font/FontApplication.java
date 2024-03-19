package vip.openpark.font;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author anthony
 * @version 2024/3/18 18:01
 */
@Slf4j
public class FontApplication {
    public static void main(String[] args) {
        // 获取 target/classes 资源文件路径
        String folderPath = FontApplication.class.getClassLoader().getResource("").getPath();
        String filePath = folderPath + "设置字体的PDF文件.pdf";
        log.info("PDF文件生成路径: {}", filePath);

        Document document = null;
        try {
            // 创建PDF输出流
            PdfWriter pdfWriter = new PdfWriter(filePath);
            // 创建 pdf 文档对象
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            // 创建文档对象
            document = new Document(pdfDocument);

            Paragraph paragraph = new Paragraph("君不见黄河之水天上，\n奔流到海不复回。");
            paragraph.setFont(createFont());
            document.add(paragraph);
        } catch (Exception e) {
            log.error("PDF文件生成失败", e);
        } finally {
            // 关闭文档
            if (document != null) {
                document.close(); // 会级联关闭 IRenderer、PdfDocument、PdfWriter、PdfReader等
            }
        }
    }

    private static final String ALIBABA_FONT = "./font/AlibabaPuHuiTi-3/AlibabaPuHuiTi-3-45-Light/AlibabaPuHuiTi-3-45-Light.ttf";
    private static final String SOURCE_HAN_SERIF = "./font/adobe-fonts/09_SourceHanSerifSC/OTF/SimplifiedChinese/SourceHanSerifSC-Bold.otf";

    /**
     * 创建字体
     * 常见字体，详见：{@link com.itextpdf.io.font.constants.StandardFonts}，itextpdf-io 模块下
     * <a href="https://kb.itextpdf.com/itext/language-specific-examples">官方参考文档</a>
     *
     * @return PdfFont
     * @throws IOException 找不到字体文件
     */
    private static PdfFont createFont() throws IOException {
        // fontProgram：STSong-Light，encoding：UniGB-UCS2-H，是华文宋体的英文写法，横细竖粗，结构规范，常常用作排版用字
        // 可以解决中文写入 PDF 文件不显示的问题
        // return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");

        // 使用阿里巴巴普惠体
        // 阿里普惠字体 官网下载地址；https://www.alibabafonts.com/#/font
        // return PdfFontFactory.createFont(ALIBABA_FONT, PdfEncodings.IDENTITY_H);

        // 使用思源黑体
        // SOURCE_HAN_SERIF github 下载地址：https://github.com/adobe-fonts/source-han-serif/releases
        return PdfFontFactory.createFont(SOURCE_HAN_SERIF, PdfEncodings.IDENTITY_H);
    }
}