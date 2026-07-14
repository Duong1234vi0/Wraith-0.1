/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.T_3594_S;
import lightning.product.Z_2491_A;
import lightning.product.b_257_Y;
import lightning.product.o_1290_k;

public class l_1233_K
extends T_3594_S {
    private final D_4792_h t_148_a;
    private final D_1098_v s_956_w;
    private final o_1290_k u_2550_I;
    private float M_588_G;
    private float P_4830_p;
    private float h_1847_R;
    private int Q_4569_t;
    private int M_182_A;
    private int t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private float w_1457_N;
    private float Y_601_j;

    public l_1233_K(D_4792_h vertexBuilder, D_1098_v currentTransformMatrix, o_1290_k normalMatrix) {
        this.t_148_a = vertexBuilder;
        this.s_956_w = currentTransformMatrix.u_1723_Y();
        this.s_956_w.P_1922_E();
        this.u_2550_I = normalMatrix.u_1723_Y();
        this.u_2550_I.P_1922_E();
        this.J_1907_R();
    }

    private void J_1907_R() {
        this.M_588_G = 0.0f;
        this.P_4830_p = 0.0f;
        this.h_1847_R = 0.0f;
        this.Q_4569_t = 0;
        this.M_182_A = 10;
        this.t_1786_h = 0xF000F0;
        this.multiplayerClientSuggestionProvider = 0.0f;
        this.w_1457_N = 1.0f;
        this.Y_601_j = 0.0f;
    }

    @Override
    public void endVertex() {
        M_1336_P vector3f = new M_1336_P(this.multiplayerClientSuggestionProvider, this.w_1457_N, this.Y_601_j);
        vector3f.n_1700_B(this.u_2550_I);
        b_257_Y direction = b_257_Y.n_1700_B(vector3f.n_1700_B(), vector3f.J_1907_R(), vector3f.R_4764_Y());
        Z_2491_A vector4f = new Z_2491_A(this.M_588_G, this.P_4830_p, this.h_1847_R, 1.0f);
        vector4f.n_1700_B(this.s_956_w);
        vector4f.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        vector4f.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-90.0f));
        vector4f.n_1700_B(direction.J_1907_R());
        float f = -vector4f.n_1700_B();
        float f1 = -vector4f.J_1907_R();
        this.t_148_a.pos(this.M_588_G, this.P_4830_p, this.h_1847_R).n_1700_B(1.0f, 1.0f, 1.0f, 1.0f).tex(f, f1).overlay(this.Q_4569_t, this.M_182_A).J_1907_R(this.t_1786_h).normal(this.multiplayerClientSuggestionProvider, this.w_1457_N, this.Y_601_j).endVertex();
        this.J_1907_R();
    }

    @Override
    public D_4792_h pos(double x, double y, double z) {
        this.M_588_G = (float)x;
        this.P_4830_p = (float)y;
        this.h_1847_R = (float)z;
        return this;
    }

    @Override
    public D_4792_h color(int red, int green, int blue, int alpha) {
        return this;
    }

    @Override
    public D_4792_h tex(float u, float v) {
        return this;
    }

    @Override
    public D_4792_h overlay(int u, int v) {
        this.Q_4569_t = u;
        this.M_182_A = v;
        return this;
    }

    @Override
    public D_4792_h lightmap(int u, int v) {
        this.t_1786_h = u | v << 16;
        return this;
    }

    @Override
    public D_4792_h normal(float x, float y, float z) {
        this.multiplayerClientSuggestionProvider = x;
        this.w_1457_N = y;
        this.Y_601_j = z;
        return this;
    }
}


