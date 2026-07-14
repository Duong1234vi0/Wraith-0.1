package sky.core.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import sky.core.SkyCore;
import sky.core.events.EventKey;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.combat.AimBot;
import sky.core.modules.impl.combat.AntiBot;
import sky.core.modules.impl.combat.AntiThorns;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.combat.AutoExplosion;
import sky.core.modules.impl.combat.AutoSwap;
import sky.core.modules.impl.combat.AutoTotem;
import sky.core.modules.impl.combat.CrystalAura;
import sky.core.modules.impl.combat.HitBox;
import sky.core.modules.impl.combat.NoEntityTrace;
import sky.core.modules.impl.combat.NoFriendDamage;
import sky.core.modules.impl.combat.NoSlotChange;
import sky.core.modules.impl.combat.NoVelocity;
import sky.core.modules.impl.combat.PacketCriticals;
import sky.core.modules.impl.miscellaneous.AutoAccept;
import sky.core.modules.impl.miscellaneous.AutoContract;
import sky.core.modules.impl.miscellaneous.AutoDuel;
import sky.core.modules.impl.miscellaneous.AutoMessage;
import sky.core.modules.impl.miscellaneous.ChatHelper;
import sky.core.modules.impl.miscellaneous.ClickFriend;
import sky.core.modules.impl.miscellaneous.ElytraHelper;
import sky.core.modules.impl.miscellaneous.HolyWorldHelper;
import sky.core.modules.impl.miscellaneous.InstantRebreak;
import sky.core.modules.impl.miscellaneous.ItemHelper;
import sky.core.modules.impl.miscellaneous.NameProtect;
import sky.core.modules.impl.miscellaneous.OpenWalls;
import sky.core.modules.impl.miscellaneous.PotionTracker;
import sky.core.modules.impl.miscellaneous.ReallyWorldHelper;
import sky.core.modules.impl.miscellaneous.SRPSpoofer;
import sky.core.modules.impl.miscellaneous.ScoreboardHealth;
import sky.core.modules.impl.miscellaneous.SlimeHelper;
import sky.core.modules.impl.miscellaneous.TapeMouse;
import sky.core.modules.impl.miscellaneous.TargetPearl;
import sky.core.modules.impl.miscellaneous.ToggleSounds;
import sky.core.modules.impl.miscellaneous.UseTracker;
import sky.core.modules.impl.movement.AirStuck;
import sky.core.modules.impl.movement.ElytraBooster;
import sky.core.modules.impl.movement.ElytraMotion;
import sky.core.modules.impl.movement.ElytraTarget;
import sky.core.modules.impl.movement.Flight;
import sky.core.modules.impl.movement.FreeCamera;
import sky.core.modules.impl.movement.GrimGlide;
import sky.core.modules.impl.movement.GuiMove;
import sky.core.modules.impl.movement.NoControllerWeb;
import sky.core.modules.impl.movement.NoJumpDelay;
import sky.core.modules.impl.movement.NoPush;
import sky.core.modules.impl.movement.NoSlow;
import sky.core.modules.impl.movement.NoWeb;
import sky.core.modules.impl.movement.Speed;
import sky.core.modules.impl.movement.Spider;
import sky.core.modules.impl.movement.Sprint;
import sky.core.modules.impl.movement.Timer;
import sky.core.modules.impl.player.AimingItems;
import sky.core.modules.impl.player.AntiAFK;
import sky.core.modules.impl.player.AutoArmor;
import sky.core.modules.impl.player.AutoCraft;
import sky.core.modules.impl.player.AutoJoiner;
import sky.core.modules.impl.player.AutoLeave;
import sky.core.modules.impl.player.AutoPotion;
import sky.core.modules.impl.player.AutoRespawn;
import sky.core.modules.impl.player.AutoTool;
import sky.core.modules.impl.player.ChestStealer;
import sky.core.modules.impl.player.ClickPearl;
import sky.core.modules.impl.player.FastBreak;
import sky.core.modules.impl.player.FastExp;
import sky.core.modules.impl.player.ItemRelease;
import sky.core.modules.impl.player.ItemScroller;
import sky.core.modules.impl.player.ItemsCooldown;
import sky.core.modules.impl.player.KitsChest;
import sky.core.modules.impl.player.KitsEc;
import sky.core.modules.impl.player.LockSlot;
import sky.core.modules.impl.player.NoInteract;
import sky.core.modules.impl.visuals.Ambience;
import sky.core.modules.impl.visuals.Animation;
import sky.core.modules.impl.visuals.ArmorDurabilityView;
import sky.core.modules.impl.visuals.Arrows;
import sky.core.modules.impl.visuals.AspectRatio;
import sky.core.modules.impl.visuals.BlockESP;
import sky.core.modules.impl.visuals.BlockOutline;
import sky.core.modules.impl.visuals.Box3D;
import sky.core.modules.impl.visuals.ChinaHat;
import sky.core.modules.impl.visuals.Crosshair;
import sky.core.modules.impl.visuals.CrosshairAddons;
import sky.core.modules.impl.visuals.Cubes;
import sky.core.modules.impl.visuals.CustomModels;
import sky.core.modules.impl.visuals.ExtendedTab;
import sky.core.modules.impl.visuals.FireworkESP;
import sky.core.modules.impl.visuals.FullBright;
import sky.core.modules.impl.visuals.GhostPlayer;
import sky.core.modules.impl.visuals.Hands;
import sky.core.modules.impl.visuals.Interface;
import sky.core.modules.impl.visuals.ItemPhysics;
import sky.core.modules.impl.visuals.ItemReplacer;
import sky.core.modules.impl.visuals.JumpCircle;
import sky.core.modules.impl.visuals.KillEffect;
import sky.core.modules.impl.visuals.Particles;
import sky.core.modules.impl.visuals.ParticularWater;
import sky.core.modules.impl.visuals.Prediction;
import sky.core.modules.impl.visuals.Removals;
import sky.core.modules.impl.visuals.RichiDog;
import sky.core.modules.impl.visuals.SantaHat;
import sky.core.modules.impl.visuals.Saturation;
import sky.core.modules.impl.visuals.SeeInvisibles;
import sky.core.modules.impl.visuals.ShulkerPreview;
import sky.core.modules.impl.visuals.SwordAnimations;
import sky.core.modules.impl.visuals.Tags;
import sky.core.modules.impl.visuals.TargetESP;
import sky.core.modules.impl.visuals.TotemParticles;
import sky.core.modules.impl.visuals.Tracers;
import sky.core.modules.impl.visuals.Trails;
import sky.core.modules.impl.visuals.ViewModel;
import sky.core.modules.impl.visuals.Wings;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.misc.SoundUtil;

