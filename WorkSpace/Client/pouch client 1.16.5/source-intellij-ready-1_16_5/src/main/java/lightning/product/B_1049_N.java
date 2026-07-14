/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.tuple.Pair
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.B_3871_I;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.J_1907_R;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.X_4340_E;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.h_3270_j;
import lightning.product.l_3747_P;
import lightning.product.n_1700_B;
import lightning.product.o_2840_r;
import lightning.product.u_530_F;
import net.optifine.Config;
import net.optifine.SmartAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.apache.commons.lang3.tuple.Pair;

public class B_1049_N {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/misc/underwater.png");

    public static void n_1700_B(MinecraftClient minecraftIn, g_221_o matrixStackIn) {
        X_4340_E playerentity;
        c_4037_x.u_2550_I();
        n_1700_B bot1 = null;
        for (n_1700_B bot : J_1907_R.n_1700_B) {
            if (minecraftIn.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E x_4340_E = playerentity = bot1 != null ? bot1.P_1922_E.Q_2552_b : minecraftIn.Y_259_p;
        if (!playerentity.j_1564_a) {
            if (Reflector.ForgeEventFactory_renderBlockOverlay.exists() && Reflector.ForgeBlockModelShapes_getTexture3.exists()) {
                Object object;
                Pair<K_4074_S, c_1514_x> pair = B_1049_N.J_1907_R(playerentity);
                if (pair != null && !Reflector.ForgeEventFactory_renderBlockOverlay.callBoolean(playerentity, matrixStackIn, object = Reflector.getFieldValue(Reflector.RenderBlockOverlayEvent_OverlayType_BLOCK), pair.getLeft(), pair.getRight())) {
                    B_3871_I textureatlassprite = (B_3871_I)Reflector.call(minecraftIn.z_1333_t().J_1907_R(), Reflector.ForgeBlockModelShapes_getTexture3, pair.getLeft(), minecraftIn.Y_601_j, pair.getRight());
                    B_1049_N.n_1700_B(minecraftIn, textureatlassprite, matrixStackIn);
                }
            } else {
                K_4074_S blockstate = B_1049_N.n_1700_B(playerentity);
                if (blockstate != null) {
                    B_1049_N.n_1700_B(minecraftIn, minecraftIn.z_1333_t().J_1907_R().n_1700_B(blockstate), matrixStackIn);
                }
            }
        }
        if (bot1 != null) {
            if (bot1.P_1922_E.Q_2552_b.d_2461_k()) {
                c_4037_x.M_588_G();
                return;
            }
        } else if (minecraftIn.Y_259_p.d_2461_k()) {
            c_4037_x.M_588_G();
            return;
        }
        if (bot1 != null) {
            if (((N_4263_v)bot1.P_1922_E.Q_2552_b).n_1700_B(FluidTags.J_1907_R)) {
                B_1049_N.J_1907_R(minecraftIn, matrixStackIn);
            }
        } else if (((N_4263_v)minecraftIn.Y_259_p).n_1700_B(FluidTags.J_1907_R) && !Reflector.ForgeEventFactory_renderWaterOverlay.callBoolean(playerentity, matrixStackIn)) {
            B_1049_N.J_1907_R(minecraftIn, matrixStackIn);
        }
        if (bot1 != null ? ((N_4263_v)bot1.P_1922_E.Q_2552_b).n_1700_B(FluidTags.R_4764_Y) : ((N_4263_v)minecraftIn.Y_259_p).n_1700_B(FluidTags.R_4764_Y)) {
            // empty if block
        }
        if (bot1 != null) {
            if (bot1.P_1922_E.Q_2552_b.RealmsPersistence()) {
                B_1049_N.R_4764_Y(minecraftIn, matrixStackIn);
            }
        } else if (minecraftIn.Y_259_p.RealmsPersistence() && !Reflector.ForgeEventFactory_renderFireOverlay.callBoolean(playerentity, matrixStackIn)) {
            B_1049_N.R_4764_Y(minecraftIn, matrixStackIn);
        }
        c_4037_x.M_588_G();
    }

    @Nullable
    private static K_4074_S n_1700_B(a_3913_L playerIn) {
        Pair<K_4074_S, c_1514_x> pair = B_1049_N.J_1907_R(playerIn);
        return pair == null ? null : (K_4074_S)pair.getLeft();
    }

    private static Pair<K_4074_S, c_1514_x> J_1907_R(a_3913_L p_getOverlayBlock_0_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i < 8; ++i) {
            double d0 = p_getOverlayBlock_0_.O_3598_v() + (double)(((float)((i >> 0) % 2) - 0.5f) * p_getOverlayBlock_0_.C_415_h() * 0.8f);
            double d1 = p_getOverlayBlock_0_.X_2048_Y() + (double)(((float)((i >> 1) % 2) - 0.5f) * 0.1f);
            double d2 = p_getOverlayBlock_0_.l_2647_k() + (double)(((float)((i >> 2) % 2) - 0.5f) * p_getOverlayBlock_0_.C_415_h() * 0.8f);
            blockpos$mutable.n_1700_B(d0, d1, d2);
            K_4074_S blockstate = p_getOverlayBlock_0_.O_508_d.getBlockState(blockpos$mutable);
            if (blockstate.w_1484_f() == O_2369_F.n_1700_B || !blockstate.M_182_A(p_getOverlayBlock_0_.O_508_d, blockpos$mutable)) continue;
            return Pair.of((Object)blockstate, (Object)blockpos$mutable.toImmutable());
        }
        return null;
    }

    private static void n_1700_B(MinecraftClient minecraftIn, B_3871_I spriteIn, g_221_o matrixStackIn) {
        if (SmartAnimations.isActive()) {
            SmartAnimations.spriteRendered(spriteIn);
        }
        minecraftIn.G_624_v().n_1700_B(spriteIn.u_2550_I().R_4764_Y());
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        float f = 0.1f;
        float f1 = -1.0f;
        float f2 = 1.0f;
        float f3 = -1.0f;
        float f4 = 1.0f;
        float f5 = -0.5f;
        float f6 = spriteIn.u_1723_Y();
        float f7 = spriteIn.v_4262_N();
        float f8 = spriteIn.w_1484_f();
        float f9 = spriteIn.t_148_a();
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        bufferbuilder.n_1700_B(7, E_688_b.C_2741_M);
        bufferbuilder.n_1700_B(matrix4f, -1.0f, -1.0f, -0.5f).n_1700_B(0.1f, 0.1f, 0.1f, 1.0f).tex(f7, f9).endVertex();
        bufferbuilder.n_1700_B(matrix4f, 1.0f, -1.0f, -0.5f).n_1700_B(0.1f, 0.1f, 0.1f, 1.0f).tex(f6, f9).endVertex();
        bufferbuilder.n_1700_B(matrix4f, 1.0f, 1.0f, -0.5f).n_1700_B(0.1f, 0.1f, 0.1f, 1.0f).tex(f6, f8).endVertex();
        bufferbuilder.n_1700_B(matrix4f, -1.0f, 1.0f, -0.5f).n_1700_B(0.1f, 0.1f, 0.1f, 1.0f).tex(f7, f8).endVertex();
        bufferbuilder.u_1723_Y();
        o_2840_r.n_1700_B(bufferbuilder);
    }

    private static void J_1907_R(MinecraftClient minecraftIn, g_221_o matrixStackIn) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.w_1457_N);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        if (!Config.isShaders() || Shaders.isUnderwaterOverlay()) {
            c_4037_x.x_607_J();
            minecraftIn.G_624_v().n_1700_B(n_1700_B);
            if (SmartAnimations.isActive()) {
                SmartAnimations.textureRendered(minecraftIn.G_624_v().J_1907_R(n_1700_B).getGlTextureId());
            }
            n_1700_B bot1 = null;
            for (n_1700_B bot : J_1907_R.n_1700_B) {
                if (minecraftIn.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
            }
            D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
            float f = bot1 != null ? bot1.P_1922_E.Q_2552_b.RealmsConfirmScreen() : minecraftIn.Y_259_p.RealmsConfirmScreen();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            float f1 = 4.0f;
            float f2 = -1.0f;
            float f3 = 1.0f;
            float f4 = -1.0f;
            float f5 = 1.0f;
            float f6 = -0.5f;
            float f7 = bot1 != null ? -bot1.P_1922_E.Q_2552_b.p_178_J / 64.0f : -minecraftIn.Y_259_p.p_178_J / 64.0f;
            float f8 = bot1 != null ? bot1.P_1922_E.Q_2552_b.f_4016_n / 64.0f : minecraftIn.Y_259_p.f_4016_n / 64.0f;
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            bufferbuilder.n_1700_B(7, E_688_b.C_2741_M);
            bufferbuilder.n_1700_B(matrix4f, -1.0f, -1.0f, -0.5f).n_1700_B(f, f, f, 0.1f).tex(4.0f + f7, 4.0f + f8).endVertex();
            bufferbuilder.n_1700_B(matrix4f, 1.0f, -1.0f, -0.5f).n_1700_B(f, f, f, 0.1f).tex(0.0f + f7, 4.0f + f8).endVertex();
            bufferbuilder.n_1700_B(matrix4f, 1.0f, 1.0f, -0.5f).n_1700_B(f, f, f, 0.1f).tex(0.0f + f7, 0.0f + f8).endVertex();
            bufferbuilder.n_1700_B(matrix4f, -1.0f, 1.0f, -0.5f).n_1700_B(f, f, f, 0.1f).tex(4.0f + f7, 0.0f + f8).endVertex();
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            c_4037_x.Y_259_p();
        }
    }

