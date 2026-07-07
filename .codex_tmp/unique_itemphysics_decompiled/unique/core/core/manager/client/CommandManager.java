package unique.core.core.manager.client;

import com.mojang.brigadier.CommandDispatcher;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_310;
import net.minecraft.class_637;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.IManager;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.impl.AddonsCommand;
import unique.core.features.cmd.impl.BenchMarkCommand;
import unique.core.features.cmd.impl.BindCommand;
import unique.core.features.cmd.impl.BlockESPCommand;
import unique.core.features.cmd.impl.CfgCommand;
import unique.core.features.cmd.impl.ChestStealerCommand;
import unique.core.features.cmd.impl.DrawCommand;
import unique.core.features.cmd.impl.DropAllCommand;
import unique.core.features.cmd.impl.EClipCommand;
import unique.core.features.cmd.impl.FriendCommand;
import unique.core.features.cmd.impl.GamemodeCommand;
import unique.core.features.cmd.impl.GarbageCleanerCommand;
import unique.core.features.cmd.impl.GetNbtCommand;
import unique.core.features.cmd.impl.GotoWaypointCommand;
import unique.core.features.cmd.impl.GpsCommand;
import unique.core.features.cmd.impl.HClipCommand;
import unique.core.features.cmd.impl.HelpCommand;
import unique.core.features.cmd.impl.HorseSpeedCommand;
import unique.core.features.cmd.impl.InvCleanerCommand;
import unique.core.features.cmd.impl.KitCommand;
import unique.core.features.cmd.impl.LoginCommand;
import unique.core.features.cmd.impl.MacroCommand;
import unique.core.features.cmd.impl.ModuleCommand;
import unique.core.features.cmd.impl.NukerCommand;
import unique.core.features.cmd.impl.OpenFolderCommand;
import unique.core.features.cmd.impl.PrefixCommand;
import unique.core.features.cmd.impl.RctCommand;
import unique.core.features.cmd.impl.ResetBindsCommand;
import unique.core.features.cmd.impl.RpcCommand;
import unique.core.features.cmd.impl.StaffCommand;
import unique.core.features.cmd.impl.TabParseCommand;
import unique.core.features.cmd.impl.TrackerCommand;
import unique.core.features.cmd.impl.TreasureCommand;
import unique.core.features.cmd.impl.VClipCommand;
import unique.core.features.cmd.impl.WayPointCommand;

public class CommandManager implements IManager {
   private String prefix = "@";
   private final CommandDispatcher<class_2172> dispatcher = new CommandDispatcher();
   private final class_2172 source = new class_637(null, class_310.method_1551());
   private final List<Command> commands = new ArrayList<>();

   public CommandManager() {
      this.add(new RpcCommand());
      this.add(new KitCommand());
      this.add(new GpsCommand());
      this.add(new CfgCommand());
      this.add(new RctCommand());
      this.add(new BindCommand());
      this.add(new DrawCommand());
      this.add(new HelpCommand());
      this.add(new NukerCommand());
      this.add(new EClipCommand());
      this.add(new HClipCommand());
      this.add(new LoginCommand());
      this.add(new MacroCommand());
      this.add(new StaffCommand());
      this.add(new VClipCommand());
      this.add(new AddonsCommand());
      this.add(new GetNbtCommand());
      this.add(new FriendCommand());
      this.add(new ModuleCommand());
      this.add(new PrefixCommand());
      this.add(new TrackerCommand());
      this.add(new GamemodeCommand());
      this.add(new DropAllCommand());
      this.add(new TreasureCommand());
      this.add(new WayPointCommand());
      this.add(new TabParseCommand());
      this.add(new BlockESPCommand());
      this.add(new BenchMarkCommand());
      this.add(new HorseSpeedCommand());
      this.add(new OpenFolderCommand());
      this.add(new ResetBindsCommand());
      this.add(new InvCleanerCommand());
      this.add(new GotoWaypointCommand());
      this.add(new ChestStealerCommand());
      this.add(new GarbageCleanerCommand());
   }

   private void add(@NotNull Command command) {
      command.register(this.dispatcher);
      this.commands.add(command);
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public Command get(Class<? extends Command> commandClass) {
      for (Command command : this.commands) {
         if (command.getClass().equals(commandClass)) {
            return command;
         }
      }

      return null;
   }

   @NotNull
   public static String getClientMessage() {
      return class_124.field_1068 + "⌊" + class_124.field_1065 + "⚡" + class_124.field_1068 + "⌉" + class_124.field_1070;
   }

   public List<Command> getCommands() {
      return this.commands;
   }

   public class_2172 getSource() {
      return this.source;
   }

   public CommandDispatcher<class_2172> getDispatcher() {
      return this.dispatcher;
   }

   public void registerCommand(Command command) {
      if (command != null) {
         command.register(this.dispatcher);
         this.commands.add(command);
      }
   }
}
