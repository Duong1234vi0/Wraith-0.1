/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.player.EntitySpawnEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1297;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_638.class})
public class ClientWorldMixin
implements QuickImports {
    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void initHook(CallbackInfo info) {
        SafeEvents.dispatch(() -> new WorldLoadEvent());
    }

    @Inject(method={"addEntity"}, at={@At(value="HEAD")}, cancellable=true)
    public void addEntityHook(class_1297 entity, CallbackInfo ci) {
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        EntitySpawnEvent event = SafeEvents.dispatch(() -> new EntitySpawnEvent(entity));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }
}
