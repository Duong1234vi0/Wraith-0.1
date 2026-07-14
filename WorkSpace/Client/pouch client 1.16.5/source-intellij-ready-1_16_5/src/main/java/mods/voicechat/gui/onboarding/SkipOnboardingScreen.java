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
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;

public class SkipOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.skip.title").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a DESCRIPTION = new F_2904_S("message.voicechat.onboarding.skip.description");
    private static final x_282_a CONFIRM = new F_2904_S("message.voicechat.onboarding.confirm");

    public SkipOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        this.addBackOrCancelButton();
        this.addPositiveButton(CONFIRM, button -> OnboardingManager.finishOnboarding());
    }

    @Override
    public k_2603_m getNextScreen() {
        return this.previous;
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, TITLE);
        this.renderMultilineText(stack, DESCRIPTION);
    }
}


