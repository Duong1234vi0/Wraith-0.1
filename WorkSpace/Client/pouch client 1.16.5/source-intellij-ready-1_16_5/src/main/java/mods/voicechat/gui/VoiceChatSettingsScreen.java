/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.VoiceChatScreenBase;
import mods.voicechat.gui.audiodevice.SelectMicrophoneScreen;
import mods.voicechat.gui.audiodevice.SelectSpeakerScreen;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.widgets.DenoiserButton;
import mods.voicechat.gui.widgets.EnumButton;
import mods.voicechat.gui.widgets.KeybindButton;
import mods.voicechat.gui.widgets.MicActivationButton;
import mods.voicechat.gui.widgets.MicAmplificationSlider;
import mods.voicechat.gui.widgets.MicTestButton;
import mods.voicechat.gui.widgets.VoiceActivationSlider;
import mods.voicechat.gui.widgets.VoiceSoundSlider;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.MicrophoneActivationType;
import mods.voicechat.voice.client.speaker.AudioType;

public class VoiceChatSettingsScreen
extends VoiceChatScreenBase {
    private static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_voicechat_settings.png");
    private static final x_282_a TITLE = new F_2904_S("gui.voicechat.voice_chat_settings.title");
    private static final x_282_a ASSIGN_TOOLTIP = new F_2904_S("message.voicechat.press_to_reassign_key");
    private static final x_282_a PUSH_TO_TALK = new F_2904_S("message.voicechat.activation_type.ptt");
    private static final x_282_a ADJUST_VOLUMES = new F_2904_S("message.voicechat.adjust_volumes");
    private static final x_282_a SELECT_MICROPHONE = new F_2904_S("message.voicechat.select_microphone");
    private static final x_282_a SELECT_SPEAKER = new F_2904_S("message.voicechat.select_speaker");
    private static final x_282_a BACK = new F_2904_S("message.voicechat.back");
    @Nullable
    private final k_2603_m parent;
    private VoiceActivationSlider voiceActivationSlider;
    private MicTestButton micTestButton;
    private KeybindButton keybindButton;

    public VoiceChatSettingsScreen(@Nullable k_2603_m parent) {
        super(TITLE, 248, 219);
        this.parent = parent;
    }

    public VoiceChatSettingsScreen() {
        this((k_2603_m)null);
    }

    @Override
    protected void init() {
        super.init();
        int y = this.guiTop + 20;
        this.addButton(new VoiceSoundSlider(this.guiLeft + 10, y, this.xSize - 20, 20));
        this.addButton(new MicAmplificationSlider(this.guiLeft + 10, y += 21, this.xSize - 20, 20));
        this.addButton(new DenoiserButton(this.guiLeft + 10, y += 21, this.xSize - 20, 20));
        this.voiceActivationSlider = new VoiceActivationSlider(this.guiLeft + 10 + 20 + 1, (y += 21) + 21, this.xSize - 20 - 20 - 1, 20);
        this.micTestButton = new MicTestButton(this.guiLeft + 10, y + 21, this.voiceActivationSlider);
        this.keybindButton = new KeybindButton(V_4423_d.RealmsWorldOptions, this.guiLeft + 10 + 20 + 1, y + 21, this.xSize - 20 - 20 - 1, 20, PUSH_TO_TALK);
        this.addButton(new MicActivationButton(this.guiLeft + 10, y, this.xSize - 20, 20, type -> {
            this.voiceActivationSlider.visible = MicrophoneActivationType.VOICE.equals(type);
            this.keybindButton.visible = MicrophoneActivationType.PTT.equals(type);
        }));
        this.addButton(this.micTestButton);
        this.addButton(this.voiceActivationSlider);
        this.addButton(this.keybindButton);
        this.addButton(new EnumButton<AudioType>(this.guiLeft + 10, y += 42, this.xSize - 20, 20, VoicechatClient.CLIENT_CONFIG.audioType){

            @Override
            protected x_282_a getText(AudioType type) {
                return new F_2904_S("message.voicechat.audio_type", type.getText());
            }

            @Override
            protected void onUpdate(AudioType type) {
                ClientVoicechat client = ClientManager.getClient();
                if (client != null) {
                    VoiceChatSettingsScreen.this.micTestButton.stop();
                    client.reloadAudio();
                }
            }
        });
        y += 21;
        if (this.isIngame()) {
            this.addButton(new Button(this.guiLeft + 10, y, this.xSize - 20, 20, ADJUST_VOLUMES, button -> this.minecraft.n_1700_B(new AdjustVolumesScreen())));
            y += 21;
        }
        this.addButton(new Button(this.guiLeft + 10, y, this.xSize / 2 - 15, 20, SELECT_MICROPHONE, button -> this.minecraft.n_1700_B(new SelectMicrophoneScreen(this))));
        this.addButton(new Button(this.guiLeft + this.xSize / 2 + 1, y, (this.xSize - 20) / 2 - 1, 20, SELECT_SPEAKER, button -> this.minecraft.n_1700_B(new SelectSpeakerScreen(this))));
        y += 21;
        if (!this.isIngame() && this.parent != null) {
            this.addButton(new Button(this.guiLeft + 10, y, this.xSize - 20, 20, BACK, button -> this.minecraft.n_1700_B(this.parent)));
        }
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.minecraft.G_624_v().n_1700_B(TEXTURE);
        if (this.isIngame()) {
            this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        }
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        int titleWidth = this.font.n_1700_B((FormattedText)TITLE);
        this.font.J_1907_R(poseStack, TITLE.u_1723_Y(), (float)(this.guiLeft + (this.xSize - titleWidth) / 2), (float)(this.guiTop + 7), this.getFontColor());
        x_282_a sliderTooltip = this.voiceActivationSlider.getHoverText();
        if (this.voiceActivationSlider.isHovered() && sliderTooltip != null) {
            this.renderTooltip(poseStack, sliderTooltip, mouseX, mouseY);
        } else if (this.micTestButton.isHovered()) {
            this.micTestButton.renderToolTip(poseStack, mouseX, mouseY);
        } else if (this.keybindButton.isHovered()) {
            this.renderTooltip(poseStack, ASSIGN_TOOLTIP, mouseX, mouseY);
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        if (this.keybindButton.isListening()) {
            return false;
        }
        return super.shouldCloseOnEsc();
    }
}


