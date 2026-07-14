/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ClientboundPongResponsePacket;
import lightning.product.ClientboundStatusResponsePacket;
import lightning.product.particlesParticleOptions;

public interface M_2450_l
extends particlesParticleOptions {
    public void handleServerInfo(ClientboundStatusResponsePacket var1);

    public void handlePong(ClientboundPongResponsePacket var1);
}


