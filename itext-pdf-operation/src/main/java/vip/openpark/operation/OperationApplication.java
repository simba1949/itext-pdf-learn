package vip.openpark.operation;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import lombok.extern.slf4j.Slf4j;
import vip.openpark.operation.core.*;

/**
 * @author anthony
 * @version 2024/3/19 15:52
 */
@Slf4j
public class OperationApplication {
    public static void main(String[] args) {
        // 获取 target/classes 资源文件路径
        String folderPath = OperationApplication.class.getClassLoader().getResource("").getPath();
        String filePath = folderPath + "PDF操作.pdf";

        Document document = null;
        try {
            // 创建PDF输出流
            PdfWriter pdfWriter = new PdfWriter(filePath);
            // 创建 pdf 文档对象
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            // 创建文档对象
            document = new Document(pdfDocument);
            // document 的操作
            DocumentOperation.document(document);

            // Paragraph 的操作
            document.add(ParagraphOperation.paragraph());

            // Table 操作
            Table table = TableOperation.table();
            // 背景操作
            BackgroundOperation.background(table);
            document.add(table);

            // 页眉和页脚操作
            HeaderAndFooterOperation.headerAndFooter(pdfDocument, document);
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