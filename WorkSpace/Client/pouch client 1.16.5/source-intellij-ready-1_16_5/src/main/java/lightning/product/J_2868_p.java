/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.F_1241_B;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.G_652_w;
import lightning.product.I_4817_s;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.P_11_z;
import lightning.product.DoubleBlockCombiner;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_563_h;
import lightning.product.e_933_M;
import lightning.product.h_2829_o;
import lightning.product.i_2154_H;
import lightning.product.BedBlockEntity;
import lightning.product.k_2789_z;
import lightning.product.m_3054_I;
import lightning.product.o_3283_D;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;

public class J_2868_p
extends HorizontalDirectionalBlock
implements k_2789_z {
    public static final e_563_h<h_2829_o> P_4830_p = BlockStateProperties.M_1641_O;
    public static final U_1266_O h_1847_R = BlockStateProperties.Y_601_j;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 3.0, 0.0, 16.0, 9.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 3.0, 3.0, 3.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 13.0, 3.0, 3.0, 16.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(13.0, 0.0, 0.0, 16.0, 3.0, 3.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(13.0, 0.0, 13.0, 16.0, 3.0, 16.0);
    protected static final s_1395_c Y_601_j = x_268_Y.n_1700_B(Q_4569_t, M_182_A, multiplayerClientSuggestionProvider);
    protected static final s_1395_c Y_259_p = x_268_Y.n_1700_B(Q_4569_t, t_1786_h, w_1457_N);
    protected static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(Q_4569_t, M_182_A, t_1786_h);
    protected static final s_1395_c C_2741_M = x_268_Y.n_1700_B(Q_4569_t, multiplayerClientSuggestionProvider, w_1457_N);
    private final e_933_M k_2293_S;

    public J_2868_p(e_933_M colorIn, q_4293_E.P_1922_E properties) {
        super(properties);
        this.k_2293_S = colorIn;
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, h_2829_o.J_1907_R)).n_1700_B(h_1847_R, false));
    }

    @Nullable
    public static b_257_Y n_1700_B(BlockGetter reader, c_1514_x pos) {
        K_4074_S blockstate = reader.getBlockState(pos);
        return blockstate.J_1907_R() instanceof J_2868_p ? blockstate.R_4764_Y(w_612_n) : null;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.J_1907_R;
        }
        if (state.R_4764_Y(P_4830_p) != h_2829_o.n_1700_B && !(state = worldIn.getBlockState(pos = pos.offset(state.R_4764_Y(w_612_n)))).n_1700_B(this)) {
            return m_3054_I.J_1907_R;
        }
        if (!J_2868_p.n_1700_B(worldIn)) {
            worldIn.n_1700_B(pos, false);
            c_1514_x blockpos = pos.offset(state.R_4764_Y(w_612_n).u_1723_Y());
            if (worldIn.getBlockState(blockpos).n_1700_B(this)) {
                worldIn.n_1700_B(blockpos, false);
            }
            worldIn.n_1700_B(null, P_11_z.n_1700_B(), null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5.0f, true, F_1241_B.n_1700_B.R_4764_Y);
            return m_3054_I.n_1700_B;
        }
        if (state.R_4764_Y(h_1847_R).booleanValue()) {
            if (!this.n_1700_B(worldIn, pos)) {
                player.n_1700_B((x_282_a)new F_2904_S("block.minecraft.bed.occupied"), true);
            }
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(pos).ifLeft(result -> {
            if (result != null) {
                player.n_1700_B(result.n_1700_B(), true);
            }
        });
        return m_3054_I.n_1700_B;
    }

    public static boolean n_1700_B(b_4507_u world) {
        return world.G_624_v().w_1484_f();
    }

    private boolean n_1700_B(b_4507_u world, c_1514_x pos) {
        List<L_2225_p> list = world.n_1700_B(L_2225_p.class, new I_4817_s(pos), r_4811_B::z_2372_L);
        if (list.isEmpty()) {
            return false;
        }
        list.get(0).t_2932_z();
        return true;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        super.n_1700_B(worldIn, pos, entityIn, fallDistance * 0.5f);
    }

    @Override
    public void n_1700_B(BlockGetter worldIn, N_4263_v entityIn) {
        if (entityIn.UploadTokenCache()) {
            super.n_1700_B(worldIn, entityIn);
        } else {
            this.n_1700_B(entityIn);
        }
    }

    private void n_1700_B(N_4263_v entity) {
        e_2866_D vector3d = entity.I_4348_c();
        if (vector3d.R_4764_Y < 0.0) {
            double d0 = entity instanceof r_4811_B ? 1.0 : 0.8;
            entity.h_1847_R(vector3d.J_1907_R, -vector3d.R_4764_Y * (double)0.66f * d0, vector3d.G_564_y);
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == J_2868_p.n_1700_B(stateIn.R_4764_Y(P_4830_p), stateIn.R_4764_Y(w_612_n))) {
            return facingState.n_1700_B(this) && facingState.R_4764_Y(P_4830_p) != stateIn.R_4764_Y(P_4830_p) ? (K_4074_S)stateIn.n_1700_B(h_1847_R, facingState.R_4764_Y(h_1847_R)) : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    private static b_257_Y n_1700_B(h_2829_o part, b_257_Y direction) {
        return part == h_2829_o.J_1907_R ? direction : direction.u_1723_Y();
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        c_1514_x blockpos;
        K_4074_S blockstate;
        h_2829_o bedpart;
        if (!worldIn.Y_259_p && player.G_624_v() && (bedpart = state.R_4764_Y(P_4830_p)) == h_2829_o.J_1907_R && (blockstate = worldIn.getBlockState(blockpos = pos.offset(J_2868_p.n_1700_B(bedpart, state.R_4764_Y(w_612_n))))).J_1907_R() == this && blockstate.R_4764_Y(P_4830_p) == h_2829_o.n_1700_B) {
            worldIn.n_1700_B(blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 35);
            worldIn.n_1700_B(player, 2001, blockpos, T_2915_h.s_956_w(blockstate));
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getPlacementHorizontalFacing();
        c_1514_x blockpos = context.getPos();
        c_1514_x blockpos1 = blockpos.offset(direction);
        return context.getWorld().getBlockState(blockpos1).n_1700_B(context) ? (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(w_612_n, direction) : null;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        b_257_Y direction = J_2868_p.w_1484_f(state).u_1723_Y();
        switch (direction) {
            case R_4764_Y: {
                return Y_601_j;
            }
            case G_564_y: {
                return Y_259_p;
            }
            case P_1922_E: {
                return Q_2552_b;
            }
        }
        return C_2741_M;
    }

    public static b_257_Y w_1484_f(K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        return state.R_4764_Y(P_4830_p) == h_2829_o.n_1700_B ? direction.u_1723_Y() : direction;
    }

    public static DoubleBlockCombiner.R_4764_Y t_148_a(K_4074_S state) {
        h_2829_o bedpart = state.R_4764_Y(P_4830_p);
        return bedpart == h_2829_o.n_1700_B ? DoubleBlockCombiner.R_4764_Y.J_1907_R : DoubleBlockCombiner.R_4764_Y.R_4764_Y;
    }

    private static boolean J_1907_R(BlockGetter blockReader, c_1514_x pos) {
        return blockReader.getBlockState(pos.down()).J_1907_R() instanceof J_2868_p;
    }

    public static Optional<e_2866_D> n_1700_B(t_5_h<?> type, o_3283_D collisionReader, c_1514_x pos, float orientation) {
        b_257_Y direction2;
        b_257_Y direction = collisionReader.getBlockState(pos).R_4764_Y(w_612_n);
        b_257_Y direction1 = direction.v_4262_N();
        b_257_Y b_257_Y2 = direction2 = direction1.n_1700_B(orientation) ? direction1.u_1723_Y() : direction1;
        if (J_2868_p.J_1907_R(collisionReader, pos)) {
            return J_2868_p.n_1700_B(type, collisionReader, pos, direction, direction2);
        }
        int[][] aint = J_2868_p.n_1700_B(direction, direction2);
        Optional<e_2866_D> optional = J_2868_p.n_1700_B(type, collisionReader, pos, aint, true);
        return optional.isPresent() ? optional : J_2868_p.n_1700_B(type, collisionReader, pos, aint, false);
    }

    private static Optional<e_2866_D> n_1700_B(t_5_h<?> type, o_3283_D collisionReader, c_1514_x pos, b_257_Y direction1, b_257_Y direction2) {
        int[][] aint = J_2868_p.J_1907_R(direction1, direction2);
        Optional<e_2866_D> optional = J_2868_p.n_1700_B(type, collisionReader, pos, aint, true);
        if (optional.isPresent()) {
            return optional;
        }
        c_1514_x blockpos = pos.down();
        Optional<e_2866_D> optional1 = J_2868_p.n_1700_B(type, collisionReader, blockpos, aint, true);
        if (optional1.isPresent()) {
            return optional1;
        }
        int[][] aint1 = J_2868_p.n_1700_B(direction1);
        Optional<e_2866_D> optional2 = J_2868_p.n_1700_B(type, collisionReader, pos, aint1, true);
        if (optional2.isPresent()) {
            return optional2;
        }
        Optional<e_2866_D> optional3 = J_2868_p.n_1700_B(type, collisionReader, pos, aint, false);
        if (optional3.isPresent()) {
            return optional3;
        }
        Optional<e_2866_D> optional4 = J_2868_p.n_1700_B(type, collisionReader, blockpos, aint, false);
        return optional4.isPresent() ? optional4 : J_2868_p.n_1700_B(type, collisionReader, pos, aint1, false);
    }

    private static Optional<e_2866_D> n_1700_B(t_5_h<?> type, o_3283_D collisionReader, c_1514_x pos, int[][] p_242654_3_, boolean p_242654_4_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int[] aint : p_242654_3_) {
            blockpos$mutable.n_1700_B(pos.getX() + aint[0], pos.getY(), pos.getZ() + aint[1]);
            e_2866_D vector3d = G_652_w.n_1700_B(type, collisionReader, blockpos$mutable, p_242654_4_);
            if (vector3d == null) continue;
            return Optional.of(vector3d);
        }
        return Optional.empty();
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.J_1907_R;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p, h_1847_R);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new BedBlockEntity(this.k_2293_S);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        super.n_1700_B(worldIn, pos, state, placer, stack);
        if (!worldIn.Y_259_p) {
            c_1514_x blockpos = pos.offset(state.R_4764_Y(w_612_n));
            worldIn.n_1700_B(blockpos, (K_4074_S)state.n_1700_B(P_4830_p, h_2829_o.n_1700_B), 3);
            worldIn.n_1700_B(pos, a_3742_W.n_1700_B);
            state.n_1700_B((LevelAccessor)worldIn, pos, 3);
        }
    }

    public e_933_M J_1907_R() {
        return this.k_2293_S;
    }

    @Override
    public long n_1700_B(K_4074_S state, c_1514_x pos) {
        c_1514_x blockpos = pos.offset(state.R_4764_Y(w_612_n), state.R_4764_Y(P_4830_p) == h_2829_o.n_1700_B ? 0 : 1);
        return u_530_F.R_4764_Y(blockpos.getX(), pos.getY(), blockpos.getZ());
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    private static int[][] n_1700_B(b_257_Y direction1, b_257_Y direction2) {
        return (int[][])ArrayUtils.addAll((Object[])J_2868_p.J_1907_R(direction1, direction2), (Object[])J_2868_p.n_1700_B(direction1));
    }

    private static int[][] J_1907_R(b_257_Y direction1, b_257_Y direction2) {
        return new int[][]{{direction2.t_148_a(), direction2.u_2550_I()}, {direction2.t_148_a() - direction1.t_148_a(), direction2.u_2550_I() - direction1.u_2550_I()}, {direction2.t_148_a() - direction1.t_148_a() * 2, direction2.u_2550_I() - direction1.u_2550_I() * 2}, {-direction1.t_148_a() * 2, -direction1.u_2550_I() * 2}, {-direction2.t_148_a() - direction1.t_148_a() * 2, -direction2.u_2550_I() - direction1.u_2550_I() * 2}, {-direction2.t_148_a() - direction1.t_148_a(), -direction2.u_2550_I() - direction1.u_2550_I()}, {-direction2.t_148_a(), -direction2.u_2550_I()}, {-direction2.t_148_a() + direction1.t_148_a(), -direction2.u_2550_I() + direction1.u_2550_I()}, {direction1.t_148_a(), direction1.u_2550_I()}, {direction2.t_148_a() + direction1.t_148_a(), direction2.u_2550_I() + direction1.u_2550_I()}};
    }

    private static int[][] n_1700_B(b_257_Y direction) {
        return new int[][]{{0, 0}, {-direction.t_148_a(), -direction.u_2550_I()}};
    }
}


