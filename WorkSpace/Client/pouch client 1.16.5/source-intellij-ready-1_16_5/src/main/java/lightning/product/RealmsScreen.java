/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.stream.Collectors;
import lightning.product.I_1084_e;
import lightning.product.V_2511_L;
import lightning.product.e_1813_Z;
import lightning.product.k_2603_m;
import lightning.product.NarrationHelper;
import lightning.product.RealmsLabel;

public abstract class RealmsScreen
extends k_2603_m {
    public RealmsScreen() {
        super(I_1084_e.n_1700_B);
    }

    protected static int G_564_y(int p_239562_0_) {
        return 40 + p_239562_0_ * 13;
    }

    @Override
    public void tick() {
        for (V_2511_L widget : this.buttons) {
            if (!(widget instanceof e_1813_Z)) continue;
            ((e_1813_Z)((Object)widget)).tick();
        }
    }

    public void P_1922_E() {
        List<String> list = this.children.stream().filter(RealmsLabel.class::isInstance).map(RealmsLabel.class::cast).map(RealmsLabel::n_1700_B).collect(Collectors.toList());
        NarrationHelper.n_1700_B(list);
    }
}


