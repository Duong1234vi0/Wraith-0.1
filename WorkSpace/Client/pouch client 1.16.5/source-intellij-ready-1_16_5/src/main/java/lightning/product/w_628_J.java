/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4700_p;
import lightning.product.K_4074_S;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.a_3742_W;
import lightning.product.BlockTags;
import lightning.product.Material;

public final class w_628_J
extends Enum<w_628_J>
implements E_4700_p {
    public static final /* enum */ w_628_J n_1700_B = new w_628_J("harp", SoundEvents.Particles);
    public static final /* enum */ w_628_J J_1907_R = new w_628_J("basedrum", SoundEvents.JumpCircle);
    public static final /* enum */ w_628_J R_4764_Y = new w_628_J("snare", SoundEvents.Removals);
    public static final /* enum */ w_628_J G_564_y = new w_628_J("hat", SoundEvents.Particular);
    public static final /* enum */ w_628_J P_1922_E = new w_628_J("bass", SoundEvents.KillEffect);
    public static final /* enum */ w_628_J u_1723_Y = new w_628_J("flute", SoundEvents.R_4688_l);
    public static final /* enum */ w_628_J v_4262_N = new w_628_J("bell", SoundEvents.LogoutSpots);
    public static final /* enum */ w_628_J w_1484_f = new w_628_J("guitar", SoundEvents.ObjectInfo);
    public static final /* enum */ w_628_J t_148_a = new w_628_J("chime", SoundEvents.w_2099_r);
    public static final /* enum */ w_628_J s_956_w = new w_628_J("xylophone", SoundEvents.SantaHat);
    public static final /* enum */ w_628_J u_2550_I = new w_628_J("iron_xylophone", SoundEvents.SeeInvisibles);
    public static final /* enum */ w_628_J M_588_G = new w_628_J("cow_bell", SoundEvents.ShulkerPreview);
    public static final /* enum */ w_628_J P_4830_p = new w_628_J("didgeridoo", SoundEvents.Skeleton);
    public static final /* enum */ w_628_J h_1847_R = new w_628_J("bit", SoundEvents.t_1595_x);
    public static final /* enum */ w_628_J Q_4569_t = new w_628_J("banjo", SoundEvents.Tags);
    public static final /* enum */ w_628_J M_182_A = new w_628_J("pling", SoundEvents.Prediction);
    private final String t_1786_h;
    private final SoundEvent multiplayerClientSuggestionProvider;
    private static final /* synthetic */ w_628_J[] w_1457_N;

    public static w_628_J[] values() {
        return (w_628_J[])w_1457_N.clone();
    }

    public static w_628_J valueOf(String name) {
        return Enum.valueOf(w_628_J.class, name);
    }

    private w_628_J(String name, SoundEvent sound) {
        this.t_1786_h = name;
        this.multiplayerClientSuggestionProvider = sound;
    }

    @Override
    public String n_1700_B() {
        return this.t_1786_h;
    }

    public SoundEvent J_1907_R() {
        return this.multiplayerClientSuggestionProvider;
    }

    public static w_628_J n_1700_B(K_4074_S p_208087_0_) {
        if (p_208087_0_.n_1700_B(a_3742_W.v_887_r)) {
            return u_1723_Y;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.y_2772_m)) {
            return v_4262_N;
        }
        if (p_208087_0_.n_1700_B(BlockTags.J_1907_R)) {
            return w_1484_f;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.ServerHelper)) {
            return t_148_a;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.Nuker)) {
            return s_956_w;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.H_1883_T)) {
            return u_2550_I;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.C_415_h)) {
            return M_588_G;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.A_3244_K)) {
            return P_4830_p;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.B_2580_P)) {
            return h_1847_R;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.M_4609_z)) {
            return Q_4569_t;
        }
        if (p_208087_0_.n_1700_B(a_3742_W.X_2960_b)) {
            return M_182_A;
        }
        Material material = p_208087_0_.R_4764_Y();
        if (material == Material.d_2427_y) {
            return J_1907_R;
        }
        if (material == Material.Q_2552_b) {
            return R_4764_Y;
        }
        if (material == Material.x_607_J) {
            return G_564_y;
        }
        return material != Material.q_2307_F && material != Material.Z_875_P ? n_1700_B : P_1922_E;
    }

    private static /* synthetic */ w_628_J[] R_4764_Y() {
        return new w_628_J[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A};
    }

    static {
        w_1457_N = w_628_J.R_4764_Y();
    }
}



