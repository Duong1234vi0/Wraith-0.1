/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 */
package code.alphadlc.features.module;

import code.alphadlc.features.impl.combat.AimAssistModule;
import code.alphadlc.features.impl.combat.AntiBot;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.AutoArmor;
import code.alphadlc.features.impl.combat.AutoDodge;
import code.alphadlc.features.impl.combat.AutoExplosion;
import code.alphadlc.features.impl.combat.AutoPotion;
import code.alphadlc.features.impl.combat.AutoSwap;
import code.alphadlc.features.impl.combat.AutoTotem;
import code.alphadlc.features.impl.combat.CrystalTap;
import code.alphadlc.features.impl.combat.ElytraResolver;
import code.alphadlc.features.impl.combat.FastBow;
import code.alphadlc.features.impl.combat.HitBox;
import code.alphadlc.features.impl.combat.NoFriendDamage;
import code.alphadlc.features.impl.combat.NoInteract;
import code.alphadlc.features.impl.combat.PacketCriticals;
import code.alphadlc.features.impl.combat.ProjectHelper;
import code.alphadlc.features.impl.combat.ShiftTap;
import code.alphadlc.features.impl.combat.TriggerBot;
import code.alphadlc.features.impl.combat.Velocity;
import code.alphadlc.features.impl.misc.AutoAuth;
import code.alphadlc.features.impl.misc.AutoBrew;
import code.alphadlc.features.impl.misc.AutoDuel;
import code.alphadlc.features.impl.misc.AutoLeave;
import code.alphadlc.features.impl.misc.AutoMineBot;
import code.alphadlc.features.impl.misc.AutoTpAccept;
import code.alphadlc.features.impl.misc.AutoWarden;
import code.alphadlc.features.impl.misc.ChestStealer;
import code.alphadlc.features.impl.misc.ClickFriend;
import code.alphadlc.features.impl.misc.ClientSounds;
import code.alphadlc.features.impl.misc.DeathCoords;
import code.alphadlc.features.impl.misc.ElytraHelper;
import code.alphadlc.features.impl.misc.FreeCam;
import code.alphadlc.features.impl.misc.Globals;
import code.alphadlc.features.impl.misc.ItemScroller;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.features.impl.misc.ServerHelper;
import code.alphadlc.features.impl.misc.ServerJoiner;
import code.alphadlc.features.impl.misc.ServerRPSpoofer;
import code.alphadlc.features.impl.misc.ShulkerBypass;
import code.alphadlc.features.impl.misc.SoundsRemove;
import code.alphadlc.features.impl.misc.TapeMouse;
import code.alphadlc.features.impl.misc.UseTracker;
import code.alphadlc.features.impl.misc.XCarry;
import code.alphadlc.features.impl.misc.astrummc.AstrummcHelper;
import code.alphadlc.features.impl.movement.AirStuck;
import code.alphadlc.features.impl.movement.AutoSprint;
import code.alphadlc.features.impl.movement.Blink;
import code.alphadlc.features.impl.movement.DragonFly;
import code.alphadlc.features.impl.movement.ElytraJump;
import code.alphadlc.features.impl.movement.ElytraMotion;
import code.alphadlc.features.impl.movement.ElytraRecast;
import code.alphadlc.features.impl.movement.ElytraTarget;
import code.alphadlc.features.impl.movement.ElytraWhatsapp;
import code.alphadlc.features.impl.movement.Flight;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.features.impl.movement.HighJump;
import code.alphadlc.features.impl.movement.NoClip;
import code.alphadlc.features.impl.movement.NoSlow;
import code.alphadlc.features.impl.movement.NoWeb;
import code.alphadlc.features.impl.movement.Speed;
import code.alphadlc.features.impl.movement.Spider;
import code.alphadlc.features.impl.movement.Strafe;
import code.alphadlc.features.impl.movement.SuperFireWork;
import code.alphadlc.features.impl.movement.TargetStrafe;
import code.alphadlc.features.impl.movement.WindHop;
import code.alphadlc.features.impl.player.AntiAFK;
import code.alphadlc.features.impl.player.AutoRespawn;
import code.alphadlc.features.impl.player.AutoTool;
import code.alphadlc.features.impl.player.AutoUse;
import code.alphadlc.features.impl.player.ClickPearl;
import code.alphadlc.features.impl.player.EnderChestPlus;
import code.alphadlc.features.impl.player.FakePing;
import code.alphadlc.features.impl.player.FastBreak;
import code.alphadlc.features.impl.player.FreeLook;
import code.alphadlc.features.impl.player.LevitationControl;
import code.alphadlc.features.impl.player.NameProtect;
import code.alphadlc.features.impl.player.NoDelay;
import code.alphadlc.features.impl.player.NoFall;
import code.alphadlc.features.impl.player.NoPush;
import code.alphadlc.features.impl.player.SafeWalk;
import code.alphadlc.features.impl.player.autoMine.receivers.AutoMineModule;
import code.alphadlc.features.impl.render.Arrows;
import code.alphadlc.features.impl.render.AspectRatio;
import code.alphadlc.features.impl.render.AuctionHelper;
import code.alphadlc.features.impl.render.BlockESP;
import code.alphadlc.features.impl.render.BlockOverlay;
import code.alphadlc.features.impl.render.CameraSettings;
import code.alphadlc.features.impl.render.Chams;
import code.alphadlc.features.impl.render.ChinaHat;
import code.alphadlc.features.impl.render.ClickGui;
import code.alphadlc.features.impl.render.CrossHair;
import code.alphadlc.features.impl.render.CustomCape;
import code.alphadlc.features.impl.render.CustomModelModule;
import code.alphadlc.features.impl.render.Esp;
import code.alphadlc.features.impl.render.EspSkeleton;
import code.alphadlc.features.impl.render.FireFly;
import code.alphadlc.features.impl.render.Fullbright;
import code.alphadlc.features.impl.render.HandShader;
import code.alphadlc.features.impl.render.HitMarker;
import code.alphadlc.features.impl.render.HitRange;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.impl.render.ItemReplacer;
import code.alphadlc.features.impl.render.KillEffect;
import code.alphadlc.features.impl.render.LineGlyphs;
import code.alphadlc.features.impl.render.NameTags;
import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.features.impl.render.Particles;
import code.alphadlc.features.impl.render.Prediction;
import code.alphadlc.features.impl.render.SeeInvisible;
import code.alphadlc.features.impl.render.ShaderFog;
import code.alphadlc.features.impl.render.ShulkerView;
import code.alphadlc.features.impl.render.SmoothCamera;
import code.alphadlc.features.impl.render.SwingAnimations;
import code.alphadlc.features.impl.render.TargetESP;
import code.alphadlc.features.impl.render.Trails;
import code.alphadlc.features.impl.render.UseIndicator;
import code.alphadlc.features.impl.render.ViewModel;
import code.alphadlc.features.impl.render.WardenHelper;
import code.alphadlc.features.impl.render.WorldTweaks;
import code.alphadlc.features.impl.render.XRay;
import code.alphadlc.features.impl.render.jumpCircle.receiver.JumpCircleModule;
import code.alphadlc.features.impl.render.wings.Wings;
import code.alphadlc.features.module.Module;
import code.alphadlc.utils.client.managers.event.EventManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;

