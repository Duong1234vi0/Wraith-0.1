/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2586
 *  net.minecraft.class_2591
 *  net.minecraft.class_2680
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.BlockEntityProgressEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2586.class})
public class BlockEntityMixin {
    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void initHook(class_2591<?> type, class_2338 pos, class_2680 state, CallbackInfo ci) {
        class_2586 blockEntity = (class_2586)this;
        if (blockEntity != null) {
            SafeEvents.dispatch(() -> new BlockEntityProgressEvent(blockEntity, BlockEntityProgressEvent.Type.ADD));
        }
    }

    @Inject(method={"markRemoved"}, at={@At(value="HEAD")})
    private void markRemovedHook(CallbackInfo ci) {
        class_2586 blockEntity = (class_2586)this;
        if (blockEntity != null) {
            SafeEvents.dispatch(() -> new BlockEntityProgressEvent(blockEntity, BlockEntityProgressEvent.Type.REMOVE));
        }
    }
}
