/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.TagContainer;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_3620_e;
import lightning.product.G_3474_H;
import lightning.product.H_1748_a;
import lightning.product.EmptyTickList;
import lightning.product.I_14_v;
import lightning.product.I_3457_f;
import lightning.product.BiomeColors;
import lightning.product.DimensionSpecialEffects;
import lightning.product.K_4074_S;
import lightning.product.BlockTintCache;
import lightning.product.N_4263_v;
import lightning.product.R_137_M;
import lightning.product.R_2450_T;
import lightning.product.LevelData;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.SimpleSoundInstance;
import lightning.product.T_673_n;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_3163_W;
import lightning.product.W_2853_p;
import lightning.product.SoundEvent;
import lightning.product.ProfilerFiller;
import lightning.product.X_4340_E;
import lightning.product.SoundInstance;
import lightning.product.Z_1993_T;
import lightning.product.Z_3903_F;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.Cursor3D;
import lightning.product.WritableLevelData;
import lightning.product.i_4895_l;
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.BlockTags;
import lightning.product.CrashReportCategory;
import lightning.product.r_4097_j;
import lightning.product.r_4399_U;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.ColorResolver;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;
import lightning.product.z_883_p;
import mods.voicechat.eventforge.WorldEvent;
import net.minecraft.server.G_564_y;
import net.optifine.Config;
import net.optifine.CustomGuis;
import net.optifine.DynamicLights;
import net.optifine.RandomEntities;
import net.optifine.override.PlayerControllerOF;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;

