/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.b_257_Y;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.v_3569_v;
import lightning.product.z_1333_t;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntities;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.model.ModelSprite;
import net.optifine.render.BoxVertexPositions;
import net.optifine.render.VertexPosition;
import net.optifine.shaders.Shaders;

public class e_4189_z {
    public float n_1700_B = 64.0f;
    public float J_1907_R = 32.0f;
    private int multiplayerClientSuggestionProvider;
    private int w_1457_N;
    public float R_4764_Y;
    public float G_564_y;
    public float P_1922_E;
    public float u_1723_Y;
    public float v_4262_N;
    public float w_1484_f;
    public boolean t_148_a;
    public boolean s_956_w = true;
    public final ObjectList<n_1700_B> u_2550_I = new ObjectArrayList();
    public final ObjectList<e_4189_z> M_588_G = new ObjectArrayList();
    public List P_4830_p = new ArrayList();
    public boolean h_1847_R = false;
    public float Q_4569_t = 1.0f;
    public float M_182_A = 1.0f;
    public float t_1786_h = 1.0f;
    private g_2336_b Y_601_j = null;
    private String Y_259_p = null;
    private ModelUpdater Q_2552_b;
    private z_883_p C_2741_M = Config.getRenderGlobal();

    public e_4189_z(v_3569_v model) {
        model.accept(this);
        this.J_1907_R(model.textureWidth, model.textureHeight);
    }

    public e_4189_z(v_3569_v model, int texOffX, int texOffY) {
        this(model.textureWidth, model.textureHeight, texOffX, texOffY);
        model.accept(this);
    }

    public e_4189_z(int textureWidthIn, int textureHeightIn, int textureOffsetXIn, int textureOffsetYIn) {
        this.J_1907_R(textureWidthIn, textureHeightIn);
        this.n_1700_B(textureOffsetXIn, textureOffsetYIn);
    }

    private e_4189_z() {
    }

    public e_4189_z n_1700_B() {
        e_4189_z modelrenderer = new e_4189_z();
        modelrenderer.n_1700_B(this);
        return modelrenderer;
    }

    public void n_1700_B(e_4189_z modelRendererIn) {
        this.u_1723_Y = modelRendererIn.u_1723_Y;
        this.v_4262_N = modelRendererIn.v_4262_N;
        this.w_1484_f = modelRendererIn.w_1484_f;
        this.R_4764_Y = modelRendererIn.R_4764_Y;
        this.G_564_y = modelRendererIn.G_564_y;
        this.P_1922_E = modelRendererIn.P_1922_E;
    }

    public void J_1907_R(e_4189_z renderer) {
        this.M_588_G.add((Object)renderer);
    }

    public e_4189_z n_1700_B(int x, int y) {
        this.multiplayerClientSuggestionProvider = x;
        this.w_1457_N = y;
        return this;
    }

