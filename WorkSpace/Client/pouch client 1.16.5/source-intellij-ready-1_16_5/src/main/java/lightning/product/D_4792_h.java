/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.B_3871_I;
import lightning.product.D_1098_v;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.W_571_B;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.e_1689_x;
import lightning.product.g_221_o;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.z_3539_x;
import net.minecraftforge.client.extensions.IForgeVertexBuilder;
import net.optifine.Config;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntities;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.render.VertexPosition;
import net.optifine.shaders.Shaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface D_4792_h
extends IForgeVertexBuilder {
    public static final Logger u_1723_Y = LogManager.getLogger();
    public static final ThreadLocal<RenderEnv> v_4262_N = ThreadLocal.withInitial(() -> new RenderEnv(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), new c_1514_x(0, 0, 0)));
    public static final boolean w_1484_f = Reflector.ForgeHooksClient.exists();

    default public RenderEnv n_1700_B(K_4074_S p_getRenderEnv_1_, c_1514_x p_getRenderEnv_2_) {
        RenderEnv renderenv = v_4262_N.get();
        renderenv.reset(p_getRenderEnv_1_, p_getRenderEnv_2_);
        return renderenv;
    }

    public D_4792_h pos(double var1, double var3, double var5);

    public D_4792_h color(int var1, int var2, int var3, int var4);

    public D_4792_h tex(float var1, float var2);

    public D_4792_h overlay(int var1, int var2);

    public D_4792_h lightmap(int var1, int var2);

    public D_4792_h normal(float var1, float var2, float var3);

    public void endVertex();

    default public void n_1700_B(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
        this.pos(x, y, z);
        this.n_1700_B(red, green, blue, alpha);
        this.tex(texU, texV);
        this.R_4764_Y(overlayUV);
        this.J_1907_R(lightmapUV);
        this.normal(normalX, normalY, normalZ);
        this.endVertex();
    }

    default public D_4792_h n_1700_B(float red, float green, float blue, float alpha) {
        return this.color((int)(red * 255.0f), (int)(green * 255.0f), (int)(blue * 255.0f), (int)(alpha * 255.0f));
    }

    default public D_4792_h n_1700_B(int color) {
        float[] colors = H_2506_c.P_1922_E(color);
        return this.color((int)(colors[0] * 255.0f), (int)(colors[1] * 255.0f), (int)(colors[2] * 255.0f), (int)(colors[3] * 255.0f));
    }

    default public D_4792_h J_1907_R(int lightmapUV) {
        return this.lightmap(lightmapUV & 0xFFFF, lightmapUV >> 16 & 0xFFFF);
    }

    default public D_4792_h R_4764_Y(int overlayUV) {
        return this.overlay(overlayUV & 0xFFFF, overlayUV >> 16 & 0xFFFF);
    }

    default public void n_1700_B(g_221_o.n_1700_B matrixEntryIn, c_932_S quadIn, float redIn, float greenIn, float blueIn, int combinedLightIn, int combinedOverlayIn) {
        this.n_1700_B(matrixEntryIn, quadIn, this.getTempFloat4(1.0f, 1.0f, 1.0f, 1.0f), redIn, greenIn, blueIn, this.getTempInt4(combinedLightIn, combinedLightIn, combinedLightIn, combinedLightIn), combinedOverlayIn, false);
    }

    default public void n_1700_B(g_221_o.n_1700_B p_addVertexData_1_, c_932_S p_addVertexData_2_, float[] p_addVertexData_3_, float p_addVertexData_4_, float p_addVertexData_5_, float p_addVertexData_6_, float p_addVertexData_7_, int[] p_addVertexData_8_, int p_addVertexData_9_, boolean p_addVertexData_10_) {
        this.J_1907_R(p_addVertexData_1_, p_addVertexData_2_, p_addVertexData_3_, p_addVertexData_4_, p_addVertexData_5_, p_addVertexData_6_, p_addVertexData_7_, p_addVertexData_8_, p_addVertexData_9_, p_addVertexData_10_);
    }

    default public void n_1700_B(g_221_o.n_1700_B matrixEntryIn, c_932_S quadIn, float[] colorMuls, float redIn, float greenIn, float blueIn, int[] combinedLightsIn, int combinedOverlayIn, boolean mulColor) {
        this.J_1907_R(matrixEntryIn, quadIn, colorMuls, redIn, greenIn, blueIn, 1.0f, combinedLightsIn, combinedOverlayIn, mulColor);
    }

    default public void J_1907_R(g_221_o.n_1700_B p_addQuad_1_, c_932_S p_addQuad_2_, float[] p_addQuad_3_, float p_addQuad_4_, float p_addQuad_5_, float p_addQuad_6_, float p_addQuad_7_, int[] p_addQuad_8_, int p_addQuad_9_, boolean p_addQuad_10_) {
        IRandomEntity irandomentity;
        boolean flag1;
        int[] aint = this.isMultiTexture() ? p_addQuad_2_.getVertexDataSingle() : p_addQuad_2_.getVertexData();
        this.putSprite(p_addQuad_2_.getSprite());
        boolean flag = W_571_B.G_564_y();
        z_3539_x vector3i = p_addQuad_2_.getFace().M_182_A();
        float f = vector3i.getX();
        float f1 = vector3i.getY();
        float f2 = vector3i.getZ();
        D_1098_v matrix4f = p_addQuad_1_.n_1700_B();
        o_1290_k matrix3f = p_addQuad_1_.J_1907_R();
        float f3 = matrix3f.J_1907_R(f, f1, f2);
        float f4 = matrix3f.R_4764_Y(f, f1, f2);
        float f5 = matrix3f.G_564_y(f, f1, f2);
        int i = 8;
        int j = E_688_b.w_1484_f.n_1700_B();
        int k = aint.length / j;
        boolean bl = flag1 = Config.isShaders() && Shaders.useVelocityAttrib && Config.isMinecraftThread();
        if (flag1 && (irandomentity = RandomEntities.getRandomEntityRendered()) != null) {
            VertexPosition[] avertexposition = p_addQuad_2_.getVertexPositions(irandomentity.getId());
            this.setQuadVertexPositions(avertexposition);
        }
        for (int i1 = 0; i1 < k; ++i1) {
            M_1336_P vector3f;
            float f11;
            float f10;
            float f9;
            float f13;
            int j1 = i1 * j;
            float f6 = Float.intBitsToFloat(aint[j1 + 0]);
            float f7 = Float.intBitsToFloat(aint[j1 + 1]);
            float f8 = Float.intBitsToFloat(aint[j1 + 2]);
            float f12 = 1.0f;
            float f14 = f13 = flag ? 1.0f : p_addQuad_3_[i1];
            if (p_addQuad_10_) {
                int l = aint[j1 + 3];
                float f142 = (float)(l & 0xFF) / 255.0f;
                float f15 = (float)(l >> 8 & 0xFF) / 255.0f;
                float f16 = (float)(l >> 16 & 0xFF) / 255.0f;
                f9 = f142 * f13 * p_addQuad_4_;
                f10 = f15 * f13 * p_addQuad_5_;
                f11 = f16 * f13 * p_addQuad_6_;
                if (w_1484_f) {
                    float f17 = (float)(l >> 24 & 0xFF) / 255.0f;
                    f12 = f17 * p_addQuad_7_;
                }
            } else {
                f9 = f13 * p_addQuad_4_;
                f10 = f13 * p_addQuad_5_;
                f11 = f13 * p_addQuad_6_;
                if (w_1484_f) {
                    f12 = p_addQuad_7_;
                }
            }
            int k1 = p_addQuad_8_[i1];
            if (w_1484_f) {
                k1 = this.n_1700_B(p_addQuad_8_[i1], aint, j1);
            }
            float f19 = Float.intBitsToFloat(aint[j1 + 4]);
            float f20 = Float.intBitsToFloat(aint[j1 + 5]);
            float f21 = matrix4f.J_1907_R(f6, f7, f8, 1.0f);
            float f22 = matrix4f.R_4764_Y(f6, f7, f8, 1.0f);
            float f18 = matrix4f.G_564_y(f6, f7, f8, 1.0f);
            if (w_1484_f && (vector3f = this.n_1700_B(aint, j1, p_addQuad_1_.J_1907_R())) != null) {
                f3 = vector3f.n_1700_B();
                f4 = vector3f.J_1907_R();
                f5 = vector3f.R_4764_Y();
            }
            if (flag) {
                f12 = p_addQuad_3_[i1];
            }
            this.n_1700_B(f21, f22, f18, f9, f10, f11, f12, f19, f20, p_addQuad_9_, k1, f3, f4, f5);
        }
    }

    default public D_4792_h n_1700_B(D_1098_v matrixIn, float x, float y, float z) {
        float f = matrixIn.J_1907_R(x, y, z, 1.0f);
        float f1 = matrixIn.R_4764_Y(x, y, z, 1.0f);
        float f2 = matrixIn.G_564_y(x, y, z, 1.0f);
        return this.pos(f, f1, f2);
    }

    default public D_4792_h n_1700_B(o_1290_k matrixIn, float x, float y, float z) {
        float f = matrixIn.J_1907_R(x, y, z);
        float f1 = matrixIn.R_4764_Y(x, y, z);
        float f2 = matrixIn.G_564_y(x, y, z);
        return this.normal(f, f1, f2);
    }

    default public void putSprite(B_3871_I p_putSprite_1_) {
    }

    default public void setSprite(B_3871_I p_setSprite_1_) {
    }

    default public boolean isMultiTexture() {
        return false;
    }

    default public void setRenderType(o_2576_A p_setRenderType_1_) {
    }

    default public o_2576_A getRenderType() {
        return null;
    }

    default public void setRenderBlocks(boolean p_setRenderBlocks_1_) {
    }

    default public M_1336_P getTempVec3f(M_1336_P p_getTempVec3f_1_) {
        return p_getTempVec3f_1_.P_1922_E();
    }

    default public M_1336_P getTempVec3f(float p_getTempVec3f_1_, float p_getTempVec3f_2_, float p_getTempVec3f_3_) {
        return new M_1336_P(p_getTempVec3f_1_, p_getTempVec3f_2_, p_getTempVec3f_3_);
    }

    default public float[] getTempFloat4(float p_getTempFloat4_1_, float p_getTempFloat4_2_, float p_getTempFloat4_3_, float p_getTempFloat4_4_) {
        return new float[]{p_getTempFloat4_1_, p_getTempFloat4_2_, p_getTempFloat4_3_, p_getTempFloat4_4_};
    }

    default public int[] getTempInt4(int p_getTempInt4_1_, int p_getTempInt4_2_, int p_getTempInt4_3_, int p_getTempInt4_4_) {
        return new int[]{p_getTempInt4_1_, p_getTempInt4_2_, p_getTempInt4_3_, p_getTempInt4_4_};
    }

    default public o_3091_w.n_1700_B getRenderTypeBuffer() {
        return null;
    }

    default public void setQuadVertexPositions(VertexPosition[] p_setQuadVertexPositions_1_) {
    }

    default public void setMidBlock(float p_setMidBlock_1_, float p_setMidBlock_2_, float p_setMidBlock_3_) {
    }

    default public D_4792_h n_1700_B() {
        return null;
    }

    default public int n_1700_B(int p_applyBakedLighting_1_, int[] p_applyBakedLighting_2_, int p_applyBakedLighting_3_) {
        int i = D_4792_h.G_564_y(0);
        int j = e_1689_x.n_1700_B(p_applyBakedLighting_2_[p_applyBakedLighting_3_ + i]);
        int k = e_1689_x.J_1907_R(p_applyBakedLighting_2_[p_applyBakedLighting_3_ + i]);
        if (j == 0 && k == 0) {
            return p_applyBakedLighting_1_;
        }
        int l = e_1689_x.n_1700_B(p_applyBakedLighting_1_);
        int i1 = e_1689_x.J_1907_R(p_applyBakedLighting_1_);
        l = Math.max(l, j);
        i1 = Math.max(i1, k);
        return e_1689_x.n_1700_B(l, i1);
    }

    public static int G_564_y(int p_getLightOffset_0_) {
        return p_getLightOffset_0_ * 8 + 6;
    }

    default public M_1336_P n_1700_B(int[] p_applyBakedNormals_1_, int p_applyBakedNormals_2_, o_1290_k p_applyBakedNormals_3_) {
        int i = 7;
        int j = p_applyBakedNormals_1_[p_applyBakedNormals_2_ + i];
        byte b0 = (byte)(j >> 0 & 0xFF);
        byte b1 = (byte)(j >> 8 & 0xFF);
        byte b2 = (byte)(j >> 16 & 0xFF);
        if (b0 == 0 && b1 == 0 && b2 == 0) {
            return null;
        }
        M_1336_P vector3f = this.getTempVec3f((float)b0 / 127.0f, (float)b1 / 127.0f, (float)b2 / 127.0f);
        vector3f.n_1700_B(p_applyBakedNormals_3_);
        return vector3f;
    }
}


