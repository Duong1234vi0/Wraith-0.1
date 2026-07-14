package sky.core.cmd.impl;

import com.adl.nativeprotect.Native;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import other.bot.Bot;
import other.bot.BotManager;
import other.bot.BotStarter;
import sky.core.cmd.Command;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;

public class BotCommand extends Command implements Wrapper {
   public BotCommand() {
      super("b", "bot");
   }

   @Native
   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(literal("connect").then(args("nick", StringArgumentType.word()).then(args("ip", StringArgumentType.word()).executes(this::handleConnect))));
      builder.then(literal("remove").then(args("nickname", StringArgumentType.word()).suggests(this::suggestAllBots).executes(this::handleRemove)));
      builder.then(literal("control").then(args("name", StringArgumentType.word()).suggests(this::suggestAllBots).executes(this::handleControl)));
      builder.then(literal("return").executes((ctx) -> {
         mc.loadWorld(mc.world);
         Minecraft var10001 = mc;
         mc.renderViewEntity = Minecraft.player;
         ChatUtil.addText((new StringTextComponent("Вы вернулись к основному игроку.")).mergeStyle(TextFormatting.GREEN));
         return 1;
      }));
      builder.then(literal("chat").then(args("nicknm", StringArgumentType.word()).suggests(this::suggestAllBots).then(args("message", StringArgumentType.greedyString()).executes(this::handleChat))));
      builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)literal("autoles").then(literal("add").then(args("botnick", StringArgumentType.word()).suggests(this::suggestNonAutoLesBots).executes(this::handleAddAutoLes)))).then(literal("remove").then(args("botnick", StringArgumentType.word()).suggests(this::suggestAutoLesBots).executes(this::handleRemoveAutoLes)))).then(literal("list").executes(this::handleListAutoLes)));
   }

   @Native
   private int handleConnect(CommandContext ctx) {
      String name = (String)ctx.getArgument("nick", String.class);
      String ip = (String)ctx.getArgument("ip", String.class);
      BotStarter.run(name, ip);
      return 1;
   }

   @Native
   private int handleRemove(CommandContext ctx) {
      String name = (String)ctx.getArgument("nickname", String.class);
      Iterator var3 = BotManager.allBots.iterator();

      Bot bot;
      do {
         if (!var3.hasNext()) {
            return 1;
         }

         bot = (Bot)var3.next();
      } while(!bot.connection.bot.getNameClear().equals(name));

      bot.connection.world.sendQuittingDisconnectingPacket();
      BotManager.autoLesBots.removeIf((b) -> {
         return b.connection.bot.getNameClear().equals(name);
      });
      BotManager.tapeMouseBots.removeIf((b) -> {
         return b.getBot().connection.bot.getNameClear().equals(name);
      });
      BotManager.allBots.remove(bot);
      ChatUtil.addText((new StringTextComponent("Бот " + name + " успешно удален.")).mergeStyle(TextFormatting.GRAY));
      return 1;
   }

   @Native
   private int handleControl(CommandContext ctx) {
      String name = (String)ctx.getArgument("name", String.class);
      Iterator var3 = BotManager.allBots.iterator();

      while(var3.hasNext()) {
         Bot bot = (Bot)var3.next();
         if (bot.connection.bot.getName().getString().equals(name)) {
            mc.renderViewEntity = bot.connection.bot;
            mc.loadWorld(bot.connection.world);
            ChatUtil.addText((new StringTextComponent("Контроль переключен на бота: " + name)).mergeStyle(TextFormatting.AQUA));
         }
      }

      return 1;
   }

   @Native
   private int handleChat(CommandContext ctx) {
      String nick = (String)ctx.getArgument("nicknm", String.class);
      String message = (String)ctx.getArgument("message", String.class);
      Iterator var4 = BotManager.allBots.iterator();

      while(var4.hasNext()) {
         Bot bot = (Bot)var4.next();
         if (bot.connection.bot.getName().getString().equals(nick)) {
            bot.connection.bot.sendChatMessage(message);
         }
      }

      return 1;
   }

   @Native
   private int handleAddAutoLes(CommandContext ctx) {
      String nick = (String)ctx.getArgument("botnick", String.class);
      Iterator var3 = BotManager.allBots.iterator();

      while(var3.hasNext()) {
         Bot bot = (Bot)var3.next();
         if (bot.connection.bot.getName().getString().equals(nick) && BotManager.autoLesBots.stream().noneMatch((b) -> {
            return b.connection.bot.getName().getString().equals(nick);
         })) {
            BotManager.autoLesBots.add(bot);
            ChatUtil.addText((new StringTextComponent("Бот " + nick + " добавлен в AutoLes.")).mergeStyle(TextFormatting.GREEN));
         }
      }

      return 1;
   }

   @Native
   private int handleRemoveAutoLes(CommandContext ctx) {
      String nick = (String)ctx.getArgument("botnick", String.class);
      BotManager.autoLesBots.removeIf((bot) -> {
         return bot.connection.bot.getName().getString().equals(nick);
      });
      ChatUtil.addText((new StringTextComponent("Бот " + nick + " удален из AutoLes.")).mergeStyle(TextFormatting.YELLOW));
      return 1;
   }

   @Native
   private int handleListAutoLes(CommandContext ctx) {
      String list = (String)BotManager.autoLesBots.stream().map((bot) -> {
         return bot.connection.bot.getName().getString();
      }).collect(Collectors.joining(", "));
      if (list.isEmpty()) {
         ChatUtil.addText((new StringTextComponent("autoles: пусто")).mergeStyle(TextFormatting.RED));
      } else {
         ChatUtil.addText((new StringTextComponent("autoles: " + list)).mergeStyle(TextFormatting.GRAY));
      }

      return 1;
   }

   @Native
   private CompletableFuture suggestAllBots(CommandContext ctx, SuggestionsBuilder builder) {
      return ISuggestionProvider.suggest(BotManager.allBots.stream().map((bot) -> {
         return bot.connection.bot.getName().getString();
      }), builder);
   }

   @Native
   private CompletableFuture suggestAutoLesBots(CommandContext ctx, SuggestionsBuilder builder) {
      return ISuggestionProvider.suggest(BotManager.autoLesBots.stream().map((bot) -> {
         return bot.connection.bot.getName().getString();
      }), builder);
   }

   @Native
   private CompletableFuture suggestNonAutoLesBots(CommandContext ctx, SuggestionsBuilder builder) {
      return ISuggestionProvider.suggest(BotManager.allBots.stream().filter((bot) -> {
         return BotManager.autoLesBots.stream().noneMatch((b) -> {
            return b.connection.bot.getName().getString().equals(bot.connection.bot.getName().getString());
         });
      }).map((bot) -> {
         return bot.connection.bot.getName().getString();
      }), builder);
   }
}
