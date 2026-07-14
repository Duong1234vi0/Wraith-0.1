package dev.client.modules.impl.combat;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.mixins.other.IMinecraftClientMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TapeMouse extends Module implements ITickable, IUtil {
   public TapeMouse() {
      super(new ModuleInfo("TapeMouse", Category.COMBAT, "Бьет с учетом кулдауна оружия"));
   }

   public void onTick(TickEvent event) {
      if (mc.player.getAttackCooldownProgress(2.0F) == 1.0F) {
         ((IMinecraftClientMixin)mc).mouseClick();
      }

   }
}
