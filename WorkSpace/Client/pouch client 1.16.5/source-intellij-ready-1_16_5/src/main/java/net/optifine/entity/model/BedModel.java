/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.BedRenderer;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class BedModel
extends v_3569_v {
    public e_4189_z headPiece;
    public e_4189_z footPiece;
    public e_4189_z[] legs = new e_4189_z[4];

    public BedModel() {
        super(o_2576_A::G_564_y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        BedRenderer bedtileentityrenderer = new BedRenderer(tileentityrendererdispatcher);
        this.headPiece = (e_4189_z)Reflector.TileEntityBedRenderer_headModel.getValue(bedtileentityrenderer);
        this.footPiece = (e_4189_z)Reflector.TileEntityBedRenderer_footModel.getValue(bedtileentityrenderer);
        this.legs = (e_4189_z[])Reflector.TileEntityBedRenderer_legModels.getValue(bedtileentityrenderer);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityBedRenderer_headModel.exists()) {
            Config.warn("Field not found: TileEntityBedRenderer.head");
            return null;
        }
        if (!Reflector.TileEntityBedRenderer_footModel.exists()) {
            Config.warn("Field not found: TileEntityBedRenderer.footModel");
            return null;
        }
        if (!Reflector.TileEntityBedRenderer_legModels.exists()) {
            Config.warn("Field not found: TileEntityBedRenderer.legModels");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityBedRenderer_headModel, this.headPiece);
        Reflector.setFieldValue(renderer, Reflector.TileEntityBedRenderer_footModel, this.footPiece);
        Reflector.setFieldValue(renderer, Reflector.TileEntityBedRenderer_legModels, this.legs);
        return renderer;
    }
}


