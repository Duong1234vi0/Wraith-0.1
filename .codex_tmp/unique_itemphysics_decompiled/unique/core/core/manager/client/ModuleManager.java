package unique.core.core.manager.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import unique.core.UniqueCore;
import unique.core.core.manager.IManager;
import unique.core.features.hud.HudElement;
import unique.core.features.hud.impl.ArmorHud;
import unique.core.features.hud.impl.Cooldowns;
import unique.core.features.hud.impl.Coords;
import unique.core.features.hud.impl.Crosshair;
import unique.core.features.hud.impl.CrosshairArrows;
import unique.core.features.hud.impl.FpsCounter;
import unique.core.features.hud.impl.Hotbar;
import unique.core.features.hud.impl.InventoryHud;
import unique.core.features.hud.impl.KeyBinds;
import unique.core.features.hud.impl.LegacyHud;
import unique.core.features.hud.impl.ModuleList;
import unique.core.features.hud.impl.PingHud;
import unique.core.features.hud.impl.PotionHud;
import unique.core.features.hud.impl.Radar;
import unique.core.features.hud.impl.RadarRewrite;
import unique.core.features.hud.impl.Speedometer;
import unique.core.features.hud.impl.StaffBoard;
import unique.core.features.hud.impl.TPSCounter;
import unique.core.features.hud.impl.TargetHud;
import unique.core.features.hud.impl.TotemCounter;
import unique.core.features.hud.impl.WaterMark;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.AntiCrash;
import unique.core.features.modules.client.AntiPacketException;
import unique.core.features.modules.client.AntiServerAdd;
import unique.core.features.modules.client.AntiServerRP;
import unique.core.features.modules.client.Capes;
import unique.core.features.modules.client.ClickGui;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.ClientSpoof;
import unique.core.features.modules.client.ClientSpoofPlus;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.client.Notifications;
import unique.core.features.modules.client.Rotations;
import unique.core.features.modules.client.SoundFX;
import unique.core.features.modules.client.ThunderHackGui;
import unique.core.features.modules.client.UnHook;
import unique.core.features.modules.client.Windows;
import unique.core.features.modules.combat.AimBot;
import unique.core.features.modules.combat.AntiBot;
import unique.core.features.modules.combat.Aura;
import unique.core.features.modules.combat.AutoAnchor;
import unique.core.features.modules.combat.AutoCrystal;
import unique.core.features.modules.combat.AutoGApple;
import unique.core.features.modules.combat.AutoPotion;
import unique.core.features.modules.combat.AutoTotem;
import unique.core.features.modules.combat.AutoTrap;
import unique.core.features.modules.combat.AutoWeb;
import unique.core.features.modules.combat.BowPop;
import unique.core.features.modules.combat.BowSpam;
import unique.core.features.modules.combat.CombatSupport;
import unique.core.features.modules.combat.Criticals;
import unique.core.features.modules.combat.ElytraMace;
import unique.core.features.modules.combat.FakeLag;
import unique.core.features.modules.combat.HitBox;
import unique.core.features.modules.combat.LegitHelper;
import unique.core.features.modules.combat.MaceDamage;
import unique.core.features.modules.combat.MaceSwap;
import unique.core.features.modules.combat.Reach;
import unique.core.features.modules.combat.SelfTrap;
import unique.core.features.modules.combat.TargetGrim;
import unique.core.features.modules.combat.TargetPearl;
import unique.core.features.modules.combat.TargetPlus;
import unique.core.features.modules.combat.TargetStrafe;
import unique.core.features.modules.combat.TriggerBot;
import unique.core.features.modules.misc.AntiAFK;
import unique.core.features.modules.misc.AntiAttack;
import unique.core.features.modules.misc.AutoAuth;
import unique.core.features.modules.misc.AutoFish;
import unique.core.features.modules.misc.AutoLeave;
import unique.core.features.modules.misc.AutoMLG;
import unique.core.features.modules.misc.ChestStealer;
import unique.core.features.modules.misc.ChorusExploit;
import unique.core.features.modules.misc.ExtraTab;
import unique.core.features.modules.misc.FakePlayer;
import unique.core.features.modules.misc.ItemScroller;
import unique.core.features.modules.misc.LagNotifier;
import unique.core.features.modules.misc.MiddleClick;
import unique.core.features.modules.misc.NameProtect;
import unique.core.features.modules.misc.Nuker;
import unique.core.features.modules.misc.PacketCanceler;
import unique.core.features.modules.misc.ServerHelper;
import unique.core.features.modules.misc.TotemPopCounter;
import unique.core.features.modules.movement.AirStuck;
import unique.core.features.modules.movement.AntiWeb;
import unique.core.features.modules.movement.AutoSprint;
import unique.core.features.modules.movement.AutoWalk;
import unique.core.features.modules.movement.Blink;
import unique.core.features.modules.movement.BoatFly;
import unique.core.features.modules.movement.ElytraBooster;
import unique.core.features.modules.movement.ElytraPlus;
import unique.core.features.modules.movement.ElytraTarget;
import unique.core.features.modules.movement.Flight;
import unique.core.features.modules.movement.GuiMove;
import unique.core.features.modules.movement.NoFall;
import unique.core.features.modules.movement.NoPush;
import unique.core.features.modules.movement.NoSlow;
import unique.core.features.modules.movement.PacketFly;
import unique.core.features.modules.movement.Phase;
import unique.core.features.modules.movement.Scaffold;
import unique.core.features.modules.movement.Speed;
import unique.core.features.modules.movement.Strafe;
import unique.core.features.modules.movement.SuperFW;
import unique.core.features.modules.movement.Timer;
import unique.core.features.modules.movement.TridentBoost;
import unique.core.features.modules.movement.Velocity;
import unique.core.features.modules.movement.WaterSpeed;
import unique.core.features.modules.player.AntiAim;
import unique.core.features.modules.player.AntiBadEffects;
import unique.core.features.modules.player.AntiHunger;
import unique.core.features.modules.player.AutoArmor;
import unique.core.features.modules.player.AutoEat;
import unique.core.features.modules.player.AutoRespawn;
import unique.core.features.modules.player.AutoTool;
import unique.core.features.modules.player.ClickAction;
import unique.core.features.modules.player.DurabilityAlert;
import unique.core.features.modules.player.ElytraSwap;
import unique.core.features.modules.player.FastUse;
import unique.core.features.modules.player.HotbarReplenish;
import unique.core.features.modules.player.InventoryCleaner;
import unique.core.features.modules.player.NoEntityTrace;
import unique.core.features.modules.player.NoJumpDelay;
import unique.core.features.modules.player.NoServerSlot;
import unique.core.features.modules.player.PortalInventory;
import unique.core.features.modules.player.SpeedMine;
import unique.core.features.modules.player.TpsSync;
import unique.core.features.modules.render.Animations;
import unique.core.features.modules.render.AspectRatio;
import unique.core.features.modules.render.BlockESP;
import unique.core.features.modules.render.Chams;
import unique.core.features.modules.render.ESP;
import unique.core.features.modules.render.FOV;
import unique.core.features.modules.render.FreeCam;
import unique.core.features.modules.render.Fullbright;
import unique.core.features.modules.render.Hat;
import unique.core.features.modules.render.ItemESP;
import unique.core.features.modules.render.ItemPhysics;
import unique.core.features.modules.render.JumpCircle;
import unique.core.features.modules.render.NameTags;
import unique.core.features.modules.render.NoCameraClip;
import unique.core.features.modules.render.NoRender;
import unique.core.features.modules.render.Particles;
import unique.core.features.modules.render.PearlESP;
import unique.core.features.modules.render.PopChams;
import unique.core.features.modules.render.Shaders;
import unique.core.features.modules.render.SoundESP;
import unique.core.features.modules.render.StorageEsp;
import unique.core.features.modules.render.TargetESP;
import unique.core.features.modules.render.Tooltips;
import unique.core.features.modules.render.Tracers;
import unique.core.features.modules.render.Trails;
import unique.core.features.modules.render.ViewModel;
import unique.core.features.modules.render.WorldTweaks;
import unique.core.features.modules.render.XRay;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.font.FontRenderers;

