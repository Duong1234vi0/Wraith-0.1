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
import lightning.product.FormattedText;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.gui.onboarding.FinalOnboardingScreen;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;
import mods.voicechat.gui.widgets.KeybindButton;

public class PttOnboardingScreen
extends OnboardingScreenBase {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.ptt.title").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    private static final x_282_a DESCRIPTION = new F_2904_S("message.voicechat.onboarding.ptt.description");
    private static final x_282_a BUTTON_DESCRIPTION = new F_2904_S("message.voicechat.onboarding.ptt.button_description");
    protected KeybindButton keybindButton;
    protected int keybindButtonPos;

    public PttOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    protected void init() {
        super.init();
        this.keybindButtonPos = this.guiTop + this.contentHeight - 60 - 16 - 40;
        this.keybindButton = new KeybindButton(V_4423_d.RealmsWorldOptions, this.guiLeft + 40, this.keybindButtonPos, this.contentWidth - 80, 20);
        this.addButton(this.keybindButton);
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
        this.font.n_1700_B(stack, BUTTON_DESCRIPTION.u_1723_Y(), (float)(this.width / 2 - this.font.n_1700_B((FormattedText)BUTTON_DESCRIPTION) / 2), (float)(this.keybindButtonPos - this.font.n_1700_B - 8), -1);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        if (this.keybindButton.isListening()) {
            return false;
        }
        return super.shouldCloseOnEsc();
    }
}


