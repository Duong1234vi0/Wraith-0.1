/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class InventoryHud
extends AbstractDraggable {
    private static final InventoryHud INSTANCE = new InventoryHud();
    private static final float SLOT_SIZE = 16.0f;
    private static final float SLOT_GAP = 2.0f;
    private static final float PANEL_PADDING = 5.0f;
    private static final int COLS = 9;
    private static final int ROWS = 3;

    private InventoryHud() {
        super("Inventory", 4, 80, 170, 62, true);
    }

    public static InventoryHud getInstance() {
        return INSTANCE;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean visible() {
        Hud hud = Hud.getInstance();
        if (hud == null) return false;
        if (!hud.isState()) return false;
        if (!Hud.interfaceSettings.isSelected("Inventory")) return false;
        return true;
    }

    @Override
    public void drawDraggable(class_332 context) {
        if (InventoryHud.mc.field_1724 == null) {
            return;
        }
        class_4587 matrix = context.method_51448();
        float gridWidth = 160.0f;
        float gridHeight = 52.0f;
        float panelWidth = gridWidth + 10.0f;
        float panelHeight = gridHeight + 10.0f;
        this.setWidth(Math.round(panelWidth));
        this.setHeight(Math.round(panelHeight));
        this.clampToScreen();
        float panelX = this.getX();
        float panelY = this.getY();
        this.drawPanelBackground(matrix, panelX, panelY, panelWidth, panelHeight);
        float startX = panelX + 5.0f;
        float startY = panelY + 5.0f;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int slot = col + row * 9 + 9;
                float slotX = startX + (float)col * 18.0f;
                float slotY = startY + (float)row * 18.0f;
                rectangle.render(ShapeProperties.create(matrix, slotX, slotY, 16.0, 16.0).round(3.0f).color(ThemeManager.BackgroundSettings.getColor()).build());
                class_1799 stack = InventoryHud.mc.field_1724.method_31548().method_5438(slot);
                if (stack.method_7960()) continue;
                Render2D.defaultDrawStack(context, stack, slotX + 1.0f, slotY + 1.0f, true, true, 0.85f);
            }
        }
    }

    private void drawPanelBackground(class_4587 matrix, float x, float y, float width, float height) {
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), x, y, width, height, 6.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, x, y, width, height).round(6.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
    }
}
