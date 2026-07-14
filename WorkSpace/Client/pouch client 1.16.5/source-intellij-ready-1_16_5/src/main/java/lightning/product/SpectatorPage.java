/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import java.util.List;
import lightning.product.SpectatorMenuItem;
import lightning.product.X_2140_T;
import lightning.product.s_448_U;

public class SpectatorPage {
    private final s_448_U n_1700_B;
    private final List<SpectatorMenuItem> J_1907_R;
    private final int R_4764_Y;

    public SpectatorPage(s_448_U categoryIn, List<SpectatorMenuItem> itemsIn, int selectedIndex) {
        this.n_1700_B = categoryIn;
        this.J_1907_R = itemsIn;
        this.R_4764_Y = selectedIndex;
    }

    public SpectatorMenuItem n_1700_B(int index) {
        return index >= 0 && index < this.J_1907_R.size() ? (SpectatorMenuItem)MoreObjects.firstNonNull((Object)this.J_1907_R.get(index), (Object)X_2140_T.n_1700_B) : X_2140_T.n_1700_B;
    }

    public int n_1700_B() {
        return this.R_4764_Y;
    }
}


