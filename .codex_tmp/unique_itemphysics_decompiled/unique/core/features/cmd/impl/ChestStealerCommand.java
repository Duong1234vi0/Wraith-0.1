package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_2172;
import net.minecraft.class_2248;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.ChestStealerArgumentType;
import unique.core.features.modules.client.ClientSettings;

public class ChestStealerCommand extends Command {
   public ChestStealerCommand() {
      super("cheststealer", "stealer", "rat");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("reset").executes(context -> {
         ModuleManager.chestStealer.items.getValue().getItemsById().clear();
         sendMessage("ChestStealer got reset.");
         return 1;
      }));
      builder.then(literal("add").then(arg("item", ChestStealerArgumentType.create()).executes(context -> {
         String blockName = (String)context.getArgument("item", String.class);
         String result = getRegistered(blockName);
         if (result != null) {
            ModuleManager.chestStealer.items.getValue().getItemsById().add(result);
            sendMessage(class_124.field_1060 + blockName + (ClientSettings.isRu() ? " добавлен в ChestStealer" : " added to ChestStealer"));
         } else {
            sendMessage(class_124.field_1061 + (ClientSettings.isRu() ? "Такого предмета нет!" : "There is no such item!"));
         }

         return 1;
      })));
      builder.then(literal("del").then(arg("item", ChestStealerArgumentType.create()).executes(context -> {
         String blockName = (String)context.getArgument("item", String.class);
         String result = getRegistered(blockName);
         if (result != null) {
            ModuleManager.chestStealer.items.getValue().getItemsById().remove(result);
            sendMessage(class_124.field_1060 + blockName + (ClientSettings.isRu() ? " удален из ChestStealer" : " removed from ChestStealer"));
         } else {
            sendMessage(class_124.field_1061 + (ClientSettings.isRu() ? "Такого предмета нет!" : "There is no such item!"));
         }

         return 1;
      })));
      builder.executes(context -> {
         if (ModuleManager.chestStealer.items.getValue().getItemsById().isEmpty()) {
            sendMessage("ChestStealer list empty");
         } else {
            StringBuilder f = new StringBuilder("ChestStealer list: ");

            for (String name : ModuleManager.chestStealer.items.getValue().getItemsById()) {
               try {
                  f.append(name).append(", ");
               } catch (Exception var5) {
               }
            }

            sendMessage(f.toString());
         }

         return 1;
      });
   }

   public static String getRegistered(String Name) {
      for (class_2248 block : class_7923.field_41175) {
         if (block.method_9539().replace("block.minecraft.", "").equalsIgnoreCase(Name)) {
            return block.method_9539().replace("block.minecraft.", "");
         }
      }

      for (class_1792 item : class_7923.field_41178) {
         if (item.method_7876().replace("item.minecraft.", "").equalsIgnoreCase(Name)) {
            return item.method_7876().replace("item.minecraft.", "");
         }
      }

      return null;
   }
}
