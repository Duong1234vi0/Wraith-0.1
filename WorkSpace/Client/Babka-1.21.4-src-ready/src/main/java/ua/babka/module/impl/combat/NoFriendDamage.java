package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventAttack;
import ua.babka.friend.Friend;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="No Friend Damage", moduleDesc="Не даёт бить друзей", moduleCategory=ModuleCategory.COMBAT)
public class NoFriendDamage
extends Module {
    @Subscribe
    private void onAttack(EventAttack e2) {
        for (Friend friend : FriendRepository.getFriends()) {
            if (e2.getEntity() == this.mc.player || !e2.getEntity().getNameForScoreboard().equals(friend.name())) continue;
            e2.cancelEvent();
        }
    }
}