    private static void R_4764_Y(MinecraftClient minecraftIn, g_221_o matrixStackIn) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.n_1700_B);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        c_4037_x.J_1907_R(519);
        c_4037_x.J_1907_R(false);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.x_607_J();
        B_3871_I textureatlassprite = g_2561_p.J_1907_R.R_4764_Y();
        if (SmartAnimations.isActive()) {
            SmartAnimations.spriteRendered(textureatlassprite);
        }
        minecraftIn.G_624_v().n_1700_B(textureatlassprite.u_2550_I().R_4764_Y());
        float f = textureatlassprite.u_1723_Y();
        float f1 = textureatlassprite.v_4262_N();
        float f2 = (f + f1) / 2.0f;
        float f3 = textureatlassprite.w_1484_f();
        float f4 = textureatlassprite.t_148_a();
        float f5 = (f3 + f4) / 2.0f;
        float f6 = textureatlassprite.h_1847_R();
        float f7 = u_530_F.v_4262_N(f6, f, f2);
        float f8 = u_530_F.v_4262_N(f6, f1, f2);
        float f9 = u_530_F.v_4262_N(f6, f3, f5);
        float f10 = u_530_F.v_4262_N(f6, f4, f5);
        float f11 = 1.0f;
        for (int i = 0; i < 2; ++i) {
            matrixStackIn.n_1700_B();
            float f12 = -0.5f;
            float f13 = 0.5f;
            float f14 = -0.5f;
            float f15 = 0.5f;
            float f16 = -0.5f;
            matrixStackIn.n_1700_B((double)((float)(-(i * 2 - 1)) * 0.24f), (double)-0.3f, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)(i * 2 - 1) * 10.0f));
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            bufferbuilder.n_1700_B(7, E_688_b.C_2741_M);
            bufferbuilder.n_1700_B(matrix4f, -0.5f, -0.5f, -0.5f).n_1700_B(1.0f, 1.0f, 1.0f, 0.9f).tex(f8, f10).endVertex();
            bufferbuilder.n_1700_B(matrix4f, 0.5f, -0.5f, -0.5f).n_1700_B(1.0f, 1.0f, 1.0f, 0.9f).tex(f7, f10).endVertex();
            bufferbuilder.n_1700_B(matrix4f, 0.5f, 0.5f, -0.5f).n_1700_B(1.0f, 1.0f, 1.0f, 0.9f).tex(f7, f9).endVertex();
            bufferbuilder.n_1700_B(matrix4f, -0.5f, 0.5f, -0.5f).n_1700_B(1.0f, 1.0f, 1.0f, 0.9f).tex(f8, f9).endVertex();
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            matrixStackIn.J_1907_R();
        }
        c_4037_x.Y_259_p();
        c_4037_x.J_1907_R(true);
        c_4037_x.J_1907_R(515);
    }
}



