/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import lightning.product.BannerRenderer;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class BannerModel
extends v_3569_v {
    public e_4189_z bannerSlate;
    public e_4189_z bannerStand;
    public e_4189_z bannerTop;

    public BannerModel() {
        super(o_2576_A::G_564_y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        BannerRenderer bannertileentityrenderer = new BannerRenderer(tileentityrendererdispatcher);
        this.bannerSlate = (e_4189_z)Reflector.TileEntityBannerRenderer_modelRenderers.getValue(bannertileentityrenderer, 0);
        this.bannerStand = (e_4189_z)Reflector.TileEntityBannerRenderer_modelRenderers.getValue(bannertileentityrenderer, 1);
        this.bannerTop = (e_4189_z)Reflector.TileEntityBannerRenderer_modelRenderers.getValue(bannertileentityrenderer, 2);
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityBannerRenderer_modelRenderers.exists()) {
            Config.warn("Field not found: TileEntityBannerRenderer.modelRenderers");
            return null;
        }
        Reflector.TileEntityBannerRenderer_modelRenderers.setValue(renderer, 0, this.bannerSlate);
        Reflector.TileEntityBannerRenderer_modelRenderers.setValue(renderer, 1, this.bannerStand);
        Reflector.TileEntityBannerRenderer_modelRenderers.setValue(renderer, 2, this.bannerTop);
        return renderer;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}


