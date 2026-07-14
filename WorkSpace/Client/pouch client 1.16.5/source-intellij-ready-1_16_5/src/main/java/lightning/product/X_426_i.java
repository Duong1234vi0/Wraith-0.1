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
import lightning.product.K_4074_S;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.f_71_T;
import lightning.product.ParticleType;

public class X_426_i
implements ParticleOptions {
    public static final ParticleOptions.n_1700_B<X_426_i> n_1700_B = new ParticleOptions.n_1700_B<X_426_i>(){

        public X_426_i n_1700_B(ParticleType<X_426_i> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            return new X_426_i(particleTypeIn, new f_71_T(reader, false).n_1700_B(false).J_1907_R());
        }

        public X_426_i n_1700_B(ParticleType<X_426_i> particleTypeIn, b_2585_i buffer) {
            return new X_426_i(particleTypeIn, T_2915_h.t_4043_B.n_1700_B(buffer.u_1723_Y()));
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, b_2585_i b_2585_i2) {
            return this.n_1700_B((ParticleType<X_426_i>)w_1969_V2, b_2585_i2);
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B((ParticleType<X_426_i>)w_1969_V2, stringReader);
        }
    };
    private final ParticleType<X_426_i> J_1907_R;
    private final K_4074_S R_4764_Y;

    public static Codec<X_426_i> n_1700_B(ParticleType<X_426_i> p_239800_0_) {
        return K_4074_S.J_1907_R.xmap(p_239801_1_ -> new X_426_i(p_239800_0_, (K_4074_S)p_239801_1_), p_239799_0_ -> p_239799_0_.R_4764_Y);
    }

    public X_426_i(ParticleType<X_426_i> particleTypeIn, K_4074_S blockStateIn) {
        this.J_1907_R = particleTypeIn;
        this.R_4764_Y = blockStateIn;
    }

    @Override
    public void n_1700_B(b_2585_i buffer) {
        buffer.G_564_y(T_2915_h.t_4043_B.n_1700_B(this.R_4764_Y));
    }

    @Override
    public String R_4764_Y() {
        return String.valueOf(V_3137_a.g_164_R.J_1907_R(this.G_564_y())) + " " + f_71_T.n_1700_B(this.R_4764_Y);
    }

    public ParticleType<X_426_i> G_564_y() {
        return this.J_1907_R;
    }

    public K_4074_S n_1700_B() {
        return this.R_4764_Y;
    }
}


