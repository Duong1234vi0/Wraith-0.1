package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.apache.commons.lang3.mutable.MutableObject;
import ua.babka.event.EventPacket;
import ua.babka.friend.Friend;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.util.network.NetworkUtils;

@ModuleInformation(moduleName="Auto Tpaccept", moduleDesc="Автопринятие телепортации", moduleCategory=ModuleCategory.PLAYER)
public class AutoTpaccept
extends Module {
    public ActionResult interactBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (!this.mc.world.getWorldBorder().contains(hitResult.getBlockPos())) {
            return ActionResult.FAIL;
        }
        MutableObject mutableObject = new MutableObject();
        mutableObject.setValue((Object)this.mc.interactionManager.interactBlockInternal(player, hand, hitResult));
        NetworkUtils.sendSilentPacket(new PlayerInteractBlockC2SPacket(hand, hitResult, 0));
        return (ActionResult)mutableObject.getValue();
    }

    @Subscribe
    public void onPacket(EventPacket e2) {
        GameMessageS2CPacket p2;
        String raw;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        Packet<?> packet = e2.getPacket();
        if (packet instanceof GameMessageS2CPacket && ((raw = (p2 = (GameMessageS2CPacket)packet).comp_763().getString()).contains("телепортироваться") || raw.contains("has requested teleport") || raw.contains("просит к вам телепортироваться") || raw.contains("запрашивает телепорт к вам"))) {
            boolean yes = false;
            for (Friend friend : FriendRepository.getFriends()) {
                if (!raw.contains(friend.name())) continue;
                yes = true;
                break;
            }
            if (!yes) {
                return;
            }
            this.mc.getNetworkHandler().sendChatCommand("tpaccept");
        }
    }
}

