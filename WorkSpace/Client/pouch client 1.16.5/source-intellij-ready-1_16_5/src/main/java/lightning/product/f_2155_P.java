/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_603_v;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Cursor3D;
import lightning.product.o_3283_D;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class f_2155_P
extends Spliterators.AbstractSpliterator<s_1395_c> {
    @Nullable
    private final N_4263_v n_1700_B;
    private final I_4817_s J_1907_R;
    private final CollisionContext R_4764_Y;
    private final Cursor3D G_564_y;
    private final c_1514_x.n_1700_B P_1922_E;
    private final s_1395_c u_1723_Y;
    private final o_3283_D v_4262_N;
    private boolean w_1484_f;
    private final BiPredicate<K_4074_S, c_1514_x> t_148_a;

    public f_2155_P(o_3283_D reader, @Nullable N_4263_v entity, I_4817_s aabb) {
        this(reader, entity, aabb, (p_241459_0_, p_241459_1_) -> true);
    }

    public f_2155_P(o_3283_D reader, @Nullable N_4263_v entity, I_4817_s aabb, BiPredicate<K_4074_S, c_1514_x> statePositionPredicate) {
        super(Long.MAX_VALUE, 1280);
        this.R_4764_Y = entity == null ? CollisionContext.J_1907_R() : CollisionContext.n_1700_B(entity);
        this.P_1922_E = new c_1514_x.n_1700_B();
        this.u_1723_Y = x_268_Y.n_1700_B(aabb);
        this.v_4262_N = reader;
        this.w_1484_f = entity != null;
        this.n_1700_B = entity;
        this.J_1907_R = aabb;
        this.t_148_a = statePositionPredicate;
        int i = u_530_F.R_4764_Y(aabb.minX - 1.0E-7) - 1;
        int j = u_530_F.R_4764_Y(aabb.maxX + 1.0E-7) + 1;
        int k = u_530_F.R_4764_Y(aabb.minY - 1.0E-7) - 1;
        int l = u_530_F.R_4764_Y(aabb.maxY + 1.0E-7) + 1;
        int i1 = u_530_F.R_4764_Y(aabb.minZ - 1.0E-7) - 1;
        int j1 = u_530_F.R_4764_Y(aabb.maxZ + 1.0E-7) + 1;
        this.G_564_y = new Cursor3D(i, k, i1, j, l, j1);
    }

    @Override
    public boolean tryAdvance(Consumer<? super s_1395_c> p_tryAdvance_1_) {
        return this.w_1484_f && this.J_1907_R(p_tryAdvance_1_) || this.n_1700_B(p_tryAdvance_1_);
    }

    boolean n_1700_B(Consumer<? super s_1395_c> p_234878_1_) {
        while (this.G_564_y.n_1700_B()) {
            BlockGetter iblockreader;
            int i = this.G_564_y.J_1907_R();
            int j = this.G_564_y.R_4764_Y();
            int k = this.G_564_y.G_564_y();
            int l = this.G_564_y.P_1922_E();
            if (l == 3 || (iblockreader = this.n_1700_B(i, k)) == null) continue;
            this.P_1922_E.n_1700_B(i, j, k);
            K_4074_S blockstate = iblockreader.getBlockState(this.P_1922_E);
            if (!this.t_148_a.test(blockstate, this.P_1922_E) || l == 1 && !blockstate.G_564_y() || l == 2 && !blockstate.n_1700_B(a_3742_W.O_2151_c)) continue;
            s_1395_c voxelshape = blockstate.R_4764_Y((BlockGetter)this.v_4262_N, (c_1514_x)this.P_1922_E, this.R_4764_Y);
            if (voxelshape == x_268_Y.J_1907_R()) {
                if (!this.J_1907_R.intersects(i, j, k, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0)) continue;
                p_234878_1_.accept(voxelshape.n_1700_B(i, (double)j, (double)k));
                return true;
            }
            s_1395_c voxelshape1 = voxelshape.n_1700_B(i, (double)j, (double)k);
            if (!x_268_Y.R_4764_Y(voxelshape1, this.u_1723_Y, BooleanOp.t_148_a)) continue;
            p_234878_1_.accept(voxelshape1);
            return true;
        }
        return false;
    }

    @Nullable
    private BlockGetter n_1700_B(int p_234876_1_, int p_234876_2_) {
        int i = p_234876_1_ >> 4;
        int j = p_234876_2_ >> 4;
        return this.v_4262_N.G_564_y(i, j);
    }

    boolean J_1907_R(Consumer<? super s_1395_c> p_234879_1_) {
        s_1395_c voxelshape;
        Objects.requireNonNull(this.n_1700_B);
        this.w_1484_f = false;
        T_603_v worldborder = this.v_4262_N.H_2857_Y();
        I_4817_s axisalignedbb = this.n_1700_B.i_601_W();
        if (!f_2155_P.n_1700_B(worldborder, axisalignedbb) && !f_2155_P.J_1907_R(voxelshape = worldborder.R_4764_Y(), axisalignedbb) && f_2155_P.n_1700_B(voxelshape, axisalignedbb)) {
            p_234879_1_.accept(voxelshape);
            return true;
        }
        return false;
    }

    private static boolean n_1700_B(s_1395_c p_241460_0_, I_4817_s p_241460_1_) {
        return x_268_Y.R_4764_Y(p_241460_0_, x_268_Y.n_1700_B(p_241460_1_.grow(1.0E-7)), BooleanOp.t_148_a);
    }

    private static boolean J_1907_R(s_1395_c p_241461_0_, I_4817_s p_241461_1_) {
        return x_268_Y.R_4764_Y(p_241461_0_, x_268_Y.n_1700_B(p_241461_1_.shrink(1.0E-7)), BooleanOp.t_148_a);
    }

    public static boolean n_1700_B(T_603_v p_234877_0_, I_4817_s p_234877_1_) {
        double d0 = u_530_F.R_4764_Y(p_234877_0_.P_1922_E());
        double d1 = u_530_F.R_4764_Y(p_234877_0_.u_1723_Y());
        double d2 = u_530_F.P_1922_E(p_234877_0_.v_4262_N());
        double d3 = u_530_F.P_1922_E(p_234877_0_.w_1484_f());
        return p_234877_1_.minX > d0 && p_234877_1_.minX < d2 && p_234877_1_.minZ > d1 && p_234877_1_.minZ < d3 && p_234877_1_.maxX > d0 && p_234877_1_.maxX < d2 && p_234877_1_.maxZ > d1 && p_234877_1_.maxZ < d3;
    }
}


