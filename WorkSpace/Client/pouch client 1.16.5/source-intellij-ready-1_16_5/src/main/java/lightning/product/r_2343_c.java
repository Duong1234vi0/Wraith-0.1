/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.i_2518_W;
import lightning.product.j_3341_s;
import lightning.product.s_3940_w;

public final class r_2343_c
extends Enum<r_2343_c>
implements s_3940_w {
    public static final /* enum */ r_2343_c n_1700_B = new r_2343_c();
    private static final i_2518_W J_1907_R;
    private static final /* synthetic */ r_2343_c[] R_4764_Y;

    public static r_2343_c[] values() {
        return (r_2343_c[])R_4764_Y.clone();
    }

    public static r_2343_c valueOf(String name) {
        return Enum.valueOf(r_2343_c.class, name);
    }

    @Override
    public int n_1700_B() {
        return 5;
    }

    @Override
    public int J_1907_R() {
        return 8;
    }

    @Override
    public float getAdvance() {
        return 6.0f;
    }

    @Override
    public float R_4764_Y() {
        return 1.0f;
    }

    @Override
    public void n_1700_B(int xOffset, int yOffset) {
        J_1907_R.n_1700_B(0, xOffset, yOffset, false);
    }

    @Override
    public boolean G_564_y() {
        return true;
    }

    private static /* synthetic */ r_2343_c[] P_4830_p() {
        return new r_2343_c[]{n_1700_B};
    }

    static {
        R_4764_Y = r_2343_c.P_4830_p();
        J_1907_R = j_3341_s.n_1700_B(new i_2518_W(i_2518_W.n_1700_B.n_1700_B, 5, 8, false), nativeImage -> {
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 5; ++j) {
                    boolean bl = j == 0 || j + 1 == 5 || i == 0 || i + 1 == 8;
                    nativeImage.n_1700_B(j, i, -1);
                }
            }
            nativeImage.v_4262_N();
        });
    }
}

