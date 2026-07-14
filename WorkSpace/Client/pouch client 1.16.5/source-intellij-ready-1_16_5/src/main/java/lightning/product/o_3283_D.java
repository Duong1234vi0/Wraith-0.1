/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_603_v;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.f_2155_P;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;

public interface o_3283_D
extends BlockGetter {
    public T_603_v H_2857_Y();

    @Nullable
    public BlockGetter G_564_y(int var1, int var2);

    default public boolean n_1700_B(@Nullable N_4263_v entityIn, s_1395_c shape) {
        return true;
    }

    default public boolean n_1700_B(K_4074_S state, c_1514_x pos, CollisionContext context) {
        s_1395_c voxelshape = state.R_4764_Y((BlockGetter)this, pos, context);
        return voxelshape.J_1907_R() || this.n_1700_B((N_4263_v)null, voxelshape.n_1700_B(pos.getX(), (double)pos.getY(), (double)pos.getZ()));
    }

    default public boolean P_1922_E(N_4263_v entity) {
        return this.n_1700_B(entity, x_268_Y.n_1700_B(entity.i_601_W()));
    }

    default public boolean J_1907_R(I_4817_s aabb) {
        return this.a_(null, aabb, entity -> true);
    }

    default public boolean u_1723_Y(N_4263_v entity) {
        return this.a_(entity, entity.i_601_W(), entity2 -> true);
    }

    default public boolean a_(N_4263_v entity, I_4817_s aabb) {
        return this.a_(entity, aabb, entity2 -> true);
    }

    default public boolean a_(@Nullable N_4263_v entity, I_4817_s aabb, Predicate<N_4263_v> entityPredicate) {
        return this.R_4764_Y(entity, aabb, entityPredicate).allMatch(s_1395_c::J_1907_R);
    }

    public Stream<s_1395_c> n_1700_B(@Nullable N_4263_v var1, I_4817_s var2, Predicate<N_4263_v> var3);

    default public Stream<s_1395_c> R_4764_Y(@Nullable N_4263_v entity, I_4817_s aabb, Predicate<N_4263_v> entityPredicate) {
        return Stream.concat(this.J_1907_R(entity, aabb), this.n_1700_B(entity, aabb, entityPredicate));
    }

    default public Stream<s_1395_c> J_1907_R(@Nullable N_4263_v entity, I_4817_s aabb) {
        return StreamSupport.stream(new f_2155_P(this, entity, aabb), false);
    }

    default public boolean n_1700_B(@Nullable N_4263_v p_242405_1_, I_4817_s p_242405_2_, BiPredicate<K_4074_S, c_1514_x> p_242405_3_) {
        return this.J_1907_R(p_242405_1_, p_242405_2_, p_242405_3_).allMatch(s_1395_c::J_1907_R);
    }

    default public Stream<s_1395_c> J_1907_R(@Nullable N_4263_v entity, I_4817_s aabb, BiPredicate<K_4074_S, c_1514_x> statePosPredicate) {
        return StreamSupport.stream(new f_2155_P(this, entity, aabb, statePosPredicate), false);
    }
}


