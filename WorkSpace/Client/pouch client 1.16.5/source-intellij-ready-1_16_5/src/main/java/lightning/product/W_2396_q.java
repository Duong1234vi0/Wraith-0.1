/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_4792_h;
import lightning.product.SkullModel;
import lightning.product.K_4074_S;
import lightning.product.O_2639_P;
import lightning.product.HumanoidHeadModel;
import lightning.product.AbstractSkullBlock;
import lightning.product.SkullBlock;
import lightning.product.Z_3224_L;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.DragonHeadModel;
import lightning.product.s_2614_w;
import lightning.product.u_4834_E;

public class W_2396_q
extends l_1802_R<O_2639_P> {
    private static final Map<SkullBlock.n_1700_B, SkullModel> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_209262_0_) -> {
        SkullModel genericheadmodel = new SkullModel(0, 0, 64, 32);
        HumanoidHeadModel genericheadmodel1 = new HumanoidHeadModel();
        DragonHeadModel dragonheadmodel = new DragonHeadModel(0.0f);
        p_209262_0_.put(SkullBlock.J_1907_R.n_1700_B, genericheadmodel);
        p_209262_0_.put(SkullBlock.J_1907_R.J_1907_R, genericheadmodel);
        p_209262_0_.put(SkullBlock.J_1907_R.R_4764_Y, genericheadmodel1);
        p_209262_0_.put(SkullBlock.J_1907_R.G_564_y, genericheadmodel1);
        p_209262_0_.put(SkullBlock.J_1907_R.P_1922_E, genericheadmodel);
        p_209262_0_.put(SkullBlock.J_1907_R.u_1723_Y, dragonheadmodel);
    });
    private static final Map<SkullBlock.n_1700_B, g_2336_b> J_1907_R = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_209263_0_) -> {
        p_209263_0_.put(SkullBlock.J_1907_R.n_1700_B, new g_2336_b("textures/entity/skeleton/skeleton.png"));
        p_209263_0_.put(SkullBlock.J_1907_R.J_1907_R, new g_2336_b("textures/entity/skeleton/wither_skeleton.png"));
        p_209263_0_.put(SkullBlock.J_1907_R.G_564_y, new g_2336_b("textures/entity/zombie/zombie.png"));
        p_209263_0_.put(SkullBlock.J_1907_R.P_1922_E, new g_2336_b("textures/entity/creeper/creeper.png"));
        p_209263_0_.put(SkullBlock.J_1907_R.u_1723_Y, new g_2336_b("textures/entity/enderdragon/dragon.png"));
        p_209263_0_.put(SkullBlock.J_1907_R.R_4764_Y, s_2614_w.n_1700_B());
    });

    public W_2396_q(f_2689_h p_i226015_1_) {
        super(p_i226015_1_);
    }

    @Override
    public void n_1700_B(O_2639_P tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = tileEntityIn.n_1700_B(partialTicks);
        K_4074_S blockstate = tileEntityIn.e_4240_b();
        boolean flag = blockstate.J_1907_R() instanceof u_4834_E;
        b_257_Y direction = flag ? blockstate.R_4764_Y(u_4834_E.P_4830_p) : null;
        float f1 = 22.5f * (float)(flag ? (2 + direction.G_564_y()) * 4 : blockstate.R_4764_Y(SkullBlock.P_4830_p));
        W_2396_q.n_1700_B(direction, f1, ((AbstractSkullBlock)blockstate.J_1907_R()).J_1907_R(), tileEntityIn.v_4262_N(), f, matrixStackIn, bufferIn, combinedLightIn);
    }

    public static void n_1700_B(@Nullable b_257_Y directionIn, float p_228879_1_, SkullBlock.n_1700_B skullType, @Nullable GameProfile gameProfileIn, float animationProgress, g_221_o matrixStackIn, o_3091_w buffer, int combinedLight) {
        SkullModel genericheadmodel = n_1700_B.get(skullType);
        matrixStackIn.n_1700_B();
        if (directionIn == null) {
            matrixStackIn.n_1700_B(0.5, 0.0, 0.5);
        } else {
            float f = 0.25f;
            matrixStackIn.n_1700_B((double)(0.5f - (float)directionIn.t_148_a() * 0.25f), 0.25, (double)(0.5f - (float)directionIn.u_2550_I() * 0.25f));
        }
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        D_4792_h ivertexbuilder = buffer.getBuffer(W_2396_q.n_1700_B(skullType, gameProfileIn));
        genericheadmodel.n_1700_B(animationProgress, p_228879_1_, 0.0f);
        genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
    }

    private static o_2576_A n_1700_B(SkullBlock.n_1700_B skullType, @Nullable GameProfile gameProfileIn) {
        g_2336_b resourcelocation = J_1907_R.get(skullType);
        if (skullType == SkullBlock.J_1907_R.R_4764_Y && gameProfileIn != null) {
            MinecraftClient minecraft = MinecraftClient.A_4115_X();
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.c_4037_x().n_1700_B(gameProfileIn);
            return map.containsKey(MinecraftProfileTexture.Type.SKIN) ? o_2576_A.w_1484_f(minecraft.c_4037_x().n_1700_B(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN)) : o_2576_A.G_564_y(s_2614_w.n_1700_B(a_3913_L.n_1700_B(gameProfileIn)));
        }
        return o_2576_A.P_1922_E(resourcelocation);
    }
}



