/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.group;

import lightning.product.C_2701_A;
import lightning.product.FormattedText;
import lightning.product.M_4239_y;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import mods.voicechat.gui.GameProfileUtils;
import mods.voicechat.gui.volume.AdjustVolumeSlider;
import mods.voicechat.gui.volume.PlayerVolumeEntry;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.gui.widgets.ListScreenEntryBase;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.common.PlayerState;

public class GroupEntry
extends ListScreenEntryBase<GroupEntry> {
    protected static final g_2336_b TALK_OUTLINE = new g_2336_b("voicechat/textures/icons/talk_outline.png");
    protected static final g_2336_b SPEAKER_OFF = new g_2336_b("voicechat/textures/icons/speaker_small_off.png");
    protected static final int PADDING = 4;
    protected static final int BG_FILL = M_4239_y.n_1700_B.n_1700_B(255, 74, 74, 74);
    protected static final int PLAYER_NAME_COLOR = M_4239_y.n_1700_B.n_1700_B(255, 255, 255, 255);
    protected final ListScreenBase parent;
    protected final MinecraftClient minecraft;
    protected PlayerState state;
    protected final AdjustVolumeSlider volumeSlider;

    public GroupEntry(ListScreenBase parent, PlayerState state) {
        this.parent = parent;
        this.minecraft = MinecraftClient.A_4115_X();
        this.state = state;
        this.volumeSlider = new AdjustVolumeSlider(0, 0, 100, 20, new PlayerVolumeEntry.PlayerVolumeConfigEntry(state.getUuid()));
        this.children.add(this.volumeSlider);
    }

    @Override
    public void render(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
        ClientVoicechat client;
        C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL);
        poseStack.n_1700_B();
        int outlineSize = height - 8;
        poseStack.n_1700_B((double)(left + 4), (double)(top + 4), 0.0);
        float scale = (float)outlineSize / 10.0f;
        poseStack.n_1700_B(scale, scale, scale);
        if (!this.state.isDisabled() && (client = ClientManager.getClient()) != null && client.getTalkCache().isTalking(this.state.getUuid())) {
            this.minecraft.G_624_v().n_1700_B(TALK_OUTLINE);
            k_2603_m.blit(poseStack, 0, 0, 0.0f, 0.0f, 10, 10, 16, 16);
        }
        this.minecraft.G_624_v().n_1700_B(GameProfileUtils.getSkin(this.state.getUuid()));
        C_2701_A.blit(poseStack, 1, 1, 8, 8, 8.0f, 8.0f, 8, 8, 64, 64);
        c_4037_x.Y_601_j();
        C_2701_A.blit(poseStack, 1, 1, 8, 8, 40.0f, 8.0f, 8, 8, 64, 64);
        c_4037_x.Y_259_p();
        if (this.state.isDisabled()) {
            poseStack.n_1700_B();
            poseStack.n_1700_B(1.0, 1.0, 0.0);
            poseStack.n_1700_B(0.5f, 0.5f, 1.0f);
            this.minecraft.G_624_v().n_1700_B(SPEAKER_OFF);
            k_2603_m.blit(poseStack, 0, 0, 0.0f, 0.0f, 16, 16, 16, 16);
            poseStack.J_1907_R();
        }
        poseStack.J_1907_R();
        U_2871_b name = new U_2871_b(this.state.getName());
        this.minecraft.t_148_a.J_1907_R(poseStack, name, (float)(left + 4 + outlineSize + 4), (float)(top + height / 2 - this.minecraft.t_148_a.n_1700_B / 2), PLAYER_NAME_COLOR);
        if (hovered && !ClientManager.getPlayerStateManager().getOwnID().equals(this.state.getUuid())) {
            this.volumeSlider.setWidth(Math.min(width - (4 + outlineSize + 4 + this.minecraft.t_148_a.n_1700_B((FormattedText)name) + 4 + 4), 100));
            this.volumeSlider.x = left + (width - this.volumeSlider.getWidth() - 4);
            this.volumeSlider.y = top + (height - this.volumeSlider.getHeightRealms()) / 2;
            this.volumeSlider.render(poseStack, mouseX, mouseY, delta);
        }
    }

    public PlayerState getState() {
        return this.state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}



