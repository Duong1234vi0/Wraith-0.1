package sky.core.cmd;

import com.mojang.brigadier.CommandDispatcher;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.command.ISuggestionProvider;
import sky.core.cmd.impl.AutoContractCommand;
import sky.core.cmd.impl.BindCommand;
import sky.core.cmd.impl.BlockESPCommand;
import sky.core.cmd.impl.BotCommand;
import sky.core.cmd.impl.ConfigCommand;
import sky.core.cmd.impl.FakePlayerCommand;
import sky.core.cmd.impl.FriendCommand;
import sky.core.cmd.impl.GpsCommand;
import sky.core.cmd.impl.MacroCommand;
import sky.core.cmd.impl.RctCommand;
import sky.core.cmd.impl.StaffCommand;
import sky.core.cmd.impl.WaypointCommand;
import sky.core.utils.Wrapper;

public class CommandManager implements Wrapper {
   private String prefix = ".";
   private final CommandDispatcher dispatcher = new CommandDispatcher();
   private final List commands = new ArrayList();

   public ISuggestionProvider getSource() {
      ClientPlayNetHandler conn = mc.getConnection();
      return conn != null ? conn.getSuggestionProvider() : new ClientSuggestionProvider((ClientPlayNetHandler)null, mc);
   }

   private void initialize() {
      this.add(new ConfigCommand());
      this.add(new FriendCommand());
      this.add(new GpsCommand());
      this.add(new BindCommand());
      this.add(new StaffCommand());
      this.add(new FakePlayerCommand());
      this.add(new BotCommand());
      this.add(new MacroCommand());
      this.add(new WaypointCommand());
      this.add(new BlockESPCommand());
      this.add(new RctCommand());
      this.add(new AutoContractCommand());
   }

   public CommandManager() {
      this.initialize();
   }

   private void add(Command command) {
      command.add(this.dispatcher);
      this.commands.add(command);
   }

   public String getPrefix() {
      return this.prefix;
   }

   public CommandDispatcher getDispatcher() {
      return this.dispatcher;
   }

   public List getCommands() {
      return this.commands;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }
}
