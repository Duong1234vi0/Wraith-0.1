/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.J_2538_C;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.e_933_M;
import lightning.product.StandingAndWallBlockItem;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.AbstractBannerBlock;
import lightning.product.x_282_a;
import org.apache.commons.lang3.Validate;

public class x_2414_j
extends StandingAndWallBlockItem {
    public x_2414_j(T_2915_h p_i48529_1_, T_2915_h p_i48529_2_, q_1613_l.n_1700_B builder) {
        super(p_i48529_1_, p_i48529_2_, builder);
        Validate.isInstanceOf(AbstractBannerBlock.class, (Object)p_i48529_1_);
        Validate.isInstanceOf(AbstractBannerBlock.class, (Object)p_i48529_2_);
    }

    public static void n_1700_B(Z_1993_T stack, List<x_282_a> p_185054_1_) {
        U_2912_j compoundnbt = stack.J_1907_R("BlockEntityTag");
        if (compoundnbt != null && compoundnbt.P_1922_E("Patterns")) {
            q_2896_o listnbt = compoundnbt.G_564_y("Patterns", 10);
            for (int i = 0; i < listnbt.size() && i < 6; ++i) {
                U_2912_j compoundnbt1 = listnbt.n_1700_B(i);
                e_933_M dyecolor = e_933_M.n_1700_B(compoundnbt1.w_1484_f("Color"));
                J_2538_C bannerpattern = J_2538_C.n_1700_B(compoundnbt1.M_588_G("Pattern"));
                if (bannerpattern == null) continue;
                p_185054_1_.add(new F_2904_S("block.minecraft.banner." + bannerpattern.n_1700_B() + "." + dyecolor.R_4764_Y()).n_1700_B(D_4024_W.w_1484_f));
            }
        }
    }

    public e_933_M R_4764_Y() {
        return ((AbstractBannerBlock)this.v_4262_N()).J_1907_R();
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        x_2414_j.n_1700_B(stack, tooltip);
    }
}