public class k_4690_i
extends b_4507_u {
    private final Int2ObjectMap<N_4263_v> n_1700_B = new Int2ObjectOpenHashMap();
    private final W_2853_p J_1907_R;
    private final z_883_p R_4764_Y;
    private final n_1700_B C_2741_M;
    private final DimensionSpecialEffects k_2293_S;
    private final MinecraftClient q_2307_F = MinecraftClient.A_4115_X();
    private final List<X_4340_E> Z_875_P = Lists.newArrayList();
    private i_4895_l c_3005_b = new i_4895_l();
    private final Map<String, F_3620_e> H_2857_Y = Maps.newHashMap();
    private int A_4115_X;
    private final Object2ObjectArrayMap<ColorResolver, BlockTintCache> Y_1740_V = j_3341_s.n_1700_B(new Object2ObjectArrayMap(3), (T p_lambda$new$0_0_) -> {
        p_lambda$new$0_0_.put((Object)BiomeColors.n_1700_B, (Object)new BlockTintCache());
        p_lambda$new$0_0_.put((Object)BiomeColors.J_1907_R, (Object)new BlockTintCache());
        p_lambda$new$0_0_.put((Object)BiomeColors.R_4764_Y, (Object)new BlockTintCache());
    });
    private final r_4399_U t_4043_B;
    private boolean x_607_J = false;

    public k_4690_i(W_2853_p p_i242067_1_, n_1700_B p_i242067_2_, f_2392_k<b_4507_u> p_i242067_3_, Z_3903_F p_i242067_4_, int p_i242067_5_, Supplier<ProfilerFiller> p_i242067_6_, z_883_p p_i242067_7_, boolean p_i242067_8_, long p_i242067_9_) {
        super(p_i242067_2_, p_i242067_3_, p_i242067_4_, p_i242067_6_, true, p_i242067_8_, p_i242067_9_);
        this.J_1907_R = p_i242067_1_;
        this.t_4043_B = new r_4399_U(this, p_i242067_5_);
        this.C_2741_M = p_i242067_2_;
        this.R_4764_Y = p_i242067_7_;
        this.k_2293_S = DimensionSpecialEffects.n_1700_B(p_i242067_4_);
        this.J_1907_R(new c_1514_x(8, 64, 8), 0.0f);
        this.B_1668_F();
        this.g_164_R();
        if (Reflector.CapabilityProvider_gatherCapabilities.exists()) {
            Reflector.call(this, Reflector.CapabilityProvider_gatherCapabilities, new Object[0]);
        }
        Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, this);
        lightning.product.A_4115_X.n_1700_B(new WorldEvent.Load(this));
        if (this.q_2307_F.w_1457_N != null && this.q_2307_F.w_1457_N.getClass() == V_3163_W.class) {
            this.q_2307_F.w_1457_N = new PlayerControllerOF(this.q_2307_F, this.J_1907_R);
            CustomGuis.setPlayerControllerOF((PlayerControllerOF)this.q_2307_F.w_1457_N);
        }
    }

    public DimensionSpecialEffects n_1700_B() {
        return this.k_2293_S;
    }

    public void n_1700_B(BooleanSupplier hasTimeLeft) {
        this.H_2857_Y().w_1457_N();
        this.Q_2552_b();
        this.D_4792_h().n_1700_B("blocks");
        this.t_4043_B.n_1700_B(hasTimeLeft);
        this.D_4792_h().R_4764_Y();
    }

    private void Q_2552_b() {
        this.n_1700_B(this.Y_601_j.P_1922_E() + 1L);
        if (this.Y_601_j.s_956_w().J_1907_R(A_2352_Z.s_956_w)) {
            this.J_1907_R(this.Y_601_j.u_1723_Y() + 1L);
        }
    }

    public void n_1700_B(long p_239134_1_) {
        this.C_2741_M.n_1700_B(p_239134_1_);
    }

    public void J_1907_R(long time) {
        if (time < 0L) {
            time = -time;
            this.H_1990_U().n_1700_B(A_2352_Z.s_956_w).n_1700_B(false, (G_564_y)null);
        } else {
            this.H_1990_U().n_1700_B(A_2352_Z.s_956_w).n_1700_B(true, (G_564_y)null);
        }
        this.C_2741_M.J_1907_R(time);
    }

    public Iterable<N_4263_v> J_1907_R() {
        return this.n_1700_B.values();
    }

    public void R_4764_Y() {
        ProfilerFiller iprofiler = this.D_4792_h();
        iprofiler.n_1700_B("entities");
        try {
            ObjectIterator objectiterator = this.n_1700_B.int2ObjectEntrySet().iterator();
            while (objectiterator.hasNext()) {
                Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)objectiterator.next();
                N_4263_v entity = (N_4263_v)entry.getValue();
                if (entity.y_2772_m()) continue;
                iprofiler.n_1700_B("tick");
                if (!entity.t_4219_U) {
                    this.n_1700_B(this::n_1700_B, entity);
                }
                iprofiler.R_4764_Y();
                iprofiler.n_1700_B("remove");
                if (entity.t_4219_U) {
                    objectiterator.remove();
                    this.J_1907_R(entity);
                }
                iprofiler.R_4764_Y();
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        this.g_221_o();
        iprofiler.R_4764_Y();
    }

    public void n_1700_B(N_4263_v entityIn) {
        if (!(entityIn instanceof a_3913_L) && !this.v_4262_N().n_1700_B(entityIn)) {
            this.v_4262_N(entityIn);
        } else {
            entityIn.u_1723_Y(entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k());
            entityIn.j_276_v = entityIn.p_178_J;
            entityIn.UploadStatus = entityIn.f_4016_n;
            if (entityIn.y_1700_S || entityIn.d_2461_k()) {
                ++entityIn.RealmsWorldResetDto;
                this.D_4792_h().n_1700_B(() -> V_3137_a.g_221_o.J_1907_R(entityIn.f_4016_n()).toString());
                if (ReflectorForge.canUpdate(entityIn)) {
                    entityIn.v_();
                }
                this.D_4792_h().R_4764_Y();
            }
            this.v_4262_N(entityIn);
            if (entityIn.y_1700_S) {
                for (N_4263_v entity : entityIn.o_3599_Z()) {
                    this.n_1700_B(entityIn, entity);
                }
            }
        }
    }

    public void n_1700_B(N_4263_v p_217420_1_, N_4263_v p_217420_2_) {
        if (!p_217420_2_.t_4219_U && p_217420_2_.l_3609_d() == p_217420_1_) {
            if (p_217420_2_ instanceof a_3913_L || this.v_4262_N().n_1700_B(p_217420_2_)) {
                p_217420_2_.u_1723_Y(p_217420_2_.O_3598_v(), p_217420_2_.X_2960_b(), p_217420_2_.l_2647_k());
                p_217420_2_.j_276_v = p_217420_2_.p_178_J;
                p_217420_2_.UploadStatus = p_217420_2_.f_4016_n;
                if (p_217420_2_.y_1700_S) {
                    ++p_217420_2_.RealmsWorldResetDto;
                    p_217420_2_.x_607_J();
                }
                this.v_4262_N(p_217420_2_);
                if (p_217420_2_.y_1700_S) {
                    for (N_4263_v entity : p_217420_2_.o_3599_Z()) {
                        this.n_1700_B(p_217420_2_, entity);
                    }
                }
            }
        } else {
            p_217420_2_.A_3959_N();
        }
    }

    private void v_4262_N(N_4263_v entityIn) {
        if (entityIn.H_1873_g()) {
            this.D_4792_h().n_1700_B("chunkCheck");
            int i = u_530_F.R_4764_Y(entityIn.O_3598_v() / 16.0);
            int j = u_530_F.R_4764_Y(entityIn.X_2960_b() / 16.0);
            int k = u_530_F.R_4764_Y(entityIn.l_2647_k() / 16.0);
            if (!entityIn.y_1700_S || entityIn.u_744_e != i || entityIn.RetryCallException != j || entityIn.r_3651_U != k) {
                if (entityIn.y_1700_S && this.R_4764_Y(entityIn.u_744_e, entityIn.r_3651_U)) {
                    this.u_1723_Y(entityIn.u_744_e, entityIn.r_3651_U).removeEntityAtIndex(entityIn, entityIn.RetryCallException);
                }
                if (!entityIn.o_1800_r() && !this.R_4764_Y(i, k)) {
                    if (entityIn.y_1700_S) {
                        G_564_y.warn("Entity {} left loaded chunk area", (Object)entityIn);
                    }
                    entityIn.y_1700_S = false;
                } else {
                    this.u_1723_Y(i, k).addEntity(entityIn);
                }
            }
            this.D_4792_h().R_4764_Y();
        }
    }

    public void n_1700_B(H_1748_a chunkIn) {
        Collection collection = Reflector.ForgeWorld_tileEntitiesToBeRemoved.exists() ? (Collection)Reflector.getFieldValue(this, Reflector.ForgeWorld_tileEntitiesToBeRemoved) : this.M_588_G;
        collection.addAll(chunkIn.getTileEntityMap().values());
        this.t_4043_B.G_564_y().n_1700_B(chunkIn.getPos(), false);
    }

    public void n_1700_B(int chunkX, int chunkZ) {
        this.Y_1740_V.forEach((p_lambda$onChunkLoaded$2_2_, p_lambda$onChunkLoaded$2_3_) -> p_lambda$onChunkLoaded$2_3_.n_1700_B(chunkX, chunkZ));
    }

    public void G_564_y() {
        this.Y_1740_V.forEach((p_lambda$clearColorCaches$3_0_, p_lambda$clearColorCaches$3_1_) -> p_lambda$clearColorCaches$3_1_.n_1700_B());
    }

    @Override
    public boolean R_4764_Y(int chunkX, int chunkZ) {
        return true;
    }

    public int P_1922_E() {
        return this.n_1700_B.size();
    }

    public void n_1700_B(int playerId, X_4340_E playerEntityIn) {
        this.J_1907_R(playerId, playerEntityIn);
        this.Z_875_P.add(playerEntityIn);
    }

    public void n_1700_B(int entityIdIn, N_4263_v entityToSpawn) {
        this.J_1907_R(entityIdIn, entityToSpawn);
    }

    private void J_1907_R(int entityIdIn, N_4263_v entityToSpawn) {
        if (!Reflector.EntityJoinWorldEvent_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.EntityJoinWorldEvent_Constructor, entityToSpawn, this)) {
            this.n_1700_B(entityIdIn);
            this.n_1700_B.put(entityIdIn, (Object)entityToSpawn);
            this.v_4262_N().n_1700_B(u_530_F.R_4764_Y(entityToSpawn.O_3598_v() / 16.0), u_530_F.R_4764_Y(entityToSpawn.l_2647_k() / 16.0), ChunkStatus.P_4830_p, true).addEntity(entityToSpawn);
            if (Reflector.IForgeEntity_onAddedToWorld.exists()) {
                Reflector.call(entityToSpawn, Reflector.IForgeEntity_onAddedToWorld, new Object[0]);
            }
            this.R_4764_Y(entityToSpawn);
        }
    }

    public void n_1700_B(int eid) {
        N_4263_v entity = (N_4263_v)this.n_1700_B.remove(eid);
        if (entity != null) {
            entity.Ops();
            this.J_1907_R(entity);
            lightning.product.A_4115_X.n_1700_B(new I_3457_f(entity));
        }
    }

    public void J_1907_R(N_4263_v entityIn) {
        entityIn.Ping();
        if (entityIn.y_1700_S) {
            this.u_1723_Y(entityIn.u_744_e, entityIn.r_3651_U).removeEntity(entityIn);
        }
        this.Z_875_P.remove(entityIn);
        if (Reflector.IForgeEntity_onRemovedFromWorld.exists()) {
            Reflector.call(entityIn, Reflector.IForgeEntity_onRemovedFromWorld, new Object[0]);
        }
        if (Reflector.EntityLeaveWorldEvent_Constructor.exists()) {
            Reflector.postForgeBusEvent(Reflector.EntityLeaveWorldEvent_Constructor, entityIn, this);
        }
        this.G_564_y(entityIn);
    }

    public void J_1907_R(H_1748_a chunkIn) {
        try {
            for (Int2ObjectMap.Entry entry : this.n_1700_B.int2ObjectEntrySet()) {
                N_4263_v entity = (N_4263_v)entry.getValue();
                int i = u_530_F.R_4764_Y(entity.O_3598_v() / 16.0);
                int j = u_530_F.R_4764_Y(entity.l_2647_k() / 16.0);
                if (i != chunkIn.getPos().J_1907_R || j != chunkIn.getPos().R_4764_Y) continue;
                chunkIn.addEntity(entity);
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    @Nullable
    public N_4263_v J_1907_R(int id) {
        return (N_4263_v)this.n_1700_B.get(id);
    }

    public void n_1700_B(c_1514_x pos, K_4074_S state) {
        this.n_1700_B(pos, state, 19);
    }

    @Override
    public void w_1484_f() {
        this.J_1907_R.getNetworkManager().n_1700_B(new F_2904_S("multiplayer.status.quitting"));
    }

    public void n_1700_B(int posX, int posY, int posZ) {
        int i = 32;
        Random random = new Random();
        boolean flag = false;
        if (this.q_2307_F.w_1457_N.getCurrentGameType() == I_14_v.R_4764_Y) {
            for (Z_1993_T itemstack : this.q_2307_F.Y_259_p.f_3449_S()) {
                if (itemstack.J_1907_R() != a_3742_W.N_4890_q.u_1723_Y()) continue;
                flag = true;
                break;
            }
        }
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int j = 0; j < 667; ++j) {
            this.n_1700_B(posX, posY, posZ, 16, random, flag, blockpos$mutable);
            this.n_1700_B(posX, posY, posZ, 32, random, flag, blockpos$mutable);
        }
    }

    public void n_1700_B(int x, int y, int z, int offset, Random random, boolean holdingBarrier, c_1514_x.n_1700_B pos) {
        int i = x + this.w_1457_N.nextInt(offset) - this.w_1457_N.nextInt(offset);
        int j = y + this.w_1457_N.nextInt(offset) - this.w_1457_N.nextInt(offset);
        int k = z + this.w_1457_N.nextInt(offset) - this.w_1457_N.nextInt(offset);
        pos.n_1700_B(i, j, k);
        K_4074_S blockstate = this.getBlockState(pos);
        blockstate.J_1907_R().n_1700_B(blockstate, (b_4507_u)this, (c_1514_x)pos, random);
        FluidState fluidstate = this.getFluidState(pos);
        if (!fluidstate.R_4764_Y()) {
            fluidstate.n_1700_B(this, pos, random);
            ParticleOptions iparticledata = fluidstate.w_1484_f();
            if (iparticledata != null && this.w_1457_N.nextInt(10) == 0) {
                boolean flag = blockstate.G_564_y((BlockGetter)this, (c_1514_x)pos, b_257_Y.n_1700_B);
                z_3539_x blockpos = pos.down();
                this.n_1700_B((c_1514_x)blockpos, this.getBlockState((c_1514_x)blockpos), iparticledata, flag);
            }
        }
        if (holdingBarrier && blockstate.n_1700_B(a_3742_W.N_4890_q)) {
            this.n_1700_B(ParticleTypes.R_4764_Y, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5, 0.0, 0.0, 0.0);
        }
        if (!blockstate.multiplayerClientSuggestionProvider(this, pos)) {
            this.P_1922_E(pos).Q_4569_t().ifPresent(p_lambda$animateTick$4_2_ -> {
                if (p_lambda$animateTick$4_2_.n_1700_B(this.w_1457_N)) {
                    this.n_1700_B(p_lambda$animateTick$4_2_.n_1700_B(), (double)pos.getX() + this.w_1457_N.nextDouble(), (double)pos.getY() + this.w_1457_N.nextDouble(), (double)pos.getZ() + this.w_1457_N.nextDouble(), 0.0, 0.0, 0.0);
                }
            });
        }
    }

    private void n_1700_B(c_1514_x blockPosIn, K_4074_S blockStateIn, ParticleOptions particleDataIn, boolean shapeDownSolid) {
        if (blockStateIn.P_4830_p().R_4764_Y()) {
            s_1395_c voxelshape = blockStateIn.u_2550_I(this, blockPosIn);
            double d0 = voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
            if (d0 < 1.0) {
                if (shapeDownSolid) {
                    this.n_1700_B((double)blockPosIn.getX(), (double)(blockPosIn.getX() + 1), (double)blockPosIn.getZ(), (double)(blockPosIn.getZ() + 1), (double)(blockPosIn.getY() + 1) - 0.05, particleDataIn);
                }
            } else if (!blockStateIn.n_1700_B(BlockTags.H_1990_U)) {
                double d1 = voxelshape.J_1907_R(b_257_Y.n_1700_B.J_1907_R);
                if (d1 > 0.0) {
                    this.n_1700_B(blockPosIn, particleDataIn, voxelshape, (double)blockPosIn.getY() + d1 - 0.05);
                } else {
                    c_1514_x blockpos = blockPosIn.down();
                    K_4074_S blockstate = this.getBlockState(blockpos);
                    s_1395_c voxelshape1 = blockstate.u_2550_I(this, blockpos);
                    double d2 = voxelshape1.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
                    if (d2 < 1.0 && blockstate.P_4830_p().R_4764_Y()) {
                        this.n_1700_B(blockPosIn, particleDataIn, voxelshape, (double)blockPosIn.getY() - 0.05);
                    }
                }
            }
        }
    }

    private void n_1700_B(c_1514_x posIn, ParticleOptions particleDataIn, s_1395_c voxelShapeIn, double y) {
        this.n_1700_B((double)posIn.getX() + voxelShapeIn.J_1907_R(b_257_Y.n_1700_B.n_1700_B), (double)posIn.getX() + voxelShapeIn.R_4764_Y(b_257_Y.n_1700_B.n_1700_B), (double)posIn.getZ() + voxelShapeIn.J_1907_R(b_257_Y.n_1700_B.R_4764_Y), (double)posIn.getZ() + voxelShapeIn.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y), y, particleDataIn);
    }

    private void n_1700_B(double xStart, double xEnd, double zStart, double zEnd, double y, ParticleOptions particleDataIn) {
        this.n_1700_B(particleDataIn, u_530_F.G_564_y(this.w_1457_N.nextDouble(), xStart, xEnd), y, u_530_F.G_564_y(this.w_1457_N.nextDouble(), zStart, zEnd), 0.0, 0.0, 0.0);
    }

    public void u_1723_Y() {
        try {
            ObjectIterator objectiterator = this.n_1700_B.int2ObjectEntrySet().iterator();
            while (objectiterator.hasNext()) {
                Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)objectiterator.next();
                N_4263_v entity = (N_4263_v)entry.getValue();
                if (!entity.t_4219_U) continue;
                objectiterator.remove();
                this.J_1907_R(entity);
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    public CrashReportCategory n_1700_B(n_3236_c report) {
        CrashReportCategory crashreportcategory = super.n_1700_B(report);
        crashreportcategory.n_1700_B("Server brand", () -> this.q_2307_F.Y_259_p.h_1847_R());
        crashreportcategory.n_1700_B("Server type", () -> this.q_2307_F.n_3318_d() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server");
        return crashreportcategory;
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, double x, double y, double z, SoundEvent soundIn, D_38_f category, float volume, float pitch) {
        if (Reflector.ForgeEventFactory_onPlaySoundAtEntity.exists()) {
            Object object = Reflector.ForgeEventFactory_onPlaySoundAtEntity.call(new Object[]{player, soundIn, category, Float.valueOf(volume), Float.valueOf(pitch)});
            if (Reflector.callBoolean(object, Reflector.Event_isCanceled, new Object[0]) || Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getSound, new Object[0]) == null) {
                return;
            }
            soundIn = (SoundEvent)Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getSound, new Object[0]);
            category = (D_38_f)((Object)Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getCategory, new Object[0]));
            volume = Reflector.callFloat(object, Reflector.PlaySoundAtEntityEvent_getVolume, new Object[0]);
        }
        if (player == this.q_2307_F.Y_259_p) {
            this.n_1700_B(x, y, z, soundIn, category, volume, pitch, false);
        }
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L playerIn, N_4263_v entityIn, SoundEvent eventIn, D_38_f categoryIn, float volume, float pitch) {
        if (Reflector.ForgeEventFactory_onPlaySoundAtEntity.exists()) {
            Object object = Reflector.ForgeEventFactory_onPlaySoundAtEntity.call(new Object[]{playerIn, eventIn, categoryIn, Float.valueOf(volume), Float.valueOf(pitch)});
            if (Reflector.callBoolean(object, Reflector.Event_isCanceled, new Object[0]) || Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getSound, new Object[0]) == null) {
                return;
            }
            eventIn = (SoundEvent)Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getSound, new Object[0]);
            categoryIn = (D_38_f)((Object)Reflector.call(object, Reflector.PlaySoundAtEntityEvent_getCategory, new Object[0]));
            volume = Reflector.callFloat(object, Reflector.PlaySoundAtEntityEvent_getVolume, new Object[0]);
        }
        if (playerIn == this.q_2307_F.Y_259_p) {
            this.q_2307_F.Z_976_R().n_1700_B((SoundInstance)new T_673_n(eventIn, categoryIn, entityIn));
        }
    }

    public void n_1700_B(c_1514_x pos, SoundEvent soundIn, D_38_f category, float volume, float pitch, boolean distanceDelay) {
        this.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, soundIn, category, volume, pitch, distanceDelay);
    }

    @Override
    public void n_1700_B(double x, double y, double z, SoundEvent soundIn, D_38_f category, float volume, float pitch, boolean distanceDelay) {
        double d0 = this.q_2307_F.s_956_w.M_588_G().J_1907_R().R_4764_Y(x, y, z);
        SimpleSoundInstance simplesound = new SimpleSoundInstance(soundIn, category, volume, pitch, x, y, z);
        if (distanceDelay && d0 > 100.0) {
            double d1 = Math.sqrt(d0) / 40.0;
            this.q_2307_F.Z_976_R().n_1700_B(simplesound, (int)(d1 * 20.0));
        } else {
            this.q_2307_F.Z_976_R().n_1700_B(simplesound);
        }
    }

    @Override
    public void n_1700_B(double x, double y, double z, double motionX, double motionY, double motionZ, @Nullable U_2912_j compound) {
        this.q_2307_F.v_4262_N.n_1700_B(new R_137_M.P_1922_E(this, x, y, z, motionX, motionY, motionZ, this.q_2307_F.v_4262_N, compound));
    }

    @Override
    public void n_1700_B(Packet<?> packetIn) {
        this.J_1907_R.n_1700_B(packetIn);
    }

    @Override
    public G_3474_H s_956_w() {
        return this.J_1907_R.R_4764_Y();
    }

    public void n_1700_B(i_4895_l scoreboardIn) {
        this.c_3005_b = scoreboardIn;
    }

    @Override
    public TickList<T_2915_h> u_2550_I() {
        return EmptyTickList.n_1700_B();
    }

    @Override
    public TickList<Fluid> M_588_G() {
        return EmptyTickList.n_1700_B();
    }

    public r_4399_U v_4262_N() {
        return this.t_4043_B;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, K_4074_S newState, int flags) {
        this.x_607_J = this.C_2741_M();
        boolean flag = super.n_1700_B(pos, newState, flags);
        this.x_607_J = false;
        return flag;
    }

    private boolean C_2741_M() {
        if (this.q_2307_F.w_1457_N instanceof PlayerControllerOF) {
            PlayerControllerOF playercontrollerof = (PlayerControllerOF)this.q_2307_F.w_1457_N;
            return playercontrollerof.isActing();
        }
        return false;
    }

    public boolean t_148_a() {
        return this.x_607_J;
    }

    public void R_4764_Y(N_4263_v p_onEntityAdded_1_) {
        RandomEntities.entityLoaded(p_onEntityAdded_1_, this);
        if (Config.isDynamicLights()) {
            DynamicLights.entityAdded(p_onEntityAdded_1_, Config.getRenderGlobal());
        }
    }

    public void G_564_y(N_4263_v p_onEntityRemoved_1_) {
        RandomEntities.entityUnloaded(p_onEntityRemoved_1_, this);
        if (Config.isDynamicLights()) {
            DynamicLights.entityRemoved(p_onEntityRemoved_1_, Config.getRenderGlobal());
        }
    }

    @Override
    @Nullable
    public F_3620_e n_1700_B(String mapName) {
        return this.H_2857_Y.get(mapName);
    }

    @Override
    public void n_1700_B(F_3620_e mapDataIn) {
        this.H_2857_Y.put(mapDataIn.P_1922_E(), mapDataIn);
    }

    @Override
    public int h_1847_R() {
        return 0;
    }

    @Override
    public i_4895_l Q_4569_t() {
        return this.c_3005_b;
    }

    @Override
    public TagContainer M_182_A() {
        return this.J_1907_R.u_2550_I();
    }

    @Override
    public r_4097_j t_1786_h() {
        return this.J_1907_R.Q_4569_t();
    }

    @Override
    public void n_1700_B(c_1514_x pos, K_4074_S oldState, K_4074_S newState, int flags) {
        this.R_4764_Y.n_1700_B(this, pos, oldState, newState, flags);
    }

    @Override
    public void n_1700_B(c_1514_x blockPosIn, K_4074_S oldState, K_4074_S newState) {
        this.R_4764_Y.n_1700_B(blockPosIn, oldState, newState);
    }

    public void J_1907_R(int sectionX, int sectionY, int sectionZ) {
        this.R_4764_Y.n_1700_B(sectionX, sectionY, sectionZ);
    }

    @Override
    public void n_1700_B(int breakerId, c_1514_x pos, int progress) {
        this.R_4764_Y.J_1907_R(breakerId, pos, progress);
    }

    @Override
    public void J_1907_R(int id, c_1514_x pos, int data) {
        this.R_4764_Y.n_1700_B(id, pos, data);
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, int type, c_1514_x pos, int data) {
        try {
            this.R_4764_Y.n_1700_B(player, type, pos, data);
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Playing level event");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Level event being played");
            crashreportcategory.n_1700_B("Block coordinates", CrashReportCategory.n_1700_B(pos));
            crashreportcategory.n_1700_B("Event source", player);
            crashreportcategory.n_1700_B("Event type", type);
            crashreportcategory.n_1700_B("Event data", data);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public void n_1700_B(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.R_4764_Y.n_1700_B(particleData, particleData.G_564_y().P_1922_E(), x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public void n_1700_B(ParticleOptions particleData, boolean forceAlwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.R_4764_Y.n_1700_B(particleData, particleData.G_564_y().P_1922_E() || forceAlwaysRender, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public void J_1907_R(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.R_4764_Y.n_1700_B(particleData, false, true, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public void J_1907_R(ParticleOptions particleData, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.R_4764_Y.n_1700_B(particleData, particleData.G_564_y().P_1922_E() || ignoreRange, true, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public List<X_4340_E> multiplayerClientSuggestionProvider() {
        return this.Z_875_P;
    }

    @Override
    public k_594_Q R_4764_Y(int x, int y, int z) {
        return this.t_1786_h().J_1907_R(V_3137_a.PlayerInfo).R_4764_Y(biomeBiomes.J_1907_R);
    }

    public float n_1700_B(float partialTicks) {
        float f = this.G_564_y(partialTicks);
        float f1 = 1.0f - (u_530_F.J_1907_R(f * ((float)Math.PI * 2)) * 2.0f + 0.2f);
        f1 = u_530_F.n_1700_B(f1, 0.0f, 1.0f);
        f1 = 1.0f - f1;
        f1 = (float)((double)f1 * (1.0 - (double)(this.w_1484_f(partialTicks) * 5.0f) / 16.0));
        f1 = (float)((double)f1 * (1.0 - (double)(this.u_1723_Y(partialTicks) * 5.0f) / 16.0));
        return f1 * 0.8f + 0.2f;
    }

    public e_2866_D n_1700_B(c_1514_x blockPosIn, float partialTicks) {
        float f9;
        float f = this.G_564_y(partialTicks);
        float f1 = u_530_F.J_1907_R(f * ((float)Math.PI * 2)) * 2.0f + 0.5f;
        f1 = u_530_F.n_1700_B(f1, 0.0f, 1.0f);
        k_594_Q biome = this.P_1922_E(blockPosIn);
        int i = biome.n_1700_B();
        float f2 = (float)(i >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(i >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(i & 0xFF) / 255.0f;
        f2 *= f1;
        f3 *= f1;
        f4 *= f1;
        float f5 = this.w_1484_f(partialTicks);
        if (f5 > 0.0f) {
            float f6 = (f2 * 0.3f + f3 * 0.59f + f4 * 0.11f) * 0.6f;
            float f7 = 1.0f - f5 * 0.75f;
            f2 = f2 * f7 + f6 * (1.0f - f7);
            f3 = f3 * f7 + f6 * (1.0f - f7);
            f4 = f4 * f7 + f6 * (1.0f - f7);
        }
        if ((f9 = this.u_1723_Y(partialTicks)) > 0.0f) {
            float f10 = (f2 * 0.3f + f3 * 0.59f + f4 * 0.11f) * 0.2f;
            float f8 = 1.0f - f9 * 0.75f;
            f2 = f2 * f8 + f10 * (1.0f - f8);
            f3 = f3 * f8 + f10 * (1.0f - f8);
            f4 = f4 * f8 + f10 * (1.0f - f8);
        }
        if (this.A_4115_X > 0) {
            float f11 = (float)this.A_4115_X - partialTicks;
            if (f11 > 1.0f) {
                f11 = 1.0f;
            }
            f2 = f2 * (1.0f - (f11 *= 0.45f)) + 0.8f * f11;
            f3 = f3 * (1.0f - f11) + 0.8f * f11;
            f4 = f4 * (1.0f - f11) + 1.0f * f11;
        }
        return new e_2866_D(f2, f3, f4);
    }

    public e_2866_D J_1907_R(float partialTicks) {
        float f = this.G_564_y(partialTicks);
        float f1 = u_530_F.J_1907_R(f * ((float)Math.PI * 2)) * 2.0f + 0.5f;
        f1 = u_530_F.n_1700_B(f1, 0.0f, 1.0f);
        float f2 = 1.0f;
        float f3 = 1.0f;
        float f4 = 1.0f;
        float f5 = this.w_1484_f(partialTicks);
        if (f5 > 0.0f) {
            float f6 = (f2 * 0.3f + f3 * 0.59f + f4 * 0.11f) * 0.6f;
            float f7 = 1.0f - f5 * 0.95f;
            f2 = f2 * f7 + f6 * (1.0f - f7);
            f3 = f3 * f7 + f6 * (1.0f - f7);
            f4 = f4 * f7 + f6 * (1.0f - f7);
        }
        f2 *= f1 * 0.9f + 0.1f;
        f3 *= f1 * 0.9f + 0.1f;
        f4 *= f1 * 0.85f + 0.15f;
        float f9 = this.u_1723_Y(partialTicks);
        if (f9 > 0.0f) {
            float f10 = (f2 * 0.3f + f3 * 0.59f + f4 * 0.11f) * 0.2f;
            float f8 = 1.0f - f9 * 0.95f;
            f2 = f2 * f8 + f10 * (1.0f - f8);
            f3 = f3 * f8 + f10 * (1.0f - f8);
            f4 = f4 * f8 + f10 * (1.0f - f8);
        }
        return new e_2866_D(f2, f3, f4);
    }

    public float R_4764_Y(float partialTicks) {
        float f = this.G_564_y(partialTicks);
        float f1 = 1.0f - (u_530_F.J_1907_R(f * ((float)Math.PI * 2)) * 2.0f + 0.25f);
        f1 = u_530_F.n_1700_B(f1, 0.0f, 1.0f);
        return f1 * f1 * 0.5f;
    }

    public int P_4830_p() {
        return this.A_4115_X;
    }

    @Override
    public void R_4764_Y(int timeFlashIn) {
        this.A_4115_X = timeFlashIn;
    }

    @Override
    public float func_230487_a_(b_257_Y p_230487_1_, boolean p_230487_2_) {
        boolean flag = this.n_1700_B().P_1922_E();
        boolean flag1 = Config.isShaders();
        if (!p_230487_2_) {
            return flag ? 0.9f : 1.0f;
        }
        switch (p_230487_1_) {
            case n_1700_B: {
                return flag ? 0.9f : (flag1 ? Shaders.blockLightLevel05 : 0.5f);
            }
            case J_1907_R: {
                return flag ? 0.9f : 1.0f;
            }
            case R_4764_Y: 
            case G_564_y: {
                if (Config.isShaders()) {
                    return Shaders.blockLightLevel08;
                }
                return 0.8f;
            }
            case P_1922_E: 
            case u_1723_Y: {
                if (Config.isShaders()) {
                    return Shaders.blockLightLevel06;
                }
                return 0.6f;
            }
        }
        return 1.0f;
    }

    @Override
    public int getBlockColor(c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        BlockTintCache colorcache = (BlockTintCache)this.Y_1740_V.get((Object)colorResolverIn);
        return colorcache.n_1700_B(blockPosIn, () -> this.n_1700_B(blockPosIn, colorResolverIn));
    }

    public int n_1700_B(c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        int i = MinecraftClient.A_4115_X().P_4830_p.x_607_J;
        if (i == 0) {
            return colorResolverIn.getColor(this.P_1922_E(blockPosIn), blockPosIn.getX(), blockPosIn.getZ());
        }
        int j = (i * 2 + 1) * (i * 2 + 1);
        int k = 0;
        int l = 0;
        int i1 = 0;
        Cursor3D cubecoordinateiterator = new Cursor3D(blockPosIn.getX() - i, blockPosIn.getY(), blockPosIn.getZ() - i, blockPosIn.getX() + i, blockPosIn.getY(), blockPosIn.getZ() + i);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        while (cubecoordinateiterator.n_1700_B()) {
            blockpos$mutable.n_1700_B(cubecoordinateiterator.J_1907_R(), cubecoordinateiterator.R_4764_Y(), cubecoordinateiterator.G_564_y());
            int j1 = colorResolverIn.getColor(this.P_1922_E(blockpos$mutable), blockpos$mutable.getX(), blockpos$mutable.getZ());
            k += (j1 & 0xFF0000) >> 16;
            l += (j1 & 0xFF00) >> 8;
            i1 += j1 & 0xFF;
        }
        return (k / j & 0xFF) << 16 | (l / j & 0xFF) << 8 | i1 / j & 0xFF;
    }

    @Override
    public c_1514_x w_1457_N() {
        if (this.q_2307_F.Y_601_j != null) {
            c_1514_x blockpos = new c_1514_x(this.Y_601_j.J_1907_R(), this.Y_601_j.R_4764_Y(), this.Y_601_j.G_564_y());
            if (!this.H_2857_Y().n_1700_B(blockpos)) {
                blockpos = this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, new c_1514_x(this.H_2857_Y().n_1700_B(), 0.0, this.H_2857_Y().J_1907_R()));
            }
            return blockpos;
        }
        return null;
    }

    public float Y_601_j() {
        return this.Y_601_j.w_1484_f();
    }

    public void J_1907_R(c_1514_x p_239136_1_, float p_239136_2_) {
        this.Y_601_j.n_1700_B(p_239136_1_, p_239136_2_);
    }

    public String toString() {
        return "ClientLevel";
    }

    public n_1700_B Y_259_p() {
        return this.C_2741_M;
    }

    @Override
    public /* synthetic */ LevelData k_2293_S() {
        return this.Y_259_p();
    }

    @Override
    public /* synthetic */ ChunkSource q_2307_F() {
        return this.v_4262_N();
    }

    public static class n_1700_B
    implements WritableLevelData {
        private final boolean n_1700_B;
        private final A_2352_Z J_1907_R;
        private final boolean R_4764_Y;
        private int G_564_y;
        private int P_1922_E;
        private int u_1723_Y;
        private float v_4262_N;
        private long w_1484_f;
        private long t_148_a;
        private boolean s_956_w;
        private R_2450_T u_2550_I;
        private boolean M_588_G;

        public n_1700_B(R_2450_T p_i232338_1_, boolean p_i232338_2_, boolean flatWorld) {
            this.u_2550_I = p_i232338_1_;
            this.n_1700_B = p_i232338_2_;
            this.R_4764_Y = flatWorld;
            this.J_1907_R = new A_2352_Z();
        }

        @Override
        public int J_1907_R() {
            return this.G_564_y;
        }

        @Override
        public int R_4764_Y() {
            return this.P_1922_E;
        }

        @Override
        public int G_564_y() {
            return this.u_1723_Y;
        }

        @Override
        public float w_1484_f() {
            return this.v_4262_N;
        }

        @Override
        public long P_1922_E() {
            return this.w_1484_f;
        }

        @Override
        public long u_1723_Y() {
            return this.t_148_a;
        }

        @Override
        public void n_1700_B(int x) {
            this.G_564_y = x;
        }

        @Override
        public void J_1907_R(int y) {
            this.P_1922_E = y;
        }

        @Override
        public void R_4764_Y(int z) {
            this.u_1723_Y = z;
        }

        @Override
        public void n_1700_B(float angle) {
            this.v_4262_N = angle;
        }

        public void n_1700_B(long time) {
            this.w_1484_f = time;
        }

        public void J_1907_R(long time) {
            this.t_148_a = time;
        }

        @Override
        public void n_1700_B(c_1514_x spawnPoint, float angle) {
            this.G_564_y = spawnPoint.getX();
            this.P_1922_E = spawnPoint.getY();
            this.u_1723_Y = spawnPoint.getZ();
            this.v_4262_N = angle;
        }

        @Override
        public boolean t_148_a() {
            return false;
        }

        @Override
        public boolean v_4262_N() {
            return this.s_956_w;
        }

        @Override
        public void n_1700_B(boolean isRaining) {
            this.s_956_w = isRaining;
        }

        @Override
        public boolean n_1700_B() {
            return this.n_1700_B;
        }

        @Override
        public A_2352_Z s_956_w() {
            return this.J_1907_R;
        }

        @Override
        public R_2450_T u_2550_I() {
            return this.u_2550_I;
        }

        @Override
        public boolean M_588_G() {
            return this.M_588_G;
        }

        @Override
        public void n_1700_B(CrashReportCategory category) {
            WritableLevelData.super.n_1700_B(category);
        }

        public void n_1700_B(R_2450_T difficulty) {
            Reflector.ForgeHooks_onDifficultyChange.callVoid(new Object[]{difficulty, this.u_2550_I});
            this.u_2550_I = difficulty;
        }

        public void J_1907_R(boolean difficultyLocked) {
            this.M_588_G = difficultyLocked;
        }

        public double P_4830_p() {
            return this.R_4764_Y ? 0.0 : 63.0;
        }

        public double h_1847_R() {
            return this.R_4764_Y ? 1.0 : 0.03125;
        }
    }
}



