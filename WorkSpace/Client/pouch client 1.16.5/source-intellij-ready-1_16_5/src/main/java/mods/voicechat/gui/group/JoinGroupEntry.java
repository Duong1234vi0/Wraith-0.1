/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package mods.voicechat.gui.group;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.M_4239_y;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.api.Group;
import mods.voicechat.gui.GameProfileUtils;
import mods.voicechat.gui.GroupType;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.gui.widgets.ListScreenEntryBase;
import mods.voicechat.voice.common.ClientGroup;
import mods.voicechat.voice.common.PlayerState;

public class JoinGroupEntry
extends ListScreenEntryBase<JoinGroupEntry> {
    protected static final g_2336_b LOCK = new g_2336_b("voicechat/textures/icons/lock.png");
    protected static final x_282_a GROUP_MEMBERS = new F_2904_S("message.voicechat.group_members").n_1700_B(D_4024_W.w_1484_f);
    protected static final x_282_a NO_GROUP_MEMBERS = new F_2904_S("message.voicechat.no_group_members").n_1700_B(D_4024_W.w_1484_f);
    protected static final int SKIN_SIZE = 12;
    protected static final int PADDING = 4;
    protected static final int BG_FILL = M_4239_y.n_1700_B.n_1700_B(255, 74, 74, 74);
    protected static final int BG_FILL_SELECTED = M_4239_y.n_1700_B.n_1700_B(255, 90, 90, 90);
    protected static final int PLAYER_NAME_COLOR = M_4239_y.n_1700_B.n_1700_B(255, 255, 255, 255);
    protected final ListScreenBase parent;
    protected final MinecraftClient minecraft;
    protected final Group group;

    public JoinGroupEntry(ListScreenBase parent, Group group) {
        this.parent = parent;
        this.minecraft = MinecraftClient.A_4115_X();
        this.group = group;
    }

    @Override
    public void render(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
        if (hovered) {
            C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL_SELECTED);
        } else {
            C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL);
        }
        boolean hasPassword = this.group.group.hasPassword();
        if (hasPassword) {
            poseStack.n_1700_B();
            poseStack.n_1700_B((double)(left + 4), (double)((float)top + (float)height / 2.0f - 8.0f), 0.0);
            poseStack.n_1700_B(1.3333334f, 1.3333334f, 1.0f);
            this.minecraft.G_624_v().n_1700_B(LOCK);
            k_2603_m.blit(poseStack, 0, 0, 0.0f, 0.0f, 12, 12, 16, 16);
            poseStack.J_1907_R();
        }
        U_2871_b groupName = new U_2871_b(this.group.group.getName());
        this.minecraft.t_148_a.J_1907_R(poseStack, groupName, (float)(left + 4 + (hasPassword ? 20 : 0)), (float)(top + height / 2 - this.minecraft.t_148_a.n_1700_B / 2), PLAYER_NAME_COLOR);
        int textWidth = this.minecraft.t_148_a.n_1700_B((FormattedText)groupName) + (hasPassword ? 20 : 0);
        int headsPerRow = (width - (4 + textWidth + 4 + 4)) / 13;
        int rows = 2;
        for (int i = 0; i < this.group.members.size(); ++i) {
            PlayerState state = this.group.members.get(i);
            int headXIndex = i / rows;
            int headYIndex = i % rows;
            if (i >= headsPerRow * rows) break;
            int headPosX = left + width - 12 - 4 - headXIndex * 13;
            int headPosY = top + height / 2 - 13 + 13 * headYIndex;
            poseStack.n_1700_B();
            this.minecraft.G_624_v().n_1700_B(GameProfileUtils.getSkin(state.getUuid()));
            poseStack.n_1700_B((double)headPosX, (double)headPosY, 0.0);
            float scale = 1.5f;
            poseStack.n_1700_B(scale, scale, scale);
            k_2603_m.blit(poseStack, 0, 0, 8.0f, 8.0f, 8, 8, 64, 64);
            c_4037_x.Y_601_j();
            k_2603_m.blit(poseStack, 0, 0, 40.0f, 8.0f, 8, 8, 64, 64);
            c_4037_x.Y_259_p();
            poseStack.J_1907_R();
        }
        if (!hovered) {
            return;
        }
        ArrayList tooltip = Lists.newArrayList();
        if (this.group.getGroup().getType().equals(Group.Type.NORMAL)) {
            tooltip.add(new F_2904_S("message.voicechat.group_title", new U_2871_b(this.group.getGroup().getName())).u_1723_Y());
        } else {
            tooltip.add(new F_2904_S("message.voicechat.group_type_title", new U_2871_b(this.group.getGroup().getName()), GroupType.fromType(this.group.getGroup().getType()).getTranslation()).u_1723_Y());
        }
        if (this.group.getMembers().isEmpty()) {
            tooltip.add(NO_GROUP_MEMBERS.u_1723_Y());
        } else {
            tooltip.add(GROUP_MEMBERS.u_1723_Y());
            int maxMembers = 10;
            for (int i = 0; i < this.group.getMembers().size(); ++i) {
                if (i >= maxMembers) {
                    tooltip.add(new F_2904_S("message.voicechat.more_members", this.group.getMembers().size() - maxMembers).n_1700_B(D_4024_W.w_1484_f).u_1723_Y());
                    break;
                }
                PlayerState state = this.group.getMembers().get(i);
                tooltip.add(new U_2871_b("  " + state.getName()).n_1700_B(D_4024_W.w_1484_f).u_1723_Y());
            }
        }
        this.parent.postRender(() -> this.parent.renderTooltip(poseStack, tooltip, mouseX, mouseY));
    }

    public Group getGroup() {
        return this.group;
    }

    public static class Group {
        private final ClientGroup group;
        private final List<PlayerState> members;

        public Group(ClientGroup group) {
            this.group = group;
            this.members = new ArrayList<PlayerState>();
        }

        public ClientGroup getGroup() {
            return this.group;
        }

        public List<PlayerState> getMembers() {
            return this.members;
        }
    }
}



