/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.DynamicLike
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.DynamicLike;
import java.util.List;
import java.util.Objects;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.BorderChangeListener;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.h_2866_S;
import lightning.product.j_3341_s;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class T_603_v {
    private final List<BorderChangeListener> n_1700_B = Lists.newArrayList();
    private double R_4764_Y = 0.2;
    private double G_564_y = 5.0;
    private int P_1922_E = 15;
    private int u_1723_Y = 5;
    private double v_4262_N;
    private double w_1484_f;
    private int t_148_a = 29999984;
    private n_1700_B s_956_w = new G_564_y(6.0E7);
    public static final R_4764_Y J_1907_R = new R_4764_Y(0.0, 0.0, 0.2, 5.0, 5, 15, 6.0E7, 0L, 0.0);

    public boolean n_1700_B(c_1514_x pos) {
        return (double)(pos.getX() + 1) > this.P_1922_E() && (double)pos.getX() < this.v_4262_N() && (double)(pos.getZ() + 1) > this.u_1723_Y() && (double)pos.getZ() < this.w_1484_f();
    }

    public boolean n_1700_B(Y_1387_d range) {
        return (double)range.G_564_y() > this.P_1922_E() && (double)range.J_1907_R() < this.v_4262_N() && (double)range.P_1922_E() > this.u_1723_Y() && (double)range.R_4764_Y() < this.w_1484_f();
    }

    public boolean n_1700_B(I_4817_s bb) {
        return bb.maxX > this.P_1922_E() && bb.minX < this.v_4262_N() && bb.maxZ > this.u_1723_Y() && bb.minZ < this.w_1484_f();
    }

    public double n_1700_B(N_4263_v entityIn) {
        return this.n_1700_B(entityIn.O_3598_v(), entityIn.l_2647_k());
    }

    public s_1395_c R_4764_Y() {
        return this.s_956_w.P_4830_p();
    }

    public double n_1700_B(double x, double z) {
        double d0 = z - this.u_1723_Y();
        double d1 = this.w_1484_f() - z;
        double d2 = x - this.P_1922_E();
        double d3 = this.v_4262_N() - x;
        double d4 = Math.min(d2, d3);
        d4 = Math.min(d4, d0);
        return Math.min(d4, d1);
    }

    public h_2866_S G_564_y() {
        return this.s_956_w.t_148_a();
    }

    public double P_1922_E() {
        return this.s_956_w.n_1700_B();
    }

    public double u_1723_Y() {
        return this.s_956_w.R_4764_Y();
    }

    public double v_4262_N() {
        return this.s_956_w.J_1907_R();
    }

    public double w_1484_f() {
        return this.s_956_w.G_564_y();
    }

    public double n_1700_B() {
        return this.v_4262_N;
    }

    public double J_1907_R() {
        return this.w_1484_f;
    }

    public void J_1907_R(double x, double z) {
        this.v_4262_N = x;
        this.w_1484_f = z;
        this.s_956_w.u_2550_I();
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.n_1700_B(this, x, z);
        }
    }

    public double t_148_a() {
        return this.s_956_w.P_1922_E();
    }

    public long s_956_w() {
        return this.s_956_w.v_4262_N();
    }

    public double u_2550_I() {
        return this.s_956_w.w_1484_f();
    }

    public void n_1700_B(double newSize) {
        this.s_956_w = new G_564_y(newSize);
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.n_1700_B(this, newSize);
        }
    }

    public void n_1700_B(double oldSize, double newSize, long time) {
        this.s_956_w = oldSize == newSize ? new G_564_y(newSize) : new J_1907_R(oldSize, newSize, time);
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.n_1700_B(this, oldSize, newSize, time);
        }
    }

    protected List<BorderChangeListener> M_588_G() {
        return Lists.newArrayList(this.n_1700_B);
    }

    public void n_1700_B(BorderChangeListener listener) {
        this.n_1700_B.add(listener);
    }

    public void n_1700_B(int size) {
        this.t_148_a = size;
        this.s_956_w.s_956_w();
    }

    public int P_4830_p() {
        return this.t_148_a;
    }

    public double h_1847_R() {
        return this.G_564_y;
    }

    public void J_1907_R(double bufferSize) {
        this.G_564_y = bufferSize;
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.R_4764_Y(this, bufferSize);
        }
    }

    public double Q_4569_t() {
        return this.R_4764_Y;
    }

    public void R_4764_Y(double newAmount) {
        this.R_4764_Y = newAmount;
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.J_1907_R(this, newAmount);
        }
    }

    public double M_182_A() {
        return this.s_956_w.u_1723_Y();
    }

    public int t_1786_h() {
        return this.P_1922_E;
    }

    public void J_1907_R(int warningTime) {
        this.P_1922_E = warningTime;
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.n_1700_B(this, warningTime);
        }
    }

    public int multiplayerClientSuggestionProvider() {
        return this.u_1723_Y;
    }

    public void R_4764_Y(int warningDistance) {
        this.u_1723_Y = warningDistance;
        for (BorderChangeListener iborderlistener : this.M_588_G()) {
            iborderlistener.J_1907_R(this, warningDistance);
        }
    }

    public void w_1457_N() {
        this.s_956_w = this.s_956_w.M_588_G();
    }

    public R_4764_Y Y_601_j() {
        return new R_4764_Y(this);
    }

    public void n_1700_B(R_4764_Y serializer) {
        this.J_1907_R(serializer.n_1700_B(), serializer.J_1907_R());
        this.R_4764_Y(serializer.R_4764_Y());
        this.J_1907_R(serializer.G_564_y());
        this.R_4764_Y(serializer.P_1922_E());
        this.J_1907_R(serializer.u_1723_Y());
        if (serializer.w_1484_f() > 0L) {
            this.n_1700_B(serializer.v_4262_N(), serializer.t_148_a(), serializer.w_1484_f());
        } else {
            this.n_1700_B(serializer.v_4262_N());
        }
    }

    class G_564_y
    implements n_1700_B {
        private final double J_1907_R;
        private double R_4764_Y;
        private double G_564_y;
        private double P_1922_E;
        private double u_1723_Y;
        private s_1395_c v_4262_N;

        public G_564_y(double size) {
            this.J_1907_R = size;
            this.h_1847_R();
        }

        @Override
        public double n_1700_B() {
            return this.R_4764_Y;
        }

        @Override
        public double J_1907_R() {
            return this.P_1922_E;
        }

        @Override
        public double R_4764_Y() {
            return this.G_564_y;
        }

        @Override
        public double G_564_y() {
            return this.u_1723_Y;
        }

        @Override
        public double P_1922_E() {
            return this.J_1907_R;
        }

        @Override
        public h_2866_S t_148_a() {
            return h_2866_S.R_4764_Y;
        }

        @Override
        public double u_1723_Y() {
            return 0.0;
        }

        @Override
        public long v_4262_N() {
            return 0L;
        }

        @Override
        public double w_1484_f() {
            return this.J_1907_R;
        }

        private void h_1847_R() {
            this.R_4764_Y = Math.max(T_603_v.this.n_1700_B() - this.J_1907_R / 2.0, (double)(-T_603_v.this.t_148_a));
            this.G_564_y = Math.max(T_603_v.this.J_1907_R() - this.J_1907_R / 2.0, (double)(-T_603_v.this.t_148_a));
            this.P_1922_E = Math.min(T_603_v.this.n_1700_B() + this.J_1907_R / 2.0, (double)T_603_v.this.t_148_a);
            this.u_1723_Y = Math.min(T_603_v.this.J_1907_R() + this.J_1907_R / 2.0, (double)T_603_v.this.t_148_a);
            this.v_4262_N = x_268_Y.n_1700_B(x_268_Y.n_1700_B, x_268_Y.n_1700_B(Math.floor(this.n_1700_B()), Double.NEGATIVE_INFINITY, Math.floor(this.R_4764_Y()), Math.ceil(this.J_1907_R()), Double.POSITIVE_INFINITY, Math.ceil(this.G_564_y())), BooleanOp.P_1922_E);
        }

        @Override
        public void s_956_w() {
            this.h_1847_R();
        }

        @Override
        public void u_2550_I() {
            this.h_1847_R();
        }

        @Override
        public n_1700_B M_588_G() {
            return this;
        }

        @Override
        public s_1395_c P_4830_p() {
            return this.v_4262_N;
        }
    }

    static interface n_1700_B {
        public double n_1700_B();

        public double J_1907_R();

        public double R_4764_Y();

        public double G_564_y();

        public double P_1922_E();

        public double u_1723_Y();

        public long v_4262_N();

        public double w_1484_f();

        public h_2866_S t_148_a();

        public void s_956_w();

        public void u_2550_I();

        public n_1700_B M_588_G();

        public s_1395_c P_4830_p();
    }

    class J_1907_R
    implements n_1700_B {
        private final double J_1907_R;
        private final double R_4764_Y;
        private final long G_564_y;
        private final long P_1922_E;
        private final double u_1723_Y;

        private J_1907_R(double oldSize, double newSize, long transitionTime) {
            this.J_1907_R = oldSize;
            this.R_4764_Y = newSize;
            this.u_1723_Y = transitionTime;
            this.P_1922_E = j_3341_s.J_1907_R();
            this.G_564_y = this.P_1922_E + transitionTime;
        }

        @Override
        public double n_1700_B() {
            return Math.max(T_603_v.this.n_1700_B() - this.P_1922_E() / 2.0, (double)(-T_603_v.this.t_148_a));
        }

        @Override
        public double R_4764_Y() {
            return Math.max(T_603_v.this.J_1907_R() - this.P_1922_E() / 2.0, (double)(-T_603_v.this.t_148_a));
        }

        @Override
        public double J_1907_R() {
            return Math.min(T_603_v.this.n_1700_B() + this.P_1922_E() / 2.0, (double)T_603_v.this.t_148_a);
        }

        @Override
        public double G_564_y() {
            return Math.min(T_603_v.this.J_1907_R() + this.P_1922_E() / 2.0, (double)T_603_v.this.t_148_a);
        }

        @Override
        public double P_1922_E() {
            double d0 = (double)(j_3341_s.J_1907_R() - this.P_1922_E) / this.u_1723_Y;
            return d0 < 1.0 ? u_530_F.G_564_y(d0, this.J_1907_R, this.R_4764_Y) : this.R_4764_Y;
        }

        @Override
        public double u_1723_Y() {
            return Math.abs(this.J_1907_R - this.R_4764_Y) / (double)(this.G_564_y - this.P_1922_E);
        }

        @Override
        public long v_4262_N() {
            return this.G_564_y - j_3341_s.J_1907_R();
        }

        @Override
        public double w_1484_f() {
            return this.R_4764_Y;
        }

        @Override
        public h_2866_S t_148_a() {
            return this.R_4764_Y < this.J_1907_R ? h_2866_S.J_1907_R : h_2866_S.n_1700_B;
        }

        @Override
        public void u_2550_I() {
        }

        @Override
        public void s_956_w() {
        }

        @Override
        public n_1700_B M_588_G() {
            n_1700_B n_1700_B2;
            if (this.v_4262_N() <= 0L) {
                T_603_v t_603_v = T_603_v.this;
                Objects.requireNonNull(t_603_v);
                n_1700_B2 = t_603_v.new G_564_y(this.R_4764_Y);
            } else {
                n_1700_B2 = this;
            }
            return n_1700_B2;
        }

        @Override
        public s_1395_c P_4830_p() {
            return x_268_Y.n_1700_B(x_268_Y.n_1700_B, x_268_Y.n_1700_B(Math.floor(this.n_1700_B()), Double.NEGATIVE_INFINITY, Math.floor(this.R_4764_Y()), Math.ceil(this.J_1907_R()), Double.POSITIVE_INFINITY, Math.ceil(this.G_564_y())), BooleanOp.P_1922_E);
        }
    }

    public static class R_4764_Y {
        private final double n_1700_B;
        private final double J_1907_R;
        private final double R_4764_Y;
        private final double G_564_y;
        private final int P_1922_E;
        private final int u_1723_Y;
        private final double v_4262_N;
        private final long w_1484_f;
        private final double t_148_a;

        private R_4764_Y(double centerX, double centerZ, double damagePerBlock, double damageBuffer, int warningDistance, int warningTime, double size, long sizeLerpTime, double sizeLerpTarget) {
            this.n_1700_B = centerX;
            this.J_1907_R = centerZ;
            this.R_4764_Y = damagePerBlock;
            this.G_564_y = damageBuffer;
            this.P_1922_E = warningDistance;
            this.u_1723_Y = warningTime;
            this.v_4262_N = size;
            this.w_1484_f = sizeLerpTime;
            this.t_148_a = sizeLerpTarget;
        }

        private R_4764_Y(T_603_v border) {
            this.n_1700_B = border.n_1700_B();
            this.J_1907_R = border.J_1907_R();
            this.R_4764_Y = border.Q_4569_t();
            this.G_564_y = border.h_1847_R();
            this.P_1922_E = border.multiplayerClientSuggestionProvider();
            this.u_1723_Y = border.t_1786_h();
            this.v_4262_N = border.t_148_a();
            this.w_1484_f = border.s_956_w();
            this.t_148_a = border.u_2550_I();
        }

        public double n_1700_B() {
            return this.n_1700_B;
        }

        public double J_1907_R() {
            return this.J_1907_R;
        }

        public double R_4764_Y() {
            return this.R_4764_Y;
        }

        public double G_564_y() {
            return this.G_564_y;
        }

        public int P_1922_E() {
            return this.P_1922_E;
        }

        public int u_1723_Y() {
            return this.u_1723_Y;
        }

        public double v_4262_N() {
            return this.v_4262_N;
        }

        public long w_1484_f() {
            return this.w_1484_f;
        }

        public double t_148_a() {
            return this.t_148_a;
        }

        public static R_4764_Y n_1700_B(DynamicLike<?> dynamic, R_4764_Y defaultIn) {
            double d0 = dynamic.get("BorderCenterX").asDouble(defaultIn.n_1700_B);
            double d1 = dynamic.get("BorderCenterZ").asDouble(defaultIn.J_1907_R);
            double d2 = dynamic.get("BorderSize").asDouble(defaultIn.v_4262_N);
            long i = dynamic.get("BorderSizeLerpTime").asLong(defaultIn.w_1484_f);
            double d3 = dynamic.get("BorderSizeLerpTarget").asDouble(defaultIn.t_148_a);
            double d4 = dynamic.get("BorderSafeZone").asDouble(defaultIn.G_564_y);
            double d5 = dynamic.get("BorderDamagePerBlock").asDouble(defaultIn.R_4764_Y);
            int j = dynamic.get("BorderWarningBlocks").asInt(defaultIn.P_1922_E);
            int k = dynamic.get("BorderWarningTime").asInt(defaultIn.u_1723_Y);
            return new R_4764_Y(d0, d1, d5, d4, j, k, d2, i, d3);
        }

        public void n_1700_B(U_2912_j nbt) {
            nbt.n_1700_B("BorderCenterX", this.n_1700_B);
            nbt.n_1700_B("BorderCenterZ", this.J_1907_R);
            nbt.n_1700_B("BorderSize", this.v_4262_N);
            nbt.n_1700_B("BorderSizeLerpTime", this.w_1484_f);
            nbt.n_1700_B("BorderSafeZone", this.G_564_y);
            nbt.n_1700_B("BorderDamagePerBlock", this.R_4764_Y);
            nbt.n_1700_B("BorderSizeLerpTarget", this.t_148_a);
            nbt.n_1700_B("BorderWarningBlocks", (double)this.P_1922_E);
            nbt.n_1700_B("BorderWarningTime", (double)this.u_1723_Y);
        }
    }
}


