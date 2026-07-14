/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.E_4668_a;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class MoveToTargetSink
extends Behavior<Z_530_i> {
    private int n_1700_B;
    @Nullable
    private b_1722_e R_4764_Y;
    @Nullable
    private c_1514_x G_564_y;
    private float P_1922_E;

    public MoveToTargetSink() {
        this(150, 250);
    }

    public MoveToTargetSink(int p_i241908_1_, int p_i241908_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Y_1740_V, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.Y_601_j, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.n_1700_B)), p_i241908_1_, p_i241908_2_);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i owner) {
        if (this.n_1700_B > 0) {
            --this.n_1700_B;
            return false;
        }
        E_4668_a<?> brain = owner.y_1945_D();
        WalkTarget walktarget = brain.R_4764_Y(MemoryModuleType.P_4830_p).get();
        boolean flag = this.n_1700_B(owner, walktarget);
        if (!flag && this.n_1700_B(owner, walktarget, worldIn.X_933_l())) {
            this.G_564_y = walktarget.n_1700_B().J_1907_R();
            return true;
        }
        brain.J_1907_R(MemoryModuleType.P_4830_p);
        if (flag) {
            brain.J_1907_R(MemoryModuleType.Y_1740_V);
        }
        return false;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        if (this.R_4764_Y != null && this.G_564_y != null) {
            Optional<WalkTarget> optional = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.P_4830_p);
            PathNavigation pathnavigator = entityIn.e_4240_b();
            return !pathnavigator.M_588_G() && optional.isPresent() && !this.n_1700_B(entityIn, optional.get());
        }
        return false;
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        if (entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p) && !this.n_1700_B(entityIn, entityIn.y_1945_D().R_4764_Y(MemoryModuleType.P_4830_p).get()) && entityIn.e_4240_b().multiplayerClientSuggestionProvider()) {
            this.n_1700_B = worldIn.e_4240_b().nextInt(40);
        }
        entityIn.e_4240_b().h_1847_R();
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.Y_601_j);
        this.R_4764_Y = null;
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.Y_601_j, this.R_4764_Y);
        entityIn.e_4240_b().n_1700_B(this.R_4764_Y, (double)this.P_1922_E);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, Z_530_i owner, long gameTime) {
        WalkTarget walktarget;
        b_1722_e path = owner.e_4240_b().s_956_w();
        E_4668_a<?> brain = owner.y_1945_D();
        if (this.R_4764_Y != path) {
            this.R_4764_Y = path;
            brain.n_1700_B(MemoryModuleType.Y_601_j, path);
        }
        if (path != null && this.G_564_y != null && (walktarget = brain.R_4764_Y(MemoryModuleType.P_4830_p).get()).n_1700_B().J_1907_R().distanceSq(this.G_564_y) > 4.0 && this.n_1700_B(owner, walktarget, worldIn.X_933_l())) {
            this.G_564_y = walktarget.n_1700_B().J_1907_R();
            this.R_4764_Y(worldIn, owner, gameTime);
        }
    }

    private boolean n_1700_B(Z_530_i p_220487_1_, WalkTarget p_220487_2_, long p_220487_3_) {
        c_1514_x blockpos = p_220487_2_.n_1700_B().J_1907_R();
        this.R_4764_Y = p_220487_1_.e_4240_b().n_1700_B(blockpos, 0);
        this.P_1922_E = p_220487_2_.J_1907_R();
        E_4668_a<Long> brain = p_220487_1_.y_1945_D();
        if (this.n_1700_B(p_220487_1_, p_220487_2_)) {
            brain.J_1907_R(MemoryModuleType.Y_1740_V);
        } else {
            boolean flag;
            boolean bl = flag = this.R_4764_Y != null && this.R_4764_Y.s_956_w();
            if (flag) {
                brain.J_1907_R(MemoryModuleType.Y_1740_V);
            } else if (!brain.n_1700_B(MemoryModuleType.Y_1740_V)) {
                brain.n_1700_B(MemoryModuleType.Y_1740_V, Long.valueOf(p_220487_3_));
            }
            if (this.R_4764_Y != null) {
                return true;
            }
            e_2866_D vector3d = W_3371_U.J_1907_R((PathfinderMob)p_220487_1_, 10, 7, e_2866_D.R_4764_Y(blockpos));
            if (vector3d != null) {
                this.R_4764_Y = p_220487_1_.e_4240_b().n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, 0);
                return this.R_4764_Y != null;
            }
        }
        return false;
    }

    private boolean n_1700_B(Z_530_i p_220486_1_, WalkTarget p_220486_2_) {
        return p_220486_2_.n_1700_B().J_1907_R().manhattanDistance(p_220486_1_.b_2312_j()) <= p_220486_2_.R_4764_Y();
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.R_4764_Y(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


