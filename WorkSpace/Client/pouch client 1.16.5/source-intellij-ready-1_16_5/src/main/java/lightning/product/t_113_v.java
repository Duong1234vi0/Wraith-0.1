/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import lightning.product.A_2629_w;
import lightning.product.B_1335_M;
import lightning.product.B_368_w;
import lightning.product.F_2904_S;
import lightning.product.StructureFeature;
import lightning.product.J_1008_m;
import lightning.product.K_4518_s;
import lightning.product.P_2068_y;
import lightning.product.V_3982_O;
import lightning.product.W_4813_f;
import lightning.product.Y_3066_B;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_3334_n;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.o_3456_E;
import lightning.product.Items;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.x_282_a;
import lightning.product.z_936_s;

public class t_113_v
implements Consumer<Consumer<A_2629_w>> {
    public void n_1700_B(Consumer<A_2629_w> p_accept_1_) {
        A_2629_w advancement = A_2629_w.n_1700_B.n_1700_B().n_1700_B(a_3742_W.e_1231_S, (x_282_a)new F_2904_S("advancements.end.root.title"), (x_282_a)new F_2904_S("advancements.end.root.description"), new g_2336_b("textures/gui/advancements/backgrounds/end.png"), W_4813_f.n_1700_B, false, false, false).n_1700_B("entered_end", z_936_s.n_1700_B.n_1700_B(b_4507_u.w_1484_f)).n_1700_B(p_accept_1_, "end/root");
        A_2629_w advancement1 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(a_3742_W.H_1491_c, (x_282_a)new F_2904_S("advancements.end.kill_dragon.title"), (x_282_a)new F_2904_S("advancements.end.kill_dragon.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("killed_dragon", B_1335_M.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.Y_601_j))).n_1700_B(p_accept_1_, "end/kill_dragon");
        A_2629_w advancement2 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.v_2746_S, (x_282_a)new F_2904_S("advancements.end.enter_end_gateway.title"), (x_282_a)new F_2904_S("advancements.end.enter_end_gateway.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("entered_end_gateway", V_3982_O.n_1700_B.n_1700_B(a_3742_W.ItemRelease)).n_1700_B(p_accept_1_, "end/enter_end_gateway");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.LoomBlock, (x_282_a)new F_2904_S("advancements.end.respawn_dragon.title"), (x_282_a)new F_2904_S("advancements.end.respawn_dragon.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("summoned_dragon", Y_3066_B.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.Y_601_j))).n_1700_B(p_accept_1_, "end/respawn_dragon");
        A_2629_w advancement3 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement2).n_1700_B(a_3742_W.ClickPearl, (x_282_a)new F_2904_S("advancements.end.find_end_city.title"), (x_282_a)new F_2904_S("advancements.end.find_end_city.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("in_city", b_3334_n.n_1700_B.n_1700_B(B_368_w.n_1700_B(StructureFeature.Q_4569_t))).n_1700_B(p_accept_1_, "end/find_end_city");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.O_3671_t, (x_282_a)new F_2904_S("advancements.end.dragon_breath.title"), (x_282_a)new F_2904_S("advancements.end.dragon_breath.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("dragon_breath", P_2068_y.n_1700_B.n_1700_B(Items.O_3671_t)).n_1700_B(p_accept_1_, "end/dragon_breath");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.NetherVines, (x_282_a)new F_2904_S("advancements.end.levitate.title"), (x_282_a)new F_2904_S("advancements.end.levitate.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(50)).n_1700_B("levitated", K_4518_s.n_1700_B.n_1700_B(o_3456_E.J_1907_R(MinMaxBounds.n_1700_B.n_1700_B(50.0f)))).n_1700_B(p_accept_1_, "end/levitate");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.NyliumBlock, (x_282_a)new F_2904_S("advancements.end.elytra.title"), (x_282_a)new F_2904_S("advancements.end.elytra.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("elytra", P_2068_y.n_1700_B.n_1700_B(Items.NyliumBlock)).n_1700_B(p_accept_1_, "end/elytra");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(a_3742_W.F_391_H, (x_282_a)new F_2904_S("advancements.end.dragon_egg.title"), (x_282_a)new F_2904_S("advancements.end.dragon_egg.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("dragon_egg", P_2068_y.n_1700_B.n_1700_B(a_3742_W.F_391_H)).n_1700_B(p_accept_1_, "end/dragon_egg");
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((Consumer)object);
    }
}



