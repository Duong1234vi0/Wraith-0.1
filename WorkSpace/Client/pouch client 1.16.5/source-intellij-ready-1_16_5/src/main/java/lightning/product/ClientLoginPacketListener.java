/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ClientboundHelloPacket;
import lightning.product.ClientboundGameProfilePacket;
import lightning.product.V_173_d;
import lightning.product.ClientboundCustomQueryPacket;
import lightning.product.i_4972_c;
import lightning.product.particlesParticleOptions;

public interface ClientLoginPacketListener
extends particlesParticleOptions {
    public void n_1700_B(ClientboundHelloPacket var1);

    public void n_1700_B(ClientboundGameProfilePacket var1);

    public void n_1700_B(V_173_d var1);

    public void n_1700_B(i_4972_c var1);

    public void n_1700_B(ClientboundCustomQueryPacket var1);
}


