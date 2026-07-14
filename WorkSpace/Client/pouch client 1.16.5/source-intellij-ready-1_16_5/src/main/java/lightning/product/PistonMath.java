/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.b_257_Y;

public class PistonMath {
    public static I_4817_s n_1700_B(I_4817_s p_227019_0_, b_257_Y p_227019_1_, double p_227019_2_) {
        double d0 = p_227019_2_ * (double)p_227019_1_.P_1922_E().n_1700_B();
        double d1 = Math.min(d0, 0.0);
        double d2 = Math.max(d0, 0.0);
        switch (p_227019_1_) {
            case P_1922_E: {
                return new I_4817_s(p_227019_0_.minX + d1, p_227019_0_.minY, p_227019_0_.minZ, p_227019_0_.minX + d2, p_227019_0_.maxY, p_227019_0_.maxZ);
            }
            case u_1723_Y: {
                return new I_4817_s(p_227019_0_.maxX + d1, p_227019_0_.minY, p_227019_0_.minZ, p_227019_0_.maxX + d2, p_227019_0_.maxY, p_227019_0_.maxZ);
            }
            case n_1700_B: {
                return new I_4817_s(p_227019_0_.minX, p_227019_0_.minY + d1, p_227019_0_.minZ, p_227019_0_.maxX, p_227019_0_.minY + d2, p_227019_0_.maxZ);
            }
            default: {
                return new I_4817_s(p_227019_0_.minX, p_227019_0_.maxY + d1, p_227019_0_.minZ, p_227019_0_.maxX, p_227019_0_.maxY + d2, p_227019_0_.maxZ);
            }
            case R_4764_Y: {
                return new I_4817_s(p_227019_0_.minX, p_227019_0_.minY, p_227019_0_.minZ + d1, p_227019_0_.maxX, p_227019_0_.maxY, p_227019_0_.minZ + d2);
            }
            case G_564_y: 
        }
        return new I_4817_s(p_227019_0_.minX, p_227019_0_.minY, p_227019_0_.maxZ + d1, p_227019_0_.maxX, p_227019_0_.maxY, p_227019_0_.maxZ + d2);
    }
}


