package sky.core.cmd.impl;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import other.party.IRCClient;
import sky.core.SkyCore;
import sky.core.cmd.Command;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;

public class PartyCommand extends Command implements ArgumentType, Wrapper {
   public PartyCommand() {
      super("party", "p");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((context) -> {
         this.showHelp();
         return 1;
      });
      builder.then(literal("create").executes((context) -> {
         this.sendPartyCommand("create", "");
         return 1;
      }));
      builder.then(literal("joincode").then(args("code", this).executes((context) -> {
         String code = (String)context.getArgument("code", String.class);
         this.sendPartyCommand("joincode", code);
         return 1;
      })));
      builder.then(literal("join").executes((context) -> {
         this.sendPartyCommand("join", "");
         return 1;
      }));
      builder.then(literal("dismiss").executes((context) -> {
         this.sendPartyCommand("dismiss", "");
         return 1;
      }));
      builder.then(literal("leave").executes((context) -> {
         this.sendPartyCommand("leave", "");
         return 1;
      }));
      builder.then(literal("kick").then(args("player", this).executes((context) -> {
         String player = (String)context.getArgument("player", String.class);
         this.sendPartyCommand("kick", player);
         return 1;
      })));
      builder.then(literal("invite").then(args("player", this).executes((context) -> {
         String player = (String)context.getArgument("player", String.class);
         this.sendPartyCommand("invite", player);
         return 1;
      })));
      builder.then(literal("disband").executes((context) -> {
         this.sendPartyCommand("disband", "");
         return 1;
      }));
      builder.then(literal("info").executes((context) -> {
         this.sendPartyCommand("info", "");
         return 1;
      }));
   }

   private void sendPartyCommand(String command, String arg) {
      IRCClient client = SkyCore.getInstance().getIrcClient();
      if (client != null && client.isConnected()) {
         client.sendPartyCommand(command, arg);
      } else {
         ChatUtil.addText((new StringTextComponent("IRC не подключен!")).mergeStyle(TextFormatting.RED));
      }

   }

   private void showHelp() {
      String prefix = "§b[IRC] §r";
      ChatUtil.addText(new StringTextComponent(prefix + ".party create §7- Создать группу"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party joincode <код> §7- Войти по коду"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party join §7- Принять приглашение"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party dismiss §7- Отклонить приглашение"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party leave §7- Покинуть группу"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party kick <игрок> §7- Кикнуть игрока"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party invite <игрок> §7- Пригласить игрока"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party disband §7- Расформировать группу"));
      ChatUtil.addText(new StringTextComponent(prefix + ".party info §7- Информация о группе"));
   }

   public String parse(StringReader reader) throws CommandSyntaxException {
      return reader.readString();
   }

   public CompletableFuture listSuggestions(CommandContext context, SuggestionsBuilder builder) {
      IRCClient client = SkyCore.getInstance().getIrcClient();
      if (client != null && client.isConnected()) {
         List users = client.getOnlineUsers();
         String current = builder.getRemaining().toLowerCase();
         Stream var10000 = users.stream().filter((name) -> {
            return name.toLowerCase().startsWith(current);
         }).filter((name) -> {
            return !name.equals(client.getUsername());
         });
         Objects.requireNonNull(builder);
         var10000.forEach(builder::suggest);
      }

      return builder.buildFuture();
   }
}
