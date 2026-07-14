/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.server;

import java.nio.file.Path;
import java.util.function.UnaryOperator;
import lightning.product.DedicatedServerProperties;
import lightning.product.r_4097_j;

public class u_1723_Y {
    private final Path n_1700_B;
    private DedicatedServerProperties J_1907_R;

    public u_1723_Y(r_4097_j p_i242100_1_, Path p_i242100_2_) {
        this.n_1700_B = p_i242100_2_;
        this.J_1907_R = DedicatedServerProperties.n_1700_B(p_i242100_1_, p_i242100_2_);
    }

    public DedicatedServerProperties n_1700_B() {
        return this.J_1907_R;
    }

    public void J_1907_R() {
        this.J_1907_R.J_1907_R(this.n_1700_B);
    }

    public u_1723_Y n_1700_B(UnaryOperator<DedicatedServerProperties> p_219033_1_) {
        this.J_1907_R = (DedicatedServerProperties)p_219033_1_.apply(this.J_1907_R);
        this.J_1907_R.J_1907_R(this.n_1700_B);
        return this;
    }
}


