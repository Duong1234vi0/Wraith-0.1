package ua.babka.util.player;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import ua.babka.util.IMinecraft;

@Environment(value=EnvType.CLIENT)
public final class PetBrain {
    private Vec3d prevPos;
    private Vec3d pos;
    private Vec3d motion = Vec3d.ZERO;
    private Vec3d smoothVel = Vec3d.ZERO;
    private float direction = (float)(Math.random() * 360.0);
    private float smoothBody;
    private float smoothHeadYaw;
    private float smoothHeadPitch;
    private boolean lay;
    private long lastMoveTime = System.currentTimeMillis();
    private AttackPhase attackPhase = AttackPhase.RUNNING_FORWARD;
    private Vec3d attackStrikeTarget = null;
    private boolean didJumpThisPhase = false;
    private double leashVelY = 0.0;
    private int attackTargetId = -1;
    public double teleportDistance = 8.0;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    private ClientPlayerEntity entity;
    private LivingEntity attackTarget;

    public void setEntity(ClientPlayerEntity entity) {
        this.entity = entity;
    }

    public void setAttackTarget(LivingEntity target) {
        if (target == null) {
            this.attackTarget = null;
            this.attackTargetId = -1;
            this.attackStrikeTarget = null;
            this.attackPhase = AttackPhase.RUNNING_FORWARD;
            this.didJumpThisPhase = false;
        } else {
            int newId = target.getId();
            if (newId != this.attackTargetId) {
                this.attackTarget = target;
                this.attackTargetId = newId;
                this.attackPhase = AttackPhase.RUNNING_FORWARD;
                this.attackStrikeTarget = null;
                this.didJumpThisPhase = false;
            }
        }
    }

    public LivingEntity getAttackTarget() {
        return this.attackTarget;
    }

    public void onUpdate() {
        if (this.entity != null && IMinecraft.mc.world != null) {
            Vec3d playerPos = this.entity.getPos();
            if (this.pos == null) {
                this.prevPos = this.pos = playerPos;
                this.motion = Vec3d.ZERO;
                this.smoothVel = Vec3d.ZERO;
            } else {
                this.prevPos = this.pos;
                double distToPlayer = this.pos.distanceTo(playerPos);
                if (distToPlayer > this.teleportDistance) {
                    float angle = (float)Math.toRadians(this.entity.getYaw() + 140.0f);
                    this.prevPos = this.pos = new Vec3d(playerPos.x + Math.cos(angle) * 1.5, playerPos.y, playerPos.z + Math.sin(angle) * 1.5);
                    this.motion = Vec3d.ZERO;
                    this.smoothVel = Vec3d.ZERO;
                } else {
                    boolean playerHighAbove;
                    boolean onGround = this.isSolidBox(this.pos.x, this.pos.y - 0.1, this.pos.z, 0.25);
                    boolean bl = playerHighAbove = !this.entity.isOnGround() && playerPos.y - this.pos.y > 1.5;
                    if (!onGround && !playerHighAbove) {
                        this.motion = this.motion.add(0.0, -0.078, 0.0);
                    } else if (onGround && this.motion.y < 0.0) {
                        this.motion = new Vec3d(this.motion.x, 0.0, this.motion.z);
                    }
                    if (this.attackTarget != null) {
                        boolean dogFar;
                        boolean dead = !this.attackTarget.isAlive() || this.attackTarget.isRemoved();
                        boolean tooFar = playerPos.distanceTo(this.attackTarget.getPos()) > 5.0;
                        boolean bl2 = dogFar = this.pos.distanceTo(this.attackTarget.getPos()) > 14.0;
                        if (dead || tooFar || dogFar) {
                            this.attackTarget = null;
                            this.attackTargetId = -1;
                            this.attackStrikeTarget = null;
                            this.attackPhase = AttackPhase.RUNNING_FORWARD;
                            this.didJumpThisPhase = false;
                        }
                    }
                    if (this.attackTarget != null) {
                        this.handleAttackMode(onGround);
                    } else if (playerHighAbove) {
                        this.handleFollowFlying(playerPos);
                    } else {
                        this.handleFollowPlayer(playerPos, distToPlayer);
                    }
                    if (!playerHighAbove) {
                        this.motion = new Vec3d(this.motion.x * 0.88, this.motion.y, this.motion.z * 0.88);
                        this.pos = this.moveWithCollision(this.pos, this.motion);
                    }
                    this.handleRotation();
                    this.limbTick();
                    if (this.motion.horizontalLengthSquared() > 4.0E-4 || this.attackTarget != null) {
                        this.lastMoveTime = System.currentTimeMillis();
                    }
                    this.lay = System.currentTimeMillis() - this.lastMoveTime > 2500L;
                }
            }
        }
    }

