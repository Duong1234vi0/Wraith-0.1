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
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.ItemInput;
import lightning.product.ItemParser;
import lightning.product.ParticleType;

public class N_3869_i
implements ParticleOptions {
    public static final ParticleOptions.n_1700_B<N_3869_i> n_1700_B = new ParticleOptions.n_1700_B<N_3869_i>(){

        public N_3869_i n_1700_B(ParticleType<N_3869_i> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            ItemParser itemparser = new ItemParser(reader, false).v_4262_N();
            Z_1993_T itemstack = new ItemInput(itemparser.n_1700_B(), itemparser.J_1907_R()).n_1700_B(1, false);
            return new N_3869_i(particleTypeIn, itemstack);
        }

        public N_3869_i n_1700_B(ParticleType<N_3869_i> particleTypeIn, b_2585_i buffer) {
            return new N_3869_i(particleTypeIn, buffer.u_2550_I());
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, b_2585_i b_2585_i2) {
            return this.n_1700_B((ParticleType<N_3869_i>)w_1969_V2, b_2585_i2);
        }

        @Override
        public /* synthetic */ ParticleOptions J_1907_R(ParticleType w_1969_V2, StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B((ParticleType<N_3869_i>)w_1969_V2, stringReader);
        }
    };
    private final ParticleType<N_3869_i> J_1907_R;
    private final Z_1993_T R_4764_Y;

    public static Codec<N_3869_i> n_1700_B(ParticleType<N_3869_i> p_239809_0_) {
        return Z_1993_T.n_1700_B.xmap(p_239810_1_ -> new N_3869_i(p_239809_0_, (Z_1993_T)p_239810_1_), p_239808_0_ -> p_239808_0_.R_4764_Y);
    }

    public N_3869_i(ParticleType<N_3869_i> p_i47952_1_, Z_1993_T p_i47952_2_) {
        this.J_1907_R = p_i47952_1_;
        this.R_4764_Y = p_i47952_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buffer) {
        buffer.n_1700_B(this.R_4764_Y);
    }

    @Override
    public String R_4764_Y() {
        return String.valueOf(V_3137_a.g_164_R.J_1907_R(this.G_564_y())) + " " + new ItemInput(this.R_4764_Y.J_1907_R(), this.R_4764_Y.Q_4569_t()).J_1907_R();
    }

    public ParticleType<N_3869_i> G_564_y() {
        return this.J_1907_R;
    }

    public Z_1993_T n_1700_B() {
        return this.R_4764_Y;
    }
}


