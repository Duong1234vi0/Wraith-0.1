package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.ItemStackAccessor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinHandMyItemsItemStack implements ItemStackAccessor {

    @Unique
    private int hmi$transform = -1;
    @Unique
    private int hmi$swingSpeed = 10;
    @Unique
    private int hmi$renderAsBlock = -1;

    @Override
    public void hMI5_0$setTransform(boolean value) {
        this.hmi$transform = value ? 1 : 0;
    }

    @Override
    public void hMI5_0$setTransform(int value) {
        this.hmi$transform = value;
    }

    @Override
    public int hMI5_0$getTransform() {
        return this.hmi$transform;
    }

    @Override
    public void hMI5_0$setSwingSpeed(int value) {
        this.hmi$swingSpeed = value;
    }

    @Override
    public int hMI5_0$getSwingSpeed() {
        return this.hmi$swingSpeed;
    }

    @Override
    public void hMI5_0$setRenderAsBlock(boolean value) {
        this.hmi$renderAsBlock = value ? 1 : 0;
    }

    @Override
    public int hMI5_0$getRenderAsBlock() {
        return this.hmi$renderAsBlock;
    }

    @Inject(method = "copy", at = @At("RETURN"))
    private void hmi$copyRuntimeState(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack copy = cir.getReturnValue();
        if (copy == ItemStack.EMPTY || copy == (Object) this) {
            return;
        }

        ItemStackAccessor accessor = (ItemStackAccessor) (Object) copy;
        accessor.hMI5_0$setTransform(this.hmi$transform);
        accessor.hMI5_0$setSwingSpeed(this.hmi$swingSpeed);
        if (this.hmi$renderAsBlock >= 0) {
            accessor.hMI5_0$setRenderAsBlock(this.hmi$renderAsBlock == 1);
        }
    }
}
