package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import org.jetbrains.annotations.NotNull;
import unique.core.features.cmd.Command;

public class TrackerCommand extends Command {
   public TrackerCommand() {
      super("tracker");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.executes(context -> {
         sendMessage("Tracker Module Has Been Removed.");
         return 1;
      });
   }
}
