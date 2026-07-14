/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Codec
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.LeavesBlock;
import lightning.product.J_270_s;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.j_3341_s;

public class z_2963_s {
    private static final Predicate<K_4074_S> n_1700_B = p_222688_0_ -> !p_222688_0_.v_4262_N();
    private static final Predicate<K_4074_S> J_1907_R = p_222689_0_ -> p_222689_0_.R_4764_Y().R_4764_Y();
    private final J_270_s R_4764_Y = new J_270_s(9, 256);
    private final Predicate<K_4074_S> G_564_y;
    private final ChunkAccess P_1922_E;

    public z_2963_s(ChunkAccess chunkIn, n_1700_B type) {
        this.G_564_y = type.P_1922_E();
        this.P_1922_E = chunkIn;
    }

    public static void n_1700_B(ChunkAccess chunkIn, Set<n_1700_B> types) {
        int i = types.size();
        ObjectArrayList objectlist = new ObjectArrayList(i);
        ObjectListIterator objectlistiterator = objectlist.iterator();
        int j = chunkIn.s_956_w() + 16;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k = 0; k < 16; ++k) {
            block1: for (int l = 0; l < 16; ++l) {
                for (n_1700_B heightmap$type : types) {
                    objectlist.add((Object)chunkIn.getHeightmap(heightmap$type));
                }
                for (int i1 = j - 1; i1 >= 0; --i1) {
                    blockpos$mutable.n_1700_B(k, i1, l);
                    K_4074_S blockstate = chunkIn.getBlockState(blockpos$mutable);
                    if (blockstate.n_1700_B(a_3742_W.n_1700_B)) continue;
                    while (objectlistiterator.hasNext()) {
                        z_2963_s heightmap = (z_2963_s)objectlistiterator.next();
                        if (!heightmap.G_564_y.test(blockstate)) continue;
                        heightmap.n_1700_B(k, l, i1 + 1);
                        objectlistiterator.remove();
                    }
                    if (objectlist.isEmpty()) continue block1;
                    objectlistiterator.back(i);
                }
            }
        }
    }

    public boolean n_1700_B(int p_202270_1_, int p_202270_2_, int p_202270_3_, K_4074_S p_202270_4_) {
        int i = this.n_1700_B(p_202270_1_, p_202270_3_);
        if (p_202270_2_ <= i - 2) {
            return false;
        }
        if (this.G_564_y.test(p_202270_4_)) {
            if (p_202270_2_ >= i) {
                this.n_1700_B(p_202270_1_, p_202270_3_, p_202270_2_ + 1);
                return true;
            }
        } else if (i - 1 == p_202270_2_) {
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int j = p_202270_2_ - 1; j >= 0; --j) {
                blockpos$mutable.n_1700_B(p_202270_1_, j, p_202270_3_);
                if (!this.G_564_y.test(this.P_1922_E.getBlockState(blockpos$mutable))) continue;
                this.n_1700_B(p_202270_1_, p_202270_3_, j + 1);
                return true;
            }
            this.n_1700_B(p_202270_1_, p_202270_3_, 0);
            return true;
        }
        return false;
    }

    public int n_1700_B(int x, int z) {
        return this.n_1700_B(z_2963_s.J_1907_R(x, z));
    }

    private int n_1700_B(int dataArrayIndex) {
        return this.R_4764_Y.n_1700_B(dataArrayIndex);
    }

    private void n_1700_B(int x, int z, int value) {
        this.R_4764_Y.J_1907_R(z_2963_s.J_1907_R(x, z), value);
    }

    public void n_1700_B(long[] dataIn) {
        System.arraycopy(dataIn, 0, this.R_4764_Y.n_1700_B(), 0, dataIn.length);
    }

    public long[] n_1700_B() {
        return this.R_4764_Y.n_1700_B();
    }

    private static int J_1907_R(int x, int z) {
        return x + z * 16;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("WORLD_SURFACE_WG", lightning.product.z_2963_s$J_1907_R.n_1700_B, n_1700_B);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("WORLD_SURFACE", lightning.product.z_2963_s$J_1907_R.R_4764_Y, n_1700_B);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("OCEAN_FLOOR_WG", lightning.product.z_2963_s$J_1907_R.n_1700_B, J_1907_R);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("OCEAN_FLOOR", lightning.product.z_2963_s$J_1907_R.J_1907_R, J_1907_R);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("MOTION_BLOCKING", lightning.product.z_2963_s$J_1907_R.R_4764_Y, p_222680_0_ -> p_222680_0_.R_4764_Y().R_4764_Y() || !p_222680_0_.P_4830_p().R_4764_Y());
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("MOTION_BLOCKING_NO_LEAVES", lightning.product.z_2963_s$J_1907_R.J_1907_R, p_222682_0_ -> (p_222682_0_.R_4764_Y().R_4764_Y() || !p_222682_0_.P_4830_p().R_4764_Y()) && !(p_222682_0_.J_1907_R() instanceof LeavesBlock));
        public static final Codec<n_1700_B> v_4262_N;
        private final String w_1484_f;
        private final J_1907_R t_148_a;
        private final Predicate<K_4074_S> s_956_w;
        private static final Map<String, n_1700_B> u_2550_I;
        private static final /* synthetic */ n_1700_B[] M_588_G;

        public static n_1700_B[] values() {
            return (n_1700_B[])M_588_G.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String idIn, J_1907_R usageIn, Predicate<K_4074_S> heightLimitPredicateIn) {
            this.w_1484_f = idIn;
            this.t_148_a = usageIn;
            this.s_956_w = heightLimitPredicateIn;
        }

        public String J_1907_R() {
            return this.w_1484_f;
        }

        public boolean R_4764_Y() {
            return this.t_148_a == lightning.product.z_2963_s$J_1907_R.R_4764_Y;
        }

        public boolean G_564_y() {
            return this.t_148_a != lightning.product.z_2963_s$J_1907_R.n_1700_B;
        }

        @Nullable
        public static n_1700_B n_1700_B(String idIn) {
            return u_2550_I.get(idIn);
        }

        public Predicate<K_4074_S> P_1922_E() {
            return this.s_956_w;
        }

        @Override
        public String n_1700_B() {
            return this.w_1484_f;
        }

        private static /* synthetic */ n_1700_B[] u_1723_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            M_588_G = lightning.product.z_2963_s$n_1700_B.u_1723_Y();
            v_4262_N = E_4700_p.n_1700_B(n_1700_B::values, n_1700_B::n_1700_B);
            u_2550_I = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_222679_0_) -> {
                for (n_1700_B heightmap$type : lightning.product.z_2963_s$n_1700_B.values()) {
                    p_222679_0_.put(heightmap$type.w_1484_f, heightmap$type);
                }
            });
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.z_2963_s$J_1907_R.n_1700_B();
        }
    }
}


