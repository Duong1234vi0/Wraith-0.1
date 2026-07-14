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
import lightning.product.TieredItem;
import lightning.product.Attributes;
import lightning.product.J_4485_t;
import lightning.product.K_4074_S;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.BlockTags;
import lightning.product.Tier;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.Material;

public class SwordItem
extends TieredItem
implements J_4485_t {
    private final float n_1700_B;
    private final Multimap<Attribute, U_1880_G> J_1907_R;

    public SwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, q_1613_l.n_1700_B builderIn) {
        super(tier, builderIn);
        this.n_1700_B = (float)attackDamageIn + tier.R_4764_Y();
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)Attributes.u_1723_Y, (Object)new U_1880_G(u_1723_Y, "Weapon modifier", (double)this.n_1700_B, U_1880_G.n_1700_B.n_1700_B));
        builder.put((Object)Attributes.w_1484_f, (Object)new U_1880_G(v_4262_N, "Weapon modifier", (double)attackSpeedIn, U_1880_G.n_1700_B.n_1700_B));
        this.J_1907_R = builder.build();
    }

    public float v_4262_N() {
        return this.n_1700_B;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        return !player.G_624_v();
    }

    @Override
    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        if (state.n_1700_B(a_3742_W.y_1700_S)) {
            return 15.0f;
        }
        Material material = state.R_4764_Y();
        return material != Material.P_1922_E && material != Material.v_4262_N && material != Material.q_4610_l && !state.n_1700_B(BlockTags.d_2427_y) && material != Material.z_4693_k ? 1.0f : 1.5f;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, r_4811_B target, r_4811_B attacker) {
        stack.n_1700_B(1, attacker, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        return true;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, K_4074_S state, c_1514_x pos, r_4811_B entityLiving) {
        if (state.w_1484_f(worldIn, pos) != 0.0f) {
            stack.n_1700_B(2, entityLiving, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        }
        return true;
    }

    @Override
    public boolean J_1907_R(K_4074_S blockIn) {
        return blockIn.n_1700_B(a_3742_W.y_1700_S);
    }

    @Override
    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        return equipmentSlot == e_1174_E.n_1700_B ? this.J_1907_R : super.n_1700_B(equipmentSlot);
    }
}


