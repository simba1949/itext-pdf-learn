package vip.openpark.operation.core;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.properties.BackgroundImage;

import java.net.MalformedURLException;

/**
 * 设置背景
 * {@link ElementPropertyContainer} 提供设置背景图方法
 *
 * @author anthony
 * @version 2024/3/21 16:58
 */
public class BackgroundOperation {
    public static void background(ElementPropertyContainer propertyContainer) throws MalformedURLException {
        String classesPath = BackgroundOperation.class.getClassLoader().getResource("").getPath();
        String imagePath = classesPath + "static/images/backgroud.png";
        // 创建背景图片
        ImageData imageData = ImageDataFactory.create(imagePath);
        PdfImageXObject pdfImageXObject = new PdfImageXObject(imageData);
        // 创建背景
        BackgroundImage backgroundImage =
            new BackgroundImage.Builder()
                .setImage(pdfImageXObject)
                .build();
        // 设置背景
        propertyContainer.setBackgroundImage(backgroundImage);
    }
}