package ua.babka.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.module.impl.render.NoRender;
import ua.babka.util.Instance;

public class RemovalsMixin {

    @Mixin(value={GameRenderer.class})
    public static class GameRendererMixin {
        @Inject(method={"showFloatingItem"}, at={@At(value="HEAD")}, cancellable=true)
        private void cancelTotemAnimation(ItemStack stack, CallbackInfo ci) {
            if (stack == null || !stack.isOf(Items.TOTEM_OF_UNDYING)) {
                return;
            }
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Анимацию тотема")) {
                ci.cancel();
            }
        }
    }

    @Mixin(value={BackgroundRenderer.class})
    public static class BackgroundRendererMixin {
        @Inject(method={"getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"}, at={@At(value="HEAD")}, cancellable=true)
        private static void cancelBadEffectsFog(Entity entity, float tickDelta, CallbackInfoReturnable<Object> cir) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Плохие эффекты")) {
                cir.setReturnValue(null);
            }
        }
    }

    @Mixin(value={BlockEntityRenderDispatcher.class})
    public static class BlockEntityRendererMixin {
        @Inject(method={"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"}, at={@At(value="HEAD")}, cancellable=true)
        private <T extends BlockEntity> void cancelBlockEntity(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Блок-сущности")) {
                ci.cancel();
            }
        }
    }

    @Mixin(value={EntityRenderDispatcher.class})
    public static class EntityRenderDispatcherMixin {
        @Inject(method={"renderShadow"}, at={@At(value="HEAD")}, cancellable=true)
        private static void cancelShadow(CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Тени")) {
                ci.cancel();
            }
        }
    }

    @Mixin(value={WorldRenderer.class})
    public static class WorldRendererMixin {
        @Inject(method={"renderWeather"}, at={@At(value="HEAD")}, cancellable=true)
        private void cancelWeather(CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Погода")) {
                ci.cancel();
            }
        }

        @Inject(method={"addWeatherParticlesAndSound"}, at={@At(value="HEAD")}, cancellable=true)
        private void cancelWeatherParticlesAndSound(CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Погода")) {
                ci.cancel();
            }
        }

        @Inject(method={"renderClouds"}, at={@At(value="HEAD")}, cancellable=true)
        private void cancelClouds(CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Облака")) {
                ci.cancel();
            }
        }
    }

    @Mixin(value={ParticleManager.class})
    public static class ParticleManagerMixin {
        @Inject(method={"addParticle(Lnet/minecraft/client/particle/Particle;)V"}, at={@At(value="HEAD")}, cancellable=true)
        private void cancelParticle(Particle particle, CallbackInfo ci) {
            NoRender nr = Instance.get(NoRender.class);
            if (nr != null && nr.isRemoved("Частицы")) {
                ci.cancel();
            }
        }
    }
}

