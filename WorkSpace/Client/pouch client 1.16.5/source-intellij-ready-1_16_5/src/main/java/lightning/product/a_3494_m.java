/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import lightning.product.C_3615_s;
import lightning.product.H_1748_a;
import lightning.product.R_3197_Z;
import lightning.product.Y_1387_d;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_4690_i;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.r_4399_U;

public class a_3494_m
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private double J_1907_R = Double.MIN_VALUE;
    private final int R_4764_Y = 12;
    @Nullable
    private n_1700_B G_564_y;

    public a_3494_m(MinecraftClient client) {
        this.n_1700_B = client;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        double d0 = j_3341_s.R_4764_Y();
        if (d0 - this.J_1907_R > 3.0E9) {
            this.J_1907_R = d0;
            R_3197_Z integratedserver = this.n_1700_B.n_3318_d();
            this.G_564_y = integratedserver != null ? new n_1700_B(this, integratedserver, camX, camZ) : null;
        }
        if (this.G_564_y != null) {
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.G_564_y(2.0f);
            c_4037_x.e_4240_b();
            c_4037_x.J_1907_R(false);
            Map<Y_1387_d, String> map = this.G_564_y.J_1907_R.getNow(null);
            double d1 = this.n_1700_B.s_956_w.M_588_G().J_1907_R().R_4764_Y * 0.85;
            for (Map.Entry<Y_1387_d, String> entry : this.G_564_y.n_1700_B.entrySet()) {
                Y_1387_d chunkpos = entry.getKey();
                Object s = entry.getValue();
                if (map != null) {
                    s = (String)s + map.get(chunkpos);
                }
                String[] astring = ((String)s).split("\n");
                int i = 0;
                for (String s1 : astring) {
                    n_4915_r.n_1700_B(s1, (double)((chunkpos.J_1907_R << 4) + 8), d1 + (double)i, (double)((chunkpos.R_4764_Y << 4) + 8), -1, 0.15f);
                    i -= 2;
                }
            }
            c_4037_x.J_1907_R(true);
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
        }
    }

    final class n_1700_B {
        private final Map<Y_1387_d, String> n_1700_B;
        private final CompletableFuture<Map<Y_1387_d, String>> J_1907_R;

        private n_1700_B(a_3494_m this$0, R_3197_Z p_i226030_2_, double p_i226030_3_, double p_i226030_5_) {
            k_4690_i clientworld = this$0.n_1700_B.Y_601_j;
            f_2392_k<b_4507_u> registrykey = clientworld.g_2268_R();
            int i = (int)p_i226030_3_ >> 4;
            int j = (int)p_i226030_5_ >> 4;
            ImmutableMap.Builder builder = ImmutableMap.builder();
            r_4399_U clientchunkprovider = clientworld.v_4262_N();
            for (int k = i - 12; k <= i + 12; ++k) {
                for (int l = j - 12; l <= j + 12; ++l) {
                    Y_1387_d chunkpos = new Y_1387_d(k, l);
                    Object s = "";
                    H_1748_a chunk = clientchunkprovider.n_1700_B(k, l, false);
                    s = (String)s + "Client: ";
                    if (chunk == null) {
                        s = (String)s + "0n/a\n";
                    } else {
                        s = (String)s + (chunk.isEmpty() ? " E" : "");
                        s = (String)s + "\n";
                    }
                    builder.put((Object)chunkpos, s);
                }
            }
            this.n_1700_B = builder.build();
            this.J_1907_R = p_i226030_2_.n_1700_B(() -> {
                e_3591_l serverworld = p_i226030_2_.n_1700_B(registrykey);
                if (serverworld == null) {
                    return ImmutableMap.of();
                }
                ImmutableMap.Builder builder1 = ImmutableMap.builder();
                C_3615_s serverchunkprovider = serverworld.Y_259_p();
                for (int i1 = i - 12; i1 <= i + 12; ++i1) {
                    for (int j1 = j - 12; j1 <= j + 12; ++j1) {
                        Y_1387_d chunkpos1 = new Y_1387_d(i1, j1);
                        builder1.put((Object)chunkpos1, (Object)("Server: " + serverchunkprovider.J_1907_R(chunkpos1)));
                    }
                }
                return builder1.build();
            });
        }
    }
}


