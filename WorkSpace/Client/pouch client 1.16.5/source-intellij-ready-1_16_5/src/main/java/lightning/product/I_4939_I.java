/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4472_w;
import lightning.product.D_4792_h;
import lightning.product.E_4346_v;
import lightning.product.RenderLayer;
import lightning.product.H_3330_w;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.X_4340_E;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.Items;
import lightning.product.r_4811_B;
import net.optifine.Config;
import net.optifine.CustomItems;

public class I_4939_I<T extends r_4811_B, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/elytra.png");
    private final D_4472_w<T> J_1907_R = new D_4472_w();

    public I_4939_I(j_4203_m<T, M> rendererIn) {
        super(rendererIn);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack = ((r_4811_B)entitylivingbaseIn).J_1907_R(e_1174_E.P_1922_E);
        if (this.n_1700_B(itemstack, entitylivingbaseIn)) {
            g_2336_b resourcelocation;
            if (entitylivingbaseIn instanceof X_4340_E) {
                X_4340_E abstractclientplayerentity = (X_4340_E)entitylivingbaseIn;
                if (abstractclientplayerentity.X_933_l() && abstractclientplayerentity.Z_976_R() != null) {
                    resourcelocation = abstractclientplayerentity.Z_976_R();
                } else if (abstractclientplayerentity.T_3594_S() && abstractclientplayerentity.T_2506_i() && abstractclientplayerentity.e_2887_G() != null && abstractclientplayerentity.n_1700_B(E_4346_v.n_1700_B)) {
                    resourcelocation = abstractclientplayerentity.e_2887_G();
                } else {
                    resourcelocation = this.J_1907_R(itemstack, entitylivingbaseIn);
                    if (Config.isCustomItems()) {
                        resourcelocation = CustomItems.getCustomElytraTexture(itemstack, resourcelocation);
                    }
                }
            } else {
                resourcelocation = this.J_1907_R(itemstack, entitylivingbaseIn);
                if (Config.isCustomItems()) {
                    resourcelocation = CustomItems.getCustomElytraTexture(itemstack, resourcelocation);
                }
            }
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, 0.0, 0.125);
            ((EntityModel)this.getEntityModel()).n_1700_B(this.J_1907_R);
            this.J_1907_R.n_1700_B(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            D_4792_h ivertexbuilder = H_3330_w.n_1700_B(bufferIn, o_2576_A.n_1700_B(resourcelocation), false, itemstack.Y_259_p());
            this.J_1907_R.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.J_1907_R();
        }
    }

    public boolean n_1700_B(Z_1993_T p_shouldRender_1_, T p_shouldRender_2_) {
        return p_shouldRender_1_.J_1907_R() == Items.NyliumBlock;
    }

    public g_2336_b J_1907_R(Z_1993_T p_getElytraTexture_1_, T p_getElytraTexture_2_) {
        return n_1700_B;
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