public class ModuleManager implements Wrapper {
   private final List modules = new CopyOnWriteArrayList();
   public AttackAura attackAura;
   public CrystalAura crystalAura;
   public AntiThorns antiThorns;
   public SwordAnimations swordAnimations;
   public ViewModel viewModel;
   public Removals removals;
   public AspectRatio aspectRatio;
   public Ambience ambience;
   public ElytraTarget elytraTarget;
   public NoWeb noWeb;
   public ElytraMotion elytraMotion;
   public OpenWalls openWalls;
   public SantaHat santaHat;
   public ChatHelper chatHelper;
   public TargetESP targetESP;

   public void init() {
      this.attackAura = new AttackAura();
      this.add(this.attackAura);
      this.add(new AutoTotem());
      this.add(new AntiBot());
      this.add(new AutoExplosion());
      this.crystalAura = new CrystalAura();
      this.add(this.crystalAura);
      this.add(new InstantRebreak());
      this.add(new HolyWorldHelper());
      this.add(new NoFriendDamage());
      this.add(new AutoSwap());
      this.add(new AimBot());
      this.add(new NoEntityTrace());
      this.add(new HitBox());
      this.add(new NoSlotChange());
      this.add(new NoVelocity());
      this.add(new PacketCriticals());
      this.add(new ItemRelease());
      this.antiThorns = new AntiThorns();
      this.add(this.antiThorns);
      this.add(new Sprint());
      this.add(new NoSlow());
      this.add(new ElytraBooster());
      this.add(new FreeCamera());
      this.add(new AirStuck());
      this.add(new NoJumpDelay());
      this.add(new Timer());
      this.add(new NoPush());
      this.add(new GuiMove());
      this.add(new Speed());
      this.noWeb = new NoWeb();
      this.add(this.noWeb);
      this.add(new NoControllerWeb());
      this.add(new Flight());
      this.elytraMotion = new ElytraMotion();
      this.add(this.elytraMotion);
      this.add(new ClickPearl());
      this.add(new AutoRespawn());
      this.add(new ItemsCooldown());
      this.add(new FastExp());
      this.add(new ItemScroller());
      this.add(new AutoPotion());
      this.add(new AimingItems());
      this.add(new KitsEc());
      this.add(new KitsChest());
      this.add(new AutoTool());
      this.add(new AntiAFK());
      this.add(new LockSlot());
      this.add(new FastBreak());
      this.add(new AutoArmor());
      this.add(new AutoLeave());
      this.add(new AutoCraft());
      this.add(new AutoJoiner());
      this.add(new ChestStealer());
      this.add(new NoInteract());
      this.ambience = new Ambience();
      this.add(this.ambience);
      this.add(new GhostPlayer());
      this.add(new Spider());
      this.viewModel = new ViewModel();
      this.add(this.viewModel);
      this.add(new Box3D());
      this.targetESP = new TargetESP();
      this.add(this.targetESP);
      this.add(new BlockOutline());
      this.swordAnimations = new SwordAnimations();
      this.add(this.swordAnimations);
      this.add(new ItemReplacer());
      this.removals = new Removals();
      this.add(this.removals);
      this.aspectRatio = new AspectRatio();
      this.add(new KillEffect());
      this.add(this.aspectRatio);
      this.add(new ArmorDurabilityView());
      this.add(new ExtendedTab());
      this.add(new ItemPhysics());
      this.add(new ChinaHat());
      this.add(new Prediction());
      this.add(new Tags());
      this.add(new Arrows());
      this.add(new SRPSpoofer());
      this.add(new FullBright());
      this.add(new Saturation());
      this.add(new Interface());
      this.add(new Tracers());
      this.add(new Trails());
      this.add(new SeeInvisibles());
      this.add(new ShulkerPreview());
      this.add(new Hands());
      this.add(new Animation());
      this.add(new FireworkESP());
      this.add(new Crosshair());
      this.add(new CrosshairAddons());
      this.add(new Wings());
      this.add(new CustomModels());
      this.add(new RichiDog());
      this.add(new ElytraHelper());
      this.add(new AutoAccept());
      this.add(new AutoMessage());
      this.add(new ItemHelper());
      this.add(new ClickFriend());
      this.add(new SlimeHelper());
      this.add(new NameProtect());
      this.add(new TargetPearl());
      this.add(new Cubes());
      this.add(new PotionTracker());
      this.add(new UseTracker());
      this.add(new ToggleSounds());
      this.openWalls = new OpenWalls();
      this.add(this.openWalls);
      this.add(new GrimGlide());
      this.add(new ScoreboardHealth());
      this.add(new ReallyWorldHelper());
      this.add(new TapeMouse());
      this.add(new AutoContract());
      this.add(new AutoDuel());
      this.chatHelper = new ChatHelper();
      this.add(this.chatHelper);
      this.add(new BlockESP());
      this.elytraTarget = new ElytraTarget();
      this.add(this.elytraTarget);
      this.add(new Particles());
      this.add(new ParticularWater());
      this.add(new TotemParticles());
      this.add(new JumpCircle());
      this.modules.sort(Comparator.comparing(Module::getName));
      EventManager.register(this);
   }

