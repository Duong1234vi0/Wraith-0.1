/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1937.class})
public abstract class WorldMixin
implements QuickImports {
    @Inject(method={"onBlockChanged"}, at={@At(value="RETURN")})
    private void onBlockChangedHook(class_2338 pos, class_2680 oldBlock, class_2680 newBlock, CallbackInfo ci) {
        if (WorldMixin.mc.field_1687 != this) {
            return;
        }
        SafeEvents.dispatch(() -> new BlockUpdateEvent(newBlock, pos.method_10062(), BlockUpdateEvent.Type.UPDATE));
    }
}
