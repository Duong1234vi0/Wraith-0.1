/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_1268
 *  net.minecraft.class_1810
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_239
 *  net.minecraft.class_243
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.player.autoMine.controllers;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.misc.creeperFarm.WalkDirectionUtil;
import code.alphadlc.features.impl.player.autoMine.controllers.RegionController;
import code.alphadlc.features.impl.player.autoMine.mine.MineMovementBrainer;
import code.alphadlc.features.impl.player.autoMine.mine.state.MineState;
import code.alphadlc.features.impl.player.autoMine.receivers.AutoMineModule;
import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.rotations.constructor.LinearConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.inv.InventoryResult;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.inv.InventoryToolkit;
import code.alphadlc.utils.math.task.TaskPriority;
import net.minecraft.class_10185;
import net.minecraft.class_1268;
import net.minecraft.class_1810;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public class MineController
implements QuickImports {
    private final RegionController regionController;
    private final MineState mineState;
    private final MineMovementBrainer brainer;
    private final LinearConstructor spAngle = new LinearConstructor();
    private long lastSwapTime = -1L;
    private long lastBreakUpdate = -1L;
    private class_2338 lastBreakingBlockPos = null;

    public MineController(RegionController regionController) {
        this.regionController = regionController;
        this.mineState = new MineState();
        this.brainer = new MineMovementBrainer();
    }

    public void onWorldRender(WorldRenderEvent worldRenderEvent) {
    }

    public void onDraw(DrawEvent drawEvent) {
        String status;
        double x = (double)mc.method_22683().method_4486() / 2.0 + 10.0;
        double y = (double)mc.method_22683().method_4502() / 2.0 + 38.0;
        String string = status = this.brainer.isPathClear() ? "Walking" : "Digging";
        if (this.brainer.isStuck()) {
            status = "STUCK \u2014 Digging";
        }
        if (this.regionController.getTargetBlock() == null) {
            status = "No target";
        }
    }

    public void onTick() {
        this.mineState.onTick();
        this.swapPickaxe();
        class_2338 targetBlock = this.regionController.getTargetBlock();
        this.brainer.update(targetBlock);
        if (targetBlock == null) {
            MineController.mc.field_1690.field_1886.method_23481(false);
            return;
        }
        class_2338 blockToLookAt = this.getLookTarget(targetBlock);
        class_2338 blockToMine = null;
        class_2350 hitSide = class_2350.field_11036;
        class_239 class_2392 = MineController.mc.field_1765;
        if (class_2392 instanceof class_3965) {
            class_3965 blockHitResult = (class_3965)class_2392;
            class_2338 hitPos = blockHitResult.method_17777();
            double hitDist = MineController.mc.field_1724.method_33571().method_1022(class_243.method_24953((class_2382)hitPos));
            double targetDist = MineController.mc.field_1724.method_33571().method_1022(class_243.method_24953((class_2382)targetBlock));
            boolean shouldMine = false;
            if (this.brainer.getObstacleBlock() != null && hitPos.equals((Object)this.brainer.getObstacleBlock())) {
                shouldMine = true;
            } else if (hitPos.equals((Object)targetBlock)) {
                shouldMine = true;
            } else if (hitDist < 5.0 && RegionController.isValidBlockState(WorldUtility.fromBlockPos(hitPos))) {
                shouldMine = true;
            } else if (hitDist < 5.0 && this.isNearTarget(hitPos, targetBlock)) {
                shouldMine = true;
            } else if (hitDist < 5.0 && (this.brainer.isShouldDigObstacle() || this.brainer.isStuck())) {
                shouldMine = true;
            } else if (hitDist < targetDist && hitDist < 5.0 && !WorldUtility.fromBlockPos(hitPos).method_26215()) {
                shouldMine = true;
            } else if (targetDist < 4.5 && hitDist < 5.0 && !WorldUtility.fromBlockPos(hitPos).method_26215()) {
                shouldMine = true;
            }
            if (shouldMine) {
                blockToMine = hitPos.method_10062();
                hitSide = blockHitResult.method_17780();
                this.lastBreakingBlockPos = blockToMine;
                this.lastBreakUpdate = System.currentTimeMillis();
            }
        } else {
            blockToMine = this.lastBreakingBlockPos != null && System.currentTimeMillis() - this.lastBreakUpdate < 500L ? this.lastBreakingBlockPos : blockToLookAt;
        }
        if (blockToMine != null && MineController.mc.field_1687 != null && !MineController.mc.field_1687.method_8320(blockToMine).method_26215()) {
            MineController.mc.field_1690.field_1886.method_23481(true);
            MineController.mc.field_1761.method_2902(blockToMine, hitSide);
            MineController.mc.field_1724.method_6104(class_1268.field_5808);
        } else if (System.currentTimeMillis() - this.lastBreakUpdate > 200L) {
            MineController.mc.field_1690.field_1886.method_23481(false);
        }
    }

    private boolean isNearTarget(class_2338 hitPos, class_2338 targetBlock) {
        return Math.abs(hitPos.method_10263() - targetBlock.method_10263()) <= 2 && Math.abs(hitPos.method_10264() - targetBlock.method_10264()) <= 2 && Math.abs(hitPos.method_10260() - targetBlock.method_10260()) <= 2;
    }

    public void onInput(InputEvent inputEvent) {
        class_2338 targetBlock = this.regionController.getTargetBlock();
        if (targetBlock == null) {
            return;
        }
        class_2338 obstacle = this.brainer.getObstacleBlock();
        if (obstacle != null && this.brainer.isShouldDigObstacle()) {
            float clientYaw = TurnsConnection.INSTANCE.getRotation().getYaw();
            class_243 toObstacle = class_243.method_24953((class_2382)obstacle).method_1020(MineController.mc.field_1724.method_19538());
            class_10185 pi = WalkDirectionUtil.fromVec3d(toObstacle, clientYaw);
            inputEvent.setDirectional(pi.comp_3159(), false, false, false);
            return;
        }
        class_2338 moveTarget = this.brainer.getMoveTarget();
        if (moveTarget == null) {
            moveTarget = targetBlock;
        }
        float clientYaw = TurnsConnection.INSTANCE.getRotation().getYaw();
        class_243 targetDirection = class_243.method_24953((class_2382)moveTarget).method_1020(MineController.mc.field_1724.method_19538());
        class_10185 playerInput = WalkDirectionUtil.fromVec3d(targetDirection, clientYaw);
        inputEvent.setDirectional(playerInput.comp_3159(), playerInput.comp_3160(), playerInput.comp_3161(), playerInput.comp_3162());
    }

    public void onRotationUpdate(RotationUpdateEvent rotationUpdateEvent) {
        class_2338 targetBlock = this.regionController.getTargetBlock();
        if (targetBlock == null) {
            return;
        }
        class_2338 lookAt = this.getLookTarget(targetBlock);
        Turns turns = MathAngle.calculateAngle(class_243.method_24953((class_2382)lookAt));
        TurnsConnection.INSTANCE.rotateTo(turns, new TurnsConfig(this.spAngle, true, false), TaskPriority.HIGH_IMPORTANCE_1, AlphaDLC.getInstance().getModuleProvider().get(AutoMineModule.class));
    }

    private class_2338 getLookTarget(class_2338 targetBlock) {
        class_2338 obstacle = this.brainer.getObstacleBlock();
        if (obstacle != null && this.brainer.isShouldDigObstacle()) {
            return obstacle;
        }
        return targetBlock;
    }

    public void onWorldChange() {
        this.brainer.reset();
        this.lastBreakingBlockPos = null;
    }

    private void swapPickaxe() {
        if (!(MineController.mc.field_1724.method_6047().method_7909() instanceof class_1810) && System.currentTimeMillis() - this.lastSwapTime > 2000L) {
            InventoryResult inventoryResult = InventoryToolkit.findInInventory(itemStack -> itemStack.method_7909() instanceof class_1810);
            if (inventoryResult == null || inventoryResult.slot() == -1 || inventoryResult.slot() - 36 == MineController.mc.field_1724.method_31548().field_7545) {
                return;
            }
            if (inventoryResult.slot() - 36 != 0) {
                InventoryTask.swapSlots(inventoryResult.slot(), 0);
            }
            if (inventoryResult.slot() - 36 < 9 && inventoryResult.slot() - 36 != MineController.mc.field_1724.method_31548().field_7545) {
                MineController.mc.field_1724.method_31548().field_7545 = Math.clamp((long)(inventoryResult.slot() - 36), 0, 8);
            }
            this.lastSwapTime = System.currentTimeMillis();
        }
    }

    public RegionController getRegionController() {
        return this.regionController;
    }

    public MineState getMineState() {
        return this.mineState;
    }

    public MineMovementBrainer getBrainer() {
        return this.brainer;
    }

    public LinearConstructor getSpAngle() {
        return this.spAngle;
    }

    public long getLastSwapTime() {
        return this.lastSwapTime;
    }

    public long getLastBreakUpdate() {
        return this.lastBreakUpdate;
    }

    public class_2338 getLastBreakingBlockPos() {
        return this.lastBreakingBlockPos;
    }
}
