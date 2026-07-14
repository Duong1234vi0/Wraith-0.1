/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.W_1671_y;
import lightning.product.t_4467_k;
import lightning.product.Input;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

public class MovementTutorialStepInstance
implements TutorialStepInstance {
    private static final x_282_a n_1700_B = new F_2904_S("tutorial.move.title", W_1671_y.n_1700_B("forward"), W_1671_y.n_1700_B("left"), W_1671_y.n_1700_B("back"), W_1671_y.n_1700_B("right"));
    private static final x_282_a J_1907_R = new F_2904_S("tutorial.move.description", W_1671_y.n_1700_B("jump"));
    private static final x_282_a R_4764_Y = new F_2904_S("tutorial.look.title");
    private static final x_282_a G_564_y = new F_2904_S("tutorial.look.description");
    private final W_1671_y P_1922_E;
    private TutorialToast u_1723_Y;
    private TutorialToast v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private boolean u_2550_I;
    private boolean M_588_G;
    private int P_4830_p = -1;
    private int h_1847_R = -1;

    public MovementTutorialStepInstance(W_1671_y tutorial) {
        this.P_1922_E = tutorial;
    }

    @Override
    public void n_1700_B() {
        ++this.w_1484_f;
        if (this.u_2550_I) {
            ++this.t_148_a;
            this.u_2550_I = false;
        }
        if (this.M_588_G) {
            ++this.s_956_w;
            this.M_588_G = false;
        }
        if (this.P_4830_p == -1 && this.t_148_a > 40) {
            if (this.u_1723_Y != null) {
                this.u_1723_Y.G_564_y();
                this.u_1723_Y = null;
            }
            this.P_4830_p = this.w_1484_f;
        }
        if (this.h_1847_R == -1 && this.s_956_w > 40) {
            if (this.v_4262_N != null) {
                this.v_4262_N.G_564_y();
                this.v_4262_N = null;
            }
            this.h_1847_R = this.w_1484_f;
        }
        if (this.P_4830_p != -1 && this.h_1847_R != -1) {
            if (this.P_1922_E.u_1723_Y() == I_14_v.J_1907_R) {
                this.P_1922_E.n_1700_B(t_4467_k.J_1907_R);
            } else {
                this.P_1922_E.n_1700_B(t_4467_k.u_1723_Y);
            }
        }
        if (this.u_1723_Y != null) {
            this.u_1723_Y.n_1700_B((float)this.t_148_a / 40.0f);
        }
        if (this.v_4262_N != null) {
            this.v_4262_N.n_1700_B((float)this.s_956_w / 40.0f);
        }
        if (this.w_1484_f >= 100) {
            if (this.P_4830_p == -1 && this.u_1723_Y == null) {
                this.u_1723_Y = new TutorialToast(TutorialToast.n_1700_B.n_1700_B, n_1700_B, J_1907_R, true);
                this.P_1922_E.P_1922_E().e_1992_r().n_1700_B(this.u_1723_Y);
            } else if (this.P_4830_p != -1 && this.w_1484_f - this.P_4830_p >= 20 && this.h_1847_R == -1 && this.v_4262_N == null) {
                this.v_4262_N = new TutorialToast(TutorialToast.n_1700_B.J_1907_R, R_4764_Y, G_564_y, true);
                this.P_1922_E.P_1922_E().e_1992_r().n_1700_B(this.v_4262_N);
            }
        }
    }

    @Override
    public void J_1907_R() {
        if (this.u_1723_Y != null) {
            this.u_1723_Y.G_564_y();
            this.u_1723_Y = null;
        }
        if (this.v_4262_N != null) {
            this.v_4262_N.G_564_y();
            this.v_4262_N = null;
        }
    }

    @Override
    public void n_1700_B(Input input) {
        if (input.forwardKeyDown || input.backKeyDown || input.leftKeyDown || input.rightKeyDown || input.jump) {
            this.u_2550_I = true;
        }
    }

    @Override
    public void n_1700_B(double velocityX, double velocityY) {
        if (Math.abs(velocityX) > 0.01 || Math.abs(velocityY) > 0.01) {
            this.M_588_G = true;
        }
    }
}


