/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.List;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.Tag;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.EmptyPoolElement;
import lightning.product.StructurePoolElement;
import lightning.product.l_4118_l;
import lightning.product.q_2896_o;
import lightning.product.y_3814_I;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PoolElementStructurePiece
extends E_3771_B {
    private static final Logger G_564_y = LogManager.getLogger();
    protected final StructurePoolElement n_1700_B;
    protected c_1514_x J_1907_R;
    private final int P_1922_E;
    protected final W_2163_m R_4764_Y;
    private final List<y_3814_I> u_1723_Y = Lists.newArrayList();
    private final b_2085_h v_4262_N;

    public PoolElementStructurePiece(b_2085_h p_i242036_1_, StructurePoolElement p_i242036_2_, c_1514_x p_i242036_3_, int p_i242036_4_, W_2163_m p_i242036_5_, BoundingBox p_i242036_6_) {
        super(StructurePieceType.l_1233_K, 0);
        this.v_4262_N = p_i242036_1_;
        this.n_1700_B = p_i242036_2_;
        this.J_1907_R = p_i242036_3_;
        this.P_1922_E = p_i242036_4_;
        this.R_4764_Y = p_i242036_5_;
        this.h_1847_R = p_i242036_6_;
    }

    public PoolElementStructurePiece(b_2085_h p_i242037_1_, U_2912_j p_i242037_2_) {
        super(StructurePieceType.l_1233_K, p_i242037_2_);
        this.v_4262_N = p_i242037_1_;
        this.J_1907_R = new c_1514_x(p_i242037_2_.w_1484_f("PosX"), p_i242037_2_.w_1484_f("PosY"), p_i242037_2_.w_1484_f("PosZ"));
        this.P_1922_E = p_i242037_2_.w_1484_f("ground_level_delta");
        this.n_1700_B = StructurePoolElement.R_4764_Y.parse((DynamicOps)l_4118_l.n_1700_B, (Object)p_i242037_2_.M_182_A("pool_element")).resultOrPartial(arg_0 -> ((Logger)G_564_y).error(arg_0)).orElse(EmptyPoolElement.J_1907_R);
        this.R_4764_Y = W_2163_m.valueOf(p_i242037_2_.M_588_G("rotation"));
        this.h_1847_R = this.n_1700_B.n_1700_B(p_i242037_1_, this.J_1907_R, this.R_4764_Y);
        q_2896_o listnbt = p_i242037_2_.G_564_y("junctions", 10);
        this.u_1723_Y.clear();
        listnbt.forEach(p_214827_1_ -> this.u_1723_Y.add(y_3814_I.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, p_214827_1_))));
    }

    @Override
    protected void n_1700_B(U_2912_j tagCompound) {
        tagCompound.J_1907_R("PosX", this.J_1907_R.getX());
        tagCompound.J_1907_R("PosY", this.J_1907_R.getY());
        tagCompound.J_1907_R("PosZ", this.J_1907_R.getZ());
        tagCompound.J_1907_R("ground_level_delta", this.P_1922_E);
        StructurePoolElement.R_4764_Y.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.n_1700_B).resultOrPartial(arg_0 -> ((Logger)G_564_y).error(arg_0)).ifPresent(p_237002_1_ -> tagCompound.n_1700_B("pool_element", (Tag)p_237002_1_));
        tagCompound.n_1700_B("rotation", this.R_4764_Y.name());
        q_2896_o listnbt = new q_2896_o();
        for (y_3814_I jigsawjunction : this.u_1723_Y) {
            listnbt.add((Tag)jigsawjunction.n_1700_B(l_4118_l.n_1700_B).getValue());
        }
        tagCompound.n_1700_B("junctions", listnbt);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
        return this.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_7_, false);
    }

    public boolean n_1700_B(WorldGenLevel p_237001_1_, J_3017_d p_237001_2_, z_1753_f p_237001_3_, Random p_237001_4_, BoundingBox p_237001_5_, c_1514_x p_237001_6_, boolean p_237001_7_) {
        return this.n_1700_B.n_1700_B(this.v_4262_N, p_237001_1_, p_237001_2_, p_237001_3_, this.J_1907_R, p_237001_6_, this.R_4764_Y, p_237001_5_, p_237001_4_, p_237001_7_);
    }

    @Override
    public void n_1700_B(int x, int y, int z) {
        super.n_1700_B(x, y, z);
        this.J_1907_R = this.J_1907_R.add(x, y, z);
    }

    @Override
    public W_2163_m n_1700_B() {
        return this.R_4764_Y;
    }

    public String toString() {
        return String.format("<%s | %s | %s | %s>", new Object[]{this.getClass().getSimpleName(), this.J_1907_R, this.R_4764_Y, this.n_1700_B});
    }

    public StructurePoolElement J_1907_R() {
        return this.n_1700_B;
    }

    public c_1514_x R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.P_1922_E;
    }

    public void n_1700_B(y_3814_I junction) {
        this.u_1723_Y.add(junction);
    }

    public List<y_3814_I> P_1922_E() {
        return this.u_1723_Y;
    }
}


