/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.b_257_Y;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import net.optifine.Config;
import net.optifine.shaders.ShadersRender;

public class T_2978_m<T extends TheEndPortalBlockEntity>
extends l_1802_R<T> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/environment/end_sky.png");
    public static final g_2336_b J_1907_R = new g_2336_b("textures/entity/end_portal.png");
    private static final Random R_4764_Y = new Random(31100L);
    private static final List<o_2576_A> G_564_y = (List)IntStream.range(0, 16).mapToObj(p_lambda$static$0_0_ -> o_2576_A.n_1700_B(p_lambda$static$0_0_ + 1)).collect(ImmutableList.toImmutableList());

    public T_2978_m(f_2689_h rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void n_1700_B(T tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!Config.isShaders() || !ShadersRender.renderEndPortal(tileEntityIn, partialTicks, this.n_1700_B(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn)) {
            R_4764_Y.setSeed(31100L);
            double d0 = ((i_2154_H)tileEntityIn).x_607_J().distanceSq(this.v_4262_N.P_1922_E.J_1907_R(), true);
            int i = this.n_1700_B(d0);
            float f = this.n_1700_B();
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            this.n_1700_B(tileEntityIn, f, 0.15f, matrix4f, bufferIn.getBuffer(G_564_y.get(0)));
            for (int j = 1; j < i; ++j) {
                this.n_1700_B(tileEntityIn, f, 2.0f / (float)(18 - j), matrix4f, bufferIn.getBuffer(G_564_y.get(j)));
            }
        }
    }

    private void n_1700_B(T tileEntityIn, float p_228883_2_, float p_228883_3_, D_1098_v p_228883_4_, D_4792_h p_228883_5_) {
        float f = (R_4764_Y.nextFloat() * 0.5f + 0.1f) * p_228883_3_;
        float f1 = (R_4764_Y.nextFloat() * 0.5f + 0.4f) * p_228883_3_;
        float f2 = (R_4764_Y.nextFloat() * 0.5f + 0.5f) * p_228883_3_;
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, f, f1, f2, b_257_Y.G_564_y);
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f1, f2, b_257_Y.R_4764_Y);
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, f, f1, f2, b_257_Y.u_1723_Y);
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, f, f1, f2, b_257_Y.P_1922_E);
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f, f1, f2, b_257_Y.n_1700_B);
        this.n_1700_B(tileEntityIn, p_228883_4_, p_228883_5_, 0.0f, 1.0f, p_228883_2_, p_228883_2_, 1.0f, 1.0f, 0.0f, 0.0f, f, f1, f2, b_257_Y.J_1907_R);
    }

    private void n_1700_B(T tileEntityIn, D_1098_v p_228884_2_, D_4792_h p_228884_3_, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float p_228884_12_, float p_228884_13_, float p_228884_14_, b_257_Y p_228884_15_) {
        if (((TheEndPortalBlockEntity)tileEntityIn).n_1700_B(p_228884_15_)) {
            p_228884_3_.n_1700_B(p_228884_2_, p_228884_4_, p_228884_6_, p_228884_8_).n_1700_B(p_228884_12_, p_228884_13_, p_228884_14_, 1.0f).endVertex();
            p_228884_3_.n_1700_B(p_228884_2_, p_228884_5_, p_228884_6_, p_228884_9_).n_1700_B(p_228884_12_, p_228884_13_, p_228884_14_, 1.0f).endVertex();
            p_228884_3_.n_1700_B(p_228884_2_, p_228884_5_, p_228884_7_, p_228884_10_).n_1700_B(p_228884_12_, p_228884_13_, p_228884_14_, 1.0f).endVertex();
            p_228884_3_.n_1700_B(p_228884_2_, p_228884_4_, p_228884_7_, p_228884_11_).n_1700_B(p_228884_12_, p_228884_13_, p_228884_14_, 1.0f).endVertex();
        }
    }

    protected int n_1700_B(double p_191286_1_) {
        if (p_191286_1_ > 36864.0) {
            return 1;
        }
        if (p_191286_1_ > 25600.0) {
            return 3;
        }
        if (p_191286_1_ > 16384.0) {
            return 5;
        }
        if (p_191286_1_ > 9216.0) {
            return 7;
        }
        if (p_191286_1_ > 4096.0) {
            return 9;
        }
        if (p_191286_1_ > 1024.0) {
            return 11;
        }
        if (p_191286_1_ > 576.0) {
            return 13;
        }
        return p_191286_1_ > 256.0 ? 14 : 15;
    }

    protected float n_1700_B() {
        return 0.75f;
    }
}


