package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Auto Swap", moduleDesc="Автоматичестки свапает предметы", moduleCategory=ModuleCategory.COMBAT)
public class AutoSwap
extends Module {
    private final ModeSetting mode = this.modeCreate();
    private final BindSetting swapKey = new BindSetting("Клавиша свапа", -1);
    private final ModeSetting firstItem = new ModeSetting("Свапать с", "Шар", "Гепл", "Щит", "Талисман", "Шар");
    private final ModeSetting secondItem = new ModeSetting("Свапать с", "Шар", "Гепл", "Щит", "Талисман", "Шар");
    private boolean swapped;

    @Subscribe
    private void onPlayerUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null) {
            return;
        }
        if (this.swapped) {
            this.swapped = false;
            boolean sameItem = this.firstItem.getValue().equals(this.secondItem.getValue());
            int slotFirstItem = this.findItemByName(this.firstItem.getValue(), sameItem);
            int slotSecondItem = this.findItemByName(this.secondItem.getValue(), sameItem);
            if (slotFirstItem == 40 && slotSecondItem == 40) {
                slotSecondItem = InventoryUtil.searchItemStack(item -> item.getItem() == Items.PLAYER_HEAD && item != this.mc.player.getOffHandStack());
            }
            if (slotFirstItem == -1 && slotSecondItem == -1) {
                return;
            }
            if (slotFirstItem == 40 || slotFirstItem == -1 && slotSecondItem != 40) {
                if (slotSecondItem >= 0 && slotSecondItem <= 8) {
                    int finalSlotSecondItem = slotSecondItem;
                    switch (this.mode.getValue()) {
                        case "Vanilla": {
                            this.mc.interactionManager.clickSlot(0, 45, finalSlotSecondItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
                            break;
                        }
                        case "Grim": {
                            InventoryUtil.swapWithBypassGrim(() -> this.mc.interactionManager.clickSlot(0, 45, finalSlotSecondItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                            break;
                        }
                        case "Polar": {
                            InventoryUtil.swapWithBypassPolar(() -> this.mc.interactionManager.clickSlot(0, 45, finalSlotSecondItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                        }
                    }
                } else if (slotSecondItem != -1) {
                    int finalSlotSecondItem = slotSecondItem;
                    switch (this.mode.getValue()) {
                        case "Vanilla": {
                            this.mc.interactionManager.clickSlot(0, finalSlotSecondItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
                            break;
                        }
                        case "Grim": {
                            InventoryUtil.swapWithBypassGrim(() -> this.mc.interactionManager.clickSlot(0, finalSlotSecondItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                            break;
                        }
                        case "Polar": {
                            InventoryUtil.swapWithBypassPolar(() -> this.mc.interactionManager.clickSlot(0, finalSlotSecondItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                        }
                    }
                }
            } else {
                if (slotFirstItem == -1) {
                    return;
                }
                if (slotFirstItem >= 0 && slotFirstItem <= 8) {
                    switch (this.mode.getValue()) {
                        case "Vanilla": {
                            this.mc.interactionManager.clickSlot(0, 45, slotFirstItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
                            break;
                        }
                        case "Grim": {
                            InventoryUtil.swapWithBypassGrim(() -> this.mc.interactionManager.clickSlot(0, 45, slotFirstItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                            break;
                        }
                        case "Polar": {
                            InventoryUtil.swapWithBypassPolar(() -> this.mc.interactionManager.clickSlot(0, 45, slotFirstItem, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                        }
                    }
                } else {
                    switch (this.mode.getValue()) {
                        case "Vanilla": {
                            this.mc.interactionManager.clickSlot(0, slotFirstItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
                            break;
                        }
                        case "Grim": {
                            InventoryUtil.swapWithBypassGrim(() -> this.mc.interactionManager.clickSlot(0, slotFirstItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                            break;
                        }
                        case "Polar": {
                            InventoryUtil.swapWithBypassPolar(() -> this.mc.interactionManager.clickSlot(0, slotFirstItem, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player));
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (e2.getAction() == 0) {
            return;
        }
        if (e2.getKey() == this.swapKey.getValue().intValue()) {
            this.swapped = true;
        }
    }

    private int findItemByName(String name, boolean ignoreOffhand) {
        switch (name) {
            case "Гепл": {
                if (!ignoreOffhand && this.mc.player.getOffHandStack().getItem() == Items.GOLDEN_APPLE) {
                    return 40;
                }
                return InventoryUtil.searchItem(Items.GOLDEN_APPLE);
            }
            case "Щит": {
                if (!ignoreOffhand && this.mc.player.getOffHandStack().getItem() == Items.SHIELD) {
                    return 40;
                }
                return InventoryUtil.searchItem(Items.SHIELD);
            }
            case "Талисман": {
                if (!ignoreOffhand && this.mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING && this.mc.player.getOffHandStack().get(DataComponentTypes.ATTRIBUTE_MODIFIERS) != null && !((AttributeModifiersComponent)this.mc.player.getOffHandStack().get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).comp_2393().isEmpty()) {
                    return 40;
                }
                return InventoryUtil.searchItemStack(item -> item.getItem() == Items.TOTEM_OF_UNDYING && item.get(DataComponentTypes.ATTRIBUTE_MODIFIERS) != null && !((AttributeModifiersComponent)item.get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).comp_2393().isEmpty());
            }
            case "Шар": {
                if (!ignoreOffhand && this.mc.player.getOffHandStack().getItem() == Items.PLAYER_HEAD && this.mc.player.getOffHandStack().get(DataComponentTypes.ATTRIBUTE_MODIFIERS) != null && !((AttributeModifiersComponent)this.mc.player.getOffHandStack().get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).comp_2393().isEmpty()) {
                    return 40;
                }
                return InventoryUtil.searchItemStack(item -> item.getItem() == Items.PLAYER_HEAD && item.get(DataComponentTypes.ATTRIBUTE_MODIFIERS) != null && !((AttributeModifiersComponent)item.get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).comp_2393().isEmpty());
            }
        }
        return -1;
    }
}

