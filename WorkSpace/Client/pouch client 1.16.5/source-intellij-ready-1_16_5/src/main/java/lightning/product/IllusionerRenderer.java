/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.IllagerRenderer;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.IllagerModel;
import lightning.product.x_4904_Z;
import lightning.product.y_2798_W;

public class IllusionerRenderer
extends IllagerRenderer<y_2798_W> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/illusioner.png");

    public IllusionerRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new IllagerModel(0.0f, 0.0f, 64, 64), 0.5f);
        this.n_1700_B(new x_4904_Z<y_2798_W, IllagerModel<y_2798_W>>(this, (j_4203_m)this){

            @Override
            public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, y_2798_W entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (entitylivingbaseIn.ModuleCategory() || entitylivingbaseIn.P_2272_O()) {
                    super.n_1700_B(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                }
            }

            @Override
            public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
                this.n_1700_B(g_221_o2, o_3091_w2, n, (y_2798_W)n_4263_v, f, f2, f3, f4, f5, f6);
            }
        });
        ((IllagerModel)this.v_4262_N).J_1907_R().s_956_w = true;
    }

    @Override
    public g_2336_b n_1700_B(y_2798_W entity) {
        return n_1700_B;
    }

    @Override
    public void n_1700_B(y_2798_W entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (entityIn.F_3572_x()) {
            e_2866_D[] avector3d = entityIn.c_3005_b(partialTicks);
            float f = this.n_1700_B(entityIn, partialTicks);
            for (int i = 0; i < avector3d.length; ++i) {
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(avector3d[i].J_1907_R + (double)u_530_F.J_1907_R((float)i + f * 0.5f) * 0.025, avector3d[i].R_4764_Y + (double)u_530_F.J_1907_R((float)i + f * 0.75f) * 0.0125, avector3d[i].G_564_y + (double)u_530_F.J_1907_R((float)i + f * 0.7f) * 0.025);
                super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                matrixStackIn.J_1907_R();
            }
        } else {
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    protected boolean J_1907_R(y_2798_W livingEntityIn) {
        return true;
    }

    @Override
    protected /* synthetic */ boolean G_564_y(r_4811_B r_4811_B2) {
        return this.J_1907_R((y_2798_W)r_4811_B2);
    }
}



