/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Collection;
import lightning.product.s_4514_h;

public class GameTestTicker {
    public static final GameTestTicker n_1700_B = new GameTestTicker();
    private final Collection<s_4514_h> J_1907_R = Lists.newCopyOnWriteArrayList();

    public void n_1700_B(s_4514_h p_229573_1_) {
        this.J_1907_R.add(p_229573_1_);
    }

    public void n_1700_B() {
        this.J_1907_R.clear();
    }

    public void J_1907_R() {
        this.J_1907_R.forEach(s_4514_h::J_1907_R);
        this.J_1907_R.removeIf(s_4514_h::t_148_a);
    }
}


