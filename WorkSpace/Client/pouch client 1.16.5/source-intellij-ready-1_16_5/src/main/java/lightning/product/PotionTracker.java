/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import lightning.product.D_4024_W;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.TextColor;
import lightning.product.L_1875_m;
import lightning.product.U_2474_c;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.V_3137_a;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.c_973_a;
import lightning.product.g_2336_b;
import lightning.product.k_2610_C;
import lightning.product.k_3129_Y;
import lightning.product.BooleanSetting;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import lightning.product.ModuleCategory;

public class PotionTracker
extends Module {
    private final BooleanSetting ignorirovatSebyaEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0441\u0435\u0431\u044f", false);
    private final BooleanSetting ignorirovatObychnyeZelyaEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043e\u0431\u044b\u0447\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", true);

    public PotionTracker() {
        super("PotionTracker", ModuleCategory.P_1922_E);
        this.addSettings(this.ignorirovatSebyaEnabled, this.ignorirovatObychnyeZelyaEnabled);
    }

    @Y_1740_V
    public void n_1700_B(k_3129_Y event) {
        if (this.ignorirovatSebyaEnabled.isEnabled().booleanValue() && event.J_1907_R() instanceof a_3913_L && event.J_1907_R().equals(PotionTracker.c_3005_b.Y_259_p)) {
            return;
        }
        Z_1993_T stack = event.R_4764_Y();
        ArrayList<k_2610_C> displayedEffects = new ArrayList<k_2610_C>(event.P_1922_E());
        if (this.ignorirovatObychnyeZelyaEnabled.isEnabled().booleanValue() && "minecraft".equals(V_3137_a.B_1668_F.J_1907_R(L_1875_m.G_564_y(stack)).R_4764_Y())) {
            return;
        }
        int totalReceivedDuration = 0;
        int totalMaxDuration = 0;
        for (k_2610_C effect : displayedEffects) {
            totalReceivedDuration += (int)((double)effect.J_1907_R() * event.G_564_y());
            totalMaxDuration += effect.J_1907_R();
        }
        int receivedSeconds = totalReceivedDuration / 20;
        int maxSeconds = totalMaxDuration / 20;
        int successPercentage = maxSeconds > 0 ? receivedSeconds * 100 / maxSeconds : 100;
        StringBuilder effectsList = new StringBuilder();
        for (k_2610_C effect : displayedEffects) {
            String effectName = effect.n_1700_B().G_564_y().getString();
            int duration = (int)((double)effect.J_1907_R() * event.G_564_y());
            int minutes = duration / 20 / 60;
            int seconds = duration / 20 % 60;
            String romanAmplifier = U_2474_c.n_1700_B(effect.R_4764_Y() + 1);
            effectsList.append((Object)D_4024_W.w_1484_f).append("\u25cf ").append((Object)D_4024_W.P_4830_p).append(effectName).append(" ").append(romanAmplifier).append((Object)D_4024_W.w_1484_f).append(" ").append(String.format("%d:%02d", minutes, seconds)).append("\n");
        }
        if (effectsList.length() > 1) {
            effectsList.setLength(effectsList.length() - 1);
        }
        ArrayList<x_282_a> tooltipLines = new ArrayList<x_282_a>();
        tooltipLines.add(stack.multiplayerClientSuggestionProvider().P_1922_E());
        L_1875_m.n_1700_B(stack, tooltipLines, 1.0f);
        U_2871_b hoverText = new U_2871_b("");
        for (int i = 0; i < tooltipLines.size(); ++i) {
            hoverText.n_1700_B((x_282_a)tooltipLines.get(i));
            if (i >= tooltipLines.size() - 1) continue;
            hoverText.n_1700_B("\n");
        }
        c_973_a hoverEvent = new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverText);
        MutableComponent chatMessage = new U_2871_b("").n_1700_B(new U_2871_b(event.J_1907_R().O_1309_Q().getString()).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(new U_2871_b(" \u043f\u043e\u043b\u0443\u0447\u0438\u043b \u044d\u0444\u0444\u0435\u043a\u0442\u044b \u0438\u0437 ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(event.R_4764_Y().multiplayerClientSuggestionProvider()).n_1700_B(new U_2871_b("\n").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(new U_2871_b("\u25cf \u0423\u0441\u043f\u0435\u0448\u043d\u043e\u0441\u0442\u044c ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f))).n_1700_B(new U_2871_b(successPercentage + "%").n_1700_B(new U_2871_b("").n_1700_B().n_1700_B(TextColor.n_1700_B(H_2506_c.n_1700_B(Color.RED.getRGB(), Color.GREEN.getRGB(), (float)successPercentage / 100.0f))))).n_1700_B(new U_2871_b("\n" + String.valueOf(effectsList)).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.w_1484_f)));
        chatMessage.n_1700_B(chatMessage.n_1700_B().n_1700_B(hoverEvent));
        v_1900_v.n_1700_B(chatMessage, new Object[0]);
        MutableComponent notificationMessage = new U_2871_b("").n_1700_B(new U_2871_b(event.J_1907_R().O_1309_Q().getString()).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b(" \u043f\u043e\u043b\u0443\u0447\u0438\u043b \u044d\u0444\u0444\u0435\u043a\u0442\u044b \u0438\u0437 ").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.M_182_A))).n_1700_B(((MutableComponent)stack.multiplayerClientSuggestionProvider()).n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b("\n").n_1700_B(new U_2871_b("").n_1700_B().J_1907_R(D_4024_W.Q_2552_b))).n_1700_B(new U_2871_b(" " + successPercentage + "%").n_1700_B(new U_2871_b("").n_1700_B().n_1700_B(TextColor.n_1700_B(H_2506_c.n_1700_B(Color.RED.getRGB(), Color.GREEN.getRGB(), (float)successPercentage / 100.0f)))));
        int potionColor = L_1875_m.R_4764_Y(stack);
        U_3758_B.n_1700_B(new g_2336_b("minecraft", "textures/item/splash_potion.png"), notificationMessage, potionColor);
    }
}



