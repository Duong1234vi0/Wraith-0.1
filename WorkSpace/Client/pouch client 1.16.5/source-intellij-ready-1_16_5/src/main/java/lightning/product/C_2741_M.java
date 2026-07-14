/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.P_3504_Q;
import lightning.product.Y_601_j;
import lightning.product.Input;
import lombok.Generated;

public class C_2741_M
extends Input {
    private final Y_601_j n_1700_B;

    public C_2741_M(Y_601_j keyBindings) {
        this.n_1700_B = keyBindings;
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
        this.forwardKeyDown = false;
        this.backKeyDown = false;
        this.leftKeyDown = false;
        this.rightKeyDown = false;
        this.jump = false;
        this.sneaking = false;
    }

    @Override
    public void tickMovement(boolean isForcedDown) {
        this.forwardKeyDown = this.n_1700_B.J_1907_R();
        this.backKeyDown = this.n_1700_B.R_4764_Y();
        this.leftKeyDown = this.n_1700_B.G_564_y();
        this.rightKeyDown = this.n_1700_B.P_1922_E();
        this.jump = this.n_1700_B.u_1723_Y();
        this.sneaking = this.n_1700_B.v_4262_N();
        float[] movement = this.n_1700_B.t_148_a();
        this.moveForward = movement[0];
        this.moveStrafe = movement[1];
        if (isForcedDown) {
            this.jump = false;
        }
    }

    @Override
    public P_3504_Q getMoveVector() {
        return new P_3504_Q(this.moveStrafe, this.moveForward);
    }

    @Override
    public boolean isMovingForward() {
        return this.moveForward > 1.0E-5f;
    }

    @Generated
    public Y_601_j n_1700_B() {
        return this.n_1700_B;
    }
}


