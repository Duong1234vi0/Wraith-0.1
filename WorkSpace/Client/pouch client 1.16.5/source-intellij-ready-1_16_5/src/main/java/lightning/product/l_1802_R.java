/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.o_3091_w;
import lightning.product.BlockEntityType;
import lightning.product.t_5_h;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.util.Either;

public abstract class l_1802_R<T extends i_2154_H>
implements IEntityRenderer {
    protected final f_2689_h v_4262_N;
    private BlockEntityType n_1700_B = null;
    private g_2336_b J_1907_R = null;

    public l_1802_R(f_2689_h rendererDispatcherIn) {
        this.v_4262_N = rendererDispatcherIn;
    }

    public abstract void n_1700_B(T var1, float var2, g_221_o var3, o_3091_w var4, int var5, int var6);

    public boolean n_1700_B(T te) {
        return false;
    }

    @Override
    public Either<t_5_h, BlockEntityType> getType() {
        return this.n_1700_B == null ? null : Either.makeRight(this.n_1700_B);
    }

    @Override
    public void setType(Either<t_5_h, BlockEntityType> p_setType_1_) {
        this.n_1700_B = p_setType_1_.getRight().get();
    }

    @Override
    public g_2336_b getLocationTextureCustom() {
        return this.J_1907_R;
    }

    @Override
    public void setLocationTextureCustom(g_2336_b p_setLocationTextureCustom_1_) {
        this.J_1907_R = p_setLocationTextureCustom_1_;
    }
}


