/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import lightning.product.c_1514_x;

public class Y_1387_d {
    public static final long n_1700_B = Y_1387_d.n_1700_B(1875016, 1875016);
    public final int J_1907_R;
    public final int R_4764_Y;
    private int G_564_y = 0;

    public Y_1387_d(int x, int z) {
        this.J_1907_R = x;
        this.R_4764_Y = z;
    }

    public Y_1387_d(c_1514_x pos) {
        this.J_1907_R = pos.getX() >> 4;
        this.R_4764_Y = pos.getZ() >> 4;
    }

    public Y_1387_d(long longIn) {
        this.J_1907_R = (int)longIn;
        this.R_4764_Y = (int)(longIn >> 32);
    }

    public long n_1700_B() {
        return Y_1387_d.n_1700_B(this.J_1907_R, this.R_4764_Y);
    }

    public static long n_1700_B(int x, int z) {
        return (long)x & 0xFFFFFFFFL | ((long)z & 0xFFFFFFFFL) << 32;
    }

    public static int n_1700_B(long chunkAsLong) {
        return (int)(chunkAsLong & 0xFFFFFFFFL);
    }

    public static int J_1907_R(long chunkAsLong) {
        return (int)(chunkAsLong >>> 32 & 0xFFFFFFFFL);
    }

    public int hashCode() {
        if (this.G_564_y != 0) {
            return this.G_564_y;
        }
        int i = 1664525 * this.J_1907_R + 1013904223;
        int j = 1664525 * (this.R_4764_Y ^ 0xDEADBEEF) + 1013904223;
        this.G_564_y = i ^ j;
        return this.G_564_y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof Y_1387_d)) {
            return false;
        }
        Y_1387_d chunkpos = (Y_1387_d)p_equals_1_;
        return this.J_1907_R == chunkpos.J_1907_R && this.R_4764_Y == chunkpos.R_4764_Y;
    }

    public int J_1907_R() {
        return this.J_1907_R << 4;
    }

    public int R_4764_Y() {
        return this.R_4764_Y << 4;
    }

    public int G_564_y() {
        return (this.J_1907_R << 4) + 15;
    }

    public int P_1922_E() {
        return (this.R_4764_Y << 4) + 15;
    }

    public int u_1723_Y() {
        return this.J_1907_R >> 5;
    }

    public int v_4262_N() {
        return this.R_4764_Y >> 5;
    }

    public int w_1484_f() {
        return this.J_1907_R & 0x1F;
    }

    public int t_148_a() {
        return this.R_4764_Y & 0x1F;
    }

    public String toString() {
        return "[" + this.J_1907_R + ", " + this.R_4764_Y + "]";
    }

    public c_1514_x s_956_w() {
        return new c_1514_x(this.J_1907_R(), 0, this.R_4764_Y());
    }

    public int n_1700_B(Y_1387_d chunkPosIn) {
        return Math.max(Math.abs(this.J_1907_R - chunkPosIn.J_1907_R), Math.abs(this.R_4764_Y - chunkPosIn.R_4764_Y));
    }

    public static Stream<Y_1387_d> n_1700_B(Y_1387_d center, int radius) {
        return Y_1387_d.n_1700_B(new Y_1387_d(center.J_1907_R - radius, center.R_4764_Y - radius), new Y_1387_d(center.J_1907_R + radius, center.R_4764_Y + radius));
    }

    public static Stream<Y_1387_d> n_1700_B(final Y_1387_d start, final Y_1387_d end) {
        int i = Math.abs(start.J_1907_R - end.J_1907_R) + 1;
        int j = Math.abs(start.R_4764_Y - end.R_4764_Y) + 1;
        final int k = start.J_1907_R < end.J_1907_R ? 1 : -1;
        final int l = start.R_4764_Y < end.R_4764_Y ? 1 : -1;
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Y_1387_d>((long)(i * j), 64){
            @Nullable
            private Y_1387_d P_1922_E;

            @Override
            public boolean tryAdvance(Consumer<? super Y_1387_d> p_tryAdvance_1_) {
                if (this.P_1922_E == null) {
                    this.P_1922_E = start;
                } else {
                    int i1 = this.P_1922_E.J_1907_R;
                    int j1 = this.P_1922_E.R_4764_Y;
                    if (i1 == end.J_1907_R) {
                        if (j1 == end.R_4764_Y) {
                            return false;
                        }
                        this.P_1922_E = new Y_1387_d(start.J_1907_R, j1 + l);
                    } else {
                        this.P_1922_E = new Y_1387_d(i1 + k, j1);
                    }
                }
                p_tryAdvance_1_.accept(this.P_1922_E);
                return true;
            }
        }, false);
    }
}

