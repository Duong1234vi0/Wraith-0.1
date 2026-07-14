/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.M_1336_P;
import lightning.product.Y_1387_d;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.ChunkAccess;
import lightning.product.g_221_o;
import lightning.product.k_4690_i;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.z_2963_s;
import lightning.product.z_883_p;

public class HeightMapRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;

    public HeightMapRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        k_4690_i iworld = this.n_1700_B.Y_601_j;
        c_4037_x.v_4276_D();
        c_4037_x.Y_259_p();
        c_4037_x.e_4240_b();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_1514_x blockpos = new c_1514_x(camX, 0.0, camZ);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
        for (int i = -32; i <= 32; i += 16) {
            for (int j = -32; j <= 32; j += 16) {
                ChunkAccess ichunk = iworld.t_148_a(blockpos.add(i, 0, j));
                for (Map.Entry<z_2963_s.n_1700_B, z_2963_s> entry : ichunk.getHeightmaps()) {
                    z_2963_s.n_1700_B heightmap$type = entry.getKey();
                    Y_1387_d chunkpos = ichunk.getPos();
                    M_1336_P vector3f = this.n_1700_B(heightmap$type);
                    for (int k = 0; k < 16; ++k) {
                        for (int l = 0; l < 16; ++l) {
                            int i1 = chunkpos.J_1907_R * 16 + k;
                            int j1 = chunkpos.R_4764_Y * 16 + l;
                            float f = (float)((double)((float)iworld.n_1700_B(heightmap$type, i1, j1) + (float)heightmap$type.ordinal() * 0.09375f) - camY);
                            z_883_p.n_1700_B(bufferbuilder, (double)((float)i1 + 0.25f) - camX, f, (double)((float)j1 + 0.25f) - camZ, (double)((float)i1 + 0.75f) - camX, f + 0.09375f, (double)((float)j1 + 0.75f) - camZ, vector3f.n_1700_B(), vector3f.J_1907_R(), vector3f.R_4764_Y(), 1.0f);
                        }
                    }
                }
            }
        }
        tessellator.J_1907_R();
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }

    private M_1336_P n_1700_B(z_2963_s.n_1700_B p_239373_1_) {
        switch (p_239373_1_) {
            case n_1700_B: {
                return new M_1336_P(1.0f, 1.0f, 0.0f);
            }
            case R_4764_Y: {
                return new M_1336_P(1.0f, 0.0f, 1.0f);
            }
            case J_1907_R: {
                return new M_1336_P(0.0f, 0.7f, 0.0f);
            }
            case G_564_y: {
                return new M_1336_P(0.0f, 0.0f, 0.5f);
            }
            case P_1922_E: {
                return new M_1336_P(0.0f, 0.3f, 0.3f);
            }
            case u_1723_Y: {
                return new M_1336_P(0.0f, 0.5f, 0.5f);
            }
        }
        return new M_1336_P(0.0f, 0.0f, 0.0f);
    }
}



