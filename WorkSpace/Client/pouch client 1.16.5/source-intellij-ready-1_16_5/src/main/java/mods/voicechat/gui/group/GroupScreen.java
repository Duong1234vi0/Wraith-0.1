/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.group;

import java.util.Collections;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.U_2871_b;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.api.Group;
import mods.voicechat.gui.GroupType;
import mods.voicechat.gui.group.GroupList;
import mods.voicechat.gui.group.JoinGroupScreen;
import mods.voicechat.gui.tooltips.DisableTooltipSupplier;
import mods.voicechat.gui.tooltips.HideGroupHudTooltipSupplier;
import mods.voicechat.gui.tooltips.MuteTooltipSupplier;
import mods.voicechat.gui.widgets.ImageButton;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.gui.widgets.ToggleImageButton;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.LeaveGroupPacket;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.MicrophoneActivationType;
import mods.voicechat.voice.common.ClientGroup;

public class GroupScreen
extends ListScreenBase {
    protected static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_group.png");
    protected static final g_2336_b LEAVE = new g_2336_b("voicechat/textures/icons/leave.png");
    protected static final g_2336_b MICROPHONE = new g_2336_b("voicechat/textures/icons/microphone_button.png");
    protected static final g_2336_b SPEAKER = new g_2336_b("voicechat/textures/icons/speaker_button.png");
    protected static final g_2336_b GROUP_HUD = new g_2336_b("voicechat/textures/icons/group_hud_button.png");
    protected static final x_282_a TITLE = new F_2904_S("gui.voicechat.group.title");
    protected static final x_282_a LEAVE_GROUP = new F_2904_S("message.voicechat.leave_group");
    protected static final int HEADER_SIZE = 16;
    protected static final int FOOTER_SIZE = 32;
    protected static final int UNIT_SIZE = 18;
    protected static final int CELL_HEIGHT = 36;
    protected GroupList groupList;
    protected int units;
    protected final ClientGroup group;
    protected ToggleImageButton mute;
    protected ToggleImageButton disable;
    protected ToggleImageButton showHUD;
    protected ImageButton leave;

    public GroupScreen(ClientGroup group) {
        super(TITLE, 236, 0);
        this.group = group;
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft += 2;
        this.guiTop = 32;
        int minUnits = u_530_F.u_1723_Y(2.2222223f);
        this.units = Math.max(minUnits, (this.height - 16 - 32 - this.guiTop * 2) / 18);
        this.ySize = 16 + this.units * 18 + 32;
        ClientPlayerStateManager stateManager = ClientManager.getPlayerStateManager();
        if (this.groupList != null) {
            this.groupList.updateSize(this.width, this.units * 18, this.guiTop + 16);
        } else {
            this.groupList = new GroupList(this, this.width, this.units * 18, this.guiTop + 16, 36);
        }
        this.addListener(this.groupList);
        int buttonY = this.guiTop + this.ySize - 20 - 7;
        int buttonSize = 20;
        this.mute = new ToggleImageButton(this.guiLeft + 7, buttonY, MICROPHONE, stateManager::isMuted, button -> stateManager.setMuted(!stateManager.isMuted()), new MuteTooltipSupplier(this, stateManager));
        this.addButton(this.mute);
        this.disable = new ToggleImageButton(this.guiLeft + 7 + buttonSize + 3, buttonY, SPEAKER, stateManager::isDisabled, button -> stateManager.setDisabled(!stateManager.isDisabled()), new DisableTooltipSupplier(this, stateManager));
        this.addButton(this.disable);
        this.showHUD = new ToggleImageButton(this.guiLeft + 7 + (buttonSize + 3) * 2, buttonY, GROUP_HUD, () -> VoicechatClient.CLIENT_CONFIG.showGroupHud.get(), button -> VoicechatClient.CLIENT_CONFIG.showGroupHud.set((Object)((Boolean)VoicechatClient.CLIENT_CONFIG.showGroupHud.get() == false ? 1 : 0)).save(), new HideGroupHudTooltipSupplier(this));
        this.addButton(this.showHUD);
        this.leave = new ImageButton(this.guiLeft + this.xSize - buttonSize - 7, buttonY, LEAVE, button -> {
            ClientServerNetManager.sendToServer(new LeaveGroupPacket());
            this.minecraft.n_1700_B(new JoinGroupScreen());
        }, (button, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, Collections.singletonList(LEAVE_GROUP.u_1723_Y()), mouseX, mouseY));
        this.addButton(this.leave);
        this.checkButtons();
    }

    @Override
    public void tick() {
        super.tick();
        this.checkButtons();
    }

    private void checkButtons() {
        this.mute.active = ((MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get())).equals((Object)MicrophoneActivationType.VOICE);
        this.showHUD.active = (Boolean)VoicechatClient.CLIENT_CONFIG.hideIcons.get() == false;
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.minecraft.G_624_v().n_1700_B(TEXTURE);
        this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, 16);
        for (int i = 0; i < this.units; ++i) {
            this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * i, 0, 16, this.xSize, 18);
        }
        this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * this.units, 0, 34, this.xSize, 32);
        this.blit(poseStack, this.guiLeft + 10, this.guiTop + 16 + 6 - 2, this.xSize, 0, 12, 12);
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        F_2904_S title = this.group.getType().equals(Group.Type.NORMAL) ? new F_2904_S("message.voicechat.group_title", new U_2871_b(this.group.getName())) : new F_2904_S("message.voicechat.group_type_title", new U_2871_b(this.group.getName()), GroupType.fromType(this.group.getType()).getTranslation());
        this.font.J_1907_R(poseStack, title, (float)(this.guiLeft + this.xSize / 2 - this.font.n_1700_B((FormattedText)title) / 2), (float)(this.guiTop + 5), 0x404040);
        this.groupList.render(poseStack, mouseX, mouseY, delta);
    }
}


