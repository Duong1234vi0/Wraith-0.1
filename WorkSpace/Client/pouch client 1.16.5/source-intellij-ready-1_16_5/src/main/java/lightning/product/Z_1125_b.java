/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashBasedTable
 *  com.google.common.collect.Table
 *  com.google.common.primitives.UnsignedLong
 *  com.mojang.serialization.Dynamic
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.primitives.UnsignedLong;
import com.mojang.serialization.Dynamic;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;
import lightning.product.TimerCallback;
import lightning.product.O_1568_Z;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.q_2896_o;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Z_1125_b<T> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final O_1568_Z<T> J_1907_R;
    private final Queue<n_1700_B<T>> R_4764_Y = new PriorityQueue<n_1700_B<T>>(Z_1125_b.R_4764_Y());
    private UnsignedLong G_564_y = UnsignedLong.ZERO;
    private final Table<String, Long, n_1700_B<T>> P_1922_E = HashBasedTable.create();

    private static <T> Comparator<n_1700_B<T>> R_4764_Y() {
        return Comparator.comparingLong(p_227578_0_ -> p_227578_0_.n_1700_B).thenComparing(p_227577_0_ -> p_227577_0_.J_1907_R);
    }

    public Z_1125_b(O_1568_Z<T> p_i232176_1_, Stream<Dynamic<Tag>> p_i232176_2_) {
        this(p_i232176_1_);
        this.R_4764_Y.clear();
        this.P_1922_E.clear();
        this.G_564_y = UnsignedLong.ZERO;
        p_i232176_2_.forEach(p_237478_1_ -> {
            if (!(p_237478_1_.getValue() instanceof U_2912_j)) {
                n_1700_B.warn("Invalid format of events: {}", p_237478_1_);
            } else {
                this.n_1700_B((U_2912_j)p_237478_1_.getValue());
            }
        });
    }

    public Z_1125_b(O_1568_Z<T> p_i51188_1_) {
        this.J_1907_R = p_i51188_1_;
    }

    public void n_1700_B(T p_216331_1_, long gameTime) {
        n_1700_B<T> entry;
        while ((entry = this.R_4764_Y.peek()) != null && entry.n_1700_B <= gameTime) {
            this.R_4764_Y.remove();
            this.P_1922_E.remove((Object)entry.R_4764_Y, (Object)gameTime);
            entry.G_564_y.n_1700_B(p_216331_1_, this, gameTime);
        }
        return;
    }

    public void n_1700_B(String p_227576_1_, long p_227576_2_, TimerCallback<T> p_227576_4_) {
        if (!this.P_1922_E.contains((Object)p_227576_1_, (Object)p_227576_2_)) {
            this.G_564_y = this.G_564_y.plus(UnsignedLong.ONE);
            n_1700_B<T> entry = new n_1700_B<T>(p_227576_2_, this.G_564_y, p_227576_1_, p_227576_4_);
            this.P_1922_E.put((Object)p_227576_1_, (Object)p_227576_2_, entry);
            this.R_4764_Y.add(entry);
        }
    }

    public int n_1700_B(String p_227575_1_) {
        Collection collection = this.P_1922_E.row((Object)p_227575_1_).values();
        collection.forEach(this.R_4764_Y::remove);
        int i = collection.size();
        collection.clear();
        return i;
    }

    public Set<String> n_1700_B() {
        return Collections.unmodifiableSet(this.P_1922_E.rowKeySet());
    }

    private void n_1700_B(U_2912_j p_216329_1_) {
        U_2912_j compoundnbt = p_216329_1_.M_182_A("Callback");
        TimerCallback<T> itimercallback = this.J_1907_R.n_1700_B(compoundnbt);
        if (itimercallback != null) {
            String s = p_216329_1_.M_588_G("Name");
            long i = p_216329_1_.t_148_a("TriggerTime");
            this.n_1700_B(s, i, itimercallback);
        }
    }

    private U_2912_j n_1700_B(n_1700_B<T> p_216332_1_) {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", p_216332_1_.R_4764_Y);
        compoundnbt.n_1700_B("TriggerTime", p_216332_1_.n_1700_B);
        compoundnbt.n_1700_B("Callback", this.J_1907_R.n_1700_B(p_216332_1_.G_564_y));
        return compoundnbt;
    }

    public q_2896_o J_1907_R() {
        q_2896_o listnbt = new q_2896_o();
        this.R_4764_Y.stream().sorted(Z_1125_b.R_4764_Y()).map(this::n_1700_B).forEach(listnbt::add);
        return listnbt;
    }

    public static class n_1700_B<T> {
        public final long n_1700_B;
        public final UnsignedLong J_1907_R;
        public final String R_4764_Y;
        public final TimerCallback<T> G_564_y;

        private n_1700_B(long p_i50837_1_, UnsignedLong p_i50837_3_, String p_i50837_4_, TimerCallback<T> p_i50837_5_) {
            this.n_1700_B = p_i50837_1_;
            this.J_1907_R = p_i50837_3_;
            this.R_4764_Y = p_i50837_4_;
            this.G_564_y = p_i50837_5_;
        }
    }
}


