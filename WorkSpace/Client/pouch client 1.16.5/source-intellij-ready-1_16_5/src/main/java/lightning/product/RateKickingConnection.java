/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lightning.product.F_2904_S;
import lightning.product.a_3942_s;
import lightning.product.c_1633_k;
import lightning.product.w_690_m;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RateKickingConnection
extends c_1633_k {
    private static final Logger v_4262_N = LogManager.getLogger();
    private static final x_282_a w_1484_f = new F_2904_S("disconnect.exceeded_packet_rate");
    private final int t_148_a;

    public RateKickingConnection(int p_i242078_1_) {
        super(a_3942_s.n_1700_B);
        this.t_148_a = p_i242078_1_;
    }

    @Override
    protected void J_1907_R() {
        super.J_1907_R();
        float f = this.M_588_G();
        if (f > (float)this.t_148_a) {
            v_4262_N.warn("Player exceeded rate-limit (sent {} packets per second)", (Object)Float.valueOf(f));
            this.n_1700_B(new w_690_m(w_1484_f), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_244277_1_ -> this.n_1700_B(w_1484_f)));
            this.s_956_w();
        }
    }
}


