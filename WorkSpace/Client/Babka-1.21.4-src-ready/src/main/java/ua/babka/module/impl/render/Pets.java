package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import ua.babka.core.RenderDog;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.player.PetBrain;

@ModuleInformation(moduleName="Pets", moduleDesc="null", moduleCategory=ModuleCategory.RENDER)
public class Pets
extends Module {
    private final PetBrain brain = new PetBrain();
    private final RenderDog model = new RenderDog();
    private final ModeSetting texture = new ModeSetting("Текстура", "Пёс", "Пёс", "Такса");
    private final SliderSetting teleportDistance = new SliderSetting("Дист. телепорта", 35.0, 5.0, 500.0, 5.0);
    private final SliderSetting scale = new SliderSetting("Размер", 1.0, 0.5, 2.0, 0.05);

    @Subscribe
    public void onTick(EventTick event) {
        LivingEntity living;
        Entity entity;
        if (this.mc.player == null) {
            return;
        }
        this.brain.setEntity(this.mc.player);
        this.brain.teleportDistance = this.teleportDistance.getValue();
        this.brain.onUpdate();
        if (this.mc.options.attackKey.isPressed() && (entity = this.mc.targetedEntity) instanceof LivingEntity && (living = (LivingEntity)entity).isAlive() && living != this.mc.player) {
            this.brain.setAttackTarget(living);
        }
    }

    @Subscribe
    private void onWorldRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        float tickDelta = e2.getTickDelta();
        Vec3d dogWorldPos = this.brain.getPos(tickDelta);
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack matrices = e2.getMatrixStack();
        matrices.push();
        matrices.translate(dogWorldPos.x - cam.x, dogWorldPos.y - cam.y, dogWorldPos.z - cam.z);
        float s2 = (float)this.scale.getValue();
        matrices.scale(s2, s2, s2);
        String texName = this.texture.is("Пёс") ? "dog.png" : "djekrussel.png";
        this.model.setTextureFileName(texName);
        float age = (float)this.mc.player.age + tickDelta;
        this.model.setRotationAngles(age, this.brain);
        BlockPos lightPos = BlockPos.ofFloored((Position)dogWorldPos);
        int light = LightmapTextureManager.pack((int)this.mc.world.getLightLevel(LightType.BLOCK, lightPos), (int)this.mc.world.getLightLevel(LightType.SKY, lightPos));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        VertexConsumerProvider.Immediate vcp = this.mc.getBufferBuilders().getEntityVertexConsumers();
        this.model.render(matrices, (VertexConsumerProvider)vcp, this.brain, light);
        vcp.draw();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        matrices.pop();
    }

    @Override
    public void onEnable() {
        if (this.mc.player != null) {
            this.brain.setEntity(this.mc.player);
        }
        super.onEnable();
    }
}

