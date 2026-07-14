package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="JumpCircle", moduleDesc="Круг в момент прыжка", moduleCategory=ModuleCategory.RENDER)
public class JumpCircle
extends Module {
    private static final float MAX_LIFETIME_MS = 1850.0f;
    private static final float ROTATION_SPEED = 120.0f;
    private static final float PULSE_SPEED = 7.0f;
    private static final float PULSE_SCALE = 0.06f;
    private static final float PULSE_ALPHA = 0.12f;
    private static final int MAX_CIRCLES = 8;
    private static final Identifier CIRCLE_TEXTURE = Identifier.of((String)"mre", (String)"images/jump/circle.png");
    private final SliderSetting radius = new SliderSetting("Радиус", 1.85, 0.5, 4.0, 0.1);
    private final SliderSetting speed = new SliderSetting("Скорость", 1.2, 1.0, 5.0, 0.1);
    private final SliderSetting fadeSpeed = new SliderSetting("Скорость исчезновения", 1.5, 1.0, 5.0, 0.5);
    private final List<CircleData> circles = new ArrayList<CircleData>();
    private boolean wasOnGround = true;

    @Override
    public void onEnable() {
        if (this.mc.player != null) {
            this.wasOnGround = this.mc.player.isOnGround();
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.circles.clear();
        super.onDisable();
    }

    @Subscribe
    public void onTick(EventTick event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        boolean isOnGround = this.mc.player.isOnGround();
        if (this.wasOnGround && !isOnGround) {
            Vec3d pos = new Vec3d(this.mc.player.getX(), Math.floor(this.mc.player.getY()) + 0.001, this.mc.player.getZ());
            this.circles.add(new CircleData(pos, System.currentTimeMillis()));
            while (this.circles.size() > 8) {
                this.circles.remove(0);
            }
        }
        this.wasOnGround = isOnGround;
        long now = System.currentTimeMillis();
        float lifeTimeMs = this.getLifeTimeMs();
        Iterator<CircleData> it = this.circles.iterator();
        while (it.hasNext()) {
            if (now - it.next().startTimeMs <= (long)lifeTimeMs) continue;
            it.remove();
        }
    }

    @Subscribe
    public void onWorldRender(EventWorldRender event) {
        if (this.circles.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d cameraPos = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack ms = event.getMatrixStack();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.blendFunc((int)770, (int)1);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)CIRCLE_TEXTURE);
        for (CircleData circle : this.circles) {
            float alpha;
            float progress = this.getProgress(now, circle);
            if (progress >= 1.0f || (alpha = this.getAlpha(progress)) <= 0.01f) continue;
            this.renderGlowCircle(ms, cameraPos, circle, progress, alpha, now);
        }
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private float getLifeTimeMs() {
        return 1850.0f / Math.max(0.25f, (float)this.speed.getValue());
    }

    private float getProgress(long now, CircleData circle) {
        return (float)(now - circle.startTimeMs) / this.getLifeTimeMs();
    }

    private float getAlpha(float progress) {
        float fade = MathHelper.clamp((float)(progress * (float)this.fadeSpeed.getValue()), (float)0.0f, (float)1.0f);
        return 1.0f - fade;
    }

    private void renderGlowCircle(MatrixStack ms, Vec3d cam, CircleData circle, float progress, float alpha, long now) {
        float lifeTimeSec = (float)(now - circle.startTimeMs) / 1000.0f;
        float easedProgress = JumpCircle.easeOutCubic(progress);
        float scale = Math.min(easedProgress * (float)this.radius.getValue(), (float)this.radius.getValue());
        float rotation = lifeTimeSec * 120.0f * (float)this.speed.getValue();
        rotation += (float)Math.sin((double)progress * Math.PI * 2.0) * 30.0f;
        float pulse = (float)Math.sin(lifeTimeSec * 7.0f * (float)this.speed.getValue());
        float pulseScale = 1.0f + pulse * 0.06f;
        float pulseAlpha = MathHelper.clamp((float)(alpha * (1.0f + pulse * 0.12f)), (float)0.0f, (float)1.0f);
        float alphaBoost = MathHelper.clamp((float)(pulseAlpha * 1.25f), (float)0.0f, (float)1.0f);
        float finalScale = scale * pulseScale;
        int theme = ColorProvider.getThemeColor();
        int themeTwo = ColorProvider.getThemeColorTwo();
        int colorA = ColorProvider.setAlpha(theme, (int)(255.0f * alphaBoost));
        int colorB = ColorProvider.setAlpha(themeTwo, (int)(255.0f * alphaBoost));
        int darkA = ColorProvider.setAlpha(JumpCircle.darken(theme, 0.65f), (int)(255.0f * MathHelper.clamp((float)(alphaBoost * 0.9f), (float)0.0f, (float)1.0f)));
        int darkB = ColorProvider.setAlpha(JumpCircle.darken(themeTwo, 0.65f), (int)(255.0f * MathHelper.clamp((float)(alphaBoost * 0.9f), (float)0.0f, (float)1.0f)));
        ms.push();
        ms.translate(circle.pos.x - cam.x, circle.pos.y - cam.y, circle.pos.z - cam.z);
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));
        ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation));
        Matrix4f matrix = ms.peek().getPositionMatrix();
        float half = finalScale * 0.5f;
        float thickScale = finalScale * 1.08f;
        float thickHalf = thickScale * 0.5f;
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        this.addTexturedQuad(buf, matrix, -half, -half, half, half, colorA, colorB);
        this.addTexturedQuad(buf, matrix, -thickHalf, -thickHalf, thickHalf, thickHalf, darkA, darkB);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        ms.pop();
    }

    private void addTexturedQuad(BufferBuilder buf, Matrix4f mat, float x1, float y1, float x2, float y2, int colorA, int colorB) {
        int aR = colorA >> 16 & 0xFF;
        int aG = colorA >> 8 & 0xFF;
        int aB = colorA & 0xFF;
        int aA = colorA >> 24 & 0xFF;
        int bR = colorB >> 16 & 0xFF;
        int bG = colorB >> 8 & 0xFF;
        int bB = colorB & 0xFF;
        int bA = colorB >> 24 & 0xFF;
        buf.vertex(mat, x1, y1, 0.0f).texture(0.0f, 1.0f).color(aR, aG, aB, aA);
        buf.vertex(mat, x1, y2, 0.0f).texture(0.0f, 0.0f).color(bR, bG, bB, bA);
        buf.vertex(mat, x2, y2, 0.0f).texture(1.0f, 0.0f).color(bR, bG, bB, bA);
        buf.vertex(mat, x2, y1, 0.0f).texture(1.0f, 1.0f).color(aR, aG, aB, aA);
    }

    private static int darken(int color, float factor) {
        int r2 = (int)((float)ColorProvider.red(color) * factor);
        int g2 = (int)((float)ColorProvider.green(color) * factor);
        int b2 = (int)((float)ColorProvider.blue(color) * factor);
        int a2 = ColorProvider.alpha(color);
        return ColorProvider.rgba(r2, g2, b2, a2);
    }

    private static float easeOutCubic(float t2) {
        float u2 = 1.0f - t2;
        return 1.0f - u2 * u2 * u2;
    }

    private record CircleData(Vec3d pos, long startTimeMs) {
    }
}

