/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.UnmodifiableIterator
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_4114_x;
import lightning.product.D_2364_U;
import lightning.product.BlockGetter;
import lightning.product.G_652_w;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.MinecartSpawner;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.BlockUtil;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.MinecartChest;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.g_2711_h;
import lightning.product.h_256_u;
import lightning.product.MinecartHopper;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.MinecartFurnace;
import lightning.product.n_3832_I;
import lightning.product.BlockTags;
import lightning.product.Items;
import lightning.product.r_1637_F;
import lightning.product.r_4811_B;
import lightning.product.Minecart;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_448_E;
import lightning.product.w_801_N;
import lightning.product.z_2326_J;
import lightning.product.z_3539_x;

public abstract class y_4319_k
extends N_4263_v {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> J_1907_R = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Float> R_4764_Y = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.R_4764_Y);
    private static final h_256_u<Integer> G_564_y = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> P_1922_E = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> u_1723_Y = C_4114_x.n_1700_B(y_4319_k.class, EntityDataSerializers.t_148_a);
    private static final ImmutableMap<I_1170_F, ImmutableList<Integer>> v_4262_N = ImmutableMap.of((Object)((Object)I_1170_F.n_1700_B), (Object)ImmutableList.of((Object)0, (Object)1, (Object)-1), (Object)((Object)I_1170_F.u_1723_Y), (Object)ImmutableList.of((Object)0, (Object)1, (Object)-1), (Object)((Object)I_1170_F.G_564_y), (Object)ImmutableList.of((Object)0, (Object)1));
    private boolean w_1484_f;
    private static final Map<w_801_N, Pair<z_3539_x, z_3539_x>> t_148_a = j_3341_s.n_1700_B(Maps.newEnumMap(w_801_N.class), shapeVectorMap -> {
        z_3539_x vector3i = b_257_Y.P_1922_E.M_182_A();
        z_3539_x vector3i1 = b_257_Y.u_1723_Y.M_182_A();
        z_3539_x vector3i2 = b_257_Y.R_4764_Y.M_182_A();
        z_3539_x vector3i3 = b_257_Y.G_564_y.M_182_A();
        z_3539_x vector3i4 = vector3i.down();
        z_3539_x vector3i5 = vector3i1.down();
        z_3539_x vector3i6 = vector3i2.down();
        z_3539_x vector3i7 = vector3i3.down();
        shapeVectorMap.put(w_801_N.n_1700_B, Pair.of((Object)vector3i2, (Object)vector3i3));
        shapeVectorMap.put(w_801_N.J_1907_R, Pair.of((Object)vector3i, (Object)vector3i1));
        shapeVectorMap.put(w_801_N.R_4764_Y, Pair.of((Object)vector3i4, (Object)vector3i1));
        shapeVectorMap.put(w_801_N.G_564_y, Pair.of((Object)vector3i, (Object)vector3i5));
        shapeVectorMap.put(w_801_N.P_1922_E, Pair.of((Object)vector3i2, (Object)vector3i7));
        shapeVectorMap.put(w_801_N.u_1723_Y, Pair.of((Object)vector3i6, (Object)vector3i3));
        shapeVectorMap.put(w_801_N.v_4262_N, Pair.of((Object)vector3i3, (Object)vector3i1));
        shapeVectorMap.put(w_801_N.w_1484_f, Pair.of((Object)vector3i3, (Object)vector3i));
        shapeVectorMap.put(w_801_N.t_148_a, Pair.of((Object)vector3i2, (Object)vector3i));
        shapeVectorMap.put(w_801_N.s_956_w, Pair.of((Object)vector3i2, (Object)vector3i1));
    });
    private int s_956_w;
    private double u_2550_I;
    private double M_588_G;
    private double P_4830_p;
    private double h_1847_R;
    private double Q_4569_t;
    private double M_182_A;
    private double t_1786_h;
    private double multiplayerClientSuggestionProvider;

    protected y_4319_k(t_5_h<?> type, b_4507_u worldIn) {
        super(type, worldIn);
        this.s_2632_s = true;
    }

    protected y_4319_k(t_5_h<?> type, b_4507_u worldIn, double posX, double posY, double posZ) {
        this(type, worldIn);
        this.J_1907_R(posX, posY, posZ);
        this.v_4262_N(e_2866_D.n_1700_B);
        this.r_715_M = posX;
        this.A_1038_p = posY;
        this.i_1637_u = posZ;
    }

    public static y_4319_k n_1700_B(b_4507_u worldIn, double x, double y, double z, n_1700_B typeIn) {
        if (typeIn == lightning.product.y_4319_k$n_1700_B.J_1907_R) {
            return new MinecartChest(worldIn, x, y, z);
        }
        if (typeIn == lightning.product.y_4319_k$n_1700_B.R_4764_Y) {
            return new MinecartFurnace(worldIn, x, y, z);
        }
        if (typeIn == lightning.product.y_4319_k$n_1700_B.G_564_y) {
            return new r_1637_F(worldIn, x, y, z);
        }
        if (typeIn == lightning.product.y_4319_k$n_1700_B.P_1922_E) {
            return new MinecartSpawner(worldIn, x, y, z);
        }
        if (typeIn == lightning.product.y_4319_k$n_1700_B.u_1723_Y) {
            return new MinecartHopper(worldIn, x, y, z);
        }
        return typeIn == lightning.product.y_4319_k$n_1700_B.v_4262_N ? new z_2326_J(worldIn, x, y, z) : new Minecart(worldIn, x, y, z);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(n_1700_B, 0);
        this.l_4537_E.n_1700_B(J_1907_R, 1);
        this.l_4537_E.n_1700_B(R_4764_Y, Float.valueOf(0.0f));
        this.l_4537_E.n_1700_B(G_564_y, T_2915_h.s_956_w(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider()));
        this.l_4537_E.n_1700_B(P_1922_E, 6);
        this.l_4537_E.n_1700_B(u_1723_Y, false);
    }

    @Override
    public boolean u_1723_Y(N_4263_v entity) {
        return g_1462_f.n_1700_B(this, entity);
    }

    @Override
    public boolean w_728_N() {
        return true;
    }

    @Override
    protected e_2866_D n_1700_B(b_257_Y.n_1700_B axis, BlockUtil.J_1907_R result) {
        return r_4811_B.t_148_a(super.n_1700_B(axis, result));
    }

    @Override
    public double s_1671_u() {
        return 0.0;
    }

    @Override
    public e_2866_D b_(r_4811_B livingEntity) {
        b_257_Y direction = this.d_2545_n();
        if (direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R) {
            return super.b_(livingEntity);
        }
        int[][] aint = G_652_w.n_1700_B(direction);
        c_1514_x blockpos = this.b_2312_j();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        ImmutableList<I_1170_F> immutablelist = livingEntity.x_2635_q();
        for (I_1170_F pose : immutablelist) {
            R_1815_U entitysize = livingEntity.n_1700_B(pose);
            float f = Math.min(entitysize.n_1700_B, 1.0f) / 2.0f;
            UnmodifiableIterator unmodifiableIterator = ((ImmutableList)v_4262_N.get((Object)pose)).iterator();
            while (unmodifiableIterator.hasNext()) {
                int i = (Integer)unmodifiableIterator.next();
                for (int[] aint1 : aint) {
                    e_2866_D vector3d;
                    I_4817_s axisalignedbb;
                    blockpos$mutable.n_1700_B(blockpos.getX() + aint1[0], blockpos.getY() + i, blockpos.getZ() + aint1[1]);
                    double d0 = this.O_508_d.n_1700_B(G_652_w.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable), () -> G_652_w.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable.down()));
                    if (!G_652_w.n_1700_B(d0) || !G_652_w.n_1700_B(this.O_508_d, livingEntity, (axisalignedbb = new I_4817_s(-f, 0.0, -f, f, entitysize.J_1907_R, f)).offset(vector3d = e_2866_D.n_1700_B(blockpos$mutable, d0)))) continue;
                    livingEntity.J_1907_R(pose);
                    return vector3d;
                }
            }
        }
        double d1 = this.i_601_W().maxY;
        blockpos$mutable.n_1700_B((double)blockpos.getX(), d1, (double)blockpos.getZ());
        for (I_1170_F pose1 : immutablelist) {
            double d2 = livingEntity.n_1700_B((I_1170_F)pose1).J_1907_R;
            int j = u_530_F.P_1922_E(d1 - (double)blockpos$mutable.getY() + d2);
            double d3 = G_652_w.n_1700_B(blockpos$mutable, j, pos -> this.O_508_d.getBlockState((c_1514_x)pos).u_2550_I(this.O_508_d, (c_1514_x)pos));
            if (!(d1 + d2 <= d3)) continue;
            livingEntity.J_1907_R(pose1);
            break;
        }
        return super.b_(livingEntity);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (!this.O_508_d.Y_259_p && !this.t_4219_U) {
            boolean flag;
            if (this.n_1700_B(source)) {
                return false;
            }
            this.J_1907_R(-this.u_2550_I());
            this.n_1700_B(10);
            this.RealmsCreateRealmScreen();
            this.n_1700_B(this.w_1484_f() + amount * 10.0f);
            boolean bl = flag = source.u_2550_I() instanceof a_3913_L && ((a_3913_L)source.u_2550_I()).C_415_h.G_564_y;
            if (flag || this.w_1484_f() > 40.0f) {
                this.C_3538_G();
                if (flag && !this.t_3452_g()) {
                    this.Ops();
                } else {
                    this.J_1907_R(source);
                }
            }
            return true;
        }
        return true;
    }

    @Override
    protected float RegionPingResult() {
        K_4074_S blockstate = this.O_508_d.getBlockState(this.b_2312_j());
        return blockstate.n_1700_B(BlockTags.n_3318_d) ? 1.0f : super.RegionPingResult();
    }

    public void J_1907_R(P_11_z source) {
        this.Ops();
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            Z_1993_T itemstack = new Z_1993_T(Items.u_925_K);
            if (this.t_3452_g()) {
                itemstack.n_1700_B(this.k_2302_P());
            }
            this.a_(itemstack);
        }
    }

    @Override
    public void D_4361_a() {
        this.J_1907_R(-this.u_2550_I());
        this.n_1700_B(10);
        this.n_1700_B(this.w_1484_f() + this.w_1484_f() * 10.0f);
    }

    @Override
    public boolean C_290_v() {
        return !this.t_4219_U;
    }

    private static Pair<z_3539_x, z_3539_x> n_1700_B(w_801_N shape) {
        return t_148_a.get(shape);
    }

    @Override
    public b_257_Y d_2545_n() {
        return this.w_1484_f ? this.o_2767_H().u_1723_Y().v_4262_N() : this.o_2767_H().v_4262_N();
    }

    @Override
    public void v_() {
        if (this.t_148_a() > 0) {
            this.n_1700_B(this.t_148_a() - 1);
        }
        if (this.w_1484_f() > 0.0f) {
            this.n_1700_B(this.w_1484_f() - 1.0f);
        }
        if (this.X_2960_b() < -64.0) {
            this.j_1564_a();
        }
        this.J_739_q();
        if (this.O_508_d.Y_259_p) {
            if (this.s_956_w > 0) {
                double d4 = this.O_3598_v() + (this.u_2550_I - this.O_3598_v()) / (double)this.s_956_w;
                double d5 = this.X_2960_b() + (this.M_588_G - this.X_2960_b()) / (double)this.s_956_w;
                double d6 = this.l_2647_k() + (this.P_4830_p - this.l_2647_k()) / (double)this.s_956_w;
                double d1 = u_530_F.u_1723_Y(this.h_1847_R - (double)this.p_178_J);
                this.p_178_J = (float)((double)this.p_178_J + d1 / (double)this.s_956_w);
                this.f_4016_n = (float)((double)this.f_4016_n + (this.Q_4569_t - (double)this.f_4016_n) / (double)this.s_956_w);
                --this.s_956_w;
                this.J_1907_R(d4, d5, d6);
                this.J_1907_R(this.p_178_J, this.f_4016_n);
            } else {
                this.t_4219_U();
                this.J_1907_R(this.p_178_J, this.f_4016_n);
            }
        } else {
            double d3;
            c_1514_x blockpos;
            K_4074_S blockstate;
            int k;
            int j;
            int i;
            if (!this.u_744_e()) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04, 0.0));
            }
            if (this.O_508_d.getBlockState(new c_1514_x(i = u_530_F.R_4764_Y(this.O_3598_v()), (j = u_530_F.R_4764_Y(this.X_2960_b())) - 1, k = u_530_F.R_4764_Y(this.l_2647_k()))).n_1700_B(BlockTags.n_3318_d)) {
                --j;
            }
            if (g_2711_h.v_4262_N(blockstate = this.O_508_d.getBlockState(blockpos = new c_1514_x(i, j, k)))) {
                this.R_4764_Y(blockpos, blockstate);
                if (blockstate.n_1700_B(a_3742_W.H_1475_K)) {
                    this.n_1700_B(i, j, k, blockstate.R_4764_Y(v_448_E.M_182_A));
                }
            } else {
                this.u_1723_Y();
            }
            this.F_2624_D();
            this.f_4016_n = 0.0f;
            double d0 = this.r_715_M - this.O_3598_v();
            double d2 = this.i_1637_u - this.l_2647_k();
            if (d0 * d0 + d2 * d2 > 0.001) {
                this.p_178_J = (float)(u_530_F.G_564_y(d2, d0) * 180.0 / Math.PI);
                if (this.w_1484_f) {
                    this.p_178_J += 180.0f;
                }
            }
            if ((d3 = (double)u_530_F.v_4262_N(this.p_178_J - this.j_276_v)) < -170.0 || d3 >= 170.0) {
                this.p_178_J += 180.0f;
                this.w_1484_f = !this.w_1484_f;
            }
            this.J_1907_R(this.p_178_J, this.f_4016_n);
            if (this.h_1847_R() == lightning.product.y_4319_k$n_1700_B.n_1700_B && y_4319_k.R_4764_Y(this.I_4348_c()) > 0.01) {
                List<N_4263_v> list = this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W().grow(0.2f, 0.0, 0.2f), I_408_V.n_1700_B(this));
                if (!list.isEmpty()) {
                    for (int l = 0; l < list.size(); ++l) {
                        N_4263_v entity1 = list.get(l);
                        if (!(entity1 instanceof a_3913_L || entity1 instanceof D_2364_U || entity1 instanceof y_4319_k || this.H_1883_T() || entity1.y_2772_m())) {
                            entity1.s_956_w(this);
                            continue;
                        }
                        entity1.P_1922_E(this);
                    }
                }
            } else {
                for (N_4263_v entity : this.O_508_d.n_1700_B((N_4263_v)this, this.i_601_W().grow(0.2f, 0.0, 0.2f))) {
                    if (this.Y_601_j(entity) || !entity.w_728_N() || !(entity instanceof y_4319_k)) continue;
                    entity.P_1922_E(this);
                }
            }
            this.RealmsScreenWithCallback();
            if (this.W_3464_O()) {
                this.dtoRealmsServerAddress();
                this.U_1241_n *= 0.5f;
            }
            this.S_4022_R = false;
        }
    }

    protected double P_1922_E() {
        return 0.4;
    }

    public void n_1700_B(int x, int y, int z, boolean receivingPower) {
    }

    protected void u_1723_Y() {
        double d0 = this.P_1922_E();
        e_2866_D vector3d = this.I_4348_c();
        this.h_1847_R(u_530_F.n_1700_B(vector3d.J_1907_R, -d0, d0), vector3d.R_4764_Y, u_530_F.n_1700_B(vector3d.G_564_y, -d0, d0));
        if (this.e_1992_r) {
            this.v_4262_N(this.I_4348_c().n_1700_B(0.5));
        }
        this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
        if (!this.e_1992_r) {
            this.v_4262_N(this.I_4348_c().n_1700_B(0.95));
        }
    }

    protected void R_4764_Y(c_1514_x pos, K_4074_S state) {
        double d14;
        N_4263_v entity;
        this.U_1241_n = 0.0f;
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        e_2866_D vector3d = this.M_182_A(d0, d1, d2);
        d1 = pos.getY();
        boolean flag = false;
        boolean flag1 = false;
        g_2711_h abstractrailblock = (g_2711_h)state.J_1907_R();
        if (abstractrailblock == a_3742_W.l_4537_E) {
            flag = state.R_4764_Y(v_448_E.M_182_A);
            flag1 = !flag;
        }
        double d3 = 0.0078125;
        e_2866_D vector3d1 = this.I_4348_c();
        w_801_N railshape = state.R_4764_Y(abstractrailblock.t_148_a());
        switch (railshape) {
            case R_4764_Y: {
                this.v_4262_N(vector3d1.J_1907_R(-0.0078125, 0.0, 0.0));
                d1 += 1.0;
                break;
            }
            case G_564_y: {
                this.v_4262_N(vector3d1.J_1907_R(0.0078125, 0.0, 0.0));
                d1 += 1.0;
                break;
            }
            case P_1922_E: {
                this.v_4262_N(vector3d1.J_1907_R(0.0, 0.0, 0.0078125));
                d1 += 1.0;
                break;
            }
            case u_1723_Y: {
                this.v_4262_N(vector3d1.J_1907_R(0.0, 0.0, -0.0078125));
                d1 += 1.0;
            }
        }
        vector3d1 = this.I_4348_c();
        Pair<z_3539_x, z_3539_x> pair = y_4319_k.n_1700_B(railshape);
        z_3539_x vector3i = (z_3539_x)pair.getFirst();
        z_3539_x vector3i1 = (z_3539_x)pair.getSecond();
        double d4 = vector3i1.getX() - vector3i.getX();
        double d5 = vector3i1.getZ() - vector3i.getZ();
        double d6 = Math.sqrt(d4 * d4 + d5 * d5);
        double d7 = vector3d1.J_1907_R * d4 + vector3d1.G_564_y * d5;
        if (d7 < 0.0) {
            d4 = -d4;
            d5 = -d5;
        }
        double d8 = Math.min(2.0, Math.sqrt(y_4319_k.R_4764_Y(vector3d1)));
        vector3d1 = new e_2866_D(d8 * d4 / d6, vector3d1.R_4764_Y, d8 * d5 / d6);
        this.v_4262_N(vector3d1);
        N_4263_v n_4263_v = entity = this.o_3599_Z().isEmpty() ? null : this.o_3599_Z().get(0);
        if (entity instanceof a_3913_L) {
            e_2866_D vector3d2 = entity.I_4348_c();
            double d9 = y_4319_k.R_4764_Y(vector3d2);
            double d11 = y_4319_k.R_4764_Y(this.I_4348_c());
            if (d9 > 1.0E-4 && d11 < 0.01) {
                this.v_4262_N(this.I_4348_c().J_1907_R(vector3d2.J_1907_R * 0.1, 0.0, vector3d2.G_564_y * 0.1));
                flag1 = false;
            }
        }
        if (flag1) {
            double d22 = Math.sqrt(y_4319_k.R_4764_Y(this.I_4348_c()));
            if (d22 < 0.03) {
                this.v_4262_N(e_2866_D.n_1700_B);
            } else {
                this.v_4262_N(this.I_4348_c().G_564_y(0.5, 0.0, 0.5));
            }
        }
        double d23 = (double)pos.getX() + 0.5 + (double)vector3i.getX() * 0.5;
        double d10 = (double)pos.getZ() + 0.5 + (double)vector3i.getZ() * 0.5;
        double d12 = (double)pos.getX() + 0.5 + (double)vector3i1.getX() * 0.5;
        double d13 = (double)pos.getZ() + 0.5 + (double)vector3i1.getZ() * 0.5;
        d4 = d12 - d23;
        d5 = d13 - d10;
        if (d4 == 0.0) {
            d14 = d2 - (double)pos.getZ();
        } else if (d5 == 0.0) {
            d14 = d0 - (double)pos.getX();
        } else {
            double d15 = d0 - d23;
            double d16 = d2 - d10;
            d14 = (d15 * d4 + d16 * d5) * 2.0;
        }
        d0 = d23 + d4 * d14;
        d2 = d10 + d5 * d14;
        this.J_1907_R(d0, d1, d2);
        double d24 = this.H_1883_T() ? 0.75 : 1.0;
        double d25 = this.P_1922_E();
        vector3d1 = this.I_4348_c();
        this.n_1700_B(L_461_d.n_1700_B, new e_2866_D(u_530_F.n_1700_B(d24 * vector3d1.J_1907_R, -d25, d25), 0.0, u_530_F.n_1700_B(d24 * vector3d1.G_564_y, -d25, d25)));
        if (vector3i.getY() != 0 && u_530_F.R_4764_Y(this.O_3598_v()) - pos.getX() == vector3i.getX() && u_530_F.R_4764_Y(this.l_2647_k()) - pos.getZ() == vector3i.getZ()) {
            this.J_1907_R(this.O_3598_v(), this.X_2960_b() + (double)vector3i.getY(), this.l_2647_k());
        } else if (vector3i1.getY() != 0 && u_530_F.R_4764_Y(this.O_3598_v()) - pos.getX() == vector3i1.getX() && u_530_F.R_4764_Y(this.l_2647_k()) - pos.getZ() == vector3i1.getZ()) {
            this.J_1907_R(this.O_3598_v(), this.X_2960_b() + (double)vector3i1.getY(), this.l_2647_k());
        }
        this.v_4262_N();
        e_2866_D vector3d3 = this.M_182_A(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        if (vector3d3 != null && vector3d != null) {
            double d17 = (vector3d.R_4764_Y - vector3d3.R_4764_Y) * 0.05;
            e_2866_D vector3d4 = this.I_4348_c();
            double d18 = Math.sqrt(y_4319_k.R_4764_Y(vector3d4));
            if (d18 > 0.0) {
                this.v_4262_N(vector3d4.G_564_y((d18 + d17) / d18, 1.0, (d18 + d17) / d18));
            }
            this.J_1907_R(this.O_3598_v(), vector3d3.R_4764_Y, this.l_2647_k());
        }
        int j = u_530_F.R_4764_Y(this.O_3598_v());
        int i = u_530_F.R_4764_Y(this.l_2647_k());
        if (j != pos.getX() || i != pos.getZ()) {
            e_2866_D vector3d5 = this.I_4348_c();
            double d26 = Math.sqrt(y_4319_k.R_4764_Y(vector3d5));
            this.h_1847_R(d26 * (double)(j - pos.getX()), vector3d5.R_4764_Y, d26 * (double)(i - pos.getZ()));
        }
        if (flag) {
            e_2866_D vector3d6 = this.I_4348_c();
            double d27 = Math.sqrt(y_4319_k.R_4764_Y(vector3d6));
            if (d27 > 0.01) {
                double d19 = 0.06;
                this.v_4262_N(vector3d6.J_1907_R(vector3d6.J_1907_R / d27 * 0.06, 0.0, vector3d6.G_564_y / d27 * 0.06));
            } else {
                e_2866_D vector3d7 = this.I_4348_c();
                double d20 = vector3d7.J_1907_R;
                double d21 = vector3d7.G_564_y;
                if (railshape == w_801_N.J_1907_R) {
                    if (this.n_1700_B(pos.west())) {
                        d20 = 0.02;
                    } else if (this.n_1700_B(pos.east())) {
                        d20 = -0.02;
                    }
                } else {
                    if (railshape != w_801_N.n_1700_B) {
                        return;
                    }
                    if (this.n_1700_B(pos.north())) {
                        d21 = 0.02;
                    } else if (this.n_1700_B(pos.south())) {
                        d21 = -0.02;
                    }
                }
                this.h_1847_R(d20, vector3d7.R_4764_Y, d21);
            }
        }
    }

    private boolean n_1700_B(c_1514_x pos) {
        return this.O_508_d.getBlockState(pos).v_4262_N(this.O_508_d, pos);
    }

    protected void v_4262_N() {
        double d0 = this.H_1883_T() ? 0.997 : 0.96;
        this.v_4262_N(this.I_4348_c().G_564_y(d0, 0.0, d0));
    }

    @Nullable
    public e_2866_D n_1700_B(double x, double y, double z, double offset) {
        K_4074_S blockstate;
        int k;
        int j;
        int i = u_530_F.R_4764_Y(x);
        if (this.O_508_d.getBlockState(new c_1514_x(i, (j = u_530_F.R_4764_Y(y)) - 1, k = u_530_F.R_4764_Y(z))).n_1700_B(BlockTags.n_3318_d)) {
            --j;
        }
        if (g_2711_h.v_4262_N(blockstate = this.O_508_d.getBlockState(new c_1514_x(i, j, k)))) {
            w_801_N railshape = blockstate.R_4764_Y(((g_2711_h)blockstate.J_1907_R()).t_148_a());
            y = j;
            if (railshape.J_1907_R()) {
                y = j + 1;
            }
            Pair<z_3539_x, z_3539_x> pair = y_4319_k.n_1700_B(railshape);
            z_3539_x vector3i = (z_3539_x)pair.getFirst();
            z_3539_x vector3i1 = (z_3539_x)pair.getSecond();
            double d0 = vector3i1.getX() - vector3i.getX();
            double d1 = vector3i1.getZ() - vector3i.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            if (vector3i.getY() != 0 && u_530_F.R_4764_Y(x += (d0 /= d2) * offset) - i == vector3i.getX() && u_530_F.R_4764_Y(z += (d1 /= d2) * offset) - k == vector3i.getZ()) {
                y += (double)vector3i.getY();
            } else if (vector3i1.getY() != 0 && u_530_F.R_4764_Y(x) - i == vector3i1.getX() && u_530_F.R_4764_Y(z) - k == vector3i1.getZ()) {
                y += (double)vector3i1.getY();
            }
            return this.M_182_A(x, y, z);
        }
        return null;
    }

    @Nullable
    public e_2866_D M_182_A(double x, double y, double z) {
        K_4074_S blockstate;
        int k;
        int j;
        int i = u_530_F.R_4764_Y(x);
        if (this.O_508_d.getBlockState(new c_1514_x(i, (j = u_530_F.R_4764_Y(y)) - 1, k = u_530_F.R_4764_Y(z))).n_1700_B(BlockTags.n_3318_d)) {
            --j;
        }
        if (g_2711_h.v_4262_N(blockstate = this.O_508_d.getBlockState(new c_1514_x(i, j, k)))) {
            double d9;
            w_801_N railshape = blockstate.R_4764_Y(((g_2711_h)blockstate.J_1907_R()).t_148_a());
            Pair<z_3539_x, z_3539_x> pair = y_4319_k.n_1700_B(railshape);
            z_3539_x vector3i = (z_3539_x)pair.getFirst();
            z_3539_x vector3i1 = (z_3539_x)pair.getSecond();
            double d0 = (double)i + 0.5 + (double)vector3i.getX() * 0.5;
            double d1 = (double)j + 0.0625 + (double)vector3i.getY() * 0.5;
            double d2 = (double)k + 0.5 + (double)vector3i.getZ() * 0.5;
            double d3 = (double)i + 0.5 + (double)vector3i1.getX() * 0.5;
            double d4 = (double)j + 0.0625 + (double)vector3i1.getY() * 0.5;
            double d5 = (double)k + 0.5 + (double)vector3i1.getZ() * 0.5;
            double d6 = d3 - d0;
            double d7 = (d4 - d1) * 2.0;
            double d8 = d5 - d2;
            if (d6 == 0.0) {
                d9 = z - (double)k;
            } else if (d8 == 0.0) {
                d9 = x - (double)i;
            } else {
                double d10 = x - d0;
                double d11 = z - d2;
                d9 = (d10 * d6 + d11 * d8) * 2.0;
            }
            x = d0 + d6 * d9;
            y = d1 + d7 * d9;
            z = d2 + d8 * d9;
            if (d7 < 0.0) {
                y += 1.0;
            } else if (d7 > 0.0) {
                y += 0.5;
            }
            return new e_2866_D(x, y, z);
        }
        return null;
    }

    @Override
    public I_4817_s h_2739_B() {
        I_4817_s axisalignedbb = this.i_601_W();
        return this.Y_601_j() ? axisalignedbb.grow((double)Math.abs(this.multiplayerClientSuggestionProvider()) / 16.0) : axisalignedbb;
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        if (compound.t_1786_h("CustomDisplayTile")) {
            this.J_1907_R(n_3832_I.R_4764_Y(compound.M_182_A("DisplayState")));
            this.R_4764_Y(compound.w_1484_f("DisplayOffset"));
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        if (this.Y_601_j()) {
            compound.n_1700_B("CustomDisplayTile", true);
            compound.n_1700_B("DisplayState", n_3832_I.n_1700_B(this.Q_4569_t()));
            compound.J_1907_R("DisplayOffset", this.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void P_1922_E(N_4263_v entityIn) {
        double d1;
        double d0;
        double d2;
        if (!(this.O_508_d.Y_259_p || entityIn.j_1564_a || this.j_1564_a || this.Y_601_j(entityIn) || !((d2 = (d0 = entityIn.O_3598_v() - this.O_3598_v()) * d0 + (d1 = entityIn.l_2647_k() - this.l_2647_k()) * d1) >= (double)1.0E-4f))) {
            d2 = u_530_F.n_1700_B(d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0 / d2;
            if (d3 > 1.0) {
                d3 = 1.0;
            }
            d0 *= d3;
            d1 *= d3;
            d0 *= (double)0.1f;
            d1 *= (double)0.1f;
            d0 *= (double)(1.0f - this.M_1641_O);
            d1 *= (double)(1.0f - this.M_1641_O);
            d0 *= 0.5;
            d1 *= 0.5;
            if (entityIn instanceof y_4319_k) {
                e_2866_D vector3d1;
                double d5;
                double d4 = entityIn.O_3598_v() - this.O_3598_v();
                e_2866_D vector3d = new e_2866_D(d4, 0.0, d5 = entityIn.l_2647_k() - this.l_2647_k()).G_564_y();
                double d6 = Math.abs(vector3d.J_1907_R(vector3d1 = new e_2866_D(u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180)), 0.0, u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180))).G_564_y()));
                if (d6 < (double)0.8f) {
                    return;
                }
                e_2866_D vector3d2 = this.I_4348_c();
                e_2866_D vector3d3 = entityIn.I_4348_c();
                if (((y_4319_k)entityIn).h_1847_R() == lightning.product.y_4319_k$n_1700_B.R_4764_Y && this.h_1847_R() != lightning.product.y_4319_k$n_1700_B.R_4764_Y) {
                    this.v_4262_N(vector3d2.G_564_y(0.2, 1.0, 0.2));
                    this.w_1484_f(vector3d3.J_1907_R - d0, 0.0, vector3d3.G_564_y - d1);
                    entityIn.v_4262_N(vector3d3.G_564_y(0.95, 1.0, 0.95));
                } else if (((y_4319_k)entityIn).h_1847_R() != lightning.product.y_4319_k$n_1700_B.R_4764_Y && this.h_1847_R() == lightning.product.y_4319_k$n_1700_B.R_4764_Y) {
                    entityIn.v_4262_N(vector3d3.G_564_y(0.2, 1.0, 0.2));
                    entityIn.w_1484_f(vector3d2.J_1907_R + d0, 0.0, vector3d2.G_564_y + d1);
                    this.v_4262_N(vector3d2.G_564_y(0.95, 1.0, 0.95));
                } else {
                    double d7 = (vector3d3.J_1907_R + vector3d2.J_1907_R) / 2.0;
                    double d8 = (vector3d3.G_564_y + vector3d2.G_564_y) / 2.0;
                    this.v_4262_N(vector3d2.G_564_y(0.2, 1.0, 0.2));
                    this.w_1484_f(d7 - d0, 0.0, d8 - d1);
                    entityIn.v_4262_N(vector3d3.G_564_y(0.2, 1.0, 0.2));
                    entityIn.w_1484_f(d7 + d0, 0.0, d8 + d1);
                }
            } else {
                this.w_1484_f(-d0, 0.0, -d1);
                entityIn.w_1484_f(d0 / 4.0, 0.0, d1 / 4.0);
            }
        }
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.u_2550_I = x;
        this.M_588_G = y;
        this.P_4830_p = z;
        this.h_1847_R = yaw;
        this.Q_4569_t = pitch;
        this.s_956_w = posRotationIncrements + 2;
        this.h_1847_R(this.M_182_A, this.t_1786_h, this.multiplayerClientSuggestionProvider);
    }

    @Override
    public void s_956_w(double x, double y, double z) {
        this.M_182_A = x;
        this.t_1786_h = y;
        this.multiplayerClientSuggestionProvider = z;
        this.h_1847_R(this.M_182_A, this.t_1786_h, this.multiplayerClientSuggestionProvider);
    }

    public void n_1700_B(float damage) {
        this.l_4537_E.J_1907_R(R_4764_Y, Float.valueOf(damage));
    }

    public float w_1484_f() {
        return this.l_4537_E.n_1700_B(R_4764_Y).floatValue();
    }

    public void n_1700_B(int rollingAmplitude) {
        this.l_4537_E.J_1907_R(n_1700_B, rollingAmplitude);
    }

    public int t_148_a() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void J_1907_R(int rollingDirection) {
        this.l_4537_E.J_1907_R(J_1907_R, rollingDirection);
    }

    public int u_2550_I() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    public abstract n_1700_B h_1847_R();

    public K_4074_S Q_4569_t() {
        return !this.Y_601_j() ? this.M_182_A() : T_2915_h.n_1700_B(this.D_60_a().n_1700_B(G_564_y));
    }

    public K_4074_S M_182_A() {
        return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    public int multiplayerClientSuggestionProvider() {
        return !this.Y_601_j() ? this.w_1457_N() : this.D_60_a().n_1700_B(P_1922_E).intValue();
    }

    public int w_1457_N() {
        return 6;
    }

    public void J_1907_R(K_4074_S displayTile) {
        this.D_60_a().J_1907_R(G_564_y, T_2915_h.s_956_w(displayTile));
        this.n_1700_B(true);
    }

    public void R_4764_Y(int displayTileOffset) {
        this.D_60_a().J_1907_R(P_1922_E, displayTileOffset);
        this.n_1700_B(true);
    }

    public boolean Y_601_j() {
        return this.D_60_a().n_1700_B(u_1723_Y);
    }

    public void n_1700_B(boolean showBlock) {
        this.D_60_a().J_1907_R(u_1723_Y, showBlock);
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.y_4319_k$n_1700_B.n_1700_B();
        }
    }
}


