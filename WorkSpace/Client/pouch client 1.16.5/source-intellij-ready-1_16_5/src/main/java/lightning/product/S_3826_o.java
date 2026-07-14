/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.L_4237_Q;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import net.minecraftforge.client.extensions.IForgeBakedModel;

public interface S_3826_o
extends IForgeBakedModel {
    public List<c_932_S> n_1700_B(@Nullable K_4074_S var1, @Nullable b_257_Y var2, Random var3);

    public boolean n_1700_B();

    public boolean J_1907_R();

    public boolean R_4764_Y();

    public boolean G_564_y();

    public B_3871_I P_1922_E();

    default public ItemTransforms u_1723_Y() {
        return ItemTransforms.n_1700_B;
    }

    public L_4237_Q v_4262_N();
}


