/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.UUID;
import lightning.product.N_4263_v;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.r_4811_B;
import net.optifine.IRandomEntity;

public class RandomEntity
implements IRandomEntity {
    private N_4263_v entity;

    @Override
    public int getId() {
        UUID uuid = this.entity.w_2705_t();
        long i = uuid.getLeastSignificantBits();
        return (int)(i & Integer.MAX_VALUE);
    }

    @Override
    public c_1514_x getSpawnPosition() {
        return this.entity.D_60_a().J_1907_R;
    }

    @Override
    public k_594_Q getSpawnBiome() {
        return this.entity.D_60_a().n_1700_B;
    }

    @Override
    public String getName() {
        return this.entity.t_3452_g() ? this.entity.k_2302_P().getString() : null;
    }

    @Override
    public int getHealth() {
        if (!(this.entity instanceof r_4811_B)) {
            return 0;
        }
        r_4811_B livingentity = (r_4811_B)this.entity;
        return (int)livingentity.g_46_E();
    }

    @Override
    public int getMaxHealth() {
        if (!(this.entity instanceof r_4811_B)) {
            return 0;
        }
        r_4811_B livingentity = (r_4811_B)this.entity;
        return (int)livingentity.L_1733_J();
    }

    public N_4263_v getEntity() {
        return this.entity;
    }

    public void setEntity(N_4263_v entity) {
        this.entity = entity;
    }
}

