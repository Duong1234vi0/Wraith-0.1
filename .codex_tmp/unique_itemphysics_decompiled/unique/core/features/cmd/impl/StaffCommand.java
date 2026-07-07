package unique.core.features.cmd.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import org.jetbrains.annotations.NotNull;
import unique.core.features.cmd.Command;

public class StaffCommand extends Command {
   public static List<String> staffNames = new ArrayList<>();

   public StaffCommand() {
      super("staff");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("reset").executes(context -> {
         staffNames.clear();
         sendMessage("staff list got reset.");
         return 1;
      }));
      builder.then(literal("add").then(arg("name", StringArgumentType.word()).executes(context -> {
         String name = (String)context.getArgument("name", String.class);
         staffNames.add(name);
         sendMessage(class_124.field_1060 + name + " added to staff list");
         return 1;
      })));
      builder.then(literal("del").then(arg("name", StringArgumentType.word()).executes(context -> {
         String name = (String)context.getArgument("name", String.class);
         staffNames.remove(name);
         sendMessage(class_124.field_1060 + name + " removed from staff list");
         return 1;
      })));
      builder.executes(context -> {
         if (staffNames.isEmpty()) {
            sendMessage("Staff list empty");
         } else {
            StringBuilder f = new StringBuilder("Staff: ");

            for (String staff : staffNames) {
               try {
                  f.append(staff).append(", ");
               } catch (Exception var5) {
               }
            }

            sendMessage(f.toString());
         }

         return 1;
      });
   }
}
