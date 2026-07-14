/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.D_4792_h;
import lightning.product.EndCrystalRenderer;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.reflect.Reflector;

public class EnderCrystalModel
extends v_3569_v {
    public e_4189_z cube;
    public e_4189_z glass;
    public e_4189_z base;

    public EnderCrystalModel() {
        super(o_2576_A::G_564_y);
        EndCrystalRenderer endercrystalrenderer = new EndCrystalRenderer(MinecraftClient.A_4115_X().O_508_d());
        this.cube = (e_4189_z)Reflector.RenderEnderCrystal_modelRenderers.getValue(endercrystalrenderer, 0);
        this.glass = (e_4189_z)Reflector.RenderEnderCrystal_modelRenderers.getValue(endercrystalrenderer, 1);
        this.base = (e_4189_z)Reflector.RenderEnderCrystal_modelRenderers.getValue(endercrystalrenderer, 2);
    }

    public EndCrystalRenderer updateRenderer(EndCrystalRenderer render) {
        if (!Reflector.RenderEnderCrystal_modelRenderers.exists()) {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystal");
            return null;
        }
        Reflector.RenderEnderCrystal_modelRenderers.setValue(render, 0, this.cube);
        Reflector.RenderEnderCrystal_modelRenderers.setValue(render, 1, this.glass);
        Reflector.RenderEnderCrystal_modelRenderers.setValue(render, 2, this.base);
        return render;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }
}



