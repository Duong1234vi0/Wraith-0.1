package dev.client.modules.impl.render;

import dev.client.WildClient;
import dev.client.event.classes.EntityColorEvent;
import dev.client.event.interfaces.IEntityColorable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SeeInvisible extends Module implements IEntityColorable {
   public SeeInvisible() {
      super(new ModuleInfo("SeeInvisible", Category.RENDER, "Показывает противников в невидимости"));
   }

   public void changeColor(EntityColorEvent event) {
      event.setColor(WildClient.INSTANCE.getThemeManager().getTheme().color().getRGB());
      event.cancel();
   }
}
