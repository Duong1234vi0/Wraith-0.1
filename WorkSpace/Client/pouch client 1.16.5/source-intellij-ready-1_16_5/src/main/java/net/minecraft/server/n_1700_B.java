/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lightning.product.B_4088_l;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.c_973_a;
import lightning.product.g_2336_b;
import lightning.product.BossEvent;
import lightning.product.ServerBossEvent;
import lightning.product.n_3832_I;
import lightning.product.q_2896_o;
import lightning.product.u_530_F;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class n_1700_B
extends ServerBossEvent {
    private final g_2336_b n_1700_B;
    private final Set<UUID> J_1907_R = Sets.newHashSet();
    private int s_956_w;
    private int u_2550_I = 100;

    public n_1700_B(g_2336_b idIn, x_282_a nameIn) {
        super(nameIn, BossEvent.n_1700_B.v_4262_N, BossEvent.J_1907_R.n_1700_B);
        this.n_1700_B = idIn;
        this.n_1700_B(0.0f);
    }

    public g_2336_b J_1907_R() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(B_4088_l player) {
        super.n_1700_B(player);
        this.J_1907_R.add(player.w_2705_t());
    }

    public void n_1700_B(UUID player) {
        this.J_1907_R.add(player);
    }

    @Override
    public void J_1907_R(B_4088_l player) {
        super.J_1907_R(player);
        this.J_1907_R.remove(player.w_2705_t());
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.J_1907_R.clear();
    }

    public int G_564_y() {
        return this.s_956_w;
    }

    public int P_1922_E() {
        return this.u_2550_I;
    }

    public void n_1700_B(int value) {
        this.s_956_w = value;
        this.n_1700_B(u_530_F.n_1700_B((float)value / (float)this.u_2550_I, 0.0f, 1.0f));
    }

    public void J_1907_R(int max) {
        this.u_2550_I = max;
        this.n_1700_B(u_530_F.n_1700_B((float)this.s_956_w / (float)max, 0.0f, 1.0f));
    }

    public final x_282_a u_1723_Y() {
        return ComponentUtils.n_1700_B(this.t_148_a()).n_1700_B(p_211569_1_ -> p_211569_1_.n_1700_B(this.s_956_w().n_1700_B()).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b(this.J_1907_R().toString()))).n_1700_B(this.J_1907_R().toString()));
    }

    public boolean n_1700_B(Collection<B_4088_l> serverPlayerList) {
        HashSet set = Sets.newHashSet();
        HashSet set1 = Sets.newHashSet();
        for (UUID uuid : this.J_1907_R) {
            boolean flag = false;
            for (B_4088_l serverplayerentity : serverPlayerList) {
                if (!serverplayerentity.w_2705_t().equals(uuid)) continue;
                flag = true;
                break;
            }
            if (flag) continue;
            set.add(uuid);
        }
        for (B_4088_l serverplayerentity1 : serverPlayerList) {
            boolean flag1 = false;
            for (UUID uuid2 : this.J_1907_R) {
                if (!serverplayerentity1.w_2705_t().equals(uuid2)) continue;
                flag1 = true;
                break;
            }
            if (flag1) continue;
            set1.add(serverplayerentity1);
        }
        for (UUID uuid1 : set) {
            for (B_4088_l serverplayerentity3 : this.M_182_A()) {
                if (!serverplayerentity3.w_2705_t().equals(uuid1)) continue;
                this.J_1907_R(serverplayerentity3);
                break;
            }
            this.J_1907_R.remove(uuid1);
        }
        for (B_4088_l serverplayerentity2 : set1) {
            this.n_1700_B(serverplayerentity2);
        }
        return !set.isEmpty() || !set1.isEmpty();
    }

    public U_2912_j v_4262_N() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", x_282_a.n_1700_B.n_1700_B(this.R_4764_Y));
        compoundnbt.n_1700_B("Visible", this.Q_4569_t());
        compoundnbt.J_1907_R("Value", this.s_956_w);
        compoundnbt.J_1907_R("Max", this.u_2550_I);
        compoundnbt.n_1700_B("Color", this.s_956_w().J_1907_R());
        compoundnbt.n_1700_B("Overlay", this.u_2550_I().n_1700_B());
        compoundnbt.n_1700_B("DarkenScreen", this.M_588_G());
        compoundnbt.n_1700_B("PlayBossMusic", this.P_4830_p());
        compoundnbt.n_1700_B("CreateWorldFog", this.h_1847_R());
        q_2896_o listnbt = new q_2896_o();
        for (UUID uuid : this.J_1907_R) {
            listnbt.add(n_3832_I.n_1700_B(uuid));
        }
        compoundnbt.n_1700_B("Players", listnbt);
        return compoundnbt;
    }

    public static n_1700_B n_1700_B(U_2912_j nbt, g_2336_b idIn) {
        n_1700_B customserverbossinfo = new n_1700_B(idIn, x_282_a.n_1700_B.n_1700_B(nbt.M_588_G("Name")));
        customserverbossinfo.G_564_y(nbt.t_1786_h("Visible"));
        customserverbossinfo.n_1700_B(nbt.w_1484_f("Value"));
        customserverbossinfo.J_1907_R(nbt.w_1484_f("Max"));
        customserverbossinfo.n_1700_B(BossEvent.n_1700_B.n_1700_B(nbt.M_588_G("Color")));
        customserverbossinfo.n_1700_B(BossEvent.J_1907_R.n_1700_B(nbt.M_588_G("Overlay")));
        customserverbossinfo.n_1700_B(nbt.t_1786_h("DarkenScreen"));
        customserverbossinfo.J_1907_R(nbt.t_1786_h("PlayBossMusic"));
        customserverbossinfo.R_4764_Y(nbt.t_1786_h("CreateWorldFog"));
        q_2896_o listnbt = nbt.G_564_y("Players", 11);
        for (int i = 0; i < listnbt.size(); ++i) {
            customserverbossinfo.n_1700_B(n_3832_I.n_1700_B(listnbt.s_956_w(i)));
        }
        return customserverbossinfo;
    }

    public void R_4764_Y(B_4088_l player) {
        if (this.J_1907_R.contains(player.w_2705_t())) {
            this.n_1700_B(player);
        }
    }

    public void G_564_y(B_4088_l player) {
        super.J_1907_R(player);
    }
}