public class ModuleManager implements IManager {
   public ArrayList<Module> modules = new ArrayList<>();
   public List<Module> sortedModules = new ArrayList<>();
   public List<Integer> activeMouseKeys = new ArrayList<>();
   public static AntiPacketException antiPacketException = new AntiPacketException();
   public static InventoryCleaner inventoryCleaner = new InventoryCleaner();
   public static PortalInventory portalInventory = new PortalInventory();
   public static TotemPopCounter totemPopCounter = new TotemPopCounter();
   public static HotbarReplenish hotbarReplenish = new HotbarReplenish();
   public static DurabilityAlert durabilityAlert = new DurabilityAlert();
   public static CrosshairArrows crosshairArrows = new CrosshairArrows();
   public static PacketCanceler packetCanceler = new PacketCanceler();
   public static ClientSettings clientSettings = new ClientSettings();
   public static ThunderHackGui thunderHackGui = new ThunderHackGui();
   public static AntiBadEffects antiBadEffects = new AntiBadEffects();
   public static Notifications notifications = new Notifications();
   public static NoEntityTrace noEntityTrace = new NoEntityTrace();
   public static ChorusExploit chorusExploit = new ChorusExploit();
   public static AntiServerAdd antiServerAdd = new AntiServerAdd();
   public static TridentBoost tridentBoost = new TridentBoost();
   public static TargetStrafe targetStrafe = new TargetStrafe();
   public static RadarRewrite radarRewrite = new RadarRewrite();
   public static NoServerSlot noServerSlot = new NoServerSlot();
   public static NoCameraClip noCameraClip = new NoCameraClip();
   public static ItemScroller itemScroller = new ItemScroller();
   public static ChestStealer chestStealer = new ChestStealer();
   public static AntiServerRP antiServerRP = new AntiServerRP();
   public static TotemCounter totemCounter = new TotemCounter();
   public static ServerHelper serverHelper = new ServerHelper();
   public static WorldTweaks worldTweaks = new WorldTweaks();
   public static Speedometer speedometer = new Speedometer();
   public static NoJumpDelay noJumpDelay = new NoJumpDelay();
   public static NameProtect nameProtect = new NameProtect();
   public static MiddleClick middleClick = new MiddleClick();
   public static LagNotifier lagNotifier = new LagNotifier();
   public static AutoRespawn autoRespawn = new AutoRespawn();
   public static AutoCrystal autoCrystal = new AutoCrystal();
   public static AspectRatio aspectRatio = new AspectRatio();
   public static ClientSpoof clientSpoof = new ClientSpoof();
   public static LegitHelper legitHelper = new LegitHelper();
   public static AutoAnchor autoAnchor = new AutoAnchor();
   public static WaterSpeed waterSpeed = new WaterSpeed();
   public static TriggerBot triggerBot = new TriggerBot();
   public static TPSCounter tpsCounter = new TPSCounter();
   public static StorageEsp storageEsp = new StorageEsp();
   public static StaffBoard staffBoard = new StaffBoard();
   public static ModuleList moduleList = new ModuleList();
   public static JumpCircle jumpCircle = new JumpCircle();
   public static Fullbright fullbright = new Fullbright();
   public static FpsCounter fpsCounter = new FpsCounter();
   public static FakePlayer fakePlayer = new FakePlayer();
   public static ElytraSwap elytraSwap = new ElytraSwap();
   public static ElytraPlus elytraPlus = new ElytraPlus();
   public static AutoSprint autoSprint = new AutoSprint();
   public static AutoGApple autoGApple = new AutoGApple();
   public static AntiHunger antiHunger = new AntiHunger();
   public static Animations animations = new Animations();
   public static AntiAttack antiAttack = new AntiAttack();
   public static Particles particles = new Particles();
   public static WaterMark waterMark = new WaterMark();
   public static ViewModel viewModel = new ViewModel();
   public static TargetHud targetHud = new TargetHud();
   public static SpeedMine speedMine = new SpeedMine();
   public static PotionHud potionHud = new PotionHud();
   public static PacketFly packetFly = new PacketFly();
   public static LegacyHud legacyHud = new LegacyHud();
   public static HudEditor hudEditor = new HudEditor();
   public static Crosshair crosshair = new Crosshair();
   public static Criticals criticals = new Criticals();
   public static AutoTotem autoTotem = new AutoTotem();
   public static AutoLeave autoLeave = new AutoLeave();
   public static AutoArmor autoArmor = new AutoArmor();
   public static Cooldowns cooldowns = new Cooldowns();
   public static Rotations rotations = new Rotations();
   public static AntiCrash antiCrash = new AntiCrash();
   public static Velocity velocity = new Velocity();
   public static Tooltips tooltips = new Tooltips();
   public static Scaffold scaffold = new Scaffold();
   public static PopChams popChams = new PopChams();
   public static NoRender noRender = new NoRender();
   public static NameTags nameTags = new NameTags();
   public static KeyBinds keyBinds = new KeyBinds();
   public static ExtraTab extraTab = new ExtraTab();
   public static ClickGui clickGui = new ClickGui();
   public static AutoTrap autoTrap = new AutoTrap();
   public static AutoTool autoTool = new AutoTool();
   public static SoundESP soundESP = new SoundESP();
   public static AutoFish autoFish = new AutoFish();
   public static AutoAuth autoAuth = new AutoAuth();
   public static ArmorHud armorHud = new ArmorHud();
   public static SelfTrap selfTrap = new SelfTrap();
   public static AutoWalk autoWalk = new AutoWalk();
   public static BlockESP blockESP = new BlockESP();
   public static AutoEat autoEat = new AutoEat();
   public static AntiAFK antiAFK = new AntiAFK();
   public static SoundFX soundFX = new SoundFX();
   public static TpsSync tpsSync = new TpsSync();
   public static Shaders shaders = new Shaders();
   public static PingHud pingHud = new PingHud();
   public static ItemESP itemESP = new ItemESP();
   public static GuiMove guiMove = new GuiMove();
   public static FreeCam freeCam = new FreeCam();
   public static FastUse fastUse = new FastUse();
   public static BowSpam bowSpam = new BowSpam();
   public static BoatFly boatFly = new BoatFly();
   public static AutoWeb autoWeb = new AutoWeb();
   public static AntiWeb antiWeb = new AntiWeb();
   public static AntiBot antiBot = new AntiBot();
   public static AntiAim antiAim = new AntiAim();
   public static Tracers tracers = new Tracers();
   public static BowPop bowPop = new BowPop();
   public static Trails trails = new Trails();
   public static Strafe strafe = new Strafe();
   public static NoSlow noSlow = new NoSlow();
   public static NoFall noFall = new NoFall();
   public static Hotbar hotbar = new Hotbar();
   public static HitBox hitBox = new HitBox();
   public static Flight flight = new Flight();
   public static Coords coords = new Coords();
   public static AimBot aimBot = new AimBot();
   public static NoPush noPush = new NoPush();
   public static UnHook unHook = new UnHook();
   public static Timer timer = new Timer();
   public static Speed speed = new Speed();
   public static Reach reach = new Reach();
   public static Radar radar = new Radar();
   public static Nuker nuker = new Nuker();
   public static Chams chams = new Chams();
   public static Blink blink = new Blink();
   public static Phase phase = new Phase();
   public static XRay xray = new XRay();
   public static Aura aura = new Aura();
   public static FOV fov = new FOV();
   public static ESP esp = new ESP();
   public static AutoPotion autoPotion = new AutoPotion();
   public static PearlESP pearlESP = new PearlESP();
   public static AutoMLG autoMLG = new AutoMLG();
   public static FakeLag fakeLag = new FakeLag();
   public static Capes capes = new Capes();
   public static SuperFW superFW = new SuperFW();
   public static ClientSpoofPlus clientSpoofPlus = new ClientSpoofPlus();
   public static InventoryHud inventoryHud = new InventoryHud();
   public static Hat hat = new Hat();
   public static MaceSwap maceSwap = new MaceSwap();
   public static MaceDamage maceDamage = new MaceDamage();
   public static TargetPearl targetPearl = new TargetPearl();
   public static ElytraBooster elytraBooster = new ElytraBooster();
   public static ElytraTarget elytraOvertake = new ElytraTarget();
   public static ItemPhysics itemPhysics = new ItemPhysics();
   public static ClickAction clickAction = new ClickAction();
   public static TargetPlus targetPlus = new TargetPlus();
   public static AirStuck airStuck = new AirStuck();
   public static TargetESP targetESP = new TargetESP();
   public static Windows windows = new Windows();
   public static TargetGrim targetGrim = new TargetGrim();
   public static CombatSupport combatSupport = new CombatSupport();
   public static ElytraMace elytraMace = new ElytraMace();

