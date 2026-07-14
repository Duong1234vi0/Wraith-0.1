package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventAttack;
import ua.babka.event.EventWorldRender;
import ua.babka.mixin.LivingEntityRendererAccessor;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="HitGhost", moduleDesc="Призрак при ударе", moduleCategory=ModuleCategory.RENDER)
public class HitGhost
extends Module {
    private static final float DURATION = 3.0f;
    private static final float HEIGHT = 3.5f;
    private final List<Ghost> ghosts = new ArrayList<Ghost>();

    @Subscribe
    public void onAttack(EventAttack e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Entity entity = e2.getEntity();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            if (player == this.mc.player) {
                return;
            }
            this.ghosts.add(new Ghost(player, player.getPos(), player.getBodyYaw(), player.isSneaking(), player.age, System.currentTimeMillis()));
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
        RenderSystem.depthMask((boolean)false);
        VertexConsumerProvider.Immediate vcp = this.mc.getBufferBuilders().getEntityVertexConsumers();
        for (Ghost g3 : this.ghosts) {
            EntityRenderer rawRenderer;
            float t2 = (float)(now - g3.time) / dur;
            if (t2 >= 1.0f) continue;
            float alpha = (1.0f - t2) * 0.6f;
            float rise = 3.5f * this.ease(t2);
            PlayerEntity player = g3.player;
            if (!(player instanceof AbstractClientPlayerEntity)) continue;
            AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity)player;
            if (!player.isAlive() || !((rawRenderer = this.mc.getEntityRenderDispatcher().getRenderer((Entity)player)) instanceof PlayerEntityRenderer)) continue;
            PlayerEntityRenderer renderer = (PlayerEntityRenderer)rawRenderer;
            PlayerEntityRenderState state = renderer.createRenderState();
            renderer.updateRenderState(clientPlayer, state, e2.getTickDelta());
            PlayerEntityModel model = (PlayerEntityModel)renderer.getModel();
            model.setAngles(state);
            m2.push();
            m2.translate(g3.pos.x - cam.x, g3.pos.y - cam.y + (double)rise, g3.pos.z - cam.z);
            float baseScale = state.baseScale;
            m2.scale(baseScale, baseScale, baseScale);
            ((LivingEntityRendererAccessor)renderer).babka$setupTransforms((LivingEntityRenderState)state, m2, g3.yaw, baseScale);
            m2.scale(-1.0f, -1.0f, 1.0f);
            ((LivingEntityRendererAccessor)renderer).babka$scale((LivingEntityRenderState)state, m2);
            m2.translate(0.0f, -1.501f, 0.0f);
            if (g3.sneak) {
                m2.translate(0.0f, 0.2f, 0.0f);
                m2.multiply(RotationAxis.POSITIVE_X.rotationDegrees(28.0f));
            }
            VertexConsumer vc = vcp.getBuffer(RenderLayer.getEntityTranslucent((Identifier)clientPlayer.getSkinTextures().comp_1626()));
            int ghostColor = (int)(alpha * 255.0f) << 24 | 0xFFFFFF;
            model.render(m2, vc, 0xF000F0, OverlayTexture.DEFAULT_UV, ghostColor);
            m2.pop();
        }
        vcp.draw();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
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
        PlayerEntity player;
        Vec3d pos;
        float yaw;
        boolean sneak;
        float phase;
        long time;

        Ghost(PlayerEntity player, Vec3d pos, float yaw, boolean sneak, float phase, long time) {
            this.player = player;
            this.pos = pos;
            this.yaw = yaw;
            this.sneak = sneak;
            this.phase = phase;
            this.time = time;
        }
    }
}

