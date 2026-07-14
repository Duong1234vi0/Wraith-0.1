/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.O_4606_n;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.StructurePoolElementType;
import lightning.product.StructurePoolElement;
import lightning.product.JigsawBlock;
import lightning.product.JigsawBlockEntity;
import lightning.product.z_1753_f;

public class z_4596_H
extends StructurePoolElement {
    public static final Codec<z_4596_H> n_1700_B = RecordCodecBuilder.create(p_236817_0_ -> p_236817_0_.group((App)ConfiguredFeature.J_1907_R.fieldOf("feature").forGetter(p_236818_0_ -> p_236818_0_.J_1907_R), z_4596_H.J_1907_R()).apply((Applicative)p_236817_0_, z_4596_H::new));
    private final Supplier<ConfiguredFeature<?, ?>> J_1907_R;
    private final U_2912_j G_564_y;

    protected z_4596_H(Supplier<ConfiguredFeature<?, ?>> p_i242004_1_, X_2241_P.n_1700_B p_i242004_2_) {
        super(p_i242004_2_);
        this.J_1907_R = p_i242004_1_;
        this.G_564_y = this.u_1723_Y();
    }

    private U_2912_j u_1723_Y() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("name", "minecraft:bottom");
        compoundnbt.n_1700_B("final_state", "minecraft:air");
        compoundnbt.n_1700_B("pool", "minecraft:empty");
        compoundnbt.n_1700_B("target", "minecraft:empty");
        compoundnbt.n_1700_B("joint", JigsawBlockEntity.n_1700_B.n_1700_B.n_1700_B());
        return compoundnbt;
    }

    public c_1514_x n_1700_B(b_2085_h p_214868_1_, W_2163_m p_214868_2_) {
        return c_1514_x.ZERO;
    }

    @Override
    public List<a_2886_t.J_1907_R> n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn, Random rand) {
        ArrayList list = Lists.newArrayList();
        list.add(new a_2886_t.J_1907_R(pos, (K_4074_S)a_3742_W.q_2034_t.multiplayerClientSuggestionProvider().n_1700_B(JigsawBlock.P_4830_p, O_4606_n.n_1700_B(b_257_Y.n_1700_B, b_257_Y.G_564_y)), this.G_564_y));
        return list;
    }

    @Override
    public BoundingBox n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn) {
        c_1514_x blockpos = this.n_1700_B(templateManagerIn, rotationIn);
        return new BoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + blockpos.getX(), pos.getY() + blockpos.getY(), pos.getZ() + blockpos.getZ());
    }

    @Override
    public boolean n_1700_B(b_2085_h p_230378_1_, WorldGenLevel p_230378_2_, J_3017_d p_230378_3_, z_1753_f p_230378_4_, c_1514_x p_230378_5_, c_1514_x p_230378_6_, W_2163_m p_230378_7_, BoundingBox p_230378_8_, Random p_230378_9_, boolean p_230378_10_) {
        return this.J_1907_R.get().n_1700_B(p_230378_2_, p_230378_4_, p_230378_9_, p_230378_5_);
    }

    @Override
    public StructurePoolElementType<?> n_1700_B() {
        return StructurePoolElementType.R_4764_Y;
    }

    public String toString() {
        return "Feature[" + String.valueOf(V_3137_a.RealmsServerPing.J_1907_R((Feature<?>)this.J_1907_R.get().J_1907_R())) + "]";
    }
}


