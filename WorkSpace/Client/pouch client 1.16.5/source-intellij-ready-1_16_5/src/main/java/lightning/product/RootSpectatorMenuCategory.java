/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.F_2904_S;
import lightning.product.SpectatorMenuItem;
import lightning.product.TeleportToTeamMenuCategory;
import lightning.product.TeleportToPlayerMenuCategory;
import lightning.product.s_448_U;
import lightning.product.x_282_a;

public class RootSpectatorMenuCategory
implements s_448_U {
    private static final x_282_a n_1700_B = new F_2904_S("spectatorMenu.root.prompt");
    private final List<SpectatorMenuItem> J_1907_R = Lists.newArrayList();

    public RootSpectatorMenuCategory() {
        this.J_1907_R.add(new TeleportToPlayerMenuCategory());
        this.J_1907_R.add(new TeleportToTeamMenuCategory());
    }

    @Override
    public List<SpectatorMenuItem> n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public x_282_a J_1907_R() {
        return n_1700_B;
    }
}


