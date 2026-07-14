/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePoolElementType;
import lightning.product.StructurePoolElement;
import lightning.product.z_1753_f;

public class K_4040_w
extends StructurePoolElement {
    public static final Codec<K_4040_w> n_1700_B = RecordCodecBuilder.create(p_236835_0_ -> p_236835_0_.group((App)StructurePoolElement.R_4764_Y.listOf().fieldOf("elements").forGetter(p_236836_0_ -> p_236836_0_.J_1907_R), K_4040_w.J_1907_R()).apply((Applicative)p_236835_0_, K_4040_w::new));
    private final List<StructurePoolElement> J_1907_R;

    public K_4040_w(List<StructurePoolElement> p_i51405_1_, X_2241_P.n_1700_B p_i51405_2_) {
        super(p_i51405_2_);
        if (p_i51405_1_.isEmpty()) {
            throw new IllegalArgumentException("Elements are empty");
        }
        this.J_1907_R = p_i51405_1_;
        this.J_1907_R(p_i51405_2_);
    }

    @Override
    public List<a_2886_t.J_1907_R> n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn, Random rand) {
        return this.J_1907_R.get(0).n_1700_B(templateManagerIn, pos, rotationIn, rand);
    }

    @Override
    public BoundingBox n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn) {
        BoundingBox mutableboundingbox = BoundingBox.n_1700_B();
        for (StructurePoolElement jigsawpiece : this.J_1907_R) {
            BoundingBox mutableboundingbox1 = jigsawpiece.n_1700_B(templateManagerIn, pos, rotationIn);
            mutableboundingbox.J_1907_R(mutableboundingbox1);
        }
        return mutableboundingbox;
    }

    @Override
    public boolean n_1700_B(b_2085_h p_230378_1_, WorldGenLevel p_230378_2_, J_3017_d p_230378_3_, z_1753_f p_230378_4_, c_1514_x p_230378_5_, c_1514_x p_230378_6_, W_2163_m p_230378_7_, BoundingBox p_230378_8_, Random p_230378_9_, boolean p_230378_10_) {
        for (StructurePoolElement jigsawpiece : this.J_1907_R) {
            if (jigsawpiece.n_1700_B(p_230378_1_, p_230378_2_, p_230378_3_, p_230378_4_, p_230378_5_, p_230378_6_, p_230378_7_, p_230378_8_, p_230378_9_, p_230378_10_)) continue;
            return false;
        }
        return true;
    }

    @Override
    public StructurePoolElementType<?> n_1700_B() {
        return StructurePoolElementType.J_1907_R;
    }

    @Override
    public StructurePoolElement n_1700_B(X_2241_P.n_1700_B placementBehaviour) {
        super.n_1700_B(placementBehaviour);
        this.J_1907_R(placementBehaviour);
        return this;
    }

    public String toString() {
        return "List[" + this.J_1907_R.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
    }

    private void J_1907_R(X_2241_P.n_1700_B p_214864_1_) {
        this.J_1907_R.forEach(p_214863_1_ -> p_214863_1_.n_1700_B(p_214864_1_));
    }
}


