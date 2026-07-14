package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventAttack;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.StringSetting;

@ModuleInformation(moduleName="Fake Player", moduleDesc="Создаёт фальшивого игрока", moduleCategory=ModuleCategory.MISC)
public class FakePlayer
extends Module {
    private static final String[] NAMES = new String[]{"xX_NoobSlayer_Xx", "TechnoViking", "DreamWasTaken", "Notch", "Herobrine", "CreeperKing", "Steve_42", "Alex_1337", "DarkLord", "PvPMaster", "NoobSlayer", "MinerDude", "CraftGod", "LMAO_Pro", "GamerGirl", "xDragonSlayer", "WASDAWSDWASDAWSDWASDWAD", "RandomGuy", "TrollFace", "PingMeister"};
    private static final Random RANDOM = new Random();
    public final StringSetting customName = new StringSetting("Кастомный ник", "");
    private OtherClientPlayerEntity fakePlayer;
    private float fakeHealth = 20.0f;

    @Override
    public void onEnable() {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        String name = this.customName.getValue().isEmpty() ? NAMES[RANDOM.nextInt(NAMES.length)] : this.customName.getValue();
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        this.fakePlayer = new OtherClientPlayerEntity(this.mc.world, profile);
        this.fakePlayer.setHealth(this.fakeHealth);
        this.fakePlayer.copyFrom((Entity)this.mc.player);
        this.fakePlayer.setPos(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
        this.mc.world.addEntity((Entity)this.fakePlayer);
    }

    @Override
    public void onDisable() {
        if (this.mc.world != null && this.fakePlayer != null) {
            this.mc.world.removeEntity(this.fakePlayer.getId(), Entity.RemovalReason.DISCARDED);
            this.fakePlayer = null;
        }
    }

    @Subscribe
    private void onAttack(EventAttack e2) {
        if (this.fakePlayer == null || e2.getEntity() != this.fakePlayer) {
            return;
        }
        e2.setCancelled(true);
        this.fakeHealth -= 2.0f;
        if (this.fakeHealth <= 0.0f) {
            this.fakeHealth = 20.0f;
            this.fakePlayer.setHealth(20.0f);
            this.fakePlayer.hurtTime = 10;
            this.fakePlayer.maxHurtTime = 10;
            this.fakePlayer.clearStatusEffects();
            this.fakePlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            this.fakePlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 4));
            this.fakePlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
            this.mc.world.playSound(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(), SoundEvents.ITEM_TOTEM_USE, this.fakePlayer.getSoundCategory(), 1.0f, 1.0f, false);
            return;
        }
        this.fakePlayer.setHealth(this.fakeHealth);
        this.fakePlayer.hurtTime = 10;
        this.fakePlayer.maxHurtTime = 10;
        this.fakePlayer.timeUntilRegen = 20;
        Vec3d knockback = this.mc.player.getPos().subtract(this.fakePlayer.getPos()).normalize().multiply(1.5);
        this.fakePlayer.addVelocity(knockback.x, 0.3, knockback.z);
        this.mc.world.playSound(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(), SoundEvents.ENTITY_PLAYER_HURT, this.fakePlayer.getSoundCategory(), 1.0f, 1.0f, false);
        this.mc.player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
    }
}

