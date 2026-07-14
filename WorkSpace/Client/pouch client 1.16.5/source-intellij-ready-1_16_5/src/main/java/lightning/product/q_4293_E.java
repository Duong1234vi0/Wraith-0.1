/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.serialization.MapCodec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.LootContextParams;
import lightning.product.Projectile;
import lightning.product.DebugPackets;
import lightning.product.BlockGetter;
import lightning.product.F_4094_N;
import lightning.product.BlockHitResult;
import lightning.product.G_4961_S;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.P_1008_U;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.X_1313_W;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SoundType;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.h_3270_j;
import lightning.product.k_2789_z;
import lightning.product.m_3054_I;
import lightning.product.ClientBootstrap;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_4099_E;
import lightning.product.r_109_r;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.MaterialColor;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;

public abstract class q_4293_E {
    protected static final b_257_Y[] n_1700_B = new b_257_Y[]{b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.R_4764_Y, b_257_Y.G_564_y, b_257_Y.n_1700_B, b_257_Y.J_1907_R};
    protected final Material J_1907_R;
    protected final boolean R_4764_Y;
    protected final float G_564_y;
    protected final boolean P_1922_E;
    protected final SoundType u_1723_Y;
    protected final float v_4262_N;
    protected final float w_1484_f;
    protected final float t_148_a;
    protected final boolean s_956_w;
    protected final P_1922_E u_2550_I;
    @Nullable
    protected g_2336_b M_588_G;

