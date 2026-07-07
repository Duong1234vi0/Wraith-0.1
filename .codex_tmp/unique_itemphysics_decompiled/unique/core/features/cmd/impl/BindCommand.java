package unique.core.features.cmd.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_3675;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.ModuleArgumentType;
import unique.core.features.hud.impl.KeyBinds;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.impl.Bind;

public class BindCommand extends Command {
   public BindCommand() {
      super("bind");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(
         arg("module", ModuleArgumentType.create())
            .then(
               arg("key", StringArgumentType.word())
                  .executes(
                     context -> {
                        Module module = (Module)context.getArgument("module", Module.class);
                        String stringKey = (String)context.getArgument("key", String.class);
                        if (stringKey == null) {
                           sendMessage(module.getName() + " Is Bound To " + class_124.field_1080 + module.getBind().getBind());
                           return 1;
                        } else {
                           int key;
                           if (!stringKey.equalsIgnoreCase("none") && !stringKey.equalsIgnoreCase("null")) {
                              try {
                                 key = class_3675.method_15981("key.keyboard." + stringKey.toLowerCase()).method_1444();
                              } catch (NumberFormatException var5) {
                                 sendMessage(ClientSettings.isRu() ? "Такой кнопки не существует!" : "There Is No Such Button");
                                 return 1;
                              }
                           } else {
                              key = -1;
                           }

                           if (key == 0) {
                              sendMessage("Unknown key '" + stringKey + "'!");
                              return 1;
                           } else {
                              module.setBind(key, !stringKey.equals("M") && stringKey.contains("M"), false);
                              sendMessage(
                                 "Bind for "
                                    + class_124.field_1060
                                    + module.getName()
                                    + class_124.field_1068
                                    + " set to "
                                    + class_124.field_1080
                                    + stringKey.toUpperCase()
                              );
                              return 1;
                           }
                        }
                     }
                  )
            )
      );
      builder.then(
         literal("list")
            .executes(
               context -> {
                  StringBuilder binds = new StringBuilder("Binds: ");

                  for (Module feature : Managers.MODULE.modules) {
                     if (!Objects.equals(feature.getBind().getBind(), "None")) {
                        binds.append("\n- ")
                           .append(feature.getName())
                           .append(" -> ")
                           .append(KeyBinds.getShortKeyName(feature))
                           .append(feature.getBind().isHold() ? "[hold]" : "");
                     }
                  }

                  sendMessage(binds.toString());
                  return 1;
               }
            )
      );
      builder.then(literal("clear").executes(context -> {
         for (Module mod : Managers.MODULE.modules) {
            mod.setBind(new Bind(-1, false, false));
         }

         return 1;
      }));
      builder.then(literal("reset").executes(context -> {
         for (Module mod : Managers.MODULE.modules) {
            mod.setBind(new Bind(-1, false, false));
         }

         sendMessage("Done!");
         return 1;
      }));
   }
}
