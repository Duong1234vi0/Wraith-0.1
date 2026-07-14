package ua.babka.render.model;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import ua.babka.module.impl.render.ChinaHat;
import ua.babka.render.ColorProvider;

public class ChinaHatRenderer
extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private static final int SEGMENTS = 60;
    private static final float PI2 = (float)Math.PI * 2;

    public ChinaHatRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        LivingEntity living;
        ChinaHat module = ChinaHat.getInstance();
        MinecraftClient mc = MinecraftClient.getInstance();
        if (module == null || !module.isEnabled() || mc.player == null || mc.world == null) {
            return;
        }
        boolean isSelf = state.id == mc.player.getId();
        boolean isFriend = false;
        Entity entity = mc.world.getEntityById(state.id);
        if (entity != null) {
            String string = entity.getName().getString();
        }
        if (!isSelf && !isFriend) {
            return;
        }
        if (isSelf && mc.options.getPerspective().isFirstPerson()) {
            return;
        }
        matrixStack.push();
        ((PlayerEntityModel)this.getContextModel()).head.rotate(matrixStack);
        float yOffset = -0.489f;
        if (entity instanceof LivingEntity && !(living = (LivingEntity)entity).getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            yOffset -= 0.0625f;
        }
        matrixStack.translate(0.0f, yOffset, 0.0f);
        if (vertexConsumers instanceof VertexConsumerProvider.Immediate) {
            VertexConsumerProvider.Immediate immediate = (VertexConsumerProvider.Immediate)vertexConsumers;
            immediate.draw();
        }
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        Tessellator tessellator = Tessellator.getInstance();
        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
        float width = 0.62f;
        float coneHeight = 0.3f;
        int centerColor = this.getThemeColor(200);
        int edgeColor = this.getThemeColor(80);
        RenderSystem.depthMask((boolean)false);
        BufferBuilder bufferInside = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        for (int i2 = 0; i2 < 60; ++i2) {
            float angle1 = (float)i2 * ((float)Math.PI * 2) / 60.0f;
            float angle2 = (float)(i2 + 1) * ((float)Math.PI * 2) / 60.0f;
            float x1 = -MathHelper.sin((float)angle1) * width;
            float z1 = MathHelper.cos((float)angle1) * width;
            float x2 = -MathHelper.sin((float)angle2) * width;
            float z2 = MathHelper.cos((float)angle2) * width;
            bufferInside.vertex(matrix, 0.0f, -coneHeight, 0.0f).color(centerColor);
            bufferInside.vertex(matrix, x2, 0.0f, z2).color(edgeColor);
            bufferInside.vertex(matrix, x1, 0.0f, z1).color(edgeColor);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)bufferInside.end());
        RenderSystem.depthMask((boolean)true);
        BufferBuilder bufferOutside = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        for (int i3 = 0; i3 < 60; ++i3) {
            float angle1 = (float)i3 * ((float)Math.PI * 2) / 60.0f;
            float angle2 = (float)(i3 + 1) * ((float)Math.PI * 2) / 60.0f;
            float x1 = -MathHelper.sin((float)angle1) * width;
            float z1 = MathHelper.cos((float)angle1) * width;
            float x2 = -MathHelper.sin((float)angle2) * width;
            float z2 = MathHelper.cos((float)angle2) * width;
            bufferOutside.vertex(matrix, 0.0f, -coneHeight, 0.0f).color(centerColor);
            bufferOutside.vertex(matrix, x1, 0.0f, z1).color(edgeColor);
            bufferOutside.vertex(matrix, x2, 0.0f, z2).color(edgeColor);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)bufferOutside.end());
        BufferBuilder outlineBuffer = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        RenderSystem.lineWidth((float)2.0f);
        int outlineColor = this.getThemeColor(255);
        for (int i4 = 0; i4 < 60; ++i4) {
            float angle1 = (float)i4 * ((float)Math.PI * 2) / 60.0f;
            float angle2 = (float)(i4 + 1) * ((float)Math.PI * 2) / 60.0f;
            float x1 = -MathHelper.sin((float)angle1) * width;
            float z1 = MathHelper.cos((float)angle1) * width;
            float x2 = -MathHelper.sin((float)angle2) * width;
            float z2 = MathHelper.cos((float)angle2) * width;
            outlineBuffer.vertex(matrix, x1, 0.0f, z1).color(outlineColor);
            outlineBuffer.vertex(matrix, x2, 0.0f, z2).color(outlineColor);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)outlineBuffer.end());
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        matrixStack.pop();
    }

    private int getThemeColor(int alpha) {
        int themeColor = ColorProvider.getColorClient();
        int r2 = themeColor >> 16 & 0xFF;
        int g2 = themeColor >> 8 & 0xFF;
        int b2 = themeColor & 0xFF;
        return alpha << 24 | r2 << 16 | g2 << 8 | b2;
    }
}