    public e_4189_z n_1700_B(String partName, float x, float y, float z, int width, int height, int depth, float delta, int texX, int texY) {
        this.n_1700_B(texX, texY);
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, delta, delta, delta, this.t_148_a, false);
        return this;
    }

    public e_4189_z n_1700_B(float x, float y, float z, float width, float height, float depth) {
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, 0.0f, 0.0f, 0.0f, this.t_148_a, false);
        return this;
    }

    public e_4189_z n_1700_B(float x, float y, float z, float width, float height, float depth, boolean mirrorIn) {
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, 0.0f, 0.0f, 0.0f, mirrorIn, false);
        return this;
    }

    public void n_1700_B(float x, float y, float z, float width, float height, float depth, float delta) {
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, delta, delta, delta, this.t_148_a, false);
    }

    public void n_1700_B(float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ) {
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, this.t_148_a, false);
    }

    public void n_1700_B(float x, float y, float z, float width, float height, float depth, float delta, boolean mirrorIn) {
        this.n_1700_B(this.multiplayerClientSuggestionProvider, this.w_1457_N, x, y, z, width, height, depth, delta, delta, delta, mirrorIn, false);
    }

    private void n_1700_B(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, boolean p_228305_13_) {
        this.u_2550_I.add((Object)new n_1700_B(texOffX, texOffY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, mirorIn, this.n_1700_B, this.J_1907_R));
    }

    public void n_1700_B(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn) {
        this.R_4764_Y = rotationPointXIn;
        this.G_564_y = rotationPointYIn;
        this.P_1922_E = rotationPointZIn;
    }

    public void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn) {
        this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (!(!this.s_956_w || this.u_2550_I.isEmpty() && this.M_588_G.isEmpty() && this.P_4830_p.isEmpty())) {
            o_2576_A rendertype = null;
            o_3091_w.n_1700_B irendertypebuffer$impl = null;
            if (this.Y_601_j != null) {
                if (this.C_2741_M.v_4262_N) {
                    return;
                }
                irendertypebuffer$impl = bufferIn.getRenderTypeBuffer();
                if (irendertypebuffer$impl != null) {
                    D_4792_h ivertexbuilder = bufferIn.n_1700_B();
                    rendertype = irendertypebuffer$impl.R_4764_Y();
                    bufferIn = irendertypebuffer$impl.n_1700_B(this.Y_601_j, bufferIn);
                    if (ivertexbuilder != null) {
                        bufferIn = z_1333_t.n_1700_B(ivertexbuilder, bufferIn);
                    }
                }
            }
            if (this.Q_2552_b != null) {
                this.Q_2552_b.update();
            }
            matrixStackIn.n_1700_B();
            this.n_1700_B(matrixStackIn);
            this.n_1700_B(matrixStackIn.R_4764_Y(), bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            int j = this.M_588_G.size();
            for (int i = 0; i < j; ++i) {
                e_4189_z modelrenderer = (e_4189_z)this.M_588_G.get(i);
                modelrenderer.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            }
            int k = this.P_4830_p.size();
            for (int l = 0; l < k; ++l) {
                ModelSprite modelsprite = (ModelSprite)this.P_4830_p.get(l);
                modelsprite.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            }
            matrixStackIn.J_1907_R();
            if (rendertype != null) {
                irendertypebuffer$impl.getBuffer(rendertype);
            }
        }
    }

    public void n_1700_B(g_221_o matrixStackIn) {
        matrixStackIn.n_1700_B((double)(this.R_4764_Y / 16.0f), (double)(this.G_564_y / 16.0f), (double)(this.P_1922_E / 16.0f));
        if (this.w_1484_f != 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.J_1907_R(this.w_1484_f));
        }
        if (this.v_4262_N != 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.J_1907_R(this.v_4262_N));
        }
        if (this.u_1723_Y != 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.J_1907_R(this.u_1723_Y));
        }
    }

    private void n_1700_B(g_221_o.n_1700_B matrixEntryIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        D_1098_v matrix4f = matrixEntryIn.n_1700_B();
        o_1290_k matrix3f = matrixEntryIn.J_1907_R();
        boolean flag = Config.isShaders() && Shaders.useVelocityAttrib && Config.isMinecraftThread();
        int i = this.u_2550_I.size();
        for (int j = 0; j < i; ++j) {
            IRandomEntity irandomentity;
            n_1700_B modelrenderer$modelbox = (n_1700_B)this.u_2550_I.get(j);
            VertexPosition[][] avertexposition = null;
            if (flag && (irandomentity = RandomEntities.getRandomEntityRendered()) != null) {
                avertexposition = modelrenderer$modelbox.n_1700_B(irandomentity.getId());
            }
            int i1 = modelrenderer$modelbox.v_4262_N.length;
            for (int k = 0; k < i1; ++k) {
                R_4764_Y modelrenderer$texturedquad = modelrenderer$modelbox.v_4262_N[k];
                if (modelrenderer$texturedquad == null) continue;
                if (avertexposition != null) {
                    bufferIn.setQuadVertexPositions(avertexposition[k]);
                }
                M_1336_P vector3f = bufferIn.getTempVec3f(modelrenderer$texturedquad.J_1907_R);
                vector3f.n_1700_B(matrix3f);
                float f = vector3f.n_1700_B();
                float f1 = vector3f.J_1907_R();
                float f2 = vector3f.R_4764_Y();
                for (int l = 0; l < 4; ++l) {
                    J_1907_R modelrenderer$positiontexturevertex = modelrenderer$texturedquad.n_1700_B[l];
                    float f3 = modelrenderer$positiontexturevertex.n_1700_B.n_1700_B() / 16.0f;
                    float f4 = modelrenderer$positiontexturevertex.n_1700_B.J_1907_R() / 16.0f;
                    float f5 = modelrenderer$positiontexturevertex.n_1700_B.R_4764_Y() / 16.0f;
                    float f6 = matrix4f.J_1907_R(f3, f4, f5, 1.0f);
                    float f7 = matrix4f.R_4764_Y(f3, f4, f5, 1.0f);
                    float f8 = matrix4f.G_564_y(f3, f4, f5, 1.0f);
                    bufferIn.n_1700_B(f6, f7, f8, red, green, blue, alpha, modelrenderer$positiontexturevertex.J_1907_R, modelrenderer$positiontexturevertex.R_4764_Y, packedOverlayIn, packedLightIn, f, f1, f2);
                }
            }
        }
    }

    public e_4189_z J_1907_R(int textureWidthIn, int textureHeightIn) {
        this.n_1700_B = textureWidthIn;
        this.J_1907_R = textureHeightIn;
        return this;
    }

    public n_1700_B n_1700_B(Random randomIn) {
        return (n_1700_B)this.u_2550_I.get(randomIn.nextInt(this.u_2550_I.size()));
    }

    public void n_1700_B(float p_addSprite_1_, float p_addSprite_2_, float p_addSprite_3_, int p_addSprite_4_, int p_addSprite_5_, int p_addSprite_6_, float p_addSprite_7_) {
        this.P_4830_p.add(new ModelSprite(this, this.multiplayerClientSuggestionProvider, this.w_1457_N, p_addSprite_1_, p_addSprite_2_, p_addSprite_3_, p_addSprite_4_, p_addSprite_5_, p_addSprite_6_, p_addSprite_7_));
    }

    public g_2336_b J_1907_R() {
        return this.Y_601_j;
    }

    public void n_1700_B(g_2336_b p_setTextureLocation_1_) {
        this.Y_601_j = p_setTextureLocation_1_;
    }

    public String R_4764_Y() {
        return this.Y_259_p;
    }

    public void n_1700_B(String p_setId_1_) {
        this.Y_259_p = p_setId_1_;
    }

    public void n_1700_B(int[][] p_addBox_1_, float p_addBox_2_, float p_addBox_3_, float p_addBox_4_, float p_addBox_5_, float p_addBox_6_, float p_addBox_7_, float p_addBox_8_) {
        this.u_2550_I.add((Object)new n_1700_B(p_addBox_1_, p_addBox_2_, p_addBox_3_, p_addBox_4_, p_addBox_5_, p_addBox_6_, p_addBox_7_, p_addBox_8_, p_addBox_8_, p_addBox_8_, this.t_148_a, this.n_1700_B, this.J_1907_R));
    }

    public e_4189_z n_1700_B(int p_getChild_1_) {
        if (this.M_588_G == null) {
            return null;
        }
        return p_getChild_1_ >= 0 && p_getChild_1_ < this.M_588_G.size() ? (e_4189_z)this.M_588_G.get(p_getChild_1_) : null;
    }

    public e_4189_z J_1907_R(String p_getChild_1_) {
        if (p_getChild_1_ == null) {
            return null;
        }
        if (this.M_588_G != null) {
            for (int i = 0; i < this.M_588_G.size(); ++i) {
                e_4189_z modelrenderer = (e_4189_z)this.M_588_G.get(i);
                if (!p_getChild_1_.equals(modelrenderer.R_4764_Y())) continue;
                return modelrenderer;
            }
        }
        return null;
    }

    public e_4189_z R_4764_Y(String p_getChildDeep_1_) {
        if (p_getChildDeep_1_ == null) {
            return null;
        }
        e_4189_z modelrenderer = this.J_1907_R(p_getChildDeep_1_);
        if (modelrenderer != null) {
            return modelrenderer;
        }
        if (this.M_588_G != null) {
            for (int i = 0; i < this.M_588_G.size(); ++i) {
                e_4189_z modelrenderer1 = (e_4189_z)this.M_588_G.get(i);
                e_4189_z modelrenderer2 = modelrenderer1.R_4764_Y(p_getChildDeep_1_);
                if (modelrenderer2 == null) continue;
                return modelrenderer2;
            }
        }
        return null;
    }

    public void n_1700_B(ModelUpdater p_setModelUpdater_1_) {
        this.Q_2552_b = p_setModelUpdater_1_;
    }

    public String toString() {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("id: " + this.Y_259_p + ", boxes: " + (this.u_2550_I != null ? Integer.valueOf(this.u_2550_I.size()) : null) + ", submodels: " + (this.M_588_G != null ? Integer.valueOf(this.M_588_G.size()) : null));
        return stringbuffer.toString();
    }

    public static class n_1700_B {
        private final R_4764_Y[] v_4262_N;
        public final float n_1700_B;
        public final float J_1907_R;
        public final float R_4764_Y;
        public final float G_564_y;
        public final float P_1922_E;
        public final float u_1723_Y;
        private BoxVertexPositions w_1484_f;

        public n_1700_B(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, float texWidth, float texHeight) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = z;
            this.G_564_y = x + width;
            this.P_1922_E = y + height;
            this.u_1723_Y = z + depth;
            this.v_4262_N = new R_4764_Y[6];
            float f = x + width;
            float f1 = y + height;
            float f2 = z + depth;
            x -= deltaX;
            y -= deltaY;
            z -= deltaZ;
            f += deltaX;
            f1 += deltaY;
            f2 += deltaZ;
            if (mirorIn) {
                float f3 = f;
                f = x;
                x = f3;
            }
            J_1907_R modelrenderer$positiontexturevertex7 = new J_1907_R(x, y, z, 0.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex = new J_1907_R(f, y, z, 0.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex1 = new J_1907_R(f, f1, z, 8.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex2 = new J_1907_R(x, f1, z, 8.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex3 = new J_1907_R(x, y, f2, 0.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex4 = new J_1907_R(f, y, f2, 0.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex5 = new J_1907_R(f, f1, f2, 8.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex6 = new J_1907_R(x, f1, f2, 8.0f, 0.0f);
            float f4 = texOffX;
            float f5 = (float)texOffX + depth;
            float f6 = (float)texOffX + depth + width;
            float f7 = (float)texOffX + depth + width + width;
            float f8 = (float)texOffX + depth + width + depth;
            float f9 = (float)texOffX + depth + width + depth + width;
            float f10 = texOffY;
            float f11 = (float)texOffY + depth;
            float f12 = (float)texOffY + depth + height;
            this.v_4262_N[2] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex}, f5, f10, f6, f11, texWidth, texHeight, mirorIn, b_257_Y.n_1700_B);
            this.v_4262_N[3] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex1, modelrenderer$positiontexturevertex2, modelrenderer$positiontexturevertex6, modelrenderer$positiontexturevertex5}, f6, f11, f7, f10, texWidth, texHeight, mirorIn, b_257_Y.J_1907_R);
            this.v_4262_N[1] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex6, modelrenderer$positiontexturevertex2}, f4, f11, f5, f12, texWidth, texHeight, mirorIn, b_257_Y.P_1922_E);
            this.v_4262_N[4] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex, modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex2, modelrenderer$positiontexturevertex1}, f5, f11, f6, f12, texWidth, texHeight, mirorIn, b_257_Y.R_4764_Y);
            this.v_4262_N[0] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex, modelrenderer$positiontexturevertex1, modelrenderer$positiontexturevertex5}, f6, f11, f8, f12, texWidth, texHeight, mirorIn, b_257_Y.u_1723_Y);
            this.v_4262_N[5] = new R_4764_Y(new J_1907_R[]{modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex5, modelrenderer$positiontexturevertex6}, f8, f11, f9, f12, texWidth, texHeight, mirorIn, b_257_Y.G_564_y);
        }

        public n_1700_B(int[][] p_i242122_1_, float p_i242122_2_, float p_i242122_3_, float p_i242122_4_, float p_i242122_5_, float p_i242122_6_, float p_i242122_7_, float p_i242122_8_, float p_i242122_9_, float p_i242122_10_, boolean p_i242122_11_, float p_i242122_12_, float p_i242122_13_) {
            this.n_1700_B = p_i242122_2_;
            this.J_1907_R = p_i242122_3_;
            this.R_4764_Y = p_i242122_4_;
            this.G_564_y = p_i242122_2_ + p_i242122_5_;
            this.P_1922_E = p_i242122_3_ + p_i242122_6_;
            this.u_1723_Y = p_i242122_4_ + p_i242122_7_;
            this.v_4262_N = new R_4764_Y[6];
            float f = p_i242122_2_ + p_i242122_5_;
            float f1 = p_i242122_3_ + p_i242122_6_;
            float f2 = p_i242122_4_ + p_i242122_7_;
            p_i242122_2_ -= p_i242122_8_;
            p_i242122_3_ -= p_i242122_9_;
            p_i242122_4_ -= p_i242122_10_;
            f += p_i242122_8_;
            f1 += p_i242122_9_;
            f2 += p_i242122_10_;
            if (p_i242122_11_) {
                float f3 = f;
                f = p_i242122_2_;
                p_i242122_2_ = f3;
            }
            J_1907_R modelrenderer$positiontexturevertex7 = new J_1907_R(p_i242122_2_, p_i242122_3_, p_i242122_4_, 0.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex = new J_1907_R(f, p_i242122_3_, p_i242122_4_, 0.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex1 = new J_1907_R(f, f1, p_i242122_4_, 8.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex2 = new J_1907_R(p_i242122_2_, f1, p_i242122_4_, 8.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex3 = new J_1907_R(p_i242122_2_, p_i242122_3_, f2, 0.0f, 0.0f);
            J_1907_R modelrenderer$positiontexturevertex4 = new J_1907_R(f, p_i242122_3_, f2, 0.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex5 = new J_1907_R(f, f1, f2, 8.0f, 8.0f);
            J_1907_R modelrenderer$positiontexturevertex6 = new J_1907_R(p_i242122_2_, f1, f2, 8.0f, 0.0f);
            this.v_4262_N[2] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex}, p_i242122_1_[1], true, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.n_1700_B);
            this.v_4262_N[3] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex1, modelrenderer$positiontexturevertex2, modelrenderer$positiontexturevertex6, modelrenderer$positiontexturevertex5}, p_i242122_1_[0], true, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.J_1907_R);
            this.v_4262_N[1] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex6, modelrenderer$positiontexturevertex2}, p_i242122_1_[5], false, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.P_1922_E);
            this.v_4262_N[4] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex, modelrenderer$positiontexturevertex7, modelrenderer$positiontexturevertex2, modelrenderer$positiontexturevertex1}, p_i242122_1_[2], false, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.R_4764_Y);
            this.v_4262_N[0] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex, modelrenderer$positiontexturevertex1, modelrenderer$positiontexturevertex5}, p_i242122_1_[4], false, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.u_1723_Y);
            this.v_4262_N[5] = this.n_1700_B(new J_1907_R[]{modelrenderer$positiontexturevertex3, modelrenderer$positiontexturevertex4, modelrenderer$positiontexturevertex5, modelrenderer$positiontexturevertex6}, p_i242122_1_[3], false, p_i242122_12_, p_i242122_13_, p_i242122_11_, b_257_Y.G_564_y);
        }

        private R_4764_Y n_1700_B(J_1907_R[] p_makeTexturedQuad_1_, int[] p_makeTexturedQuad_2_, boolean p_makeTexturedQuad_3_, float p_makeTexturedQuad_4_, float p_makeTexturedQuad_5_, boolean p_makeTexturedQuad_6_, b_257_Y p_makeTexturedQuad_7_) {
            if (p_makeTexturedQuad_2_ == null) {
                return null;
            }
            return p_makeTexturedQuad_3_ ? new R_4764_Y(p_makeTexturedQuad_1_, p_makeTexturedQuad_2_[2], p_makeTexturedQuad_2_[3], p_makeTexturedQuad_2_[0], p_makeTexturedQuad_2_[1], p_makeTexturedQuad_4_, p_makeTexturedQuad_5_, p_makeTexturedQuad_6_, p_makeTexturedQuad_7_) : new R_4764_Y(p_makeTexturedQuad_1_, p_makeTexturedQuad_2_[0], p_makeTexturedQuad_2_[1], p_makeTexturedQuad_2_[2], p_makeTexturedQuad_2_[3], p_makeTexturedQuad_4_, p_makeTexturedQuad_5_, p_makeTexturedQuad_6_, p_makeTexturedQuad_7_);
        }

        public VertexPosition[][] n_1700_B(int p_getBoxVertexPositions_1_) {
            if (this.w_1484_f == null) {
                this.w_1484_f = new BoxVertexPositions();
            }
            return (VertexPosition[][])this.w_1484_f.get(p_getBoxVertexPositions_1_);
        }
    }

    static class R_4764_Y {
        public final J_1907_R[] n_1700_B;
        public final M_1336_P J_1907_R;

        public R_4764_Y(J_1907_R[] positionsIn, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirrorIn, b_257_Y directionIn) {
            this.n_1700_B = positionsIn;
            float f = 0.0f / texWidth;
            float f1 = 0.0f / texHeight;
            if (Config.isAntialiasing()) {
                f = 0.05f / texWidth;
                f1 = 0.05f / texHeight;
                if (u2 < u1) {
                    f = -f;
                }
                if (v2 < v1) {
                    f1 = -f1;
                }
            }
            positionsIn[0] = positionsIn[0].n_1700_B(u2 / texWidth - f, v1 / texHeight + f1);
            positionsIn[1] = positionsIn[1].n_1700_B(u1 / texWidth + f, v1 / texHeight + f1);
            positionsIn[2] = positionsIn[2].n_1700_B(u1 / texWidth + f, v2 / texHeight - f1);
            positionsIn[3] = positionsIn[3].n_1700_B(u2 / texWidth - f, v2 / texHeight - f1);
            if (mirrorIn) {
                int i = positionsIn.length;
                for (int j = 0; j < i / 2; ++j) {
                    J_1907_R modelrenderer$positiontexturevertex = positionsIn[j];
                    positionsIn[j] = positionsIn[i - 1 - j];
                    positionsIn[i - 1 - j] = modelrenderer$positiontexturevertex;
                }
            }
            this.J_1907_R = directionIn.M_588_G();
            if (mirrorIn) {
                this.J_1907_R.n_1700_B(-1.0f, 1.0f, 1.0f);
            }
        }
    }

    static class J_1907_R {
        public final M_1336_P n_1700_B;
        public final float J_1907_R;
        public final float R_4764_Y;

        public J_1907_R(float x, float y, float z, float texU, float texV) {
            this(new M_1336_P(x, y, z), texU, texV);
        }

        public J_1907_R n_1700_B(float texU, float texV) {
            return new J_1907_R(this.n_1700_B, texU, texV);
        }

        public J_1907_R(M_1336_P posIn, float texU, float texV) {
            this.n_1700_B = posIn;
            this.J_1907_R = texU;
            this.R_4764_Y = texV;
        }
    }
}


