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

public abstract class ListModel<E extends N_4263_v>
extends EntityModel<E> {
    public ListModel() {
        this(o_2576_A::G_564_y);
    }

    public ListModel(Function<g_2336_b, o_2576_A> p_i232335_1_) {
        super(p_i232335_1_);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.n_1700_B().forEach(p_228272_8_ -> p_228272_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
    }

    public abstract Iterable<e_4189_z> n_1700_B();
}


