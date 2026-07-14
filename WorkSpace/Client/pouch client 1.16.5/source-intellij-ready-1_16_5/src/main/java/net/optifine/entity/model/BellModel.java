/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.BellRenderer;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class BellModel
extends v_3569_v {
    public e_4189_z bellBody;

    public BellModel() {
        super(o_2576_A::G_564_y);
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        BellRenderer belltileentityrenderer = new BellRenderer(tileentityrendererdispatcher);
        this.bellBody = (e_4189_z)Reflector.TileEntityBellRenderer_modelRenderer.getValue(belltileentityrenderer);
    }

    public l_1802_R updateRenderer(l_1802_R renderer) {
        if (!Reflector.TileEntityBellRenderer_modelRenderer.exists()) {
            Config.warn("Field not found: TileEntityBellRenderer.modelRenderer");
            return null;
        }
        Reflector.TileEntityBellRenderer_modelRenderer.setValue(renderer, this.bellBody);
        return renderer;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}


