/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.WorldGenTickList;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.D_38_f;
import lightning.product.StructureFeature;
import lightning.product.BiomeManager;
import lightning.product.I_4817_s;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.R_1900_x;
import lightning.product.WorldGenLevel;
import lightning.product.LevelData;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.T_603_v;
import lightning.product.U_2912_j;
import lightning.product.SoundEvent;
import lightning.product.Y_1387_d;
import lightning.product.Z_1993_T;
import lightning.product.Z_3903_F;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SectionPos;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.k_2789_z;
import lightning.product.k_594_Q;
import lightning.product.r_4097_j;
import lightning.product.Fluid;
import lightning.product.u_530_F;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class K_3381_i
implements WorldGenLevel {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final List<ChunkAccess> J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final e_3591_l u_1723_Y;
    private final long v_4262_N;
    private final LevelData w_1484_f;
    private final Random t_148_a;
    private final Z_3903_F s_956_w;
    private final TickList<T_2915_h> u_2550_I = new WorldGenTickList<T_2915_h>(p_205335_1_ -> this.t_148_a((c_1514_x)p_205335_1_).getBlocksToBeTicked());
    private final TickList<Fluid> M_588_G = new WorldGenTickList<Fluid>(p_205334_1_ -> this.t_148_a((c_1514_x)p_205334_1_).getFluidsToBeTicked());
    private final BiomeManager P_4830_p;
    private final Y_1387_d h_1847_R;
    private final Y_1387_d Q_4569_t;
    private final J_3017_d M_182_A;

    public K_3381_i(e_3591_l p_i50698_1_, List<ChunkAccess> p_i50698_2_) {
        int i = u_530_F.R_4764_Y(Math.sqrt(p_i50698_2_.size()));
        if (i * i != p_i50698_2_.size()) {
            throw j_3341_s.R_4764_Y(new IllegalStateException("Cache size is not a square."));
        }
        Y_1387_d chunkpos = p_i50698_2_.get(p_i50698_2_.size() / 2).getPos();
        this.J_1907_R = p_i50698_2_;
        this.R_4764_Y = chunkpos.J_1907_R;
        this.G_564_y = chunkpos.R_4764_Y;
        this.P_1922_E = i;
        this.u_1723_Y = p_i50698_1_;
        this.v_4262_N = p_i50698_1_.n_1700_B();
        this.w_1484_f = p_i50698_1_.k_2293_S();
        this.t_148_a = p_i50698_1_.e_4240_b();
        this.s_956_w = p_i50698_1_.G_624_v();
        this.P_4830_p = new BiomeManager(this, BiomeManager.n_1700_B(this.v_4262_N), p_i50698_1_.G_624_v().P_4830_p());
        this.h_1847_R = p_i50698_2_.get(0).getPos();
        this.Q_4569_t = p_i50698_2_.get(p_i50698_2_.size() - 1).getPos();
        this.M_182_A = p_i50698_1_.R_4764_Y().n_1700_B(this);
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    @Override
    public ChunkAccess P_1922_E(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ, ChunkStatus.n_1700_B);
    }

    @Override
    @Nullable
    public ChunkAccess n_1700_B(int x, int z, ChunkStatus requiredStatus, boolean nonnull) {
        ChunkAccess ichunk;
        if (this.R_4764_Y(x, z)) {
            int i = x - this.h_1847_R.J_1907_R;
            int j = z - this.h_1847_R.R_4764_Y;
            ichunk = this.J_1907_R.get(i + j * this.P_1922_E);
            if (ichunk.getStatus().J_1907_R(requiredStatus)) {
                return ichunk;
            }
        } else {
            ichunk = null;
        }
        if (!nonnull) {
            return null;
        }
        n_1700_B.error("Requested chunk : {} {}", (Object)x, (Object)z);
        n_1700_B.error("Region bounds : {} {} | {} {}", (Object)this.h_1847_R.J_1907_R, (Object)this.h_1847_R.R_4764_Y, (Object)this.Q_4569_t.J_1907_R, (Object)this.Q_4569_t.R_4764_Y);
        if (ichunk != null) {
            throw j_3341_s.R_4764_Y(new RuntimeException(String.format("Chunk is not of correct status. Expecting %s, got %s | %s %s", requiredStatus, ichunk.getStatus(), x, z)));
        }
        throw j_3341_s.R_4764_Y(new RuntimeException(String.format("We are asking a region for a chunk out of bound | %s %s", x, z)));
    }

    @Override
    public boolean R_4764_Y(int chunkX, int chunkZ) {
        return chunkX >= this.h_1847_R.J_1907_R && chunkX <= this.Q_4569_t.J_1907_R && chunkZ >= this.h_1847_R.R_4764_Y && chunkZ <= this.Q_4569_t.R_4764_Y;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        return this.P_1922_E(pos.getX() >> 4, pos.getZ() >> 4).getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return this.t_148_a(pos).getFluidState(pos);
    }

    @Override
    @Nullable
    public a_3913_L n_1700_B(double x, double y, double z, double distance, Predicate<N_4263_v> predicate) {
        return null;
    }

    @Override
    public int d_2427_y() {
        return 0;
    }

    @Override
    public BiomeManager z_1737_N() {
        return this.P_4830_p;
    }

    @Override
    public k_594_Q R_4764_Y(int x, int y, int z) {
        return this.u_1723_Y.R_4764_Y(x, y, z);
    }

    @Override
    public float func_230487_a_(b_257_Y p_230487_1_, boolean p_230487_2_) {
        return 1.0f;
    }

    @Override
    public R_1900_x getLightManager() {
        return this.u_1723_Y.getLightManager();
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, boolean dropBlock, @Nullable N_4263_v entity, int recursionLeft) {
        K_4074_S blockstate = this.getBlockState(pos);
        if (blockstate.v_4262_N()) {
            return false;
        }
        if (dropBlock) {
            i_2154_H tileentity = blockstate.J_1907_R().G_564_y() ? this.getTileEntity(pos) : null;
            T_2915_h.n_1700_B(blockstate, (b_4507_u)this.u_1723_Y, pos, tileentity, entity, Z_1993_T.J_1907_R);
        }
        return this.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3, recursionLeft);
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        ChunkAccess ichunk = this.t_148_a(pos);
        i_2154_H tileentity = ichunk.getTileEntity(pos);
        if (tileentity != null) {
            return tileentity;
        }
        U_2912_j compoundnbt = ichunk.getDeferredTileEntity(pos);
        K_4074_S blockstate = ichunk.getBlockState(pos);
        if (compoundnbt != null) {
            if ("DUMMY".equals(compoundnbt.M_588_G("id"))) {
                T_2915_h block = blockstate.J_1907_R();
                if (!(block instanceof k_2789_z)) {
                    return null;
                }
                tileentity = ((k_2789_z)((Object)block)).n_1700_B(this.u_1723_Y);
            } else {
                tileentity = i_2154_H.J_1907_R(blockstate, compoundnbt);
            }
            if (tileentity != null) {
                ichunk.addTileEntity(pos, tileentity);
                return tileentity;
            }
        }
        if (blockstate.J_1907_R() instanceof k_2789_z) {
            n_1700_B.warn("Tried to access a block entity before it was created. {}", (Object)pos);
        }
        return null;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, K_4074_S state, int flags, int recursionLeft) {
        T_2915_h block;
        ChunkAccess ichunk = this.t_148_a(pos);
        K_4074_S blockstate = ichunk.setBlockState(pos, state, false);
        if (blockstate != null) {
            this.u_1723_Y.J_1907_R(pos, blockstate, state);
        }
        if ((block = state.J_1907_R()).G_564_y()) {
            if (ichunk.getStatus().v_4262_N() == ChunkStatus.G_564_y.J_1907_R) {
                ichunk.addTileEntity(pos, ((k_2789_z)((Object)block)).n_1700_B(this));
            } else {
                U_2912_j compoundnbt = new U_2912_j();
                compoundnbt.J_1907_R("x", pos.getX());
                compoundnbt.J_1907_R("y", pos.getY());
                compoundnbt.J_1907_R("z", pos.getZ());
                compoundnbt.n_1700_B("id", "DUMMY");
                ichunk.addTileEntity(compoundnbt);
            }
        } else if (blockstate != null && blockstate.J_1907_R().G_564_y()) {
            ichunk.removeTileEntity(pos);
        }
        if (state.t_1786_h(this, pos)) {
            this.P_4830_p(pos);
        }
        return true;
    }

    private void P_4830_p(c_1514_x pos) {
        this.t_148_a(pos).P_1922_E(pos);
    }

    @Override
    public boolean a_(N_4263_v entityIn) {
        int i = u_530_F.R_4764_Y(entityIn.O_3598_v() / 16.0);
        int j = u_530_F.R_4764_Y(entityIn.l_2647_k() / 16.0);
        this.P_1922_E(i, j).addEntity(entityIn);
        return true;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, boolean isMoving) {
        return this.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
    }

    @Override
    public T_603_v H_2857_Y() {
        return this.u_1723_Y.H_2857_Y();
    }

    @Override
    public boolean v_4276_D() {
        return false;
    }

    @Override
    @Deprecated
    public e_3591_l J_1907_R() {
        return this.u_1723_Y;
    }

    @Override
    public r_4097_j t_1786_h() {
        return this.u_1723_Y.t_1786_h();
    }

    @Override
    public LevelData k_2293_S() {
        return this.w_1484_f;
    }

    @Override
    public DifficultyInstance J_1907_R(c_1514_x pos) {
        if (!this.R_4764_Y(pos.getX() >> 4, pos.getZ() >> 4)) {
            throw new RuntimeException("We are asking a region for a chunk out of bound");
        }
        return new DifficultyInstance(this.u_1723_Y.x_607_J(), this.u_1723_Y.Z_976_R(), 0L, this.u_1723_Y.Y_1740_V());
    }

    @Override
    public ChunkSource q_2307_F() {
        return this.u_1723_Y.Y_259_p();
    }

    @Override
    public long n_1700_B() {
        return this.v_4262_N;
    }

    @Override
    public TickList<T_2915_h> u_2550_I() {
        return this.u_2550_I;
    }

    @Override
    public TickList<Fluid> M_588_G() {
        return this.M_588_G;
    }

    @Override
    public int d_2461_k() {
        return this.u_1723_Y.d_2461_k();
    }

    @Override
    public Random e_4240_b() {
        return this.t_148_a;
    }

    @Override
    public int n_1700_B(z_2963_s.n_1700_B heightmapType, int x, int z) {
        return this.P_1922_E(x >> 4, z >> 4).getTopBlockY(heightmapType, x & 0xF, z & 0xF) + 1;
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, c_1514_x pos, SoundEvent soundIn, D_38_f category, float volume, float pitch) {
    }

    @Override
    public void n_1700_B(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, int type, c_1514_x pos, int data) {
    }

    @Override
    public Z_3903_F G_624_v() {
        return this.s_956_w;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, Predicate<K_4074_S> state) {
        return state.test(this.getBlockState(pos));
    }

    @Override
    public <T extends N_4263_v> List<T> n_1700_B(Class<? extends T> clazz, I_4817_s aabb, @Nullable Predicate<? super T> filter) {
        return Collections.emptyList();
    }

    @Override
    public List<N_4263_v> J_1907_R(@Nullable N_4263_v entityIn, I_4817_s boundingBox, @Nullable Predicate<? super N_4263_v> predicate) {
        return Collections.emptyList();
    }

    public List<a_3913_L> multiplayerClientSuggestionProvider() {
        return Collections.emptyList();
    }

    @Override
    public Stream<? extends StructureStart<?>> n_1700_B(SectionPos p_241827_1_, StructureFeature<?> p_241827_2_) {
        return this.M_182_A.n_1700_B(p_241827_1_, p_241827_2_);
    }
}


