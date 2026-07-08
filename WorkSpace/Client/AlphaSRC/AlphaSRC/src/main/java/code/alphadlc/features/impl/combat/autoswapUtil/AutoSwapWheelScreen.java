/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_437
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.features.impl.combat.autoswapUtil;

import code.alphadlc.features.impl.combat.AutoSwap;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class AutoSwapWheelScreen
extends class_437
implements QuickImports {
    private static final int SLOT_COUNT = 3;
    private static final float CENTER_Y_OFFSET = -6.0f;
    private static final float INNER_RING_R = 34.0f;
    private static final float OUTER_RING_R = 118.0f;
    private static final int SECTOR_BASE = -870703064;
    private static final int SECTOR_HOVER = -399163316;
    private static final int DIVIDER = 0x55FFFFFF;
    private final AutoSwap autoSwap;
    private int hoveredSlot = -1;
    private final class_1799[] displayStacks = new class_1799[3];

    public AutoSwapWheelScreen(AutoSwap autoSwap) {
        super((class_2561)class_2561.method_43473());
        this.autoSwap = autoSwap;
    }

    public boolean method_25421() {
        return false;
    }

    public boolean method_25422() {
        return true;
    }

    public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {
    }

    public void method_25419() {
        if (this.field_22787 != null) {
            this.field_22787.method_1507(null);
        }
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.method_25419();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (this.hoveredSlot < 0 || this.hoveredSlot >= 3) {
            return super.method_25402(mouseX, mouseY, button);
        }
        boolean slotEmpty = this.autoSwap.isWheelSlotEmpty(this.hoveredSlot);
        if (button == 0) {
            if (slotEmpty) {
                this.autoSwap.startSelectingItem(this.hoveredSlot);
            } else {
                class_1799 stack = this.displayStacks[this.hoveredSlot];
                if (stack != null && !stack.method_7960()) {
                    this.method_25419();
                    this.autoSwap.startSwapToItemStack(stack);
                }
            }
            return true;
        }
        if (button == 1 && !slotEmpty) {
            this.autoSwap.clearWheelSlot(this.hoveredSlot);
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int i;
        float centerX = (float)this.field_22789 / 2.0f;
        float centerY = (float)this.field_22790 / 2.0f + -6.0f;
        this.hoveredSlot = this.findHoveredSlot(mouseX, mouseY, centerX, centerY);
        context.method_25294(0, 0, this.field_22789, this.field_22790, 0x7C000000);
        float sectorSize = 120.0f;
        float startOffset = -90.0f;
        for (i = 0; i < 3; ++i) {
            float start = startOffset + (float)i * sectorSize;
            float end = start + sectorSize;
            int color = i == this.hoveredSlot ? -399163316 : -870703064;
            this.drawRingSector(context, centerX, centerY, 34.0f, 118.0f, start, end, color);
        }
        for (i = 0; i < 3; ++i) {
            this.drawRadialLine(context, centerX, centerY, 34.0f, 118.0f, startOffset + (float)i * sectorSize, 0x55FFFFFF);
        }
        this.renderCenterHub(context, centerX, centerY);
        this.renderSlotItems(context, centerX, centerY, startOffset, sectorSize);
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawCenteredString(context.method_51448(), "\u041b\u041a\u041c \u2014 \u0432\u044b\u0431\u0440\u0430\u0442\u044c / \u0434\u043e\u0431\u0430\u0432\u0438\u0442\u044c  |  \u041f\u041a\u041c \u2014 \u043e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0441\u043b\u043e\u0442", centerX, centerY + 118.0f + 28.0f, -1800947253);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void renderCenterHub(class_332 context, float centerX, float centerY) {
        float hub = 32.0f;
        rectangle.render(ShapeProperties.create(context.method_51448(), centerX - hub, centerY - hub, hub * 2.0f, hub * 2.0f).round(hub).thickness(1.0f).outlineColor(0x44FFFFFF).color(1428301872).build());
    }

    private void renderSlotItems(class_332 context, float centerX, float centerY, float startOffset, float sectorSize) {
        if (this.field_22787 == null || this.field_22787.field_1724 == null) {
            return;
        }
        float iconRadius = 82.08f;
        for (int i = 0; i < 3; ++i) {
            class_1799 stack;
            this.displayStacks[i] = stack = this.autoSwap.getWheelSlotStack(i);
            float midDeg = startOffset + (float)i * sectorSize + sectorSize * 0.5f;
            double rad = Math.toRadians(midDeg);
            float ix = centerX + (float)Math.cos(rad) * iconRadius;
            float iy = centerY + (float)Math.sin(rad) * iconRadius;
            context.method_51448().method_22903();
            context.method_51448().method_46416(ix, iy, 200.0f);
            if (!stack.method_7960()) {
                context.method_51448().method_22905(1.6f, 1.6f, 1.0f);
                context.method_51427(stack, -8, -8);
            } else {
                Fonts.getSize(18, Fonts.Type.SuisseIntlSemiBold).drawCenteredString(context.method_51448(), "+", 0.0, -6.0, -1996488705);
            }
            context.method_51448().method_22909();
        }
    }

    private int findHoveredSlot(double mouseX, double mouseY, float centerX, float centerY) {
        float dx = (float)mouseX - centerX;
        float dy = (float)mouseY - centerY;
        float dist = class_3532.method_15355((float)(dx * dx + dy * dy));
        if (dist < 34.0f || dist > 118.0f) {
            return -1;
        }
        float angleDeg = (float)((Math.toDegrees(Math.atan2(dy, dx)) + 360.0) % 360.0);
        float normalized = (angleDeg + 90.0f) % 360.0f;
        float sectorSize = 120.0f;
        int slot = (int)(normalized / sectorSize);
        return Math.min(slot, 2);
    }

    private void drawRingSector(class_332 context, float cx, float cy, float innerR, float outerR, float startDeg, float endDeg, int color) {
        Matrix4f matrix = context.method_51448().method_23760().method_23761();
        boolean cullWasEnabled = GL11.glIsEnabled((int)2884);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
        int segments = 48;
        float startRad = (float)Math.toRadians(startDeg);
        float endRad = (float)Math.toRadians(endDeg);
        float step = (endRad - startRad) / (float)segments;
        for (int i = 0; i < segments; ++i) {
            float a1 = startRad + step * (float)i;
            float a2 = startRad + step * (float)(i + 1);
            float cos1 = class_3532.method_15362((float)a1);
            float sin1 = class_3532.method_15374((float)a1);
            float cos2 = class_3532.method_15362((float)a2);
            float sin2 = class_3532.method_15374((float)a2);
            float ox1 = cx + outerR * cos1;
            float oy1 = cy + outerR * sin1;
            float ox2 = cx + outerR * cos2;
            float oy2 = cy + outerR * sin2;
            float ix1 = cx + innerR * cos1;
            float iy1 = cy + innerR * sin1;
            float ix2 = cx + innerR * cos2;
            float iy2 = cy + innerR * sin2;
            buffer.method_22918(matrix, ox1, oy1, 0.0f).method_39415(color);
            buffer.method_22918(matrix, ox2, oy2, 0.0f).method_39415(color);
            buffer.method_22918(matrix, ix2, iy2, 0.0f).method_39415(color);
            buffer.method_22918(matrix, ox1, oy1, 0.0f).method_39415(color);
            buffer.method_22918(matrix, ix2, iy2, 0.0f).method_39415(color);
            buffer.method_22918(matrix, ix1, iy1, 0.0f).method_39415(color);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        if (cullWasEnabled) {
            RenderSystem.enableCull();
        } else {
            RenderSystem.disableCull();
        }
        RenderSystem.disableBlend();
    }

    private void drawRadialLine(class_332 context, float cx, float cy, float innerR, float outerR, float angleDeg, int color) {
        float y1;
        float sin;
        float y2;
        float dy;
        float x1;
        float rad = (float)Math.toRadians(angleDeg);
        float cos = class_3532.method_15362((float)rad);
        float x2 = cx + outerR * cos;
        float dx = x2 - (x1 = cx + innerR * cos);
        float length = class_3532.method_15355((float)(dx * dx + (dy = (y2 = cy + outerR * (sin = class_3532.method_15374((float)rad))) - (y1 = cy + innerR * sin)) * dy));
        if (length <= 0.001f) {
            return;
        }
        float lineWidth = 1.0f;
        float perpX = -dy / length * lineWidth;
        float perpY = dx / length * lineWidth;
        Matrix4f matrix = context.method_51448().method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
        buffer.method_22918(matrix, x1 + perpX, y1 + perpY, 0.0f).method_39415(color);
        buffer.method_22918(matrix, x2 + perpX, y2 + perpY, 0.0f).method_39415(color);
        buffer.method_22918(matrix, x1 - perpX, y1 - perpY, 0.0f).method_39415(color);
        buffer.method_22918(matrix, x1 - perpX, y1 - perpY, 0.0f).method_39415(color);
        buffer.method_22918(matrix, x2 + perpX, y2 + perpY, 0.0f).method_39415(color);
        buffer.method_22918(matrix, x2 - perpX, y2 - perpY, 0.0f).method_39415(color);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.disableBlend();
    }
}
