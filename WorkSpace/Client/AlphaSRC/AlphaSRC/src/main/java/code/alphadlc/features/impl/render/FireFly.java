/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3d
 *  org.joml.Vector3dc
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.math.time.StopWatch;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2902;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class FireFly
extends Module {
    private static final int MAX_SPARKLES_PER_ENTITY = 6;
    private static final int MAX_TRAIL_RENDER_POINTS = 4;
    private static final float MAX_RENDER_DISTANCE_SQ = 5184.0f;
    private static final float TRAIL_RENDER_DISTANCE_SQ = 1600.0f;
    private final List<FireFlyEntity> particles = new ArrayList<FireFlyEntity>();
    private final Random random = new Random();
    private final Vector3d renderScratch = new Vector3d();
    private SliderSettings count = new SliderSettings("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u043e\u0432").setValue(35.0f).range(10, 100);
    private SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f").setValue(0.35f).range(0.01f, 1.0f);
    private SliderSettings radius = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u043f\u0430\u0432\u043d\u0430", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u044f \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u043e\u0432").setValue(40.0f).range(10.0f, 100.0f);
    private SliderSettings floorHeight = new SliderSettings("\u0412\u044b\u0441\u043e\u0442\u0430 \u043e\u0442 \u043f\u043e\u043b\u0430", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0432\u044b\u0441\u043e\u0442\u0430 \u043d\u0430\u0434 \u043f\u043e\u0432\u0435\u0440\u0445\u043d\u043e\u0441\u0442\u044c\u044e \u0437\u0435\u043c\u043b\u0438").setValue(12.0f).range(1.0f, 40.0f);
    private SliderSettings trailLength = new SliderSettings("\u0414\u043b\u0438\u043d\u0430 \u0448\u043b\u0435\u0439\u0444\u0430", "\u0414\u043b\u0438\u043d\u0430 \u0445\u0432\u043e\u0441\u0442\u0430 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u0430").setValue(6.0f).range(2.0f, 15.0f);
    private SliderSettings trailWidth = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u0448\u043b\u0435\u0439\u0444\u0430", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u0445\u0432\u043e\u0441\u0442\u0430 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u0430").setValue(1.5f).range(0.5f, 4.0f).step(0.5f);
    private SliderSettings fireflySize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u043e\u0432").setValue(2.0f).range(0.5f, 5.0f).step(0.1f);
    private SliderSettings gravity = new SliderSettings("\u0413\u0440\u0430\u0432\u0438\u0442\u0430\u0446\u0438\u044f", "\u0421\u0438\u043b\u0430 \u043f\u0440\u0438\u0442\u044f\u0436\u0435\u043d\u0438\u044f \u0432\u043d\u0438\u0437").setValue(0.001f).range(0.0f, 0.01f);
    private SliderSettings depth3D = new SliderSettings("3D \u0433\u043b\u0443\u0431\u0438\u043d\u0430", "\u0421\u0438\u043b\u0430 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u043a \u043a\u0430\u043c\u0435\u0440\u0435").setValue(0.0f).range(0.0f, 2.5f);
    private BooleanSetting randomColor = new BooleanSetting("\u0420\u0430\u043d\u0434\u043e\u043c\u043d\u044b\u0439 \u0446\u0432\u0435\u0442", "\u0421\u043b\u0443\u0447\u0430\u0439\u043d\u044b\u0435 \u0446\u0432\u0435\u0442\u0430 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u043e\u0432");
    private BooleanSetting collision = new BooleanSetting("\u041a\u043e\u043b\u043b\u0438\u0437\u0438\u044f", "\u0421\u0442\u043e\u043b\u043a\u043d\u043e\u0432\u0435\u043d\u0438\u0435 \u0441 \u0431\u043b\u043e\u043a\u0430\u043c\u0438");
    private BooleanSetting trail = new BooleanSetting("\u0428\u043b\u0435\u0439\u0444", "\u0420\u0438\u0441\u043e\u0432\u0430\u0442\u044c \u0445\u0432\u043e\u0441\u0442 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u0430");
    private BooleanSetting smoothTrail = new BooleanSetting("\u041f\u043b\u0430\u0432\u043d\u044b\u0439 \u0448\u043b\u0435\u0439\u0444", "\u0411\u043e\u043b\u0435\u0435 \u043f\u043b\u0430\u0432\u043d\u044b\u0439 \u0438 \u043d\u0435\u0437\u0430\u043c\u0435\u0442\u043d\u044b\u0439 \u0445\u0432\u043e\u0441\u0442").visible(() -> this.trail.isValue());
    private BooleanSetting sparkles = new BooleanSetting("\u0418\u0441\u043a\u0440\u044b", "\u041c\u0430\u043b\u0435\u043d\u044c\u043a\u0438\u0435 \u0447\u0430\u0441\u0442\u0438\u0446\u044b \u043e\u0442 \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u043e\u0432");
    private SliderSettings sparkleAmount = new SliderSettings("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0438\u0441\u043a\u0440", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0438\u0441\u043a\u0440 \u043e\u0442 \u043a\u0430\u0436\u0434\u043e\u0433\u043e \u0441\u0432\u0435\u0442\u043b\u044f\u0447\u043a\u0430").setValue(2.0f).range(1.0f, 6.0f).visible(() -> this.sparkles.isValue());
    private class_2960 texture = class_2960.method_60654((String)"textures/glow.png");

    public FireFly() {
        super("FireFly", "FireFly", ModuleCategory.RENDER);
        this.randomColor.setValue(true);
        this.collision.setValue(false);
        this.trail.setValue(true);
        this.smoothTrail.setValue(true);
        this.sparkles.setValue(true);
        this.setup(this.count, this.speed, this.radius, this.floorHeight, this.trailLength, this.trailWidth, this.fireflySize, this.gravity, this.depth3D, this.randomColor, this.collision, this.trail, this.smoothTrail, this.sparkles, this.sparkleAmount);
    }

    public static FireFly getInstance() {
        return Instance.get(FireFly.class);
    }

    @Override
    public void activate() {
        super.activate();
        this.particles.clear();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.particles.clear();
    }

    private double resolveSurfaceY(double x, double z) {
        class_2338 surface = FireFly.mc.field_1687.method_8598(class_2902.class_2903.field_13197, class_2338.method_49637((double)x, (double)0.0, (double)z));
        return surface.method_10264();
    }

    private double randomHeightAboveSurface(double surfaceY) {
        return surfaceY + 0.2 + this.random.nextDouble() * (double)this.floorHeight.getValue();
    }

    private double getMinPlayerDistance() {
        float spawnRadius = this.radius.getValue();
        return class_3532.method_15363((float)(spawnRadius * 0.3f), (float)4.0f, (float)(spawnRadius * 0.8f));
    }

    private void spawnParticle() {
        if (FireFly.mc.field_1724 == null || FireFly.mc.field_1687 == null) {
            return;
        }
        int spawnRadius = (int)this.radius.getValue();
        double minPlayerDist = this.getMinPlayerDistance();
        double minPlayerDistSq = minPlayerDist * minPlayerDist;
        for (int attempt = 0; attempt < 20; ++attempt) {
            double surfaceY;
            double spawnY;
            double dz;
            double spawnX = FireFly.mc.field_1724.method_23317() + (this.random.nextDouble() * (double)spawnRadius * 2.0 - (double)spawnRadius);
            double spawnZ = FireFly.mc.field_1724.method_23321() + (this.random.nextDouble() * (double)spawnRadius * 2.0 - (double)spawnRadius);
            double dx = spawnX - FireFly.mc.field_1724.method_23317();
            if (dx * dx + (dz = spawnZ - FireFly.mc.field_1724.method_23321()) * dz < minPlayerDistSq || !FireFly.mc.field_1687.method_8320(class_2338.method_49637((double)spawnX, (double)(spawnY = this.randomHeightAboveSurface(surfaceY = this.resolveSurfaceY(spawnX, spawnZ))), (double)spawnZ)).method_26215()) continue;
            double velocitySpeed = (double)this.speed.getValue() * (0.85 + this.random.nextDouble() * 0.3);
            double velocityYaw = Math.toRadians(this.random.nextDouble() * 360.0);
            Vector3d initialVelocity = new Vector3d(-Math.sin(velocityYaw) * velocitySpeed, (this.random.nextDouble() - 0.5) * velocitySpeed * 0.15, Math.cos(velocityYaw) * velocitySpeed);
            this.steerAwayFromPlayer(initialVelocity, spawnX, spawnY, spawnZ, minPlayerDist);
            int color = this.randomColor.isValue() ? this.randomColor() : ColorAssist.getClientColor();
            this.particles.add(new FireFlyEntity(spawnX, spawnY, spawnZ, initialVelocity, color, surfaceY));
            return;
        }
    }

    private void steerAwayFromPlayer(Vector3d velocity, double x, double y, double z, double minDist) {
        double dz;
        double dy;
        double dx = x - FireFly.mc.field_1724.method_23317();
        double distSq = dx * dx + (dy = y - FireFly.mc.field_1724.method_23320()) * dy + (dz = z - FireFly.mc.field_1724.method_23321()) * dz;
        if (distSq < 1.0E-8) {
            return;
        }
        double dist = Math.sqrt(distSq);
        double awayX = dx / dist;
        double awayY = dy / dist;
        double awayZ = dz / dist;
        double dot = velocity.x * awayX + velocity.y * awayY + velocity.z * awayZ;
        if (dist < minDist && dot < 0.0) {
            velocity.x -= awayX * dot;
            velocity.y -= awayY * dot;
            velocity.z -= awayZ * dot;
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.isState() || FireFly.mc.field_1724 == null || FireFly.mc.field_1687 == null) {
            return;
        }
        double maxDistSq = this.radius.getValue() * this.radius.getValue();
        this.particles.removeIf(particle -> particle.isDead() || particle.position.distanceSquared(FireFly.mc.field_1724.method_23317(), FireFly.mc.field_1724.method_23318(), FireFly.mc.field_1724.method_23321()) > maxDistSq);
        int targetCount = (int)this.count.getValue();
        int deficit = Math.min(targetCount - this.particles.size(), 5);
        for (int i = 0; i < deficit; ++i) {
            this.spawnParticle();
        }
        for (FireFlyEntity particle2 : this.particles) {
            particle2.update();
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent event) {
        if (!this.isState() || FireFly.mc.field_1724 == null || FireFly.mc.field_1687 == null || this.particles.isEmpty()) {
            return;
        }
        float partialTicks = event.getPartialTicks();
        class_4184 camera = FireFly.mc.method_1561().field_4686;
        class_243 cameraPos = camera.method_19326();
        float sizeMul = this.fireflySize.getValue();
        class_4587 matrix = event.getStack();
        if (this.trail.isValue()) {
            this.renderBatchedTrails(matrix, cameraPos, partialTicks);
        }
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.setShaderTexture((int)0, (class_2960)this.texture);
        class_4587 billboardStack = new class_4587();
        billboardStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        billboardStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        boolean hasGeometry = false;
        for (FireFlyEntity particle : this.particles) {
            particle.lerpRenderPosition(this.renderScratch, partialTicks);
            if (FireFly.distanceSquared(cameraPos, this.renderScratch) > 5184.0) continue;
            hasGeometry = true;
            int baseAlpha = particle.getAlpha();
            int pulseAlpha = particle.getPulseAlpha(this.renderScratch);
            int finalAlpha = Math.min(baseAlpha, pulseAlpha);
            int color = particle.getColor();
            float glowRadius = 0.34f * sizeMul;
            float coreRadius = 0.18f * sizeMul;
            float brightRadius = 0.08f * sizeMul;
            int glowColor = ColorAssist.multAlpha(color, (float)finalAlpha * 0.45f / 255.0f);
            int mainColor = ColorAssist.multAlpha(color, (float)finalAlpha * 0.9f / 255.0f);
            int brightColor = ColorAssist.getColor(255, 255, 255, finalAlpha);
            this.drawBillboard(buffer, this.renderScratch, glowRadius, glowColor, camera, billboardStack);
            this.drawBillboard(buffer, this.renderScratch, coreRadius, mainColor, camera, billboardStack);
            this.drawBillboard(buffer, this.renderScratch, brightRadius, brightColor, camera, billboardStack);
            if (!this.sparkles.isValue()) continue;
            this.renderSparkles(buffer, particle, camera, billboardStack, sizeMul, partialTicks);
        }
        if (hasGeometry) {
            class_286.method_43433((class_9801)buffer.method_60800());
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
    }

    private void renderBatchedTrails(class_4587 matrix, class_243 cameraPos, float partialTicks) {
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29344, class_290.field_1576);
        Matrix4f matrix4f = matrix.method_23760().method_23761();
        boolean hasLines = false;
        boolean smooth = this.smoothTrail.isValue();
        for (FireFlyEntity particle : this.particles) {
            Vector3d tail;
            int startIndex;
            List<Vector3d> trailPoints;
            int trailCount;
            particle.lerpRenderPosition(this.renderScratch, partialTicks);
            if (FireFly.distanceSquared(cameraPos, this.renderScratch) > 1600.0 || (trailCount = (trailPoints = particle.getTrail()).size()) == 0) continue;
            int baseAlpha = particle.getAlpha();
            int color = particle.getColor();
            for (int i = startIndex = Math.max(0, trailCount - 4); i < trailCount - 1; ++i) {
                Vector3d start = trailPoints.get(i);
                Vector3d end = trailPoints.get(i + 1);
                float fade = (float)(i + 1 - startIndex) / (float)Math.max(1, trailCount - 1 - startIndex);
                float smoothFade = smooth ? fade * fade : fade;
                int trailAlpha = (int)((float)baseAlpha * smoothFade * 0.8f);
                if (trailAlpha <= 8) continue;
                int lineColor = ColorAssist.multAlpha(color, (float)trailAlpha / 255.0f);
                buffer.method_22918(matrix4f, (float)start.x, (float)start.y, (float)start.z).method_39415(lineColor);
                buffer.method_22918(matrix4f, (float)end.x, (float)end.y, (float)end.z).method_39415(lineColor);
                hasLines = true;
            }
            Vector3d vector3d = tail = trailCount > 1 ? trailPoints.get(trailCount - 1) : trailPoints.get(0);
            int headAlpha = (int)((float)baseAlpha * 0.75f);
            if (headAlpha <= 8) continue;
            int headColor = ColorAssist.multAlpha(color, (float)headAlpha / 255.0f);
            buffer.method_22918(matrix4f, (float)tail.x, (float)tail.y, (float)tail.z).method_39415(headColor);
            buffer.method_22918(matrix4f, (float)this.renderScratch.x, (float)this.renderScratch.y, (float)this.renderScratch.z).method_39415(headColor);
            hasLines = true;
        }
        if (!hasLines) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        RenderSystem.lineWidth((float)Math.max(0.5f, this.trailWidth.getValue()));
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    private void renderSparkles(class_287 buffer, FireFlyEntity particle, class_4184 camera, class_4587 baseStack, float sizeMul, float partialTicks) {
        int baseAlpha = particle.getAlpha();
        for (Sparkle sparkle : particle.getSparkles()) {
            int sparkleAlpha;
            sparkle.lerpRenderPosition(this.renderScratch, partialTicks);
            float lifeFade = 1.0f - sparkle.getRenderAge(partialTicks) / sparkle.maxAge;
            if (lifeFade <= 0.01f || (sparkleAlpha = (int)((float)baseAlpha * lifeFade * 0.75f)) <= 0) continue;
            int sparkleColor = ColorAssist.multAlpha(particle.getColor(), (float)sparkleAlpha / 255.0f);
            float sparkleSize = 0.05f * lifeFade * sizeMul;
            this.drawBillboard(buffer, this.renderScratch, sparkleSize, sparkleColor, camera, baseStack);
        }
    }

    private static double distanceSquared(class_243 cameraPos, Vector3d particlePos) {
        double dx = particlePos.x - cameraPos.field_1352;
        double dy = particlePos.y - cameraPos.field_1351;
        double dz = particlePos.z - cameraPos.field_1350;
        return dx * dx + dy * dy + dz * dz;
    }

    private void drawBillboard(class_287 buffer, Vector3d pos, float size, int color, class_4184 camera, class_4587 baseStack) {
        class_243 camPos = camera.method_19326();
        baseStack.method_22903();
        baseStack.method_22904(pos.x - camPos.field_1352, pos.y - camPos.field_1351, pos.z - camPos.field_1350);
        baseStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        baseStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        Matrix4f mat = baseStack.method_23760().method_23761();
        buffer.method_22918(mat, -size, -size, 0.0f).method_22913(0.0f, 0.0f).method_39415(color);
        buffer.method_22918(mat, -size, size, 0.0f).method_22913(0.0f, 1.0f).method_39415(color);
        buffer.method_22918(mat, size, size, 0.0f).method_22913(1.0f, 1.0f).method_39415(color);
        buffer.method_22918(mat, size, -size, 0.0f).method_22913(1.0f, 0.0f).method_39415(color);
        baseStack.method_22909();
    }

    private int randomColor() {
        float hue = this.random.nextFloat() * 0.2f;
        float saturation = 0.7f + this.random.nextFloat() * 0.3f;
        float brightness = 0.9f + this.random.nextFloat() * 0.1f;
        int rgb = Color.HSBtoRGB(hue, saturation, brightness);
        return 0xFF000000 | rgb & 0xFFFFFF;
    }

    private class FireFlyEntity {
        private final StopWatch timer = new StopWatch();
        private final int color;
        private final Vector3d position;
        private final Vector3d prevPosition;
        private final Vector3d velocity;
        private final List<Vector3d> trail = new ArrayList<Vector3d>();
        private final List<Sparkle> sparkles = new ArrayList<Sparkle>();
        private class_238 box;
        private double surfaceY;
        private int surfaceColumnX = Integer.MIN_VALUE;
        private int surfaceColumnZ = Integer.MIN_VALUE;
        private float alpha;
        private boolean dead;
        private double targetY;
        private long directionChangeTime;
        private int sparkleSpawnCounter;
        private int trailCooldown;

        FireFlyEntity(double x, double y, double z, Vector3d velocity, int color, double surfaceY) {
            this.position = new Vector3d(x, y, z);
            this.prevPosition = new Vector3d(x, y, z);
            this.velocity = new Vector3d((Vector3dc)velocity);
            this.color = color;
            this.surfaceY = surfaceY;
            this.surfaceColumnX = class_3532.method_15357((double)x);
            this.surfaceColumnZ = class_3532.method_15357((double)z);
            this.targetY = this.randomHeightInBand(surfaceY);
            this.trail.add(new Vector3d(x, y, z));
            this.box = new class_238(x, y, z, x, y, z).method_1014(0.35);
        }

        private double randomHeightInBand(double surface) {
            return surface + 0.2 + FireFly.this.random.nextDouble() * (double)FireFly.this.floorHeight.getValue();
        }

        private void refreshSurfaceIfNeeded() {
            int colX = class_3532.method_15357((double)this.position.x);
            int colZ = class_3532.method_15357((double)this.position.z);
            if (colX == this.surfaceColumnX && colZ == this.surfaceColumnZ) {
                return;
            }
            this.surfaceColumnX = colX;
            this.surfaceColumnZ = colZ;
            this.surfaceY = FireFly.this.resolveSurfaceY(this.position.x, this.position.z);
        }

        void lerpRenderPosition(Vector3d out, float partialTicks) {
            out.x = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.x, (double)this.position.x);
            out.y = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.y, (double)this.position.y);
            out.z = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.z, (double)this.position.z);
        }

        int getPulseAlpha(Vector3d renderPos) {
            double pulse = (Math.sin((double)this.timer.elapsedTime() / 400.0 + renderPos.x) + 1.0) / 2.0;
            return (int)((pulse * 0.5 + 0.5) * 255.0);
        }

        void update() {
            if (QuickImports.mc.field_1687 == null || QuickImports.mc.field_1724 == null) {
                return;
            }
            this.prevPosition.set((Vector3dc)this.position);
            this.refreshSurfaceIfNeeded();
            double minY = this.surfaceY + 0.15;
            double maxY = this.surfaceY + (double)FireFly.this.floorHeight.getValue();
            if (System.currentTimeMillis() - this.directionChangeTime > (long)(1200 + FireFly.this.random.nextInt(1800))) {
                this.directionChangeTime = System.currentTimeMillis();
                double newYaw = Math.toRadians(FireFly.this.random.nextDouble() * 360.0);
                double newSpeed = (double)FireFly.this.speed.getValue() * (0.75 + FireFly.this.random.nextDouble() * 0.5);
                if (FireFly.this.depth3D.getValue() > 0.0f) {
                    class_243 eyePos = QuickImports.mc.field_1724.method_33571();
                    double toCameraX = eyePos.field_1352 - this.position.x;
                    double toCameraZ = eyePos.field_1350 - this.position.z;
                    double toCameraDist = Math.sqrt(toCameraX * toCameraX + toCameraZ * toCameraZ);
                    if (toCameraDist > 0.1) {
                        double pull = (double)FireFly.this.depth3D.getValue() * 0.25;
                        this.velocity.x = -Math.sin(newYaw) * newSpeed + toCameraX / toCameraDist * pull;
                        this.velocity.z = Math.cos(newYaw) * newSpeed + toCameraZ / toCameraDist * pull;
                    } else {
                        this.velocity.x = -Math.sin(newYaw) * newSpeed;
                        this.velocity.z = Math.cos(newYaw) * newSpeed;
                    }
                } else {
                    this.velocity.x = -Math.sin(newYaw) * newSpeed;
                    this.velocity.z = Math.cos(newYaw) * newSpeed;
                }
                this.targetY = this.randomHeightInBand(this.surfaceY);
            }
            this.velocity.y += (this.targetY - this.position.y) * 0.018;
            this.velocity.y -= (double)FireFly.this.gravity.getValue();
            this.velocity.x += (FireFly.this.random.nextDouble() - 0.5) * 0.008;
            this.velocity.y += (FireFly.this.random.nextDouble() - 0.5) * 0.005;
            this.velocity.z += (FireFly.this.random.nextDouble() - 0.5) * 0.008;
            FireFly.this.steerAwayFromPlayer(this.velocity, this.position.x, this.position.y, this.position.z, FireFly.this.getMinPlayerDistance());
            double maxSpeed = (double)FireFly.this.speed.getValue() * 1.15;
            this.velocity.x = class_3532.method_15350((double)this.velocity.x, (double)(-maxSpeed), (double)maxSpeed);
            this.velocity.y = class_3532.method_15350((double)this.velocity.y, (double)(-maxSpeed * 0.45), (double)(maxSpeed * 0.45));
            this.velocity.z = class_3532.method_15350((double)this.velocity.z, (double)(-maxSpeed), (double)maxSpeed);
            this.velocity.x *= 0.988;
            this.velocity.y *= 0.982;
            this.velocity.z *= 0.988;
            if (FireFly.this.collision.isValue()) {
                this.moveWithCollision();
            } else {
                this.position.x += this.velocity.x;
                this.position.y += this.velocity.y;
                this.position.z += this.velocity.z;
            }
            if (this.position.y < minY) {
                this.position.y = minY;
                this.velocity.y = Math.abs(this.velocity.y) * 0.35;
            } else if (this.position.y > maxY) {
                this.position.y = maxY;
                this.velocity.y = -Math.abs(this.velocity.y) * 0.35;
            }
            this.targetY = class_3532.method_15350((double)this.targetY, (double)minY, (double)maxY);
            int maxTrailLen = (int)FireFly.this.trailLength.getValue();
            if (++this.trailCooldown >= 2) {
                this.trailCooldown = 0;
                if (this.trail.size() >= maxTrailLen) {
                    Vector3d reused = this.trail.remove(0);
                    reused.set((Vector3dc)this.position);
                    this.trail.add(reused);
                } else {
                    this.trail.add(new Vector3d((Vector3dc)this.position));
                }
            }
            if (FireFly.this.sparkles.isValue()) {
                this.sparkles.removeIf(sparkle -> {
                    sparkle.update();
                    return sparkle.age >= sparkle.maxAge;
                });
                ++this.sparkleSpawnCounter;
                if (this.sparkleSpawnCounter >= 4 && this.sparkles.size() < 6) {
                    this.sparkleSpawnCounter = 0;
                    int sparkleCount = Math.min((int)FireFly.this.sparkleAmount.getValue(), 6 - this.sparkles.size());
                    for (int i = 0; i < sparkleCount; ++i) {
                        this.spawnSparkle();
                    }
                }
            }
            this.alpha = this.timer.elapsedTime() < 800L ? Math.min(255.0f, this.alpha + 8.0f) : Math.min(255.0f, this.alpha + 2.0f);
            this.box = new class_238(this.position.x, this.position.y, this.position.z, this.position.x, this.position.y, this.position.z).method_1014(0.35);
        }

        private void spawnSparkle() {
            Vector3d sparklePos = new Vector3d(this.position.x + (FireFly.this.random.nextDouble() - 0.5) * 0.12, this.position.y + (FireFly.this.random.nextDouble() - 0.5) * 0.12, this.position.z + (FireFly.this.random.nextDouble() - 0.5) * 0.12);
            Vector3d sparkleVel = new Vector3d((FireFly.this.random.nextDouble() - 0.5) * 0.018, (FireFly.this.random.nextDouble() - 0.5) * 0.018, (FireFly.this.random.nextDouble() - 0.5) * 0.018);
            this.sparkles.add(new Sparkle(FireFly.this, sparklePos, sparkleVel));
        }

        private void moveWithCollision() {
            double nextX = this.position.x + this.velocity.x;
            double nextY = this.position.y + this.velocity.y;
            double nextZ = this.position.z + this.velocity.z;
            class_2338 nextPos = class_2338.method_49637((double)nextX, (double)nextY, (double)nextZ);
            if (QuickImports.mc.field_1687.method_8320(nextPos).method_26215()) {
                this.position.x = nextX;
                this.position.y = nextY;
                this.position.z = nextZ;
                return;
            }
            class_2338 xPos = class_2338.method_49637((double)nextX, (double)this.position.y, (double)this.position.z);
            class_2338 yPos = class_2338.method_49637((double)this.position.x, (double)nextY, (double)this.position.z);
            class_2338 zPos = class_2338.method_49637((double)this.position.x, (double)this.position.y, (double)nextZ);
            if (QuickImports.mc.field_1687.method_8320(xPos).method_26215()) {
                this.position.x = nextX;
            } else {
                this.velocity.x *= -0.55;
            }
            if (QuickImports.mc.field_1687.method_8320(yPos).method_26215()) {
                this.position.y = nextY;
            } else {
                this.velocity.y *= -0.4;
            }
            if (QuickImports.mc.field_1687.method_8320(zPos).method_26215()) {
                this.position.z = nextZ;
            } else {
                this.velocity.z *= -0.55;
            }
        }

        int getAlpha() {
            return (int)this.alpha;
        }

        public StopWatch getTimer() {
            return this.timer;
        }

        public int getColor() {
            return this.color;
        }

        public Vector3d getPosition() {
            return this.position;
        }

        public Vector3d getPrevPosition() {
            return this.prevPosition;
        }

        public Vector3d getVelocity() {
            return this.velocity;
        }

        public List<Vector3d> getTrail() {
            return this.trail;
        }

        public List<Sparkle> getSparkles() {
            return this.sparkles;
        }

        public class_238 getBox() {
            return this.box;
        }

        public double getSurfaceY() {
            return this.surfaceY;
        }

        public int getSurfaceColumnX() {
            return this.surfaceColumnX;
        }

        public int getSurfaceColumnZ() {
            return this.surfaceColumnZ;
        }

        public boolean isDead() {
            return this.dead;
        }

        public double getTargetY() {
            return this.targetY;
        }

        public long getDirectionChangeTime() {
            return this.directionChangeTime;
        }

        public int getSparkleSpawnCounter() {
            return this.sparkleSpawnCounter;
        }

        public int getTrailCooldown() {
            return this.trailCooldown;
        }
    }

    private class Sparkle {
        final Vector3d position;
        final Vector3d prevPosition;
        final Vector3d velocity;
        final float maxAge;
        float age;

        Sparkle(FireFly fireFly, Vector3d position, Vector3d velocity) {
            this.position = new Vector3d((Vector3dc)position);
            this.prevPosition = new Vector3d((Vector3dc)position);
            this.velocity = velocity;
            this.maxAge = 16.0f + fireFly.random.nextFloat() * 12.0f;
        }

        void update() {
            this.prevPosition.set((Vector3dc)this.position);
            this.position.x += this.velocity.x;
            this.position.y += this.velocity.y;
            this.position.z += this.velocity.z;
            this.velocity.x *= 0.95;
            this.velocity.y *= 0.95;
            this.velocity.z *= 0.95;
            this.velocity.y -= 0.001;
            this.age += 1.0f;
        }

        void lerpRenderPosition(Vector3d out, float partialTicks) {
            out.x = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.x, (double)this.position.x);
            out.y = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.y, (double)this.position.y);
            out.z = class_3532.method_16436((double)partialTicks, (double)this.prevPosition.z, (double)this.position.z);
        }

        float getRenderAge(float partialTicks) {
            return this.age + partialTicks;
        }
    }
}
