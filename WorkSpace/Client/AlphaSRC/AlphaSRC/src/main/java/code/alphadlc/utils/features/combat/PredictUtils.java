/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.combat;

import code.alphadlc.features.impl.combat.CombatModules;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class PredictUtils
implements QuickImports {
    private static final Map<UUID, PositionData> positionCache = new ConcurrentHashMap<UUID, PositionData>();

    public static void updateEntity(class_1309 entity) {
        PositionData data = positionCache.computeIfAbsent(entity.method_5667(), k -> new PositionData());
        data.update(entity.method_23317(), entity.method_23318(), entity.method_23321());
    }

    public static PositionData getData(class_1309 entity) {
        return positionCache.get(entity.method_5667());
    }

    public static class_243 predict(class_1309 entity, int ticks, float extraForward, boolean isMeFlying) {
        PositionData data = PredictUtils.getData(entity);
        class_243 pos = new class_243(entity.method_23317(), entity.method_23318() + (double)(entity.method_5751() / 2.0f), entity.method_23321());
        if (data == null) {
            return PredictUtils.predictElytraPhysics(entity, pos, ticks);
        }
        class_243 forward = data.getResolvedForward();
        double speed = data.getLastSpeed();
        boolean isHighSpeed = data.isSpeedChanged();
        if (entity.method_6128()) {
            double horizontalSpeed = Math.hypot(forward.field_1352, forward.field_1350) * 20.0;
            double verticalSpeed = Math.abs(forward.field_1351) * 20.0;
            if (horizontalSpeed <= 5.0 && verticalSpeed <= 5.0) {
                return pos;
            }
            boolean shouldPredict = isMeFlying && entity.method_6128() && isHighSpeed;
            float predictMultiplier = shouldPredict ? (float)(ticks + 2) + extraForward : (float)ticks;
            class_243 linearPredict = pos.method_1019(forward.method_18805((double)predictMultiplier, (double)predictMultiplier, (double)predictMultiplier));
            class_243 physicsPredict = PredictUtils.predictElytraPhysics(entity, pos, ticks);
            double weight = class_3532.method_15350((double)(speed / 50.0), (double)0.3, (double)0.9);
            return new class_243(class_3532.method_16436((double)weight, (double)physicsPredict.field_1352, (double)linearPredict.field_1352), class_3532.method_16436((double)weight, (double)physicsPredict.field_1351, (double)linearPredict.field_1351), class_3532.method_16436((double)weight, (double)physicsPredict.field_1350, (double)linearPredict.field_1350));
        }
        if (speed > 1.0) {
            return pos.method_1019(forward.method_18805((double)ticks, (double)ticks, (double)ticks));
        }
        return pos;
    }

    public static class_243 predict(class_1309 entity, class_243 pos, int ticks) {
        PositionData data = PredictUtils.getData(entity);
        if (data != null && entity.method_6128()) {
            class_243 forward = data.getResolvedForward();
            double horizontalSpeed = Math.hypot(forward.field_1352, forward.field_1350) * 20.0;
            double verticalSpeed = Math.abs(forward.field_1351) * 20.0;
            if (horizontalSpeed <= 5.0 && verticalSpeed <= 5.0) {
                return pos;
            }
            return pos.method_1019(forward.method_18805((double)ticks, (double)ticks, (double)ticks));
        }
        return PredictUtils.predictElytraPhysics(entity, pos, ticks);
    }

    public static class_243 predictElytraPhysics(class_1309 entity, class_243 pos, int ticks) {
        class_243 velocity = entity.method_18798();
        if (!entity.method_6128()) {
            return pos.method_1019(velocity.method_18805((double)ticks, (double)ticks, (double)ticks));
        }
        double horizontalDelta = Math.hypot(entity.field_6014 - entity.method_23317(), entity.field_5969 - entity.method_23321()) * 20.0;
        double verticalDelta = Math.abs(entity.method_23318() - entity.field_6036) * 20.0;
        if (horizontalDelta <= 5.0 && verticalDelta <= 5.0) {
            return pos;
        }
        for (int i = 0; i < ticks; ++i) {
            class_243 rotation = entity.method_5720();
            float pitchRad = (float)Math.toRadians(entity.method_36455());
            double horizontalSpeed = Math.sqrt(velocity.field_1352 * velocity.field_1352 + velocity.field_1350 * velocity.field_1350);
            double velocityLength = velocity.method_1033();
            float cos = class_3532.method_15362((float)pitchRad);
            cos = (float)((double)(cos * cos) * Math.min(1.0, rotation.method_1033() / 0.4));
            velocity = velocity.method_1031(0.0, -0.08 * (-1.0 + (double)cos * 0.75), 0.0);
            if (velocity.field_1351 < 0.0 && horizontalSpeed > 0.0) {
                double d5 = velocity.field_1351 * -0.1 * (double)cos;
                velocity = velocity.method_1031(rotation.field_1352 * d5 / horizontalSpeed, d5, rotation.field_1350 * d5 / horizontalSpeed);
            }
            if (pitchRad < 0.0f && horizontalSpeed > 0.0) {
                double lift = velocityLength * (double)(-class_3532.method_15374((float)pitchRad)) * 0.04;
                velocity = velocity.method_1031(-rotation.field_1352 * lift / horizontalSpeed, lift * 3.2, -rotation.field_1350 * lift / horizontalSpeed);
            }
            if (horizontalSpeed > 0.0) {
                velocity = velocity.method_1031((rotation.field_1352 / horizontalSpeed * velocityLength - velocity.field_1352) * 0.1, 0.0, (rotation.field_1350 / horizontalSpeed * velocityLength - velocity.field_1350) * 0.1);
            }
            velocity = velocity.method_18805(0.99, 0.98, 0.99);
            pos = pos.method_1019(velocity);
        }
        return pos;
    }

    public static class_243 bypasselytrahacking(class_1309 target) {
        class_243 interpolatedRotation = class_243.method_1030((float)target.method_53829(), (float)target.method_53831());
        class_243 rotationVector = target.method_5720();
        class_243 relativePos = target.method_19538().method_1031(0.0, (double)(target.method_17682() * 0.6f), 0.0).method_1020(PredictUtils.mc.field_1724.method_33571());
        class_243 blendedDirection = interpolatedRotation.method_1029().method_35590(rotationVector, interpolatedRotation.method_1033());
        return relativePos.method_1019(blendedDirection.method_1029().method_1021((double)CombatModules.getElytraForward()));
    }

    public static void cleanup() {
        long now = System.currentTimeMillis();
        positionCache.entrySet().removeIf(e -> now - ((PositionData)e.getValue()).getLastUpdate() > 10000L);
    }

    public static void clear() {
        positionCache.clear();
    }

    public static class PositionData {
        private double serverX;
        private double serverY;
        private double serverZ;
        private double prevServerX;
        private double prevServerY;
        private double prevServerZ;
        private double backUpX;
        private double backUpY;
        private double backUpZ;
        private double lastSpeed;
        private double prevSpeed;
        private long lastUpdate;

        public class_243 getResolvedPos() {
            return new class_243(this.serverX, this.serverY, this.serverZ);
        }

        public class_243 getResolvedForward() {
            return new class_243(this.serverX - this.prevServerX, this.serverY - this.prevServerY, this.serverZ - this.prevServerZ);
        }

        public void update(double x, double y, double z) {
            this.backUpX = this.prevServerX;
            this.backUpY = this.prevServerY;
            this.backUpZ = this.prevServerZ;
            this.prevServerX = this.serverX;
            this.prevServerY = this.serverY;
            this.prevServerZ = this.serverZ;
            this.serverX = x;
            this.serverY = y;
            this.serverZ = z;
            this.prevSpeed = this.lastSpeed;
            this.lastSpeed = this.getResolvedForward().method_1033() * 20.0;
            this.lastUpdate = System.currentTimeMillis();
        }

        public boolean isSpeedChanged() {
            return this.lastSpeed >= 20.0 || this.lastSpeed != this.prevSpeed && this.lastSpeed == 0.0;
        }

        public double getServerX() {
            return this.serverX;
        }

        public double getServerY() {
            return this.serverY;
        }

        public double getServerZ() {
            return this.serverZ;
        }

        public double getPrevServerX() {
            return this.prevServerX;
        }

        public double getPrevServerY() {
            return this.prevServerY;
        }

        public double getPrevServerZ() {
            return this.prevServerZ;
        }

        public double getBackUpX() {
            return this.backUpX;
        }

        public double getBackUpY() {
            return this.backUpY;
        }

        public double getBackUpZ() {
            return this.backUpZ;
        }

        public double getLastSpeed() {
            return this.lastSpeed;
        }

        public double getPrevSpeed() {
            return this.prevSpeed;
        }

        public long getLastUpdate() {
            return this.lastUpdate;
        }
    }
}
