/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.shorts.ShortList
 */
package lightning.product;

import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.function.Function;
import java.util.function.Predicate;
import lightning.product.TickList;
import lightning.product.V_4824_J;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.n_1254_X;
import lightning.product.q_2896_o;
import lightning.product.s_2187_o;

public class ProtoTickList<T>
implements TickList<T> {
    protected final Predicate<T> n_1700_B;
    private final Y_1387_d J_1907_R;
    private final ShortList[] R_4764_Y = new ShortList[16];

    public ProtoTickList(Predicate<T> filter, Y_1387_d pos) {
        this(filter, pos, new q_2896_o());
    }

    public ProtoTickList(Predicate<T> filter, Y_1387_d pos, q_2896_o p_i51496_3_) {
        this.n_1700_B = filter;
        this.J_1907_R = pos;
        for (int i = 0; i < p_i51496_3_.size(); ++i) {
            q_2896_o listnbt = p_i51496_3_.J_1907_R(i);
            for (int j = 0; j < listnbt.size(); ++j) {
                ChunkAccess.n_1700_B(this.R_4764_Y, i).add(listnbt.G_564_y(j));
            }
        }
    }

    public q_2896_o n_1700_B() {
        return s_2187_o.n_1700_B(this.R_4764_Y);
    }

    public void n_1700_B(TickList<T> tickList, Function<c_1514_x, T> func) {
        for (int i = 0; i < this.R_4764_Y.length; ++i) {
            if (this.R_4764_Y[i] == null) continue;
            for (Short oshort : this.R_4764_Y[i]) {
                c_1514_x blockpos = n_1254_X.n_1700_B(oshort, i, this.J_1907_R);
                tickList.n_1700_B(blockpos, func.apply(blockpos), 0);
            }
            this.R_4764_Y[i].clear();
        }
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, T itemIn) {
        return false;
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime, V_4824_J priority) {
        ChunkAccess.n_1700_B(this.R_4764_Y, pos.getY() >> 4).add(n_1254_X.J_1907_R(pos));
    }

    @Override
    public boolean J_1907_R(c_1514_x pos, T obj) {
        return false;
    }
}


