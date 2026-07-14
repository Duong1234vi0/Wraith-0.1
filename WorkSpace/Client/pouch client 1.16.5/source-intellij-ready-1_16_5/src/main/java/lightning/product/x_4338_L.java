/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import lightning.product.A_1130_n;
import lightning.product.AreaTransformer2;
import lightning.product.V_4170_D;
import lightning.product.j_3341_s;
import lightning.product.Context;
import lightning.product.t_4013_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class x_4338_L
extends Enum<x_4338_L>
implements A_1130_n,
AreaTransformer2 {
    public static final /* enum */ x_4338_L n_1700_B = new x_4338_L();
    private static final Logger J_1907_R;
    private static final Int2IntMap R_4764_Y;
    private static final /* synthetic */ x_4338_L[] G_564_y;

    public static x_4338_L[] values() {
        return (x_4338_L[])G_564_y.clone();
    }

    public static x_4338_L valueOf(String name) {
        return Enum.valueOf(x_4338_L.class, name);
    }

    @Override
    public int n_1700_B(Context p_215723_1_, t_4013_W p_215723_2_, t_4013_W p_215723_3_, int p_215723_4_, int p_215723_5_) {
        int i = p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + 1), this.J_1907_R(p_215723_5_ + 1));
        int j = p_215723_3_.n_1700_B(this.n_1700_B(p_215723_4_ + 1), this.J_1907_R(p_215723_5_ + 1));
        if (i > 255) {
            J_1907_R.debug("old! {}", (Object)i);
        }
        int k = (j - 2) % 29;
        if (!V_4170_D.J_1907_R(i) && j >= 2 && k == 1) {
            return R_4764_Y.getOrDefault(i, i);
        }
        if (p_215723_1_.n_1700_B(3) == 0 || k == 0) {
            int l = i;
            if (i == 2) {
                l = 17;
            } else if (i == 4) {
                l = 18;
            } else if (i == 27) {
                l = 28;
            } else if (i == 29) {
                l = 1;
            } else if (i == 5) {
                l = 19;
            } else if (i == 32) {
                l = 33;
            } else if (i == 30) {
                l = 31;
            } else if (i == 1) {
                l = p_215723_1_.n_1700_B(3) == 0 ? 18 : 4;
            } else if (i == 12) {
                l = 13;
            } else if (i == 21) {
                l = 22;
            } else if (i == 168) {
                l = 169;
            } else if (i == 0) {
                l = 24;
            } else if (i == 45) {
                l = 48;
            } else if (i == 46) {
                l = 49;
            } else if (i == 10) {
                l = 50;
            } else if (i == 3) {
                l = 34;
            } else if (i == 35) {
                l = 36;
            } else if (V_4170_D.n_1700_B(i, 38)) {
                l = 37;
            } else if ((i == 24 || i == 48 || i == 49 || i == 50) && p_215723_1_.n_1700_B(3) == 0) {
                int n = l = p_215723_1_.n_1700_B(2) == 0 ? 1 : 4;
            }
            if (k == 0 && l != i) {
                l = R_4764_Y.getOrDefault(l, i);
            }
            if (l != i) {
                int i1 = 0;
                if (V_4170_D.n_1700_B(p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + 1), this.J_1907_R(p_215723_5_ + 0)), i)) {
                    ++i1;
                }
                if (V_4170_D.n_1700_B(p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + 2), this.J_1907_R(p_215723_5_ + 1)), i)) {
                    ++i1;
                }
                if (V_4170_D.n_1700_B(p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + 0), this.J_1907_R(p_215723_5_ + 1)), i)) {
                    ++i1;
                }
                if (V_4170_D.n_1700_B(p_215723_2_.n_1700_B(this.n_1700_B(p_215723_4_ + 1), this.J_1907_R(p_215723_5_ + 2)), i)) {
                    ++i1;
                }
                if (i1 >= 3) {
                    return l;
                }
            }
        }
        return i;
    }

    private static /* synthetic */ x_4338_L[] n_1700_B() {
        return new x_4338_L[]{n_1700_B};
    }

    static {
        G_564_y = x_4338_L.n_1700_B();
        J_1907_R = LogManager.getLogger();
        R_4764_Y = (Int2IntMap)j_3341_s.n_1700_B(new Int2IntOpenHashMap(), p_242941_0_ -> {
            p_242941_0_.put(1, 129);
            p_242941_0_.put(2, 130);
            p_242941_0_.put(3, 131);
            p_242941_0_.put(4, 132);
            p_242941_0_.put(5, 133);
            p_242941_0_.put(6, 134);
            p_242941_0_.put(12, 140);
            p_242941_0_.put(21, 149);
            p_242941_0_.put(23, 151);
            p_242941_0_.put(27, 155);
            p_242941_0_.put(28, 156);
            p_242941_0_.put(29, 157);
            p_242941_0_.put(30, 158);
            p_242941_0_.put(32, 160);
            p_242941_0_.put(33, 161);
            p_242941_0_.put(34, 162);
            p_242941_0_.put(35, 163);
            p_242941_0_.put(36, 164);
            p_242941_0_.put(37, 165);
            p_242941_0_.put(38, 166);
            p_242941_0_.put(39, 167);
        });
    }
}


