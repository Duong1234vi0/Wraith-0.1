package dev.client;

import dev.client.managers.CommandManager;
import dev.client.managers.ConfigManager;
import dev.client.managers.DraggableManager;
import dev.client.managers.EventManager;
import dev.client.managers.FriendManager;
import dev.client.managers.ModuleManager;
import dev.client.managers.NickNameManager;
import dev.client.managers.NotifyManager;
import dev.client.managers.RotationManager;
import dev.client.managers.ThemeManager;
import dev.client.ui.altmanager.AltManagerScreen;
import dev.client.ui.gui.Gui;
import dev.client.ui.mainmenu.MainScreen;
import dev.client.util.math.TimerUtil;
import dev.client.util.other.NameGen;
import dev.client.util.render.GradientText;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class WildClient implements ClientModInitializer {
   private ModuleManager moduleManager;
   private EventManager eventManager;
   private DraggableManager draggableManager;
   private ThemeManager themeManager;
   public static WildClient INSTANCE;
   private GradientText gradientText;
   private Gui gui;
   private static int mousePressed = -1;
   private TimerUtil timerUtil;
   private FriendManager friendManager;
   private RotationManager rotationManager;
   private CommandManager commandManager;
   private ConfigManager configManager;
   private NotifyManager notifyManager;
   private MainScreen mainScreen;
   private AltManagerScreen altManagerScreen;
   private NickNameManager nickNameManager;
   private NameGen nameGen;
   private float bodyPitch = 10.0F;
   private float timerValue;

   @Compile
   public void onInitializeClient() {
      INSTANCE = this;
      this.mainScreen = new MainScreen();
      this.timerValue = 1.0F;
      this.draggableManager = new DraggableManager();
      this.gradientText = new GradientText();
      this.eventManager = new EventManager();
      this.moduleManager = new ModuleManager();
      this.themeManager = new ThemeManager();
      this.friendManager = new FriendManager();
      this.rotationManager = new RotationManager();
      this.commandManager = new CommandManager();
      this.notifyManager = new NotifyManager();
      this.altManagerScreen = new AltManagerScreen(this.mainScreen);
      this.nickNameManager = new NickNameManager();
      this.nameGen = new NameGen();
      this.gui = new Gui();
      this.timerUtil = new TimerUtil();
      this.configManager = new ConfigManager(this.moduleManager, this.draggableManager, this.themeManager);
      HudRenderCallback.EVENT.register(this::render2D);
      ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
         INSTANCE.getConfigManager().saveConfig("default");
         INSTANCE.getConfigManager().saveDraggables();
         INSTANCE.getConfigManager().saveTheme();
      });
   }

   private void render2D(DrawContext context, RenderTickCounter tickCounter) {
      INSTANCE.getEventManager().getRender2DEvent().update(context, tickCounter);
      this.eventManager.hookEvent(INSTANCE.getEventManager().getRender2DEvent());
      INSTANCE.getEventManager().getHudRenderEvent().update(context, tickCounter);
      this.eventManager.hookEvent(INSTANCE.getEventManager().getHudRenderEvent());
   }


   public CommandManager getCommandManager() {
      return this.commandManager;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public DraggableManager getDraggableManager() {
      return this.draggableManager;
   }

   public GradientText getGradientText() {
      return this.gradientText;
   }

   public Gui getGui() {
      return this.gui;
   }

   public ThemeManager getThemeManager() {
      return this.themeManager;
   }

   public FriendManager getFriendManager() {
      return this.friendManager;
   }

   public RotationManager getRotationManager() {
      return this.rotationManager;
   }

   public ConfigManager getConfigManager() {
      return this.configManager;
   }

   public NotifyManager getNotifyManager() {
      return this.notifyManager;
   }

   public MainScreen getMainScreen() {
      return this.mainScreen;
   }

   public AltManagerScreen getAltManagerScreen() {
      return this.altManagerScreen;
   }

   public NickNameManager getNickNameManager() {
      return this.nickNameManager;
   }

   public NameGen getNameGen() {
      return this.nameGen;
   }

   public float getBodyPitch() {
      return this.bodyPitch;
   }

   public void setBodyPitch(float bodyPitch) {
      this.bodyPitch = bodyPitch;
   }

   public float getTimerValue() {
      return this.timerValue;
   }

   public void setTimerValue(float timerValue) {
      this.timerValue = timerValue;
   }
}
