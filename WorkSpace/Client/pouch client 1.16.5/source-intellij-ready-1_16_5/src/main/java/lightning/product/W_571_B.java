/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_688_b;
import lightning.product.K_4074_S;
import lightning.product.S_3826_o;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_46_E;
import lightning.product.j_3341_s;
import lightning.product.k_4467_X;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_2576_A;
import lightning.product.CrashReportCategory;
import lightning.product.ChunkBufferBuilderPack;
import lightning.product.u_530_F;
import lightning.product.z_883_p;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.optifine.BetterSnow;
import net.optifine.BlockPosM;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.EmissiveTextures;
import net.optifine.model.BlockModelCustomizer;
import net.optifine.model.ListQuadsOverlay;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.LightCacheOF;
import net.optifine.render.RenderEnv;
import net.optifine.render.RenderTypes;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;
import net.optifine.util.BlockUtils;

public class W_571_B {
    private final k_4467_X n_1700_B;
    private static final ThreadLocal<J_1907_R> J_1907_R = ThreadLocal.withInitial(() -> new J_1907_R());
    private static float R_4764_Y = 0.2f;
    private static boolean G_564_y = false;
    private static final LightCacheOF P_1922_E = new LightCacheOF();
    private static final o_2576_A[] u_1723_Y = new o_2576_A[]{RenderTypes.CUTOUT, RenderTypes.CUTOUT_MIPPED, RenderTypes.TRANSLUCENT};
    private boolean v_4262_N = Reflector.ForgeHooksClient.exists();

    public W_571_B(k_4467_X blockColorsIn) {
        this.n_1700_B = blockColorsIn;
    }

    public boolean n_1700_B(BlockAndTintGetter worldIn, S_3826_o modelIn, K_4074_S stateIn, c_1514_x posIn, g_221_o matrixIn, D_4792_h buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlayIn) {
        return this.n_1700_B(worldIn, modelIn, stateIn, posIn, matrixIn, buffer, checkSides, randomIn, rand, combinedOverlayIn, EmptyModelData.INSTANCE);
    }

