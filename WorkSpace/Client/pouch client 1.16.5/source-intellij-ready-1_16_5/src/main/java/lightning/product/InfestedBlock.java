/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.A_2352_Z;
import lightning.product.F_1241_B;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_3591_l;
import lightning.product.Silverfish;
import lightning.product.q_4293_E;
import lightning.product.t_5_h;

public class InfestedBlock
extends T_2915_h {
    private final T_2915_h P_4830_p;
    private static final Map<T_2915_h, T_2915_h> h_1847_R = Maps.newIdentityHashMap();

    public InfestedBlock(T_2915_h blockIn, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = blockIn;
        h_1847_R.put(blockIn, this);
    }

    public T_2915_h J_1907_R() {
        return this.P_4830_p;
    }

    public static boolean w_1484_f(K_4074_S state) {
        return h_1847_R.containsKey(state.J_1907_R());
    }

    private void n_1700_B(e_3591_l world, c_1514_x pos) {
        Silverfish silverfishentity = t_5_h.t_4219_U.n_1700_B(world);
        silverfishentity.J_1907_R((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, 0.0f, 0.0f);
        world.a_(silverfishentity);
        silverfishentity.T_2506_i();
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
        super.n_1700_B(state, worldIn, pos, stack);
        if (worldIn.H_1990_U().J_1907_R(A_2352_Z.u_1723_Y) && K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) == 0) {
            this.n_1700_B(worldIn, pos);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, F_1241_B explosionIn) {
        if (worldIn instanceof e_3591_l) {
            this.n_1700_B((e_3591_l)worldIn, pos);
        }
    }

    public static K_4074_S n_1700_B(T_2915_h blockIn) {
        return h_1847_R.get(blockIn).multiplayerClientSuggestionProvider();
    }
}


