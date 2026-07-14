/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.c_1325_f;
import lightning.product.Context;

public final class B_73_M
extends Enum<B_73_M>
implements c_1325_f {
    public static final /* enum */ B_73_M n_1700_B = new B_73_M();
    private static final /* synthetic */ B_73_M[] J_1907_R;

    public static B_73_M[] values() {
        return (B_73_M[])J_1907_R.clone();
    }

    public static B_73_M valueOf(String name) {
        return Enum.valueOf(B_73_M.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        int[] aint = new int[1];
        if (!(this.n_1700_B(aint, center) || this.n_1700_B(aint, north, west, south, east, center, 38, 37) || this.n_1700_B(aint, north, west, south, east, center, 39, 37) || this.n_1700_B(aint, north, west, south, east, center, 32, 5))) {
            if (center != 2 || north != 12 && west != 12 && east != 12 && south != 12) {
                if (center == 6) {
                    if (north == 2 || west == 2 || east == 2 || south == 2 || north == 30 || west == 30 || east == 30 || south == 30 || north == 12 || west == 12 || east == 12 || south == 12) {
                        return 1;
                    }
                    if (north == 21 || south == 21 || west == 21 || east == 21 || north == 168 || south == 168 || west == 168 || east == 168) {
                        return 23;
                    }
                }
                return center;
            }
            return 34;
        }
        return aint[0];
    }

    private boolean n_1700_B(int[] p_242935_1_, int p_242935_2_) {
        if (!V_4170_D.n_1700_B(p_242935_2_, 3)) {
            return false;
        }
        p_242935_1_[0] = p_242935_2_;
        return true;
    }

    private boolean n_1700_B(int[] p_151635_1_, int p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
        if (p_151635_6_ != p_151635_7_) {
            return false;
        }
        p_151635_1_[0] = V_4170_D.n_1700_B(p_151635_2_, p_151635_7_) && V_4170_D.n_1700_B(p_151635_3_, p_151635_7_) && V_4170_D.n_1700_B(p_151635_5_, p_151635_7_) && V_4170_D.n_1700_B(p_151635_4_, p_151635_7_) ? p_151635_6_ : p_151635_8_;
        return true;
    }

    private static /* synthetic */ B_73_M[] n_1700_B() {
        return new B_73_M[]{n_1700_B};
    }

    static {
        J_1907_R = B_73_M.n_1700_B();
    }
}


