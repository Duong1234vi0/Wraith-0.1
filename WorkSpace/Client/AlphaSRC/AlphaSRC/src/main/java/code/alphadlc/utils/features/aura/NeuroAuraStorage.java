/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.aura;

import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.NeuroPattern;
import code.alphadlc.utils.features.rotate.Rotation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class NeuroAuraStorage
implements QuickImports {
    private static final long MIN_RECORD_INTERVAL = 50L;
    private static final int MAX_FRAMES = 20000;
    private static final String PATTERNS_DIRECTORY = "data_patterns";
    private static final String LEGACY_PATTERNS_DIRECTORY = "neuro_patterns";
    private static final String PRIMARY_EXTENSION = ".data";
    private static final String LEGACY_EXTENSION = ".neuro";
    private static final float SYNC_SCORE_THRESHOLD = 45.0f;
    private static final float MAX_YAW_CORRECTION = 8.0f;
    private static final float MAX_PITCH_CORRECTION = 6.0f;
    private final List<NeuroPattern> recordedPatterns = new CopyOnWriteArrayList<NeuroPattern>();
    private boolean isRecording = false;
    private boolean isUsingNeuro = false;
    private boolean showStats = true;
    private String currentPatternName = null;
    private String lastDebugMessage = "\u0413\u043e\u0442\u043e\u0432!";
    private int recordedThisSession = 0;
    private long lastRecordTime = 0L;
    private float prevRecordYaw = 0.0f;
    private float prevRecordPitch = 0.0f;
    private boolean hasRecordedBefore = false;
    private final List<Frame> frames = new CopyOnWriteArrayList<Frame>();
    private int playbackIndex = -1;
    private int ticksSinceSync = 0;
    private float smoothedYawDelta = 0.0f;
    private float smoothedPitchDelta = 0.0f;
    private float smoothedOutputYaw = Float.NaN;
    private float smoothedOutputPitch = Float.NaN;
    private float yawSpeedFactor = 1.0f;
    private float pitchSpeedFactor = 1.0f;
    private int speedProfileTicks = 0;
    private class_243 currentAimPoint = null;
    private class_243 targetRandomPoint = null;
    private int aimPointTicks = 0;
    private class_1309 lastAimTarget = null;
    private boolean lastWasIdle = true;
    private int attackCount = 0;
    private float randomXOffset = 0.0f;
    private float randomYRatio = 0.66f;
    private float randomZOffset = 0.0f;

    public NeuroAuraStorage() {
        this.createPatternsDirectory();
    }

    private void createPatternsDirectory() {
        try {
            Path path = Paths.get(PATTERNS_DIRECTORY, new String[0]);
            if (!Files.exists(path, new LinkOption[0])) {
                Files.createDirectories(path, new FileAttribute[0]);
            }
        }
        catch (IOException e) {
            this.lastDebugMessage = "\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0430\u043f\u043a\u0438";
        }
    }

    public void recordTick(class_1309 target, float currentYaw, float currentPitch) {
        boolean hasTarget;
        if (!this.isRecording || NeuroAuraStorage.mc.field_1724 == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastRecordTime < 50L) {
            return;
        }
        float deltaYaw = 0.0f;
        float deltaPitch = 0.0f;
        if (this.hasRecordedBefore) {
            deltaYaw = class_3532.method_15393((float)(currentYaw - this.prevRecordYaw));
            deltaPitch = currentPitch - this.prevRecordPitch;
        }
        float angleYaw = 0.0f;
        float anglePitch = 0.0f;
        double distance = 0.0;
        boolean bl = hasTarget = target != null;
        if (hasTarget) {
            AimData aimData = this.getAimData(target, currentYaw, currentPitch, null, true);
            angleYaw = aimData.angleYaw;
            anglePitch = aimData.anglePitch;
            distance = aimData.distance;
        }
        Frame frame = new Frame();
        frame.deltaYaw = deltaYaw;
        frame.deltaPitch = deltaPitch;
        frame.angleYaw = angleYaw;
        frame.anglePitch = anglePitch;
        frame.distance = distance;
        frame.hasTarget = hasTarget;
        frame.smoothness = this.calculateSmoothness(deltaYaw, deltaPitch);
        this.frames.add(frame);
        while (this.frames.size() > 20000) {
            this.frames.remove(0);
        }
        if (hasTarget) {
            boolean crit = NeuroAuraStorage.mc.field_1724.field_6017 > 0.0f && !NeuroAuraStorage.mc.field_1724.method_24828();
            String type = target instanceof class_1657 ? "player" : "mob";
            this.recordedPatterns.add(new NeuroPattern(angleYaw, anglePitch, deltaYaw, deltaPitch, distance, crit, 0.0, type, frame.smoothness));
            while (this.recordedPatterns.size() > 20000) {
                this.recordedPatterns.remove(0);
            }
        }
        this.prevRecordYaw = currentYaw;
        this.prevRecordPitch = currentPitch;
        this.hasRecordedBefore = true;
        this.lastRecordTime = now;
        ++this.recordedThisSession;
        if (this.recordedThisSession % 20 == 0) {
            this.lastDebugMessage = "\u00a7a\u0417\u0430\u043f\u0438\u0441\u044c: \u00a7f" + this.frames.size();
        }
    }

    public Rotation getNeuroRotation(class_1309 target, float currentYaw, float currentPitch, boolean idle) {
        boolean airborne;
        if (!this.isUsingNeuro || target == null || NeuroAuraStorage.mc.field_1724 == null || this.frames.isEmpty()) {
            this.resetState();
            return null;
        }
        if (!idle && this.lastWasIdle) {
            this.rollNewRandomPoint();
            ++this.attackCount;
        }
        this.lastWasIdle = idle;
        boolean needSync = this.playbackIndex < 0 || this.playbackIndex >= this.frames.size();
        AimData aimData = this.getAimData(target, currentYaw, currentPitch, null, idle);
        boolean bl = airborne = !NeuroAuraStorage.mc.field_1724.method_24828() || NeuroAuraStorage.mc.field_1724.method_18798().field_1351 != 0.0;
        if (Math.abs(aimData.angleYaw) > 110.0f) {
            needSync = true;
            this.smoothedYawDelta = 0.0f;
            this.smoothedPitchDelta = 0.0f;
            this.smoothedOutputYaw = currentYaw;
            this.smoothedOutputPitch = currentPitch;
        }
        if (!needSync && this.ticksSinceSync >= 5) {
            float distDiff;
            float pitchDiff;
            Frame currentFrame = this.frames.get(this.playbackIndex);
            float yawDiff = Math.abs(class_3532.method_15393((float)(currentFrame.angleYaw - aimData.angleYaw)));
            if (yawDiff + (pitchDiff = Math.abs(currentFrame.anglePitch - aimData.anglePitch)) + (distDiff = (float)Math.abs(currentFrame.distance - aimData.distance)) * 0.3f > 45.0f) {
                needSync = true;
            }
        }
        if (needSync) {
            this.playbackIndex = this.findBest(aimData.angleYaw, aimData.anglePitch, aimData.distance);
            this.ticksSinceSync = 0;
        }
        Frame frame = this.frames.get(this.playbackIndex);
        aimData = this.getAimData(target, currentYaw, currentPitch, frame, idle);
        float applyYaw = frame.deltaYaw;
        float applyPitch = frame.deltaPitch;
        this.updateSpeedProfile(idle, airborne, aimData);
        if (Math.abs(frame.angleYaw) > 3.0f && Math.abs(aimData.angleYaw) > 3.0f && Math.signum(frame.angleYaw) != Math.signum(aimData.angleYaw)) {
            applyYaw = -applyYaw;
        }
        if (Math.abs(frame.anglePitch) > 3.0f && Math.abs(aimData.anglePitch) > 3.0f && Math.signum(frame.anglePitch) != Math.signum(aimData.anglePitch)) {
            applyPitch = -applyPitch;
        }
        applyYaw = this.adaptRecordedDelta(applyYaw, aimData.angleYaw, frame.smoothness, idle, 8.0f);
        applyPitch = this.adaptRecordedDelta(applyPitch, aimData.anglePitch, frame.smoothness, idle, 6.0f);
        if (Math.abs(aimData.angleYaw) < 32.0f) {
            applyYaw = class_3532.method_16439((float)0.58f, (float)applyYaw, (float)aimData.angleYaw);
        }
        if (Math.abs(aimData.anglePitch) < 24.0f) {
            applyPitch = class_3532.method_16439((float)0.52f, (float)applyPitch, (float)aimData.anglePitch);
        }
        this.smoothedYawDelta = this.smoothDelta(this.smoothedYawDelta, applyYaw, frame.smoothness);
        this.smoothedPitchDelta = this.smoothDelta(this.smoothedPitchDelta, applyPitch, frame.smoothness);
        float quantizedYaw = this.quantizeToMouseStep(this.smoothedYawDelta, aimData.angleYaw);
        float quantizedPitch = this.quantizeToMouseStep(this.smoothedPitchDelta, aimData.anglePitch);
        float rawYaw = class_3532.method_15393((float)(currentYaw + (quantizedYaw += this.getMicroJitter(true, idle, airborne, aimData))));
        float rawPitch = class_3532.method_15363((float)(currentPitch + (quantizedPitch += this.getMicroJitter(false, idle, airborne, aimData))), (float)-90.0f, (float)90.0f);
        float finalYaw = this.smoothOutputRotation(rawYaw, currentYaw, frame.smoothness, idle, true);
        float finalPitch = this.smoothOutputRotation(rawPitch, currentPitch, frame.smoothness, idle, false);
        ++this.playbackIndex;
        ++this.ticksSinceSync;
        for (int skipped = 0; this.playbackIndex < this.frames.size() && !this.frames.get((int)this.playbackIndex).hasTarget && skipped < 5; ++skipped) {
            ++this.playbackIndex;
        }
        if (this.playbackIndex >= this.frames.size()) {
            float newAngleYaw = class_3532.method_15393((float)(aimData.perfectYaw - finalYaw));
            float newAnglePitch = aimData.perfectPitch - finalPitch;
            this.playbackIndex = this.findBest(newAngleYaw, newAnglePitch, aimData.distance);
            this.ticksSinceSync = 0;
        }
        this.lastDebugMessage = String.format("\u00a7a[%d/%d] dY%.2f dP%.2f", this.playbackIndex, this.frames.size(), Float.valueOf(quantizedYaw), Float.valueOf(quantizedPitch));
        return new Rotation(finalYaw, finalPitch);
    }

    private void rollNewRandomPoint() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        this.randomXOffset = r.nextFloat(-0.38f, 0.38f);
        this.randomYRatio = r.nextFloat(0.4f, 0.85f);
        this.randomZOffset = r.nextFloat(-0.38f, 0.38f);
    }

    private AimData getAimData(class_1309 target, float currentYaw, float currentPitch, Frame frame, boolean relaxed) {
        class_243 eyePos = NeuroAuraStorage.mc.field_1724.method_33571();
        class_243 point = this.selectAimPoint(target, relaxed);
        double distance = eyePos.method_1022(point);
        double dx = point.field_1352 - eyePos.field_1352;
        double dy = point.field_1351 - eyePos.field_1351;
        double dz = point.field_1350 - eyePos.field_1350;
        double distXZ = Math.sqrt(dx * dx + dz * dz);
        float perfectYaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
        float perfectPitch = (float)Math.toDegrees(Math.atan2(-dy, distXZ));
        AimData aimData = new AimData();
        aimData.targetPoint = point;
        aimData.distance = distance;
        aimData.perfectYaw = perfectYaw;
        aimData.perfectPitch = perfectPitch;
        aimData.angleYaw = class_3532.method_15393((float)(perfectYaw - currentYaw));
        aimData.anglePitch = perfectPitch - currentPitch;
        return aimData;
    }

    private float adaptRecordedDelta(float recordedDelta, float currentAngle, float smoothness, boolean idle, float maxCorrection) {
        float correctionWeight = idle ? 0.14f : 0.045f;
        float correctionLimit = idle ? maxCorrection * 0.65f : maxCorrection * 0.3f;
        float correction = class_3532.method_15363((float)(currentAngle - recordedDelta), (float)(-correctionLimit), (float)correctionLimit);
        float result = recordedDelta + correction * correctionWeight;
        if (Math.abs(currentAngle) < Math.abs(result) && Math.signum(currentAngle) == Math.signum(result)) {
            result = currentAngle;
        }
        float preserveFactor = idle ? class_3532.method_15363((float)(1.0f - smoothness * 0.22f), (float)0.8f, (float)0.97f) : class_3532.method_15363((float)(1.0f - smoothness * 0.1f), (float)0.91f, (float)0.99f);
        result *= preserveFactor;
        if (Math.abs(currentAngle) <= GCDUtil.getGCDValue()) {
            return currentAngle;
        }
        return result;
    }

    private class_243 selectAimPoint(class_1309 target, boolean relaxed) {
        if (target != this.lastAimTarget) {
            this.lastAimTarget = target;
            this.currentAimPoint = null;
            this.targetRandomPoint = null;
            this.aimPointTicks = 0;
            this.rollNewRandomPoint();
        }
        class_238 box = target.method_5829();
        class_243 eyePos = NeuroAuraStorage.mc.field_1724.method_33571();
        class_243 stablePoint = new class_243(box.method_1005().field_1352, box.field_1322 + box.method_17940() * 0.72, box.method_1005().field_1350);
        if (box.method_1014(0.12).method_1006(eyePos) || eyePos.method_1025(stablePoint) <= 2.25) {
            this.currentAimPoint = stablePoint;
            this.targetRandomPoint = stablePoint;
            this.aimPointTicks = 0;
            return stablePoint;
        }
        double xCenter = (box.field_1323 + box.field_1320) * 0.5;
        double zCenter = (box.field_1321 + box.field_1324) * 0.5;
        double halfW = box.method_17939() * 0.5;
        double halfD = box.method_17941() * 0.5;
        double height = box.method_17940();
        class_243 desired = new class_243(xCenter + halfW * (double)this.randomXOffset, box.field_1322 + height * (double)this.randomYRatio, zCenter + halfD * (double)this.randomZOffset);
        if (this.targetRandomPoint == null) {
            this.targetRandomPoint = desired;
        } else {
            float driftLerp = relaxed ? 0.13f : 0.07f;
            this.targetRandomPoint = new class_243(class_3532.method_16436((double)driftLerp, (double)this.targetRandomPoint.field_1352, (double)desired.field_1352), class_3532.method_16436((double)driftLerp, (double)this.targetRandomPoint.field_1351, (double)desired.field_1351), class_3532.method_16436((double)driftLerp, (double)this.targetRandomPoint.field_1350, (double)desired.field_1350));
        }
        if (this.currentAimPoint == null) {
            this.currentAimPoint = this.targetRandomPoint;
            this.aimPointTicks = 0;
            return this.currentAimPoint;
        }
        float pointLerp = relaxed ? 0.11f : 0.055f;
        this.currentAimPoint = new class_243(class_3532.method_16436((double)pointLerp, (double)this.currentAimPoint.field_1352, (double)this.targetRandomPoint.field_1352), class_3532.method_16436((double)pointLerp, (double)this.currentAimPoint.field_1351, (double)this.targetRandomPoint.field_1351), class_3532.method_16436((double)pointLerp, (double)this.currentAimPoint.field_1350, (double)this.targetRandomPoint.field_1350));
        ++this.aimPointTicks;
        return this.currentAimPoint;
    }

    private float smoothDelta(float current, float target, float smoothness) {
        float lerpFactor = class_3532.method_15363((float)(0.035f + (1.0f - smoothness) * 0.12f), (float)0.035f, (float)0.15f);
        return current + (target - current) * lerpFactor;
    }

    private float smoothOutputRotation(float targetRotation, float currentRotation, float smoothness, boolean idle, boolean yawAxis) {
        float delta;
        float previous;
        float f = previous = yawAxis ? this.smoothedOutputYaw : this.smoothedOutputPitch;
        if (Float.isNaN(previous)) {
            previous = currentRotation;
        }
        float f2 = delta = yawAxis ? class_3532.method_15393((float)(targetRotation - previous)) : targetRotation - previous;
        float maxStep = yawAxis ? (idle ? 1.68f : 0.86f) : (idle ? 1.26f : 0.62f);
        float lerpFactor = idle ? class_3532.method_15363((float)(0.08f + (1.0f - smoothness) * 0.09f), (float)0.08f, (float)0.17f) : class_3532.method_15363((float)(0.04f + (1.0f - smoothness) * 0.055f), (float)0.04f, (float)0.095f);
        float smoothed = previous + class_3532.method_15363((float)(delta * (lerpFactor *= yawAxis ? this.yawSpeedFactor : this.pitchSpeedFactor)), (float)(-(maxStep *= yawAxis ? this.yawSpeedFactor : this.pitchSpeedFactor)), (float)maxStep);
        if (yawAxis) {
            this.smoothedOutputYaw = smoothed = class_3532.method_15393((float)smoothed);
        } else {
            this.smoothedOutputPitch = smoothed = class_3532.method_15363((float)smoothed, (float)-90.0f, (float)90.0f);
        }
        return smoothed;
    }

    private void updateSpeedProfile(boolean idle, boolean airborne, AimData aimData) {
        if (this.speedProfileTicks > 0) {
            --this.speedProfileTicks;
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        float anglePressure = class_3532.method_15363((float)((Math.abs(aimData.angleYaw) + Math.abs(aimData.anglePitch)) / 35.0f), (float)0.0f, (float)1.0f);
        float baseYawMin = idle ? 1.06f : 0.96f;
        float baseYawMax = idle ? 1.34f : 1.12f;
        float basePitchMin = idle ? 1.0f : 0.9f;
        float basePitchMax = idle ? 1.24f : 1.05f;
        this.yawSpeedFactor = random.nextFloat(baseYawMin, baseYawMax + anglePressure * (idle ? 0.1f : 0.16f));
        this.pitchSpeedFactor = random.nextFloat(basePitchMin, basePitchMax + anglePressure * (idle ? 0.08f : 0.12f));
        if (!idle && anglePressure > 0.58f) {
            this.yawSpeedFactor = Math.max(this.yawSpeedFactor, 1.08f + anglePressure * 0.24f);
            this.pitchSpeedFactor = Math.max(this.pitchSpeedFactor, 1.0f + anglePressure * 0.18f);
        }
        if (airborne) {
            this.yawSpeedFactor *= 0.97f;
            this.pitchSpeedFactor *= 0.95f;
        }
        this.speedProfileTicks = random.nextInt(idle ? 3 : 2, idle ? 7 : 5);
    }

    private float getMicroJitter(boolean yawAxis, boolean idle, boolean airborne, AimData aimData) {
        float amplitude;
        float gcd = GCDUtil.getGCDValue();
        if (gcd <= 0.0f) {
            return 0.0f;
        }
        float pressure = Math.abs(yawAxis ? aimData.angleYaw : aimData.anglePitch);
        if (!idle) {
            float f = yawAxis ? 10.0f : 7.0f;
            if (pressure > f) {
                return 0.0f;
            }
        }
        float f = amplitude = yawAxis ? gcd * 0.018f : gcd * 0.012f;
        if (airborne) {
            amplitude *= 0.35f;
        }
        float wave = (float)Math.sin(((float)NeuroAuraStorage.mc.field_1724.field_6012 + (yawAxis ? 0.0f : 7.0f)) * (idle ? 0.42f : 0.28f));
        return wave * amplitude;
    }

    private float quantizeToMouseStep(float delta, float remainingAngle) {
        float quantized;
        float gcd = GCDUtil.getGCDValue();
        if (gcd <= 0.0f) {
            return delta;
        }
        float limited = delta;
        if (Math.abs(remainingAngle) < Math.abs(limited) && Math.signum(remainingAngle) == Math.signum(limited)) {
            limited = remainingAngle;
        }
        if ((quantized = (float)Math.round(limited / gcd) * gcd) == 0.0f && Math.abs(remainingAngle) >= gcd * 0.35f && Math.abs(limited) > 0.001f) {
            quantized = Math.signum(limited) * gcd;
        }
        if (Math.abs(remainingAngle) < Math.abs(quantized) && Math.signum(remainingAngle) == Math.signum(quantized)) {
            quantized = remainingAngle;
        }
        return quantized;
    }

    private float calculateSmoothness(float deltaYaw, float deltaPitch) {
        float magnitude = Math.abs(deltaYaw) + Math.abs(deltaPitch);
        float base = 1.0f - magnitude / 18.0f;
        float periodic = (float)Math.sin(((float)this.recordedThisSession + (float)NeuroAuraStorage.mc.field_1724.field_6012 * 0.31f) * 0.34f) * 0.012f;
        float noise = ThreadLocalRandom.current().nextFloat(-0.008f, 0.008f);
        return class_3532.method_15363((float)(base + periodic + noise), (float)0.22f, (float)0.88f);
    }

    private int findBest(float angleYaw, float anglePitch, double distance) {
        int best = 0;
        float bestScore = Float.MAX_VALUE;
        for (int i = 0; i < this.frames.size(); ++i) {
            float distanceDiff;
            float pitchDiff;
            float yawDiff;
            float score;
            Frame frame = this.frames.get(i);
            if (!frame.hasTarget || !((score = (yawDiff = Math.abs(class_3532.method_15393((float)(frame.angleYaw - angleYaw)))) + (pitchDiff = Math.abs(frame.anglePitch - anglePitch)) + (distanceDiff = (float)Math.abs(frame.distance - distance)) * 0.3f) < bestScore)) continue;
            bestScore = score;
            best = i;
        }
        return best;
    }

    public boolean savePatterns(String profileName) {
        boolean bl;
        if (this.frames.isEmpty()) {
            this.lastDebugMessage = "\u00a7c\u041d\u0435\u0442 \u0437\u0430\u043f\u0438\u0441\u0435\u0439";
            return false;
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data_patterns/" + profileName + PRIMARY_EXTENSION));
        try {
            SaveData data = new SaveData();
            data.patterns = new ArrayList<NeuroPattern>(this.recordedPatterns);
            data.frames = new ArrayList<Frame>(this.frames);
            out.writeObject(data);
            this.currentPatternName = profileName;
            this.lastDebugMessage = "\u00a7a\u0421\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u043e " + this.frames.size();
            bl = true;
        }
        catch (Throwable throwable) {
            try {
                try {
                    out.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (IOException e) {
                this.lastDebugMessage = "\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f";
                return false;
            }
        }
        out.close();
        return bl;
    }

    public boolean loadPatterns(String profileName) {
        boolean bl;
        File file = this.resolveProfileFile(profileName);
        if (!file.exists()) {
            this.lastDebugMessage = "\u00a7e\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e: " + profileName;
            return false;
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        try {
            Object obj = in.readObject();
            this.recordedPatterns.clear();
            this.frames.clear();
            if (obj instanceof SaveData) {
                SaveData data = (SaveData)obj;
                if (data.patterns != null) {
                    this.recordedPatterns.addAll(data.patterns);
                }
                if (data.frames != null) {
                    this.frames.addAll(data.frames);
                }
            } else if (obj instanceof List) {
                List list = (List)obj;
                this.recordedPatterns.addAll(list);
            }
            if (this.frames.isEmpty() && !this.recordedPatterns.isEmpty()) {
                this.rebuildFramesFromPatterns();
            }
            this.currentPatternName = profileName;
            this.resetState();
            this.lastDebugMessage = "\u00a7a\u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u043e " + this.frames.size();
            bl = !this.frames.isEmpty();
        }
        catch (Throwable throwable) {
            try {
                try {
                    in.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (IOException | ClassNotFoundException e) {
                this.lastDebugMessage = "\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438";
                return false;
            }
        }
        in.close();
        return bl;
    }

    private void rebuildFramesFromPatterns() {
        for (NeuroPattern pattern : this.recordedPatterns) {
            Frame frame = new Frame();
            frame.deltaYaw = pattern.getDeltaYaw();
            frame.deltaPitch = pattern.getDeltaPitch();
            frame.angleYaw = pattern.getYaw();
            frame.anglePitch = pattern.getPitch();
            frame.distance = pattern.getDistance();
            frame.hasTarget = true;
            frame.smoothness = class_3532.method_15363((float)pattern.getSmoothness(), (float)0.18f, (float)0.9f);
            this.frames.add(frame);
        }
    }

    public boolean deletePatterns(String profileName) {
        File primaryFile = new File("data_patterns/" + profileName + PRIMARY_EXTENSION);
        File legacyFile = new File("neuro_patterns/" + profileName + LEGACY_EXTENSION);
        boolean deleted = false;
        if (primaryFile.exists()) {
            deleted = primaryFile.delete();
        }
        if (legacyFile.exists()) {
            boolean bl = deleted = legacyFile.delete() || deleted;
        }
        if (deleted) {
            if (profileName.equals(this.currentPatternName)) {
                this.currentPatternName = null;
            }
            this.lastDebugMessage = "\u00a7a\u0423\u0434\u0430\u043b\u0435\u043d\u043e";
            return true;
        }
        return false;
    }

    public int getPatternCount() {
        return this.recordedPatterns.size();
    }

    public int getFrameCount() {
        return this.frames.size();
    }

    public void startRecording() {
        this.recordedPatterns.clear();
        this.frames.clear();
        this.isRecording = true;
        this.isUsingNeuro = false;
        this.recordedThisSession = 0;
        this.lastRecordTime = 0L;
        this.currentPatternName = null;
        this.hasRecordedBefore = false;
        this.prevRecordYaw = 0.0f;
        this.prevRecordPitch = 0.0f;
        this.resetState();
        this.lastDebugMessage = "\u00a7a\u0417\u0430\u043f\u0438\u0441\u044c";
    }

    public void stopRecording() {
        this.isRecording = false;
        this.lastDebugMessage = "\u00a7e\u0421\u0442\u043e\u043f: " + this.frames.size();
    }

    public void clearPatterns() {
        this.recordedPatterns.clear();
        this.frames.clear();
        this.isRecording = false;
        this.isUsingNeuro = false;
        this.recordedThisSession = 0;
        this.currentPatternName = null;
        this.hasRecordedBefore = false;
        this.prevRecordYaw = 0.0f;
        this.prevRecordPitch = 0.0f;
        this.resetState();
        this.lastDebugMessage = "\u00a7e\u041e\u0447\u0438\u0449\u0435\u043d\u043e";
    }

    public void resetState() {
        this.playbackIndex = -1;
        this.ticksSinceSync = 0;
        this.smoothedYawDelta = 0.0f;
        this.smoothedPitchDelta = 0.0f;
        this.smoothedOutputYaw = Float.NaN;
        this.smoothedOutputPitch = Float.NaN;
        this.yawSpeedFactor = 1.0f;
        this.pitchSpeedFactor = 1.0f;
        this.speedProfileTicks = 0;
        this.currentAimPoint = null;
        this.targetRandomPoint = null;
        this.aimPointTicks = 0;
        this.lastAimTarget = null;
        this.lastWasIdle = true;
        this.attackCount = 0;
        this.rollNewRandomPoint();
    }

    public String getStatusString() {
        String status = "\u00a78[\u00a7bData\u00a78] \u00a7f" + this.frames.size();
        if (this.isRecording) {
            status = status + " \u00a7a[REC]";
        }
        if (this.isUsingNeuro) {
            status = status + " \u00a7b[ON " + this.playbackIndex + "]";
        }
        return status;
    }

    public List<String> getPatternNames() {
        ArrayList<String> names = new ArrayList<String>();
        this.collectPatternNames(names, new File(PATTERNS_DIRECTORY), PRIMARY_EXTENSION);
        this.collectPatternNames(names, new File(LEGACY_PATTERNS_DIRECTORY), LEGACY_EXTENSION);
        return names;
    }

    private void collectPatternNames(List<String> names, File directory, String extension) {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }
        File[] files = directory.listFiles((dir, name) -> name.endsWith(extension));
        if (files == null) {
            return;
        }
        for (File file : files) {
            String name2 = file.getName().replace(extension, "");
            if (names.contains(name2)) continue;
            names.add(name2);
        }
    }

    private File resolveProfileFile(String profileName) {
        File primaryFile = new File("data_patterns/" + profileName + PRIMARY_EXTENSION);
        if (primaryFile.exists()) {
            return primaryFile;
        }
        return new File("neuro_patterns/" + profileName + LEGACY_EXTENSION);
    }

    public List<NeuroPattern> getRecordedPatterns() {
        return this.recordedPatterns;
    }

    public boolean isRecording() {
        return this.isRecording;
    }

    public void setRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }

    public boolean isUsingNeuro() {
        return this.isUsingNeuro;
    }

    public void setUsingNeuro(boolean isUsingNeuro) {
        this.isUsingNeuro = isUsingNeuro;
    }

    public boolean isShowStats() {
        return this.showStats;
    }

    public void setShowStats(boolean showStats) {
        this.showStats = showStats;
    }

    public String getCurrentPatternName() {
        return this.currentPatternName;
    }

    public void setCurrentPatternName(String currentPatternName) {
        this.currentPatternName = currentPatternName;
    }

    public String getLastDebugMessage() {
        return this.lastDebugMessage;
    }

    public int getRecordedThisSession() {
        return this.recordedThisSession;
    }

    private static class Frame
    implements Serializable {
        private static final long serialVersionUID = 7L;
        float deltaYaw;
        float deltaPitch;
        float angleYaw;
        float anglePitch;
        double distance;
        boolean hasTarget;
        float smoothness;

        private Frame() {
        }
    }

    private static class AimData {
        class_243 targetPoint;
        float perfectYaw;
        float perfectPitch;
        float angleYaw;
        float anglePitch;
        double distance;

        private AimData() {
        }
    }

    private static class SaveData
    implements Serializable {
        private static final long serialVersionUID = 7L;
        List<NeuroPattern> patterns;
        List<Frame> frames;

        private SaveData() {
        }
    }
}
