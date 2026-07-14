/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;

public class U_1258_d {
    private final A_2629_w n_1700_B;
    private final U_1258_d J_1907_R;
    private final U_1258_d R_4764_Y;
    private final int G_564_y;
    private final List<U_1258_d> P_1922_E = Lists.newArrayList();
    private U_1258_d u_1723_Y;
    private U_1258_d v_4262_N;
    private int w_1484_f;
    private float t_148_a;
    private float s_956_w;
    private float u_2550_I;
    private float M_588_G;

    public U_1258_d(A_2629_w advancementIn, @Nullable U_1258_d parentIn, @Nullable U_1258_d siblingIn, int indexIn, int xIn) {
        if (advancementIn.R_4764_Y() == null) {
            throw new IllegalArgumentException("Can't position an invisible advancement!");
        }
        this.n_1700_B = advancementIn;
        this.J_1907_R = parentIn;
        this.R_4764_Y = siblingIn;
        this.G_564_y = indexIn;
        this.u_1723_Y = this;
        this.w_1484_f = xIn;
        this.t_148_a = -1.0f;
        U_1258_d advancementtreenode = null;
        for (A_2629_w advancement : advancementIn.P_1922_E()) {
            advancementtreenode = this.n_1700_B(advancement, advancementtreenode);
        }
    }

    @Nullable
    private U_1258_d n_1700_B(A_2629_w advancementIn, @Nullable U_1258_d previous) {
        if (advancementIn.R_4764_Y() != null) {
            previous = new U_1258_d(advancementIn, this, previous, this.P_1922_E.size() + 1, this.w_1484_f + 1);
            this.P_1922_E.add(previous);
        } else {
            for (A_2629_w advancement : advancementIn.P_1922_E()) {
                previous = this.n_1700_B(advancement, previous);
            }
        }
        return previous;
    }

    private void n_1700_B() {
        if (this.P_1922_E.isEmpty()) {
            this.t_148_a = this.R_4764_Y != null ? this.R_4764_Y.t_148_a + 1.0f : 0.0f;
        } else {
            U_1258_d advancementtreenode = null;
            for (U_1258_d advancementtreenode1 : this.P_1922_E) {
                advancementtreenode1.n_1700_B();
                advancementtreenode = advancementtreenode1.n_1700_B(advancementtreenode == null ? advancementtreenode1 : advancementtreenode);
            }
            this.J_1907_R();
            float f = (this.P_1922_E.get((int)0).t_148_a + this.P_1922_E.get((int)(this.P_1922_E.size() - 1)).t_148_a) / 2.0f;
            if (this.R_4764_Y != null) {
                this.t_148_a = this.R_4764_Y.t_148_a + 1.0f;
                this.s_956_w = this.t_148_a - f;
            } else {
                this.t_148_a = f;
            }
        }
    }

    private float n_1700_B(float offsetY, int columnX, float subtreeTopY) {
        this.t_148_a += offsetY;
        this.w_1484_f = columnX;
        if (this.t_148_a < subtreeTopY) {
            subtreeTopY = this.t_148_a;
        }
        for (U_1258_d advancementtreenode : this.P_1922_E) {
            subtreeTopY = advancementtreenode.n_1700_B(offsetY + this.s_956_w, columnX + 1, subtreeTopY);
        }
        return subtreeTopY;
    }

    private void n_1700_B(float yIn) {
        this.t_148_a += yIn;
        for (U_1258_d advancementtreenode : this.P_1922_E) {
            advancementtreenode.n_1700_B(yIn);
        }
    }

    private void J_1907_R() {
        float f = 0.0f;
        float f1 = 0.0f;
        for (int i = this.P_1922_E.size() - 1; i >= 0; --i) {
            U_1258_d advancementtreenode = this.P_1922_E.get(i);
            advancementtreenode.t_148_a += f;
            advancementtreenode.s_956_w += f;
            f += advancementtreenode.M_588_G + (f1 += advancementtreenode.u_2550_I);
        }
    }

    @Nullable
    private U_1258_d R_4764_Y() {
        if (this.v_4262_N != null) {
            return this.v_4262_N;
        }
        return !this.P_1922_E.isEmpty() ? this.P_1922_E.get(0) : null;
    }

