/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.M_4472_P;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Z_4149_q;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.SoulFireBlock;

public abstract class BaseFireBlock
extends T_2915_h {
    private final float h_1847_R;
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    public BaseFireBlock(q_4293_E.P_1922_E properties, float fireDamage) {
        super(properties);
        this.h_1847_R = fireDamage;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return BaseFireBlock.n_1700_B(context.getWorld(), context.getPos());
    }

    public static K_4074_S n_1700_B(BlockGetter reader, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = reader.getBlockState(blockpos);
        return SoulFireBlock.n_1700_B(blockstate.J_1907_R()) ? a_3742_W.t_1446_I.multiplayerClientSuggestionProvider() : ((M_4472_P)a_3742_W.x_612_B).J_1907_R(reader, pos);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        block12: {
            block11: {
                c_1514_x blockpos;
                K_4074_S blockstate;
                if (rand.nextInt(24) == 0) {
                    worldIn.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.E_4256_w, D_38_f.P_1922_E, 1.0f + rand.nextFloat(), rand.nextFloat() * 0.7f + 0.3f, false);
                }
                if (this.v_4262_N(blockstate = worldIn.getBlockState(blockpos = pos.down())) || blockstate.G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R)) break block11;
                if (this.v_4262_N(worldIn.getBlockState(pos.west()))) {
                    for (int j = 0; j < 2; ++j) {
                        double d3 = (double)pos.getX() + rand.nextDouble() * (double)0.1f;
                        double d8 = (double)pos.getY() + rand.nextDouble();
                        double d13 = (double)pos.getZ() + rand.nextDouble();
                        worldIn.n_1700_B(ParticleTypes.d_2461_k, d3, d8, d13, 0.0, 0.0, 0.0);
                    }
                }
                if (this.v_4262_N(worldIn.getBlockState(pos.east()))) {
                    for (int k = 0; k < 2; ++k) {
                        double d4 = (double)(pos.getX() + 1) - rand.nextDouble() * (double)0.1f;
                        double d9 = (double)pos.getY() + rand.nextDouble();
                        double d14 = (double)pos.getZ() + rand.nextDouble();
                        worldIn.n_1700_B(ParticleTypes.d_2461_k, d4, d9, d14, 0.0, 0.0, 0.0);
                    }
                }
                if (this.v_4262_N(worldIn.getBlockState(pos.north()))) {
                    for (int l = 0; l < 2; ++l) {
                        double d5 = (double)pos.getX() + rand.nextDouble();
                        double d10 = (double)pos.getY() + rand.nextDouble();
                        double d15 = (double)pos.getZ() + rand.nextDouble() * (double)0.1f;
                        worldIn.n_1700_B(ParticleTypes.d_2461_k, d5, d10, d15, 0.0, 0.0, 0.0);
                    }
                }
                if (this.v_4262_N(worldIn.getBlockState(pos.south()))) {
                    for (int i1 = 0; i1 < 2; ++i1) {
                        double d6 = (double)pos.getX() + rand.nextDouble();
                        double d11 = (double)pos.getY() + rand.nextDouble();
                        double d16 = (double)(pos.getZ() + 1) - rand.nextDouble() * (double)0.1f;
                        worldIn.n_1700_B(ParticleTypes.d_2461_k, d6, d11, d16, 0.0, 0.0, 0.0);
                    }
                }
                if (!this.v_4262_N(worldIn.getBlockState(pos.up()))) break block12;
                for (int j1 = 0; j1 < 2; ++j1) {
                    double d7 = (double)pos.getX() + rand.nextDouble();
                    double d12 = (double)(pos.getY() + 1) - rand.nextDouble() * (double)0.1f;
                    double d17 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.n_1700_B(ParticleTypes.d_2461_k, d7, d12, d17, 0.0, 0.0, 0.0);
                }
                break block12;
            }
            for (int i = 0; i < 3; ++i) {
                double d0 = (double)pos.getX() + rand.nextDouble();
                double d1 = (double)pos.getY() + rand.nextDouble() * 0.5 + 0.5;
                double d2 = (double)pos.getZ() + rand.nextDouble();
                worldIn.n_1700_B(ParticleTypes.d_2461_k, d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    protected abstract boolean v_4262_N(K_4074_S var1);

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!entityIn.r_3651_U()) {
            entityIn.u_1723_Y(entityIn.w_612_n() + 1);
            if (entityIn.w_612_n() == 0) {
                entityIn.P_1922_E(8);
            }
            entityIn.n_1700_B(P_11_z.n_1700_B, this.h_1847_R);
        }
        super.n_1700_B(state, worldIn, pos, entityIn);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            Optional<Z_4149_q> optional;
            if (BaseFireBlock.n_1700_B(worldIn) && (optional = Z_4149_q.n_1700_B(worldIn, pos, b_257_Y.n_1700_B.n_1700_B)).isPresent()) {
                optional.get().J_1907_R();
                return;
            }
            if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
                worldIn.n_1700_B(pos, false);
            }
        }
    }

    private static boolean n_1700_B(b_4507_u world) {
        return world.g_2268_R() == b_4507_u.u_1723_Y || world.g_2268_R() == b_4507_u.v_4262_N;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!worldIn.v_4276_D()) {
            worldIn.n_1700_B((a_3913_L)null, 1009, pos, 0);
        }
    }

    public static boolean n_1700_B(b_4507_u world, c_1514_x pos, b_257_Y direction) {
        K_4074_S blockstate = world.getBlockState(pos);
        if (!blockstate.v_4262_N()) {
            return false;
        }
        return BaseFireBlock.n_1700_B(world, pos).n_1700_B((T_1316_M)world, pos) || BaseFireBlock.J_1907_R(world, pos, direction);
    }

    private static boolean J_1907_R(b_4507_u world, c_1514_x pos, b_257_Y directionIn) {
        if (!BaseFireBlock.n_1700_B(world)) {
            return false;
        }
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable();
        boolean flag = false;
        for (b_257_Y direction : b_257_Y.values()) {
            if (!world.getBlockState(blockpos$mutable.n_1700_B(pos).n_1700_B(direction)).n_1700_B(a_3742_W.ClientBootstrap)) continue;
            flag = true;
            break;
        }
        if (!flag) {
            return false;
        }
        b_257_Y.n_1700_B direction$axis = directionIn.h_1847_R().G_564_y() ? directionIn.w_1484_f().h_1847_R() : b_257_Y.R_4764_Y.n_1700_B.J_1907_R(world.w_1457_N);
        return Z_4149_q.n_1700_B(world, pos, direction$axis).isPresent();
    }
}



