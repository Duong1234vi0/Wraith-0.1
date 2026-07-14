/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.onboarding;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.V_4423_d;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.onboarding.IntroductionOnboardingScreen;
import mods.voicechat.voice.client.ChatUtils;
import mods.voicechat.voice.client.ClientManager;

public class OnboardingManager {
    private static final MinecraftClient MC = MinecraftClient.A_4115_X();

    public static boolean isOnboarding() {
        return (Boolean)VoicechatClient.CLIENT_CONFIG.onboardingFinished.get() == false;
    }

    public static void startOnboarding(@Nullable k_2603_m parent) {
        MC.n_1700_B(OnboardingManager.getOnboardingScreen(parent));
    }

    public static k_2603_m getOnboardingScreen(@Nullable k_2603_m parent) {
        return new IntroductionOnboardingScreen(parent);
    }

    public static void finishOnboarding() {
        VoicechatClient.CLIENT_CONFIG.muted.set((Object)true).save();
        VoicechatClient.CLIENT_CONFIG.disabled.set((Object)false).save();
        VoicechatClient.CLIENT_CONFIG.onboardingFinished.set((Object)true).save();
        ClientManager.getPlayerStateManager().onFinishOnboarding();
        MC.n_1700_B((k_2603_m)null);
    }

    public static void onConnecting() {
        if (!OnboardingManager.isOnboarding()) {
            return;
        }
        ChatUtils.sendModMessage(new F_2904_S("message.voicechat.set_up", V_4423_d.ValueObject.s_956_w().P_1922_E().n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j)));
    }
}



