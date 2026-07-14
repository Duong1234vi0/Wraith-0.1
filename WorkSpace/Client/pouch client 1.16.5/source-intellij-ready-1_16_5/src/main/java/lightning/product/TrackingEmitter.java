/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NoRenderParticle;
import lightning.product.N_4263_v;
import lightning.product.ParticleOptions;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;

public class TrackingEmitter
extends NoRenderParticle {
    private final N_4263_v n_1700_B;
    private int J_1907_R;
    private final int H_2857_Y;
    private final ParticleOptions A_4115_X;

    public TrackingEmitter(b_4507_u world, N_4263_v entity, ParticleOptions particleData) {
        this(world, entity, particleData, 3);
    }

    public TrackingEmitter(b_4507_u world, N_4263_v entity, ParticleOptions particleData, int lifetime) {
        this(world, entity, particleData, lifetime, entity.I_4348_c());
    }

    private TrackingEmitter(b_4507_u world, N_4263_v entity, ParticleOptions particleData, int lifetime, e_2866_D motionVector) {
        super(world, entity.O_3598_v(), entity.P_1922_E(0.5), entity.l_2647_k(), motionVector.J_1907_R, motionVector.R_4764_Y, motionVector.G_564_y);
        this.n_1700_B = entity;
        this.H_2857_Y = lifetime;
        this.A_4115_X = particleData;
        this.n_1700_B();
    }

    @Override
    public void n_1700_B() {
        for (int i = 0; i < 16; ++i) {
            double d2;
            double d1;
            double d0 = this.multiplayerClientSuggestionProvider.nextFloat() * 2.0f - 1.0f;
            if (d0 * d0 + (d1 = (double)(this.multiplayerClientSuggestionProvider.nextFloat() * 2.0f - 1.0f)) * d1 + (d2 = (double)(this.multiplayerClientSuggestionProvider.nextFloat() * 2.0f - 1.0f)) * d2 > 1.0) continue;
            double d3 = this.n_1700_B.R_4764_Y(d0 / 4.0);
            double d4 = this.n_1700_B.P_1922_E(0.5 + d1 / 4.0);
            double d5 = this.n_1700_B.u_1723_Y(d2 / 4.0);
            this.R_4764_Y.n_1700_B(this.A_4115_X, false, d3, d4, d5, d0, d1 + 0.2, d2);
        }
        ++this.J_1907_R;
        if (this.J_1907_R >= this.H_2857_Y) {
            this.s_956_w();
        }
    }
}


