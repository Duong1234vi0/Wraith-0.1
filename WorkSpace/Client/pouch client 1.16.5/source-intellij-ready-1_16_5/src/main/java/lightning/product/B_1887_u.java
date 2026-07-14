/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMaps
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import javax.annotation.Nullable;
import lightning.product.DataLayerStorageMap;
import lightning.product.SectionTracker;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.i_4702_v;
import lightning.product.LightChunkGetter;

public abstract class B_1887_u<M extends DataLayerStorageMap<M>>
extends SectionTracker {
    protected static final DataLayer n_1700_B = new DataLayer();
    private static final b_257_Y[] u_2550_I = b_257_Y.values();
    private final K_4719_o M_588_G;
    private final LightChunkGetter P_4830_p;
    protected final LongSet J_1907_R = new LongOpenHashSet();
    protected final LongSet R_4764_Y = new LongOpenHashSet();
    protected final LongSet G_564_y = new LongOpenHashSet();
    protected volatile M P_1922_E;
    protected final M u_1723_Y;
    protected final LongSet v_4262_N = new LongOpenHashSet();
    protected final LongSet w_1484_f = new LongOpenHashSet();
    protected final Long2ObjectMap<DataLayer> t_148_a = Long2ObjectMaps.synchronize((Long2ObjectMap)new Long2ObjectOpenHashMap());
    private final LongSet h_1847_R = new LongOpenHashSet();
    private final LongSet Q_4569_t = new LongOpenHashSet();
    private final LongSet M_182_A = new LongOpenHashSet();
    protected volatile boolean s_956_w;

    protected B_1887_u(K_4719_o lightTypeIn, LightChunkGetter chunkLightProvider, M dataMap) {
        super(3, 16, 256);
        this.M_588_G = lightTypeIn;
        this.P_4830_p = chunkLightProvider;
        this.u_1723_Y = dataMap;
        this.P_1922_E = ((DataLayerStorageMap)dataMap).J_1907_R();
        ((DataLayerStorageMap)this.P_1922_E).G_564_y();
    }

    protected boolean v_4262_N(long sectionPosIn) {
        return this.n_1700_B(sectionPosIn, true) != null;
    }

    @Nullable
    protected DataLayer n_1700_B(long sectionPosIn, boolean cached) {
        return this.n_1700_B(cached ? this.u_1723_Y : this.P_1922_E, sectionPosIn);
    }

    @Nullable
    protected DataLayer n_1700_B(M map, long sectionPosIn) {
        return ((DataLayerStorageMap)map).R_4764_Y(sectionPosIn);
    }

    @Nullable
    public DataLayer w_1484_f(long sectionPosIn) {
        DataLayer nibblearray = (DataLayer)this.t_148_a.get(sectionPosIn);
        return nibblearray != null ? nibblearray : this.n_1700_B(sectionPosIn, false);
    }

    protected abstract int G_564_y(long var1);

    protected int t_148_a(long worldPos) {
        long i = SectionPos.P_1922_E(worldPos);
        DataLayer nibblearray = this.n_1700_B(i, true);
        return nibblearray.n_1700_B(SectionPos.J_1907_R(c_1514_x.unpackX(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackY(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackZ(worldPos)));
    }

    protected void J_1907_R(long worldPos, int lightLevel) {
        long i = SectionPos.P_1922_E(worldPos);
        if (this.v_4262_N.add(i)) {
            ((DataLayerStorageMap)this.u_1723_Y).n_1700_B(i);
        }
        DataLayer nibblearray = this.n_1700_B(i, true);
        nibblearray.n_1700_B(SectionPos.J_1907_R(c_1514_x.unpackX(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackY(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackZ(worldPos)), lightLevel);
        for (int j = -1; j <= 1; ++j) {
            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    this.w_1484_f.add(SectionPos.P_1922_E(c_1514_x.offset(worldPos, k, l, j)));
                }
            }
        }
    }

    @Override
    protected int R_4764_Y(long sectionPosIn) {
        if (sectionPosIn == Long.MAX_VALUE) {
            return 2;
        }
        if (this.J_1907_R.contains(sectionPosIn)) {
            return 0;
        }
        return !this.M_182_A.contains(sectionPosIn) && ((DataLayerStorageMap)this.u_1723_Y).J_1907_R(sectionPosIn) ? 1 : 2;
    }

    @Override
    protected int J_1907_R(long pos) {
        if (this.R_4764_Y.contains(pos)) {
            return 2;
        }
        return !this.J_1907_R.contains(pos) && !this.G_564_y.contains(pos) ? 2 : 0;
    }

    @Override
    protected void n_1700_B(long sectionPosIn, int level) {
        int i = this.R_4764_Y(sectionPosIn);
        if (i != 0 && level == 0) {
            this.J_1907_R.add(sectionPosIn);
            this.G_564_y.remove(sectionPosIn);
        }
        if (i == 0 && level != 0) {
            this.J_1907_R.remove(sectionPosIn);
            this.R_4764_Y.remove(sectionPosIn);
        }
        if (i >= 2 && level != 2) {
            if (this.M_182_A.contains(sectionPosIn)) {
                this.M_182_A.remove(sectionPosIn);
            } else {
                ((DataLayerStorageMap)this.u_1723_Y).n_1700_B(sectionPosIn, this.s_956_w(sectionPosIn));
                this.v_4262_N.add(sectionPosIn);
                this.u_2550_I(sectionPosIn);
                for (int j = -1; j <= 1; ++j) {
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            this.w_1484_f.add(SectionPos.P_1922_E(c_1514_x.offset(sectionPosIn, k, l, j)));
                        }
                    }
                }
            }
        }
        if (i != 2 && level >= 2) {
            this.M_182_A.add(sectionPosIn);
        }
        this.s_956_w = !this.M_182_A.isEmpty();
    }

    protected DataLayer s_956_w(long sectionPosIn) {
        DataLayer nibblearray = (DataLayer)this.t_148_a.get(sectionPosIn);
        return nibblearray != null ? nibblearray : new DataLayer();
    }

    protected void n_1700_B(i_4702_v<?, ?> engine, long sectionPosIn) {
        if (engine.R_4764_Y() < 8192) {
            engine.n_1700_B(p_227469_2_ -> SectionPos.P_1922_E(p_227469_2_) == sectionPosIn);
        } else {
            int i = SectionPos.R_4764_Y(SectionPos.J_1907_R(sectionPosIn));
            int j = SectionPos.R_4764_Y(SectionPos.R_4764_Y(sectionPosIn));
            int k = SectionPos.R_4764_Y(SectionPos.G_564_y(sectionPosIn));
            for (int l = 0; l < 16; ++l) {
                for (int i1 = 0; i1 < 16; ++i1) {
                    for (int j1 = 0; j1 < 16; ++j1) {
                        long k1 = c_1514_x.pack(i + l, j + i1, k + j1);
                        engine.P_1922_E(k1);
                    }
                }
            }
        }
    }

    protected boolean n_1700_B() {
        return this.s_956_w;
    }

    protected void n_1700_B(i_4702_v<M, ?> engine, boolean updateSkyLight, boolean updateBlockLight) {
        if (this.n_1700_B() || !this.t_148_a.isEmpty()) {
            LongIterator longIterator = this.M_182_A.iterator();
            while (longIterator.hasNext()) {
                long i = (Long)longIterator.next();
                this.n_1700_B(engine, i);
                DataLayer nibblearray = (DataLayer)this.t_148_a.remove(i);
                DataLayer nibblearray1 = ((DataLayerStorageMap)this.u_1723_Y).G_564_y(i);
                if (!this.Q_4569_t.contains(SectionPos.u_1723_Y(i))) continue;
                if (nibblearray != null) {
                    this.t_148_a.put(i, (Object)nibblearray);
                    continue;
                }
                if (nibblearray1 == null) continue;
                this.t_148_a.put(i, (Object)nibblearray1);
            }
            ((DataLayerStorageMap)this.u_1723_Y).R_4764_Y();
            longIterator = this.M_182_A.iterator();
            while (longIterator.hasNext()) {
                long k = (Long)longIterator.next();
                this.M_588_G(k);
            }
            this.M_182_A.clear();
            this.s_956_w = false;
            for (Long2ObjectMap.Entry entry : this.t_148_a.long2ObjectEntrySet()) {
                long j = entry.getLongKey();
                if (!this.v_4262_N(j)) continue;
                DataLayer nibblearray2 = (DataLayer)entry.getValue();
                if (((DataLayerStorageMap)this.u_1723_Y).R_4764_Y(j) == nibblearray2) continue;
                this.n_1700_B(engine, j);
                ((DataLayerStorageMap)this.u_1723_Y).n_1700_B(j, nibblearray2);
                this.v_4262_N.add(j);
            }
            ((DataLayerStorageMap)this.u_1723_Y).R_4764_Y();
            if (!updateBlockLight) {
                longIterator = this.t_148_a.keySet().iterator();
                while (longIterator.hasNext()) {
                    long l = (Long)longIterator.next();
                    this.J_1907_R(engine, l);
                }
            } else {
                longIterator = this.h_1847_R.iterator();
                while (longIterator.hasNext()) {
                    long i1 = (Long)longIterator.next();
                    this.J_1907_R(engine, i1);
                }
            }
            this.h_1847_R.clear();
            ObjectIterator objectiterator = this.t_148_a.long2ObjectEntrySet().iterator();
            while (objectiterator.hasNext()) {
                Long2ObjectMap.Entry entry1 = (Long2ObjectMap.Entry)objectiterator.next();
                long j1 = entry1.getLongKey();
                if (!this.v_4262_N(j1)) continue;
                objectiterator.remove();
            }
        }
    }

    private void J_1907_R(i_4702_v<M, ?> p_241538_1_, long p_241538_2_) {
        if (this.v_4262_N(p_241538_2_)) {
            int i = SectionPos.R_4764_Y(SectionPos.J_1907_R(p_241538_2_));
            int j = SectionPos.R_4764_Y(SectionPos.R_4764_Y(p_241538_2_));
            int k = SectionPos.R_4764_Y(SectionPos.G_564_y(p_241538_2_));
            for (b_257_Y direction : u_2550_I) {
                long l = SectionPos.n_1700_B(p_241538_2_, direction);
                if (this.t_148_a.containsKey(l) || !this.v_4262_N(l)) continue;
                for (int i1 = 0; i1 < 16; ++i1) {
                    for (int j1 = 0; j1 < 16; ++j1) {
                        long k1;
                        long l1 = switch (direction) {
                            case b_257_Y.n_1700_B -> {
                                k1 = c_1514_x.pack(i + j1, j, k + i1);
                                yield c_1514_x.pack(i + j1, j - 1, k + i1);
                            }
                            case b_257_Y.J_1907_R -> {
                                k1 = c_1514_x.pack(i + j1, j + 16 - 1, k + i1);
                                yield c_1514_x.pack(i + j1, j + 16, k + i1);
                            }
                            case b_257_Y.R_4764_Y -> {
                                k1 = c_1514_x.pack(i + i1, j + j1, k);
                                yield c_1514_x.pack(i + i1, j + j1, k - 1);
                            }
                            case b_257_Y.G_564_y -> {
                                k1 = c_1514_x.pack(i + i1, j + j1, k + 16 - 1);
                                yield c_1514_x.pack(i + i1, j + j1, k + 16);
                            }
                            case b_257_Y.P_1922_E -> {
                                k1 = c_1514_x.pack(i, j + i1, k + j1);
                                yield c_1514_x.pack(i - 1, j + i1, k + j1);
                            }
                            default -> {
                                k1 = c_1514_x.pack(i + 16 - 1, j + i1, k + j1);
                                yield c_1514_x.pack(i + 16, j + i1, k + j1);
                            }
                        };
                        p_241538_1_.n_1700_B(k1, l1, p_241538_1_.J_1907_R(k1, l1, p_241538_1_.R_4764_Y(k1)), false);
                        p_241538_1_.n_1700_B(l1, k1, p_241538_1_.J_1907_R(l1, k1, p_241538_1_.R_4764_Y(l1)), false);
                    }
                }
            }
        }
    }

    protected void u_2550_I(long sectionPos) {
    }

    protected void M_588_G(long p_215523_1_) {
    }

    protected void J_1907_R(long p_215526_1_, boolean p_215526_3_) {
    }

    public void R_4764_Y(long sectionColumnPos, boolean retain) {
        if (retain) {
            this.Q_4569_t.add(sectionColumnPos);
        } else {
            this.Q_4569_t.remove(sectionColumnPos);
        }
    }

    protected void n_1700_B(long sectionPosIn, @Nullable DataLayer array, boolean p_215529_4_) {
        if (array != null) {
            this.t_148_a.put(sectionPosIn, (Object)array);
            if (!p_215529_4_) {
                this.h_1847_R.add(sectionPosIn);
            }
        } else {
            this.t_148_a.remove(sectionPosIn);
        }
    }

    protected void G_564_y(long sectionPosIn, boolean isEmpty) {
        boolean flag = this.J_1907_R.contains(sectionPosIn);
        if (!flag && !isEmpty) {
            this.G_564_y.add(sectionPosIn);
            this.n_1700_B(Long.MAX_VALUE, sectionPosIn, 0, true);
        }
        if (flag && isEmpty) {
            this.R_4764_Y.add(sectionPosIn);
            this.n_1700_B(Long.MAX_VALUE, sectionPosIn, 2, false);
        }
    }

    protected void P_1922_E() {
        if (this.J_1907_R()) {
            this.n_1700_B(Integer.MAX_VALUE);
        }
    }

    protected void u_1723_Y() {
        if (!this.v_4262_N.isEmpty()) {
            Object m = ((DataLayerStorageMap)this.u_1723_Y).J_1907_R();
            ((DataLayerStorageMap)m).G_564_y();
            this.P_1922_E = m;
            this.v_4262_N.clear();
        }
        if (!this.w_1484_f.isEmpty()) {
            LongIterator longiterator = this.w_1484_f.iterator();
            while (longiterator.hasNext()) {
                long i = longiterator.nextLong();
                this.P_4830_p.n_1700_B(this.M_588_G, SectionPos.n_1700_B(i));
            }
            this.w_1484_f.clear();
        }
    }
}


