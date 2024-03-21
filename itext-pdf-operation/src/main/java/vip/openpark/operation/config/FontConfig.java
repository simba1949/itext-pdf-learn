package vip.openpark.operation.config;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

/**
 * @author anthony
 * @version 2024/3/21 15:29
 */
public class FontConfig {
    public static PdfFont createFont() throws IOException {
        // fontProgram：STSong-Light，encoding：UniGB-UCS2-H，是华文宋体的英文写法，横细竖粗，结构规范，常常用作排版用字
        // 可以解决中文写入 PDF 文件不显示的问题
        return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
    }
}