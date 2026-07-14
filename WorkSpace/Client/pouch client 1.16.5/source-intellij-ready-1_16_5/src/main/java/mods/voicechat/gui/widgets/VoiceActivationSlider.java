/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.widgets;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.DebouncedSlider;
import mods.voicechat.gui.widgets.MicTestButton;
import mods.voicechat.voice.common.Utils;

public class VoiceActivationSlider
extends DebouncedSlider
implements MicTestButton.MicListener {
    private static final g_2336_b SLIDER = new g_2336_b("voicechat/textures/gui/voice_activation_slider.png");
    private static final x_282_a NO_ACTIVATION = new F_2904_S("message.voicechat.voice_activation.disabled").n_1700_B(D_4024_W.P_4830_p);
    private double micValue;

    public VoiceActivationSlider(int x, int y, int width, int height) {
        super(x, y, width, height, new U_2871_b(""), Utils.dbToPerc(((Double)VoicechatClient.CLIENT_CONFIG.voiceActivationThreshold.get()).floatValue()));
        this.func_230979_b_();
    }

    @Override
    protected void renderBg(g_221_o poseStack, MinecraftClient minecraft, int i, int j) {
        minecraft.G_624_v().n_1700_B(SLIDER);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        int width = (int)((double)(this.getWidth() - 2) * this.micValue);
        this.blit(poseStack, this.x + 1, this.y + 1, 0, 0, width, 18);
        super.renderBg(poseStack, minecraft, i, j);
    }

    @Override
    protected void func_230979_b_() {
        long db = Math.round(Utils.percToDb(this.sliderValue));
        F_2904_S component = new F_2904_S("message.voicechat.voice_activation", db);
        if (db >= -10L) {
            component.n_1700_B(D_4024_W.P_4830_p);
        }
        this.setMessage(component);
    }

    @Nullable
    public x_282_a getHoverText() {
        if (this.sliderValue >= 1.0) {
            return NO_ACTIVATION;
        }
        return null;
    }

    @Override
    public boolean isHovered() {
        return this.isHovered;
    }

    @Override
    public void applyDebounced() {
        VoicechatClient.CLIENT_CONFIG.voiceActivationThreshold.set((Object)Utils.percToDb(this.sliderValue)).save();
    }

    @Override
    public void onMicValue(double percentage) {
        this.micValue = percentage;
    }
}


