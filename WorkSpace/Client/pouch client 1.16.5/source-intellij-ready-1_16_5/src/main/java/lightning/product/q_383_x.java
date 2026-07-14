/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class q_383_x {
    public static synchronized ByteBuffer n_1700_B(int capacity) {
        return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
    }

    public static FloatBuffer J_1907_R(int capacity) {
        return q_383_x.n_1700_B(capacity << 2).asFloatBuffer();
    }
}

