/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.O_694_j;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.gui.VoiceChatScreenBase;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.JoinGroupPacket;
import mods.voicechat.voice.common.ClientGroup;

public class EnterPasswordScreen
extends VoiceChatScreenBase {
    private static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_enter_password.png");
    private static final x_282_a TITLE = new F_2904_S("gui.voicechat.enter_password.title");
    private static final x_282_a JOIN_GROUP = new F_2904_S("message.voicechat.join_group");
    private static final x_282_a ENTER_GROUP_PASSWORD = new F_2904_S("message.voicechat.enter_group_password");
    private static final x_282_a PASSWORD = new F_2904_S("message.voicechat.password");
    private O_694_j password;
    private Button joinGroup;
    private ClientGroup group;

    public EnterPasswordScreen(ClientGroup group) {
        super(TITLE, 195, 74);
        this.group = group;
    }

    @Override
    protected void init() {
        super.init();
        this.hoverAreas.clear();
        this.children.clear();
        this.buttons.clear();
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.password = new O_694_j(this.font, this.guiLeft + 7, this.guiTop + 7 + (this.font.n_1700_B + 5) * 2 - 5 + 1, this.xSize - 14, 12, new U_2871_b(""));
        this.password.setMaxStringLength(32);
        this.password.setValidator(s -> s.isEmpty() || Voicechat.GROUP_REGEX.matcher((CharSequence)s).matches());
        this.addButton(this.password);
        this.joinGroup = new Button(this.guiLeft + 7, this.guiTop + this.ySize - 20 - 7, this.xSize - 14, 20, JOIN_GROUP, button -> this.joinGroup());
        this.addButton(this.joinGroup);
    }

    private void joinGroup() {
        if (!this.password.getText().isEmpty()) {
            ClientServerNetManager.sendToServer(new JoinGroupPacket(this.group.getId(), this.password.getText()));
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.password.tick();
        this.joinGroup.active = !this.password.getText().isEmpty();
    }

    @Override
    public void onClose() {
        super.onClose();
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.minecraft.G_624_v().n_1700_B(TEXTURE);
        this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.font.J_1907_R(poseStack, ENTER_GROUP_PASSWORD, (float)(this.guiLeft + this.xSize / 2 - this.font.n_1700_B((FormattedText)ENTER_GROUP_PASSWORD) / 2), (float)(this.guiTop + 7), 0x404040);
        this.font.J_1907_R(poseStack, PASSWORD, (float)(this.guiLeft + 8), (float)(this.guiTop + 7 + this.font.n_1700_B + 5), 0x404040);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B((k_2603_m)null);
            return true;
        }
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == 257) {
            this.joinGroup();
            return true;
        }
        return false;
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String passwordText = this.password.getText();
        this.init(client, width, height);
        this.password.setText(passwordText);
    }
}



