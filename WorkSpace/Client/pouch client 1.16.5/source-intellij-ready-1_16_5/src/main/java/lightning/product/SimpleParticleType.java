/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.ParticleType;

public class SimpleParticleType
extends ParticleType<SimpleParticleType>
implements ParticleOptions {
    private static final ParticleOptions.n_1700_B<SimpleParticleType> n_1700_B = new ParticleOptions.n_1700_B<SimpleParticleType>(){

        public SimpleParticleType n_1700_B(ParticleType<SimpleParticleType> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            return (SimpleParticleType)particleTypeIn;
        }

        public SimpleParticleType n_1700_B(ParticleType<SimpleParticleType> particleTypeIn, b_2585_i buffer) {
            return (SimpleParticleType)particleTypeIn;
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, b_2585_i b_2585_i2) {
            return this.n_1700_B((ParticleType<SimpleParticleType>)w_1969_V2, b_2585_i2);
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B((ParticleType<SimpleParticleType>)w_1969_V2, stringReader);
        }
    };
    private final Codec<SimpleParticleType> J_1907_R = Codec.unit(this::n_1700_B);

    protected SimpleParticleType(boolean alwaysShow) {
        super(alwaysShow, n_1700_B);
    }

    public SimpleParticleType n_1700_B() {
        return this;
    }

    @Override
    public Codec<SimpleParticleType> J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public void n_1700_B(b_2585_i buffer) {
    }

    @Override
    public String R_4764_Y() {
        return V_3137_a.g_164_R.J_1907_R(this).toString();
    }

    public /* synthetic */ ParticleType G_564_y() {
        return this.n_1700_B();
    }
}


