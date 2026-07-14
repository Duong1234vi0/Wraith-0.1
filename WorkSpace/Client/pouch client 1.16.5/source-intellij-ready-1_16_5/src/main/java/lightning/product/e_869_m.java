/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.V_4423_d;
import lightning.product.a_178_J;
import lightning.product.Input;

public class e_869_m
extends Input {
    private final V_4423_d n_1700_B;

    public e_869_m(V_4423_d gameSettingsIn) {
        this.n_1700_B = gameSettingsIn;
    }

    @Override
    public void tickMovement(boolean p_225607_1_) {
        this.forwardKeyDown = this.n_1700_B.O_508_d.G_564_y();
        this.backKeyDown = this.n_1700_B.A_1038_p.G_564_y();
        this.leftKeyDown = this.n_1700_B.r_715_M.G_564_y();
        this.rightKeyDown = this.n_1700_B.i_1637_u.G_564_y();
        float f = this.forwardKeyDown == this.backKeyDown ? 0.0f : (this.moveForward = this.forwardKeyDown ? 1.0f : -1.0f);
        this.moveStrafe = this.leftKeyDown == this.rightKeyDown ? 0.0f : (this.leftKeyDown ? 1.0f : -1.0f);
        this.jump = this.n_1700_B.Ping.G_564_y();
        this.sneaking = this.n_1700_B.p_178_J.G_564_y();
        a_178_J event = new a_178_J(this.moveForward, this.moveStrafe, this.forwardKeyDown, this.backKeyDown, this.leftKeyDown, this.rightKeyDown, this.jump, this.sneaking, 0.3);
        A_4115_X.n_1700_B(event);
        double sneakMultiplier = event.t_148_a();
        this.moveForward = event.n_1700_B();
        this.moveStrafe = event.J_1907_R();
        this.forwardKeyDown = event.R_4764_Y();
        this.backKeyDown = event.G_564_y();
        this.leftKeyDown = event.P_1922_E();
        this.rightKeyDown = event.u_1723_Y();
        this.jump = event.v_4262_N();
        this.sneaking = event.w_1484_f();
        if (p_225607_1_) {
            this.moveStrafe = (float)((double)this.moveStrafe * sneakMultiplier);
            this.moveForward = (float)((double)this.moveForward * sneakMultiplier);
        }
    }
}


