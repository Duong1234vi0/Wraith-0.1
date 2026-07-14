package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.model.CosmeticsKatanaRenderer;

@ModuleInformation(moduleName="Косметика", moduleDesc="Косметические эффекты на игроках", moduleCategory=ModuleCategory.RENDER)
public class Cosmetics
extends Module {
    private static Cosmetics instance;
    public final ModeSetting type = new ModeSetting("Тип", "Wings", "Wings", "Katana", "Demon");
    public final BooleanSetting self = new BooleanSetting("На себя", true);
    public final BooleanSetting players = new BooleanSetting("На игроков", false);
    public final SliderSetting size = new SliderSetting("Размер", 1.0, 0.5, 1.5, 0.05).setVisible(() -> this.type.is("Wings") || this.type.is("Demon"));
    public final BooleanSetting glow = new BooleanSetting("Подсветка", true).setVisible(() -> this.type.is("Katana") || this.type.is("Demon"));
    public final SliderSetting glowLevel = new SliderSetting("Уровень подсветки", 50.0, 0.0, 100.0, 1.0).setVisible(() -> (this.type.is("Katana") || this.type.is("Demon")) && this.glow.getValue());
    public final SliderSetting fillAlpha = new SliderSetting("Прозрачность заливки", 20.0, 0.0, 100.0, 1.0).setVisible(() -> this.type.is("Katana"));
    public final SliderSetting outlineAlpha = new SliderSetting("Прозрачность обводки", 85.0, 0.0, 100.0, 1.0).setVisible(() -> this.type.is("Katana"));
    private float selfBodyYaw;
    private boolean selfBodyYawInitialized;
    private static final float DEFAULT_SPREAD = 8.0f;
    private static final int DEFAULT_ALPHA = 220;
    private static final WingPoint[] SHAPE;

    public Cosmetics() {
        instance = this;
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                registrationHelper.register((FeatureRenderer)new CosmeticsKatanaRenderer((FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel>)entityRenderer));
            }
        });
    }

    public static Cosmetics getInstance() {
        return instance;
    }

    @Subscribe
    public void onRender3D(EventWorldRender event) {
        if (this.mc.player == null || this.mc.world == null || this.mc.gameRenderer == null) {
            return;
        }
        if (!this.type.is("Wings") && !this.type.is("Demon")) {
            return;
        }
        MatrixStack stack = event.getMatrixStack();
        float tickDelta = event.getTickDelta();
        Vec3d camera = this.mc.gameRenderer.getCamera().getPos();
        if (this.self.getValue() && !this.mc.options.getPerspective().isFirstPerson() && this.mc.player.isAlive()) {
            try {
                this.renderPlayer(stack, (PlayerEntity)this.mc.player, tickDelta, camera);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (this.players.getValue()) {
            for (Entity entity : this.mc.world.getEntities()) {
                PlayerEntity player;
                if (!(entity instanceof PlayerEntity) || (player = (PlayerEntity)entity) == this.mc.player || !player.isAlive()) continue;
                try {
                    this.renderPlayer(stack, player, tickDelta, camera);
                }
                catch (Exception exception) {}
            }
        }
    }

    private void renderPlayer(MatrixStack stack, PlayerEntity player, float tickDelta, Vec3d camera) {
        if (this.type.is("Wings") && !this.hasElytra(player)) {
            this.renderWings(stack, player, tickDelta, camera);
        } else if (this.type.is("Demon")) {
            this.renderDemon(stack, player, tickDelta, camera);
        }
    }

    private boolean hasElytra(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA);
    }

    private void renderWings(MatrixStack stack, PlayerEntity player, float tickDelta, Vec3d camera) {
        double x2 = MathHelper.lerp((double)tickDelta, (double)player.prevX, (double)player.getX()) - camera.x;
        double y2 = MathHelper.lerp((double)tickDelta, (double)player.prevY, (double)player.getY()) - camera.y;
        double z2 = MathHelper.lerp((double)tickDelta, (double)player.prevZ, (double)player.getZ()) - camera.z;
        float bodyYaw = this.resolveBodyYaw(player, tickDelta);
        float move = MathHelper.clamp((float)player.limbAnimator.getSpeed(tickDelta), (float)0.0f, (float)1.0f);
        WingPose pose = this.resolvePose(player, tickDelta);
        if (pose == null) {
            return;
        }
        float flapOpenOffset = this.computeFlapOffset(player, tickDelta);
        float flap = (float)Math.sin(((float)player.age + tickDelta) * pose.flapSpeed) * pose.flapAmplitude;
        float open = (8.0f + flap + flapOpenOffset + move * pose.motionSpreadBoost) * pose.openMultiplier;
        float wingScale = this.size.getFloatValue() * pose.scaleMultiplier;
        int baseColor = ColorProvider.getColorClient();
        int glowColor = ColorProvider.interpolateColor(baseColor, Cosmetics.getColor(255, 255, 255, 255), 0.28f);
        int coreColor = ColorProvider.interpolateColor(baseColor, Cosmetics.getColor(255, 255, 255, 255), 0.55f);
        stack.push();
        stack.translate(x2, y2, z2);
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - bodyYaw));
        if (pose.preTranslateY != 0.0f || pose.preTranslateZ != 0.0f) {
            stack.translate(0.0f, pose.preTranslateY, pose.preTranslateZ);
        }
        if (pose.pitchRotation != 0.0f) {
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pose.pitchRotation));
        }
        if (pose.rollRotation != 0.0f) {
            stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(pose.rollRotation));
        }
        stack.translate(0.0f, pose.anchorY, pose.anchorZ);
        stack.scale(wingScale, wingScale, wingScale);
        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        this.renderWingSide(stack, -1.0f, open, baseColor, glowColor, coreColor, pose);
        this.renderWingSide(stack, 1.0f, open, baseColor, glowColor, coreColor, pose);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        stack.pop();
    }

    private float computeFlapOffset(PlayerEntity player, float tickDelta) {
        float t2 = ((float)player.age + tickDelta) * 0.22f;
        return (float)(Math.sin(t2) * 7.5 + Math.sin(t2 * 2.35f) * (double)3.8f);
    }

    private void renderWingSide(MatrixStack stack, float side, float open, int baseColor, int glowColor, int coreColor, WingPose pose) {
        stack.push();
        stack.translate(side * pose.sideOffset, pose.sideYOffset, pose.sideZOffset);
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(side * open));
        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(side * pose.sideRoll));
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pose.sidePitch));
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        this.drawWingLayer(stack, side, 1.22f, Cosmetics.setAlpha(glowColor, 48), Cosmetics.setAlpha(glowColor, 0));
        this.drawWingLayer(stack, side, 0.84f, Cosmetics.setAlpha(coreColor, 57), Cosmetics.setAlpha(coreColor, 0));
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        this.drawWingLayer(stack, side, 1.0f, Cosmetics.setAlpha(baseColor, 220), Cosmetics.setAlpha(baseColor, 10));
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        this.drawWingOutline(stack, side, 1.0f, Cosmetics.setAlpha(baseColor, 136));
        this.drawWingRibs(stack, side, 0.96f, Cosmetics.setAlpha(glowColor, 44));
        stack.pop();
    }

    private void drawWingLayer(MatrixStack stack, float side, float scale, int rootColor, int edgeColor) {
        Matrix4f matrix = stack.peek().getPositionMatrix();
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        for (int i2 = 0; i2 < SHAPE.length; ++i2) {
            WingPoint cur = SHAPE[i2];
            WingPoint next = SHAPE[(i2 + 1) % SHAPE.length];
            this.vertex(buffer, matrix, 0.0f, 0.0f, 0.0f, rootColor);
            this.vertex(buffer, matrix, side * cur.x * scale, cur.y * scale, 0.0f, this.applyPointAlpha(edgeColor, cur.alphaMul));
            this.vertex(buffer, matrix, side * next.x * scale, next.y * scale, 0.0f, this.applyPointAlpha(edgeColor, next.alphaMul));
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
    }

    private void drawWingOutline(MatrixStack stack, float side, float scale, int color) {
        Matrix4f matrix = stack.peek().getPositionMatrix();
        RenderSystem.lineWidth((float)1.35f);
        GL11.glEnable((int)2848);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
        for (WingPoint p2 : SHAPE) {
            this.vertex(buffer, matrix, side * p2.x * scale, p2.y * scale, 0.0f, color);
        }
        this.vertex(buffer, matrix, side * Cosmetics.SHAPE[0].x * scale, Cosmetics.SHAPE[0].y * scale, 0.0f, color);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        GL11.glDisable((int)2848);
    }

    private void drawWingRibs(MatrixStack stack, float side, float scale, int color) {
        int[] ribIndices = new int[]{2, 4, 7, 9, 11};
        Matrix4f matrix = stack.peek().getPositionMatrix();
        RenderSystem.lineWidth((float)0.9f);
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);
        for (int idx : ribIndices) {
            WingPoint p2 = SHAPE[idx];
            this.vertex(buffer, matrix, 0.0f, 0.0f, 0.0f, Cosmetics.setAlpha(color, Math.max(8, (int)((float)Cosmetics.alpha(color) * 0.75f))));
            this.vertex(buffer, matrix, side * p2.x * scale, p2.y * scale, 0.0f, this.applyPointAlpha(color, p2.alphaMul));
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
    }

    private void renderDemon(MatrixStack stack, PlayerEntity player, float tickDelta, Vec3d camera) {
        double x2 = MathHelper.lerp((double)tickDelta, (double)player.prevX, (double)player.getX()) - camera.x;
        double y2 = MathHelper.lerp((double)tickDelta, (double)player.prevY, (double)player.getY()) - camera.y;
        double z2 = MathHelper.lerp((double)tickDelta, (double)player.prevZ, (double)player.getZ()) - camera.z;
        int color = ColorProvider.getColorClient();
        float s2 = this.size.getFloatValue();
        float age = ((float)player.age + tickDelta) * 0.05f;
        stack.push();
        stack.translate(x2, y2 + (double)(0.2f * s2), z2);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        Matrix4f mat = stack.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        float floorR = 0.8f * s2;
        int segments = 48;
        BufferBuilder ring = tess.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        for (int i2 = 0; i2 <= segments; ++i2) {
            float angle = (float)((double)i2 * Math.PI * 2.0 / (double)segments) + age;
            float cos = (float)Math.cos(angle) * floorR;
            float sin = (float)Math.sin(angle) * floorR;
            float aFade = 0.25f + 0.15f * (float)Math.sin(angle * 3.0f + age * 2.0f);
            ring.vertex(mat, cos, 0.0f, sin).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, 0.05f);
            ring.vertex(mat, cos * 0.15f, 0.06f * s2, sin * 0.15f).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, aFade);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)ring.end());
        int pillars = 6;
        float pillarH = 1.6f * s2;
        float orbR = 0.06f * s2;
        for (int p2 = 0; p2 < pillars; ++p2) {
            float baseAngle = (float)((double)p2 * Math.PI * 2.0 / (double)pillars) + age;
            float px = (float)Math.cos(baseAngle) * floorR;
            float pz = (float)Math.sin(baseAngle) * floorR;
            float osc = 0.5f + 0.5f * (float)Math.sin(age * 3.0f + (float)p2);
            BufferBuilder pillar = tess.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
            for (int i3 = 0; i3 <= 12; ++i3) {
                float t2 = (float)i3 / 12.0f;
                float py = t2 * pillarH * osc;
                float thin = 1.0f - t2 * 0.7f;
                float aP = (1.0f - t2) * 0.4f;
                pillar.vertex(mat, px - orbR * thin, py, pz - orbR * thin).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, aP * 0.6f);
                pillar.vertex(mat, px + orbR * thin, py, pz + orbR * thin).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, aP);
            }
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)pillar.end());
        }
        float topR = 0.35f * s2;
        float topY = 1.4f * s2;
        BufferBuilder topRing = tess.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
        for (int i4 = 0; i4 <= segments; ++i4) {
            float angle = (float)((double)i4 * Math.PI * 2.0 / (double)segments) - age * 0.7f;
            float cos = (float)Math.cos(angle) * topR;
            float sin = (float)Math.sin(angle) * topR;
            float aFade = 0.2f + 0.2f * (float)Math.sin(angle * 4.0f - age);
            topRing.vertex(mat, cos, topY, sin).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, 0.05f);
            topRing.vertex(mat, cos * 0.2f, topY + 0.04f * s2, sin * 0.2f).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, aFade);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)topRing.end());
        if (this.glow.getValue()) {
            RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
            float pulse = (float)(Math.sin((double)System.currentTimeMillis() / 500.0) * 0.15 + 0.85);
            float glowStr = (float)(this.glowLevel.getValue() / 100.0 * (double)0.35f * (double)pulse);
            BufferBuilder glowRing = tess.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
            float glowR = floorR * 1.4f;
            for (int i5 = 0; i5 <= segments; ++i5) {
                float angle = (float)((double)i5 * Math.PI * 2.0 / (double)segments) + age * 0.5f;
                float cos = (float)Math.cos(angle) * glowR;
                float sin = (float)Math.sin(angle) * glowR;
                glowRing.vertex(mat, cos, -0.02f, sin).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, glowStr * 0.3f);
                glowRing.vertex(mat, cos * 0.9f, 0.02f, sin * 0.9f).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, glowStr);
            }
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)glowRing.end());
            RenderSystem.defaultBlendFunc();
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        stack.pop();
    }

    private int applyPointAlpha(int color, float mul) {
        return Cosmetics.setAlpha(color, Math.max(0, Math.min(255, (int)((float)Cosmetics.alpha(color) * mul))));
    }

    private static int setAlpha(int color, int a2) {
        return MathHelper.clamp((int)a2, (int)0, (int)255) << 24 | color & 0xFFFFFF;
    }

    private static int alpha(int color) {
        return color >> 24 & 0xFF;
    }

    private static int red(int c2) {
        return c2 >> 16 & 0xFF;
    }

    private static int green(int c2) {
        return c2 >> 8 & 0xFF;
    }

    private static int blue(int c2) {
        return c2 & 0xFF;
    }

    private static int getColor(int r2, int g2, int b2, int a2) {
        return a2 << 24 | r2 << 16 | g2 << 8 | b2;
    }

    private void vertex(BufferBuilder buffer, Matrix4f matrix, float x2, float y2, float z2, int color) {
        buffer.vertex(matrix, x2, y2, z2).color((float)Cosmetics.red(color) / 255.0f, (float)Cosmetics.green(color) / 255.0f, (float)Cosmetics.blue(color) / 255.0f, (float)Cosmetics.alpha(color) / 255.0f);
    }

    private float resolveBodyYaw(PlayerEntity player, float tickDelta) {
        float target = MathHelper.lerpAngleDegrees((float)tickDelta, (float)player.prevBodyYaw, (float)player.bodyYaw);
        if (player != this.mc.player) {
            return target;
        }
        if (!this.selfBodyYawInitialized || player.age < 2) {
            this.selfBodyYaw = target;
            this.selfBodyYawInitialized = true;
            return this.selfBodyYaw;
        }
        this.selfBodyYaw = Cosmetics.approachDegrees(this.selfBodyYaw, target, 14.0f);
        return this.selfBodyYaw;
    }

    private static float approachDegrees(float cur, float tgt, float max) {
        float d2 = MathHelper.wrapDegrees((float)(tgt - cur));
        return cur + MathHelper.clamp((float)d2, (float)(-max), (float)max);
    }

    private WingPose resolvePose(PlayerEntity player, float tickDelta) {
        if (player.isGliding()) {
            float ft = (float)player.getGlidingTicks() + tickDelta;
            float fp = MathHelper.clamp((float)(ft * ft / 100.0f), (float)0.0f, (float)1.0f);
            float pr = fp * (-90.0f - MathHelper.lerp((float)tickDelta, (float)player.prevPitch, (float)player.getPitch()));
            return new WingPose(0.34f, 0.46f, 0.0f, 0.0f, pr, 0.0f, 0.76f, 0.92f, 0.1f, 0.58f, 0.05f, 0.06f, -5.0f, -2.0f, 0.13f);
        }
        if (player.isTouchingWater()) {
            return null;
        }
        if (player.isSneaking()) {
            return new WingPose(0.24f, 0.1f, 1.2f, 0.1f, 22.0f, 0.0f, 0.82f, 1.0f, 0.14f, 4.5f, 0.06f, 0.02f, -8.0f, -4.0f, 0.11f);
        }
        return new WingPose(0.0f, 0.0f, 1.38f, 0.1f, 0.0f, 0.0f, 1.0f, 1.0f, 0.15f, 7.5f, 0.06f, 0.02f, -11.0f, -4.0f, 0.145f);
    }

    @Override
    public void onEnable() {
        this.selfBodyYawInitialized = false;
        super.onEnable();
    }

    static {
        SHAPE = new WingPoint[]{new WingPoint(0.08f, 0.1f, 0.88f), new WingPoint(0.28f, 0.34f, 0.78f), new WingPoint(0.56f, 0.82f, 0.62f), new WingPoint(0.86f, 0.3f, 0.52f), new WingPoint(1.14f, 0.46f, 0.4f), new WingPoint(1.24f, 0.04f, 0.3f), new WingPoint(1.02f, -0.18f, 0.28f), new WingPoint(1.18f, -0.64f, 0.22f), new WingPoint(0.86f, -0.46f, 0.2f), new WingPoint(0.8f, -0.98f, 0.14f), new WingPoint(0.54f, -0.74f, 0.16f), new WingPoint(0.3f, -1.16f, 0.12f), new WingPoint(0.1f, -0.54f, 0.18f)};
    }

    private record WingPose(float preTranslateY, float preTranslateZ, float anchorY, float anchorZ, float pitchRotation, float rollRotation, float openMultiplier, float scaleMultiplier, float motionSpreadBoost, float flapAmplitude, float sideOffset, float sideYOffset, float sideZOffset, float sideRoll, float sidePitch, float flapSpeed) {
        WingPose(float preTranslateY, float preTranslateZ, float anchorY, float anchorZ, float pitchRotation, float rollRotation, float openMultiplier, float scaleMultiplier, float motionSpreadBoost, float flapAmplitude, float sideOffset, float sideZOffset, float sideRoll, float sidePitch, float flapSpeed) {
            this(preTranslateY, preTranslateZ, anchorY, anchorZ, pitchRotation, rollRotation, openMultiplier, scaleMultiplier, motionSpreadBoost, flapAmplitude, sideOffset, 0.0f, sideZOffset, sideRoll, sidePitch, flapSpeed);
        }
    }

    private record WingPoint(float x, float y, float alphaMul) {
    }
}

