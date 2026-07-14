package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import sky.core.SkyCore;
import sky.core.events.EventAttack;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class NoFriendDamage extends Module {
   public NoFriendDamage() {
      super("NoFriendDamage", "Отключает возможность наносить урон друзьям", Category.Combat);
   }

   @EventTarget
   public void onEvent(EventAttack event) {
      AttackAura attackAura = (AttackAura)SkyCore.getInstance().getModuleManager().getModule(AttackAura.class);
      Entity var4 = event.getTarget();
      if (var4 instanceof PlayerEntity player) {
         if (SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName()) && (!attackAura.isEnabled() || AttackAura.target != event.getTarget())) {
            event.setCancelled(true);
         }
      }

   }
}
