/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.q_1613_l;

public class R_1148_E {
    private final q_1613_l n_1700_B;
    private final String J_1907_R;
    private int R_4764_Y = -1;
    private boolean G_564_y = false;
    private boolean P_1922_E = false;
    private boolean u_1723_Y = false;
    private boolean v_4262_N = false;
    private boolean w_1484_f = false;
    private String t_148_a = "";

    public R_1148_E(q_1613_l item, String name) {
        this.n_1700_B = item;
        this.J_1907_R = name;
    }

    public q_1613_l n_1700_B() {
        return this.n_1700_B;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public void n_1700_B(int bindKey) {
        this.R_4764_Y = bindKey;
    }

    public boolean G_564_y() {
        return this.G_564_y;
    }

    public void n_1700_B(boolean throwAtEnemy) {
        this.G_564_y = throwAtEnemy;
    }

    public boolean P_1922_E() {
        return this.u_1723_Y;
    }

    public void J_1907_R(boolean activateOnRightShift) {
        this.u_1723_Y = activateOnRightShift;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E;
    }

    public void R_4764_Y(boolean throwForward) {
        this.P_1922_E = throwForward;
    }

    public boolean v_4262_N() {
        return this.v_4262_N;
    }

    public void G_564_y(boolean onlyInCombat) {
        this.v_4262_N = onlyInCombat;
    }

    public boolean w_1484_f() {
        return this.w_1484_f;
    }

    public void P_1922_E(boolean useOnAllies) {
        this.w_1484_f = useOnAllies;
    }

    public String t_148_a() {
        return this.t_148_a != null ? this.t_148_a : "";
    }

    public void n_1700_B(String potionName) {
        this.t_148_a = potionName != null ? potionName : "";
    }
}

