/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2226_Q;
import lightning.product.F_1723_g;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.e_3068_v;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;

public class SocialInteractionsPlayerList
extends ContainerObjectSelectionList<e_3068_v> {
    private final F_1723_g n_1700_B;
    private final MinecraftClient J_1907_R;
    private final List<e_3068_v> R_4764_Y = Lists.newArrayList();
    @Nullable
    private String G_564_y;

    public SocialInteractionsPlayerList(F_1723_g p_i242133_1_, MinecraftClient p_i242133_2_, int p_i242133_3_, int p_i242133_4_, int p_i242133_5_, int p_i242133_6_, int p_i242133_7_) {
        super(p_i242133_2_, p_i242133_3_, p_i242133_4_, p_i242133_5_, p_i242133_6_, p_i242133_7_);
        this.n_1700_B = p_i242133_1_;
        this.J_1907_R = p_i242133_2_;
        this.func_244605_b(false);
        this.func_244606_c(false);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        double d0 = this.J_1907_R.RealmsServerPing().w_1457_N();
        c_4037_x.n_1700_B((int)((double)this.getRowLeft() * d0), (int)((double)(this.height - this.y1) * d0), (int)((double)(this.getScrollbarPosition() + 6) * d0), (int)((double)(this.height - (this.height - this.y1) - this.y0 - 4) * d0));
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        c_4037_x.w_1457_N();
    }

    public void n_1700_B(Collection<UUID> p_244759_1_, double p_244759_2_) {
        this.R_4764_Y.clear();
        for (UUID uuid : p_244759_1_) {
            A_2226_Q networkplayerinfo = this.J_1907_R.Y_259_p.n_1700_B.n_1700_B(uuid);
            if (networkplayerinfo == null) continue;
            this.R_4764_Y.add(new e_3068_v(this.J_1907_R, this.n_1700_B, networkplayerinfo.n_1700_B().getId(), networkplayerinfo.n_1700_B().getName(), networkplayerinfo::u_1723_Y));
        }
        this.J_1907_R();
        this.R_4764_Y.sort((p_244655_0_, p_244655_1_) -> p_244655_0_.n_1700_B().compareToIgnoreCase(p_244655_1_.n_1700_B()));
        this.replaceEntries(this.R_4764_Y);
        this.setScrollAmount(p_244759_2_);
    }

    private void J_1907_R() {
        if (this.G_564_y != null) {
            this.R_4764_Y.removeIf(p_244654_1_ -> !p_244654_1_.n_1700_B().toLowerCase(Locale.ROOT).contains(this.G_564_y));
            this.replaceEntries(this.R_4764_Y);
        }
    }

    public void n_1700_B(String p_244658_1_) {
        this.G_564_y = p_244658_1_;
    }

    public boolean n_1700_B() {
        return this.R_4764_Y.isEmpty();
    }

    public void n_1700_B(A_2226_Q p_244657_1_, F_1723_g.n_1700_B p_244657_2_) {
        UUID uuid = p_244657_1_.n_1700_B().getId();
        for (e_3068_v filterlistentry : this.R_4764_Y) {
            if (!filterlistentry.J_1907_R().equals(uuid)) continue;
            filterlistentry.n_1700_B(false);
            return;
        }
        if ((p_244657_2_ == F_1723_g.n_1700_B.n_1700_B || this.J_1907_R.dtoRealmsServerAddress().R_4764_Y(uuid)) && (Strings.isNullOrEmpty((String)this.G_564_y) || p_244657_1_.n_1700_B().getName().toLowerCase(Locale.ROOT).contains(this.G_564_y))) {
            e_3068_v filterlistentry1 = new e_3068_v(this.J_1907_R, this.n_1700_B, p_244657_1_.n_1700_B().getId(), p_244657_1_.n_1700_B().getName(), p_244657_1_::u_1723_Y);
            this.addEntry(filterlistentry1);
            this.R_4764_Y.add(filterlistentry1);
        }
    }

    public void n_1700_B(UUID p_244659_1_) {
        for (e_3068_v filterlistentry : this.R_4764_Y) {
            if (!filterlistentry.J_1907_R().equals(p_244659_1_)) continue;
            filterlistentry.n_1700_B(true);
            return;
        }
    }
}



