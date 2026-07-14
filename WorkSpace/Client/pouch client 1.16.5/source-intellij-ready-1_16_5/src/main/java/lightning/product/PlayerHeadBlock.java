/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.O_2639_P;
import lightning.product.U_2912_j;
import lightning.product.SkullBlock;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import org.apache.commons.lang3.StringUtils;

public class PlayerHeadBlock
extends SkullBlock {
    protected PlayerHeadBlock(q_4293_E.P_1922_E properties) {
        super(SkullBlock.J_1907_R.R_4764_Y, properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        super.n_1700_B(worldIn, pos, state, placer, stack);
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof O_2639_P) {
            O_2639_P skulltileentity = (O_2639_P)tileentity;
            GameProfile gameprofile = null;
            if (stack.h_1847_R()) {
                U_2912_j compoundnbt = stack.Q_4569_t();
                if (compoundnbt.R_4764_Y("SkullOwner", 10)) {
                    gameprofile = n_3832_I.n_1700_B(compoundnbt.M_182_A("SkullOwner"));
                } else if (compoundnbt.R_4764_Y("SkullOwner", 8) && !StringUtils.isBlank((CharSequence)compoundnbt.M_588_G("SkullOwner"))) {
                    gameprofile = new GameProfile((UUID)null, compoundnbt.M_588_G("SkullOwner"));
                }
            }
            skulltileentity.n_1700_B(gameprofile);
        }
    }
}


