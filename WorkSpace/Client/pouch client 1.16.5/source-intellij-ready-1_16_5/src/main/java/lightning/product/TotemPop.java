/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lightning.product.C_1375_J;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.Q_2753_H;
import lightning.product.U_3758_B;
import lightning.product.U_679_Y;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_2049_e;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.ClientboundPlayerCombatPacket;
import lightning.product.o_3091_w;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.s_4405_m;
import lightning.product.Packet;
import lightning.product.v_2826_q;
import lightning.product.w_2040_b;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;

public class TotemPop
extends Module {
    private final Map<UUID, Integer> v_4262_N = new HashMap<UUID, Integer>();
    private final Map<UUID, List<n_1700_B>> w_1484_f = new HashMap<UUID, List<n_1700_B>>();
    private final BooleanSetting prizrakiPriPopeTotemaEnabled = new BooleanSetting("\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438 \u043f\u0440\u0438 \u043f\u043e\u043f\u0435 \u0442\u043e\u0442\u0435\u043c\u0430", true);
    private final NumberSetting vremyaEffektaMsSetting = new NumberSetting("\u0412\u0440\u0435\u043c\u044f \u044d\u0444\u0444\u0435\u043a\u0442\u0430, \u043c\u0441", 1500.0f, 200.0f, 4000.0f, 50.0f, () -> this.prizrakiPriPopeTotemaEnabled.isEnabled());
    private final NumberSetting vysotaPodemaSetting = new NumberSetting("\u0412\u044b\u0441\u043e\u0442\u0430 \u043f\u043e\u0434\u044a\u0451\u043c\u0430", 1.0f, 0.2f, 6.0f, 0.1f, () -> this.prizrakiPriPopeTotemaEnabled.isEnabled());
    private final BooleanSetting optimizaciyaRenderaEnabled = new BooleanSetting("\u041e\u043f\u0442\u0438\u043c\u0438\u0437\u0430\u0446\u0438\u044f \u0440\u0435\u043d\u0434\u0435\u0440\u0430", true);

    public TotemPop() {
        super("TotemPop", ModuleCategory.R_4764_Y);
        this.addSettings(this.prizrakiPriPopeTotemaEnabled, this.vremyaEffektaMsSetting, this.vysotaPodemaSetting, this.optimizaciyaRenderaEnabled);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.v_4262_N.clear();
        this.w_1484_f.clear();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (TotemPop.c_3005_b.Y_601_j == null || TotemPop.c_3005_b.O_508_d().J_1907_R == null) {
            return;
        }
        if (!this.prizrakiPriPopeTotemaEnabled.isEnabled().booleanValue()) {
            return;
        }
        long now = System.currentTimeMillis();
        int renderedCount = 0;
        int MAX_RENDERS_PER_FRAME = this.optimizaciyaRenderaEnabled.isEnabled() != false ? 5 : Integer.MAX_VALUE;
        for (Map.Entry<UUID, List<n_1700_B>> entry : this.w_1484_f.entrySet()) {
            UUID playerUuid = entry.getKey();
            a_3913_L player = TotemPop.c_3005_b.Y_601_j.n_1700_B(playerUuid);
            if (player == null || this.optimizaciyaRenderaEnabled.isEnabled().booleanValue() && player.G_564_y((N_4263_v)TotemPop.c_3005_b.Y_259_p) > 1024.0) continue;
            List<n_1700_B> snapshots = entry.getValue();
            snapshots.removeIf(s -> now - s.J_1907_R > (long)((Float)this.vremyaEffektaMsSetting.getValue()).intValue());
            if (snapshots.isEmpty()) continue;
            for (n_1700_B snap : snapshots) {
                if (renderedCount >= MAX_RENDERS_PER_FRAME) break;
                this.n_1700_B(player, snap, now);
                ++renderedCount;
            }
            if (renderedCount < MAX_RENDERS_PER_FRAME) continue;
            break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(a_3913_L player, n_1700_B snap, long now) {
        int i;
        float duration = Math.max(1.0f, ((Float)this.vremyaEffektaMsSetting.getValue()).floatValue());
        float t = (float)Math.min(1.0, (double)((float)(now - snap.J_1907_R) / duration));
        float tSmooth = t * t * (3.0f - 2.0f * t);
        float yOffset = ((Float)this.vysotaPodemaSetting.getValue()).floatValue() * tSmooth;
        float alphaVal = 0.6f * (1.0f - tSmooth);
        boolean useShader = s_4405_m.Y_259_p.n_1700_B();
        if (useShader) {
            I_4817_s current = player.i_601_W();
            e_2866_D currPos = player.s_4990_V();
            e_2866_D delta = snap.n_1700_B.G_564_y(currPos);
            I_4817_s aabbAtSnap = current.offset(delta.J_1907_R, delta.R_4764_Y, delta.G_564_y).offset(0.0, yOffset, 0.0);
            Vector2f center = v_2826_q.n_1700_B(aabbAtSnap.getCenter());
            if (center == null || center.x == Float.MAX_VALUE) {
                return;
            }
            double minX = center.x;
            double minY = center.y;
            double maxX = center.x;
            double maxY = center.y;
            for (e_2866_D corner : v_2826_q.n_1700_B(aabbAtSnap)) {
                Vector2f v = v_2826_q.n_1700_B(corner);
                if (v == null || v.x == Float.MAX_VALUE) continue;
                minX = Math.min(minX, (double)v.x);
                minY = Math.min(minY, (double)v.y);
                maxX = Math.max(maxX, (double)v.x);
                maxY = Math.max(maxY, (double)v.y);
            }
            double width = Math.max(1.0, maxX - minX);
            double height = Math.max(1.0, maxY - minY);
            U_679_Y mw = c_3005_b.RealmsServerPing();
            int themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int color1 = H_2506_c.J_1907_R(themeColor, 30);
            int color2 = themeColor;
            int color3 = H_2506_c.J_1907_R(themeColor, 0.7f);
            int color4 = H_2506_c.J_1907_R(themeColor, 0.5f);
            s_4405_m.Y_259_p.J_1907_R();
            s_4405_m.Y_259_p.n_1700_B("location", (float)(minX * mw.w_1457_N()), (float)((double)mw.h_1847_R() - height * mw.w_1457_N() - minY * mw.w_1457_N()));
            s_4405_m.Y_259_p.n_1700_B("rectSize", (float)(width * mw.w_1457_N()), (float)(height * mw.w_1457_N()));
            s_4405_m.Y_259_p.n_1700_B("tex", new int[]{0});
            s_4405_m.Y_259_p.n_1700_B("alpha", alphaVal);
            s_4405_m.Y_259_p.n_1700_B("color1", (float)H_2506_c.n_1700_B(color1) / 255.0f, (float)H_2506_c.J_1907_R(color1) / 255.0f, (float)H_2506_c.R_4764_Y(color1) / 255.0f, 1.0f);
            s_4405_m.Y_259_p.n_1700_B("color2", (float)H_2506_c.n_1700_B(color2) / 255.0f, (float)H_2506_c.J_1907_R(color2) / 255.0f, (float)H_2506_c.R_4764_Y(color2) / 255.0f, 1.0f);
            s_4405_m.Y_259_p.n_1700_B("color3", (float)H_2506_c.n_1700_B(color3) / 255.0f, (float)H_2506_c.J_1907_R(color3) / 255.0f, (float)H_2506_c.R_4764_Y(color3) / 255.0f, 1.0f);
            s_4405_m.Y_259_p.n_1700_B("color4", (float)H_2506_c.n_1700_B(color4) / 255.0f, (float)H_2506_c.J_1907_R(color4) / 255.0f, (float)H_2506_c.R_4764_Y(color4) / 255.0f, 1.0f);
        }
        e_2866_D view = TotemPop.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        double x = snap.n_1700_B.J_1907_R - view.J_1907_R;
        double y = snap.n_1700_B.R_4764_Y + (double)yOffset - view.R_4764_Y;
        double z = snap.n_1700_B.G_564_y - view.G_564_y;
        g_221_o stack = new g_221_o();
        stack.n_1700_B();
        o_3091_w.n_1700_B buffer = c_3005_b.j_1564_a().J_1907_R();
        w_2040_b rendererManager = c_3005_b.O_508_d();
        Z_2049_e<a_3913_L> renderer = rendererManager.n_1700_B(player);
        Z_1993_T mainHandItem = player.R_4764_Y(x_1688_C.n_1700_B).t_148_a();
        Z_1993_T offHandItem = player.R_4764_Y(x_1688_C.J_1907_R).t_148_a();
        Z_1993_T[] armorItems = new Z_1993_T[player.l_1268_F.J_1907_R.size()];
        for (i = 0; i < player.l_1268_F.J_1907_R.size(); ++i) {
            armorItems[i] = player.l_1268_F.J_1907_R.get(i).t_148_a();
            player.l_1268_F.J_1907_R.set(i, Z_1993_T.J_1907_R);
        }
        player.n_1700_B(x_1688_C.n_1700_B, Z_1993_T.J_1907_R);
        player.n_1700_B(x_1688_C.J_1907_R, Z_1993_T.J_1907_R);
        try {
            rendererManager.n_1700_B(player, x, y, z, player.p_178_J, c_3005_b.RealmsClientConfig(), stack, buffer, 0xF000F0);
        }
        finally {
            player.n_1700_B(x_1688_C.n_1700_B, mainHandItem);
            player.n_1700_B(x_1688_C.J_1907_R, offHandItem);
            for (i = 0; i < player.l_1268_F.J_1907_R.size(); ++i) {
                player.l_1268_F.J_1907_R.set(i, armorItems[i]);
            }
            buffer.J_1907_R();
            stack.J_1907_R();
            if (useShader) {
                s_4405_m.Y_259_p.R_4764_Y();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        Packet<?> entity;
        if (!event.J_1907_R()) {
            return;
        }
        Packet<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof C_1375_J) {
            C_1375_J statusPacket = (C_1375_J)t_3138_Z2;
            entity = statusPacket.n_1700_B(TotemPop.c_3005_b.Y_601_j);
            if (entity instanceof a_3913_L && entity != TotemPop.c_3005_b.Y_259_p && statusPacket.J_1907_R() == 35) {
                UUID playerUuid = ((N_4263_v)((Object)entity)).w_2705_t();
                int pops = this.v_4262_N.getOrDefault(playerUuid, 0) + 1;
                this.v_4262_N.put(playerUuid, pops);
                e_2866_D pos = ((N_4263_v)((Object)entity)).s_4990_V();
                n_1700_B snapshot = new n_1700_B(pos, System.currentTimeMillis());
                this.w_1484_f.computeIfAbsent(playerUuid, k -> new LinkedList()).add(snapshot);
                String playerName = ((N_4263_v)((Object)entity)).O_1309_Q().getString();
                U_3758_B.n_1700_B("O", playerName + " \u043f\u043e\u043f\u043d\u0443\u043b \u0442\u043e\u0442\u0435\u043c [x" + pops + "]", H_2506_c.n_1700_B(255, 215, 0));
            }
            return;
        }
        entity = event.G_564_y();
        if (entity instanceof ClientboundPlayerCombatPacket) {
            N_4263_v deadEntity;
            ClientboundPlayerCombatPacket combatPacket = (ClientboundPlayerCombatPacket)entity;
            if (combatPacket.n_1700_B == ClientboundPlayerCombatPacket.n_1700_B.R_4764_Y && (deadEntity = TotemPop.c_3005_b.Y_601_j.J_1907_R(combatPacket.J_1907_R)) instanceof a_3913_L) {
                UUID deadUuid = deadEntity.w_2705_t();
                Integer pops = this.v_4262_N.remove(deadUuid);
                this.w_1484_f.remove(deadUuid);
                if (pops != null && pops > 0) {
                    String name = deadEntity.O_1309_Q().getString();
                    U_3758_B.n_1700_B("O", name + " \u0443\u043c\u0435\u0440. \u041f\u043e\u043f\u043d\u0443\u043b \u0442\u043e\u0442\u0435\u043c\u043e\u0432: " + pops, H_2506_c.n_1700_B(255, 50, 50));
                }
            }
        }
    }

    private static class n_1700_B {
        private final e_2866_D n_1700_B;
        private final long J_1907_R;

        private n_1700_B(e_2866_D position, long timeMs) {
            this.n_1700_B = position;
            this.J_1907_R = timeMs;
        }
    }
}



