/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import javax.annotation.Nullable;
import lightning.product.DataLayer;

public abstract class DataLayerStorageMap<M extends DataLayerStorageMap<M>> {
    private final long[] J_1907_R = new long[2];
    private final DataLayer[] R_4764_Y = new DataLayer[2];
    private boolean G_564_y;
    protected final Long2ObjectOpenHashMap<DataLayer> n_1700_B;

    protected DataLayerStorageMap(Long2ObjectOpenHashMap<DataLayer> arrayStorage) {
        this.n_1700_B = arrayStorage;
        this.R_4764_Y();
        this.G_564_y = true;
    }

    public abstract M J_1907_R();

    public void n_1700_B(long sectionPosIn) {
        this.n_1700_B.put(sectionPosIn, (Object)((DataLayer)this.n_1700_B.get(sectionPosIn)).J_1907_R());
        this.R_4764_Y();
    }

    public boolean J_1907_R(long sectionPosIn) {
        return this.n_1700_B.containsKey(sectionPosIn);
    }

    @Nullable
    public DataLayer R_4764_Y(long sectionPosIn) {
        DataLayer nibblearray;
        if (this.G_564_y) {
            for (int i = 0; i < 2; ++i) {
                if (sectionPosIn != this.J_1907_R[i]) continue;
                return this.R_4764_Y[i];
            }
        }
        if ((nibblearray = (DataLayer)this.n_1700_B.get(sectionPosIn)) == null) {
            return null;
        }
        if (this.G_564_y) {
            for (int j = 1; j > 0; --j) {
                this.J_1907_R[j] = this.J_1907_R[j - 1];
                this.R_4764_Y[j] = this.R_4764_Y[j - 1];
            }
            this.J_1907_R[0] = sectionPosIn;
            this.R_4764_Y[0] = nibblearray;
        }
        return nibblearray;
    }

    @Nullable
    public DataLayer G_564_y(long sectionPosIn) {
        return (DataLayer)this.n_1700_B.remove(sectionPosIn);
    }

    public void n_1700_B(long sectionPosIn, DataLayer array) {
        this.n_1700_B.put(sectionPosIn, (Object)array);
    }

    public void R_4764_Y() {
        for (int i = 0; i < 2; ++i) {
            this.J_1907_R[i] = Long.MAX_VALUE;
            this.R_4764_Y[i] = null;
        }
    }

    public void G_564_y() {
        this.G_564_y = false;
    }
}


