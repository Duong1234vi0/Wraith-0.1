package dev.client.modules.impl.combat;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AutoArmor extends Module implements ITickable, IUtil {
   public AutoArmor() {
      super(new ModuleInfo("AutoArmor", Category.COMBAT, "NoDesc"));
   }

   public void onTick(TickEvent event) {
   }
}