   private void add(Module module) {
      this.modules.add(module);
   }

   public Module getModule(Class classModule) {
      Iterator var2 = this.modules.iterator();

      Module module;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         module = (Module)var2.next();
      } while(module == null || module.getClass() != classModule);

      return module;
   }

   public Optional findModuleByName(String name) {
      return this.modules.stream().filter((module) -> {
         return module.getName().equalsIgnoreCase(name);
      }).findFirst();
   }

   @EventTarget
   private void onKey(EventKey e) {
      if (e.getKey() == 344) {
         mc.displayGuiScreen(SkyCore.getInstance().getDropDown());
      }

      Iterator var2 = this.modules.iterator();

      label77:
      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         if (module.getBind() == e.getKey() && (module.getToggleMode() == Module.ToggleMode.TOGGLE && e.isHold() || module.getToggleMode() == Module.ToggleMode.HOLD)) {
            module.toggle();
         }

         Iterator var4 = module.getSettings().iterator();

         while(true) {
            BooleanSetting booleanSetting;
            boolean isToggleMode;
            do {
               do {
                  Setting setting;
                  do {
                     if (!var4.hasNext()) {
                        continue label77;
                     }

                     setting = (Setting)var4.next();
                  } while(!(setting instanceof BooleanSetting));

                  booleanSetting = (BooleanSetting)setting;
               } while(booleanSetting.getBind() != e.getKey());

               isToggleMode = booleanSetting.getToggleMode() == Module.ToggleMode.TOGGLE;
            } while(isToggleMode && !e.isHold());

            boolean newValue = !(Boolean)booleanSetting.get();
            booleanSetting.set(newValue);
            if (!Module.isSuppressToggleEffects() && booleanSetting.isKeybindvisible()) {
               SoundUtil.playSound(ToggleSounds.getSoundFile(newValue));
               if ((Boolean)NotificationRender.module.get()) {
                  String categoryIcon = NotificationRender.getCategoryIcon(module);
                  NotificationManager.addNotification((String)categoryIcon, (String)("Module " + booleanSetting.getName() + (newValue ? " enabled!" : " disabled!")), -1);
               }
            }
         }
      }

   }

   public List getModules() {
      return this.modules;
   }

   public AttackAura getAttackAura() {
      return this.attackAura;
   }

   public CrystalAura getCrystalAura() {
      return this.crystalAura;
   }

   public AntiThorns getAntiThorns() {
      return this.antiThorns;
   }

   public SwordAnimations getSwordAnimations() {
      return this.swordAnimations;
   }

   public ViewModel getViewModel() {
      return this.viewModel;
   }

   public Removals getRemovals() {
      return this.removals;
   }

   public AspectRatio getAspectRatio() {
      return this.aspectRatio;
   }

   public Ambience getAmbience() {
      return this.ambience;
   }

   public ElytraTarget getElytraTarget() {
      return this.elytraTarget;
   }

   public NoWeb getNoWeb() {
      return this.noWeb;
   }

   public ElytraMotion getElytraMotion() {
      return this.elytraMotion;
   }

   public OpenWalls getOpenWalls() {
      return this.openWalls;
   }

   public SantaHat getSantaHat() {
      return this.santaHat;
   }

   public ChatHelper getChatHelper() {
      return this.chatHelper;
   }

   public TargetESP getTargetESP() {
      return this.targetESP;
   }
}