   public ModuleManager() {
      for (Field field : this.getClass().getDeclaredFields()) {
         if (Module.class.isAssignableFrom(field.getType())) {
            field.setAccessible(true);

            try {
               this.modules.add((Module)field.get(this));
            } catch (IllegalAccessException var6) {
               var6.printStackTrace();
            }
         }
      }
   }

   public Module get(String name) {
      for (Module module : this.modules) {
         if (module.getName().equalsIgnoreCase(name)) {
            return module;
         }
      }

      return null;
   }

   public ArrayList<Module> getEnabledModules() {
      ArrayList<Module> enabledModules = new ArrayList<>();

      for (Module module : this.modules) {
         if (module.isEnabled()) {
            enabledModules.add(module);
         }
      }

      return enabledModules;
   }

   public ArrayList<Module> getModulesByCategory(Module.Category category) {
      ArrayList<Module> modulesCategory = new ArrayList<>();
      this.modules.forEach(module -> {
         if (module.getCategory() == category) {
            modulesCategory.add(module);
         }
      });
      return modulesCategory;
   }

   public <T extends Module> T getModuleByClass(Class<T> clazz) {
      for (Module module : this.modules) {
         if (clazz.isInstance(module)) {
            return (T)module;
         }
      }

      return null;
   }

   public class_742 getPlayerByName(String name) {
      if (mc.field_1687 == null) {
         return null;
      } else {
         for (class_742 player : mc.field_1687.method_18456()) {
            if (player.method_5477().getString().equalsIgnoreCase(name)) {
               return player;
            }
         }

         return null;
      }
   }

