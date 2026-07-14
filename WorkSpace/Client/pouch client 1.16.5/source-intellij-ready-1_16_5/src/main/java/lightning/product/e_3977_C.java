/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_4115_X;
import lightning.product.D_4792_h;
import lightning.product.E_3601_d;
import lightning.product.ItemTransforms;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.R_4273_N;
import lightning.product.ResourceManager;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.W_3959_H;
import lightning.product.W_571_B;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.d_1620_j;
import lightning.product.g_221_o;
import lightning.product.h_3270_j;
import lightning.product.k_4467_X;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.o_3091_w;
import lightning.product.CrashReportCategory;
import lightning.product.BlockModelShaper;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;
import net.optifine.reflect.Reflector;

public class e_3977_C
implements ResourceManagerReloadListener {
    private final BlockModelShaper n_1700_B;
    private final W_571_B J_1907_R;
    private final R_4273_N R_4764_Y;
    private final Random G_564_y = new Random();
    private final k_4467_X P_1922_E;

    public e_3977_C(BlockModelShaper shapes, k_4467_X colors) {
        this.n_1700_B = shapes;
        this.P_1922_E = colors;
        this.J_1907_R = Reflector.ForgeBlockModelRenderer_Constructor.exists() ? (W_571_B)Reflector.newInstance(Reflector.ForgeBlockModelRenderer_Constructor, this.P_1922_E) : new W_571_B(this.P_1922_E);
        this.R_4764_Y = new R_4273_N();
    }

    public BlockModelShaper J_1907_R() {
        return this.n_1700_B;
    }

    public void n_1700_B(K_4074_S blockStateIn, c_1514_x posIn, BlockAndTintGetter lightReaderIn, g_221_o matrixStackIn, D_4792_h vertexBuilderIn) {
        this.n_1700_B(blockStateIn, posIn, lightReaderIn, matrixStackIn, vertexBuilderIn, (IModelData)EmptyModelData.INSTANCE);
    }

    public void n_1700_B(K_4074_S p_renderBlockDamage_1_, c_1514_x p_renderBlockDamage_2_, BlockAndTintGetter p_renderBlockDamage_3_, g_221_o p_renderBlockDamage_4_, D_4792_h p_renderBlockDamage_5_, IModelData p_renderBlockDamage_6_) {
        if (p_renderBlockDamage_1_.w_1484_f() == O_2369_F.R_4764_Y) {
            S_3826_o ibakedmodel = this.n_1700_B.J_1907_R(p_renderBlockDamage_1_);
            long i = p_renderBlockDamage_1_.n_1700_B(p_renderBlockDamage_2_);
            this.J_1907_R.n_1700_B(p_renderBlockDamage_3_, ibakedmodel, p_renderBlockDamage_1_, p_renderBlockDamage_2_, p_renderBlockDamage_4_, p_renderBlockDamage_5_, true, this.G_564_y, i, Z_3224_L.n_1700_B, p_renderBlockDamage_6_);
        }
    }

    public boolean n_1700_B(K_4074_S blockStateIn, c_1514_x posIn, BlockAndTintGetter lightReaderIn, g_221_o matrixStackIn, D_4792_h vertexBuilderIn, boolean checkSides, Random rand) {
        return this.n_1700_B(blockStateIn, posIn, lightReaderIn, matrixStackIn, vertexBuilderIn, checkSides, rand, EmptyModelData.INSTANCE);
    }

    public boolean n_1700_B(K_4074_S p_renderModel_1_, c_1514_x p_renderModel_2_, BlockAndTintGetter p_renderModel_3_, g_221_o p_renderModel_4_, D_4792_h p_renderModel_5_, boolean p_renderModel_6_, Random p_renderModel_7_, IModelData p_renderModel_8_) {
        T_2915_h blockType = p_renderModel_1_.J_1907_R();
        if (blockType instanceof E_3601_d || blockType == a_3742_W.Party || blockType == a_3742_W.PotionTracker) {
            h_3270_j grassEvent = new h_3270_j(h_3270_j.n_1700_B.Q_4569_t);
            A_4115_X.n_1700_B(grassEvent);
            if (grassEvent.n_1700_B()) {
                return false;
            }
        }
        try {
            O_2369_F blockrendertype = p_renderModel_1_.w_1484_f();
            return blockrendertype != O_2369_F.R_4764_Y ? false : this.J_1907_R.n_1700_B(p_renderModel_3_, this.n_1700_B(p_renderModel_1_), p_renderModel_1_, p_renderModel_2_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, p_renderModel_1_.n_1700_B(p_renderModel_2_), Z_3224_L.n_1700_B, p_renderModel_8_);
        }
        catch (Throwable throwable1) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Tesselating block in world");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being tesselated");
            CrashReportCategory.n_1700_B(crashreportcategory, p_renderModel_2_, p_renderModel_1_);
            throw new ReportedException(crashreport);
        }
    }

    public boolean n_1700_B(c_1514_x posIn, BlockAndTintGetter lightReaderIn, D_4792_h vertexBuilderIn, FluidState fluidStateIn) {
        try {
            return this.R_4764_Y.n_1700_B(lightReaderIn, posIn, vertexBuilderIn, fluidStateIn);
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Tesselating liquid in world");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being tesselated");
            CrashReportCategory.n_1700_B(crashreportcategory, posIn, null);
            throw new ReportedException(crashreport);
        }
    }

    public W_571_B R_4764_Y() {
        return this.J_1907_R;
    }

    public S_3826_o n_1700_B(K_4074_S state) {
        return this.n_1700_B.J_1907_R(state);
    }

    public void n_1700_B(K_4074_S blockStateIn, g_221_o matrixStackIn, o_3091_w bufferTypeIn, int combinedLightIn, int combinedOverlayIn) {
        this.n_1700_B(blockStateIn, matrixStackIn, bufferTypeIn, combinedLightIn, combinedOverlayIn, (IModelData)EmptyModelData.INSTANCE);
    }

    public void n_1700_B(K_4074_S p_renderBlock_1_, g_221_o p_renderBlock_2_, o_3091_w p_renderBlock_3_, int p_renderBlock_4_, int p_renderBlock_5_, IModelData p_renderBlock_6_) {
        O_2369_F blockrendertype = p_renderBlock_1_.w_1484_f();
        if (blockrendertype != O_2369_F.n_1700_B) {
            switch (blockrendertype) {
                case R_4764_Y: {
                    S_3826_o ibakedmodel = this.n_1700_B(p_renderBlock_1_);
                    int i = this.P_1922_E.n_1700_B(p_renderBlock_1_, (BlockAndTintGetter)null, (c_1514_x)null, 0);
                    float f = (float)(i >> 16 & 0xFF) / 255.0f;
                    float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                    float f2 = (float)(i & 0xFF) / 255.0f;
                    this.J_1907_R.n_1700_B(p_renderBlock_2_.R_4764_Y(), p_renderBlock_3_.getBuffer(d_1620_j.n_1700_B(p_renderBlock_1_, false)), p_renderBlock_1_, ibakedmodel, f, f1, f2, p_renderBlock_4_, p_renderBlock_5_, p_renderBlock_6_);
                    break;
                }
                case J_1907_R: {
                    if (Reflector.IForgeItem_getItemStackTileEntityRenderer.exists()) {
                        Z_1993_T itemstack = new Z_1993_T(p_renderBlock_1_.J_1907_R());
                        W_3959_H itemstacktileentityrenderer = (W_3959_H)Reflector.call(itemstack.J_1907_R(), Reflector.IForgeItem_getItemStackTileEntityRenderer, new Object[0]);
                        itemstacktileentityrenderer.n_1700_B(itemstack, ItemTransforms.J_1907_R.n_1700_B, p_renderBlock_2_, p_renderBlock_3_, p_renderBlock_4_, p_renderBlock_5_);
                        break;
                    }
                    W_3959_H.n_1700_B.n_1700_B(new Z_1993_T(p_renderBlock_1_.J_1907_R()), ItemTransforms.J_1907_R.n_1700_B, p_renderBlock_2_, p_renderBlock_3_, p_renderBlock_4_, p_renderBlock_5_);
                }
            }
        }
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.R_4764_Y.n_1700_B();
    }

    public IResourceType G_564_y() {
        return VanillaResourceType.MODELS;
    }
}



