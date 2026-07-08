/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1934
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2353
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_3486
 *  net.minecraft.class_3532
 *  net.minecraft.class_3610
 *  net.minecraft.class_640
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.PlayerTravelEvent;
import code.alphadlc.events.player.SwimmingEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.input.MoveUtil;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1934;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_640;

public class WaterSpeed
extends Module {
    private final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u043e\u0431\u0445\u043e\u0434\u0430").value("Vanilla", "FunTime", "Wall", "Grim");
    private final SelectSetting wallModeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u043e\u0442 \u0441\u0442\u0435\u043d", "\u0420\u0435\u0436\u0438\u043c \u043e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u044f \u043e\u0442 \u0441\u0442\u0435\u043d").value("MetaHvH", "FunTime").visible(() -> this.modeSetting.isSelected("Wall"));
    private final SliderSettings ftSpeedSetting = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c FunTime").range(0.1f, 0.5f).setValue(0.2f).visible(() -> this.modeSetting.isSelected("FunTime"));
    private double wallVerticalBoost = 0.05;
    private double wallRadius;
    private double wallBoost;

    public WaterSpeed() {
        super("WaterSpeed", "WaterSpeed", ModuleCategory.MOVEMENT);
        this.setup(this.modeSetting, this.wallModeSetting, this.ftSpeedSetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (WaterSpeed.mc.field_1724 == null || WaterSpeed.mc.field_1687 == null) {
            return;
        }
        boolean isVanillaServer = Network.isVanilla();
        if (isVanillaServer || mc.method_1562() != null) {
            for (class_1657 player : WaterSpeed.mc.field_1687.method_18456()) {
                class_640 entry;
                if (player == null || player == WaterSpeed.mc.field_1724) continue;
                boolean isSpectator = player.method_7325();
                class_640 class_6402 = entry = mc.method_1562() != null ? mc.method_1562().method_2871(player.method_5667()) : null;
                if (entry != null) {
                    class_1934 gameMode = entry.method_2958();
                    boolean bl = isSpectator = gameMode == class_1934.field_9219;
                }
                if (!isVanillaServer && !isSpectator) continue;
                String playerName = player.method_5477().getString();
                String status = isSpectator ? "Spectator" : (isVanillaServer ? "Vanilla \u0441\u0435\u0440\u0432\u0435\u0440" : "");
                if (status.isEmpty()) continue;
                Logger.info(String.format("[WaterSpeed] \u0418\u0433\u0440\u043e\u043a %s: %s (UUID: %s)", playerName, status, player.method_5667()));
            }
        }
        switch (this.modeSetting.getSelected()) {
            case "Vanilla": {
                this.handleVanilla();
                break;
            }
            case "FunTime": {
                this.handleFunTime();
                break;
            }
            case "Wall": {
                this.handleWall();
                break;
            }
            case "Grim": {
                this.handleGrim();
            }
        }
    }

    @EventHandler
    public void onSwimming(SwimmingEvent e) {
        if (this.modeSetting.isSelected("FunTime")) {
            if (WaterSpeed.mc.field_1690.field_1903.method_1434()) {
                float pitch = TurnsConnection.INSTANCE.getRotation().getPitch();
                float boost = pitch >= 0.0f ? class_3532.method_15363((float)(pitch / 45.0f), (float)1.0f, (float)2.0f) : 0.8f;
                e.getVector().field_1351 = 0.5 * (double)boost;
            } else if (WaterSpeed.mc.field_1690.field_1832.method_1434()) {
                e.getVector().field_1351 = -0.8;
            }
        }
    }

    @EventHandler
    public void onTravel(PlayerTravelEvent e) {
        if (!e.isPre()) {
            return;
        }
        if (WaterSpeed.mc.field_1724 == null || !WaterSpeed.mc.field_1724.method_5799()) {
            return;
        }
        if (this.modeSetting.isSelected("Wall") && WaterSpeed.mc.field_1724.field_5976) {
            class_243 motion = e.getMotion();
            e.setMotion(new class_243(motion.field_1352 * 1.1, motion.field_1351, motion.field_1350 * 1.1));
        }
    }

    private void handleVanilla() {
        boolean forward;
        if (WaterSpeed.mc.field_1724.method_5799() && !WaterSpeed.mc.field_1724.method_5681() && (forward = WaterSpeed.mc.field_1690.field_1894.method_1434())) {
            class_243 velocity = WaterSpeed.mc.field_1724.method_18798();
            WaterSpeed.mc.field_1724.method_18800(velocity.field_1352 * 1.00061, velocity.field_1351, velocity.field_1350 * 1.00061);
        }
    }

    private void handleFunTime() {
        if (WaterSpeed.mc.field_1724.method_5799() && WaterSpeed.mc.field_1724.method_24828()) {
            WaterSpeed.mc.field_1724.method_6043();
            WaterSpeed.mc.field_1724.method_18800(WaterSpeed.mc.field_1724.method_18798().field_1352, (double)this.ftSpeedSetting.getValue(), WaterSpeed.mc.field_1724.method_18798().field_1350);
        }
    }

    private void handleWall() {
        if (this.wallModeSetting.isSelected("FunTime")) {
            this.wallRadius = 0.35;
            this.wallBoost = 0.05;
        } else if (this.wallModeSetting.isSelected("MetaHvH")) {
            this.wallRadius = 0.35;
            this.wallBoost = 0.4;
        }
        if (!WaterSpeed.mc.field_1724.method_5799()) {
            return;
        }
        if (!WaterSpeed.mc.field_1724.field_5976) {
            return;
        }
        if (!this.isWaterNearFeet()) {
            return;
        }
        class_2350 collisionFace = this.getCollisionFace();
        if (collisionFace == null) {
            return;
        }
        class_243 pushDir = new class_243((double)(-collisionFace.method_10148()), 0.0, (double)(-collisionFace.method_10165()));
        if (pushDir.method_1027() < 1.0E-6) {
            return;
        }
        double[] moveDir = this.calculateDirection(WaterSpeed.mc.field_1724.field_3913.field_3905, WaterSpeed.mc.field_1724.field_3913.field_3907, this.wallBoost);
        class_243 combined = new class_243(moveDir[0], 0.0, moveDir[1]).method_1019(pushDir.method_1029().method_1021(this.wallBoost * 0.6));
        class_243 velocity = WaterSpeed.mc.field_1724.method_18798();
        class_243 result = velocity.method_1019(combined);
        double vertical = Math.max(velocity.field_1351, this.wallVerticalBoost);
        WaterSpeed.mc.field_1724.method_18800(result.field_1352, vertical, result.field_1350);
        WaterSpeed.mc.field_1724.field_6017 = 0.0f;
    }

    private void handleGrim() {
        if (WaterSpeed.mc.field_1724 == null || WaterSpeed.mc.field_1687 == null) {
            return;
        }
        if (!WaterSpeed.mc.field_1690.field_1903.method_1434()) {
            return;
        }
        if (WaterSpeed.mc.field_1724.method_5799()) {
            class_2338 playerPos = WaterSpeed.mc.field_1724.method_24515();
            double waterLevel = this.findWaterSurfaceLevel(playerPos);
            double playerEyeY = WaterSpeed.mc.field_1724.method_23318() + (double)WaterSpeed.mc.field_1724.method_5751();
            if (playerEyeY >= waterLevel - 0.35 && playerEyeY <= waterLevel + 0.35) {
                WaterSpeed.mc.field_1724.method_18800(WaterSpeed.mc.field_1724.method_18798().field_1352, 2.5, WaterSpeed.mc.field_1724.method_18798().field_1350);
                MoveUtil.setMotion(MoveUtil.getMotion() * 5.0);
            } else if (playerEyeY < waterLevel - 0.35) {
                WaterSpeed.mc.field_1724.method_18800(WaterSpeed.mc.field_1724.method_18798().field_1352, 0.12, WaterSpeed.mc.field_1724.method_18798().field_1350);
            }
        }
    }

    private double findWaterSurfaceLevel(class_2338 startPos) {
        class_2338 checkPos = startPos;
        for (int i = 0; i < 5; ++i) {
            class_3610 fluidState = WaterSpeed.mc.field_1687.method_8316(checkPos);
            class_3610 aboveFluid = WaterSpeed.mc.field_1687.method_8316(checkPos.method_10084());
            if (fluidState.method_15767(class_3486.field_15517) && !aboveFluid.method_15767(class_3486.field_15517)) {
                return (float)checkPos.method_10264() + fluidState.method_15763((class_1922)WaterSpeed.mc.field_1687, checkPos);
            }
            checkPos = checkPos.method_10084();
        }
        class_3610 fluidState = WaterSpeed.mc.field_1687.method_8316(startPos);
        if (fluidState.method_15767(class_3486.field_15517)) {
            return (float)startPos.method_10264() + fluidState.method_15763((class_1922)WaterSpeed.mc.field_1687, startPos);
        }
        return (double)startPos.method_10264() + 1.0;
    }

    private class_2350 getCollisionFace() {
        class_238 box = WaterSpeed.mc.field_1724.method_5829();
        for (class_2350 dir : class_2350.class_2353.field_11062) {
            class_238 shifted = box.method_989((double)dir.method_10148() * 0.05, 0.0, (double)dir.method_10165() * 0.05);
            boolean hasCollision = WaterSpeed.mc.field_1687.method_20812((class_1297)WaterSpeed.mc.field_1724, shifted).iterator().hasNext();
            if (!hasCollision) continue;
            return dir;
        }
        return null;
    }

    private boolean isWaterNearFeet() {
        class_238 box = WaterSpeed.mc.field_1724.method_5829();
        int minX = class_3532.method_15357((double)(box.field_1323 - this.wallRadius));
        int maxX = class_3532.method_15357((double)(box.field_1320 + this.wallRadius));
        int minY = class_3532.method_15357((double)(box.field_1322 - 0.2));
        int maxY = class_3532.method_15357((double)(box.field_1322 + 0.2));
        int minZ = class_3532.method_15357((double)(box.field_1321 - this.wallRadius));
        int maxZ = class_3532.method_15357((double)(box.field_1324 + this.wallRadius));
        class_2338.class_2339 mutablePos = new class_2338.class_2339();
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    mutablePos.method_10103(x, y, z);
                    class_2680 state = WaterSpeed.mc.field_1687.method_8320((class_2338)mutablePos);
                    if (!state.method_26227().method_15767(class_3486.field_15517) && !state.method_26227().method_15767(class_3486.field_15518)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private double[] calculateDirection(float forward, float sideways, double distance) {
        float yaw = WaterSpeed.mc.field_1724.method_36454();
        float sinYaw = class_3532.method_15374((float)((float)Math.toRadians(yaw + 90.0f)));
        float cosYaw = class_3532.method_15362((float)((float)Math.toRadians(yaw + 90.0f)));
        double xMovement = (double)forward * distance * (double)cosYaw + (double)sideways * distance * (double)sinYaw;
        double zMovement = (double)forward * distance * (double)sinYaw - (double)sideways * distance * (double)cosYaw;
        return new double[]{xMovement, zMovement};
    }

    public SelectSetting getModeSetting() {
        return this.modeSetting;
    }

    public SelectSetting getWallModeSetting() {
        return this.wallModeSetting;
    }

    public SliderSettings getFtSpeedSetting() {
        return this.ftSpeedSetting;
    }

    public double getWallVerticalBoost() {
        return this.wallVerticalBoost;
    }

    public double getWallRadius() {
        return this.wallRadius;
    }

    public double getWallBoost() {
        return this.wallBoost;
    }
}
