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

public record BuiltGlow(SizeState size, QuadRadiusState radius, QuadColorState color, float glowRadius, float softness, float intensity, boolean additive) implements IRenderer
{
    private static final ShaderProgramKey GLOW_SHADER_KEY = new ShaderProgramKey(ResourceProvider.getShaderIdentifier("glow"), VertexFormats.POSITION_COLOR, Defines.EMPTY);

    @Override
    public void render(Matrix4f matrix, float x2, float y2, float z2) {
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.defaultBlendFunc();
        float w2 = this.size.width();
        float h2 = this.size.height();
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)GLOW_SHADER_KEY);
        shader.getUniform("Size").set(w2, h2);
        shader.getUniform("Radius").set(this.radius.radius1(), this.radius.radius2(), this.radius.radius3(), this.radius.radius4());
        shader.getUniform("GlowRadius").set(this.glowRadius);
        shader.getUniform("Softness").set(this.softness);
        shader.getUniform("Intensity").set(this.intensity);
        BufferBuilder bb2 = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bb2.vertex(matrix, x2, y2, z2).color(this.color.color1());
        bb2.vertex(matrix, x2, y2 + h2, z2).color(this.color.color2());
        bb2.vertex(matrix, x2 + w2, y2 + h2, z2).color(this.color.color3());
        bb2.vertex(matrix, x2 + w2, y2, z2).color(this.color.color4());
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)bb2.end());
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
}

