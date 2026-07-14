/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.O_728_b;

public class soundsSoundEventRegistration {
    private final List<O_728_b> n_1700_B;
    private final boolean J_1907_R;
    private final String R_4764_Y;

    public soundsSoundEventRegistration(List<O_728_b> soundsIn, boolean replceIn, String subtitleIn) {
        this.n_1700_B = soundsIn;
        this.J_1907_R = replceIn;
        this.R_4764_Y = subtitleIn;
    }

    public List<O_728_b> n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    @Nullable
    public String R_4764_Y() {
        return this.R_4764_Y;
    }
}