    @Nullable
    private U_1258_d G_564_y() {
        if (this.v_4262_N != null) {
            return this.v_4262_N;
        }
        return !this.P_1922_E.isEmpty() ? this.P_1922_E.get(this.P_1922_E.size() - 1) : null;
    }

    private U_1258_d n_1700_B(U_1258_d nodeIn) {
        if (this.R_4764_Y == null) {
            return nodeIn;
        }
        U_1258_d advancementtreenode = this;
        U_1258_d advancementtreenode1 = this;
        U_1258_d advancementtreenode2 = this.R_4764_Y;
        U_1258_d advancementtreenode3 = this.J_1907_R.P_1922_E.get(0);
        float f = this.s_956_w;
        float f1 = this.s_956_w;
        float f2 = advancementtreenode2.s_956_w;
        float f3 = advancementtreenode3.s_956_w;
        while (advancementtreenode2.G_564_y() != null && advancementtreenode.R_4764_Y() != null) {
            advancementtreenode2 = advancementtreenode2.G_564_y();
            advancementtreenode = advancementtreenode.R_4764_Y();
            advancementtreenode3 = advancementtreenode3.R_4764_Y();
            advancementtreenode1 = advancementtreenode1.G_564_y();
            advancementtreenode1.u_1723_Y = this;
            float f4 = advancementtreenode2.t_148_a + f2 - (advancementtreenode.t_148_a + f) + 1.0f;
            if (f4 > 0.0f) {
                advancementtreenode2.n_1700_B(this, nodeIn).n_1700_B(this, f4);
                f += f4;
                f1 += f4;
            }
            f2 += advancementtreenode2.s_956_w;
            f += advancementtreenode.s_956_w;
            f3 += advancementtreenode3.s_956_w;
            f1 += advancementtreenode1.s_956_w;
        }
        if (advancementtreenode2.G_564_y() != null && advancementtreenode1.G_564_y() == null) {
            advancementtreenode1.v_4262_N = advancementtreenode2.G_564_y();
            advancementtreenode1.s_956_w += f2 - f1;
        } else {
            if (advancementtreenode.R_4764_Y() != null && advancementtreenode3.R_4764_Y() == null) {
                advancementtreenode3.v_4262_N = advancementtreenode.R_4764_Y();
                advancementtreenode3.s_956_w += f - f3;
            }
            nodeIn = this;
        }
        return nodeIn;
    }

    private void n_1700_B(U_1258_d nodeIn, float shift) {
        float f = nodeIn.G_564_y - this.G_564_y;
        if (f != 0.0f) {
            nodeIn.u_2550_I -= shift / f;
            this.u_2550_I += shift / f;
        }
        nodeIn.M_588_G += shift;
        nodeIn.t_148_a += shift;
        nodeIn.s_956_w += shift;
    }

    private U_1258_d n_1700_B(U_1258_d self, U_1258_d other) {
        return this.u_1723_Y != null && self.J_1907_R.P_1922_E.contains(this.u_1723_Y) ? this.u_1723_Y : other;
    }

    private void P_1922_E() {
        if (this.n_1700_B.R_4764_Y() != null) {
            this.n_1700_B.R_4764_Y().n_1700_B(this.w_1484_f, this.t_148_a);
        }
        if (!this.P_1922_E.isEmpty()) {
            for (U_1258_d advancementtreenode : this.P_1922_E) {
                advancementtreenode.P_1922_E();
            }
        }
    }

    public static void n_1700_B(A_2629_w root) {
        if (root.R_4764_Y() == null) {
            throw new IllegalArgumentException("Can't position children of an invisible root!");
        }
        U_1258_d advancementtreenode = new U_1258_d(root, null, null, 1, 0);
        advancementtreenode.n_1700_B();
        float f = advancementtreenode.n_1700_B(0.0f, 0, advancementtreenode.t_148_a);
        if (f < 0.0f) {
            advancementtreenode.n_1700_B(-f);
        }
        advancementtreenode.P_1922_E();
    }
}

