/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.shorts.ShortArrayList
 *  it.unimi.dsi.fastutil.shorts.ShortList
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 */
package lightning.product;

import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.FeatureAccess;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.i_2154_H;
import lightning.product.r_3634_h;
import lightning.product.Fluid;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;

public interface ChunkAccess
extends BlockGetter,
FeatureAccess {
    @Nullable
    public K_4074_S setBlockState(c_1514_x var1, K_4074_S var2, boolean var3);

    public void addTileEntity(c_1514_x var1, i_2154_H var2);

    public void addEntity(N_4263_v var1);

    @Nullable
    default public P_3550_Z t_148_a() {
        P_3550_Z[] achunksection = this.getSections();
        for (int i = achunksection.length - 1; i >= 0; --i) {
            P_3550_Z chunksection = achunksection[i];
            if (P_3550_Z.n_1700_B(chunksection)) continue;
            return chunksection;
        }
        return null;
    }

    default public int s_956_w() {
        P_3550_Z chunksection = this.t_148_a();
        return chunksection == null ? 0 : chunksection.v_4262_N();
    }

    public Set<c_1514_x> getTileEntitiesPos();

    public P_3550_Z[] getSections();

    public Collection<Map.Entry<z_2963_s.n_1700_B, z_2963_s>> getHeightmaps();

    public void setHeightmap(z_2963_s.n_1700_B var1, long[] var2);

    public z_2963_s getHeightmap(z_2963_s.n_1700_B var1);

    public int getTopBlockY(z_2963_s.n_1700_B var1, int var2, int var3);

    public Y_1387_d getPos();

    public void setLastSaveTime(long var1);

    public Map<StructureFeature<?>, StructureStart<?>> getStructureStarts();

    public void setStructureStarts(Map<StructureFeature<?>, StructureStart<?>> var1);

    default public boolean n_1700_B(int startY, int endY) {
        if (startY < 0) {
            startY = 0;
        }
        if (endY >= 256) {
            endY = 255;
        }
        for (int i = startY; i <= endY; i += 16) {
            if (P_3550_Z.n_1700_B(this.getSections()[i >> 4])) continue;
            return false;
        }
        return true;
    }

    @Nullable
    public c_1108_W getBiomes();

    public void setModified(boolean var1);

    public boolean isModified();

    public ChunkStatus getStatus();

    public void removeTileEntity(c_1514_x var1);

    default public void P_1922_E(c_1514_x pos) {
        LogManager.getLogger().warn("Trying to mark a block for PostProcessing @ {}, but this operation is not supported.", (Object)pos);
    }

    public ShortList[] getPackedPositions();

    default public void J_1907_R(short packedPosition, int index) {
        ChunkAccess.n_1700_B(this.getPackedPositions(), index).add(packedPosition);
    }

    default public void addTileEntity(U_2912_j nbt) {
        LogManager.getLogger().warn("Trying to set a BlockEntity, but this operation is not supported.");
    }

    @Nullable
    public U_2912_j getDeferredTileEntity(c_1514_x var1);

    @Nullable
    public U_2912_j getTileEntityNBT(c_1514_x var1);

    public Stream<c_1514_x> getLightSources();

    public TickList<T_2915_h> getBlocksToBeTicked();

    public TickList<Fluid> getFluidsToBeTicked();

    public r_3634_h getUpgradeData();

    public void setInhabitedTime(long var1);

    public long getInhabitedTime();

    public static ShortList n_1700_B(ShortList[] packedPositions, int index) {
        if (packedPositions[index] == null) {
            packedPositions[index] = new ShortArrayList();
        }
        return packedPositions[index];
    }

    public boolean hasLight();

    public void setLight(boolean var1);
}


