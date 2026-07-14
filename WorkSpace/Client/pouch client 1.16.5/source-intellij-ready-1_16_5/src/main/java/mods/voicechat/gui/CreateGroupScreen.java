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
import mods.voicechat.gui.GroupType;
import mods.voicechat.gui.VoiceChatScreenBase;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.CreateGroupPacket;

public class CreateGroupScreen
extends VoiceChatScreenBase {
    private static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_create_group.png");
    private static final x_282_a TITLE = new F_2904_S("gui.voicechat.create_group.title");
    private static final x_282_a CREATE = new F_2904_S("message.voicechat.create");
    private static final x_282_a CREATE_GROUP = new F_2904_S("message.voicechat.create_group");
    private static final x_282_a GROUP_NAME = new F_2904_S("message.voicechat.group_name");
    private static final x_282_a OPTIONAL_PASSWORD = new F_2904_S("message.voicechat.optional_password");
    private static final x_282_a GROUP_TYPE = new F_2904_S("message.voicechat.group_type");
    private O_694_j groupName;
    private O_694_j password;
    private GroupType groupType = GroupType.NORMAL;
    private Button groupTypeButton;
    private Button createGroup;

    public CreateGroupScreen() {
        super(TITLE, 195, 124);
    }

    @Override
    protected void init() {
        super.init();
        this.hoverAreas.clear();
        this.children.clear();
        this.buttons.clear();
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.groupName = new O_694_j(this.font, this.guiLeft + 7, this.guiTop + 31, this.xSize - 14, 12, new U_2871_b(""));
        this.groupName.setMaxStringLength(24);
        this.groupName.setValidator(s -> s.isEmpty() || Voicechat.GROUP_REGEX.matcher((CharSequence)s).matches());
        this.addButton(this.groupName);
        this.password = new O_694_j(this.font, this.guiLeft + 7, this.guiTop + 57, this.xSize - 14, 12, new U_2871_b(""));
        this.password.setMaxStringLength(32);
        this.password.setValidator(s -> s.isEmpty() || Voicechat.GROUP_REGEX.matcher((CharSequence)s).matches());
        this.addButton(this.password);
        this.groupTypeButton = new Button(this.guiLeft + 6, this.guiTop + 74, this.xSize - 12, 20, GROUP_TYPE, button -> {
            this.groupType = GroupType.values()[(this.groupType.ordinal() + 1) % GroupType.values().length];
        }){

            @Override
            public x_282_a getMessage() {
                return new F_2904_S("message.voicechat.group_type").n_1700_B(": ").n_1700_B(CreateGroupScreen.this.groupType.getTranslation());
            }
        };
        this.addButton(this.groupTypeButton);
        this.createGroup = new Button(this.guiLeft + 6, this.guiTop + this.ySize - 27, this.xSize - 12, 20, CREATE, button -> this.createGroup());
        this.addButton(this.createGroup);
    }

    private void createGroup() {
        if (!this.groupName.getText().isEmpty()) {
            ClientServerNetManager.sendToServer(new CreateGroupPacket(this.groupName.getText(), this.password.getText().isEmpty() ? null : this.password.getText(), this.groupType.getType()));
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.groupName.tick();
        this.password.tick();
        this.createGroup.active = !this.groupName.getText().isEmpty();
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
        this.font.J_1907_R(poseStack, CREATE_GROUP, (float)(this.guiLeft + this.xSize / 2 - this.font.n_1700_B((FormattedText)CREATE_GROUP) / 2), (float)(this.guiTop + 7), 0x404040);
        this.font.J_1907_R(poseStack, GROUP_NAME, (float)(this.guiLeft + 8), (float)(this.guiTop + 7 + this.font.n_1700_B + 5), 0x404040);
        this.font.J_1907_R(poseStack, OPTIONAL_PASSWORD, (float)(this.guiLeft + 8), (float)(this.guiTop + 7 + (this.font.n_1700_B + 5) * 2 + 10 + 2), 0x404040);
        if (mouseX >= this.groupTypeButton.x && mouseY >= this.groupTypeButton.y && mouseX < this.groupTypeButton.x + this.groupTypeButton.getWidth() && mouseY < this.groupTypeButton.y + this.groupTypeButton.getHeightRealms()) {
            this.renderTooltip(poseStack, this.minecraft.t_148_a.J_1907_R(this.groupType.getDescription(), 200), mouseX, mouseY);
        }
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
            this.createGroup();
            return true;
        }
        return false;
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String groupNameText = this.groupName.getText();
        String passwordText = this.password.getText();
        this.init(client, width, height);
        this.groupName.setText(groupNameText);
        this.password.setText(passwordText);
    }
}



