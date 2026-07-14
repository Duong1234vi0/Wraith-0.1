/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.SocialInteractionsService
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.SocialInteractionsService;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lightning.product.A_2226_Q;
import lightning.product.F_1723_g;
import lightning.product.MinecraftClient;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;

public class PlayerSocialManager {
    private final MinecraftClient n_1700_B;
    private final Set<UUID> J_1907_R = Sets.newHashSet();
    private final SocialInteractionsService R_4764_Y;
    private final Map<String, UUID> G_564_y = Maps.newHashMap();

    public PlayerSocialManager(MinecraftClient p_i242141_1_, SocialInteractionsService p_i242141_2_) {
        this.n_1700_B = p_i242141_1_;
        this.R_4764_Y = p_i242141_2_;
    }

    public void n_1700_B(UUID p_244646_1_) {
        this.J_1907_R.add(p_244646_1_);
    }

    public void J_1907_R(UUID p_244647_1_) {
        this.J_1907_R.remove(p_244647_1_);
    }

    public boolean R_4764_Y(UUID p_244756_1_) {
        return this.G_564_y(p_244756_1_) || this.P_1922_E(p_244756_1_);
    }

    public boolean G_564_y(UUID p_244648_1_) {
        return this.J_1907_R.contains(p_244648_1_);
    }

    public boolean P_1922_E(UUID p_244757_1_) {
        return this.R_4764_Y.isBlockedPlayer(p_244757_1_);
    }

    public Set<UUID> n_1700_B() {
        return this.J_1907_R;
    }

    public UUID n_1700_B(String p_244797_1_) {
        return this.G_564_y.getOrDefault(p_244797_1_, j_3341_s.J_1907_R);
    }

    public void n_1700_B(A_2226_Q p_244645_1_) {
        k_2603_m screen;
        GameProfile gameprofile = p_244645_1_.n_1700_B();
        if (gameprofile.isComplete()) {
            this.G_564_y.put(gameprofile.getName(), gameprofile.getId());
        }
        if ((screen = this.n_1700_B.Y_1740_V) instanceof F_1723_g) {
            F_1723_g socialinteractionsscreen = (F_1723_g)screen;
            socialinteractionsscreen.n_1700_B(p_244645_1_);
        }
    }

    public void u_1723_Y(UUID p_244649_1_) {
        k_2603_m screen = this.n_1700_B.Y_1740_V;
        if (screen instanceof F_1723_g) {
            F_1723_g socialinteractionsscreen = (F_1723_g)screen;
            socialinteractionsscreen.n_1700_B(p_244649_1_);
        }
    }
}



