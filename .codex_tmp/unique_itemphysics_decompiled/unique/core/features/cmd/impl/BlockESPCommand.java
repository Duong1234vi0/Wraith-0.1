package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2248;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.SearchArgumentType;
import unique.core.features.modules.client.ClientSettings;

public class BlockESPCommand extends Command {
   public BlockESPCommand() {
      super("blockesp");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("reset").executes(context -> {
         ModuleManager.blockESP.selectedBlocks.getValue().clear();
         sendMessage(ClientSettings.isRu() ? "BlockESP был очищен!" : "BlockESP got reset.");
         mc.field_1769.method_3279();
         return 1;
      }));
      builder.then(literal("add").then(arg("block", SearchArgumentType.create()).executes(context -> {
         String blockName = (String)context.getArgument("block", String.class);
         class_2248 result = getRegisteredBlock(blockName);
         if (result != null) {
            ModuleManager.blockESP.selectedBlocks.getValue().add(result);
            sendMessage(class_124.field_1060 + blockName + (ClientSettings.isRu() ? " добавлен в BlockESP" : " added to BlockESP"));
         } else {
            sendMessage(class_124.field_1061 + (ClientSettings.isRu() ? "Такого блока нет!" : "There is no such block!"));
         }

         mc.field_1769.method_3279();
         return 1;
      })));
      builder.then(literal("del").then(arg("block", SearchArgumentType.create()).executes(context -> {
         String blockName = (String)context.getArgument("block", String.class);
         class_2248 result = getRegisteredBlock(blockName);
         if (result != null) {
            ModuleManager.blockESP.selectedBlocks.getValue().remove(result);
            sendMessage(class_124.field_1060 + blockName + (ClientSettings.isRu() ? " удален из BlockESP" : " removed from BlockESP"));
         } else {
            sendMessage(class_124.field_1061 + (ClientSettings.isRu() ? "Такого блока нет!" : "There is no such block!"));
         }

         mc.field_1769.method_3279();
         return 1;
      })));
      builder.executes(context -> {
         if (ModuleManager.blockESP.selectedBlocks.getValue().getItemsById().isEmpty()) {
            sendMessage("BlockESP list empty");
         } else {
            StringBuilder f = new StringBuilder("BlockESP list: ");

            for (String name : ModuleManager.blockESP.selectedBlocks.getValue().getItemsById()) {
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

   public static class_2248 getRegisteredBlock(String blockName) {
      for (class_2248 block : class_7923.field_41175) {
         if (block.method_9539().replace("block.minecraft.", "").equalsIgnoreCase(blockName.replace("block.minecraft.", ""))) {
            return block;
         }
      }

      return null;
   }
}
