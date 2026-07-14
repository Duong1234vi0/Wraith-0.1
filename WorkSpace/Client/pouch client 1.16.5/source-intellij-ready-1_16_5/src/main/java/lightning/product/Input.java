/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_3504_Q;

public class Input {
    public float moveStrafe;
    public float moveForward;
    public boolean forwardKeyDown;
    public boolean backKeyDown;
    public boolean leftKeyDown;
    public boolean rightKeyDown;
    public boolean jump;
    public boolean sneaking;

    public void tickMovement(boolean p_225607_1_) {
    }

    public P_3504_Q getMoveVector() {
        return new P_3504_Q(this.moveStrafe, this.moveForward);
    }

    public boolean isMovingForward() {
        return this.moveForward > 1.0E-5f;
    }
}


