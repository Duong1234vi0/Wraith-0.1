/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector4i
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.animation.implement.OutBack;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.TriggerBot;
import code.alphadlc.features.impl.render.Particles;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4i;

public class TargetESP
extends Module {
    private Animation esp_anim = new Decelerate().setMs(400).setValue(1.0);
    private Animation crystal2FadeAnim = new OutBack().setMs(500).setValue(1.0);
    private SelectSetting targetEspType = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0438\u0440\u0430\u0435\u0442 \u0442\u0438\u043f \u0446\u0435\u043b\u0438 esp").value("\u041a\u0432\u0430\u0434\u0440\u0430\u0442", "\u041a\u0440\u0443\u0433", "\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438", "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", "\u041a\u0443\u0431\u044b \u0442\u0430\u0440\u0433\u0435\u0442 \u0435\u0441\u043f", "\u041a\u0432\u0430\u0434\u0440\u0430\u04422", "\u041a\u0432\u0430\u0434\u0440\u0430\u0442 3", "\u0421\u0432\u0430\u0441\u0442\u043e\u043d").selected("\u041a\u0440\u0443\u0433");
    private BooleanSetting crystalFill = new BooleanSetting("\u0417\u0430\u043b\u0438\u0432\u043a\u0430 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u043e\u0432", "\u0417\u0430\u043b\u0438\u0432\u0430\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b \u0446\u0432\u0435\u0442\u043e\u043c").visible(() -> this.targetEspType.isSelected("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b"));
    private RadioSetting colorMode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430", "\u0412\u044b\u0431\u043e\u0440 \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u0446\u0432\u0435\u0442\u0430", new String[]{"\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u041a\u0430\u0441\u0442\u043e\u043c"}, "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439").visible(() -> this.targetEspType.isSelected("\u041a\u0440\u0443\u0433") || this.targetEspType.isSelected("\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438") || this.targetEspType.isSelected("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b") || this.targetEspType.isSelected("\u041a\u0443\u0431\u044b \u0442\u0430\u0440\u0433\u0435\u0442 \u0435\u0441\u043f") || this.targetEspType.isSelected("\u041a\u0432\u0430\u0434\u0440\u0430\u04422") || this.targetEspType.isSelected("\u041a\u0432\u0430\u0434\u0440\u0430\u0442 3") || this.targetEspType.isSelected("\u0421\u0432\u0430\u0441\u0442\u043e\u043d"));
    private ColorSetting maincolor = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u0442\u0430\u0440\u0433\u0435\u0442\u0435\u0441\u043f").value(new Color(255, 255, 255, 55).getRGB()).presets(new Color(0, 246, 255, 255).getRGB(), new Color(183, 1, 195, 255).getRGB(), new Color(255, 60, 0, 255).getRGB(), new Color(171, 253, 0, 255).getRGB()).visible(() -> this.colorMode.get().equals("\u041a\u0430\u0441\u0442\u043e\u043c") && (this.targetEspType.isSelected("\u041a\u0440\u0443\u0433") || this.targetEspType.isSelected("\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438") || this.targetEspType.isSelected("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b") || this.targetEspType.isSelected("\u041a\u0443\u0431\u044b \u0442\u0430\u0440\u0433\u0435\u0442 \u0435\u0441\u043f") || this.targetEspType.isSelected("\u041a\u0432\u0430\u0434\u0440\u0430\u04422") || this.targetEspType.isSelected("\u041a\u0432\u0430\u0434\u0440\u0430\u0442 3") || this.targetEspType.isSelected("\u0421\u0432\u0430\u0441\u0442\u043e\u043d")));
    public BooleanSetting redOnHit = new BooleanSetting("\u041a\u0440\u0430\u0441\u043d\u0435\u0442\u044c \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435", "ESP \u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u0441\u044f \u043a\u0440\u0430\u0441\u043d\u044b\u043c \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435").setValue(true);
    private static final Vector3f[] CRYSTAL_VERTICES = new Vector3f[]{new Vector3f(0.0f, 1.5f, 0.0f), new Vector3f(0.0f, -1.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(-1.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector3f(0.0f, 0.0f, -1.0f)};
    private static final int[][] CRYSTAL_FACES = new int[][]{{0, 2, 4}, {0, 4, 3}, {0, 3, 5}, {0, 5, 2}, {1, 4, 2}, {1, 3, 4}, {1, 5, 3}, {1, 2, 5}};

    public static TargetESP getInstance() {
        return Instance.get(TargetESP.class);
    }

    public Animation getCrystal2FadeAnim() {
        return this.crystal2FadeAnim;
    }

    public BooleanSetting getCrystalFill() {
        return this.crystalFill;
    }

    public int getColor() {
        return this.colorMode.get().equals("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439") ? ThemeManager.primaryColor.getColor() : this.maincolor.getColor();
    }

    public TargetESP() {
        super("TargetEsp", "TargetEsp", ModuleCategory.RENDER);
        this.setup(this.targetEspType, this.crystalFill, this.colorMode, this.maincolor, this.redOnHit);
    }

    @Override
    public void activate() {
        this.crystal2FadeAnim.setDirection(Direction.FORWARDS);
        super.activate();
    }

    @Override
    public void deactivate() {
        this.crystal2FadeAnim.setDirection(Direction.BACKWARDS);
        super.deactivate();
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() == 2) {
            Render3D.updateTargetEsp();
        }
    }

    private static class_1309 getCombatTarget() {
        if (AttackAura.getInstance().isState() && AttackAura.getInstance().getTarget() != null) {
            return AttackAura.getInstance().getTarget();
        }
        TriggerBot tb = TriggerBot.getInstance();
        if (tb != null && tb.isState() && tb.getTarget() != null) {
            return tb.getTarget();
        }
        return null;
    }

    private static class_1309 getCombatLastTarget() {
        TriggerBot tb;
        if (AttackAura.getInstance().isState()) {
            class_1309 auraTarget = AttackAura.getInstance().getTarget();
            if (auraTarget != null) {
                return auraTarget;
            }
            class_1309 lastAuraTarget = AttackAura.getInstance().getLastTarget();
            if (lastAuraTarget != null) {
                return lastAuraTarget;
            }
        }
        if ((tb = TriggerBot.getInstance()) != null && tb.isState() && tb.getTarget() != null) {
            return tb.getTarget();
        }
        return AttackAura.getInstance().getLastTarget();
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        boolean combatActive;
        float tickDelta = e.getPartialTicks();
        class_1309 target = TargetESP.getCombatTarget();
        class_1309 lastTarget = TargetESP.getCombatLastTarget();
        this.esp_anim.setDirection(target != null ? Direction.FORWARDS : Direction.BACKWARDS);
        boolean bl = combatActive = lastTarget != null && !this.esp_anim.isFinished(Direction.BACKWARDS);
        if (combatActive) {
            float anim = this.esp_anim.getOutput().floatValue();
            float red = this.redOnHit.isValue() ? class_3532.method_15363((float)(((float)lastTarget.field_6235 - tickCounter.method_60637(false)) / 20.0f), (float)0.0f, (float)1.0f) : 0.0f;
            switch (this.targetEspType.getSelected()) {
                case "\u041a\u0432\u0430\u0434\u0440\u0430\u0442": {
                    Render3D.drawCube(lastTarget, anim, red);
                    break;
                }
                case "\u041a\u0440\u0443\u0433": {
                    Render3D.drawCircle(e.getStack(), lastTarget, anim, red);
                    break;
                }
                case "\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438": {
                    Render3D.drawGhosts(lastTarget, anim, red, 0.62f);
                    break;
                }
                case "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b": {
                    this.renderCrystalsBlumeStyle(e.getStack(), lastTarget, anim, red, tickDelta);
                    break;
                }
                case "\u041a\u0443\u0431\u044b \u0442\u0430\u0440\u0433\u0435\u0442 \u0435\u0441\u043f": {
                    Render3D.drawTargetEspCubes(lastTarget, anim, red);
                    break;
                }
                case "\u041a\u0432\u0430\u0434\u0440\u0430\u04422": {
                    Render3D.drawTextureCube(lastTarget, anim, red, class_2960.method_60654((String)"textures/capture1.png"), false);
                    break;
                }
                case "\u041a\u0432\u0430\u0434\u0440\u0430\u0442 3": {
                    Render3D.drawTextureCube(lastTarget, anim, red, class_2960.method_60654((String)"textures/targetesp_2.png"), false);
                    break;
                }
                case "\u0421\u0432\u0430\u0441\u0442\u043e\u043d": {
                    Render3D.drawTextureCube(lastTarget, anim, red, class_2960.method_60654((String)"textures/svaston.png"), true);
                }
            }
        }
    }

    private void renderCrystalsBlumeStyle(class_4587 matrixStack, class_1309 living, float anim, float red, float tickDelta) {
        if (living == null) {
            return;
        }
        class_243 vec3d = living.method_30950(tickDelta);
        double time = (double)System.nanoTime() / 1.0E9;
        double lengthX = living.method_5829().method_17939();
        double lengthZ = living.method_5829().method_17941();
        int baseColor = this.redOnHit.isValue() && red > 0.0f ? ColorAssist.red : (this.crystalFill.isValue() ? ColorAssist.clampSaturation(this.getColor(), 1.0f, 1.1f) : -1);
        baseColor = ColorAssist.multAlpha(baseColor, anim);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 builder = class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
        class_4184 camera = TargetESP.mc.field_1773.method_19418();
        for (int i = 0; i < 32; ++i) {
            double cos = Math.cos(time + (double)i) * (lengthX + (double)0.2f);
            double sin = Math.sin(time + (double)i) * (lengthZ + (double)0.2f);
            class_243 crystalPos = vec3d.method_1031(cos, (double)((float)i / 8.0f % living.method_17682()), sin);
            class_243 locationDelta = new class_243(vec3d.field_1352 - crystalPos.field_1352, 0.0, vec3d.field_1350 - crystalPos.field_1350);
            Turns rotation = MathAngle.fromVec3d(locationDelta);
            class_243 targetPos = crystalPos.method_1020(rotation.toVector().method_1021((double)(1.0f - anim)));
            matrixStack.method_22903();
            matrixStack.method_22904(targetPos.field_1352, targetPos.field_1351, targetPos.field_1350);
            matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-rotation.getYaw()));
            matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
            matrixStack.method_22905(0.1f, 0.1f, 0.1f);
            Matrix4f matrix4f = matrixStack.method_23760().method_23761();
            for (int[] face : CRYSTAL_FACES) {
                Vector3f v1 = CRYSTAL_VERTICES[face[0]];
                Vector3f v2 = CRYSTAL_VERTICES[face[1]];
                Vector3f v3 = CRYSTAL_VERTICES[face[2]];
                builder.method_22918(matrix4f, v1.x, v1.y, v1.z).method_39415(baseColor);
                builder.method_22918(matrix4f, v2.x, v2.y, v2.z).method_39415(baseColor);
                builder.method_22918(matrix4f, v3.x, v3.y, v3.z).method_39415(baseColor);
            }
            matrixStack.method_22909();
            class_4587 glowMat = new class_4587();
            glowMat.method_22903();
            glowMat.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            glowMat.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
            glowMat.method_22904(targetPos.field_1352 - camera.method_19326().field_1352, targetPos.field_1351 - camera.method_19326().field_1351, targetPos.field_1350 - camera.method_19326().field_1350);
            glowMat.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            glowMat.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            float glowSize = 0.2f;
            int glowColor = ColorAssist.multAlpha(this.getColor(), 0.3f * anim);
            Render3D.drawTexture(glowMat.method_23760(), Particles.ParticleType.shariki.texture(), -glowSize * 2.0f, -glowSize * 2.0f, glowSize * 4.0f, glowSize * 4.0f, new Vector4i(glowColor, glowColor, glowColor, glowColor), false);
            glowMat.method_22909();
        }
        class_9801 builtBuffer = builder.method_60794();
        if (builtBuffer != null && builtBuffer.method_60822().comp_750() > 0) {
            class_286.method_43433((class_9801)builtBuffer);
        }
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
    }
}
