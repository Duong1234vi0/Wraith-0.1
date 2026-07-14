/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BoundingBox;
import lightning.product.W_2163_m;
import lightning.product.StructureProcessor;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.q_4099_E;
import lightning.product.u_530_F;

public class w_1748_S {
    private q_4099_E n_1700_B = q_4099_E.n_1700_B;
    private W_2163_m J_1907_R = W_2163_m.n_1700_B;
    private c_1514_x R_4764_Y = c_1514_x.ZERO;
    private boolean G_564_y;
    @Nullable
    private Y_1387_d P_1922_E;
    @Nullable
    private BoundingBox u_1723_Y;
    private boolean v_4262_N = true;
    @Nullable
    private Random w_1484_f;
    @Nullable
    private int t_148_a;
    private final List<StructureProcessor> s_956_w = Lists.newArrayList();
    private boolean u_2550_I;
    private boolean M_588_G;

    public w_1748_S n_1700_B() {
        w_1748_S placementsettings = new w_1748_S();
        placementsettings.n_1700_B = this.n_1700_B;
        placementsettings.J_1907_R = this.J_1907_R;
        placementsettings.R_4764_Y = this.R_4764_Y;
        placementsettings.G_564_y = this.G_564_y;
        placementsettings.P_1922_E = this.P_1922_E;
        placementsettings.u_1723_Y = this.u_1723_Y;
        placementsettings.v_4262_N = this.v_4262_N;
        placementsettings.w_1484_f = this.w_1484_f;
        placementsettings.t_148_a = this.t_148_a;
        placementsettings.s_956_w.addAll(this.s_956_w);
        placementsettings.u_2550_I = this.u_2550_I;
        placementsettings.M_588_G = this.M_588_G;
        return placementsettings;
    }

    public w_1748_S n_1700_B(q_4099_E mirrorIn) {
        this.n_1700_B = mirrorIn;
        return this;
    }

    public w_1748_S n_1700_B(W_2163_m rotationIn) {
        this.J_1907_R = rotationIn;
        return this;
    }

    public w_1748_S n_1700_B(c_1514_x center) {
        this.R_4764_Y = center;
        return this;
    }

    public w_1748_S n_1700_B(boolean ignoreEntitiesIn) {
        this.G_564_y = ignoreEntitiesIn;
        return this;
    }

    public w_1748_S n_1700_B(Y_1387_d chunkPosIn) {
        this.P_1922_E = chunkPosIn;
        return this;
    }

    public w_1748_S n_1700_B(BoundingBox boundingBoxIn) {
        this.u_1723_Y = boundingBoxIn;
        return this;
    }

    public w_1748_S n_1700_B(@Nullable Random randomIn) {
        this.w_1484_f = randomIn;
        return this;
    }

    public w_1748_S J_1907_R(boolean p_215223_1_) {
        this.u_2550_I = p_215223_1_;
        return this;
    }

    public w_1748_S J_1907_R() {
        this.s_956_w.clear();
        return this;
    }

    public w_1748_S n_1700_B(StructureProcessor structureProcessorIn) {
        this.s_956_w.add(structureProcessorIn);
        return this;
    }

    public w_1748_S J_1907_R(StructureProcessor structureProcessorIn) {
        this.s_956_w.remove(structureProcessorIn);
        return this;
    }

    public q_4099_E R_4764_Y() {
        return this.n_1700_B;
    }

    public W_2163_m G_564_y() {
        return this.J_1907_R;
    }

    public c_1514_x P_1922_E() {
        return this.R_4764_Y;
    }

    public Random J_1907_R(@Nullable c_1514_x seed) {
        if (this.w_1484_f != null) {
            return this.w_1484_f;
        }
        return seed == null ? new Random(j_3341_s.J_1907_R()) : new Random(u_530_F.n_1700_B(seed));
    }

    public boolean u_1723_Y() {
        return this.G_564_y;
    }

    @Nullable
    public BoundingBox v_4262_N() {
        if (this.u_1723_Y == null && this.P_1922_E != null) {
            this.s_956_w();
        }
        return this.u_1723_Y;
    }

    public boolean w_1484_f() {
        return this.u_2550_I;
    }

    public List<StructureProcessor> t_148_a() {
        return this.s_956_w;
    }

    void s_956_w() {
        if (this.P_1922_E != null) {
            this.u_1723_Y = this.J_1907_R(this.P_1922_E);
        }
    }

    public boolean u_2550_I() {
        return this.v_4262_N;
    }

    public a_2886_t.G_564_y n_1700_B(List<a_2886_t.G_564_y> p_237132_1_, @Nullable c_1514_x p_237132_2_) {
        int i = p_237132_1_.size();
        if (i == 0) {
            throw new IllegalStateException("No palettes");
        }
        return p_237132_1_.get(this.J_1907_R(p_237132_2_).nextInt(i));
    }

    @Nullable
    private BoundingBox J_1907_R(@Nullable Y_1387_d pos) {
        if (pos == null) {
            return this.u_1723_Y;
        }
        int i = pos.J_1907_R * 16;
        int j = pos.R_4764_Y * 16;
        return new BoundingBox(i, 0, j, i + 16 - 1, 255, j + 16 - 1);
    }

    public w_1748_S R_4764_Y(boolean p_237133_1_) {
        this.M_588_G = p_237133_1_;
        return this;
    }

    public boolean M_588_G() {
        return this.M_588_G;
    }
}


