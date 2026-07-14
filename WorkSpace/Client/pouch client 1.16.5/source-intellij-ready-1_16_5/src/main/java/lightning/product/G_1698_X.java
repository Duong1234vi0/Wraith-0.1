/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Arrays;
import lightning.product.B_1887_u;
import lightning.product.DataLayerStorageMap;
import lightning.product.FlatDataLayer;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.i_4702_v;
import lightning.product.LightChunkGetter;

public class G_1698_X
extends B_1887_u<n_1700_B> {
    private static final b_257_Y[] u_2550_I = new b_257_Y[]{b_257_Y.R_4764_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E, b_257_Y.u_1723_Y};
    private final LongSet M_588_G = new LongOpenHashSet();
    private final LongSet P_4830_p = new LongOpenHashSet();
    private final LongSet h_1847_R = new LongOpenHashSet();
    private final LongSet Q_4569_t = new LongOpenHashSet();
    private volatile boolean M_182_A;

    protected G_1698_X(LightChunkGetter lightProvider) {
        super(K_4719_o.n_1700_B, lightProvider, new n_1700_B((Long2ObjectOpenHashMap<DataLayer>)new Long2ObjectOpenHashMap(), new Long2IntOpenHashMap(), Integer.MAX_VALUE));
    }

    @Override
    protected int G_564_y(long worldPos) {
        long i = SectionPos.P_1922_E(worldPos);
        int j = SectionPos.R_4764_Y(i);
        n_1700_B skylightstorage$storagemap = (n_1700_B)this.P_1922_E;
        int k = skylightstorage$storagemap.R_4764_Y.get(SectionPos.u_1723_Y(i));
        if (k != skylightstorage$storagemap.J_1907_R && j < k) {
            DataLayer nibblearray = this.n_1700_B(skylightstorage$storagemap, i);
            if (nibblearray == null) {
                worldPos = c_1514_x.atSectionBottomY(worldPos);
                while (nibblearray == null) {
                    i = SectionPos.n_1700_B(i, b_257_Y.J_1907_R);
                    if (++j >= k) {
                        return 15;
                    }
                    worldPos = c_1514_x.offset(worldPos, 0, 16, 0);
                    nibblearray = this.n_1700_B(skylightstorage$storagemap, i);
                }
            }
            return nibblearray.n_1700_B(SectionPos.J_1907_R(c_1514_x.unpackX(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackY(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackZ(worldPos)));
        }
        return 15;
    }

    @Override
    protected void u_2550_I(long sectionPos) {
        long j;
        int k;
        int i = SectionPos.R_4764_Y(sectionPos);
        if (((n_1700_B)this.u_1723_Y).J_1907_R > i) {
            ((n_1700_B)this.u_1723_Y).J_1907_R = i;
            ((n_1700_B)this.u_1723_Y).R_4764_Y.defaultReturnValue(((n_1700_B)this.u_1723_Y).J_1907_R);
        }
        if ((k = ((n_1700_B)this.u_1723_Y).R_4764_Y.get(j = SectionPos.u_1723_Y(sectionPos))) < i + 1) {
            ((n_1700_B)this.u_1723_Y).R_4764_Y.put(j, i + 1);
            if (this.Q_4569_t.contains(j)) {
                this.t_1786_h(sectionPos);
                if (k > ((n_1700_B)this.u_1723_Y).J_1907_R) {
                    long l = SectionPos.J_1907_R(SectionPos.J_1907_R(sectionPos), k - 1, SectionPos.G_564_y(sectionPos));
                    this.M_182_A(l);
                }
                this.v_4262_N();
            }
        }
    }

    private void M_182_A(long p_223403_1_) {
        this.h_1847_R.add(p_223403_1_);
        this.P_4830_p.remove(p_223403_1_);
    }

    private void t_1786_h(long p_223404_1_) {
        this.P_4830_p.add(p_223404_1_);
        this.h_1847_R.remove(p_223404_1_);
    }

    private void v_4262_N() {
        this.M_182_A = !this.P_4830_p.isEmpty() || !this.h_1847_R.isEmpty();
    }

    @Override
    protected void M_588_G(long p_215523_1_) {
        long i = SectionPos.u_1723_Y(p_215523_1_);
        boolean flag = this.Q_4569_t.contains(i);
        if (flag) {
            this.M_182_A(p_215523_1_);
        }
        int j = SectionPos.R_4764_Y(p_215523_1_);
        if (((n_1700_B)this.u_1723_Y).R_4764_Y.get(i) == j + 1) {
            long k = p_215523_1_;
            while (!this.v_4262_N(k) && this.J_1907_R(j)) {
                --j;
                k = SectionPos.n_1700_B(k, b_257_Y.n_1700_B);
            }
            if (this.v_4262_N(k)) {
                ((n_1700_B)this.u_1723_Y).R_4764_Y.put(i, j + 1);
                if (flag) {
                    this.t_1786_h(k);
                }
            } else {
                ((n_1700_B)this.u_1723_Y).R_4764_Y.remove(i);
            }
        }
        if (flag) {
            this.v_4262_N();
        }
    }

    @Override
    protected void J_1907_R(long p_215526_1_, boolean p_215526_3_) {
        this.P_1922_E();
        if (p_215526_3_ && this.Q_4569_t.add(p_215526_1_)) {
            int i = ((n_1700_B)this.u_1723_Y).R_4764_Y.get(p_215526_1_);
            if (i != ((n_1700_B)this.u_1723_Y).J_1907_R) {
                long j = SectionPos.J_1907_R(SectionPos.J_1907_R(p_215526_1_), i - 1, SectionPos.G_564_y(p_215526_1_));
                this.t_1786_h(j);
                this.v_4262_N();
            }
        } else if (!p_215526_3_) {
            this.Q_4569_t.remove(p_215526_1_);
        }
    }

    @Override
    protected boolean n_1700_B() {
        return super.n_1700_B() || this.M_182_A;
    }

    @Override
    protected DataLayer s_956_w(long sectionPosIn) {
        DataLayer nibblearray = (DataLayer)this.t_148_a.get(sectionPosIn);
        if (nibblearray != null) {
            return nibblearray;
        }
        long i = SectionPos.n_1700_B(sectionPosIn, b_257_Y.J_1907_R);
        int j = ((n_1700_B)this.u_1723_Y).R_4764_Y.get(SectionPos.u_1723_Y(sectionPosIn));
        if (j != ((n_1700_B)this.u_1723_Y).J_1907_R && SectionPos.R_4764_Y(i) < j) {
            DataLayer nibblearray1;
            while ((nibblearray1 = this.n_1700_B(i, true)) == null) {
                i = SectionPos.n_1700_B(i, b_257_Y.J_1907_R);
            }
            return new DataLayer(new FlatDataLayer(nibblearray1, 0).n_1700_B());
        }
        return new DataLayer();
    }

    @Override
    protected void n_1700_B(i_4702_v<n_1700_B, ?> engine, boolean updateSkyLight, boolean updateBlockLight) {
        super.n_1700_B(engine, updateSkyLight, updateBlockLight);
        if (updateSkyLight) {
            LongIterator longIterator;
            if (!this.P_4830_p.isEmpty()) {
                longIterator = this.P_4830_p.iterator();
                while (longIterator.hasNext()) {
                    long i = (Long)longIterator.next();
                    int j = this.R_4764_Y(i);
                    if (j == 2 || this.h_1847_R.contains(i) || !this.M_588_G.add(i)) continue;
                    if (j == 1) {
                        this.n_1700_B(engine, i);
                        if (this.v_4262_N.add(i)) {
                            ((n_1700_B)this.u_1723_Y).n_1700_B(i);
                        }
                        Arrays.fill(this.n_1700_B(i, true).n_1700_B(), (byte)-1);
                        int i3 = SectionPos.R_4764_Y(SectionPos.J_1907_R(i));
                        int k3 = SectionPos.R_4764_Y(SectionPos.R_4764_Y(i));
                        int i4 = SectionPos.R_4764_Y(SectionPos.G_564_y(i));
                        for (b_257_Y direction : u_2550_I) {
                            long j1 = SectionPos.n_1700_B(i, direction);
                            if (!this.h_1847_R.contains(j1) && (this.M_588_G.contains(j1) || this.P_4830_p.contains(j1)) || !this.v_4262_N(j1)) continue;
                            for (int k1 = 0; k1 < 16; ++k1) {
                                for (int l1 = 0; l1 < 16; ++l1) {
                                    long i2;
                                    long j2 = switch (direction) {
                                        case b_257_Y.R_4764_Y -> {
                                            i2 = c_1514_x.pack(i3 + k1, k3 + l1, i4);
                                            yield c_1514_x.pack(i3 + k1, k3 + l1, i4 - 1);
                                        }
                                        case b_257_Y.G_564_y -> {
                                            i2 = c_1514_x.pack(i3 + k1, k3 + l1, i4 + 16 - 1);
                                            yield c_1514_x.pack(i3 + k1, k3 + l1, i4 + 16);
                                        }
                                        case b_257_Y.P_1922_E -> {
                                            i2 = c_1514_x.pack(i3, k3 + k1, i4 + l1);
                                            yield c_1514_x.pack(i3 - 1, k3 + k1, i4 + l1);
                                        }
                                        default -> {
                                            i2 = c_1514_x.pack(i3 + 16 - 1, k3 + k1, i4 + l1);
                                            yield c_1514_x.pack(i3 + 16, k3 + k1, i4 + l1);
                                        }
                                    };
                                    engine.n_1700_B(i2, j2, engine.J_1907_R(i2, j2, 0), true);
                                }
                            }
                        }
                        for (int j4 = 0; j4 < 16; ++j4) {
                            for (int k4 = 0; k4 < 16; ++k4) {
                                long l4 = c_1514_x.pack(SectionPos.R_4764_Y(SectionPos.J_1907_R(i)) + j4, SectionPos.R_4764_Y(SectionPos.R_4764_Y(i)), SectionPos.R_4764_Y(SectionPos.G_564_y(i)) + k4);
                                long i5 = c_1514_x.pack(SectionPos.R_4764_Y(SectionPos.J_1907_R(i)) + j4, SectionPos.R_4764_Y(SectionPos.R_4764_Y(i)) - 1, SectionPos.R_4764_Y(SectionPos.G_564_y(i)) + k4);
                                engine.n_1700_B(l4, i5, engine.J_1907_R(l4, i5, 0), true);
                            }
                        }
                        continue;
                    }
                    for (int k = 0; k < 16; ++k) {
                        for (int l = 0; l < 16; ++l) {
                            long i1 = c_1514_x.pack(SectionPos.R_4764_Y(SectionPos.J_1907_R(i)) + k, SectionPos.R_4764_Y(SectionPos.R_4764_Y(i)) + 16 - 1, SectionPos.R_4764_Y(SectionPos.G_564_y(i)) + l);
                            engine.n_1700_B(Long.MAX_VALUE, i1, 0, true);
                        }
                    }
                }
            }
            this.P_4830_p.clear();
            if (!this.h_1847_R.isEmpty()) {
                longIterator = this.h_1847_R.iterator();
                while (longIterator.hasNext()) {
                    long k2 = (Long)longIterator.next();
                    if (!this.M_588_G.remove(k2) || !this.v_4262_N(k2)) continue;
                    for (int l2 = 0; l2 < 16; ++l2) {
                        for (int j3 = 0; j3 < 16; ++j3) {
                            long l3 = c_1514_x.pack(SectionPos.R_4764_Y(SectionPos.J_1907_R(k2)) + l2, SectionPos.R_4764_Y(SectionPos.R_4764_Y(k2)) + 16 - 1, SectionPos.R_4764_Y(SectionPos.G_564_y(k2)) + j3);
                            engine.n_1700_B(Long.MAX_VALUE, l3, 15, false);
                        }
                    }
                }
            }
            this.h_1847_R.clear();
            this.M_182_A = false;
        }
    }

    protected boolean J_1907_R(int p_215550_1_) {
        return p_215550_1_ >= ((n_1700_B)this.u_1723_Y).J_1907_R;
    }

    protected boolean P_4830_p(long p_215551_1_) {
        int i = c_1514_x.unpackY(p_215551_1_);
        if ((i & 0xF) != 15) {
            return false;
        }
        long j = SectionPos.P_1922_E(p_215551_1_);
        long k = SectionPos.u_1723_Y(j);
        if (!this.Q_4569_t.contains(k)) {
            return false;
        }
        int l = ((n_1700_B)this.u_1723_Y).R_4764_Y.get(k);
        return SectionPos.R_4764_Y(l) == i + 16;
    }

    protected boolean h_1847_R(long p_215549_1_) {
        long i = SectionPos.u_1723_Y(p_215549_1_);
        int j = ((n_1700_B)this.u_1723_Y).R_4764_Y.get(i);
        return j == ((n_1700_B)this.u_1723_Y).J_1907_R || SectionPos.R_4764_Y(p_215549_1_) >= j;
    }

    protected boolean Q_4569_t(long p_215548_1_) {
        long i = SectionPos.u_1723_Y(p_215548_1_);
        return this.Q_4569_t.contains(i);
    }

    public static final class n_1700_B
    extends DataLayerStorageMap<n_1700_B> {
        private int J_1907_R;
        private final Long2IntOpenHashMap R_4764_Y;

        public n_1700_B(Long2ObjectOpenHashMap<DataLayer> p_i50496_1_, Long2IntOpenHashMap p_i50496_2_, int p_i50496_3_) {
            super(p_i50496_1_);
            this.R_4764_Y = p_i50496_2_;
            p_i50496_2_.defaultReturnValue(p_i50496_3_);
            this.J_1907_R = p_i50496_3_;
        }

        public n_1700_B n_1700_B() {
            return new n_1700_B((Long2ObjectOpenHashMap<DataLayer>)this.n_1700_B.clone(), this.R_4764_Y.clone(), this.J_1907_R);
        }

        @Override
        public /* synthetic */ DataLayerStorageMap J_1907_R() {
            return this.n_1700_B();
        }
    }
}


