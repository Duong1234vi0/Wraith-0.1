/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DynamicOps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.serialization.DynamicOps;
import java.util.Optional;
import lightning.product.D_38_f;
import lightning.product.J_4485_t;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.l_4118_l;
import lightning.product.m_3054_I;
import lightning.product.n_3832_I;
import lightning.product.q_1613_l;
import lightning.product.q_2232_A;
import lightning.product.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompassItem
extends q_1613_l
implements J_4485_t {
    private static final Logger n_1700_B = LogManager.getLogger();

    public CompassItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    public static boolean G_564_y(Z_1993_T p_234670_0_) {
        U_2912_j compoundnbt = p_234670_0_.Q_4569_t();
        return compoundnbt != null && (compoundnbt.P_1922_E("LodestoneDimension") || compoundnbt.P_1922_E("LodestonePos"));
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return CompassItem.G_564_y(stack) || super.P_1922_E(stack);
    }

    public static Optional<f_2392_k<b_4507_u>> n_1700_B(U_2912_j p_234667_0_) {
        return b_4507_u.P_1922_E.parse((DynamicOps)l_4118_l.n_1700_B, (Object)p_234667_0_.R_4764_Y("LodestoneDimension")).result();
    }

    @Override
    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, N_4263_v entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.Y_259_p && CompassItem.G_564_y(stack)) {
            U_2912_j compoundnbt = stack.M_182_A();
            if (compoundnbt.P_1922_E("LodestoneTracked") && !compoundnbt.t_1786_h("LodestoneTracked")) {
                return;
            }
            Optional<f_2392_k<b_4507_u>> optional = CompassItem.n_1700_B(compoundnbt);
            if (optional.isPresent() && optional.get() == worldIn.g_2268_R() && compoundnbt.P_1922_E("LodestonePos") && !((e_3591_l)worldIn).p_178_J().n_1700_B(q_2232_A.C_2741_M, n_3832_I.J_1907_R(compoundnbt.M_182_A("LodestonePos")))) {
                compoundnbt.multiplayerClientSuggestionProvider("LodestonePos");
            }
        }
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        boolean flag;
        c_1514_x blockpos = context.getPos();
        b_4507_u world = context.getWorld();
        if (!world.getBlockState(blockpos).n_1700_B(a_3742_W.m_3052_r)) {
            return super.n_1700_B(context);
        }
        world.n_1700_B((a_3913_L)null, blockpos, SoundEvents.p_4879_r, D_38_f.w_1484_f, 1.0f, 1.0f);
        a_3913_L playerentity = context.getPlayer();
        Z_1993_T itemstack = context.getItem();
        boolean bl = flag = !playerentity.C_415_h.G_564_y && itemstack.t_4043_B() == 1;
        if (flag) {
            this.n_1700_B(world.g_2268_R(), blockpos, itemstack.M_182_A());
        } else {
            Z_1993_T itemstack1 = new Z_1993_T(Items.X_1303_p, 1);
            U_2912_j compoundnbt = itemstack.h_1847_R() ? itemstack.Q_4569_t().v_4262_N() : new U_2912_j();
            itemstack1.R_4764_Y(compoundnbt);
            if (!playerentity.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            this.n_1700_B(world.g_2268_R(), blockpos, compoundnbt);
            if (!playerentity.l_1268_F.P_1922_E(itemstack1)) {
                playerentity.n_1700_B(itemstack1, false);
            }
        }
        return m_3054_I.n_1700_B(world.Y_259_p);
    }

    private void n_1700_B(f_2392_k<b_4507_u> p_234669_1_, c_1514_x p_234669_2_, U_2912_j p_234669_3_) {
        p_234669_3_.n_1700_B("LodestonePos", n_3832_I.n_1700_B(p_234669_2_));
        b_4507_u.P_1922_E.encodeStart((DynamicOps)l_4118_l.n_1700_B, p_234669_1_).resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)).ifPresent(p_234668_1_ -> p_234669_3_.n_1700_B("LodestoneDimension", (Tag)p_234668_1_));
        p_234669_3_.n_1700_B("LodestoneTracked", true);
    }

    @Override
    public String u_1723_Y(Z_1993_T stack) {
        return CompassItem.G_564_y(stack) ? "item.minecraft.lodestone_compass" : super.u_1723_Y(stack);
    }
}


