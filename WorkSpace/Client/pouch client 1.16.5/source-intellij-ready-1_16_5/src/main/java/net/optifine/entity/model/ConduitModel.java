/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.ConduitRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class ConduitModel
extends v_3569_v {
    public e_4189_z eye;
    public e_4189_z wind;
    public e_4189_z base;
    public e_4189_z cage;

    public ConduitModel() {
        super(o_2576_A::R_4764_Y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        ConduitRenderer conduittileentityrenderer = new ConduitRenderer(tileentityrendererdispatcher);
        this.eye = (e_4189_z)Reflector.TileEntityConduitRenderer_modelRenderers.getValue(conduittileentityrenderer, 0);
        this.wind = (e_4189_z)Reflector.TileEntityConduitRenderer_modelRenderers.getValue(conduittileentityrenderer, 1);
        this.base = (e_4189_z)Reflector.TileEntityConduitRenderer_modelRenderers.getValue(conduittileentityrenderer, 2);
        this.cage = (e_4189_z)Reflector.TileEntityConduitRenderer_modelRenderers.getValue(conduittileentityrenderer, 3);
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityConduitRenderer_modelRenderers.exists()) {
            Config.warn("Field not found: TileEntityConduitRenderer.modelRenderers");
            return null;
        }
        Reflector.TileEntityConduitRenderer_modelRenderers.setValue(renderer, 0, this.eye);
        Reflector.TileEntityConduitRenderer_modelRenderers.setValue(renderer, 1, this.wind);
        Reflector.TileEntityConduitRenderer_modelRenderers.setValue(renderer, 2, this.base);
        Reflector.TileEntityConduitRenderer_modelRenderers.setValue(renderer, 3, this.cage);
        return renderer;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}


