package ua.babka.render.shader;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
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
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import ua.babka.module.impl.render.ShaderHands;
import ua.babka.render.ColorProvider;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;

public class ShaderHandsRenderer
implements IMinecraft {
    private static final float EPSILON = 0.001f;
    private static final ShaderProgramKey shaderHandsMaskDiff = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/hands/hands_mask_diff"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final ShaderProgramKey shaderHandsGlow = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/hands/hands_glow"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final ShaderProgramKey shaderHandsOverlay = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/hands/hands_overlay"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final ShaderProgramKey shaderHandsKawaseDown = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/hands/hands_kawase_down"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final ShaderProgramKey shaderHandsKawaseUp = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/hands/hands_kawase_up"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static final ShaderProgramKey blockOverlay = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/blockoverlay/block_overlay"), VertexFormats.POSITION_TEXTURE_COLOR, Defines.EMPTY);
    private static ShaderHandsRenderer instance;
    private Framebuffer beforeBuffer;
    private Framebuffer afterBuffer;
    private Framebuffer maskBuffer;
    private final List<Framebuffer> bloomBuffers = new ArrayList<Framebuffer>();
    private int width = -1;
    private int height = -1;
    private boolean hasBeforeCapture;
    private boolean pendingComposite;
    private int configuredBeforeDepthTex = -1;
    private int configuredAfterDepthTex = -1;

    public static ShaderHandsRenderer getInstance() {
        if (instance == null) {
            instance = new ShaderHandsRenderer();
        }
        return instance;
    }

    public void captureBeforeHands() {
        ShaderHands module = this.getModule();
        if (!this.isEffectEnabled(module)) {
            this.invalidateState();
            return;
        }
        this.ensureBuffers();
        if (this.beforeBuffer == null) {
            return;
        }
        this.copyMainFramebuffer(this.beforeBuffer);
        this.hasBeforeCapture = true;
    }

    public void captureAfterHands() {
        ShaderHands module = this.getModule();
        if (!this.isEffectEnabled(module)) {
            this.invalidateState();
            return;
        }
        this.ensureBuffers();
        if (this.beforeBuffer == null || this.afterBuffer == null || this.maskBuffer == null) {
            return;
        }
        if (!this.hasBeforeCapture) {
            return;
        }
        this.copyMainFramebuffer(this.afterBuffer);
        this.pendingComposite = true;
    }

    public void renderOverlayIfPending() {
        ShaderProgram glowShader;
        if (!this.pendingComposite) {
            return;
        }
        this.ensureBuffers();
        if (this.beforeBuffer == null || this.afterBuffer == null || this.maskBuffer == null) {
            return;
        }
        ShaderHands module = this.getModule();
        if (!this.isEffectEnabled(module)) {
            this.invalidateState();
            return;
        }
        ShaderProgram maskShader = mc.getShaderLoader().getOrCreateProgram(shaderHandsMaskDiff);
        if (maskShader == null) {
            this.invalidateState();
            return;
        }
        this.maskBuffer.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.maskBuffer.clear();
        this.maskBuffer.beginWrite(false);
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShader((ShaderProgramKey)shaderHandsMaskDiff);
        RenderSystem.setShaderTexture((int)0, (int)this.beforeBuffer.getColorAttachment());
        RenderSystem.setShaderTexture((int)1, (int)this.afterBuffer.getColorAttachment());
        int beforeDepth = this.beforeBuffer.getDepthAttachment();
        int afterDepth = this.afterBuffer.getDepthAttachment();
        if (beforeDepth != 0 && beforeDepth != this.configuredBeforeDepthTex) {
            this.configureDepthTexture(beforeDepth);
            this.configuredBeforeDepthTex = beforeDepth;
        }
        if (afterDepth != 0 && afterDepth != this.configuredAfterDepthTex) {
            this.configureDepthTexture(afterDepth);
            this.configuredAfterDepthTex = afterDepth;
        }
        RenderSystem.setShaderTexture((int)2, (int)beforeDepth);
        RenderSystem.setShaderTexture((int)3, (int)afterDepth);
        this.drawFullscreenQuad();
        RenderSystem.enableDepthTest();
        float glowValue = module.glow.getFloatValue();
        float fillValue = module.fill.getFloatValue();
        float alphaValue = module.alpha.getFloatValue();
        float outlineValue = module.outline.getFloatValue();
        boolean hasGlow = glowValue > 0.001f;
        boolean hasFill = fillValue > 0.001f && alphaValue > 0.001f;
        int color1 = ColorProvider.getThemeColor();
        int color2 = ColorProvider.getThemeColor();
        if (module.mode.is("Красивый")) {
            this.renderPrettyMode(module, color1, color2, glowValue, fillValue, alphaValue, outlineValue);
            this.invalidateState();
            return;
        }
        int blurredMaskTexture = 0;
        if (hasGlow) {
            int iterations = Math.max(3, Math.min(8, 4 + Math.round(outlineValue * 0.7f)));
            blurredMaskTexture = this.runKawaseBloom(iterations);
        }
        mc.getFramebuffer().beginWrite(true);
        RenderSystem.enableBlend();
        RenderSystem.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
        RenderSystem.disableDepthTest();
        ShaderProgram shaderProgram = glowShader = hasGlow ? mc.getShaderLoader().getOrCreateProgram(shaderHandsGlow) : null;
        if (glowShader != null) {
            RenderSystem.blendFuncSeparate((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE, (GlStateManager.SrcFactor)GlStateManager.SrcFactor.ZERO, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            RenderSystem.setShader((ShaderProgramKey)shaderHandsGlow);
            RenderSystem.setShaderTexture((int)0, (int)blurredMaskTexture);
            RenderSystem.setShaderTexture((int)1, (int)this.maskBuffer.getColorAttachment());
            this.setUniform(glowShader, "color", (float)ColorProvider.red(color1) / 255.0f, (float)ColorProvider.green(color1) / 255.0f, (float)ColorProvider.blue(color1) / 255.0f);
            this.setUniform(glowShader, "color2", (float)ColorProvider.red(color2) / 255.0f, (float)ColorProvider.green(color2) / 255.0f, (float)ColorProvider.blue(color2) / 255.0f);
            this.setUniform(glowShader, "exposure", 1.0f + glowValue * 1.8f);
            this.drawFullscreenQuad();
        }
        if (hasFill) {
            ShaderProgram overlayShader = mc.getShaderLoader().getOrCreateProgram(shaderHandsOverlay);
            if (overlayShader == null) {
                this.restoreCompositeState();
                this.invalidateState();
                return;
            }
            RenderSystem.blendFuncSeparate((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SrcFactor)GlStateManager.SrcFactor.ZERO, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            RenderSystem.setShader((ShaderProgramKey)shaderHandsOverlay);
            RenderSystem.setShaderTexture((int)0, (int)this.maskBuffer.getColorAttachment());
            this.setUniform(overlayShader, "color", (float)ColorProvider.red(color1) / 255.0f, (float)ColorProvider.green(color1) / 255.0f, (float)ColorProvider.blue(color1) / 255.0f);
            this.setUniform(overlayShader, "fill", fillValue);
            this.setUniform(overlayShader, "alpha", alphaValue);
            this.drawFullscreenQuad();
        }
        this.restoreCompositeState();
        this.invalidateState();
    }

    public void invalidateState() {
        this.hasBeforeCapture = false;
        this.pendingComposite = false;
        this.configuredBeforeDepthTex = -1;
        this.configuredAfterDepthTex = -1;
    }

    private int runKawaseBloom(int iterations) {
        Framebuffer dst;
        int i2;
        this.ensureBloomBuffers(iterations);
        if (this.bloomBuffers.isEmpty()) {
            return this.maskBuffer.getColorAttachment();
        }
        int currentTexture = this.maskBuffer.getColorAttachment();
        ShaderProgram downShader = mc.getShaderLoader().getOrCreateProgram(shaderHandsKawaseDown);
        ShaderProgram upShader = mc.getShaderLoader().getOrCreateProgram(shaderHandsKawaseUp);
        if (downShader == null || upShader == null) {
            return currentTexture;
        }
        for (i2 = 0; i2 < iterations; ++i2) {
            dst = this.bloomBuffers.get(i2);
            dst.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            dst.clear();
            dst.beginWrite(true);
            RenderSystem.setShader((ShaderProgramKey)shaderHandsKawaseDown);
            RenderSystem.setShaderTexture((int)0, (int)currentTexture);
            this.setHandsKawaseUniforms(downShader, dst.textureWidth, dst.textureHeight, 1.0f + (float)i2);
            this.drawFullscreenQuad();
            currentTexture = dst.getColorAttachment();
        }
        for (i2 = iterations - 1; i2 >= 1; --i2) {
            dst = this.bloomBuffers.get(i2 - 1);
            dst.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            dst.clear();
            dst.beginWrite(true);
            RenderSystem.setShader((ShaderProgramKey)shaderHandsKawaseUp);
            RenderSystem.setShaderTexture((int)0, (int)currentTexture);
            this.setHandsKawaseUniforms(upShader, dst.textureWidth, dst.textureHeight, 1.0f + (float)i2);
            this.setUniform(upShader, "color", 1.0f, 1.0f, 1.0f);
            this.drawFullscreenQuad();
            currentTexture = dst.getColorAttachment();
        }
        mc.getFramebuffer().beginWrite(true);
        return currentTexture;
    }

    private void copyMainFramebuffer(Framebuffer target) {
        int readFbo = GL11.glGetInteger((int)36010);
        int drawFbo = GL11.glGetInteger((int)36006);
        GL30.glBindFramebuffer((int)36008, (int)ShaderHandsRenderer.mc.getFramebuffer().fbo);
        GL30.glBindFramebuffer((int)36009, (int)target.fbo);
        GL30.glBlitFramebuffer((int)0, (int)0, (int)this.width, (int)this.height, (int)0, (int)0, (int)this.width, (int)this.height, (int)16640, (int)9728);
        GL30.glBindFramebuffer((int)36008, (int)readFbo);
        GL30.glBindFramebuffer((int)36009, (int)drawFbo);
        mc.getFramebuffer().beginWrite(true);
    }

    private void configureDepthTexture(int depthTex) {
        RenderSystem.bindTexture((int)depthTex);
        GL11.glTexParameteri((int)3553, (int)34892, (int)0);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        RenderSystem.bindTexture((int)0);
    }

    private void ensureBuffers() {
        int w2 = mc.getWindow().getFramebufferWidth();
        int h2 = mc.getWindow().getFramebufferHeight();
        if (w2 == this.width && h2 == this.height && this.beforeBuffer != null && this.afterBuffer != null && this.maskBuffer != null) {
            return;
        }
        if (this.beforeBuffer != null) {
            this.beforeBuffer.delete();
        }
        if (this.afterBuffer != null) {
            this.afterBuffer.delete();
        }
        if (this.maskBuffer != null) {
            this.maskBuffer.delete();
        }
        for (Framebuffer fb : this.bloomBuffers) {
            fb.delete();
        }
        this.bloomBuffers.clear();
        this.beforeBuffer = new SimpleFramebuffer(w2, h2, true);
        this.afterBuffer = new SimpleFramebuffer(w2, h2, true);
        this.maskBuffer = new SimpleFramebuffer(w2, h2, true);
        this.width = w2;
        this.height = h2;
        this.configuredBeforeDepthTex = -1;
        this.configuredAfterDepthTex = -1;
    }

    private void ensureBloomBuffers(int iterations) {
        while (this.bloomBuffers.size() > iterations) {
            int last = this.bloomBuffers.size() - 1;
            this.bloomBuffers.get(last).delete();
            this.bloomBuffers.remove(last);
        }
        for (int i2 = 0; i2 < iterations; ++i2) {
            Framebuffer fb;
            int w2 = Math.max(2, this.width >> i2 + 1);
            int h2 = Math.max(2, this.height >> i2 + 1);
            if (i2 >= this.bloomBuffers.size()) {
                fb = new SimpleFramebuffer(w2, h2, false);
                this.setLinearFiltering(fb);
                this.bloomBuffers.add(fb);
                continue;
            }
            fb = this.bloomBuffers.get(i2);
            if (fb.textureWidth == w2 && fb.textureHeight == h2) continue;
            fb.delete();
            fb = new SimpleFramebuffer(w2, h2, false);
            this.setLinearFiltering(fb);
            this.bloomBuffers.set(i2, fb);
        }
    }

    private void setLinearFiltering(Framebuffer fb) {
        RenderSystem.bindTexture((int)fb.getColorAttachment());
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        RenderSystem.bindTexture((int)0);
    }

    private ShaderHands getModule() {
        return Instance.get(ShaderHands.class);
    }

    private void renderPrettyMode(ShaderHands module, int color1, int color2, float glowValue, float fillValue, float alphaValue, float outlineValue) {
        ShaderProgram shader = mc.getShaderLoader().getOrCreateProgram(blockOverlay);
        if (shader == null) {
            return;
        }
        mc.getFramebuffer().beginWrite(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader((ShaderProgramKey)blockOverlay);
        RenderSystem.setShaderTexture((int)0, (int)this.maskBuffer.getColorAttachment());
        this.setUniform(shader, "texelSize", 1.0f / (float)Math.max(1, mc.getWindow().getFramebufferWidth()), 1.0f / (float)Math.max(1, mc.getWindow().getFramebufferHeight()));
        this.setUniform(shader, "color", (float)ColorProvider.red(color1) / 255.0f, (float)ColorProvider.green(color1) / 255.0f, (float)ColorProvider.blue(color1) / 255.0f);
        this.setUniform(shader, "color2", (float)ColorProvider.red(color2) / 255.0f, (float)ColorProvider.green(color2) / 255.0f, (float)ColorProvider.blue(color2) / 255.0f);
        this.setUniform(shader, "time", (float)(System.currentTimeMillis() % 100000L) / 1000.0f);
        this.setUniform(shader, "speed", module.waveSpeed.getFloatValue());
        this.setUniform(shader, "scale", module.waveScale.getFloatValue());
        this.setUniform(shader, "outline", outlineValue);
        this.setUniform(shader, "glow", glowValue);
        this.setUniform(shader, "fill", fillValue);
        this.setUniform(shader, "alpha", alphaValue);
        this.setUniform(shader, "outlineOnly", 0.0f);
        this.drawFullscreenQuad();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        this.restoreCompositeState();
    }

    private void restoreCompositeState() {
        RenderSystem.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.setShaderTexture((int)1, (int)0);
        RenderSystem.setShaderTexture((int)2, (int)0);
        RenderSystem.setShaderTexture((int)3, (int)0);
        mc.getFramebuffer().beginWrite(true);
    }

    private boolean isEffectEnabled(ShaderHands module) {
        if (module == null || !module.isEnabled()) {
            return false;
        }
        boolean hasGlow = module.glow.getFloatValue() > 0.001f;
        boolean hasFill = module.fill.getFloatValue() > 0.001f && module.alpha.getFloatValue() > 0.001f;
        return hasGlow || hasFill;
    }

    private void setUniform(ShaderProgram shader, String name, float v2) {
        GlUniform u2 = shader.getUniform(name);
        if (u2 != null) {
            u2.set(v2);
        }
    }

    private void setUniform(ShaderProgram shader, String name, float x2, float y2) {
        GlUniform u2 = shader.getUniform(name);
        if (u2 != null) {
            u2.set(x2, y2);
        }
    }

    private void setUniform(ShaderProgram shader, String name, float x2, float y2, float z2) {
        GlUniform u2 = shader.getUniform(name);
        if (u2 != null) {
            u2.set(x2, y2, z2);
        }
    }

    private void setHandsKawaseUniforms(ShaderProgram shader, int texWidth, int texHeight, float offset) {
        this.setUniform(shader, "uSize", Math.max(1, texWidth), Math.max(1, texHeight));
        this.setUniform(shader, "uOffset", offset, offset);
        this.setUniform(shader, "uHalfPixel", 0.5f / (float)Math.max(1, texWidth), 0.5f / (float)Math.max(1, texHeight));
    }

    private void drawFullscreenQuad() {
        float sw = Math.max(mc.getWindow().getScaledWidth(), 1);
        float sh = Math.max(mc.getWindow().getScaledHeight(), 1);
        BufferBuilder b2 = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        b2.vertex(0.0f, 0.0f, 0.0f).texture(0.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(0.0f, sh, 0.0f).texture(0.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(sw, sh, 0.0f).texture(1.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        b2.vertex(sw, 0.0f, 0.0f).texture(1.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)b2.end());
    }
}

