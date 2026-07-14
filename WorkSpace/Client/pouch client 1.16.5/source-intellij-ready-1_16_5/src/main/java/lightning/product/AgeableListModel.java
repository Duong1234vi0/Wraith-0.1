/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Function;
import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;

public abstract class AgeableListModel<E extends N_4263_v>
extends EntityModel<E> {
    private final boolean n_1700_B;
    private final float J_1907_R;
    private final float R_4764_Y;
    private final float G_564_y;
    private final float P_1922_E;
    private final float u_1723_Y;

    protected AgeableListModel(boolean p_i225943_1_, float p_i225943_2_, float p_i225943_3_) {
        this(p_i225943_1_, p_i225943_2_, p_i225943_3_, 2.0f, 2.0f, 24.0f);
    }

    protected AgeableListModel(boolean p_i225944_1_, float p_i225944_2_, float p_i225944_3_, float p_i225944_4_, float p_i225944_5_, float p_i225944_6_) {
        this(o_2576_A::G_564_y, p_i225944_1_, p_i225944_2_, p_i225944_3_, p_i225944_4_, p_i225944_5_, p_i225944_6_);
    }

    protected AgeableListModel(Function<g_2336_b, o_2576_A> p_i225942_1_, boolean p_i225942_2_, float p_i225942_3_, float p_i225942_4_, float p_i225942_5_, float p_i225942_6_, float p_i225942_7_) {
        super(p_i225942_1_);
        this.n_1700_B = p_i225942_2_;
        this.J_1907_R = p_i225942_3_;
        this.R_4764_Y = p_i225942_4_;
        this.G_564_y = p_i225942_5_;
        this.P_1922_E = p_i225942_6_;
        this.u_1723_Y = p_i225942_7_;
    }

    protected AgeableListModel() {
        this(false, 5.0f, 2.0f);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.M_182_A) {
            matrixStackIn.n_1700_B();
            if (this.n_1700_B) {
                float f = 1.5f / this.G_564_y;
                matrixStackIn.n_1700_B(f, f, f);
            }
            matrixStackIn.n_1700_B(0.0, (double)(this.J_1907_R / 16.0f), (double)(this.R_4764_Y / 16.0f));
            this.n_1700_B().forEach(p_228230_8_ -> p_228230_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            float f1 = 1.0f / this.P_1922_E;
            matrixStackIn.n_1700_B(f1, f1, f1);
            matrixStackIn.n_1700_B(0.0, (double)(this.u_1723_Y / 16.0f), 0.0);
            this.J_1907_R().forEach(p_228229_8_ -> p_228229_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
        } else {
            this.n_1700_B().forEach(p_228228_8_ -> p_228228_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            this.J_1907_R().forEach(p_228227_8_ -> p_228227_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
        }
    }

    protected abstract Iterable<e_4189_z> n_1700_B();

    protected abstract Iterable<e_4189_z> J_1907_R();
}


