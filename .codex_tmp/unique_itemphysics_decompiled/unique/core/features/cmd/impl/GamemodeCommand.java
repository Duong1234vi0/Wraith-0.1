package unique.core.features.cmd.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_1934;
import net.minecraft.class_2172;
import unique.core.features.cmd.Command;

public class GamemodeCommand extends Command {
   public GamemodeCommand() {
      super("gamemode", "gm");
   }

   @Override
   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(arg("mode", StringArgumentType.greedyString()).executes(context -> {
         String mode = (String)context.getArgument("mode", String.class);
         switch (mode) {
            case "survival":
            case "0":
               mc.field_1761.method_2907(class_1934.field_9215);
            case "creative":
            case "1":
               mc.field_1761.method_2907(class_1934.field_9220);
            case "spectator":
            case "2":
               mc.field_1761.method_2907(class_1934.field_9219);
            case "adventure":
            case "3":
               mc.field_1761.method_2907(class_1934.field_9216);
            default:
               return 1;
         }
      }));
   }
}
