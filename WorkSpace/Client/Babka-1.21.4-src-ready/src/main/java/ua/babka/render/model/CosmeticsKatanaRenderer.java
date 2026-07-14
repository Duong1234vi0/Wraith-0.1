package ua.babka.render.model;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import ua.babka.module.impl.render.Cosmetics;
import ua.babka.render.ColorProvider;

public class CosmeticsKatanaRenderer
extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    public CosmeticsKatanaRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }

    public void render(MatrixStack ms, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        boolean isSelf;
        Cosmetics module = Cosmetics.getInstance();
        MinecraftClient mc = MinecraftClient.getInstance();
        if (module == null || !module.isEnabled() || !module.type.is("Katana") || mc.player == null || mc.world == null) {
            return;
        }
        boolean bl = isSelf = state.id == mc.player.getId();
        if (isSelf && (!module.self.getValue() || mc.options.getPerspective().isFirstPerson())) {
            return;
        }
        if (!isSelf && !module.players.getValue()) {
            return;
        }
        int themeColor = ColorProvider.getColorClient();
        int tr = themeColor >> 16 & 0xFF;
        int tg = themeColor >> 8 & 0xFF;
        int tb = themeColor & 0xFF;
        ms.push();
        ModelPart body = ((PlayerEntityModel)this.getContextModel()).body;
        float savedPitch = body.pitch;
        float savedRoll = body.roll;
        body.pitch = 0.0f;
        body.roll = 0.0f;
        body.rotate(ms);
        body.pitch = savedPitch;
        body.roll = savedRoll;
        ms.translate(-0.15f, 0.1f, 0.28f);
        ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-45.0f));
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-3.0f));
        ms.scale(1.15f, 1.15f, 1.15f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        Tessellator tess = Tessellator.getInstance();
        Matrix4f mat = ms.peek().getPositionMatrix();
        RenderSystem.colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
        RenderSystem.disableBlend();
        this.box(tess, mat, -0.042f, -0.48f, -0.024f, 0.042f, -0.42f, 0.024f, 0, 0, 0, 255);
        this.box(tess, mat, -0.032f, -0.42f, -0.018f, 0.032f, 0.0f, 0.018f, 0, 0, 0, 255);
        this.box(tess, mat, -0.085f, 0.0f, -0.034f, 0.085f, 0.05f, 0.034f, 0, 0, 0, 255);
        this.box(tess, mat, -0.022f, 0.05f, -0.01f, 0.022f, 0.96f, 0.01f, 0, 0, 0, 255);
        this.box(tess, mat, -0.012f, 0.96f, -0.007f, 0.012f, 1.07f, 0.007f, 0, 0, 0, 255);
        RenderSystem.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        int bgA = (int)(module.fillAlpha.getValue() / 100.0 * 255.0);
        int outA = (int)(module.outlineAlpha.getValue() / 100.0 * 255.0);
        this.box(tess, mat, -0.042f, -0.48f, -0.024f, 0.042f, -0.42f, 0.024f, tr, tg, tb, bgA);
        this.box(tess, mat, -0.032f, -0.42f, -0.018f, 0.032f, 0.0f, 0.018f, tr, tg, tb, bgA);
        this.box(tess, mat, -0.085f, 0.0f, -0.034f, 0.085f, 0.05f, 0.034f, tr, tg, tb, bgA);
        this.box(tess, mat, -0.022f, 0.05f, -0.01f, 0.022f, 0.96f, 0.01f, tr, tg, tb, bgA);
        this.box(tess, mat, -0.012f, 0.96f, -0.007f, 0.012f, 1.07f, 0.007f, tr, tg, tb, bgA);
        this.boxEdge(tess, mat, -0.042f, -0.48f, -0.024f, 0.042f, -0.42f, 0.024f, tr, tg, tb, outA);
        this.boxEdge(tess, mat, -0.032f, -0.42f, -0.018f, 0.032f, 0.0f, 0.018f, tr, tg, tb, outA);
        this.boxEdge(tess, mat, -0.085f, 0.0f, -0.034f, 0.085f, 0.05f, 0.034f, tr, tg, tb, outA);
        this.boxEdge(tess, mat, -0.022f, 0.05f, -0.01f, 0.022f, 0.96f, 0.01f, tr, tg, tb, outA);
        this.boxEdge(tess, mat, -0.012f, 0.96f, -0.007f, 0.012f, 1.07f, 0.007f, tr, tg, tb, outA);
        if (module.glow.getValue()) {
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            RenderSystem.depthMask((boolean)false);
            RenderSystem.disableDepthTest();
            float glowBase = (float)(module.glowLevel.getValue() / 100.0);
            float pulse = (float)(Math.sin((double)System.currentTimeMillis() / 700.0) * 0.12 + 0.88);
            glowBase *= pulse;
            int layers = 12;
            float maxEx = 0.045f;
            for (int i2 = 0; i2 < layers; ++i2) {
                float t2 = (float)i2 / (float)(layers - 1);
                float falloff = (1.0f - t2) * (1.0f - t2);
                int ga = (int)(glowBase * falloff * 180.0f);
                if (ga <= 1) continue;
                float ex = t2 * maxEx;
                this.box(tess, mat, -0.042f - ex, -0.48f - ex, -0.024f - ex, 0.042f + ex, -0.42f + ex, 0.024f + ex, tr, tg, tb, ga);
                this.box(tess, mat, -0.032f - ex, -0.42f - ex, -0.018f - ex, 0.032f + ex, 0.0f + ex, 0.018f + ex, tr, tg, tb, ga);
                this.box(tess, mat, -0.085f - ex, 0.0f - ex, -0.034f - ex, 0.085f + ex, 0.05f + ex, 0.034f + ex, tr, tg, tb, ga);
                this.box(tess, mat, -0.022f - ex, 0.05f - ex, -0.01f - ex, 0.022f + ex, 0.96f + ex, 0.01f + ex, tr, tg, tb, ga);
                this.box(tess, mat, -0.012f - ex, 0.96f - ex, -0.007f - ex, 0.012f + ex, 1.07f + ex, 0.007f + ex, tr, tg, tb, ga);
            }
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)true);
            RenderSystem.defaultBlendFunc();
        }
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ms.pop();
    }

    private void box(Tessellator tess, Matrix4f mat, float x0, float y0, float z0, float x1, float y1, float z1, int r2, int g2, int b2, int a2) {
        if (a2 <= 0) {
            return;
        }
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        BufferBuilder buf = tess.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buf.vertex(mat, x0, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y0, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y0, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y0, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x0, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z0).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y1, z1).color(r2, g2, b2, a2);
        buf.vertex(mat, x1, y0, z1).color(r2, g2, b2, a2);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
    }

    private void boxEdge(Tessellator tess, Matrix4f mat, float x0, float y0, float z0, float x1, float y1, float z1, int r2, int g2, int b2, int a2) {
        if (a2 <= 0) {
            return;
        }
        float e2 = 0.004f;
        int br = Math.min(r2 + 40, 255);
        int bg = Math.min(g2 + 40, 255);
        int bb2 = Math.min(b2 + 40, 255);
        this.box(tess, mat, x0 - e2, y0 - e2, z0 - e2, x1 + e2, y0 + e2, z0 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y0 - e2, z1 - e2, x1 + e2, y0 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y0 - e2, z0 - e2, x0 + e2, y0 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x1 - e2, y0 - e2, z0 - e2, x1 + e2, y0 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y1 - e2, z0 - e2, x1 + e2, y1 + e2, z0 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y1 - e2, z1 - e2, x1 + e2, y1 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y1 - e2, z0 - e2, x0 + e2, y1 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x1 - e2, y1 - e2, z0 - e2, x1 + e2, y1 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y0 - e2, z0 - e2, x0 + e2, y1 + e2, z0 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x1 - e2, y0 - e2, z0 - e2, x1 + e2, y1 + e2, z0 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x0 - e2, y0 - e2, z1 - e2, x0 + e2, y1 + e2, z1 + e2, br, bg, bb2, a2);
        this.box(tess, mat, x1 - e2, y0 - e2, z1 - e2, x1 + e2, y1 + e2, z1 + e2, br, bg, bb2, a2);
    }
}

