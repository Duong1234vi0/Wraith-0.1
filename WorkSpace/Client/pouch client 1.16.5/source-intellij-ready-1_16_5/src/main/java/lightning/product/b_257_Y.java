/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterators
 *  com.mojang.serialization.Codec
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Iterators;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.D_1098_v;
import lightning.product.E_4700_p;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_2491_A;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lightning.product.z_3539_x;

public final class b_257_Y
extends Enum<b_257_Y>
implements E_4700_p {
    public static final /* enum */ b_257_Y n_1700_B = new b_257_Y(0, 1, -1, "down", lightning.product.b_257_Y$J_1907_R.J_1907_R, lightning.product.b_257_Y$n_1700_B.J_1907_R, new z_3539_x(0, -1, 0));
    public static final /* enum */ b_257_Y J_1907_R = new b_257_Y(1, 0, -1, "up", lightning.product.b_257_Y$J_1907_R.n_1700_B, lightning.product.b_257_Y$n_1700_B.J_1907_R, new z_3539_x(0, 1, 0));
    public static final /* enum */ b_257_Y R_4764_Y = new b_257_Y(2, 3, 2, "north", lightning.product.b_257_Y$J_1907_R.J_1907_R, lightning.product.b_257_Y$n_1700_B.R_4764_Y, new z_3539_x(0, 0, -1));
    public static final /* enum */ b_257_Y G_564_y = new b_257_Y(3, 2, 0, "south", lightning.product.b_257_Y$J_1907_R.n_1700_B, lightning.product.b_257_Y$n_1700_B.R_4764_Y, new z_3539_x(0, 0, 1));
    public static final /* enum */ b_257_Y P_1922_E = new b_257_Y(4, 5, 1, "west", lightning.product.b_257_Y$J_1907_R.J_1907_R, lightning.product.b_257_Y$n_1700_B.n_1700_B, new z_3539_x(-1, 0, 0));
    public static final /* enum */ b_257_Y u_1723_Y = new b_257_Y(5, 4, 3, "east", lightning.product.b_257_Y$J_1907_R.n_1700_B, lightning.product.b_257_Y$n_1700_B.n_1700_B, new z_3539_x(1, 0, 0));
    private final int t_148_a;
    private final int s_956_w;
    private final int u_2550_I;
    private final String M_588_G;
    private final n_1700_B P_4830_p;
    private final J_1907_R h_1847_R;
    private final z_3539_x Q_4569_t;
    public static final b_257_Y[] v_4262_N;
    private static final Map<String, b_257_Y> M_182_A;
    public static final b_257_Y[] w_1484_f;
    private static final b_257_Y[] t_1786_h;
    private static final Long2ObjectMap<b_257_Y> multiplayerClientSuggestionProvider;
    private static final /* synthetic */ b_257_Y[] w_1457_N;

    public static b_257_Y[] values() {
        return (b_257_Y[])w_1457_N.clone();
    }

    public static b_257_Y valueOf(String name) {
        return Enum.valueOf(b_257_Y.class, name);
    }

    private b_257_Y(int indexIn, int oppositeIn, int horizontalIndexIn, String nameIn, J_1907_R axisDirectionIn, n_1700_B axisIn, z_3539_x directionVecIn) {
        this.t_148_a = indexIn;
        this.u_2550_I = horizontalIndexIn;
        this.s_956_w = oppositeIn;
        this.M_588_G = nameIn;
        this.P_4830_p = axisIn;
        this.h_1847_R = axisDirectionIn;
        this.Q_4569_t = directionVecIn;
    }

    public static b_257_Y[] n_1700_B(N_4263_v entityIn) {
        b_257_Y direction2;
        float f = entityIn.J_1907_R(1.0f) * ((float)Math.PI / 180);
        float f1 = -entityIn.R_4764_Y(1.0f) * ((float)Math.PI / 180);
        float f2 = u_530_F.n_1700_B(f);
        float f3 = u_530_F.J_1907_R(f);
        float f4 = u_530_F.n_1700_B(f1);
        float f5 = u_530_F.J_1907_R(f1);
        boolean flag = f4 > 0.0f;
        boolean flag1 = f2 < 0.0f;
        boolean flag2 = f5 > 0.0f;
        float f6 = flag ? f4 : -f4;
        float f7 = flag1 ? -f2 : f2;
        float f8 = flag2 ? f5 : -f5;
        float f9 = f6 * f3;
        float f10 = f8 * f3;
        b_257_Y direction = flag ? u_1723_Y : P_1922_E;
        b_257_Y direction1 = flag1 ? J_1907_R : n_1700_B;
        b_257_Y b_257_Y2 = direction2 = flag2 ? G_564_y : R_4764_Y;
        if (f6 > f8) {
            if (f7 > f9) {
                return b_257_Y.n_1700_B(direction1, direction, direction2);
            }
            return f10 > f7 ? b_257_Y.n_1700_B(direction, direction2, direction1) : b_257_Y.n_1700_B(direction, direction1, direction2);
        }
        if (f7 > f10) {
            return b_257_Y.n_1700_B(direction1, direction2, direction);
        }
        return f9 > f7 ? b_257_Y.n_1700_B(direction2, direction, direction1) : b_257_Y.n_1700_B(direction2, direction1, direction);
    }

    private static b_257_Y[] n_1700_B(b_257_Y first, b_257_Y second, b_257_Y third) {
        return new b_257_Y[]{first, second, third, third.u_1723_Y(), second.u_1723_Y(), first.u_1723_Y()};
    }

    public static b_257_Y n_1700_B(D_1098_v matrixIn, b_257_Y directionIn) {
        z_3539_x vector3i = directionIn.M_182_A();
        Z_2491_A vector4f = new Z_2491_A(vector3i.getX(), vector3i.getY(), vector3i.getZ(), 0.0f);
        vector4f.n_1700_B(matrixIn);
        return b_257_Y.n_1700_B(vector4f.n_1700_B(), vector4f.J_1907_R(), vector4f.R_4764_Y());
    }

    public w_3785_E J_1907_R() {
        w_3785_E quaternion = M_1336_P.J_1907_R.R_4764_Y(90.0f);
        switch (this.ordinal()) {
            case 0: {
                return M_1336_P.J_1907_R.R_4764_Y(180.0f);
            }
            case 1: {
                return w_3785_E.n_1700_B.v_4262_N();
            }
            case 2: {
                quaternion.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
                return quaternion;
            }
            case 3: {
                return quaternion;
            }
            case 4: {
                quaternion.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
                return quaternion;
            }
        }
        quaternion.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-90.0f));
        return quaternion;
    }

    public int R_4764_Y() {
        return this.t_148_a;
    }

    public int G_564_y() {
        return this.u_2550_I;
    }

    public J_1907_R P_1922_E() {
        return this.h_1847_R;
    }

    public b_257_Y u_1723_Y() {
        return v_4262_N[this.s_956_w];
    }

    public b_257_Y v_4262_N() {
        switch (this.ordinal()) {
            case 2: {
                return u_1723_Y;
            }
            case 3: {
                return P_1922_E;
            }
            case 4: {
                return R_4764_Y;
            }
            case 5: {
                return G_564_y;
            }
        }
        throw new IllegalStateException("Unable to get Y-rotated facing of " + String.valueOf(this));
    }

    public b_257_Y w_1484_f() {
        switch (this.ordinal()) {
            case 2: {
                return P_1922_E;
            }
            case 3: {
                return u_1723_Y;
            }
            case 4: {
                return G_564_y;
            }
            case 5: {
                return R_4764_Y;
            }
        }
        throw new IllegalStateException("Unable to get CCW facing of " + String.valueOf(this));
    }

    public int t_148_a() {
        return this.Q_4569_t.getX();
    }

    public int s_956_w() {
        return this.Q_4569_t.getY();
    }

    public int u_2550_I() {
        return this.Q_4569_t.getZ();
    }

    public M_1336_P M_588_G() {
        return new M_1336_P(this.t_148_a(), this.s_956_w(), this.u_2550_I());
    }

    public String P_4830_p() {
        return this.M_588_G;
    }

    public n_1700_B h_1847_R() {
        return this.P_4830_p;
    }

    @Nullable
    public static b_257_Y n_1700_B(@Nullable String name) {
        return name == null ? null : M_182_A.get(name.toLowerCase(Locale.ROOT));
    }

    public static b_257_Y n_1700_B(int index) {
        return w_1484_f[u_530_F.n_1700_B(index % w_1484_f.length)];
    }

    public static b_257_Y J_1907_R(int horizontalIndexIn) {
        return t_1786_h[u_530_F.n_1700_B(horizontalIndexIn % t_1786_h.length)];
    }

    @Nullable
    public static b_257_Y n_1700_B(int x, int y, int z) {
        return (b_257_Y)multiplayerClientSuggestionProvider.get(c_1514_x.pack(x, y, z));
    }

    public static b_257_Y n_1700_B(double angle) {
        return b_257_Y.J_1907_R(u_530_F.R_4764_Y(angle / 90.0 + 0.5) & 3);
    }

    public static b_257_Y n_1700_B(n_1700_B axisIn, J_1907_R axisDirectionIn) {
        switch (axisIn.ordinal()) {
            case 0: {
                return axisDirectionIn == lightning.product.b_257_Y$J_1907_R.n_1700_B ? u_1723_Y : P_1922_E;
            }
            case 1: {
                return axisDirectionIn == lightning.product.b_257_Y$J_1907_R.n_1700_B ? J_1907_R : n_1700_B;
            }
        }
        return axisDirectionIn == lightning.product.b_257_Y$J_1907_R.n_1700_B ? G_564_y : R_4764_Y;
    }

    public float Q_4569_t() {
        return (this.u_2550_I & 3) * 90;
    }

    public static b_257_Y n_1700_B(Random rand) {
        return j_3341_s.n_1700_B(v_4262_N, rand);
    }

    public static b_257_Y n_1700_B(double x, double y, double z) {
        return b_257_Y.n_1700_B((float)x, (float)y, (float)z);
    }

    public static b_257_Y n_1700_B(float x, float y, float z) {
        b_257_Y direction = R_4764_Y;
        float f = Float.MIN_VALUE;
        for (b_257_Y direction1 : v_4262_N) {
            float f1 = x * (float)direction1.Q_4569_t.getX() + y * (float)direction1.Q_4569_t.getY() + z * (float)direction1.Q_4569_t.getZ();
            if (!(f1 > f)) continue;
            f = f1;
            direction = direction1;
        }
        return direction;
    }

    public String toString() {
        return this.M_588_G;
    }

    @Override
    public String n_1700_B() {
        return this.M_588_G;
    }

    public static b_257_Y n_1700_B(J_1907_R axisDirectionIn, n_1700_B axisIn) {
        for (b_257_Y direction : v_4262_N) {
            if (direction.P_1922_E() != axisDirectionIn || direction.h_1847_R() != axisIn) continue;
            return direction;
        }
        throw new IllegalArgumentException("No such direction: " + String.valueOf((Object)axisDirectionIn) + " " + String.valueOf(axisIn));
    }

    public z_3539_x M_182_A() {
        return this.Q_4569_t;
    }

    public boolean n_1700_B(float degrees) {
        float f = degrees * ((float)Math.PI / 180);
        float f1 = -u_530_F.n_1700_B(f);
        float f2 = u_530_F.J_1907_R(f);
        return (float)this.Q_4569_t.getX() * f1 + (float)this.Q_4569_t.getZ() * f2 > 0.0f;
    }

    private static /* synthetic */ b_257_Y[] t_1786_h() {
        return new b_257_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        w_1457_N = b_257_Y.t_1786_h();
        v_4262_N = b_257_Y.values();
        M_182_A = Arrays.stream(v_4262_N).collect(Collectors.toMap(b_257_Y::P_4830_p, p_lambda$static$0_0_ -> p_lambda$static$0_0_));
        w_1484_f = (b_257_Y[])Arrays.stream(v_4262_N).sorted(Comparator.comparingInt(p_lambda$static$1_0_ -> p_lambda$static$1_0_.t_148_a)).toArray(b_257_Y[]::new);
        t_1786_h = (b_257_Y[])Arrays.stream(v_4262_N).filter(p_lambda$static$3_0_ -> p_lambda$static$3_0_.h_1847_R().G_564_y()).sorted(Comparator.comparingInt(p_lambda$static$4_0_ -> p_lambda$static$4_0_.u_2550_I)).toArray(b_257_Y[]::new);
        multiplayerClientSuggestionProvider = (Long2ObjectMap)Arrays.stream(v_4262_N).collect(Collectors.toMap(p_lambda$static$6_0_ -> new c_1514_x(p_lambda$static$6_0_.M_182_A()).toLong(), p_lambda$static$7_0_ -> p_lambda$static$7_0_, (p_lambda$static$8_0_, p_lambda$static$8_1_) -> {
            throw new IllegalArgumentException("Duplicate keys");
        }, Long2ObjectOpenHashMap::new));
    }

    public static abstract sealed class n_1700_B
    extends Enum<n_1700_B>
    implements Predicate<b_257_Y>,
    E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("x"){

            @Override
            public int n_1700_B(int x, int y, int z) {
                return x;
            }

            @Override
            public double n_1700_B(double x, double y, double z) {
                return x;
            }

            @Override
            public /* synthetic */ boolean test(@Nullable Object object) {
                return super.n_1700_B((b_257_Y)object);
            }
        };
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("y"){

            @Override
            public int n_1700_B(int x, int y, int z) {
                return y;
            }

            @Override
            public double n_1700_B(double x, double y, double z) {
                return y;
            }

            @Override
            public /* synthetic */ boolean test(@Nullable Object object) {
                return super.n_1700_B((b_257_Y)object);
            }
        };
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("z"){

            @Override
            public int n_1700_B(int x, int y, int z) {
                return z;
            }

            @Override
            public double n_1700_B(double x, double y, double z) {
                return z;
            }

            @Override
            public /* synthetic */ boolean test(@Nullable Object object) {
                return super.n_1700_B((b_257_Y)object);
            }
        };
        private static final n_1700_B[] P_1922_E;
        public static final Codec<n_1700_B> G_564_y;
        private static final Map<String, n_1700_B> u_1723_Y;
        private final String v_4262_N;
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn) {
            this.v_4262_N = nameIn;
        }

        @Nullable
        public static n_1700_B n_1700_B(String name) {
            return u_1723_Y.get(name.toLowerCase(Locale.ROOT));
        }

        public String J_1907_R() {
            return this.v_4262_N;
        }

        public boolean R_4764_Y() {
            return this == J_1907_R;
        }

        public boolean G_564_y() {
            return this == n_1700_B || this == R_4764_Y;
        }

        public String toString() {
            return this.v_4262_N;
        }

        public static n_1700_B n_1700_B(Random rand) {
            return j_3341_s.n_1700_B(P_1922_E, rand);
        }

        public boolean n_1700_B(@Nullable b_257_Y p_test_1_) {
            return p_test_1_ != null && p_test_1_.h_1847_R() == this;
        }

        public R_4764_Y P_1922_E() {
            switch (this.ordinal()) {
                case 0: 
                case 2: {
                    return lightning.product.b_257_Y$R_4764_Y.n_1700_B;
                }
                case 1: {
                    return lightning.product.b_257_Y$R_4764_Y.J_1907_R;
                }
            }
            throw new Error("Someone's been tampering with the universe!");
        }

        @Override
        public String n_1700_B() {
            return this.v_4262_N;
        }

        public abstract int n_1700_B(int var1, int var2, int var3);

        public abstract double n_1700_B(double var1, double var3, double var5);

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((b_257_Y)object);
        }

        private static /* synthetic */ n_1700_B[] u_1723_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            w_1484_f = lightning.product.b_257_Y$n_1700_B.u_1723_Y();
            P_1922_E = lightning.product.b_257_Y$n_1700_B.values();
            G_564_y = E_4700_p.n_1700_B(n_1700_B::values, n_1700_B::n_1700_B);
            u_1723_Y = Arrays.stream(P_1922_E).collect(Collectors.toMap(n_1700_B::J_1907_R, p_lambda$static$0_0_ -> p_lambda$static$0_0_));
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(1, "Towards positive");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(-1, "Towards negative");
        private final int R_4764_Y;
        private final String G_564_y;
        private static final /* synthetic */ J_1907_R[] P_1922_E;

        public static J_1907_R[] values() {
            return (J_1907_R[])P_1922_E.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(int offset, String description) {
            this.R_4764_Y = offset;
            this.G_564_y = description;
        }

        public int n_1700_B() {
            return this.R_4764_Y;
        }

        public String toString() {
            return this.G_564_y;
        }

        public J_1907_R J_1907_R() {
            return this == n_1700_B ? J_1907_R : n_1700_B;
        }

        private static /* synthetic */ J_1907_R[] R_4764_Y() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.b_257_Y$J_1907_R.R_4764_Y();
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y>
    implements Iterable<b_257_Y>,
    Predicate<b_257_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y(new b_257_Y[]{R_4764_Y, u_1723_Y, G_564_y, P_1922_E}, new n_1700_B[]{lightning.product.b_257_Y$n_1700_B.n_1700_B, lightning.product.b_257_Y$n_1700_B.R_4764_Y});
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y(new b_257_Y[]{J_1907_R, n_1700_B}, new n_1700_B[]{lightning.product.b_257_Y$n_1700_B.J_1907_R});
        private final b_257_Y[] R_4764_Y;
        private final n_1700_B[] G_564_y;
        private static final /* synthetic */ R_4764_Y[] P_1922_E;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])P_1922_E.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private R_4764_Y(b_257_Y[] facingValuesIn, n_1700_B[] axisValuesIn) {
            this.R_4764_Y = facingValuesIn;
            this.G_564_y = axisValuesIn;
        }

        public b_257_Y n_1700_B(Random rand) {
            return j_3341_s.n_1700_B(this.R_4764_Y, rand);
        }

        public n_1700_B J_1907_R(Random p_244803_1_) {
            return j_3341_s.n_1700_B(this.G_564_y, p_244803_1_);
        }

        public boolean n_1700_B(@Nullable b_257_Y p_test_1_) {
            return p_test_1_ != null && p_test_1_.h_1847_R().P_1922_E() == this;
        }

        @Override
        public Iterator<b_257_Y> iterator() {
            return Iterators.forArray((Object[])this.R_4764_Y);
        }

        public Stream<b_257_Y> n_1700_B() {
            return Arrays.stream(this.R_4764_Y);
        }

        @Override
        public /* synthetic */ boolean test(@Nullable Object object) {
            return this.n_1700_B((b_257_Y)object);
        }

        private static /* synthetic */ R_4764_Y[] J_1907_R() {
            return new R_4764_Y[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.b_257_Y$R_4764_Y.J_1907_R();
        }
    }
}


