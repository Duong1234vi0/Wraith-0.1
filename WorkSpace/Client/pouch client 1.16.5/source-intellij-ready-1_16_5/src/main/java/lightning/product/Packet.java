/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.b_2585_i;
import lightning.product.particlesParticleOptions;

public interface Packet<T extends particlesParticleOptions> {
    public void n_1700_B(b_2585_i var1) throws IOException;

    public void J_1907_R(b_2585_i var1) throws IOException;

    public void n_1700_B(T var1);

    default public boolean n_1700_B() {
        return false;
    }
}


