package unique.core.core.hooks;

import unique.core.core.manager.client.ModuleManager;

public class ModuleShutdownHook extends Thread {
   @Override
   public void run() {
      if (ModuleManager.unHook.isEnabled()) {
         ModuleManager.unHook.disable();
      }
   }
}
