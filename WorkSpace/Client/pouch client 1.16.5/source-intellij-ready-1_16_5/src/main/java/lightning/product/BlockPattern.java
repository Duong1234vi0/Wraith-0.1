/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.T_1316_M;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.BlockInWorld;
import lightning.product.z_3539_x;

public class BlockPattern {
    private final Predicate<BlockInWorld>[][][] n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public BlockPattern(Predicate<BlockInWorld>[][][] predicates) {
        this.n_1700_B = predicates;
        this.J_1907_R = predicates.length;
        if (this.J_1907_R > 0) {
            this.R_4764_Y = predicates[0].length;
            this.G_564_y = this.R_4764_Y > 0 ? predicates[0][0].length : 0;
        } else {
            this.R_4764_Y = 0;
            this.G_564_y = 0;
        }
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    @Nullable
    private J_1907_R n_1700_B(c_1514_x pos, b_257_Y finger, b_257_Y thumb, LoadingCache<c_1514_x, BlockInWorld> lcache) {
        for (int i = 0; i < this.G_564_y; ++i) {
            for (int j = 0; j < this.R_4764_Y; ++j) {
                for (int k = 0; k < this.J_1907_R; ++k) {
                    if (this.n_1700_B[k][j][i].test((BlockInWorld)lcache.getUnchecked((Object)BlockPattern.n_1700_B(pos, finger, thumb, i, j, k)))) continue;
                    return null;
                }
            }
        }
        return new J_1907_R(pos, finger, thumb, lcache, this.G_564_y, this.R_4764_Y, this.J_1907_R);
    }

    @Nullable
    public J_1907_R n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        LoadingCache<c_1514_x, BlockInWorld> loadingcache = BlockPattern.n_1700_B(worldIn, false);
        int i = Math.max(Math.max(this.G_564_y, this.R_4764_Y), this.J_1907_R);
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos, pos.add(i - 1, i - 1, i - 1))) {
            for (b_257_Y direction : b_257_Y.values()) {
                for (b_257_Y direction1 : b_257_Y.values()) {
                    J_1907_R blockpattern$patternhelper;
                    if (direction1 == direction || direction1 == direction.u_1723_Y() || (blockpattern$patternhelper = this.n_1700_B(blockpos, direction, direction1, loadingcache)) == null) continue;
                    return blockpattern$patternhelper;
                }
            }
        }
        return null;
    }

    public static LoadingCache<c_1514_x, BlockInWorld> n_1700_B(T_1316_M worldIn, boolean forceLoadIn) {
        return CacheBuilder.newBuilder().build((CacheLoader)new n_1700_B(worldIn, forceLoadIn));
    }

    protected static c_1514_x n_1700_B(c_1514_x pos, b_257_Y finger, b_257_Y thumb, int palmOffset, int thumbOffset, int fingerOffset) {
        if (finger != thumb && finger != thumb.u_1723_Y()) {
            z_3539_x vector3i = new z_3539_x(finger.t_148_a(), finger.s_956_w(), finger.u_2550_I());
            z_3539_x vector3i1 = new z_3539_x(thumb.t_148_a(), thumb.s_956_w(), thumb.u_2550_I());
            z_3539_x vector3i2 = vector3i.crossProduct(vector3i1);
            return pos.add(vector3i1.getX() * -thumbOffset + vector3i2.getX() * palmOffset + vector3i.getX() * fingerOffset, vector3i1.getY() * -thumbOffset + vector3i2.getY() * palmOffset + vector3i.getY() * fingerOffset, vector3i1.getZ() * -thumbOffset + vector3i2.getZ() * palmOffset + vector3i.getZ() * fingerOffset);
        }
        throw new IllegalArgumentException("Invalid forwards & up combination");
    }

    public static class J_1907_R {
        private final c_1514_x n_1700_B;
        private final b_257_Y J_1907_R;
        private final b_257_Y R_4764_Y;
        private final LoadingCache<c_1514_x, BlockInWorld> G_564_y;
        private final int P_1922_E;
        private final int u_1723_Y;
        private final int v_4262_N;

        public J_1907_R(c_1514_x posIn, b_257_Y fingerIn, b_257_Y thumbIn, LoadingCache<c_1514_x, BlockInWorld> lcacheIn, int widthIn, int heightIn, int depthIn) {
            this.n_1700_B = posIn;
            this.J_1907_R = fingerIn;
            this.R_4764_Y = thumbIn;
            this.G_564_y = lcacheIn;
            this.P_1922_E = widthIn;
            this.u_1723_Y = heightIn;
            this.v_4262_N = depthIn;
        }

        public c_1514_x n_1700_B() {
            return this.n_1700_B;
        }

        public b_257_Y J_1907_R() {
            return this.J_1907_R;
        }

        public b_257_Y R_4764_Y() {
            return this.R_4764_Y;
        }

        public BlockInWorld n_1700_B(int palmOffset, int thumbOffset, int fingerOffset) {
            return (BlockInWorld)this.G_564_y.getUnchecked((Object)BlockPattern.n_1700_B(this.n_1700_B, this.J_1907_R(), this.R_4764_Y(), palmOffset, thumbOffset, fingerOffset));
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object)this).add("up", (Object)this.R_4764_Y).add("forwards", (Object)this.J_1907_R).add("frontTopLeft", (Object)this.n_1700_B).toString();
        }
    }

    static class n_1700_B
    extends CacheLoader<c_1514_x, BlockInWorld> {
        private final T_1316_M n_1700_B;
        private final boolean J_1907_R;

        public n_1700_B(T_1316_M worldIn, boolean forceLoadIn) {
            this.n_1700_B = worldIn;
            this.J_1907_R = forceLoadIn;
        }

        public BlockInWorld n_1700_B(c_1514_x p_load_1_) throws Exception {
            return new BlockInWorld(this.n_1700_B, p_load_1_, this.J_1907_R);
        }

        public /* synthetic */ Object load(Object object) throws Exception {
            return this.n_1700_B((c_1514_x)object);
        }
    }
}


