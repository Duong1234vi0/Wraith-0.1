package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1536;
import net.minecraft.class_2663;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class NoPush extends Module {
   public Setting<Boolean> blocks = new Setting<>("Blocks", true);
   public Setting<Boolean> players = new Setting<>("Players", true);
   public Setting<Boolean> water = new Setting<>("Liquids", true);
   public Setting<Boolean> fishingHook = new Setting<>("FishingHook", true);

   public NoPush() {
      super("NoPush", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2663 pac
         && pac.method_11470() == 31
         && pac.method_11469(mc.field_1687) instanceof class_1536 hook
         && this.fishingHook.getValue()
         && hook.method_26957() == mc.field_1724) {
         e.cancel();
      }
   }
}
