/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Y_408_h;
import lightning.product.Z_3822_q;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.ClientboundChatPacket;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class ChatBubbles
extends Module {
    private final NumberSetting vremyaPokazaSetting = new NumberSetting("\u0412\u0440\u0435\u043c\u044f \u043f\u043e\u043a\u0430\u0437\u0430", 5.0f, 1.0f, 15.0f, 0.5f);
    private final BooleanSetting peredGlazamiEnabled = new BooleanSetting("\u041f\u0435\u0440\u0435\u0434 \u0433\u043b\u0430\u0437\u0430\u043c\u0438", false);
    private final NumberSetting vysotaSetting = new NumberSetting("\u0412\u044b\u0441\u043e\u0442\u0430", 2.5f, 1.0f, 5.0f, 0.1f, () -> this.peredGlazamiEnabled.isEnabled() == false);
    private final NumberSetting distanciyaSetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 5.0f, 2.0f, 15.0f, 0.5f, this.peredGlazamiEnabled::isEnabled);
    private final NumberSetting masshtabSetting = new NumberSetting("\u041c\u0430\u0441\u0448\u0442\u0430\u0431", 1.0f, 0.5f, 3.0f, 0.1f);
    private final BooleanSetting fonEnabled = new BooleanSetting("\u0424\u043e\u043d", true);
    private final BooleanSetting tolkoSvoeEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441\u0432\u043e\u0451", false);
    private final Map<UUID, n_1700_B> h_1847_R = new ConcurrentHashMap<UUID, n_1700_B>();
    private final List<J_1907_R> Q_4569_t = new ArrayList<J_1907_R>();
    private static final Pattern M_182_A = Pattern.compile("^[\u25cb\u25cf\u25c6\u25c7\u2605\u2606\\s]*\\w+\\s+([\\w_]{3,16})\\s*[\u27a0\u2192\u25ba>\u00bb:]+\\s*(.+)$");
    private static final Pattern t_1786_h = Pattern.compile("^<?([\\w_]{3,16})>?\\s*[>:\u00bb\u27a0]+\\s*(.+)$");
    private static final Pattern multiplayerClientSuggestionProvider = Pattern.compile("^(?:\\[[^\\]]+\\]\\s*)+([\\w_]{3,16})\\s*[>:\u00bb\u27a0]+\\s*(.+)$");
    private static final Pattern w_1457_N = Pattern.compile("^\\[?([\\w_]{3,16})\\]?\\s*[>:\u00bb\u27a0]+\\s*(.+)$");
    private static final Pattern Y_601_j = Pattern.compile("([\\w_]{3,16})\\s*[\u27a0\u2192\u25ba>\u00bb:]+\\s*(.+)$");

    public ChatBubbles() {
        super("ChatBubbles", ModuleCategory.R_4764_Y);
        this.addSettings(this.vremyaPokazaSetting, this.peredGlazamiEnabled, this.vysotaSetting, this.distanciyaSetting, this.masshtabSetting, this.fonEnabled, this.tolkoSvoeEnabled);
    }

    @Override
    public void onDisable() {
        this.h_1847_R.clear();
        this.Q_4569_t.clear();
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        if (!event.J_1907_R()) {
            return;
        }
        if (ChatBubbles.c_3005_b.Y_259_p == null || ChatBubbles.c_3005_b.Y_601_j == null) {
            return;
        }
        Packet<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof ClientboundChatPacket) {
            ClientboundChatPacket packet = (ClientboundChatPacket)t_3138_Z2;
            if (packet.G_564_y() == Y_408_h.R_4764_Y) {
                return;
            }
            String message = packet.J_1907_R().getString();
            String cleanMessage = message.replaceAll("\u00a7[0-9a-fk-or]", "");
            String playerName = null;
            String chatText = null;
            Matcher matcher = M_182_A.matcher(cleanMessage);
            if (matcher.find()) {
                playerName = matcher.group(1);
                chatText = matcher.group(2);
            }
            if (playerName == null && (matcher = multiplayerClientSuggestionProvider.matcher(cleanMessage)).find()) {
                playerName = matcher.group(1);
                chatText = matcher.group(2);
            }
            if (playerName == null && (matcher = t_1786_h.matcher(cleanMessage)).find()) {
                playerName = matcher.group(1);
                chatText = matcher.group(2);
            }
            if (playerName == null && (matcher = w_1457_N.matcher(cleanMessage)).find()) {
                playerName = matcher.group(1);
                chatText = matcher.group(2);
            }
            if (playerName == null && (matcher = Y_601_j.matcher(cleanMessage)).find()) {
                playerName = matcher.group(1);
                chatText = matcher.group(2);
            }
            if (playerName == null || chatText == null) {
                return;
            }
            if (chatText.trim().isEmpty()) {
                return;
            }
            String finalPlayerName = playerName;
            a_3913_L player = ChatBubbles.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider().stream().filter(p -> p.O_1309_Q().getString().equalsIgnoreCase(finalPlayerName)).findFirst().orElse(null);
            if (player == null) {
                return;
            }
            if (this.tolkoSvoeEnabled.isEnabled().booleanValue() && player != ChatBubbles.c_3005_b.Y_259_p) {
                return;
            }
            long now = System.currentTimeMillis();
            String finalText = chatText.trim();
            this.h_1847_R.put(player.w_2705_t(), new n_1700_B(finalText, now));
            if (ChatBubbles.c_3005_b.Y_259_p != null) {
                float yaw = ChatBubbles.c_3005_b.Y_259_p.p_178_J;
                double distance = ((Float)this.distanciyaSetting.getValue()).doubleValue();
                double radYaw = Math.toRadians(yaw);
                double dirX = -Math.sin(radYaw);
                double dirZ = Math.cos(radYaw);
                double worldX = ChatBubbles.c_3005_b.Y_259_p.O_3598_v() + dirX * distance;
                double worldY = ChatBubbles.c_3005_b.Y_259_p.X_2960_b() + (double)ChatBubbles.c_3005_b.Y_259_p.X_1313_W() + 0.5;
                double worldZ = ChatBubbles.c_3005_b.Y_259_p.l_2647_k() + dirZ * distance;
                this.Q_4569_t.add(new J_1907_R(playerName, finalText, now, worldX, worldY, worldZ));
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (ChatBubbles.c_3005_b.Y_259_p == null || ChatBubbles.c_3005_b.Y_601_j == null) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long maxTime = (long)(((Float)this.vremyaPokazaSetting.getValue()).floatValue() * 1000.0f);
        this.h_1847_R.entrySet().removeIf(entry -> currentTime - ((n_1700_B)entry.getValue()).J_1907_R > maxTime + 100L);
        this.Q_4569_t.removeIf(b -> currentTime - b.R_4764_Y > maxTime + 100L);
        if (this.peredGlazamiEnabled.isEnabled().booleanValue()) {
            this.n_1700_B(event, currentTime);
        } else {
            e_2866_D camera = ChatBubbles.c_3005_b.O_508_d().J_1907_R.J_1907_R();
            for (a_3913_L a_3913_L2 : ChatBubbles.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
                float alpha;
                n_1700_B bubble = this.h_1847_R.get(a_3913_L2.w_2705_t());
                if (bubble == null) continue;
                float elapsed = (float)(currentTime - bubble.J_1907_R) / 1000.0f;
                float maxTimeSeconds = ((Float)this.vremyaPokazaSetting.getValue()).floatValue();
                float fadeInProgress = u_530_F.n_1700_B(elapsed / 0.3f, 0.0f, 1.0f);
                float fadeIn = this.n_1700_B(fadeInProgress);
                float fadeOut = 1.0f;
                float fadeOutDuration = 1.5f;
                if (elapsed > maxTimeSeconds - fadeOutDuration) {
                    float fadeOutProgress = u_530_F.n_1700_B((maxTimeSeconds - elapsed) / fadeOutDuration, 0.0f, 1.0f);
                    fadeOut = this.n_1700_B(fadeOutProgress);
                }
                if ((alpha = fadeIn * fadeOut) <= 0.02f) continue;
                double x = u_530_F.G_564_y((double)event.J_1907_R(), a_3913_L2.q_1982_R, a_3913_L2.O_3598_v()) - camera.J_1907_R;
                double y = u_530_F.G_564_y((double)event.J_1907_R(), a_3913_L2.dtoRealmsServerAddress, a_3913_L2.X_2960_b()) - camera.R_4764_Y + (double)((Float)this.vysotaSetting.getValue()).floatValue();
                double z = u_530_F.G_564_y((double)event.J_1907_R(), a_3913_L2.w_612_n, a_3913_L2.l_2647_k()) - camera.G_564_y;
                this.n_1700_B(bubble.n_1700_B, x, y, z, alpha);
            }
        }
    }

    private void n_1700_B(I_4477_R event, long currentTime) {
        if (this.Q_4569_t.isEmpty()) {
            return;
        }
        e_2866_D camera = ChatBubbles.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        float maxTimeSeconds = ((Float)this.vremyaPokazaSetting.getValue()).floatValue();
        float spacing = 0.4f * ((Float)this.masshtabSetting.getValue()).floatValue();
        int count = 0;
        for (int i = this.Q_4569_t.size() - 1; i >= 0 && count < 5; --i) {
            float alpha;
            J_1907_R bubble = this.Q_4569_t.get(i);
            float elapsed = (float)(currentTime - bubble.R_4764_Y) / 1000.0f;
            float fadeInProgress = u_530_F.n_1700_B(elapsed / 0.3f, 0.0f, 1.0f);
            float fadeIn = this.n_1700_B(fadeInProgress);
            float fadeOut = 1.0f;
            float fadeOutDuration = 1.5f;
            if (elapsed > maxTimeSeconds - fadeOutDuration) {
                float fadeOutProgress = u_530_F.n_1700_B((maxTimeSeconds - elapsed) / fadeOutDuration, 0.0f, 1.0f);
                fadeOut = this.n_1700_B(fadeOutProgress);
            }
            if ((alpha = fadeIn * fadeOut) <= 0.02f) continue;
            float scaleAnim = 0.7f + 0.3f * this.n_1700_B(fadeInProgress);
            float yOffset = (1.0f - this.n_1700_B(Math.min(1.0f, elapsed / 0.5f))) * 0.3f;
            String fullText = bubble.n_1700_B + ": " + bubble.J_1907_R;
            double renderX = bubble.G_564_y - camera.J_1907_R;
            double renderY = bubble.P_1922_E - camera.R_4764_Y - (double)((float)count * spacing) + (double)yOffset;
            double renderZ = bubble.u_1723_Y - camera.G_564_y;
            this.n_1700_B(fullText, renderX, renderY, renderZ, alpha, scaleAnim);
            ++count;
        }
    }

    private float n_1700_B(float t) {
        return 1.0f - (1.0f - t) * (1.0f - t);
    }

    private float J_1907_R(float t) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        return 1.0f + c3 * (float)Math.pow(t - 1.0f, 3.0) + c1 * (float)Math.pow(t - 1.0f, 2.0);
    }

    private void n_1700_B(String message, double x, double y, double z, float alpha, float scaleAnim) {
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(x, y, z);
        float yaw = -ChatBubbles.c_3005_b.O_508_d().J_1907_R.P_1922_E();
        float pitch = ChatBubbles.c_3005_b.O_508_d().J_1907_R.G_564_y();
        c_4037_x.R_4764_Y(yaw, 0.0f, 1.0f, 0.0f);
        c_4037_x.R_4764_Y(pitch, 1.0f, 0.0f, 0.0f);
        float scaleFactor = ((Float)this.masshtabSetting.getValue()).floatValue() * 0.015f * scaleAnim;
        c_4037_x.J_1907_R(-scaleFactor, -scaleFactor, scaleFactor);
        c_4037_x.t_1786_h();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        Z_3822_q font = l_3370_o.P_1922_E[32];
        float textWidth = font.n_1700_B(message);
        float textHeight = font.h_1847_R();
        float textX = -textWidth / 2.0f;
        float textY = -textHeight / 2.0f;
        if (this.fonEnabled.isEnabled().booleanValue()) {
            float padding = 6.0f;
            int bgAlpha = (int)(200.0f * alpha);
            c_4037_x.e_4240_b();
            D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
            buffer.n_1700_B(7, E_688_b.Y_601_j);
            float r = 0.0f;
            float g = 0.0f;
            float b = 0.0f;
            float a = (float)bgAlpha / 255.0f;
            float left = textX - padding;
            float top = textY - padding;
            float right = textX + textWidth + padding;
            float bottom = textY + textHeight + padding;
            buffer.pos(left, bottom, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(right, bottom, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(right, top, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(left, top, 0.0).n_1700_B(r, g, b, a).endVertex();
            l_3747_P.n_1700_B().J_1907_R();
            c_4037_x.x_607_J();
        }
        int themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int textAlpha = (int)(255.0f * alpha);
        int textColor = H_2506_c.n_1700_B(themeColor, textAlpha);
        int shadowColor = H_2506_c.n_1700_B(0, 0, 0, textAlpha);
        g_221_o ms = new g_221_o();
        font.n_1700_B(ms, message, (double)(textX + 1.0f), (double)(textY + 1.0f), shadowColor);
        font.n_1700_B(ms, message, (double)textX, (double)textY, textColor);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(String message, double x, double y, double z, float alpha) {
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(x, y, z);
        float yaw = -ChatBubbles.c_3005_b.O_508_d().J_1907_R.P_1922_E();
        float pitch = ChatBubbles.c_3005_b.O_508_d().J_1907_R.G_564_y();
        c_4037_x.R_4764_Y(yaw, 0.0f, 1.0f, 0.0f);
        c_4037_x.R_4764_Y(pitch, 1.0f, 0.0f, 0.0f);
        float scaleFactor = ((Float)this.masshtabSetting.getValue()).floatValue() * 0.015f;
        c_4037_x.J_1907_R(-scaleFactor, -scaleFactor, scaleFactor);
        c_4037_x.t_1786_h();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        Z_3822_q font = l_3370_o.P_1922_E[32];
        float textWidth = font.n_1700_B(message);
        float textHeight = font.h_1847_R();
        float textX = -textWidth / 2.0f;
        float textY = -textHeight / 2.0f;
        if (this.fonEnabled.isEnabled().booleanValue()) {
            float padding = 6.0f;
            int bgAlpha = (int)(200.0f * alpha);
            c_4037_x.e_4240_b();
            D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
            buffer.n_1700_B(7, E_688_b.Y_601_j);
            float r = 0.0f;
            float g = 0.0f;
            float b = 0.0f;
            float a = (float)bgAlpha / 255.0f;
            float left = textX - padding;
            float top = textY - padding;
            float right = textX + textWidth + padding;
            float bottom = textY + textHeight + padding;
            buffer.pos(left, bottom, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(right, bottom, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(right, top, 0.0).n_1700_B(r, g, b, a).endVertex();
            buffer.pos(left, top, 0.0).n_1700_B(r, g, b, a).endVertex();
            l_3747_P.n_1700_B().J_1907_R();
            c_4037_x.x_607_J();
        }
        int themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int textAlpha = (int)(255.0f * alpha);
        int textColor = H_2506_c.n_1700_B(themeColor, textAlpha);
        int shadowColor = H_2506_c.n_1700_B(0, 0, 0, textAlpha);
        g_221_o ms = new g_221_o();
        font.n_1700_B(ms, message, (double)(textX + 1.0f), (double)(textY + 1.0f), shadowColor);
        font.n_1700_B(ms, message, (double)textX, (double)textY, textColor);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.d_2461_k();
    }

    static class n_1700_B {
        final String n_1700_B;
        final long J_1907_R;

        n_1700_B(String message, long startTime) {
            this.n_1700_B = message;
            this.J_1907_R = startTime;
        }
    }

    static class J_1907_R {
        final String n_1700_B;
        final String J_1907_R;
        final long R_4764_Y;
        final double G_564_y;
        final double P_1922_E;
        final double u_1723_Y;

        J_1907_R(String playerName, String message, long startTime, double worldX, double worldY, double worldZ) {
            this.n_1700_B = playerName;
            this.J_1907_R = message;
            this.R_4764_Y = startTime;
            this.G_564_y = worldX;
            this.P_1922_E = worldY;
            this.u_1723_Y = worldZ;
        }
    }
}