   public List<Module.Category> getCategories() {
      return new ArrayList<>(Module.Category.values());
   }

   public void onLoad(String category) {
      try {
         UniqueCore.EVENT_BUS.unsubscribe(unHook);
      } catch (Exception var3) {
      }

      unHook.setEnabled(false);
      this.modules.sort(Comparator.comparing(Module::getName));
      this.modules.forEach(m -> {
         if (m.isEnabled() && (m.getCategory().getName().equalsIgnoreCase(category) || category.equals("none"))) {
            UniqueCore.EVENT_BUS.subscribe(m);
         }
      });
      if (ConfigManager.firstLaunch) {
         notifications.enable();
         soundFX.enable();
      }
   }

   public void onUpdate() {
      if (!Module.fullNullCheck()) {
         this.modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
      }
   }

   public void onRender2D(class_332 context) {
      if (!mc.method_53526().method_53536() && !mc.field_1690.field_1842) {
         this.modules.stream().filter(Module::isEnabled).forEach(m -> m.onRender2D(context));
         UniqueCore.core.onRender2D(context);
      }
   }

   public void onRender3D(class_4587 stack) {
      this.modules.stream().filter(Module::isEnabled).forEach(module -> module.onRender3D(stack));
   }

   public void sortModules() {
      this.sortedModules = this.getEnabledModules()
         .stream()
         .filter(Module::isDrawn)
         .sorted(Comparator.comparing(module -> FontRenderers.getModulesRenderer().getStringWidth(module.getFullArrayString()) * -1.0F))
         .collect(Collectors.toList());
   }

