/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import lightning.product.ErrorCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LongRunningTask
implements Runnable,
ErrorCallback {
    public static final Logger n_1700_B = LogManager.getLogger();
    protected RealmsLongRunningMcoTaskScreen J_1907_R;

    protected static void n_1700_B(int p_238125_0_) {
        try {
            Thread.sleep(p_238125_0_ * 1000);
        }
        catch (InterruptedException interruptedexception) {
            n_1700_B.error("", (Throwable)interruptedexception);
        }
    }

    public static void n_1700_B(k_2603_m p_238127_0_) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.execute(() -> minecraft.n_1700_B(p_238127_0_));
    }

    public void n_1700_B(RealmsLongRunningMcoTaskScreen p_224987_1_) {
        this.J_1907_R = p_224987_1_;
    }

    @Override
    public void n_1700_B(x_282_a p_230434_1_) {
        this.J_1907_R.n_1700_B(p_230434_1_);
    }

    public void J_1907_R(x_282_a p_224989_1_) {
        this.J_1907_R.J_1907_R(p_224989_1_);
    }

    public boolean n_1700_B() {
        return this.J_1907_R.n_1700_B();
    }

    public void J_1907_R() {
    }

    public void R_4764_Y() {
    }

    public void G_564_y() {
    }
}



