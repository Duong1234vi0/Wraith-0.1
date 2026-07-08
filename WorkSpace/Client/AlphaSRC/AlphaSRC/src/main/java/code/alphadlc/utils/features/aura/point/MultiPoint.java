/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3545
 *  net.minecraft.class_3959$class_3960
 */
package code.alphadlc.utils.features.aura.point;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.utils.RaycastAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3545;
import net.minecraft.class_3959;

public class MultiPoint
implements QuickImports {
    private final Random random = new SecureRandom();
    private class_243 offset = class_243.field_1353;

    public class_3545<class_243, class_238> computeVector(class_1309 entity, float maxDistance, Turns initialAngle, class_243 velocity, boolean ignoreWalls) {
        class_3545<List<class_243>, class_238> candidatePoints = this.generateCandidatePoints(entity, maxDistance, ignoreWalls);
        class_243 bestVector = this.findBestVector((List)candidatePoints.method_15442(), initialAngle);
        this.updateOffset(velocity);
        return new class_3545((Object)(bestVector == null ? entity.method_33571() : bestVector).method_1019(this.offset), (Object)((class_238)candidatePoints.method_15441()));
    }

    public class_3545<List<class_243>, class_238> generateCandidatePoints(class_1309 entity, float maxDistance, boolean ignoreWalls) {
        class_238 entityBox = entity.method_5829();
        double stepY = entityBox.method_17940() / 10.0;
        List<class_243> list = Stream.iterate(entityBox.field_1322, y -> y <= entityBox.field_1325, y -> y + stepY).map(y -> new class_243(entityBox.method_1005().field_1352, y.doubleValue(), entityBox.method_1005().field_1350)).filter(point -> this.isValidPoint(MultiPoint.mc.field_1724.method_33571(), (class_243)point, maxDistance, ignoreWalls)).toList();
        return new class_3545(list, (Object)entityBox);
    }

    public boolean hasValidPoint(class_1309 entity, float maxDistance, boolean ignoreWalls) {
        class_238 entityBox = entity.method_5829();
        double stepY = entityBox.method_17940() / 10.0;
        return Stream.iterate(entityBox.field_1322, y -> y < entityBox.field_1325, y -> y + stepY).map(y -> new class_243(entityBox.method_1005().field_1352, y.doubleValue(), entityBox.method_1005().field_1350)).anyMatch(point -> this.isValidPoint(MultiPoint.mc.field_1724.method_33571(), (class_243)point, maxDistance, ignoreWalls));
    }

    private boolean isValidPoint(class_243 startPoint, class_243 endPoint, float maxDistance, boolean ignoreWalls) {
        return startPoint.method_1022(endPoint) <= (double)maxDistance && (ignoreWalls || !RaycastAngle.raycast(startPoint, endPoint, class_3959.class_3960.field_17558).method_17783().equals((Object)class_239.class_240.field_1332));
    }

    private class_243 findBestVector(List<class_243> candidatePoints, Turns initialAngle) {
        return candidatePoints.stream().min(Comparator.comparing(point -> this.calculateRotationDifference(MultiPoint.mc.field_1724.method_33571(), (class_243)point, initialAngle))).orElse(null);
    }

    private double calculateRotationDifference(class_243 startPoint, class_243 endPoint, Turns initialAngle) {
        Turns targetAngle = MathAngle.fromVec3d(endPoint.method_1020(startPoint));
        Turns delta = MathAngle.calculateDelta(initialAngle, targetAngle);
        return Math.hypot(delta.getYaw(), delta.getPitch());
    }

    private void updateOffset(class_243 velocity) {
        this.offset = this.offset.method_1031(this.random.nextGaussian(), this.random.nextGaussian(), this.random.nextGaussian()).method_18806(velocity);
    }

    public Random getRandom() {
        return this.random;
    }

    public class_243 getOffset() {
        return this.offset;
    }
}
