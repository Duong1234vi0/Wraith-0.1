package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.EventPopTotem;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.util.player.MoveUtil;

@ModuleInformation(moduleName="Auto Totem", moduleDesc="Автоматичестки берет тотем", moduleCategory=ModuleCategory.COMBAT)
public class AutoTotem
extends Module {
    private static final int SWAP_COOLDOWN_TICKS = 5;
    private final SliderSetting health = new SliderSetting("Уровень здоровья", 4.0, 1.0, 20.0, 0.5);
    private final SliderSetting elytraHealth = new SliderSetting("Здоровье на элитре", 8.0, 1.0, 20.0, 0.5);
    private final BooleanSetting swapBack = new BooleanSetting("Возвращать предмет", true);
    private final BooleanSetting noBallSwitch = new BooleanSetting("Игнорировать проверки, если в руке шар", false);
    private final BooleanSetting saveEnchanted = new BooleanSetting("Сохранять зачарованный", true);
    private final BooleanSetting ignoreDragonidTotem = new BooleanSetting("Не брать тотем Драконида", true);
    private final BooleanSetting stop = new BooleanSetting("Синхронизировать с GuiMove", false);
    private final ModeListSetting mode = new ModeListSetting("Проверки на", new BooleanSetting("Золотые сердца", false), new BooleanSetting("Кристаллы", false), new BooleanSetting("Падение", false), new BooleanSetting("Динамит", false), new BooleanSetting("Трезубец", false), new BooleanSetting("Нет брони", false));
    private final SliderSetting distancecrystall = new SliderSetting("Дистанция проверки на кристалл", 6.0, 1.0, 6.0, 1.0).setVisible(() -> this.mode.isEnabled("Кристаллы"));
    private final SliderSetting noArmorHealth = new SliderSetting("Здоровье без брони", 12.0, 1.0, 20.0, 0.5).setVisible(() -> this.mode.isEnabled("Нет брони"));
    private int nonEnchantedTotems;
    private int oldItem = -1;
    private int ticks;
    private ItemStack oldOffhandStack = ItemStack.EMPTY;
    private boolean totemLocked;
    private int slotToSwap = -1;

    @Subscribe
    private void totemSwap(EventPlayerUpdate e2) {
        if (this.slotToSwap != -1) {
            boolean swappingBack;
            int s2 = this.slotToSwap;
            this.slotToSwap = -1;
            if (!this.isValidSwapSlot(s2)) {
                return;
            }
            ItemStack fromStack = this.mc.player.currentScreenHandler.getSlot(s2).getStack();
            boolean swappingTotem = fromStack.isOf(Items.TOTEM_OF_UNDYING);
            boolean bl = swappingBack = this.oldItem != -1 && this.isSameReturnItem(fromStack, this.oldOffhandStack);
            if (this.oldItem != -1 && this.oldItem == s2) {
                this.oldItem = -1;
                this.oldOffhandStack = ItemStack.EMPTY;
                this.totemLocked = false;
            } else if (!this.mc.player.getOffHandStack().isEmpty() && this.oldItem == -1) {
                this.oldItem = s2;
                this.oldOffhandStack = this.mc.player.getOffHandStack().copy();
            }
            this.mc.interactionManager.clickSlot(this.mc.player.currentScreenHandler.syncId, s2, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
            if (swappingTotem) {
                this.totemLocked = true;
            } else if (swappingBack) {
                this.totemLocked = false;
            }
            this.ticks = 0;
        }
    }

    @Subscribe
    private void onUpdate(EventPlayerUpdate event) {
        int returnSlot;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        ++this.ticks;
        Slot slot = this.findTotemSlotAndUpdateCount();
        if (this.isTotemLockActive() && !this.hasTotemInHand() && slot != null && this.ticks >= 5 && !this.isSwapBlockedByBall()) {
            this.doSwap(slot.id);
            return;
        }
        if (this.shouldReturnSavedItem(slot) && (returnSlot = this.findReturnSlot()) != -1) {
            this.doSwap(returnSlot);
            return;
        }
        if (this.ticks >= 5) {
            if (!this.isSwapBlockedByBall() && !this.hasTotemInHand()) {
                List nearbyTridents;
                boolean isAnyTridentMoving;
                if (this.mode.isEnabled("Кристаллы") && !this.mc.world.getEntitiesByClass(EndCrystalEntity.class, this.mc.player.getBoundingBox().expand(this.distancecrystall.getValue()), entity -> true).isEmpty() && slot != null) {
                    this.doSwap(slot.id);
                    return;
                }
                if (this.mode.isEnabled("Динамит")) {
                    if (!this.mc.world.getEntitiesByClass(TntEntity.class, this.mc.player.getBoundingBox().expand(8.0), entity -> true).isEmpty() && slot != null) {
                        this.doSwap(slot.id);
                        return;
                    }
                    if (!this.mc.world.getEntitiesByClass(TntMinecartEntity.class, this.mc.player.getBoundingBox().expand(8.0), entity -> true).isEmpty() && slot != null) {
                        this.doSwap(slot.id);
                        return;
                    }
                }
                if (this.mode.isEnabled("Трезубец") && (isAnyTridentMoving = (nearbyTridents = this.mc.world.getEntitiesByClass(TridentEntity.class, this.mc.player.getBoundingBox().expand(10.0), entity -> true)).stream().anyMatch(trident -> trident.getX() != trident.prevX || trident.getY() != trident.prevY || trident.getZ() != trident.prevZ)) && slot != null) {
                    this.doSwap(slot.id);
                    return;
                }
            }
            if (this.needTotem()) {
                if (slot != null && !this.hasTotemInHand()) {
                    this.doSwap(slot.id);
                }
            } else if (this.canSwapBack()) {
                List nearbyTridents;
                boolean isAnyTridentMoving;
                returnSlot = this.findReturnSlot();
                if (returnSlot == -1) {
                    this.oldItem = -1;
                    this.oldOffhandStack = ItemStack.EMPTY;
                    this.slotToSwap = -1;
                    return;
                }
                if (this.mode.isEnabled("Кристаллы") && !this.mc.world.getEntitiesByClass(EndCrystalEntity.class, this.mc.player.getBoundingBox().expand(this.distancecrystall.getValue()), entity -> true).isEmpty()) {
                    this.slotToSwap = -1;
                    return;
                }
                if (!(!this.mode.isEnabled("Динамит") || this.mc.world.getEntitiesByClass(TntMinecartEntity.class, this.mc.player.getBoundingBox().expand(8.0), entity -> true).isEmpty() && this.mc.world.getEntitiesByClass(TntEntity.class, this.mc.player.getBoundingBox().expand(8.0), entity -> true).isEmpty())) {
                    this.slotToSwap = -1;
                    return;
                }
                if (this.mode.isEnabled("Трезубец") && (isAnyTridentMoving = (nearbyTridents = this.mc.world.getEntitiesByClass(TridentEntity.class, this.mc.player.getBoundingBox().expand(10.0), entity -> true)).stream().anyMatch(trident -> trident.getX() != trident.prevX || trident.getY() != trident.prevY || trident.getZ() != trident.prevZ))) {
                    this.slotToSwap = -1;
                    return;
                }
                this.doSwap(returnSlot);
            }
        }
    }

    @Subscribe
    private void onPacket(EventPacket event) {
        if (event.getType() == EventPacket.Type.SEND || this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (!(packet instanceof EntitySpawnS2CPacket)) {
            return;
        }
        EntitySpawnS2CPacket packet2 = (EntitySpawnS2CPacket)packet;
        if (packet2.getEntityType() != EntityType.END_CRYSTAL || !this.mode.isEnabled("Кристаллы") || this.isSwapBlockedByBall()) {
            return;
        }
        EndCrystalEntity crystal = new EndCrystalEntity((World)this.mc.world, packet2.getX(), packet2.getY(), packet2.getZ());
        if (this.mc.player.squaredDistanceTo((Entity)crystal) > this.distancecrystall.getValue() * this.distancecrystall.getValue() || this.hasTotemInHand()) {
            return;
        }
        Slot crystalSlot = this.findTotemSlotAndUpdateCount();
        if (crystalSlot == null) {
            return;
        }
        this.doSwap(crystalSlot.id);
    }

    @Subscribe
    private void onPopTotem(EventPopTotem event) {
        if (this.mc.player == null || event.getPlayer() != this.mc.player) {
            return;
        }
        this.ticks = 5;
        this.totemLocked = true;
        Slot slot = this.findTotemSlotAndUpdateCount();
        if (slot != null) {
            this.doSwap(slot.id);
        }
    }

    private boolean hasTotemInHand() {
        for (Hand hand : Hand.values()) {
            ItemStack stack = this.mc.player.getStackInHand(hand);
            if (!this.isUsableTotem(stack)) continue;
            return true;
        }
        return false;
    }

    private boolean isUsableTotem(ItemStack stack) {
        if (stack.isEmpty() || !stack.isOf(Items.TOTEM_OF_UNDYING) || this.isDragonidTotem(stack)) {
            return false;
        }
        return !this.saveEnchanted.getValue() || !stack.hasEnchantments() || this.nonEnchantedTotems <= 0;
    }

    private boolean isDragonidTotem(ItemStack stack) {
        if (!this.ignoreDragonidTotem.getValue() || stack.isEmpty() || !stack.isOf(Items.TOTEM_OF_UNDYING)) {
            return false;
        }
        return stack.getName().getString().contains("Тотем Драконида");
    }

    private boolean needTotem() {
        boolean blockByBall = this.isSwapBlockedByBall();
        if (!blockByBall && this.mode.isEnabled("Падение") && !this.mc.player.isTouchingWater() && !this.mc.player.isGliding() && this.mc.player.fallDistance > 10.0f) {
            return true;
        }
        if (this.mode.isEnabled("Нет брони") && this.hasMissingArmor() && this.getEffectiveHealth() <= (float)this.noArmorHealth.getValue()) {
            return true;
        }
        return this.getEffectiveHealth() <= this.getTotemHealthThreshold();
    }

    private boolean hasMissingArmor() {
        return this.mc.player.getEquippedStack(EquipmentSlot.FEET).isEmpty() || this.mc.player.getEquippedStack(EquipmentSlot.LEGS).isEmpty() || this.mc.player.getEquippedStack(EquipmentSlot.CHEST).isEmpty() || this.mc.player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
    }

    private boolean shouldReturnSavedItem(Slot totemSlot) {
        if (!this.swapBack.getValue() || this.oldItem == -1) {
            return false;
        }
        if (this.isSameReturnItem(this.mc.player.getOffHandStack(), this.oldOffhandStack)) {
            this.oldItem = -1;
            this.oldOffhandStack = ItemStack.EMPTY;
            this.totemLocked = false;
            return false;
        }
        if (this.isTotemLockActive()) {
            return false;
        }
        return totemSlot == null && !this.isUsableTotem(this.mc.player.getOffHandStack());
    }

    private boolean canSwapBack() {
        if (!this.totemLocked || this.oldItem == -1 || !this.swapBack.getValue()) {
            return false;
        }
        if (this.isCriticalHalfHeart()) {
            return false;
        }
        return this.getEffectiveHealth() >= this.getSwapBackHealthThreshold();
    }

    private boolean isTotemLockActive() {
        return this.totemLocked && this.getEffectiveHealth() < this.getSwapBackHealthThreshold();
    }

    private float getEffectiveHealth() {
        float currentHealth = this.mc.player.getHealth();
        if (this.mode.isEnabled("Золотые сердца") && !this.mc.player.isGliding() && this.mc.player.hasStatusEffect(StatusEffects.ABSORPTION)) {
            currentHealth += this.mc.player.getAbsorptionAmount();
        }
        return currentHealth;
    }

    private float getTotemHealthThreshold() {
        return this.mc.player.isGliding() ? (float)this.elytraHealth.getValue() : (float)this.health.getValue();
    }

    private float getSwapBackHealthThreshold() {
        return this.getTotemHealthThreshold() + 2.0f;
    }

    private boolean isCriticalHalfHeart() {
        return this.mc.player.getHealth() <= 1.0f;
    }

    private int findReturnSlot() {
        if (this.isValidSwapSlot(this.oldItem) && this.isSameReturnItem(this.mc.player.currentScreenHandler.getSlot(this.oldItem).getStack(), this.oldOffhandStack)) {
            return this.oldItem;
        }
        if (this.oldOffhandStack.isEmpty()) {
            return this.isValidSwapSlot(this.oldItem) ? this.oldItem : -1;
        }
        for (Slot slot : this.mc.player.currentScreenHandler.slots) {
            if (slot.id == 45 || !this.isSameReturnItem(slot.getStack(), this.oldOffhandStack)) continue;
            this.oldItem = slot.id;
            return slot.id;
        }
        return -1;
    }

    private boolean isSameReturnItem(ItemStack current, ItemStack expected) {
        if (current.isEmpty() || expected.isEmpty()) {
            return false;
        }
        return current.getItem() == expected.getItem() && ItemStack.areItemsAndComponentsEqual((ItemStack)current, (ItemStack)expected);
    }

    private Slot findTotemSlotAndUpdateCount() {
        this.nonEnchantedTotems = (int)this.mc.player.currentScreenHandler.slots.stream().filter(s2 -> s2.getStack().isOf(Items.TOTEM_OF_UNDYING) && !this.isDragonidTotem(s2.getStack()) && !s2.getStack().hasEnchantments()).count();
        return this.mc.player.currentScreenHandler.slots.stream().filter(s2 -> s2.getStack().isOf(Items.TOTEM_OF_UNDYING) && !this.isDragonidTotem(s2.getStack()) && (!this.saveEnchanted.getValue() || !s2.getStack().hasEnchantments() || this.nonEnchantedTotems <= 0)).findFirst().orElse(null);
    }

    private void doSwap(int fromSlot) {
        boolean swappingSavedItemBack;
        if (!this.isValidSwapSlot(fromSlot)) {
            this.slotToSwap = -1;
            return;
        }
        ItemStack fromStack = this.mc.player.currentScreenHandler.getSlot(fromSlot).getStack();
        boolean swappingTotemToOffhand = fromStack.isOf(Items.TOTEM_OF_UNDYING);
        boolean bl = swappingSavedItemBack = this.oldItem != -1 && this.isSameReturnItem(fromStack, this.oldOffhandStack);
        if (!MoveUtil.hasPlayerMovement() || !this.stop.getValue() && !this.mc.player.isSprinting()) {
            if (this.oldItem != -1 && this.oldItem == fromSlot) {
                this.oldItem = -1;
                this.oldOffhandStack = ItemStack.EMPTY;
                this.totemLocked = false;
            } else if (!this.mc.player.getOffHandStack().isEmpty() && this.oldItem == -1) {
                this.oldItem = fromSlot;
                this.oldOffhandStack = this.mc.player.getOffHandStack().copy();
            }
            this.mc.interactionManager.clickSlot(this.mc.player.currentScreenHandler.syncId, fromSlot, 40, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
            if (swappingTotemToOffhand) {
                this.totemLocked = true;
            } else if (swappingSavedItemBack) {
                this.totemLocked = false;
            }
            this.slotToSwap = -1;
            this.ticks = 0;
        } else {
            this.slotToSwap = fromSlot;
        }
    }

    private boolean isValidSwapSlot(int slot) {
        return slot >= 0 && slot < this.mc.player.currentScreenHandler.slots.size();
    }

    private boolean isSwapBlockedByBall() {
        return !(!this.noBallSwitch.getValue() || !this.mc.player.getOffHandStack().isOf(Items.PLAYER_HEAD) || this.mode.isEnabled("Падение") && this.mc.player.fallDistance > 5.0f);
    }

    @Override
    public void onDisable() {
        this.oldItem = -1;
        this.oldOffhandStack = ItemStack.EMPTY;
        this.totemLocked = false;
        this.ticks = 0;
        this.slotToSwap = -1;
        super.onDisable();
    }
}

