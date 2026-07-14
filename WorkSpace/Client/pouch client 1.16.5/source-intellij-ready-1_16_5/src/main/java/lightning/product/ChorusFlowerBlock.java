/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.ChorusPlantBlock;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.EntityTypeTags;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public class ChorusFlowerBlock
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.A_1038_p;
    private final ChorusPlantBlock h_1847_R;

    protected ChorusFlowerBlock(ChorusPlantBlock plantBlock, q_4293_E.P_1922_E builder) {
        super(builder);
        this.h_1847_R = plantBlock;
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) < 5;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i;
        c_1514_x blockpos = pos.up();
        if (worldIn.u_1723_Y(blockpos) && blockpos.getY() < 256 && (i = state.R_4764_Y(P_4830_p).intValue()) < 5) {
            boolean flag = false;
            boolean flag1 = false;
            K_4074_S blockstate = worldIn.getBlockState(pos.down());
            T_2915_h block = blockstate.J_1907_R();
            if (block == a_3742_W.e_1231_S) {
                flag = true;
            } else if (block == this.h_1847_R) {
                int j = 1;
                for (int k = 0; k < 4; ++k) {
                    T_2915_h block1 = worldIn.getBlockState(pos.down(j + 1)).J_1907_R();
                    if (block1 != this.h_1847_R) {
                        if (block1 != a_3742_W.e_1231_S) break;
                        flag1 = true;
                        break;
                    }
                    ++j;
                }
                if (j < 2 || j <= random.nextInt(flag1 ? 5 : 4)) {
                    flag = true;
                }
            } else if (blockstate.v_4262_N()) {
                flag = true;
            }
            if (flag && ChorusFlowerBlock.J_1907_R(worldIn, blockpos, (b_257_Y)null) && worldIn.u_1723_Y(pos.up(2))) {
                worldIn.n_1700_B(pos, this.h_1847_R.n_1700_B((BlockGetter)worldIn, pos), 2);
                this.n_1700_B((b_4507_u)worldIn, blockpos, i);
            } else if (i < 4) {
                int l = random.nextInt(4);
                if (flag1) {
                    ++l;
                }
                boolean flag2 = false;
                for (int i1 = 0; i1 < l; ++i1) {
                    b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(random);
                    c_1514_x blockpos1 = pos.offset(direction);
                    if (!worldIn.u_1723_Y(blockpos1) || !worldIn.u_1723_Y(blockpos1.down()) || !ChorusFlowerBlock.J_1907_R(worldIn, blockpos1, direction.u_1723_Y())) continue;
                    this.n_1700_B((b_4507_u)worldIn, blockpos1, i + 1);
                    flag2 = true;
                }
                if (flag2) {
                    worldIn.n_1700_B(pos, this.h_1847_R.n_1700_B((BlockGetter)worldIn, pos), 2);
                } else {
                    this.n_1700_B(worldIn, pos);
                }
            } else {
                this.n_1700_B(worldIn, pos);
            }
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, int age) {
        worldIn.n_1700_B(pos, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, age), 2);
        worldIn.R_4764_Y(1033, pos, 0);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        worldIn.n_1700_B(pos, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, 5), 2);
        worldIn.R_4764_Y(1034, pos, 0);
    }

    private static boolean J_1907_R(T_1316_M worldIn, c_1514_x pos, @Nullable b_257_Y excludingSide) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            if (direction == excludingSide || worldIn.u_1723_Y(pos.offset(direction))) continue;
            return false;
        }
        return true;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing != b_257_Y.J_1907_R && !stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        if (blockstate.J_1907_R() != this.h_1847_R && !blockstate.n_1700_B(a_3742_W.e_1231_S)) {
            if (!blockstate.v_4262_N()) {
                return false;
            }
            boolean flag = false;
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                K_4074_S blockstate1 = worldIn.getBlockState(pos.offset(direction));
                if (blockstate1.n_1700_B(this.h_1847_R)) {
                    if (flag) {
                        return false;
                    }
                    flag = true;
                    continue;
                }
                if (blockstate1.v_4262_N()) continue;
                return false;
            }
            return flag;
        }
        return true;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    public static void n_1700_B(LevelAccessor worldIn, c_1514_x pos, Random rand, int maxHorizontalDistance) {
        worldIn.n_1700_B(pos, ((ChorusPlantBlock)a_3742_W.ChestStealer).n_1700_B((BlockGetter)worldIn, pos), 2);
        ChorusFlowerBlock.n_1700_B(worldIn, pos, rand, pos, maxHorizontalDistance, 0);
    }

    private static void n_1700_B(LevelAccessor worldIn, c_1514_x branchPos, Random rand, c_1514_x originalBranchPos, int maxHorizontalDistance, int iterations) {
        ChorusPlantBlock chorusplantblock = (ChorusPlantBlock)a_3742_W.ChestStealer;
        int i = rand.nextInt(4) + 1;
        if (iterations == 0) {
            ++i;
        }
        for (int j = 0; j < i; ++j) {
            c_1514_x blockpos = branchPos.up(j + 1);
            if (!ChorusFlowerBlock.J_1907_R(worldIn, blockpos, (b_257_Y)null)) {
                return;
            }
            worldIn.n_1700_B(blockpos, chorusplantblock.n_1700_B((BlockGetter)worldIn, blockpos), 2);
            worldIn.n_1700_B(blockpos.down(), chorusplantblock.n_1700_B((BlockGetter)worldIn, blockpos.down()), 2);
        }
        boolean flag = false;
        if (iterations < 4) {
            int l = rand.nextInt(4);
            if (iterations == 0) {
                ++l;
            }
            for (int k = 0; k < l; ++k) {
                b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(rand);
                c_1514_x blockpos1 = branchPos.up(i).offset(direction);
                if (Math.abs(blockpos1.getX() - originalBranchPos.getX()) >= maxHorizontalDistance || Math.abs(blockpos1.getZ() - originalBranchPos.getZ()) >= maxHorizontalDistance || !worldIn.u_1723_Y(blockpos1) || !worldIn.u_1723_Y(blockpos1.down()) || !ChorusFlowerBlock.J_1907_R(worldIn, blockpos1, direction.u_1723_Y())) continue;
                flag = true;
                worldIn.n_1700_B(blockpos1, chorusplantblock.n_1700_B((BlockGetter)worldIn, blockpos1), 2);
                worldIn.n_1700_B(blockpos1.offset(direction.u_1723_Y()), chorusplantblock.n_1700_B((BlockGetter)worldIn, blockpos1.offset(direction.u_1723_Y())), 2);
                ChorusFlowerBlock.n_1700_B(worldIn, blockpos1, rand, originalBranchPos, maxHorizontalDistance, iterations + 1);
            }
        }
        if (!flag) {
            worldIn.n_1700_B(branchPos.up(i), (K_4074_S)a_3742_W.ChorusExploit.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, 5), 2);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
        if (projectile.f_4016_n().n_1700_B(EntityTypeTags.u_1723_Y)) {
            c_1514_x blockpos = hit.n_1700_B();
            worldIn.n_1700_B(blockpos, true, projectile);
        }
    }
}



