/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.onboarding;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.Button;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;

public abstract class OnboardingScreenBase
extends k_2603_m {
    public static final x_282_a NEXT = new F_2904_S("message.voicechat.onboarding.next");
    public static final x_282_a BACK = new F_2904_S("message.voicechat.onboarding.back");
    public static final x_282_a CANCEL = new F_2904_S("message.voicechat.onboarding.cancel");
    protected static final int TEXT_COLOR = -1;
    protected static final int PADDING = 8;
    protected static final int SMALL_PADDING = 2;
    protected static final int BUTTON_HEIGHT = 20;
    protected int contentWidth;
    protected int guiLeft;
    protected int guiTop;
    protected int contentHeight;
    @Nullable
    protected k_2603_m previous;

    public OnboardingScreenBase(x_282_a title, @Nullable k_2603_m previous) {
        super(title);
        this.previous = previous;
    }

    @Override
    protected void init() {
        super.init();
        this.contentWidth = this.width / 2;
        this.guiLeft = (this.width - this.contentWidth) / 2;
        this.guiTop = 20;
        this.contentHeight = this.height - this.guiTop * 2;
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Nullable
    public k_2603_m getNextScreen() {
        return null;
    }

    protected void addPositiveButton(x_282_a text, Button.n_1700_B onPress) {
        Button nextButton = new Button(this.guiLeft + this.contentWidth / 2 + 4, this.guiTop + this.contentHeight - 20, this.contentWidth / 2 - 4, 20, text, onPress);
        this.addButton(nextButton);
    }

    protected void addNextButton() {
        this.addPositiveButton(NEXT, button -> this.minecraft.n_1700_B(this.getNextScreen()));
    }

    protected void addBackOrCancelButton(boolean big) {
        x_282_a text = CANCEL;
        if (this.previous instanceof OnboardingScreenBase) {
            text = BACK;
        }
        Button cancel = new Button(this.guiLeft, this.guiTop + this.contentHeight - 20, big ? this.contentWidth : this.contentWidth / 2 - 4, 20, text, button -> this.minecraft.n_1700_B(this.previous));
        this.addButton(cancel);
    }

    protected void addBackOrCancelButton() {
        this.addBackOrCancelButton(false);
    }

    protected void renderTitle(g_221_o stack, x_282_a titleComponent) {
        int titleWidth = this.font.n_1700_B((FormattedText)titleComponent);
        this.font.n_1700_B(stack, titleComponent.u_1723_Y(), (float)(this.width / 2 - titleWidth / 2), (float)this.guiTop, -1);
    }

    protected void renderMultilineText(g_221_o stack, x_282_a textComponent) {
        List<FormattedCharSequence> text = this.font.J_1907_R(textComponent, this.contentWidth);
        for (int i = 0; i < text.size(); ++i) {
            FormattedCharSequence line = text.get(i);
            this.font.n_1700_B(stack, line, (float)(this.width / 2 - this.font.n_1700_B(line) / 2), (float)(this.guiTop + this.font.n_1700_B + 20 + i * (this.font.n_1700_B + 1)), -1);
        }
    }
}