    private void handleAttackMode(boolean onGround) {
        Vec3d toStrike;
        double dist;
        Vec3d targetPos = this.attackTarget.getPos();
        if (this.attackStrikeTarget == null) {
            Vec3d dir = targetPos.subtract(this.pos).normalize();
            this.attackStrikeTarget = this.attackPhase == AttackPhase.RUNNING_FORWARD ? targetPos.add(dir.multiply(0.5)) : targetPos.subtract(dir.multiply(0.5));
        }
        if (!this.didJumpThisPhase && onGround) {
            this.motion = new Vec3d(this.motion.x, 0.22, this.motion.z);
            this.didJumpThisPhase = true;
        }
        if ((dist = (toStrike = this.attackStrikeTarget.subtract(this.pos)).horizontalLength()) < 0.2) {
            this.attackPhase = this.attackPhase == AttackPhase.RUNNING_FORWARD ? AttackPhase.RUNNING_BACK : AttackPhase.RUNNING_FORWARD;
            this.didJumpThisPhase = false;
            this.attackStrikeTarget = null;
        } else {
            Vec3d wantedVel = toStrike.normalize().multiply(0.46);
            this.smoothVel = PetBrain.lerpVec(this.smoothVel, wantedVel, 0.2);
            this.motion = new Vec3d(this.smoothVel.x, this.motion.y, this.smoothVel.z);
        }
    }

    private void handleFollowPlayer(Vec3d playerPos, double distToPlayer) {
        Vec3d wantedVel;
        this.attackPhase = AttackPhase.RUNNING_FORWARD;
        if (distToPlayer > 2.5) {
            double speed = distToPlayer > 7.0 ? 0.46 : 0.34;
            Vec3d dir = playerPos.subtract(this.pos).normalize();
            wantedVel = new Vec3d(dir.x * speed, 0.0, dir.z * speed);
        } else if (distToPlayer < 0.6) {
            double xM = -Math.sin(Math.toRadians(this.direction)) * 0.04;
            double zM = Math.cos(Math.toRadians(this.direction)) * 0.04;
            wantedVel = new Vec3d(xM, 0.0, zM);
        } else {
            wantedVel = Vec3d.ZERO;
            this.direction += 0.5f;
        }
        this.smoothVel = PetBrain.lerpVec(this.smoothVel, wantedVel, 0.16);
        this.motion = new Vec3d(this.smoothVel.x, this.motion.y, this.smoothVel.z);
    }

    private void handleFollowFlying(Vec3d playerPos) {
        Vec3d playerMotion = this.entity.getVelocity();
        Vec3d behindOffset = playerMotion.horizontalLength() > 0.02 ? playerMotion.normalize().multiply(-1.2) : Vec3d.ZERO;
        Vec3d anchor = new Vec3d(playerPos.x + behindOffset.x, playerPos.y - 1.6, playerPos.z + behindOffset.z);
        double dist = this.pos.distanceTo(anchor);
        if (dist > 6.0) {
            this.pos = PetBrain.lerpVec(this.pos, anchor, 0.6);
            this.leashVelY = 0.0;
            this.motion = Vec3d.ZERO;
            this.smoothVel = Vec3d.ZERO;
        } else {
            double hDist = Math.sqrt((this.pos.x - anchor.x) * (this.pos.x - anchor.x) + (this.pos.z - anchor.z) * (this.pos.z - anchor.z));
            double tH = hDist < 1.5 ? 0.06 : MathHelper.clamp((double)(hDist * 0.045), (double)0.06, (double)0.18);
            double nx = this.pos.x + (anchor.x - this.pos.x) * tH;
            double nz = this.pos.z + (anchor.z - this.pos.z) * tH;
            double dy = anchor.y - this.pos.y;
            this.leashVelY -= 0.018;
            this.leashVelY += dy * 0.055;
            this.leashVelY *= 0.8;
            double ny = this.pos.y + this.leashVelY;
            this.pos = new Vec3d(nx, ny, nz);
            this.motion = Vec3d.ZERO;
            this.smoothVel = Vec3d.ZERO;
        }
    }

