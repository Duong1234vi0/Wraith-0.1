/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ServerboundCustomQueryPacket;
import lightning.product.ServerboundKeyPacket;
import lightning.product.ServerboundHelloPacket;
import lightning.product.particlesParticleOptions;

public interface ServerLoginPacketListener
extends particlesParticleOptions {
    public void n_1700_B(ServerboundHelloPacket var1);

    public void n_1700_B(ServerboundKeyPacket var1);

    public void n_1700_B(ServerboundCustomQueryPacket var1);
}


