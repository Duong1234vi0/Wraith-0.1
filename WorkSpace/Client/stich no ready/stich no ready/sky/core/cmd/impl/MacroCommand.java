package sky.core.cmd.impl;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import sky.core.SkyCore;
import sky.core.cmd.Command;
import sky.core.events.EventClientClick;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.MacroManager;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.KeyMapper;

public class MacroCommand extends Command implements ArgumentType, Wrapper {
   public MacroCommand() {
      super("mac", "macros");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("add").then(args("args", StringArgumentType.greedyString()).suggests(this::suggestAdd).executes(this::executeAdd)));
      builder.then(literal("remove").then(args("name", StringArgumentType.word()).suggests(this::suggestMacroNames).executes((ctx) -> {
         String name = (String)ctx.getArgument("name", String.class);
         SkyCore.getInstance().getMacroManager().removeMacro(name);
         IFormattableTextComponent msg = (new StringTextComponent("Макрос c именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)).append((new StringTextComponent(name)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent("' удален!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         ChatUtil.addText(msg);
         return 1;
      })));
      builder.then(literal("list").executes((ctx) -> {
         List macros = SkyCore.getInstance().getMacroManager().getMacros();
         if (macros.isEmpty()) {
            ChatUtil.addText((new StringTextComponent("Список макросов пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            return 1;
         } else {
            ChatUtil.addText((new StringTextComponent("Список макросов:")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            Iterator var3 = macros.iterator();

            while(var3.hasNext()) {
               MacroManager.MacroEntry m = (MacroManager.MacroEntry)var3.next();
               String key = KeyMapper.getKey(m.getKeyCode());
               IFormattableTextComponent nameLbl = (new StringTextComponent("Имя: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
               IFormattableTextComponent nameVal = (new StringTextComponent(m.getName())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               IFormattableTextComponent keyLbl = (new StringTextComponent(" Клавиша: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
               IFormattableTextComponent keyVal = (new StringTextComponent(key)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               IFormattableTextComponent textLbl = (new StringTextComponent(" Текст: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
               IFormattableTextComponent textVal = (new StringTextComponent(m.getCommand())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GREEN));
               String var10000 = SkyCore.getInstance().getCommandsManager().getPrefix();
               String removeCmd = var10000 + this.getCommand() + " remove " + m.getName();
               IFormattableTextComponent removeBtn = (new StringTextComponent(" [Удалить]")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED).setClickEvent(new EventClientClick(Action.RUN_COMMAND, removeCmd)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, (new StringTextComponent("Клик для удаления макроса")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED)))));
               ChatUtil.addText((new StringTextComponent("")).append(nameLbl).append(nameVal).append(keyLbl).append(keyVal).append(textLbl).append(textVal).append(removeBtn));
            }

            ChatUtil.addText((new StringTextComponent("Всего: " + macros.size())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            return 1;
         }
      }));
      builder.then(literal("clear").executes((ctx) -> {
         List macros = SkyCore.getInstance().getMacroManager().getMacros();
         if (macros.isEmpty()) {
            ChatUtil.addText((new StringTextComponent("Список макросов пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            return 1;
         } else {
            SkyCore.getInstance().getMacroManager().clear();
            ChatUtil.addText((new StringTextComponent("Список макросов очищен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            return 1;
         }
      }));
   }

   private int executeAdd(CommandContext ctx) {
      String rest = ((String)ctx.getArgument("args", String.class)).trim();
      if (rest.isEmpty()) {
         return this.invalidArgs(ctx);
      } else {
         String[] tokens = rest.split("\\s+");
         if (tokens.length < 3) {
            return this.invalidArgs(ctx);
         } else {
            String name = tokens[0];
            String keyToken = tokens[1];
            String command = String.join(" ", Arrays.asList(tokens).subList(2, tokens.length));
            KeyMapper key = KeyMapper.fromName(keyToken);
            if (key == null) {
               return this.invalidArgs(ctx);
            } else {
               SkyCore.getInstance().getMacroManager().addMacro(name, key.getKeyCode(), command);
               IFormattableTextComponent msg = (new StringTextComponent("Макрос с именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)).append((new StringTextComponent(name)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent("' и кнопкой активации: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY))).append((new StringTextComponent(KeyMapper.getKey(key.getKeyCode()))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent(" добавлен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
               ChatUtil.addText(msg);
               return 1;
            }
         }
      }
   }

   private CompletableFuture suggestMacroNames(CommandContext ctx, SuggestionsBuilder builder) {
      List names = (List)SkyCore.getInstance().getMacroManager().getMacros().stream().map(MacroManager.MacroEntry::getName).collect(Collectors.toList());
      return ISuggestionProvider.suggest(names, builder);
   }

   private CompletableFuture suggestAdd(CommandContext ctx, SuggestionsBuilder builder) {
      String input = ctx.getInput();
      String remaining = builder.getRemaining();
      boolean ends = remaining.endsWith(" ");
      String trimmed = remaining.trim();
      String[] parts = trimmed.isEmpty() ? new String[0] : trimmed.split("\\s+");
      int replaceStart;
      if (parts.length == 0) {
         replaceStart = input.length();
      } else if (ends) {
         replaceStart = input.length();
      } else {
         replaceStart = input.length() - parts[parts.length - 1].length();
      }

      SuggestionsBuilder sb = new SuggestionsBuilder(input, replaceStart);
      if (parts.length == 0 || parts.length == 1 && !ends) {
         return sb.buildFuture();
      } else if (parts.length != 1 && (parts.length != 2 || ends)) {
         return sb.buildFuture();
      } else {
         String keyPrefix = parts.length == 2 ? parts[1].toUpperCase(Locale.ROOT) : "";
         KeyMapper[] var11 = KeyMapper.values();
         int var12 = var11.length;

         for(int var13 = 0; var13 < var12; ++var13) {
            KeyMapper key = var11[var13];
            if (key.name().startsWith(keyPrefix)) {
               sb.suggest(key.name());
            }
         }

         return sb.buildFuture();
      }
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      return reader.readString();
   }
}
