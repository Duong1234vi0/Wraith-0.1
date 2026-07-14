package sky.core.cmd.impl;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
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
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.staffmanager.StaffManager;
import sky.core.utils.misc.ChatUtil;

public class StaffCommand extends Command implements ArgumentType, Wrapper {
   public StaffCommand() {
      super("staff");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("add").then(args("player", new StaffCommand()).executes((context) -> {
         String name = (String)context.getArgument("player", String.class);
         boolean exists = SkyCore.getInstance().getStaffManager().isStaff(name);
         if (!exists) {
            SkyCore.getInstance().getStaffManager().addStaff(name);
         }

         IFormattableTextComponent prefix = (new StringTextComponent("Персонал с именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         IFormattableTextComponent nameText = (new StringTextComponent(name)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
         IFormattableTextComponent suffix = (new StringTextComponent("'" + (exists ? " уже существует!" : " добавлен!"))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
         return 1;
      })));
      builder.then(literal("remove").then(args("player", new StaffCommand()).executes((context) -> {
         String name = (String)context.getArgument("player", String.class);
         boolean isStaff = SkyCore.getInstance().getStaffManager().isStaff(name);
         if (!isStaff) {
            return this.invalidArgs(context);
         } else {
            SkyCore.getInstance().getStaffManager().removeStaff(name);
            IFormattableTextComponent prefix = (new StringTextComponent("Персонал с именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent nameText = (new StringTextComponent(name)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent suffix = (new StringTextComponent("' удалён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
            return 1;
         }
      })));
      builder.then(literal("list").executes((context) -> {
         List list = SkyCore.getInstance().getStaffManager().getStaff();
         IFormattableTextComponent emptyMsg;
         if (list != null && !list.isEmpty()) {
            emptyMsg = (new StringTextComponent("Список персонала:")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
               StaffManager.StaffEntry e = (StaffManager.StaffEntry)var4.next();
               String staffName = e.getName();
               IFormattableTextComponent staffText = (new StringTextComponent(staffName)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               StringTextComponent var10000 = new StringTextComponent(" [Удалить]");
               Style var10001 = Style.EMPTY.applyFormatting(TextFormatting.RED);
               ClickEvent.Action var10004 = Action.RUN_COMMAND;
               String var10005 = SkyCore.getInstance().getCommandsManager().getPrefix();
               IFormattableTextComponent removeBtn = var10000.setStyle(var10001.setClickEvent(new EventClientClick(var10004, var10005 + this.getCommand() + " remove " + staffName)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, (new StringTextComponent("Клик для удаления персонала")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED)))));
               ChatUtil.addText((new StringTextComponent("")).append(staffText).append(removeBtn));
            }

            IFormattableTextComponent total = (new StringTextComponent("Всего: " + list.size())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(total);
            return 1;
         } else {
            emptyMsg = (new StringTextComponent("Список персонала пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            return 1;
         }
      }));
      builder.then(literal("clear").executes((context) -> {
         List list = SkyCore.getInstance().getStaffManager().getStaff();
         IFormattableTextComponent emptyMsg;
         if (list != null && !list.isEmpty()) {
            SkyCore.getInstance().getStaffManager().clearStaff();
            emptyMsg = (new StringTextComponent("Список персонала очищен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            return 1;
         } else {
            emptyMsg = (new StringTextComponent("Список персонала пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            return 1;
         }
      }));
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      return reader.readString();
   }

   public CompletableFuture listSuggestions(CommandContext context, SuggestionsBuilder builder) {
      String[] inputParts = context.getInput().split(" ");
      boolean isRemove = inputParts.length > 1 && "remove".equals(inputParts[1]);
      List suggestions;
      if (isRemove) {
         suggestions = (List)SkyCore.getInstance().getStaffManager().getStaff().stream().map(StaffManager.StaffEntry::getName).collect(Collectors.toList());
      } else {
         suggestions = (List)mc.getConnection().getPlayerInfoMap().stream().map((info) -> {
            return info.getGameProfile().getName();
         }).collect(Collectors.toList());
      }

      return ISuggestionProvider.suggest(suggestions, builder);
   }
}
