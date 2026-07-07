package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;
import unique.core.features.modules.Module;
import unique.core.setting.impl.Bind;

public class ResetBindsCommand extends Command {
   public ResetBindsCommand() {
      super("resetbinds", "unbind", "fuckbinds");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.executes(context -> {
         for (Module mod : Managers.MODULE.modules) {
            mod.setBind(new Bind(-1, false, false));
         }

         return 1;
      });
   }
}
