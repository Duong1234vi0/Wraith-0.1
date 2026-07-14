package sky.core.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.utils.misc.ChatUtil;

public abstract class Command {
   public List command;

   public Command(String... command) {
      this.command = Arrays.asList(command);
   }

   public abstract void run(LiteralArgumentBuilder var1);

   public static RequiredArgumentBuilder args(String name, ArgumentType type) {
      return RequiredArgumentBuilder.argument(name, type);
   }

   public static LiteralArgumentBuilder literal(String name) {
      return LiteralArgumentBuilder.literal(name);
   }

   public void add(CommandDispatcher dispatcher) {
      Iterator var2 = this.command.iterator();

      while(var2.hasNext()) {
         String name = (String)var2.next();
         LiteralArgumentBuilder builder = LiteralArgumentBuilder.literal(name);
         this.run(builder);
         dispatcher.register(builder);
      }

   }

   public String getCommand() {
      return (String)this.command.get(0);
   }

   protected int invalidArgs(CommandContext ctx) {
      String input = ctx.getInput();
      IFormattableTextComponent errorMsg = (new StringTextComponent("Неверно указаны аргументы - \"" + input + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED));
      ChatUtil.addText(errorMsg);
      IFormattableTextComponent helpMsg = (new StringTextComponent("Для помощи - .help")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED));
      ChatUtil.addText(helpMsg);
      return 1;
   }
}
