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
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;
import mods.voicechat.gui.onboarding.PttOnboardingScreen;
import mods.voicechat.gui.onboarding.VoiceActivationOnboardingScreen;
import mods.voicechat.voice.client.MicrophoneActivationType;

public class ActivationOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.activation.title").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a DESCRIPTION = new F_2904_S("message.voicechat.onboarding.activation").n_1700_B("\n\n").n_1700_B(new F_2904_S("message.voicechat.onboarding.activation.ptt", new F_2904_S("message.voicechat.onboarding.activation.ptt.name").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j))).n_1700_B("\n\n").n_1700_B(new F_2904_S("message.voicechat.onboarding.activation.voice", new F_2904_S("message.voicechat.onboarding.activation.voice.name").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Y_601_j)));

    public ActivationOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        Button ptt = new Button(this.guiLeft, this.guiTop + this.contentHeight - 40 - 8, this.contentWidth / 2 - 4, 20, new F_2904_S("message.voicechat.onboarding.activation.ptt.name"), button -> {
            VoicechatClient.CLIENT_CONFIG.microphoneActivationType.set((Object)MicrophoneActivationType.PTT).save();
            this.minecraft.n_1700_B(new PttOnboardingScreen(this));
        });
        this.addButton(ptt);
        Button voice = new Button(this.guiLeft + this.contentWidth / 2 + 4, this.guiTop + this.contentHeight - 40 - 8, this.contentWidth / 2 - 4, 20, new F_2904_S("message.voicechat.onboarding.activation.voice.name"), button -> {
            VoicechatClient.CLIENT_CONFIG.microphoneActivationType.set((Object)MicrophoneActivationType.VOICE).save();
            this.minecraft.n_1700_B(new VoiceActivationOnboardingScreen(this));
        });
        this.addButton(voice);
        this.addBackOrCancelButton(true);
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, TITLE);
        this.renderMultilineText(stack, DESCRIPTION);
    }
}


