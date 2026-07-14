/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import lightning.product.D_2103_L;
import lightning.product.PackRepository;
import lightning.product.g_2336_b;
import lightning.product.PackSource;
import lightning.product.u_4608_G;
import lightning.product.x_282_a;

public class I_2861_N {
    private final PackRepository n_1700_B;
    private final List<D_2103_L> J_1907_R;
    private final List<D_2103_L> R_4764_Y;
    private final Function<D_2103_L, g_2336_b> G_564_y;
    private final Runnable P_1922_E;
    private final Consumer<PackRepository> u_1723_Y;

    public I_2861_N(Runnable p_i242059_1_, Function<D_2103_L, g_2336_b> p_i242059_2_, PackRepository p_i242059_3_, Consumer<PackRepository> p_i242059_4_) {
        this.P_1922_E = p_i242059_1_;
        this.G_564_y = p_i242059_2_;
        this.n_1700_B = p_i242059_3_;
        this.J_1907_R = Lists.newArrayList(p_i242059_3_.P_1922_E());
        Collections.reverse(this.J_1907_R);
        this.R_4764_Y = Lists.newArrayList(p_i242059_3_.R_4764_Y());
        this.R_4764_Y.removeAll(this.J_1907_R);
        this.u_1723_Y = p_i242059_4_;
    }

    public Stream<G_564_y> n_1700_B() {
        return this.R_4764_Y.stream().map(p_238870_1_ -> new J_1907_R((D_2103_L)p_238870_1_));
    }

    public Stream<G_564_y> J_1907_R() {
        return this.J_1907_R.stream().map(p_238866_1_ -> new R_4764_Y((D_2103_L)p_238866_1_));
    }

    public void R_4764_Y() {
        this.n_1700_B.n_1700_B((Collection)Lists.reverse(this.J_1907_R).stream().map(D_2103_L::P_1922_E).collect(ImmutableList.toImmutableList()));
        this.u_1723_Y.accept(this.n_1700_B);
    }

    public void G_564_y() {
        this.n_1700_B.n_1700_B();
        this.J_1907_R.retainAll(this.n_1700_B.R_4764_Y());
        this.R_4764_Y.clear();
        this.R_4764_Y.addAll(this.n_1700_B.R_4764_Y());
        this.R_4764_Y.removeAll(this.J_1907_R);
    }

    class R_4764_Y
    extends n_1700_B {
        public R_4764_Y(D_2103_L p_i232298_2_) {
            super(p_i232298_2_);
        }

        @Override
        protected List<D_2103_L> n_1700_B() {
            return I_2861_N.this.J_1907_R;
        }

        @Override
        protected List<D_2103_L> J_1907_R() {
            return I_2861_N.this.R_4764_Y;
        }

        @Override
        public boolean Q_4569_t() {
            return true;
        }

        @Override
        public void M_182_A() {
        }

        @Override
        public void t_1786_h() {
            this.s_956_w();
        }
    }

    class J_1907_R
    extends n_1700_B {
        public J_1907_R(D_2103_L p_i232299_2_) {
            super(p_i232299_2_);
        }

        @Override
        protected List<D_2103_L> n_1700_B() {
            return I_2861_N.this.R_4764_Y;
        }

        @Override
        protected List<D_2103_L> J_1907_R() {
            return I_2861_N.this.J_1907_R;
        }

        @Override
        public boolean Q_4569_t() {
            return false;
        }

        @Override
        public void M_182_A() {
            this.s_956_w();
        }

        @Override
        public void t_1786_h() {
        }
    }

    public static interface G_564_y {
        public g_2336_b R_4764_Y();

        public u_4608_G G_564_y();

        public x_282_a P_1922_E();

        public x_282_a u_1723_Y();

        public PackSource v_4262_N();

        default public x_282_a multiplayerClientSuggestionProvider() {
            return this.v_4262_N().decorate(this.u_1723_Y());
        }

        public boolean w_1484_f();

        public boolean t_148_a();

        public void M_182_A();

        public void t_1786_h();

        public void M_588_G();

        public void h_1847_R();

        public boolean Q_4569_t();

        default public boolean w_1457_N() {
            return !this.Q_4569_t();
        }

        default public boolean Y_601_j() {
            return this.Q_4569_t() && !this.t_148_a();
        }

        public boolean u_2550_I();

        public boolean P_4830_p();
    }

    abstract class n_1700_B
    implements G_564_y {
        private final D_2103_L J_1907_R;

        public n_1700_B(D_2103_L p_i232297_2_) {
            this.J_1907_R = p_i232297_2_;
        }

        protected abstract List<D_2103_L> n_1700_B();

        protected abstract List<D_2103_L> J_1907_R();

        @Override
        public g_2336_b R_4764_Y() {
            return I_2861_N.this.G_564_y.apply(this.J_1907_R);
        }

        @Override
        public u_4608_G G_564_y() {
            return this.J_1907_R.R_4764_Y();
        }

        @Override
        public x_282_a P_1922_E() {
            return this.J_1907_R.n_1700_B();
        }

        @Override
        public x_282_a u_1723_Y() {
            return this.J_1907_R.J_1907_R();
        }

        @Override
        public PackSource v_4262_N() {
            return this.J_1907_R.t_148_a();
        }

        @Override
        public boolean w_1484_f() {
            return this.J_1907_R.v_4262_N();
        }

        @Override
        public boolean t_148_a() {
            return this.J_1907_R.u_1723_Y();
        }

        protected void s_956_w() {
            this.n_1700_B().remove(this.J_1907_R);
            this.J_1907_R.w_1484_f().n_1700_B(this.J_1907_R(), this.J_1907_R, Function.identity(), true);
            I_2861_N.this.P_1922_E.run();
        }

        protected void n_1700_B(int p_238879_1_) {
            List<D_2103_L> list = this.n_1700_B();
            int i = list.indexOf(this.J_1907_R);
            list.remove(i);
            list.add(i + p_238879_1_, this.J_1907_R);
            I_2861_N.this.P_1922_E.run();
        }

        @Override
        public boolean u_2550_I() {
            List<D_2103_L> list = this.n_1700_B();
            int i = list.indexOf(this.J_1907_R);
            return i > 0 && !list.get(i - 1).v_4262_N();
        }

        @Override
        public void M_588_G() {
            this.n_1700_B(-1);
        }

        @Override
        public boolean P_4830_p() {
            List<D_2103_L> list = this.n_1700_B();
            int i = list.indexOf(this.J_1907_R);
            return i >= 0 && i < list.size() - 1 && !list.get(i + 1).v_4262_N();
        }

        @Override
        public void h_1847_R() {
            this.n_1700_B(1);
        }
    }
}


