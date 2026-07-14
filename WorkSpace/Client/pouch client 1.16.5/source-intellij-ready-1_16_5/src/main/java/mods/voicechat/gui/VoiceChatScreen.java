/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui;

import java.util.Objects;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.VoiceChatScreenBase;
import mods.voicechat.gui.VoiceChatSettingsScreen;
import mods.voicechat.gui.group.GroupScreen;
import mods.voicechat.gui.group.JoinGroupScreen;
import mods.voicechat.gui.tooltips.DisableTooltipSupplier;
import mods.voicechat.gui.tooltips.HideTooltipSupplier;
import mods.voicechat.gui.tooltips.MuteTooltipSupplier;
import mods.voicechat.gui.tooltips.RecordingTooltipSupplier;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.widgets.ImageButton;
import mods.voicechat.gui.widgets.ToggleImageButton;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.voice.client.AudioRecorder;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.common.ClientGroup;

public class VoiceChatScreen
extends VoiceChatScreenBase {
    private static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_voicechat.png");
    private static final g_2336_b MICROPHONE = new g_2336_b("voicechat/textures/icons/microphone_button.png");
    private static final g_2336_b HIDE = new g_2336_b("voicechat/textures/icons/hide_button.png");
    private static final g_2336_b VOLUMES = new g_2336_b("voicechat/textures/icons/adjust_volumes.png");
    private static final g_2336_b SPEAKER = new g_2336_b("voicechat/textures/icons/speaker_button.png");
    private static final g_2336_b RECORD = new g_2336_b("voicechat/textures/icons/record_button.png");
    private static final x_282_a TITLE = new F_2904_S("gui.voicechat.voice_chat.title");
    private static final x_282_a SETTINGS = new F_2904_S("message.voicechat.settings");
    private static final x_282_a GROUP = new F_2904_S("message.voicechat.group");
    private static final x_282_a ADJUST_PLAYER_VOLUMES = new F_2904_S("message.voicechat.adjust_volumes");
    private ToggleImageButton mute;
    private ToggleImageButton disable;
    private VoiceChatScreenBase.HoverArea recordingHoverArea;
    private ClientPlayerStateManager stateManager = ClientManager.getPlayerStateManager();

    public VoiceChatScreen() {
        super(TITLE, 195, 76);
    }

    @Override
    protected void init() {
        super.init();
        ClientVoicechat client = ClientManager.getClient();
        this.mute = new ToggleImageButton(this.guiLeft + 6, this.guiTop + this.ySize - 6 - 20, MICROPHONE, this.stateManager::isMuted, button -> this.stateManager.setMuted(!this.stateManager.isMuted()), new MuteTooltipSupplier(this, this.stateManager));
        this.addButton(this.mute);
        this.disable = new ToggleImageButton(this.guiLeft + 6 + 20 + 2, this.guiTop + this.ySize - 6 - 20, SPEAKER, this.stateManager::isDisabled, button -> this.stateManager.setDisabled(!this.stateManager.isDisabled()), new DisableTooltipSupplier(this, this.stateManager));
        this.addButton(this.disable);
        ImageButton volumes = new ImageButton(this.guiLeft + 6 + 20 + 2 + 20 + 2, this.guiTop + this.ySize - 6 - 20, VOLUMES, button -> this.minecraft.n_1700_B(new AdjustVolumesScreen()), (button, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, ADJUST_PLAYER_VOLUMES, mouseX, mouseY));
        this.addButton(volumes);
        if (client != null && ((Boolean)VoicechatClient.CLIENT_CONFIG.useNatives.get()).booleanValue() && (client.getRecorder() != null || client.getConnection() != null && client.getConnection().getData().allowRecording())) {
            ToggleImageButton record = new ToggleImageButton(this.guiLeft + this.xSize - 6 - 20 - 2 - 20, this.guiTop + this.ySize - 6 - 20, RECORD, () -> ClientManager.getClient() != null && ClientManager.getClient().getRecorder() != null, button -> this.toggleRecording(), new RecordingTooltipSupplier(this));
            this.addButton(record);
        }
        ToggleImageButton hide = new ToggleImageButton(this.guiLeft + this.xSize - 6 - 20, this.guiTop + this.ySize - 6 - 20, HIDE, () -> VoicechatClient.CLIENT_CONFIG.hideIcons.get(), button -> VoicechatClient.CLIENT_CONFIG.hideIcons.set((Object)((Boolean)VoicechatClient.CLIENT_CONFIG.hideIcons.get() == false ? 1 : 0)).save(), new HideTooltipSupplier(this));
        this.addButton(hide);
        Button settings = new Button(this.guiLeft + 6, this.guiTop + 6 + 15, 75, 20, SETTINGS, button -> this.minecraft.n_1700_B(new VoiceChatSettingsScreen()));
        this.addButton(settings);
        Button group = new Button(this.guiLeft + this.xSize - 6 - 75 + 1, this.guiTop + 6 + 15, 75, 20, GROUP, button -> {
            ClientGroup g = this.stateManager.getGroup();
            if (g != null) {
                this.minecraft.n_1700_B(new GroupScreen(g));
            } else {
                this.minecraft.n_1700_B(new JoinGroupScreen());
            }
        });
        this.addButton(group);
        group.active = client != null && client.getConnection() != null && client.getConnection().getData().groupsEnabled();
        this.recordingHoverArea = new VoiceChatScreenBase.HoverArea(72, this.ySize - 6 - 20, this.xSize - 122, 20);
        this.checkButtons();
    }

    @Override
    public void tick() {
        super.tick();
        this.checkButtons();
    }

    private void checkButtons() {
        this.mute.active = MuteTooltipSupplier.canMuteMic();
        this.disable.active = this.stateManager.canEnable();
    }

    private void toggleRecording() {
        ClientVoicechat c = ClientManager.getClient();
        if (c == null) {
            return;
        }
        c.toggleRecording();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.ValueObject).J_1907_R()) {
            this.minecraft.n_1700_B((k_2603_m)null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.minecraft.G_624_v().n_1700_B(TEXTURE);
        this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        int titleWidth = this.font.n_1700_B((FormattedText)TITLE);
        this.font.J_1907_R(poseStack, TITLE.u_1723_Y(), (float)(this.guiLeft + (this.xSize - titleWidth) / 2), (float)(this.guiTop + 7), 0x404040);
        ClientVoicechat client = ClientManager.getClient();
        if (client != null && client.getRecorder() != null) {
            AudioRecorder recorder = client.getRecorder();
            U_2871_b time = new U_2871_b(recorder.getDuration());
            MutableComponent h_2671_n = time.n_1700_B(D_4024_W.P_1922_E);
            float f = (float)(this.guiLeft + this.recordingHoverArea.getPosX()) + (float)this.recordingHoverArea.getWidth() / 2.0f - (float)this.font.n_1700_B((FormattedText)time) / 2.0f;
            float f2 = (float)(this.guiTop + this.recordingHoverArea.getPosY()) + (float)this.recordingHoverArea.getHeight() / 2.0f;
            Objects.requireNonNull(this.font);
            this.font.J_1907_R(poseStack, h_2671_n, f, f2 - 9.0f / 2.0f, 0);
            if (this.recordingHoverArea.isHovered(this.guiLeft, this.guiTop, mouseX, mouseY)) {
                this.renderTooltip(poseStack, new F_2904_S("message.voicechat.storage_size", recorder.getStorage()), mouseX, mouseY);
            }
        }
    }
}


