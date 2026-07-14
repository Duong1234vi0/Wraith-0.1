package ua.babka.mixin;

import net.minecraft.client.item.ItemModelManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import ua.babka.module.impl.player.ItemReplacer;
import ua.babka.util.Instance;

@Mixin(value={ItemModelManager.class})
public abstract class ItemModelManagerMixin {
    @ModifyVariable(method={"update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ModelTransformationMode;ZLnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V"}, at=@At(value="HEAD"), argsOnly=true, ordinal=0)
    private ItemStack onUpdateItemStack(ItemStack stack) {
        return Instance.get(ItemReplacer.class).getRenderStack(stack);
    }
}

