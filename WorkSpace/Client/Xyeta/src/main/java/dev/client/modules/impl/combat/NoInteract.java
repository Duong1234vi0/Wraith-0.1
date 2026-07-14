package dev.client.modules.impl.combat;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NoInteract extends Module {
   public NoInteract() {
      super(new ModuleInfo("NoInteract", Category.COMBAT, "Отключает взаимодействие с интерактивными блоками"));
   }
}
