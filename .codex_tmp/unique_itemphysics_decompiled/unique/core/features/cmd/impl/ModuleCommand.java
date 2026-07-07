package unique.core.features.cmd.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.Objects;
import net.minecraft.class_1074;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.ModuleArgumentType;
import unique.core.features.cmd.args.SettingArgumentType;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.EnumConverter;
import unique.core.setting.impl.PositionSetting;

public class ModuleCommand extends Command {
   public ModuleCommand() {
      super("module", "modules");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(arg("module", ModuleArgumentType.create()).executes(context -> {
         Module module = (Module)context.getArgument("module", Module.class);
         sendMessage(module.getDisplayName() + " : " + class_1074.method_4662(module.getDescription(), new Object[0]));

         for (Setting<?> setting2 : module.getSettings()) {
            sendMessage(setting2.getName() + " : " + setting2.getValue());
         }

         return 1;
      }));
      builder.then(
         ((RequiredArgumentBuilder)arg("module", ModuleArgumentType.create()).then(literal("reset").executes(context -> {
               Module module = (Module)context.getArgument("module", Module.class);

               for (Setting s : module.getSettings()) {
                  if (s.getValue() instanceof ColorSetting cs) {
                     cs.setDefault();
                  } else {
                     s.setValue(s.getDefaultValue());
                  }
               }

               return 1;
            })))
            .then(
               arg("setting", SettingArgumentType.create())
                  .then(
                     arg("settingValue", StringArgumentType.greedyString())
                        .executes(
                           context -> {
                              Module module = (Module)context.getArgument("module", Module.class);
                              String settingName = (String)context.getArgument("setting", String.class);
                              String settingValue = (String)context.getArgument("settingValue", String.class);
                              Setting setting = null;

                              for (Setting set : module.getSettings()) {
                                 if (Objects.equals(set.getName(), settingName)) {
                                    setting = set;
                                 }
                              }

                              if (setting == null) {
                                 sendMessage("No such setting");
                                 return 1;
                              } else {
                                 JsonParser jp = new JsonParser();
                                 if (setting.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
                                    setting.setValue(settingValue);
                                    sendMessage(
                                       class_124.field_1063
                                          + module.getName()
                                          + " "
                                          + setting.getName()
                                          + (ClientSettings.isRu() ? " был выставлен " : " has been set to ")
                                          + settingValue
                                    );
                                    return 1;
                                 } else {
                                    try {
                                       if (setting.getName().equalsIgnoreCase("Enabled")) {
                                          if (settingValue.equalsIgnoreCase("true")) {
                                             module.enable();
                                          }

                                          if (settingValue.equalsIgnoreCase("false")) {
                                             module.disable();
                                          }
                                       }

                                       setCommandValue(module, setting, jp.parse(settingValue));
                                    } catch (Exception var7) {
                                       sendMessage(
                                          (ClientSettings.isRu() ? "Неверное значение! Эта настройка требует тип: " : "Bad Value! This setting requires a: ")
                                             + setting.getValue().getClass().getSimpleName()
                                       );
                                       return 1;
                                    }

                                    if (settingValue.contains("toggle")) {
                                       sendMessage(
                                          class_124.field_1080
                                             + module.getName()
                                             + " "
                                             + setting.getName()
                                             + (ClientSettings.isRu() ? " был переключен" : " has been toggled")
                                       );
                                    } else {
                                       sendMessage(
                                          class_124.field_1080
                                             + module.getName()
                                             + " "
                                             + setting.getName()
                                             + (ClientSettings.isRu() ? " был выставлен " : " has been set to ")
                                             + settingValue
                                       );
                                    }

                                    return 1;
                                 }
                              }
                           }
                        )
                  )
            )
      );
      builder.executes(
         context -> {
            sendMessage("Modules: ");

            for (Module.Category category : Managers.MODULE.getCategories()) {
               StringBuilder modules = new StringBuilder(category.getName() + ": ");

               for (Module module1 : Managers.MODULE.getModulesByCategory(category)) {
                  modules.append(module1.isEnabled() ? class_124.field_1060 : class_124.field_1061)
                     .append(module1.getName())
                     .append(class_124.field_1068)
                     .append(", ");
               }

               sendMessage(modules.toString());
            }

            return 1;
         }
      );
   }

   public static void setCommandValue(@NotNull Module feature, Setting setting, JsonElement element) {
      for (Setting checkSetting : feature.getSettings()) {
         if (Objects.equals(setting.getName(), checkSetting.getName())) {
            String var6 = checkSetting.getValue().getClass().getSimpleName();
            switch (var6) {
               case "SettingGroup":
               case "Bind":
                  return;
               case "Boolean":
                  if (element.getAsString().equals("toggle")) {
                     checkSetting.setValue(!(Boolean)checkSetting.getValue());
                     return;
                  }

                  checkSetting.setValue(element.getAsBoolean());
                  return;
               case "BooleanSettingGroup":
                  ((BooleanSettingGroup)checkSetting.getValue()).setEnabled(element.getAsBoolean());
                  break;
               case "Double":
                  checkSetting.setValue(element.getAsDouble());
                  return;
               case "Float":
                  checkSetting.setValue(element.getAsFloat());
                  return;
               case "Integer":
                  checkSetting.setValue(element.getAsInt());
                  return;
               case "String":
                  String str = element.getAsString();
                  checkSetting.setValue(str.replace("_", " "));
                  return;
               case "ColorSetting":
                  JsonArray array = element.getAsJsonArray();
                  ((ColorSetting)checkSetting.getValue()).setColor(array.get(0).getAsInt());
                  ((ColorSetting)checkSetting.getValue()).setRainbow(array.get(1).getAsBoolean());
                  return;
               case "PositionSetting":
                  JsonArray array3 = element.getAsJsonArray();
                  ((PositionSetting)checkSetting.getValue()).setX(array3.get(0).getAsFloat());
                  ((PositionSetting)checkSetting.getValue()).setY(array3.get(1).getAsFloat());
                  return;
               default:
                  try {
                     EnumConverter converter = new EnumConverter((Class<? extends Enum>)((Enum)checkSetting.getValue()).getClass());
                     Enum value = converter.doBackward(element);
                     checkSetting.setValue(value == null ? checkSetting.getDefaultValue() : value);
                  } catch (Exception var10) {
                  }
            }
         }
      }
   }
}
