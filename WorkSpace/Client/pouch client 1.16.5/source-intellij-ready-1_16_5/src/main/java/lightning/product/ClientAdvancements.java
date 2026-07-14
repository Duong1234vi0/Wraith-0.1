/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.C_3304_p;
import lightning.product.ServerboundSeenAdvancementsPacket;
import lightning.product.ClientboundUpdateAdvancementsPacket;
import lightning.product.W_2853_p;
import lightning.product.MinecraftClient;
import lightning.product.AdvancementList;
import lightning.product.g_2336_b;
import lightning.product.j_2808_U;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientAdvancements {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final MinecraftClient J_1907_R;
    private final AdvancementList R_4764_Y = new AdvancementList();
    private final Map<A_2629_w, C_3304_p> G_564_y = Maps.newHashMap();
    @Nullable
    private n_1700_B P_1922_E;
    @Nullable
    private A_2629_w u_1723_Y;

    public ClientAdvancements(MinecraftClient p_i47380_1_) {
        this.J_1907_R = p_i47380_1_;
    }

    public void n_1700_B(ClientboundUpdateAdvancementsPacket packetIn) {
        if (packetIn.P_1922_E()) {
            this.R_4764_Y.n_1700_B();
            this.G_564_y.clear();
        }
        this.R_4764_Y.n_1700_B(packetIn.R_4764_Y());
        this.R_4764_Y.n_1700_B(packetIn.J_1907_R());
        for (Map.Entry<g_2336_b, C_3304_p> entry : packetIn.G_564_y().entrySet()) {
            A_2629_w advancement = this.R_4764_Y.n_1700_B(entry.getKey());
            if (advancement != null) {
                C_3304_p advancementprogress = entry.getValue();
                advancementprogress.n_1700_B(advancement.u_1723_Y(), advancement.t_148_a());
                this.G_564_y.put(advancement, advancementprogress);
                if (this.P_1922_E != null) {
                    this.P_1922_E.n_1700_B(advancement, advancementprogress);
                }
                if (packetIn.P_1922_E() || !advancementprogress.n_1700_B() || advancement.R_4764_Y() == null || !advancement.R_4764_Y().w_1484_f()) continue;
                this.J_1907_R.e_1992_r().n_1700_B(new j_2808_U(advancement));
                continue;
            }
            n_1700_B.warn("Server informed client about progress for unknown advancement {}", (Object)entry.getKey());
        }
    }

    public AdvancementList n_1700_B() {
        return this.R_4764_Y;
    }

    public void n_1700_B(@Nullable A_2629_w advancementIn, boolean tellServer) {
        W_2853_p clientplaynethandler = this.J_1907_R.k_2293_S();
        if (clientplaynethandler != null && advancementIn != null && tellServer) {
            clientplaynethandler.n_1700_B(ServerboundSeenAdvancementsPacket.n_1700_B(advancementIn));
        }
        if (this.u_1723_Y != advancementIn) {
            this.u_1723_Y = advancementIn;
            if (this.P_1922_E != null) {
                this.P_1922_E.P_1922_E(advancementIn);
            }
        }
    }

    public void n_1700_B(@Nullable n_1700_B listenerIn) {
        this.P_1922_E = listenerIn;
        this.R_4764_Y.n_1700_B(listenerIn);
        if (listenerIn != null) {
            for (Map.Entry<A_2629_w, C_3304_p> entry : this.G_564_y.entrySet()) {
                listenerIn.n_1700_B(entry.getKey(), entry.getValue());
            }
            listenerIn.P_1922_E(this.u_1723_Y);
        }
    }

    public static interface n_1700_B
    extends AdvancementList.n_1700_B {
        public void n_1700_B(A_2629_w var1, C_3304_p var2);

        public void P_1922_E(@Nullable A_2629_w var1);
    }
}



