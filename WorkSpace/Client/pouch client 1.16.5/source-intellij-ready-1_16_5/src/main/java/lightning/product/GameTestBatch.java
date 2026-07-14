/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collection;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.TestFunction;
import lightning.product.e_3591_l;

public class GameTestBatch {
    private final String n_1700_B;
    private final Collection<TestFunction> J_1907_R;
    @Nullable
    private final Consumer<e_3591_l> R_4764_Y;

    public GameTestBatch(String p_i226065_1_, Collection<TestFunction> p_i226065_2_, @Nullable Consumer<e_3591_l> p_i226065_3_) {
        if (p_i226065_2_.isEmpty()) {
            throw new IllegalArgumentException("A GameTestBatch must include at least one TestFunction!");
        }
        this.n_1700_B = p_i226065_1_;
        this.J_1907_R = p_i226065_2_;
        this.R_4764_Y = p_i226065_3_;
    }

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public Collection<TestFunction> J_1907_R() {
        return this.J_1907_R;
    }

    public void n_1700_B(e_3591_l p_229464_1_) {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.accept(p_229464_1_);
        }
    }
}


