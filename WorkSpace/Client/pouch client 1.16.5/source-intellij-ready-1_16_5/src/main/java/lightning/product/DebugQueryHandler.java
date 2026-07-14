/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.H_1420_X;
import lightning.product.T_4830_s;
import lightning.product.U_2912_j;
import lightning.product.W_2853_p;
import lightning.product.c_1514_x;

public class DebugQueryHandler {
    private final W_2853_p n_1700_B;
    private int J_1907_R = -1;
    @Nullable
    private Consumer<U_2912_j> R_4764_Y;

    public DebugQueryHandler(W_2853_p p_i49773_1_) {
        this.n_1700_B = p_i49773_1_;
    }

    public boolean n_1700_B(int p_211548_1_, @Nullable U_2912_j p_211548_2_) {
        if (this.J_1907_R == p_211548_1_ && this.R_4764_Y != null) {
            this.R_4764_Y.accept(p_211548_2_);
            this.R_4764_Y = null;
            return true;
        }
        return false;
    }

    private int n_1700_B(Consumer<U_2912_j> p_211546_1_) {
        this.R_4764_Y = p_211546_1_;
        return ++this.J_1907_R;
    }

    public void n_1700_B(int entId, Consumer<U_2912_j> p_211549_2_) {
        int i = this.n_1700_B(p_211549_2_);
        this.n_1700_B.n_1700_B(new T_4830_s(i, entId));
    }

    public void n_1700_B(c_1514_x p_211547_1_, Consumer<U_2912_j> p_211547_2_) {
        int i = this.n_1700_B(p_211547_2_);
        this.n_1700_B.n_1700_B(new H_1420_X(i, p_211547_1_));
    }
}


