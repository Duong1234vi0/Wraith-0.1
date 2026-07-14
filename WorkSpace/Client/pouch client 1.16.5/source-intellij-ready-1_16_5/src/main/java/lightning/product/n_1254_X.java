/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 *  it.unimi.dsi.fastutil.shorts.ShortList
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.StructureFeature;
import lightning.product.H_1748_a;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.R_1900_x;
import lightning.product.T_2915_h;
import lightning.product.T_3975_o;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.i_2154_H;
import lightning.product.r_3634_h;
import lightning.product.Fluid;
import lightning.product.ProtoTickList;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_1254_X
implements ChunkAccess {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Y_1387_d J_1907_R;
    private volatile boolean R_4764_Y;
    @Nullable
    private c_1108_W G_564_y;
    @Nullable
    private volatile R_1900_x P_1922_E;
    private final Map<z_2963_s.n_1700_B, z_2963_s> u_1723_Y = Maps.newEnumMap(z_2963_s.n_1700_B.class);
    private volatile ChunkStatus v_4262_N = ChunkStatus.n_1700_B;
    private final Map<c_1514_x, i_2154_H> w_1484_f = Maps.newHashMap();
    private final Map<c_1514_x, U_2912_j> t_148_a = Maps.newHashMap();
    private final P_3550_Z[] s_956_w = new P_3550_Z[16];
    private final List<U_2912_j> u_2550_I = Lists.newArrayList();
    private final List<c_1514_x> M_588_G = Lists.newArrayList();
    private final ShortList[] P_4830_p = new ShortList[16];
    private final Map<StructureFeature<?>, StructureStart<?>> h_1847_R = Maps.newHashMap();
    private final Map<StructureFeature<?>, LongSet> Q_4569_t = Maps.newHashMap();
    private final r_3634_h M_182_A;
    private final ProtoTickList<T_2915_h> t_1786_h;
    private final ProtoTickList<Fluid> multiplayerClientSuggestionProvider;
    private long w_1457_N;
    private final Map<T_3975_o.n_1700_B, BitSet> Y_601_j = new Object2ObjectArrayMap();
    private volatile boolean Y_259_p;

    public n_1254_X(Y_1387_d pos, r_3634_h data) {
        this(pos, data, null, new ProtoTickList<T_2915_h>(block -> block == null || block.multiplayerClientSuggestionProvider().v_4262_N(), pos), new ProtoTickList<Fluid>(fluid -> fluid == null || fluid == Fluids.n_1700_B, pos));
    }

    public n_1254_X(Y_1387_d pos, r_3634_h upgradeData, @Nullable P_3550_Z[] sections, ProtoTickList<T_2915_h> pendingBlockTicks, ProtoTickList<Fluid> pendingFluidTicks) {
        this.J_1907_R = pos;
        this.M_182_A = upgradeData;
        this.t_1786_h = pendingBlockTicks;
        this.multiplayerClientSuggestionProvider = pendingFluidTicks;
        if (sections != null) {
            if (this.s_956_w.length == sections.length) {
                System.arraycopy(sections, 0, this.s_956_w, 0, this.s_956_w.length);
            } else {
                n_1700_B.warn("Could not set level chunk sections, array length is {} instead of {}", (Object)sections.length, (Object)this.s_956_w.length);
            }
        }
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        int i = pos.getY();
        if (b_4507_u.G_564_y(i)) {
            return a_3742_W.z_2759_Q.multiplayerClientSuggestionProvider();
        }
        P_3550_Z chunksection = this.getSections()[i >> 4];
        return P_3550_Z.n_1700_B(chunksection) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : chunksection.n_1700_B(pos.getX() & 0xF, i & 0xF, pos.getZ() & 0xF);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        int i = pos.getY();
        if (b_4507_u.G_564_y(i)) {
            return Fluids.n_1700_B.w_1484_f();
        }
        P_3550_Z chunksection = this.getSections()[i >> 4];
        return P_3550_Z.n_1700_B(chunksection) ? Fluids.n_1700_B.w_1484_f() : chunksection.J_1907_R(pos.getX() & 0xF, i & 0xF, pos.getZ() & 0xF);
    }

    @Override
    public Stream<c_1514_x> getLightSources() {
        return this.M_588_G.stream();
    }

    public ShortList[] n_1700_B() {
        ShortList[] ashortlist = new ShortList[16];
        for (c_1514_x blockpos : this.M_588_G) {
            ChunkAccess.n_1700_B(ashortlist, blockpos.getY() >> 4).add(n_1254_X.J_1907_R(blockpos));
        }
        return ashortlist;
    }

    public void n_1700_B(short packedPosition, int lightValue) {
        this.n_1700_B(n_1254_X.n_1700_B(packedPosition, lightValue, this.J_1907_R));
    }

    public void n_1700_B(c_1514_x lightPos) {
        this.M_588_G.add(lightPos.toImmutable());
    }

    @Override
    @Nullable
    public K_4074_S setBlockState(c_1514_x pos, K_4074_S state, boolean isMoving) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        if (j >= 0 && j < 256) {
            if (this.s_956_w[j >> 4] == H_1748_a.EMPTY_SECTION && state.n_1700_B(a_3742_W.n_1700_B)) {
                return state;
            }
            if (state.u_1723_Y() > 0) {
                this.M_588_G.add(new c_1514_x((i & 0xF) + this.getPos().J_1907_R(), j, (k & 0xF) + this.getPos().R_4764_Y()));
            }
            P_3550_Z chunksection = this.n_1700_B(j >> 4);
            K_4074_S blockstate = chunksection.n_1700_B(i & 0xF, j & 0xF, k & 0xF, state);
            if (this.v_4262_N.J_1907_R(ChunkStatus.t_148_a) && state != blockstate && (state.J_1907_R(this, pos) != blockstate.J_1907_R(this, pos) || state.u_1723_Y() != blockstate.u_1723_Y() || state.P_1922_E() || blockstate.P_1922_E())) {
                R_1900_x worldlightmanager = this.G_564_y();
                worldlightmanager.n_1700_B(pos);
            }
            EnumSet<z_2963_s.n_1700_B> enumset1 = this.getStatus().w_1484_f();
            EnumSet<z_2963_s.n_1700_B> enumset = null;
            for (z_2963_s.n_1700_B heightmap$type : enumset1) {
                z_2963_s heightmap = this.u_1723_Y.get(heightmap$type);
                if (heightmap != null) continue;
                if (enumset == null) {
                    enumset = EnumSet.noneOf(z_2963_s.n_1700_B.class);
                }
                enumset.add(heightmap$type);
            }
            if (enumset != null) {
                z_2963_s.n_1700_B(this, enumset);
            }
            for (z_2963_s.n_1700_B heightmap$type1 : enumset1) {
                this.u_1723_Y.get(heightmap$type1).n_1700_B(i & 0xF, j, k & 0xF, state);
            }
            return blockstate;
        }
        return a_3742_W.z_2759_Q.multiplayerClientSuggestionProvider();
    }

    public P_3550_Z n_1700_B(int sectionId) {
        if (this.s_956_w[sectionId] == H_1748_a.EMPTY_SECTION) {
            this.s_956_w[sectionId] = new P_3550_Z(sectionId << 4);
        }
        return this.s_956_w[sectionId];
    }

    @Override
    public void addTileEntity(c_1514_x pos, i_2154_H tileEntityIn) {
        tileEntityIn.R_4764_Y(pos);
        this.w_1484_f.put(pos, tileEntityIn);
    }

    @Override
    public Set<c_1514_x> getTileEntitiesPos() {
        HashSet set = Sets.newHashSet(this.t_148_a.keySet());
        set.addAll(this.w_1484_f.keySet());
        return set;
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return this.w_1484_f.get(pos);
    }

    public Map<c_1514_x, i_2154_H> J_1907_R() {
        return this.w_1484_f;
    }

    public void n_1700_B(U_2912_j entityCompound) {
        this.u_2550_I.add(entityCompound);
    }

    @Override
    public void addEntity(N_4263_v entityIn) {
        if (!entityIn.y_2772_m()) {
            U_2912_j compoundnbt = new U_2912_j();
            entityIn.G_564_y(compoundnbt);
            this.n_1700_B(compoundnbt);
        }
    }

    public List<U_2912_j> R_4764_Y() {
        return this.u_2550_I;
    }

    public void n_1700_B(c_1108_W biomes) {
        this.G_564_y = biomes;
    }

    @Override
    @Nullable
    public c_1108_W getBiomes() {
        return this.G_564_y;
    }

    @Override
    public void setModified(boolean modified) {
        this.R_4764_Y = modified;
    }

    @Override
    public boolean isModified() {
        return this.R_4764_Y;
    }

    @Override
    public ChunkStatus getStatus() {
        return this.v_4262_N;
    }

    public void n_1700_B(ChunkStatus status) {
        this.v_4262_N = status;
        this.setModified(true);
    }

    @Override
    public P_3550_Z[] getSections() {
        return this.s_956_w;
    }

    @Nullable
    public R_1900_x G_564_y() {
        return this.P_1922_E;
    }

    @Override
    public Collection<Map.Entry<z_2963_s.n_1700_B, z_2963_s>> getHeightmaps() {
        return Collections.unmodifiableSet(this.u_1723_Y.entrySet());
    }

    @Override
    public void setHeightmap(z_2963_s.n_1700_B type, long[] data) {
        this.getHeightmap(type).n_1700_B(data);
    }

    @Override
    public z_2963_s getHeightmap(z_2963_s.n_1700_B typeIn) {
        return this.u_1723_Y.computeIfAbsent(typeIn, type -> new z_2963_s(this, (z_2963_s.n_1700_B)type));
    }

    @Override
    public int getTopBlockY(z_2963_s.n_1700_B heightmapType, int x, int z) {
        z_2963_s heightmap = this.u_1723_Y.get(heightmapType);
        if (heightmap == null) {
            z_2963_s.n_1700_B(this, EnumSet.of(heightmapType));
            heightmap = this.u_1723_Y.get(heightmapType);
        }
        return heightmap.n_1700_B(x & 0xF, z & 0xF) - 1;
    }

    @Override
    public Y_1387_d getPos() {
        return this.J_1907_R;
    }

    @Override
    public void setLastSaveTime(long saveTime) {
    }

    @Override
    @Nullable
    public StructureStart<?> func_230342_a_(StructureFeature<?> p_230342_1_) {
        return this.h_1847_R.get(p_230342_1_);
    }

    @Override
    public void func_230344_a_(StructureFeature<?> p_230344_1_, StructureStart<?> p_230344_2_) {
        this.h_1847_R.put(p_230344_1_, p_230344_2_);
        this.R_4764_Y = true;
    }

    @Override
    public Map<StructureFeature<?>, StructureStart<?>> getStructureStarts() {
        return Collections.unmodifiableMap(this.h_1847_R);
    }

    @Override
    public void setStructureStarts(Map<StructureFeature<?>, StructureStart<?>> structureStartsIn) {
        this.h_1847_R.clear();
        this.h_1847_R.putAll(structureStartsIn);
        this.R_4764_Y = true;
    }

    @Override
    public LongSet func_230346_b_(StructureFeature<?> p_230346_1_) {
        return this.Q_4569_t.computeIfAbsent(p_230346_1_, structureIn -> new LongOpenHashSet());
    }

    @Override
    public void func_230343_a_(StructureFeature<?> p_230343_1_, long p_230343_2_) {
        this.Q_4569_t.computeIfAbsent(p_230343_1_, structureIn -> new LongOpenHashSet()).add(p_230343_2_);
        this.R_4764_Y = true;
    }

    @Override
    public Map<StructureFeature<?>, LongSet> getStructureReferences() {
        return Collections.unmodifiableMap(this.Q_4569_t);
    }

    @Override
    public void setStructureReferences(Map<StructureFeature<?>, LongSet> structureReferences) {
        this.Q_4569_t.clear();
        this.Q_4569_t.putAll(structureReferences);
        this.R_4764_Y = true;
    }

    public static short J_1907_R(c_1514_x pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int l = i & 0xF;
        int i1 = j & 0xF;
        int j1 = k & 0xF;
        return (short)(l | i1 << 4 | j1 << 8);
    }

    public static c_1514_x n_1700_B(short packedPos, int yOffset, Y_1387_d chunkPosIn) {
        int i = (packedPos & 0xF) + (chunkPosIn.J_1907_R << 4);
        int j = (packedPos >>> 4 & 0xF) + (yOffset << 4);
        int k = (packedPos >>> 8 & 0xF) + (chunkPosIn.R_4764_Y << 4);
        return new c_1514_x(i, j, k);
    }

    @Override
    public void P_1922_E(c_1514_x pos) {
        if (!b_4507_u.Q_4569_t(pos)) {
            ChunkAccess.n_1700_B(this.P_4830_p, pos.getY() >> 4).add(n_1254_X.J_1907_R(pos));
        }
    }

    @Override
    public ShortList[] getPackedPositions() {
        return this.P_4830_p;
    }

    @Override
    public void J_1907_R(short packedPosition, int index) {
        ChunkAccess.n_1700_B(this.P_4830_p, index).add(packedPosition);
    }

    public ProtoTickList<T_2915_h> P_1922_E() {
        return this.t_1786_h;
    }

    public ProtoTickList<Fluid> u_1723_Y() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public r_3634_h getUpgradeData() {
        return this.M_182_A;
    }

    @Override
    public void setInhabitedTime(long newInhabitedTime) {
        this.w_1457_N = newInhabitedTime;
    }

    @Override
    public long getInhabitedTime() {
        return this.w_1457_N;
    }

    @Override
    public void addTileEntity(U_2912_j nbt) {
        this.t_148_a.put(new c_1514_x(nbt.w_1484_f("x"), nbt.w_1484_f("y"), nbt.w_1484_f("z")), nbt);
    }

    public Map<c_1514_x, U_2912_j> v_4262_N() {
        return Collections.unmodifiableMap(this.t_148_a);
    }

    @Override
    public U_2912_j getDeferredTileEntity(c_1514_x pos) {
        return this.t_148_a.get(pos);
    }

    @Override
    @Nullable
    public U_2912_j getTileEntityNBT(c_1514_x pos) {
        i_2154_H tileentity = this.getTileEntity(pos);
        return tileentity != null ? tileentity.n_1700_B(new U_2912_j()) : this.t_148_a.get(pos);
    }

    @Override
    public void removeTileEntity(c_1514_x pos) {
        this.w_1484_f.remove(pos);
        this.t_148_a.remove(pos);
    }

    @Nullable
    public BitSet n_1700_B(T_3975_o.n_1700_B type) {
        return this.Y_601_j.get(type);
    }

    public BitSet J_1907_R(T_3975_o.n_1700_B type) {
        return this.Y_601_j.computeIfAbsent(type, typeIn -> new BitSet(65536));
    }

    public void n_1700_B(T_3975_o.n_1700_B type, BitSet mask) {
        this.Y_601_j.put(type, mask);
    }

    public void n_1700_B(R_1900_x lightManager) {
        this.P_1922_E = lightManager;
    }

    @Override
    public boolean hasLight() {
        return this.Y_259_p;
    }

    @Override
    public void setLight(boolean lightCorrectIn) {
        this.Y_259_p = lightCorrectIn;
        this.setModified(true);
    }

    public /* synthetic */ TickList getFluidsToBeTicked() {
        return this.u_1723_Y();
    }

    public /* synthetic */ TickList getBlocksToBeTicked() {
        return this.P_1922_E();
    }
}


