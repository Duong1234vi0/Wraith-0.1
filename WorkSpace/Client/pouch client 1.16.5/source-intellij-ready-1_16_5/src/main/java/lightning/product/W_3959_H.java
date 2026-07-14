/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Pair
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Pair;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lightning.product.D_4792_h;
import lightning.product.ItemTransforms;
import lightning.product.H_3330_w;
import lightning.product.J_2538_C;
import lightning.product.J_2868_p;
import lightning.product.L_3273_c;
import lightning.product.O_2639_P;
import lightning.product.T_2910_P;
import lightning.product.T_2915_h;
import lightning.product.AbstractSkullBlock;
import lightning.product.U_2912_j;
import lightning.product.W_2396_q;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_433_S;
import lightning.product.e_1689_x;
import lightning.product.e_933_M;
import lightning.product.f_2689_h;
import lightning.product.f_395_A;
import lightning.product.g_221_o;
import lightning.product.g_2561_p;
import lightning.product.i_2154_H;
import lightning.product.BedBlockEntity;
import lightning.product.m_1551_m;
import lightning.product.ShieldModel;
import lightning.product.n_3832_I;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.TridentModel;
import lightning.product.Items;
import lightning.product.r_4889_F;
import lightning.product.s_3081_t;
import lightning.product.AbstractBannerBlock;
import lightning.product.t_693_s;
import lightning.product.v_1669_V;
import lightning.product.BannerRenderer;
import net.optifine.EmissiveTextures;
import org.apache.commons.lang3.StringUtils;

public class W_3959_H {
    private static final a_433_S[] R_4764_Y = (a_433_S[])Arrays.stream(e_933_M.values()).sorted(Comparator.comparingInt(e_933_M::J_1907_R)).map(a_433_S::new).toArray(a_433_S[]::new);
    private static final a_433_S G_564_y = new a_433_S((e_933_M)null);
    public static final W_3959_H n_1700_B = new W_3959_H();
    private final t_693_s P_1922_E = new t_693_s();
    private final t_693_s u_1723_Y = new f_395_A();
    private final s_3081_t v_4262_N = new s_3081_t();
    private final r_4889_F w_1484_f = new r_4889_F();
    private final BedBlockEntity t_148_a = new BedBlockEntity();
    private final m_1551_m s_956_w = new m_1551_m();
    private final ShieldModel u_2550_I = new ShieldModel();
    public TridentModel J_1907_R = new TridentModel();

    public void n_1700_B(Z_1993_T stack, ItemTransforms.J_1907_R p_239207_2_, g_221_o matrixStack, o_3091_w buffer, int combinedLight, int combinedOverlay) {
        if (EmissiveTextures.isActive()) {
            EmissiveTextures.beginRender();
        }
        this.n_1700_B(stack, matrixStack, buffer, combinedLight, combinedOverlay);
        if (EmissiveTextures.isActive()) {
            if (EmissiveTextures.hasEmissive()) {
                EmissiveTextures.beginRenderEmissive();
                this.n_1700_B(stack, matrixStack, buffer, e_1689_x.n_1700_B, combinedOverlay);
                EmissiveTextures.endRenderEmissive();
            }
            EmissiveTextures.endRender();
        }
    }

