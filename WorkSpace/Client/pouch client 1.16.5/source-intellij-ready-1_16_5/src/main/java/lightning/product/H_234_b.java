/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.I_2212_R;
import lightning.product.J_1565_t;
import lightning.product.N_1091_Y;
import lightning.product.U_2871_b;
import lightning.product.U_679_Y;
import lightning.product.V_4423_d;

public class H_234_b
extends I_2212_R {
    public H_234_b(U_679_Y mainWindowIn) {
        this(mainWindowIn, mainWindowIn.Y_601_j());
    }

    private H_234_b(U_679_Y mainWindowIn, @Nullable N_1091_Y monitorIn) {
        super("options.fullscreen.resolution", -1.0, monitorIn != null ? (double)(monitorIn.P_1922_E() - 1) : -1.0, 1.0f, (V_4423_d p_225306_2_) -> {
            if (monitorIn == null) {
                return -1.0;
            }
            Optional<J_1565_t> optional = mainWindowIn.u_1723_Y();
            return optional.map(p_225304_1_ -> monitorIn.n_1700_B((J_1565_t)p_225304_1_)).orElse(-1.0);
        }, (V_4423_d p_225303_2_, Double p_225303_3_) -> {
            if (monitorIn != null) {
                if (p_225303_3_ == -1.0) {
                    mainWindowIn.n_1700_B(Optional.empty());
                } else {
                    mainWindowIn.n_1700_B(Optional.of(monitorIn.n_1700_B(p_225303_3_.intValue())));
                }
            }
        }, (V_4423_d p_225305_1_, I_2212_R p_225305_2_) -> {
            if (monitorIn == null) {
                return new F_2904_S("options.fullscreen.unavailable");
            }
            double d0 = p_225305_2_.get((V_4423_d)p_225305_1_);
            return d0 == -1.0 ? p_225305_2_.getGenericValueComponent(new F_2904_S("options.fullscreen.current")) : p_225305_2_.getGenericValueComponent(new U_2871_b(monitorIn.n_1700_B((int)d0).toString()));
        });
    }
}

