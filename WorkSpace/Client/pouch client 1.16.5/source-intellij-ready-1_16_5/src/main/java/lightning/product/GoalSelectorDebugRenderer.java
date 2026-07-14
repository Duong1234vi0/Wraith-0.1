/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;

public class GoalSelectorDebugRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private final Map<Integer, List<n_1700_B>> J_1907_R = Maps.newHashMap();

    @Override
    public void n_1700_B() {
        this.J_1907_R.clear();
    }

    public void n_1700_B(int p_217682_1_, List<n_1700_B> p_217682_2_) {
        this.J_1907_R.put(p_217682_1_, p_217682_2_);
    }

    public GoalSelectorDebugRenderer(MinecraftClient client) {
        this.n_1700_B = client;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        h_3572_K activerenderinfo = this.n_1700_B.s_956_w.M_588_G();
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_1514_x blockpos = new c_1514_x(activerenderinfo.J_1907_R().J_1907_R, 0.0, activerenderinfo.J_1907_R().G_564_y);
        this.J_1907_R.forEach((p_217683_1_, p_217683_2_) -> {
            for (int i = 0; i < p_217683_2_.size(); ++i) {
                n_1700_B entityaidebugrenderer$entry = (n_1700_B)p_217683_2_.get(i);
                if (!blockpos.withinDistance(entityaidebugrenderer$entry.n_1700_B, 160.0)) continue;
                double d0 = (double)entityaidebugrenderer$entry.n_1700_B.getX() + 0.5;
                double d1 = (double)entityaidebugrenderer$entry.n_1700_B.getY() + 2.0 + (double)i * 0.25;
                double d2 = (double)entityaidebugrenderer$entry.n_1700_B.getZ() + 0.5;
                int j = entityaidebugrenderer$entry.G_564_y ? -16711936 : -3355444;
                n_4915_r.n_1700_B(entityaidebugrenderer$entry.R_4764_Y, d0, d1, d2, j);
            }
        });
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }

    public static class n_1700_B {
        public final c_1514_x n_1700_B;
        public final int J_1907_R;
        public final String R_4764_Y;
        public final boolean G_564_y;

        public n_1700_B(c_1514_x p_i50834_1_, int p_i50834_2_, String p_i50834_3_, boolean p_i50834_4_) {
            this.n_1700_B = p_i50834_1_;
            this.J_1907_R = p_i50834_2_;
            this.R_4764_Y = p_i50834_3_;
            this.G_564_y = p_i50834_4_;
        }
    }
}



