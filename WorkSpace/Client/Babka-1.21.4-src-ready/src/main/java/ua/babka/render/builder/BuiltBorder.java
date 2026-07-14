package ua.babka.render.builder;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import org.joml.Matrix4f;
import ua.babka.render.ResourceProvider;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.model.IRenderer;

public record BuiltBorder(SizeState size, QuadRadiusState radius, QuadColorState color, float thickness, float internalSmoothness, float externalSmoothness) implements IRenderer
{
    private static final ShaderProgramKey RECTANGLE_SHADER_KEY = new ShaderProgramKey(ResourceProvider.getShaderIdentifier("border"), VertexFormats.POSITION_COLOR, Defines.EMPTY);

    @Override
    public void render(Matrix4f matrix, float x2, float y2, float z2) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        float width = this.size.width();
        float height = this.size.height();
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)RECTANGLE_SHADER_KEY);
        shader.getUniform("Size").set(width, height);
        shader.getUniform("Radius").set(this.radius.radius1(), this.radius.radius2(), this.radius.radius3(), this.radius.radius4());
        shader.getUniform("Thickness").set(this.thickness);
        shader.getUniform("Smoothness").set(this.internalSmoothness, this.externalSmoothness);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        builder.vertex(matrix, x2, y2, z2).color(this.color.color1());
        builder.vertex(matrix, x2, y2 + height, z2).color(this.color.color2());
        builder.vertex(matrix, x2 + width, y2 + height, z2).color(this.color.color3());
        builder.vertex(matrix, x2 + width, y2, z2).color(this.color.color4());
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
}

