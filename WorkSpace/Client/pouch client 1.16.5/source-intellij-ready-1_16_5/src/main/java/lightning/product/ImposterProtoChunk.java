/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.BitSet;
import java.util.Map;
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
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.StructureStart;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.n_1254_X;
import lightning.product.r_3634_h;
import lightning.product.Fluid;
import lightning.product.ProtoTickList;
import lightning.product.z_2963_s;

public class ImposterProtoChunk
extends n_1254_X {
    private final H_1748_a n_1700_B;

    public ImposterProtoChunk(H_1748_a chunk) {
        super(chunk.getPos(), r_3634_h.n_1700_B);
        this.n_1700_B = chunk;
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return this.n_1700_B.getTileEntity(pos);
    }

    @Override
    @Nullable
    public K_4074_S getBlockState(c_1514_x pos) {
        return this.n_1700_B.getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return this.n_1700_B.getFluidState(pos);
    }

    @Override
    public int Z_875_P() {
        return this.n_1700_B.Z_875_P();
    }

    @Override
    @Nullable
    public K_4074_S setBlockState(c_1514_x pos, K_4074_S state, boolean isMoving) {
        return null;
    }

    @Override
    public void addTileEntity(c_1514_x pos, i_2154_H tileEntityIn) {
    }

    @Override
    public void addEntity(N_4263_v entityIn) {
    }

    @Override
    public void n_1700_B(ChunkStatus status) {
    }

    @Override
    public P_3550_Z[] getSections() {
        return this.n_1700_B.getSections();
    }

    @Override
    @Nullable
    public R_1900_x G_564_y() {
        return this.n_1700_B.getWorldLightManager();
    }

    @Override
    public void setHeightmap(z_2963_s.n_1700_B type, long[] data) {
    }

    private z_2963_s.n_1700_B n_1700_B(z_2963_s.n_1700_B type) {
        if (type == z_2963_s.n_1700_B.n_1700_B) {
            return z_2963_s.n_1700_B.J_1907_R;
        }
        return type == z_2963_s.n_1700_B.R_4764_Y ? z_2963_s.n_1700_B.G_564_y : type;
    }

    @Override
    public int getTopBlockY(z_2963_s.n_1700_B heightmapType, int x, int z) {
        return this.n_1700_B.getTopBlockY(this.n_1700_B(heightmapType), x, z);
    }

    @Override
    public Y_1387_d getPos() {
        return this.n_1700_B.getPos();
    }

    @Override
    public void setLastSaveTime(long saveTime) {
    }

    @Override
    @Nullable
    public StructureStart<?> func_230342_a_(StructureFeature<?> p_230342_1_) {
        return this.n_1700_B.func_230342_a_(p_230342_1_);
    }

    @Override
    public void func_230344_a_(StructureFeature<?> p_230344_1_, StructureStart<?> p_230344_2_) {
    }

    @Override
    public Map<StructureFeature<?>, StructureStart<?>> getStructureStarts() {
        return this.n_1700_B.getStructureStarts();
    }

    @Override
    public void setStructureStarts(Map<StructureFeature<?>, StructureStart<?>> structureStartsIn) {
    }

    @Override
    public LongSet func_230346_b_(StructureFeature<?> p_230346_1_) {
        return this.n_1700_B.func_230346_b_(p_230346_1_);
    }

    @Override
    public void func_230343_a_(StructureFeature<?> p_230343_1_, long p_230343_2_) {
    }

    @Override
    public Map<StructureFeature<?>, LongSet> getStructureReferences() {
        return this.n_1700_B.getStructureReferences();
    }

    @Override
    public void setStructureReferences(Map<StructureFeature<?>, LongSet> structureReferences) {
    }

    @Override
    public c_1108_W getBiomes() {
        return this.n_1700_B.getBiomes();
    }

    @Override
    public void setModified(boolean modified) {
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public ChunkStatus getStatus() {
        return this.n_1700_B.getStatus();
    }

    @Override
    public void removeTileEntity(c_1514_x pos) {
    }

    @Override
    public void P_1922_E(c_1514_x pos) {
    }

    @Override
    public void addTileEntity(U_2912_j nbt) {
    }

    @Override
    @Nullable
    public U_2912_j getDeferredTileEntity(c_1514_x pos) {
        return this.n_1700_B.getDeferredTileEntity(pos);
    }

    @Override
    @Nullable
    public U_2912_j getTileEntityNBT(c_1514_x pos) {
        return this.n_1700_B.getTileEntityNBT(pos);
    }

    @Override
    public void n_1700_B(c_1108_W biomes) {
    }

    @Override
    public Stream<c_1514_x> getLightSources() {
        return this.n_1700_B.getLightSources();
    }

    @Override
    public ProtoTickList<T_2915_h> P_1922_E() {
        return new ProtoTickList<T_2915_h>(block -> block.multiplayerClientSuggestionProvider().v_4262_N(), this.getPos());
    }

    @Override
    public ProtoTickList<Fluid> u_1723_Y() {
        return new ProtoTickList<Fluid>(fluid -> fluid == Fluids.n_1700_B, this.getPos());
    }

    @Override
    public BitSet n_1700_B(T_3975_o.n_1700_B type) {
        throw j_3341_s.R_4764_Y(new UnsupportedOperationException("Meaningless in this context"));
    }

    @Override
    public BitSet J_1907_R(T_3975_o.n_1700_B type) {
        throw j_3341_s.R_4764_Y(new UnsupportedOperationException("Meaningless in this context"));
    }

    public H_1748_a w_1484_f() {
        return this.n_1700_B;
    }

    @Override
    public boolean hasLight() {
        return this.n_1700_B.hasLight();
    }

    @Override
    public void setLight(boolean lightCorrectIn) {
        this.n_1700_B.setLight(lightCorrectIn);
    }

    @Override
    public /* synthetic */ TickList getFluidsToBeTicked() {
        return this.u_1723_Y();
    }

    @Override
    public /* synthetic */ TickList getBlocksToBeTicked() {
        return this.P_1922_E();
    }
}


