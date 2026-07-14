/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.group;

import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.SimpleSoundInstance;
import lightning.product.Button;
import lightning.product.SoundEvents;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import mods.voicechat.gui.CreateGroupScreen;
import mods.voicechat.gui.EnterPasswordScreen;
import mods.voicechat.gui.group.JoinGroupEntry;
import mods.voicechat.gui.group.JoinGroupList;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.JoinGroupPacket;
import mods.voicechat.voice.common.ClientGroup;

public class JoinGroupScreen
extends ListScreenBase {
    protected static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_join_group.png");
    protected static final x_282_a TITLE = new F_2904_S("gui.voicechat.join_create_group.title");
    protected static final x_282_a CREATE_GROUP = new F_2904_S("message.voicechat.create_group_button");
    protected static final x_282_a JOIN_CREATE_GROUP = new F_2904_S("message.voicechat.join_create_group");
    protected static final x_282_a NO_GROUPS = new F_2904_S("message.voicechat.no_groups").n_1700_B(D_4024_W.w_1484_f);
    protected static final int HEADER_SIZE = 16;
    protected static final int FOOTER_SIZE = 32;
    protected static final int UNIT_SIZE = 18;
    protected static final int CELL_HEIGHT = 36;
    protected JoinGroupList groupList;
    protected Button createGroup;
    protected int units;

    public JoinGroupScreen() {
        super(TITLE, 236, 0);
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft += 2;
        this.guiTop = 32;
        int minUnits = u_530_F.u_1723_Y(2.2222223f);
        this.units = Math.max(minUnits, (this.height - 16 - 32 - this.guiTop * 2) / 18);
        this.ySize = 16 + this.units * 18 + 32;
        if (this.groupList != null) {
            this.groupList.updateSize(this.width, this.units * 18, this.guiTop + 16);
        } else {
            this.groupList = new JoinGroupList(this, this.width, this.units * 18, this.guiTop + 16, 36);
        }
        this.addListener(this.groupList);
        this.createGroup = new Button(this.guiLeft + 7, this.guiTop + this.ySize - 20 - 7, this.xSize - 14, 20, CREATE_GROUP, button -> this.minecraft.n_1700_B(new CreateGroupScreen()));
        this.addButton(this.createGroup);
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
        this.font.J_1907_R(poseStack, JOIN_CREATE_GROUP, (float)(this.guiLeft + this.xSize / 2 - this.font.n_1700_B((FormattedText)JOIN_CREATE_GROUP) / 2), (float)(this.guiTop + 5), 0x404040);
        if (!this.groupList.isEmpty()) {
            this.groupList.render(poseStack, mouseX, mouseY, delta);
        } else {
            JoinGroupScreen.drawCenteredString(poseStack, this.font, NO_GROUPS, this.width / 2, this.guiTop + 16 + this.units * 18 / 2 - this.font.n_1700_B / 2, -1);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        for (JoinGroupEntry entry : this.groupList.getEventListeners()) {
            if (!entry.isMouseOver(mouseX, mouseY)) continue;
            ClientGroup group = entry.getGroup().getGroup();
            this.minecraft.Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HayBlock, 1.0f));
            if (group.hasPassword()) {
                this.minecraft.n_1700_B(new EnterPasswordScreen(group));
            } else {
                ClientServerNetManager.sendToServer(new JoinGroupPacket(group.getId(), null));
            }
            return true;
        }
        return false;
    }
}


