package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;

public class HelpCommand extends Command {
   public HelpCommand() {
      super("help");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.executes(
         context -> {
            sendMessage("Commands: \n");
            AtomicBoolean flip = new AtomicBoolean(false);
            Managers.COMMAND
               .getCommands()
               .forEach(
                  command -> {
                     mc.field_1724
                        .method_43496(
                           class_2561.method_30163(
                              (flip.get() ? class_124.field_1076 : class_124.field_1064)
                                 + Managers.COMMAND.getPrefix()
                                 + (flip.get() ? class_124.field_1075 : class_124.field_1062)
                                 + command.getName()
                                 + (command.getAliases().isEmpty() ? "" : " (" + command.getAliases() + ")")
                                 + class_124.field_1063
                                 + " -> "
                                 + (flip.get() ? class_124.field_1068 : class_124.field_1080)
                                 + command.getDescription()
                           )
                        );
                     flip.set(!flip.get());
                  }
               );
            return 1;
         }
      );
   }
}
