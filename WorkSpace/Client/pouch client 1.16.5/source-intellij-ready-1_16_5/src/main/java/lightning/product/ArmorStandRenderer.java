/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_4315_z;
import lightning.product.D_686_b;
import lightning.product.I_4939_I;
import lightning.product.M_1336_P;
import lightning.product.ArmorStandArmorModel;
import lightning.product.g_2016_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.o_4479_Q;
import lightning.product.u_530_F;
import lightning.product.ArmorStandModel;
import lightning.product.w_2040_b;
import lightning.product.x_4904_Z;

public class ArmorStandRenderer
extends o_4479_Q<D_686_b, ArmorStandArmorModel> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/armorstand/wood.png");

    public ArmorStandRenderer(w_2040_b manager) {
        super(manager, new ArmorStandModel(), 0.0f);
        this.n_1700_B(new B_4315_z<D_686_b, ArmorStandArmorModel, ArmorStandArmorModel>(this, new ArmorStandArmorModel(0.5f), new ArmorStandArmorModel(1.0f)));
        this.n_1700_B(new x_4904_Z<D_686_b, ArmorStandArmorModel>(this));
        this.n_1700_B(new I_4939_I<D_686_b, ArmorStandArmorModel>(this));
        this.n_1700_B(new g_2016_P<D_686_b, ArmorStandArmorModel>(this));
    }

    @Override
    public g_2336_b n_1700_B(D_686_b entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(D_686_b entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - rotationYaw));
        float f = (float)(entityLiving.O_508_d.X_933_l() - entityLiving.w_1484_f) + partialTicks;
        if (f < 5.0f) {
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.n_1700_B(f / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean J_1907_R(D_686_b entity) {
        double d0 = this.J_1907_R.J_1907_R(entity);
        float f = entity.Z_875_P() ? 32.0f : 64.0f;
        return d0 >= (double)(f * f) ? false : entity.V_118_c();
    }

    @Override
    @Nullable
    protected o_2576_A n_1700_B(D_686_b p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        if (!p_230496_1_.Q_4569_t()) {
            return super.n_1700_B(p_230496_1_, p_230496_2_, p_230496_3_, p_230496_4_);
        }
        g_2336_b resourcelocation = this.n_1700_B(p_230496_1_);
        if (p_230496_3_) {
            return o_2576_A.R_4764_Y(resourcelocation, false);
        }
        return p_230496_2_ ? o_2576_A.n_1700_B(resourcelocation, false) : null;
    }
}


