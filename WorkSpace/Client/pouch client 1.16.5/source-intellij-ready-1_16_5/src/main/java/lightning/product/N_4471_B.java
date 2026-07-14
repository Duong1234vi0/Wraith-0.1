/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import java.util.function.IntSupplier;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.P_4249_L;
import lightning.product.ResourceManager;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.w_4282_K;

public class N_4471_B
implements AutoCloseable {
    private final w_4282_K R_4764_Y;
    public final P_4249_L n_1700_B;
    public final P_4249_L J_1907_R;
    private final List<IntSupplier> G_564_y = Lists.newArrayList();
    private final List<String> P_1922_E = Lists.newArrayList();
    private final List<Integer> u_1723_Y = Lists.newArrayList();
    private final List<Integer> v_4262_N = Lists.newArrayList();
    private D_1098_v w_1484_f;

    public N_4471_B(ResourceManager resourceManager, String programName, P_4249_L framebufferInIn, P_4249_L framebufferOutIn) throws IOException {
        this.R_4764_Y = new w_4282_K(resourceManager, programName);
        this.n_1700_B = framebufferInIn;
        this.J_1907_R = framebufferOutIn;
    }

    @Override
    public void close() {
        this.R_4764_Y.close();
    }

    public void n_1700_B(String auxName, IntSupplier auxFramebufferIn, int width, int height) {
        this.P_1922_E.add(this.P_1922_E.size(), auxName);
        this.G_564_y.add(this.G_564_y.size(), auxFramebufferIn);
        this.u_1723_Y.add(this.u_1723_Y.size(), width);
        this.v_4262_N.add(this.v_4262_N.size(), height);
    }

    public void n_1700_B(D_1098_v p_195654_1_) {
        this.w_1484_f = p_195654_1_;
    }

    public void n_1700_B(float partialTicks) {
        this.n_1700_B.t_148_a();
        float f = this.J_1907_R.n_1700_B;
        float f1 = this.J_1907_R.J_1907_R;
        c_4037_x.R_4764_Y(0, 0, (int)f, (int)f1);
        this.R_4764_Y.n_1700_B("DiffuseSampler", this.n_1700_B::u_2550_I);
        for (int i = 0; i < this.G_564_y.size(); ++i) {
            this.R_4764_Y.n_1700_B(this.P_1922_E.get(i), this.G_564_y.get(i));
            this.R_4764_Y.J_1907_R("AuxSize" + i).n_1700_B(this.u_1723_Y.get(i).intValue(), this.v_4262_N.get(i).intValue());
        }
        this.R_4764_Y.J_1907_R("ProjMat").n_1700_B(this.w_1484_f);
        this.R_4764_Y.J_1907_R("InSize").n_1700_B(this.n_1700_B.n_1700_B, this.n_1700_B.J_1907_R);
        this.R_4764_Y.J_1907_R("OutSize").n_1700_B(f, f1);
        this.R_4764_Y.J_1907_R("Time").n_1700_B(partialTicks);
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        this.R_4764_Y.J_1907_R("ScreenSize").n_1700_B(minecraft.RealmsServerPing().u_2550_I(), minecraft.RealmsServerPing().M_588_G());
        this.R_4764_Y.u_1723_Y();
        this.J_1907_R.R_4764_Y(MinecraftClient.n_1700_B);
        this.J_1907_R.J_1907_R(false);
        c_4037_x.J_1907_R(519);
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        bufferbuilder.pos(0.0, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(f, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(f, f1, 500.0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.0, f1, 500.0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.u_1723_Y();
        o_2840_r.n_1700_B(bufferbuilder);
        c_4037_x.J_1907_R(515);
        this.R_4764_Y.P_1922_E();
        this.J_1907_R.t_148_a();
        this.n_1700_B.w_1484_f();
        for (IntSupplier object : this.G_564_y) {
            if (!(object instanceof P_4249_L)) continue;
            ((P_4249_L)((Object)object)).w_1484_f();
        }
    }

    public w_4282_K n_1700_B() {
        return this.R_4764_Y;
    }
}



