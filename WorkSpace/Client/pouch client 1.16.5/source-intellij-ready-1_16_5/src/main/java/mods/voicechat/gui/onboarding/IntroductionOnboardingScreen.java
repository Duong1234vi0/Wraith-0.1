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
import mods.voicechat.gui.onboarding.MicOnboardingScreen;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;
import mods.voicechat.gui.onboarding.SkipOnboardingScreen;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public class IntroductionOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.introduction.title", CommonCompatibilityManager.INSTANCE.getModName()).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a DESCRIPTION = new F_2904_S("message.voicechat.onboarding.introduction.description");
    private static final x_282_a SKIP = new F_2904_S("message.voicechat.onboarding.introduction.skip");

    public IntroductionOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        Button skipButton = new Button(this.guiLeft, this.guiTop + this.contentHeight - 40 - 8, this.contentWidth, 20, SKIP, button -> this.minecraft.n_1700_B(new SkipOnboardingScreen(this)));
        this.addButton(skipButton);
        this.addBackOrCancelButton();
        this.addNextButton();
    }

    @Override
    public k_2603_m getNextScreen() {
        return new MicOnboardingScreen(this);
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, TITLE);
        this.renderMultilineText(stack, DESCRIPTION);
    }
}


