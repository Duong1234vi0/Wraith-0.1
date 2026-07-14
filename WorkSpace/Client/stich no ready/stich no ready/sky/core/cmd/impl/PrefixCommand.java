package sky.core.cmd.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.cmd.Command;
import sky.core.utils.misc.ChatUtil;

public class PrefixCommand extends Command {
   public PrefixCommand() {
      super("prefix");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(args("value", StringArgumentType.greedyString()).executes((ctx) -> {
         String newPrefix = (String)ctx.getArgument("value", String.class);
         SkyCore.getInstance().getCommandsManager().setPrefix(newPrefix);
         IFormattableTextComponent msg = (new StringTextComponent("Префикс для использования команд установлен на '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)).append((new StringTextComponent(newPrefix)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent("'")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         ChatUtil.addText(msg);
         return 1;
      }));
   }
}
