/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lightning.product.TickList;
import lightning.product.Q_2410_O;
import lightning.product.U_2912_j;
import lightning.product.V_4824_J;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.q_2896_o;

public class x_4674_u<T>
implements TickList<T> {
    private final List<n_1700_B<T>> n_1700_B;
    private final Function<T, g_2336_b> J_1907_R;

    public x_4674_u(Function<T, g_2336_b> p_i231603_1_, List<Q_2410_O<T>> p_i231603_2_, long p_i231603_3_) {
        this(p_i231603_1_, p_i231603_2_.stream().map(p_234854_2_ -> new n_1700_B(p_234854_2_.J_1907_R(), p_234854_2_.n_1700_B, (int)(p_234854_2_.J_1907_R - p_i231603_3_), p_234854_2_.R_4764_Y)).collect(Collectors.toList()));
    }

    private x_4674_u(Function<T, g_2336_b> p_i50010_1_, List<n_1700_B<T>> p_i50010_2_) {
        this.n_1700_B = p_i50010_2_;
        this.J_1907_R = p_i50010_1_;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, T itemIn) {
        return false;
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime, V_4824_J priority) {
        this.n_1700_B.add(new n_1700_B<T>(itemIn, pos, scheduledTime, priority));
    }

    @Override
    public boolean J_1907_R(c_1514_x pos, T obj) {
        return false;
    }

    public q_2896_o n_1700_B() {
        q_2896_o listnbt = new q_2896_o();
        for (n_1700_B<T> tickholder : this.n_1700_B) {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("i", this.J_1907_R.apply(tickholder.G_564_y).toString());
            compoundnbt.J_1907_R("x", tickholder.n_1700_B.getX());
            compoundnbt.J_1907_R("y", tickholder.n_1700_B.getY());
            compoundnbt.J_1907_R("z", tickholder.n_1700_B.getZ());
            compoundnbt.J_1907_R("t", tickholder.J_1907_R);
            compoundnbt.J_1907_R("p", tickholder.R_4764_Y.n_1700_B());
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }

    public static <T> x_4674_u<T> n_1700_B(q_2896_o p_222984_0_, Function<T, g_2336_b> p_222984_1_, Function<g_2336_b, T> p_222984_2_) {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < p_222984_0_.size(); ++i) {
            U_2912_j compoundnbt = p_222984_0_.n_1700_B(i);
            T t = p_222984_2_.apply(new g_2336_b(compoundnbt.M_588_G("i")));
            if (t == null) continue;
            c_1514_x blockpos = new c_1514_x(compoundnbt.w_1484_f("x"), compoundnbt.w_1484_f("y"), compoundnbt.w_1484_f("z"));
            list.add(new n_1700_B<T>(t, blockpos, compoundnbt.w_1484_f("t"), V_4824_J.n_1700_B(compoundnbt.w_1484_f("p"))));
        }
        return new x_4674_u<T>(p_222984_1_, list);
    }

    public void n_1700_B(TickList<T> p_234855_1_) {
        this.n_1700_B.forEach(p_234856_1_ -> p_234855_1_.n_1700_B(p_234856_1_.n_1700_B, p_234856_1_.G_564_y, p_234856_1_.J_1907_R, p_234856_1_.R_4764_Y));
    }

    static class n_1700_B<T> {
        private final T G_564_y;
        public final c_1514_x n_1700_B;
        public final int J_1907_R;
        public final V_4824_J R_4764_Y;

        private n_1700_B(T p_i231604_1_, c_1514_x p_i231604_2_, int p_i231604_3_, V_4824_J p_i231604_4_) {
            this.G_564_y = p_i231604_1_;
            this.n_1700_B = p_i231604_2_;
            this.J_1907_R = p_i231604_3_;
            this.R_4764_Y = p_i231604_4_;
        }

        public String toString() {
            return String.valueOf(this.G_564_y) + ": " + String.valueOf(this.n_1700_B) + ", " + this.J_1907_R + ", " + String.valueOf((Object)this.R_4764_Y);
        }
    }
}


