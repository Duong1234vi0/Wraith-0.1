/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.IOException;
import lightning.product.DefaultedRegistry;
import lightning.product.RenderLayer;
import lightning.product.I_2154_Z;
import lightning.product.VillagerHeadModel;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.VillagerData;
import lightning.product.K_2872_v;
import lightning.product.N_4263_v;
import lightning.product.Resource;
import lightning.product.VillagerProfession;
import lightning.product.ReloadableResourceManager;
import lightning.product.R_3043_n;
import lightning.product.EntityModel;
import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class X_3615_B<T extends r_4811_B, M extends EntityModel<T>>
extends RenderLayer<T, M>
implements ResourceManagerReloadListener {
    private static final Int2ObjectMap<g_2336_b> n_1700_B = (Int2ObjectMap)j_3341_s.n_1700_B(new Int2ObjectOpenHashMap(), p_215348_0_ -> {
        p_215348_0_.put(1, (Object)new g_2336_b("stone"));
        p_215348_0_.put(2, (Object)new g_2336_b("iron"));
        p_215348_0_.put(3, (Object)new g_2336_b("gold"));
        p_215348_0_.put(4, (Object)new g_2336_b("emerald"));
        p_215348_0_.put(5, (Object)new g_2336_b("diamond"));
    });
    private final Object2ObjectMap<R_3043_n, K_2872_v.n_1700_B> J_1907_R = new Object2ObjectOpenHashMap();
    private final Object2ObjectMap<VillagerProfession, K_2872_v.n_1700_B> R_4764_Y = new Object2ObjectOpenHashMap();
    private final ReloadableResourceManager G_564_y;
    private final String P_1922_E;

    public X_3615_B(j_4203_m<T, M> p_i50955_1_, ReloadableResourceManager p_i50955_2_, String p_i50955_3_) {
        super(p_i50955_1_);
        this.G_564_y = p_i50955_2_;
        this.P_1922_E = p_i50955_3_;
        p_i50955_2_.n_1700_B(this);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!((N_4263_v)entitylivingbaseIn).F_3572_x()) {
            VillagerData villagerdata = ((I_2154_Z)entitylivingbaseIn).c_2086_l();
            R_3043_n villagertype = villagerdata.n_1700_B();
            VillagerProfession villagerprofession = villagerdata.J_1907_R();
            K_2872_v.n_1700_B villagermetadatasection$hattype = this.n_1700_B(this.J_1907_R, "type", V_3137_a.O_508_d, villagertype);
            K_2872_v.n_1700_B villagermetadatasection$hattype1 = this.n_1700_B(this.R_4764_Y, "profession", V_3137_a.r_715_M, villagerprofession);
            Object m = this.getEntityModel();
            ((VillagerHeadModel)m).n_1700_B(villagermetadatasection$hattype1 == K_2872_v.n_1700_B.n_1700_B || villagermetadatasection$hattype1 == K_2872_v.n_1700_B.J_1907_R && villagermetadatasection$hattype != K_2872_v.n_1700_B.R_4764_Y);
            g_2336_b resourcelocation = this.n_1700_B("type", V_3137_a.O_508_d.J_1907_R(villagertype));
            X_3615_B.renderCutoutModel(m, resourcelocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0f, 1.0f, 1.0f);
            ((VillagerHeadModel)m).n_1700_B(true);
            if (villagerprofession != VillagerProfession.n_1700_B && !((r_4811_B)entitylivingbaseIn).d_()) {
                g_2336_b resourcelocation1 = this.n_1700_B("profession", V_3137_a.r_715_M.J_1907_R(villagerprofession));
                X_3615_B.renderCutoutModel(m, resourcelocation1, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0f, 1.0f, 1.0f);
                if (villagerprofession != VillagerProfession.M_588_G) {
                    g_2336_b resourcelocation2 = this.n_1700_B("profession_level", (g_2336_b)n_1700_B.get(u_530_F.n_1700_B(villagerdata.R_4764_Y(), 1, n_1700_B.size())));
                    X_3615_B.renderCutoutModel(m, resourcelocation2, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0f, 1.0f, 1.0f);
                }
            }
        }
    }

    private g_2336_b n_1700_B(String p_215351_1_, g_2336_b p_215351_2_) {
        return new g_2336_b(p_215351_2_.R_4764_Y(), "textures/entity/" + this.P_1922_E + "/" + p_215351_1_ + "/" + p_215351_2_.J_1907_R() + ".png");
    }

    public <K> K_2872_v.n_1700_B n_1700_B(Object2ObjectMap<K, K_2872_v.n_1700_B> p_215350_1_, String p_215350_2_, DefaultedRegistry<K> p_215350_3_, K p_215350_4_) {
        return (K_2872_v.n_1700_B)((Object)p_215350_1_.computeIfAbsent(p_215350_4_, p_215349_4_ -> {
            try (Resource iresource = this.G_564_y.n_1700_B(this.n_1700_B(p_215350_2_, p_215350_3_.J_1907_R(p_215350_4_)));){
                K_2872_v villagermetadatasection = iresource.n_1700_B(K_2872_v.n_1700_B);
                if (villagermetadatasection == null) return K_2872_v.n_1700_B.n_1700_B;
                K_2872_v.n_1700_B n_1700_B2 = villagermetadatasection.n_1700_B();
                return n_1700_B2;
            }
            catch (IOException iOException) {
                // empty catch block
            }
            return K_2872_v.n_1700_B.n_1700_B;
        }));
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.R_4764_Y.clear();
        this.J_1907_R.clear();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


