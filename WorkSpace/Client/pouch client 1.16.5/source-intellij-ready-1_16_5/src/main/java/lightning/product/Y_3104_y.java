/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.C_3615_s;
import lightning.product.TickList;
import lightning.product.BoundingBox;
import lightning.product.Q_2410_O;
import lightning.product.U_2912_j;
import lightning.product.V_4824_J;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.q_2896_o;
import lightning.product.CrashReportCategory;

public class Y_3104_y<T>
implements TickList<T> {
    protected final Predicate<T> n_1700_B;
    private final Function<T, g_2336_b> J_1907_R;
    private final Set<Q_2410_O<T>> R_4764_Y = Sets.newHashSet();
    private final TreeSet<Q_2410_O<T>> G_564_y = Sets.newTreeSet(Q_2410_O.n_1700_B());
    private final e_3591_l P_1922_E;
    private final Queue<Q_2410_O<T>> u_1723_Y = Queues.newArrayDeque();
    private final List<Q_2410_O<T>> v_4262_N = Lists.newArrayList();
    private final Consumer<Q_2410_O<T>> w_1484_f;

    public Y_3104_y(e_3591_l p_i231625_1_, Predicate<T> p_i231625_2_, Function<T, g_2336_b> p_i231625_3_, Consumer<Q_2410_O<T>> p_i231625_4_) {
        this.n_1700_B = p_i231625_2_;
        this.J_1907_R = p_i231625_3_;
        this.P_1922_E = p_i231625_1_;
        this.w_1484_f = p_i231625_4_;
    }

    public void n_1700_B() {
        Q_2410_O<T> nextticklistentry1;
        int i = this.G_564_y.size();
        if (i != this.R_4764_Y.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        }
        if (i > 65536) {
            i = 65536;
        }
        C_3615_s serverchunkprovider = this.P_1922_E.Y_259_p();
        Iterator<Q_2410_O<T>> iterator = this.G_564_y.iterator();
        this.P_1922_E.D_4792_h().n_1700_B("cleaning");
        while (i > 0 && iterator.hasNext()) {
            Q_2410_O<T> nextticklistentry = iterator.next();
            if (nextticklistentry.J_1907_R > this.P_1922_E.X_933_l()) break;
            if (!serverchunkprovider.n_1700_B(nextticklistentry.n_1700_B)) continue;
            iterator.remove();
            this.R_4764_Y.remove(nextticklistentry);
            this.u_1723_Y.add(nextticklistentry);
            --i;
        }
        this.P_1922_E.D_4792_h().J_1907_R("ticking");
        while ((nextticklistentry1 = this.u_1723_Y.poll()) != null) {
            if (serverchunkprovider.n_1700_B(nextticklistentry1.n_1700_B)) {
                try {
                    this.v_4262_N.add(nextticklistentry1);
                    this.w_1484_f.accept(nextticklistentry1);
                    continue;
                }
                catch (Throwable throwable) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Exception while ticking");
                    CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being ticked");
                    CrashReportCategory.n_1700_B(crashreportcategory, nextticklistentry1.n_1700_B, null);
                    throw new ReportedException(crashreport);
                }
            }
            this.n_1700_B(nextticklistentry1.n_1700_B, nextticklistentry1.J_1907_R(), 0);
        }
        this.P_1922_E.D_4792_h().R_4764_Y();
        this.v_4262_N.clear();
        this.u_1723_Y.clear();
    }

    @Override
    public boolean J_1907_R(c_1514_x pos, T obj) {
        return this.u_1723_Y.contains(new Q_2410_O<T>(pos, obj));
    }

    public List<Q_2410_O<T>> n_1700_B(Y_1387_d pos, boolean remove, boolean skipCompleted) {
        int i = (pos.J_1907_R << 4) - 2;
        int j = i + 16 + 2;
        int k = (pos.R_4764_Y << 4) - 2;
        int l = k + 16 + 2;
        return this.n_1700_B(new BoundingBox(i, 0, k, j, 256, l), remove, skipCompleted);
    }

    public List<Q_2410_O<T>> n_1700_B(BoundingBox p_205366_1_, boolean remove, boolean skipCompleted) {
        List<Q_2410_O<T>> list = this.n_1700_B((List)null, this.G_564_y, p_205366_1_, remove);
        if (remove && list != null) {
            this.R_4764_Y.removeAll(list);
        }
        list = this.n_1700_B(list, this.u_1723_Y, p_205366_1_, remove);
        if (!skipCompleted) {
            list = this.n_1700_B(list, this.v_4262_N, p_205366_1_, remove);
        }
        return list == null ? Collections.emptyList() : list;
    }

    @Nullable
    private List<Q_2410_O<T>> n_1700_B(@Nullable List<Q_2410_O<T>> result, Collection<Q_2410_O<T>> entries, BoundingBox bb, boolean remove) {
        Iterator<Q_2410_O<T>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Q_2410_O<T> nextticklistentry = iterator.next();
            c_1514_x blockpos = nextticklistentry.n_1700_B;
            if (blockpos.getX() < bb.n_1700_B || blockpos.getX() >= bb.G_564_y || blockpos.getZ() < bb.R_4764_Y || blockpos.getZ() >= bb.u_1723_Y) continue;
            if (remove) {
                iterator.remove();
            }
            if (result == null) {
                result = Lists.newArrayList();
            }
            result.add(nextticklistentry);
        }
        return result;
    }

    public void n_1700_B(BoundingBox area, c_1514_x offset) {
        for (Q_2410_O<T> nextticklistentry : this.n_1700_B(area, false, false)) {
            if (!area.J_1907_R(nextticklistentry.n_1700_B)) continue;
            c_1514_x blockpos = nextticklistentry.n_1700_B.add(offset);
            T t = nextticklistentry.J_1907_R();
            this.n_1700_B(new Q_2410_O<T>(blockpos, t, nextticklistentry.J_1907_R, nextticklistentry.R_4764_Y));
        }
    }

    public q_2896_o n_1700_B(Y_1387_d p_219503_1_) {
        List<Q_2410_O<T>> list = this.n_1700_B(p_219503_1_, false, true);
        return Y_3104_y.n_1700_B(this.J_1907_R, list, this.P_1922_E.X_933_l());
    }

    private static <T> q_2896_o n_1700_B(Function<T, g_2336_b> p_219502_0_, Iterable<Q_2410_O<T>> p_219502_1_, long p_219502_2_) {
        q_2896_o listnbt = new q_2896_o();
        for (Q_2410_O<T> nextticklistentry : p_219502_1_) {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("i", p_219502_0_.apply(nextticklistentry.J_1907_R()).toString());
            compoundnbt.J_1907_R("x", nextticklistentry.n_1700_B.getX());
            compoundnbt.J_1907_R("y", nextticklistentry.n_1700_B.getY());
            compoundnbt.J_1907_R("z", nextticklistentry.n_1700_B.getZ());
            compoundnbt.J_1907_R("t", (int)(nextticklistentry.J_1907_R - p_219502_2_));
            compoundnbt.J_1907_R("p", nextticklistentry.R_4764_Y.n_1700_B());
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, T itemIn) {
        return this.R_4764_Y.contains(new Q_2410_O<T>(pos, itemIn));
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime, V_4824_J priority) {
        if (!this.n_1700_B.test(itemIn)) {
            this.n_1700_B(new Q_2410_O<T>(pos, itemIn, (long)scheduledTime + this.P_1922_E.X_933_l(), priority));
        }
    }

    private void n_1700_B(Q_2410_O<T> p_219504_1_) {
        if (!this.R_4764_Y.contains(p_219504_1_)) {
            this.R_4764_Y.add(p_219504_1_);
            this.G_564_y.add(p_219504_1_);
        }
    }

    public int J_1907_R() {
        return this.R_4764_Y.size();
    }
}


