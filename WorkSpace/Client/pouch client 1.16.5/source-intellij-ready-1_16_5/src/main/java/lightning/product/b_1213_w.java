/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  it.unimi.dsi.fastutil.ints.Int2IntArrayMap
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.stream.Collectors;
import lightning.product.A_1726_L;
import lightning.product.c_4037_x;

public class b_1213_w {
    private ImmutableList<A_1726_L> n_1700_B;
    private IntList J_1907_R = new IntArrayList();
    private int R_4764_Y;
    private String G_564_y;
    private int P_1922_E = -1;
    private int u_1723_Y = -1;
    private int v_4262_N = -1;
    private Int2IntMap w_1484_f = new Int2IntArrayMap();

    public b_1213_w(ImmutableList<A_1726_L> elementsIn) {
        this.n_1700_B = elementsIn;
        int i = 0;
        for (A_1726_L vertexformatelement : elementsIn) {
            this.J_1907_R.add(i);
            A_1726_L.J_1907_R vertexformatelement$usage = vertexformatelement.J_1907_R();
            if (vertexformatelement$usage == A_1726_L.J_1907_R.n_1700_B) {
                this.P_1922_E = i;
            } else if (vertexformatelement$usage == A_1726_L.J_1907_R.J_1907_R) {
                this.u_1723_Y = i;
            } else if (vertexformatelement$usage == A_1726_L.J_1907_R.R_4764_Y) {
                this.v_4262_N = i;
            } else if (vertexformatelement$usage == A_1726_L.J_1907_R.G_564_y) {
                this.w_1484_f.put(vertexformatelement.R_4764_Y(), i);
            }
            i += vertexformatelement.G_564_y();
        }
        this.R_4764_Y = i;
    }

    public String toString() {
        return "format: " + this.G_564_y + " " + this.n_1700_B.size() + " elements: " + this.n_1700_B.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public int n_1700_B() {
        return this.J_1907_R() / 4;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public ImmutableList<A_1726_L> R_4764_Y() {
        return this.n_1700_B;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            b_1213_w vertexformat = (b_1213_w)p_equals_1_;
            return this.R_4764_Y != vertexformat.R_4764_Y ? false : this.n_1700_B.equals(vertexformat.n_1700_B);
        }
        return false;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    public void n_1700_B(long pointerIn) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> this.n_1700_B(pointerIn));
        } else {
            int i = this.J_1907_R();
            ImmutableList<A_1726_L> list = this.R_4764_Y();
            for (int j = 0; j < list.size(); ++j) {
                ((A_1726_L)list.get(j)).n_1700_B(pointerIn + (long)this.J_1907_R.getInt(j), i);
            }
        }
    }

    public void G_564_y() {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(this::G_564_y);
        } else {
            for (A_1726_L vertexformatelement : this.R_4764_Y()) {
                vertexformatelement.P_1922_E();
            }
        }
    }

    public int n_1700_B(int p_getOffset_1_) {
        return this.J_1907_R.getInt(p_getOffset_1_);
    }

    public boolean P_1922_E() {
        return this.P_1922_E >= 0;
    }

    public int u_1723_Y() {
        return this.P_1922_E;
    }

    public boolean v_4262_N() {
        return this.u_1723_Y >= 0;
    }

    public int w_1484_f() {
        return this.u_1723_Y;
    }

    public boolean t_148_a() {
        return this.v_4262_N >= 0;
    }

    public int s_956_w() {
        return this.v_4262_N;
    }

    public boolean J_1907_R(int p_hasUV_1_) {
        return this.w_1484_f.containsKey(p_hasUV_1_);
    }

    public int R_4764_Y(int p_getUvOffsetById_1_) {
        return this.w_1484_f.get(p_getUvOffsetById_1_);
    }

    public String u_2550_I() {
        return this.G_564_y;
    }

    public void n_1700_B(String p_setName_1_) {
        this.G_564_y = p_setName_1_;
    }

    public void n_1700_B(b_1213_w p_copyFrom_1_) {
        this.n_1700_B = p_copyFrom_1_.n_1700_B;
        this.J_1907_R = p_copyFrom_1_.J_1907_R;
        this.R_4764_Y = p_copyFrom_1_.R_4764_Y;
        this.G_564_y = p_copyFrom_1_.G_564_y;
        this.P_1922_E = p_copyFrom_1_.P_1922_E;
        this.u_1723_Y = p_copyFrom_1_.u_1723_Y;
        this.v_4262_N = p_copyFrom_1_.v_4262_N;
        this.w_1484_f = p_copyFrom_1_.w_1484_f;
    }

    public b_1213_w M_588_G() {
        b_1213_w vertexformat = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.of());
        vertexformat.n_1700_B(this);
        return vertexformat;
    }
}

