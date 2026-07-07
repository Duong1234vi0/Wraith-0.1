package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2172;
import unique.core.api.IAddon;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;

public class AddonsCommand extends Command {
   public AddonsCommand() {
      super("addons");
   }

   @Override
   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
      builder.executes(context -> {
         List<IAddon> sortedAddons = Managers.ADDON.getAddons().stream().filter(Objects::nonNull).sorted(Comparator.comparing(IAddon::getName)).toList();
         if (sortedAddons.isEmpty()) {
            sendMessage("No Addons Installed.");
            return 1;
         } else {
            sortedAddons.forEach(iAddon -> sendMessage(iAddon.getName() + " By " + iAddon.getAuthor()));
            return 1;
         }
      });
   }
}
