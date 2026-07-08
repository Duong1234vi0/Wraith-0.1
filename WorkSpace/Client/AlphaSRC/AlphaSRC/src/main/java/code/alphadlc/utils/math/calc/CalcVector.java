/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 */
package code.alphadlc.utils.math.calc;

import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class CalcVector {
    public static class_243 lerpPosition(class_1297 entity) {
        float tickDelta = QuickImports.mc.method_61966().method_60637(true);
        return new class_243(entity.field_6014 + (entity.method_23317() - entity.field_6014) * (double)tickDelta, entity.field_6036 + (entity.method_23318() - entity.field_6036) * (double)tickDelta, entity.field_5969 + (entity.method_23321() - entity.field_5969) * (double)tickDelta);
    }
}
