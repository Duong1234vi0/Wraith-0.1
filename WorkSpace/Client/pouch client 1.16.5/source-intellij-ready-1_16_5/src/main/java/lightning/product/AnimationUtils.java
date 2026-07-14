/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1630_j;
import lightning.product.Z_530_i;
import lightning.product.e_4189_z;
import lightning.product.k_4231_L;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class AnimationUtils {
    public static void n_1700_B(e_4189_z rightArm, e_4189_z leftArm, e_4189_z head, boolean leftHanded) {
        e_4189_z modelrenderer = leftHanded ? rightArm : leftArm;
        e_4189_z modelrenderer1 = leftHanded ? leftArm : rightArm;
        modelrenderer.v_4262_N = (leftHanded ? -0.3f : 0.3f) + head.v_4262_N;
        modelrenderer1.v_4262_N = (leftHanded ? 0.6f : -0.6f) + head.v_4262_N;
        modelrenderer.u_1723_Y = -1.5707964f + head.u_1723_Y + 0.1f;
        modelrenderer1.u_1723_Y = -1.5f + head.u_1723_Y;
    }

    public static void n_1700_B(e_4189_z rightArm, e_4189_z leftArm, r_4811_B entity, boolean leftHanded) {
        e_4189_z modelrenderer = leftHanded ? rightArm : leftArm;
        e_4189_z modelrenderer1 = leftHanded ? leftArm : rightArm;
        modelrenderer.v_4262_N = leftHanded ? -0.8f : 0.8f;
        modelrenderer1.u_1723_Y = modelrenderer.u_1723_Y = -0.97079635f;
        float f = Z_1630_j.v_4262_N(entity.B_2580_P());
        float f1 = u_530_F.n_1700_B((float)entity.g_1031_K(), 0.0f, f);
        float f2 = f1 / f;
        modelrenderer1.v_4262_N = u_530_F.v_4262_N(f2, 0.4f, 0.85f) * (float)(leftHanded ? 1 : -1);
        modelrenderer1.u_1723_Y = u_530_F.v_4262_N(f2, modelrenderer1.u_1723_Y, -1.5707964f);
    }

    public static <T extends Z_530_i> void n_1700_B(e_4189_z rightArm, e_4189_z leftArm, T entity, float swingProgress, float ageInTicks) {
        float f = u_530_F.n_1700_B(swingProgress * (float)Math.PI);
        float f1 = u_530_F.n_1700_B((1.0f - (1.0f - swingProgress) * (1.0f - swingProgress)) * (float)Math.PI);
        rightArm.w_1484_f = 0.0f;
        leftArm.w_1484_f = 0.0f;
        rightArm.v_4262_N = 0.15707964f;
        leftArm.v_4262_N = -0.15707964f;
        if (entity.d_2169_p() == k_4231_L.J_1907_R) {
            rightArm.u_1723_Y = -1.8849558f + u_530_F.J_1907_R(ageInTicks * 0.09f) * 0.15f;
            leftArm.u_1723_Y = -0.0f + u_530_F.J_1907_R(ageInTicks * 0.19f) * 0.5f;
            rightArm.u_1723_Y += f * 2.2f - f1 * 0.4f;
            leftArm.u_1723_Y += f * 1.2f - f1 * 0.4f;
        } else {
            rightArm.u_1723_Y = -0.0f + u_530_F.J_1907_R(ageInTicks * 0.19f) * 0.5f;
            leftArm.u_1723_Y = -1.8849558f + u_530_F.J_1907_R(ageInTicks * 0.09f) * 0.15f;
            rightArm.u_1723_Y += f * 1.2f - f1 * 0.4f;
            leftArm.u_1723_Y += f * 2.2f - f1 * 0.4f;
        }
        AnimationUtils.n_1700_B(rightArm, leftArm, ageInTicks);
    }

    public static void n_1700_B(e_4189_z rightArm, e_4189_z leftArm, float ageInTicks) {
        rightArm.w_1484_f += u_530_F.J_1907_R(ageInTicks * 0.09f) * 0.05f + 0.05f;
        leftArm.w_1484_f -= u_530_F.J_1907_R(ageInTicks * 0.09f) * 0.05f + 0.05f;
        rightArm.u_1723_Y += u_530_F.n_1700_B(ageInTicks * 0.067f) * 0.05f;
        leftArm.u_1723_Y -= u_530_F.n_1700_B(ageInTicks * 0.067f) * 0.05f;
    }

    public static void n_1700_B(e_4189_z leftArm, e_4189_z rightArm, boolean isAggresive, float swingProgress, float ageInTicks) {
        float f2;
        float f = u_530_F.n_1700_B(swingProgress * (float)Math.PI);
        float f1 = u_530_F.n_1700_B((1.0f - (1.0f - swingProgress) * (1.0f - swingProgress)) * (float)Math.PI);
        rightArm.w_1484_f = 0.0f;
        leftArm.w_1484_f = 0.0f;
        rightArm.v_4262_N = -(0.1f - f * 0.6f);
        leftArm.v_4262_N = 0.1f - f * 0.6f;
        rightArm.u_1723_Y = f2 = (float)(-Math.PI) / (isAggresive ? 1.5f : 2.25f);
        leftArm.u_1723_Y = f2;
        rightArm.u_1723_Y += f * 1.2f - f1 * 0.4f;
        leftArm.u_1723_Y += f * 1.2f - f1 * 0.4f;
        AnimationUtils.n_1700_B(rightArm, leftArm, ageInTicks);
    }
}


