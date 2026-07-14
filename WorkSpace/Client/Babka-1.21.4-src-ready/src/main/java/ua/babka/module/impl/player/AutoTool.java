package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Auto Tool", moduleDesc="Выбирает лучший инструмент для добычи блоков", moduleCategory=ModuleCategory.PLAYER)
public class AutoTool
extends Module {
    private final ModeSetting mode = new ModeSetting("Мод", "Vanilla", "Vanilla", "Grim");
    private int itemIndex = -1;
    private int oldSlot = -1;
    private boolean status;

    @Subscribe
    private void onTick(EventPlayerUpdate ignored) {
        if (this.mc.player == null || this.mc.player.isCreative()) {
            this.itemIndex = -1;
            return;
        }
        if (this.mc.player.isUsingItem()) {
            return;
        }
        if (this.isMousePressed()) {
            if (this.oldSlot == -1) {
                this.itemIndex = this.findBestToolSlot();
                if (this.itemIndex != -1) {
                    if (this.itemIndex >= 0 && this.itemIndex <= 8) {
                        this.oldSlot = this.mc.player.getInventory().selectedSlot;
                        this.mc.player.getInventory().selectedSlot = this.itemIndex;
                        this.mc.interactionManager.syncSelectedSlot();
                        this.status = true;
                    } else {
                        switch (this.mode.getValue()) {
                            case "Vanilla": {
                                this.swap();
                                break;
                            }
                            case "Grim": {
                                InventoryUtil.swapWithBypassGrim(this::swap);
                                break;
                            }
                            case "ReallyWorld": {
                                if (this.mc.player.isOnGround()) {
                                    InventoryUtil.swapWithBypassPolar(this::swap);
                                    break;
                                }
                                InventoryUtil.swapWithBypassGrim(this::swap);
                            }
                        }
                        this.oldSlot = this.itemIndex;
                    }
                }
            }
        } else if (this.oldSlot != -1) {
            if (this.oldSlot >= 0 && this.oldSlot <= 8) {
                this.mc.player.getInventory().selectedSlot = this.oldSlot;
                this.mc.interactionManager.syncSelectedSlot();
                this.oldSlot = -1;
                this.status = false;
            } else {
                switch (this.mode.getValue()) {
                    case "Vanilla": {
                        this.swapBack();
                        break;
                    }
                    case "Grim": {
                        InventoryUtil.swapWithBypassGrim(this::swapBack);
                        break;
                    }
                    case "ReallyWorld": {
                        if (this.mc.player.isOnGround()) {
                            InventoryUtil.swapWithBypassPolar(this::swapBack);
                            break;
                        }
                        InventoryUtil.swapWithBypassGrim(this::swapBack);
                    }
                }
            }
        }
    }

    private void swap() {
        if (this.itemIndex == -1) {
            return;
        }
        this.status = true;
        this.mc.interactionManager.clickSlot(0, this.itemIndex, this.mc.player.getInventory().selectedSlot, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
    }

    private void swapBack() {
        if (this.oldSlot == -1 || !this.status) {
            return;
        }
        this.mc.interactionManager.clickSlot(0, this.oldSlot, this.mc.player.getInventory().selectedSlot, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
        this.oldSlot = -1;
        this.status = false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.status = false;
        this.itemIndex = -1;
        this.oldSlot = -1;
    }

    private int findBestToolSlot() {
        HitResult hitResult = this.mc.crosshairTarget;
        if (hitResult instanceof BlockHitResult) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = this.mc.world.getBlockState(pos);
            int bestSlot = -1;
            float bestSpeed = 1.0f;
            for (int slot = 0; slot < 36; ++slot) {
                ItemStack stack = this.mc.player.getInventory().getStack(slot);
                float speed = stack.getMiningSpeedMultiplier(state);
                if (!(speed > bestSpeed)) continue;
                bestSpeed = speed;
                bestSlot = slot;
            }
            return bestSlot;
        }
        return -1;
    }

    private boolean isMousePressed() {
        return this.mc.crosshairTarget != null && this.mc.options.attackKey.isPressed();
    }
}

