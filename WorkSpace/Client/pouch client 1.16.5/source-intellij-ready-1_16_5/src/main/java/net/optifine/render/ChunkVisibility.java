/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Map;
import lightning.product.H_1748_a;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1322_b;
import lightning.product.i_2154_H;
import lightning.product.u_530_F;
import net.optifine.Config;

public class ChunkVisibility {
    public static final int MASK_FACINGS = 63;
    public static final b_257_Y[][] enumFacingArrays = ChunkVisibility.makeEnumFacingArrays(false);
    public static final b_257_Y[][] enumFacingOppositeArrays = ChunkVisibility.makeEnumFacingArrays(true);
    private static int counter = 0;
    private static int iMaxStatic = -1;
    private static int iMaxStaticFinal = 16;
    private static b_4507_u worldLast = null;
    private static int pcxLast = Integer.MIN_VALUE;
    private static int pczLast = Integer.MIN_VALUE;

    public static int getMaxChunkY(b_4507_u world, N_4263_v viewEntity, int renderDistanceChunks) {
        int i = u_530_F.R_4764_Y(viewEntity.O_3598_v()) >> 4;
        int j = u_530_F.R_4764_Y(viewEntity.X_2960_b()) >> 4;
        int k = u_530_F.R_4764_Y(viewEntity.l_2647_k()) >> 4;
        j = Config.limit(j, 0, 15);
        H_1748_a chunk = world.u_1723_Y(i, k);
        int l = i - renderDistanceChunks;
        int i1 = i + renderDistanceChunks;
        int j1 = k - renderDistanceChunks;
        int k1 = k + renderDistanceChunks;
        if (world != worldLast || i != pcxLast || k != pczLast) {
            counter = 0;
            iMaxStaticFinal = 16;
            worldLast = world;
            pcxLast = i;
            pczLast = k;
        }
        if (counter == 0) {
            iMaxStatic = -1;
        }
        int l1 = iMaxStatic;
        switch (counter) {
            case 0: {
                i1 = i;
                k1 = k;
                break;
            }
            case 1: {
                l = i;
                k1 = k;
                break;
            }
            case 2: {
                i1 = i;
                j1 = k;
                break;
            }
            case 3: {
                l = i;
                j1 = k;
            }
        }
        for (int i2 = l; i2 < i1; ++i2) {
            block9: for (int j2 = j1; j2 < k1; ++j2) {
                H_1748_a chunk1 = world.u_1723_Y(i2, j2);
                if (chunk1.isEmpty()) continue;
                P_3550_Z[] achunksection = chunk1.getSections();
                for (int k2 = achunksection.length - 1; k2 > l1; --k2) {
                    P_3550_Z chunksection = achunksection[k2];
                    if (chunksection == null || chunksection.R_4764_Y()) continue;
                    if (k2 <= l1) break;
                    l1 = k2;
                    break;
                }
                try {
                    Map<c_1514_x, i_2154_H> map = chunk1.getTileEntityMap();
                    if (!map.isEmpty()) {
                        for (c_1514_x blockpos : map.keySet()) {
                            int l2 = blockpos.getY() >> 4;
                            if (l2 <= l1) continue;
                            l1 = l2;
                        }
                    }
                }
                catch (ConcurrentModificationException map) {
                    // empty catch block
                }
                e_1322_b<N_4263_v>[] classinheritancemultimap = chunk1.getEntityLists();
                for (int i3 = classinheritancemultimap.length - 1; i3 > l1; --i3) {
                    e_1322_b<N_4263_v> classinheritancemultimap1 = classinheritancemultimap[i3];
                    if (classinheritancemultimap1.isEmpty() || chunk1 == chunk && i3 == j && classinheritancemultimap1.size() == 1) continue;
                    if (i3 <= l1) continue block9;
                    l1 = i3;
                    continue block9;
                }
            }
        }
        if (counter < 3) {
            iMaxStatic = l1;
            l1 = iMaxStaticFinal;
        } else {
            iMaxStaticFinal = l1;
            iMaxStatic = -1;
        }
        counter = (counter + 1) % 4;
        return l1 << 4;
    }

    public static boolean isFinished() {
        return counter == 0;
    }

    private static b_257_Y[][] makeEnumFacingArrays(boolean opposite) {
        int i = 64;
        b_257_Y[][] adirection = new b_257_Y[i][];
        for (int j = 0; j < i; ++j) {
            ArrayList<b_257_Y> list = new ArrayList<b_257_Y>();
            for (int k = 0; k < b_257_Y.v_4262_N.length; ++k) {
                b_257_Y direction = b_257_Y.v_4262_N[k];
                b_257_Y direction1 = opposite ? direction.u_1723_Y() : direction;
                int l = 1 << direction1.ordinal();
                if ((j & l) == 0) continue;
                list.add(direction);
            }
            b_257_Y[] adirection1 = list.toArray(new b_257_Y[list.size()]);
            adirection[j] = adirection1;
        }
        return adirection;
    }

    public static b_257_Y[] getFacingsNotOpposite(int setDisabled) {
        int i = ~setDisabled & 0x3F;
        return enumFacingOppositeArrays[i];
    }

    public static void reset() {
        worldLast = null;
    }
}

