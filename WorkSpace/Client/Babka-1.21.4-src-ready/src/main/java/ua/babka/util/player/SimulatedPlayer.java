package ua.babka.util.player;

import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Property;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import ua.babka.util.IMinecraft;
import ua.babka.util.player.MoveUtil;

public class SimulatedPlayer
implements IMinecraft {
    public final PlayerEntity player;
    public final SimulatedPlayerInput input;
    public Vec3d pos;
    public Vec3d velocity;
    public Box boundingBox;
    public float yaw;
    public float pitch;
    public boolean sprinting;
    public float fallDistance;
    public int jumpingCooldown;
    public boolean isJumping;
    public boolean isFallFlying;
    public boolean onGround;
    public boolean horizontalCollision;
    public boolean verticalCollision;
    public boolean touchingWater;
    public boolean isSwimming;
    public boolean submergedInWater;
    private final Object2DoubleMap<TagKey<Fluid>> fluidHeight;
    private final HashSet<TagKey<Fluid>> submergedFluidTag;
    private int simulatedTicks = 0;
    private boolean clipLedged = false;
    private static final double STEP_HEIGHT = 0.5;

    public SimulatedPlayer(PlayerEntity player, SimulatedPlayerInput input, Vec3d pos, Vec3d velocity, Box boundingBox, float yaw, float pitch, boolean sprinting, float fallDistance, int jumpingCooldown, boolean isJumping, boolean isFallFlying, boolean onGround, boolean horizontalCollision, boolean verticalCollision, boolean touchingWater, boolean isSwimming, boolean submergedInWater, Object2DoubleMap<TagKey<Fluid>> fluidHeight, HashSet<TagKey<Fluid>> submergedFluidTag) {
        this.player = player;
        this.input = input;
        this.pos = pos;
        this.velocity = velocity;
        this.boundingBox = boundingBox;
        this.yaw = yaw;
        this.pitch = pitch;
        this.sprinting = sprinting;
        this.fallDistance = fallDistance;
        this.jumpingCooldown = jumpingCooldown;
        this.isJumping = isJumping;
        this.isFallFlying = isFallFlying;
        this.onGround = onGround;
        this.horizontalCollision = horizontalCollision;
        this.verticalCollision = verticalCollision;
        this.touchingWater = touchingWater;
        this.isSwimming = isSwimming;
        this.submergedInWater = submergedInWater;
        this.fluidHeight = fluidHeight;
        this.submergedFluidTag = submergedFluidTag;
    }

    public static SimulatedPlayer simulateLocalPlayer(int ticks) {
        SimulatedPlayer simulatedPlayer = SimulatedPlayer.fromClientPlayer(SimulatedPlayerInput.fromClientPlayer(SimulatedPlayer.mc.player.input.playerInput));
        for (int i2 = 0; i2 < ticks; ++i2) {
            simulatedPlayer.tick();
        }
        return simulatedPlayer;
    }

    public static SimulatedPlayer simulateOtherPlayer(PlayerEntity player, int ticks) {
        SimulatedPlayer simulatedPlayer = SimulatedPlayer.fromOtherPlayer(player, SimulatedPlayerInput.guessInput(player));
        for (int i2 = 0; i2 < ticks; ++i2) {
            simulatedPlayer.tick();
        }
        return simulatedPlayer;
    }

    public static SimulatedPlayer fromClientPlayer(SimulatedPlayerInput input) {
        ClientPlayerEntity player = SimulatedPlayer.mc.player;
        return new SimulatedPlayer((PlayerEntity)player, input, player.getPos(), player.getVelocity(), player.getBoundingBox(), player.getYaw(), player.getPitch(), player.isSprinting(), player.fallDistance, player.jumpingCooldown, player.jumping, player.isGliding(), player.isOnGround(), player.horizontalCollision, player.verticalCollision, player.isTouchingWater(), player.isSwimming(), player.isSubmergedInWater(), (Object2DoubleMap<TagKey<Fluid>>)new Object2DoubleArrayMap(player.fluidHeight), new HashSet<TagKey<Fluid>>(player.submergedFluidTag));
    }

    public static SimulatedPlayer fromOtherPlayer(PlayerEntity player, SimulatedPlayerInput input) {
        return new SimulatedPlayer(player, input, player.getPos(), player.getPos().subtract(new Vec3d(player.prevX, player.prevY, player.prevZ)), player.getBoundingBox(), player.getYaw(), player.getPitch(), player.isSprinting(), player.fallDistance, player.jumpingCooldown, player.jumping, player.isGliding(), player.isOnGround(), player.horizontalCollision, player.verticalCollision, player.isTouchingWater(), player.isSwimming(), player.isSubmergedInWater(), (Object2DoubleMap<TagKey<Fluid>>)new Object2DoubleArrayMap(player.fluidHeight), new HashSet<TagKey<Fluid>>(player.submergedFluidTag));
    }

    public Vec3d pos() {
        return this.player.getPos();
    }

    public void tick() {
        ++this.simulatedTicks;
        this.clipLedged = false;
        if (this.pos.y <= -70.0) {
            return;
        }
        this.input.update();
        this.checkWaterState();
        this.updateSubmergedInWaterState();
        this.updateSwimming();
        if (this.jumpingCooldown > 0) {
            --this.jumpingCooldown;
        }
        this.isJumping = this.input.playerInput.comp_3163();
        double newX = this.velocity.x;
        double newY = this.velocity.y;
        double newZ = this.velocity.z;
        if (Math.abs(this.velocity.x) < 0.003) {
            newX = 0.0;
        }
        if (Math.abs(this.velocity.y) < 0.003) {
            newY = 0.0;
        }
        if (Math.abs(this.velocity.z) < 0.003) {
            newZ = 0.0;
        }
        if (this.onGround) {
            this.isFallFlying = false;
        }
        this.velocity = new Vec3d(newX, newY, newZ);
        if (this.isJumping) {
            double fluidLevel = this.isInLava() ? this.getFluidHeight((TagKey<Fluid>)FluidTags.LAVA) : this.getFluidHeight((TagKey<Fluid>)FluidTags.WATER);
            boolean inWater = this.isTouchingWater() && fluidLevel > 0.0;
            double swimHeight = this.getSwimHeight();
            if (inWater && (!this.onGround || fluidLevel > swimHeight)) {
                this.swimUpward((TagKey<Fluid>)FluidTags.WATER);
            } else if (this.isInLava() && (!this.onGround || fluidLevel > swimHeight)) {
                this.swimUpward((TagKey<Fluid>)FluidTags.LAVA);
            } else if ((this.onGround || inWater && fluidLevel <= swimHeight) && this.jumpingCooldown == 0) {
                this.jump();
                this.jumpingCooldown = 10;
            }
        }
        float sidewaysSpeed = this.input.movementSideways * 0.98f;
        float forwardSpeed = this.input.movementForward * 0.98f;
        float upwardsSpeed = 0.0f;
        if (this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.SLOW_FALLING) || this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.LEVITATION)) {
            this.onLanding();
        }
        this.travel(new Vec3d((double)sidewaysSpeed, (double)upwardsSpeed, (double)forwardSpeed));
    }

    private void travel(Vec3d movementInput) {
        boolean falling;
        if (this.isSwimming && !this.player.hasVehicle()) {
            double g2 = this.getRotationVector().y;
            double h2 = g2 < -0.2 ? 0.085 : 0.06;
            BlockPos posAbove = new BlockPos(MathHelper.floor((double)this.pos.x), MathHelper.floor((double)(this.pos.y + 1.0 - 0.1)), MathHelper.floor((double)this.pos.z));
            if (g2 <= 0.0 || this.input.playerInput.comp_3163() || !this.player.getWorld().getBlockState(posAbove).getFluidState().isEmpty()) {
                this.velocity = this.velocity.add(0.0, (g2 - this.velocity.y) * h2, 0.0);
            }
        }
        double beforeTravelVelocityY = this.velocity.y;
        double d2 = 0.08;
        boolean bl = falling = this.velocity.y <= 0.0;
        if (this.velocity.y <= 0.0 && this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.SLOW_FALLING)) {
            d2 = 0.01;
            this.onLanding();
        }
        if (this.isTouchingWater() && this.player.shouldSwimInFluids()) {
            Vec3d vec3d2;
            double e2 = this.pos.y;
            float f2 = this.isSprinting() ? 0.9f : 0.8f;
            float g3 = 0.02f;
            float h3 = (float)this.getAttributeValue((RegistryEntry<EntityAttribute>)EntityAttributes.WATER_MOVEMENT_EFFICIENCY);
            if (!this.onGround) {
                h3 *= 0.5f;
            }
            if (h3 > 0.0f) {
                f2 += (0.54600006f - f2) * h3 / 3.0f;
                g3 += (this.getMovementSpeed() - g3) * h3 / 3.0f;
            }
            if (this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.DOLPHINS_GRACE)) {
                f2 = 0.96f;
            }
            this.updateVelocity(g3, movementInput);
            this.move(this.velocity);
            Vec3d tempVel = this.velocity;
            if (this.horizontalCollision && this.isClimbing()) {
                tempVel = new Vec3d(tempVel.x, 0.2, tempVel.z);
            }
            this.velocity = tempVel.multiply((double)f2, 0.8, (double)f2);
            this.velocity = vec3d2 = this.player.applyFluidMovingSpeed(d2, falling, this.velocity);
            if (this.horizontalCollision && this.doesNotCollide(vec3d2.x, vec3d2.y + 0.6 - this.pos.y + e2, vec3d2.z)) {
                this.velocity = new Vec3d(vec3d2.x, 0.3, vec3d2.z);
            }
        } else if (this.isInLava() && this.player.shouldSwimInFluids()) {
            double e3 = this.pos.y;
            this.updateVelocity(0.02f, movementInput);
            this.move(this.velocity);
            if (this.getFluidHeight((TagKey<Fluid>)FluidTags.LAVA) <= this.getSwimHeight()) {
                this.velocity = this.velocity.multiply(0.5, 0.8, 0.5);
                this.velocity = this.player.applyFluidMovingSpeed(d2, falling, this.velocity);
            } else {
                this.velocity = this.velocity.multiply(0.5);
            }
            if (!this.player.hasNoGravity()) {
                this.velocity = this.velocity.add(0.0, -d2 / 4.0, 0.0);
            }
            if (this.horizontalCollision && this.doesNotCollide(this.velocity.x, this.velocity.y + 0.6 - this.pos.y + e3, this.velocity.z)) {
                this.velocity = new Vec3d(this.velocity.x, 0.3, this.velocity.z);
            }
        } else if (this.isFallFlying) {
            double k2;
            Vec3d e4 = this.velocity;
            if (e4.y > -0.5) {
                this.fallDistance = 1.0f;
            }
            Vec3d vec3d3 = this.getRotationVector(this.pitch + (this.pitch - this.player.prevPitch), this.yaw + (this.yaw - this.player.prevYaw));
            float f3 = this.pitch * ((float)Math.PI / 180);
            double g4 = Math.sqrt(vec3d3.x * vec3d3.x + vec3d3.z * vec3d3.z);
            double horizontalSpeed = this.velocity.horizontalLength();
            double i2 = vec3d3.length();
            float j2 = MathHelper.cos((float)f3);
            j2 = (float)((double)j2 * ((double)j2 * Math.min(1.0, i2 / 0.4)));
            e4 = this.velocity.add(0.0, d2 * (-1.0 + (double)j2 * 0.75), 0.0);
            if (e4.y < 0.0 && g4 > 0.0) {
                k2 = e4.y * -0.1 * (double)j2;
                e4 = e4.add(vec3d3.x * k2 / g4, k2, vec3d3.z * k2 / g4);
            }
            if (f3 < 0.0f && g4 > 0.0) {
                k2 = horizontalSpeed * (double)(-MathHelper.sin((float)f3)) * 0.04;
                e4 = e4.add(-vec3d3.x * k2 / g4, k2 * 3.2, -vec3d3.z * k2 / g4);
            }
            if (g4 > 0.0) {
                e4 = e4.add((vec3d3.x / g4 * horizontalSpeed - e4.x) * 0.1, 0.0, (vec3d3.z / g4 * horizontalSpeed - e4.z) * 0.1);
            }
            this.velocity = e4.multiply(0.99, 0.98, 0.99);
            this.move(this.velocity);
        } else {
            BlockPos blockPos = this.getVelocityAffectingPos();
            float p2 = this.player.getWorld().getBlockState(blockPos).getBlock().getSlipperiness();
            float f4 = this.onGround ? p2 * 0.91f : 0.91f;
            Vec3d vec3d6 = this.applyMovementInput(movementInput, p2);
            double q2 = vec3d6.y;
            if (this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.LEVITATION)) {
                StatusEffectInstance levitation = this.getStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.LEVITATION);
                if (levitation != null) {
                    q2 += (0.05 * (double)(levitation.getAmplifier() + 1) - vec3d6.y) * 0.2;
                }
            } else if (this.player.getWorld().isClient() && !this.player.getWorld().isChunkLoaded(blockPos)) {
                q2 = this.pos.y > (double)this.player.getWorld().getBottomY() ? -0.1 : 0.0;
            } else if (!this.player.hasNoGravity()) {
                q2 -= d2;
            }
            this.velocity = this.player.hasNoDrag() ? new Vec3d(vec3d6.x, q2, vec3d6.z) : new Vec3d(vec3d6.x * (double)f4, q2 * (double)0.98f, vec3d6.z * (double)f4);
        }
        if (this.player.getAbilities().flying && !this.player.hasVehicle()) {
            this.velocity = new Vec3d(this.velocity.x, beforeTravelVelocityY * 0.6, this.velocity.z);
            this.onLanding();
        }
    }

    private Vec3d applyMovementInput(Vec3d movementInput, float slipperiness) {
        this.updateVelocity(this.getMovementSpeed(slipperiness), movementInput);
        this.velocity = this.applyClimbingSpeed(this.velocity);
        this.move(this.velocity);
        Vec3d result = this.velocity;
        BlockPos posBlock = this.posToBlockPos(this.pos);
        BlockState state = this.getState(posBlock);
        if ((this.horizontalCollision || this.isJumping) && (this.isClimbing() || state != null && state.isOf(Blocks.POWDER_SNOW) && PowderSnowBlock.canWalkOnPowderSnow((Entity)this.player))) {
            result = new Vec3d(result.x, 0.2, result.z);
        }
        return result;
    }

    private void updateVelocity(float speed, Vec3d movementInput) {
        Vec3d vec = Entity.movementInputToVelocity((Vec3d)movementInput, (float)speed, (float)this.yaw);
        this.velocity = this.velocity.add(vec);
    }

    private float getMovementSpeed(float slipperiness) {
        return this.onGround ? this.getMovementSpeed() * (0.21600002f / (slipperiness * slipperiness * slipperiness)) : this.getAirStrafingSpeed();
    }

    private float getAirStrafingSpeed() {
        float speed = 0.02f;
        if (this.input.playerInput.comp_3165()) {
            return speed + 0.006f;
        }
        return speed;
    }

    private float getMovementSpeed() {
        return 0.1f;
    }

    private void move(Vec3d movement) {
        Vec3d modifiedMovement = movement;
        Vec3d adjustedMovement = this.adjustMovementForCollisions(modifiedMovement = this.adjustMovementForSneaking(modifiedMovement));
        if (adjustedMovement.lengthSquared() > 1.0E-7) {
            this.pos = this.pos.add(adjustedMovement);
            this.boundingBox = this.player.dimensions.getBoxAt(this.pos);
        }
        boolean xCollision = !MathHelper.approximatelyEquals((double)movement.x, (double)adjustedMovement.x);
        boolean zCollision = !MathHelper.approximatelyEquals((double)movement.z, (double)adjustedMovement.z);
        this.horizontalCollision = xCollision || zCollision;
        this.verticalCollision = movement.y != adjustedMovement.y;
        boolean bl = this.onGround = this.verticalCollision && movement.y < 0.0;
        if (!this.isTouchingWater()) {
            this.checkWaterState();
        }
        if (this.onGround) {
            this.onLanding();
        } else if (movement.y < 0.0) {
            this.fallDistance -= (float)movement.y;
        }
        Vec3d currentVel = this.velocity;
        if (this.horizontalCollision || this.verticalCollision) {
            this.velocity = new Vec3d(xCollision ? 0.0 : currentVel.x, this.onGround ? 0.0 : currentVel.y, zCollision ? 0.0 : currentVel.z);
        }
    }

    private Vec3d adjustMovementForCollisions(Vec3d movement) {
        boolean stepPossible;
        Box box = new Box(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3).offset(this.pos);
        List collisionShapes = Collections.emptyList();
        Vec3d adjusted = movement.lengthSquared() == 0.0 ? movement : Entity.adjustMovementForCollisions((Entity)this.player, (Vec3d)movement, (Box)box, (World)this.player.getWorld(), collisionShapes);
        boolean xCollide = movement.x != adjusted.x;
        boolean yCollide = movement.y != adjusted.y;
        boolean zCollide = movement.z != adjusted.z;
        boolean bl = stepPossible = this.onGround || yCollide && movement.y < 0.0;
        if (this.player.getStepHeight() > 0.0f && stepPossible && (xCollide || zCollide)) {
            Vec3d stepAdjust = Entity.adjustMovementForCollisions((Entity)this.player, (Vec3d)new Vec3d(movement.x, (double)this.player.getStepHeight(), movement.z), (Box)box, (World)this.player.getWorld(), collisionShapes);
            Vec3d stepOffset = Entity.adjustMovementForCollisions((Entity)this.player, (Vec3d)new Vec3d(0.0, (double)this.player.getStepHeight(), 0.0), (Box)box.stretch(movement.x, 0.0, movement.z), (World)this.player.getWorld(), collisionShapes);
            Vec3d combined = Entity.adjustMovementForCollisions((Entity)this.player, (Vec3d)new Vec3d(movement.x, 0.0, movement.z), (Box)box.offset(stepOffset), (World)this.player.getWorld(), collisionShapes).add(stepOffset);
            if (stepOffset.y < (double)this.player.getStepHeight() && combined.horizontalLengthSquared() > stepAdjust.horizontalLengthSquared()) {
                stepAdjust = combined;
            }
            if (stepAdjust.horizontalLengthSquared() > adjusted.horizontalLengthSquared()) {
                return stepAdjust.add(Entity.adjustMovementForCollisions((Entity)this.player, (Vec3d)new Vec3d(0.0, -stepAdjust.y + movement.y, 0.0), (Box)box.offset(stepAdjust), (World)this.player.getWorld(), collisionShapes));
            }
        }
        return adjusted;
    }

    private void onLanding() {
        this.fallDistance = 0.0f;
    }

    public void jump() {
        this.velocity = this.velocity.add(0.0, (double)this.getJumpVelocity() - this.velocity.y, 0.0);
        if (this.isSprinting()) {
            float rad = (float)Math.toRadians(this.yaw);
            this.velocity = this.velocity.add((double)(-MathHelper.sin((float)rad)) * 0.2, 0.0, (double)MathHelper.cos((float)rad) * 0.2);
        }
    }

    private Vec3d applyClimbingSpeed(Vec3d motion) {
        if (!this.isClimbing()) {
            return motion;
        }
        this.onLanding();
        double clampedX = MathHelper.clamp((double)motion.x, (double)-0.15f, (double)0.15f);
        double clampedZ = MathHelper.clamp((double)motion.z, (double)-0.15f, (double)0.15f);
        double clampedY = Math.max(motion.y, (double)-0.15f);
        if (clampedY < 0.0 && !this.getState(this.posToBlockPos(this.pos)).isOf(Blocks.SCAFFOLDING) && this.player.isHoldingOntoLadder()) {
            clampedY = 0.0;
        }
        return new Vec3d(clampedX, clampedY, clampedZ);
    }

    public boolean isClimbing() {
        BlockPos posBlock = this.posToBlockPos(this.pos);
        BlockState state = this.getState(posBlock);
        if (state.isIn(BlockTags.CLIMBABLE)) {
            return true;
        }
        return state.getBlock() instanceof TrapdoorBlock && this.canEnterTrapdoor(posBlock, state);
    }

    private boolean canEnterTrapdoor(BlockPos pos, BlockState state) {
        if (!((Boolean)state.get((Property)TrapdoorBlock.OPEN)).booleanValue()) {
            return false;
        }
        BlockState below = this.player.getWorld().getBlockState(pos.down());
        return below.isOf(Blocks.LADDER) && ((Direction)below.get((Property)LadderBlock.FACING)).equals((Object)state.get((Property)TrapdoorBlock.FACING));
    }

    private Vec3d adjustMovementForSneaking(Vec3d movement) {
        if (movement.y <= 0.0 && this.isOnGroundOrCloseToGround()) {
            double dx;
            double dz = movement.z;
            double step = 0.05;
            for (dx = movement.x; dx != 0.0 && this.player.getWorld().isSpaceEmpty((Entity)this.player, this.boundingBox.offset(dx, -0.5, 0.0)); dx += dx > 0.0 ? -step : step) {
                if (!(dx < step) || !(dx >= -step)) continue;
                dx = 0.0;
                break;
            }
            while (dz != 0.0 && this.player.getWorld().isSpaceEmpty((Entity)this.player, this.boundingBox.offset(0.0, -0.5, dz))) {
                if (dz < step && dz >= -step) {
                    dz = 0.0;
                    break;
                }
                dz += dz > 0.0 ? -step : step;
            }
            while (dx != 0.0 && dz != 0.0 && this.player.getWorld().isSpaceEmpty((Entity)this.player, this.boundingBox.offset(dx, -0.5, dz))) {
                double d2 = dx < step && dx >= -step ? 0.0 : (dx = dx > 0.0 ? dx - step : dx + step);
                if (dz < step && dz >= -step) {
                    dz = 0.0;
                    break;
                }
                dz += dz > 0.0 ? -step : step;
            }
            if (movement.x != dx || movement.z != dz) {
                this.clipLedged = true;
            }
            if (this.shouldClipAtLedge()) {
                movement = new Vec3d(dx, movement.y, dz);
            }
        }
        return movement;
    }

    protected boolean shouldClipAtLedge() {
        return this.input.playerInput.sneak() || this.input.forceSafeWalk;
    }

    private boolean isOnGroundOrCloseToGround() {
        return this.onGround || (double)this.fallDistance < 0.5 && !this.player.getWorld().isSpaceEmpty((Entity)this.player, this.boundingBox.offset(0.0, (double)this.fallDistance - 0.5, 0.0));
    }

    private boolean isSprinting() {
        return this.sprinting;
    }

    private float getJumpVelocity() {
        return 0.42f * this.getJumpVelocityMultiplier() + this.getJumpBoostVelocityModifier();
    }

    private float getJumpBoostVelocityModifier() {
        if (this.hasStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.JUMP_BOOST)) {
            StatusEffectInstance boost = this.getStatusEffect((RegistryEntry<StatusEffect>)StatusEffects.JUMP_BOOST);
            return 0.1f * (float)(boost.getAmplifier() + 1);
        }
        return 0.0f;
    }

    private float getJumpVelocityMultiplier() {
        float multiplier1 = 0.0f;
        Block block = this.getState(this.posToBlockPos(this.pos)).getBlock();
        if (block != null) {
            multiplier1 = block.getJumpVelocityMultiplier();
        }
        float multiplier2 = 0.0f;
        Block block2 = this.getState(this.getVelocityAffectingPos()).getBlock();
        if (block2 != null) {
            multiplier2 = block2.getJumpVelocityMultiplier();
        }
        return multiplier1 == 1.0f ? multiplier2 : multiplier1;
    }

    private boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return this.doesNotCollide(this.boundingBox.offset(offsetX, offsetY, offsetZ));
    }

    private boolean doesNotCollide(Box box) {
        return this.player.getWorld().isSpaceEmpty((Entity)this.player, box) && !this.player.getWorld().containsFluid(box);
    }

    private void swimUpward(TagKey<Fluid> fluidTag) {
        this.velocity = this.velocity.add(0.0, (double)0.04f, 0.0);
    }

    private BlockPos getVelocityAffectingPos() {
        return BlockPos.ofFloored((double)this.pos.x, (double)(this.boundingBox.minY - 0.5000001), (double)this.pos.z);
    }

    private double getSwimHeight() {
        return (double)this.player.getStandingEyeHeight() < 0.4 ? 0.0 : 0.4;
    }

    private boolean isTouchingWater() {
        return this.touchingWater;
    }

    public boolean isInLava() {
        return this.fluidHeight.getDouble((Object)FluidTags.LAVA) > 0.0;
    }

    private void checkWaterState() {
        BoatEntity boat;
        if (this.player.getVehicle() instanceof BoatEntity && !(boat = (BoatEntity)this.player.getVehicle()).isSubmergedInWater()) {
            this.touchingWater = false;
            return;
        }
        if (this.updateMovementInFluid((TagKey<Fluid>)FluidTags.WATER, 0.014)) {
            this.onLanding();
            this.touchingWater = true;
        } else {
            this.touchingWater = false;
        }
    }

    private void updateSwimming() {
        this.isSwimming = this.isSwimming ? this.isSprinting() && this.isTouchingWater() && !this.player.hasVehicle() : this.isSprinting() && this.isSubmergedInWater() && !this.player.hasVehicle() && this.player.getWorld().getFluidState(this.posToBlockPos(this.pos)).isIn(FluidTags.WATER);
    }

    private void updateSubmergedInWaterState() {
        BoatEntity boat;
        this.submergedInWater = this.submergedFluidTag.contains(FluidTags.WATER);
        this.submergedFluidTag.clear();
        double eyeLevel = this.getEyeY() - 0.1111111119389534;
        Entity vehicle = this.player.getVehicle();
        if (vehicle instanceof BoatEntity && !(boat = (BoatEntity)vehicle).isSubmergedInWater() && boat.getBoundingBox().maxY >= eyeLevel && boat.getBoundingBox().minY <= eyeLevel) {
            return;
        }
        BlockPos posEye = BlockPos.ofFloored((double)this.pos.x, (double)eyeLevel, (double)this.pos.z);
        FluidState fluidState = this.player.getWorld().getFluidState(posEye);
        double height = (float)posEye.getY() + fluidState.getHeight((BlockView)this.player.getWorld(), posEye);
        if (height > eyeLevel) {
            this.submergedFluidTag.addAll(fluidState.streamTags().toList());
        }
    }

    private double getEyeY() {
        return this.pos.y + (double)this.player.getStandingEyeHeight();
    }

    public boolean isSubmergedInWater() {
        return this.submergedInWater && this.isTouchingWater();
    }

    private double getFluidHeight(TagKey<Fluid> tag) {
        return this.fluidHeight.getDouble(tag);
    }

    private boolean updateMovementInFluid(TagKey<Fluid> tag, double speed) {
        if (this.isRegionUnloaded()) {
            return false;
        }
        Box box = this.boundingBox.contract(0.001);
        int i2 = MathHelper.floor((double)box.minX);
        int j2 = MathHelper.ceil((double)box.maxX);
        int k2 = MathHelper.floor((double)box.minY);
        int l2 = MathHelper.ceil((double)box.maxY);
        int m2 = MathHelper.floor((double)box.minZ);
        int n2 = MathHelper.ceil((double)box.maxZ);
        double d2 = 0.0;
        boolean pushedByFluids = true;
        boolean foundFluid = false;
        Vec3d fluidVelocity = Vec3d.ZERO;
        int count = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int p2 = i2; p2 < j2; ++p2) {
            for (int q2 = k2; q2 < l2; ++q2) {
                for (int r2 = m2; r2 < n2; ++r2) {
                    double e2;
                    mutable.set(p2, q2, r2);
                    FluidState fluidState = this.player.getWorld().getFluidState((BlockPos)mutable);
                    if (!fluidState.isIn(tag) || !((e2 = (double)((float)q2 + fluidState.getHeight((BlockView)this.player.getWorld(), (BlockPos)mutable))) >= box.minY)) continue;
                    foundFluid = true;
                    d2 = Math.max(e2 - box.minY, d2);
                    if (!pushedByFluids) continue;
                    Vec3d vel = fluidState.getVelocity((BlockView)this.player.getWorld(), (BlockPos)mutable);
                    if (d2 < 0.4) {
                        vel = vel.multiply(d2);
                    }
                    fluidVelocity = fluidVelocity.add(vel);
                    ++count;
                }
            }
        }
        if (fluidVelocity.length() > 0.0) {
            if (count > 0) {
                fluidVelocity = fluidVelocity.multiply(1.0 / (double)count);
            }
            fluidVelocity = fluidVelocity.multiply(speed);
            if (Math.abs(this.velocity.x) < 0.003 && Math.abs(this.velocity.z) < 0.003 && fluidVelocity.length() < 0.0045) {
                fluidVelocity = fluidVelocity.normalize().multiply(0.0045);
            }
            this.velocity = this.velocity.add(fluidVelocity);
        }
        this.fluidHeight.put(tag, d2);
        return foundFluid;
    }

    private boolean isRegionUnloaded() {
        Box box = this.boundingBox.expand(1.0);
        int i2 = MathHelper.floor((double)box.minX);
        int j2 = MathHelper.ceil((double)box.maxX);
        int k2 = MathHelper.floor((double)box.minZ);
        int l2 = MathHelper.ceil((double)box.maxZ);
        return !this.player.getWorld().isRegionLoaded(i2, k2, j2, l2);
    }

    private Vec3d getRotationVector() {
        return this.getRotationVector(this.pitch, this.yaw);
    }

    private Vec3d getRotationVector(float pitch, float yaw) {
        float f2 = (float)((double)pitch * Math.PI / 180.0);
        float g2 = (float)((double)(-yaw) * Math.PI / 180.0);
        float h2 = MathHelper.cos((float)g2);
        float i2 = MathHelper.sin((float)g2);
        float j2 = MathHelper.cos((float)f2);
        float k2 = MathHelper.sin((float)f2);
        return new Vec3d((double)(i2 * j2), (double)(-k2), (double)(h2 * j2));
    }

    public boolean hasStatusEffect(RegistryEntry<StatusEffect> effect) {
        StatusEffectInstance instance = this.player.getStatusEffect(effect);
        return instance != null && instance.getDuration() >= this.simulatedTicks;
    }

    private StatusEffectInstance getStatusEffect(RegistryEntry<StatusEffect> effect) {
        StatusEffectInstance instance = this.player.getStatusEffect(effect);
        if (instance == null || instance.getDuration() < this.simulatedTicks) {
            return null;
        }
        return instance;
    }

    public double getAttributeValue(RegistryEntry<EntityAttribute> attribute) {
        return this.player.getAttributes().getValue(attribute);
    }

    public SimulatedPlayer clone() {
        return new SimulatedPlayer(this.player, this.input, this.pos, this.velocity, this.boundingBox, this.yaw, this.pitch, this.sprinting, this.fallDistance, this.jumpingCooldown, this.isJumping, this.isFallFlying, this.onGround, this.horizontalCollision, this.verticalCollision, this.touchingWater, this.isSwimming, this.submergedInWater, (Object2DoubleMap<TagKey<Fluid>>)new Object2DoubleArrayMap(this.fluidHeight), new HashSet<TagKey<Fluid>>(this.submergedFluidTag));
    }

    public BlockPos posToBlockPos(Vec3d pos) {
        return new BlockPos(MathHelper.floor((double)pos.x), MathHelper.floor((double)pos.y), MathHelper.floor((double)pos.z));
    }

    public BlockState getState(BlockPos pos) {
        return this.player.getWorld().getBlockState(pos);
    }

    public static class SimulatedPlayerInput
    extends Input {
        public boolean forceSafeWalk = false;
        public float movementForward;
        public float movementSideways;
        public PlayerInput playerInput;
        public static final double MAX_WALKING_SPEED = 0.121;

        public SimulatedPlayerInput(PlayerInput input) {
            this.playerInput = input;
        }

        public void update() {
            this.movementForward = this.playerInput.comp_3159() != this.playerInput.comp_3160() ? (this.playerInput.comp_3159() ? 1.0f : -1.0f) : 0.0f;
            if (this.playerInput.comp_3161() == this.playerInput.comp_3162()) {
                this.movementSideways = 0.0f;
            } else {
                float f2 = this.movementSideways = this.playerInput.comp_3161() ? 1.0f : -1.0f;
            }
            if (this.playerInput.sneak()) {
                this.movementSideways *= 0.3f;
                this.movementForward *= 0.3f;
            }
        }

        public String toString() {
            return "SimulatedPlayerInput(forwards={" + this.playerInput.comp_3159() + "}, backwards={" + this.playerInput.comp_3160() + "}, left={" + this.playerInput.comp_3161() + "}, right={" + this.playerInput.comp_3162() + "}, jumping={" + this.playerInput.comp_3163() + "}, sprinting=" + this.playerInput.comp_3165() + ", slowDown=" + this.playerInput.sneak() + ")";
        }

        public static SimulatedPlayerInput fromClientPlayer(PlayerInput input) {
            return new SimulatedPlayerInput(input);
        }

        public static SimulatedPlayerInput guessInput(PlayerEntity entity) {
            Vec3d velocity = entity.getPos().subtract(new Vec3d(entity.prevX, entity.prevY, entity.prevZ));
            double horizontalVelocity = velocity.horizontalLengthSquared();
            PlayerInput input = new PlayerInput(false, false, false, false, !entity.isOnGround(), entity.isSneaking(), horizontalVelocity >= 0.014641);
            if (horizontalVelocity > 0.0025000000000000005) {
                double velocityAngle = MoveUtil.getDegreesRelativeToView(velocity, entity.getYaw());
                double wrappedAngle = MathHelper.wrapDegrees((double)velocityAngle);
                input = MoveUtil.getDirectionalInputForDegrees(input, wrappedAngle);
            }
            return new SimulatedPlayerInput(input);
        }
    }
}

