/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import java.util.Arrays;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

public class AutoTpAccept
extends Module {
    private final String[] teleportMessages = new String[]{"has requested teleport", "\u043f\u0440\u043e\u0441\u0438\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", "\u0445\u043e\u0447\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f \u043a \u0432\u0430\u043c", "\u043f\u0440\u043e\u0441\u0438\u0442 \u043a \u0432\u0430\u043c \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f"};
    private boolean canAccept;
    private final BooleanSetting friendSetting = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0434\u0440\u0443\u0437\u044c\u044f", "\u0411\u0443\u0434\u0435\u0442 \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u044c \u0437\u0430\u043f\u0440\u043e\u0441\u044b \u0442\u043e\u043b\u044c\u043a\u043e \u043e\u0442 \u0434\u0440\u0443\u0437\u0435\u0439").setValue(false);

    public AutoTpAccept() {
        super("AutoTpAccept", "AutoTpAccept", ModuleCategory.MISC);
        this.setup(this.friendSetting);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_7439) {
            boolean validPlayer;
            class_7439 m = (class_7439)class_25962;
            String message = m.comp_763().getString();
            boolean bl = validPlayer = !this.friendSetting.isValue() || FriendUtils.getFriends().stream().anyMatch(s -> message.contains(s.getName()));
            if (this.isTeleportMessage(message)) {
                this.canAccept = validPlayer;
            }
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (!Network.isPvp() && this.canAccept) {
            AutoTpAccept.mc.field_1724.field_3944.method_45730("tpaccept");
            this.canAccept = false;
        }
    }

    private boolean isTeleportMessage(String message) {
        return Arrays.stream(this.teleportMessages).map(String::toLowerCase).anyMatch(message::contains);
    }
}
