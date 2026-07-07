package unique.core.api;

import com.mojang.logging.LogUtils;
import java.util.List;
import unique.core.features.cmd.Command;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.Module;

public interface IAddon {
   void onInitialize();

   List<Module> getModules();

   List<Command> getCommands();

   List<HudElement> getHudElements();

   String getPackage();

   String getName();

   String getAuthor();

   String getRepo();

   String getVersion();

   default String getDescription() {
      return "";
   }

   default void onShutdown() {
      LogUtils.getLogger().info("Shutting down addon: " + this.getName());
   }
}
