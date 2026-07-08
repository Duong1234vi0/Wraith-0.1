/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class NoFriendDamage
extends Module {
    public NoFriendDamage() {
        super("NoFriendDamage", "NoFriendDamage", ModuleCategory.COMBAT);
    }

    @EventHandler
    public void onAttack(AttackEvent e) {
        e.setCancelled(FriendUtils.isFriend(e.getEntity()));
    }
}
