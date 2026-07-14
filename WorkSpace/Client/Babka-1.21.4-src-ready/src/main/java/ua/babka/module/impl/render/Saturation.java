package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import ua.babka.event.EventHUD;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Saturation", moduleDesc="Изменяет насыщенность цветов на экране", moduleCategory=ModuleCategory.RENDER)
public class Saturation
extends Module {
    private static final ShaderProgramKey SATURATION_SHADER_KEY = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/saturation"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private final SliderSetting saturation = new SliderSetting("Насыщенность", 1.0, 0.0, 2.0, 0.05f);
    private SimpleFramebuffer tempFbo;
    private int fbWidth = -1;
    private int fbHeight = -1;

    @Override
    public void onDisable() {
        if (this.tempFbo != null) {
            this.tempFbo.delete();
            this.tempFbo = null;
        }
        this.fbWidth = -1;
        this.fbHeight = -1;
        super.onDisable();
    }

    @Subscribe
    public void onHudRender(EventHUD event) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        float sat = this.saturation.getFloatValue();
        if (Math.abs(sat - 1.0f) < 0.001f) {
            return;
        }
        Framebuffer main = this.mc.getFramebuffer();
        this.ensureTempFbo(main);
        if (this.tempFbo == null) {
            return;
        }
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
        this.tempFbo.beginWrite(false);
        RenderSystem.clear((int)16384);
        main.draw(this.tempFbo.textureWidth, this.tempFbo.textureHeight);
        main.beginWrite(false);
        RenderSystem.setShaderTexture((int)0, (int)this.tempFbo.getColorAttachment());
        ShaderProgram shader = this.mc.getShaderLoader().getOrCreateProgram(SATURATION_SHADER_KEY);
        if (shader == null) {
            return;
        }
        RenderSystem.setShader((ShaderProgramKey)SATURATION_SHADER_KEY);
        GlUniform uniform = shader.getUniform("Saturation");
        if (uniform != null) {
            uniform.set(sat);
        }
        this.drawFullscreenQuad();
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
    }

    private void ensureTempFbo(Framebuffer main) {
        int w2 = main.textureWidth;
        int h2 = main.textureHeight;
        if (this.tempFbo == null || this.fbWidth != w2 || this.fbHeight != h2) {
            if (this.tempFbo != null) {
                this.tempFbo.delete();
            }
            this.tempFbo = new SimpleFramebuffer(w2, h2, false);
            this.fbWidth = w2;
            this.fbHeight = h2;
        }
    }

    private void drawFullscreenQuad() {
        float width = Math.max(this.mc.getWindow().getScaledWidth(), 1);
        float height = Math.max(this.mc.getWindow().getScaledHeight(), 1);
        BufferBuilder b2 = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        b2.vertex(0.0f, 0.0f, 0.0f).texture(0.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(0.0f, height, 0.0f).texture(0.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(width, height, 0.0f).texture(1.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(width, 0.0f, 0.0f).texture(1.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)b2.end());
    }
}

