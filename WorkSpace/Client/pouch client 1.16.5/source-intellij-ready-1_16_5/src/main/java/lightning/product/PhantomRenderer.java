/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.PhantomEyesLayer;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.m_3937_C;
import lightning.product.PhantomModel;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class PhantomRenderer
extends r_1334_c<m_3937_C, PhantomModel<m_3937_C>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/phantom.png");

    public PhantomRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new PhantomModel(), 0.75f);
        this.n_1700_B(new PhantomEyesLayer<m_3937_C>(this));
    }

    @Override
    public g_2336_b n_1700_B(m_3937_C entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(m_3937_C entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        int i = entitylivingbaseIn.u_1723_Y();
        float f = 1.0f + 0.15f * (float)i;
        matrixStackIn.n_1700_B(f, f, f);
        matrixStackIn.n_1700_B(0.0, 1.3125, 0.1875);
    }

    @Override
    protected void n_1700_B(m_3937_C entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(entityLiving.f_4016_n));
    }
}


