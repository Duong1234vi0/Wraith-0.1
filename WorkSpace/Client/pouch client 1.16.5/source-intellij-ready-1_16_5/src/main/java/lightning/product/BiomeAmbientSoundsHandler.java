/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.Optional;
import java.util.Random;
import lightning.product.D_38_f;
import lightning.product.AmbientMoodSettings;
import lightning.product.BiomeManager;
import lightning.product.K_4719_o;
import lightning.product.SimpleSoundInstance;
import lightning.product.V_4964_s;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundEvent;
import lightning.product.X_4340_E;
import lightning.product.SoundInstance;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.AmbientAdditionsSettings;
import lightning.product.k_4218_M;
import lightning.product.k_594_Q;
import lightning.product.u_530_F;

public class BiomeAmbientSoundsHandler
implements V_4964_s {
    private final X_4340_E n_1700_B;
    private final k_4218_M J_1907_R;
    private final BiomeManager R_4764_Y;
    private final Random G_564_y;
    private Object2ObjectArrayMap<k_594_Q, n_1700_B> P_1922_E = new Object2ObjectArrayMap();
    private Optional<AmbientMoodSettings> u_1723_Y = Optional.empty();
    private Optional<AmbientAdditionsSettings> v_4262_N = Optional.empty();
    private float w_1484_f;
    private k_594_Q t_148_a;

    public BiomeAmbientSoundsHandler(X_4340_E player, k_4218_M soundHandler, BiomeManager biomeManager) {
        this.G_564_y = player.O_508_d.e_4240_b();
        this.n_1700_B = player;
        this.J_1907_R = soundHandler;
        this.R_4764_Y = biomeManager;
    }

    public float n_1700_B() {
        return this.w_1484_f;
    }

    @Override
    public void J_1907_R() {
        this.P_1922_E.values().removeIf(AbstractTickableSoundInstance::multiplayerClientSuggestionProvider);
        k_594_Q biome = this.R_4764_Y.n_1700_B(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
        if (biome != this.t_148_a) {
            this.t_148_a = biome;
            this.u_1723_Y = biome.t_1786_h();
            this.v_4262_N = biome.multiplayerClientSuggestionProvider();
            this.P_1922_E.values().forEach(n_1700_B::n_1700_B);
            biome.M_182_A().ifPresent(soundEvent -> {
                n_1700_B biomesoundhandler$sound = (n_1700_B)this.P_1922_E.compute((Object)biome, (biomeIn, biomeSound) -> {
                    if (biomeSound == null) {
                        biomeSound = new n_1700_B((SoundEvent)soundEvent);
                        this.J_1907_R.n_1700_B((SoundInstance)biomeSound);
                    }
                    biomeSound.J_1907_R();
                    return biomeSound;
                });
            });
        }
        this.v_4262_N.ifPresent(currentAmbientAdditionalSound -> {
            if (this.G_564_y.nextDouble() < currentAmbientAdditionalSound.J_1907_R()) {
                this.J_1907_R.n_1700_B(SimpleSoundInstance.J_1907_R(currentAmbientAdditionalSound.n_1700_B()));
            }
        });
        this.u_1723_Y.ifPresent(currentAmbientMoodSound -> {
            b_4507_u world = this.n_1700_B.O_508_d;
            int i = currentAmbientMoodSound.R_4764_Y() * 2 + 1;
            c_1514_x blockpos = new c_1514_x(this.n_1700_B.O_3598_v() + (double)this.G_564_y.nextInt(i) - (double)currentAmbientMoodSound.R_4764_Y(), this.n_1700_B.X_2048_Y() + (double)this.G_564_y.nextInt(i) - (double)currentAmbientMoodSound.R_4764_Y(), this.n_1700_B.l_2647_k() + (double)this.G_564_y.nextInt(i) - (double)currentAmbientMoodSound.R_4764_Y());
            int j = world.getLightFor(K_4719_o.n_1700_B, blockpos);
            this.w_1484_f = j > 0 ? (this.w_1484_f -= (float)j / (float)world.Z_875_P() * 0.001f) : (this.w_1484_f -= (float)(world.getLightFor(K_4719_o.J_1907_R, blockpos) - 1) / (float)currentAmbientMoodSound.J_1907_R());
            if (this.w_1484_f >= 1.0f) {
                double d0 = (double)blockpos.getX() + 0.5;
                double d1 = (double)blockpos.getY() + 0.5;
                double d2 = (double)blockpos.getZ() + 0.5;
                double d3 = d0 - this.n_1700_B.O_3598_v();
                double d4 = d1 - this.n_1700_B.X_2048_Y();
                double d5 = d2 - this.n_1700_B.l_2647_k();
                double d6 = u_530_F.n_1700_B(d3 * d3 + d4 * d4 + d5 * d5);
                double d7 = d6 + currentAmbientMoodSound.G_564_y();
                SimpleSoundInstance simplesound = SimpleSoundInstance.J_1907_R(currentAmbientMoodSound.n_1700_B(), this.n_1700_B.O_3598_v() + d3 / d6 * d7, this.n_1700_B.X_2048_Y() + d4 / d6 * d7, this.n_1700_B.l_2647_k() + d5 / d6 * d7);
                this.J_1907_R.n_1700_B(simplesound);
                this.w_1484_f = 0.0f;
            } else {
                this.w_1484_f = Math.max(this.w_1484_f, 0.0f);
            }
        });
    }

    public static class n_1700_B
    extends AbstractTickableSoundInstance {
        private int n_1700_B;
        private int Q_4569_t;

        public n_1700_B(SoundEvent sound) {
            super(sound, D_38_f.t_148_a);
            this.s_956_w = true;
            this.u_2550_I = 0;
            this.P_1922_E = 1.0f;
            this.h_1847_R = true;
        }

        @Override
        public void R_4764_Y() {
            if (this.Q_4569_t < 0) {
                this.w_1457_N();
            }
            this.Q_4569_t += this.n_1700_B;
            this.P_1922_E = u_530_F.n_1700_B((float)this.Q_4569_t / 40.0f, 0.0f, 1.0f);
        }

        public void n_1700_B() {
            this.Q_4569_t = Math.min(this.Q_4569_t, 40);
            this.n_1700_B = -1;
        }

        public void J_1907_R() {
            this.Q_4569_t = Math.max(0, this.Q_4569_t);
            this.n_1700_B = 1;
        }
    }
}


