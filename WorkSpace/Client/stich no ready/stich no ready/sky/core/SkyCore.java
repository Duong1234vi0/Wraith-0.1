package sky.core;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.EventManager;
import mods.cape.Cape;
import mods.proxy.Config;
import mods.proxy.ProxyServer;
import mods.viaversion.viamcp.ViaMCP;
import net.minecraft.util.text.StringTextComponent;
import other.party.IRCClient;
import sky.core.cmd.CommandManager;
import sky.core.handlers.HandlerManager;
import sky.core.modules.ModuleManager;
import sky.core.ui.gui.DropDown;
import sky.core.utils.component.ComponentManager;
import sky.core.utils.discord.rpc.DiscordManager;
import sky.core.utils.managers.impl.AccountManager;
import sky.core.utils.managers.impl.BlockESPManager;
import sky.core.utils.managers.impl.ClientConfig;
import sky.core.utils.managers.impl.ConfigManager;
import sky.core.utils.managers.impl.FriendManager;
import sky.core.utils.managers.impl.MacroManager;
import sky.core.utils.managers.impl.ThemeManager;
import sky.core.utils.managers.impl.WaypointManager;
import sky.core.utils.managers.impl.dragmanager.DraggingManager;
import sky.core.utils.managers.impl.staffmanager.StaffManager;

public class SkyCore {
   private static SkyCore instance;
   private ModuleManager moduleManager;
   private ConfigManager configManager;
   private ThemeManager themeManager;
   private AccountManager accountManager;
   private ClientConfig clientConfig;
   private FriendManager friendManager;
   private StaffManager staffManager;
   private MacroManager macroManager;
   private WaypointManager waypointManager;
   private BlockESPManager blockESPManager;
   private CommandManager commandsManager;
   private IRCClient ircClient;
   private DraggingManager draggingManager;
   private HandlerManager handlerManager;
   public DropDown dropDown;
   private ComponentManager componentManager;
   private Cape cape;
   private DiscordManager discordManager;

   public SkyCore() {
      instance = this;
      this.init();
   }

   private void init() {
      this.initMods();
      this.initManager();
      EventManager.register(this);
   }

   @Native
   private void initManager() {
      this.draggingManager = new DraggingManager();
      this.draggingManager.init();
      this.accountManager = new AccountManager();
      this.accountManager.init();
      this.themeManager = new ThemeManager();
      this.themeManager.init();
      this.configManager = new ConfigManager();
      this.configManager.init();
      this.moduleManager = new ModuleManager();
      this.moduleManager.init();
      this.friendManager = new FriendManager();
      this.friendManager.init();
      this.staffManager = new StaffManager();
      this.staffManager.init();
      this.macroManager = new MacroManager();
      this.macroManager.init();
      this.waypointManager = new WaypointManager();
      this.waypointManager.init();
      this.blockESPManager = new BlockESPManager();
      this.blockESPManager.init();
      this.componentManager = new ComponentManager();
      this.componentManager.init();
      this.clientConfig = new ClientConfig();
      this.clientConfig.init();
      this.handlerManager = new HandlerManager();
      this.handlerManager.init();
      this.clientConfig.applySettings();
      this.dropDown = new DropDown(new StringTextComponent(""));
      this.clientConfig.initializeDefaultTheme();
      this.commandsManager = new CommandManager();
      this.discordManager = (new DiscordManager()).start();
   }

   @Native
   private void initMods() {
      new ProxyServer();
      Config.loadConfig();
      this.cape = new Cape();
      this.cape.init();

      try {
         ViaMCP.create();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void shutdown() {
      this.draggingManager.save();
      if (this.clientConfig != null && ClientConfig.getCurrentActiveTheme() != null) {
         this.clientConfig.onThemeApplied(ClientConfig.getCurrentActiveTheme());
      }

      this.accountManager.updateLastAccountFile();
      this.clientConfig.saveCurrentSettings();
      if (this.discordManager != null) {
         this.discordManager.stopRPC();
      }

   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public ConfigManager getConfigManager() {
      return this.configManager;
   }

   public ThemeManager getThemeManager() {
      return this.themeManager;
   }

   public AccountManager getAccountManager() {
      return this.accountManager;
   }

   public ClientConfig getClientConfig() {
      return this.clientConfig;
   }

   public FriendManager getFriendManager() {
      return this.friendManager;
   }

   public StaffManager getStaffManager() {
      return this.staffManager;
   }

   public MacroManager getMacroManager() {
      return this.macroManager;
   }

   public WaypointManager getWaypointManager() {
      return this.waypointManager;
   }

   public BlockESPManager getBlockESPManager() {
      return this.blockESPManager;
   }

   public CommandManager getCommandsManager() {
      return this.commandsManager;
   }

   public IRCClient getIrcClient() {
      return this.ircClient;
   }

   public DraggingManager getDraggingManager() {
      return this.draggingManager;
   }

   public HandlerManager getHandlerManager() {
      return this.handlerManager;
   }

   public DropDown getDropDown() {
      return this.dropDown;
   }

   public ComponentManager getComponentManager() {
      return this.componentManager;
   }

   public Cape getCape() {
      return this.cape;
   }

   public DiscordManager getDiscordManager() {
      return this.discordManager;
   }

   public static SkyCore getInstance() {
      return instance;
   }
}
