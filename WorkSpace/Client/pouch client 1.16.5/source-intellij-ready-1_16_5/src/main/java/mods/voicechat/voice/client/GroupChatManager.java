/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import mods.voicechat.VoicechatClient;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.GroupPlayerIconOrientation;
import mods.voicechat.voice.common.PlayerState;

public class GroupChatManager {
    private static final g_2336_b TALK_OUTLINE = new g_2336_b("voicechat/textures/icons/talk_outline.png");
    private static final g_2336_b SPEAKER_OFF_ICON = new g_2336_b("voicechat/textures/icons/speaker_small_off.png");

    public static void renderIcons(g_221_o matrixStack) {
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return;
        }
        MinecraftClient mc = MinecraftClient.A_4115_X();
        List<PlayerState> groupMembers = GroupChatManager.getGroupMembers((Boolean)VoicechatClient.CLIENT_CONFIG.showOwnGroupIcon.get());
        matrixStack.n_1700_B();
        int posX = (Integer)VoicechatClient.CLIENT_CONFIG.groupPlayerIconPosX.get();
        int posY = (Integer)VoicechatClient.CLIENT_CONFIG.groupPlayerIconPosY.get();
        if (posX < 0) {
            matrixStack.n_1700_B((double)mc.RealmsServerPing().Q_4569_t(), 0.0, 0.0);
        }
        if (posY < 0) {
            matrixStack.n_1700_B(0.0, (double)mc.RealmsServerPing().M_182_A(), 0.0);
        }
        matrixStack.n_1700_B((double)posX, (double)posY, 0.0);
        float scale = ((Double)VoicechatClient.CLIENT_CONFIG.groupHudIconScale.get()).floatValue();
        matrixStack.n_1700_B(scale, scale, 1.0f);
        boolean vertical = ((GroupPlayerIconOrientation)((Object)VoicechatClient.CLIENT_CONFIG.groupPlayerIconOrientation.get())).equals((Object)GroupPlayerIconOrientation.VERTICAL);
        for (int i = 0; i < groupMembers.size(); ++i) {
            PlayerState state = groupMembers.get(i);
            matrixStack.n_1700_B();
            if (vertical) {
                if (posY < 0) {
                    matrixStack.n_1700_B(0.0, (double)i * -11.0, 0.0);
                } else {
                    matrixStack.n_1700_B(0.0, (double)i * 11.0, 0.0);
                }
            } else if (posX < 0) {
                matrixStack.n_1700_B((double)i * -11.0, 0.0, 0.0);
            } else {
                matrixStack.n_1700_B((double)i * 11.0, 0.0, 0.0);
            }
            matrixStack.J_1907_R();
        }
        matrixStack.J_1907_R();
    }

    public static List<PlayerState> getGroupMembers() {
        return GroupChatManager.getGroupMembers(true);
    }

    public static List<PlayerState> getGroupMembers(boolean includeSelf) {
        ArrayList<PlayerState> entries = new ArrayList<PlayerState>();
        UUID group = ClientManager.getPlayerStateManager().getGroupID();
        if (group == null) {
            return entries;
        }
        for (PlayerState state : ClientManager.getPlayerStateManager().getPlayerStates(includeSelf)) {
            if (!state.hasGroup() || !state.getGroup().equals(group)) continue;
            entries.add(state);
        }
        entries.sort(Comparator.comparing(PlayerState::getName));
        return entries;
    }
}