public class ModuleRepository {
    private final List<Module> modules = new ArrayList<Module>();

    public void setup() {
        this.register(new Particles(), new ItemReplacer(), new TargetStrafe(), new Strafe(), new AirStuck(), new ElytraMotion(), new ElytraRecast(), new ElytraJump(), new ElytraTarget(), new ElytraWhatsapp(), new Flight(), new ElytraResolver(), new HighJump(), new ShiftTap(), new AspectRatio(), new FreeLook(), new ClickPearl(), new ClickFriend(), new Globals(), new WindHop(), new TargetESP(), new NoWeb(), new ServerHelper(), new AstrummcHelper(), new AutoMineModule(), new AutoMineBot(), new AutoWarden(), new ItemScroller(), new Hud(), new ClickGui(), new AuctionHelper(), new WardenHelper(), new Prediction(), new XRay(), new AttackAura(), new AimAssistModule(), new ProjectHelper(), new HitBox(), new TriggerBot(), new AutoSwap(), new NoFriendDamage(), new AntiBot(), new AutoExplosion(), new CrystalTap(), new FastBow(), new AutoSprint(), new NoPush(), new ElytraHelper(), new ServerJoiner(), new NoDelay(), new Velocity(), new AutoRespawn(), new NoSlow(), new GuiMove(), new Blink(), new AutoTool(), new FastBreak(), new CameraSettings(), new SmoothCamera(), new Speed(), new DragonFly(), new SwingAnimations(), new ViewModel(), new Chams(), new Esp(), new EspSkeleton(), new NameTags(), new ShulkerView(), new BlockESP(), new AutoTotem(), new EnderChestPlus(), new FreeCam(), new ChestStealer(), new AutoBrew(), new AutoTpAccept(), new AutoAuth(), new Arrows(), new AutoLeave(), new NoClip(), new NoRender(), new Fullbright(), new NameProtect(), new SeeInvisible(), new AutoArmor(), new AutoUse(), new AutoPotion(), new NoInteract(), new CrossHair(), new HitMarker(), new HitRange(), new SuperFireWork(), new Spider(), new SafeWalk(), new ServerRPSpoofer(), new NoFall(), new DeathCoords(), new XCarry(), new AntiAFK(), new FakePing(), new LevitationControl(), new KillEffect(), new FireFly(), new LineGlyphs(), new UseTracker(), new ShulkerBypass(), new ChinaHat(), new PacketCriticals(), new JumpCircleModule(), new AutoDodge(), new ClientSounds(), new SoundsRemove(), new Trails(), new CustomModelModule(), new CustomCape(), new UseIndicator(), new TapeMouse(), new AutoDuel(), new Panic(), new Wings(), new WorldTweaks(), new ShaderFog(), new BlockOverlay(), new HandShader());
        this.sanitizeBrokenModules();
    }

    private void sanitizeBrokenModules() {
        this.modules.removeIf(module -> {
            if (EventManager.canInspectEventHandlers(module)) {
                return false;
            }
            LogManager.getLogger((String)"AlphaDLC").warn("Removing broken module {} from registry", (Object)module.getClass().getName());
            if (module.isState()) {
                module.setState(false);
            }
            return true;
        });
    }

    public void register(Module ... module) {
        this.modules.addAll(List.of(module));
    }

    public List<Module> modules() {
        return this.modules;
    }
}
