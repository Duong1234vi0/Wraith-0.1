/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.A_4919_q;
import lightning.product.LootContextParams;
import lightning.product.F_1241_B;
import lightning.product.LeavesBlock;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_4094_N;
import lightning.product.MutableComponent;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.NonNullList;
import lightning.product.S_1134_u;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.Stats;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SoundType;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_3316_o;
import lightning.product.g_4560_H;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.n_1494_c;
import lightning.product.n_4637_L;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.q_4293_E;
import lightning.product.r_109_r;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.v_1669_V;
import lightning.product.w_424_u;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.BooleanOp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class T_2915_h
extends q_4293_E
implements q_1803_e {
    protected static final Logger Y_1740_V = LogManager.getLogger();
    public static final w_424_u<K_4074_S> t_4043_B = new w_424_u();
    private static final LoadingCache<s_1395_c, Boolean> P_4830_p = CacheBuilder.newBuilder().maximumSize(512L).weakKeys().build((CacheLoader)new CacheLoader<s_1395_c, Boolean>(){

        public Boolean n_1700_B(s_1395_c p_load_1_) {
            return !x_268_Y.R_4764_Y(x_268_Y.J_1907_R(), p_load_1_, BooleanOp.v_4262_N);
        }

        public /* synthetic */ Object load(Object object) throws Exception {
            return this.n_1700_B((s_1395_c)object);
        }
    });
    protected final Y_1835_y<T_2915_h, K_4074_S> x_607_J;
    private K_4074_S h_1847_R;
    @Nullable
    private String Q_4569_t;
    @Nullable
    private q_1613_l M_182_A;
    private static final ThreadLocal<Object2ByteLinkedOpenHashMap<n_1700_B>> t_1786_h = ThreadLocal.withInitial(() -> {
        Object2ByteLinkedOpenHashMap<n_1700_B> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<n_1700_B>(2048, 0.25f){

            protected void rehash(int p_rehash_1_) {
            }
        };
        object2bytelinkedopenhashmap.defaultReturnValue((byte)127);
        return object2bytelinkedopenhashmap;
    });

    public static int s_956_w(@Nullable K_4074_S state) {
        if (state == null) {
            return 0;
        }
        int i = t_4043_B.n_1700_B(state);
        return i == -1 ? 0 : i;
    }

    public static K_4074_S n_1700_B(int id) {
        K_4074_S blockstate = t_4043_B.n_1700_B(id);
        return blockstate == null ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : blockstate;
    }

    public static T_2915_h n_1700_B(@Nullable q_1613_l itemIn) {
        return itemIn instanceof v_1669_V ? ((v_1669_V)itemIn).v_4262_N() : a_3742_W.n_1700_B;
    }

    public static K_4074_S n_1700_B(K_4074_S oldState, K_4074_S newState, b_4507_u worldIn, c_1514_x pos) {
        s_1395_c voxelshape = x_268_Y.J_1907_R(oldState.u_2550_I(worldIn, pos), newState.u_2550_I(worldIn, pos), BooleanOp.R_4764_Y).n_1700_B(pos.getX(), (double)pos.getY(), (double)pos.getZ());
        for (N_4263_v entity : worldIn.n_1700_B((N_4263_v)null, voxelshape.n_1700_B())) {
            double d0 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.J_1907_R, entity.i_601_W().offset(0.0, 1.0, 0.0), Stream.of(voxelshape), -1.0);
            entity.P_4830_p(entity.O_3598_v(), entity.X_2960_b() + 1.0 + d0, entity.l_2647_k());
        }
        return newState;
    }

    public static s_1395_c n_1700_B(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x_268_Y.n_1700_B(x1 / 16.0, y1 / 16.0, z1 / 16.0, x2 / 16.0, y2 / 16.0, z2 / 16.0);
    }

    public boolean n_1700_B(r_109_r<T_2915_h> tagIn) {
        return tagIn.n_1700_B(this);
    }

    public boolean J_1907_R(T_2915_h block) {
        return this == block;
    }

    public static K_4074_S J_1907_R(K_4074_S currentState, LevelAccessor worldIn, c_1514_x pos) {
        K_4074_S blockstate = currentState;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : n_1700_B) {
            blockpos$mutable.n_1700_B(pos, direction);
            blockstate = blockstate.n_1700_B(direction, worldIn.getBlockState(blockpos$mutable), worldIn, pos, blockpos$mutable);
        }
        return blockstate;
    }

    public static void n_1700_B(K_4074_S oldState, K_4074_S newState, LevelAccessor worldIn, c_1514_x pos, int flags) {
        T_2915_h.n_1700_B(oldState, newState, worldIn, pos, flags, 512);
    }

    public static void n_1700_B(K_4074_S oldState, K_4074_S newState, LevelAccessor world, c_1514_x pos, int flags, int recursionLeft) {
        if (newState != oldState) {
            if (newState.v_4262_N()) {
                if (!world.v_4276_D()) {
                    world.n_1700_B(pos, (flags & 0x20) == 0, (N_4263_v)null, recursionLeft);
                }
            } else {
                world.n_1700_B(pos, newState, flags & 0xFFFFFFDF, recursionLeft);
            }
        }
    }

    public T_2915_h(q_4293_E.P_1922_E properties) {
        super(properties);
        Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder = new Y_1835_y.n_1700_B<T_2915_h, K_4074_S>(this);
        this.n_1700_B(builder);
        this.x_607_J = builder.n_1700_B(T_2915_h::multiplayerClientSuggestionProvider, K_4074_S::new);
        this.u_2550_I(this.x_607_J.J_1907_R());
    }

    public static boolean R_4764_Y(T_2915_h blockIn) {
        return blockIn instanceof LeavesBlock || blockIn == a_3742_W.N_4890_q || blockIn == a_3742_W.X_2048_Y || blockIn == a_3742_W.l_2647_k || blockIn == a_3742_W.E_3343_g || blockIn == a_3742_W.A_3244_K || blockIn.n_1700_B(BlockTags.t_4219_U);
    }

    public boolean a_(K_4074_S state) {
        return this.P_1922_E;
    }

    public static boolean R_4764_Y(K_4074_S adjacentState, BlockGetter blockState, c_1514_x blockAccess, b_257_Y pos) {
        c_1514_x blockpos = blockAccess.offset(pos);
        K_4074_S blockstate = blockState.getBlockState(blockpos);
        if (adjacentState.n_1700_B(blockstate, pos)) {
            return false;
        }
        if (blockstate.M_588_G()) {
            n_1700_B block$rendersidecachekey = new n_1700_B(adjacentState, blockstate, pos);
            Object2ByteLinkedOpenHashMap<n_1700_B> object2bytelinkedopenhashmap = t_1786_h.get();
            byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst((Object)block$rendersidecachekey);
            if (b0 != 127) {
                return b0 != 0;
            }
            s_1395_c voxelshape = adjacentState.n_1700_B(blockState, blockAccess, pos);
            s_1395_c voxelshape1 = blockstate.n_1700_B(blockState, blockpos, pos.u_1723_Y());
            boolean flag = x_268_Y.R_4764_Y(voxelshape, voxelshape1, BooleanOp.P_1922_E);
            if (object2bytelinkedopenhashmap.size() == 2048) {
                object2bytelinkedopenhashmap.removeLastByte();
            }
            object2bytelinkedopenhashmap.putAndMoveToFirst((Object)block$rendersidecachekey, (byte)(flag ? 1 : 0));
            return flag;
        }
        return true;
    }

    public static boolean R_4764_Y(BlockGetter worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos).n_1700_B(worldIn, pos, b_257_Y.J_1907_R, F_4094_N.R_4764_Y);
    }

    public static boolean n_1700_B(T_1316_M worldIn, c_1514_x pos, b_257_Y directionIn) {
        K_4074_S blockstate = worldIn.getBlockState(pos);
        return directionIn == b_257_Y.n_1700_B && blockstate.n_1700_B(BlockTags.RealmsServerPing) ? false : blockstate.n_1700_B((BlockGetter)worldIn, pos, directionIn, F_4094_N.J_1907_R);
    }

    public static boolean n_1700_B(s_1395_c shape, b_257_Y side) {
        s_1395_c voxelshape = shape.n_1700_B(side);
        return T_2915_h.n_1700_B(voxelshape);
    }

    public static boolean n_1700_B(s_1395_c shape) {
        return (Boolean)P_4830_p.getUnchecked((Object)shape);
    }

    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return !T_2915_h.n_1700_B(state.s_956_w(reader, pos)) && state.P_4830_p().R_4764_Y();
    }

    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
    }

    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
    }

    public static List<Z_1993_T> n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, @Nullable i_2154_H tileEntityIn) {
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(worldIn).n_1700_B(worldIn.w_1457_N).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(pos)).n_1700_B(LootContextParams.t_148_a, Z_1993_T.J_1907_R).J_1907_R(LootContextParams.w_1484_f, tileEntityIn);
        return state.n_1700_B(lootcontext$builder);
    }

    public static List<Z_1993_T> n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, @Nullable i_2154_H tileEntityIn, @Nullable N_4263_v entityIn, Z_1993_T stack) {
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(worldIn).n_1700_B(worldIn.w_1457_N).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.n_1700_B(pos)).n_1700_B(LootContextParams.t_148_a, stack).J_1907_R(LootContextParams.n_1700_B, entityIn).J_1907_R(LootContextParams.w_1484_f, tileEntityIn);
        return state.n_1700_B(lootcontext$builder);
    }

    public static void G_564_y(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        if (worldIn instanceof e_3591_l) {
            T_2915_h.n_1700_B(state, (e_3591_l)worldIn, pos, (i_2154_H)null).forEach(stackToSpawn -> T_2915_h.n_1700_B(worldIn, pos, stackToSpawn));
            state.n_1700_B((e_3591_l)worldIn, pos, Z_1993_T.J_1907_R);
        }
    }

    public static void n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos, @Nullable i_2154_H tileEntityIn) {
        if (worldIn instanceof e_3591_l) {
            T_2915_h.n_1700_B(state, (e_3591_l)worldIn, pos, tileEntityIn).forEach(stackToSpawn -> T_2915_h.n_1700_B((e_3591_l)worldIn, pos, stackToSpawn));
            state.n_1700_B((e_3591_l)worldIn, pos, Z_1993_T.J_1907_R);
        }
    }

    public static void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, @Nullable i_2154_H tileEntityIn, N_4263_v entityIn, Z_1993_T stack) {
        if (worldIn instanceof e_3591_l) {
            T_2915_h.n_1700_B(state, (e_3591_l)worldIn, pos, tileEntityIn, entityIn, stack).forEach(stackToSpawn -> T_2915_h.n_1700_B(worldIn, pos, stackToSpawn));
            state.n_1700_B((e_3591_l)worldIn, pos, stack);
        }
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, Z_1993_T stack) {
        if (!worldIn.Y_259_p && !stack.n_1700_B() && worldIn.H_1990_U().J_1907_R(A_2352_Z.u_1723_Y)) {
            float f = 0.5f;
            double d0 = (double)(worldIn.w_1457_N.nextFloat() * 0.5f) + 0.25;
            double d1 = (double)(worldIn.w_1457_N.nextFloat() * 0.5f) + 0.25;
            double d2 = (double)(worldIn.w_1457_N.nextFloat() * 0.5f) + 0.25;
            n_1494_c itementity = new n_1494_c(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, stack);
            itementity.t_148_a();
            worldIn.a_(itementity);
        }
    }

    protected void n_1700_B(e_3591_l worldIn, c_1514_x pos, int amount) {
        if (worldIn.H_1990_U().J_1907_R(A_2352_Z.u_1723_Y)) {
            while (amount > 0) {
                int i = n_4637_L.n_1700_B(amount);
                amount -= i;
                worldIn.a_(new n_4637_L(worldIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, i));
            }
        }
    }

    public float u_2550_I() {
        return this.G_564_y;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, F_1241_B explosionIn) {
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
    }

    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return this.multiplayerClientSuggestionProvider();
    }

    public void n_1700_B(b_4507_u worldIn, a_3913_L player, c_1514_x pos, K_4074_S state, @Nullable i_2154_H te, Z_1993_T stack) {
        player.n_1700_B(Stats.n_1700_B.J_1907_R(this));
        player.C_2741_M(0.005f);
        T_2915_h.n_1700_B(state, worldIn, pos, te, (N_4263_v)player, stack);
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        if (state.J_1907_R() == a_3742_W.ClientBootstrap) {
            A_4115_X.n_1700_B(new g_4560_H(this, pos));
        }
    }

    public boolean n_1700_B() {
        return !this.J_1907_R.J_1907_R() && !this.J_1907_R.n_1700_B();
    }

    public MutableComponent M_588_G() {
        return new F_2904_S(this.P_4830_p());
    }

    public String P_4830_p() {
        if (this.Q_4569_t == null) {
            this.Q_4569_t = j_3341_s.n_1700_B("block", V_3137_a.q_4610_l.J_1907_R(this));
        }
        return this.Q_4569_t;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        entityIn.R_4764_Y(fallDistance, 1.0f);
    }

    public void n_1700_B(BlockGetter worldIn, N_4263_v entityIn) {
        entityIn.v_4262_N(entityIn.I_4348_c().G_564_y(1.0, 0.0, 1.0));
    }

    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(this);
    }

    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        items.add(new Z_1993_T(this));
    }

    public float h_1847_R() {
        return this.v_4262_N;
    }

    public float Q_4569_t() {
        return this.w_1484_f;
    }

    public float M_182_A() {
        return this.t_148_a;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        worldIn.n_1700_B(player, 2001, pos, T_2915_h.s_956_w(state));
        if (this.n_1700_B(BlockTags.q_1982_R)) {
            A_4919_q.n_1700_B(player, false);
        }
    }

    public void R_4764_Y(b_4507_u worldIn, c_1514_x pos) {
    }

    public boolean n_1700_B(F_1241_B explosionIn) {
        return true;
    }

    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
    }

    public Y_1835_y<T_2915_h, K_4074_S> t_1786_h() {
        return this.x_607_J;
    }

    protected final void u_2550_I(K_4074_S state) {
        this.h_1847_R = state;
    }

    public final K_4074_S multiplayerClientSuggestionProvider() {
        return this.h_1847_R;
    }

    public SoundType M_588_G(K_4074_S state) {
        return this.u_1723_Y;
    }

    @Override
    public q_1613_l u_1723_Y() {
        if (this.M_182_A == null) {
            this.M_182_A = q_1613_l.n_1700_B(this);
        }
        return this.M_182_A;
    }

    public boolean w_1457_N() {
        return this.s_956_w;
    }

    public String toString() {
        return "Block{" + String.valueOf(V_3137_a.q_4610_l.J_1907_R(this)) + "}";
    }

    public void n_1700_B(Z_1993_T stack, @Nullable BlockGetter worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
    }

    @Override
    protected T_2915_h v_4262_N() {
        return this;
    }

    public static final class n_1700_B {
        private final K_4074_S n_1700_B;
        private final K_4074_S J_1907_R;
        private final b_257_Y R_4764_Y;

        public n_1700_B(K_4074_S state, K_4074_S adjacentState, b_257_Y side) {
            this.n_1700_B = state;
            this.J_1907_R = adjacentState;
            this.R_4764_Y = side;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof n_1700_B)) {
                return false;
            }
            n_1700_B block$rendersidecachekey = (n_1700_B)p_equals_1_;
            return this.n_1700_B == block$rendersidecachekey.n_1700_B && this.J_1907_R == block$rendersidecachekey.J_1907_R && this.R_4764_Y == block$rendersidecachekey.R_4764_Y;
        }

        public int hashCode() {
            int i = this.n_1700_B.hashCode();
            i = 31 * i + this.J_1907_R.hashCode();
            return 31 * i + this.R_4764_Y.hashCode();
        }
    }
}



