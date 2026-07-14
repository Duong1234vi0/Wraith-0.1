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

public class ChestLargeModel
extends v_3569_v {
    public e_4189_z lid_left;
    public e_4189_z base_left;
    public e_4189_z knob_left;
    public e_4189_z lid_right;
    public e_4189_z base_right;
    public e_4189_z knob_right;

    public ChestLargeModel() {
        super(o_2576_A::R_4764_Y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        ChestRenderer chesttileentityrenderer = new ChestRenderer(tileentityrendererdispatcher);
        this.lid_left = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 3);
        this.base_left = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 4);
        this.knob_left = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 5);
        this.lid_right = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 6);
        this.base_right = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 7);
        this.knob_right = (e_4189_z)Reflector.TileEntityChestRenderer_modelRenderers.getValue(chesttileentityrenderer, 8);
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityChestRenderer_modelRenderers.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.modelRenderers");
            return null;
        }
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 3, this.lid_left);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 4, this.base_left);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 5, this.knob_left);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 6, this.lid_right);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 7, this.base_right);
        Reflector.TileEntityChestRenderer_modelRenderers.setValue(renderer, 8, this.knob_right);
        return renderer;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}


