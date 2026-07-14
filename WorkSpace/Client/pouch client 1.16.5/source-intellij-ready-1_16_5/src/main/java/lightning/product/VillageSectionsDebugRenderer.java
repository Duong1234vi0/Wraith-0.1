/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Set;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;

public class VillageSectionsDebugRenderer
implements n_4915_r.n_1700_B {
    private final Set<SectionPos> n_1700_B = Sets.newHashSet();

    VillageSectionsDebugRenderer() {
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B.clear();
    }

    public void n_1700_B(SectionPos p_239378_1_) {
        this.n_1700_B.add(p_239378_1_);
    }

    public void J_1907_R(SectionPos p_239379_1_) {
        this.n_1700_B.remove(p_239379_1_);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        this.n_1700_B(camX, camY, camZ);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(double p_239376_1_, double p_239376_3_, double p_239376_5_) {
        c_1514_x blockpos = new c_1514_x(p_239376_1_, p_239376_3_, p_239376_5_);
        this.n_1700_B.forEach(p_239377_1_ -> {
            if (blockpos.withinDistance(p_239377_1_.u_2550_I(), 60.0)) {
                VillageSectionsDebugRenderer.R_4764_Y(p_239377_1_);
            }
        });
    }

    private static void R_4764_Y(SectionPos p_239380_0_) {
        float f = 1.0f;
        c_1514_x blockpos = p_239380_0_.u_2550_I();
        c_1514_x blockpos1 = blockpos.add(-1.0, -1.0, -1.0);
        c_1514_x blockpos2 = blockpos.add(1.0, 1.0, 1.0);
        n_4915_r.n_1700_B(blockpos1, blockpos2, 0.2f, 1.0f, 0.2f, 0.15f);
    }
}


