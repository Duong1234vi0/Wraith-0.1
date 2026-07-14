/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.model;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_1290_k;
import lightning.product.u_530_F;

public class ModelSprite {
    private e_4189_z modelRenderer = null;
    private int textureOffsetX = 0;
    private int textureOffsetY = 0;
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;
    private int sizeX = 0;
    private int sizeY = 0;
    private int sizeZ = 0;
    private float sizeAdd = 0.0f;
    private float minU = 0.0f;
    private float minV = 0.0f;
    private float maxU = 0.0f;
    private float maxV = 0.0f;

    public ModelSprite(e_4189_z modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd) {
        this.modelRenderer = modelRenderer;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.sizeAdd = sizeAdd;
        this.minU = (float)textureOffsetX / modelRenderer.n_1700_B;
        this.minV = (float)textureOffsetY / modelRenderer.J_1907_R;
        this.maxU = (float)(textureOffsetX + sizeX) / modelRenderer.n_1700_B;
        this.maxV = (float)(textureOffsetY + sizeY) / modelRenderer.J_1907_R;
    }

    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float f = 0.0625f;
        matrixStackIn.n_1700_B((double)(this.posX * f), (double)(this.posY * f), (double)(this.posZ * f));
        float f1 = this.minU;
        float f2 = this.maxU;
        float f3 = this.minV;
        float f4 = this.maxV;
        if (this.modelRenderer.t_148_a) {
            f1 = this.maxU;
            f2 = this.minU;
        }
        if (this.modelRenderer.h_1847_R) {
            f3 = this.maxV;
            f4 = this.minV;
        }
        ModelSprite.renderItemIn2D(matrixStackIn, bufferIn, f1, f3, f2, f4, this.sizeX, this.sizeY, f * (float)this.sizeZ, this.modelRenderer.n_1700_B, this.modelRenderer.J_1907_R, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        matrixStackIn.n_1700_B((double)(-this.posX * f), (double)(-this.posY * f), (double)(-this.posZ * f));
    }

    public static void renderItemIn2D(g_221_o matrixStackIn, D_4792_h bufferIn, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (width < 6.25E-4f) {
            width = 6.25E-4f;
        }
        float f = maxU - minU;
        float f1 = maxV - minV;
        float f2 = u_530_F.P_1922_E(f) * (texWidth / 16.0f);
        float f3 = u_530_F.P_1922_E(f1) * (texHeight / 16.0f);
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = -1.0f;
        ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f3, 0.0f, red, green, blue, alpha, minU, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f3, 0.0f, red, green, blue, alpha, maxU, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, f2, 0.0f, 0.0f, red, green, blue, alpha, maxU, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, 0.0f, 0.0f, red, green, blue, alpha, minU, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
        f4 = 0.0f;
        f5 = 0.0f;
        f6 = 1.0f;
        ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, 0.0f, width, red, green, blue, alpha, minU, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, f2, 0.0f, width, red, green, blue, alpha, maxU, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f3, width, red, green, blue, alpha, maxU, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
        ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f3, width, red, green, blue, alpha, minU, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
        float f7 = 0.5f * f / (float)sizeX;
        float f8 = 0.5f * f1 / (float)sizeY;
        f4 = -1.0f;
        f5 = 0.0f;
        f6 = 0.0f;
        for (int i = 0; i < sizeX; ++i) {
            float f9 = (float)i / (float)sizeX;
            float f10 = minU + f * f9 + f7;
            ModelSprite.addVertex(matrixStackIn, bufferIn, f9 * f2, f3, width, red, green, blue, alpha, f10, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f9 * f2, f3, 0.0f, red, green, blue, alpha, f10, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f9 * f2, 0.0f, 0.0f, red, green, blue, alpha, f10, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f9 * f2, 0.0f, width, red, green, blue, alpha, f10, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
        }
        f4 = 1.0f;
        f5 = 0.0f;
        f6 = 0.0f;
        for (int j = 0; j < sizeX; ++j) {
            float f12 = (float)j / (float)sizeX;
            float f15 = minU + f * f12 + f7;
            float f11 = f12 + 1.0f / (float)sizeX;
            ModelSprite.addVertex(matrixStackIn, bufferIn, f11 * f2, 0.0f, width, red, green, blue, alpha, f15, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f11 * f2, 0.0f, 0.0f, red, green, blue, alpha, f15, minV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f11 * f2, f3, 0.0f, red, green, blue, alpha, f15, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f11 * f2, f3, width, red, green, blue, alpha, f15, maxV, packedOverlayIn, packedLightIn, f4, f5, f6);
        }
        f4 = 0.0f;
        f5 = 1.0f;
        f6 = 0.0f;
        for (int k = 0; k < sizeY; ++k) {
            float f13 = (float)k / (float)sizeY;
            float f16 = minV + f1 * f13 + f8;
            float f18 = f13 + 1.0f / (float)sizeY;
            ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f18 * f3, width, red, green, blue, alpha, minU, f16, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f18 * f3, width, red, green, blue, alpha, maxU, f16, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f18 * f3, 0.0f, red, green, blue, alpha, maxU, f16, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f18 * f3, 0.0f, red, green, blue, alpha, minU, f16, packedOverlayIn, packedLightIn, f4, f5, f6);
        }
        f4 = 0.0f;
        f5 = -1.0f;
        f6 = 0.0f;
        for (int l = 0; l < sizeY; ++l) {
            float f14 = (float)l / (float)sizeY;
            float f17 = minV + f1 * f14 + f8;
            ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f14 * f3, width, red, green, blue, alpha, maxU, f17, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f14 * f3, width, red, green, blue, alpha, minU, f17, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, 0.0f, f14 * f3, 0.0f, red, green, blue, alpha, minU, f17, packedOverlayIn, packedLightIn, f4, f5, f6);
            ModelSprite.addVertex(matrixStackIn, bufferIn, f2, f14 * f3, 0.0f, red, green, blue, alpha, maxU, f17, packedOverlayIn, packedLightIn, f4, f5, f6);
        }
    }

    static void addVertex(g_221_o matrixStackIn, D_4792_h bufferIn, float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        float f = matrix3f.J_1907_R(normalX, normalY, normalZ);
        float f1 = matrix3f.R_4764_Y(normalX, normalY, normalZ);
        float f2 = matrix3f.G_564_y(normalX, normalY, normalZ);
        float f3 = matrix4f.J_1907_R(x, y, z, 1.0f);
        float f4 = matrix4f.R_4764_Y(x, y, z, 1.0f);
        float f5 = matrix4f.G_564_y(x, y, z, 1.0f);
        bufferIn.n_1700_B(f3, f4, f5, red, green, blue, alpha, texU, texV, overlayUV, lightmapUV, f, f1, f2);
    }
}

