package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.util.Comparator;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.MoveInputEvent;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.Instance;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Target Pearl", moduleDesc="Бросок перл в сторону цели", moduleCategory=ModuleCategory.PLAYER)
public class TargetPearl
extends Module {
    private static final double MAX_TRACK_DISTANCE = 256.0;
    private static final double MIN_LANDING_DISTANCE = 11.0;
    private static final long LOCAL_THROW_COOLDOWN_MS = 2500L;
    private static final float DIRECT_MIN_PITCH = -25.0f;
    private static final float DIRECT_MAX_PITCH = 35.0f;
    private static final float PITCH_STEP = 0.25f;
    private final ModeSetting mode = new ModeSetting("Тип", "Автоматический", "По бинду", "Автоматический");
    private final BindSetting bind = new BindSetting("Бинд", -1).setVisible(() -> this.mode.is("По бинду"));
    private final BooleanSetting onlyTarget = new BooleanSetting("Только за противником", false);
    private final BooleanSetting ignoreFriends = new BooleanSetting("Игнорировать друзей", true);
    private long lastThrowTime;
    private EnderPearlEntity targetPearl;
    private int lastHandledPearlId = -1;
    private boolean isThrowing;
    private Vec2f serverRotation;

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (this.mc.player == null || this.mc.world == null || this.mc.currentScreen != null) {
            return;
        }
        if (e2.getAction() == 0) {
            return;
        }
        if (!this.mode.is("По бинду") || e2.getKey() != this.bind.getValue().intValue()) {
            return;
        }
        if (this.canThrowNow()) {
            this.aimAndThrowPearl();
        }
    }

    @Subscribe
    private void onUpdate(EventPlayerUpdate e2) {
        EnderPearlEntity pearl;
        Entity handled;
        if (this.mc.player == null || this.mc.world == null) {
            this.resetThrowState();
            return;
        }
        if (!(this.lastHandledPearlId == -1 || (handled = this.mc.world.getEntityById(this.lastHandledPearlId)) instanceof EnderPearlEntity && (pearl = (EnderPearlEntity)handled).isAlive())) {
            this.lastHandledPearlId = -1;
        }
        if (this.mode.is("Автоматический") && this.canThrowNow()) {
            this.aimAndThrowPearl();
        }
    }

    @Subscribe
    private void onMoveInput(MoveInputEvent event) {
        if (!this.isEnabled() || !this.isThrowing || this.serverRotation == null) {
            return;
        }
        float forward = event.getForward();
        float strafe = event.getStrafe();
        if (forward == 0.0f && strafe == 0.0f) {
            return;
        }
        double targetAngle = MathHelper.wrapDegrees((double)Math.toDegrees(TargetPearl.direction(this.serverRotation.x, forward, strafe)));
        float bestForward = 0.0f;
        float bestStrafe = 0.0f;
        float smallestDifference = Float.MAX_VALUE;
        for (float testForward = -1.0f; testForward <= 1.0f; testForward += 1.0f) {
            for (float testStrafe = -1.0f; testStrafe <= 1.0f; testStrafe += 1.0f) {
                double testAngle;
                float difference;
                if (testForward == 0.0f && testStrafe == 0.0f || !((difference = Math.abs(MathHelper.wrapDegrees((float)((float)(targetAngle - (testAngle = MathHelper.wrapDegrees((double)Math.toDegrees(TargetPearl.direction(this.serverRotation.x, testForward, testStrafe))))))))) < smallestDifference)) continue;
                smallestDifference = difference;
                bestForward = testForward;
                bestStrafe = testStrafe;
            }
        }
        event.forward = bestForward;
        event.strafe = bestStrafe;
    }

    @Override
    public void onDisable() {
        this.resetThrowState();
        this.lastHandledPearlId = -1;
        this.lastThrowTime = 0L;
        super.onDisable();
    }

    private boolean canThrowNow() {
        if (System.currentTimeMillis() - this.lastThrowTime < 2500L) {
            return false;
        }
        return !this.mc.player.getItemCooldownManager().isCoolingDown(new ItemStack((ItemConvertible)Items.ENDER_PEARL));
    }
    private void aimAndThrowPearl() {
        Vec3d landingPosition = this.getTargetPearlLandingPosition();
        if (landingPosition == null) {
            this.resetThrowState();
            return;
        }
        float[] rotations = this.calculateYawPitch(landingPosition);
        if (rotations == null || Float.isNaN(rotations[0]) || Float.isNaN(rotations[1])) {
            this.resetThrowState();
            return;
        }
        Vec3d trajectoryLanding = this.checkTrajectory(rotations[0], rotations[1]);
        double allowedError = Math.max(3.0, this.mc.player.getPos().distanceTo(landingPosition) * 0.12);
        if (trajectoryLanding == null || landingPosition.distanceTo(trajectoryLanding) > allowedError) {
            this.resetThrowState();
            return;
        }
        if (!this.hasPearl()) {
            this.resetThrowState();
            return;
        }
        float previousYaw = this.mc.player.getYaw();
        float previousPitch = this.mc.player.getPitch();
        this.isThrowing = true;
        this.serverRotation = new Vec2f(rotations[0], rotations[1]);
        try {
            this.mc.player.setYaw(rotations[0]);
            this.mc.player.setPitch(rotations[1]);
            this.mc.player.networkHandler.sendPacket((Packet)new PlayerMoveC2SPacket.LookAndOnGround(rotations[0], rotations[1], this.mc.player.isOnGround(), this.mc.player.horizontalCollision));
            InventoryUtil.swapAndUseHvH(Items.ENDER_PEARL);
            this.lastThrowTime = System.currentTimeMillis();
            if (this.targetPearl != null) {
                this.lastHandledPearlId = this.targetPearl.getId();
            }
        }
        finally {
            this.mc.player.setYaw(previousYaw);
            this.mc.player.setPitch(previousPitch);
            this.resetThrowState();
        }
    }

    private Vec3d getTargetPearlLandingPosition() {
        this.targetPearl = this.getTargetPearl();
        if (this.targetPearl == null || !this.targetPearl.isAlive()) {
            return null;
        }
        Vec3d landingPos = this.predictPearlLanding(this.targetPearl);
        if (landingPos == null || !this.isWithinRange(landingPos)) {
            return null;
        }
        return landingPos;
    }

    private EnderPearlEntity getTargetPearl() {
        Box searchBox = this.mc.player.getBoundingBox().expand(256.0);
        LivingEntity auraTarget = Instance.get(AttackAura.class) != null ? Instance.get(AttackAura.class).getTarget() : null;
        return this.mc.world.getOtherEntities((Entity)this.mc.player, searchBox, entity -> {
            EnderPearlEntity pearl;
            return entity instanceof EnderPearlEntity && (pearl = (EnderPearlEntity)entity).isAlive() && pearl.getOwner() != this.mc.player && pearl.getId() != this.lastHandledPearlId && !this.isIgnoredFriend(pearl.getOwner()) && (!this.onlyTarget.getValue() || auraTarget != null && pearl.getOwner() == auraTarget);
        }).stream().map(entity -> (EnderPearlEntity)entity).filter(pearl -> this.getHorizontalDistanceTo((EnderPearlEntity)pearl) <= 256.0).min(Comparator.comparingDouble(this::getHorizontalDistanceTo)).orElse(null);
    }

    private boolean isIgnoredFriend(Entity owner) {
        if (!this.ignoreFriends.getValue() || !(owner instanceof PlayerEntity)) {
            return false;
        }
        PlayerEntity player = (PlayerEntity)owner;
        return FriendRepository.isFriend(player.getNameForScoreboard());
    }

    private double getHorizontalDistanceTo(EnderPearlEntity pearl) {
        Vec3d playerPos = this.mc.player.getPos();
        Vec3d pearlPos = pearl.getPos();
        double dx = pearlPos.x - playerPos.x;
        double dz = pearlPos.z - playerPos.z;
        return Math.sqrt(dx * dx + dz * dz);
    }

    private Vec3d predictPearlLanding(EnderPearlEntity pearl) {
        Vec3d position = pearl.getPos();
        Vec3d velocity = pearl.getVelocity();
        Vec3d lastPosition = position;
        for (int i2 = 0; i2 < 200; ++i2) {
            lastPosition = position;
            if (this.hitsBlock(lastPosition, position = position.add(velocity)) || position.y <= (double)this.mc.world.getBottomY()) {
                return new Vec3d((double)MathHelper.floor((double)lastPosition.x) + 0.5, (double)MathHelper.floor((double)lastPosition.y), (double)MathHelper.floor((double)lastPosition.z) + 0.5);
            }
            velocity = this.updatePearlMotion(velocity, position);
        }
        return new Vec3d((double)MathHelper.floor((double)lastPosition.x) + 0.5, (double)MathHelper.floor((double)lastPosition.y), (double)MathHelper.floor((double)lastPosition.z) + 0.5);
    }

    private Vec3d updatePearlMotion(Vec3d motion, Vec3d position) {
        BlockPos blockPos = BlockPos.ofFloored((Position)position);
        if (this.mc.world.getBlockState(blockPos).isOf(Blocks.WATER)) {
            return motion.multiply(0.8).add(0.0, -0.03, 0.0);
        }
        return motion.multiply(0.99).add(0.0, -0.03, 0.0);
    }

    private boolean isWithinRange(Vec3d landingPos) {
        double distanceToLanding = this.mc.player.getPos().distanceTo(landingPos);
        return distanceToLanding >= 11.0 && distanceToLanding <= 256.0;
    }

    private float[] calculateYawPitch(Vec3d targetPosition) {
        Vec3d playerPosition = this.mc.player.getPos();
        double dx = targetPosition.x - playerPosition.x;
        double dy = targetPosition.y - this.mc.player.getEyeY();
        double dz = targetPosition.z - playerPosition.z;
        float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        double allowedError = Math.max(1.5, this.mc.player.getPos().distanceTo(targetPosition) * 0.08);
        TrajectoryCandidate directCandidate = this.findBestCandidate(targetPosition, yaw, -25.0f, 35.0f, allowedError, true);
        if (directCandidate != null) {
            return new float[]{yaw, MathHelper.clamp((float)directCandidate.pitch, (float)-90.0f, (float)90.0f)};
        }
        TrajectoryCandidate fallbackCandidate = this.findBestCandidate(targetPosition, yaw, -85.0f, 85.0f, allowedError, false);
        if (fallbackCandidate == null) {
            double fallbackPitch = -Math.toDegrees(Math.atan2(dy, horizontalDistance)) + 5.0;
            return new float[]{yaw, MathHelper.clamp((float)((float)fallbackPitch), (float)-90.0f, (float)90.0f)};
        }
        return new float[]{yaw, MathHelper.clamp((float)fallbackCandidate.pitch, (float)-90.0f, (float)90.0f)};
    }

    private TrajectoryCandidate findBestCandidate(Vec3d targetPosition, float yaw, float minPitch, float maxPitch, double allowedError, boolean preferDirect) {
        Vec3d playerPosition = this.mc.player.getPos();
        double velocity = 1.5;
        TrajectoryCandidate bestCandidate = null;
        block0: for (float pitch = minPitch; pitch <= maxPitch; pitch += 0.25f) {
            float pitchRad = (float)Math.toRadians(pitch);
            double vx = (double)(-MathHelper.sin((float)((float)Math.toRadians(yaw))) * MathHelper.cos((float)pitchRad)) * velocity;
            double vy = (double)(-MathHelper.sin((float)pitchRad)) * velocity;
            double vz = (double)(MathHelper.cos((float)((float)Math.toRadians(yaw))) * MathHelper.cos((float)pitchRad)) * velocity;
            Vec3d pos = new Vec3d(playerPosition.x, this.mc.player.getEyeY(), playerPosition.z);
            Vec3d motion = new Vec3d(vx, vy, vz);
            for (int i2 = 0; i2 < 200; ++i2) {
                Vec3d previous = pos;
                pos = pos.add(motion);
                motion = this.updatePearlMotion(motion, pos);
                if (this.hitsEntity(previous, pos)) continue block0;
                if (!this.hitsBlock(previous, pos) && pos.y > (double)this.mc.world.getBottomY()) continue;
                double distanceToTarget = pos.distanceTo(targetPosition);
                TrajectoryCandidate candidate = new TrajectoryCandidate(pitch, distanceToTarget, i2 + 1, pos);
                if (!this.isBetterCandidate(candidate, bestCandidate, allowedError, preferDirect)) continue block0;
                bestCandidate = candidate;
                continue block0;
            }
        }
        if (bestCandidate == null || bestCandidate.distanceToTarget > allowedError) {
            return null;
        }
        return bestCandidate;
    }

    private boolean isBetterCandidate(TrajectoryCandidate candidate, TrajectoryCandidate currentBest, double allowedError, boolean preferDirect) {
        boolean bestAccurate;
        if (currentBest == null) {
            return true;
        }
        boolean candidateAccurate = candidate.distanceToTarget <= allowedError;
        boolean bl = bestAccurate = currentBest.distanceToTarget <= allowedError;
        if (candidateAccurate != bestAccurate) {
            return candidateAccurate;
        }
        if (preferDirect && candidateAccurate && bestAccurate) {
            float bestPitchAbs;
            float candidatePitchAbs = Math.abs(candidate.pitch);
            if (Math.abs(candidatePitchAbs - (bestPitchAbs = Math.abs(currentBest.pitch))) > 0.01f) {
                return candidatePitchAbs < bestPitchAbs;
            }
            if (candidate.ticks != currentBest.ticks) {
                return candidate.ticks < currentBest.ticks;
            }
        }
        if (Math.abs(candidate.distanceToTarget - currentBest.distanceToTarget) > 0.01) {
            return candidate.distanceToTarget < currentBest.distanceToTarget;
        }
        if (candidate.ticks != currentBest.ticks) {
            return candidate.ticks < currentBest.ticks;
        }
        if (!preferDirect) {
            return Math.abs(candidate.pitch) < Math.abs(currentBest.pitch);
        }
        return false;
    }

    private Vec3d checkTrajectory(float yaw, float pitch) {
        float yawRad = (float)Math.toRadians(yaw);
        float pitchRad = (float)Math.toRadians(pitch);
        double velocity = 1.5;
        double x2 = this.mc.player.getX() - (double)(MathHelper.cos((float)yawRad) * 0.16f);
        double y2 = this.mc.player.getY() + (double)this.mc.player.getEyeHeight(this.mc.player.getPose()) - 0.1;
        double z2 = this.mc.player.getZ() - (double)(MathHelper.sin((float)yawRad) * 0.16f);
        double motionX = (double)(-MathHelper.sin((float)yawRad) * MathHelper.cos((float)pitchRad)) * velocity;
        double motionY = (double)(-MathHelper.sin((float)pitchRad)) * velocity;
        double motionZ = (double)(MathHelper.cos((float)yawRad) * MathHelper.cos((float)pitchRad)) * velocity;
        Vec3d position = new Vec3d(x2, y2, z2);
        Vec3d motion = new Vec3d(motionX, motionY, motionZ);
        for (int i2 = 0; i2 <= 200; ++i2) {
            Vec3d previous = position;
            position = position.add(motion);
            motion = this.updatePearlMotion(motion, position);
            if (this.hitsEntity(previous, position)) {
                return null;
            }
            if (!this.hitsBlock(previous, position) && !(position.y <= (double)this.mc.world.getBottomY())) continue;
            return new Vec3d((double)MathHelper.floor((double)position.x) + 0.5, (double)MathHelper.floor((double)position.y), (double)MathHelper.floor((double)position.z) + 0.5);
        }
        return null;
    }

    private boolean hitsBlock(Vec3d from, Vec3d to) {
        return this.mc.world.raycast(new RaycastContext(from, to, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)this.mc.player)).getType() == HitResult.Type.BLOCK;
    }

    private boolean hitsEntity(Vec3d from, Vec3d to) {
        Box searchBox = new Box(from, to).expand(0.3);
        for (Entity entity2 : this.mc.world.getOtherEntities((Entity)this.mc.player, searchBox, entity -> {
            if (!entity.isAlive() || entity.isSpectator() || entity.noClip) {
                return false;
            }
            if (entity == this.targetPearl) {
                return false;
            }
            return !(entity instanceof EnderPearlEntity);
        })) {
            if (!entity2.getBoundingBox().expand(0.25).raycast(from, to).isPresent()) continue;
            return true;
        }
        return false;
    }

    private boolean hasPearl() {
        return this.mc.player.getMainHandStack().isOf(Items.ENDER_PEARL) || this.mc.player.getOffHandStack().isOf(Items.ENDER_PEARL) || InventoryUtil.searchItem(Items.ENDER_PEARL, 0, 8) != -1 || InventoryUtil.searchItem(Items.ENDER_PEARL, 9, 45) != -1;
    }

    private void resetThrowState() {
        this.isThrowing = false;
        this.targetPearl = null;
        this.serverRotation = null;
    }

    private static double direction(float rotationYaw, float moveForward, float moveStrafing) {
        if (moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (moveForward < 0.0f) {
            forward = -0.5f;
        } else if (moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    private static final class TrajectoryCandidate {
        private final float pitch;
        private final double distanceToTarget;
        private final int ticks;
        private final Vec3d landingPos;

        private TrajectoryCandidate(float pitch, double distanceToTarget, int ticks, Vec3d landingPos) {
            this.pitch = pitch;
            this.distanceToTarget = distanceToTarget;
            this.ticks = ticks;
            this.landingPos = landingPos;
        }
    }
}

