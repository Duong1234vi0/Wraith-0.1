/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lightning.product.B_1887_u;
import lightning.product.DataLayerStorageMap;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.LightChunkGetter;

public class m_689_s
extends B_1887_u<n_1700_B> {
    protected m_689_s(LightChunkGetter p_i51300_1_) {
        super(K_4719_o.J_1907_R, p_i51300_1_, new n_1700_B((Long2ObjectOpenHashMap<DataLayer>)new Long2ObjectOpenHashMap()));
    }

    @Override
    protected int G_564_y(long worldPos) {
        long i = SectionPos.P_1922_E(worldPos);
        DataLayer nibblearray = this.n_1700_B(i, false);
        return nibblearray == null ? 0 : nibblearray.n_1700_B(SectionPos.J_1907_R(c_1514_x.unpackX(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackY(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackZ(worldPos)));
    }

    public static final class n_1700_B
    extends DataLayerStorageMap<n_1700_B> {
        public n_1700_B(Long2ObjectOpenHashMap<DataLayer> arrayStorage) {
            super(arrayStorage);
        }

        public n_1700_B n_1700_B() {
            return new n_1700_B((Long2ObjectOpenHashMap<DataLayer>)this.n_1700_B.clone());
        }

        @Override
        public /* synthetic */ DataLayerStorageMap J_1907_R() {
            return this.n_1700_B();
        }
    }
}


