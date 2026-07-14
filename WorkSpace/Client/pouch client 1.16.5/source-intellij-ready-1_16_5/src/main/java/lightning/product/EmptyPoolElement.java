/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

public class EmptyPoolElement
extends StructurePoolElement {
    public static final Codec<EmptyPoolElement> n_1700_B;
    public static final EmptyPoolElement J_1907_R;

    private EmptyPoolElement() {
        super(X_2241_P.n_1700_B.n_1700_B);
    }

    @Override
    public List<a_2886_t.J_1907_R> n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn, Random rand) {
        return Collections.emptyList();
    }

    @Override
    public BoundingBox n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn) {
        return BoundingBox.n_1700_B();
    }

    @Override
    public boolean n_1700_B(b_2085_h p_230378_1_, WorldGenLevel p_230378_2_, J_3017_d p_230378_3_, z_1753_f p_230378_4_, c_1514_x p_230378_5_, c_1514_x p_230378_6_, W_2163_m p_230378_7_, BoundingBox p_230378_8_, Random p_230378_9_, boolean p_230378_10_) {
        return true;
    }

    @Override
    public StructurePoolElementType<?> n_1700_B() {
        return StructurePoolElementType.G_564_y;
    }

    public String toString() {
        return "Empty";
    }

    static {
        J_1907_R = new EmptyPoolElement();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


