package ua.babka.combat;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import ua.babka.Babka;
import ua.babka.event.EventAttack;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.impl.combat.PacketCriticals;
import ua.babka.module.impl.movement.NoJumpDelay;
import ua.babka.util.IMinecraft;
import ua.babka.util.math.StopWatch;

public class IdealHitUtils
implements IMinecraft {
    private static final int WATER_CRIT_INTENT_TICKS = 8;
    private static final int WATER_CRIT_CONTACT_TICKS = 10;
    private static final double WATER_CRIT_MIN_UPWARD_VELOCITY = 0.05;
    private static int lastWaterContactAge = Integer.MIN_VALUE;
    private static int lastWaterCritIntentAge = Integer.MIN_VALUE;
    private final StopWatch attackCooldown = new StopWatch();

    public IdealHitUtils() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    @Subscribe
    private void onAttack(EventAttack e2) {
    }

    public boolean cooldownIsReached(boolean toSprinting) {
        float progress = IdealHitUtils.mc.player.getAttackCooldownProgress(0.5f);
        float threshold = toSprinting ? 0.8f : 0.9f;
        return progress >= threshold;
    }

    public float getAICooldown() {
        if (IdealHitUtils.mc.player.getMainHandStack().getItem() == Items.AIR) {
            return 0.9f;
        }
        if (IdealHitUtils.mc.player.getMainHandStack().getItem() instanceof AxeItem || IdealHitUtils.mc.player.getMainHandStack().getItem() instanceof ShovelItem) {
            return 0.95f;
        }
        return 0.93f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean canAIFall() {
        BlockPos posWater = BlockPos.ofFloored((Position)IdealHitUtils.mc.player.getPos().add(0.0, (double)-0.4f, 0.0));
        if (IdealHitUtils.mc.world.getBlockState(posWater).isOf(Blocks.WATER)) {
            return true;
        }
        if (this.getBlock(0.0, 3.0, 0.0) == Blocks.AIR && this.getBlock(0.0, 2.0, 0.0) == Blocks.AIR) {
            if (this.getBlock(0.0, 1.0, 0.0) == Blocks.AIR) return true;
        }
        if (IdealHitUtils.mc.player.fallDistance < (this.getBlock(0.0, 2.0, 0.0) != Blocks.AIR ? 0.08f : 0.6f)) return true;
        if (!(IdealHitUtils.mc.player.fallDistance > 1.2f)) return false;
        return true;
    }

    public boolean canCritical() {
        return this.canCritical(null);
    }

    public boolean canCritical(LivingEntity target) {
        boolean smartCrit;
        boolean isCritPossible;
        this.updateWaterCritState();
        PacketCriticals packetCriticals = Babka.getInstance().getModuleStorage().get(PacketCriticals.class);
        boolean packetCrits = packetCriticals != null && packetCriticals.isEnabled();
        boolean hasSlowFalling = IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING);
        boolean inCobweb = IdealHitUtils.isInCobweb();
        if (packetCrits && inCobweb) {
            return true;
        }
        if (packetCrits && hasSlowFalling) {
            return IdealHitUtils.mc.player.getVelocity().y < 0.0 && IdealHitUtils.mc.player.fallDistance > 0.0f;
        }
        if (this.isTryingWaterCrit()) {
            return this.isWaterCritWindow();
        }
        boolean bl = isCritPossible = !IdealHitUtils.mc.player.isOnGround() && IdealHitUtils.mc.player.getVelocity().y < 0.0 && IdealHitUtils.mc.player.fallDistance > 0.0f;
        if (this.isNoJumpDelayCeilingCritIntent()) {
            return this.isNoJumpDelayCeilingCritWindow();
        }
        if (this.isNoJumpDelayJumpCritIntent()) {
            return this.isNoJumpDelayJumpCritWindow();
        }
        if (this.cannotPerformCrit()) {
            return true;
        }
        AttackAura aura = Babka.getInstance().getModuleStorage().get(AttackAura.class);
        boolean bl2 = smartCrit = aura != null && aura.smartCrits.getValue();
        if (smartCrit && IdealHitUtils.mc.player.isOnGround()) {
            return true;
        }
        return isCritPossible;
    }

    private boolean isNoJumpDelayCeilingCritIntent() {
        NoJumpDelay noJumpDelay = Babka.getInstance().getModuleStorage().get(NoJumpDelay.class);
        return noJumpDelay != null && noJumpDelay.isEnabled() && IdealHitUtils.mc.options != null && IdealHitUtils.mc.options.jumpKey.isPressed() && this.hasLowCeilingForJumpCrit();
    }

    private boolean isNoJumpDelayJumpCritIntent() {
        NoJumpDelay noJumpDelay = Babka.getInstance().getModuleStorage().get(NoJumpDelay.class);
        return noJumpDelay != null && noJumpDelay.isEnabled() && IdealHitUtils.mc.options != null && IdealHitUtils.mc.options.jumpKey.isPressed();
    }

    private boolean isNoJumpDelayCeilingCritWindow() {
        return IdealHitUtils.mc.player != null && !IdealHitUtils.mc.player.isOnGround() && IdealHitUtils.mc.player.fallDistance > 0.0f && IdealHitUtils.mc.player.getVelocity().y <= 0.01 && !IdealHitUtils.mc.player.isTouchingWater() && !IdealHitUtils.mc.player.isSubmergedInWater() && !IdealHitUtils.mc.player.isInLava() && !IdealHitUtils.mc.player.isClimbing() && !IdealHitUtils.mc.player.hasVehicle() && !IdealHitUtils.mc.player.getAbilities().flying;
    }

    public boolean isNoJumpDelayJumpCritWindow() {
        NoJumpDelay noJumpDelay = Babka.getInstance().getModuleStorage().get(NoJumpDelay.class);
        return IdealHitUtils.mc.player != null && IdealHitUtils.mc.world != null && noJumpDelay != null && noJumpDelay.isEnabled() && IdealHitUtils.mc.options != null && IdealHitUtils.mc.options.jumpKey.isPressed() && !IdealHitUtils.mc.player.isOnGround() && IdealHitUtils.mc.player.fallDistance > 0.0f && IdealHitUtils.mc.player.getVelocity().y < 0.0 && !IdealHitUtils.mc.player.isTouchingWater() && !IdealHitUtils.mc.player.isSubmergedInWater() && !IdealHitUtils.mc.player.isInLava() && !IdealHitUtils.mc.player.isClimbing() && !IdealHitUtils.mc.player.hasVehicle() && !IdealHitUtils.mc.player.getAbilities().flying && !IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.LEVITATION) && !IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING) && !IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.BLINDNESS) && !IdealHitUtils.mc.player.isGliding() && !IdealHitUtils.isInCobweb();
    }

    private boolean hasLowCeilingForJumpCrit() {
        if (IdealHitUtils.mc.player == null || IdealHitUtils.mc.world == null) {
            return false;
        }
        Box box = IdealHitUtils.mc.player.getBoundingBox().contract(0.03);
        Box headBox = new Box(box.minX, box.maxY, box.minZ, box.maxX, box.maxY + 0.32, box.maxZ);
        for (BlockPos pos : BlockPos.iterate((int)MathHelper.floor((double)headBox.minX), (int)MathHelper.floor((double)headBox.minY), (int)MathHelper.floor((double)headBox.minZ), (int)MathHelper.floor((double)headBox.maxX), (int)MathHelper.floor((double)headBox.maxY), (int)MathHelper.floor((double)headBox.maxZ))) {
            BlockState state = IdealHitUtils.mc.world.getBlockState(pos);
            if (state.isAir() || state.getCollisionShape((BlockView)IdealHitUtils.mc.world, pos).isEmpty()) continue;
            return true;
        }
        return false;
    }

    public static boolean canPacketCrit() {
        return IdealHitUtils.isInCobweb() || IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING);
    }

    private void updateWaterCritState() {
        if (IdealHitUtils.mc.player == null || IdealHitUtils.mc.world == null) {
            lastWaterContactAge = Integer.MIN_VALUE;
            lastWaterCritIntentAge = Integer.MIN_VALUE;
            return;
        }
        boolean nearWaterSurface = this.isNearWaterSurface();
        if (!nearWaterSurface) {
            return;
        }
        lastWaterContactAge = IdealHitUtils.mc.player.age;
        if (this.isWaterCritIntentState()) {
            lastWaterCritIntentAge = IdealHitUtils.mc.player.age;
        }
    }

    private boolean isWaterCritIntentState() {
        if (IdealHitUtils.mc.player == null || IdealHitUtils.mc.options == null) {
            return false;
        }
        return IdealHitUtils.mc.options.jumpKey.isPressed() && !IdealHitUtils.mc.player.isOnGround() && !IdealHitUtils.mc.player.isSubmergedInWater() && IdealHitUtils.mc.player.getVelocity().y > 0.05;
    }

    private boolean isTryingWaterCrit() {
        if (IdealHitUtils.mc.player == null || IdealHitUtils.mc.options == null || !IdealHitUtils.mc.options.jumpKey.isPressed()) {
            return false;
        }
        return IdealHitUtils.mc.player.age - lastWaterCritIntentAge <= 8 && IdealHitUtils.mc.player.age - lastWaterContactAge <= 10;
    }

    private boolean isWaterCritWindow() {
        return IdealHitUtils.mc.player != null && !IdealHitUtils.mc.player.isOnGround() && !IdealHitUtils.mc.player.isTouchingWater() && !IdealHitUtils.mc.player.isSubmergedInWater() && IdealHitUtils.mc.player.fallDistance > 0.0f && IdealHitUtils.mc.player.getVelocity().y < 0.0;
    }

    private boolean isNearWaterSurface() {
        if (IdealHitUtils.mc.player == null || IdealHitUtils.mc.world == null) {
            return false;
        }
        BlockPos below = BlockPos.ofFloored((Position)IdealHitUtils.mc.player.getPos().add(0.0, (double)-0.4f, 0.0));
        return IdealHitUtils.mc.player.isTouchingWater() || IdealHitUtils.mc.player.isSubmergedInWater() || IdealHitUtils.mc.world.getBlockState(below).isOf(Blocks.WATER);
    }

    private boolean cannotPerformCrit() {
        double effectiveJumpHeight = IdealHitUtils.mc.player.getStepHeight();
        Vec3d jumpVec = new Vec3d(0.0, effectiveJumpHeight, 0.0);
        Vec3d allowedMovement = IdealHitUtils.mc.player.adjustMovementForCollisions(jumpVec);
        boolean cobweb = IdealHitUtils.isInCobweb();
        BlockPos posWater = BlockPos.ofFloored((Position)IdealHitUtils.mc.player.getPos().add(0.0, (double)(IdealHitUtils.mc.player.getHeight() / 2.0f), 0.0));
        return IdealHitUtils.mc.player.isInLava() || IdealHitUtils.mc.player.isClimbing() || IdealHitUtils.mc.world.getBlockState(posWater).isOf(Blocks.WATER) || IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.LEVITATION) || IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING) || IdealHitUtils.mc.player.hasStatusEffect(StatusEffects.BLINDNESS) || cobweb || IdealHitUtils.mc.player.isGliding() || IdealHitUtils.mc.player.hasVehicle() || IdealHitUtils.mc.player.getAbilities().flying || IdealHitUtils.mc.player.isTouchingWater() || allowedMovement.y < (double)IdealHitUtils.mc.player.getStepHeight() - 0.5 && IdealHitUtils.mc.player.isOnGround();
    }

    public static boolean isInCobweb() {
        Box box = IdealHitUtils.mc.player.getBoundingBox();
        for (BlockPos pos : BlockPos.iterate((int)MathHelper.floor((double)box.minX), (int)MathHelper.floor((double)box.minY), (int)MathHelper.floor((double)box.minZ), (int)MathHelper.floor((double)box.maxX), (int)MathHelper.floor((double)box.maxY), (int)MathHelper.floor((double)box.maxZ))) {
            if (!IdealHitUtils.mc.world.getBlockState(pos).isOf(Blocks.COBWEB)) continue;
            return true;
        }
        return false;
    }

    public Block getBlock(double x2, double y2, double z2) {
        return IdealHitUtils.mc.world.getBlockState(IdealHitUtils.mc.player.getBlockPos().add((int)x2, (int)y2, (int)z2)).getBlock();
    }

    public boolean findFall(float fallDistance) {
        Vec3d rotationVec = IdealHitUtils.mc.player.getRotationVector();
        double tempVelocityX = IdealHitUtils.mc.player.getVelocity().x;
        double tempVelocityY = IdealHitUtils.mc.player.getVelocity().y;
        double tempVelocityZ = IdealHitUtils.mc.player.getVelocity().z;
        float n2 = MathHelper.cos((float)(IdealHitUtils.mc.player.getPitch() * ((float)Math.PI / 180)));
        n2 = (float)((double)(n2 * n2) * Math.min(rotationVec.length() / 0.4, 1.0));
        Vec3d vec3d = new Vec3d(tempVelocityX, tempVelocityY, tempVelocityZ).add(0.0, 0.08 * (-1.0 + (double)n2 * 0.75), 0.0);
        tempVelocityY = vec3d.y * (double)0.98f;
        return tempVelocityY < (double)fallDistance;
    }
}

