/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.math.calc.CalcVector;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_9801;
import org.joml.Matrix4f;

public class HitRange
extends Module {
    static final int SIDES = 8;
    static final float Y_OFFSET = 0.02f;
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442").setColor(new Color(80, 180, 255, 200).getRGB());
    private final SliderSettings thickness = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430").setValue(0.06f).range(0.01f, 0.5f);
    private final SliderSettings opacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043a\u043e\u043b\u044c\u0446\u0430").setValue(0.85f).range(0.1f, 1.0f).step(0.05f);
    private final SliderSettings radius = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043a\u043e\u043b\u044c\u0446\u0430 \u0432 \u0431\u043b\u043e\u043a\u0430\u0445").setValue(3.32f).range(1.0f, 6.0f).step(0.1f);
    private final SliderSettings rotSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f").setValue(0.4f).range(0.05f, 2.0f);
    private final BooleanSetting depthTest = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0437\u0430 \u0431\u043b\u043e\u043a\u0430\u043c\u0438", "\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0437\u0430 \u0431\u043b\u043e\u043a\u0430\u043c\u0438").setValue(true);
    private float rotationAngle = 0.0f;

    public HitRange() {
        super("HitRange", "Hit Range", ModuleCategory.RENDER);
        this.setup(this.color, this.thickness, this.opacity, this.radius, this.rotSpeed, this.depthTest);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (HitRange.mc.field_1724 == null || HitRange.mc.field_1687 == null) {
            return;
        }
        float hitRadius = this.radius.getValue();
        float hitRadiusSq = hitRadius * hitRadius;
        boolean enemyNear = false;
        for (class_1657 p : HitRange.mc.field_1687.method_18456()) {
            if (p == HitRange.mc.field_1724 || !(p.method_5858((class_1297)HitRange.mc.field_1724) <= (double)hitRadiusSq)) continue;
            enemyNear = true;
            break;
        }
        this.rotationAngle = (this.rotationAngle + this.rotSpeed.getValue()) % 360.0f;
        class_243 origin = CalcVector.lerpPosition((class_1297)HitRange.mc.field_1724);
        double y = origin.field_1351 + (double)0.02f;
        float alphaMul = this.opacity.getValue();
        int ringColor = enemyNear ? ColorAssist.multAlpha(new Color(255, 45, 45, 220).getRGB(), alphaMul) : ColorAssist.multAlpha(this.color.getColor(), alphaMul);
        int glowColor = ColorAssist.multAlpha(ringColor, 0.25f);
        double rotRad = Math.toRadians(this.rotationAngle);
        float half = this.thickness.getValue() / 2.0f;
        boolean depth = this.depthTest.isValue();
        class_4587 matrix = e.getStack();
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int i = 0; i < 8; ++i) {
            double a1 = rotRad + Math.PI * 2 * (double)i / 8.0;
            double a2 = rotRad + Math.PI * 2 * (double)(i + 1) / 8.0;
            double outerR = hitRadius + half;
            double innerR = hitRadius - half;
            HitRange.appendRingQuad(buffer, matrix4f, origin.field_1352, y, origin.field_1350, outerR, innerR, a1, a2, ringColor);
            double gOuter = hitRadius + half * 3.0f;
            double gInner = hitRadius - half * 3.0f;
            HitRange.appendRingQuad(buffer, matrix4f, origin.field_1352, y, origin.field_1350, gOuter, gInner, a1, a2, glowColor);
        }
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        if (depth) {
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
        } else {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask((boolean)false);
        }
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.depthMask((boolean)true);
        if (!depth) {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private static void appendRingQuad(class_287 buffer, Matrix4f matrix, double cx, double y, double cz, double outerR, double innerR, double a1, double a2, int color) {
        float ox1 = (float)(cx + outerR * Math.cos(a1));
        float oz1 = (float)(cz + outerR * Math.sin(a1));
        float ox2 = (float)(cx + outerR * Math.cos(a2));
        float oz2 = (float)(cz + outerR * Math.sin(a2));
        float ix1 = (float)(cx + innerR * Math.cos(a1));
        float iz1 = (float)(cz + innerR * Math.sin(a1));
        float ix2 = (float)(cx + innerR * Math.cos(a2));
        float iz2 = (float)(cz + innerR * Math.sin(a2));
        float fy = (float)y;
        buffer.method_22918(matrix, ox1, fy, oz1).method_39415(color);
        buffer.method_22918(matrix, ox2, fy, oz2).method_39415(color);
        buffer.method_22918(matrix, ix2, fy, iz2).method_39415(color);
        buffer.method_22918(matrix, ix1, fy, iz1).method_39415(color);
    }
}
