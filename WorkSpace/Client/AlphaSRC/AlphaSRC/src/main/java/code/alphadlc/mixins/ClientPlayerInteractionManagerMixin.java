/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1269$class_9860
 *  net.minecraft.class_1269$class_9861
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_636
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.BlockBreakingEvent;
import code.alphadlc.events.block.BreakBlockEvent;
import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.utils.client.FakePlayerManager;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.interactions.item.ItemToolkit;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_636.class})
public class ClientPlayerInteractionManagerMixin {
    @Inject(method={"attackEntity"}, at={@At(value="HEAD")}, cancellable=true)
    public void attackEntityHook(class_1657 player, class_1297 target, CallbackInfo info) {
        if (FakePlayerManager.handleAttack(target)) {
            player.method_6104(class_1268.field_5808);
            info.cancel();
            return;
        }
        AttackEvent event = SafeEvents.dispatch(() -> new AttackEvent(target));
        if (event != null && event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"interactItem"}, at={@At(value="RETURN")})
    public void interactItemHook(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        class_1269.class_9860 success;
        Object object = cir.getReturnValue();
        if (object instanceof class_1269.class_9860 && !(success = (class_1269.class_9860)object).comp_2909().equals((Object)class_1269.class_9861.field_52427)) {
            SafeEvents.dispatch(() -> new UsingItemEvent(0));
        }
    }

    @Inject(method={"stopUsingItem"}, at={@At(value="HEAD")})
    public void stopUsingItemHook(CallbackInfo ci) {
        SafeEvents.dispatch(() -> new UsingItemEvent(2));
        if (ItemToolkit.INSTANCE.isUseItem()) {
            ItemToolkit.INSTANCE.setUseItem(false);
        }
    }

    @Inject(method={"interactItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void gameModeHook(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        UsingItemEvent event = SafeEvents.dispatch(() -> new UsingItemEvent(-1, hand));
        if (event != null && event.isCancelled()) {
            cir.setReturnValue((Object)class_1269.field_5811);
        }
    }

    @Inject(method={"clickSlot"}, at={@At(value="HEAD")}, cancellable=true)
    public void clickSlotHook(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo info) {
        ClickSlotEvent event = SafeEvents.dispatch(() -> new ClickSlotEvent(syncId, slotId, button, actionType));
        if (event != null && event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"updateBlockBreakingProgress"}, at={@At(value="HEAD")})
    private void injectBlockBreaking(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
        SafeEvents.dispatch(() -> new BlockBreakingEvent(pos, direction));
    }

    @Inject(method={"breakBlock"}, at={@At(value="RETURN")})
    private void injectBreakBlock(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
        SafeEvents.dispatch(() -> new BreakBlockEvent(pos));
    }
}
