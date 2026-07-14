/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Stopwatch
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.objects.Object2LongMap
 *  it.unimi.dsi.fastutil.objects.Object2LongMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.TestFunction;
import lightning.product.H_4938_m;
import lightning.product.V_2718_G;
import lightning.product.W_2163_m;
import lightning.product.a_3322_s;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.j_2644_e;
import lightning.product.GameTestListener;
import lightning.product.v_4829_R;

public class s_4514_h {
    private final TestFunction n_1700_B;
    @Nullable
    private c_1514_x J_1907_R;
    private final e_3591_l R_4764_Y;
    private final Collection<GameTestListener> G_564_y = Lists.newArrayList();
    private final int P_1922_E;
    private final Collection<H_4938_m> u_1723_Y = Lists.newCopyOnWriteArrayList();
    private Object2LongMap<Runnable> v_4262_N = new Object2LongOpenHashMap();
    private long w_1484_f;
    private long t_148_a;
    private boolean s_956_w = false;
    private final Stopwatch u_2550_I = Stopwatch.createUnstarted();
    private boolean M_588_G = false;
    private final W_2163_m P_4830_p;
    @Nullable
    private Throwable h_1847_R;

    public s_4514_h(TestFunction p_i232556_1_, W_2163_m p_i232556_2_, e_3591_l p_i232556_3_) {
        this.n_1700_B = p_i232556_1_;
        this.R_4764_Y = p_i232556_3_;
        this.P_1922_E = p_i232556_1_.R_4764_Y();
        this.P_4830_p = p_i232556_1_.v_4262_N().n_1700_B(p_i232556_2_);
    }

    void n_1700_B(c_1514_x p_229503_1_) {
        this.J_1907_R = p_229503_1_;
    }

    void n_1700_B() {
        this.w_1484_f = this.R_4764_Y.X_933_l() + 1L + this.n_1700_B.u_1723_Y();
        this.u_2550_I.start();
    }

    public void J_1907_R() {
        if (!this.t_148_a()) {
            this.t_148_a = this.R_4764_Y.X_933_l() - this.w_1484_f;
            if (this.t_148_a >= 0L) {
                if (this.t_148_a == 0L) {
                    this.M_182_A();
                }
                ObjectIterator objectiterator = this.v_4262_N.object2LongEntrySet().iterator();
                while (objectiterator.hasNext()) {
                    Object2LongMap.Entry entry = (Object2LongMap.Entry)objectiterator.next();
                    if (entry.getLongValue() > this.t_148_a) continue;
                    try {
                        ((Runnable)entry.getKey()).run();
                    }
                    catch (Exception exception) {
                        this.n_1700_B(exception);
                    }
                    objectiterator.remove();
                }
                if (this.t_148_a > (long)this.P_1922_E) {
                    if (this.u_1723_Y.isEmpty()) {
                        this.n_1700_B(new V_2718_G("Didn't succeed or fail within " + this.n_1700_B.R_4764_Y() + " ticks"));
                    } else {
                        this.u_1723_Y.forEach(p_229509_1_ -> p_229509_1_.J_1907_R(this.t_148_a));
                        if (this.h_1847_R == null) {
                            this.n_1700_B(new V_2718_G("No sequences finished"));
                        }
                    }
                } else {
                    this.u_1723_Y.forEach(p_229505_1_ -> p_229505_1_.n_1700_B(this.t_148_a));
                }
            }
        }
    }

    private void M_182_A() {
        if (this.s_956_w) {
            throw new IllegalStateException("Test already started");
        }
        this.s_956_w = true;
        try {
            this.n_1700_B.n_1700_B(new v_4829_R(this));
        }
        catch (Exception exception) {
            this.n_1700_B(exception);
        }
    }

    public String R_4764_Y() {
        return this.n_1700_B.n_1700_B();
    }

    public c_1514_x G_564_y() {
        return this.J_1907_R;
    }

    public e_3591_l P_1922_E() {
        return this.R_4764_Y;
    }

    public boolean u_1723_Y() {
        return this.M_588_G && this.h_1847_R == null;
    }

    public boolean v_4262_N() {
        return this.h_1847_R != null;
    }

    public boolean w_1484_f() {
        return this.s_956_w;
    }

    public boolean t_148_a() {
        return this.M_588_G;
    }

    private void t_1786_h() {
        if (!this.M_588_G) {
            this.M_588_G = true;
            this.u_2550_I.stop();
        }
    }

    public void n_1700_B(Throwable p_229506_1_) {
        this.t_1786_h();
        this.h_1847_R = p_229506_1_;
        this.G_564_y.forEach(p_229511_1_ -> p_229511_1_.J_1907_R(this));
    }

    @Nullable
    public Throwable s_956_w() {
        return this.h_1847_R;
    }

    public String toString() {
        return this.R_4764_Y();
    }

    public void n_1700_B(GameTestListener p_229504_1_) {
        this.G_564_y.add(p_229504_1_);
    }

    public void n_1700_B(c_1514_x p_240543_1_, int p_240543_2_) {
        j_2644_e structureblocktileentity = a_3322_s.n_1700_B(this.P_4830_p(), p_240543_1_, this.h_1847_R(), p_240543_2_, this.R_4764_Y, false);
        this.n_1700_B(structureblocktileentity.x_607_J());
        structureblocktileentity.n_1700_B(this.R_4764_Y());
        a_3322_s.n_1700_B(this.J_1907_R, new c_1514_x(1, 0, -1), this.h_1847_R(), this.R_4764_Y);
        this.G_564_y.forEach(p_229508_1_ -> p_229508_1_.n_1700_B(this));
    }

    public boolean u_2550_I() {
        return this.n_1700_B.G_564_y();
    }

    public boolean M_588_G() {
        return !this.n_1700_B.G_564_y();
    }

    public String P_4830_p() {
        return this.n_1700_B.J_1907_R();
    }

    public W_2163_m h_1847_R() {
        return this.P_4830_p;
    }

    public TestFunction Q_4569_t() {
        return this.n_1700_B;
    }
}


