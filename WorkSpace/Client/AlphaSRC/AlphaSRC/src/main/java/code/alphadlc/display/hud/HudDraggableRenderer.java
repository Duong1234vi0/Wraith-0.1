/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_7833
 */
package code.alphadlc.display.hud;

import code.alphadlc.AlphaDLC;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.display.render.HudShine;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_7833;

public final class HudDraggableRenderer {
    private HudDraggableRenderer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void render(class_332 context) {
        class_310 client = class_310.method_1551();
        if (client == null || client.field_1724 == null || client.field_1687 == null || client.field_1690.field_1842) {
            return;
        }
        Hud hud = Hud.getInstance();
        if (hud == null || !hud.isState()) {
            return;
        }
        List<AbstractDraggable> draggables = AlphaDLC.getInstance().getDraggableRepository().draggable();
        int count = draggables.size();
        for (int i = 0; i < count; ++i) {
            AbstractDraggable draggable = draggables.get(i);
            if (draggable.canDraw(hud, draggable)) {
                draggable.startAnimation();
            } else {
                draggable.stopAnimation();
            }
            float alpha = draggable.getScaleAnimation().getOutput().floatValue() * Hud.setAlpha.getValue();
            if (draggable.isCloseAnimationFinished()) continue;
            draggable.updateEditorMotion();
            float offsetX = draggable.getRenderX() - (float)draggable.getX();
            float offsetY = draggable.getRenderY() - (float)draggable.getY();
            float sway = draggable.getSwayAngle();
            float centerX = (float)draggable.getX() + (float)draggable.getWidth() / 2.0f;
            float centerY = (float)draggable.getY() + (float)draggable.getHeight() / 2.0f;
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)class_3532.method_15363((float)alpha, (float)0.0f, (float)1.0f));
            try {
                float scale = draggable.getScaleFactor();
                context.method_51448().method_22903();
                context.method_51448().method_46416(offsetX, offsetY, 0.0f);
                context.method_51448().method_46416(centerX, centerY, 0.0f);
                context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(sway));
                context.method_51448().method_22905(scale, scale, 1.0f);
                context.method_51448().method_46416(-centerX, -centerY, 0.0f);
                Rectangle.beginBatch();
                try {
                    draggable.drawDraggable(context);
                }
                finally {
                    Rectangle.endBatch();
                }
                draggable.clampToScreen();
                if (Hud.shine.isValue() && draggable.visible() && Hud.shineElements.isSelected(draggable.getName())) {
                    int panels = Rectangle.panelCount();
                    String name = draggable.getName();
                    boolean onlyLargest = "Target Hud".equals(name) || "TotemBar".equals(name);
                    boolean exceptLargest = "ArmorBar".equals(name) || "CustomHotbar".equals(name);
                    int largest = -1;
                    if (onlyLargest || exceptLargest) {
                        float bestArea = -1.0f;
                        for (int p = 0; p < panels; ++p) {
                            float pw = Rectangle.panelWidth(p);
                            float ph = Rectangle.panelHeight(p);
                            if (!(pw >= 20.0f) || !(ph >= 12.0f) || !(pw * ph > bestArea)) continue;
                            bestArea = pw * ph;
                            largest = p;
                        }
                    }
                    for (int p = 0; p < panels; ++p) {
                        float pw = Rectangle.panelWidth(p);
                        float ph = Rectangle.panelHeight(p);
                        if (pw < 20.0f || ph < 12.0f || onlyLargest && p != largest || exceptLargest && p == largest) continue;
                        HudShine.render(context.method_51448(), Rectangle.panelX(p), Rectangle.panelY(p), pw, ph, Rectangle.panelRound(p), Hud.shineSize.getValue(), Hud.shineSpeed.getValue(), Hud.shineBrightness.getValue());
                    }
                }
                context.method_51448().method_22909();
                continue;
            }
            finally {
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }
}
