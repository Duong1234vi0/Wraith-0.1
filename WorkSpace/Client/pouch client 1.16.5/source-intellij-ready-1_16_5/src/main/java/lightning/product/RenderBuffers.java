/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.SortedMap;
import lightning.product.OutlineBufferSource;
import lightning.product.D_3318_r;
import lightning.product.b_4440_Q;
import lightning.product.g_2561_p;
import lightning.product.j_3341_s;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.ChunkBufferBuilderPack;

public class RenderBuffers {
    private final ChunkBufferBuilderPack n_1700_B = new ChunkBufferBuilderPack();
    private final SortedMap<o_2576_A, D_3318_r> J_1907_R = (SortedMap)j_3341_s.n_1700_B(new Object2ObjectLinkedOpenHashMap(), (T p_228485_1_) -> {
        p_228485_1_.put((Object)b_4440_Q.v_4262_N(), (Object)this.n_1700_B.n_1700_B(o_2576_A.u_1723_Y()));
        p_228485_1_.put((Object)b_4440_Q.w_1484_f(), (Object)this.n_1700_B.n_1700_B(o_2576_A.w_1484_f()));
        p_228485_1_.put((Object)b_4440_Q.n_1700_B(), (Object)this.n_1700_B.n_1700_B(o_2576_A.v_4262_N()));
        p_228485_1_.put((Object)b_4440_Q.s_956_w(), (Object)this.n_1700_B.n_1700_B(o_2576_A.t_148_a()));
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, b_4440_Q.J_1907_R());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, b_4440_Q.R_4764_Y());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, b_4440_Q.G_564_y());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, b_4440_Q.P_1922_E());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, b_4440_Q.u_1723_Y());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.u_2550_I());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.h_1847_R());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.Q_4569_t());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.t_1786_h());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.multiplayerClientSuggestionProvider());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.M_182_A());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.w_1457_N());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.Y_601_j());
        RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, o_2576_A.P_4830_p());
        g_2561_p.u_2550_I.forEach(p_228488_1_ -> RenderBuffers.n_1700_B((Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r>)p_228485_1_, p_228488_1_));
    });
    private final o_3091_w.n_1700_B R_4764_Y = o_3091_w.n_1700_B(this.J_1907_R, new D_3318_r(256));
    private final o_3091_w.n_1700_B G_564_y = o_3091_w.n_1700_B(new D_3318_r(256));
    private final OutlineBufferSource P_1922_E = new OutlineBufferSource(this.R_4764_Y);

    private static void n_1700_B(Object2ObjectLinkedOpenHashMap<o_2576_A, D_3318_r> mapBuildersIn, o_2576_A renderTypeIn) {
        mapBuildersIn.put((Object)renderTypeIn, (Object)new D_3318_r(renderTypeIn.q_2307_F()));
    }

    public ChunkBufferBuilderPack n_1700_B() {
        return this.n_1700_B;
    }

    public o_3091_w.n_1700_B J_1907_R() {
        return this.R_4764_Y;
    }

    public o_3091_w.n_1700_B R_4764_Y() {
        return this.G_564_y;
    }

    public OutlineBufferSource G_564_y() {
        return this.P_1922_E;
    }
}


