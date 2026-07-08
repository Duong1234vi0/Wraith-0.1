/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 */
package code.alphadlc.utils.features.aura.rotations.constructor;

import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public abstract class RotateConstructor {
    private final String name;

    protected RotateConstructor(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Turns limitAngleChange(Turns currentAngle, Turns targetAngle) {
        return this.limitAngleChange(currentAngle, targetAngle, class_243.field_1353, null);
    }

    public abstract Turns limitAngleChange(Turns var1, Turns var2, class_243 var3, class_1297 var4);

    public class_243 randomValue() {
        return class_243.field_1353;
    }
}
