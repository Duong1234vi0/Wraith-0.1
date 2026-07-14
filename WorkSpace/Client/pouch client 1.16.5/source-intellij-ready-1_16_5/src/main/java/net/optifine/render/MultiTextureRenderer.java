/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.nio.Buffer;
import java.nio.IntBuffer;
import lightning.product.B_3871_I;
import lightning.product.L_3848_p;
import lightning.product.X_933_l;
import lightning.product.u_530_F;
import net.optifine.Config;
import net.optifine.render.MultiTextureData;
import net.optifine.render.SpriteRenderData;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTex;

public class MultiTextureRenderer {
    private static IntBuffer bufferPositions = Config.createDirectIntBuffer(1024);
    private static IntBuffer bufferCounts = Config.createDirectIntBuffer(1024);
    private static boolean shaders;

    public static void draw(int drawMode, MultiTextureData multiTextureData) {
        shaders = Config.isShaders();
        SpriteRenderData[] aspriterenderdata = multiTextureData.getSpriteRenderDatas();
        for (int i = 0; i < aspriterenderdata.length; ++i) {
            SpriteRenderData spriterenderdata = aspriterenderdata[i];
            MultiTextureRenderer.draw(drawMode, spriterenderdata);
        }
    }

    private static void draw(int drawMode, SpriteRenderData srd) {
        B_3871_I textureatlassprite = srd.getSprite();
        int[] aint = srd.getPositions();
        int[] aint1 = srd.getCounts();
        X_933_l.w_1457_N(textureatlassprite.u_1723_Y);
        if (shaders) {
            int i = textureatlassprite.u_2550_I != null ? textureatlassprite.u_2550_I.u_1723_Y : 0;
            int j = textureatlassprite.M_588_G != null ? textureatlassprite.M_588_G.u_1723_Y : 0;
            L_3848_p atlastexture = textureatlassprite.u_2550_I();
            ShadersTex.bindNSTextures(i, j, atlastexture.h_1847_R(), atlastexture.Q_4569_t(), atlastexture.M_588_G());
            if (Shaders.uniform_spriteBounds.isDefined()) {
                Shaders.uniform_spriteBounds.setValue(textureatlassprite.u_1723_Y(), textureatlassprite.w_1484_f(), textureatlassprite.v_4262_N(), textureatlassprite.t_148_a());
            }
        }
        if (bufferPositions.capacity() < aint.length) {
            int k = u_530_F.R_4764_Y(aint.length);
            bufferPositions = Config.createDirectIntBuffer(k);
            bufferCounts = Config.createDirectIntBuffer(k);
        }
        ((Buffer)bufferPositions).clear();
        ((Buffer)bufferCounts).clear();
        bufferPositions.put(aint);
        bufferCounts.put(aint1);
        ((Buffer)bufferPositions).flip();
        ((Buffer)bufferCounts).flip();
        X_933_l.n_1700_B(drawMode, bufferPositions, bufferCounts);
    }
}

