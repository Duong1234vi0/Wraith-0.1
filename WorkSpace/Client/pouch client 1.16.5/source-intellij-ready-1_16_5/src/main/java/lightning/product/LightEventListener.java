/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1514_x;
import lightning.product.SectionPos;

public interface LightEventListener {
    default public void n_1700_B(c_1514_x p_215567_1_, boolean p_215567_2_) {
        this.n_1700_B(SectionPos.n_1700_B(p_215567_1_), p_215567_2_);
    }

    public void n_1700_B(SectionPos var1, boolean var2);
}


