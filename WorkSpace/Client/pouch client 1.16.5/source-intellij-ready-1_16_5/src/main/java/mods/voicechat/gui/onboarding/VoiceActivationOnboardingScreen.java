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
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.gui.onboarding.FinalOnboardingScreen;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;
import mods.voicechat.gui.widgets.DenoiserButton;
import mods.voicechat.gui.widgets.MicAmplificationSlider;
import mods.voicechat.gui.widgets.MicTestButton;
import mods.voicechat.gui.widgets.VoiceActivationSlider;

public class VoiceActivationOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.voice.title").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a DESCRIPTION = new F_2904_S("message.voicechat.onboarding.voice.description");
    protected VoiceActivationSlider slider;
    protected MicTestButton micTestButton;

    public VoiceActivationOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        int bottom = this.guiTop + this.contentHeight - 24 - 40;
        int space = 22;
        this.addButton(new MicAmplificationSlider(this.guiLeft, bottom - space * 2, this.contentWidth, 20));
        this.addButton(new DenoiserButton(this.guiLeft, bottom - space, this.contentWidth, 20));
        this.slider = new VoiceActivationSlider(this.guiLeft + 20 + 2, bottom, this.contentWidth - 20 - 2, 20);
        this.micTestButton = new MicTestButton(this.guiLeft, bottom, this.slider);
        this.addButton(this.micTestButton);
        this.addButton(this.slider);
        this.addBackOrCancelButton();
        this.addNextButton();
    }

    @Override
    public k_2603_m getNextScreen() {
        return new FinalOnboardingScreen(this);
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, TITLE);
        this.renderMultilineText(stack, DESCRIPTION);
        x_282_a sliderTooltip = this.slider.getHoverText();
        if (this.slider.isHovered() && sliderTooltip != null) {
            this.renderTooltip(stack, sliderTooltip, mouseX, mouseY);
        } else if (this.micTestButton.isHovered()) {
            this.micTestButton.onTooltip(this.micTestButton, stack, mouseX, mouseY);
        }
    }
}