    private static Vec3d lerpVec(Vec3d from, Vec3d to, double t2) {
        return new Vec3d(from.x + (to.x - from.x) * t2, from.y + (to.y - from.y) * t2, from.z + (to.z - from.z) * t2);
    }

    private void handleRotation() {
        if (this.attackTarget != null) {
            Vec3d targetPos = this.attackTarget.getPos();
            Vec3d toTarget = targetPos.subtract(this.pos);
            if (toTarget.horizontalLength() > 0.01) {
                float targetBody = (float)Math.toDegrees(Math.atan2(-toTarget.x, toTarget.z));
                this.smoothBody += PetBrain.wrapDegrees(targetBody - this.smoothBody) * 0.35f;
            }
        } else if (this.motion.x != 0.0 || this.motion.z != 0.0) {
            float targetBody = (float)Math.toDegrees(Math.atan2(-this.motion.x, this.motion.z));
            this.smoothBody += PetBrain.wrapDegrees(targetBody - this.smoothBody) * 0.15f;
        }
        Vec3d lookAt = IMinecraft.mc.options != null && IMinecraft.mc.options.getPerspective().isFirstPerson() ? (IMinecraft.mc.player != null ? IMinecraft.mc.player.getEyePos() : IMinecraft.mc.gameRenderer.getCamera().getPos()) : IMinecraft.mc.gameRenderer.getCamera().getPos();
        Vec3d headPos = this.pos.add(0.0, 0.55, 0.0);
        double dx = lookAt.x - headPos.x;
        double dy = lookAt.y - headPos.y;
        double dz = lookAt.z - headPos.z;
        float worldYaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
        float targetHeadPitch = (float)(-Math.toDegrees(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz))));
        float targetHeadYaw = PetBrain.wrapDegrees(this.smoothBody - worldYaw);
        float yawLimit = this.lay ? 90.0f : 70.0f;
        targetHeadYaw = MathHelper.clamp((float)targetHeadYaw, (float)(-yawLimit), (float)yawLimit);
        targetHeadPitch = MathHelper.clamp((float)targetHeadPitch, (float)-30.0f, (float)30.0f);
        this.smoothHeadYaw += (targetHeadYaw - this.smoothHeadYaw) * 0.18f;
        this.smoothHeadPitch += (targetHeadPitch - this.smoothHeadPitch) * 0.18f;
    }

    private void limbTick() {
        this.prevLimbSwingAmount = this.limbSwingAmount;
        float f2 = (float)this.motion.horizontalLength() * 4.0f;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.3f;
        this.limbSwing += this.limbSwingAmount;
    }

    private Vec3d moveWithCollision(Vec3d current, Vec3d vel) {
        double groundSnap;
        double newZ;
        double newX;
        double groundY;
        double W = 0.3;
        double H = 0.5;
        double nx = current.x;
        double ny = current.y;
        double nz = current.z;
        double newY = ny + vel.y;
        if (vel.y < 0.0 && (groundY = this.findGroundBelow(nx, ny, nz, 0.5)) != -999.0 && newY < groundY) {
            newY = groundY;
            this.motion = new Vec3d(this.motion.x, 0.0, this.motion.z);
        }
        if (this.isSolidBox(newX = nx + vel.x, (ny = newY) + 0.01, nz, 0.3)) {
            double stepY = ny + 1.01;
            if (!this.isSolidBox(newX, stepY + 0.01, nz, 0.3) && !this.isSolidBox(newX, stepY + 0.5 - 0.01, nz, 0.3)) {
                this.motion = new Vec3d(this.motion.x, Math.max(this.motion.y, 0.18), this.motion.z);
            } else {
                newX = nx;
                this.motion = new Vec3d(0.0, this.motion.y, this.motion.z);
            }
        }
        if (this.isSolidBox(nx = newX, ny + 0.01, newZ = nz + vel.z, 0.3)) {
            double stepY = ny + 1.01;
            if (!this.isSolidBox(nx, stepY + 0.01, newZ, 0.3) && !this.isSolidBox(nx, stepY + 0.5 - 0.01, newZ, 0.3)) {
                this.motion = new Vec3d(this.motion.x, Math.max(this.motion.y, 0.18), this.motion.z);
            } else {
                newZ = nz;
                this.motion = new Vec3d(this.motion.x, this.motion.y, 0.0);
            }
        }
        if ((groundSnap = this.findGroundBelow(nx, ny + 0.3, nz = newZ, 0.5)) != -999.0 && ny < groundSnap) {
            ny += Math.min(groundSnap - ny, 0.15);
            if (this.motion.y < 0.0) {
                this.motion = new Vec3d(this.motion.x, 0.0, this.motion.z);
            }
        }
        return new Vec3d(nx, ny, nz);
    }

    private double findGroundBelow(double x2, double y2, double z2, double height) {
        if (IMinecraft.mc.world == null) {
            return -999.0;
        }
        for (double dy = 0.0; dy <= 4.0; dy += 0.5) {
            double checkY = y2 - dy;
            BlockPos bp = BlockPos.ofFloored((double)x2, (double)(checkY - 0.1), (double)z2);
            if (IMinecraft.mc.world.getBlockState(bp).getCollisionShape((BlockView)IMinecraft.mc.world, bp).isEmpty()) continue;
            return (double)bp.getY() + 1.0;
        }
        return -999.0;
    }

    private boolean isSolidBox(double x2, double y2, double z2, double w2) {
        double[] offsets;
        if (IMinecraft.mc.world == null) {
            return false;
        }
        for (double dx : offsets = new double[]{-w2, w2}) {
            for (double dz : offsets) {
                BlockPos bp = BlockPos.ofFloored((double)(x2 + dx), (double)y2, (double)(z2 + dz));
                if (IMinecraft.mc.world.getBlockState(bp).getCollisionShape((BlockView)IMinecraft.mc.world, bp).isEmpty()) continue;
                return true;
            }
        }
        return false;
    }

    private static float wrapDegrees(float deg) {
        if ((deg %= 360.0f) >= 180.0f) {
            deg -= 360.0f;
        }
        if (deg < -180.0f) {
            deg += 360.0f;
        }
        return deg;
    }

    public Vec3d getPos(float tickDelta) {
        if (this.prevPos != null && this.pos != null) {
            return new Vec3d(MathHelper.lerp((double)tickDelta, (double)this.prevPos.x, (double)this.pos.x), MathHelper.lerp((double)tickDelta, (double)this.prevPos.y, (double)this.pos.y), MathHelper.lerp((double)tickDelta, (double)this.prevPos.z, (double)this.pos.z));
        }
        return this.pos != null ? this.pos : Vec3d.ZERO;
    }

    public Vec3d getPos() {
        return this.pos != null ? this.pos : Vec3d.ZERO;
    }

    public float getBody() {
        return this.smoothBody;
    }

    public float getYaw() {
        return this.smoothHeadYaw;
    }

    public float getPitch() {
        return this.smoothHeadPitch;
    }

    public boolean isLay() {
        return this.lay;
    }

    @Environment(value=EnvType.CLIENT)
    static enum AttackPhase {
        RUNNING_FORWARD,
        RUNNING_BACK;

    }
}

