/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_4587
 */
package code.alphadlc.common.repository.way;

import code.alphadlc.common.repository.way.Way;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_4587;

public class WayRepository
implements QuickImports,
QuickLogger {
    public List<Way> wayList = new ArrayList<Way>();

    public WayRepository(EventManager eventManager) {
        EventManager.register(this);
    }

    public boolean isEmpty() {
        return this.wayList.isEmpty();
    }

    public void addWay(String name, class_2338 pos, String server) {
        this.wayList.add(new Way(name, pos, server));
    }

    public void upsertWay(String name, class_2338 pos, String server) {
        if (name == null) {
            return;
        }
        for (int i = 0; i < this.wayList.size(); ++i) {
            Way w = this.wayList.get(i);
            if (w == null || !w.name().equalsIgnoreCase(name)) continue;
            this.wayList.set(i, new Way(name, pos, server));
            return;
        }
        this.addWay(name, pos, server);
    }

    public boolean hasWay(String text) {
        return this.wayList.stream().anyMatch(s -> s.name().equalsIgnoreCase(text));
    }

    public void deleteWay(String name) {
        this.wayList.removeIf(macro -> macro.name().equalsIgnoreCase(name));
    }

    public void clearList() {
        if (!this.isEmpty()) {
            this.wayList.clear();
        }
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (this.isEmpty() || WayRepository.mc.field_1724 == null) {
            return;
        }
        String currentServer = mc.method_1562() == null ? "vanilla" : (mc.method_1562().method_45734() == null ? "vanilla" : WayRepository.mc.method_1562().method_45734().field_3761);
        class_4587 matrix = e.getDrawContext().method_51448();
        double[] screen = new double[3];
        RenderSystem.disableDepthTest();
        this.wayList.forEach(way -> {
            if (!way.server().equalsIgnoreCase(currentServer)) {
                return;
            }
            class_243 wayVec = way.pos().method_46558();
            if (!Projection.isInFrontOfCamera(wayVec)) {
                return;
            }
            if (!Projection.worldSpaceToScreenSpaceInto(wayVec.field_1352, wayVec.field_1351, wayVec.field_1350, screen)) {
                return;
            }
            double distance = WayRepository.mc.method_1561().field_4686.method_19326().method_1022(wayVec);
            String text = way.name() + " - " + Math.round(distance) + "m";
            FontRenderer font = Fonts.getSize(10, Fonts.Type.SuisseIntlMedium);
            float textWidth = font.getStringWidth(text);
            float textHeight = font.getStringHeight(text);
            float paddingX = 5.0f;
            float paddingY = 3.0f;
            float boxWidth = textWidth + paddingX * 2.0f;
            float boxHeight = textHeight + paddingY * 2.0f;
            float x = (float)screen[0] - boxWidth / 2.0f;
            float y = (float)screen[1] - boxHeight / 2.0f;
            rectangle.render(ShapeProperties.create(matrix, x, y, boxWidth, boxHeight).round(4.0f).color(new Color(0, 0, 0, 255).getRGB()).build());
            rectangle.render(ShapeProperties.create(matrix, x, y, boxWidth, boxHeight).round(4.0f).thickness(3.0f).outlineColor(new Color(220, 225, 235, 235).getRGB()).color(0).build());
            font.drawCenteredBoth(matrix, text, x + boxWidth / 2.0f, y, boxHeight, ColorAssist.getText());
        });
        RenderSystem.enableDepthTest();
    }
}
