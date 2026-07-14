/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.Target;
import lightning.product.N_4263_v;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;

public class b_1722_e {
    private final List<D_1436_R> n_1700_B;
    private D_1436_R[] J_1907_R = new D_1436_R[0];
    private D_1436_R[] R_4764_Y = new D_1436_R[0];
    private Set<Target> G_564_y;
    private int P_1922_E;
    private final c_1514_x u_1723_Y;
    private final float v_4262_N;
    private final boolean w_1484_f;

    public b_1722_e(List<D_1436_R> p_i51804_1_, c_1514_x p_i51804_2_, boolean p_i51804_3_) {
        this.n_1700_B = p_i51804_1_;
        this.u_1723_Y = p_i51804_2_;
        this.v_4262_N = p_i51804_1_.isEmpty() ? Float.MAX_VALUE : this.n_1700_B.get(this.n_1700_B.size() - 1).n_1700_B(this.u_1723_Y);
        this.w_1484_f = p_i51804_3_;
    }

    public void n_1700_B() {
        ++this.P_1922_E;
    }

    public boolean J_1907_R() {
        return this.P_1922_E <= 0;
    }

    public boolean R_4764_Y() {
        return this.P_1922_E >= this.n_1700_B.size();
    }

    @Nullable
    public D_1436_R G_564_y() {
        return !this.n_1700_B.isEmpty() ? this.n_1700_B.get(this.n_1700_B.size() - 1) : null;
    }

    public D_1436_R n_1700_B(int index) {
        return this.n_1700_B.get(index);
    }

    public void J_1907_R(int p_215747_1_) {
        if (this.n_1700_B.size() > p_215747_1_) {
            this.n_1700_B.subList(p_215747_1_, this.n_1700_B.size()).clear();
        }
    }

    public void n_1700_B(int index, D_1436_R point) {
        this.n_1700_B.set(index, point);
    }

    public int P_1922_E() {
        return this.n_1700_B.size();
    }

    public int u_1723_Y() {
        return this.P_1922_E;
    }

    public void R_4764_Y(int currentPathIndexIn) {
        this.P_1922_E = currentPathIndexIn;
    }

    public e_2866_D n_1700_B(N_4263_v entityIn, int index) {
        D_1436_R pathpoint = this.n_1700_B.get(index);
        double d0 = (double)pathpoint.n_1700_B + (double)((int)(entityIn.C_415_h() + 1.0f)) * 0.5;
        double d1 = pathpoint.J_1907_R;
        double d2 = (double)pathpoint.R_4764_Y + (double)((int)(entityIn.C_415_h() + 1.0f)) * 0.5;
        return new e_2866_D(d0, d1, d2);
    }

    public c_1514_x G_564_y(int p_242947_1_) {
        return this.n_1700_B.get(p_242947_1_).R_4764_Y();
    }

    public e_2866_D n_1700_B(N_4263_v entityIn) {
        return this.n_1700_B(entityIn, this.P_1922_E);
    }

    public c_1514_x v_4262_N() {
        return this.n_1700_B.get(this.P_1922_E).R_4764_Y();
    }

    public D_1436_R w_1484_f() {
        return this.n_1700_B.get(this.P_1922_E);
    }

    @Nullable
    public D_1436_R t_148_a() {
        return this.P_1922_E > 0 ? this.n_1700_B.get(this.P_1922_E - 1) : null;
    }

    public boolean n_1700_B(@Nullable b_1722_e pathentityIn) {
        if (pathentityIn == null) {
            return false;
        }
        if (pathentityIn.n_1700_B.size() != this.n_1700_B.size()) {
            return false;
        }
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            D_1436_R pathpoint = this.n_1700_B.get(i);
            D_1436_R pathpoint1 = pathentityIn.n_1700_B.get(i);
            if (pathpoint.n_1700_B == pathpoint1.n_1700_B && pathpoint.J_1907_R == pathpoint1.J_1907_R && pathpoint.R_4764_Y == pathpoint1.R_4764_Y) continue;
            return false;
        }
        return true;
    }

    public boolean s_956_w() {
        return this.w_1484_f;
    }

    public D_1436_R[] u_2550_I() {
        return this.J_1907_R;
    }

    public D_1436_R[] M_588_G() {
        return this.R_4764_Y;
    }

    public static b_1722_e n_1700_B(b_2585_i buf) {
        boolean flag = buf.readBoolean();
        int i = buf.readInt();
        int j = buf.readInt();
        HashSet set = Sets.newHashSet();
        for (int k = 0; k < j; ++k) {
            set.add(Target.n_1700_B(buf));
        }
        c_1514_x blockpos = new c_1514_x(buf.readInt(), buf.readInt(), buf.readInt());
        ArrayList list = Lists.newArrayList();
        int l = buf.readInt();
        for (int i1 = 0; i1 < l; ++i1) {
            list.add(D_1436_R.J_1907_R(buf));
        }
        D_1436_R[] apathpoint = new D_1436_R[buf.readInt()];
        for (int j1 = 0; j1 < apathpoint.length; ++j1) {
            apathpoint[j1] = D_1436_R.J_1907_R(buf);
        }
        D_1436_R[] apathpoint1 = new D_1436_R[buf.readInt()];
        for (int k1 = 0; k1 < apathpoint1.length; ++k1) {
            apathpoint1[k1] = D_1436_R.J_1907_R(buf);
        }
        b_1722_e path = new b_1722_e(list, blockpos, flag);
        path.J_1907_R = apathpoint;
        path.R_4764_Y = apathpoint1;
        path.G_564_y = set;
        path.P_1922_E = i;
        return path;
    }

    public String toString() {
        return "Path(length=" + this.n_1700_B.size() + ")";
    }

    public c_1514_x P_4830_p() {
        return this.u_1723_Y;
    }

    public float h_1847_R() {
        return this.v_4262_N;
    }
}


