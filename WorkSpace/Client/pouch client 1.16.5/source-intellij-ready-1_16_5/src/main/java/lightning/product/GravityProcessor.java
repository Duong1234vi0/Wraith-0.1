/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import javax.annotation.Nullable;
import lightning.product.T_1316_M;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;
import lightning.product.z_2963_s;

public class GravityProcessor
extends StructureProcessor {
    public static final Codec<GravityProcessor> n_1700_B = RecordCodecBuilder.create(p_237082_0_ -> p_237082_0_.group((App)z_2963_s.n_1700_B.v_4262_N.fieldOf("heightmap").orElse((Object)z_2963_s.n_1700_B.n_1700_B).forGetter(p_237084_0_ -> p_237084_0_.J_1907_R), (App)Codec.INT.fieldOf("offset").orElse((Object)0).forGetter(p_237083_0_ -> p_237083_0_.R_4764_Y)).apply((Applicative)p_237082_0_, GravityProcessor::new));
    private final z_2963_s.n_1700_B J_1907_R;
    private final int R_4764_Y;

    public GravityProcessor(z_2963_s.n_1700_B heightmap, int offset) {
        this.J_1907_R = heightmap;
        this.R_4764_Y = offset;
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        z_2963_s.n_1700_B heightmap$type = p_230386_1_ instanceof e_3591_l ? (this.J_1907_R == z_2963_s.n_1700_B.n_1700_B ? z_2963_s.n_1700_B.J_1907_R : (this.J_1907_R == z_2963_s.n_1700_B.R_4764_Y ? z_2963_s.n_1700_B.G_564_y : this.J_1907_R)) : this.J_1907_R;
        int i = p_230386_1_.n_1700_B(heightmap$type, p_230386_5_.n_1700_B.getX(), p_230386_5_.n_1700_B.getZ()) + this.R_4764_Y;
        int j = p_230386_4_.n_1700_B.getY();
        return new a_2886_t.J_1907_R(new c_1514_x(p_230386_5_.n_1700_B.getX(), i + j, p_230386_5_.n_1700_B.getZ()), p_230386_5_.J_1907_R, p_230386_5_.R_4764_Y);
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.R_4764_Y;
    }
}