    public q_4293_E(P_1922_E properties) {
        this.J_1907_R = properties.n_1700_B;
        this.R_4764_Y = properties.R_4764_Y;
        this.M_588_G = properties.P_4830_p;
        this.G_564_y = properties.u_1723_Y;
        this.P_1922_E = properties.t_148_a;
        this.u_1723_Y = properties.G_564_y;
        this.v_4262_N = properties.s_956_w;
        this.w_1484_f = properties.u_2550_I;
        this.t_148_a = properties.M_588_G;
        this.s_956_w = properties.Q_2552_b;
        this.u_2550_I = properties;
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos, int flags, int recursionLeft) {
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return !state.multiplayerClientSuggestionProvider(worldIn, pos);
            }
            case J_1907_R: {
                return worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R);
            }
            case R_4764_Y: {
                return !state.multiplayerClientSuggestionProvider(worldIn, pos);
            }
        }
        return false;
    }

    @Deprecated
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return stateIn;
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, K_4074_S adjacentBlockState, b_257_Y side) {
        return false;
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        DebugPackets.n_1700_B(worldIn, pos);
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
    }

    @Deprecated
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (this.G_564_y() && !state.n_1700_B(newState.J_1907_R())) {
            worldIn.t_1786_h(pos);
        }
    }

    @Deprecated
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        return m_3054_I.R_4764_Y;
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, int id, int param) {
        return false;
    }

    @Deprecated
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Deprecated
    public boolean J_1907_R(K_4074_S state) {
        return false;
    }

    @Deprecated
    public boolean R_4764_Y(K_4074_S state) {
        return false;
    }

    @Deprecated
    public w_1454_v G_564_y(K_4074_S state) {
        return this.J_1907_R.v_4262_N();
    }

    @Deprecated
    public FluidState P_1922_E(K_4074_S state) {
        return Fluids.n_1700_B.w_1484_f();
    }

    @Deprecated
    public boolean u_1723_Y(K_4074_S state) {
        return false;
    }

    public G_564_y R_4764_Y() {
        return lightning.product.q_4293_E$G_564_y.n_1700_B;
    }

    @Deprecated
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return state;
    }

    @Deprecated
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state;
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        return this.J_1907_R.P_1922_E() && (useContext.getItem().n_1700_B() || useContext.getItem().J_1907_R() != this.u_1723_Y());
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, Fluid fluid) {
        return this.J_1907_R.P_1922_E() || !this.J_1907_R.J_1907_R();
    }

    @Deprecated
    public List<Z_1993_T> n_1700_B(K_4074_S state, q_1704_m.n_1700_B builder) {
        g_2336_b resourcelocation = this.P_1922_E();
        if (resourcelocation == o_4810_o.n_1700_B) {
            return Collections.emptyList();
        }
        q_1704_m lootcontext = builder.n_1700_B(LootContextParams.v_4262_N, state).n_1700_B(f_1402_I.M_588_G);
        e_3591_l serverworld = lootcontext.R_4764_Y();
        p_4985_U loottable = serverworld.T_2506_i().F_2624_D().n_1700_B(resourcelocation);
        return loottable.n_1700_B(lootcontext);
    }

    @Deprecated
    public long n_1700_B(K_4074_S state, c_1514_x pos) {
        return u_530_F.n_1700_B(pos);
    }

    @Deprecated
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.s_956_w(worldIn, pos);
    }

    @Deprecated
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return this.J_1907_R(state, reader, pos, CollisionContext.J_1907_R());
    }

    @Deprecated
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return x_268_Y.n_1700_B();
    }

    @Deprecated
    public int G_564_y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        if (state.t_148_a(worldIn, pos)) {
            return worldIn.Z_875_P();
        }
        return state.n_1700_B(worldIn, pos) ? 0 : 1;
    }

    @Nullable
    @Deprecated
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return null;
    }

    @Deprecated
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return true;
    }

    @Deprecated
    public float P_1922_E(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.multiplayerClientSuggestionProvider(worldIn, pos) ? 0.2f : 1.0f;
    }

    @Deprecated
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return 0;
    }

    @Deprecated
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return x_268_Y.J_1907_R();
    }

    @Deprecated
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.R_4764_Y ? state.s_956_w(worldIn, pos) : x_268_Y.n_1700_B();
    }

    @Deprecated
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return this.J_1907_R(state, reader, pos, context);
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        this.J_1907_R(state, worldIn, pos, random);
    }

    @Deprecated
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
    }

    @Deprecated
    public float n_1700_B(K_4074_S state, a_3913_L player, BlockGetter worldIn, c_1514_x pos) {
        float f = state.w_1484_f(worldIn, pos);
        if (f == -1.0f) {
            return 0.0f;
        }
        int i = player.G_564_y(state) ? 30 : 100;
        return player.R_4764_Y(state) / f / (float)i;
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
    }

    @Deprecated
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return 0;
    }

    @Deprecated
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
    }

    @Deprecated
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return 0;
    }

    public final boolean G_564_y() {
        return this instanceof k_2789_z;
    }

    public final g_2336_b P_1922_E() {
        if (this.M_588_G == null) {
            g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(this.v_4262_N());
            this.M_588_G = new g_2336_b(resourcelocation.R_4764_Y(), "blocks/" + resourcelocation.J_1907_R());
        }
        return this.M_588_G;
    }

    @Deprecated
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
    }

    public abstract q_1613_l u_1723_Y();

    protected abstract T_2915_h v_4262_N();

    public MaterialColor w_1484_f() {
        return this.u_2550_I.J_1907_R.apply(this.v_4262_N().multiplayerClientSuggestionProvider());
    }

    public static class P_1922_E {
        private Material n_1700_B;
        private Function<K_4074_S, MaterialColor> J_1907_R;
        private boolean R_4764_Y = true;
        private SoundType G_564_y = SoundType.P_1922_E;
        private ToIntFunction<K_4074_S> P_1922_E = light -> 0;
        private float u_1723_Y;
        private float v_4262_N;
        private boolean w_1484_f;
        private boolean t_148_a;
        private float s_956_w = 0.6f;
        private float u_2550_I = 1.0f;
        private float M_588_G = 1.0f;
        private g_2336_b P_4830_p;
        private boolean h_1847_R = true;
        private boolean Q_4569_t;
        private J_1907_R<t_5_h<?>> M_182_A = (state, reader, pos, entityType) -> state.G_564_y(reader, pos, b_257_Y.J_1907_R) && state.u_1723_Y() < 14;
        private R_4764_Y t_1786_h = (state, reader, pos) -> state.R_4764_Y().u_1723_Y() && state.multiplayerClientSuggestionProvider(reader, pos);
        private R_4764_Y multiplayerClientSuggestionProvider;
        private R_4764_Y w_1457_N = this.multiplayerClientSuggestionProvider = (state, reader, pos) -> this.n_1700_B.R_4764_Y() && state.multiplayerClientSuggestionProvider(reader, pos);
        private R_4764_Y Y_601_j = (state, reader, pos) -> false;
        private R_4764_Y Y_259_p = (state, reader, pos) -> false;
        private boolean Q_2552_b;

        private P_1922_E(Material materialIn, MaterialColor mapColorIn) {
            this(materialIn, (K_4074_S state) -> mapColorIn);
        }

        private P_1922_E(Material material, Function<K_4074_S, MaterialColor> stateColorFunction) {
            this.n_1700_B = material;
            this.J_1907_R = stateColorFunction;
        }

        public static P_1922_E n_1700_B(Material materialIn) {
            return lightning.product.q_4293_E$P_1922_E.n_1700_B(materialIn, materialIn.w_1484_f());
        }

        public static P_1922_E n_1700_B(Material materialIn, e_933_M color) {
            return lightning.product.q_4293_E$P_1922_E.n_1700_B(materialIn, color.P_1922_E());
        }

        public static P_1922_E n_1700_B(Material materialIn, MaterialColor mapColorIn) {
            return new P_1922_E(materialIn, mapColorIn);
        }

        public static P_1922_E n_1700_B(Material material, Function<K_4074_S, MaterialColor> stateColorFunction) {
            return new P_1922_E(material, stateColorFunction);
        }

        public static P_1922_E n_1700_B(q_4293_E blockIn) {
            P_1922_E abstractblock$properties = new P_1922_E(blockIn.J_1907_R, blockIn.u_2550_I.J_1907_R);
            abstractblock$properties.n_1700_B = blockIn.u_2550_I.n_1700_B;
            abstractblock$properties.v_4262_N = blockIn.u_2550_I.v_4262_N;
            abstractblock$properties.u_1723_Y = blockIn.u_2550_I.u_1723_Y;
            abstractblock$properties.R_4764_Y = blockIn.u_2550_I.R_4764_Y;
            abstractblock$properties.t_148_a = blockIn.u_2550_I.t_148_a;
            abstractblock$properties.P_1922_E = blockIn.u_2550_I.P_1922_E;
            abstractblock$properties.J_1907_R = blockIn.u_2550_I.J_1907_R;
            abstractblock$properties.G_564_y = blockIn.u_2550_I.G_564_y;
            abstractblock$properties.s_956_w = blockIn.u_2550_I.s_956_w;
            abstractblock$properties.u_2550_I = blockIn.u_2550_I.u_2550_I;
            abstractblock$properties.Q_2552_b = blockIn.u_2550_I.Q_2552_b;
            abstractblock$properties.h_1847_R = blockIn.u_2550_I.h_1847_R;
            abstractblock$properties.Q_4569_t = blockIn.u_2550_I.Q_4569_t;
            abstractblock$properties.w_1484_f = blockIn.u_2550_I.w_1484_f;
            return abstractblock$properties;
        }

        public P_1922_E n_1700_B() {
            this.R_4764_Y = false;
            this.h_1847_R = false;
            return this;
        }

        public P_1922_E J_1907_R() {
            this.h_1847_R = false;
            return this;
        }

        public P_1922_E n_1700_B(float slipperinessIn) {
            this.s_956_w = slipperinessIn;
            return this;
        }

        public P_1922_E J_1907_R(float factor) {
            this.u_2550_I = factor;
            return this;
        }

        public P_1922_E R_4764_Y(float factor) {
            this.M_588_G = factor;
            return this;
        }

        public P_1922_E n_1700_B(SoundType soundTypeIn) {
            this.G_564_y = soundTypeIn;
            return this;
        }

        public P_1922_E n_1700_B(ToIntFunction<K_4074_S> stateLightFunction) {
            this.P_1922_E = stateLightFunction;
            return this;
        }

        public P_1922_E n_1700_B(float hardnessIn, float resistanceIn) {
            this.v_4262_N = hardnessIn;
            this.u_1723_Y = Math.max(0.0f, resistanceIn);
            return this;
        }

        public P_1922_E R_4764_Y() {
            return this.G_564_y(0.0f);
        }

        public P_1922_E G_564_y(float hardnessAndResistance) {
            this.n_1700_B(hardnessAndResistance, hardnessAndResistance);
            return this;
        }

        public P_1922_E G_564_y() {
            this.t_148_a = true;
            return this;
        }

        public P_1922_E P_1922_E() {
            this.Q_2552_b = true;
            return this;
        }

        public P_1922_E u_1723_Y() {
            this.P_4830_p = o_4810_o.n_1700_B;
            return this;
        }

        public P_1922_E n_1700_B(T_2915_h blockIn) {
            this.P_4830_p = blockIn.P_1922_E();
            return this;
        }

        public P_1922_E v_4262_N() {
            this.Q_4569_t = true;
            return this;
        }

        public P_1922_E n_1700_B(J_1907_R<t_5_h<?>> spawnPredicate) {
            this.M_182_A = spawnPredicate;
            return this;
        }

        public P_1922_E n_1700_B(R_4764_Y opaquePredicate) {
            this.t_1786_h = opaquePredicate;
            return this;
        }

        public P_1922_E J_1907_R(R_4764_Y suffocatesPredicate) {
            this.multiplayerClientSuggestionProvider = suffocatesPredicate;
            return this;
        }

        public P_1922_E R_4764_Y(R_4764_Y blocksVisionPredicate) {
            this.w_1457_N = blocksVisionPredicate;
            return this;
        }

        public P_1922_E G_564_y(R_4764_Y postProcessingPredicate) {
            this.Y_601_j = postProcessingPredicate;
            return this;
        }

        public P_1922_E P_1922_E(R_4764_Y emmisiveRenderPredicate) {
            this.Y_259_p = emmisiveRenderPredicate;
            return this;
        }

        public P_1922_E w_1484_f() {
            this.w_1484_f = true;
            return this;
        }
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y();
        public static final /* enum */ G_564_y J_1907_R = new G_564_y();
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y();
        private static final /* synthetic */ G_564_y[] G_564_y;

        public static G_564_y[] values() {
            return (G_564_y[])G_564_y.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private static /* synthetic */ G_564_y[] n_1700_B() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.q_4293_E$G_564_y.n_1700_B();
        }
    }

    public static interface R_4764_Y {
        public boolean test(K_4074_S var1, BlockGetter var2, c_1514_x var3);
    }

    public static interface J_1907_R<A> {
        public boolean test(K_4074_S var1, BlockGetter var2, c_1514_x var3, A var4);
    }

    public static abstract class lightning.product.q_4293_E$n_1700_B
    extends P_1008_U<T_2915_h, K_4074_S> {
        private final int J_1907_R;
        private final boolean P_1922_E;
        private final boolean u_1723_Y;
        private final Material v_4262_N;
        private final MaterialColor w_1484_f;
        private final float t_148_a;
        private final boolean s_956_w;
        private final boolean u_2550_I;
        private final R_4764_Y M_588_G;
        private final R_4764_Y P_4830_p;
        private final R_4764_Y h_1847_R;
        private final R_4764_Y Q_4569_t;
        private final R_4764_Y M_182_A;
        @Nullable
        protected n_1700_B n_1700_B;

        protected lightning.product.q_4293_E$n_1700_B(T_2915_h block, ImmutableMap<v_3760_Q<?>, Comparable<?>> propertyValueMap, MapCodec<K_4074_S> stateCodec) {
            super(block, propertyValueMap, stateCodec);
            P_1922_E abstractblock$properties = block.u_2550_I;
            this.J_1907_R = abstractblock$properties.P_1922_E.applyAsInt(this.M_182_A());
            this.P_1922_E = block.J_1907_R(this.M_182_A());
            this.u_1723_Y = abstractblock$properties.Q_4569_t;
            this.v_4262_N = abstractblock$properties.n_1700_B;
            this.w_1484_f = abstractblock$properties.J_1907_R.apply(this.M_182_A());
            this.t_148_a = abstractblock$properties.v_4262_N;
            this.s_956_w = abstractblock$properties.w_1484_f;
            this.u_2550_I = abstractblock$properties.h_1847_R;
            this.M_588_G = abstractblock$properties.t_1786_h;
            this.P_4830_p = abstractblock$properties.multiplayerClientSuggestionProvider;
            this.h_1847_R = abstractblock$properties.w_1457_N;
            this.Q_4569_t = abstractblock$properties.Y_601_j;
            this.M_182_A = abstractblock$properties.Y_259_p;
        }

        public void n_1700_B() {
            if (!this.J_1907_R().w_1457_N()) {
                this.n_1700_B = new n_1700_B(this.M_182_A());
            }
        }

        public T_2915_h J_1907_R() {
            return (T_2915_h)this.R_4764_Y;
        }

        public Material R_4764_Y() {
            return this.v_4262_N;
        }

        public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, t_5_h<?> type) {
            return this.J_1907_R().u_2550_I.M_182_A.test(this.M_182_A(), worldIn, pos, type);
        }

        public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos) {
            return this.n_1700_B != null ? this.n_1700_B.v_4262_N : this.J_1907_R().a_(this.M_182_A(), worldIn, pos);
        }

        public int J_1907_R(BlockGetter worldIn, c_1514_x pos) {
            return this.n_1700_B != null ? this.n_1700_B.w_1484_f : this.J_1907_R().G_564_y(this.M_182_A(), worldIn, pos);
        }

        public s_1395_c n_1700_B(BlockGetter worldIn, c_1514_x pos, b_257_Y directionIn) {
            return this.n_1700_B != null && this.n_1700_B.t_148_a != null ? this.n_1700_B.t_148_a[directionIn.ordinal()] : x_268_Y.n_1700_B(this.R_4764_Y(worldIn, pos), directionIn);
        }

        public s_1395_c R_4764_Y(BlockGetter reader, c_1514_x pos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), reader, pos);
        }

        public boolean G_564_y() {
            return this.n_1700_B == null || this.n_1700_B.R_4764_Y;
        }

        public boolean P_1922_E() {
            return this.P_1922_E;
        }

        public int u_1723_Y() {
            return this.J_1907_R;
        }

        public boolean v_4262_N() {
            return this.u_1723_Y;
        }

        public MaterialColor G_564_y(BlockGetter worldIn, c_1514_x pos) {
            return this.w_1484_f;
        }

        public K_4074_S n_1700_B(W_2163_m rot) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), rot);
        }

        public K_4074_S n_1700_B(q_4099_E mirrorIn) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), mirrorIn);
        }

        public O_2369_F w_1484_f() {
            return this.J_1907_R().n_1700_B(this.M_182_A());
        }

        public boolean P_1922_E(BlockGetter reader, c_1514_x pos) {
            return this.M_182_A.test(this.M_182_A(), reader, pos);
        }

        public float u_1723_Y(BlockGetter reader, c_1514_x pos) {
            return this.J_1907_R().P_1922_E(this.M_182_A(), reader, pos);
        }

        public boolean v_4262_N(BlockGetter reader, c_1514_x pos) {
            return this.M_588_G.test(this.M_182_A(), reader, pos);
        }

        public boolean t_148_a() {
            return this.J_1907_R().R_4764_Y(this.M_182_A());
        }

        public int J_1907_R(BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), blockAccess, pos, side);
        }

        public boolean s_956_w() {
            return this.J_1907_R().u_1723_Y(this.M_182_A());
        }

        public int n_1700_B(b_4507_u worldIn, c_1514_x pos) {
            return this.J_1907_R().J_1907_R(this.M_182_A(), worldIn, pos);
        }

        public float w_1484_f(BlockGetter worldIn, c_1514_x pos) {
            return this.t_148_a;
        }

        public float n_1700_B(a_3913_L player, BlockGetter worldIn, c_1514_x pos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), player, worldIn, pos);
        }

        public int R_4764_Y(BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
            return this.J_1907_R().J_1907_R(this.M_182_A(), blockAccess, pos, side);
        }

        public w_1454_v u_2550_I() {
            return this.J_1907_R().G_564_y(this.M_182_A());
        }

        public boolean t_148_a(BlockGetter worldIn, c_1514_x pos) {
            if (this.n_1700_B != null) {
                return this.n_1700_B.n_1700_B;
            }
            K_4074_S blockstate = this.M_182_A();
            return blockstate.M_588_G() ? T_2915_h.n_1700_B(blockstate.R_4764_Y(worldIn, pos)) : false;
        }

        public boolean M_588_G() {
            return this.u_2550_I;
        }

        public boolean n_1700_B(K_4074_S state, b_257_Y face) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), state, face);
        }

        public s_1395_c s_956_w(BlockGetter worldIn, c_1514_x pos) {
            return this.n_1700_B(worldIn, pos, CollisionContext.J_1907_R());
        }

        public s_1395_c n_1700_B(BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, context);
        }

        public s_1395_c J_1907_R(BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
            return ClientBootstrap.Y_601_j().J_1907_R().P_4830_p().w_1484_f() && ClientBootstrap.Y_601_j().J_1907_R().P_4830_p().h_1847_R().stream().noneMatch(clazz -> clazz.isInstance(this.J_1907_R())) ? x_268_Y.n_1700_B() : this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, context);
        }

        public s_1395_c u_2550_I(BlockGetter worldIn, c_1514_x pos) {
            return this.n_1700_B != null ? this.n_1700_B.J_1907_R : this.R_4764_Y(worldIn, pos, CollisionContext.J_1907_R());
        }

        public s_1395_c R_4764_Y(BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
            X_1313_W eventBlockCollide = new X_1313_W(pos);
            A_4115_X.n_1700_B(eventBlockCollide);
            if (eventBlockCollide.n_1700_B()) {
                return x_268_Y.n_1700_B();
            }
            return this.J_1907_R().J_1907_R(this.M_182_A(), worldIn, pos, context);
        }

        public s_1395_c M_588_G(BlockGetter worldIn, c_1514_x pos) {
            return this.J_1907_R().J_1907_R(this.M_182_A(), worldIn, pos);
        }

        public s_1395_c G_564_y(BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
            return this.J_1907_R().R_4764_Y(this.M_182_A(), worldIn, pos, context);
        }

        public s_1395_c P_4830_p(BlockGetter reader, c_1514_x pos) {
            return this.J_1907_R().R_4764_Y(this.M_182_A(), reader, pos);
        }

        public final boolean n_1700_B(BlockGetter reader, c_1514_x pos, N_4263_v entity) {
            return this.n_1700_B(reader, pos, entity, b_257_Y.J_1907_R);
        }

        public final boolean n_1700_B(BlockGetter reader, c_1514_x pos, N_4263_v entityIn, b_257_Y direction) {
            return T_2915_h.n_1700_B(this.R_4764_Y(reader, pos, CollisionContext.n_1700_B(entityIn)), direction);
        }

        public e_2866_D h_1847_R(BlockGetter access, c_1514_x pos) {
            G_564_y abstractblock$offsettype = this.J_1907_R().R_4764_Y();
            if (abstractblock$offsettype == lightning.product.q_4293_E$G_564_y.n_1700_B) {
                return e_2866_D.n_1700_B;
            }
            long i = u_530_F.R_4764_Y(pos.getX(), 0, pos.getZ());
            return new e_2866_D(((double)((float)(i & 0xFL) / 15.0f) - 0.5) * 0.5, abstractblock$offsettype == lightning.product.q_4293_E$G_564_y.R_4764_Y ? ((double)((float)(i >> 4 & 0xFL) / 15.0f) - 1.0) * 0.2 : 0.0, ((double)((float)(i >> 8 & 0xFL) / 15.0f) - 0.5) * 0.5);
        }

        public boolean n_1700_B(b_4507_u world, c_1514_x pos, int id, int param) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), world, pos, id, param);
        }

        public void n_1700_B(b_4507_u worldIn, c_1514_x posIn, T_2915_h blockIn, c_1514_x fromPosIn, boolean isMoving) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, posIn, blockIn, fromPosIn, isMoving);
        }

        public final void n_1700_B(LevelAccessor world, c_1514_x pos, int flag) {
            this.n_1700_B(world, pos, flag, 512);
        }

        public final void n_1700_B(LevelAccessor world, c_1514_x pos, int flag, int recursionLeft) {
            this.J_1907_R();
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (b_257_Y direction : n_1700_B) {
                blockpos$mutable.n_1700_B(pos, direction);
                K_4074_S blockstate = world.getBlockState(blockpos$mutable);
                K_4074_S blockstate1 = blockstate.n_1700_B(direction.u_1723_Y(), this.M_182_A(), world, (c_1514_x)blockpos$mutable, pos);
                T_2915_h.n_1700_B(blockstate, blockstate1, world, blockpos$mutable, flag, recursionLeft);
            }
        }

        public final void J_1907_R(LevelAccessor worldIn, c_1514_x pos, int flags) {
            this.J_1907_R(worldIn, pos, flags, 512);
        }

        public void J_1907_R(LevelAccessor world, c_1514_x pos, int flags, int recursionLeft) {
            this.J_1907_R().n_1700_B(this.M_182_A(), world, pos, flags, recursionLeft);
        }

        public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, oldState, isMoving);
        }

        public void J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
            this.J_1907_R().J_1907_R(this.M_182_A(), worldIn, pos, newState, isMoving);
        }

        public void n_1700_B(e_3591_l worldIn, c_1514_x posIn, Random randomIn) {
            this.J_1907_R().J_1907_R(this.M_182_A(), worldIn, posIn, randomIn);
        }

        public void J_1907_R(e_3591_l worldIn, c_1514_x posIn, Random randomIn) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, posIn, randomIn);
        }

        public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, entityIn);
        }

        public void n_1700_B(e_3591_l worldIn, c_1514_x pos, Z_1993_T stack) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, stack);
        }

        public List<Z_1993_T> n_1700_B(q_1704_m.n_1700_B builder) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), builder);
        }

        public m_3054_I n_1700_B(b_4507_u worldIn, a_3913_L player, x_1688_C handIn, BlockHitResult resultIn) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, resultIn.n_1700_B(), player, handIn, resultIn);
        }

        public void n_1700_B(b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
            this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, player);
        }

        public boolean Q_4569_t(BlockGetter blockReaderIn, c_1514_x blockPosIn) {
            return this.P_4830_p.test(this.M_182_A(), blockReaderIn, blockPosIn);
        }

        public boolean M_182_A(BlockGetter worldIn, c_1514_x pos) {
            h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.C_2741_M);
            A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return false;
            }
            return this.h_1847_R.test(this.M_182_A(), worldIn, pos);
        }

        public K_4074_S n_1700_B(b_257_Y face, K_4074_S queried, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x offsetPos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), face, queried, worldIn, currentPos, offsetPos);
        }

        public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos, type);
        }

        public boolean n_1700_B(BlockPlaceContext useContext) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), useContext);
        }

        public boolean n_1700_B(Fluid fluidIn) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), fluidIn);
        }

        public boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos);
        }

        public boolean t_1786_h(BlockGetter worldIn, c_1514_x pos) {
            return this.Q_4569_t.test(this.M_182_A(), worldIn, pos);
        }

        @Nullable
        public t_3286_u J_1907_R(b_4507_u worldIn, c_1514_x pos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), worldIn, pos);
        }

        @Override
        public boolean n_1700_B(r_109_r<T_2915_h> tag) {
            return this.J_1907_R().n_1700_B(tag);
        }

        @Override
        public boolean n_1700_B(r_109_r<T_2915_h> tag, Predicate<lightning.product.q_4293_E$n_1700_B> predicate) {
            return this.J_1907_R().n_1700_B(tag) && predicate.test(this);
        }

        public boolean n_1700_B(T_2915_h tagIn) {
            return this.J_1907_R().J_1907_R(tagIn);
        }

        public FluidState P_4830_p() {
            return this.J_1907_R().P_1922_E(this.M_182_A());
        }

        public boolean h_1847_R() {
            return this.J_1907_R().a_(this.M_182_A());
        }

        public long n_1700_B(c_1514_x pos) {
            return this.J_1907_R().n_1700_B(this.M_182_A(), pos);
        }

        public SoundType Q_4569_t() {
            return this.J_1907_R().M_588_G(this.M_182_A());
        }

        public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
            this.J_1907_R().n_1700_B(worldIn, state, hit, projectile);
        }

        public boolean G_564_y(BlockGetter blockReaderIn, c_1514_x blockPosIn, b_257_Y directionIn) {
            return this.n_1700_B(blockReaderIn, blockPosIn, directionIn, F_4094_N.n_1700_B);
        }

        public boolean n_1700_B(BlockGetter blockReader, c_1514_x pos, b_257_Y direction, F_4094_N blockVoxelShape) {
            return this.n_1700_B != null ? this.n_1700_B.n_1700_B(direction, blockVoxelShape) : blockVoxelShape.n_1700_B(this.M_182_A(), blockReader, pos, direction);
        }

        public boolean multiplayerClientSuggestionProvider(BlockGetter reader, c_1514_x pos) {
            return this.n_1700_B != null ? this.n_1700_B.G_564_y : T_2915_h.n_1700_B(this.u_2550_I(reader, pos));
        }

        protected abstract K_4074_S M_182_A();

        public boolean t_1786_h() {
            return this.s_956_w;
        }

        static final class n_1700_B {
            private static final b_257_Y[] P_1922_E = b_257_Y.values();
            private static final int u_1723_Y = F_4094_N.values().length;
            protected final boolean n_1700_B;
            private final boolean v_4262_N;
            private final int w_1484_f;
            @Nullable
            private final s_1395_c[] t_148_a;
            protected final s_1395_c J_1907_R;
            protected final boolean R_4764_Y;
            private final boolean[] s_956_w;
            protected final boolean G_564_y;

            private n_1700_B(K_4074_S stateIn) {
                T_2915_h block = stateIn.J_1907_R();
                this.n_1700_B = stateIn.t_148_a(G_4961_S.n_1700_B, c_1514_x.ZERO);
                this.v_4262_N = block.a_(stateIn, G_4961_S.n_1700_B, c_1514_x.ZERO);
                this.w_1484_f = block.G_564_y(stateIn, G_4961_S.n_1700_B, c_1514_x.ZERO);
                if (!stateIn.M_588_G()) {
                    this.t_148_a = null;
                } else {
                    this.t_148_a = new s_1395_c[P_1922_E.length];
                    s_1395_c voxelshape = block.n_1700_B(stateIn, G_4961_S.n_1700_B, c_1514_x.ZERO);
                    b_257_Y[] b_257_YArray = P_1922_E;
                    int n = b_257_YArray.length;
                    for (int i = 0; i < n; ++i) {
                        b_257_Y direction = b_257_YArray[i];
                        this.t_148_a[direction.ordinal()] = x_268_Y.n_1700_B(voxelshape, direction);
                    }
                }
                this.J_1907_R = block.J_1907_R(stateIn, (BlockGetter)G_4961_S.n_1700_B, c_1514_x.ZERO, CollisionContext.J_1907_R());
                this.R_4764_Y = Arrays.stream(b_257_Y.n_1700_B.values()).anyMatch(axis -> this.J_1907_R.J_1907_R((b_257_Y.n_1700_B)axis) < 0.0 || this.J_1907_R.R_4764_Y((b_257_Y.n_1700_B)axis) > 1.0);
                this.s_956_w = new boolean[P_1922_E.length * u_1723_Y];
                for (b_257_Y direction1 : P_1922_E) {
                    for (F_4094_N blockvoxelshape : F_4094_N.values()) {
                        this.s_956_w[lightning.product.q_4293_E$n_1700_B$n_1700_B.J_1907_R((b_257_Y)direction1, (F_4094_N)blockvoxelshape)] = blockvoxelshape.n_1700_B(stateIn, G_4961_S.n_1700_B, c_1514_x.ZERO, direction1);
                    }
                }
                this.G_564_y = T_2915_h.n_1700_B(stateIn.u_2550_I(G_4961_S.n_1700_B, c_1514_x.ZERO));
            }

            public boolean n_1700_B(b_257_Y direction, F_4094_N blockVoxelShape) {
                return this.s_956_w[lightning.product.q_4293_E$n_1700_B$n_1700_B.J_1907_R(direction, blockVoxelShape)];
            }

            private static int J_1907_R(b_257_Y direction, F_4094_N blockVoxelShape) {
                return direction.ordinal() * u_1723_Y + blockVoxelShape.ordinal();
            }
        }
    }
}



