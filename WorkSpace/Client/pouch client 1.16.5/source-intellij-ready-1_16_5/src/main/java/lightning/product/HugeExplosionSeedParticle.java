/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Objects;
import lightning.product.NoRenderParticle;
import lightning.product.b_4507_u;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.ParticleTypes;

public class HugeExplosionSeedParticle
extends NoRenderParticle {
    private int n_1700_B;
    private final int J_1907_R = 8;

    private HugeExplosionSeedParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
    }

    @Override
    public void n_1700_B() {
        for (int i = 0; i < 6; ++i) {
            double d0 = this.v_4262_N + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 4.0;
            double d1 = this.w_1484_f + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 4.0;
            double d2 = this.t_148_a + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 4.0;
            float f = this.n_1700_B;
            Objects.requireNonNull(this);
            this.R_4764_Y.n_1700_B(ParticleTypes.C_2741_M, d0, d1, d2, (double)(f / 8.0f), 0.0, 0.0);
        }
        ++this.n_1700_B;
        if (this.n_1700_B == this.J_1907_R) {
            this.s_956_w();
        }
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new HugeExplosionSeedParticle(worldIn, x, y, z);
        }
    }
}


