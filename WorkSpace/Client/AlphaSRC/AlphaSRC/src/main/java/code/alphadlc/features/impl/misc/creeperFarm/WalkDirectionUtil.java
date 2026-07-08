/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.NonNull
 *  net.minecraft.class_10185
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.misc.creeperFarm;

import code.alphadlc.utils.input.MoveUtil;
import lombok.NonNull;
import net.minecraft.class_10185;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class WalkDirectionUtil {
    public static class_10185 fromVec3d(class_243 vec3d, float yaw) {
        class_243 direction = vec3d.method_1029();
        float movementAngle = (float)Math.toDegrees(Math.atan2(direction.field_1350, direction.field_1352)) - 90.0f;
        float angleDiff = class_3532.method_15393((float)(movementAngle - yaw));
        return MoveUtil.getPlayerInput(angleDiff);
    }

    public static class_10185 fromEntity(@NonNull class_1297 entity, float yaw) {
        if (entity == null) {
            throw new NullPointerException("entity is marked non-null but is null");
        }
        return WalkDirectionUtil.fromVec3d(entity.method_19538(), yaw);
    }
}
