/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  it.unimi.dsi.fastutil.objects.ObjectSet
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lightning.product.BlockStateProperties;
import lightning.product.D_3746_J;
import lightning.product.H_1748_a;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.M_4466_T;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.p_1429_o;
import lightning.product.LevelAccessor;
import lightning.product.StemGrownBlock;
import lightning.product.t_693_s;
import lightning.product.v_3445_Z;
import lightning.product.z_1196_U;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class r_3634_h {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final r_3634_h n_1700_B = new r_3634_h();
    private static final z_1196_U[] R_4764_Y = z_1196_U.values();
    private final EnumSet<z_1196_U> G_564_y = EnumSet.noneOf(z_1196_U.class);
    private final int[][] P_1922_E = new int[16][];
    private static final Map<T_2915_h, J_1907_R> u_1723_Y = new IdentityHashMap<T_2915_h, J_1907_R>();
    private static final Set<J_1907_R> v_4262_N = Sets.newHashSet();

    private r_3634_h() {
    }

    public r_3634_h(U_2912_j p_i47714_1_) {
        this();
        if (p_i47714_1_.R_4764_Y("Indices", 10)) {
            U_2912_j compoundnbt = p_i47714_1_.M_182_A("Indices");
            for (int i = 0; i < this.P_1922_E.length; ++i) {
                String s = String.valueOf(i);
                if (!compoundnbt.R_4764_Y(s, 11)) continue;
                this.P_1922_E[i] = compoundnbt.h_1847_R(s);
            }
        }
        int j = p_i47714_1_.w_1484_f("Sides");
        for (z_1196_U direction8 : z_1196_U.values()) {
            if ((j & 1 << direction8.ordinal()) == 0) continue;
            this.G_564_y.add(direction8);
        }
    }

    public void n_1700_B(H_1748_a chunkIn) {
        this.J_1907_R(chunkIn);
        for (z_1196_U direction8 : R_4764_Y) {
            r_3634_h.n_1700_B(chunkIn, direction8);
        }
        b_4507_u world = chunkIn.getWorld();
        v_4262_N.forEach(p_208829_1_ -> p_208829_1_.n_1700_B(world));
    }

    private static void n_1700_B(H_1748_a p_196991_0_, z_1196_U p_196991_1_) {
        b_4507_u world = p_196991_0_.getWorld();
        if (p_196991_0_.getUpgradeData().G_564_y.remove((Object)p_196991_1_)) {
            Set<b_257_Y> set = p_196991_1_.n_1700_B();
            boolean i = false;
            int j = 15;
            boolean flag = set.contains(b_257_Y.u_1723_Y);
            boolean flag1 = set.contains(b_257_Y.P_1922_E);
            boolean flag2 = set.contains(b_257_Y.G_564_y);
            boolean flag3 = set.contains(b_257_Y.R_4764_Y);
            boolean flag4 = set.size() == 1;
            Y_1387_d chunkpos = p_196991_0_.getPos();
            int k = chunkpos.J_1907_R() + (!flag4 || !flag3 && !flag2 ? (flag1 ? 0 : 15) : 1);
            int l = chunkpos.J_1907_R() + (!flag4 || !flag3 && !flag2 ? (flag1 ? 0 : 15) : 14);
            int i1 = chunkpos.R_4764_Y() + (!flag4 || !flag && !flag1 ? (flag3 ? 0 : 15) : 1);
            int j1 = chunkpos.R_4764_Y() + (!flag4 || !flag && !flag1 ? (flag3 ? 0 : 15) : 14);
            b_257_Y[] adirection = b_257_Y.values();
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(k, 0, i1, l, world.c_3005_b() - 1, j1)) {
                K_4074_S blockstate;
                K_4074_S blockstate1 = blockstate = world.getBlockState(blockpos);
                for (b_257_Y direction : adirection) {
                    blockpos$mutable.n_1700_B(blockpos, direction);
                    blockstate1 = r_3634_h.n_1700_B(blockstate1, direction, world, blockpos, blockpos$mutable);
                }
                T_2915_h.n_1700_B(blockstate, blockstate1, world, blockpos, 18);
            }
        }
    }

    private static K_4074_S n_1700_B(K_4074_S p_196987_0_, b_257_Y p_196987_1_, LevelAccessor p_196987_2_, c_1514_x p_196987_3_, c_1514_x p_196987_4_) {
        return u_1723_Y.getOrDefault(p_196987_0_.J_1907_R(), lightning.product.r_3634_h$n_1700_B.J_1907_R).n_1700_B(p_196987_0_, p_196987_1_, p_196987_2_.getBlockState(p_196987_4_), p_196987_2_, p_196987_3_, p_196987_4_);
    }

    private void J_1907_R(H_1748_a p_196989_1_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
        Y_1387_d chunkpos = p_196989_1_.getPos();
        b_4507_u iworld = p_196989_1_.getWorld();
        for (int i = 0; i < 16; ++i) {
            P_3550_Z chunksection = p_196989_1_.getSections()[i];
            int[] aint = this.P_1922_E[i];
            this.P_1922_E[i] = null;
            if (chunksection == null || aint == null || aint.length <= 0) continue;
            b_257_Y[] adirection = b_257_Y.values();
            M_4466_T<K_4074_S> palettedcontainer = chunksection.t_148_a();
            for (int j : aint) {
                K_4074_S blockstate;
                int k = j & 0xF;
                int l = j >> 8 & 0xF;
                int i1 = j >> 4 & 0xF;
                blockpos$mutable.n_1700_B(chunkpos.J_1907_R() + k, (i << 4) + l, chunkpos.R_4764_Y() + i1);
                K_4074_S blockstate1 = blockstate = palettedcontainer.n_1700_B(j);
                for (b_257_Y direction : adirection) {
                    blockpos$mutable1.n_1700_B(blockpos$mutable, direction);
                    if (blockpos$mutable.getX() >> 4 != chunkpos.J_1907_R || blockpos$mutable.getZ() >> 4 != chunkpos.R_4764_Y) continue;
                    blockstate1 = r_3634_h.n_1700_B(blockstate1, direction, iworld, blockpos$mutable, blockpos$mutable1);
                }
                T_2915_h.n_1700_B(blockstate, blockstate1, iworld, blockpos$mutable, 18);
            }
        }
        for (int j1 = 0; j1 < this.P_1922_E.length; ++j1) {
            if (this.P_1922_E[j1] != null) {
                J_1907_R.warn("Discarding update data for section {} for chunk ({} {})", (Object)j1, (Object)chunkpos.J_1907_R, (Object)chunkpos.R_4764_Y);
            }
            this.P_1922_E[j1] = null;
        }
    }

    public boolean n_1700_B() {
        for (int[] aint : this.P_1922_E) {
            if (aint == null) continue;
            return false;
        }
        return this.G_564_y.isEmpty();
    }

    public U_2912_j J_1907_R() {
        U_2912_j compoundnbt = new U_2912_j();
        U_2912_j compoundnbt1 = new U_2912_j();
        for (int i = 0; i < this.P_1922_E.length; ++i) {
            String s = String.valueOf(i);
            if (this.P_1922_E[i] == null || this.P_1922_E[i].length == 0) continue;
            compoundnbt1.n_1700_B(s, this.P_1922_E[i]);
        }
        if (!compoundnbt1.u_1723_Y()) {
            compoundnbt.n_1700_B("Indices", compoundnbt1);
        }
        int j = 0;
        for (z_1196_U direction8 : this.G_564_y) {
            j |= 1 << direction8.ordinal();
        }
        compoundnbt.n_1700_B("Sides", (byte)j);
        return compoundnbt;
    }

    static abstract sealed class n_1700_B
    extends Enum<n_1700_B>
    implements J_1907_R {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(new T_2915_h[]{a_3742_W.RegionExploit, a_3742_W.M_766_z, a_3742_W.CavityFinder, a_3742_W.e_87_p, a_3742_W.K_2336_H, a_3742_W.n_421_x, a_3742_W.p_1976_q, a_3742_W.A_4252_m, a_3742_W.a_794_m, a_3742_W.E_170_p, a_3742_W.m_229_F, a_3742_W.f_4340_D, a_3742_W.A_2204_Z, a_3742_W.R_4912_F, a_3742_W.S_315_z, a_3742_W.o_977_F, a_3742_W.S_1165_y, a_3742_W.E_738_L, a_3742_W.c_1608_O, a_3742_W.ModeSetting, a_3742_W.MultiBooleanSetting, a_3742_W.F_391_H, a_3742_W.t_4043_B, a_3742_W.A_4115_X, a_3742_W.Y_1740_V, a_3742_W.X_4895_T, a_3742_W.L_103_L, a_3742_W.n_3197_X, a_3742_W.P_2947_S, a_3742_W.O_4761_U, a_3742_W.w_2705_t, a_3742_W.k_2302_P, a_3742_W.t_3452_g, a_3742_W.V_118_c, a_3742_W.I_1407_m, a_3742_W.o_2767_H, a_3742_W.d_2545_n}){

            @Override
            public K_4074_S n_1700_B(K_4074_S p_196982_1_, b_257_Y p_196982_2_, K_4074_S p_196982_3_, LevelAccessor p_196982_4_, c_1514_x p_196982_5_, c_1514_x p_196982_6_) {
                return p_196982_1_;
            }
        };
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(new T_2915_h[0]){

            @Override
            public K_4074_S n_1700_B(K_4074_S p_196982_1_, b_257_Y p_196982_2_, K_4074_S p_196982_3_, LevelAccessor p_196982_4_, c_1514_x p_196982_5_, c_1514_x p_196982_6_) {
                return p_196982_1_.n_1700_B(p_196982_2_, p_196982_4_.getBlockState(p_196982_6_), p_196982_4_, p_196982_5_, p_196982_6_);
            }
        };
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(new T_2915_h[]{a_3742_W.L_1362_X, a_3742_W.NumberSetting}){

            @Override
            public K_4074_S n_1700_B(K_4074_S p_196982_1_, b_257_Y p_196982_2_, K_4074_S p_196982_3_, LevelAccessor p_196982_4_, c_1514_x p_196982_5_, c_1514_x p_196982_6_) {
                if (p_196982_3_.n_1700_B(p_196982_1_.J_1907_R()) && p_196982_2_.h_1847_R().G_564_y() && p_196982_1_.R_4764_Y(v_3445_Z.Q_4569_t) == p_1429_o.n_1700_B && p_196982_3_.R_4764_Y(v_3445_Z.Q_4569_t) == p_1429_o.n_1700_B) {
                    b_257_Y direction = p_196982_1_.R_4764_Y(v_3445_Z.h_1847_R);
                    if (p_196982_2_.h_1847_R() != direction.h_1847_R() && direction == p_196982_3_.R_4764_Y(v_3445_Z.h_1847_R)) {
                        p_1429_o chesttype = p_196982_2_ == direction.v_4262_N() ? p_1429_o.J_1907_R : p_1429_o.R_4764_Y;
                        p_196982_4_.n_1700_B(p_196982_6_, (K_4074_S)p_196982_3_.n_1700_B(v_3445_Z.Q_4569_t, chesttype.J_1907_R()), 18);
                        if (direction == b_257_Y.R_4764_Y || direction == b_257_Y.u_1723_Y) {
                            i_2154_H tileentity = p_196982_4_.getTileEntity(p_196982_5_);
                            i_2154_H tileentity1 = p_196982_4_.getTileEntity(p_196982_6_);
                            if (tileentity instanceof t_693_s && tileentity1 instanceof t_693_s) {
                                t_693_s.n_1700_B((t_693_s)tileentity, (t_693_s)tileentity1);
                            }
                        }
                        return (K_4074_S)p_196982_1_.n_1700_B(v_3445_Z.Q_4569_t, chesttype);
                    }
                }
                return p_196982_1_;
            }
        };
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(true, new T_2915_h[]{a_3742_W.RealmsClientConfig, a_3742_W.Ping, a_3742_W.f_4016_n, a_3742_W.p_178_J, a_3742_W.A_1038_p, a_3742_W.i_1637_u}){
            private final ThreadLocal<List<ObjectSet<c_1514_x>>> v_4262_N = ThreadLocal.withInitial(() -> Lists.newArrayListWithCapacity((int)7));

            @Override
            public K_4074_S n_1700_B(K_4074_S p_196982_1_, b_257_Y p_196982_2_, K_4074_S p_196982_3_, LevelAccessor p_196982_4_, c_1514_x p_196982_5_, c_1514_x p_196982_6_) {
                K_4074_S blockstate = p_196982_1_.n_1700_B(p_196982_2_, p_196982_4_.getBlockState(p_196982_6_), p_196982_4_, p_196982_5_, p_196982_6_);
                if (p_196982_1_ != blockstate) {
                    int i = blockstate.R_4764_Y(BlockStateProperties.j_276_v);
                    List<ObjectSet<c_1514_x>> list = this.v_4262_N.get();
                    if (list.isEmpty()) {
                        for (int j = 0; j < 7; ++j) {
                            list.add((ObjectSet<c_1514_x>)new ObjectOpenHashSet());
                        }
                    }
                    list.get(i).add((Object)p_196982_5_.toImmutable());
                }
                return p_196982_1_;
            }

            @Override
            public void n_1700_B(LevelAccessor p_208826_1_) {
                c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
                List<ObjectSet<c_1514_x>> list = this.v_4262_N.get();
                for (int i = 2; i < list.size(); ++i) {
                    int j = i - 1;
                    ObjectSet<c_1514_x> objectset = list.get(j);
                    ObjectSet<c_1514_x> objectset1 = list.get(i);
                    for (c_1514_x blockpos : objectset) {
                        K_4074_S blockstate = p_208826_1_.getBlockState(blockpos);
                        if (blockstate.R_4764_Y(BlockStateProperties.j_276_v) < j) continue;
                        p_208826_1_.n_1700_B(blockpos, (K_4074_S)blockstate.n_1700_B(BlockStateProperties.j_276_v, j), 18);
                        if (i == 7) continue;
                        for (b_257_Y direction : u_1723_Y) {
                            blockpos$mutable.n_1700_B(blockpos, direction);
                            K_4074_S blockstate1 = p_208826_1_.getBlockState(blockpos$mutable);
                            if (!blockstate1.J_1907_R(BlockStateProperties.j_276_v) || blockstate.R_4764_Y(BlockStateProperties.j_276_v) <= i) continue;
                            objectset1.add((Object)blockpos$mutable.toImmutable());
                        }
                    }
                }
                list.clear();
            }
        };
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(new T_2915_h[]{a_3742_W.n_4539_g, a_3742_W.L_1733_J}){

            @Override
            public K_4074_S n_1700_B(K_4074_S p_196982_1_, b_257_Y p_196982_2_, K_4074_S p_196982_3_, LevelAccessor p_196982_4_, c_1514_x p_196982_5_, c_1514_x p_196982_6_) {
                StemGrownBlock stemgrownblock;
                if (p_196982_1_.R_4764_Y(D_3746_J.P_4830_p) == 7 && p_196982_3_.n_1700_B(stemgrownblock = ((D_3746_J)p_196982_1_.J_1907_R()).t_148_a())) {
                    return (K_4074_S)stemgrownblock.t_148_a().multiplayerClientSuggestionProvider().n_1700_B(HorizontalDirectionalBlock.w_612_n, p_196982_2_);
                }
                return p_196982_1_;
            }
        };
        public static final b_257_Y[] u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(T_2915_h ... p_i47847_3_) {
            this(false, p_i47847_3_);
        }

        private n_1700_B(boolean p_i49366_3_, T_2915_h ... p_i49366_4_) {
            for (T_2915_h block : p_i49366_4_) {
                u_1723_Y.put(block, this);
            }
            if (p_i49366_3_) {
                v_4262_N.add(this);
            }
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            v_4262_N = lightning.product.r_3634_h$n_1700_B.n_1700_B();
            u_1723_Y = b_257_Y.values();
        }
    }

    public static interface J_1907_R {
        public K_4074_S n_1700_B(K_4074_S var1, b_257_Y var2, K_4074_S var3, LevelAccessor var4, c_1514_x var5, c_1514_x var6);

        default public void n_1700_B(LevelAccessor p_208826_1_) {
        }
    }
}



