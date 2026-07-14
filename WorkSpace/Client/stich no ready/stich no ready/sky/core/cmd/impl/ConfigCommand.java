package sky.core.cmd.impl;

import com.adl.nativeprotect.Native;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import sky.core.SkyCore;
import sky.core.cmd.Command;
import sky.core.events.EventClientClick;
import sky.core.utils.managers.FilePath;
import sky.core.utils.misc.ChatUtil;

public class ConfigCommand extends Command implements ArgumentType {
   public ConfigCommand() {
      super("config", "cfg");
   }

   @Native
   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("save").then(args("name", StringArgumentType.word()).executes((context) -> {
         String configName = (String)context.getArgument("name", String.class);
         SkyCore.getInstance().getConfigManager().saveConfig(configName);
         IFormattableTextComponent prefix = (new StringTextComponent("Конфиг с именем ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         IFormattableTextComponent nameText = (new StringTextComponent("\"" + configName + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
         IFormattableTextComponent suffix = (new StringTextComponent(" сохранён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
         return 1;
      })));
      builder.then(literal("load").then(args("name", new ConfigCommand()).executes((context) -> {
         String configName = (String)context.getArgument("name", String.class);
         if (!SkyCore.getInstance().getConfigManager().getConfigNames().contains(configName)) {
            return this.invalidArgs(context);
         } else {
            SkyCore.getInstance().getConfigManager().loadConfig(configName);
            IFormattableTextComponent prefix = (new StringTextComponent("Конфиг с именем ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent nameText = (new StringTextComponent("\"" + configName + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent suffix = (new StringTextComponent(" загружён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
            return 1;
         }
      })));
      builder.then(literal("remove").then(args("name", new ConfigCommand()).executes((context) -> {
         String configName = (String)context.getArgument("name", String.class);
         if (!SkyCore.getInstance().getConfigManager().getConfigNames().contains(configName)) {
            return this.invalidArgs(context);
         } else {
            SkyCore.getInstance().getConfigManager().deleteConfig(configName);
            IFormattableTextComponent prefix = (new StringTextComponent("Конфиг с именем ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent nameText = (new StringTextComponent("\"" + configName + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent suffix = (new StringTextComponent(" удалён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
            return 1;
         }
      })));
      builder.then(literal("list").executes((context) -> {
         List configList = SkyCore.getInstance().getConfigManager().getConfigNames();
         IFormattableTextComponent emptyMsg;
         if (configList != null && !configList.isEmpty()) {
            emptyMsg = (new StringTextComponent("Список конфигов:")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            Iterator var4 = configList.iterator();

            while(var4.hasNext()) {
               String cfgName = (String)var4.next();
               IFormattableTextComponent configText = (new StringTextComponent(cfgName)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               StringTextComponent var10000 = new StringTextComponent(" [Загрузить]");
               Style var10001 = Style.EMPTY.applyFormatting(TextFormatting.GREEN);
               ClickEvent.Action var10004 = Action.RUN_COMMAND;
               String var10005 = SkyCore.getInstance().getCommandsManager().getPrefix();
               IFormattableTextComponent loadBtn = var10000.setStyle(var10001.setClickEvent(new EventClientClick(var10004, var10005 + this.getCommand() + " load " + cfgName)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Загрузить конфиг " + cfgName))));
               var10000 = new StringTextComponent(" [Удалить]");
               var10001 = Style.EMPTY.applyFormatting(TextFormatting.RED);
               var10004 = Action.RUN_COMMAND;
               var10005 = SkyCore.getInstance().getCommandsManager().getPrefix();
               IFormattableTextComponent removeBtn = var10000.setStyle(var10001.setClickEvent(new EventClientClick(var10004, var10005 + this.getCommand() + " remove " + cfgName)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Удалить конфиг " + cfgName))));
               ChatUtil.addText((new StringTextComponent("")).append(configText).append(loadBtn).append(removeBtn));
            }

            return 1;
         } else {
            emptyMsg = (new StringTextComponent("Список конфигураций пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            return 1;
         }
      }));
      builder.then(literal("dir").executes((context) -> {
         try {
            File configDirectory = new File(FilePath.BASE_PATH + "custom\\");
            Runtime.getRuntime().exec("explorer " + configDirectory.getAbsolutePath());
         } catch (IOException var2) {
            var2.printStackTrace();
         }

         return 1;
      }));
      builder.then(literal("clear").executes((context) -> {
         List configs = SkyCore.getInstance().getConfigManager().getConfigNames();
         configs.forEach((config) -> {
            SkyCore.getInstance().getConfigManager().deleteConfig(config);
         });
         IFormattableTextComponent clearedMsg = (new StringTextComponent("Список конфигов очищен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(clearedMsg);
         return 1;
      }));
      builder.then(literal("reset").executes((context) -> {
         SkyCore.getInstance().getConfigManager().resetToDefaults();
         IFormattableTextComponent resetMsg = (new StringTextComponent("Конфиг сброшен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(resetMsg);
         return 1;
      }));
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      String config = reader.readString();
      boolean exists = SkyCore.getInstance().getConfigManager().getConfigNames().contains(config);
      if (!exists) {
         throw (new DynamicCommandExceptionType((name) -> {
            return new StringTextComponent("Конфиг с именем " + String.valueOf(name) + " не существует");
         })).create(config);
      } else {
         return config;
      }
   }

   public CompletableFuture listSuggestions(CommandContext context, SuggestionsBuilder builder) {
      List configNames = SkyCore.getInstance().getConfigManager().getConfigNames();
      return ISuggestionProvider.suggest(configNames, builder);
   }
}
