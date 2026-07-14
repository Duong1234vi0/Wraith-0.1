package ua.babka.mixin;

import com.mojang.blaze3d.systems.ProjectionType;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.RenderPass;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.Babka;
import ua.babka.module.impl.render.Ambience;
import ua.babka.render.ColorProvider;

@Mixin(value={WorldRenderer.class})
public class SkyShaderMixin {
    private static final ShaderProgramKey babkaSkyShader = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/ambience_sky/ambience_sky"), VertexFormats.POSITION, Defines.EMPTY);
    private static final ShaderProgramKey summerSkyShader = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/ambience_sky/ambience_sky_summer"), VertexFormats.POSITION, Defines.EMPTY);
    private static final ShaderProgramKey plasmaSkyShader = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/ambience_sky/ambience_sky_plasma"), VertexFormats.POSITION, Defines.EMPTY);
    private static final ShaderProgramKey balatroSkyShader = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/ambience_sky/ambience_sky_balatro"), VertexFormats.POSITION, Defines.EMPTY);
    @Shadow
    private DefaultFramebufferSet framebufferSet;
    @Shadow
    private MinecraftClient client;

    @Inject(method={"renderSky"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderSky(FrameGraphBuilder frameGraphBuilder, Camera camera, float tickDelta, Fog fog, CallbackInfo ci) {
        try {
            if (Babka.getInstance() == null) {
                return;
            }
            Ambience ambience = Babka.getInstance().getModuleStorage().get(Ambience.class);
            if (ambience == null || !ambience.isEnabled() || !ambience.skyMode.is("Shader")) {
                return;
            }
            ci.cancel();
            RenderPass skyPass = frameGraphBuilder.createPass("babka_sky");
            this.framebufferSet.mainFramebuffer = skyPass.transfer(this.framebufferSet.mainFramebuffer);
            skyPass.markToBeVisited();
            String shaderType = ambience.skyType.getValue();
            boolean throughWalls = ambience.skyThroughWalls.getValue();
            float speed = ambience.skySpeed.getFloatValue();
            float scale = ambience.skyScale.getFloatValue();
            float alpha = ambience.skyAlpha.getFloatValue();
            skyPass.setRenderer(() -> {
                ShaderProgram shader = RenderSystem.setShader((ShaderProgramKey)(switch (shaderType) {
                    case "Лето" -> summerSkyShader;
                    case "Плазма" -> plasmaSkyShader;
                    case "Балатро" -> balatroSkyShader;
                    default -> babkaSkyShader;
                }));
                if (shader == null) {
                    return;
                }
                int color1 = ColorProvider.getThemeColor();
                int color2 = ColorProvider.getThemeColorTwo();
                float fw = this.client.getFramebuffer().textureWidth;
                float fh = this.client.getFramebuffer().textureHeight;
                float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0f;
                float yawRad = (float)Math.toRadians(-camera.getYaw());
                float pitchRad = (float)Math.toRadians(camera.getPitch());
                SkyShaderMixin.setUniformVec3(shader, "uColor", (float)(color1 >> 16 & 0xFF) / 255.0f, (float)(color1 >> 8 & 0xFF) / 255.0f, (float)(color1 & 0xFF) / 255.0f);
                SkyShaderMixin.setUniformVec3(shader, "uColor2", (float)(color2 >> 16 & 0xFF) / 255.0f, (float)(color2 >> 8 & 0xFF) / 255.0f, (float)(color2 & 0xFF) / 255.0f);
                SkyShaderMixin.setUniformFloat(shader, "uTime", time);
                SkyShaderMixin.setUniformFloat(shader, "uSpeed", speed);
                SkyShaderMixin.setUniformFloat(shader, "uScale", scale);
                SkyShaderMixin.setUniformFloat(shader, "uAlpha", alpha);
                SkyShaderMixin.setUniformVec2(shader, "uResolution", fw, fh);
                SkyShaderMixin.setUniformVec2(shader, "uCameraDir", yawRad, pitchRad);
                SkyShaderMixin.setUniformFloat(shader, "uFov", ((Integer)this.client.options.getFov().getValue()).intValue());
                Matrix4f savedProj = new Matrix4f((Matrix4fc)RenderSystem.getProjectionMatrix());
                RenderSystem.setProjectionMatrix((Matrix4f)new Matrix4f(), (ProjectionType)ProjectionType.ORTHOGRAPHIC);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask((boolean)false);
                RenderSystem.disableCull();
                if (throughWalls) {
                    RenderSystem.disableDepthTest();
                } else {
                    RenderSystem.enableDepthTest();
                    RenderSystem.depthFunc((int)515);
                }
                Matrix4f identity = new Matrix4f();
                BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
                buf.vertex(identity, -1.0f, -1.0f, 1.0f);
                buf.vertex(identity, 1.0f, -1.0f, 1.0f);
                buf.vertex(identity, 1.0f, 1.0f, 1.0f);
                buf.vertex(identity, -1.0f, 1.0f, 1.0f);
                BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
                RenderSystem.depthMask((boolean)true);
                RenderSystem.enableCull();
                RenderSystem.disableBlend();
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                RenderSystem.depthFunc((int)515);
                RenderSystem.enableDepthTest();
                RenderSystem.setProjectionMatrix((Matrix4f)savedProj, (ProjectionType)ProjectionType.PERSPECTIVE);
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void setUniformFloat(ShaderProgram shader, String name, float value) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(value);
        }
    }

    private static void setUniformVec2(ShaderProgram shader, String name, float x2, float y2) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(x2, y2);
        }
    }

    private static void setUniformVec3(ShaderProgram shader, String name, float x2, float y2, float z2) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(x2, y2, z2);
        }
    }
}

