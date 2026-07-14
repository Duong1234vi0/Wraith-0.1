/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.z_883_p;

public class WorldGenAttemptRenderer
implements n_4915_r.n_1700_B {
    private final List<c_1514_x> n_1700_B = Lists.newArrayList();
    private final List<Float> J_1907_R = Lists.newArrayList();
    private final List<Float> R_4764_Y = Lists.newArrayList();
    private final List<Float> G_564_y = Lists.newArrayList();
    private final List<Float> P_1922_E = Lists.newArrayList();
    private final List<Float> u_1723_Y = Lists.newArrayList();

    public void n_1700_B(c_1514_x pos, float size, float red, float green, float blue, float alpha) {
        this.n_1700_B.add(pos);
        this.J_1907_R.add(Float.valueOf(size));
        this.R_4764_Y.add(Float.valueOf(alpha));
        this.G_564_y.add(Float.valueOf(red));
        this.P_1922_E.add(Float.valueOf(green));
        this.u_1723_Y.add(Float.valueOf(blue));
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            c_1514_x blockpos = this.n_1700_B.get(i);
            Float f = this.J_1907_R.get(i);
            float f1 = f.floatValue() / 2.0f;
            z_883_p.n_1700_B(bufferbuilder, (double)((float)blockpos.getX() + 0.5f - f1) - camX, (double)((float)blockpos.getY() + 0.5f - f1) - camY, (double)((float)blockpos.getZ() + 0.5f - f1) - camZ, (double)((float)blockpos.getX() + 0.5f + f1) - camX, (double)((float)blockpos.getY() + 0.5f + f1) - camY, (double)((float)blockpos.getZ() + 0.5f + f1) - camZ, this.G_564_y.get(i).floatValue(), this.P_1922_E.get(i).floatValue(), this.u_1723_Y.get(i).floatValue(), this.R_4764_Y.get(i).floatValue());
        }
        tessellator.J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }
}


