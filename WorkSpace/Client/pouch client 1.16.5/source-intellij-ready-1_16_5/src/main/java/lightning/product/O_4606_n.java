/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lightning.product.E_4700_p;
import lightning.product.b_257_Y;

public final class O_4606_n
extends Enum<O_4606_n>
implements E_4700_p {
    public static final /* enum */ O_4606_n n_1700_B = new O_4606_n("down_east", b_257_Y.n_1700_B, b_257_Y.u_1723_Y);
    public static final /* enum */ O_4606_n J_1907_R = new O_4606_n("down_north", b_257_Y.n_1700_B, b_257_Y.R_4764_Y);
    public static final /* enum */ O_4606_n R_4764_Y = new O_4606_n("down_south", b_257_Y.n_1700_B, b_257_Y.G_564_y);
    public static final /* enum */ O_4606_n G_564_y = new O_4606_n("down_west", b_257_Y.n_1700_B, b_257_Y.P_1922_E);
    public static final /* enum */ O_4606_n P_1922_E = new O_4606_n("up_east", b_257_Y.J_1907_R, b_257_Y.u_1723_Y);
    public static final /* enum */ O_4606_n u_1723_Y = new O_4606_n("up_north", b_257_Y.J_1907_R, b_257_Y.R_4764_Y);
    public static final /* enum */ O_4606_n v_4262_N = new O_4606_n("up_south", b_257_Y.J_1907_R, b_257_Y.G_564_y);
    public static final /* enum */ O_4606_n w_1484_f = new O_4606_n("up_west", b_257_Y.J_1907_R, b_257_Y.P_1922_E);
    public static final /* enum */ O_4606_n t_148_a = new O_4606_n("west_up", b_257_Y.P_1922_E, b_257_Y.J_1907_R);
    public static final /* enum */ O_4606_n s_956_w = new O_4606_n("east_up", b_257_Y.u_1723_Y, b_257_Y.J_1907_R);
    public static final /* enum */ O_4606_n u_2550_I = new O_4606_n("north_up", b_257_Y.R_4764_Y, b_257_Y.J_1907_R);
    public static final /* enum */ O_4606_n M_588_G = new O_4606_n("south_up", b_257_Y.G_564_y, b_257_Y.J_1907_R);
    private static final Int2ObjectMap<O_4606_n> P_4830_p;
    private final String h_1847_R;
    private final b_257_Y Q_4569_t;
    private final b_257_Y M_182_A;
    private static final /* synthetic */ O_4606_n[] t_1786_h;

    public static O_4606_n[] values() {
        return (O_4606_n[])t_1786_h.clone();
    }

    public static O_4606_n valueOf(String name) {
        return Enum.valueOf(O_4606_n.class, name);
    }

    private static int J_1907_R(b_257_Y p_239643_0_, b_257_Y p_239643_1_) {
        return p_239643_0_.ordinal() << 3 | p_239643_1_.ordinal();
    }

    private O_4606_n(String p_i232507_3_, b_257_Y p_i232507_4_, b_257_Y p_i232507_5_) {
        this.h_1847_R = p_i232507_3_;
        this.M_182_A = p_i232507_4_;
        this.Q_4569_t = p_i232507_5_;
    }

    @Override
    public String n_1700_B() {
        return this.h_1847_R;
    }

    public static O_4606_n n_1700_B(b_257_Y p_239641_0_, b_257_Y p_239641_1_) {
        int i = O_4606_n.J_1907_R(p_239641_1_, p_239641_0_);
        return (O_4606_n)P_4830_p.get(i);
    }

    public b_257_Y J_1907_R() {
        return this.M_182_A;
    }

    public b_257_Y R_4764_Y() {
        return this.Q_4569_t;
    }

    private static /* synthetic */ O_4606_n[] G_564_y() {
        return new O_4606_n[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G};
    }

    static {
        t_1786_h = O_4606_n.G_564_y();
        P_4830_p = new Int2ObjectOpenHashMap(O_4606_n.values().length);
        for (O_4606_n jigsaworientation : O_4606_n.values()) {
            P_4830_p.put(O_4606_n.J_1907_R(jigsaworientation.Q_4569_t, jigsaworientation.M_182_A), (Object)jigsaworientation);
        }
    }
}

