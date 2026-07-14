package sky.core.cmd.impl;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.time.format.DateTimeFormatter;
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
import sky.core.utils.managers.impl.FriendManager;
import sky.core.utils.misc.ChatUtil;

public class FriendCommand extends Command implements ArgumentType, Wrapper {
   public FriendCommand() {
      super("fr", "friend");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("add").then(args("player", new FriendCommand()).executes((context) -> {
         String friendName = (String)context.getArgument("player", String.class);
         boolean alreadyFriend = SkyCore.getInstance().getFriendManager().isFriend(friendName);
         IFormattableTextComponent prefix = (new StringTextComponent("Друг с именем ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         IFormattableTextComponent nameText = (new StringTextComponent("\"" + friendName + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
         IFormattableTextComponent suffix = (new StringTextComponent(alreadyFriend ? " уже существует!" : " добавлен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         if (!alreadyFriend) {
            SkyCore.getInstance().getFriendManager().addFriend(friendName);
         }

         ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
         return 1;
      })));
      builder.then(literal("remove").then(args("friend", new FriendCommand()).executes((context) -> {
         String friendName = (String)context.getArgument("friend", String.class);
         boolean isFriend = SkyCore.getInstance().getFriendManager().isFriend(friendName);
         if (!isFriend) {
            return this.invalidArgs(context);
         } else {
            SkyCore.getInstance().getFriendManager().removeFriend(friendName);
            IFormattableTextComponent prefix = (new StringTextComponent("Друг с именем ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            IFormattableTextComponent nameText = (new StringTextComponent("\"" + friendName + "\"")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
            IFormattableTextComponent suffix = (new StringTextComponent(" удалён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText((new StringTextComponent("")).append(prefix).append(nameText).append(suffix));
            return 1;
         }
      })));
      builder.then(literal("list").executes((context) -> {
         List friends = SkyCore.getInstance().getFriendManager().getFriends();
         IFormattableTextComponent emptyMsg;
         if (friends != null && !friends.isEmpty()) {
            emptyMsg = (new StringTextComponent("Список друзей:")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            Iterator var5 = friends.iterator();

            while(var5.hasNext()) {
               FriendManager.FriendEntry friend = (FriendManager.FriendEntry)var5.next();
               String friendName = friend.getName();
               IFormattableTextComponent friendText = (new StringTextComponent(friendName)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               IFormattableTextComponent added = (new StringTextComponent(" Добавлен: ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
               IFormattableTextComponent date = (new StringTextComponent(friend.getAddedTime().format(formatter))).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               StringTextComponent var10000 = new StringTextComponent(" [Удалить]");
               Style var10001 = Style.EMPTY.applyFormatting(TextFormatting.RED);
               ClickEvent.Action var10004 = Action.RUN_COMMAND;
               String var10005 = SkyCore.getInstance().getCommandsManager().getPrefix();
               IFormattableTextComponent removeBtn = var10000.setStyle(var10001.setClickEvent(new EventClientClick(var10004, var10005 + this.getCommand() + " remove " + friendName)).setHoverEvent(new HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, (new StringTextComponent("Клик для удаления" + friendName)).setStyle(Style.EMPTY.applyFormatting(TextFormatting.RED)))));
               ChatUtil.addText((new StringTextComponent("")).append(friendText).append(added).append(date).append(removeBtn));
            }

            IFormattableTextComponent total = (new StringTextComponent("Всего: " + friends.size())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(total);
            return 1;
         } else {
            emptyMsg = (new StringTextComponent("Список друзей пуст!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(emptyMsg);
            return 1;
         }
      }));
      builder.then(literal("clear").executes((context) -> {
         SkyCore.getInstance().getFriendManager().clearFriends();
         IFormattableTextComponent clearedMsg = (new StringTextComponent("Список друзей очищен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(clearedMsg);
         return 1;
      }));
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      String input = reader.readString();
      if (!"remove".equals(reader.getString().split(" ")[1]) || SkyCore.getInstance().getFriendManager() != null && SkyCore.getInstance().getFriendManager().isFriend(input)) {
         return input;
      } else {
         throw (new DynamicCommandExceptionType((name) -> {
            return new StringTextComponent("Друга с именем " + String.valueOf(name) + " не существует");
         })).create(input);
      }
   }

   public CompletableFuture listSuggestions(CommandContext context, SuggestionsBuilder builder) {
      String[] inputParts = context.getInput().split(" ");
      boolean isRemoveCommand = inputParts.length > 1 && "remove".equals(inputParts[1]);
      List suggestions;
      if (isRemoveCommand) {
         suggestions = (List)SkyCore.getInstance().getFriendManager().getFriends().stream().map(FriendManager.FriendEntry::getName).collect(Collectors.toList());
      } else {
         suggestions = (List)mc.getConnection().getPlayerInfoMap().stream().map((info) -> {
            return info.getGameProfile().getName();
         }).collect(Collectors.toList());
      }

      return ISuggestionProvider.suggest(suggestions, builder);
   }
}
