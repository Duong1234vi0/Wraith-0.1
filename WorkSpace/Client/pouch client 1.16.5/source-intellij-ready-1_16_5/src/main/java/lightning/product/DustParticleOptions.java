/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Locale;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;
import lightning.product.ParticleType;

public class DustParticleOptions
implements ParticleOptions {
    public static final DustParticleOptions n_1700_B = new DustParticleOptions(1.0f, 0.0f, 0.0f, 1.0f);
    public static final Codec<DustParticleOptions> J_1907_R = RecordCodecBuilder.create(p_239803_0_ -> p_239803_0_.group((App)Codec.FLOAT.fieldOf("r").forGetter(p_239807_0_ -> Float.valueOf(p_239807_0_.G_564_y)), (App)Codec.FLOAT.fieldOf("g").forGetter(p_239806_0_ -> Float.valueOf(p_239806_0_.P_1922_E)), (App)Codec.FLOAT.fieldOf("b").forGetter(p_239805_0_ -> Float.valueOf(p_239805_0_.u_1723_Y)), (App)Codec.FLOAT.fieldOf("scale").forGetter(p_239804_0_ -> Float.valueOf(p_239804_0_.v_4262_N))).apply((Applicative)p_239803_0_, DustParticleOptions::new));
    public static final ParticleOptions.n_1700_B<DustParticleOptions> R_4764_Y = new ParticleOptions.n_1700_B<DustParticleOptions>(){

        public DustParticleOptions n_1700_B(ParticleType<DustParticleOptions> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float f = (float)reader.readDouble();
            reader.expect(' ');
            float f1 = (float)reader.readDouble();
            reader.expect(' ');
            float f2 = (float)reader.readDouble();
            reader.expect(' ');
            float f3 = (float)reader.readDouble();
            return new DustParticleOptions(f, f1, f2, f3);
        }

        public DustParticleOptions n_1700_B(ParticleType<DustParticleOptions> particleTypeIn, b_2585_i buffer) {
            return new DustParticleOptions(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, b_2585_i b_2585_i2) {
            return this.n_1700_B((ParticleType<DustParticleOptions>)w_1969_V2, b_2585_i2);
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B((ParticleType<DustParticleOptions>)w_1969_V2, stringReader);
        }
    };
    private final float G_564_y;
    private final float P_1922_E;
    private final float u_1723_Y;
    private final float v_4262_N;

    public DustParticleOptions(float red, float green, float blue, float alpha) {
        this.G_564_y = red;
        this.P_1922_E = green;
        this.u_1723_Y = blue;
        this.v_4262_N = u_530_F.n_1700_B(alpha, 0.01f, 4.0f);
    }

    @Override
    public void n_1700_B(b_2585_i buffer) {
        buffer.writeFloat(this.G_564_y);
        buffer.writeFloat(this.P_1922_E);
        buffer.writeFloat(this.u_1723_Y);
        buffer.writeFloat(this.v_4262_N);
    }

    @Override
    public String R_4764_Y() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", V_3137_a.g_164_R.J_1907_R(this.G_564_y()), Float.valueOf(this.G_564_y), Float.valueOf(this.P_1922_E), Float.valueOf(this.u_1723_Y), Float.valueOf(this.v_4262_N));
    }

    public ParticleType<DustParticleOptions> G_564_y() {
        return ParticleTypes.Q_4569_t;
    }

    public float n_1700_B() {
        return this.G_564_y;
    }

    public float J_1907_R() {
        return this.P_1922_E;
    }

    public float P_1922_E() {
        return this.u_1723_Y;
    }

    public float u_1723_Y() {
        return this.v_4262_N;
    }
}


