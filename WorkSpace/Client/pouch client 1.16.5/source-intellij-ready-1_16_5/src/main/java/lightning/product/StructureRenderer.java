/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lightning.product.D_4792_h;
import lightning.product.BoundingBox;
import lightning.product.Z_3903_F;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.k_4690_i;
import lightning.product.n_4915_r;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.z_883_p;

public class StructureRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private final Map<Z_3903_F, Map<String, BoundingBox>> J_1907_R = Maps.newIdentityHashMap();
    private final Map<Z_3903_F, Map<String, BoundingBox>> R_4764_Y = Maps.newIdentityHashMap();
    private final Map<Z_3903_F, Map<String, Boolean>> G_564_y = Maps.newIdentityHashMap();

    public StructureRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        h_3572_K activerenderinfo = this.n_1700_B.s_956_w.M_588_G();
        k_4690_i iworld = this.n_1700_B.Y_601_j;
        Z_3903_F dimensiontype = iworld.G_624_v();
        c_1514_x blockpos = new c_1514_x(activerenderinfo.J_1907_R().J_1907_R, 0.0, activerenderinfo.J_1907_R().G_564_y);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.C_2741_M());
        if (this.J_1907_R.containsKey(dimensiontype)) {
            for (BoundingBox m_1908_H : this.J_1907_R.get(dimensiontype).values()) {
                if (!blockpos.withinDistance(m_1908_H.v_4262_N(), 500.0)) continue;
                z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, (double)m_1908_H.n_1700_B - camX, (double)m_1908_H.J_1907_R - camY, (double)m_1908_H.R_4764_Y - camZ, (double)(m_1908_H.G_564_y + 1) - camX, (double)(m_1908_H.P_1922_E + 1) - camY, (double)(m_1908_H.u_1723_Y + 1) - camZ, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
        if (this.R_4764_Y.containsKey(dimensiontype)) {
            for (Map.Entry entry : this.R_4764_Y.get(dimensiontype).entrySet()) {
                String s = (String)entry.getKey();
                BoundingBox mutableboundingbox1 = (BoundingBox)entry.getValue();
                Boolean obool = this.G_564_y.get(dimensiontype).get(s);
                if (!blockpos.withinDistance(mutableboundingbox1.v_4262_N(), 500.0)) continue;
                if (obool.booleanValue()) {
                    z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, (double)mutableboundingbox1.n_1700_B - camX, (double)mutableboundingbox1.J_1907_R - camY, (double)mutableboundingbox1.R_4764_Y - camZ, (double)(mutableboundingbox1.G_564_y + 1) - camX, (double)(mutableboundingbox1.P_1922_E + 1) - camY, (double)(mutableboundingbox1.u_1723_Y + 1) - camZ, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
                    continue;
                }
                z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, (double)mutableboundingbox1.n_1700_B - camX, (double)mutableboundingbox1.J_1907_R - camY, (double)mutableboundingbox1.R_4764_Y - camZ, (double)(mutableboundingbox1.G_564_y + 1) - camX, (double)(mutableboundingbox1.P_1922_E + 1) - camY, (double)(mutableboundingbox1.u_1723_Y + 1) - camZ, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f);
            }
        }
    }

    public void n_1700_B(BoundingBox p_223454_1_, List<BoundingBox> p_223454_2_, List<Boolean> p_223454_3_, Z_3903_F p_223454_4_) {
        if (!this.J_1907_R.containsKey(p_223454_4_)) {
            this.J_1907_R.put(p_223454_4_, Maps.newHashMap());
        }
        if (!this.R_4764_Y.containsKey(p_223454_4_)) {
            this.R_4764_Y.put(p_223454_4_, Maps.newHashMap());
            this.G_564_y.put(p_223454_4_, Maps.newHashMap());
        }
        this.J_1907_R.get(p_223454_4_).put(p_223454_1_.toString(), p_223454_1_);
        for (int i = 0; i < p_223454_2_.size(); ++i) {
            BoundingBox mutableboundingbox = p_223454_2_.get(i);
            Boolean obool = p_223454_3_.get(i);
            this.R_4764_Y.get(p_223454_4_).put(mutableboundingbox.toString(), mutableboundingbox);
            this.G_564_y.get(p_223454_4_).put(mutableboundingbox.toString(), obool);
        }
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
        this.G_564_y.clear();
    }
}



