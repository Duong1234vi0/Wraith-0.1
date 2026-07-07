package unique.core.features.cmd.impl;

import baritone.api.BaritoneAPI;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import unique.core.UniqueCore;
import unique.core.core.manager.world.WayPointManager;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.WayPointArgumentType;
import unique.core.features.modules.client.ClientSettings;

public class GotoWaypointCommand extends Command {
   public GotoWaypointCommand() {
      super("goto");
   }

   @Override
   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
      builder.then(
         arg("name", WayPointArgumentType.create())
            .executes(
               context -> {
                  if (!UniqueCore.baritone) {
                     sendMessage(
                        ClientSettings.isRu()
                           ? "Баритон не найден (можешь скачать на https://meteorclient.com)"
                           : "Baritone not found (you can download it at https://meteorclient.com)"
                     );
                     return 1;
                  } else {
                     WayPointManager.WayPoint wp = (WayPointManager.WayPoint)context.getArgument("name", WayPointManager.WayPoint.class);
                     if (!mc.field_1687.method_27983().method_29177().method_12832().equals(wp.getDimension())) {
                        sendMessage(ClientSettings.isRu() ? "Метка в другом измерении" : "Waypoint is in another dimension");
                        return 1;
                     } else {
                        BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("goto " + wp.getX() + " " + wp.getY() + " " + wp.getZ());
                        return 1;
                     }
                  }
               }
            )
      );
   }
}
