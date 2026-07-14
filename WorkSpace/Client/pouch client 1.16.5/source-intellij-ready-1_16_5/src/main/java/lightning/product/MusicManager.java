/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.SimpleSoundInstance;
import lightning.product.SoundInstance;
import lightning.product.Music;
import lightning.product.MinecraftClient;
import lightning.product.k_4218_M;
import lightning.product.u_530_F;

public class MusicManager {
    private final Random n_1700_B = new Random();
    private final MinecraftClient J_1907_R;
    @Nullable
    private SoundInstance R_4764_Y;
    private int G_564_y = 100;

    public MusicManager(MinecraftClient client) {
        this.J_1907_R = client;
    }

    public void n_1700_B() {
        Music backgroundmusicselector = this.J_1907_R.H_1990_U();
        if (this.R_4764_Y != null) {
            if (!backgroundmusicselector.n_1700_B().n_1700_B().equals(this.R_4764_Y.u_1723_Y()) && backgroundmusicselector.G_564_y()) {
                this.J_1907_R.Z_976_R().J_1907_R(this.R_4764_Y);
                this.G_564_y = u_530_F.n_1700_B(this.n_1700_B, 0, backgroundmusicselector.J_1907_R() / 2);
            }
            if (!this.J_1907_R.Z_976_R().R_4764_Y(this.R_4764_Y)) {
                this.R_4764_Y = null;
                this.G_564_y = Math.min(this.G_564_y, u_530_F.n_1700_B(this.n_1700_B, backgroundmusicselector.J_1907_R(), backgroundmusicselector.R_4764_Y()));
            }
        }
        this.G_564_y = Math.min(this.G_564_y, backgroundmusicselector.R_4764_Y());
        if (this.R_4764_Y == null && this.G_564_y-- <= 0) {
            this.n_1700_B(backgroundmusicselector);
        }
    }

    public void n_1700_B(Music selector) {
        this.R_4764_Y = SimpleSoundInstance.n_1700_B(selector.n_1700_B());
        if (this.R_4764_Y.v_4262_N() != k_4218_M.n_1700_B) {
            this.J_1907_R.Z_976_R().n_1700_B(this.R_4764_Y);
        }
        this.G_564_y = Integer.MAX_VALUE;
    }

    public void J_1907_R() {
        if (this.R_4764_Y != null) {
            this.J_1907_R.Z_976_R().J_1907_R(this.R_4764_Y);
            this.R_4764_Y = null;
        }
        this.G_564_y += 100;
    }

    public boolean J_1907_R(Music selector) {
        return this.R_4764_Y == null ? false : selector.n_1700_B().n_1700_B().equals(this.R_4764_Y.u_1723_Y());
    }
}



