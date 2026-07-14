/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package mods.viaversion.viamcp.fixes;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.x_1688_C;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class AttackOrder {
    public static void sendConditionalSwing(HitResult ray, x_1688_C enumHand) {
        if (ray != null && ray.R_4764_Y() != HitResult.n_1700_B.R_4764_Y) {
            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B(enumHand);
        }
    }

    public static void sendFixedAttack(a_3913_L entityIn, N_4263_v target) {
        if (ViaLoadingBase.getInstance().getTargetVersion().olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            MinecraftAccess.c_3005_b.w_1457_N.attackEntity(entityIn, target);
        } else {
            MinecraftAccess.c_3005_b.w_1457_N.attackEntity(entityIn, target);
            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        }
    }
}



