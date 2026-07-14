package sky.core.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.cmd.Command;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.GradientUtil;

public class HelpCommand extends Command {
   public HelpCommand() {
      super("help");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((ctx) -> {
         int start = ThemeEditor.getColor(ThemeSettings.LOGO);
         int end = ColorUtil.darken(start, 0.5F);
         CommandInfo[] commands = new CommandInfo[]{new CommandInfo("config", new String[]{"clear", "dir", "list", "load", "remove", "save", "reset"}, HelpCommand.BracketType.PARENTHESES, "cfg"), new CommandInfo("friend", new String[]{"add", "clear", "list", "remove"}, HelpCommand.BracketType.PARENTHESES, "fr"), new CommandInfo("macros", new String[]{"add", "clear", "list", "remove"}, HelpCommand.BracketType.PARENTHESES, "mac"), new CommandInfo("bind", new String[]{"add", "clear", "list", "remove"}, HelpCommand.BracketType.PARENTHESES), new CommandInfo("staff", new String[]{"add", "clear", "list", "remove"}, HelpCommand.BracketType.PARENTHESES), new CommandInfo("gps", new String[]{"off", "info", "<pos>"}, HelpCommand.BracketType.SQUARE), new CommandInfo("waypoint", new String[]{"add", "clear", "list", "remove"}, HelpCommand.BracketType.PARENTHESES, "way"), new CommandInfo("prefix", new String[]{"<prefix>"}, HelpCommand.BracketType.ANGLE), new CommandInfo("help", new String[]{"<command>"}, HelpCommand.BracketType.SQUARE), new CommandInfo("autocontract", new String[]{"<nickname>"}, HelpCommand.BracketType.ANGLE, "act")};
         CommandInfo[] var5 = commands;
         int var6 = commands.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            CommandInfo cmd = var5[var7];
            this.displayCommand(cmd.name, cmd.subcommands, cmd.bracketType, start, end);
            if (cmd.alias != null) {
               this.displayAlias(cmd.alias, cmd.name, start, end);
            }
         }

         return 1;
      });
   }

   private void displayCommand(String cmd, String[] subcommands, BracketType bracketType, int start, int end) {
      IFormattableTextComponent name = GradientUtil.gradient("." + cmd, start, end);
      IFormattableTextComponent details = (new StringTextComponent(bracketType.open)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));

      for(int i = 0; i < subcommands.length; ++i) {
         details.append((new StringTextComponent(subcommands[i])).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         if (i < subcommands.length - 1) {
            details.append((new StringTextComponent(" ¦ ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         }
      }

      details.append((new StringTextComponent(bracketType.close)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
      ChatUtil.addText((new StringTextComponent("")).append(name).append(new StringTextComponent(" ")).append(details));
   }

   private void displayAlias(String alias, String full, int start, int end) {
      IFormattableTextComponent aliasComp = GradientUtil.gradient("." + alias, start, end);
      IFormattableTextComponent map = (new StringTextComponent(" -> " + full)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
      ChatUtil.addText((new StringTextComponent("")).append(aliasComp).append(map));
   }

   private static enum BracketType {
      PARENTHESES("(", ")"),
      SQUARE("[", "]"),
      ANGLE("<", ">"),
      NONE("", "");

      private final String open;
      private final String close;

      private BracketType(String open, String close) {
         this.open = open;
         this.close = close;
      }

      // $FF: synthetic method
      private static BracketType[] $values() {
         return new BracketType[]{PARENTHESES, SQUARE, ANGLE, NONE};
      }
   }

   private static class CommandInfo {
      String name;
      String[] subcommands;
      BracketType bracketType;
      String alias;

      CommandInfo(String name, String[] subcommands, BracketType bracketType, String alias) {
         this.name = name;
         this.subcommands = subcommands;
         this.bracketType = bracketType;
         this.alias = alias;
      }

      CommandInfo(String name, String[] subcommands, BracketType bracketType) {
         this(name, subcommands, bracketType, (String)null);
      }
   }
}
