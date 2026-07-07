package unique.core.core;

import unique.core.UniqueCore;
import unique.core.core.manager.client.AddonManager;
import unique.core.core.manager.client.AsyncManager;
import unique.core.core.manager.client.CommandManager;
import unique.core.core.manager.client.ConfigManager;
import unique.core.core.manager.client.MacroManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.core.manager.client.NotificationManager;
import unique.core.core.manager.client.ProxyManager;
import unique.core.core.manager.client.ServerManager;
import unique.core.core.manager.client.ShaderManager;
import unique.core.core.manager.client.SoundManager;
import unique.core.core.manager.client.TelemetryManager;
import unique.core.core.manager.player.CombatManager;
import unique.core.core.manager.player.FriendManager;
import unique.core.core.manager.player.PlayerManager;
import unique.core.core.manager.world.WayPointManager;
import unique.core.utility.ThunderUtility;

public class Managers {
   public static final CombatManager COMBAT = new CombatManager();
   public static final FriendManager FRIEND = new FriendManager();
   public static final PlayerManager PLAYER = new PlayerManager();
   public static final WayPointManager WAYPOINT = new WayPointManager();
   public static final AddonManager ADDON = new AddonManager();
   public static final AsyncManager ASYNC = new AsyncManager();
   public static final ModuleManager MODULE = new ModuleManager();
   public static final ConfigManager CONFIG = new ConfigManager();
   public static final MacroManager MACRO = new MacroManager();
   public static final NotificationManager NOTIFICATION = new NotificationManager();
   public static final ProxyManager PROXY = new ProxyManager();
   public static final ServerManager SERVER = new ServerManager();
   public static final ShaderManager SHADER = new ShaderManager();
   public static final SoundManager SOUND = new SoundManager();
   public static final TelemetryManager TELEMETRY = new TelemetryManager();
   public static final CommandManager COMMAND = new CommandManager();

   public static void init() {
      ADDON.initAddons();
      CONFIG.load(CONFIG.getCurrentConfig());
      MODULE.onLoad("none");
      FRIEND.loadFriends();
      MACRO.onLoad();
      WAYPOINT.onLoad();
      PROXY.onLoad();
      SOUND.registerSounds();
      ASYNC.run(() -> {
         ThunderUtility.syncContributors();
         ThunderUtility.parseStarGazer();
         ThunderUtility.parseCommits();
         TELEMETRY.fetchData();
      });
   }

   public static void subscribe() {
      UniqueCore.EVENT_BUS.subscribe(NOTIFICATION);
      UniqueCore.EVENT_BUS.subscribe(SERVER);
      UniqueCore.EVENT_BUS.subscribe(PLAYER);
      UniqueCore.EVENT_BUS.subscribe(COMBAT);
      UniqueCore.EVENT_BUS.subscribe(ASYNC);
      UniqueCore.EVENT_BUS.subscribe(TELEMETRY);
   }
}
