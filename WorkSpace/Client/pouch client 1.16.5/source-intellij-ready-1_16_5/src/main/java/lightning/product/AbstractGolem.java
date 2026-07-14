/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.P_11_z;
import lightning.product.SoundEvent;
import lightning.product.b_4507_u;
import lightning.product.PathfinderMob;
import lightning.product.t_5_h;

public abstract class AbstractGolem
extends PathfinderMob {
    protected AbstractGolem(t_5_h<? extends AbstractGolem> type, b_4507_u worldIn) {
        super((t_5_h<? extends PathfinderMob>)type, worldIn);
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return null;
    }

    @Override
    public int v_4276_D() {
        return 120;
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return false;
    }
}


