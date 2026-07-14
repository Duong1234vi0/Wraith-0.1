package dev.client.managers;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.impl.combat.AntiBot;
import dev.client.modules.impl.combat.Aura;
import dev.client.modules.impl.combat.AutoApple;
import dev.client.modules.impl.combat.AutoExplosion;
import dev.client.modules.impl.combat.AutoPotion;
import dev.client.modules.impl.combat.AutoSwap;
import dev.client.modules.impl.combat.AutoTotem;
import dev.client.modules.impl.combat.BowSpammer;
import dev.client.modules.impl.combat.HitBox;
import dev.client.modules.impl.combat.HitSound;
import dev.client.modules.impl.combat.NoFriendDamage;
import dev.client.modules.impl.combat.NoInteract;
import dev.client.modules.impl.combat.ShiftTap;
import dev.client.modules.impl.combat.SyncTps;
import dev.client.modules.impl.combat.TapeMouse;
import dev.client.modules.impl.combat.TriggerBot;
import dev.client.modules.impl.combat.Velocity;
import dev.client.modules.impl.movement.AirStuck;
import dev.client.modules.impl.movement.Blink;
import dev.client.modules.impl.movement.ElytraBooster;
import dev.client.modules.impl.movement.ElytraBounce;
import dev.client.modules.impl.movement.ElytraMotion;
import dev.client.modules.impl.movement.InventoryMove;
import dev.client.modules.impl.movement.NoJumpDelay;
import dev.client.modules.impl.movement.NoSlow;
import dev.client.modules.impl.movement.NoWeb;
import dev.client.modules.impl.movement.Parkour;
import dev.client.modules.impl.movement.SafeWalk;
import dev.client.modules.impl.movement.Speed;
import dev.client.modules.impl.movement.Sprint;
import dev.client.modules.impl.movement.Strafe;
import dev.client.modules.impl.movement.TargetStrafe;
import dev.client.modules.impl.player.AntiAfk;
import dev.client.modules.impl.player.AutoRespawn;
import dev.client.modules.impl.player.FastBreak;
import dev.client.modules.impl.player.FastUse;
import dev.client.modules.impl.player.FreeCam;
import dev.client.modules.impl.player.GodMode;
import dev.client.modules.impl.player.ItemScroller;
import dev.client.modules.impl.player.ItemSwapFix;
import dev.client.modules.impl.player.NameProtect;
import dev.client.modules.impl.player.NoEntityTrace;
import dev.client.modules.impl.player.NoPush;
import dev.client.modules.impl.player.OpenWall;
import dev.client.modules.impl.player.PearlBlockThrow;
import dev.client.modules.impl.render.Arrows;
import dev.client.modules.impl.render.BetterWorld;
import dev.client.modules.impl.render.BlockEsp;
import dev.client.modules.impl.render.BlockOutline;
import dev.client.modules.impl.render.ChinaHat;
import dev.client.modules.impl.render.Crosshair;
import dev.client.modules.impl.render.CustomModel;
import dev.client.modules.impl.render.GlyphLines;
import dev.client.modules.impl.render.GuiModule;
import dev.client.modules.impl.render.HitEffect;
import dev.client.modules.impl.render.HpAlert;
import dev.client.modules.impl.render.Interface;
import dev.client.modules.impl.render.ItemEsp;
import dev.client.modules.impl.render.ItemPhysics;
import dev.client.modules.impl.render.JumpCircle;
import dev.client.modules.impl.render.KillEffect;
import dev.client.modules.impl.render.Particles;
import dev.client.modules.impl.render.PlayerEsp;
import dev.client.modules.impl.render.Prediction;
import dev.client.modules.impl.render.SeeInvisible;
import dev.client.modules.impl.render.ShaderHand;
import dev.client.modules.impl.render.SmoothCamera;
import dev.client.modules.impl.render.SwingAnimation;
import dev.client.modules.impl.render.Wings;
import dev.client.modules.impl.util.AHHelper;
import dev.client.modules.impl.util.AutoDuel;
import dev.client.modules.impl.util.AutoLeave;
import dev.client.modules.impl.util.AutoTool;
import dev.client.modules.impl.util.AutoTpAccept;
import dev.client.modules.impl.util.BetterChat;
import dev.client.modules.impl.util.ChestStealer;
import dev.client.modules.impl.util.ClanUpgrader;
import dev.client.modules.impl.util.ClickFriend;
import dev.client.modules.impl.util.ClickPearl;
import dev.client.modules.impl.util.ClientSound;
import dev.client.modules.impl.util.CordDropper;
import dev.client.modules.impl.util.ElytraFix;
import dev.client.modules.impl.util.ElytraHelper;
import dev.client.modules.impl.util.FakePlayer;
import dev.client.modules.impl.util.LockSlot;
import dev.client.modules.impl.util.NoItemBreak;
import dev.client.modules.impl.util.NoRender;
import dev.client.modules.impl.util.NoRotation;
import dev.client.modules.impl.util.ReallyWorldHelper;
import dev.client.modules.impl.util.RpSpoof;
import dev.client.modules.impl.util.WebTrap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class ModuleManager {
   private final List<Module> modules = new ArrayList();
   private final HashMap<Class, Module> modules2 = new HashMap();

   public ModuleManager() {
      this.init();
   }

   @Compile
   private void init() {
      this.addModules(new Sprint(), new GuiModule(), new Interface(), new BetterWorld(), new RpSpoof(), new TriggerBot(), new InventoryMove(), new AutoTpAccept(), new SeeInvisible(), new NoRender(), new AutoTool(), new ItemSwapFix(), new ElytraBounce(), new SafeWalk(), new JumpCircle(), new Particles(), new PlayerEsp(), new SwingAnimation(), new Wings(), new NameProtect(), new NoFriendDamage(), new BowSpammer(), new AntiAfk(), new BlockOutline(), new ItemPhysics(), new TapeMouse(), new Prediction(), new FastBreak(), new NoEntityTrace(), new ShiftTap(), new AntiBot(), new Velocity(), new HitBox(), new NoInteract(), new NoWeb(), new AirStuck(), new NoSlow(), new ClickPearl(), new AutoRespawn(), new AutoLeave(), new ChestStealer(), new Crosshair(), new HitEffect(), new ChinaHat(), new KillEffect(), new GlyphLines(), new ShaderHand(), new SmoothCamera(), new HpAlert(), new Arrows(), new ClickFriend(), new Blink(), new ItemScroller(), new HitSound(), new FreeCam(), new NoPush(), new BlockEsp(), new ItemEsp(), new AutoTotem(), new Aura(), new ElytraHelper(), new AutoApple(), new FakePlayer(), new ClientSound(), new NoJumpDelay(), new AutoPotion(), new OpenWall(), new NoItemBreak(), new Speed(), new Strafe(), new ElytraBooster(), new ElytraMotion(), new TargetStrafe(), new CordDropper(), new Parkour(), new NoRotation(), new FastUse(), new AutoSwap(), new AutoDuel(), new AutoExplosion(), new SyncTps(), new BetterChat(), new WebTrap(), new LockSlot(), new ReallyWorldHelper(), new ElytraFix(), new GodMode(), new PearlBlockThrow(), new AHHelper(), new ClanUpgrader(), new CustomModel());
      this.modules.forEach((m) -> this.modules2.put(m.getClass(), m));
   }

   @Compile
   private void addModules(Module... modules) {
      this.modules.addAll(Arrays.asList(modules));
   }

   public List<Module> getModules() {
      return this.modules;
   }

   public Module getByClass(Class clasz) {
      return (Module)this.modules2.get(clasz);
   }

   public void initDesc() {
      this.modules.forEach(Module::initDesc);
   }

   public List<Module> getByCategory(Category category) {
      List<Module> modules1 = new ArrayList<>();
      this.modules.stream()
         .filter((module) -> module.getModuleInfo().category() == category)
         .forEach(modules1::add);
      return modules1;
   }

   public List<Module> search(String text) {
      String lower = text.toLowerCase();
      return (List)this.modules.stream().filter((module) -> module.getModuleInfo().name().toLowerCase().contains(lower)).collect(Collectors.toList());
   }
}
