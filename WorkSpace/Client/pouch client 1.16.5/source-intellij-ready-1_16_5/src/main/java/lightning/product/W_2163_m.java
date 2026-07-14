/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lightning.product.b_257_Y;
import lightning.product.j_3341_s;
import lightning.product.r_4970_d;

public final class W_2163_m
extends Enum<W_2163_m> {
    public static final /* enum */ W_2163_m n_1700_B = new W_2163_m(r_4970_d.n_1700_B);
    public static final /* enum */ W_2163_m J_1907_R = new W_2163_m(r_4970_d.Y_259_p);
    public static final /* enum */ W_2163_m R_4764_Y = new W_2163_m(r_4970_d.R_4764_Y);
    public static final /* enum */ W_2163_m G_564_y = new W_2163_m(r_4970_d.Q_2552_b);
    private final r_4970_d P_1922_E;
    private static final /* synthetic */ W_2163_m[] u_1723_Y;

    public static W_2163_m[] values() {
        return (W_2163_m[])u_1723_Y.clone();
    }

    public static W_2163_m valueOf(String name) {
        return Enum.valueOf(W_2163_m.class, name);
    }

    private W_2163_m(r_4970_d orientation) {
        this.P_1922_E = orientation;
    }

    public W_2163_m n_1700_B(W_2163_m rotation) {
        switch (rotation.ordinal()) {
            case 2: {
                switch (this.ordinal()) {
                    case 0: {
                        return R_4764_Y;
                    }
                    case 1: {
                        return G_564_y;
                    }
                    case 2: {
                        return n_1700_B;
                    }
                    case 3: {
                        return J_1907_R;
                    }
                }
            }
            case 3: {
                switch (this.ordinal()) {
                    case 0: {
                        return G_564_y;
                    }
                    case 1: {
                        return n_1700_B;
                    }
                    case 2: {
                        return J_1907_R;
                    }
                    case 3: {
                        return R_4764_Y;
                    }
                }
            }
            case 1: {
                switch (this.ordinal()) {
                    case 0: {
                        return J_1907_R;
                    }
                    case 1: {
                        return R_4764_Y;
                    }
                    case 2: {
                        return G_564_y;
                    }
                    case 3: {
                        return n_1700_B;
                    }
                }
            }
        }
        return this;
    }

    public r_4970_d n_1700_B() {
        return this.P_1922_E;
    }

    public b_257_Y n_1700_B(b_257_Y facing) {
        if (facing.h_1847_R() == b_257_Y.n_1700_B.J_1907_R) {
            return facing;
        }
        switch (this.ordinal()) {
            case 1: {
                return facing.v_4262_N();
            }
            case 2: {
                return facing.u_1723_Y();
            }
            case 3: {
                return facing.w_1484_f();
            }
        }
        return facing;
    }

    public int n_1700_B(int rotation, int positionCount) {
        switch (this.ordinal()) {
            case 1: {
                return (rotation + positionCount / 4) % positionCount;
            }
            case 2: {
                return (rotation + positionCount / 2) % positionCount;
            }
            case 3: {
                return (rotation + positionCount * 3 / 4) % positionCount;
            }
        }
        return rotation;
    }

    public static W_2163_m n_1700_B(Random rand) {
        return j_3341_s.n_1700_B(W_2163_m.values(), rand);
    }

    public static List<W_2163_m> J_1907_R(Random rand) {
        ArrayList list = Lists.newArrayList((Object[])W_2163_m.values());
        Collections.shuffle(list, rand);
        return list;
    }

    private static /* synthetic */ W_2163_m[] J_1907_R() {
        return new W_2163_m[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        u_1723_Y = W_2163_m.J_1907_R();
    }
}

