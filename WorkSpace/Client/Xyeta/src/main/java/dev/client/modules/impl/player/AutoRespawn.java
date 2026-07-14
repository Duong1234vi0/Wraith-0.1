package dev.client.modules.impl.player;

import dev.client.event.classes.DeathScreenEvent;
import dev.client.event.interfaces.IDeathScreen;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class AutoRespawn extends Module implements IDeathScreen, IUtil {
   public AutoRespawn() {
      super(new ModuleInfo("AutoRespawn", Category.PLAYER, "Выбирает возрождение после гибели игрока"));
   }

   @Compile
   public void onDeathScreen(DeathScreenEvent event) {
      mc.player.requestRespawn();
      mc.setScreen((Screen)null);
   }
}
