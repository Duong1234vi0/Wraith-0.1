/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class ServerHelperOverlay {
    private float posX = 30.0f;
    private float posY = 25.0f;

    public void render(class_332 context, String title, List<ServerHelperRenderItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        class_4587 matrices = context.method_51448();
        float headerHeight = 14.0f;
        float itemHeight = 11.5f;
        float padding = 2.0f;
        float iconSize = 8.0f;
        float separator = 0.5f;
        float contentWidth = padding * 2.0f;
        float[] itemWidths = new float[items.size()];
        for (int i = 0; i < items.size(); ++i) {
            ServerHelperRenderItem item = items.get(i);
            String bind = item.bindKey() != -1 ? StringHelper.getBindName(item.bindKey()) : "";
            float bindWidth = bind.isEmpty() ? 0.0f : Fonts.getSize(12).getStringWidth(bind);
            itemWidths[i] = 2.0f + iconSize + 2.0f + (bind.isEmpty() ? 0.0f : separator + 2.0f + bindWidth + 3.5f);
            contentWidth += itemWidths[i];
            if (i >= items.size() - 1) continue;
            contentWidth += separator;
        }
        float totalWidth = contentWidth;
        float totalHeight = headerHeight + itemHeight + padding * 2.0f;
        int bg = ColorAssist.getColor(30, 25, 40, 220);
        int header = ColorAssist.getColor(30, 25, 40, 255);
        int text = ColorAssist.getColor(180, 140, 255, 255);
        Render2D.drawQuad(this.posX, this.posY, totalWidth, totalHeight, bg);
        Render2D.drawQuad(this.posX, this.posY, totalWidth, headerHeight, header);
        Fonts.getSize(15).drawString(matrices, title, this.posX + 4.5f, this.posY + 5.5f, text);
        float itemsY = this.posY + headerHeight + 1.0f;
        float currentX = this.posX + (totalWidth - contentWidth) / 2.0f + padding;
        for (int i = 0; i < items.size(); ++i) {
            String bind;
            ServerHelperRenderItem item = items.get(i);
            float itemWidth = itemWidths[i];
            int itemBg = ColorAssist.getColor(50, 50, 50, 178);
            Render2D.drawQuad(currentX, itemsY, itemWidth, itemHeight, itemBg);
            float iconX = currentX + 2.0f;
            float iconY = itemsY + (itemHeight - iconSize) / 2.0f;
            class_1799 stack = item.icon().method_7854();
            if (item.hasTint()) {
                Render2D.defaultDrawStack(context, stack, iconX, iconY, false, false, 0.45f);
            } else {
                Render2D.defaultDrawStack(context, stack, iconX, iconY, false, false, 0.45f);
            }
            String string = bind = item.bindKey() != -1 ? StringHelper.getBindName(item.bindKey()) : "";
            if (!bind.isEmpty()) {
                float bindX = iconX + iconSize + 2.0f + separator + 2.0f;
                Fonts.getSize(12).drawString(matrices, bind, bindX, itemsY + 4.5f, text);
            }
            currentX += itemWidth + separator;
        }
    }
}