    public boolean n_1700_B(BlockAndTintGetter p_renderModel_1_, S_3826_o p_renderModel_2_, K_4074_S p_renderModel_3_, c_1514_x p_renderModel_4_, g_221_o p_renderModel_5_, D_4792_h p_renderModel_6_, boolean p_renderModel_7_, Random p_renderModel_8_, long p_renderModel_9_, int p_renderModel_11_, IModelData p_renderModel_12_) {
        boolean flag;
        g_46_E renderBlockModelEvent = new g_46_E(p_renderModel_5_, p_renderModel_3_, p_renderModel_4_);
        A_4115_X.n_1700_B(renderBlockModelEvent);
        if (renderBlockModelEvent.n_1700_B()) {
            return true;
        }
        boolean bl = flag = MinecraftClient.H_2857_Y() && ReflectorForge.getLightValue(p_renderModel_3_, p_renderModel_1_, p_renderModel_4_) == 0 && p_renderModel_2_.n_1700_B();
        if (this.v_4262_N) {
            p_renderModel_12_ = p_renderModel_2_.getModelData(p_renderModel_1_, p_renderModel_4_, p_renderModel_3_, p_renderModel_12_);
        }
        e_2866_D vector3d = p_renderModel_3_.h_1847_R(p_renderModel_1_, p_renderModel_4_);
        p_renderModel_5_.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
        try {
            boolean flag1;
            if (Config.isShaders()) {
                SVertexBuilder.pushEntity(p_renderModel_3_, p_renderModel_6_);
            }
            if (!Config.isAlternateBlocks()) {
                p_renderModel_9_ = 0L;
            }
            RenderEnv renderenv = p_renderModel_6_.n_1700_B(p_renderModel_3_, p_renderModel_4_);
            p_renderModel_2_ = BlockModelCustomizer.getRenderModel(p_renderModel_2_, p_renderModel_3_, renderenv);
            boolean bl2 = flag1 = flag ? this.J_1907_R(p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_8_, p_renderModel_9_, p_renderModel_11_, p_renderModel_12_) : this.R_4764_Y(p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_8_, p_renderModel_9_, p_renderModel_11_, p_renderModel_12_);
            if (flag1) {
                this.n_1700_B(p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_11_, p_renderModel_7_, p_renderModel_8_, p_renderModel_9_, renderenv, flag, vector3d);
            }
            if (Config.isShaders()) {
                SVertexBuilder.popEntity(p_renderModel_6_);
            }
            return flag1;
        }
        catch (Throwable throwable1) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Tesselating block model");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block model being tesselated");
            CrashReportCategory.n_1700_B(crashreportcategory, p_renderModel_4_, p_renderModel_3_);
            crashreportcategory.n_1700_B("Using AO", flag);
            throw new ReportedException(crashreport);
        }
    }

    public boolean J_1907_R(BlockAndTintGetter worldIn, S_3826_o modelIn, K_4074_S stateIn, c_1514_x posIn, g_221_o matrixStackIn, D_4792_h buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlayIn) {
        return this.J_1907_R(worldIn, modelIn, stateIn, posIn, matrixStackIn, buffer, checkSides, randomIn, rand, combinedOverlayIn, EmptyModelData.INSTANCE);
    }

    public boolean J_1907_R(BlockAndTintGetter p_renderModelSmooth_1_, S_3826_o p_renderModelSmooth_2_, K_4074_S p_renderModelSmooth_3_, c_1514_x p_renderModelSmooth_4_, g_221_o p_renderModelSmooth_5_, D_4792_h p_renderModelSmooth_6_, boolean p_renderModelSmooth_7_, Random p_renderModelSmooth_8_, long p_renderModelSmooth_9_, int p_renderModelSmooth_11_, IModelData p_renderModelSmooth_12_) {
        List<c_932_S> list1;
        boolean flag = false;
        RenderEnv renderenv = p_renderModelSmooth_6_.n_1700_B(p_renderModelSmooth_3_, p_renderModelSmooth_4_);
        o_2576_A rendertype = p_renderModelSmooth_6_.getRenderType();
        for (b_257_Y direction : b_257_Y.v_4262_N) {
            if (p_renderModelSmooth_7_ && !BlockUtils.shouldSideBeRendered(p_renderModelSmooth_3_, p_renderModelSmooth_1_, p_renderModelSmooth_4_, direction, renderenv)) continue;
            p_renderModelSmooth_8_.setSeed(p_renderModelSmooth_9_);
            List<c_932_S> list = this.v_4262_N ? p_renderModelSmooth_2_.getQuads(p_renderModelSmooth_3_, direction, p_renderModelSmooth_8_, p_renderModelSmooth_12_) : p_renderModelSmooth_2_.n_1700_B(p_renderModelSmooth_3_, direction, p_renderModelSmooth_8_);
            list = BlockModelCustomizer.getRenderQuads(list, p_renderModelSmooth_1_, p_renderModelSmooth_3_, p_renderModelSmooth_4_, direction, rendertype, p_renderModelSmooth_9_, renderenv);
            this.n_1700_B(p_renderModelSmooth_1_, p_renderModelSmooth_3_, p_renderModelSmooth_4_, p_renderModelSmooth_5_, p_renderModelSmooth_6_, list, p_renderModelSmooth_11_, renderenv);
            flag = true;
        }
        p_renderModelSmooth_8_.setSeed(p_renderModelSmooth_9_);
        List<c_932_S> list = list1 = this.v_4262_N ? p_renderModelSmooth_2_.getQuads(p_renderModelSmooth_3_, null, p_renderModelSmooth_8_, p_renderModelSmooth_12_) : p_renderModelSmooth_2_.n_1700_B(p_renderModelSmooth_3_, null, p_renderModelSmooth_8_);
        if (!list1.isEmpty()) {
            list1 = BlockModelCustomizer.getRenderQuads(list1, p_renderModelSmooth_1_, p_renderModelSmooth_3_, p_renderModelSmooth_4_, null, rendertype, p_renderModelSmooth_9_, renderenv);
            this.n_1700_B(p_renderModelSmooth_1_, p_renderModelSmooth_3_, p_renderModelSmooth_4_, p_renderModelSmooth_5_, p_renderModelSmooth_6_, list1, p_renderModelSmooth_11_, renderenv);
            flag = true;
        }
        return flag;
    }

    public boolean R_4764_Y(BlockAndTintGetter worldIn, S_3826_o modelIn, K_4074_S stateIn, c_1514_x posIn, g_221_o matrixStackIn, D_4792_h buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlayIn) {
        return this.R_4764_Y(worldIn, modelIn, stateIn, posIn, matrixStackIn, buffer, checkSides, randomIn, rand, combinedOverlayIn, EmptyModelData.INSTANCE);
    }

    public boolean R_4764_Y(BlockAndTintGetter p_renderModelFlat_1_, S_3826_o p_renderModelFlat_2_, K_4074_S p_renderModelFlat_3_, c_1514_x p_renderModelFlat_4_, g_221_o p_renderModelFlat_5_, D_4792_h p_renderModelFlat_6_, boolean p_renderModelFlat_7_, Random p_renderModelFlat_8_, long p_renderModelFlat_9_, int p_renderModelFlat_11_, IModelData p_renderModelFlat_12_) {
        List<c_932_S> list1;
        boolean flag = false;
        RenderEnv renderenv = p_renderModelFlat_6_.n_1700_B(p_renderModelFlat_3_, p_renderModelFlat_4_);
        o_2576_A rendertype = p_renderModelFlat_6_.getRenderType();
        for (b_257_Y direction : b_257_Y.v_4262_N) {
            if (p_renderModelFlat_7_ && !BlockUtils.shouldSideBeRendered(p_renderModelFlat_3_, p_renderModelFlat_1_, p_renderModelFlat_4_, direction, renderenv)) continue;
            p_renderModelFlat_8_.setSeed(p_renderModelFlat_9_);
            List<c_932_S> list = this.v_4262_N ? p_renderModelFlat_2_.getQuads(p_renderModelFlat_3_, direction, p_renderModelFlat_8_, p_renderModelFlat_12_) : p_renderModelFlat_2_.n_1700_B(p_renderModelFlat_3_, direction, p_renderModelFlat_8_);
            int i = z_883_p.n_1700_B(p_renderModelFlat_1_, p_renderModelFlat_3_, p_renderModelFlat_4_.offset(direction));
            list = BlockModelCustomizer.getRenderQuads(list, p_renderModelFlat_1_, p_renderModelFlat_3_, p_renderModelFlat_4_, direction, rendertype, p_renderModelFlat_9_, renderenv);
            this.n_1700_B(p_renderModelFlat_1_, p_renderModelFlat_3_, p_renderModelFlat_4_, i, p_renderModelFlat_11_, false, p_renderModelFlat_5_, p_renderModelFlat_6_, list, renderenv);
            flag = true;
        }
        p_renderModelFlat_8_.setSeed(p_renderModelFlat_9_);
        List<c_932_S> list = list1 = this.v_4262_N ? p_renderModelFlat_2_.getQuads(p_renderModelFlat_3_, null, p_renderModelFlat_8_, p_renderModelFlat_12_) : p_renderModelFlat_2_.n_1700_B(p_renderModelFlat_3_, null, p_renderModelFlat_8_);
        if (!list1.isEmpty()) {
            list1 = BlockModelCustomizer.getRenderQuads(list1, p_renderModelFlat_1_, p_renderModelFlat_3_, p_renderModelFlat_4_, null, rendertype, p_renderModelFlat_9_, renderenv);
            this.n_1700_B(p_renderModelFlat_1_, p_renderModelFlat_3_, p_renderModelFlat_4_, -1, p_renderModelFlat_11_, true, p_renderModelFlat_5_, p_renderModelFlat_6_, list1, renderenv);
            flag = true;
        }
        return flag;
    }

    private void n_1700_B(BlockAndTintGetter p_renderQuadsSmooth_1_, K_4074_S p_renderQuadsSmooth_2_, c_1514_x p_renderQuadsSmooth_3_, g_221_o p_renderQuadsSmooth_4_, D_4792_h p_renderQuadsSmooth_5_, List<c_932_S> p_renderQuadsSmooth_6_, int p_renderQuadsSmooth_7_, RenderEnv p_renderQuadsSmooth_8_) {
        float[] afloat = p_renderQuadsSmooth_8_.getQuadBounds();
        BitSet bitset = p_renderQuadsSmooth_8_.getBoundsFlags();
        n_1700_B blockmodelrenderer$ambientocclusionface = p_renderQuadsSmooth_8_.getAoFace();
        int i = p_renderQuadsSmooth_6_.size();
        for (int j = 0; j < i; ++j) {
            c_932_S bakedquad = p_renderQuadsSmooth_6_.get(j);
            this.n_1700_B(p_renderQuadsSmooth_1_, p_renderQuadsSmooth_2_, p_renderQuadsSmooth_3_, bakedquad.getVertexData(), bakedquad.getFace(), afloat, bitset);
            blockmodelrenderer$ambientocclusionface.n_1700_B(p_renderQuadsSmooth_1_, p_renderQuadsSmooth_2_, p_renderQuadsSmooth_3_, bakedquad.getFace(), afloat, bitset, bakedquad.applyDiffuseLighting());
            if (bakedquad.getSprite().Q_4569_t) {
                blockmodelrenderer$ambientocclusionface.n_1700_B();
            }
            this.n_1700_B(p_renderQuadsSmooth_1_, p_renderQuadsSmooth_2_, p_renderQuadsSmooth_3_, p_renderQuadsSmooth_5_, p_renderQuadsSmooth_4_.R_4764_Y(), bakedquad, blockmodelrenderer$ambientocclusionface.n_1700_B[0], blockmodelrenderer$ambientocclusionface.n_1700_B[1], blockmodelrenderer$ambientocclusionface.n_1700_B[2], blockmodelrenderer$ambientocclusionface.n_1700_B[3], blockmodelrenderer$ambientocclusionface.J_1907_R[0], blockmodelrenderer$ambientocclusionface.J_1907_R[1], blockmodelrenderer$ambientocclusionface.J_1907_R[2], blockmodelrenderer$ambientocclusionface.J_1907_R[3], p_renderQuadsSmooth_7_, p_renderQuadsSmooth_8_);
        }
    }

    private void n_1700_B(BlockAndTintGetter p_renderQuadSmooth_1_, K_4074_S p_renderQuadSmooth_2_, c_1514_x p_renderQuadSmooth_3_, D_4792_h p_renderQuadSmooth_4_, g_221_o.n_1700_B p_renderQuadSmooth_5_, c_932_S p_renderQuadSmooth_6_, float p_renderQuadSmooth_7_, float p_renderQuadSmooth_8_, float p_renderQuadSmooth_9_, float p_renderQuadSmooth_10_, int p_renderQuadSmooth_11_, int p_renderQuadSmooth_12_, int p_renderQuadSmooth_13_, int p_renderQuadSmooth_14_, int p_renderQuadSmooth_15_, RenderEnv p_renderQuadSmooth_16_) {
        float f2;
        float f1;
        float f;
        int i = CustomColors.getColorMultiplier(p_renderQuadSmooth_6_, p_renderQuadSmooth_2_, p_renderQuadSmooth_1_, p_renderQuadSmooth_3_, p_renderQuadSmooth_16_);
        if (!p_renderQuadSmooth_6_.hasTintIndex() && i == -1) {
            f = 1.0f;
            f1 = 1.0f;
            f2 = 1.0f;
        } else {
            int j = i != -1 ? i : this.n_1700_B.n_1700_B(p_renderQuadSmooth_2_, p_renderQuadSmooth_1_, p_renderQuadSmooth_3_, p_renderQuadSmooth_6_.getTintIndex());
            f = (float)(j >> 16 & 0xFF) / 255.0f;
            f1 = (float)(j >> 8 & 0xFF) / 255.0f;
            f2 = (float)(j & 0xFF) / 255.0f;
        }
        p_renderQuadSmooth_4_.n_1700_B(p_renderQuadSmooth_5_, p_renderQuadSmooth_6_, p_renderQuadSmooth_4_.getTempFloat4(p_renderQuadSmooth_7_, p_renderQuadSmooth_8_, p_renderQuadSmooth_9_, p_renderQuadSmooth_10_), f, f1, f2, p_renderQuadSmooth_4_.getTempInt4(p_renderQuadSmooth_11_, p_renderQuadSmooth_12_, p_renderQuadSmooth_13_, p_renderQuadSmooth_14_), p_renderQuadSmooth_15_, true);
    }

    private void n_1700_B(BlockAndTintGetter blockReaderIn, K_4074_S stateIn, c_1514_x posIn, int[] vertexData, b_257_Y face, @Nullable float[] quadBounds, BitSet boundsFlags) {
        float f = 32.0f;
        float f1 = 32.0f;
        float f2 = 32.0f;
        float f3 = -32.0f;
        float f4 = -32.0f;
        float f5 = -32.0f;
        int i = vertexData.length / 4;
        for (int j = 0; j < 4; ++j) {
            float f6 = Float.intBitsToFloat(vertexData[j * i]);
            float f7 = Float.intBitsToFloat(vertexData[j * i + 1]);
            float f8 = Float.intBitsToFloat(vertexData[j * i + 2]);
            f = Math.min(f, f6);
            f1 = Math.min(f1, f7);
            f2 = Math.min(f2, f8);
            f3 = Math.max(f3, f6);
            f4 = Math.max(f4, f7);
            f5 = Math.max(f5, f8);
        }
        if (quadBounds != null) {
            quadBounds[b_257_Y.P_1922_E.R_4764_Y()] = f;
            quadBounds[b_257_Y.u_1723_Y.R_4764_Y()] = f3;
            quadBounds[b_257_Y.n_1700_B.R_4764_Y()] = f1;
            quadBounds[b_257_Y.J_1907_R.R_4764_Y()] = f4;
            quadBounds[b_257_Y.R_4764_Y.R_4764_Y()] = f2;
            quadBounds[b_257_Y.G_564_y.R_4764_Y()] = f5;
            int k = b_257_Y.v_4262_N.length;
            quadBounds[b_257_Y.P_1922_E.R_4764_Y() + k] = 1.0f - f;
            quadBounds[b_257_Y.u_1723_Y.R_4764_Y() + k] = 1.0f - f3;
            quadBounds[b_257_Y.n_1700_B.R_4764_Y() + k] = 1.0f - f1;
            quadBounds[b_257_Y.J_1907_R.R_4764_Y() + k] = 1.0f - f4;
            quadBounds[b_257_Y.R_4764_Y.R_4764_Y() + k] = 1.0f - f2;
            quadBounds[b_257_Y.G_564_y.R_4764_Y() + k] = 1.0f - f5;
        }
        float f9 = 1.0E-4f;
        float f10 = 0.9999f;
        switch (face) {
            case n_1700_B: {
                boundsFlags.set(1, f >= 1.0E-4f || f2 >= 1.0E-4f || f3 <= 0.9999f || f5 <= 0.9999f);
                boundsFlags.set(0, f1 == f4 && (f1 < 1.0E-4f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
                break;
            }
            case J_1907_R: {
                boundsFlags.set(1, f >= 1.0E-4f || f2 >= 1.0E-4f || f3 <= 0.9999f || f5 <= 0.9999f);
                boundsFlags.set(0, f1 == f4 && (f4 > 0.9999f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
                break;
            }
            case R_4764_Y: {
                boundsFlags.set(1, f >= 1.0E-4f || f1 >= 1.0E-4f || f3 <= 0.9999f || f4 <= 0.9999f);
                boundsFlags.set(0, f2 == f5 && (f2 < 1.0E-4f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
                break;
            }
            case G_564_y: {
                boundsFlags.set(1, f >= 1.0E-4f || f1 >= 1.0E-4f || f3 <= 0.9999f || f4 <= 0.9999f);
                boundsFlags.set(0, f2 == f5 && (f5 > 0.9999f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
                break;
            }
            case P_1922_E: {
                boundsFlags.set(1, f1 >= 1.0E-4f || f2 >= 1.0E-4f || f4 <= 0.9999f || f5 <= 0.9999f);
                boundsFlags.set(0, f == f3 && (f < 1.0E-4f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
                break;
            }
            case u_1723_Y: {
                boundsFlags.set(1, f1 >= 1.0E-4f || f2 >= 1.0E-4f || f4 <= 0.9999f || f5 <= 0.9999f);
                boundsFlags.set(0, f == f3 && (f3 > 0.9999f || stateIn.multiplayerClientSuggestionProvider(blockReaderIn, posIn)));
            }
        }
    }

    private void n_1700_B(BlockAndTintGetter p_renderQuadsFlat_1_, K_4074_S p_renderQuadsFlat_2_, c_1514_x p_renderQuadsFlat_3_, int p_renderQuadsFlat_4_, int p_renderQuadsFlat_5_, boolean p_renderQuadsFlat_6_, g_221_o p_renderQuadsFlat_7_, D_4792_h p_renderQuadsFlat_8_, List<c_932_S> p_renderQuadsFlat_9_, RenderEnv p_renderQuadsFlat_10_) {
        BitSet bitset = p_renderQuadsFlat_10_.getBoundsFlags();
        int i = p_renderQuadsFlat_9_.size();
        for (int j = 0; j < i; ++j) {
            c_932_S bakedquad = p_renderQuadsFlat_9_.get(j);
            if (p_renderQuadsFlat_6_) {
                this.n_1700_B(p_renderQuadsFlat_1_, p_renderQuadsFlat_2_, p_renderQuadsFlat_3_, bakedquad.getVertexData(), bakedquad.getFace(), null, bitset);
                c_1514_x blockpos = bitset.get(0) ? p_renderQuadsFlat_3_.offset(bakedquad.getFace()) : p_renderQuadsFlat_3_;
                p_renderQuadsFlat_4_ = z_883_p.n_1700_B(p_renderQuadsFlat_1_, p_renderQuadsFlat_2_, blockpos);
            }
            if (bakedquad.getSprite().Q_4569_t) {
                p_renderQuadsFlat_4_ = e_1689_x.n_1700_B;
            }
            float f = p_renderQuadsFlat_1_.func_230487_a_(bakedquad.getFace(), bakedquad.applyDiffuseLighting());
            this.n_1700_B(p_renderQuadsFlat_1_, p_renderQuadsFlat_2_, p_renderQuadsFlat_3_, p_renderQuadsFlat_8_, p_renderQuadsFlat_7_.R_4764_Y(), bakedquad, f, f, f, f, p_renderQuadsFlat_4_, p_renderQuadsFlat_4_, p_renderQuadsFlat_4_, p_renderQuadsFlat_4_, p_renderQuadsFlat_5_, p_renderQuadsFlat_10_);
        }
    }

    public void n_1700_B(g_221_o.n_1700_B matrixEntry, D_4792_h buffer, @Nullable K_4074_S state, S_3826_o modelIn, float red, float green, float blue, int combinedLightIn, int combinedOverlayIn) {
        this.n_1700_B(matrixEntry, buffer, state, modelIn, red, green, blue, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
    }

    public void n_1700_B(g_221_o.n_1700_B p_renderModel_1_, D_4792_h p_renderModel_2_, @Nullable K_4074_S p_renderModel_3_, S_3826_o p_renderModel_4_, float p_renderModel_5_, float p_renderModel_6_, float p_renderModel_7_, int p_renderModel_8_, int p_renderModel_9_, IModelData p_renderModel_10_) {
        Random random = new Random();
        long i = 42L;
        for (b_257_Y direction : b_257_Y.v_4262_N) {
            random.setSeed(42L);
            if (this.v_4262_N) {
                W_571_B.n_1700_B(p_renderModel_1_, p_renderModel_2_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_4_.getQuads(p_renderModel_3_, direction, random, p_renderModel_10_), p_renderModel_8_, p_renderModel_9_);
                continue;
            }
            W_571_B.n_1700_B(p_renderModel_1_, p_renderModel_2_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_4_.n_1700_B(p_renderModel_3_, direction, random), p_renderModel_8_, p_renderModel_9_);
        }
        random.setSeed(42L);
        if (this.v_4262_N) {
            W_571_B.n_1700_B(p_renderModel_1_, p_renderModel_2_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_4_.getQuads(p_renderModel_3_, null, random, p_renderModel_10_), p_renderModel_8_, p_renderModel_9_);
        } else {
            W_571_B.n_1700_B(p_renderModel_1_, p_renderModel_2_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_4_.n_1700_B(p_renderModel_3_, null, random), p_renderModel_8_, p_renderModel_9_);
        }
    }

    private static void n_1700_B(g_221_o.n_1700_B matrixEntry, D_4792_h buffer, float red, float green, float blue, List<c_932_S> listQuads, int combinedLightIn, int combinedOverlayIn) {
        boolean flag = EmissiveTextures.isActive();
        Iterator<c_932_S> iterator = listQuads.iterator();
        while (iterator.hasNext()) {
            float f2;
            float f1;
            float f;
            c_932_S bakedquad = iterator.next();
            if (flag && (bakedquad = EmissiveTextures.getEmissiveQuad(bakedquad)) == null) continue;
            if (bakedquad.hasTintIndex()) {
                f = u_530_F.n_1700_B(red, 0.0f, 1.0f);
                f1 = u_530_F.n_1700_B(green, 0.0f, 1.0f);
                f2 = u_530_F.n_1700_B(blue, 0.0f, 1.0f);
            } else {
                f = 1.0f;
                f1 = 1.0f;
                f2 = 1.0f;
            }
            buffer.n_1700_B(matrixEntry, bakedquad, f, f1, f2, combinedLightIn, combinedOverlayIn);
        }
        return;
    }

    public static void n_1700_B() {
        J_1907_R.get().n_1700_B();
    }

    public static void J_1907_R() {
        J_1907_R.get().J_1907_R();
    }

    public static float n_1700_B(float p_fixAoLightValue_0_) {
        return p_fixAoLightValue_0_ == 0.2f ? R_4764_Y : p_fixAoLightValue_0_;
    }

    public static void R_4764_Y() {
        R_4764_Y = 1.0f - Config.getAmbientOcclusionLevel() * 0.8f;
        G_564_y = Config.isShaders() && Shaders.isSeparateAo();
    }

    public static boolean G_564_y() {
        return G_564_y;
    }

    private void n_1700_B(BlockAndTintGetter p_renderOverlayModels_1_, S_3826_o p_renderOverlayModels_2_, K_4074_S p_renderOverlayModels_3_, c_1514_x p_renderOverlayModels_4_, g_221_o p_renderOverlayModels_5_, D_4792_h p_renderOverlayModels_6_, int p_renderOverlayModels_7_, boolean p_renderOverlayModels_8_, Random p_renderOverlayModels_9_, long p_renderOverlayModels_10_, RenderEnv p_renderOverlayModels_12_, boolean p_renderOverlayModels_13_, e_2866_D p_renderOverlayModels_14_) {
        if (p_renderOverlayModels_12_.isOverlaysRendered()) {
            for (int i = 0; i < u_1723_Y.length; ++i) {
                o_2576_A rendertype = u_1723_Y[i];
                ListQuadsOverlay listquadsoverlay = p_renderOverlayModels_12_.getListQuadsOverlay(rendertype);
                if (listquadsoverlay.size() <= 0) continue;
                ChunkBufferBuilderPack regionrendercachebuilder = p_renderOverlayModels_12_.getRegionRenderCacheBuilder();
                if (regionrendercachebuilder != null) {
                    D_3318_r bufferbuilder = regionrendercachebuilder.n_1700_B(rendertype);
                    if (!bufferbuilder.s_956_w()) {
                        bufferbuilder.n_1700_B(7, E_688_b.w_1484_f);
                    }
                    for (int j = 0; j < listquadsoverlay.size(); ++j) {
                        c_932_S bakedquad = listquadsoverlay.getQuad(j);
                        List<c_932_S> list = listquadsoverlay.getListQuadsSingle(bakedquad);
                        K_4074_S blockstate = listquadsoverlay.getBlockState(j);
                        if (bakedquad.getQuadEmissive() != null) {
                            listquadsoverlay.addQuad(bakedquad.getQuadEmissive(), blockstate);
                        }
                        p_renderOverlayModels_12_.reset(blockstate, p_renderOverlayModels_4_);
                        if (p_renderOverlayModels_13_) {
                            this.n_1700_B(p_renderOverlayModels_1_, blockstate, p_renderOverlayModels_4_, p_renderOverlayModels_5_, bufferbuilder, list, p_renderOverlayModels_7_, p_renderOverlayModels_12_);
                            continue;
                        }
                        int k = z_883_p.n_1700_B(p_renderOverlayModels_1_, blockstate, p_renderOverlayModels_4_.offset(bakedquad.getFace()));
                        this.n_1700_B(p_renderOverlayModels_1_, blockstate, p_renderOverlayModels_4_, k, p_renderOverlayModels_7_, false, p_renderOverlayModels_5_, bufferbuilder, list, p_renderOverlayModels_12_);
                    }
                }
                listquadsoverlay.clear();
            }
        }
        if (Config.isBetterSnow() && !p_renderOverlayModels_12_.isBreakingAnimation() && BetterSnow.shouldRender(p_renderOverlayModels_1_, p_renderOverlayModels_3_, p_renderOverlayModels_4_)) {
            S_3826_o ibakedmodel = BetterSnow.getModelSnowLayer();
            K_4074_S blockstate1 = BetterSnow.getStateSnowLayer();
            p_renderOverlayModels_5_.n_1700_B(-p_renderOverlayModels_14_.J_1907_R, -p_renderOverlayModels_14_.R_4764_Y, -p_renderOverlayModels_14_.G_564_y);
            this.n_1700_B(p_renderOverlayModels_1_, ibakedmodel, blockstate1, p_renderOverlayModels_4_, p_renderOverlayModels_5_, p_renderOverlayModels_6_, p_renderOverlayModels_8_, p_renderOverlayModels_9_, p_renderOverlayModels_10_, p_renderOverlayModels_7_);
        }
    }

    public static class n_1700_B {
        private final float[] n_1700_B = new float[4];
        private final int[] J_1907_R = new int[4];
        private BlockPosM R_4764_Y = new BlockPosM();

        public n_1700_B() {
            this(null);
        }

        public n_1700_B(W_571_B p_i46235_1_) {
        }

        public void n_1700_B() {
            int i;
            this.J_1907_R[0] = i = e_1689_x.n_1700_B;
            this.J_1907_R[1] = i;
            this.J_1907_R[2] = i;
            this.J_1907_R[3] = i;
            this.n_1700_B[0] = 1.0f;
            this.n_1700_B[1] = 1.0f;
            this.n_1700_B[2] = 1.0f;
            this.n_1700_B[3] = 1.0f;
        }

        public void n_1700_B(BlockAndTintGetter reader, K_4074_S state, c_1514_x pos, b_257_Y direction, float[] vertexes, BitSet bitSet, boolean applyDiffuseLighting) {
            int l1;
            float f28;
            int k1;
            float f27;
            int j1;
            float f26;
            int i1;
            float f4;
            boolean flag3;
            c_1514_x blockpos = bitSet.get(0) ? pos.offset(direction) : pos;
            R_4764_Y blockmodelrenderer$neighborinfo = lightning.product.W_571_B$R_4764_Y.n_1700_B(direction);
            BlockPosM blockposm = this.R_4764_Y;
            LightCacheOF lightcacheof = P_1922_E;
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[0]);
            K_4074_S blockstate = reader.getBlockState(blockposm);
            int i = LightCacheOF.getPackedLight(blockstate, reader, blockposm);
            float f = LightCacheOF.getBrightness(blockstate, reader, blockposm);
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[1]);
            K_4074_S blockstate1 = reader.getBlockState(blockposm);
            int j = LightCacheOF.getPackedLight(blockstate1, reader, blockposm);
            float f1 = LightCacheOF.getBrightness(blockstate1, reader, blockposm);
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[2]);
            K_4074_S blockstate2 = reader.getBlockState(blockposm);
            int k = LightCacheOF.getPackedLight(blockstate2, reader, blockposm);
            float f2 = LightCacheOF.getBrightness(blockstate2, reader, blockposm);
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[3]);
            K_4074_S blockstate3 = reader.getBlockState(blockposm);
            int l = LightCacheOF.getPackedLight(blockstate3, reader, blockposm);
            float f3 = LightCacheOF.getBrightness(blockstate3, reader, blockposm);
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[0], direction);
            boolean flag = reader.getBlockState(blockposm).J_1907_R(reader, (c_1514_x)blockposm) == 0;
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[1], direction);
            boolean flag1 = reader.getBlockState(blockposm).J_1907_R(reader, (c_1514_x)blockposm) == 0;
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[2], direction);
            boolean flag2 = reader.getBlockState(blockposm).J_1907_R(reader, (c_1514_x)blockposm) == 0;
            blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[3], direction);
            boolean bl = flag3 = reader.getBlockState(blockposm).J_1907_R(reader, (c_1514_x)blockposm) == 0;
            if (!flag2 && !flag) {
                f4 = f;
                i1 = i;
            } else {
                blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[0], blockmodelrenderer$neighborinfo.v_4262_N[2]);
                K_4074_S blockstate4 = reader.getBlockState(blockposm);
                f4 = LightCacheOF.getBrightness(blockstate4, reader, blockposm);
                i1 = LightCacheOF.getPackedLight(blockstate4, reader, blockposm);
            }
            if (!flag3 && !flag) {
                f26 = f;
                j1 = i;
            } else {
                blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[0], blockmodelrenderer$neighborinfo.v_4262_N[3]);
                K_4074_S blockstate5 = reader.getBlockState(blockposm);
                f26 = LightCacheOF.getBrightness(blockstate5, reader, blockposm);
                j1 = LightCacheOF.getPackedLight(blockstate5, reader, blockposm);
            }
            if (!flag2 && !flag1) {
                f27 = f;
                k1 = i;
            } else {
                blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[1], blockmodelrenderer$neighborinfo.v_4262_N[2]);
                K_4074_S blockstate6 = reader.getBlockState(blockposm);
                f27 = LightCacheOF.getBrightness(blockstate6, reader, blockposm);
                k1 = LightCacheOF.getPackedLight(blockstate6, reader, blockposm);
            }
            if (!flag3 && !flag1) {
                f28 = f;
                l1 = i;
            } else {
                blockposm.setPosOffset(blockpos, blockmodelrenderer$neighborinfo.v_4262_N[1], blockmodelrenderer$neighborinfo.v_4262_N[3]);
                K_4074_S blockstate7 = reader.getBlockState(blockposm);
                f28 = LightCacheOF.getBrightness(blockstate7, reader, blockposm);
                l1 = LightCacheOF.getPackedLight(blockstate7, reader, blockposm);
            }
            int i3 = LightCacheOF.getPackedLight(state, reader, pos);
            blockposm.setPosOffset(pos, direction);
            K_4074_S blockstate8 = reader.getBlockState(blockposm);
            if (bitSet.get(0) || !blockstate8.t_148_a(reader, blockposm)) {
                i3 = LightCacheOF.getPackedLight(blockstate8, reader, blockposm);
            }
            float f5 = bitSet.get(0) ? LightCacheOF.getBrightness(reader.getBlockState(blockpos), reader, blockpos) : LightCacheOF.getBrightness(reader.getBlockState(pos), reader, pos);
            P_1922_E blockmodelrenderer$vertextranslations = lightning.product.W_571_B$P_1922_E.n_1700_B(direction);
            if (bitSet.get(1) && blockmodelrenderer$neighborinfo.w_1484_f) {
                float f29 = (f3 + f + f26 + f5) * 0.25f;
                float f31 = (f2 + f + f4 + f5) * 0.25f;
                float f32 = (f2 + f1 + f27 + f5) * 0.25f;
                float f33 = (f3 + f1 + f28 + f5) * 0.25f;
                float f10 = vertexes[blockmodelrenderer$neighborinfo.t_148_a[0].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.t_148_a[1].P_4830_p];
                float f11 = vertexes[blockmodelrenderer$neighborinfo.t_148_a[2].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.t_148_a[3].P_4830_p];
                float f12 = vertexes[blockmodelrenderer$neighborinfo.t_148_a[4].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.t_148_a[5].P_4830_p];
                float f13 = vertexes[blockmodelrenderer$neighborinfo.t_148_a[6].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.t_148_a[7].P_4830_p];
                float f14 = vertexes[blockmodelrenderer$neighborinfo.s_956_w[0].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.s_956_w[1].P_4830_p];
                float f15 = vertexes[blockmodelrenderer$neighborinfo.s_956_w[2].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.s_956_w[3].P_4830_p];
                float f16 = vertexes[blockmodelrenderer$neighborinfo.s_956_w[4].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.s_956_w[5].P_4830_p];
                float f17 = vertexes[blockmodelrenderer$neighborinfo.s_956_w[6].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.s_956_w[7].P_4830_p];
                float f18 = vertexes[blockmodelrenderer$neighborinfo.u_2550_I[0].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.u_2550_I[1].P_4830_p];
                float f19 = vertexes[blockmodelrenderer$neighborinfo.u_2550_I[2].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.u_2550_I[3].P_4830_p];
                float f20 = vertexes[blockmodelrenderer$neighborinfo.u_2550_I[4].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.u_2550_I[5].P_4830_p];
                float f21 = vertexes[blockmodelrenderer$neighborinfo.u_2550_I[6].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.u_2550_I[7].P_4830_p];
                float f22 = vertexes[blockmodelrenderer$neighborinfo.M_588_G[0].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.M_588_G[1].P_4830_p];
                float f23 = vertexes[blockmodelrenderer$neighborinfo.M_588_G[2].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.M_588_G[3].P_4830_p];
                float f24 = vertexes[blockmodelrenderer$neighborinfo.M_588_G[4].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.M_588_G[5].P_4830_p];
                float f25 = vertexes[blockmodelrenderer$neighborinfo.M_588_G[6].P_4830_p] * vertexes[blockmodelrenderer$neighborinfo.M_588_G[7].P_4830_p];
                this.n_1700_B[blockmodelrenderer$vertextranslations.v_4262_N] = f29 * f10 + f31 * f11 + f32 * f12 + f33 * f13;
                this.n_1700_B[blockmodelrenderer$vertextranslations.w_1484_f] = f29 * f14 + f31 * f15 + f32 * f16 + f33 * f17;
                this.n_1700_B[blockmodelrenderer$vertextranslations.t_148_a] = f29 * f18 + f31 * f19 + f32 * f20 + f33 * f21;
                this.n_1700_B[blockmodelrenderer$vertextranslations.s_956_w] = f29 * f22 + f31 * f23 + f32 * f24 + f33 * f25;
                int i2 = this.n_1700_B(l, i, j1, i3);
                int j2 = this.n_1700_B(k, i, i1, i3);
                int k2 = this.n_1700_B(k, j, k1, i3);
                int l2 = this.n_1700_B(l, j, l1, i3);
                this.J_1907_R[blockmodelrenderer$vertextranslations.v_4262_N] = this.n_1700_B(i2, j2, k2, l2, f10, f11, f12, f13);
                this.J_1907_R[blockmodelrenderer$vertextranslations.w_1484_f] = this.n_1700_B(i2, j2, k2, l2, f14, f15, f16, f17);
                this.J_1907_R[blockmodelrenderer$vertextranslations.t_148_a] = this.n_1700_B(i2, j2, k2, l2, f18, f19, f20, f21);
                this.J_1907_R[blockmodelrenderer$vertextranslations.s_956_w] = this.n_1700_B(i2, j2, k2, l2, f22, f23, f24, f25);
            } else {
                float f6 = (f3 + f + f26 + f5) * 0.25f;
                float f7 = (f2 + f + f4 + f5) * 0.25f;
                float f8 = (f2 + f1 + f27 + f5) * 0.25f;
                float f9 = (f3 + f1 + f28 + f5) * 0.25f;
                this.J_1907_R[blockmodelrenderer$vertextranslations.v_4262_N] = this.n_1700_B(l, i, j1, i3);
                this.J_1907_R[blockmodelrenderer$vertextranslations.w_1484_f] = this.n_1700_B(k, i, i1, i3);
                this.J_1907_R[blockmodelrenderer$vertextranslations.t_148_a] = this.n_1700_B(k, j, k1, i3);
                this.J_1907_R[blockmodelrenderer$vertextranslations.s_956_w] = this.n_1700_B(l, j, l1, i3);
                this.n_1700_B[blockmodelrenderer$vertextranslations.v_4262_N] = f6;
                this.n_1700_B[blockmodelrenderer$vertextranslations.w_1484_f] = f7;
                this.n_1700_B[blockmodelrenderer$vertextranslations.t_148_a] = f8;
                this.n_1700_B[blockmodelrenderer$vertextranslations.s_956_w] = f9;
            }
            float f30 = reader.func_230487_a_(direction, applyDiffuseLighting);
            int j3 = 0;
            while (j3 < this.n_1700_B.length) {
                int n = j3++;
                this.n_1700_B[n] = this.n_1700_B[n] * f30;
            }
        }

        private int n_1700_B(int br1, int br2, int br3, int br4) {
            if (br1 == 0) {
                br1 = br4;
            }
            if (br2 == 0) {
                br2 = br4;
            }
            if (br3 == 0) {
                br3 = br4;
            }
            return br1 + br2 + br3 + br4 >> 2 & 0xFF00FF;
        }

        private int n_1700_B(int b1, int b2, int b3, int b4, float w1, float w2, float w3, float w4) {
            int i = (int)((float)(b1 >> 16 & 0xFF) * w1 + (float)(b2 >> 16 & 0xFF) * w2 + (float)(b3 >> 16 & 0xFF) * w3 + (float)(b4 >> 16 & 0xFF) * w4) & 0xFF;
            int j = (int)((float)(b1 & 0xFF) * w1 + (float)(b2 & 0xFF) * w2 + (float)(b3 & 0xFF) * w3 + (float)(b4 & 0xFF) * w4) & 0xFF;
            return i << 16 | j;
        }
    }

    static class J_1907_R {
        private boolean n_1700_B;
        private final Long2IntLinkedOpenHashMap J_1907_R = j_3341_s.n_1700_B(() -> {
            Long2IntLinkedOpenHashMap long2intlinkedopenhashmap = new Long2IntLinkedOpenHashMap(100, 0.25f){

                protected void rehash(int p_rehash_1_) {
                }
            };
            long2intlinkedopenhashmap.defaultReturnValue(Integer.MAX_VALUE);
            return long2intlinkedopenhashmap;
        });
        private final Long2FloatLinkedOpenHashMap R_4764_Y = j_3341_s.n_1700_B(() -> {
            Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap = new Long2FloatLinkedOpenHashMap(100, 0.25f){

                protected void rehash(int p_rehash_1_) {
                }
            };
            long2floatlinkedopenhashmap.defaultReturnValue(Float.NaN);
            return long2floatlinkedopenhashmap;
        });

        private J_1907_R() {
        }

        public void n_1700_B() {
            this.n_1700_B = true;
        }

        public void J_1907_R() {
            this.n_1700_B = false;
            this.J_1907_R.clear();
            this.R_4764_Y.clear();
        }

        public int n_1700_B(K_4074_S blockStateIn, BlockAndTintGetter lightReaderIn, c_1514_x blockPosIn) {
            int j;
            long i = blockPosIn.toLong();
            if (this.n_1700_B && (j = this.J_1907_R.get(i)) != Integer.MAX_VALUE) {
                return j;
            }
            int k = z_883_p.n_1700_B(lightReaderIn, blockStateIn, blockPosIn);
            if (this.n_1700_B) {
                if (this.J_1907_R.size() == 100) {
                    this.J_1907_R.removeFirstInt();
                }
                this.J_1907_R.put(i, k);
            }
            return k;
        }

        public float J_1907_R(K_4074_S blockStateIn, BlockAndTintGetter lightReaderIn, c_1514_x blockPosIn) {
            float f;
            long i = blockPosIn.toLong();
            if (this.n_1700_B && !Float.isNaN(f = this.R_4764_Y.get(i))) {
                return f;
            }
            float f1 = blockStateIn.u_1723_Y(lightReaderIn, blockPosIn);
            if (this.n_1700_B) {
                if (this.R_4764_Y.size() == 100) {
                    this.R_4764_Y.removeFirstFloat();
                }
                this.R_4764_Y.put(i, f1);
            }
            return f1;
        }
    }

    static final class P_1922_E
    extends Enum<P_1922_E> {
        public static final /* enum */ P_1922_E n_1700_B = new P_1922_E(0, 1, 2, 3);
        public static final /* enum */ P_1922_E J_1907_R = new P_1922_E(2, 3, 0, 1);
        public static final /* enum */ P_1922_E R_4764_Y = new P_1922_E(3, 0, 1, 2);
        public static final /* enum */ P_1922_E G_564_y = new P_1922_E(0, 1, 2, 3);
        public static final /* enum */ P_1922_E P_1922_E = new P_1922_E(3, 0, 1, 2);
        public static final /* enum */ P_1922_E u_1723_Y = new P_1922_E(1, 2, 3, 0);
        private final int v_4262_N;
        private final int w_1484_f;
        private final int t_148_a;
        private final int s_956_w;
        private static final P_1922_E[] u_2550_I;
        private static final /* synthetic */ P_1922_E[] M_588_G;

        public static P_1922_E[] values() {
            return (P_1922_E[])M_588_G.clone();
        }

        public static P_1922_E valueOf(String name) {
            return Enum.valueOf(P_1922_E.class, name);
        }

        private P_1922_E(int vert0In, int vert1In, int vert2In, int vert3In) {
            this.v_4262_N = vert0In;
            this.w_1484_f = vert1In;
            this.t_148_a = vert2In;
            this.s_956_w = vert3In;
        }

        public static P_1922_E n_1700_B(b_257_Y facingIn) {
            return u_2550_I[facingIn.R_4764_Y()];
        }

        private static /* synthetic */ P_1922_E[] n_1700_B() {
            return new P_1922_E[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            M_588_G = lightning.product.W_571_B$P_1922_E.n_1700_B();
            u_2550_I = j_3341_s.n_1700_B(new P_1922_E[6], p_lambda$static$0_0_ -> {
                p_lambda$static$0_0_[b_257_Y.n_1700_B.R_4764_Y()] = n_1700_B;
                p_lambda$static$0_0_[b_257_Y.J_1907_R.R_4764_Y()] = J_1907_R;
                p_lambda$static$0_0_[b_257_Y.R_4764_Y.R_4764_Y()] = R_4764_Y;
                p_lambda$static$0_0_[b_257_Y.G_564_y.R_4764_Y()] = G_564_y;
                p_lambda$static$0_0_[b_257_Y.P_1922_E.R_4764_Y()] = P_1922_E;
                p_lambda$static$0_0_[b_257_Y.u_1723_Y.R_4764_Y()] = u_1723_Y;
            });
        }
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y(b_257_Y.n_1700_B, false);
        public static final /* enum */ G_564_y J_1907_R = new G_564_y(b_257_Y.J_1907_R, false);
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y(b_257_Y.R_4764_Y, false);
        public static final /* enum */ G_564_y G_564_y = new G_564_y(b_257_Y.G_564_y, false);
        public static final /* enum */ G_564_y P_1922_E = new G_564_y(b_257_Y.P_1922_E, false);
        public static final /* enum */ G_564_y u_1723_Y = new G_564_y(b_257_Y.u_1723_Y, false);
        public static final /* enum */ G_564_y v_4262_N = new G_564_y(b_257_Y.n_1700_B, true);
        public static final /* enum */ G_564_y w_1484_f = new G_564_y(b_257_Y.J_1907_R, true);
        public static final /* enum */ G_564_y t_148_a = new G_564_y(b_257_Y.R_4764_Y, true);
        public static final /* enum */ G_564_y s_956_w = new G_564_y(b_257_Y.G_564_y, true);
        public static final /* enum */ G_564_y u_2550_I = new G_564_y(b_257_Y.P_1922_E, true);
        public static final /* enum */ G_564_y M_588_G = new G_564_y(b_257_Y.u_1723_Y, true);
        private final int P_4830_p;
        private static final /* synthetic */ G_564_y[] h_1847_R;

        public static G_564_y[] values() {
            return (G_564_y[])h_1847_R.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private G_564_y(b_257_Y facingIn, boolean flip) {
            this.P_4830_p = facingIn.R_4764_Y() + (flip ? b_257_Y.values().length : 0);
        }

        private static /* synthetic */ G_564_y[] n_1700_B() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G};
        }

        static {
            h_1847_R = lightning.product.W_571_B$G_564_y.n_1700_B();
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y(new b_257_Y[]{b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.R_4764_Y, b_257_Y.G_564_y}, 0.5f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.G_564_y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.G_564_y});
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y(new b_257_Y[]{b_257_Y.u_1723_Y, b_257_Y.P_1922_E, b_257_Y.R_4764_Y, b_257_Y.G_564_y}, 1.0f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.G_564_y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.G_564_y});
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y(new b_257_Y[]{b_257_Y.J_1907_R, b_257_Y.n_1700_B, b_257_Y.u_1723_Y, b_257_Y.P_1922_E}, 0.8f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.u_2550_I}, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.M_588_G}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.M_588_G}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.u_2550_I});
        public static final /* enum */ R_4764_Y G_564_y = new R_4764_Y(new b_257_Y[]{b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.n_1700_B, b_257_Y.J_1907_R}, 0.8f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.P_1922_E}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.u_2550_I, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.P_1922_E, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.P_1922_E}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.u_1723_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.M_588_G, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.u_1723_Y, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.u_1723_Y});
        public static final /* enum */ R_4764_Y P_1922_E = new R_4764_Y(new b_257_Y[]{b_257_Y.J_1907_R, b_257_Y.n_1700_B, b_257_Y.R_4764_Y, b_257_Y.G_564_y}, 0.6f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.G_564_y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.G_564_y});
        public static final /* enum */ R_4764_Y u_1723_Y = new R_4764_Y(new b_257_Y[]{b_257_Y.n_1700_B, b_257_Y.J_1907_R, b_257_Y.R_4764_Y, b_257_Y.G_564_y}, 0.6f, true, new G_564_y[]{lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.G_564_y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.v_4262_N, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.n_1700_B, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.R_4764_Y, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.t_148_a, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.R_4764_Y}, new G_564_y[]{lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.G_564_y, lightning.product.W_571_B$G_564_y.w_1484_f, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.s_956_w, lightning.product.W_571_B$G_564_y.J_1907_R, lightning.product.W_571_B$G_564_y.G_564_y});
        private final b_257_Y[] v_4262_N;
        private final boolean w_1484_f;
        private final G_564_y[] t_148_a;
        private final G_564_y[] s_956_w;
        private final G_564_y[] u_2550_I;
        private final G_564_y[] M_588_G;
        private static final R_4764_Y[] P_4830_p;
        private static final /* synthetic */ R_4764_Y[] h_1847_R;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])h_1847_R.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private R_4764_Y(b_257_Y[] cornersIn, float brightness, boolean doNonCubicWeightIn, G_564_y[] vert0WeightsIn, G_564_y[] vert1WeightsIn, G_564_y[] vert2WeightsIn, G_564_y[] vert3WeightsIn) {
            this.v_4262_N = cornersIn;
            this.w_1484_f = doNonCubicWeightIn;
            this.t_148_a = vert0WeightsIn;
            this.s_956_w = vert1WeightsIn;
            this.u_2550_I = vert2WeightsIn;
            this.M_588_G = vert3WeightsIn;
        }

        public static R_4764_Y n_1700_B(b_257_Y facing) {
            return P_4830_p[facing.R_4764_Y()];
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            h_1847_R = lightning.product.W_571_B$R_4764_Y.n_1700_B();
            P_4830_p = j_3341_s.n_1700_B(new R_4764_Y[6], p_lambda$static$0_0_ -> {
                p_lambda$static$0_0_[b_257_Y.n_1700_B.R_4764_Y()] = n_1700_B;
                p_lambda$static$0_0_[b_257_Y.J_1907_R.R_4764_Y()] = J_1907_R;
                p_lambda$static$0_0_[b_257_Y.R_4764_Y.R_4764_Y()] = R_4764_Y;
                p_lambda$static$0_0_[b_257_Y.G_564_y.R_4764_Y()] = G_564_y;
                p_lambda$static$0_0_[b_257_Y.P_1922_E.R_4764_Y()] = P_1922_E;
                p_lambda$static$0_0_[b_257_Y.u_1723_Y.R_4764_Y()] = u_1723_Y;
            });
        }
    }
}



