/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.GameTestListener;
import lightning.product.s_4514_h;

public class MultipleTestTracker {
    private final Collection<s_4514_h> n_1700_B = Lists.newArrayList();
    @Nullable
    private Collection<GameTestListener> J_1907_R = Lists.newArrayList();

    public MultipleTestTracker() {
    }

    public MultipleTestTracker(Collection<s_4514_h> p_i226072_1_) {
        this.n_1700_B.addAll(p_i226072_1_);
    }

    public void n_1700_B(s_4514_h p_229579_1_) {
        this.n_1700_B.add(p_229579_1_);
        this.J_1907_R.forEach(p_229579_1_::n_1700_B);
    }

    public void n_1700_B(GameTestListener p_240558_1_) {
        this.J_1907_R.add(p_240558_1_);
        this.n_1700_B.forEach(p_240559_1_ -> p_240559_1_.n_1700_B(p_240558_1_));
    }

    public void n_1700_B(final Consumer<s_4514_h> p_240556_1_) {
        this.n_1700_B(new GameTestListener(){

            @Override
            public void n_1700_B(s_4514_h p_225644_1_) {
            }

            @Override
            public void J_1907_R(s_4514_h p_225645_1_) {
                p_240556_1_.accept(p_225645_1_);
            }
        });
    }

    public int n_1700_B() {
        return (int)this.n_1700_B.stream().filter(s_4514_h::v_4262_N).filter(s_4514_h::u_2550_I).count();
    }

    public int J_1907_R() {
        return (int)this.n_1700_B.stream().filter(s_4514_h::v_4262_N).filter(s_4514_h::M_588_G).count();
    }

    public int R_4764_Y() {
        return (int)this.n_1700_B.stream().filter(s_4514_h::t_148_a).count();
    }

    public boolean G_564_y() {
        return this.n_1700_B() > 0;
    }

    public boolean P_1922_E() {
        return this.J_1907_R() > 0;
    }

    public int u_1723_Y() {
        return this.n_1700_B.size();
    }

    public boolean v_4262_N() {
        return this.R_4764_Y() == this.u_1723_Y();
    }

    public String w_1484_f() {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append('[');
        this.n_1700_B.forEach(p_229582_1_ -> {
            if (!p_229582_1_.w_1484_f()) {
                stringbuffer.append(' ');
            } else if (p_229582_1_.u_1723_Y()) {
                stringbuffer.append('+');
            } else if (p_229582_1_.v_4262_N()) {
                stringbuffer.append(p_229582_1_.u_2550_I() ? (char)'X' : (char)'x');
            } else {
                stringbuffer.append('_');
            }
        });
        stringbuffer.append(']');
        return stringbuffer.toString();
    }

    public String toString() {
        return this.w_1484_f();
    }
}


