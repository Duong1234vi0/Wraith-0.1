package unique.core.features.cmd.impl;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_3532;
import net.minecraft.class_2828.class_2829;
import org.jetbrains.annotations.NotNull;
import unique.core.features.cmd.Command;

public class HClipCommand extends Command {
   public HClipCommand() {
      super("hclip");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(
         literal("s")
            .executes(
               context -> {
                  double x = -(class_3532.method_15374(mc.field_1724.method_36454() * (float) (Math.PI / 180.0)) * 0.8);
                  double z = class_3532.method_15362(mc.field_1724.method_36454() * (float) (Math.PI / 180.0)) * 0.8;

                  for (int i = 0; i < 10; i++) {
                     mc.field_1724
                        .field_3944
                        .method_52787(new class_2829(mc.field_1724.method_23317() + x, mc.field_1724.method_23318(), mc.field_1724.method_23321() + z, false));
                  }

                  mc.field_1724.method_5814(mc.field_1724.method_23317() + x, mc.field_1724.method_23318(), mc.field_1724.method_23321() + z);
                  return 1;
               }
            )
      );
      builder.then(
         arg("count", DoubleArgumentType.doubleArg())
            .executes(
               context -> {
                  double speed = (Double)context.getArgument("count", Double.class);

                  try {
                     sendMessage(class_124.field_1060 + "клипаемся на  " + speed + " блоков.");
                     mc.field_1724
                        .method_5814(
                           mc.field_1724.method_23317() - class_3532.method_15374(mc.field_1724.method_36454() * (float) (Math.PI / 180.0)) * speed,
                           mc.field_1724.method_23318(),
                           mc.field_1724.method_23321() + class_3532.method_15362(mc.field_1724.method_36454() * (float) (Math.PI / 180.0)) * speed
                        );
                  } catch (Exception var4) {
                  }

                  return 1;
               }
            )
      );
      builder.executes(context -> {
         sendMessage("Попробуй .hclip <число>, .hclip s");
         return 1;
      });
   }
}
