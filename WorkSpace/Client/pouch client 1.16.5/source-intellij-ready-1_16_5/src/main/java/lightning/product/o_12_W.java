/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lightning.product.F_2904_S;
import lightning.product.O_2639_P;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.StandingAndWallBlockItem;
import lightning.product.n_3832_I;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;

public class o_12_W
extends StandingAndWallBlockItem {
    public o_12_W(T_2915_h floorBlockIn, T_2915_h wallBlockIn, q_1613_l.n_1700_B builder) {
        super(floorBlockIn, wallBlockIn, builder);
    }

    @Override
    public x_282_a w_1484_f(Z_1993_T stack) {
        if (stack.J_1907_R() == Items.C_3560_B && stack.h_1847_R()) {
            U_2912_j compoundnbt1;
            String s = null;
            U_2912_j compoundnbt = stack.Q_4569_t();
            if (compoundnbt.R_4764_Y("SkullOwner", 8)) {
                s = compoundnbt.M_588_G("SkullOwner");
            } else if (compoundnbt.R_4764_Y("SkullOwner", 10) && (compoundnbt1 = compoundnbt.M_182_A("SkullOwner")).R_4764_Y("Name", 8)) {
                s = compoundnbt1.M_588_G("Name");
            }
            if (s != null) {
                return new F_2904_S(this.J_1907_R() + ".named", s);
            }
        }
        return super.w_1484_f(stack);
    }

    @Override
    public boolean J_1907_R(U_2912_j nbt) {
        super.J_1907_R(nbt);
        if (nbt.R_4764_Y("SkullOwner", 8) && !StringUtils.isBlank((CharSequence)nbt.M_588_G("SkullOwner"))) {
            GameProfile gameprofile = new GameProfile((UUID)null, nbt.M_588_G("SkullOwner"));
            gameprofile = O_2639_P.J_1907_R(gameprofile);
            nbt.n_1700_B("SkullOwner", n_3832_I.n_1700_B(new U_2912_j(), gameprofile));
            return true;
        }
        return false;
    }
}


