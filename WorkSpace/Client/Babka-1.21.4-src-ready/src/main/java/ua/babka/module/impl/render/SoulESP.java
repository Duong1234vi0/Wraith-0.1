package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import ua.babka.event.EventPacket;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Soul ESP", moduleDesc="Отображает душу убитого игрока", moduleCategory=ModuleCategory.RENDER)
public class SoulESP
extends Module {
    private static final float DURATION = 3.0f;
    private static final float HEIGHT = 3.5f;
    private final List<Ghost> ghosts = new ArrayList<Ghost>();

    @Subscribe
    public void onPacket(EventPacket e2) {
        Entity entity;
        EntityStatusS2CPacket packet;
        byte status;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket && ((status = (packet = (EntityStatusS2CPacket)packet2).getStatus()) == 3 || status == 35) && (entity = packet.getEntity((World)this.mc.world)) instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            if (player == this.mc.player) {
                return;
            }
            this.ghosts.add(new Ghost(player.getPos(), player.getBodyYaw(), player.isSneaking(), player.age, System.currentTimeMillis()));
        }
    }

    @Subscribe
    public void onRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null || this.ghosts.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        float dur = 3000.0f;
        this.ghosts.removeIf(g2 -> (float)(now - g2.time) >= dur);
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack m2 = e2.getMatrixStack();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        for (Ghost g3 : this.ghosts) {
            float t2 = (float)(now - g3.time) / dur;
            if (t2 >= 1.0f) continue;
            float alpha = (1.0f - t2) * 0.6f;
            float rise = 3.5f * this.ease(t2);
            int colorFirst = ColorProvider.getColorClient();
            int colorSecond = ColorProvider.getColorClient();
            int c2 = ColorProvider.interpolateColor(colorFirst, colorSecond, t2);
            float r2 = (float)ColorProvider.red(c2) / 255.0f;
            float gr = (float)ColorProvider.green(c2) / 255.0f;
            float b2 = (float)ColorProvider.blue(c2) / 255.0f;
            m2.push();
            m2.translate(g3.pos.x - cam.x, g3.pos.y - cam.y + (double)rise, g3.pos.z - cam.z);
            m2.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - g3.yaw));
            m2.scale(-1.0f, -1.0f, 1.0f);
            m2.translate(0.0, -1.5, 0.0);
            if (g3.sneak) {
                m2.translate(0.0, 0.2, 0.0);
                m2.multiply(RotationAxis.POSITIVE_X.rotationDegrees(28.0f));
            }
            Matrix4f mat = m2.peek().getPositionMatrix();
            BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            float u2 = 0.0625f;
            float swing = MathHelper.sin((float)(g3.phase * 0.6662f)) * 0.6f;
            this.box(buf, mat, -4.0f * u2, 0.0f, -2.0f * u2, 8.0f * u2, 12.0f * u2, 4.0f * u2, r2, gr, b2, alpha);
            this.box(buf, mat, -4.0f * u2, -8.0f * u2, -4.0f * u2, 8.0f * u2, 8.0f * u2, 8.0f * u2, r2, gr, b2, alpha);
            m2.push();
            m2.translate(-6.0f * u2, 2.0f * u2, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(-swing));
            m2.translate(6.0f * u2, -2.0f * u2, 0.0f);
            this.box(buf, m2.peek().getPositionMatrix(), -8.0f * u2, -2.0f * u2, -2.0f * u2, 4.0f * u2, 12.0f * u2, 4.0f * u2, r2, gr, b2, alpha);
            m2.pop();
            m2.push();
            m2.translate(6.0f * u2, 2.0f * u2, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(swing));
            m2.translate(-6.0f * u2, -2.0f * u2, 0.0f);
            this.box(buf, m2.peek().getPositionMatrix(), 4.0f * u2, -2.0f * u2, -2.0f * u2, 4.0f * u2, 12.0f * u2, 4.0f * u2, r2, gr, b2, alpha);
            m2.pop();
            m2.push();
            m2.translate(-2.0f * u2, 12.0f * u2, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(swing));
            m2.translate(2.0f * u2, -12.0f * u2, 0.0f);
            this.box(buf, m2.peek().getPositionMatrix(), -4.0f * u2, 12.0f * u2, -2.0f * u2, 4.0f * u2, 12.0f * u2, 4.0f * u2, r2, gr, b2, alpha);
            m2.pop();
            m2.push();
            m2.translate(2.0f * u2, 12.0f * u2, 0.0f);
            m2.multiply(RotationAxis.POSITIVE_X.rotation(-swing));
            m2.translate(-2.0f * u2, -12.0f * u2, 0.0f);
            this.box(buf, m2.peek().getPositionMatrix(), 0.0f, 12.0f * u2, -2.0f * u2, 4.0f * u2, 12.0f * u2, 4.0f * u2, r2, gr, b2, alpha);
            m2.pop();
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
            m2.pop();
        }
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private void box(BufferBuilder b2, Matrix4f m2, float x2, float y2, float z2, float sx, float sy, float sz, float r2, float g2, float bl, float a2) {
        float x22 = x2 + sx;
        float y22 = y2 + sy;
        float z22 = z2 + sz;
        b2.vertex(m2, x2, y2, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y2, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y22, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z2).color(r2, g2, bl, a2);
        b2.vertex(m2, x22, y2, z22).color(r2, g2, bl, a2);
        b2.vertex(m2, x2, y2, z22).color(r2, g2, bl, a2);
    }

    private float ease(float t2) {
        return 1.0f - (float)Math.pow(1.0f - MathHelper.clamp((float)t2, (float)0.0f, (float)1.0f), 3.0);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.ghosts.clear();
    }

    private static class Ghost {
        Vec3d pos;
        float yaw;
        boolean sneak;
        float phase;
        long time;

        Ghost(Vec3d pos, float yaw, boolean sneak, float phase, long time) {
            this.pos = pos;
            this.yaw = yaw;
            this.sneak = sneak;
            this.phase = phase;
            this.time = time;
        }
    }
}

