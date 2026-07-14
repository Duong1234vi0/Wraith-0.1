/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class LocateHidingPlace
extends Behavior<r_4811_B> {
    private final float n_1700_B;
    private final int R_4764_Y;
    private final int G_564_y;
    private Optional<c_1514_x> P_1922_E = Optional.empty();

    public LocateHidingPlace(int p_i50361_1_, float speed, int p_i50361_3_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.J_1907_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.H_2857_Y, (Object)((Object)S_50_d.R_4764_Y)));
        this.R_4764_Y = p_i50361_1_;
        this.n_1700_B = speed;
        this.G_564_y = p_i50361_3_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        Optional<c_1514_x> optional = worldIn.p_178_J().R_4764_Y(poiType -> poiType == q_2232_A.multiplayerClientSuggestionProvider, pos -> true, owner.b_2312_j(), this.G_564_y + 1, b_4946_z.J_1907_R.R_4764_Y);
        this.P_1922_E = optional.isPresent() && optional.get().withinDistance(owner.s_4990_V(), (double)this.G_564_y) ? optional : Optional.empty();
        return true;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        Optional<F_427_K> optional1;
        E_4668_a<?> brain = entityIn.y_1945_D();
        Optional<c_1514_x> optional = this.P_1922_E;
        if (!optional.isPresent() && !(optional = worldIn.p_178_J().n_1700_B(poiType -> poiType == q_2232_A.multiplayerClientSuggestionProvider, pos -> true, b_4946_z.J_1907_R.R_4764_Y, entityIn.b_2312_j(), this.R_4764_Y, entityIn.M_3508_C())).isPresent() && (optional1 = brain.R_4764_Y(MemoryModuleType.J_1907_R)).isPresent()) {
            optional = Optional.of(optional1.get().J_1907_R());
        }
        if (optional.isPresent()) {
            brain.J_1907_R(MemoryModuleType.Y_601_j);
            brain.J_1907_R(MemoryModuleType.h_1847_R);
            brain.J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
            brain.J_1907_R(MemoryModuleType.t_1786_h);
            brain.n_1700_B(MemoryModuleType.H_2857_Y, F_427_K.n_1700_B(worldIn.g_2268_R(), optional.get()));
            if (!optional.get().withinDistance(entityIn.s_4990_V(), (double)this.G_564_y)) {
                brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(optional.get(), this.n_1700_B, this.G_564_y));
            }
        }
    }
}


