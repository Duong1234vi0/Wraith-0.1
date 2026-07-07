package unique.core.features.modules.misc;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1590;
import net.minecraft.class_1646;
import net.minecraft.class_2824;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Criticals;
import unique.core.setting.Setting;

public final class AntiAttack extends Module {
   private final Setting<Boolean> friend = new Setting<>("Friend", true);
   private final Setting<Boolean> zoglin = new Setting<>("Zoglin", true);
   private final Setting<Boolean> villager = new Setting<>("Villager", false);
   private final Setting<Boolean> oneHp = new Setting<>("OneHp", false);
   private final Setting<Float> hp = new Setting<>("Hp", 1.0F, 0.0F, 20.0F, v -> this.oneHp.getValue());

   public AntiAttack() {
      super("AntiAttack", Module.Category.MISC);
   }

   @EventHandler
   private void onPacketSend(@NotNull PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2824 pac) {
         class_1297 entity = Criticals.getEntity(pac);
         if (entity == null) {
            return;
         }

         if (Managers.FRIEND.isFriend(entity.method_5477().getString()) && this.friend.getValue()) {
            e.cancel();
         }

         if (entity instanceof class_1590 && this.zoglin.getValue()) {
            e.cancel();
         }

         if (entity instanceof class_1646 && this.villager.getValue()) {
            e.cancel();
         } else if (this.oneHp.getValue() && entity instanceof class_1309 lent && lent.method_6032() <= this.hp.getValue()) {
            e.cancel();
         }
      }
   }
}
