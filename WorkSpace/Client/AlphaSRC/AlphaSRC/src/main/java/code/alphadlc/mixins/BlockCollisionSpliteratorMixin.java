/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_5329
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.BlockCollisionEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_5329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_5329.class})
public abstract class BlockCollisionSpliteratorMixin {
    @Redirect(method={"computeNext"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"))
    private class_2680 computeNext(class_1922 instance, class_2338 blockPos) {
        class_2680 state = instance.method_8320(blockPos);
        BlockCollisionEvent event = SafeEvents.dispatch(() -> new BlockCollisionEvent(blockPos, state));
        return event != null ? event.getState() : state;
    }
}
