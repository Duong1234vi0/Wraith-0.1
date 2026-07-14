/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  com.mojang.serialization.Codec
 *  javax.annotation.concurrent.Immutable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.AbstractIterator;
import com.mojang.serialization.Codec;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.concurrent.Immutable;
import lightning.product.I_4817_s;
import lightning.product.BoundingBox;
import lightning.product.Position;
import lightning.product.W_2163_m;
import lightning.product.b_257_Y;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.p_602_A;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Immutable
public class c_1514_x
extends z_3539_x {
    public static final Codec<c_1514_x> CODEC = Codec.INT_STREAM.comapFlatMap(stream -> j_3341_s.n_1700_B(stream, 3).map(coordinates -> new c_1514_x(coordinates[0], coordinates[1], coordinates[2])), pos -> IntStream.of(pos.getX(), pos.getY(), pos.getZ())).stable();
    private static final Logger LOGGER = LogManager.getLogger();
    public static final c_1514_x ZERO = new c_1514_x(0, 0, 0);
    private static final int NUM_X_BITS;
    private static final int NUM_Z_BITS;
    private static final int NUM_Y_BITS;
    private static final long X_MASK;
    private static final long Y_MASK;
    private static final long Z_MASK;
    private static final int INVERSE_START_BITS_Z;
    private static final int INVERSE_START_BITS_X;

    public c_1514_x(int x, int y, int z) {
        super(x, y, z);
    }

    public c_1514_x(double x, double y, double z) {
        super(x, y, z);
    }

    public c_1514_x(e_2866_D vec) {
        this(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public c_1514_x(Position position) {
        this(position.n_1700_B(), position.J_1907_R(), position.R_4764_Y());
    }

    public c_1514_x(z_3539_x source) {
        this(source.getX(), source.getY(), source.getZ());
    }

    public static long offset(long pos, b_257_Y direction) {
        return c_1514_x.offset(pos, direction.t_148_a(), direction.s_956_w(), direction.u_2550_I());
    }

    public static long offset(long pos, int dx, int dy, int dz) {
        return c_1514_x.pack(c_1514_x.unpackX(pos) + dx, c_1514_x.unpackY(pos) + dy, c_1514_x.unpackZ(pos) + dz);
    }

    public static int unpackX(long packedPos) {
        return (int)(packedPos << 64 - INVERSE_START_BITS_X - NUM_X_BITS >> 64 - NUM_X_BITS);
    }

    public static int unpackY(long packedPos) {
        return (int)(packedPos << 64 - NUM_Y_BITS >> 64 - NUM_Y_BITS);
    }

    public static int unpackZ(long packedPos) {
        return (int)(packedPos << 64 - INVERSE_START_BITS_Z - NUM_Z_BITS >> 64 - NUM_Z_BITS);
    }

    public static c_1514_x fromLong(long packedPos) {
        return new c_1514_x(c_1514_x.unpackX(packedPos), c_1514_x.unpackY(packedPos), c_1514_x.unpackZ(packedPos));
    }

    public long toLong() {
        return c_1514_x.pack(this.getX(), this.getY(), this.getZ());
    }

    public static long pack(int x, int y, int z) {
        long i = 0L;
        i |= ((long)x & X_MASK) << INVERSE_START_BITS_X;
        return (i |= ((long)y & Y_MASK) << 0) | ((long)z & Z_MASK) << INVERSE_START_BITS_Z;
    }

    public static long atSectionBottomY(long packedPos) {
        return packedPos & 0xFFFFFFFFFFFFFFF0L;
    }

    public c_1514_x add(double x, double y, double z) {
        return x == 0.0 && y == 0.0 && z == 0.0 ? this : new c_1514_x((double)this.getX() + x, (double)this.getY() + y, (double)this.getZ() + z);
    }

    public c_1514_x add(int x, int y, int z) {
        return x == 0 && y == 0 && z == 0 ? this : new c_1514_x(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    public c_1514_x add(z_3539_x vec) {
        return this.add(vec.getX(), vec.getY(), vec.getZ());
    }

    public c_1514_x subtract(z_3539_x vec) {
        return this.add(-vec.getX(), -vec.getY(), -vec.getZ());
    }

    @Override
    public c_1514_x up() {
        return this.offset(b_257_Y.J_1907_R);
    }

    @Override
    public c_1514_x up(int n) {
        return this.offset(b_257_Y.J_1907_R, n);
    }

    @Override
    public c_1514_x down() {
        return this.offset(b_257_Y.n_1700_B);
    }

    @Override
    public c_1514_x down(int n) {
        return this.offset(b_257_Y.n_1700_B, n);
    }

    public c_1514_x north() {
        return this.offset(b_257_Y.R_4764_Y);
    }

    public c_1514_x north(int n) {
        return this.offset(b_257_Y.R_4764_Y, n);
    }

    public c_1514_x south() {
        return this.offset(b_257_Y.G_564_y);
    }

    public c_1514_x south(int n) {
        return this.offset(b_257_Y.G_564_y, n);
    }

    public c_1514_x west() {
        return this.offset(b_257_Y.P_1922_E);
    }

    public c_1514_x west(int n) {
        return this.offset(b_257_Y.P_1922_E, n);
    }

    public c_1514_x east() {
        return this.offset(b_257_Y.u_1723_Y);
    }

    public c_1514_x east(int n) {
        return this.offset(b_257_Y.u_1723_Y, n);
    }

    public c_1514_x offset(b_257_Y facing) {
        return new c_1514_x(this.getX() + facing.t_148_a(), this.getY() + facing.s_956_w(), this.getZ() + facing.u_2550_I());
    }

    @Override
    public c_1514_x offset(b_257_Y facing, int n) {
        return n == 0 ? this : new c_1514_x(this.getX() + facing.t_148_a() * n, this.getY() + facing.s_956_w() * n, this.getZ() + facing.u_2550_I() * n);
    }

    public c_1514_x func_241872_a(b_257_Y.n_1700_B p_241872_1_, int p_241872_2_) {
        if (p_241872_2_ == 0) {
            return this;
        }
        int i = p_241872_1_ == b_257_Y.n_1700_B.n_1700_B ? p_241872_2_ : 0;
        int j = p_241872_1_ == b_257_Y.n_1700_B.J_1907_R ? p_241872_2_ : 0;
        int k = p_241872_1_ == b_257_Y.n_1700_B.R_4764_Y ? p_241872_2_ : 0;
        return new c_1514_x(this.getX() + i, this.getY() + j, this.getZ() + k);
    }

    public c_1514_x rotate(W_2163_m rotationIn) {
        switch (rotationIn) {
            default: {
                return this;
            }
            case J_1907_R: {
                return new c_1514_x(-this.getZ(), this.getY(), this.getX());
            }
            case R_4764_Y: {
                return new c_1514_x(-this.getX(), this.getY(), -this.getZ());
            }
            case G_564_y: 
        }
        return new c_1514_x(this.getZ(), this.getY(), -this.getX());
    }

    @Override
    public c_1514_x crossProduct(z_3539_x vec) {
        return new c_1514_x(this.getY() * vec.getZ() - this.getZ() * vec.getY(), this.getZ() * vec.getX() - this.getX() * vec.getZ(), this.getX() * vec.getY() - this.getY() * vec.getX());
    }

    public c_1514_x toImmutable() {
        return this;
    }

    public n_1700_B toMutable() {
        return new n_1700_B(this.getX(), this.getY(), this.getZ());
    }

    public static Iterable<c_1514_x> getRandomPositions(final Random rand, final int amount, final int minX, final int minY, final int minZ, int maxX, int maxY, int maxZ) {
        final int i = maxX - minX + 1;
        final int j = maxY - minY + 1;
        final int k = maxZ - minZ + 1;
        return () -> new AbstractIterator<c_1514_x>(){
            final n_1700_B n_1700_B = new n_1700_B();
            int J_1907_R = amount;

            protected c_1514_x n_1700_B() {
                if (this.J_1907_R <= 0) {
                    return (c_1514_x)this.endOfData();
                }
                n_1700_B blockpos = this.n_1700_B.n_1700_B(minX + rand.nextInt(i), minY + rand.nextInt(j), minZ + rand.nextInt(k));
                --this.J_1907_R;
                return blockpos;
            }

            protected /* synthetic */ Object computeNext() {
                return this.n_1700_B();
            }
        };
    }

    public static Iterable<c_1514_x> getProximitySortedBoxPositionsIterator(c_1514_x pos, final int xWidth, final int yHeight, final int zWidth) {
        final int i = xWidth + yHeight + zWidth;
        final int j = pos.getX();
        final int k = pos.getY();
        final int l = pos.getZ();
        return () -> new AbstractIterator<c_1514_x>(){
            private final n_1700_B w_1484_f = new n_1700_B();
            private int t_148_a;
            private int s_956_w;
            private int u_2550_I;
            private int M_588_G;
            private int P_4830_p;
            private boolean h_1847_R;

            protected c_1514_x n_1700_B() {
                if (this.h_1847_R) {
                    this.h_1847_R = false;
                    this.w_1484_f.setZ(l - (this.w_1484_f.getZ() - l));
                    return this.w_1484_f;
                }
                n_1700_B blockpos = null;
                while (blockpos == null) {
                    if (this.P_4830_p > this.u_2550_I) {
                        ++this.M_588_G;
                        if (this.M_588_G > this.s_956_w) {
                            ++this.t_148_a;
                            if (this.t_148_a > i) {
                                return (c_1514_x)this.endOfData();
                            }
                            this.s_956_w = Math.min(xWidth, this.t_148_a);
                            this.M_588_G = -this.s_956_w;
                        }
                        this.u_2550_I = Math.min(yHeight, this.t_148_a - Math.abs(this.M_588_G));
                        this.P_4830_p = -this.u_2550_I;
                    }
                    int i1 = this.M_588_G;
                    int j1 = this.P_4830_p;
                    int k1 = this.t_148_a - Math.abs(i1) - Math.abs(j1);
                    if (k1 <= zWidth) {
                        this.h_1847_R = k1 != 0;
                        blockpos = this.w_1484_f.n_1700_B(j + i1, k + j1, l + k1);
                    }
                    ++this.P_4830_p;
                }
                return blockpos;
            }

            protected /* synthetic */ Object computeNext() {
                return this.n_1700_B();
            }
        };
    }

    public static Optional<c_1514_x> getClosestMatchingPosition(c_1514_x pos, int width, int height, Predicate<c_1514_x> posFilter) {
        return c_1514_x.getProximitySortedBoxPositions(pos, width, height, width).filter(posFilter).findFirst();
    }

    public static Stream<c_1514_x> getProximitySortedBoxPositions(c_1514_x pos, int xWidth, int yHeight, int zWidth) {
        return StreamSupport.stream(c_1514_x.getProximitySortedBoxPositionsIterator(pos, xWidth, yHeight, zWidth).spliterator(), false);
    }

    public static Iterable<c_1514_x> getAllInBoxMutable(c_1514_x firstPos, c_1514_x secondPos) {
        return c_1514_x.getAllInBoxMutable(Math.min(firstPos.getX(), secondPos.getX()), Math.min(firstPos.getY(), secondPos.getY()), Math.min(firstPos.getZ(), secondPos.getZ()), Math.max(firstPos.getX(), secondPos.getX()), Math.max(firstPos.getY(), secondPos.getY()), Math.max(firstPos.getZ(), secondPos.getZ()));
    }

    public static Stream<c_1514_x> getAllInBox(c_1514_x firstPos, c_1514_x secondPos) {
        return StreamSupport.stream(c_1514_x.getAllInBoxMutable(firstPos, secondPos).spliterator(), false);
    }

    public static Stream<c_1514_x> getAllInBox(BoundingBox box) {
        return c_1514_x.getAllInBox(Math.min(box.n_1700_B, box.G_564_y), Math.min(box.J_1907_R, box.P_1922_E), Math.min(box.R_4764_Y, box.u_1723_Y), Math.max(box.n_1700_B, box.G_564_y), Math.max(box.J_1907_R, box.P_1922_E), Math.max(box.R_4764_Y, box.u_1723_Y));
    }

    public static Stream<c_1514_x> getAllInBox(I_4817_s aabb) {
        return c_1514_x.getAllInBox(u_530_F.R_4764_Y(aabb.minX), u_530_F.R_4764_Y(aabb.minY), u_530_F.R_4764_Y(aabb.minZ), u_530_F.R_4764_Y(aabb.maxX), u_530_F.R_4764_Y(aabb.maxY), u_530_F.R_4764_Y(aabb.maxZ));
    }

    public static Stream<c_1514_x> getAllInBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return StreamSupport.stream(c_1514_x.getAllInBoxMutable(minX, minY, minZ, maxX, maxY, maxZ).spliterator(), false);
    }

    public static Iterable<c_1514_x> getAllInBoxMutable(final int x1, final int y1, final int z1, int x2, int y2, int z2) {
        final int i = x2 - x1 + 1;
        final int j = y2 - y1 + 1;
        int k = z2 - z1 + 1;
        final int l = i * j * k;
        return () -> new AbstractIterator<c_1514_x>(){
            private final n_1700_B v_4262_N = new n_1700_B();
            private int w_1484_f;

            protected c_1514_x n_1700_B() {
                if (this.w_1484_f == l) {
                    return (c_1514_x)this.endOfData();
                }
                int i1 = this.w_1484_f % i;
                int j1 = this.w_1484_f / i;
                int k1 = j1 % j;
                int l1 = j1 / j;
                ++this.w_1484_f;
                return this.v_4262_N.n_1700_B(x1 + i1, y1 + k1, z1 + l1);
            }

            protected /* synthetic */ Object computeNext() {
                return this.n_1700_B();
            }
        };
    }

    public static Iterable<n_1700_B> func_243514_a(final c_1514_x p_243514_0_, final int p_243514_1_, final b_257_Y p_243514_2_, final b_257_Y p_243514_3_) {
        Validate.validState((p_243514_2_.h_1847_R() != p_243514_3_.h_1847_R() ? 1 : 0) != 0, (String)"The two directions cannot be on the same axis", (Object[])new Object[0]);
        return () -> new AbstractIterator<n_1700_B>(){
            private final b_257_Y[] P_1922_E;
            private final n_1700_B u_1723_Y;
            private final int v_4262_N;
            private int w_1484_f;
            private int t_148_a;
            private int s_956_w;
            private int u_2550_I;
            private int M_588_G;
            private int P_4830_p;
            {
                this.P_1922_E = new b_257_Y[]{p_243514_2_, p_243514_3_, p_243514_2_.u_1723_Y(), p_243514_3_.u_1723_Y()};
                this.u_1723_Y = p_243514_0_.toMutable().n_1700_B(p_243514_3_);
                this.v_4262_N = 4 * p_243514_1_;
                this.w_1484_f = -1;
                this.u_2550_I = this.u_1723_Y.getX();
                this.M_588_G = this.u_1723_Y.getY();
                this.P_4830_p = this.u_1723_Y.getZ();
            }

            protected n_1700_B n_1700_B() {
                this.u_1723_Y.n_1700_B(this.u_2550_I, this.M_588_G, this.P_4830_p).n_1700_B(this.P_1922_E[(this.w_1484_f + 4) % 4]);
                this.u_2550_I = this.u_1723_Y.getX();
                this.M_588_G = this.u_1723_Y.getY();
                this.P_4830_p = this.u_1723_Y.getZ();
                if (this.s_956_w >= this.t_148_a) {
                    if (this.w_1484_f >= this.v_4262_N) {
                        return (n_1700_B)this.endOfData();
                    }
                    ++this.w_1484_f;
                    this.s_956_w = 0;
                    this.t_148_a = this.w_1484_f / 2 + 1;
                }
                ++this.s_956_w;
                return this.u_1723_Y;
            }

            protected /* synthetic */ Object computeNext() {
                return this.n_1700_B();
            }
        };
    }

    static {
        NUM_Z_BITS = NUM_X_BITS = 1 + u_530_F.u_1723_Y(u_530_F.R_4764_Y(30000000));
        NUM_Y_BITS = 64 - NUM_X_BITS - NUM_Z_BITS;
        X_MASK = (1L << NUM_X_BITS) - 1L;
        Y_MASK = (1L << NUM_Y_BITS) - 1L;
        Z_MASK = (1L << NUM_Z_BITS) - 1L;
        INVERSE_START_BITS_Z = NUM_Y_BITS;
        INVERSE_START_BITS_X = NUM_Y_BITS + NUM_Z_BITS;
    }

    public static class n_1700_B
    extends c_1514_x {
        public n_1700_B() {
            this(0, 0, 0);
        }

        public n_1700_B(int x_, int y_, int z_) {
            super(x_, y_, z_);
        }

        public n_1700_B(double x, double y, double z) {
            this(u_530_F.R_4764_Y(x), u_530_F.R_4764_Y(y), u_530_F.R_4764_Y(z));
        }

        @Override
        public c_1514_x add(double x, double y, double z) {
            return super.add(x, y, z).toImmutable();
        }

        @Override
        public c_1514_x add(int x, int y, int z) {
            return super.add(x, y, z).toImmutable();
        }

        @Override
        public c_1514_x offset(b_257_Y facing, int n) {
            return super.offset(facing, n).toImmutable();
        }

        @Override
        public c_1514_x func_241872_a(b_257_Y.n_1700_B p_241872_1_, int p_241872_2_) {
            return super.func_241872_a(p_241872_1_, p_241872_2_).toImmutable();
        }

        @Override
        public c_1514_x rotate(W_2163_m rotationIn) {
            return super.rotate(rotationIn).toImmutable();
        }

        public n_1700_B n_1700_B(int xIn, int yIn, int zIn) {
            this.setX(xIn);
            this.setY(yIn);
            this.setZ(zIn);
            return this;
        }

        public n_1700_B n_1700_B(double xIn, double yIn, double zIn) {
            return this.n_1700_B(u_530_F.R_4764_Y(xIn), u_530_F.R_4764_Y(yIn), u_530_F.R_4764_Y(zIn));
        }

        public n_1700_B n_1700_B(z_3539_x vec) {
            return this.n_1700_B(vec.getX(), vec.getY(), vec.getZ());
        }

        public n_1700_B n_1700_B(long packedPos) {
            return this.n_1700_B(n_1700_B.unpackX(packedPos), n_1700_B.unpackY(packedPos), n_1700_B.unpackZ(packedPos));
        }

        public n_1700_B n_1700_B(p_602_A rotation, int x, int y, int z) {
            return this.n_1700_B(rotation.n_1700_B(x, y, z, b_257_Y.n_1700_B.n_1700_B), rotation.n_1700_B(x, y, z, b_257_Y.n_1700_B.J_1907_R), rotation.n_1700_B(x, y, z, b_257_Y.n_1700_B.R_4764_Y));
        }

        public n_1700_B n_1700_B(z_3539_x pos, b_257_Y direction) {
            return this.n_1700_B(pos.getX() + direction.t_148_a(), pos.getY() + direction.s_956_w(), pos.getZ() + direction.u_2550_I());
        }

        public n_1700_B n_1700_B(z_3539_x pos, int offsetX, int offsetY, int offsetZ) {
            return this.n_1700_B(pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ);
        }

        public n_1700_B n_1700_B(b_257_Y facing) {
            return this.n_1700_B(facing, 1);
        }

        public n_1700_B n_1700_B(b_257_Y facing, int n) {
            return this.n_1700_B(this.getX() + facing.t_148_a() * n, this.getY() + facing.s_956_w() * n, this.getZ() + facing.u_2550_I() * n);
        }

        public n_1700_B J_1907_R(int xIn, int yIn, int zIn) {
            return this.n_1700_B(this.getX() + xIn, this.getY() + yIn, this.getZ() + zIn);
        }

        public n_1700_B J_1907_R(z_3539_x p_243531_1_) {
            return this.n_1700_B(this.getX() + p_243531_1_.getX(), this.getY() + p_243531_1_.getY(), this.getZ() + p_243531_1_.getZ());
        }

        public n_1700_B n_1700_B(b_257_Y.n_1700_B axis, int min, int max) {
            switch (axis) {
                case n_1700_B: {
                    return this.n_1700_B(u_530_F.n_1700_B(this.getX(), min, max), this.getY(), this.getZ());
                }
                case J_1907_R: {
                    return this.n_1700_B(this.getX(), u_530_F.n_1700_B(this.getY(), min, max), this.getZ());
                }
                case R_4764_Y: {
                    return this.n_1700_B(this.getX(), this.getY(), u_530_F.n_1700_B(this.getZ(), min, max));
                }
            }
            throw new IllegalStateException("Unable to clamp axis " + String.valueOf(axis));
        }

        @Override
        public void setX(int xIn) {
            super.setX(xIn);
        }

        @Override
        public void setY(int yIn) {
            super.setY(yIn);
        }

        @Override
        public void setZ(int zIn) {
            super.setZ(zIn);
        }

        @Override
        public c_1514_x toImmutable() {
            return new c_1514_x(this);
        }
    }
}


