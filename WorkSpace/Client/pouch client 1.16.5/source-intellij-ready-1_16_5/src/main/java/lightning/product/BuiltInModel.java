/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.L_4237_Q;
import lightning.product.S_3826_o;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;

public class BuiltInModel
implements S_3826_o {
    private final ItemTransforms n_1700_B;
    private final L_4237_Q J_1907_R;
    private final B_3871_I R_4764_Y;
    private final boolean G_564_y;

    public BuiltInModel(ItemTransforms cameraTransforms, L_4237_Q overrides, B_3871_I spite, boolean isSideLit) {
        this.n_1700_B = cameraTransforms;
        this.J_1907_R = overrides;
        this.R_4764_Y = spite;
        this.G_564_y = isSideLit;
    }

    @Override
    public List<c_932_S> n_1700_B(@Nullable K_4074_S state, @Nullable b_257_Y side, Random rand) {
        return Collections.emptyList();
    }

    @Override
    public boolean n_1700_B() {
        return false;
    }

    @Override
    public boolean J_1907_R() {
        return true;
    }

    @Override
    public boolean R_4764_Y() {
        return this.G_564_y;
    }

    @Override
    public boolean G_564_y() {
        return true;
    }

    @Override
    public B_3871_I P_1922_E() {
        return this.R_4764_Y;
    }

    @Override
    public ItemTransforms u_1723_Y() {
        return this.n_1700_B;
    }

    @Override
    public L_4237_Q v_4262_N() {
        return this.J_1907_R;
    }
}


