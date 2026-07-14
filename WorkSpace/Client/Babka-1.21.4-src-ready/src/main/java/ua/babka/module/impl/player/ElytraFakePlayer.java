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
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.StringSetting;

@ModuleInformation(moduleName="Elytra Fake Player", moduleDesc="Фальшивый игрок на элитре", moduleCategory=ModuleCategory.MISC)
public class ElytraFakePlayer
extends Module {
    private static final String[] NAMES = new String[]{"xX_NoobSlayer_Xx", "TechnoViking", "DreamWasTaken", "Notch", "Herobrine", "CreeperKing", "Steve_42", "Alex_1337", "DarkLord", "PvPMaster", "NoobSlayer", "MinerDude", "CraftGod", "LMAO_Pro", "GamerGirl", "xDragonSlayer", "TotemUser", "RandomGuy", "TrollFace", "PingMeister"};
    private static final Random RANDOM = new Random();
    public final StringSetting customName = new StringSetting("Кастомный ник", "");
    private OtherClientPlayerEntity fakePlayer;
    private float fakeHealth = 20.0f;
    private int fireworkTicks;
    private int boostCooldown;

    @Override
    public void onEnable() {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        this.spawn();
    }

    @Override
    public void onDisable() {
        if (this.mc.world != null && this.fakePlayer != null) {
            this.mc.world.removeEntity(this.fakePlayer.getId(), Entity.RemovalReason.DISCARDED);
            this.fakePlayer = null;
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        if (this.fakePlayer == null) {
            return;
        }
        this.fakePlayer.setYaw(this.mc.player.getYaw());
        this.fakePlayer.setHeadYaw(this.mc.player.getYaw());
        this.fakePlayer.startGliding();
        this.fakePlayer.setSprinting(true);
        this.fakePlayer.setNoGravity(true);
        if (this.fireworkTicks > 0) {
            --this.fireworkTicks;
            Vec3d look = Vec3d.fromPolar((float)0.0f, (float)this.fakePlayer.getYaw()).normalize();
            Vec3d vel = this.fakePlayer.getVelocity();
            this.fakePlayer.setVelocity(vel.x + look.x * 0.6, 0.4, vel.z + look.z * 0.6);
        } else {
            Vec3d vel = this.fakePlayer.getVelocity();
            this.fakePlayer.setVelocity(vel.x * 0.995, 0.05, vel.z * 0.995);
            --this.boostCooldown;
            if (this.boostCooldown <= 0) {
                this.boostCooldown = 60 + (int)(Math.random() * 20.0);
                this.fireworkTicks = 30 + (int)(Math.random() * 10.0);
                this.fakePlayer.getWorld().playSound(this.fakePlayer.getX(), this.fakePlayer.getY(), this.fakePlayer.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, this.fakePlayer.getSoundCategory(), 1.0f, 1.0f, false);
            }
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

    private void spawn() {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (this.fakePlayer != null) {
            this.mc.world.removeEntity(this.fakePlayer.getId(), Entity.RemovalReason.DISCARDED);
        }
        String name = this.customName.getValue().isEmpty() ? NAMES[RANDOM.nextInt(NAMES.length)] : this.customName.getValue();
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        this.fakePlayer = new OtherClientPlayerEntity(this.mc.world, profile);
        this.fakePlayer.setHealth(this.fakeHealth);
        this.fakePlayer.copyFrom((Entity)this.mc.player);
        this.fakePlayer.setYaw(this.mc.player.getYaw());
        this.fakePlayer.setHeadYaw(this.mc.player.getYaw());
        this.fakePlayer.setPitch(this.mc.player.getPitch());
        Vec3d look = Vec3d.fromPolar((float)0.0f, (float)this.mc.player.getYaw()).normalize();
        this.fakePlayer.refreshPositionAndAngles(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(), this.mc.player.getYaw(), this.mc.player.getPitch());
        this.fakePlayer.setVelocity(look.x * 1.5, 0.7, look.z * 1.5);
        this.fakePlayer.startGliding();
        this.fakePlayer.setSprinting(true);
        this.fakePlayer.setNoGravity(true);
        this.mc.world.addEntity((Entity)this.fakePlayer);
        this.mc.world.playSound(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, this.fakePlayer.getSoundCategory(), 1.0f, 1.0f, false);
        this.mc.world.playSound(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, this.fakePlayer.getSoundCategory(), 1.0f, 1.0f, false);
        this.fireworkTicks = 40;
        this.boostCooldown = 80;
    }
}

