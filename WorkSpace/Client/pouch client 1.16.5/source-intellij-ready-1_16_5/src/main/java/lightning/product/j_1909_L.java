/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;
import lightning.product.B_3871_I;

public class j_1909_L
extends RuntimeException {
    private final Collection<B_3871_I.n_1700_B> n_1700_B;

    public j_1909_L(B_3871_I.n_1700_B spriteInfoIn, Collection<B_3871_I.n_1700_B> spriteInfosIn) {
        super(String.format("Unable to fit: %s - size: %dx%d - Maybe try a lower resolution resourcepack?", spriteInfoIn.n_1700_B(), spriteInfoIn.J_1907_R(), spriteInfoIn.R_4764_Y()));
        this.n_1700_B = spriteInfosIn;
    }

    public Collection<B_3871_I.n_1700_B> n_1700_B() {
        return this.n_1700_B;
    }

    public j_1909_L(B_3871_I.n_1700_B p_i242107_1_, Collection<B_3871_I.n_1700_B> p_i242107_2_, int p_i242107_3_, int p_i242107_4_, int p_i242107_5_, int p_i242107_6_) {
        super(String.format("Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?", String.valueOf(p_i242107_1_.n_1700_B()), p_i242107_1_.J_1907_R(), p_i242107_1_.R_4764_Y(), p_i242107_3_, p_i242107_4_, p_i242107_5_, p_i242107_6_));
        this.n_1700_B = p_i242107_2_;
    }
}

