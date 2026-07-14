/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.L_2225_p;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.O_2639_P;
import lightning.product.R_2515_i;
import lightning.product.EntityModel;
import lightning.product.AbstractSkullBlock;
import lightning.product.U_2912_j;
import lightning.product.W_2396_q;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.l_4140_i;
import lightning.product.n_3832_I;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.v_1669_V;
import lightning.product.w_720_O;
import org.apache.commons.lang3.StringUtils;

public class g_2016_P<T extends r_4811_B, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private final float n_1700_B;
    private final float J_1907_R;
    private final float R_4764_Y;

    public g_2016_P(j_4203_m<T, M> p_i50946_1_) {
        this(p_i50946_1_, 1.0f, 1.0f, 1.0f);
    }

    public g_2016_P(j_4203_m<T, M> p_i232475_1_, float p_i232475_2_, float p_i232475_3_, float p_i232475_4_) {
        super(p_i232475_1_);
        this.n_1700_B = p_i232475_2_;
        this.J_1907_R = p_i232475_3_;
        this.R_4764_Y = p_i232475_4_;
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack = ((r_4811_B)entitylivingbaseIn).J_1907_R(e_1174_E.u_1723_Y);
        if (!itemstack.n_1700_B()) {
            boolean flag;
            q_1613_l item = itemstack.J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
            boolean bl = flag = entitylivingbaseIn instanceof L_2225_p || entitylivingbaseIn instanceof l_4140_i;
            if (((r_4811_B)entitylivingbaseIn).d_() && !(entitylivingbaseIn instanceof L_2225_p)) {
                float f = 2.0f;
                float f1 = 1.4f;
                matrixStackIn.n_1700_B(0.0, 0.03125, 0.0);
                matrixStackIn.n_1700_B(0.7f, 0.7f, 0.7f);
                matrixStackIn.n_1700_B(0.0, 1.0, 0.0);
            }
            ((w_720_O)this.getEntityModel()).R_4764_Y().n_1700_B(matrixStackIn);
            if (item instanceof v_1669_V && ((v_1669_V)item).v_4262_N() instanceof AbstractSkullBlock) {
                float f3 = 1.1875f;
                matrixStackIn.n_1700_B(1.1875f, -1.1875f, -1.1875f);
                if (flag) {
                    matrixStackIn.n_1700_B(0.0, 0.0625, 0.0);
                }
                GameProfile gameprofile = null;
                if (itemstack.h_1847_R()) {
                    String s;
                    U_2912_j compoundnbt = itemstack.Q_4569_t();
                    if (compoundnbt.R_4764_Y("SkullOwner", 10)) {
                        gameprofile = n_3832_I.n_1700_B(compoundnbt.M_182_A("SkullOwner"));
                    } else if (compoundnbt.R_4764_Y("SkullOwner", 8) && !StringUtils.isBlank((CharSequence)(s = compoundnbt.M_588_G("SkullOwner")))) {
                        gameprofile = O_2639_P.J_1907_R(new GameProfile((UUID)null, s));
                        compoundnbt.n_1700_B("SkullOwner", n_3832_I.n_1700_B(new U_2912_j(), gameprofile));
                    }
                }
                matrixStackIn.n_1700_B(-0.5, 0.0, -0.5);
                W_2396_q.n_1700_B(null, 180.0f, ((AbstractSkullBlock)((v_1669_V)item).v_4262_N()).J_1907_R(), gameprofile, limbSwing, matrixStackIn, bufferIn, packedLightIn);
            } else if (!(item instanceof R_2515_i) || ((R_2515_i)item).R_4764_Y() != e_1174_E.u_1723_Y) {
                float f2 = 0.625f;
                matrixStackIn.n_1700_B(0.0, -0.25, 0.0);
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
                matrixStackIn.n_1700_B(0.625f, -0.625f, -0.625f);
                if (flag) {
                    matrixStackIn.n_1700_B(0.0, 0.1875, 0.0);
                }
                MinecraftClient.A_4115_X().A_1038_p().n_1700_B((r_4811_B)entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.u_1723_Y, false, matrixStackIn, bufferIn, packedLightIn);
            }
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



