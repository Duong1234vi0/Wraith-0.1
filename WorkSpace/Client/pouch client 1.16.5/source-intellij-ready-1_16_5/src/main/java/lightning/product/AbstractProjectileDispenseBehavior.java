/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Projectile;
import lightning.product.S_3458_C;
import lightning.product.Position;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.BlockSource;

public abstract class AbstractProjectileDispenseBehavior
extends DefaultDispenseItemBehavior {
    @Override
    public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
        e_3591_l world = source.v_4262_N();
        Position iposition = S_3458_C.n_1700_B(source);
        b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
        Projectile projectileentity = this.n_1700_B(world, iposition, stack);
        projectileentity.R_4764_Y(direction.t_148_a(), (float)direction.s_956_w() + 0.1f, direction.u_2550_I(), this.R_4764_Y(), this.J_1907_R());
        world.a_(projectileentity);
        stack.v_4262_N(1);
        return stack;
    }

    @Override
    protected void n_1700_B(BlockSource source) {
        source.v_4262_N().R_4764_Y(1002, source.G_564_y(), 0);
    }

    protected abstract Projectile n_1700_B(b_4507_u var1, Position var2, Z_1993_T var3);

    protected float J_1907_R() {
        return 6.0f;
    }

    protected float R_4764_Y() {
        return 1.1f;
    }
}


