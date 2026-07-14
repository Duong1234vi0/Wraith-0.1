/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.StructureFeature;
import lightning.product.MineshaftConfiguration;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.q_2896_o;
import lightning.product.r_4097_j;
import lightning.product.FeatureConfiguration;
import lightning.product.z_1753_f;
import lightning.product.z_3539_x;

public abstract class StructureStart<C extends FeatureConfiguration> {
    public static final StructureStart<?> n_1700_B = new StructureStart<MineshaftConfiguration>(StructureFeature.R_4764_Y, 0, 0, BoundingBox.n_1700_B(), 0, 0L){

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, MineshaftConfiguration p_230364_7_) {
        }
    };
    private final StructureFeature<C> P_1922_E;
    protected final List<E_3771_B> J_1907_R = Lists.newArrayList();
    protected BoundingBox R_4764_Y;
    private final int u_1723_Y;
    private final int v_4262_N;
    private int w_1484_f;
    protected final WorldgenRandom G_564_y;

    public StructureStart(StructureFeature<C> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, BoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
        this.P_1922_E = p_i225876_1_;
        this.u_1723_Y = p_i225876_2_;
        this.v_4262_N = p_i225876_3_;
        this.w_1484_f = p_i225876_5_;
        this.G_564_y = new WorldgenRandom();
        this.G_564_y.R_4764_Y(p_i225876_6_, p_i225876_2_, p_i225876_3_);
        this.R_4764_Y = p_i225876_4_;
    }

    public abstract void n_1700_B(r_4097_j var1, z_1753_f var2, b_2085_h var3, int var4, int var5, k_594_Q var6, C var7);

    public BoundingBox R_4764_Y() {
        return this.R_4764_Y;
    }

    public List<E_3771_B> G_564_y() {
        return this.J_1907_R;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(WorldGenLevel p_230366_1_, J_3017_d p_230366_2_, z_1753_f p_230366_3_, Random p_230366_4_, BoundingBox p_230366_5_, Y_1387_d p_230366_6_) {
        List<E_3771_B> list = this.J_1907_R;
        synchronized (list) {
            if (!this.J_1907_R.isEmpty()) {
                BoundingBox mutableboundingbox = this.J_1907_R.get((int)0).h_1847_R;
                z_3539_x vector3i = mutableboundingbox.v_4262_N();
                c_1514_x blockpos = new c_1514_x(vector3i.getX(), mutableboundingbox.J_1907_R, vector3i.getZ());
                Iterator<E_3771_B> iterator = this.J_1907_R.iterator();
                while (iterator.hasNext()) {
                    E_3771_B structurepiece = iterator.next();
                    if (!structurepiece.v_4262_N().n_1700_B(p_230366_5_) || structurepiece.n_1700_B(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_, blockpos)) continue;
                    iterator.remove();
                }
                this.J_1907_R();
            }
        }
    }

    protected void J_1907_R() {
        this.R_4764_Y = BoundingBox.n_1700_B();
        for (E_3771_B structurepiece : this.J_1907_R) {
            this.R_4764_Y.J_1907_R(structurepiece.v_4262_N());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public U_2912_j n_1700_B(int chunkX, int chunkZ) {
        U_2912_j compoundnbt = new U_2912_j();
        if (this.P_1922_E()) {
            compoundnbt.n_1700_B("id", V_3137_a.M_1641_O.J_1907_R(this.M_588_G()).toString());
            compoundnbt.J_1907_R("ChunkX", chunkX);
            compoundnbt.J_1907_R("ChunkZ", chunkZ);
            compoundnbt.J_1907_R("references", this.w_1484_f);
            compoundnbt.n_1700_B("BB", this.R_4764_Y.w_1484_f());
            q_2896_o lvt_4_1_ = new q_2896_o();
            List<E_3771_B> list = this.J_1907_R;
            synchronized (list) {
                for (E_3771_B structurepiece : this.J_1907_R) {
                    lvt_4_1_.add(structurepiece.u_1723_Y());
                }
            }
            compoundnbt.n_1700_B("Children", lvt_4_1_);
            return compoundnbt;
        }
        compoundnbt.n_1700_B("id", "INVALID");
        return compoundnbt;
    }

    protected void n_1700_B(int p_214628_1_, Random p_214628_2_, int p_214628_3_) {
        int i = p_214628_1_ - p_214628_3_;
        int j = this.R_4764_Y.P_1922_E() + 1;
        if (j < i) {
            j += p_214628_2_.nextInt(i - j);
        }
        int k = j - this.R_4764_Y.P_1922_E;
        this.R_4764_Y.n_1700_B(0, k, 0);
        for (E_3771_B structurepiece : this.J_1907_R) {
            structurepiece.n_1700_B(0, k, 0);
        }
    }

    protected void n_1700_B(Random p_214626_1_, int p_214626_2_, int p_214626_3_) {
        int i = p_214626_3_ - p_214626_2_ + 1 - this.R_4764_Y.P_1922_E();
        int j = i > 1 ? p_214626_2_ + p_214626_1_.nextInt(i) : p_214626_2_;
        int k = j - this.R_4764_Y.J_1907_R;
        this.R_4764_Y.n_1700_B(0, k, 0);
        for (E_3771_B structurepiece : this.J_1907_R) {
            structurepiece.n_1700_B(0, k, 0);
        }
    }

    public boolean P_1922_E() {
        return !this.J_1907_R.isEmpty();
    }

    public int u_1723_Y() {
        return this.u_1723_Y;
    }

    public int v_4262_N() {
        return this.v_4262_N;
    }

    public c_1514_x n_1700_B() {
        return new c_1514_x(this.u_1723_Y << 4, 0, this.v_4262_N << 4);
    }

    public boolean w_1484_f() {
        return this.w_1484_f < this.u_2550_I();
    }

    public void t_148_a() {
        ++this.w_1484_f;
    }

    public int s_956_w() {
        return this.w_1484_f;
    }

    protected int u_2550_I() {
        return 1;
    }

    public StructureFeature<?> M_588_G() {
        return this.P_1922_E;
    }
}


