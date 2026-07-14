package ua.babka.combat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import ua.babka.combat.BestPoint;
import ua.babka.combat.NeuroPattern;
import ua.babka.combat.PerlinNoise;
import ua.babka.combat.RotationUtil;
import ua.babka.core.Rotation;
import ua.babka.util.IMinecraft;

public class NeuroAuraSystem {
    private final List<NeuroPattern> recordedPatterns = new CopyOnWriteArrayList<NeuroPattern>();
    private boolean isRecording = false;
    private boolean isUsingNeuro = false;
    private boolean showStats = true;
    private String currentPatternName = null;
    private String lastDebugMessage = "§7Готов";
    private long lastRecordTime = 0L;
    private final String patternsDirectory = "neuro_patterns";
    private int recordedThisSession = 0;
    private long sessionStartTime = 0L;
    private PerlinNoise noise = new PerlinNoise();
    private long startTime = System.currentTimeMillis();
    private Vec3d lastTargetPos = null;
    private Vec3d lastTargetVel = Vec3d.ZERO;
    private Rotation lastRotation = null;
    private float lastYaw = 0.0f;
    private float lastPitch = 0.0f;
    private float noiseFactor = 1.0f;
    private int currentPointIndex = 0;
    private final List<Vec3d> targetPoints = new ArrayList<Vec3d>();
    private static final long MIN_RECORD_INTERVAL = 50L;
    private int tickCounter = 0;
    private float smoothYawOffset = 0.0f;
    private float smoothPitchOffset = 0.0f;
    private long lastTargetUpdateTime = 0L;

    public NeuroAuraSystem() {
        this.createPatternsDirectory();
        this.startTime = System.currentTimeMillis();
    }

