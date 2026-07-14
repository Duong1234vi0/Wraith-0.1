package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import ua.babka.event.EventAttack;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;

@ModuleInformation(moduleName="KillEffect", moduleDesc="после смерти игрок распадаеться на части", moduleCategory=ModuleCategory.RENDER)
public class KillEffect
extends Module {
    private static final Identifier GLOW = Identifier.of((String)"mre", (String)"images/glow.png");
    private static final float DURATION = 3.0f;
    private static final float U = 0.0625f;
    private final ModeListSetting mode = new ModeListSetting("Режим", new BooleanSetting("Glow тело", true), new BooleanSetting("Частицы", false));
    private final List<GhostBody> bodies = new ArrayList<GhostBody>();
    private final List<ParticleBody> particleBodies = new ArrayList<ParticleBody>();
    private final Random rnd = new Random();
    private PlayerEntity target;

    @Subscribe
    private void onAttack(EventAttack e2) {
        Entity entity = e2.getEntity();
        if (entity instanceof PlayerEntity) {
            PlayerEntity p2;
            this.target = p2 = (PlayerEntity)entity;
        }
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        Entity entity;
        EntityStatusS2CPacket packet;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket && (packet = (EntityStatusS2CPacket)packet2).getStatus() == 3 && (entity = packet.getEntity((World)this.mc.world)) instanceof PlayerEntity && entity != this.mc.player) {
            this.spawn((PlayerEntity)entity);
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null || this.target == null) {
            return;
        }
        if (this.target == this.mc.player) {
            this.target = null;
            return;
        }
        if (this.target.getHealth() <= 0.0f || !this.target.isAlive() || this.target.isRemoved()) {
            this.spawn(this.target);
            this.target = null;
        }
    }

    private void spawn(PlayerEntity player) {
        if (this.mode.isEnabled("Частицы")) {
            this.spawnParticleBody(player);
        } else {
            Vec3d pos = player.getPos();
            this.bodies.add(new GhostBody(pos, player.getBodyYaw(), player.isSneaking(), player.age));
        }
    }

    private void spawnParticleBody(PlayerEntity player) {
        Vec3d pos = player.getPos();
        float yaw = player.getBodyYaw();
        boolean sneak = player.isSneaking();
        float phase = player.age;
        ArrayList<ParticlePoint> points = new ArrayList<ParticlePoint>();
        this.fillBoxBody(points, -0.25f, 0.0f, -0.125f, 0.5f, 0.75f, 0.25f, 120);
        this.fillBoxBody(points, -0.25f, -0.5f, -0.25f, 0.5f, 0.5f, 0.5f, 80);
        this.fillBoxBody(points, -0.5f, -0.125f, -0.125f, 0.25f, 0.75f, 0.25f, 50);
        this.fillBoxBody(points, 0.25f, -0.125f, -0.125f, 0.25f, 0.75f, 0.25f, 50);
        this.fillBoxBody(points, -0.25f, 0.75f, -0.125f, 0.25f, 0.75f, 0.25f, 40);
        this.fillBoxBody(points, 0.0f, 0.75f, -0.125f, 0.25f, 0.75f, 0.25f, 40);
        this.particleBodies.add(new ParticleBody(pos, yaw, sneak, phase, points));
    }

    private void fillBoxBody(List<ParticlePoint> out, float x2, float y2, float z2, float sx, float sy, float sz, int count) {
        for (int i2 = 0; i2 < count; ++i2) {
            float lx = x2 + this.rnd.nextFloat() * sx;
            float ly = y2 + this.rnd.nextFloat() * sy;
            float lz = z2 + this.rnd.nextFloat() * sz;
            float hor = (float)((this.rnd.nextDouble() - 0.5) * 0.4);
            float ver = (float)(-0.5 - this.rnd.nextDouble() * 0.5);
            Vec3d dir = new Vec3d((double)hor, (double)ver, (this.rnd.nextDouble() - 0.5) * 0.4).normalize();
            out.add(new ParticlePoint(new Vec3d((double)lx, (double)ly, (double)lz), dir, this.rnd.nextFloat() * 0.04f + 0.07f));
        }
    }

    @Subscribe
    public void onRender(EventWorldRender e2) {
        float alpha;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.bodies.removeIf(b2 -> (float)(now - b2.birth) >= 3000.0f);
        this.particleBodies.removeIf(b2 -> (float)(now - b2.birth) >= 3000.0f);
        if (this.bodies.isEmpty() && this.particleBodies.isEmpty()) {
            return;
        }
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack m2 = e2.getMatrixStack();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((int)770, (int)1);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW);
        for (GhostBody ghostBody : this.bodies) {
            float t2 = Math.min(1.0f, (float)(now - ghostBody.birth) / 3000.0f);
            alpha = (1.0f - t2) * 180.0f;
            m2.push();
            m2.translate(ghostBody.pos.x - cam.x, ghostBody.pos.y - cam.y, ghostBody.pos.z - cam.z);
            m2.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - ghostBody.yaw));
            m2.scale(-1.0f, -1.0f, 1.0f);
            m2.translate(0.0, -1.5, 0.0);
            if (ghostBody.sneak) {
                m2.translate(0.0, 0.2, 0.0);
                m2.multiply(RotationAxis.POSITIVE_X.rotationDegrees(28.0f));
            }
            float swing = MathHelper.sin((float)(ghostBody.phase * 0.6662f)) * 0.6f;
            float spread = t2 * 2.5f;
            this.drawGlowBox(m2, -0.25f + spread * 0.0625f, 0.0f, -0.125f, 0.5f, 0.75f, 0.25f, 255, 255, 255, alpha);
            this.drawGlowBox(m2, -0.25f, -0.5f + spread * 0.0625f * 0.7f, -0.25f, 0.5f, 0.5f, 0.5f, 255, 255, 255, alpha);
            m2.push();
            m2.translate(-0.375f, 0.125f, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(-swing));
            m2.translate(0.375f, -0.125f, 0.0f);
            this.drawGlowBox(m2, -0.5f - spread * 0.0625f * 0.5f, -0.125f, -0.125f, 0.25f, 0.75f, 0.25f, 255, 255, 255, alpha);
            m2.pop();
            m2.push();
            m2.translate(0.375f, 0.125f, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(swing));
            m2.translate(-0.375f, -0.125f, 0.0f);
            this.drawGlowBox(m2, 0.25f + spread * 0.0625f * 0.5f, -0.125f, -0.125f, 0.25f, 0.75f, 0.25f, 255, 255, 255, alpha);
            m2.pop();
            m2.push();
            m2.translate(-0.125f, 0.75f, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(swing));
            m2.translate(0.125f, -0.75f, 0.0f);
            this.drawGlowBox(m2, -0.25f - spread * 0.0625f * 0.3f, 0.75f, -0.125f, 0.25f, 0.75f, 0.25f, 255, 255, 255, alpha);
            m2.pop();
            m2.push();
            m2.translate(0.125f, 0.75f, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(-swing));
            m2.translate(-0.125f, -0.75f, 0.0f);
            this.drawGlowBox(m2, 0.0f + spread * 0.0625f * 0.3f, 0.75f, -0.125f, 0.25f, 0.75f, 0.25f, 255, 255, 255, alpha);
            m2.pop();
            m2.pop();
        }
        for (ParticleBody particleBody : this.particleBodies) {
            float t2 = Math.min(1.0f, (float)(now - particleBody.birth) / 3000.0f);
            alpha = (1.0f - t2 * t2) * 240.0f;
            float scatter = t2 * t2 * 5.0f;
            m2.push();
            m2.translate(particleBody.pos.x - cam.x, particleBody.pos.y - cam.y, particleBody.pos.z - cam.z);
            m2.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - particleBody.yaw));
            m2.scale(-1.0f, -1.0f, 1.0f);
            m2.translate(0.0, -1.5, 0.0);
            if (particleBody.sneak) {
                m2.translate(0.0, 0.2, 0.0);
                m2.multiply(RotationAxis.POSITIVE_X.rotationDegrees(28.0f));
            }
            Matrix4f mat = m2.peek().getPositionMatrix();
            int ai2 = MathHelper.clamp((int)((int)alpha), (int)0, (int)255);
            BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
            for (ParticlePoint p2 : particleBody.particles) {
                float px = (float)(p2.local.x + p2.dir.x * (double)scatter);
                float py = (float)(p2.local.y + p2.dir.y * (double)scatter + (double)(t2 * t2 * 2.0f));
                float pz = (float)(p2.local.z + p2.dir.z * (double)scatter);
                float sz = p2.baseSize + t2 * 0.06f;
                float h2 = sz / 2.0f;
                this.putGlowBox(buf, mat, px - h2, py - h2, pz - h2, sz, sz, sz, 255, 255, 255, ai2);
            }
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
            m2.pop();
        }
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void drawGlowBox(MatrixStack m2, float x2, float y2, float z2, float sx, float sy, float sz, int r2, int g2, int bl, float a2) {
        Matrix4f mat = m2.peek().getPositionMatrix();
        int ai2 = MathHelper.clamp((int)((int)a2), (int)0, (int)255);
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        this.putGlowBox(buf, mat, x2, y2, z2, sx, sy, sz, r2, g2, bl, ai2);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
    }

    private void putGlowBox(BufferBuilder buf, Matrix4f mat, float x2, float y2, float z2, float sx, float sy, float sz, int r2, int g2, int bl, int ai2) {
        float x22 = x2 + sx;
        float y22 = y2 + sy;
        float z22 = z2 + sz;
        buf.vertex(mat, x2, y2, z22).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z22).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z22).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z22).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z2).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y2, z2).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z2).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z2).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y2, z2).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y2, z22).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z22).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z2).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z22).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z2).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z2).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z22).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z2).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z2).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y22, z22).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y22, z22).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z2).texture(0.0f, 1.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y2, z2).texture(0.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x2, y2, z22).texture(1.0f, 0.0f).color(r2, g2, bl, ai2);
        buf.vertex(mat, x22, y2, z22).texture(1.0f, 1.0f).color(r2, g2, bl, ai2);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.bodies.clear();
        this.particleBodies.clear();
        this.target = null;
    }

    private static class GhostBody {
        Vec3d pos;
        float yaw;
        boolean sneak;
        float phase;
        long birth;

        GhostBody(Vec3d pos, float yaw, boolean sneak, float phase) {
            this.pos = pos;
            this.yaw = yaw;
            this.sneak = sneak;
            this.phase = phase;
            this.birth = System.currentTimeMillis();
        }
    }

    private static class ParticleBody {
        Vec3d pos;
        float yaw;
        boolean sneak;
        float phase;
        long birth;
        List<ParticlePoint> particles;

        ParticleBody(Vec3d pos, float yaw, boolean sneak, float phase, List<ParticlePoint> particles) {
            this.pos = pos;
            this.yaw = yaw;
            this.sneak = sneak;
            this.phase = phase;
            this.birth = System.currentTimeMillis();
            this.particles = particles;
        }
    }

    private static class ParticlePoint {
        Vec3d local;
        Vec3d dir;
        float baseSize;

        ParticlePoint(Vec3d local, Vec3d dir, float baseSize) {
            this.local = local;
            this.dir = dir;
            this.baseSize = baseSize;
        }
    }
}

