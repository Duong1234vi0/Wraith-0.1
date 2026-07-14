package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import ua.babka.event.EventPacket;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="AppleEffect", moduleDesc="частицы при поедании золотого яблока", moduleCategory=ModuleCategory.RENDER)
public class AppleEffect
extends Module {
    private static final float[] ENCH_COLOR_BLOOM = new float[]{0.7f, 0.2f, 1.0f};
    private static final float[] ENCH_COLOR_CORE = new float[]{0.85f, 0.3f, 1.0f};
    private static final Identifier GLOW = Identifier.of((String)"mre", (String)"images/glow.png");
    private static final float DURATION = 2.5f;
    private final List<AppleParticle> particles = new ArrayList<AppleParticle>();
    private final Random rnd = new Random();
    private final List<EatingPlayer> eating = new ArrayList<EatingPlayer>();

    @Subscribe
    private void onPacket(EventPacket e2) {
        EntityStatusS2CPacket packet;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet2 = e2.getPacket();
        if (!(packet2 instanceof EntityStatusS2CPacket) || (packet = (EntityStatusS2CPacket)packet2).getStatus() != 9) {
            return;
        }
        Entity entity = packet.getEntity((World)this.mc.world);
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            boolean ench = player.getMainHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE || player.getOffHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE;
            this.spawn(player.getPos(), player == this.mc.player, ench);
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            boolean ench;
            boolean hasApple;
            if (player == this.mc.player || !(hasApple = (ench = player.getMainHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE || player.getOffHandStack().getItem() == Items.ENCHANTED_GOLDEN_APPLE) || player.getMainHandStack().getItem() == Items.GOLDEN_APPLE || player.getOffHandStack().getItem() == Items.GOLDEN_APPLE) || !player.isUsingItem()) continue;
            boolean found = false;
            for (EatingPlayer ep : this.eating) {
                if (ep.player != player) continue;
                found = true;
                break;
            }
            if (found) continue;
            this.eating.add(new EatingPlayer(player, ench));
        }
        Iterator<EatingPlayer> it = this.eating.iterator();
        while (it.hasNext()) {
            EatingPlayer ep = it.next();
            if (ep.player.isUsingItem()) continue;
            this.spawn(ep.player.getPos(), false, ep.enchanted);
            it.remove();
        }
    }

    private void spawn(Vec3d pos, boolean self, boolean enchanted) {
        int count = 25 + this.rnd.nextInt(15);
        for (int i2 = 0; i2 < count; ++i2) {
            float spread = 0.6f;
            float px = (float)(pos.x + (this.rnd.nextDouble() - 0.5) * (double)spread);
            float py = (float)(pos.y + 1.0 + this.rnd.nextDouble() * 0.8);
            float pz = (float)(pos.z + (this.rnd.nextDouble() - 0.5) * (double)spread);
            float vx = (float)((this.rnd.nextDouble() - 0.5) * 0.06);
            float vy = 0.02f + this.rnd.nextFloat() * 0.04f;
            float vz = (float)((this.rnd.nextDouble() - 0.5) * 0.06);
            float size = 0.2f + this.rnd.nextFloat() * 0.15f;
            long life = (long)(2500.0 * (0.5 + this.rnd.nextDouble() * 0.5));
            this.particles.add(new AppleParticle(new Vec3d((double)px, (double)py, (double)pz), new Vec3d((double)vx, (double)vy, (double)vz), size, life, self, enchanted));
        }
    }

    @Subscribe
    public void onRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null || this.particles.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        this.particles.removeIf(p2 -> now - p2.birth >= p2.life);
        if (this.particles.isEmpty()) {
            return;
        }
        boolean thirdPerson = this.mc.options.getPerspective() != Perspective.FIRST_PERSON;
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack m2 = e2.getMatrixStack();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((int)770, (int)1);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW);
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (AppleParticle p3 : this.particles) {
            float b2;
            float g2;
            float r2;
            float b1;
            float g1;
            float r1;
            if (p3.self && !thirdPerson) continue;
            p3.update();
            float t2 = Math.min(1.0f, (float)(now - p3.birth) / (float)p3.life);
            double rx = p3.pos.x - cam.x;
            double ry = p3.pos.y - cam.y;
            double rz = p3.pos.z - cam.z;
            m2.push();
            m2.translate((float)rx, (float)ry, (float)rz);
            m2.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-this.mc.gameRenderer.getCamera().getYaw()));
            m2.multiply(RotationAxis.POSITIVE_X.rotationDegrees(this.mc.gameRenderer.getCamera().getPitch()));
            Matrix4f mat = m2.peek().getPositionMatrix();
            float curSize = p3.size + t2 * 0.1f;
            float half = curSize / 2.0f;
            int alpha = (int)((1.0f - t2) * 220.0f);
            float bloom = half * 2.0f;
            int ba2 = (int)((float)alpha * 0.25f);
            if (p3.enchanted) {
                r1 = ENCH_COLOR_BLOOM[0];
                g1 = ENCH_COLOR_BLOOM[1];
                b1 = ENCH_COLOR_BLOOM[2];
                r2 = ENCH_COLOR_CORE[0];
                g2 = ENCH_COLOR_CORE[1];
                b2 = ENCH_COLOR_CORE[2];
            } else {
                r1 = 1.0f;
                g1 = 0.85f;
                b1 = 0.2f;
                r2 = 1.0f;
                g2 = 0.9f;
                b2 = 0.4f;
            }
            buf.vertex(mat, -bloom, -bloom, 0.0f).texture(0.0f, 1.0f).color(r1, g1, b1, (float)ba2);
            buf.vertex(mat, -bloom, bloom, 0.0f).texture(0.0f, 0.0f).color(r1, g1, b1, (float)ba2);
            buf.vertex(mat, bloom, bloom, 0.0f).texture(1.0f, 0.0f).color(r1, g1, b1, (float)ba2);
            buf.vertex(mat, bloom, -bloom, 0.0f).texture(1.0f, 1.0f).color(r1, g1, b1, (float)ba2);
            buf.vertex(mat, -half, -half, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, (float)alpha);
            buf.vertex(mat, -half, half, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, (float)alpha);
            buf.vertex(mat, half, half, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, (float)alpha);
            buf.vertex(mat, half, -half, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, (float)alpha);
            m2.pop();
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.particles.clear();
        this.eating.clear();
    }

    private static class EatingPlayer {
        PlayerEntity player;
        boolean enchanted;

        EatingPlayer(PlayerEntity player, boolean enchanted) {
            this.player = player;
            this.enchanted = enchanted;
        }
    }

    private static class AppleParticle {
        Vec3d pos;
        Vec3d vel;
        float size;
        long birth;
        long life;
        long lastNs;
        boolean self;
        boolean enchanted;

        AppleParticle(Vec3d pos, Vec3d vel, float size, long life, boolean self, boolean enchanted) {
            this.pos = pos;
            this.vel = vel;
            this.size = size;
            this.birth = System.currentTimeMillis();
            this.lastNs = System.nanoTime();
            this.life = life;
            this.self = self;
            this.enchanted = enchanted;
        }

        void update() {
            long nowNs = System.nanoTime();
            double dt = (double)(nowNs - this.lastNs) / 1.0E9;
            this.lastNs = nowNs;
            this.pos = this.pos.add(this.vel.x * dt * 60.0, this.vel.y * dt * 60.0, this.vel.z * dt * 60.0);
        }
    }
}

