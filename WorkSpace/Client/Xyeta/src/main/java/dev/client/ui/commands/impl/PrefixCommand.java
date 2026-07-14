package dev.client.ui.commands.impl;

import dev.client.WildClient;
import dev.client.ui.commands.Command;
import dev.client.ui.commands.CommandInfo;
import dev.client.util.player.ChatUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class PrefixCommand extends Command {
   public PrefixCommand() {
      super(new CommandInfo(".prefix symbol", "prefix"));
   }

   @Compile
   public void execute(String[] args) {
      if (args.length != 0 && !args[0].isEmpty()) {
         if (args[0].length() == 1) {
            char s = args[0].charAt(0);
            WildClient.INSTANCE.getCommandManager().setCommandPrefix(s);
            ChatUtil.addMessage("Prefix set to " + s);
         } else {
            ChatUtil.addMessage("Invalid prefix value");
         }

      } else {
         ChatUtil.addMessage(".prefix symbol");
      }
   }
}
