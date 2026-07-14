/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  lombok.Generated
 */
package lightning.product;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;
import lightning.product.D_2530_r;
import lightning.product.DispenseItemBehavior;
import lightning.product.Attributes;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.L_1434_v;
import lightning.product.Attribute;
import lightning.product.N_4263_v;
import lightning.product.S_3458_C;
import lightning.product.U_1880_G;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.ArmorMaterial;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;
import lightning.product.BlockSource;
import lombok.Generated;

public class R_2515_i
extends q_1613_l
implements D_2530_r {
    private static final UUID[] s_956_w = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    public static final DispenseItemBehavior n_1700_B = new DefaultDispenseItemBehavior(){

        @Override
        protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
            return R_2515_i.n_1700_B(source, stack) ? stack : super.n_1700_B(source, stack);
        }
    };
    protected final e_1174_E J_1907_R;
    private final int u_2550_I;
    private final float M_588_G;
    protected final float R_4764_Y;
    protected final ArmorMaterial G_564_y;
    private final Multimap<Attribute, U_1880_G> P_4830_p;

    public static boolean n_1700_B(BlockSource blockSource, Z_1993_T stack) {
        c_1514_x blockpos = blockSource.G_564_y().offset(blockSource.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
        List<N_4263_v> list = blockSource.v_4262_N().n_1700_B(r_4811_B.class, new I_4817_s(blockpos), I_408_V.v_4262_N.and(new I_408_V.n_1700_B(stack)));
        if (list.isEmpty()) {
            return false;
        }
        r_4811_B livingentity = (r_4811_B)list.get(0);
        e_1174_E equipmentslottype = Z_530_i.s_956_w(stack);
        Z_1993_T itemstack = stack.n_1700_B(1);
        livingentity.n_1700_B(equipmentslottype, itemstack);
        if (livingentity instanceof Z_530_i) {
            ((Z_530_i)livingentity).n_1700_B(equipmentslottype, 2.0f);
            ((Z_530_i)livingentity).T_3594_S();
        }
        return true;
    }

    public R_2515_i(ArmorMaterial materialIn, e_1174_E slot, q_1613_l.n_1700_B builderIn) {
        super(builderIn.J_1907_R(materialIn.n_1700_B(slot)));
        this.G_564_y = materialIn;
        this.J_1907_R = slot;
        this.u_2550_I = materialIn.J_1907_R(slot);
        this.M_588_G = materialIn.P_1922_E();
        this.R_4764_Y = materialIn.u_1723_Y();
        S_3458_C.n_1700_B(this, n_1700_B);
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        UUID uuid = s_956_w[slot.J_1907_R()];
        builder.put((Object)Attributes.t_148_a, (Object)new U_1880_G(uuid, "Armor modifier", (double)this.u_2550_I, U_1880_G.n_1700_B.n_1700_B));
        builder.put((Object)Attributes.s_956_w, (Object)new U_1880_G(uuid, "Armor toughness", (double)this.M_588_G, U_1880_G.n_1700_B.n_1700_B));
        if (materialIn == L_1434_v.v_4262_N) {
            builder.put((Object)Attributes.R_4764_Y, (Object)new U_1880_G(uuid, "Armor knockback resistance", (double)this.R_4764_Y, U_1880_G.n_1700_B.n_1700_B));
        }
        this.P_4830_p = builder.build();
    }

    public e_1174_E R_4764_Y() {
        return this.J_1907_R;
    }

    @Override
    public int G_564_y() {
        return this.G_564_y.n_1700_B();
    }

    public ArmorMaterial P_1922_E() {
        return this.G_564_y;
    }

    @Override
    public boolean n_1700_B(Z_1993_T toRepair, Z_1993_T repair) {
        return this.G_564_y.R_4764_Y().n_1700_B(repair) || super.n_1700_B(toRepair, repair);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstack);
        Z_1993_T itemstack1 = playerIn.J_1907_R(equipmentslottype);
        if (itemstack1.n_1700_B()) {
            playerIn.n_1700_B(equipmentslottype, itemstack.t_148_a());
            itemstack.P_1922_E(0);
            return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
        }
        return InteractionResultHolder.G_564_y(itemstack);
    }

    @Override
    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        return equipmentSlot == this.J_1907_R ? this.P_4830_p : super.n_1700_B(equipmentSlot);
    }

    public int v_4262_N() {
        return this.u_2550_I;
    }

    public float w_1484_f() {
        return this.M_588_G;
    }

    @Generated
    public e_1174_E t_148_a() {
        return this.J_1907_R;
    }

    @Generated
    public float s_956_w() {
        return this.M_588_G;
    }
}


