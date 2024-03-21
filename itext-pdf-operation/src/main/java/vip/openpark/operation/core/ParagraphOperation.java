package vip.openpark.operation.core;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import vip.openpark.operation.config.FontConfig;

import java.io.IOException;

/**
 * Paragraph 操作
 *
 * @author anthony
 * @version 2024/3/21 15:24
 */
public class ParagraphOperation {
    public static Paragraph paragraph() throws IOException {
        Paragraph paragraph = new Paragraph();
        // 添加文本
        paragraph.add(createText());
        // 设置下划线
        paragraph.setUnderline();
        // 设置首行缩进
        paragraph.setFirstLineIndent(40f);
        // 设置行间距
        paragraph.setMultipliedLeading(1.5f);
        // 设置文本对齐方式
        paragraph.setTextAlignment(TextAlignment.LEFT);

        // 设置字体
        paragraph.setFont(FontConfig.createFont());
        // 设置粗体
        paragraph.setBold();
        // 设置字体大小
        paragraph.setFontSize(16f);
        // 设置字体颜色
        paragraph.setFontColor(ColorConstants.RED);

        return paragraph;
    }

    public static String createText() {
        return "马克思以前的唯物论，离开人的社会性，离开人的历史发展，去观察认识问题，因此不能了解认识对社会实践的依赖关系，即认识对生产和阶级斗争的依赖关系。" +
                   "首先，马克思主义者认为人类的生产活动是最基本的实践活动，是决定其他一切活动的东西。人的认识，主要地依赖于物质的生产活动，逐渐地了解自然的现象、自然的性质、自然的规律性、人和自然的关系；而且经过生产活动，也在各种不同程度上逐渐地认识了人和人的一定的相互关系。一切这些知识，离开生产活动是不能得到的。在没有阶级的社会中，每个人以社会一员的资格，同其他社会成员协力，结成一定的生产关系，从事生产活动，以解决人类物质生活问题。在各种阶级的社会中，各阶级的社会成员，则又以各种不同的方式，结成一定的生产关系，从事生产活动，以解决人类物质生活问题。这是人的认识发展的基本来源。" +
                   "人的社会实践，不限于生产活动一种形式，还有多种其他的形式，阶级斗争，政治生活，科学和艺术的活动，总之社会实际生活的一切领域都是社会的人所参加的。因此，人的认识，在物质生活以外，还从政治生活文化生活中（与物质生活密切联系），在各种不同程度上，知道人和人的各种关系。其中，尤以各种形式的阶级斗争，给予人的认识发展以深刻的影响。在阶级社会中，每一个人都在一定的阶级地位中生活，各种思想无不打上阶级的烙印。" +
                   "马克思主义者认为人类社会的生产活动，是一步又一步地由低级向高级发展，因此，人们的认识，不论对于自然界方面，对于社会方面，也都是一步又一步地由低级向高级发展，即由浅入深，由片面到更多的方面。在很长的历史时期内，大家对于社会的历史只能限于片面的了解，这一方面是由于剥削阶级的偏见经常歪曲社会的历史，另方面，则由于生产规模的狭小，限制了人们的眼界。人们能够对于社会历史的发展作全面的历史的了解，把对于社会的认识变成了科学，这只是到了伴随巨大生产力——大工业而出现近代无产阶级的时候，这就是马克思主义的科学。";
    }
}