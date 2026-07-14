/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.function.Predicate;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;

public class BlockUtil {
    public static J_1907_R n_1700_B(c_1514_x centerPos, b_257_Y.n_1700_B axis1, int max1, b_257_Y.n_1700_B axis2, int max2, Predicate<c_1514_x> posPredicate) {
        c_1514_x.n_1700_B blockpos$mutable = centerPos.toMutable();
        b_257_Y direction = b_257_Y.n_1700_B(b_257_Y.J_1907_R.J_1907_R, axis1);
        b_257_Y direction1 = direction.u_1723_Y();
        b_257_Y direction2 = b_257_Y.n_1700_B(b_257_Y.J_1907_R.J_1907_R, axis2);
        b_257_Y direction3 = direction2.u_1723_Y();
        int i = BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos), direction, max1);
        int j = BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos), direction1, max1);
        int k = i;
        n_1700_B[] ateleportationrepositioner$intbounds = new n_1700_B[i + 1 + j];
        ateleportationrepositioner$intbounds[i] = new n_1700_B(BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos), direction2, max2), BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos), direction3, max2));
        int l = ateleportationrepositioner$intbounds[i].n_1700_B;
        for (int i1 = 1; i1 <= i; ++i1) {
            n_1700_B teleportationrepositioner$intbounds = ateleportationrepositioner$intbounds[k - (i1 - 1)];
            ateleportationrepositioner$intbounds[k - i1] = new n_1700_B(BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos).n_1700_B(direction, i1), direction2, teleportationrepositioner$intbounds.n_1700_B), BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos).n_1700_B(direction, i1), direction3, teleportationrepositioner$intbounds.J_1907_R));
        }
        for (int l2 = 1; l2 <= j; ++l2) {
            n_1700_B teleportationrepositioner$intbounds2 = ateleportationrepositioner$intbounds[k + l2 - 1];
            ateleportationrepositioner$intbounds[k + l2] = new n_1700_B(BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos).n_1700_B(direction1, l2), direction2, teleportationrepositioner$intbounds2.n_1700_B), BlockUtil.n_1700_B(posPredicate, blockpos$mutable.n_1700_B(centerPos).n_1700_B(direction1, l2), direction3, teleportationrepositioner$intbounds2.J_1907_R));
        }
        int i3 = 0;
        int j3 = 0;
        int j1 = 0;
        int k1 = 0;
        int[] aint = new int[ateleportationrepositioner$intbounds.length];
        for (int l1 = l; l1 >= 0; --l1) {
            for (int i2 = 0; i2 < ateleportationrepositioner$intbounds.length; ++i2) {
                n_1700_B teleportationrepositioner$intbounds1 = ateleportationrepositioner$intbounds[i2];
                int j2 = l - teleportationrepositioner$intbounds1.n_1700_B;
                int k2 = l + teleportationrepositioner$intbounds1.J_1907_R;
                aint[i2] = l1 >= j2 && l1 <= k2 ? k2 + 1 - l1 : 0;
            }
            Pair<n_1700_B, Integer> pair = BlockUtil.n_1700_B(aint);
            n_1700_B teleportationrepositioner$intbounds3 = (n_1700_B)pair.getFirst();
            int k3 = 1 + teleportationrepositioner$intbounds3.J_1907_R - teleportationrepositioner$intbounds3.n_1700_B;
            int l3 = (Integer)pair.getSecond();
            if (k3 * l3 <= j1 * k1) continue;
            i3 = teleportationrepositioner$intbounds3.n_1700_B;
            j3 = l1;
            j1 = k3;
            k1 = l3;
        }
        return new J_1907_R(centerPos.func_241872_a(axis1, i3 - k).func_241872_a(axis2, j3 - l), j1, k1);
    }

    private static int n_1700_B(Predicate<c_1514_x> posPredicate, c_1514_x.n_1700_B centerPos, b_257_Y direction, int max) {
        int i;
        for (i = 0; i < max && posPredicate.test(centerPos.n_1700_B(direction)); ++i) {
        }
        return i;
    }

    @VisibleForTesting
    static Pair<n_1700_B, Integer> n_1700_B(int[] heights) {
        int i = 0;
        int j = 0;
        int k = 0;
        IntArrayList intstack = new IntArrayList();
        intstack.push(0);
        for (int l = 1; l <= heights.length; ++l) {
            int i1;
            int n = i1 = l == heights.length ? 0 : heights[l];
            while (!intstack.isEmpty()) {
                int j1 = heights[intstack.topInt()];
                if (i1 >= j1) {
                    intstack.push(l);
                    break;
                }
                intstack.popInt();
                int k1 = intstack.isEmpty() ? 0 : intstack.topInt() + 1;
                if (j1 * (l - k1) <= k * (j - i)) continue;
                j = l;
                i = k1;
                k = j1;
            }
            if (!intstack.isEmpty()) continue;
            intstack.push(l);
        }
        return new Pair((Object)new n_1700_B(i, j - 1), (Object)k);
    }

    public static class n_1700_B {
        public final int n_1700_B;
        public final int J_1907_R;

        public n_1700_B(int min, int max) {
            this.n_1700_B = min;
            this.J_1907_R = max;
        }

        public String toString() {
            return "IntBounds{min=" + this.n_1700_B + ", max=" + this.J_1907_R + "}";
        }
    }

    public static class J_1907_R {
        public final c_1514_x n_1700_B;
        public final int J_1907_R;
        public final int R_4764_Y;

        public J_1907_R(c_1514_x startPos, int width, int height) {
            this.n_1700_B = startPos;
            this.J_1907_R = width;
            this.R_4764_Y = height;
        }
    }
}


