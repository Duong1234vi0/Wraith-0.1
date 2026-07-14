/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package mods.voicechat.net;

import io.netty.buffer.Unpooled;
import lightning.product.B_4088_l;
import lightning.product.b_2585_i;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.s_4922_C;
import mods.voicechat.Voicechat;
import mods.voicechat.net.AddCategoryPacket;
import mods.voicechat.net.AddGroupPacket;
import mods.voicechat.net.Channel;
import mods.voicechat.net.CreateGroupPacket;
import mods.voicechat.net.JoinGroupPacket;
import mods.voicechat.net.JoinedGroupPacket;
import mods.voicechat.net.LeaveGroupPacket;
import mods.voicechat.net.Packet;
import mods.voicechat.net.PlayerStatePacket;
import mods.voicechat.net.PlayerStatesPacket;
import mods.voicechat.net.RemoveCategoryPacket;
import mods.voicechat.net.RemoveGroupPacket;
import mods.voicechat.net.RemoveStatePacket;
import mods.voicechat.net.RequestSecretPacket;
import mods.voicechat.net.SecretPacket;
import mods.voicechat.net.UpdateStatePacket;
import net.minecraft.server.G_564_y;

public abstract class NetManager {
    public Channel<UpdateStatePacket> updateStateChannel;
    public Channel<PlayerStatePacket> playerStateChannel;
    public Channel<PlayerStatesPacket> playerStatesChannel;
    public Channel<SecretPacket> secretChannel;
    public Channel<RequestSecretPacket> requestSecretChannel;
    public Channel<AddGroupPacket> addGroupChannel;
    public Channel<RemoveGroupPacket> removeGroupChannel;
    public Channel<JoinGroupPacket> joinGroupChannel;
    public Channel<CreateGroupPacket> createGroupChannel;
    public Channel<LeaveGroupPacket> leaveGroupChannel;
    public Channel<JoinedGroupPacket> joinedGroupChannel;
    public Channel<AddCategoryPacket> addCategoryChannel;
    public Channel<RemoveCategoryPacket> removeCategoryChannel;
    public Channel<RemoveStatePacket> removeStateChannel;

    public void init() {
        this.updateStateChannel = this.registerReceiver(UpdateStatePacket.class, false, true);
        this.playerStateChannel = this.registerReceiver(PlayerStatePacket.class, true, false);
        this.playerStatesChannel = this.registerReceiver(PlayerStatesPacket.class, true, false);
        this.secretChannel = this.registerReceiver(SecretPacket.class, true, false);
        this.requestSecretChannel = this.registerReceiver(RequestSecretPacket.class, false, true);
        this.addGroupChannel = this.registerReceiver(AddGroupPacket.class, true, false);
        this.removeGroupChannel = this.registerReceiver(RemoveGroupPacket.class, true, false);
        this.joinGroupChannel = this.registerReceiver(JoinGroupPacket.class, false, true);
        this.createGroupChannel = this.registerReceiver(CreateGroupPacket.class, false, true);
        this.leaveGroupChannel = this.registerReceiver(LeaveGroupPacket.class, false, true);
        this.joinedGroupChannel = this.registerReceiver(JoinedGroupPacket.class, true, false);
        this.addCategoryChannel = this.registerReceiver(AddCategoryPacket.class, true, false);
        this.removeCategoryChannel = this.registerReceiver(RemoveCategoryPacket.class, true, false);
        this.removeStateChannel = this.registerReceiver(RemoveStatePacket.class, true, false);
    }

    public abstract <T extends Packet<T>> Channel<T> registerReceiver(Class<T> var1, boolean var2, boolean var3);

    public static void sendToClient(B_4088_l player, Packet<?> packet) {
        if (!Voicechat.SERVER.isCompatible(player)) {
            return;
        }
        b_2585_i buffer = new b_2585_i(Unpooled.buffer());
        packet.toBytes(buffer);
        player.n_1700_B.n_1700_B(new ClientboundCustomPayloadPacket(packet.getIdentifier(), buffer));
    }

    public static interface ServerReceiver<T extends Packet<T>> {
        public void onPacket(G_564_y var1, B_4088_l var2, s_4922_C var3, T var4);
    }
}


