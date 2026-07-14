/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.B_4315_z;
import lightning.product.E_4346_v;
import lightning.product.F_1573_j;
import lightning.product.I_4939_I;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.P_328_a;
import lightning.product.EntityModel;
import lightning.product.U_2871_b;
import lightning.product.Objective;
import lightning.product.X_4340_E;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.BeeStingerLayer;
import lightning.product.Emotions;
import lightning.product.c_2873_e;
import lightning.product.e_2866_D;
import lightning.product.e_4189_z;
import lightning.product.g_2016_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_491_E;
import lightning.product.i_4895_l;
import lightning.product.j_240_A;
import lightning.product.k_4231_L;
import lightning.product.n_1658_l;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.Chams;
import lightning.product.v_4839_y;
import lightning.product.w_2040_b;
import lightning.product.Deadmau5EarsLayer;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.x_4904_Z;
import mods.cape.CustomCapeRenderLayer;

public class h_4311_S
extends o_4479_Q<X_4340_E, PlayerModel<X_4340_E>> {
    public h_4311_S(w_2040_b renderManager) {
        this(renderManager, false);
    }

    public h_4311_S(w_2040_b renderManager, boolean useSmallArms) {
        super(renderManager, new PlayerModel(0.0f, useSmallArms), 0.5f);
        this.n_1700_B(new B_4315_z(this, new n_1658_l(0.5f), new n_1658_l(1.0f)));
        this.n_1700_B(new x_4904_Z<X_4340_E, PlayerModel<X_4340_E>>(this));
        this.n_1700_B(new h_491_E<X_4340_E, PlayerModel<X_4340_E>>(this));
        this.n_1700_B(new Deadmau5EarsLayer(this));
        this.n_1700_B(new CustomCapeRenderLayer(this));
        this.n_1700_B(new g_2016_P<X_4340_E, PlayerModel<X_4340_E>>(this));
        this.n_1700_B(new I_4939_I<X_4340_E, PlayerModel<X_4340_E>>(this));
        this.n_1700_B(new c_2873_e<X_4340_E>(this));
        this.n_1700_B(new j_240_A<X_4340_E>(this));
        this.n_1700_B(new BeeStingerLayer<X_4340_E, PlayerModel<X_4340_E>>(this));
    }

    @Override
    public void n_1700_B(X_4340_E entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        Emotions emotions = Emotions.h_1847_R();
        boolean isPreview = emotions != null && emotions.Y_259_p() != null;
        Chams chams = Chams.h_1847_R();
        if (!isPreview && chams != null && chams.n_1700_B((N_4263_v)entityIn)) {
            this.J_1907_R(entityIn);
            this.J_1907_R(entityIn, entityYaw, partialTicks, matrixStackIn);
            return;
        }
        this.J_1907_R(entityIn);
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private void J_1907_R(X_4340_E entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn) {
        matrixStackIn.n_1700_B();
        this.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn);
        A_4115_X.n_1700_B(new P_328_a(entityIn, matrixStackIn, (EntityModel<?>)this.n_1700_B()));
        matrixStackIn.J_1907_R();
    }

    public void n_1700_B(X_4340_E entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn) {
        ((PlayerModel)this.v_4262_N).h_1847_R = this.G_564_y(entityIn, partialTicks);
        ((PlayerModel)this.v_4262_N).Q_4569_t = entityIn.y_2772_m();
        ((PlayerModel)this.v_4262_N).M_182_A = entityIn.d_();
        float f = u_530_F.w_1484_f(partialTicks, entityIn.D_4361_a, entityIn.C_1162_e);
        float f1 = u_530_F.w_1484_f(partialTicks, entityIn.JsonUtils, entityIn.f_3449_S);
        float f2 = f1 - f;
        float f7 = u_530_F.v_4262_N(partialTicks, entityIn.UploadStatus, entityIn.f_4016_n);
        float f8 = this.n_1700_B(entityIn, partialTicks);
        this.n_1700_B(entityIn, matrixStackIn, f8, f, partialTicks);
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        this.n_1700_B(entityIn, matrixStackIn, partialTicks);
        matrixStackIn.n_1700_B(0.0, -1.501, 0.0);
        float f9 = 0.0f;
        float f5 = 0.0f;
        if (!entityIn.y_2772_m() && entityIn.RealmsLongRunningMcoTaskScreen()) {
            f9 = u_530_F.v_4262_N(partialTicks, entityIn.A_3959_N, entityIn.G_424_k);
            f5 = entityIn.RealmsSettingsScreen - entityIn.G_424_k * (1.0f - partialTicks);
            if (entityIn.d_()) {
                f5 *= 3.0f;
            }
            if (f9 > 1.0f) {
                f9 = 1.0f;
            }
        }
        ((PlayerModel)this.v_4262_N).n_1700_B(entityIn, f5, f9, partialTicks);
        ((PlayerModel)this.v_4262_N).n_1700_B(entityIn, f5, f9, f8, f2, f7);
    }

    @Override
    public e_2866_D n_1700_B(X_4340_E entityIn, float partialTicks) {
        Emotions emotionsOffset = Emotions.h_1847_R();
        if (emotionsOffset != null && emotionsOffset.Y_259_p() != null) {
            return super.n_1700_B(entityIn, partialTicks);
        }
        return entityIn.Z_875_P() ? new e_2866_D(0.0, -0.125, 0.0) : super.n_1700_B(entityIn, partialTicks);
    }

    private void J_1907_R(X_4340_E clientPlayer) {
        PlayerModel playermodel = (PlayerModel)this.n_1700_B();
        if (clientPlayer.d_2461_k()) {
            playermodel.a_(false);
            playermodel.n_1700_B.s_956_w = true;
            playermodel.J_1907_R.s_956_w = true;
        } else {
            playermodel.a_(true);
            playermodel.J_1907_R.s_956_w = clientPlayer.n_1700_B(E_4346_v.v_4262_N);
            playermodel.Y_259_p.s_956_w = clientPlayer.n_1700_B(E_4346_v.J_1907_R);
            playermodel.w_1457_N.s_956_w = clientPlayer.n_1700_B(E_4346_v.P_1922_E);
            playermodel.Y_601_j.s_956_w = clientPlayer.n_1700_B(E_4346_v.u_1723_Y);
            playermodel.t_1786_h.s_956_w = clientPlayer.n_1700_B(E_4346_v.R_4764_Y);
            playermodel.multiplayerClientSuggestionProvider.s_956_w = clientPlayer.n_1700_B(E_4346_v.G_564_y);
            playermodel.s_956_w = clientPlayer.Z_875_P();
            n_1658_l.n_1700_B bipedmodel$armpose = h_4311_S.n_1700_B(clientPlayer, x_1688_C.n_1700_B);
            n_1658_l.n_1700_B bipedmodel$armpose1 = h_4311_S.n_1700_B(clientPlayer, x_1688_C.J_1907_R);
            if (bipedmodel$armpose.n_1700_B()) {
                n_1658_l.n_1700_B n_1700_B2 = bipedmodel$armpose1 = clientPlayer.S_4035_N().n_1700_B() ? n_1658_l.n_1700_B.n_1700_B : n_1658_l.n_1700_B.J_1907_R;
            }
            if (clientPlayer.d_2169_p() == k_4231_L.J_1907_R) {
                playermodel.t_148_a = bipedmodel$armpose;
                playermodel.w_1484_f = bipedmodel$armpose1;
            } else {
                playermodel.t_148_a = bipedmodel$armpose1;
                playermodel.w_1484_f = bipedmodel$armpose;
            }
        }
    }

    private static n_1658_l.n_1700_B n_1700_B(X_4340_E p_241741_0_, x_1688_C p_241741_1_) {
        Z_1993_T itemstack = p_241741_0_.R_4764_Y(p_241741_1_);
        if (itemstack.n_1700_B()) {
            return n_1658_l.n_1700_B.n_1700_B;
        }
        if (p_241741_0_.Q_2552_b() == p_241741_1_ && p_241741_0_.U_144_f() > 0) {
            F_1573_j useaction = itemstack.M_588_G();
            if (useaction == F_1573_j.G_564_y) {
                return n_1658_l.n_1700_B.R_4764_Y;
            }
            if (useaction == F_1573_j.P_1922_E) {
                return n_1658_l.n_1700_B.G_564_y;
            }
            if (useaction == F_1573_j.u_1723_Y) {
                return n_1658_l.n_1700_B.P_1922_E;
            }
            if (useaction == F_1573_j.v_4262_N && p_241741_1_ == p_241741_0_.Q_2552_b()) {
                return n_1658_l.n_1700_B.u_1723_Y;
            }
        } else if (!p_241741_0_.RealmsCreateRealmScreen && itemstack.J_1907_R() == Items.V_2454_J && Z_1630_j.G_564_y(itemstack)) {
            return n_1658_l.n_1700_B.v_4262_N;
        }
        return n_1658_l.n_1700_B.J_1907_R;
    }

    @Override
    public g_2336_b n_1700_B(X_4340_E entity) {
        return entity.g_221_o();
    }

    @Override
    protected void n_1700_B(X_4340_E entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        float f = 0.9375f;
        matrixStackIn.n_1700_B(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    protected void n_1700_B(X_4340_E entityIn, x_282_a displayNameIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        i_4895_l scoreboard;
        Objective scoreobjective;
        double d0 = this.J_1907_R.J_1907_R(entityIn);
        matrixStackIn.n_1700_B();
        if (d0 < 100.0 && (scoreobjective = (scoreboard = entityIn.U_3758_B()).n_1700_B(2)) != null) {
            v_4839_y score = scoreboard.J_1907_R(entityIn.L_3570_A(), scoreobjective);
            super.n_1700_B(entityIn, new U_2871_b(Integer.toString(score.J_1907_R())).n_1700_B(" ").n_1700_B(scoreobjective.G_564_y()), matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.n_1700_B(0.0, (double)0.25875f, 0.0);
        }
        super.n_1700_B(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.J_1907_R();
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, X_4340_E playerIn) {
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, playerIn, ((PlayerModel)this.v_4262_N).G_564_y, ((PlayerModel)this.v_4262_N).multiplayerClientSuggestionProvider);
    }

    public void J_1907_R(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, X_4340_E playerIn) {
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, playerIn, ((PlayerModel)this.v_4262_N).P_1922_E, ((PlayerModel)this.v_4262_N).t_1786_h);
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, X_4340_E playerIn, e_4189_z rendererArmIn, e_4189_z rendererArmwearIn) {
        PlayerModel playermodel = (PlayerModel)this.n_1700_B();
        this.J_1907_R(playerIn);
        playermodel.h_1847_R = 0.0f;
        playermodel.s_956_w = false;
        playermodel.u_2550_I = 0.0f;
        playermodel.n_1700_B(playerIn, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        rendererArmIn.u_1723_Y = 0.0f;
        rendererArmIn.n_1700_B(matrixStackIn, bufferIn.getBuffer(o_2576_A.J_1907_R(playerIn.g_221_o())), combinedLightIn, Z_3224_L.n_1700_B);
        rendererArmwearIn.u_1723_Y = 0.0f;
        rendererArmwearIn.n_1700_B(matrixStackIn, bufferIn.getBuffer(o_2576_A.w_1484_f(playerIn.g_221_o())), combinedLightIn, Z_3224_L.n_1700_B);
    }

    @Override
    protected void n_1700_B(X_4340_E entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        Emotions emotionsCheck = Emotions.h_1847_R();
        if (emotionsCheck != null && emotionsCheck.Y_259_p() != null) {
            super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
            return;
        }
        float f = entityLiving.u_1723_Y(partialTicks);
        if (entityLiving.k_578_l()) {
            super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
            float f1 = (float)entityLiving.h_3859_C() + partialTicks;
            float f2 = u_530_F.n_1700_B(f1 * f1 / 100.0f, 0.0f, 1.0f);
            if (!entityLiving.B_3040_x()) {
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f2 * (-90.0f - entityLiving.f_4016_n)));
            }
            e_2866_D vector3d = entityLiving.t_148_a(partialTicks);
            e_2866_D vector3d1 = entityLiving.I_4348_c();
            double d0 = N_4263_v.R_4764_Y(vector3d1);
            double d1 = N_4263_v.R_4764_Y(vector3d);
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vector3d1.J_1907_R * vector3d.J_1907_R + vector3d1.G_564_y * vector3d.G_564_y) / Math.sqrt(d0 * d1);
                double d3 = vector3d1.J_1907_R * vector3d.G_564_y - vector3d1.G_564_y * vector3d.J_1907_R;
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.J_1907_R((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0f) {
            super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
            float f3 = entityLiving.RowButton() ? -90.0f - entityLiving.f_4016_n : -90.0f;
            float f4 = u_530_F.v_4262_N(f, 0.0f, f3);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f4));
            if (entityLiving.x_612_B()) {
                matrixStackIn.n_1700_B(0.0, -1.0, (double)0.3f);
            }
        } else {
            super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        }
    }
}



