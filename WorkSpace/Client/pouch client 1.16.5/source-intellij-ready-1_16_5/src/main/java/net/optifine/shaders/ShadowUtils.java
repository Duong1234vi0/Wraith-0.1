/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lightning.product.B_1647_r;
import lightning.product.N_4263_v;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;
import lightning.product.z_4547_I;
import net.optifine.shaders.IteratorRenderChunks;
import net.optifine.shaders.Shaders;

public class ShadowUtils {
    public static Iterator<z_4547_I.n_1700_B> makeShadowChunkIterator(b_4507_u world, double partialTicks, N_4263_v viewEntity, int renderDistanceChunks, B_1647_r viewFrustum) {
        float f = Shaders.getShadowRenderDistance();
        if (!(f <= 0.0f) && !(f >= (float)((renderDistanceChunks - 1) * 16))) {
            int i = u_530_F.u_1723_Y(f / 16.0f) + 1;
            float f6 = world.P_1922_E((float)partialTicks);
            float f1 = Shaders.sunPathRotation * u_530_F.P_1922_E;
            float f2 = f6 > u_530_F.G_564_y && f6 < 3.0f * u_530_F.G_564_y ? f6 + u_530_F.J_1907_R : f6;
            float f3 = -u_530_F.n_1700_B(f2);
            float f4 = u_530_F.J_1907_R(f2) * u_530_F.J_1907_R(f1);
            float f5 = -u_530_F.J_1907_R(f2) * u_530_F.n_1700_B(f1);
            c_1514_x blockpos = new c_1514_x(u_530_F.R_4764_Y(viewEntity.O_3598_v()) >> 4, u_530_F.R_4764_Y(viewEntity.X_2960_b()) >> 4, u_530_F.R_4764_Y(viewEntity.l_2647_k()) >> 4);
            c_1514_x blockpos1 = blockpos.add(-f3 * (float)i, -f4 * (float)i, -f5 * (float)i);
            c_1514_x blockpos2 = blockpos.add(f3 * (float)renderDistanceChunks, f4 * (float)renderDistanceChunks, f5 * (float)renderDistanceChunks);
            return new IteratorRenderChunks(viewFrustum, blockpos1, blockpos2, i, i);
        }
        List<z_4547_I.n_1700_B> list = Arrays.asList(viewFrustum.u_1723_Y);
        return list.iterator();
    }
}