    public void n_1700_B(Z_1993_T p_renderRaw_1_, g_221_o p_renderRaw_2_, o_3091_w p_renderRaw_3_, int p_renderRaw_4_, int p_renderRaw_5_) {
        q_1613_l item = p_renderRaw_1_.J_1907_R();
        if (item instanceof v_1669_V) {
            T_2915_h block = ((v_1669_V)item).v_4262_N();
            if (block instanceof AbstractSkullBlock) {
                GameProfile gameprofile = null;
                if (p_renderRaw_1_.h_1847_R()) {
                    U_2912_j compoundnbt = p_renderRaw_1_.Q_4569_t();
                    if (compoundnbt.R_4764_Y("SkullOwner", 10)) {
                        gameprofile = n_3832_I.n_1700_B(compoundnbt.M_182_A("SkullOwner"));
                    } else if (compoundnbt.R_4764_Y("SkullOwner", 8) && !StringUtils.isBlank((CharSequence)compoundnbt.M_588_G("SkullOwner"))) {
                        GameProfile gameprofile1 = new GameProfile((UUID)null, compoundnbt.M_588_G("SkullOwner"));
                        gameprofile = O_2639_P.J_1907_R(gameprofile1);
                        compoundnbt.multiplayerClientSuggestionProvider("SkullOwner");
                        compoundnbt.n_1700_B("SkullOwner", n_3832_I.n_1700_B(new U_2912_j(), gameprofile));
                    }
                }
                W_2396_q.n_1700_B(null, 180.0f, ((AbstractSkullBlock)block).J_1907_R(), gameprofile, 0.0f, p_renderRaw_2_, p_renderRaw_3_, p_renderRaw_4_);
            } else {
                i_2154_H tileentity;
                if (block instanceof AbstractBannerBlock) {
                    this.w_1484_f.n_1700_B(p_renderRaw_1_, ((AbstractBannerBlock)block).J_1907_R());
                    tileentity = this.w_1484_f;
                } else if (block instanceof J_2868_p) {
                    this.t_148_a.n_1700_B(((J_2868_p)block).J_1907_R());
                    tileentity = this.t_148_a;
                } else if (block == a_3742_W.V_3441_j) {
                    tileentity = this.s_956_w;
                } else if (block == a_3742_W.L_1362_X) {
                    tileentity = this.P_1922_E;
                } else if (block == a_3742_W.k_2348_i) {
                    tileentity = this.v_4262_N;
                } else if (block == a_3742_W.NumberSetting) {
                    tileentity = this.u_1723_Y;
                } else {
                    if (!(block instanceof Y_3462_U)) {
                        return;
                    }
                    e_933_M dyecolor = Y_3462_U.J_1907_R(item);
                    tileentity = dyecolor == null ? G_564_y : R_4764_Y[dyecolor.J_1907_R()];
                }
                f_2689_h.J_1907_R.n_1700_B(tileentity, p_renderRaw_2_, p_renderRaw_3_, p_renderRaw_4_, p_renderRaw_5_);
            }
        } else if (item == Items.NoteBlock) {
            boolean flag = p_renderRaw_1_.J_1907_R("BlockEntityTag") != null;
            p_renderRaw_2_.n_1700_B();
            p_renderRaw_2_.n_1700_B(1.0f, -1.0f, -1.0f);
            T_2910_P rendermaterial = flag ? g_2561_p.v_4262_N : g_2561_p.w_1484_f;
            D_4792_h ivertexbuilder1 = rendermaterial.R_4764_Y().n_1700_B(H_3330_w.R_4764_Y(p_renderRaw_3_, this.u_2550_I.getRenderType(rendermaterial.n_1700_B()), true, p_renderRaw_1_.Y_259_p()));
            this.u_2550_I.J_1907_R().n_1700_B(p_renderRaw_2_, ivertexbuilder1, p_renderRaw_4_, p_renderRaw_5_, 1.0f, 1.0f, 1.0f, 1.0f);
            if (flag) {
                List<Pair<J_2538_C, e_933_M>> list = r_4889_F.n_1700_B(L_3273_c.G_564_y(p_renderRaw_1_), r_4889_F.n_1700_B(p_renderRaw_1_));
                BannerRenderer.n_1700_B(p_renderRaw_2_, p_renderRaw_3_, p_renderRaw_4_, p_renderRaw_5_, this.u_2550_I.n_1700_B(), rendermaterial, false, list, p_renderRaw_1_.Y_259_p());
            } else {
                this.u_2550_I.n_1700_B().n_1700_B(p_renderRaw_2_, ivertexbuilder1, p_renderRaw_4_, p_renderRaw_5_, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            p_renderRaw_2_.J_1907_R();
        } else if (item == Items.P_2605_j) {
            p_renderRaw_2_.n_1700_B();
            p_renderRaw_2_.n_1700_B(1.0f, -1.0f, -1.0f);
            D_4792_h ivertexbuilder = H_3330_w.R_4764_Y(p_renderRaw_3_, this.J_1907_R.getRenderType(TridentModel.n_1700_B), false, p_renderRaw_1_.Y_259_p());
            this.J_1907_R.render(p_renderRaw_2_, ivertexbuilder, p_renderRaw_4_, p_renderRaw_5_, 1.0f, 1.0f, 1.0f, 1.0f);
            p_renderRaw_2_.J_1907_R();
        }
    }
}


