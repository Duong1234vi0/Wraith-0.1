/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_3318_r;
import lightning.product.c_4037_x;
import lightning.product.o_2840_r;
import net.optifine.SmartAnimations;

public class l_3747_P {
    private final D_3318_r n_1700_B;
    private static final l_3747_P J_1907_R = new l_3747_P();

    public static l_3747_P n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        return J_1907_R;
    }

    public l_3747_P(int bufferSize) {
        this.n_1700_B = new D_3318_r(bufferSize);
    }

    public l_3747_P() {
        this(0x200000);
    }

    public void J_1907_R() {
        if (this.n_1700_B.u_2550_I != null) {
            SmartAnimations.spritesRendered(this.n_1700_B.u_2550_I);
        }
        this.n_1700_B.u_1723_Y();
        o_2840_r.n_1700_B(this.n_1700_B);
    }

    public D_3318_r R_4764_Y() {
        return this.n_1700_B;
    }
}

