package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.LivingEntityAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinHandMyItemsLivingEntity implements LivingEntityAccessor {

    @Unique
    private boolean hmi$interactOffhand;
    @Unique
    private boolean hmi$interactMainHand;
    @Unique
    private boolean hmi$blockBreaking;
    @Unique
    private int hmi$offHandSwingTicks;
    @Unique
    private boolean hmi$offHandSwinging;
    @Unique
    private float hmi$offHandSwingProgress;
    @Unique
    private float hmi$lastOffHandSwingProgress;
    @Unique
    private int hmi$mainHandSwingTicks;
    @Unique
    private boolean hmi$mainHandSwinging;
    @Unique
    private float hmi$mainHandSwingProgress;
    @Unique
    private float hmi$lastMainHandSwingProgress;
    @Unique
    private int hmi$mainSwingCount;
    @Unique
    private boolean hmi$swingMainHand;
    @Unique
    private boolean hmi$swingOffhand;

    @Shadow
    public abstract ItemStack getMainHandItem();

    @Shadow
    public abstract ItemStack getOffhandItem();

    @Override
    public int hMI5_0$getSwingCount() {
        return this.hmi$mainSwingCount;
    }

    @Override
    public void hMI5_0$resetOffHandSwing(boolean interact) {
        this.hmi$offHandSwingTicks = 0;
        this.hmi$offHandSwinging = true;
        this.hmi$swingOffhand = !this.hmi$swingOffhand;
        this.hmi$interactOffhand = interact;
    }

    @Override
    public void hMI5_0$resetMainHandSwing(boolean interact) {
        this.hmi$mainHandSwingTicks = 0;
        this.hmi$mainHandSwinging = true;
        this.hmi$swingMainHand = !this.hmi$swingMainHand;
        this.hmi$mainSwingCount++;
        this.hmi$interactMainHand = interact;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.gameMode != null && minecraft.gameMode.isDestroying()) {
            this.hmi$blockBreaking = true;
        }
    }

    @Override
    public void hMI5_0$resetAll() {
        this.hmi$interactOffhand = false;
        this.hmi$interactMainHand = false;
        this.hmi$blockBreaking = false;
        this.hmi$offHandSwingTicks = 0;
        this.hmi$offHandSwinging = false;
        this.hmi$offHandSwingProgress = 0.0F;
        this.hmi$lastOffHandSwingProgress = 0.0F;
        this.hmi$mainHandSwingTicks = 0;
        this.hmi$mainHandSwinging = false;
        this.hmi$mainHandSwingProgress = 0.0F;
        this.hmi$lastMainHandSwingProgress = 0.0F;
        this.hmi$mainSwingCount = 0;
        this.hmi$swingMainHand = false;
        this.hmi$swingOffhand = false;
    }

    @Override
    public float hMI5_0$getMainHandSwingProgress(float tickDelta) {
        float progress = this.hmi$mainHandSwingProgress - this.hmi$lastMainHandSwingProgress;
        if (progress < 0.0F) {
            progress += 1.0F;
        }
        return this.hmi$lastMainHandSwingProgress + progress * tickDelta;
    }

    @Override
    public float hMI5_0$getOffHandSwingProgress(float tickDelta) {
        float progress = this.hmi$offHandSwingProgress - this.hmi$lastOffHandSwingProgress;
        if (progress < 0.0F) {
            progress += 1.0F;
        }
        return this.hmi$lastOffHandSwingProgress + progress * tickDelta;
    }

    @Override
    public boolean hMI5_0$getMInteract() {
        return this.hmi$interactMainHand;
    }

    @Override
    public boolean hMI5_0$getOInteract() {
        return this.hmi$interactOffhand;
    }

    @Override
    public boolean hMI5_0$getBlockBreak() {
        return this.hmi$blockBreaking;
    }

    @Override
    public boolean hMI5_0$getMHandEvent() {
        return this.hmi$swingMainHand;
    }

    @Override
    public boolean hMI5_0$getOHandEvent() {
        return this.hmi$swingOffhand;
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void hmi$tickIndependentSwings(CallbackInfo ci) {
        if (!HandMyItemsRuntime.isActive()) {
            return;
        }

        this.hmi$lastOffHandSwingProgress = this.hmi$offHandSwingProgress;
        this.hmi$lastMainHandSwingProgress = this.hmi$mainHandSwingProgress;

        int mainDuration = GlobalsStorage.itemSwingSpeed.getOrDefault(this.getMainHandItem().getItem().toString(), 10);
        if (this.hmi$mainHandSwinging) {
            this.hmi$mainHandSwingTicks++;
            if (this.hmi$mainHandSwingTicks >= mainDuration) {
                this.hmi$mainHandSwingTicks = 0;
                this.hmi$mainHandSwinging = false;
            }
        } else {
            this.hmi$interactMainHand = false;
            this.hmi$blockBreaking = false;
            this.hmi$mainHandSwingTicks = 0;
        }
        this.hmi$mainHandSwingProgress = (float) this.hmi$mainHandSwingTicks / (float) mainDuration;

        int offhandDuration = GlobalsStorage.itemSwingSpeed.getOrDefault(this.getOffhandItem().getItem().toString(), 10);
        if (this.hmi$offHandSwinging) {
            this.hmi$offHandSwingTicks++;
            if (this.hmi$offHandSwingTicks >= offhandDuration) {
                this.hmi$offHandSwingTicks = 0;
                this.hmi$offHandSwinging = false;
            }
        } else {
            this.hmi$interactOffhand = false;
            this.hmi$offHandSwingTicks = 0;
        }
        this.hmi$offHandSwingProgress = (float) this.hmi$offHandSwingTicks / (float) offhandDuration;
    }

    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;Z)V", at = @At("HEAD"))
    private void hmi$observeSwing(InteractionHand hand, boolean sendToSwingingEntity, CallbackInfo ci) {
        if (!HandMyItemsRuntime.isActive()) {
            return;
        }

        if (hand == InteractionHand.OFF_HAND) {
            int duration = GlobalsStorage.itemSwingSpeed.getOrDefault(this.getOffhandItem().getItem().toString(), 10);
            if (!this.hmi$offHandSwinging || this.hmi$offHandSwingTicks >= duration / 2 || this.hmi$offHandSwingTicks < 0) {
                this.hmi$offHandSwingTicks = -1;
                this.hmi$offHandSwinging = true;
            }
            return;
        }

        int duration = GlobalsStorage.itemSwingSpeed.getOrDefault(this.getMainHandItem().getItem().toString(), 10);
        if (!this.hmi$mainHandSwinging || this.hmi$mainHandSwingTicks >= duration / 2 || this.hmi$mainHandSwingTicks < 0) {
            this.hmi$mainHandSwingTicks = -1;
            this.hmi$mainHandSwinging = true;
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.gameMode != null && minecraft.gameMode.isDestroying()) {
                this.hmi$blockBreaking = true;
            }
        }
    }
}
