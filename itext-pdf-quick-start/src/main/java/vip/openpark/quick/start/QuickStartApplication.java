package vip.openpark.quick.start;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

/**
 * @author anthony
 * @version 2024/3/18 16:22
 */
@Slf4j
public class QuickStartApplication {
    public static void main(String[] args) throws FileNotFoundException {
        // 获取 target/classes 资源文件路径
        String folderPath = QuickStartApplication.class.getClassLoader().getResource("").getPath();

        Document document = null;
        try {
            // 创建PDF输出流
            PdfWriter pdfWriter = new PdfWriter(folderPath + "/快速入门生成的PDF文件.pdf");
            // 创建 pdf 文档对象
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            // 创建文档对象
            document = new Document(pdfDocument);

            document.add(new Paragraph("Hello PDF!"));
        } catch (Exception e) {
            log.error("PDF文件生成失败", e);
        } finally {
            // 关闭文档
            if (document != null) {
                document.close(); // 会级联关闭 IRenderer、PdfDocument、PdfWriter、PdfReader等
            }
        }
    }
}