/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nullable;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.a_3742_W;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.q_2232_A;
import lightning.product.Items;

public class VillagerProfession {
    public static final VillagerProfession n_1700_B = VillagerProfession.n_1700_B("none", q_2232_A.R_4764_Y, null);
    public static final VillagerProfession J_1907_R = VillagerProfession.n_1700_B("armorer", q_2232_A.G_564_y, SoundEvents.h_355_y);
    public static final VillagerProfession R_4764_Y = VillagerProfession.n_1700_B("butcher", q_2232_A.P_1922_E, SoundEvents.K_3256_W);
    public static final VillagerProfession G_564_y = VillagerProfession.n_1700_B("cartographer", q_2232_A.u_1723_Y, SoundEvents.WaterlilyBlock);
    public static final VillagerProfession P_1922_E = VillagerProfession.n_1700_B("cleric", q_2232_A.v_4262_N, SoundEvents.LoomBlock);
    public static final VillagerProfession u_1723_Y = VillagerProfession.n_1700_B("farmer", q_2232_A.w_1484_f, (ImmutableSet<q_1613_l>)ImmutableSet.of((Object)Items.V_3441_j, (Object)Items.G_4691_Q, (Object)Items.MushroomBlock, (Object)Items.r_1970_q), (ImmutableSet<T_2915_h>)ImmutableSet.of((Object)a_3742_W.Z_735_d), SoundEvents.MagmaBlock);
    public static final VillagerProfession v_4262_N = VillagerProfession.n_1700_B("fisherman", q_2232_A.t_148_a, SoundEvents.MelonBlock);
    public static final VillagerProfession w_1484_f = VillagerProfession.n_1700_B("fletcher", q_2232_A.s_956_w, SoundEvents.s_3401_U);
    public static final VillagerProfession t_148_a = VillagerProfession.n_1700_B("leatherworker", q_2232_A.u_2550_I, SoundEvents.MushroomBlock);
    public static final VillagerProfession s_956_w = VillagerProfession.n_1700_B("librarian", q_2232_A.M_588_G, SoundEvents.MyceliumBlock);
    public static final VillagerProfession u_2550_I = VillagerProfession.n_1700_B("mason", q_2232_A.P_4830_p, SoundEvents.O_3671_t);
    public static final VillagerProfession M_588_G = VillagerProfession.n_1700_B("nitwit", q_2232_A.h_1847_R, null);
    public static final VillagerProfession P_4830_p = VillagerProfession.n_1700_B("shepherd", q_2232_A.Q_4569_t, SoundEvents.g_2492_v);
    public static final VillagerProfession h_1847_R = VillagerProfession.n_1700_B("toolsmith", q_2232_A.M_182_A, SoundEvents.g_2783_J);
    public static final VillagerProfession Q_4569_t = VillagerProfession.n_1700_B("weaponsmith", q_2232_A.t_1786_h, SoundEvents.NetherWartBlock);
    private final String M_182_A;
    private final q_2232_A t_1786_h;
    private final ImmutableSet<q_1613_l> multiplayerClientSuggestionProvider;
    private final ImmutableSet<T_2915_h> w_1457_N;
    @Nullable
    private final SoundEvent Y_601_j;

    private VillagerProfession(String nameIn, q_2232_A pointOfInterestIn, ImmutableSet<q_1613_l> specificItemsIn, ImmutableSet<T_2915_h> relatedWorldBlocksIn, @Nullable SoundEvent soundIn) {
        this.M_182_A = nameIn;
        this.t_1786_h = pointOfInterestIn;
        this.multiplayerClientSuggestionProvider = specificItemsIn;
        this.w_1457_N = relatedWorldBlocksIn;
        this.Y_601_j = soundIn;
    }

    public q_2232_A n_1700_B() {
        return this.t_1786_h;
    }

    public ImmutableSet<q_1613_l> J_1907_R() {
        return this.multiplayerClientSuggestionProvider;
    }

    public ImmutableSet<T_2915_h> R_4764_Y() {
        return this.w_1457_N;
    }

    @Nullable
    public SoundEvent G_564_y() {
        return this.Y_601_j;
    }

    public String toString() {
        return this.M_182_A;
    }

    static VillagerProfession n_1700_B(String nameIn, q_2232_A pointOfInterestIn, @Nullable SoundEvent soundIn) {
        return VillagerProfession.n_1700_B(nameIn, pointOfInterestIn, (ImmutableSet<q_1613_l>)ImmutableSet.of(), (ImmutableSet<T_2915_h>)ImmutableSet.of(), soundIn);
    }

    static VillagerProfession n_1700_B(String nameIn, q_2232_A pointOfInterestIn, ImmutableSet<q_1613_l> specificItemsIn, ImmutableSet<T_2915_h> relatedWorldBlocksIn, @Nullable SoundEvent soundIn) {
        return V_3137_a.n_1700_B(V_3137_a.r_715_M, new g_2336_b(nameIn), new VillagerProfession(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn));
    }
}


