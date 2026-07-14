/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ComparisonChain
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Ordering
 */
package lightning.product;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import lightning.product.A_2226_Q;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.PlayerMenuItem;
import lightning.product.SpectatorMenuItem;
import lightning.product.SpectatorGui;
import lightning.product.X_2140_T;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.s_448_U;
import lightning.product.x_282_a;

public class TeleportToPlayerMenuCategory
implements SpectatorMenuItem,
s_448_U {
    private static final Ordering<A_2226_Q> n_1700_B = Ordering.from((p_210243_0_, p_210243_1_) -> ComparisonChain.start().compare((Comparable)p_210243_0_.n_1700_B().getId(), (Comparable)p_210243_1_.n_1700_B().getId()).result());
    private static final x_282_a J_1907_R = new F_2904_S("spectatorMenu.teleport");
    private static final x_282_a R_4764_Y = new F_2904_S("spectatorMenu.teleport.prompt");
    private final List<SpectatorMenuItem> G_564_y = Lists.newArrayList();

    public TeleportToPlayerMenuCategory() {
        this(n_1700_B.sortedCopy(MinecraftClient.A_4115_X().k_2293_S().P_1922_E()));
    }

    public TeleportToPlayerMenuCategory(Collection<A_2226_Q> profiles) {
        for (A_2226_Q networkplayerinfo : n_1700_B.sortedCopy(profiles)) {
            if (networkplayerinfo.J_1907_R() == I_14_v.P_1922_E) continue;
            this.G_564_y.add(new PlayerMenuItem(networkplayerinfo.n_1700_B()));
        }
    }

    @Override
    public List<SpectatorMenuItem> n_1700_B() {
        return this.G_564_y;
    }

    @Override
    public x_282_a J_1907_R() {
        return R_4764_Y;
    }

    @Override
    public void n_1700_B(X_2140_T menu) {
        menu.n_1700_B(this);
    }

    @Override
    public x_282_a R_4764_Y() {
        return J_1907_R;
    }

    @Override
    public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(SpectatorGui.n_1700_B);
        C_2701_A.blit(p_230485_1_, 0, 0, 0.0f, 0.0f, 16, 16, 256, 256);
    }

    @Override
    public boolean G_564_y() {
        return !this.G_564_y.isEmpty();
    }
}



