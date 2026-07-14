/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.z_883_p;

public class e_2896_q
implements n_4915_r.n_1700_B {
    private final Map<c_1514_x, c_1514_x> n_1700_B = Maps.newHashMap();
    private final Map<c_1514_x, Float> J_1907_R = Maps.newHashMap();
    private final List<c_1514_x> R_4764_Y = Lists.newArrayList();

    public void n_1700_B(c_1514_x cavePos, List<c_1514_x> subPositions, List<Float> sizes) {
        for (int i = 0; i < subPositions.size(); ++i) {
            this.n_1700_B.put(subPositions.get(i), cavePos);
            this.J_1907_R.put(subPositions.get(i), sizes.get(i));
        }
        this.R_4764_Y.add(cavePos);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_1514_x blockpos = new c_1514_x(camX, 0.0, camZ);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
        for (Map.Entry<c_1514_x, c_1514_x> entry : this.n_1700_B.entrySet()) {
            c_1514_x blockpos1 = entry.getKey();
            c_1514_x blockpos2 = entry.getValue();
            float f = (float)(blockpos2.getX() * 128 % 256) / 256.0f;
            float f1 = (float)(blockpos2.getY() * 128 % 256) / 256.0f;
            float f2 = (float)(blockpos2.getZ() * 128 % 256) / 256.0f;
            float f3 = this.J_1907_R.get(blockpos1).floatValue();
            if (!blockpos.withinDistance(blockpos1, 160.0)) continue;
            z_883_p.n_1700_B(bufferbuilder, (double)((float)blockpos1.getX() + 0.5f) - camX - (double)f3, (double)((float)blockpos1.getY() + 0.5f) - camY - (double)f3, (double)((float)blockpos1.getZ() + 0.5f) - camZ - (double)f3, (double)((float)blockpos1.getX() + 0.5f) - camX + (double)f3, (double)((float)blockpos1.getY() + 0.5f) - camY + (double)f3, (double)((float)blockpos1.getZ() + 0.5f) - camZ + (double)f3, f, f1, f2, 0.5f);
        }
        for (c_1514_x blockpos3 : this.R_4764_Y) {
            if (!blockpos.withinDistance(blockpos3, 160.0)) continue;
            z_883_p.n_1700_B(bufferbuilder, (double)blockpos3.getX() - camX, (double)blockpos3.getY() - camY, (double)blockpos3.getZ() - camZ, (double)((float)blockpos3.getX() + 1.0f) - camX, (double)((float)blockpos3.getY() + 1.0f) - camY, (double)((float)blockpos3.getZ() + 1.0f) - camZ, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        tessellator.J_1907_R();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }
}


