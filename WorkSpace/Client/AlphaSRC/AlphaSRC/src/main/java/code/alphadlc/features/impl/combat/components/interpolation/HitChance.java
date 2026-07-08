/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.interpolation;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.rotate.RotationUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class HitChance
implements QuickImports {
    public static float get(class_1309 target) {
        if (target == null || HitChance.mc.field_1724 == null) {
            return 0.0f;
        }
        class_238 box = target.method_5829();
        class_243 center = box.method_1005();
        class_241 rot = RotationUtils.getRotations(center);
        float diffYaw = Math.abs(class_3532.method_15393((float)(rot.field_1343 - HitChance.mc.field_1724.method_36454())));
        float diffPitch = Math.abs(class_3532.method_15393((float)(rot.field_1342 - HitChance.mc.field_1724.method_36455())));
        float ang = (float)Math.hypot(diffYaw, diffPitch);
        float dist = HitChance.mc.field_1724.method_5739((class_1297)target);
        float angScore = class_3532.method_15363((float)(1.0f - ang / 70.0f), (float)0.0f, (float)1.0f);
        float distScore = class_3532.method_15363((float)(1.0f - (dist - 3.0f) / 4.0f), (float)0.0f, (float)1.0f);
        return angScore * 0.75f + distScore * 0.25f;
    }
}
