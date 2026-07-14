package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.Babka;
import ua.babka.combat.BestPoint;
import ua.babka.combat.PredictUtils;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="ElytraTarget", moduleDesc="Наведение на цель в полёте", moduleCategory=ModuleCategory.MOVEMENT)
public class ElytraTarget
extends Module {
    public final BooleanSetting hitAfterOvertake = new BooleanSetting("Бить только после перегона", false);
    public final BooleanSetting predictate = new BooleanSetting("Сила Перегона", true);
    public final SliderSetting predictValue = new SliderSetting("На сколько блоков", 2.5, 1.0, 6.0, 0.1f);
    public final BooleanSetting showPredictPoint = new BooleanSetting("Показать предикт точку", true);
    public final ModeSetting renderMode = new ModeSetting("Визуал", "Предикт", "Предикт", "Бокс", "Оверлей");

    public boolean canAttack(LivingEntity target) {
        if (target == null) {
            return false;
        }
        ClientPlayerEntity player = Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer != null ? Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer : this.mc.player;
        AttackAura attackAura = Babka.getInstance().getModuleStorage().get(AttackAura.class);
        float attackDist = attackAura != null ? attackAura.distance.getFloatValue() : 4.0f;
        double distNearest = player.getEyePos().distanceTo(BestPoint.getNearestPoint((Entity)target));
        return distNearest <= (double)attackDist;
    }

    @Subscribe
    private void onRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.showPredictPoint.getValue()) {
            return;
        }
        if (!this.mc.player.isGliding()) {
            return;
        }
        AttackAura attackAura = Babka.getInstance().getModuleStorage().get(AttackAura.class);
        if (attackAura == null || attackAura.getTarget() == null) {
            return;
        }
        if (!attackAura.getTarget().isGliding()) {
            return;
        }
        Vec3d predictPos = PredictUtils.predict(attackAura.getTarget(), this.predictValue.getValue());
        Vec3d camPos = this.mc.gameRenderer.getCamera().getPos();
        double renderX = predictPos.x - camPos.x;
        double renderY = predictPos.y - camPos.y;
        double renderZ = predictPos.z - camPos.z;
        int color = ColorProvider.getColorClient();
        float r2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(color & 0xFF) / 255.0f;
        float a2 = 0.8f;
        MatrixStack m2 = e2.getMatrixStack();
        m2.push();
        switch (this.renderMode.getValue()) {
            case "Предикт": {
                this.renderPredictCube(m2, renderX, renderY, renderZ, r2, g2, b2, a2);
                break;
            }
            case "Бокс": {
                this.renderBox(m2, renderX, renderY, renderZ, r2, g2, b2, a2, false);
                break;
            }
            case "Оверлей": {
                this.renderOverlay(m2, renderX, renderY, renderZ, r2, g2, b2, a2);
            }
        }
        m2.pop();
    }

    private void renderPredictCube(MatrixStack m2, double x2, double y2, double z2, float r2, float g2, float b2, float a2) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        m2.translate(x2, y2, z2);
        Matrix4f localMat = m2.peek().getPositionMatrix();
        float size = 0.3f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        buffer.vertex(localMat, -size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, -size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, size, size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, -size, size).color(r2, g2, b2, a2);
        buffer.vertex(localMat, -size, size, size).color(r2, g2, b2, a2);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private void renderBox(MatrixStack m2, double x2, double y2, double z2, float r2, float g2, float b2, float a2, boolean filled) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        m2.translate(x2, y2, z2);
        Matrix4f mat = m2.peek().getPositionMatrix();
        float hw = 0.45f;
        float hh = 0.9f;
        float hd = 0.45f;
        Tessellator tessellator = Tessellator.getInstance();
        if (filled) {
            BufferBuilder quads = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            quads.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, hd).color(r2, g2, b2, 0.15f);
            quads.vertex(mat, hw, hh, -hd).color(r2, g2, b2, 0.15f);
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)quads.end());
        }
        BufferBuilder lines = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        lines.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, -hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, hw, hh, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, 0.0f, hd).color(r2, g2, b2, a2);
        lines.vertex(mat, -hw, hh, hd).color(r2, g2, b2, a2);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)lines.end());
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private void renderOverlay(MatrixStack m2, double x2, double y2, double z2, float r2, float g2, float b2, float a2) {
        this.renderBox(m2, x2, y2, z2, r2, g2, b2, a2, true);
    }
}

