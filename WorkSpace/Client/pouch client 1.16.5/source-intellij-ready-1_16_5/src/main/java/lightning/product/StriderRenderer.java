/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.StriderModel;
import lightning.product.L_3233_K;
import lightning.product.SaddleLayer;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;

public class StriderRenderer
extends r_1334_c<L_3233_K, StriderModel<L_3233_K>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/strider/strider.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/strider/strider_cold.png");

    public StriderRenderer(w_2040_b p_i232473_1_) {
        super(p_i232473_1_, new StriderModel(), 0.5f);
        this.n_1700_B(new SaddleLayer(this, new StriderModel(), new g_2336_b("textures/entity/strider/strider_saddle.png")));
    }

    @Override
    public g_2336_b n_1700_B(L_3233_K entity) {
        return entity.y_4642_Y() ? t_1786_h : n_1700_B;
    }

    @Override
    protected void n_1700_B(L_3233_K entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        if (entitylivingbaseIn.d_()) {
            matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            this.R_4764_Y = 0.25f;
        } else {
            this.R_4764_Y = 0.5f;
        }
    }

    @Override
    protected boolean J_1907_R(L_3233_K p_230495_1_) {
        return p_230495_1_.y_4642_Y();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(r_4811_B r_4811_B2) {
        return this.J_1907_R((L_3233_K)r_4811_B2);
    }
}


