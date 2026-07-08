/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.player.autoMine.mine;

import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class MineMovementBrainer
implements QuickImports {
    private class_2338 obstacleBlock;
    private class_2338 moveTarget;
    private boolean shouldDigObstacle;
    private boolean pathClear;
    private class_243 lastPos;
    private long lastMovedAt;
    private boolean stuck;
    static final double STUCK_MOVE_THRESHOLD = 0.08;
    static final long STUCK_TIME_MS = 1200L;

    public MineMovementBrainer() {
        this.reset();
    }

    public void reset() {
        this.obstacleBlock = null;
        this.moveTarget = null;
        this.shouldDigObstacle = false;
        this.pathClear = true;
        this.lastPos = null;
        this.lastMovedAt = System.currentTimeMillis();
        this.stuck = false;
    }

    public void update(class_2338 targetBlock) {
        class_2338 forceObstacle;
        if (targetBlock == null || MineMovementBrainer.mc.field_1724 == null) {
            this.obstacleBlock = null;
            this.moveTarget = null;
            this.shouldDigObstacle = false;
            this.pathClear = true;
            this.stuck = false;
            return;
        }
        this.updateStuck();
        class_2338 playerFeet = MineMovementBrainer.mc.field_1724.method_24515();
        int dx = Integer.compare(targetBlock.method_10263(), playerFeet.method_10263());
        int dz = Integer.compare(targetBlock.method_10260(), playerFeet.method_10260());
        int dy = Integer.compare(targetBlock.method_10264(), playerFeet.method_10264());
        double horizontalDist = WorldUtility.horizontalDistance(playerFeet, targetBlock);
        double verticalDist = WorldUtility.verticalDistance(playerFeet, targetBlock);
        if (horizontalDist < 4.5 && Math.abs(verticalDist) < 4.5) {
            class_2338 blockingBlock = this.findBlockBetween(playerFeet, targetBlock);
            if (blockingBlock != null) {
                this.obstacleBlock = blockingBlock;
                this.shouldDigObstacle = this.isInDigRange(blockingBlock);
                this.pathClear = false;
                this.moveTarget = this.shouldDigObstacle ? null : blockingBlock;
                return;
            }
            this.obstacleBlock = null;
            this.moveTarget = targetBlock;
            this.shouldDigObstacle = false;
            this.pathClear = true;
            return;
        }
        class_2338 obstacle = this.findObstacleOnPath(playerFeet, dx, dy, dz, targetBlock);
        if (obstacle != null) {
            this.obstacleBlock = obstacle;
            this.shouldDigObstacle = this.isInDigRange(obstacle);
            this.pathClear = false;
            this.moveTarget = this.shouldDigObstacle ? null : this.obstacleBlock;
        } else {
            this.obstacleBlock = null;
            this.shouldDigObstacle = false;
            this.pathClear = true;
            this.moveTarget = targetBlock;
        }
        if (this.stuck && this.pathClear && (forceObstacle = this.findImmediateObstacle(playerFeet, dx, dz)) != null) {
            this.obstacleBlock = forceObstacle;
            this.shouldDigObstacle = true;
            this.pathClear = false;
        }
    }

    private class_2338 findBlockBetween(class_2338 playerFeet, class_2338 target) {
        int dx = Integer.signum(target.method_10263() - playerFeet.method_10263());
        int dy = Integer.signum(target.method_10264() - playerFeet.method_10264());
        int dz = Integer.signum(target.method_10260() - playerFeet.method_10260());
        class_2338 current = playerFeet;
        int maxSteps = 6;
        for (int step = 0; step < maxSteps; ++step) {
            class_2338 next = dy < 0 ? current.method_10074() : (dy > 0 ? current.method_10084().method_10084() : current.method_10069(dx, 0, dz));
            if (next.equals((Object)target)) {
                return null;
            }
            if (this.isSolid(next) && !next.equals((Object)target)) {
                return next;
            }
            current = next;
            if (dy < 0 && current.method_10264() <= target.method_10264() || dy > 0 && current.method_10264() >= target.method_10264() || dy == 0 && current.method_10263() == target.method_10263() && current.method_10260() == target.method_10260()) break;
        }
        return null;
    }

    private class_2338 findObstacleOnPath(class_2338 playerFeet, int dx, int dy, int dz, class_2338 target) {
        class_2338 current = playerFeet;
        int maxSteps = 10;
        for (int step = 0; step < maxSteps; ++step) {
            class_2338 upNext;
            class_2338 next = dx != 0 || dz != 0 ? current.method_10069(dx, 0, dz) : current.method_10069(0, dy, 0);
            if (this.isSolid(next)) {
                return next;
            }
            if (this.isSolid(next.method_10084())) {
                return next.method_10084();
            }
            if (dy > 0 && this.isSolid(upNext = next.method_10084().method_10084())) {
                return upNext;
            }
            if (dy < 0) {
                upNext = current.method_10069(dx, -1, dz);
            }
            if (Math.abs((current = next).method_10263() - target.method_10263()) > 1 || Math.abs(current.method_10260() - target.method_10260()) > 1) continue;
            if (dy == 0) break;
            class_2338 vertCheck = current.method_10069(0, dy, 0);
            if (this.isSolid(vertCheck)) {
                return vertCheck;
            }
            if (!this.isSolid(vertCheck.method_10084())) break;
            return vertCheck.method_10084();
        }
        return null;
    }

    private class_2338 findImmediateObstacle(class_2338 feet, int dx, int dz) {
        if (dx != 0 || dz != 0) {
            class_2338 front = feet.method_10069(dx, 0, dz);
            if (this.isSolid(front)) {
                return front;
            }
            if (this.isSolid(front.method_10084())) {
                return front.method_10084();
            }
        }
        if (dx != 0) {
            class_2338 frontX = feet.method_10069(dx, 0, 0);
            if (this.isSolid(frontX)) {
                return frontX;
            }
            if (this.isSolid(frontX.method_10084())) {
                return frontX.method_10084();
            }
        }
        if (dz != 0) {
            class_2338 frontZ = feet.method_10069(0, 0, dz);
            if (this.isSolid(frontZ)) {
                return frontZ;
            }
            if (this.isSolid(frontZ.method_10084())) {
                return frontZ.method_10084();
            }
        }
        if (this.isSolid(feet.method_10084().method_10084())) {
            return feet.method_10084().method_10084();
        }
        if (this.isSolid(feet.method_10074())) {
            return feet.method_10074();
        }
        return null;
    }

    private boolean isSolid(class_2338 pos) {
        class_2680 state = WorldUtility.fromBlockPos(pos);
        if (state == null || state.method_26215()) {
            return false;
        }
        if (!state.method_51366()) {
            return false;
        }
        return !state.method_27852(class_2246.field_9987) && !state.method_27852(class_2246.field_10499) && !state.method_27852(class_2246.field_10525) && !state.method_27852(class_2246.field_10465) && !state.method_27852(class_2246.field_10398) && !state.method_27852(class_2246.field_38420);
    }

    private boolean isInDigRange(class_2338 block) {
        class_243 blockCenter;
        class_243 eyePos = MineMovementBrainer.mc.field_1724.method_33571();
        return eyePos.method_1022(blockCenter = class_243.method_24953((class_2382)block)) <= 5.0;
    }

    private void updateStuck() {
        class_243 pos = MineMovementBrainer.mc.field_1724.method_19538();
        long now = System.currentTimeMillis();
        if (this.lastPos != null && pos.method_1022(this.lastPos) > 0.08) {
            this.lastMovedAt = now;
            this.stuck = false;
        } else if (now - this.lastMovedAt > 1200L) {
            this.stuck = true;
        }
        this.lastPos = pos;
    }

    public class_2338 getObstacleBlock() {
        return this.obstacleBlock;
    }

    public class_2338 getMoveTarget() {
        return this.moveTarget;
    }

    public boolean isShouldDigObstacle() {
        return this.shouldDigObstacle;
    }

    public boolean isPathClear() {
        return this.pathClear;
    }

    public class_243 getLastPos() {
        return this.lastPos;
    }

    public long getLastMovedAt() {
        return this.lastMovedAt;
    }

    public boolean isStuck() {
        return this.stuck;
    }
}
