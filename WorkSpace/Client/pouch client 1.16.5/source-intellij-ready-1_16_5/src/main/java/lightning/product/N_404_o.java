/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import lightning.product.A_4388_s;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.ClientboundTeleportEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.N_4422_X;
import lightning.product.ClientboundAddMobPacket;
import lightning.product.ClientboundUpdateAttributesPacket;
import lightning.product.X_776_r;
import lightning.product.ClientboundSetEquipmentPacket;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_4762_y;
import lightning.product.b_4507_u;
import lightning.product.ClientboundSetPassengersPacket;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_446_u;
import lightning.product.h_384_L;
import lightning.product.k_2610_C;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientGamePacketListener;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.r_4811_B;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.y_740_d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class N_404_o {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final e_3591_l J_1907_R;
    private final N_4263_v R_4764_Y;
    private final int G_564_y;
    private final boolean P_1922_E;
    private final Consumer<Packet<?>> u_1723_Y;
    private long v_4262_N;
    private long w_1484_f;
    private long t_148_a;
    private int s_956_w;
    private int u_2550_I;
    private int M_588_G;
    private e_2866_D P_4830_p = e_2866_D.n_1700_B;
    private int h_1847_R;
    private int Q_4569_t;
    private List<N_4263_v> M_182_A = Collections.emptyList();
    private boolean t_1786_h;
    private boolean multiplayerClientSuggestionProvider;

    public N_404_o(e_3591_l serverWorld, N_4263_v entity, int updateFrequency, boolean sendVelocityUpdates, Consumer<Packet<?>> packetConsumer) {
        this.J_1907_R = serverWorld;
        this.u_1723_Y = packetConsumer;
        this.R_4764_Y = entity;
        this.G_564_y = updateFrequency;
        this.P_1922_E = sendVelocityUpdates;
        this.G_564_y();
        this.s_956_w = u_530_F.G_564_y(entity.p_178_J * 256.0f / 360.0f);
        this.u_2550_I = u_530_F.G_564_y(entity.f_4016_n * 256.0f / 360.0f);
        this.M_588_G = u_530_F.G_564_y(entity.l_4088_R() * 256.0f / 360.0f);
        this.multiplayerClientSuggestionProvider = entity.M_1641_O();
    }

    public void n_1700_B() {
        List<N_4263_v> list = this.R_4764_Y.o_3599_Z();
        if (!list.equals(this.M_182_A)) {
            this.M_182_A = list;
            this.u_1723_Y.accept(new ClientboundSetPassengersPacket(this.R_4764_Y));
        }
        if (this.R_4764_Y instanceof y_740_d && this.h_1847_R % 10 == 0) {
            y_740_d itemframeentity = (y_740_d)this.R_4764_Y;
            Z_1993_T itemstack = itemframeentity.h_1847_R();
            if (itemstack.J_1907_R() instanceof G_3165_y) {
                F_3620_e mapdata = G_3165_y.J_1907_R(itemstack, this.J_1907_R);
                for (B_4088_l serverplayerentity : this.J_1907_R.multiplayerClientSuggestionProvider()) {
                    mapdata.n_1700_B(serverplayerentity, itemstack);
                    Packet<?> ipacket = ((G_3165_y)itemstack.J_1907_R()).n_1700_B(itemstack, (b_4507_u)this.J_1907_R, serverplayerentity);
                    if (ipacket == null) continue;
                    serverplayerentity.n_1700_B.n_1700_B(ipacket);
                }
            }
            this.R_4764_Y();
        }
        if (this.h_1847_R % this.G_564_y == 0 || this.R_4764_Y.LongRunningTask || this.R_4764_Y.D_60_a().n_1700_B()) {
            if (this.R_4764_Y.y_2772_m()) {
                boolean flag2;
                int i1 = u_530_F.G_564_y(this.R_4764_Y.p_178_J * 256.0f / 360.0f);
                int l1 = u_530_F.G_564_y(this.R_4764_Y.f_4016_n * 256.0f / 360.0f);
                boolean bl = flag2 = Math.abs(i1 - this.s_956_w) >= 1 || Math.abs(l1 - this.u_2550_I) >= 1;
                if (flag2) {
                    this.u_1723_Y.accept(new N_4422_X.n_1700_B(this.R_4764_Y.j_276_v(), (byte)i1, (byte)l1, this.R_4764_Y.M_1641_O()));
                    this.s_956_w = i1;
                    this.u_2550_I = l1;
                }
                this.G_564_y();
                this.R_4764_Y();
                this.t_1786_h = true;
            } else {
                e_2866_D vector3d1;
                double d0;
                boolean flag;
                ++this.Q_4569_t;
                int l = u_530_F.G_564_y(this.R_4764_Y.p_178_J * 256.0f / 360.0f);
                int k1 = u_530_F.G_564_y(this.R_4764_Y.f_4016_n * 256.0f / 360.0f);
                e_2866_D vector3d = this.R_4764_Y.s_4990_V().G_564_y(N_4422_X.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a));
                boolean flag3 = vector3d.v_4262_N() >= 7.62939453125E-6;
                Packet<ClientGamePacketListener> ipacket1 = null;
                boolean flag4 = flag3 || this.h_1847_R % 60 == 0;
                boolean bl = flag = Math.abs(l - this.s_956_w) >= 1 || Math.abs(k1 - this.u_2550_I) >= 1;
                if (this.h_1847_R > 0 || this.R_4764_Y instanceof h_384_L) {
                    boolean flag1;
                    long i = N_4422_X.n_1700_B(vector3d.J_1907_R);
                    long j = N_4422_X.n_1700_B(vector3d.R_4764_Y);
                    long k = N_4422_X.n_1700_B(vector3d.G_564_y);
                    boolean bl2 = flag1 = i < -32768L || i > 32767L || j < -32768L || j > 32767L || k < -32768L || k > 32767L;
                    if (!flag1 && this.Q_4569_t <= 400 && !this.t_1786_h && this.multiplayerClientSuggestionProvider == this.R_4764_Y.M_1641_O()) {
                        if (!(flag4 && flag || this.R_4764_Y instanceof h_384_L)) {
                            if (flag4) {
                                ipacket1 = new N_4422_X.R_4764_Y(this.R_4764_Y.j_276_v(), (short)i, (short)j, (short)k, this.R_4764_Y.M_1641_O());
                            } else if (flag) {
                                ipacket1 = new N_4422_X.n_1700_B(this.R_4764_Y.j_276_v(), (byte)l, (byte)k1, this.R_4764_Y.M_1641_O());
                            }
                        } else {
                            ipacket1 = new N_4422_X.J_1907_R(this.R_4764_Y.j_276_v(), (short)i, (short)j, (short)k, (byte)l, (byte)k1, this.R_4764_Y.M_1641_O());
                        }
                    } else {
                        this.multiplayerClientSuggestionProvider = this.R_4764_Y.M_1641_O();
                        this.Q_4569_t = 0;
                        ipacket1 = new ClientboundTeleportEntityPacket(this.R_4764_Y);
                    }
                }
                if ((this.P_1922_E || this.R_4764_Y.LongRunningTask || this.R_4764_Y instanceof r_4811_B && ((r_4811_B)this.R_4764_Y).k_578_l()) && this.h_1847_R > 0 && ((d0 = (vector3d1 = this.R_4764_Y.I_4348_c()).v_4262_N(this.P_4830_p)) > 1.0E-7 || d0 > 0.0 && vector3d1.v_4262_N() == 0.0)) {
                    this.P_4830_p = vector3d1;
                    this.u_1723_Y.accept(new ClientboundSetEntityMotionPacket(this.R_4764_Y.j_276_v(), this.P_4830_p));
                }
                if (ipacket1 != null) {
                    this.u_1723_Y.accept(ipacket1);
                }
                this.R_4764_Y();
                if (flag4) {
                    this.G_564_y();
                }
                if (flag) {
                    this.s_956_w = l;
                    this.u_2550_I = k1;
                }
                this.t_1786_h = false;
            }
            int j1 = u_530_F.G_564_y(this.R_4764_Y.l_4088_R() * 256.0f / 360.0f);
            if (Math.abs(j1 - this.M_588_G) >= 1) {
                this.u_1723_Y.accept(new X_776_r(this.R_4764_Y, (byte)j1));
                this.M_588_G = j1;
            }
            this.R_4764_Y.LongRunningTask = false;
        }
        ++this.h_1847_R;
        if (this.R_4764_Y.Ops) {
            this.n_1700_B(new ClientboundSetEntityMotionPacket(this.R_4764_Y));
            this.R_4764_Y.Ops = false;
        }
    }

    public void n_1700_B(B_4088_l player) {
        this.R_4764_Y.R_4764_Y(player);
        player.A_4115_X(this.R_4764_Y);
    }

    public void J_1907_R(B_4088_l player) {
        this.n_1700_B(player.n_1700_B::n_1700_B);
        this.R_4764_Y.J_1907_R(player);
        player.Y_1740_V(this.R_4764_Y);
    }

    public void n_1700_B(Consumer<Packet<?>> packetConsumer) {
        Z_530_i mobentity;
        if (this.R_4764_Y.t_4219_U) {
            n_1700_B.warn("Fetching packet for removed entity " + String.valueOf(this.R_4764_Y));
        }
        Packet<?> ipacket = this.R_4764_Y.f_();
        this.M_588_G = u_530_F.G_564_y(this.R_4764_Y.l_4088_R() * 256.0f / 360.0f);
        packetConsumer.accept(ipacket);
        if (!this.R_4764_Y.D_60_a().G_564_y()) {
            packetConsumer.accept(new a_4762_y(this.R_4764_Y.j_276_v(), this.R_4764_Y.D_60_a(), true));
        }
        boolean flag = this.P_1922_E;
        if (this.R_4764_Y instanceof r_4811_B) {
            Collection<A_4388_s> collection = ((r_4811_B)this.R_4764_Y).B_1146_q().J_1907_R();
            if (!collection.isEmpty()) {
                packetConsumer.accept(new ClientboundUpdateAttributesPacket(this.R_4764_Y.j_276_v(), collection));
            }
            if (((r_4811_B)this.R_4764_Y).k_578_l()) {
                flag = true;
            }
        }
        this.P_4830_p = this.R_4764_Y.I_4348_c();
        if (flag && !(ipacket instanceof ClientboundAddMobPacket)) {
            packetConsumer.accept(new ClientboundSetEntityMotionPacket(this.R_4764_Y.j_276_v(), this.P_4830_p));
        }
        if (this.R_4764_Y instanceof r_4811_B) {
            ArrayList list = Lists.newArrayList();
            for (e_1174_E equipmentslottype : e_1174_E.values()) {
                Z_1993_T itemstack = ((r_4811_B)this.R_4764_Y).J_1907_R(equipmentslottype);
                if (itemstack.n_1700_B()) continue;
                list.add(Pair.of((Object)((Object)equipmentslottype), (Object)itemstack.t_148_a()));
            }
            if (!list.isEmpty()) {
                packetConsumer.accept(new ClientboundSetEquipmentPacket(this.R_4764_Y.j_276_v(), list));
            }
        }
        if (this.R_4764_Y instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)this.R_4764_Y;
            for (k_2610_C effectinstance : livingentity.I_3457_f()) {
                packetConsumer.accept(new ClientboundUpdateMobEffectPacket(this.R_4764_Y.j_276_v(), effectinstance));
            }
        }
        if (!this.R_4764_Y.o_3599_Z().isEmpty()) {
            packetConsumer.accept(new ClientboundSetPassengersPacket(this.R_4764_Y));
        }
        if (this.R_4764_Y.y_2772_m()) {
            packetConsumer.accept(new ClientboundSetPassengersPacket(this.R_4764_Y.l_3609_d()));
        }
        if (this.R_4764_Y instanceof Z_530_i && (mobentity = (Z_530_i)this.R_4764_Y).n_4915_F()) {
            packetConsumer.accept(new e_446_u(mobentity, mobentity.y_2622_c()));
        }
    }

    private void R_4764_Y() {
        C_4114_x entitydatamanager = this.R_4764_Y.D_60_a();
        if (entitydatamanager.n_1700_B()) {
            this.n_1700_B(new a_4762_y(this.R_4764_Y.j_276_v(), entitydatamanager, false));
        }
        if (this.R_4764_Y instanceof r_4811_B) {
            Set<A_4388_s> set = ((r_4811_B)this.R_4764_Y).B_1146_q().n_1700_B();
            if (!set.isEmpty()) {
                this.n_1700_B(new ClientboundUpdateAttributesPacket(this.R_4764_Y.j_276_v(), set));
            }
            set.clear();
        }
    }

    private void G_564_y() {
        this.v_4262_N = N_4422_X.n_1700_B(this.R_4764_Y.O_3598_v());
        this.w_1484_f = N_4422_X.n_1700_B(this.R_4764_Y.X_2960_b());
        this.t_148_a = N_4422_X.n_1700_B(this.R_4764_Y.l_2647_k());
    }

    public e_2866_D J_1907_R() {
        return N_4422_X.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    private void n_1700_B(Packet<?> packet) {
        this.u_1723_Y.accept(packet);
        if (this.R_4764_Y instanceof B_4088_l) {
            ((B_4088_l)this.R_4764_Y).n_1700_B.n_1700_B(packet);
        }
    }
}


