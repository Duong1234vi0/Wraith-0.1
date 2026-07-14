/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.UseOnContext;
import lightning.product.Y_408_h;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class A_1371_U
extends q_1613_l {
    public A_1371_U(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return true;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        if (!worldIn.Y_259_p) {
            this.n_1700_B(player, state, worldIn, pos, false, player.R_4764_Y(x_1688_C.n_1700_B));
        }
        return false;
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        a_3913_L playerentity = context.getPlayer();
        b_4507_u world = context.getWorld();
        if (!world.Y_259_p && playerentity != null) {
            c_1514_x blockpos = context.getPos();
            this.n_1700_B(playerentity, world.getBlockState(blockpos), world, blockpos, true, context.getItem());
        }
        return m_3054_I.n_1700_B(world.Y_259_p);
    }

    private void n_1700_B(a_3913_L player, K_4074_S state, LevelAccessor worldIn, c_1514_x pos, boolean rightClick, Z_1993_T stack) {
        if (player.ModuleManager()) {
            T_2915_h block = state.J_1907_R();
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
            Collection<v_3760_Q<?>> collection = statecontainer.G_564_y();
            String s = V_3137_a.q_4610_l.J_1907_R(block).toString();
            if (collection.isEmpty()) {
                A_1371_U.n_1700_B(player, new F_2904_S(this.J_1907_R() + ".empty", s));
            } else {
                U_2912_j compoundnbt = stack.n_1700_B("DebugProperty");
                String s1 = compoundnbt.M_588_G(s);
                v_3760_Q<?> property = statecontainer.n_1700_B(s1);
                if (rightClick) {
                    if (property == null) {
                        property = collection.iterator().next();
                    }
                    K_4074_S blockstate = A_1371_U.n_1700_B(state, property, player.z_3000_g());
                    worldIn.n_1700_B(pos, blockstate, 18);
                    A_1371_U.n_1700_B(player, new F_2904_S(this.J_1907_R() + ".update", property.P_1922_E(), A_1371_U.n_1700_B(blockstate, property)));
                } else {
                    property = A_1371_U.n_1700_B(collection, property, player.z_3000_g());
                    String s2 = property.P_1922_E();
                    compoundnbt.n_1700_B(s, s2);
                    A_1371_U.n_1700_B(player, new F_2904_S(this.J_1907_R() + ".select", s2, A_1371_U.n_1700_B(state, property)));
                }
            }
        }
    }

    private static <T extends Comparable<T>> K_4074_S n_1700_B(K_4074_S state, v_3760_Q<T> propertyIn, boolean backwards) {
        return (K_4074_S)state.n_1700_B(propertyIn, (Comparable)A_1371_U.n_1700_B(propertyIn.n_1700_B(), state.R_4764_Y(propertyIn), backwards));
    }

    private static <T> T n_1700_B(Iterable<T> allowedValues, @Nullable T currentValue, boolean backwards) {
        return backwards ? j_3341_s.J_1907_R(allowedValues, currentValue) : j_3341_s.n_1700_B(allowedValues, currentValue);
    }

    private static void n_1700_B(a_3913_L player, x_282_a text) {
        ((B_4088_l)player).n_1700_B(text, Y_408_h.R_4764_Y, j_3341_s.J_1907_R);
    }

    private static <T extends Comparable<T>> String n_1700_B(K_4074_S state, v_3760_Q<T> propertyIn) {
        return propertyIn.n_1700_B(state.R_4764_Y(propertyIn));
    }
}



