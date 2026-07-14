package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.movement.GuiMove;
import ua.babka.module.setting.BindSetting;
import ua.babka.util.Instance;

@ModuleInformation(moduleName="Funtime Helper", moduleDesc="Полный выбрасыватель вещей", moduleCategory=ModuleCategory.MISC)
public class FuntimeHelper
extends Module {
    private final BindSetting snowballKey = new BindSetting("Снежок Заморозка", -1);
    private final BindSetting godsAuraKey = new BindSetting("Божья Аура", -1);
    private final BindSetting trapKey = new BindSetting("Трапка", -1);
    private final BindSetting plastKey = new BindSetting("Пласт", -1);
    private final BindSetting clearDustKey = new BindSetting("Явная Пыль", -1);
    private final BindSetting fireChargeKey = new BindSetting("Огненный Заряд", -1);
    private final BindSetting disorientationKey = new BindSetting("Дезориентация", -1);
    private Item pendingItem = null;
    private int tickTimer = 0;
    private int itemSlot = -1;
    private int savedSlot = -1;

    @Override
    public void onEnable() {
        super.onEnable();
        this.reset();
    }

    @Override
    public void onDisable() {
        this.restoreSlot();
        super.onDisable();
    }

    @Subscribe
    public void onTick(EventTick e2) {
        if (this.mc.player == null || this.pendingItem == null) {
            return;
        }
        ++this.tickTimer;
        switch (this.tickTimer) {
            case 1: {
                this.itemSlot = this.findItemInHotbar(this.pendingItem);
                if (this.itemSlot == -1) {
                    this.itemSlot = this.findItemInInventory(this.pendingItem);
                    if (this.itemSlot == -1) {
                        this.reset();
                        return;
                    }
                    int syncId = this.mc.player.currentScreenHandler.syncId;
                    this.savedSlot = this.mc.player.getInventory().selectedSlot;
                    this.mc.interactionManager.clickSlot(syncId, this.itemSlot, this.savedSlot, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
                    this.itemSlot = this.savedSlot;
                    break;
                }
                this.savedSlot = this.mc.player.getInventory().selectedSlot;
                if (this.savedSlot == this.itemSlot) break;
                this.mc.player.getInventory().selectedSlot = this.itemSlot;
                this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(this.itemSlot));
                break;
            }
            case 2: {
                if (this.mc.player.getInventory().selectedSlot != this.itemSlot) {
                    this.mc.player.getInventory().selectedSlot = this.itemSlot;
                    this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(this.itemSlot));
                }
                this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
                this.mc.player.swingHand(Hand.MAIN_HAND);
                break;
            }
            case 3: {
                this.restoreSlot();
                this.reset();
            }
        }
    }

    @Subscribe
    public void onKeyInput(EventKeyInput e2) {
        if (this.mc.player == null || this.pendingItem != null) {
            return;
        }
        if (e2.getAction() != 1) {
            return;
        }
        if (!this.isGuiMoveEnabled() && this.mc.currentScreen != null) {
            return;
        }
        int key = e2.getKey();
        if (key == -1) {
            return;
        }
        Item item = this.getItemForKey(key);
        if (item == null) {
            return;
        }
        if (this.findItemInHotbar(item) == -1 && this.findItemInInventory(item) == -1) {
            return;
        }
        this.pendingItem = item;
        this.tickTimer = 0;
        this.itemSlot = -1;
        this.savedSlot = -1;
    }

    private Item getItemForKey(int key) {
        if (key == this.snowballKey.getValue()) {
            return Items.SNOWBALL;
        }
        if (key == this.godsAuraKey.getValue()) {
            return Items.PHANTOM_MEMBRANE;
        }
        if (key == this.trapKey.getValue()) {
            return Items.NETHERITE_SCRAP;
        }
        if (key == this.plastKey.getValue()) {
            return Items.DRIED_KELP;
        }
        if (key == this.clearDustKey.getValue()) {
            return Items.SUGAR;
        }
        if (key == this.fireChargeKey.getValue()) {
            return Items.FIRE_CHARGE;
        }
        if (key == this.disorientationKey.getValue()) {
            return Items.ENDER_EYE;
        }
        return null;
    }

    private int findItemInHotbar(Item item) {
        for (int i2 = 0; i2 < 9; ++i2) {
            if (this.mc.player.getInventory().getStack(i2).getItem() != item) continue;
            return i2;
        }
        return -1;
    }

    private int findItemInInventory(Item item) {
        for (int i2 = 9; i2 < 45; ++i2) {
            if (this.mc.player.getInventory().getStack(i2).getItem() != item) continue;
            return i2;
        }
        return -1;
    }

    private void restoreSlot() {
        if (this.savedSlot != -1 && this.mc.player != null) {
            this.mc.player.getInventory().selectedSlot = this.savedSlot;
            this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(this.savedSlot));
            this.savedSlot = -1;
        }
    }

    private void reset() {
        this.pendingItem = null;
        this.tickTimer = 0;
        this.itemSlot = -1;
    }

    private boolean isGuiMoveEnabled() {
        GuiMove guiMove = Instance.get(GuiMove.class);
        return guiMove != null && guiMove.isEnabled();
    }
}

