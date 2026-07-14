/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_747_P;
import lightning.product.I_4817_s;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;

public class S_4325_V
implements MinecraftAccess {
    private static e_2866_D n_1700_B = e_2866_D.n_1700_B;
    private static e_2866_D J_1907_R = e_2866_D.n_1700_B;

    public static e_2866_D n_1700_B(r_4811_B target, double distance) {
        if (target == null) {
            return e_2866_D.n_1700_B;
        }
        I_4817_s box = target.i_601_W();
        double lenX = box.getXSize();
        double lenY = box.getYSize();
        double lenZ = box.getZSize();
        float minMotionXZ = 0.005f;
        float maxMotionXZ = 0.015f;
        float minMotionY = 0.0015f;
        float maxMotionY = 0.015f;
        if (J_1907_R.equals(e_2866_D.n_1700_B)) {
            J_1907_R = new e_2866_D(F_747_P.G_564_y(-0.02f, 0.02f), F_747_P.G_564_y(-0.02f, 0.02f), F_747_P.G_564_y(-0.02f, 0.02f));
        }
        if (n_1700_B.equals(e_2866_D.n_1700_B)) {
            n_1700_B = new e_2866_D(0.0, lenY * 0.5, 0.0);
        }
        n_1700_B = n_1700_B.P_1922_E(J_1907_R);
        double safeX = (lenX - 0.1) / 2.0;
        double safeZ = (lenZ - 0.1) / 2.0;
        if (S_4325_V.n_1700_B.J_1907_R >= safeX) {
            J_1907_R = new e_2866_D(-F_747_P.G_564_y(minMotionXZ, maxMotionXZ), S_4325_V.J_1907_R.R_4764_Y, S_4325_V.J_1907_R.G_564_y);
        } else if (S_4325_V.n_1700_B.J_1907_R <= -safeX) {
            J_1907_R = new e_2866_D(F_747_P.G_564_y(minMotionXZ, maxMotionXZ), S_4325_V.J_1907_R.R_4764_Y, S_4325_V.J_1907_R.G_564_y);
        }
        if (S_4325_V.n_1700_B.R_4764_Y >= lenY * 0.75) {
            J_1907_R = new e_2866_D(S_4325_V.J_1907_R.J_1907_R, -F_747_P.G_564_y(minMotionY, maxMotionY), S_4325_V.J_1907_R.G_564_y);
        } else if (S_4325_V.n_1700_B.R_4764_Y <= lenY * 0.3) {
            J_1907_R = new e_2866_D(S_4325_V.J_1907_R.J_1907_R, F_747_P.G_564_y(minMotionY, maxMotionY), S_4325_V.J_1907_R.G_564_y);
        }
        if (S_4325_V.n_1700_B.G_564_y >= safeZ) {
            J_1907_R = new e_2866_D(S_4325_V.J_1907_R.J_1907_R, S_4325_V.J_1907_R.R_4764_Y, -F_747_P.G_564_y(minMotionXZ, maxMotionXZ));
        } else if (S_4325_V.n_1700_B.G_564_y <= -safeZ) {
            J_1907_R = new e_2866_D(S_4325_V.J_1907_R.J_1907_R, S_4325_V.J_1907_R.R_4764_Y, F_747_P.G_564_y(minMotionXZ, maxMotionXZ));
        }
        n_1700_B = n_1700_B.J_1907_R(F_747_P.G_564_y(-0.05f, 0.05f), 0.0, F_747_P.G_564_y(-0.05f, 0.05f));
        return target.s_4990_V().P_1922_E(n_1700_B);
    }
}


