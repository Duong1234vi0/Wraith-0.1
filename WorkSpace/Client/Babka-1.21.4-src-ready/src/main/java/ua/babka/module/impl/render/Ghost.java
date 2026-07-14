package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Ghost", moduleDesc="Призрачное свечение вокруг", moduleCategory=ModuleCategory.RENDER)
public class Ghost
extends Module {
    private static final Identifier GLOW = Identifier.of((String)"mre", (String)"images/glow.png");
    public final BooleanSetting firstPerson = new BooleanSetting("Видеть от 1 лица", false);
    private final Vec3d[] ghostPos = new Vec3d[3];
    private final Vec3d[] ghostVel = new Vec3d[3];
    private final List<Vec3d>[] trails = new ArrayList[3];
    private long lastUpdate = 0L;

    public Ghost() {
        for (int i2 = 0; i2 < 3; ++i2) {
            this.trails[i2] = new ArrayList<Vec3d>();
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.lastUpdate = System.currentTimeMillis();
        for (int i2 = 0; i2 < 3; ++i2) {
            this.ghostPos[i2] = null;
            this.ghostVel[i2] = Vec3d.ZERO;
            this.trails[i2].clear();
        }
    }

    @Subscribe
    public void onWorldRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.firstPerson.getValue() && this.mc.options.getPerspective().isFirstPerson()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        MatrixStack matrices = e2.getMatrixStack();
        Camera camera = this.mc.gameRenderer.getCamera();
        float delta = e2.getTickDelta();
        long now = System.currentTimeMillis();
        if (this.lastUpdate == 0L) {
            this.lastUpdate = now;
        }
        float dt = (float)(now - this.lastUpdate) / 1000.0f;
        this.lastUpdate = now;
        if (dt > 0.1f) {
            dt = 0.1f;
        }
        if (dt <= 0.0f) {
            dt = 0.001f;
        }
        Vec3d playerPos = new Vec3d(player.prevX + (player.getX() - player.prevX) * (double)delta, player.prevY + (player.getY() - player.prevY) * (double)delta, player.prevZ + (player.getZ() - player.prevZ) * (double)delta);
        float age = (float)player.age + delta;
        float time = (float)(now % 100000L) / 1000.0f;
        int primary = ColorProvider.getThemeColor();
        int secondary = ColorProvider.getThemeColorTwo();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (int j2 = 0; j2 < 3; ++j2) {
            double radians = Math.toRadians(((double)age * 4.2 + (double)(j2 * 120)) % 360.0);
            float baseR = player.getWidth() * 1.2f;
            float dynR = baseR * (0.95f + 0.1f * (float)Math.sin((double)time * 0.9 + (double)j2));
            double tx = Math.cos(radians) * (double)dynR;
            double tz = Math.sin(radians) * (double)dynR;
            double ty = 0.2 + 0.13 * Math.sin((double)time * 2.2 + (double)j2);
            Vec3d target = playerPos.add(tx, ty, tz);
            if (this.ghostPos[j2] == null || this.ghostPos[j2].distanceTo(target) > 12.0) {
                this.ghostPos[j2] = target;
                this.ghostVel[j2] = Vec3d.ZERO;
            }
            Vec3d diff = target.subtract(this.ghostPos[j2]);
            double spring = 18.0;
            double damping = 0.82;
            this.ghostVel[j2] = this.ghostVel[j2].add(diff.multiply(spring * (double)dt)).multiply(Math.pow(damping, dt * 60.0f));
            this.ghostPos[j2] = this.ghostPos[j2].add(this.ghostVel[j2].multiply((double)(dt * 60.0f)));
            if (this.trails[j2].isEmpty() || this.trails[j2].get(0).distanceTo(this.ghostPos[j2]) > 0.012) {
                this.trails[j2].add(0, this.ghostPos[j2]);
                while (this.trails[j2].size() > 22) {
                    this.trails[j2].remove(this.trails[j2].size() - 1);
                }
            }
            float colorProgress = (float)j2 / 3.0f;
            int baseColor = this.interpolateColor(primary, secondary, colorProgress);
            int brightColor = this.interpolateColor(baseColor, -1, 0.32f);
            for (int i2 = 0; i2 < this.trails[j2].size(); ++i2) {
                Vec3d p2 = this.trails[j2].get(i2);
                float offset = 1.0f - (float)i2 / (float)this.trails[j2].size();
                float size = 0.16f + offset * 0.04f;
                this.drawGlow(buffer, matrices, camera, p2, size * 2.5f, this.withAlpha(baseColor, (int)(offset * 43.0f)));
                this.drawGlow(buffer, matrices, camera, p2, size * 1.4f, this.withAlpha(baseColor, (int)(offset * 90.0f)));
                this.drawGlow(buffer, matrices, camera, p2, size * 0.6f, this.withAlpha(brightColor, (int)(offset * 180.0f)));
            }
        }
        try {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        }
        catch (Exception exception) {
            // empty catch block
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    private void drawGlow(BufferBuilder buffer, MatrixStack matrices, Camera camera, Vec3d pos, float scale, int color) {
        Vec3d cam = camera.getPos();
        matrices.push();
        matrices.translate(pos.x - cam.x, pos.y - cam.y, pos.z - cam.z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        Matrix4f mat = matrices.peek().getPositionMatrix();
        buffer.vertex(mat, -scale, scale, 0.0f).texture(0.0f, 1.0f).color(color);
        buffer.vertex(mat, scale, scale, 0.0f).texture(1.0f, 1.0f).color(color);
        buffer.vertex(mat, scale, -scale, 0.0f).texture(1.0f, 0.0f).color(color);
        buffer.vertex(mat, -scale, -scale, 0.0f).texture(0.0f, 0.0f).color(color);
        matrices.pop();
    }

    private int withAlpha(int color, int alpha) {
        return MathHelper.clamp((int)alpha, (int)0, (int)255) << 24 | color & 0xFFFFFF;
    }

    private int interpolateColor(int c1, int c2, float t2) {
        t2 = MathHelper.clamp((float)t2, (float)0.0f, (float)1.0f);
        int r1 = c1 >> 16 & 0xFF;
        int g1 = c1 >> 8 & 0xFF;
        int b1 = c1 & 0xFF;
        int r2 = c2 >> 16 & 0xFF;
        int g2 = c2 >> 8 & 0xFF;
        int b2 = c2 & 0xFF;
        int r3 = (int)((float)r1 + (float)(r2 - r1) * t2);
        int g3 = (int)((float)g1 + (float)(g2 - g1) * t2);
        int b3 = (int)((float)b1 + (float)(b2 - b1) * t2);
        return 0xFF000000 | r3 << 16 | g3 << 8 | b3;
    }
}

