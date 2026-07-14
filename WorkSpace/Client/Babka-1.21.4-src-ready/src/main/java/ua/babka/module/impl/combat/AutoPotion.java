package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import ua.babka.event.EventPlayerSync;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.util.math.StopWatch;

@ModuleInformation(moduleName="Auto Potion", moduleDesc="Автоматически кидает выбранные зельки", moduleCategory=ModuleCategory.COMBAT)
public class AutoPotion
extends Module {
    private final ModeListSetting throwSettings = new ModeListSetting("Кидать", new BooleanSetting("Зелье силы", true), new BooleanSetting("Зелье скорости", true), new BooleanSetting("Зелье огнестойкости", true));
    private final BooleanSetting autoDisable = new BooleanSetting("Выключать после использования", false);
    private final StopWatch timer = new StopWatch();
    private float savedPitch;
    private boolean throwing = false;
    private static AutoPotion staticInstance;

    @Subscribe
    public void onSync(EventPlayerSync e2) {
        if (this.mc.player == null) {
            return;
        }
        if (!this.canThrow()) {
            this.throwing = false;
            return;
        }
        this.savedPitch = this.mc.player.getPitch();
        this.mc.player.setPitch(90.0f);
        this.throwing = true;
    }

    @Subscribe
    public void onUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.throwing) {
            return;
        }
        if (!this.timer.isReached(500L)) {
            return;
        }
        boolean threwAny = false;
        for (PotionType type : PotionType.values()) {
            int slot;
            if (!type.isSettingEnabled() || this.mc.player.hasStatusEffect(type.getEffect()) || (slot = this.findPotionSlot(type.getEffect())) == -1) continue;
            this.throwPotion(slot);
            threwAny = true;
        }
        this.timer.reset();
        if (threwAny && this.autoDisable.getValue()) {
            this.setEnabled(false);
        }
    }

    private void throwPotion(int slot) {
        int previousSlot = this.mc.player.getInventory().selectedSlot;
        if (slot < 9) {
            this.mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(slot));
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
            this.mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
        } else {
            this.mc.interactionManager.clickSlot(0, slot, previousSlot, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
            this.mc.getNetworkHandler().sendPacket((Packet)new UpdateSelectedSlotC2SPacket(previousSlot));
            this.mc.interactionManager.interactItem((PlayerEntity)this.mc.player, Hand.MAIN_HAND);
            this.mc.interactionManager.clickSlot(0, slot, previousSlot, SlotActionType.SWAP, (PlayerEntity)this.mc.player);
        }
    }

    private int findPotionSlot(RegistryEntry<StatusEffect> effect) {
        for (int i2 = 0; i2 < 45; ++i2) {
            PotionContentsComponent contents;
            ItemStack stack = this.mc.player.getInventory().getStack(i2);
            if (stack.getItem() != Items.SPLASH_POTION || (contents = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS)) == null) continue;
            for (StatusEffectInstance effectInstance : contents.getEffects()) {
                if (!effectInstance.getEffectType().equals(effect)) continue;
                return i2;
            }
        }
        return -1;
    }

    private boolean canThrow() {
        boolean onGround;
        if (this.mc.player == null || this.mc.world == null) {
            return false;
        }
        boolean bl = onGround = this.mc.player.isOnGround() || this.mc.world.getBlockState(BlockPos.ofFloored((double)this.mc.player.getX(), (double)(this.mc.player.getY() - 0.3), (double)this.mc.player.getZ())).isSolid();
        if (!onGround) {
            return false;
        }
        if (this.mc.player.isClimbing()) {
            return false;
        }
        if (this.mc.player.hasVehicle()) {
            return false;
        }
        if (this.mc.player.getAbilities().flying) {
            return false;
        }
        if (this.mc.player.isTouchingWater() || this.mc.player.isInLava()) {
            return false;
        }
        for (PotionType type : PotionType.values()) {
            if (!type.isSettingEnabled() || this.mc.player.hasStatusEffect(type.getEffect()) || this.findPotionSlot(type.getEffect()) == -1) continue;
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.throwing = false;
        super.onDisable();
    }

    public AutoPotion() {
        staticInstance = this;
    }

    public static AutoPotion getStaticInstance() {
        return staticInstance;
    }

    private static enum PotionType {
        STRENGTH((RegistryEntry<StatusEffect>)StatusEffects.STRENGTH, "Зелье силы"),
        SPEED((RegistryEntry<StatusEffect>)StatusEffects.SPEED, "Зелье скорости"),
        FIRE_RESISTANCE((RegistryEntry<StatusEffect>)StatusEffects.FIRE_RESISTANCE, "Зелье огнестойкости");

        private final RegistryEntry<StatusEffect> effect;
        private final String settingName;

        public boolean isSettingEnabled() {
            return AutoPotion.getStaticInstance().throwSettings.isEnabled(this.settingName);
        }

        public RegistryEntry<StatusEffect> getEffect() {
            return this.effect;
        }

        public String getSettingName() {
            return this.settingName;
        }

        private PotionType(RegistryEntry<StatusEffect> effect, String settingName) {
            this.effect = effect;
            this.settingName = settingName;
        }
    }
}

