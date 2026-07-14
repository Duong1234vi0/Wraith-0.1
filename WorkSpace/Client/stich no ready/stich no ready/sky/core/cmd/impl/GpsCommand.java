package sky.core.cmd.impl;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.cmd.Command;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;

public class GpsCommand extends Command implements ArgumentType, Wrapper {
   public static boolean gpsEnabled = false;
   public static double x = 0.0;
   public static double z = 0.0;

   public GpsCommand() {
      super("gps");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(((RequiredArgumentBuilder)args("x", new DoubleArgumentType()).executes((context) -> {
         String input = context.getInput();
         return this.invalidArgs(context);
      })).then(args("z", new DoubleArgumentType()).executes((context) -> {
         double newX = (Double)context.getArgument("x", Double.class);
         double newZ = (Double)context.getArgument("z", Double.class);
         gpsEnabled = true;
         x = newX;
         z = newZ;
         return 1;
      })));
      builder.then(literal("off").executes((context) -> {
         gpsEnabled = false;
         x = 0.0;
         z = 0.0;
         return 1;
      }));
      builder.then(literal("info").executes((context) -> {
         IFormattableTextComponent message;
         if (!gpsEnabled) {
            message = (new StringTextComponent("Сейчас GPS отключен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(message);
         } else {
            message = (new StringTextComponent("Информация о текущем GPS: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent xLabel = (new StringTextComponent("x: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent xValue = (new StringTextComponent(String.format("%.1f", x).replace(",", "."))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent zLabel = (new StringTextComponent(" z: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent zValue = (new StringTextComponent(String.format("%.1f", z).replace(",", "."))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            ChatUtil.addText((new StringTextComponent("")).append(message).append(xLabel).append(xValue).append(zLabel).append(zValue));
         }

         return 1;
      }));
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      return reader.readString();
   }

   private static class DoubleArgumentType implements ArgumentType {
      public Double parse(StringReader reader) throws CommandSyntaxException {
         try {
            return reader.readDouble();
         } catch (CommandSyntaxException var3) {
            throw (new DynamicCommandExceptionType((value) -> {
               return new StringTextComponent("Неверный формат координаты: " + String.valueOf(value));
            })).create(reader.getString());
         }
      }
   }
}
