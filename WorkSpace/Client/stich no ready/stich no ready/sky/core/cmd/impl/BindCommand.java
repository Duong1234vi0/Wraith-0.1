package sky.core.cmd.impl;

import com.adl.nativeprotect.Native;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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
import sky.core.modules.Module;
import sky.core.modules.ModuleManager;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.KeyMapper;

public class BindCommand extends Command implements Wrapper {
   public BindCommand() {
      super("bind");
   }

   @Native
   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("add").then(args("args", StringArgumentType.greedyString()).suggests(this::suggestAdd).executes(this::executeAdd)));
      builder.then(literal("remove").then(args("module", StringArgumentType.greedyString()).suggests(this::suggestModuleNames).executes(this::executeRemove)));
      builder.then(literal("list").executes(this::executeList));
      builder.then(literal("clear").executes(this::executeClear));
   }

   @Native
   private int executeAdd(CommandContext context) {
      String rest = ((String)context.getArgument("args", String.class)).trim();
      if (rest.isEmpty()) {
         return this.invalidArgs(context);
      } else {
         String[] tokens = rest.split("\\s+");
         if (tokens.length < 3) {
            return this.invalidArgs(context);
         } else {
            String modeToken = tokens[tokens.length - 1];
            String keyToken = tokens[tokens.length - 2];
            String moduleName = String.join(" ", Arrays.asList(tokens).subList(0, tokens.length - 2));
            Module.ToggleMode mode;
            if (modeToken.equalsIgnoreCase("hold")) {
               mode = Module.ToggleMode.HOLD;
            } else {
               if (!modeToken.equalsIgnoreCase("toggle")) {
                  return this.invalidArgs(context);
               }

               mode = Module.ToggleMode.TOGGLE;
            }

            KeyMapper key = KeyMapper.fromName(keyToken);
            if (key == null) {
               return this.invalidArgs(context);
            } else {
               ModuleManager mm = SkyCore.getInstance().getModuleManager();
               Module module = this.resolveModuleByFlexibleName(mm, moduleName);
               if (module == null) {
                  return this.invalidArgs(context);
               } else {
                  module.setBind(key.getKeyCode());
                  module.setToggleMode(mode);
                  IFormattableTextComponent msg1 = (new StringTextComponent("Установлен бинд ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
                  IFormattableTextComponent msg2 = (new StringTextComponent(KeyMapper.getKey(module.getBind()))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
                  IFormattableTextComponent msg3 = (new StringTextComponent(" на модуль '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
                  IFormattableTextComponent msg4 = (new StringTextComponent(module.getName())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
                  IFormattableTextComponent msg5 = (new StringTextComponent("'!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
                  ChatUtil.addText((new StringTextComponent("")).append(msg1).append(msg2).append(msg3).append(msg4).append(msg5));
                  return 1;
               }
            }
         }
      }
   }

   @Native
   private int executeRemove(CommandContext context) {
      String moduleName = (String)context.getArgument("module", String.class);
      ModuleManager mm = SkyCore.getInstance().getModuleManager();
      Module module = this.resolveModuleByFlexibleName(mm, moduleName);
      if (module == null) {
         return this.invalidArgs(context);
      } else {
         module.setBind(-100);
         IFormattableTextComponent msg1 = (new StringTextComponent("Модуль '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         IFormattableTextComponent msg2 = (new StringTextComponent(module.getName())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
         IFormattableTextComponent msg3 = (new StringTextComponent("' теперь не имеет бинда!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText((new StringTextComponent("")).append(msg1).append(msg2).append(msg3));
         return 1;
      }
   }

   @Native
   private int executeList(CommandContext context) {
      List bound = (List)SkyCore.getInstance().getModuleManager().getModules().stream().filter((mx) -> {
         return mx.getBind() != -100;
      }).collect(Collectors.toList());
      if (bound.isEmpty()) {
         IFormattableTextComponent empty = (new StringTextComponent("Список биндoв пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(empty);
         return 1;
      } else {
         Iterator var3 = bound.iterator();

         while(var3.hasNext()) {
            Module m = (Module)var3.next();
            String key = KeyMapper.getKey(m.getBind());
            IFormattableTextComponent name = (new StringTextComponent(m.getName())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent keyLabel = (new StringTextComponent(" клавиша: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent keyVal = (new StringTextComponent(key)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            String var10000 = SkyCore.getInstance().getCommandsManager().getPrefix();
            String removeCmd = var10000 + this.getCommand() + " remove " + this.normalizeModuleName(m.getName());
            IFormattableTextComponent removeBtn = (new StringTextComponent(" [Удалить]")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED).setClickEvent(new EventClientClick(Action.RUN_COMMAND, removeCmd)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, (new StringTextComponent("Клик для удаления бинда")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED)))));
            ChatUtil.addText((new StringTextComponent("")).append(name).append(keyLabel).append(keyVal).append(removeBtn));
         }

         return 1;
      }
   }

   @Native
   private int executeClear(CommandContext context) {
      boolean anyBound = SkyCore.getInstance().getModuleManager().getModules().stream().anyMatch((m) -> {
         return m.getBind() != -100;
      });
      IFormattableTextComponent clearedMsg;
      if (!anyBound) {
         clearedMsg = (new StringTextComponent("Список биндoв пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(clearedMsg);
         return 1;
      } else {
         SkyCore.getInstance().getModuleManager().getModules().forEach((m) -> {
            m.setBind(-100);
         });
         clearedMsg = (new StringTextComponent("Список Биндов очищен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(clearedMsg);
         return 1;
      }
   }

   @Native
   private CompletableFuture suggestModuleNames(CommandContext ctx, SuggestionsBuilder builder) {
      List names = (List)SkyCore.getInstance().getModuleManager().getModules().stream().map(Module::getName).map(this::normalizeModuleName).collect(Collectors.toList());
      return ISuggestionProvider.suggest(names, builder);
   }

   @Native
   private CompletableFuture suggestAdd(CommandContext ctx, SuggestionsBuilder builder) {
      String input = builder.getInput();
      String lower = input.toLowerCase(Locale.ROOT);
      int addPos = lower.indexOf(".bind add ");
      String afterAdd = addPos == -1 ? "" : input.substring(addPos + ".bind add ".length());
      boolean hasTrailingSpace = afterAdd.endsWith(" ");
      String trimmed = afterAdd.trim();
      String[] parts = trimmed.isEmpty() ? new String[0] : trimmed.split("\\s+");
      int replaceStart;
      if (parts.length == 0) {
         replaceStart = input.length();
      } else if (hasTrailingSpace) {
         replaceStart = input.length();
      } else {
         replaceStart = input.length() - parts[parts.length - 1].length();
      }

      SuggestionsBuilder sb = new SuggestionsBuilder(input, replaceStart);
      String modePrefix;
      if (parts.length == 0 || parts.length == 1 && !hasTrailingSpace) {
         modePrefix = parts.length == 0 ? "" : this.normalizeModuleName(parts[0]);
         List names = (List)SkyCore.getInstance().getModuleManager().getModules().stream().map(Module::getName).map(this::normalizeModuleName).filter((nx) -> {
            return nx.toLowerCase(Locale.ROOT).startsWith(modePrefix.toLowerCase(Locale.ROOT));
         }).collect(Collectors.toList());
         Iterator var18 = names.iterator();

         while(var18.hasNext()) {
            String n = (String)var18.next();
            sb.suggest(n);
         }

         return sb.buildFuture();
      } else if (parts.length != 1 && (parts.length != 2 || hasTrailingSpace)) {
         if (parts.length != 2 && hasTrailingSpace) {
            return sb.buildFuture();
         } else {
            modePrefix = parts.length >= 3 ? parts[2].toLowerCase(Locale.ROOT) : "";
            if ("toggle".startsWith(modePrefix)) {
               sb.suggest("toggle");
            }

            if ("hold".startsWith(modePrefix)) {
               sb.suggest("hold");
            }

            return sb.buildFuture();
         }
      } else {
         modePrefix = parts.length == 2 ? parts[1].toUpperCase(Locale.ROOT) : "";
         KeyMapper[] var13 = KeyMapper.values();
         int var14 = var13.length;

         for(int var15 = 0; var15 < var14; ++var15) {
            KeyMapper key = var13[var15];
            if (key.name().startsWith(modePrefix)) {
               sb.suggest(key.name());
            }
         }

         return sb.buildFuture();
      }
   }

   @Native
   private Module resolveModuleByFlexibleName(ModuleManager mm, String userInputName) {
      Module direct = (Module)mm.findModuleByName(userInputName).orElse((Object)null);
      if (direct != null) {
         return direct;
      } else {
         String normalized = this.normalizeModuleName(userInputName);
         Iterator var5 = mm.getModules().iterator();

         Module m;
         do {
            if (!var5.hasNext()) {
               return null;
            }

            m = (Module)var5.next();
         } while(!this.normalizeModuleName(m.getName()).equalsIgnoreCase(normalized));

         return m;
      }
   }

   @Native
   private String normalizeModuleName(String name) {
      return name.replaceAll("\\s+", "");
   }
}
