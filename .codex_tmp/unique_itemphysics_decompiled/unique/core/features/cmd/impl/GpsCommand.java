package unique.core.features.cmd.impl;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import net.minecraft.class_2338;
import org.jetbrains.annotations.NotNull;
import unique.core.UniqueCore;
import unique.core.features.cmd.Command;

public class GpsCommand extends Command {
   public GpsCommand() {
      super("gps");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("off").executes(context -> {
         UniqueCore.gps_position = null;
         return 1;
      }));
      builder.then(arg("x", IntegerArgumentType.integer()).then(arg("z", IntegerArgumentType.integer()).executes(context -> {
         int x = (Integer)context.getArgument("x", Integer.class);
         int z = (Integer)context.getArgument("z", Integer.class);
         UniqueCore.gps_position = new class_2338(x, 0, z);
         sendMessage("GPS настроен на X: " + UniqueCore.gps_position.method_10263() + " Z: " + UniqueCore.gps_position.method_10260());
         return 1;
      })));
      builder.executes(context -> {
         sendMessage("Попробуй .gps off / .gps x z");
         return 1;
      });
   }
}
