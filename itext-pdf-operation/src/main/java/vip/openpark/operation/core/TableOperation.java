package vip.openpark.operation.core;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

/**
 * Table 操作
 *
 * @author anthony
 * @version 2024/3/21 15:44
 */
public class TableOperation {
    public static Table table() {
        // 创建几列的表格对象
        Table table = new Table(4);
        // 设置table表格宽度
        table.setWidth(UnitValue.POINT).setWidth(520);

        for (int i = 0; i < 100; i++) {
            if (i == 0) {
                // 创建 Cell 对象，默认一行一列
                Cell cell00 = new Cell();
                cell00.add(new Paragraph("姓名"));
                table.addCell(cell00);

                table.addCell(new Cell().add(new Paragraph("李白")));

                table.addCell(new Cell().add(new Paragraph("性别")));
                table.addCell(new Cell().add(new Paragraph("男")));
            } else if (i == 1) {
                // 第二行数据
                table.addCell(new Cell().add(new Paragraph("代表作")));
                // 第二行数据，创建 Cell 对象，默认一行三列
                table.addCell(new Cell(1, 3).add(new Paragraph("《将进酒》《蜀道难》")));
            } else {
                table.addCell(new Cell(1, 4).add(new Paragraph("")));
            }
        }
        // 设置文本内容居中
        table.setTextAlignment(TextAlignment.CENTER);

        return table;
    }
}