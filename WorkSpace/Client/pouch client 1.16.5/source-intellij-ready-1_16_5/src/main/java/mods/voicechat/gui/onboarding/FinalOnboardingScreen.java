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
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.VoiceChatScreen;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.voice.client.MicrophoneActivationType;

public class FinalOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.final").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a FINISH_SETUP = new F_2904_S("message.voicechat.onboarding.final.finish_setup");
    protected x_282_a description = new U_2871_b("");

    public FinalOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        MutableComponent text = new F_2904_S("message.voicechat.onboarding.final.description.success", V_4423_d.ValueObject.s_956_w().P_1922_E().n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j)).n_1700_B("\n\n");
        text = ((MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get())).equals((Object)MicrophoneActivationType.PTT) ? text.n_1700_B(new F_2904_S("message.voicechat.onboarding.final.description.ptt", V_4423_d.RealmsWorldOptions.s_956_w().P_1922_E().n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j)).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider)).n_1700_B("\n\n") : text.n_1700_B(new F_2904_S("message.voicechat.onboarding.final.description.voice", V_4423_d.RegionPingResult.s_956_w().P_1922_E().n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j)).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider)).n_1700_B("\n\n");
        this.description = text.n_1700_B(new F_2904_S("message.voicechat.onboarding.final.description.configuration"));
        this.addBackOrCancelButton();
        this.addPositiveButton(FINISH_SETUP, button -> OnboardingManager.finishOnboarding());
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, TITLE);
        this.renderMultilineText(stack, this.description);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            OnboardingManager.finishOnboarding();
            return true;
        }
        if (keyCode == ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.ValueObject).J_1907_R()) {
            OnboardingManager.finishOnboarding();
            this.minecraft.n_1700_B(new VoiceChatScreen());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}


