/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.ItemTags;
import lightning.product.b_3278_X;
import lightning.product.l_52_h;
import lightning.product.Tier;
import lightning.product.Items;

public final class S_446_o
extends Enum<S_446_o>
implements Tier {
    public static final /* enum */ S_446_o n_1700_B = new S_446_o(0, 59, 2.0f, 0.0f, 15, () -> b_3278_X.n_1700_B(ItemTags.R_4764_Y));
    public static final /* enum */ S_446_o J_1907_R = new S_446_o(1, 131, 4.0f, 1.0f, 5, () -> b_3278_X.n_1700_B(ItemTags.D_4792_h));
    public static final /* enum */ S_446_o R_4764_Y = new S_446_o(2, 250, 6.0f, 2.0f, 14, () -> b_3278_X.n_1700_B(Items.D_1621_L));
    public static final /* enum */ S_446_o G_564_y = new S_446_o(3, 1561, 8.0f, 3.0f, 10, () -> b_3278_X.n_1700_B(Items.k_2273_q));
    public static final /* enum */ S_446_o P_1922_E = new S_446_o(0, 32, 12.0f, 0.0f, 22, () -> b_3278_X.n_1700_B(Items.ServerHandshakePacketListener));
    public static final /* enum */ S_446_o u_1723_Y = new S_446_o(4, 2031, 9.0f, 4.0f, 15, () -> b_3278_X.n_1700_B(Items.q_4124_m));
    private final int v_4262_N;
    private final int w_1484_f;
    private final float t_148_a;
    private final float s_956_w;
    private final int u_2550_I;
    private final l_52_h<b_3278_X> M_588_G;
    private static final /* synthetic */ S_446_o[] P_4830_p;

    public static S_446_o[] values() {
        return (S_446_o[])P_4830_p.clone();
    }

    public static S_446_o valueOf(String name) {
        return Enum.valueOf(S_446_o.class, name);
    }

    private S_446_o(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<b_3278_X> repairMaterialIn) {
        this.v_4262_N = harvestLevelIn;
        this.w_1484_f = maxUsesIn;
        this.t_148_a = efficiencyIn;
        this.s_956_w = attackDamageIn;
        this.u_2550_I = enchantabilityIn;
        this.M_588_G = new l_52_h<b_3278_X>(repairMaterialIn);
    }

    @Override
    public int n_1700_B() {
        return this.w_1484_f;
    }

    @Override
    public float J_1907_R() {
        return this.t_148_a;
    }

    @Override
    public float R_4764_Y() {
        return this.s_956_w;
    }

    @Override
    public int G_564_y() {
        return this.v_4262_N;
    }

    @Override
    public int P_1922_E() {
        return this.u_2550_I;
    }

    @Override
    public b_3278_X u_1723_Y() {
        return this.M_588_G.n_1700_B();
    }

    private static /* synthetic */ S_446_o[] P_4830_p() {
        return new S_446_o[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        P_4830_p = S_446_o.P_4830_p();
    }
}


