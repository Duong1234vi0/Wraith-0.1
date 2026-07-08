/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2561
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_898
 *  net.minecraft.class_9801
 *  org.apache.logging.log4j.LogManager
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.display.screens;

import code.alphadlc.emotions.EmotionType;
import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.features.impl.misc.Globals;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_898;
import net.minecraft.class_9801;
import org.apache.logging.log4j.LogManager;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.lwjgl.opengl.GL11;

public class EmotionWheelScreen
extends class_437
implements QuickImports {
    private static final EmotionType[] WHEEL_SLOTS = EmotionType.values();
    private static final float CENTER_Y_OFFSET = -6.0f;
    private static final float INNER_RING_R = 72.0f;
    private static final float OUTER_RING_R = 178.0f;
    private static final int SECTOR_BASE = -870703064;
    private static final int SECTOR_HOVER = -399163316;
    private static final int DIVIDER = 0x55FFFFFF;
    private final EmotionWheelManager manager;
    private int hoveredSlot = -1;

    public EmotionWheelScreen(EmotionWheelManager manager) {
        super((class_2561)class_2561.method_43473());
        this.manager = manager;
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int i;
        float centerX = (float)this.field_22789 / 2.0f;
        float centerY = (float)this.field_22790 / 2.0f + -6.0f;
        this.hoveredSlot = this.findHoveredSlot(mouseX, mouseY, centerX, centerY);
        EmotionType preview = this.getHoveredEmotion() != null ? this.getHoveredEmotion() : EmotionType.HELLO;
        this.manager.setPreviewEmotion(preview);
        context.method_25294(0, 0, this.field_22789, this.field_22790, 0x7C000000);
        float sectorSize = EmotionWheelScreen.sectorSizeDeg();
        float startOffset = -90.0f;
        for (i = 0; i < WHEEL_SLOTS.length; ++i) {
            float start = startOffset + (float)i * sectorSize;
            float end = start + sectorSize;
            int color = i == this.hoveredSlot ? -399163316 : -870703064;
            this.drawRingSector(context, centerX, centerY, 72.0f, 178.0f, start, end, color);
        }
        for (i = 0; i < WHEEL_SLOTS.length; ++i) {
            this.drawRadialLine(context, centerX, centerY, 72.0f, 178.0f, startOffset + (float)i * sectorSize, 0x55FFFFFF);
        }
        this.renderCenterHub(context, centerX, centerY);
        if (EmotionWheelScreen.mc.field_1724 != null) {
            this.drawEntityPreview(context, centerX, centerY + 8.0f, 36.0f, 180.0f, 6.0f, (class_1309)EmotionWheelScreen.mc.field_1724);
        }
        this.renderInnerText(context, centerX, centerY, preview);
        this.renderSectorLabels(context, centerX, centerY, startOffset, sectorSize);
        Fonts.getSize(11, Fonts.Type.SuisseIntlMedium).drawCenteredString(context.method_51448(), "\u0423\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0439 " + this.getBindName() + " \u0438 \u043e\u0442\u043f\u0443\u0441\u0442\u0438 \u043d\u0430 \u043d\u0443\u0436\u043d\u043e\u0439 \u044d\u043c\u043e\u0446\u0438\u0438", centerX, centerY + 178.0f + 36.0f, -1800947253);
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void renderCenterHub(class_332 context, float centerX, float centerY) {
        float hub = 70.0f;
        rectangle.render(ShapeProperties.create(context.method_51448(), centerX - hub, centerY - hub, hub * 2.0f, hub * 2.0f).round(hub).thickness(1.0f).outlineColor(0x44FFFFFF).color(-14538704).build());
    }

    private void renderInnerText(class_332 context, float centerX, float centerY, EmotionType preview) {
        class_4587 matrices = context.method_51448();
        FontRenderer titleFont = Fonts.getSize(13, Fonts.Type.SuisseIntlSemiBold);
        FontRenderer descFont = Fonts.getSize(10, Fonts.Type.SuisseIntlMedium);
        float textY = centerY + 34.0f;
        titleFont.drawCenteredString(matrices, preview.getTitle(), centerX, textY, -1);
        EmotionWheelScreen.drawWrappedCenteredText(matrices, descFont, preview.getDescription(), centerX, textY + 12.0f, 118.0f, -1177888794);
    }

    private void renderSectorLabels(class_332 context, float centerX, float centerY, float startOffset, float sectorSize) {
        class_4587 matrices = context.method_51448();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        FontRenderer font = Fonts.getSize(14, Fonts.Type.SuisseIntlSemiBold);
        float labelRadius = 135.0f;
        float maxWidth = (float)(Math.PI * 2 * (double)labelRadius * ((double)sectorSize / 360.0) * (double)0.88f);
        for (int i = 0; i < WHEEL_SLOTS.length; ++i) {
            float midDeg = startOffset + (float)i * sectorSize + sectorSize * 0.5f;
            double rad = Math.toRadians(midDeg);
            float lx = centerX + (float)Math.cos(rad) * labelRadius;
            float ly = centerY + (float)Math.sin(rad) * labelRadius;
            int color = i == this.hoveredSlot ? -1 : -985857;
            EmotionWheelScreen.drawSectorLabel(font, matrices, WHEEL_SLOTS[i].getTitle(), lx, ly, maxWidth, color);
        }
    }

    private static void drawSectorLabel(FontRenderer font, class_4587 matrices, String text, float lx, float ly, float maxWidth, int color) {
        float lineHeight = font.getStringHeight("Ag") + 2.0f;
        if (font.getStringWidth(text) <= maxWidth) {
            float visualH = font.getStringHeight(text);
            float drawY = ly - visualH / 2.0f + 3.0f;
            font.drawCenteredString(matrices, text, lx, drawY, color);
            return;
        }
        int split = text.indexOf(32);
        if (split > 0) {
            String line1 = text.substring(0, split);
            String line2 = EmotionWheelScreen.fitLabel(font, text.substring(split + 1), maxWidth);
            if (font.getStringWidth(line1) <= maxWidth && font.getStringWidth(line2) <= maxWidth) {
                float totalH = lineHeight * 2.0f;
                float topY = ly - totalH / 2.0f + 3.0f;
                font.drawCenteredString(matrices, line1, lx, topY, color);
                font.drawCenteredString(matrices, line2, lx, topY + lineHeight, color);
                return;
            }
        }
        String fitted = EmotionWheelScreen.fitLabel(font, text, maxWidth);
        float fittedH = font.getStringHeight(fitted);
        font.drawCenteredString(matrices, fitted, lx, ly - fittedH / 2.0f + 3.0f, color);
    }

    private static String fitLabel(FontRenderer font, String text, float maxWidth) {
        if (font.getStringWidth(text) <= maxWidth) {
            return text;
        }
        String trimmed = text;
        while (trimmed.length() > 1 && font.getStringWidth(trimmed + "\u2026") > maxWidth) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }
        return trimmed + "\u2026";
    }

    private static float sectorSizeDeg() {
        return 360.0f / (float)WHEEL_SLOTS.length;
    }

    private int findHoveredSlot(double mouseX, double mouseY, float centerX, float centerY) {
        float dx = (float)mouseX - centerX;
        float dy = (float)mouseY - centerY;
        float dist = class_3532.method_15355((float)(dx * dx + dy * dy));
        if (dist < 72.0f || dist > 178.0f) {
            return -1;
        }
        float angleDeg = (float)((Math.toDegrees(Math.atan2(dy, dx)) + 360.0) % 360.0);
        float normalized = (angleDeg + 90.0f) % 360.0f;
        float sectorSize = EmotionWheelScreen.sectorSizeDeg();
        int slot = (int)(normalized / sectorSize);
        return Math.min(slot, WHEEL_SLOTS.length - 1);
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

    private static void drawWrappedCenteredText(class_4587 matrices, FontRenderer font, String text, float centerX, float y, float maxWidth, int color) {
        if (text == null || text.isBlank()) {
            return;
        }
        if (font.getStringWidth(text) <= maxWidth) {
            font.drawCenteredString(matrices, text, centerX, y, color);
            return;
        }
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float lineY = y;
        float lineHeight = font.getStringHeight("Ag") + 2.0f;
        for (String word : words) {
            String candidate;
            String string = candidate = line.isEmpty() ? word : String.valueOf(line) + " " + word;
            if (font.getStringWidth(candidate) > maxWidth && !line.isEmpty()) {
                font.drawCenteredString(matrices, line.toString(), centerX, lineY, color);
                line = new StringBuilder(word);
                lineY += lineHeight;
                continue;
            }
            if (!line.isEmpty()) {
                line.append(' ');
            }
            line.append(word);
        }
        if (!line.isEmpty()) {
            font.drawCenteredString(matrices, line.toString(), centerX, lineY, color);
        }
    }

    public void method_25419() {
        this.manager.closeWheel(false);
    }

    public EmotionType getHoveredEmotion() {
        if (this.hoveredSlot < 0 || this.hoveredSlot >= WHEEL_SLOTS.length) {
            return null;
        }
        return WHEEL_SLOTS[this.hoveredSlot];
    }

    private String getBindName() {
        Globals globals = Instance.get(Globals.class);
        if (globals == null) {
            return "\u043a\u043d\u043e\u043f\u043a\u0443";
        }
        int key = globals.getEmotionWheelBind().getKey();
        if (key == -1) {
            return "\u043a\u043d\u043e\u043f\u043a\u0443";
        }
        String translation = class_3675.class_307.field_1668.method_1447(key).method_27445().getString();
        return translation == null || translation.isBlank() ? "\u043a\u043d\u043e\u043f\u043a\u0443" : translation;
    }

    private void drawEntityPreview(class_332 context, float x, float y, float size, float yaw, float pitch, class_1309 entity) {
        float wrappedYaw;
        class_4587 matrices = context.method_51448();
        matrices.method_22903();
        matrices.method_22904((double)x, (double)y, 1050.0);
        matrices.method_22905(1.0f, 1.0f, -1.0f);
        matrices.method_22905(size, size, size);
        float pitchRad = (float)Math.toRadians(pitch);
        Quaternionf cameraRotation = new Quaternionf().rotateX(-pitchRad);
        Quaternionf entityRotation = new Quaternionf().rotateZ((float)Math.PI).mul((Quaternionfc)cameraRotation);
        matrices.method_22907(entityRotation);
        float prevBodyYaw = entity.field_6283;
        float prevYaw = entity.method_36454();
        float prevPitch = entity.method_36455();
        float prevHeadYaw = entity.field_6241;
        float prevPrevHeadYaw = entity.field_6259;
        float prevPrevBodyYaw = entity.field_6220;
        float prevPrevYaw = entity.field_5982;
        float prevPrevPitch = entity.field_6004;
        float bodyYaw = wrappedYaw = class_3532.method_15393((float)yaw);
        float modelPitch = -pitch * 0.9f;
        entity.field_6283 = bodyYaw;
        entity.field_6220 = bodyYaw;
        entity.method_36456(bodyYaw);
        entity.field_5982 = bodyYaw;
        entity.method_36457(modelPitch);
        entity.field_6004 = modelPitch;
        entity.field_6241 = bodyYaw;
        entity.field_6259 = bodyYaw;
        class_898 dispatcher = mc.method_1561();
        dispatcher.method_24196(new Quaternionf((Quaternionfc)cameraRotation).conjugate());
        dispatcher.method_3948(false);
        RenderSystem.enableDepthTest();
        class_4597.class_4598 vertexConsumers = mc.method_22940().method_23000();
        try {
            dispatcher.method_62424((class_1297)entity, 0.0, 0.0, 0.0, 0.0f, matrices, (class_4597)vertexConsumers, 0xF000F0);
            if (vertexConsumers instanceof class_4597.class_4598) {
                class_4597.class_4598 immediate = vertexConsumers;
                immediate.method_22993();
            } else {
                context.method_51452();
            }
        }
        catch (Throwable t) {
            LogManager.getLogger((String)"AlphaDLC").error("Failed to render emotion wheel entity preview", t);
        }
        dispatcher.method_24196(new Quaternionf());
        dispatcher.method_3948(true);
        RenderSystem.disableDepthTest();
        entity.field_6283 = prevBodyYaw;
        entity.field_6220 = prevPrevBodyYaw;
        entity.method_36456(prevYaw);
        entity.field_5982 = prevPrevYaw;
        entity.method_36457(prevPitch);
        entity.field_6004 = prevPrevPitch;
        entity.field_6241 = prevHeadYaw;
        entity.field_6259 = prevPrevHeadYaw;
        matrices.method_22909();
    }
}
