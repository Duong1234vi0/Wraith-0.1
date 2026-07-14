/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.R_2515_i;
import lightning.product.ArmorDurability;
import lightning.product.DistantAlpha;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.AgeableListModel;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_4203_m;
import lightning.product.DyeableLeatherItem;
import lightning.product.n_1658_l;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import net.optifine.Config;
import net.optifine.CustomItems;
import net.optifine.reflect.Reflector;

public class B_4315_z<T extends r_4811_B, M extends n_1658_l<T>, A extends n_1658_l<T>>
extends RenderLayer<T, M> {
    private static final Map<String, g_2336_b> n_1700_B = Maps.newHashMap();
    private static final Map<g_2336_b, o_2576_A> J_1907_R = new ConcurrentHashMap<g_2336_b, o_2576_A>();
    private final A R_4764_Y;
    private final A G_564_y;

    public B_4315_z(j_4203_m<T, M> p_i50936_1_, A p_i50936_2_, A p_i50936_3_) {
        super(p_i50936_1_);
        this.R_4764_Y = p_i50936_2_;
        this.G_564_y = p_i50936_3_;
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B(matrixStackIn, bufferIn, entitylivingbaseIn, e_1174_E.P_1922_E, packedLightIn, this.n_1700_B(e_1174_E.P_1922_E));
        this.n_1700_B(matrixStackIn, bufferIn, entitylivingbaseIn, e_1174_E.G_564_y, packedLightIn, this.n_1700_B(e_1174_E.G_564_y));
        this.n_1700_B(matrixStackIn, bufferIn, entitylivingbaseIn, e_1174_E.R_4764_Y, packedLightIn, this.n_1700_B(e_1174_E.R_4764_Y));
        this.n_1700_B(matrixStackIn, bufferIn, entitylivingbaseIn, e_1174_E.u_1723_Y, packedLightIn, this.n_1700_B(e_1174_E.u_1723_Y));
    }

    private void n_1700_B(g_221_o p_241739_1_, o_3091_w p_241739_2_, T p_241739_3_, e_1174_E p_241739_4_, int p_241739_5_, A p_241739_6_) {
        R_2515_i armoritem;
        Z_1993_T itemstack = ((r_4811_B)p_241739_3_).J_1907_R(p_241739_4_);
        q_1613_l q_1613_l2 = itemstack.J_1907_R();
        if (q_1613_l2 instanceof R_2515_i && (armoritem = (R_2515_i)q_1613_l2).R_4764_Y() == p_241739_4_) {
            if (Reflector.ForgeHooksClient.exists()) {
                p_241739_6_ = this.n_1700_B(p_241739_3_, itemstack, p_241739_4_, p_241739_6_);
            }
            ((n_1658_l)this.getEntityModel()).n_1700_B(p_241739_6_);
            this.n_1700_B(p_241739_6_, p_241739_4_);
            this.J_1907_R(p_241739_4_);
            boolean flag = itemstack.Y_259_p();
            float durability = 1.0f;
            if (itemstack.u_1723_Y()) {
                int maxDamage = itemstack.w_1484_f();
                int damage = itemstack.v_4262_N();
                durability = (float)(maxDamage - damage) / (float)maxDamage;
            }
            float[] rgb = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ArmorDurability.class).w_1484_f() && ArmorDurability.n_1700_B(p_241739_3_) ? ArmorDurability.n_1700_B(durability) : new float[]{1.0f, 1.0f, 1.0f};
            float r = rgb[0];
            float g = rgb[1];
            float b = rgb[2];
            if (armoritem instanceof DyeableLeatherItem) {
                int i = ((DyeableLeatherItem)((Object)armoritem)).d_(itemstack);
                float f = (float)(i >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                this.n_1700_B(p_241739_1_, p_241739_2_, p_241739_5_, flag, p_241739_6_, f * r, f1 * g, f2 * b, this.n_1700_B((N_4263_v)p_241739_3_, itemstack, p_241739_4_, null), p_241739_3_);
                this.n_1700_B(p_241739_1_, p_241739_2_, p_241739_5_, flag, p_241739_6_, 1.0f, 1.0f, 1.0f, this.n_1700_B((N_4263_v)p_241739_3_, itemstack, p_241739_4_, "overlay"), p_241739_3_);
            } else {
                this.n_1700_B(p_241739_1_, p_241739_2_, p_241739_5_, flag, p_241739_6_, r, g, b, this.n_1700_B((N_4263_v)p_241739_3_, itemstack, p_241739_4_, null), p_241739_3_);
            }
        }
    }

    protected void n_1700_B(A modelIn, e_1174_E slotIn) {
        ((n_1658_l)modelIn).a_(false);
        switch (slotIn) {
            case u_1723_Y: {
                ((n_1658_l)modelIn).n_1700_B.s_956_w = true;
                ((n_1658_l)modelIn).J_1907_R.s_956_w = true;
                break;
            }
            case P_1922_E: {
                ((n_1658_l)modelIn).R_4764_Y.s_956_w = true;
                ((n_1658_l)modelIn).G_564_y.s_956_w = true;
                ((n_1658_l)modelIn).P_1922_E.s_956_w = true;
                break;
            }
            case G_564_y: {
                ((n_1658_l)modelIn).R_4764_Y.s_956_w = true;
                ((n_1658_l)modelIn).u_1723_Y.s_956_w = true;
                ((n_1658_l)modelIn).v_4262_N.s_956_w = true;
                break;
            }
            case R_4764_Y: {
                ((n_1658_l)modelIn).u_1723_Y.s_956_w = true;
                ((n_1658_l)modelIn).v_4262_N.s_956_w = true;
            }
        }
    }

    private void n_1700_B(g_221_o p_241738_1_, o_3091_w p_241738_2_, int p_241738_3_, R_2515_i p_241738_4_, boolean p_241738_5_, A p_241738_6_, boolean p_241738_7_, float p_241738_8_, float p_241738_9_, float p_241738_10_, @Nullable String p_241738_11_, T p_241738_12_) {
        this.n_1700_B(p_241738_1_, p_241738_2_, p_241738_3_, p_241738_5_, p_241738_6_, p_241738_8_, p_241738_9_, p_241738_10_, this.n_1700_B(p_241738_4_, p_241738_7_, p_241738_11_), p_241738_12_);
    }

    private void n_1700_B(g_221_o p_renderModel_1_, o_3091_w p_renderModel_2_, int p_renderModel_3_, boolean p_renderModel_4_, A p_renderModel_5_, float p_renderModel_6_, float p_renderModel_7_, float p_renderModel_8_, g_2336_b p_renderModel_9_, T p_renderModel_10_) {
        float alpha = 1.0f;
        DistantAlpha distantAlpha = (DistantAlpha)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(DistantAlpha.class);
        if (distantAlpha != null && distantAlpha.w_1484_f()) {
            alpha = distantAlpha.n_1700_B((r_4811_B)p_renderModel_10_);
        }
        o_2576_A renderType = alpha < 1.0f ? J_1907_R.computeIfAbsent(p_renderModel_9_, loc -> o_2576_A.w_1484_f(loc)) : o_2576_A.n_1700_B(p_renderModel_9_);
        D_4792_h ivertexbuilder = p_renderModel_2_.getBuffer(renderType);
        ((AgeableListModel)p_renderModel_5_).render(p_renderModel_1_, ivertexbuilder, p_renderModel_3_, Z_3224_L.n_1700_B, p_renderModel_6_, p_renderModel_7_, p_renderModel_8_, alpha);
        if (p_renderModel_4_) {
            D_4792_h glintBuilder = p_renderModel_2_.getBuffer(o_2576_A.Q_4569_t());
            ((AgeableListModel)p_renderModel_5_).render(p_renderModel_1_, glintBuilder, p_renderModel_3_, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    private A n_1700_B(e_1174_E p_241736_1_) {
        return this.J_1907_R(p_241736_1_) ? this.R_4764_Y : this.G_564_y;
    }

    private boolean J_1907_R(e_1174_E slotIn) {
        return slotIn == e_1174_E.G_564_y;
    }

    private g_2336_b n_1700_B(R_2515_i p_241737_1_, boolean p_241737_2_, @Nullable String p_241737_3_) {
        String s = "textures/models/armor/" + p_241737_1_.P_1922_E().G_564_y() + "_layer_" + (p_241737_2_ ? 2 : 1) + (String)(p_241737_3_ == null ? "" : "_" + p_241737_3_) + ".png";
        return n_1700_B.computeIfAbsent(s, g_2336_b::new);
    }

    protected A n_1700_B(T p_getArmorModelHook_1_, Z_1993_T p_getArmorModelHook_2_, e_1174_E p_getArmorModelHook_3_, A p_getArmorModelHook_4_) {
        return (A)((n_1658_l)(Reflector.ForgeHooksClient_getArmorModel.exists() ? Reflector.ForgeHooksClient_getArmorModel.call(new Object[]{p_getArmorModelHook_1_, p_getArmorModelHook_2_, p_getArmorModelHook_3_, p_getArmorModelHook_4_}) : p_getArmorModelHook_4_));
    }

    public g_2336_b n_1700_B(N_4263_v p_getArmorResource_1_, Z_1993_T p_getArmorResource_2_, e_1174_E p_getArmorResource_3_, String p_getArmorResource_4_) {
        g_2336_b resourcelocation;
        R_2515_i armoritem = (R_2515_i)p_getArmorResource_2_.J_1907_R();
        String s = armoritem.P_1922_E().G_564_y();
        String s1 = "minecraft";
        int i = s.indexOf(58);
        if (i != -1) {
            s1 = s.substring(0, i);
            s = s.substring(i + 1);
        }
        String s2 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", s1, s, this.J_1907_R(p_getArmorResource_3_) ? 2 : 1, p_getArmorResource_4_ == null ? "" : String.format("_%s", p_getArmorResource_4_));
        if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
            s2 = Reflector.callString(Reflector.ForgeHooksClient_getArmorTexture, new Object[]{p_getArmorResource_1_, p_getArmorResource_2_, s2, p_getArmorResource_3_, p_getArmorResource_4_});
        }
        if ((resourcelocation = n_1700_B.get(s2)) == null) {
            resourcelocation = new g_2336_b(s2);
            n_1700_B.put(s2, resourcelocation);
        }
        if (Config.isCustomItems()) {
            resourcelocation = CustomItems.getCustomArmorTexture(p_getArmorResource_2_, p_getArmorResource_3_, p_getArmorResource_4_, resourcelocation);
        }
        return resourcelocation;
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