   public void onLogout() {
      this.modules.forEach(Module::onLogout);
   }

   public void onLogin() {
      this.modules.forEach(Module::onLogin);
   }

   public void onUnload(String category) {
      this.modules.forEach(module -> {
         if (module.isEnabled() && (module.getCategory().getName().equalsIgnoreCase(category) || category.equals("none"))) {
            UniqueCore.EVENT_BUS.unsubscribe(module);
            module.setEnabled(false);
         }
      });
      this.modules.forEach(Module::onUnload);
   }

   public void onKeyPressed(int eventKey) {
      if (eventKey != -1 && eventKey != 0 && !(mc.field_1755 instanceof ClickGUI)) {
         this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
               module.toggle();
            }
         });
      }
   }

   public void onKeyReleased(int eventKey) {
      if (eventKey != -1 && eventKey != 0 && !(mc.field_1755 instanceof ClickGUI)) {
         this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey && module.getBind().isHold()) {
               module.disable();
            }
         });
      }
   }

   public void onMoseKeyPressed(int eventKey) {
      if (eventKey != -1 && !(mc.field_1755 instanceof ClickGUI)) {
         this.modules.forEach(module -> {
            if (Objects.equals(module.getBind().getBind(), "M" + eventKey)) {
               module.toggle();
            }
         });
      }
   }

   public void onMoseKeyReleased(int eventKey) {
      if (eventKey != -1 && !(mc.field_1755 instanceof ClickGUI)) {
         this.activeMouseKeys.add(eventKey);
         this.modules.forEach(module -> {
            if (Objects.equals(module.getBind().getBind(), "M" + eventKey) && module.getBind().isHold()) {
               module.disable();
            }
         });
      }
   }

   public ArrayList<Module> getModulesSearch(String string) {
      ArrayList<Module> modulesCategory = new ArrayList<>();
      this.modules.forEach(module -> {
         if (module.getName().toLowerCase().contains(string.toLowerCase())) {
            modulesCategory.add(module);
         }
      });
      return modulesCategory;
   }

   public void registerModule(Module module) {
      if (module != null) {
         this.modules.add(module);
         if (module.isEnabled()) {
            UniqueCore.EVENT_BUS.subscribe(module);
         }
      }
   }

   public void registerHudElement(HudElement hudElement) {
      if (hudElement != null) {
         this.modules.add(hudElement);
      }
   }
}
