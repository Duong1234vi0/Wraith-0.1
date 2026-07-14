/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.model;

import java.util.Arrays;
import lightning.product.B_3871_I;
import lightning.product.E_688_b;
import lightning.product.L_972_x;
import lightning.product.b_1213_w;
import lightning.product.c_932_S;

public class BakedQuadRetextured
extends c_932_S {
    public BakedQuadRetextured(c_932_S quad, B_3871_I spriteIn) {
        super(BakedQuadRetextured.remapVertexData(quad.getVertexData(), quad.getSprite(), spriteIn), quad.getTintIndex(), L_972_x.n_1700_B(quad.getVertexData()), spriteIn, quad.applyDiffuseLighting());
    }

    private static int[] remapVertexData(int[] vertexData, B_3871_I sprite, B_3871_I spriteNew) {
        int[] aint = Arrays.copyOf(vertexData, vertexData.length);
        for (int i = 0; i < 4; ++i) {
            b_1213_w vertexformat = E_688_b.w_1484_f;
            int j = vertexformat.n_1700_B() * i;
            int k = vertexformat.n_1700_B(2) / 4;
            aint[j + k] = Float.floatToRawIntBits(spriteNew.n_1700_B((double)sprite.P_1922_E(Float.intBitsToFloat(vertexData[j + k]))));
            aint[j + k + 1] = Float.floatToRawIntBits(spriteNew.J_1907_R((double)sprite.u_1723_Y(Float.intBitsToFloat(vertexData[j + k + 1]))));
        }
        return aint;
    }
}

