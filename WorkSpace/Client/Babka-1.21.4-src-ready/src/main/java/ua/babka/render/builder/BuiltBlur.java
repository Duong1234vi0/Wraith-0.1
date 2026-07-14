package ua.babka.render.builder;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.SimpleFramebuffer;
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

public record BuiltBlur(SizeState size, QuadRadiusState radius, QuadColorState color, float smoothness, float blurRadius) implements IRenderer
{
    private static final ShaderProgramKey BLUR_SHADER_KEY = new ShaderProgramKey(ResourceProvider.getShaderIdentifier("blur"), VertexFormats.POSITION_COLOR, Defines.EMPTY);
    private static final Supplier<SimpleFramebuffer> TEMP_FBO_SUPPLIER = Suppliers.memoize(() -> new SimpleFramebuffer(1920, 1024, false));
    private static final Framebuffer MAIN_FBO = MinecraftClient.getInstance().getFramebuffer();

    @Override
    public void render(Matrix4f matrix, float x2, float y2, float z2) {
        Framebuffer main = MinecraftClient.getInstance().getFramebuffer();
        SimpleFramebuffer fbo = (SimpleFramebuffer)TEMP_FBO_SUPPLIER.get();
        if (fbo.textureWidth != main.textureWidth || fbo.textureHeight != main.textureHeight) {
            fbo.resize(main.textureWidth, main.textureHeight);
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        fbo.beginWrite(false);
        main.draw(fbo.textureWidth, fbo.textureHeight);
        main.beginWrite(false);
        RenderSystem.setShaderTexture((int)0, (int)fbo.getColorAttachment());
        float width = this.size.width();
        float height = this.size.height();
        ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)BLUR_SHADER_KEY);
        shader.getUniform("Size").set(width, height);
        shader.getUniform("Radius").set(this.radius.radius1(), this.radius.radius2(), this.radius.radius3(), this.radius.radius4());
        shader.getUniform("Smoothness").set(this.smoothness);
        shader.getUniform("BlurRadius").set(this.blurRadius);
        BufferBuilder builder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        builder.vertex(matrix, x2, y2, z2).color(this.color.color1());
        builder.vertex(matrix, x2, y2 + height, z2).color(this.color.color2());
        builder.vertex(matrix, x2 + width, y2 + height, z2).color(this.color.color3());
        builder.vertex(matrix, x2 + width, y2, z2).color(this.color.color4());
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)builder.end());
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
}

