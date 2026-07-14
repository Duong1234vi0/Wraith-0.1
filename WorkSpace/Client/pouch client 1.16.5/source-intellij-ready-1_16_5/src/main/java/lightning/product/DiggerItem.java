/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 */
package lightning.product;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.Set;
import lightning.product.TieredItem;
import lightning.product.Attributes;
import lightning.product.J_4485_t;
import lightning.product.K_4074_S;
import lightning.product.Attribute;
import lightning.product.T_2915_h;
import lightning.product.U_1880_G;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.Tier;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;

public class DiggerItem
extends TieredItem
implements J_4485_t {
    private final Set<T_2915_h> n_1700_B;
    protected final float J_1907_R;
    private final float R_4764_Y;
    private final Multimap<Attribute, U_1880_G> G_564_y;

    protected DiggerItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<T_2915_h> effectiveBlocksIn, q_1613_l.n_1700_B builderIn) {
        super(tier, builderIn);
        this.n_1700_B = effectiveBlocksIn;
        this.J_1907_R = tier.J_1907_R();
        this.R_4764_Y = attackDamageIn + tier.R_4764_Y();
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)Attributes.u_1723_Y, (Object)new U_1880_G(u_1723_Y, "Tool modifier", (double)this.R_4764_Y, U_1880_G.n_1700_B.n_1700_B));
        builder.put((Object)Attributes.w_1484_f, (Object)new U_1880_G(v_4262_N, "Tool modifier", (double)attackSpeedIn, U_1880_G.n_1700_B.n_1700_B));
        this.G_564_y = builder.build();
    }

    @Override
    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        return this.n_1700_B.contains(state.J_1907_R()) ? this.J_1907_R : 1.0f;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, r_4811_B target, r_4811_B attacker) {
        stack.n_1700_B(2, attacker, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        return true;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, K_4074_S state, c_1514_x pos, r_4811_B entityLiving) {
        if (!worldIn.Y_259_p && state.w_1484_f(worldIn, pos) != 0.0f) {
            stack.n_1700_B(1, entityLiving, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        }
        return true;
    }

    @Override
    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        return equipmentSlot == e_1174_E.n_1700_B ? this.G_564_y : super.n_1700_B(equipmentSlot);
    }

    public float v_4262_N() {
        return this.R_4764_Y;
    }
}


