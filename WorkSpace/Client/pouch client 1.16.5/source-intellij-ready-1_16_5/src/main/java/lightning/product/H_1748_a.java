/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.shorts.ShortList
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.StructureFeature;
import lightning.product.EmptyTickList;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.R_1900_x;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2585_i;
import lightning.product.b_2971_b;
import lightning.product.b_4507_u;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_1322_b;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.k_2789_z;
import lightning.product.n_1254_X;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.EnderDragonPart;
import lightning.product.DebugLevelSource;
import lightning.product.CrashReportCategory;
import lightning.product.r_3634_h;
import lightning.product.Fluid;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.ProtoTickList;
import lightning.product.x_4674_u;
import lightning.product.x_92_N;
import lightning.product.y_3683_b;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class H_1748_a
implements ChunkAccess {
    private static final Logger LOGGER = LogManager.getLogger();
    @Nullable
    public static final P_3550_Z EMPTY_SECTION = null;
    private final P_3550_Z[] sections = new P_3550_Z[16];
    private c_1108_W blockBiomeArray;
    private final Map<c_1514_x, U_2912_j> deferredTileEntities = Maps.newHashMap();
    private boolean loaded;
    private final b_4507_u world;
    private final Map<z_2963_s.n_1700_B, z_2963_s> heightMap = Maps.newEnumMap(z_2963_s.n_1700_B.class);
    private final r_3634_h upgradeData;
    private final Map<c_1514_x, i_2154_H> tileEntities = Maps.newHashMap();
    private final e_1322_b<N_4263_v>[] entityLists;
    private final Map<StructureFeature<?>, StructureStart<?>> structureStarts = Maps.newHashMap();
    private final Map<StructureFeature<?>, LongSet> structureReferences = Maps.newHashMap();
    private final ShortList[] packedBlockPositions = new ShortList[16];
    private TickList<T_2915_h> blocksToBeTicked;
    private TickList<Fluid> fluidsToBeTicked;
    private boolean hasEntities;
    private long lastSaveTime;
    private volatile boolean dirty;
    private long inhabitedTime;
    @Nullable
    private Supplier<y_3683_b.G_564_y> locationType;
    @Nullable
    private Consumer<H_1748_a> postLoadConsumer;
    private final Y_1387_d pos;
    private volatile boolean lightCorrect;

    public H_1748_a(b_4507_u worldIn, Y_1387_d chunkPosIn, c_1108_W biomeContainerIn) {
        this(worldIn, chunkPosIn, biomeContainerIn, r_3634_h.n_1700_B, EmptyTickList.n_1700_B(), EmptyTickList.n_1700_B(), 0L, null, null);
    }

    public H_1748_a(b_4507_u worldIn, Y_1387_d chunkPosIn, c_1108_W biomeContainerIn, r_3634_h upgradeDataIn, TickList<T_2915_h> tickBlocksIn, TickList<Fluid> tickFluidsIn, long inhabitedTimeIn, @Nullable P_3550_Z[] sectionsIn, @Nullable Consumer<H_1748_a> postLoadConsumerIn) {
        this.entityLists = new e_1322_b[16];
        this.world = worldIn;
        this.pos = chunkPosIn;
        this.upgradeData = upgradeDataIn;
        for (z_2963_s.n_1700_B heightmap$type : z_2963_s.n_1700_B.values()) {
            if (!ChunkStatus.P_4830_p.w_1484_f().contains(heightmap$type)) continue;
            this.heightMap.put(heightmap$type, new z_2963_s(this, heightmap$type));
        }
        for (int i = 0; i < this.entityLists.length; ++i) {
            this.entityLists[i] = new e_1322_b<N_4263_v>(N_4263_v.class);
        }
        this.blockBiomeArray = biomeContainerIn;
        this.blocksToBeTicked = tickBlocksIn;
        this.fluidsToBeTicked = tickFluidsIn;
        this.inhabitedTime = inhabitedTimeIn;
        this.postLoadConsumer = postLoadConsumerIn;
        if (sectionsIn != null) {
            if (this.sections.length == sectionsIn.length) {
                System.arraycopy(sectionsIn, 0, this.sections, 0, this.sections.length);
            } else {
                LOGGER.warn("Could not set level chunk sections, array length is {} instead of {}", (Object)sectionsIn.length, (Object)this.sections.length);
            }
        }
    }

    public H_1748_a(b_4507_u worldIn, n_1254_X primer) {
        this(worldIn, primer.getPos(), primer.getBiomes(), primer.getUpgradeData(), primer.P_1922_E(), primer.u_1723_Y(), primer.getInhabitedTime(), primer.getSections(), null);
        for (U_2912_j compoundnbt : primer.R_4764_Y()) {
            t_5_h.n_1700_B(compoundnbt, worldIn, (N_4263_v entity) -> {
                this.addEntity((N_4263_v)entity);
                return entity;
            });
        }
        for (i_2154_H tileentity : primer.J_1907_R().values()) {
            this.addTileEntity(tileentity);
        }
        this.deferredTileEntities.putAll(primer.v_4262_N());
        for (int i = 0; i < primer.getPackedPositions().length; ++i) {
            this.packedBlockPositions[i] = primer.getPackedPositions()[i];
        }
        this.setStructureStarts(primer.getStructureStarts());
        this.setStructureReferences(primer.getStructureReferences());
        for (Map.Entry<z_2963_s.n_1700_B, z_2963_s> entry : primer.getHeightmaps()) {
            if (!ChunkStatus.P_4830_p.w_1484_f().contains(entry.getKey())) continue;
            this.getHeightmap(entry.getKey()).n_1700_B(entry.getValue().n_1700_B());
        }
        this.setLight(primer.hasLight());
        this.dirty = true;
    }

    @Override
    public z_2963_s getHeightmap(z_2963_s.n_1700_B typeIn) {
        return this.heightMap.computeIfAbsent(typeIn, type -> new z_2963_s(this, (z_2963_s.n_1700_B)type));
    }

    @Override
    public Set<c_1514_x> getTileEntitiesPos() {
        HashSet set = Sets.newHashSet(this.deferredTileEntities.keySet());
        set.addAll(this.tileEntities.keySet());
        return set;
    }

    @Override
    public P_3550_Z[] getSections() {
        return this.sections;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        if (this.world.l_1233_K()) {
            K_4074_S blockstate = null;
            if (j == 60) {
                blockstate = a_3742_W.N_4890_q.multiplayerClientSuggestionProvider();
            }
            if (j == 70) {
                blockstate = DebugLevelSource.J_1907_R(i, k);
            }
            return blockstate == null ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : blockstate;
        }
        try {
            P_3550_Z chunksection;
            if (j >= 0 && j >> 4 < this.sections.length && !P_3550_Z.n_1700_B(chunksection = this.sections[j >> 4])) {
                return chunksection.n_1700_B(i & 0xF, j & 0xF, k & 0xF);
            }
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Getting block state");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being got");
            crashreportcategory.n_1700_B("Location", () -> CrashReportCategory.n_1700_B(i, j, k));
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return this.getFluidState(pos.getX(), pos.getY(), pos.getZ());
    }

    public FluidState getFluidState(int bx, int by, int bz) {
        try {
            P_3550_Z chunksection;
            if (by >= 0 && by >> 4 < this.sections.length && !P_3550_Z.n_1700_B(chunksection = this.sections[by >> 4])) {
                return chunksection.J_1907_R(bx & 0xF, by & 0xF, bz & 0xF);
            }
            return Fluids.n_1700_B.w_1484_f();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Getting fluid state");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being got");
            crashreportcategory.n_1700_B("Location", () -> CrashReportCategory.n_1700_B(bx, by, bz));
            throw new ReportedException(crashreport);
        }
    }

    @Override
    @Nullable
    public K_4074_S setBlockState(c_1514_x pos, K_4074_S state, boolean isMoving) {
        i_2154_H tileentity;
        int i = pos.getX() & 0xF;
        int j = pos.getY();
        int k = pos.getZ() & 0xF;
        P_3550_Z chunksection = this.sections[j >> 4];
        if (chunksection == EMPTY_SECTION) {
            if (state.v_4262_N()) {
                return null;
            }
            this.sections[j >> 4] = chunksection = new P_3550_Z(j >> 4 << 4);
        }
        boolean flag = chunksection.R_4764_Y();
        K_4074_S blockstate = chunksection.n_1700_B(i, j & 0xF, k, state);
        if (blockstate == state) {
            return null;
        }
        T_2915_h block = state.J_1907_R();
        T_2915_h block1 = blockstate.J_1907_R();
        this.heightMap.get(z_2963_s.n_1700_B.P_1922_E).n_1700_B(i, j, k, state);
        this.heightMap.get(z_2963_s.n_1700_B.u_1723_Y).n_1700_B(i, j, k, state);
        this.heightMap.get(z_2963_s.n_1700_B.G_564_y).n_1700_B(i, j, k, state);
        this.heightMap.get(z_2963_s.n_1700_B.J_1907_R).n_1700_B(i, j, k, state);
        boolean flag1 = chunksection.R_4764_Y();
        if (flag != flag1) {
            this.world.q_2307_F().G_564_y().n_1700_B(pos, flag1);
        }
        if (!this.world.Y_259_p) {
            blockstate.J_1907_R(this.world, pos, state, isMoving);
        } else if (block1 != block && block1 instanceof k_2789_z) {
            this.world.t_1786_h(pos);
        }
        if (!chunksection.n_1700_B(i, j & 0xF, k).n_1700_B(block)) {
            return null;
        }
        if (block1 instanceof k_2789_z && (tileentity = this.getTileEntity(pos, n_1700_B.R_4764_Y)) != null) {
            tileentity.d_2427_y();
        }
        if (!this.world.Y_259_p) {
            state.n_1700_B(this.world, pos, blockstate, isMoving);
        }
        if (block instanceof k_2789_z) {
            i_2154_H tileentity1 = this.getTileEntity(pos, n_1700_B.R_4764_Y);
            if (tileentity1 == null) {
                tileentity1 = ((k_2789_z)((Object)block)).n_1700_B(this.world);
                this.world.n_1700_B(pos, tileentity1);
            } else {
                tileentity1.d_2427_y();
            }
        }
        this.dirty = true;
        return blockstate;
    }

    @Nullable
    public R_1900_x getWorldLightManager() {
        return this.world.q_2307_F().G_564_y();
    }

    @Override
    public void addEntity(N_4263_v entityIn) {
        int k;
        this.hasEntities = true;
        int i = u_530_F.R_4764_Y(entityIn.O_3598_v() / 16.0);
        int j = u_530_F.R_4764_Y(entityIn.l_2647_k() / 16.0);
        if (i != this.pos.J_1907_R || j != this.pos.R_4764_Y) {
            LOGGER.warn("Wrong location! ({}, {}) should be ({}, {}), {}", (Object)i, (Object)j, (Object)this.pos.J_1907_R, (Object)this.pos.R_4764_Y, (Object)entityIn);
            entityIn.t_4219_U = true;
        }
        if ((k = u_530_F.R_4764_Y(entityIn.X_2960_b() / 16.0)) < 0) {
            k = 0;
        }
        if (k >= this.entityLists.length) {
            k = this.entityLists.length - 1;
        }
        entityIn.y_1700_S = true;
        entityIn.u_744_e = this.pos.J_1907_R;
        entityIn.RetryCallException = k;
        entityIn.r_3651_U = this.pos.R_4764_Y;
        this.entityLists[k].add(entityIn);
        A_4115_X.n_1700_B(new x_92_N(entityIn));
    }

    @Override
    public void setHeightmap(z_2963_s.n_1700_B type, long[] data) {
        this.heightMap.get(type).n_1700_B(data);
    }

    public void removeEntity(N_4263_v entityIn) {
        this.removeEntityAtIndex(entityIn, entityIn.RetryCallException);
    }

    public void removeEntityAtIndex(N_4263_v entityIn, int index) {
        if (index < 0) {
            index = 0;
        }
        if (index >= this.entityLists.length) {
            index = this.entityLists.length - 1;
        }
        this.entityLists[index].remove(entityIn);
    }

    @Override
    public int getTopBlockY(z_2963_s.n_1700_B heightmapType, int x, int z) {
        return this.heightMap.get(heightmapType).n_1700_B(x & 0xF, z & 0xF) - 1;
    }

    @Nullable
    private i_2154_H createNewTileEntity(c_1514_x pos) {
        K_4074_S blockstate = this.getBlockState(pos);
        T_2915_h block = blockstate.J_1907_R();
        return !block.G_564_y() ? null : ((k_2789_z)((Object)block)).n_1700_B(this.world);
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return this.getTileEntity(pos, n_1700_B.R_4764_Y);
    }

    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos, n_1700_B creationMode) {
        i_2154_H tileentity1;
        U_2912_j compoundnbt;
        i_2154_H tileentity = this.tileEntities.get(pos);
        if (tileentity == null && (compoundnbt = this.deferredTileEntities.remove(pos)) != null && (tileentity1 = this.setDeferredTileEntity(pos, compoundnbt)) != null) {
            return tileentity1;
        }
        if (tileentity == null) {
            if (creationMode == n_1700_B.n_1700_B) {
                tileentity = this.createNewTileEntity(pos);
                this.world.n_1700_B(pos, tileentity);
            }
        } else if (tileentity.n_3318_d()) {
            this.tileEntities.remove(pos);
            return null;
        }
        return tileentity;
    }

    public void addTileEntity(i_2154_H tileEntityIn) {
        this.addTileEntity(tileEntityIn.x_607_J(), tileEntityIn);
        if (this.loaded || this.world.v_4276_D()) {
            this.world.n_1700_B(tileEntityIn.x_607_J(), tileEntityIn);
        }
    }

    @Override
    public void addTileEntity(c_1514_x pos, i_2154_H tileEntityIn) {
        if (this.getBlockState(pos).J_1907_R() instanceof k_2789_z) {
            tileEntityIn.J_1907_R(this.world, pos);
            tileEntityIn.M_182_A();
            i_2154_H tileentity = this.tileEntities.put(pos.toImmutable(), tileEntityIn);
            if (tileentity != null && tileentity != tileEntityIn) {
                tileentity.I_();
            }
        }
    }

    @Override
    public void addTileEntity(U_2912_j nbt) {
        this.deferredTileEntities.put(new c_1514_x(nbt.w_1484_f("x"), nbt.w_1484_f("y"), nbt.w_1484_f("z")), nbt);
    }

    @Override
    @Nullable
    public U_2912_j getTileEntityNBT(c_1514_x pos) {
        i_2154_H tileentity = this.getTileEntity(pos);
        if (tileentity != null && !tileentity.n_3318_d()) {
            U_2912_j compoundnbt1 = tileentity.n_1700_B(new U_2912_j());
            compoundnbt1.n_1700_B("keepPacked", false);
            return compoundnbt1;
        }
        U_2912_j compoundnbt = this.deferredTileEntities.get(pos);
        if (compoundnbt != null) {
            compoundnbt = compoundnbt.v_4262_N();
            compoundnbt.n_1700_B("keepPacked", true);
        }
        return compoundnbt;
    }

    @Override
    public void removeTileEntity(c_1514_x pos) {
        i_2154_H tileentity;
        if ((this.loaded || this.world.v_4276_D()) && (tileentity = this.tileEntities.remove(pos)) != null) {
            tileentity.I_();
        }
    }

    public void postLoad() {
        if (this.postLoadConsumer != null) {
            this.postLoadConsumer.accept(this);
            this.postLoadConsumer = null;
        }
    }

    public void markDirty() {
        this.dirty = true;
    }

    public void getEntitiesWithinAABBForEntity(@Nullable N_4263_v entityIn, I_4817_s aabb, List<N_4263_v> listToFill, @Nullable Predicate<? super N_4263_v> filter) {
        int i = u_530_F.R_4764_Y((aabb.minY - 2.0) / 16.0);
        int j = u_530_F.R_4764_Y((aabb.maxY + 2.0) / 16.0);
        i = u_530_F.n_1700_B(i, 0, this.entityLists.length - 1);
        j = u_530_F.n_1700_B(j, 0, this.entityLists.length - 1);
        for (int k = i; k <= j; ++k) {
            e_1322_b<N_4263_v> classinheritancemultimap = this.entityLists[k];
            List<N_4263_v> list = classinheritancemultimap.n_1700_B();
            int l = list.size();
            for (int i1 = 0; i1 < l; ++i1) {
                N_4263_v entity = list.get(i1);
                if (!entity.i_601_W().intersects(aabb) || entity == entityIn) continue;
                if (filter == null || filter.test(entity)) {
                    listToFill.add(entity);
                }
                if (!(entity instanceof b_2971_b)) continue;
                for (EnderDragonPart enderdragonpartentity : ((b_2971_b)entity).Q_4569_t()) {
                    if (enderdragonpartentity == entityIn || !enderdragonpartentity.i_601_W().intersects(aabb) || filter != null && !filter.test(enderdragonpartentity)) continue;
                    listToFill.add(enderdragonpartentity);
                }
            }
        }
    }

    public <T extends N_4263_v> void getEntitiesWithinAABBForList(@Nullable t_5_h<?> entitytypeIn, I_4817_s aabb, List<? super T> list, Predicate<? super T> filter) {
        int i = u_530_F.R_4764_Y((aabb.minY - 2.0) / 16.0);
        int j = u_530_F.R_4764_Y((aabb.maxY + 2.0) / 16.0);
        i = u_530_F.n_1700_B(i, 0, this.entityLists.length - 1);
        j = u_530_F.n_1700_B(j, 0, this.entityLists.length - 1);
        for (int k = i; k <= j; ++k) {
            for (N_4263_v entity : this.entityLists[k].n_1700_B(N_4263_v.class)) {
                if (entitytypeIn != null && entity.f_4016_n() != entitytypeIn || !entity.i_601_W().intersects(aabb) || !filter.test(entity)) continue;
                list.add(entity);
            }
        }
    }

    public <T extends N_4263_v> void getEntitiesOfTypeWithinAABB(Class<? extends T> entityClass, I_4817_s aabb, List<T> listToFill, @Nullable Predicate<? super T> filter) {
        int i = u_530_F.R_4764_Y((aabb.minY - 2.0) / 16.0);
        int j = u_530_F.R_4764_Y((aabb.maxY + 2.0) / 16.0);
        i = u_530_F.n_1700_B(i, 0, this.entityLists.length - 1);
        j = u_530_F.n_1700_B(j, 0, this.entityLists.length - 1);
        for (int k = i; k <= j; ++k) {
            for (N_4263_v t : this.entityLists[k].n_1700_B(entityClass)) {
                if (!t.i_601_W().intersects(aabb) || filter != null && !filter.test(t)) continue;
                listToFill.add(t);
            }
        }
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public Y_1387_d getPos() {
        return this.pos;
    }

    public void read(@Nullable c_1108_W biomeContainerIn, b_2585_i packetBufferIn, U_2912_j nbtIn, int availableSections) {
        boolean flag = biomeContainerIn != null;
        Predicate<c_1514_x> predicate = flag ? pos -> true : pos -> (availableSections & 1 << (pos.getY() >> 4)) != 0;
        Sets.newHashSet(this.tileEntities.keySet()).stream().filter(predicate).forEach(this.world::t_1786_h);
        for (int i = 0; i < this.sections.length; ++i) {
            P_3550_Z chunksection = this.sections[i];
            if ((availableSections & 1 << i) == 0) {
                if (!flag || chunksection == EMPTY_SECTION) continue;
                this.sections[i] = EMPTY_SECTION;
                continue;
            }
            if (chunksection == EMPTY_SECTION) {
                this.sections[i] = chunksection = new P_3550_Z(i << 4);
            }
            chunksection.n_1700_B(packetBufferIn);
        }
        if (biomeContainerIn != null) {
            this.blockBiomeArray = biomeContainerIn;
        }
        for (z_2963_s.n_1700_B heightmap$type : z_2963_s.n_1700_B.values()) {
            String s = heightmap$type.J_1907_R();
            if (!nbtIn.R_4764_Y(s, 12)) continue;
            this.setHeightmap(heightmap$type, nbtIn.Q_4569_t(s));
        }
        for (i_2154_H tileentity : this.tileEntities.values()) {
            tileentity.d_2427_y();
        }
    }

    @Override
    public c_1108_W getBiomes() {
        return this.blockBiomeArray;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public b_4507_u getWorld() {
        return this.world;
    }

    @Override
    public Collection<Map.Entry<z_2963_s.n_1700_B, z_2963_s>> getHeightmaps() {
        return Collections.unmodifiableSet(this.heightMap.entrySet());
    }

    public Map<c_1514_x, i_2154_H> getTileEntityMap() {
        return this.tileEntities;
    }

    public e_1322_b<N_4263_v>[] getEntityLists() {
        return this.entityLists;
    }

    @Override
    public U_2912_j getDeferredTileEntity(c_1514_x pos) {
        return this.deferredTileEntities.get(pos);
    }

    @Override
    public Stream<c_1514_x> getLightSources() {
        return StreamSupport.stream(c_1514_x.getAllInBoxMutable(this.pos.J_1907_R(), 0, this.pos.R_4764_Y(), this.pos.G_564_y(), 255, this.pos.P_1922_E()).spliterator(), false).filter(pos -> this.getBlockState((c_1514_x)pos).u_1723_Y() != 0);
    }

    @Override
    public TickList<T_2915_h> getBlocksToBeTicked() {
        return this.blocksToBeTicked;
    }

    @Override
    public TickList<Fluid> getFluidsToBeTicked() {
        return this.fluidsToBeTicked;
    }

    @Override
    public void setModified(boolean modified) {
        this.dirty = modified;
    }

    @Override
    public boolean isModified() {
        return this.dirty || this.hasEntities && this.world.X_933_l() != this.lastSaveTime;
    }

    public void setHasEntities(boolean hasEntitiesIn) {
        this.hasEntities = hasEntitiesIn;
    }

    @Override
    public void setLastSaveTime(long saveTime) {
        this.lastSaveTime = saveTime;
    }

    @Override
    @Nullable
    public StructureStart<?> func_230342_a_(StructureFeature<?> p_230342_1_) {
        return this.structureStarts.get(p_230342_1_);
    }

    @Override
    public void func_230344_a_(StructureFeature<?> p_230344_1_, StructureStart<?> p_230344_2_) {
        this.structureStarts.put(p_230344_1_, p_230344_2_);
    }

    @Override
    public Map<StructureFeature<?>, StructureStart<?>> getStructureStarts() {
        return this.structureStarts;
    }

    @Override
    public void setStructureStarts(Map<StructureFeature<?>, StructureStart<?>> structureStartsIn) {
        this.structureStarts.clear();
        this.structureStarts.putAll(structureStartsIn);
    }

    @Override
    public LongSet func_230346_b_(StructureFeature<?> p_230346_1_) {
        return this.structureReferences.computeIfAbsent(p_230346_1_, structureIn -> new LongOpenHashSet());
    }

    @Override
    public void func_230343_a_(StructureFeature<?> p_230343_1_, long p_230343_2_) {
        this.structureReferences.computeIfAbsent(p_230343_1_, structureIn -> new LongOpenHashSet()).add(p_230343_2_);
    }

    @Override
    public Map<StructureFeature<?>, LongSet> getStructureReferences() {
        return this.structureReferences;
    }

    @Override
    public void setStructureReferences(Map<StructureFeature<?>, LongSet> structureReferences) {
        this.structureReferences.clear();
        this.structureReferences.putAll(structureReferences);
    }

    @Override
    public long getInhabitedTime() {
        return this.inhabitedTime;
    }

    @Override
    public void setInhabitedTime(long newInhabitedTime) {
        this.inhabitedTime = newInhabitedTime;
    }

    public void postProcess() {
        Y_1387_d chunkpos = this.getPos();
        for (int i = 0; i < this.packedBlockPositions.length; ++i) {
            if (this.packedBlockPositions[i] == null) continue;
            for (Short oshort : this.packedBlockPositions[i]) {
                c_1514_x blockpos = n_1254_X.n_1700_B(oshort, i, chunkpos);
                K_4074_S blockstate = this.getBlockState(blockpos);
                K_4074_S blockstate1 = T_2915_h.J_1907_R(blockstate, this.world, blockpos);
                this.world.n_1700_B(blockpos, blockstate1, 20);
            }
            this.packedBlockPositions[i].clear();
        }
        this.rescheduleTicks();
        for (c_1514_x blockpos1 : Sets.newHashSet(this.deferredTileEntities.keySet())) {
            this.getTileEntity(blockpos1);
        }
        this.deferredTileEntities.clear();
        this.upgradeData.n_1700_B(this);
    }

    @Nullable
    private i_2154_H setDeferredTileEntity(c_1514_x pos, U_2912_j compound) {
        i_2154_H tileentity;
        K_4074_S blockstate = this.getBlockState(pos);
        if ("DUMMY".equals(compound.M_588_G("id"))) {
            T_2915_h block = blockstate.J_1907_R();
            if (block instanceof k_2789_z) {
                tileentity = ((k_2789_z)((Object)block)).n_1700_B(this.world);
            } else {
                tileentity = null;
                LOGGER.warn("Tried to load a DUMMY block entity @ {} but found not block entity block {} at location", (Object)pos, (Object)blockstate);
            }
        } else {
            tileentity = i_2154_H.J_1907_R(blockstate, compound);
        }
        if (tileentity != null) {
            tileentity.J_1907_R(this.world, pos);
            this.addTileEntity(tileentity);
        } else {
            LOGGER.warn("Tried to load a block entity for block {} but failed at location {}", (Object)blockstate, (Object)pos);
        }
        return tileentity;
    }

    @Override
    public r_3634_h getUpgradeData() {
        return this.upgradeData;
    }

    @Override
    public ShortList[] getPackedPositions() {
        return this.packedBlockPositions;
    }

    public void rescheduleTicks() {
        if (this.blocksToBeTicked instanceof ProtoTickList) {
            ((ProtoTickList)this.blocksToBeTicked).n_1700_B(this.world.u_2550_I(), (c_1514_x pos) -> this.getBlockState((c_1514_x)pos).J_1907_R());
            this.blocksToBeTicked = EmptyTickList.n_1700_B();
        } else if (this.blocksToBeTicked instanceof x_4674_u) {
            ((x_4674_u)this.blocksToBeTicked).n_1700_B(this.world.u_2550_I());
            this.blocksToBeTicked = EmptyTickList.n_1700_B();
        }
        if (this.fluidsToBeTicked instanceof ProtoTickList) {
            ((ProtoTickList)this.fluidsToBeTicked).n_1700_B(this.world.M_588_G(), (c_1514_x pos) -> this.getFluidState((c_1514_x)pos).n_1700_B());
            this.fluidsToBeTicked = EmptyTickList.n_1700_B();
        } else if (this.fluidsToBeTicked instanceof x_4674_u) {
            ((x_4674_u)this.fluidsToBeTicked).n_1700_B(this.world.M_588_G());
            this.fluidsToBeTicked = EmptyTickList.n_1700_B();
        }
    }

    public void saveScheduledTicks(e_3591_l serverWorldIn) {
        if (this.blocksToBeTicked == EmptyTickList.n_1700_B()) {
            this.blocksToBeTicked = new x_4674_u<T_2915_h>(V_3137_a.q_4610_l::J_1907_R, serverWorldIn.Q_2552_b().n_1700_B(this.pos, true, false), serverWorldIn.X_933_l());
            this.setModified(true);
        }
        if (this.fluidsToBeTicked == EmptyTickList.n_1700_B()) {
            this.fluidsToBeTicked = new x_4674_u<Fluid>(V_3137_a.G_624_v::J_1907_R, serverWorldIn.C_2741_M().n_1700_B(this.pos, true, false), serverWorldIn.X_933_l());
            this.setModified(true);
        }
    }

    @Override
    public ChunkStatus getStatus() {
        return ChunkStatus.P_4830_p;
    }

    public y_3683_b.G_564_y getLocationType() {
        return this.locationType == null ? y_3683_b.G_564_y.J_1907_R : this.locationType.get();
    }

    public void setLocationType(Supplier<y_3683_b.G_564_y> locationTypeIn) {
        this.locationType = locationTypeIn;
    }

    @Override
    public boolean hasLight() {
        return this.lightCorrect;
    }

    @Override
    public void setLight(boolean lightCorrectIn) {
        this.lightCorrect = lightCorrectIn;
        this.setModified(true);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.H_1748_a$n_1700_B.n_1700_B();
        }
    }
}


