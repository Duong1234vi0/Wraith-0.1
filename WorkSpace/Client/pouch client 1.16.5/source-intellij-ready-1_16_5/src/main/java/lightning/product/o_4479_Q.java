/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.D_4024_W;
import lightning.product.D_4792_h;
import lightning.product.E_4346_v;
import lightning.product.RenderLayer;
import lightning.product.I_1170_F;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.P_328_a;
import lightning.product.EntityModel;
import lightning.product.V_772_m;
import lightning.product.DistantAlpha;
import lightning.product.SeeInvisibles;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.Emotions;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.n_1700_B;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3050_h;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.Chams;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.z_2025_Z;
import net.optifine.Config;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class o_4479_Q<T extends r_4811_B, M extends EntityModel<T>>
extends Z_2049_e<T>
implements j_4203_m<T, M> {
    private static final Logger n_1700_B = LogManager.getLogger();
    public M v_4262_N;
    protected final List<RenderLayer<T, M>> w_1484_f = Lists.newArrayList();
    public r_4811_B t_148_a;
    public float s_956_w;
    public float u_2550_I;
    public float M_588_G;
    public float P_4830_p;
    public float h_1847_R;
    public float Q_4569_t;
    public static final boolean M_182_A = Boolean.getBoolean("animate.model.living");

    private n_1700_B u_1723_Y() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (minecraft.k_2293_S != null && minecraft.C_2741_M == minecraft.k_2293_S.P_1922_E.Q_2552_b) {
            return minecraft.k_2293_S;
        }
        if (minecraft.T_3594_S() != null) {
            return minecraft.k_2293_S;
        }
        return null;
    }

    public o_4479_Q(w_2040_b rendererManager, M entityModelIn, float shadowSizeIn) {
        super(rendererManager);
        this.v_4262_N = entityModelIn;
        this.R_4764_Y = shadowSizeIn;
    }

    public final boolean n_1700_B(RenderLayer<T, M> layer) {
        return this.w_1484_f.add(layer);
    }

    @Override
    public M n_1700_B() {
        return this.v_4262_N;
    }

    @Override
    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (!Reflector.RenderLivingEvent_Pre_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Pre_Constructor, entityIn, this, Float.valueOf(partialTicks), matrixStackIn, bufferIn, packedLightIn)) {
            boolean flag3;
            boolean flag1;
            b_257_Y direction;
            if (M_182_A) {
                ((r_4811_B)entityIn).G_424_k = 1.0f;
            }
            matrixStackIn.n_1700_B();
            ((EntityModel)this.v_4262_N).h_1847_R = this.G_564_y(entityIn, partialTicks);
            ((EntityModel)this.v_4262_N).Q_4569_t = ((N_4263_v)entityIn).y_2772_m();
            if (Reflector.IForgeEntity_shouldRiderSit.exists()) {
                ((EntityModel)this.v_4262_N).Q_4569_t = ((N_4263_v)entityIn).y_2772_m() && ((N_4263_v)entityIn).l_3609_d() != null && Reflector.callBoolean(((N_4263_v)entityIn).l_3609_d(), Reflector.IForgeEntity_shouldRiderSit, new Object[0]);
            }
            ((EntityModel)this.v_4262_N).M_182_A = ((r_4811_B)entityIn).d_();
            z_2025_Z playerRenderEvent = new z_2025_Z((r_4811_B)entityIn, partialTicks, ((r_4811_B)entityIn).C_1162_e, ((r_4811_B)entityIn).D_4361_a, ((r_4811_B)entityIn).f_3449_S, ((r_4811_B)entityIn).JsonUtils, ((r_4811_B)entityIn).f_4016_n, ((r_4811_B)entityIn).UploadStatus, ((r_4811_B)entityIn).u_55_V, ((r_4811_B)entityIn).RealmsPersistence);
            A_4115_X.n_1700_B(playerRenderEvent);
            float f = u_530_F.w_1484_f(partialTicks, playerRenderEvent.G_564_y(), playerRenderEvent.R_4764_Y());
            float f1 = u_530_F.w_1484_f(partialTicks, playerRenderEvent.u_1723_Y(), playerRenderEvent.P_1922_E());
            float f2 = f1 - f;
            if (((EntityModel)this.v_4262_N).Q_4569_t && ((N_4263_v)entityIn).l_3609_d() instanceof r_4811_B) {
                r_4811_B livingentity = (r_4811_B)((N_4263_v)entityIn).l_3609_d();
                f = u_530_F.w_1484_f(partialTicks, livingentity.D_4361_a, livingentity.C_1162_e);
                f2 = f1 - f;
                float f3 = u_530_F.v_4262_N(f2);
                if (f3 < -85.0f) {
                    f3 = -85.0f;
                }
                if (f3 >= 85.0f) {
                    f3 = 85.0f;
                }
                f = f1 - f3;
                if (f3 * f3 > 2500.0f) {
                    f += f3 * 0.2f;
                }
                f2 = f1 - f;
            }
            float f7 = entityIn == MinecraftClient.A_4115_X().g_2268_R() ? u_530_F.v_4262_N(partialTicks, playerRenderEvent.s_956_w(), playerRenderEvent.t_148_a()) : u_530_F.v_4262_N(partialTicks, playerRenderEvent.w_1484_f(), playerRenderEvent.v_4262_N());
            if (((N_4263_v)entityIn).h_4320_q() == I_1170_F.R_4764_Y && (direction = ((r_4811_B)entityIn).m_3147_m()) != null) {
                float f4 = ((N_4263_v)entityIn).P_1922_E(I_1170_F.n_1700_B) - 0.1f;
                matrixStackIn.n_1700_B((double)((float)(-direction.t_148_a()) * f4), 0.0, (double)((float)(-direction.u_2550_I()) * f4));
            }
            float f8 = this.n_1700_B(entityIn, partialTicks);
            this.n_1700_B(entityIn, matrixStackIn, f8, f, partialTicks);
            matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
            this.n_1700_B(entityIn, matrixStackIn, partialTicks);
            matrixStackIn.n_1700_B(0.0, (double)-1.501f, 0.0);
            float f9 = 0.0f;
            float f5 = 0.0f;
            if (!((N_4263_v)entityIn).y_2772_m() && ((r_4811_B)entityIn).RealmsLongRunningMcoTaskScreen()) {
                f9 = u_530_F.v_4262_N(partialTicks, ((r_4811_B)entityIn).A_3959_N, ((r_4811_B)entityIn).G_424_k);
                f5 = ((r_4811_B)entityIn).RealmsSettingsScreen - ((r_4811_B)entityIn).G_424_k * (1.0f - partialTicks);
                if (((r_4811_B)entityIn).d_()) {
                    f5 *= 3.0f;
                }
                if (f9 > 1.0f) {
                    f9 = 1.0f;
                }
            }
            ((EntityModel)this.v_4262_N).n_1700_B(entityIn, f5, f9, partialTicks);
            ((EntityModel)this.v_4262_N).n_1700_B(entityIn, f5, f9, f8, f2, f7);
            if (CustomEntityModels.isActive()) {
                this.t_148_a = entityIn;
                this.s_956_w = f5;
                this.u_2550_I = f9;
                this.M_588_G = f8;
                this.P_4830_p = f2;
                this.h_1847_R = f7;
                this.Q_4569_t = partialTicks;
            }
            boolean flag = Config.isShaders();
            MinecraftClient minecraft = MinecraftClient.A_4115_X();
            n_1700_B bot1 = this.u_1723_Y();
            boolean flag2 = !(flag1 = this.G_564_y(entityIn)) && !((N_4263_v)entityIn).a_(bot1 != null ? bot1.P_1922_E.Q_2552_b : minecraft.Y_259_p);
            o_2576_A rendertype = this.n_1700_B(entityIn, flag1, flag2, flag3 = minecraft.J_1907_R((N_4263_v)entityIn));
            if (rendertype != null) {
                D_4792_h ivertexbuilder = bufferIn.getBuffer(rendertype);
                float f6 = this.J_1907_R(entityIn, partialTicks);
                if (flag) {
                    boolean skipHurtFlash;
                    Emotions emotionsHurt = Emotions.h_1847_R();
                    boolean bl = skipHurtFlash = emotionsHurt != null && emotionsHurt.Y_259_p() != null;
                    if (!(skipHurtFlash || ((r_4811_B)entityIn).RealmsLongRunningMcoTaskScreen <= 0 && ((r_4811_B)entityIn).O_2151_c <= 0)) {
                        Shaders.setEntityColor(1.0f, 0.0f, 0.0f, 0.3f);
                    }
                    if (f6 > 0.0f) {
                        Shaders.setEntityColor(f6, f6, f6, 0.5f);
                    }
                }
                SeeInvisibles module = (SeeInvisibles)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(SeeInvisibles.class);
                Chams chams = Chams.h_1847_R();
                DistantAlpha distantAlpha = (DistantAlpha)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(DistantAlpha.class);
                int i = o_4479_Q.R_4764_Y(entityIn, f6);
                float baseAlpha = flag2 ? (module.w_1484_f() ? ((Float)module.h_1847_R().J_1907_R()).floatValue() : 0.15f) : 1.0f;
                float finalAlpha = distantAlpha != null && distantAlpha.w_1484_f() ? baseAlpha * distantAlpha.n_1700_B((r_4811_B)entityIn) : baseAlpha;
                ((v_3569_v)this.v_4262_N).render(matrixStackIn, ivertexbuilder, packedLightIn, i, 1.0f, 1.0f, 1.0f, finalAlpha);
            }
            if (!((N_4263_v)entityIn).d_2461_k() && this.P_1922_E) {
                for (RenderLayer<T, M> layerrenderer : this.w_1484_f) {
                    layerrenderer.render(matrixStackIn, bufferIn, packedLightIn, entityIn, f5, f9, partialTicks, f8, f2, f7);
                }
            }
            A_4115_X.n_1700_B(new P_328_a((r_4811_B)entityIn, matrixStackIn, (EntityModel<?>)this.n_1700_B()));
            if (Config.isShaders()) {
                Shaders.setEntityColor(0.0f, 0.0f, 0.0f, 0.0f);
            }
            if (CustomEntityModels.isActive()) {
                this.t_148_a = null;
            }
            matrixStackIn.J_1907_R();
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            if (Reflector.RenderLivingEvent_Post_Constructor.exists()) {
                Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Post_Constructor, entityIn, this, Float.valueOf(partialTicks), matrixStackIn, bufferIn, packedLightIn);
            }
        }
    }

    @Nullable
    protected o_2576_A n_1700_B(T p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        g_2336_b resourcelocation = this.n_1700_B(p_230496_1_);
        if (this.getLocationTextureCustom() != null) {
            resourcelocation = this.getLocationTextureCustom();
        }
        if (p_230496_3_) {
            return o_2576_A.u_1723_Y(resourcelocation);
        }
        if (p_230496_2_) {
            return ((v_3569_v)this.v_4262_N).getRenderType(resourcelocation);
        }
        if (((N_4263_v)p_230496_1_).j_306_t() && !Config.getMinecraft().u_1723_Y.G_564_y()) {
            return ((v_3569_v)this.v_4262_N).getRenderType(resourcelocation);
        }
        return p_230496_4_ ? o_2576_A.h_1847_R(resourcelocation) : null;
    }

    public static int R_4764_Y(r_4811_B livingEntityIn, float uIn) {
        Emotions emotionsOverlay = Emotions.h_1847_R();
        boolean isEmotionPreview = emotionsOverlay != null && emotionsOverlay.Y_259_p() != null;
        boolean showHurt = !isEmotionPreview && (livingEntityIn.RealmsLongRunningMcoTaskScreen > 0 || livingEntityIn.O_2151_c > 0);
        return Z_3224_L.n_1700_B(Z_3224_L.n_1700_B(uIn), Z_3224_L.n_1700_B(showHurt));
    }

    protected boolean G_564_y(T livingEntityIn) {
        return !((N_4263_v)livingEntityIn).F_3572_x();
    }

    private static float n_1700_B(b_257_Y facingIn) {
        switch (facingIn) {
            case G_564_y: {
                return 90.0f;
            }
            case P_1922_E: {
                return 0.0f;
            }
            case R_4764_Y: {
                return 270.0f;
            }
            case u_1723_Y: {
                return 180.0f;
            }
        }
        return 0.0f;
    }

    protected boolean n_1700_B(T p_230495_1_) {
        return false;
    }

    protected void n_1700_B(T entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        I_1170_F pose;
        boolean isEmotionPreview;
        Emotions emotionsCheck = Emotions.h_1847_R();
        boolean bl = isEmotionPreview = emotionsCheck != null && emotionsCheck.Y_259_p() != null;
        if (this.n_1700_B(entityLiving)) {
            rotationYaw += (float)(Math.cos((double)((r_4811_B)entityLiving).RealmsWorldResetDto * 3.25) * Math.PI * (double)0.4f);
        }
        if ((pose = ((N_4263_v)entityLiving).h_4320_q()) != I_1170_F.R_4764_Y || isEmotionPreview) {
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - rotationYaw));
        }
        if (!isEmotionPreview) {
            String s;
            if (((r_4811_B)entityLiving).O_2151_c > 0) {
                float f = ((float)((r_4811_B)entityLiving).O_2151_c + partialTicks - 1.0f) / 20.0f * 1.6f;
                if ((f = u_530_F.R_4764_Y(f)) > 1.0f) {
                    f = 1.0f;
                }
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f * this.R_4764_Y(entityLiving)));
            } else if (((r_4811_B)entityLiving).B_3040_x()) {
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-90.0f - ((r_4811_B)entityLiving).f_4016_n));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(((float)((r_4811_B)entityLiving).RealmsWorldResetDto + partialTicks) * -75.0f));
            } else if (pose == I_1170_F.R_4764_Y) {
                b_257_Y direction = ((r_4811_B)entityLiving).m_3147_m();
                float f1 = direction != null ? o_4479_Q.n_1700_B(direction) : rotationYaw;
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(this.R_4764_Y(entityLiving)));
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(270.0f));
            } else if ((((N_4263_v)entityLiving).t_3452_g() || entityLiving instanceof a_3913_L) && ("Dinnerbone".equals(s = D_4024_W.n_1700_B(((N_4263_v)entityLiving).O_1309_Q().getString())) || "Grumm".equals(s)) && (!(entityLiving instanceof a_3913_L) || ((a_3913_L)entityLiving).n_1700_B(E_4346_v.n_1700_B))) {
                matrixStackIn.n_1700_B(0.0, (double)(((N_4263_v)entityLiving).v_165_F() + 0.1f), 0.0);
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            }
        }
    }

    protected float G_564_y(T livingBase, float partialTickTime) {
        return ((r_4811_B)livingBase).Y_601_j(partialTickTime);
    }

    protected float n_1700_B(T livingBase, float partialTicks) {
        return (float)((r_4811_B)livingBase).RealmsWorldResetDto + partialTicks;
    }

    protected float R_4764_Y(T entityLivingBaseIn) {
        return 90.0f;
    }

    protected float J_1907_R(T livingEntityIn, float partialTicks) {
        return 0.0f;
    }

    protected void n_1700_B(T entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
    }

    @Override
    protected boolean J_1907_R(T entity) {
        float f;
        double d0 = this.J_1907_R.J_1907_R((N_4263_v)entity);
        float f2 = f = ((N_4263_v)entity).U_1341_G() ? 32.0f : 64.0f;
        if (d0 >= (double)(f * f)) {
            return false;
        }
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        V_772_m clientplayerentity = minecraft.Y_259_p;
        n_1700_B bot1 = this.u_1723_Y();
        boolean flag = !((N_4263_v)entity).a_(bot1 != null ? bot1.P_1922_E.Q_2552_b : clientplayerentity);
        if (entity != (bot1 != null ? bot1.P_1922_E.Q_2552_b : clientplayerentity)) {
            o_3050_h team = ((N_4263_v)entity).L_1362_X();
            o_3050_h team1 = (bot1 != null ? bot1.P_1922_E.Q_2552_b : clientplayerentity).L_1362_X();
            if (team != null) {
                o_3050_h.J_1907_R team$visible = team.t_148_a();
                switch (team$visible) {
                    case n_1700_B: {
                        return flag;
                    }
                    case J_1907_R: {
                        return false;
                    }
                    case R_4764_Y: {
                        return team1 == null ? flag : team.n_1700_B(team1) && (team.w_1484_f() || flag);
                    }
                    case G_564_y: {
                        return team1 == null ? flag : !team.n_1700_B(team1) && flag;
                    }
                }
                return true;
            }
        }
        return MinecraftClient.q_2307_F() && entity != minecraft.g_2268_R() && flag && !((N_4263_v)entity).H_1883_T();
    }

    public List<RenderLayer<T, M>> P_1922_E() {
        return this.w_1484_f;
    }
}



