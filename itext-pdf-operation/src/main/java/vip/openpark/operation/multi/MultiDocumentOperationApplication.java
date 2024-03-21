package vip.openpark.operation.multi;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;
import vip.openpark.operation.config.FontConfig;

import java.io.IOException;

/**
 * 多文档操作
 *
 * @author anthony
 * @version 2024/3/21 17:59
 */
@Slf4j
public class MultiDocumentOperationApplication {
    private static final String PDF_1_PATH = "pdf1.pdf";
    private static final String PDF_2_PATH = "pdf2.pdf";
    private static final String TARGET_PDF_PATH = "target.pdf";

    public static void main(String[] args) throws IOException {
        // 获取 target/classes 资源文件路径
        String folderPath = MultiDocumentOperationApplication.class.getClassLoader().getResource("").getPath();
        initPdf(folderPath);

        // 目标pdf
        PdfDocument dest = new PdfDocument(new PdfWriter(folderPath + TARGET_PDF_PATH));
        // 源pdf
        PdfDocument cover = new PdfDocument(new PdfReader(PDF_1_PATH));
        PdfDocument resource = new PdfDocument(new PdfReader(PDF_2_PATH));

        PdfMerger merger = new PdfMerger(dest);
        // 将源pdf文件指定位置写入到目标pdf中
        merger.merge(cover, 1, 1);
        merger.merge(resource, 1, 1);

        cover.close();
        resource.close();
        merger.close();
    }

    /**
     * 初始化PDF文件
     *
     * @param folderPath 文件夹路径
     */
    public static void initPdf(String folderPath) {
        initPdf1(folderPath);
        initPdf2(folderPath);
    }

    public static void initPdf1(String folderPath) {
        String filePath = folderPath + PDF_1_PATH;

        Document document = null;
        try {
            // 创建PDF输出流
            PdfWriter pdfWriter = new PdfWriter(filePath);
            // 创建 pdf 文档对象
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            // 创建文档对象
            document = new Document(pdfDocument);
            // 设置字体
            document.setFont(FontConfig.createFont());

            document.add(new Paragraph("君不见黄河之水天上，奔流到海不复回。"));
        } catch (Exception e) {
            log.error("PDF文件生成失败", e);
        } finally {
            // 关闭文档
            if (document != null) {
                document.close(); // 会级联关闭 IRenderer、PdfDocument、PdfWriter、PdfReader等
            }
        }
    }

    public static void initPdf2(String folderPath) {
        String filePath = folderPath + PDF_2_PATH;

        Document document = null;
        try {
            // 创建PDF输出流
            PdfWriter pdfWriter = new PdfWriter(filePath);
            // 创建 pdf 文档对象
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            // 创建文档对象
            document = new Document(pdfDocument);
            // 设置字体
            document.setFont(FontConfig.createFont());

            document.add(new Paragraph("君不见高堂明镜悲白发，朝如青丝暮成雪。"));
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