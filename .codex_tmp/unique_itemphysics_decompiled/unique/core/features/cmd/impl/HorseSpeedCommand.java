package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_1498;
import net.minecraft.class_2172;
import org.jetbrains.annotations.NotNull;
import unique.core.features.cmd.Command;
import unique.core.features.modules.client.ClientSettings;

public class HorseSpeedCommand extends Command {
   public HorseSpeedCommand() {
      super("gethorsespeed", "horsespeed");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.executes(context -> {
         if (mc.field_1724.method_5854() != null && mc.field_1724.method_5854() instanceof class_1498 horse) {
            if (!horse.method_6725()) {
               if (ClientSettings.isRu()) {
                  sendMessage(class_124.field_1061 + "У тебя нет седла!");
               } else {
                  sendMessage(class_124.field_1061 + "You don't have a saddle!");
               }

               return 1;
            }

            float speed = horse.field_6250 * 43.17F;
            float ratio = speed / 14.512F;
            String verbose = "";
            if (ratio < 0.3) {
               verbose = ClientSettings.isRu() ? "У тебя кринжовая лошадь :(" : "Your horse is shitty :(";
            }

            if (ratio > 0.3 && ratio < 0.6) {
               verbose = ClientSettings.isRu() ? "У тебя нормальная лошадь" : "Your horse is normal";
            }

            if (ratio > 0.6) {
               verbose = ClientSettings.isRu() ? "У тебя пиздатая лошадь :)" : "Your horse is good :)";
            }

            if (ratio > 0.9) {
               verbose = ClientSettings.isRu() ? "Цыганы уже в пути" : "Your horse is very good :)";
            }

            if (ClientSettings.isRu()) {
               sendMessage(class_124.field_1060 + "Скорость лошади: " + speed + " из 14.512. " + verbose);
            } else {
               sendMessage(class_124.field_1060 + "Horse speed: " + speed + " out of 14.512. " + verbose);
            }
         } else if (ClientSettings.isRu()) {
            sendMessage(class_124.field_1061 + "У тебя нет лошади!");
         } else {
            sendMessage(class_124.field_1061 + "You don't have a horse!");
         }

         return 1;
      });
   }
}
