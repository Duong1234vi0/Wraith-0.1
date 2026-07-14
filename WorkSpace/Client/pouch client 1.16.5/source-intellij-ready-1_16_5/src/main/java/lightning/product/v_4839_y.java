/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Comparator;
import javax.annotation.Nullable;
import lightning.product.Objective;
import lightning.product.i_4895_l;

public class v_4839_y {
    public static final Comparator<v_4839_y> n_1700_B = (p_210221_0_, p_210221_1_) -> {
        if (p_210221_0_.J_1907_R() > p_210221_1_.J_1907_R()) {
            return 1;
        }
        return p_210221_0_.J_1907_R() < p_210221_1_.J_1907_R() ? -1 : p_210221_1_.P_1922_E().compareToIgnoreCase(p_210221_0_.P_1922_E());
    };
    private final i_4895_l J_1907_R;
    @Nullable
    private final Objective R_4764_Y;
    private final String G_564_y;
    private int P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;

    public v_4839_y(i_4895_l scoreboard, Objective objective, String playerName) {
        this.J_1907_R = scoreboard;
        this.R_4764_Y = objective;
        this.G_564_y = playerName;
        this.u_1723_Y = true;
        this.v_4262_N = true;
    }

    public void n_1700_B(int amount) {
        if (this.R_4764_Y.R_4764_Y().J_1907_R()) {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        this.J_1907_R(this.J_1907_R() + amount);
    }

    public void n_1700_B() {
        this.n_1700_B(1);
    }

    public int J_1907_R() {
        return this.P_1922_E;
    }

    public void R_4764_Y() {
        this.J_1907_R(0);
    }

    public void J_1907_R(int points) {
        int i = this.P_1922_E;
        this.P_1922_E = points;
        if (i != points || this.v_4262_N) {
            this.v_4262_N = false;
            this.u_1723_Y().n_1700_B(this);
        }
    }

    @Nullable
    public Objective G_564_y() {
        return this.R_4764_Y;
    }

    public String P_1922_E() {
        return this.G_564_y;
    }

    public i_4895_l u_1723_Y() {
        return this.J_1907_R;
    }

    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    public void n_1700_B(boolean locked) {
        this.u_1723_Y = locked;
    }
}