    private void createPatternsDirectory() {
        try {
            Path path = Paths.get("neuro_patterns", new String[0]);
            if (!Files.exists(path, new LinkOption[0])) {
                Files.createDirectories(path, new FileAttribute[0]);
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void recordTick(LivingEntity target, float currentYaw, float currentPitch) {
        if (!this.isRecording) {
            return;
        }
        if (target == null || IMinecraft.mc.player == null) {
            return;
        }
        ++this.tickCounter;
        if (this.tickCounter % 2 != 0) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastRecordTime < 50L) {
            return;
        }
        Vec2f perfectRot = RotationUtil.sample(target.getEyePos());
        float yawDiff = MathHelper.wrapDegrees((float)(currentYaw - perfectRot.x));
        float pitchDiff = currentPitch - perfectRot.y;
        double distance = IMinecraft.mc.player.getEyePos().distanceTo(target.getEyePos());
        boolean isCritical = IMinecraft.mc.player.fallDistance > 0.0f && !IMinecraft.mc.player.isOnGround();
        double targetSpeed = target.getVelocity().horizontalLength();
        String targetType = target instanceof PlayerEntity ? "player" : "mob";
        NeuroPattern pattern = new NeuroPattern(yawDiff, pitchDiff, distance, isCritical, targetSpeed, targetType);
        this.recordedPatterns.add(pattern);
        this.lastRecordTime = now;
        ++this.recordedThisSession;
        while (this.recordedPatterns.size() > 3000) {
            this.recordedPatterns.remove(0);
        }
        if (this.recordedThisSession % 20 == 0) {
            this.lastDebugMessage = "§aЗаписано: §f" + this.recordedPatterns.size() + " паттернов";
        }
    }

    public void recordAttack(LivingEntity target, float currentYaw, float currentPitch) {
        this.recordTick(target, currentYaw, currentPitch);
    }

    public Rotation getNeuroRotation(LivingEntity target, boolean isIdle) {
        if (!this.isUsingNeuro) {
            this.lastDebugMessage = "§cNeuro выкл";
            return null;
        }
        if (this.recordedPatterns.isEmpty()) {
            this.lastDebugMessage = "§cНет паттернов";
            return null;
        }
        if (target == null || IMinecraft.mc.player == null) {
            return null;
        }
        Vec3d eyePos = IMinecraft.mc.player.getEyePos();
        Vec3d targetVel = target.getVelocity();
        Vec3d predictedPos = target.getEyePos().add(targetVel.x * 0.5, targetVel.y * 0.3, targetVel.z * 0.5);
        Vec2f perfectRotVec = RotationUtil.sample(predictedPos);
        Rotation perfectRotation = new Rotation(perfectRotVec.x, perfectRotVec.y);
        double currentDistance = eyePos.distanceTo(target.getEyePos());
        double targetSpeed = targetVel.horizontalLength();
        ArrayList<NeuroPattern> similarPatterns = new ArrayList<NeuroPattern>();
        for (NeuroPattern p2 : this.recordedPatterns) {
            double distDiff = Math.abs(p2.getDistance() - currentDistance);
            double speedDiff = Math.abs(p2.getTargetSpeed() - targetSpeed);
            if (!(distDiff < 3.0) || !(speedDiff < 1.0)) continue;
            similarPatterns.add(p2);
        }
        if (similarPatterns.size() < 10) {
            int start;
            for (int i2 = start = Math.max(0, this.recordedPatterns.size() - 1200); i2 < this.recordedPatterns.size(); ++i2) {
                NeuroPattern p3 = this.recordedPatterns.get(i2);
                if (similarPatterns.contains(p3)) continue;
                similarPatterns.add(p3);
            }
        }
        float avgYawDiff = 0.0f;
        float avgPitchDiff = 0.0f;
        float totalWeight = 0.0f;
        long currentTime = System.currentTimeMillis();
        for (NeuroPattern p4 : similarPatterns) {
            float ageWeight = (float)Math.exp((double)(-(currentTime - p4.getTimestamp())) / 300000.0);
            float distWeight = 1.0f - (float)Math.min(1.0, Math.abs(p4.getDistance() - currentDistance) / 6.0);
            float speedWeight = 1.0f - (float)Math.min(1.0, Math.abs(p4.getTargetSpeed() - targetSpeed) / 1.5);
            float weight = ageWeight * (distWeight * 0.6f + speedWeight * 0.4f);
            avgYawDiff += p4.getYaw() * weight;
            avgPitchDiff += p4.getPitch() * weight;
            totalWeight += weight;
        }
        if (totalWeight > 0.0f) {
            avgYawDiff /= totalWeight;
            avgPitchDiff /= totalWeight;
        }
        this.smoothYawOffset += (avgYawDiff - this.smoothYawOffset) * 0.15f;
        this.smoothPitchOffset += (avgPitchDiff - this.smoothPitchOffset) * 0.15f;
        float baseYaw = perfectRotation.getYaw() + this.smoothYawOffset;
        float basePitch = perfectRotation.getPitch() + this.smoothPitchOffset;
        Vec3d nearY = BestPoint.getNearestPoint((Entity)target);
        float targetY = (float)MathHelper.clamp((double)(IMinecraft.mc.player.getY() + (target.getEyeY() - IMinecraft.mc.player.getY()) * 0.5), (double)target.getBoundingBox().minY, (double)target.getBoundingBox().maxY);
        Vec2f funTargetRotVec = RotationUtil.sample(new Vec3d(nearY.x, (double)targetY, nearY.z));
        Rotation funTargetRot = new Rotation(funTargetRotVec.x, funTargetRotVec.y);
        float finalYaw = baseYaw;
        float finalPitch = basePitch;
        if (isIdle) {
            float idleYawOffset = (float)(Math.sin((double)currentTime * 0.001) * 8.0);
            float idlePitchOffset = (float)(Math.cos((double)currentTime * 0.0013) * 6.0 - 8.0);
            finalYaw += idleYawOffset;
            finalPitch += idlePitchOffset;
        }
        long timeElapsed = System.currentTimeMillis() - this.startTime;
        float yawNoise = (float)this.noise.noise((double)timeElapsed * 3.0E-4, (double)currentTime * 1.0E-6);
        float pitchNoise = (float)this.noise.noise((double)timeElapsed * 3.0E-4, 10.0 + (double)currentTime * 1.0E-6);
        if (this.lastYaw != 0.0f && this.lastPitch != 0.0f) {
            float clampSpeed;
            float yawDiff = RotationUtil.getAngleDifference(finalYaw, this.lastYaw);
            float pitchDiff = finalPitch - this.lastPitch;
            if (Math.abs(yawDiff) > 25.0f) {
                clampSpeed = Math.min(Math.abs(yawDiff) * 0.08f, 6.0f);
                finalYaw = this.lastYaw + Math.copySign(clampSpeed, yawDiff);
            }
            if (Math.abs(pitchDiff) > 15.0f) {
                clampSpeed = Math.min(Math.abs(pitchDiff) * 0.12f, 4.0f);
                finalPitch = this.lastPitch + Math.copySign(clampSpeed, pitchDiff);
            }
        }
        float diffToFunYaw = RotationUtil.getAngleDifference(finalYaw, funTargetRot.getYaw());
        float diffToFunPitch = finalPitch - funTargetRot.getPitch();
        float totalDiff = Math.abs(diffToFunYaw) + Math.abs(diffToFunPitch);
        float targetNoiseFactor = totalDiff < 8.0f ? Math.max(0.3f, this.noiseFactor - 0.02f) : Math.min(1.0f, this.noiseFactor + 0.01f);
        this.noiseFactor += (targetNoiseFactor - this.noiseFactor) * 0.1f;
        finalYaw += yawNoise * 3.0f * this.noiseFactor;
        finalPitch += pitchNoise * 2.5f * this.noiseFactor;
        finalPitch = MathHelper.clamp((float)finalPitch, (float)-90.0f, (float)90.0f);
        finalYaw = MathHelper.wrapDegrees((float)finalYaw);
        Vec3d currentPos = target.getPos();
        if (this.lastTargetPos == null || currentPos.squaredDistanceTo(this.lastTargetPos) > 1.0E-4) {
            long now = System.currentTimeMillis();
            if (this.lastTargetPos != null) {
                double dt = (double)Math.max(1L, now - this.lastTargetUpdateTime) / 1000.0;
                this.lastTargetVel = currentPos.subtract(this.lastTargetPos).multiply(1.0 / dt);
            }
            this.updateTargetPoints(target);
            this.lastTargetPos = currentPos;
            this.lastTargetUpdateTime = now;
        }
        if (!this.targetPoints.isEmpty()) {
            this.currentPointIndex = (this.currentPointIndex + 1) % this.targetPoints.size();
            Vec3d targetPoint = this.targetPoints.get(this.currentPointIndex);
            Vec2f pointRotVec = RotationUtil.sample(targetPoint);
            Rotation pointRotation = new Rotation(pointRotVec.x, pointRotVec.y);
            float mixFactor = isIdle ? 0.0f : 0.03f;
            finalYaw = finalYaw * (1.0f - mixFactor) + pointRotation.getYaw() * mixFactor;
            finalPitch = finalPitch * (1.0f - mixFactor) + pointRotation.getPitch() * mixFactor;
            finalYaw = MathHelper.wrapDegrees((float)finalYaw);
            finalPitch = MathHelper.clamp((float)finalPitch, (float)-90.0f, (float)90.0f);
        }
        this.lastDebugMessage = String.format("§aНейро: Y%.1f° P%.1f° | шум: %.2f", Float.valueOf(this.smoothYawOffset), Float.valueOf(this.smoothPitchOffset), Float.valueOf(this.noiseFactor));
        return new Rotation(finalYaw, finalPitch);
    }

    private void updateTargetPoints(LivingEntity target) {
        this.targetPoints.clear();
        Box box = target.getBoundingBox();
        Vec3d center = target.getEyePos();
        Vec3d vel = target.getVelocity();
        this.targetPoints.add(center);
        this.targetPoints.add(new Vec3d(center.x, box.minY + 0.1, center.z));
        this.targetPoints.add(new Vec3d(center.x, box.maxY - 0.1, center.z));
        this.targetPoints.add(new Vec3d(box.minX + 0.2, center.y, center.z));
        this.targetPoints.add(new Vec3d(box.maxX - 0.2, center.y, center.z));
        this.targetPoints.add(new Vec3d(center.x, center.y, box.minZ + 0.2));
        this.targetPoints.add(new Vec3d(center.x, center.y, box.maxZ - 0.2));
        Vec3d predicted = center.add(vel.x * 0.3, vel.y * 0.15, vel.z * 0.3);
        this.targetPoints.add(predicted);
        for (int i2 = 0; i2 < 4; ++i2) {
            double x2 = Math.random() * (box.maxX - box.minX) + box.minX;
            double y2 = Math.random() * (box.maxY - box.minY) + box.minY;
            double z2 = Math.random() * (box.maxZ - box.minZ) + box.minZ;
            this.targetPoints.add(new Vec3d(x2, y2, z2));
        }
        Collections.shuffle(this.targetPoints);
    }

    public void savePatterns(String profileName) {
        if (this.recordedPatterns.isEmpty()) {
            this.lastDebugMessage = "§cНет паттернов";
            return;
        }
        try {
            String filename = "neuro_patterns/" + profileName + ".neuro";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));){
                oos.writeObject(new ArrayList<NeuroPattern>(this.recordedPatterns));
            }
            this.currentPatternName = profileName;
            this.lastDebugMessage = "§aСохранено " + this.recordedPatterns.size() + " паттернов";
        }
        catch (IOException e2) {
            this.lastDebugMessage = "§cОшибка сохранения";
            e2.printStackTrace();
        }
    }

    public void loadPatterns(String profileName) {
        String filename = "neuro_patterns/" + profileName + ".neuro";
        File file = new File(filename);
        if (!file.exists()) {
            this.lastDebugMessage = "§eНет сохранений: " + profileName;
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));){
            List loaded = (List)ois.readObject();
            this.recordedPatterns.clear();
            this.recordedPatterns.addAll(loaded);
            this.currentPatternName = profileName;
            this.lastDebugMessage = "§aЗагружено " + this.recordedPatterns.size() + " паттернов";
        }
        catch (IOException | ClassNotFoundException e2) {
            this.lastDebugMessage = "§cОшибка загрузки";
            e2.printStackTrace();
        }
    }

    public int getPatternCount() {
        return this.recordedPatterns.size();
    }

    public void startRecording() {
        this.isRecording = true;
        this.recordedThisSession = 0;
        this.sessionStartTime = System.currentTimeMillis();
        this.lastDebugMessage = "§aЗапись начата";
    }

    public void stopRecording() {
        this.isRecording = false;
        this.lastDebugMessage = "§eЗапись остановлена";
    }

    public void clearPatterns() {
        this.recordedPatterns.clear();
        this.recordedThisSession = 0;
        this.currentPatternName = null;
        this.lastDebugMessage = "§eПаттерны очищены";
    }

    public String getStatusString() {
        String status = "§8[§bNeuro§8] §fПаттернов: §e" + this.recordedPatterns.size();
        if (this.isRecording) {
            status = status + " §a[ЗАПИСЬ";
            if (this.recordedThisSession > 0) {
                status = status + " +" + this.recordedThisSession;
            }
            status = status + "]";
        }
        if (this.isUsingNeuro) {
            status = status + " §b[АКТИВЕН";
            if (this.currentPatternName != null) {
                status = status + " §7(" + this.currentPatternName + ")";
            }
            status = status + "]";
        }
        return status;
    }

    public List<String> getPatternNames() {
        File[] files;
        ArrayList<String> patterns = new ArrayList<String>();
        File dir = new File("neuro_patterns");
        if (dir.exists() && dir.isDirectory() && (files = dir.listFiles((d2, name) -> name.endsWith(".neuro"))) != null) {
            for (File file : files) {
                String name2 = file.getName();
                patterns.add(name2.substring(0, name2.length() - 6));
            }
        }
        return patterns;
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
}

