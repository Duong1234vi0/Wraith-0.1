package dev.client.ui.commands.impl;

import dev.client.WildClient;
import dev.client.ui.commands.Command;
import dev.client.ui.commands.CommandInfo;
import dev.client.util.player.ChatUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class HelpCommand extends Command {
   public HelpCommand() {
      super(new CommandInfo("list of commands", "help"));
   }

   @Compile
   public void execute(String[] args) {
      ChatUtil.addMessage("WILD CLIENT BETA TEST");
      String prefix = String.valueOf(WildClient.INSTANCE.getCommandManager().getCommandPrefix());

      for(Command command : WildClient.INSTANCE.getCommandManager().getCommands()) {
         CommandInfo commandInfo1 = command.getCommandInfo();
         ChatUtil.addMessage(prefix + commandInfo1.prefix() + " - " + commandInfo1.desc());
      }

   }
}
