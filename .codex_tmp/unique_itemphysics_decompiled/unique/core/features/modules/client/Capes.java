package unique.core.features.modules.client;

import net.minecraft.class_2960;
import net.minecraft.class_742;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public final class Capes extends Module {
   public final Setting<Capes.CapeType> capeType = new Setting<>("Cape", Capes.CapeType.OFF);
   public final Setting<Boolean> elytraTexture = new Setting<>("ElytraTexture", true);
   public final Setting<Boolean> onlySelf = new Setting<>("OnlySelf", true);
   public final Setting<Boolean> friends = new Setting<>("Friends", true, v -> this.onlySelf.getValue());

   public Capes() {
      super("Capes", Module.Category.RENDER);
   }

   public class_2960 getCapeTexture(class_742 player) {
      if (this.isEnabled() && this.capeType.getValue() != Capes.CapeType.OFF) {
         return !this.onlySelf.getValue() || player == mc.field_1724 || this.friends.getValue() && Managers.FRIEND.isFriend(player)
            ? class_2960.method_60655("unique", "textures/capes/" + this.capeType.getValue().getFileName())
            : null;
      } else {
         return null;
      }
   }

   public static enum CapeType {
      Star("starcape.png"),
      aMamaki("amamaki.png"),
      Dreamcore("dreamcore.png"),
      OFF("none");

      private final String fileName;

      private CapeType(String fileName) {
         this.fileName = fileName;
      }

      public String getFileName() {
         return this.fileName;
      }
   }
}
