/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class Cursor3D {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private int u_2550_I;

    public Cursor3D(int startX, int startY, int startZ, int endX, int yHeight, int endZ) {
        this.n_1700_B = startX;
        this.J_1907_R = startY;
        this.R_4764_Y = startZ;
        this.G_564_y = endX - startX + 1;
        this.P_1922_E = yHeight - startY + 1;
        this.u_1723_Y = endZ - startZ + 1;
        this.v_4262_N = this.G_564_y * this.P_1922_E * this.u_1723_Y;
    }

    public boolean n_1700_B() {
        if (this.w_1484_f == this.v_4262_N) {
            return false;
        }
        this.t_148_a = this.w_1484_f % this.G_564_y;
        int i = this.w_1484_f / this.G_564_y;
        this.s_956_w = i % this.P_1922_E;
        this.u_2550_I = i / this.P_1922_E;
        ++this.w_1484_f;
        return true;
    }

    public int J_1907_R() {
        return this.n_1700_B + this.t_148_a;
    }

    public int R_4764_Y() {
        return this.J_1907_R + this.s_956_w;
    }

    public int G_564_y() {
        return this.R_4764_Y + this.u_2550_I;
    }

    public int P_1922_E() {
        int i = 0;
        if (this.t_148_a == 0 || this.t_148_a == this.G_564_y - 1) {
            ++i;
        }
        if (this.s_956_w == 0 || this.s_956_w == this.P_1922_E - 1) {
            ++i;
        }
        if (this.u_2550_I == 0 || this.u_2550_I == this.u_1723_Y - 1) {
            ++i;
        }
        return i;
    }
}


