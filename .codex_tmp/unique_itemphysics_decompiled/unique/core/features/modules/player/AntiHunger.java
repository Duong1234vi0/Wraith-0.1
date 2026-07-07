package unique.core.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import org.jetbrains.annotations.NotNull;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IPlayerMoveC2SPacket;
import unique.core.setting.Setting;

public class AntiHunger extends Module {
   private final Setting<Boolean> ground = new Setting<>("CancelGround", true);
   private final Setting<Boolean> sprint = new Setting<>("CancelSprint", true);

   public AntiHunger() {
      super("AntiHunger", Module.Category.PLAYER);
   }

   @EventHandler
   public void onPacketSend(@NotNull PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2828 pac && this.ground.getValue()) {
         ((IPlayerMoveC2SPacket)pac).setOnGround(false);
      }

      if (e.getPacket() instanceof class_2848 pac && this.sprint.getValue() && pac.method_12365() == class_2849.field_12981) {
         e.cancel();
      }
   }
}
