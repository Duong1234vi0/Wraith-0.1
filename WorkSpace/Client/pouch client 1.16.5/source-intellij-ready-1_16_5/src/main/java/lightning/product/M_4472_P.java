/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import lightning.product.A_2352_Z;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.PipeBlock;
import lightning.product.BaseFireBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.V_883_W;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.j_3341_s;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.x_268_Y;

public class M_4472_P
extends BaseFireBlock {
    public static final g_88_D h_1847_R = BlockStateProperties.Ping;
    public static final U_1266_O Q_4569_t = PipeBlock.P_4830_p;
    public static final U_1266_O M_182_A = PipeBlock.h_1847_R;
    public static final U_1266_O t_1786_h = PipeBlock.Q_4569_t;
    public static final U_1266_O multiplayerClientSuggestionProvider = PipeBlock.M_182_A;
    public static final U_1266_O w_1457_N = PipeBlock.t_1786_h;
    private static final Map<b_257_Y, U_1266_O> Y_601_j = PipeBlock.w_1457_N.entrySet().stream().filter(facingProperty -> facingProperty.getKey() != b_257_Y.n_1700_B).collect(j_3341_s.n_1700_B());
    private static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    private static final s_1395_c C_2741_M = T_2915_h.n_1700_B(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final s_1395_c k_2293_S = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    private static final s_1395_c q_2307_F = T_2915_h.n_1700_B(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    private final Map<K_4074_S, s_1395_c> Z_875_P;
    private final Object2IntMap<T_2915_h> c_3005_b = new Object2IntOpenHashMap();
    private final Object2IntMap<T_2915_h> H_2857_Y = new Object2IntOpenHashMap();

    public M_4472_P(q_4293_E.P_1922_E builder) {
        super(builder, 1.0f);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, 0)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false)).n_1700_B(multiplayerClientSuggestionProvider, false)).n_1700_B(w_1457_N, false));
        this.Z_875_P = ImmutableMap.copyOf(this.x_607_J.n_1700_B().stream().filter(state -> state.R_4764_Y(h_1847_R) == 0).collect(Collectors.toMap(Function.identity(), M_4472_P::w_1484_f)));
    }

    private static s_1395_c w_1484_f(K_4074_S state) {
        s_1395_c voxelshape = x_268_Y.n_1700_B();
        if (state.R_4764_Y(w_1457_N).booleanValue()) {
            voxelshape = Y_259_p;
        }
        if (state.R_4764_Y(Q_4569_t).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, k_2293_S);
        }
        if (state.R_4764_Y(t_1786_h).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, q_2307_F);
        }
        if (state.R_4764_Y(M_182_A).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, C_2741_M);
        }
        if (state.R_4764_Y(multiplayerClientSuggestionProvider).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, Q_2552_b);
        }
        return voxelshape.J_1907_R() ? P_4830_p : voxelshape;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return this.n_1700_B(stateIn, worldIn, currentPos) ? this.n_1700_B(worldIn, currentPos, (int)stateIn.R_4764_Y(h_1847_R)) : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Z_875_P.get(state.n_1700_B(h_1847_R, 0));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return this.J_1907_R(context.getWorld(), context.getPos());
    }

    protected K_4074_S J_1907_R(BlockGetter blockReader, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = blockReader.getBlockState(blockpos);
        if (!this.v_4262_N(blockstate) && !blockstate.G_564_y(blockReader, blockpos, b_257_Y.J_1907_R)) {
            K_4074_S blockstate1 = this.multiplayerClientSuggestionProvider();
            for (b_257_Y direction : b_257_Y.values()) {
                U_1266_O booleanproperty = Y_601_j.get(direction);
                if (booleanproperty == null) continue;
                blockstate1 = (K_4074_S)blockstate1.n_1700_B(booleanproperty, this.v_4262_N(blockReader.getBlockState(pos.offset(direction))));
            }
            return blockstate1;
        }
        return this.multiplayerClientSuggestionProvider();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return worldIn.getBlockState(blockpos).G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R) || this.G_564_y(worldIn, pos);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        worldIn.Q_2552_b().n_1700_B(pos, this, M_4472_P.n_1700_B(worldIn.w_1457_N));
        if (worldIn.H_1990_U().J_1907_R(A_2352_Z.n_1700_B)) {
            if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
                worldIn.n_1700_B(pos, false);
            }
            K_4074_S blockstate = worldIn.getBlockState(pos.down());
            boolean flag = blockstate.n_1700_B(worldIn.G_624_v().Q_4569_t());
            int i = state.R_4764_Y(h_1847_R);
            if (!flag && worldIn.c_4037_x() && this.n_1700_B(worldIn, pos) && rand.nextFloat() < 0.2f + (float)i * 0.03f) {
                worldIn.n_1700_B(pos, false);
            } else {
                boolean flag1;
                int j = Math.min(15, i + rand.nextInt(3) / 2);
                if (i != j) {
                    state = (K_4074_S)state.n_1700_B(h_1847_R, j);
                    worldIn.n_1700_B(pos, state, 4);
                }
                if (!flag) {
                    if (!this.G_564_y(worldIn, pos)) {
                        c_1514_x blockpos = pos.down();
                        if (!worldIn.getBlockState(blockpos).G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R) || i > 3) {
                            worldIn.n_1700_B(pos, false);
                        }
                        return;
                    }
                    if (i == 15 && rand.nextInt(4) == 0 && !this.v_4262_N(worldIn.getBlockState(pos.down()))) {
                        worldIn.n_1700_B(pos, false);
                        return;
                    }
                }
                int k = (flag1 = worldIn.C_2741_M(pos)) ? -50 : 0;
                this.n_1700_B((b_4507_u)worldIn, pos.east(), 300 + k, rand, i);
                this.n_1700_B((b_4507_u)worldIn, pos.west(), 300 + k, rand, i);
                this.n_1700_B((b_4507_u)worldIn, pos.down(), 250 + k, rand, i);
                this.n_1700_B((b_4507_u)worldIn, pos.up(), 250 + k, rand, i);
                this.n_1700_B((b_4507_u)worldIn, pos.north(), 300 + k, rand, i);
                this.n_1700_B((b_4507_u)worldIn, pos.south(), 300 + k, rand, i);
                c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
                for (int l = -1; l <= 1; ++l) {
                    for (int i1 = -1; i1 <= 1; ++i1) {
                        for (int j1 = -1; j1 <= 4; ++j1) {
                            if (l == 0 && j1 == 0 && i1 == 0) continue;
                            int k1 = 100;
                            if (j1 > 1) {
                                k1 += (j1 - 1) * 100;
                            }
                            blockpos$mutable.n_1700_B(pos, l, j1, i1);
                            int l1 = this.n_1700_B((T_1316_M)worldIn, (c_1514_x)blockpos$mutable);
                            if (l1 <= 0) continue;
                            int i2 = (l1 + 40 + worldIn.x_607_J().n_1700_B() * 7) / (i + 30);
                            if (flag1) {
                                i2 /= 2;
                            }
                            if (i2 <= 0 || rand.nextInt(k1) > i2 || worldIn.c_4037_x() && this.n_1700_B(worldIn, (c_1514_x)blockpos$mutable)) continue;
                            int j2 = Math.min(15, i + rand.nextInt(5) / 4);
                            worldIn.n_1700_B((c_1514_x)blockpos$mutable, this.n_1700_B((LevelAccessor)worldIn, (c_1514_x)blockpos$mutable, j2), 3);
                        }
                    }
                }
            }
        }
    }

    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        return worldIn.Q_2552_b(pos) || worldIn.Q_2552_b(pos.west()) || worldIn.Q_2552_b(pos.east()) || worldIn.Q_2552_b(pos.north()) || worldIn.Q_2552_b(pos.south());
    }

    private int t_148_a(K_4074_S state) {
        return state.J_1907_R(BlockStateProperties.A_4115_X) && state.R_4764_Y(BlockStateProperties.A_4115_X) != false ? 0 : this.H_2857_Y.getInt((Object)state.J_1907_R());
    }

    private int P_4830_p(K_4074_S state) {
        return state.J_1907_R(BlockStateProperties.A_4115_X) && state.R_4764_Y(BlockStateProperties.A_4115_X) != false ? 0 : this.c_3005_b.getInt((Object)state.J_1907_R());
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, int chance, Random random, int age) {
        int i = this.t_148_a(worldIn.getBlockState(pos));
        if (random.nextInt(chance) < i) {
            K_4074_S blockstate = worldIn.getBlockState(pos);
            if (random.nextInt(age + 10) < 5 && !worldIn.Q_2552_b(pos)) {
                int j = Math.min(age + random.nextInt(5) / 4, 15);
                worldIn.n_1700_B(pos, this.n_1700_B((LevelAccessor)worldIn, pos, j), 3);
            } else {
                worldIn.n_1700_B(pos, false);
            }
            T_2915_h block = blockstate.J_1907_R();
            if (block instanceof V_883_W) {
                V_883_W tntblock = (V_883_W)block;
                V_883_W.n_1700_B(worldIn, pos);
            }
        }
    }

    private K_4074_S n_1700_B(LevelAccessor world, c_1514_x pos, int age) {
        K_4074_S blockstate = M_4472_P.n_1700_B(world, pos);
        return blockstate.n_1700_B(a_3742_W.x_612_B) ? (K_4074_S)blockstate.n_1700_B(h_1847_R, age) : blockstate;
    }

    private boolean G_564_y(BlockGetter worldIn, c_1514_x pos) {
        for (b_257_Y direction : b_257_Y.values()) {
            if (!this.v_4262_N(worldIn.getBlockState(pos.offset(direction)))) continue;
            return true;
        }
        return false;
    }

    private int n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        if (!worldIn.u_1723_Y(pos)) {
            return 0;
        }
        int i = 0;
        for (b_257_Y direction : b_257_Y.values()) {
            K_4074_S blockstate = worldIn.getBlockState(pos.offset(direction));
            i = Math.max(this.P_4830_p(blockstate), i);
        }
        return i;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state) {
        return this.P_4830_p(state) > 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        super.n_1700_B(state, worldIn, pos, oldState, isMoving);
        worldIn.u_2550_I().n_1700_B(pos, this, M_4472_P.n_1700_B(worldIn.w_1457_N));
    }

    private static int n_1700_B(Random rand) {
        return 30 + rand.nextInt(10);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N);
    }

    private void n_1700_B(T_2915_h blockIn, int encouragement, int flammability) {
        this.c_3005_b.put((Object)blockIn, encouragement);
        this.H_2857_Y.put((Object)blockIn, flammability);
    }

    public static void J_1907_R() {
        M_4472_P fireblock = (M_4472_P)a_3742_W.x_612_B;
        fireblock.n_1700_B(a_3742_W.h_1847_R, 5, 20);
        fireblock.n_1700_B(a_3742_W.Q_4569_t, 5, 20);
        fireblock.n_1700_B(a_3742_W.M_182_A, 5, 20);
        fireblock.n_1700_B(a_3742_W.t_1786_h, 5, 20);
        fireblock.n_1700_B(a_3742_W.multiplayerClientSuggestionProvider, 5, 20);
        fireblock.n_1700_B(a_3742_W.w_1457_N, 5, 20);
        fireblock.n_1700_B(a_3742_W.ElytraMotion, 5, 20);
        fireblock.n_1700_B(a_3742_W.F_3698_k, 5, 20);
        fireblock.n_1700_B(a_3742_W.Flight, 5, 20);
        fireblock.n_1700_B(a_3742_W.c_892_d, 5, 20);
        fireblock.n_1700_B(a_3742_W.GuiMove, 5, 20);
        fireblock.n_1700_B(a_3742_W.HighJump, 5, 20);
        fireblock.n_1700_B(a_3742_W.k_3129_Y, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoArmor, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoBuy, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoDupe, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoFarm, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoEat, 5, 20);
        fireblock.n_1700_B(a_3742_W.h_2848_I, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoFish, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoJoiner, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoLeave, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoPilot, 5, 20);
        fireblock.n_1700_B(a_3742_W.AutoLes, 5, 20);
        fireblock.n_1700_B(a_3742_W.F_3572_x, 5, 20);
        fireblock.n_1700_B(a_3742_W.g_1031_K, 5, 20);
        fireblock.n_1700_B(a_3742_W.U_144_f, 5, 20);
        fireblock.n_1700_B(a_3742_W.g_134_G, 5, 20);
        fireblock.n_1700_B(a_3742_W.I_4683_a, 5, 20);
        fireblock.n_1700_B(a_3742_W.n_2689_l, 5, 20);
        fireblock.n_1700_B(a_3742_W.z_1737_N, 5, 5);
        fireblock.n_1700_B(a_3742_W.v_4276_D, 5, 5);
        fireblock.n_1700_B(a_3742_W.d_2461_k, 5, 5);
        fireblock.n_1700_B(a_3742_W.G_624_v, 5, 5);
        fireblock.n_1700_B(a_3742_W.T_2506_i, 5, 5);
        fireblock.n_1700_B(a_3742_W.q_4610_l, 5, 5);
        fireblock.n_1700_B(a_3742_W.X_933_l, 5, 5);
        fireblock.n_1700_B(a_3742_W.z_4693_k, 5, 5);
        fireblock.n_1700_B(a_3742_W.g_221_o, 5, 5);
        fireblock.n_1700_B(a_3742_W.e_2887_G, 5, 5);
        fireblock.n_1700_B(a_3742_W.B_1668_F, 5, 5);
        fireblock.n_1700_B(a_3742_W.g_164_R, 5, 5);
        fireblock.n_1700_B(a_3742_W.D_4792_h, 5, 5);
        fireblock.n_1700_B(a_3742_W.s_2632_s, 5, 5);
        fireblock.n_1700_B(a_3742_W.l_1233_K, 5, 5);
        fireblock.n_1700_B(a_3742_W.z_1333_t, 5, 5);
        fireblock.n_1700_B(a_3742_W.O_508_d, 5, 5);
        fireblock.n_1700_B(a_3742_W.r_715_M, 5, 5);
        fireblock.n_1700_B(a_3742_W.Z_976_R, 5, 5);
        fireblock.n_1700_B(a_3742_W.H_1990_U, 5, 5);
        fireblock.n_1700_B(a_3742_W.N_2525_X, 5, 5);
        fireblock.n_1700_B(a_3742_W.c_4037_x, 5, 5);
        fireblock.n_1700_B(a_3742_W.g_2268_R, 5, 5);
        fireblock.n_1700_B(a_3742_W.T_3594_S, 5, 5);
        fireblock.n_1700_B(a_3742_W.A_1038_p, 30, 60);
        fireblock.n_1700_B(a_3742_W.i_1637_u, 30, 60);
        fireblock.n_1700_B(a_3742_W.Ping, 30, 60);
        fireblock.n_1700_B(a_3742_W.p_178_J, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsClientConfig, 30, 60);
        fireblock.n_1700_B(a_3742_W.f_4016_n, 30, 60);
        fireblock.n_1700_B(a_3742_W.UploadTokenCache, 30, 20);
        fireblock.n_1700_B(a_3742_W.TextRenderingUtils, 15, 100);
        fireblock.n_1700_B(a_3742_W.u_744_e, 60, 100);
        fireblock.n_1700_B(a_3742_W.RetryCallException, 60, 100);
        fireblock.n_1700_B(a_3742_W.r_3651_U, 60, 100);
        fireblock.n_1700_B(a_3742_W.V_983_n, 60, 100);
        fireblock.n_1700_B(a_3742_W.X_812_G, 60, 100);
        fireblock.n_1700_B(a_3742_W.NameProtect, 60, 100);
        fireblock.n_1700_B(a_3742_W.OpenWalls, 60, 100);
        fireblock.n_1700_B(a_3742_W.Party, 60, 100);
        fireblock.n_1700_B(a_3742_W.PotionTracker, 60, 100);
        fireblock.n_1700_B(a_3742_W.s_1671_u, 60, 100);
        fireblock.n_1700_B(a_3742_W.RealmsResetNormalWorldScreen, 60, 100);
        fireblock.n_1700_B(a_3742_W.C_3538_G, 60, 100);
        fireblock.n_1700_B(a_3742_W.A_3959_N, 60, 100);
        fireblock.n_1700_B(a_3742_W.G_424_k, 60, 100);
        fireblock.n_1700_B(a_3742_W.RealmsSettingsScreen, 60, 100);
        fireblock.n_1700_B(a_3742_W.f_1043_S, 60, 100);
        fireblock.n_1700_B(a_3742_W.F_4247_a, 60, 100);
        fireblock.n_1700_B(a_3742_W.J_739_q, 60, 100);
        fireblock.n_1700_B(a_3742_W.C_1162_e, 60, 100);
        fireblock.n_1700_B(a_3742_W.D_4361_a, 60, 100);
        fireblock.n_1700_B(a_3742_W.u_55_V, 60, 100);
        fireblock.n_1700_B(a_3742_W.f_3449_S, 60, 100);
        fireblock.n_1700_B(a_3742_W.R_3077_Z, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsScreenWithCallback, 30, 60);
        fireblock.n_1700_B(a_3742_W.M_2677_i, 30, 60);
        fireblock.n_1700_B(a_3742_W.c_132_F, 30, 60);
        fireblock.n_1700_B(a_3742_W.g_4106_L, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsClientOutdatedScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.W_3464_O, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsConfirmScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsCreateRealmScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.C_290_v, 30, 60);
        fireblock.n_1700_B(a_3742_W.w_728_N, 30, 60);
        fireblock.n_1700_B(a_3742_W.J_4256_G, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsLongConfirmationScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsLongRunningMcoTaskScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.i_2993_w, 30, 60);
        fireblock.n_1700_B(a_3742_W.RealmsParentalConsentScreen, 30, 60);
        fireblock.n_1700_B(a_3742_W.U_4087_m, 15, 100);
        fireblock.n_1700_B(a_3742_W.ItemHelper, 5, 5);
        fireblock.n_1700_B(a_3742_W.M_4609_z, 60, 20);
        fireblock.n_1700_B(a_3742_W.Y_2805_J, 15, 20);
        fireblock.n_1700_B(a_3742_W.AuctionHelper, 60, 20);
        fireblock.n_1700_B(a_3742_W.AutoAccept, 60, 20);
        fireblock.n_1700_B(a_3742_W.AutoContract, 60, 20);
        fireblock.n_1700_B(a_3742_W.AutoDuel, 60, 20);
        fireblock.n_1700_B(a_3742_W.BedrockProxy, 60, 20);
        fireblock.n_1700_B(a_3742_W.BetterMinecraft, 60, 20);
        fireblock.n_1700_B(a_3742_W.BotAutoCollector, 60, 20);
        fireblock.n_1700_B(a_3742_W.Bots, 60, 20);
        fireblock.n_1700_B(a_3742_W.ClickFriend, 60, 20);
        fireblock.n_1700_B(a_3742_W.ClientSpoof, 60, 20);
        fireblock.n_1700_B(a_3742_W.DeathCoords, 60, 20);
        fireblock.n_1700_B(a_3742_W.DiscordRPC, 60, 20);
        fireblock.n_1700_B(a_3742_W.EcSaver, 60, 20);
        fireblock.n_1700_B(a_3742_W.ElytraHelper, 60, 20);
        fireblock.n_1700_B(a_3742_W.FlagDetector, 60, 20);
        fireblock.n_1700_B(a_3742_W.Globals, 60, 20);
        fireblock.n_1700_B(a_3742_W.T_797_O, 30, 60);
        fireblock.n_1700_B(a_3742_W.t_1509_b, 60, 60);
        fireblock.n_1700_B(a_3742_W.i_770_g, 60, 60);
        fireblock.n_1700_B(a_3742_W.F_489_x, 30, 20);
        fireblock.n_1700_B(a_3742_W.P_2068_y, 5, 20);
        fireblock.n_1700_B(a_3742_W.s_4405_m, 60, 100);
        fireblock.n_1700_B(a_3742_W.t_4057_p, 5, 20);
        fireblock.n_1700_B(a_3742_W.w_4866_k, 30, 20);
    }
}



