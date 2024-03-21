package vip.openpark.operation.core;

import com.itextpdf.layout.Document;
import vip.openpark.operation.config.FontConfig;

import java.io.IOException;

/**
 * Document 操作
 *
 * @author anthony
 * @version 2024/3/21 15:30
 */
public class DocumentOperation {
    public static void document(Document document) throws IOException {
        // Document 设置字体
        document.setFont(FontConfig.createFont());
    }
}