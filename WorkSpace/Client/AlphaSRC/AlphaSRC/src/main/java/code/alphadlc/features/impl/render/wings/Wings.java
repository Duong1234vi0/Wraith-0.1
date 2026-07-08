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
 *  net.minecraft.class_1304
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.lwjgl.opengl.GL11
 */
package code.alphadlc.features.impl.render.wings;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class Wings
extends Module {
    private static final WingPoint[] SHAPE = new WingPoint[]{new WingPoint(0.08f, 0.1f, 0.88f), new WingPoint(0.28f, 0.34f, 0.78f), new WingPoint(0.56f, 0.82f, 0.62f), new WingPoint(0.86f, 0.3f, 0.52f), new WingPoint(1.14f, 0.46f, 0.4f), new WingPoint(1.24f, 0.04f, 0.3f), new WingPoint(1.02f, -0.18f, 0.28f), new WingPoint(1.18f, -0.64f, 0.22f), new WingPoint(0.86f, -0.46f, 0.2f), new WingPoint(0.8f, -0.98f, 0.14f), new WingPoint(0.54f, -0.74f, 0.16f), new WingPoint(0.3f, -1.16f, 0.12f), new WingPoint(0.1f, -0.54f, 0.18f)};
    private final BooleanSetting self = new BooleanSetting("\u041d\u0430 \u0441\u0435\u0431\u044f", "\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043a\u0440\u044b\u043b\u044c\u044f \u043d\u0430 \u0441\u0435\u0431\u0435").setValue(true);
    private final BooleanSetting friends = new BooleanSetting("\u041d\u0430 \u0434\u0440\u0443\u0437\u0435\u0439", "\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043a\u0440\u044b\u043b\u044c\u044f \u043d\u0430 \u0434\u0440\u0443\u0437\u044c\u044f\u0445").setValue(false);
    private final BooleanSetting players = new BooleanSetting("\u041d\u0430 \u0438\u0433\u0440\u043e\u043a\u043e\u0432", "\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043a\u0440\u044b\u043b\u044c\u044f \u043d\u0430 \u0434\u0440\u0443\u0433\u0438\u0445 \u0438\u0433\u0440\u043e\u043a\u0430\u0445").setValue(false);
    private final SliderSettings size = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043a\u0440\u044b\u043b\u044c\u0435\u0432").setValue(1.0f).range(0.75f, 1.35f).step(0.05f);
    private final BooleanSetting useThemeColor = new BooleanSetting("\u0426\u0432\u0435\u0442 \u0442\u0435\u043c\u044b", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0446\u0432\u0435\u0442 \u0442\u0435\u043c\u044b \u043a\u043b\u0438\u0435\u043d\u0442\u0430").setValue(true);
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u043a\u0440\u044b\u043b\u044c\u0435\u0432").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB()).visible(() -> !this.useThemeColor.isValue());
    private float selfBodyYaw;
    private boolean selfBodyYawInitialized;
    private float glideProgress;

    public Wings() {
        super("Wings", "Wings", ModuleCategory.RENDER);
        this.setup(this.self, this.friends, this.players, this.size, this.useThemeColor, this.color);
    }

    @EventHandler
    public void onRender3D(WorldRenderEvent event) {
        if (Wings.mc.field_1724 == null || Wings.mc.field_1687 == null || Wings.mc.field_1773 == null) {
            return;
        }
        float tickDelta = event.getPartialTicks();
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        if (this.self.isValue() && !Wings.mc.field_1690.method_31044().method_31034() && Wings.mc.field_1724.method_5805() && !this.hasElytra((class_1657)Wings.mc.field_1724)) {
            try {
                this.renderWings((class_1657)Wings.mc.field_1724, tickDelta);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (this.players.isValue()) {
            for (class_1297 entity : Wings.mc.field_1687.method_18112()) {
                class_1657 player;
                if (!(entity instanceof class_1657) || (player = (class_1657)entity) == Wings.mc.field_1724 || !player.method_5805() || this.hasElytra(player)) continue;
                try {
                    this.renderWings(player, tickDelta);
                }
                catch (Exception exception) {}
            }
        } else if (this.friends.isValue()) {
            for (class_1297 entity : Wings.mc.field_1687.method_18112()) {
                class_1657 player;
                if (!(entity instanceof class_1657) || (player = (class_1657)entity) == Wings.mc.field_1724 || !player.method_5805() || this.hasElytra(player) || !FriendUtils.isFriend((class_1297)player)) continue;
                try {
                    this.renderWings(player, tickDelta);
                }
                catch (Exception exception) {}
            }
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA, (GlStateManager.class_4535)GlStateManager.class_4535.ONE, (GlStateManager.class_4534)GlStateManager.class_4534.ZERO);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private boolean hasElytra(class_1657 player) {
        return player.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
    }

    private void renderWings(class_1657 player, float tickDelta) {
        double x = class_3532.method_16436((double)tickDelta, (double)player.field_6014, (double)player.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)player.field_6036, (double)player.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)player.field_5969, (double)player.method_23321());
        float bodyYaw = this.resolveBodyYaw(player, tickDelta);
        float move = class_3532.method_15363((float)player.field_42108.method_48572(tickDelta), (float)0.0f, (float)1.0f);
        WingPose pose = this.resolvePose(player, tickDelta);
        if (pose == null) {
            return;
        }
        float flap = (float)Math.sin(((float)player.field_6012 + tickDelta) * pose.flapSpeed) * pose.flapAmplitude;
        float open = (8.0f + flap + move * pose.motionSpreadBoost) * pose.openMultiplier;
        float wingScale = this.size.getValue() * pose.scaleMultiplier;
        int baseColor = this.resolveBaseColor();
        int glowColor = this.resolveGlowColor(baseColor);
        int coreColor = this.resolveCoreColor(baseColor);
        int outlineColor = baseColor;
        class_4587 stack = new class_4587();
        stack.method_34425(Render3D.lastWorldSpaceMatrix.method_23761());
        stack.method_22903();
        stack.method_22904(x, y, z);
        stack.method_22907(class_7833.field_40716.rotationDegrees(180.0f - bodyYaw));
        if (pose.preTranslateY != 0.0f || pose.preTranslateZ != 0.0f) {
            stack.method_46416(0.0f, pose.preTranslateY, pose.preTranslateZ);
        }
        if (pose.pitchRotation != 0.0f) {
            stack.method_22907(class_7833.field_40714.rotationDegrees(pose.pitchRotation));
        }
        if (pose.rollRotation != 0.0f) {
            stack.method_22907(class_7833.field_40718.rotationDegrees(pose.rollRotation));
        }
        stack.method_46416(0.0f, pose.anchorY, pose.anchorZ);
        stack.method_22905(wingScale, wingScale, wingScale);
        this.renderWingSide(stack, -1.0f, open, baseColor, glowColor, coreColor, outlineColor, pose);
        this.renderWingSide(stack, 1.0f, open, baseColor, glowColor, coreColor, outlineColor, pose);
        stack.method_22909();
    }

    private void renderWingSide(class_4587 stack, float side, float open, int baseColor, int glowColor, int coreColor, int outlineColor, WingPose pose) {
        stack.method_22903();
        stack.method_46416(side * pose.sideOffset, pose.sideYOffset, pose.sideZOffset);
        stack.method_22907(class_7833.field_40716.rotationDegrees(side * open));
        stack.method_22907(class_7833.field_40718.rotationDegrees(side * pose.sideRoll));
        stack.method_22907(class_7833.field_40714.rotationDegrees(pose.sidePitch));
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        this.drawWingLayer(stack, side, 1.22f, Wings.setAlpha(glowColor, 48), Wings.setAlpha(glowColor, 0));
        this.drawWingLayer(stack, side, 0.84f, Wings.setAlpha(coreColor, 57), Wings.setAlpha(coreColor, 0));
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        this.drawWingLayer(stack, side, 1.0f, Wings.setAlpha(baseColor, 220), Wings.setAlpha(baseColor, 10));
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        this.drawWingOutline(stack, side, 1.0f, Wings.setAlpha(outlineColor, 136));
        this.drawWingRibs(stack, side, 0.96f, Wings.setAlpha(glowColor, 44));
        stack.method_22909();
    }

    private void drawWingLayer(class_4587 stack, float side, float scale, int rootColor, int edgeColor) {
        Matrix4f matrix = stack.method_23760().method_23761();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
        for (int i = 0; i < SHAPE.length; ++i) {
            WingPoint cur = SHAPE[i];
            WingPoint next = SHAPE[(i + 1) % SHAPE.length];
            this.vertex(buffer, matrix, 0.0f, 0.0f, 0.0f, rootColor);
            this.vertex(buffer, matrix, side * cur.x * scale, cur.y * scale, 0.0f, this.applyPointAlpha(edgeColor, cur.alphaMul));
            this.vertex(buffer, matrix, side * next.x * scale, next.y * scale, 0.0f, this.applyPointAlpha(edgeColor, next.alphaMul));
        }
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    private void drawWingOutline(class_4587 stack, float side, float scale, int color) {
        Matrix4f matrix = stack.method_23760().method_23761();
        RenderSystem.lineWidth((float)1.35f);
        GL11.glEnable((int)2848);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
        for (WingPoint point : SHAPE) {
            this.vertex(buffer, matrix, side * point.x * scale, point.y * scale, 0.0f, color);
        }
        this.vertex(buffer, matrix, side * Wings.SHAPE[0].x * scale, Wings.SHAPE[0].y * scale, 0.0f, color);
        class_286.method_43433((class_9801)buffer.method_60800());
        GL11.glDisable((int)2848);
    }

    private void drawWingRibs(class_4587 stack, float side, float scale, int color) {
        Matrix4f matrix = stack.method_23760().method_23761();
        int[] ribIndices = new int[]{2, 4, 7, 9, 11};
        RenderSystem.lineWidth((float)0.9f);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29344, class_290.field_1576);
        for (int idx : ribIndices) {
            WingPoint point = SHAPE[idx];
            this.vertex(buffer, matrix, 0.0f, 0.0f, 0.0f, Wings.setAlpha(color, Math.max(8, (int)((float)Wings.alpha(color) * 0.75f))));
            this.vertex(buffer, matrix, side * point.x * scale, point.y * scale, 0.0f, this.applyPointAlpha(color, point.alphaMul));
        }
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    private int applyPointAlpha(int color, float multiplier) {
        return Wings.setAlpha(color, Math.max(0, Math.min(255, (int)((float)Wings.alpha(color) * multiplier))));
    }

    private static int setAlpha(int color, int a) {
        return class_3532.method_15340((int)a, (int)0, (int)255) << 24 | color & 0xFFFFFF;
    }

    private static int alpha(int color) {
        return color >> 24 & 0xFF;
    }

    private static int red(int color) {
        return color >> 16 & 0xFF;
    }

    private static int green(int color) {
        return color >> 8 & 0xFF;
    }

    private static int blue(int color) {
        return color & 0xFF;
    }

    private static int getColor(int r, int g, int b, int a) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    private void vertex(class_287 buffer, Matrix4f matrix, float x, float y, float z, int color) {
        buffer.method_22918(matrix, x, y, z).method_39415(color);
    }

    private int resolveBaseColor() {
        return this.useThemeColor.isValue() ? ColorAssist.getClientColor() : this.color.getColor();
    }

    private int resolveGlowColor(int base) {
        return ColorAssist.interpolateColor(base, Wings.getColor(255, 255, 255, 255), 0.28f);
    }

    private int resolveCoreColor(int base) {
        return ColorAssist.interpolateColor(base, Wings.getColor(255, 255, 255, 255), 0.55f);
    }

    private float resolveBodyYaw(class_1657 player, float tickDelta) {
        float target = class_3532.method_17821((float)tickDelta, (float)player.field_6220, (float)player.field_6283);
        if (player != Wings.mc.field_1724) {
            return target;
        }
        if (!this.selfBodyYawInitialized || player.field_6012 < 2) {
            this.selfBodyYaw = target;
            this.selfBodyYawInitialized = true;
            return this.selfBodyYaw;
        }
        this.selfBodyYaw = Wings.approachDegrees(this.selfBodyYaw, target, 14.0f);
        return this.selfBodyYaw;
    }

    private static float approachDegrees(float current, float target, float maxDelta) {
        float delta = class_3532.method_15393((float)(target - current));
        delta = class_3532.method_15363((float)delta, (float)(-maxDelta), (float)maxDelta);
        return current + delta;
    }

    private WingPose resolvePose(class_1657 player, float tickDelta) {
        float pitch = class_3532.method_16439((float)tickDelta, (float)player.field_6004, (float)player.method_36455());
        if (player == Wings.mc.field_1724) {
            this.glideProgress = class_3532.method_15363((float)(this.glideProgress + (player.method_6128() ? 0.12f : -0.2f)), (float)0.0f, (float)1.0f);
        }
        if (player.method_6128()) {
            float flightProgress = player == Wings.mc.field_1724 ? this.glideProgress : 1.0f;
            float pitchRotation = flightProgress * (-90.0f - pitch);
            return new WingPose(0.34f, 0.46f, 0.0f, 0.0f, pitchRotation, 0.0f, 0.76f, 0.92f, 0.1f, 0.58f, 0.05f, 0.06f, -5.0f, -2.0f, 0.13f);
        }
        if (player.method_5799()) {
            return null;
        }
        if (player.method_5715()) {
            return new WingPose(0.0f, 0.0f, 0.96f, 0.1f, 18.0f, 0.0f, 1.0f, 1.0f, 0.18f, 4.5f, 0.06f, 0.02f, -11.0f, -4.0f, 0.12f);
        }
        return new WingPose(0.0f, 0.0f, 1.38f, 0.1f, 0.0f, 0.0f, 1.0f, 1.0f, 0.18f, 4.5f, 0.06f, 0.02f, -11.0f, -4.0f, 0.12f);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.selfBodyYawInitialized = false;
        this.glideProgress = 0.0f;
    }

    private static final class WingPose {
        final float preTranslateY;
        final float preTranslateZ;
        final float anchorY;
        final float anchorZ;
        final float pitchRotation;
        final float rollRotation;
        final float openMultiplier;
        final float scaleMultiplier;
        final float motionSpreadBoost;
        final float flapAmplitude;
        final float sideOffset;
        final float sideYOffset;
        final float sideZOffset;
        final float sideRoll;
        final float sidePitch;
        final float flapSpeed;

        WingPose(float preTranslateY, float preTranslateZ, float anchorY, float anchorZ, float pitchRotation, float rollRotation, float openMultiplier, float scaleMultiplier, float motionSpreadBoost, float flapAmplitude, float sideOffset, float sideZOffset, float sideRoll, float sidePitch, float flapSpeed) {
            this(preTranslateY, preTranslateZ, anchorY, anchorZ, pitchRotation, rollRotation, openMultiplier, scaleMultiplier, motionSpreadBoost, flapAmplitude, sideOffset, 0.0f, sideZOffset, sideRoll, sidePitch, flapSpeed);
        }

        WingPose(float preTranslateY, float preTranslateZ, float anchorY, float anchorZ, float pitchRotation, float rollRotation, float openMultiplier, float scaleMultiplier, float motionSpreadBoost, float flapAmplitude, float sideOffset, float sideYOffset, float sideZOffset, float sideRoll, float sidePitch, float flapSpeed) {
            this.preTranslateY = preTranslateY;
            this.preTranslateZ = preTranslateZ;
            this.anchorY = anchorY;
            this.anchorZ = anchorZ;
            this.pitchRotation = pitchRotation;
            this.rollRotation = rollRotation;
            this.openMultiplier = openMultiplier;
            this.scaleMultiplier = scaleMultiplier;
            this.motionSpreadBoost = motionSpreadBoost;
            this.flapAmplitude = flapAmplitude;
            this.sideOffset = sideOffset;
            this.sideYOffset = sideYOffset;
            this.sideZOffset = sideZOffset;
            this.sideRoll = sideRoll;
            this.sidePitch = sidePitch;
            this.flapSpeed = flapSpeed;
        }
    }

    private static final class WingPoint {
        final float x;
        final float y;
        final float alphaMul;

        WingPoint(float x, float y, float alphaMul) {
            this.x = x;
            this.y = y;
            this.alphaMul = alphaMul;
        }
    }
}
