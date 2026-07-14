/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.MegaJungleTrunkPlacer;
import lightning.product.GiantTrunkPlacer;
import lightning.product.V_3137_a;
import lightning.product.b_2750_W;
import lightning.product.TrunkPlacer;
import lightning.product.StraightTrunkPlacer;
import lightning.product.f_3331_m;
import lightning.product.w_839_Z;

public class TrunkPlacerType<P extends TrunkPlacer> {
    public static final TrunkPlacerType<StraightTrunkPlacer> n_1700_B = TrunkPlacerType.n_1700_B("straight_trunk_placer", StraightTrunkPlacer.P_1922_E);
    public static final TrunkPlacerType<w_839_Z> J_1907_R = TrunkPlacerType.n_1700_B("forking_trunk_placer", w_839_Z.P_1922_E);
    public static final TrunkPlacerType<GiantTrunkPlacer> R_4764_Y = TrunkPlacerType.n_1700_B("giant_trunk_placer", GiantTrunkPlacer.P_1922_E);
    public static final TrunkPlacerType<MegaJungleTrunkPlacer> G_564_y = TrunkPlacerType.n_1700_B("mega_jungle_trunk_placer", MegaJungleTrunkPlacer.u_1723_Y);
    public static final TrunkPlacerType<b_2750_W> P_1922_E = TrunkPlacerType.n_1700_B("dark_oak_trunk_placer", b_2750_W.P_1922_E);
    public static final TrunkPlacerType<f_3331_m> u_1723_Y = TrunkPlacerType.n_1700_B("fancy_trunk_placer", f_3331_m.P_1922_E);
    private final Codec<P> v_4262_N;

    private static <P extends TrunkPlacer> TrunkPlacerType<P> n_1700_B(String p_236928_0_, Codec<P> p_236928_1_) {
        return V_3137_a.n_1700_B(V_3137_a.j_2266_I, p_236928_0_, new TrunkPlacerType<P>(p_236928_1_));
    }

    private TrunkPlacerType(Codec<P> p_i232061_1_) {
        this.v_4262_N = p_i232061_1_;
    }

    public Codec<P> n_1700_B() {
        return this.v_4262_N;
    }
}


