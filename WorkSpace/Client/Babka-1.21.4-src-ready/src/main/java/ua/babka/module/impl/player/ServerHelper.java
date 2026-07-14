package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import java.util.function.Predicate;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.PlayerInput;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.movement.Sprint;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.Instance;

@ModuleInformation(moduleName="ServerHelper", moduleDesc="Помощник серверных команд", moduleCategory=ModuleCategory.PLAYER)
public class ServerHelper
extends Module {
    private final ModeSetting server = new ModeSetting("Сервер", "ReallyWorld", "ReallyWorld");
    private final BindSetting antiFlyKey = new BindSetting("Анти Полет", 71).setVisible(() -> this.server.is("ReallyWorld"));
    private final BindSetting darkPulseKey = new BindSetting("Тёмный Пульс", -1).setVisible(() -> this.server.is("ReallyWorld"));
    private final BindSetting chaosBallKey = new BindSetting("Шар Хаоса", -1).setVisible(() -> this.server.is("ReallyWorld"));
    private final BindSetting trapKey = new BindSetting("Ловушки", -1).setVisible(() -> this.server.is("ReallyWorld"));
    private boolean useAntiFly;
    private boolean useDarkPulse;
    private boolean useChaosBall;
    private boolean useTrap;

    private boolean isDarkPulse(ItemStack stack) {
        if (!stack.isOf(Items.FIREWORK_STAR)) {
            return false;
        }
        return stack.getName().getString().contains("Тёмный Пульс");
    }

    private boolean isChaosBall(ItemStack stack) {
        if (!stack.isOf(Items.PLAYER_HEAD)) {
            return false;
        }
        return stack.getName().getString().contains("Шар Хаоса");
    }

    private boolean isAntiFly(ItemStack stack) {
        if (!stack.isOf(Items.FIREWORK_STAR)) {
            return false;
        }
        return !this.isDarkPulse(stack);
    }

    private boolean isTrapItem(ItemStack stack) {
        if (stack.isOf(Items.HEART_OF_THE_SEA)) {
            return true;
        }
        if (stack.isOf(Items.ENCHANTED_BOOK)) {
            ItemEnchantmentsComponent ench = (ItemEnchantmentsComponent)stack.getOrDefault(DataComponentTypes.STORED_ENCHANTMENTS, (Object)ItemEnchantmentsComponent.DEFAULT);
            for (RegistryEntry entry : ench.getEnchantments()) {
                if (!entry.matchesId(Enchantments.VANISHING_CURSE.getValue())) continue;
                return true;
            }
        }
        return false;
    }

    private int findSlot(Predicate<ItemStack> predicate, int from, int to) {
        for (int i2 = from; i2 < to; ++i2) {
            if (!predicate.test(this.mc.player.getInventory().getStack(i2))) continue;
            return i2;
        }
        return -1;
    }

    private int findTrapInHotbar() {
        return this.findSlot(this::isTrapItem, 0, 9);
    }

    private int findTrapInInventory() {
        return this.findSlot(this::isTrapItem, 9, 45);
    }

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (this.mc.currentScreen != null) {
            return;
        }
        if (e2.getAction() != 1) {
            return;
        }
        if (e2.getKey() == this.antiFlyKey.getValue().intValue()) {
            this.useAntiFly = true;
        }
        if (e2.getKey() == this.darkPulseKey.getValue().intValue()) {
            this.useDarkPulse = true;
        }
        if (e2.getKey() == this.chaosBallKey.getValue().intValue()) {
            this.useChaosBall = true;
        }
        if (e2.getKey() == this.trapKey.getValue().intValue()) {
            this.useTrap = true;
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.useAntiFly) {
            this.useAntiFly = false;
            this.useItem(this::isAntiFly);
        }
        if (this.useDarkPulse) {
            this.useDarkPulse = false;
            this.useItem(this::isDarkPulse);
        }
        if (this.useChaosBall) {
            this.useChaosBall = false;
            this.useItem(this::isChaosBall);
        }
    }

    private void useItem(Predicate<ItemStack> predicate) {
        if (this.mc.player == null || this.mc.getNetworkHandler() == null) {
            return;
        }
        if (predicate.test(this.mc.player.getOffHandStack())) {
            this.shiftClick();
            return;
        }
        int hotbarSlot = this.findSlot(predicate, 0, 9);
        if (hotbarSlot != -1) {
            boolean wasSprinting = this.stopSprintingIfNeeded();
            this.swapToOffhand(this.mc.player.currentScreenHandler.syncId, 45, hotbarSlot);
            this.shiftClick();
            this.swapToOffhand(this.mc.player.currentScreenHandler.syncId, 45, hotbarSlot);
            if (wasSprinting) {
                this.resumeSprinting();
            }
            return;
        }
        int invSlot = this.findSlot(predicate, 9, 45);
        if (invSlot != -1) {
            boolean wasSprinting = this.stopSprintingIfNeeded();
            this.swapToOffhand(0, invSlot, 40);
            this.shiftClick();
            this.swapToOffhand(0, invSlot, 40);
            if (wasSprinting) {
                this.resumeSprinting();
            }
        }
    }

    private void shiftClick() {
        this.mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)this.mc.player, ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY));
        this.mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)this.mc.player, ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY));
    }

    private void swapToOffhand(int syncId, int slot, int button) {
        this.mc.interactionManager.clickSlot(syncId, slot, button, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
        this.mc.getNetworkHandler().sendPacket((Packet)new CloseHandledScreenC2SPacket(0));
    }

    private boolean stopSprintingIfNeeded() {
        if (!this.mc.player.isSprinting()) {
            return false;
        }
        this.mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
        this.mc.player.setSprinting(false);
        this.mc.getNetworkHandler().sendPacket((Packet)new ClientCommandC2SPacket((Entity)this.mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));
        if (!Instance.get(Sprint.class).isEnabled()) {
            this.mc.options.sprintKey.setPressed(false);
        }
        return true;
    }

    private void resumeSprinting() {
        this.mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(this.mc.player.input.playerInput));
    }

    @Subscribe
    private void onTrapTick(EventTick e2) {
        if (!this.useTrap) {
            return;
        }
        this.useTrap = false;
        if (this.mc.player == null) {
            return;
        }
        if (this.isTrapItem(this.mc.player.getMainHandStack())) {
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
            return;
        }
        if (this.isTrapItem(this.mc.player.getOffHandStack())) {
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.OFF_HAND);
            return;
        }
        int slot = this.findTrapInHotbar();
        if (slot != -1) {
            int prev = this.mc.player.getInventory().selectedSlot;
            this.mc.player.getInventory().selectedSlot = slot;
            this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(slot));
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
            this.mc.player.getInventory().selectedSlot = prev;
            this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(prev));
            return;
        }
        slot = this.findTrapInInventory();
        if (slot != -1) {
            int prev = this.mc.player.getInventory().selectedSlot;
            int syncId = this.mc.player.currentScreenHandler.syncId;
            this.mc.interactionManager.clickSlot(syncId, slot, prev, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
            this.mc.interactionManager.clickSlot(syncId, slot, prev, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
        }
    }
}

