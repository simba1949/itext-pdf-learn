package vip.openpark.operation.core;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * 页眉和页脚
 * <a href="https://kb.itextpdf.com/itext/header-and-footer-examples">官方参考文档</a>
 *
 * @author anthony
 * @version 2024/3/21 16:03
 */
public class HeaderAndFooterOperation {
    public static void headerAndFooter(PdfDocument pdfDocument, Document document) {
        // 页眉文本
        Paragraph headerParagraph = new Paragraph("实践论 论认识和实践的关系——知和行的关系");

        // 页码计数从1开始
        int totalPage = pdfDocument.getNumberOfPages();
        for (int i = 1; i <= totalPage; i++) {
            PdfPage pdfPage = pdfDocument.getPage(i);
            Rectangle pageSize = pdfPage.getPageSize();
            // 计算页眉位置
            float headerX = pageSize.getWidth() / 2;
            float headerY = pageSize.getTop() - 20;
            document.showTextAligned(headerParagraph, headerX, headerY, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

            // 页脚文本
            Paragraph footerParagraph = new Paragraph(i + "/" + totalPage);
            // 计算页脚位置
            float footerX = pageSize.getWidth() / 2;
            float footerY = pageSize.getBottom() + 20;
            document.showTextAligned(footerParagraph, footerX, footerY, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
        }
    }
}