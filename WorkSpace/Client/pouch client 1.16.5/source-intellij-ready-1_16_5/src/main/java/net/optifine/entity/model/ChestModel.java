/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.ChestRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class ChestModel
extends v_3569_v {
    public e_4189_z lid;
    public e_4189_z base;
    public e_4189_z knob;

    public ChestModel() {
        super(o_2576_A::R_4764_Y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        ChestRenderer chesttileentityrenderer = new ChestRenderer(tileentityrendererdispatcher);
        this.lid = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 0);
        this.base = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 1);
        this.knob = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 2);
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityChestRenderer_modelRenderers.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.modelRenderers");
            return null;
        }
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 0, this.lid);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 1, this.base);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 2, this.knob);
        return renderer;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}


