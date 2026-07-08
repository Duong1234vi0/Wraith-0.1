/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.player.autoMine.controllers;

import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.player.autoMine.region.states.RegionBlockMaps;
import code.alphadlc.features.impl.player.autoMine.region.states.RegionComparators;
import code.alphadlc.features.impl.player.autoMine.region.states.RegionState;
import code.alphadlc.features.impl.player.autoMine.region.utils.WorldUtility;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.geometry.Render3D;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2680;

public class RegionController
implements QuickImports {
    private final RegionState regionState;
    private long lastScanTime = -1L;
    private class_2338 targetBlock = null;
    static final int MAX_REACH_UP = 4;
    static final int MAX_REACH_DOWN = 3;

    public RegionController(RegionState regionState) {
        this.regionState = regionState;
    }

    public static RegionState createRegion(class_2338 min, class_2338 max) {
        return new RegionState(min, max);
    }

    public static boolean isValidBlockState(class_2680 blockState) {
        return blockState != null && !blockState.method_26215() && RegionBlockMaps.IMMUTABLE_BLOCK_MAP.containsKey(blockState.method_26204());
    }

    public void onTick() {
        this.targetBlock = null;
        class_2338 playerPos = RegionController.mc.field_1724.method_24515();
        if (System.currentTimeMillis() - this.lastScanTime > 100L || this.regionState.getRandStack().isEmpty()) {
            this.regionState.getRandStack().clear();
            List<class_2338> sorted = this.regionState.findBlocksSortedByDistance(playerPos, RegionController::isValidBlockState);
            sorted.forEach(this.regionState.getRandStack()::add);
            this.lastScanTime = System.currentTimeMillis();
        }
        this.updateTargetBlock();
    }

    public void onWorldRender(WorldRenderEvent worldRenderEvent) {
        Render3D.drawBox(this.regionState.asBox(), 0x25FFFFFF, 2.0f);
        this.drawTargetBlock();
    }

    public void onWorldChange() {
        this.regionState.getRandStack().clear();
        this.targetBlock = null;
    }

    private void drawTargetBlock() {
        class_2680 blockState;
        if (this.targetBlock != null && (blockState = WorldUtility.fromBlockPos(this.targetBlock)) != null && RegionBlockMaps.IMMUTABLE_BLOCK_MAP.containsKey(blockState.method_26204())) {
            Render3D.drawBox(new class_238(this.targetBlock), ColorAssist.setAlpha(RegionBlockMaps.IMMUTABLE_BLOCK_MAP.get(blockState.method_26204()).color(), 255), 2.0f);
        }
    }

    private void updateTargetBlock() {
        if (this.regionState.getRandStack().isEmpty()) {
            return;
        }
        class_2338 playerPos = RegionController.mc.field_1724.method_24515();
        List<class_2338> reachable = this.regionState.getRandStack().stream().filter(pos -> {
            double vertDist = WorldUtility.verticalDistance(playerPos, pos);
            return vertDist <= 4.0 && vertDist >= -3.0;
        }).toList();
        if (reachable.isEmpty()) {
            return;
        }
        this.targetBlock = reachable.stream().min(Comparator.comparingDouble(RegionComparators::score)).map(class_2338::method_10062).orElse(null);
    }

    public RegionState getRegionState() {
        return this.regionState;
    }

    public long getLastScanTime() {
        return this.lastScanTime;
    }

    public class_2338 getTargetBlock() {
        return this.targetBlock;
    }
}
