package ua.babka.module;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.option.Perspective;
import net.minecraft.util.math.MathHelper;
import ua.babka.Babka;
import ua.babka.core.Rotation;
import ua.babka.event.EventGameUpdate;
import ua.babka.event.EventHUD;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventTick;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.impl.combat.AntiBot;
import ua.babka.module.impl.combat.AntiThorns;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.impl.combat.AutoExplosion;
import ua.babka.module.impl.combat.AutoPotion;
import ua.babka.module.impl.combat.AutoSwap;
import ua.babka.module.impl.combat.AutoTotem;
import ua.babka.module.impl.combat.MaceTarget;
import ua.babka.module.impl.combat.NoEntityTrace;
import ua.babka.module.impl.combat.NoFriendDamage;
import ua.babka.module.impl.combat.PacketCriticals;
import ua.babka.module.impl.combat.TargetPearl;
import ua.babka.module.impl.combat.TriggerBot;
import ua.babka.module.impl.combat.Velocity;
import ua.babka.module.impl.movement.AirStuck;
import ua.babka.module.impl.movement.DragonFly;
import ua.babka.module.impl.movement.ElytraBooster;
import ua.babka.module.impl.movement.ElytraMotion;
import ua.babka.module.impl.movement.ElytraTarget;
import ua.babka.module.impl.movement.GrimGlide;
import ua.babka.module.impl.movement.GuiMove;
import ua.babka.module.impl.movement.NoJumpDelay;
import ua.babka.module.impl.movement.NoSlow;
import ua.babka.module.impl.movement.NoWeb;
import ua.babka.module.impl.movement.PearlClip;
import ua.babka.module.impl.movement.Speed;
import ua.babka.module.impl.movement.Sprint;
import ua.babka.module.impl.movement.Timer;
import ua.babka.module.impl.player.AutoEat;
import ua.babka.module.impl.player.AutoFix;
import ua.babka.module.impl.player.AutoJoin;
import ua.babka.module.impl.player.AutoLeave;
import ua.babka.module.impl.player.AutoMessage;
import ua.babka.module.impl.player.AutoPilot;
import ua.babka.module.impl.player.AutoTool;
import ua.babka.module.impl.player.AutoTpaccept;
import ua.babka.module.impl.player.BaritoneModule;
import ua.babka.module.impl.player.ClickPearl;
import ua.babka.module.impl.player.CordsDropper;
import ua.babka.module.impl.player.DeathCoords;
import ua.babka.module.impl.player.ElytraFakePlayer;
import ua.babka.module.impl.player.ElytraHelper;
import ua.babka.module.impl.player.FakePlayer;
import ua.babka.module.impl.player.FastExp;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.module.impl.player.FuntimeHelper;
import ua.babka.module.impl.player.ItemReplacer;
import ua.babka.module.impl.player.LockSlot;
import ua.babka.module.impl.player.NoPush;
import ua.babka.module.impl.player.RPSpoofer;
import ua.babka.module.impl.player.ScoreboardHealth;
import ua.babka.module.impl.player.ServerHelper;
import ua.babka.module.impl.player.StreamerMode;
import ua.babka.module.impl.player.TapeMouse;
import ua.babka.module.impl.player.TotemLogger;
import ua.babka.module.impl.player.UseTracker;
import ua.babka.module.impl.render.Ambience;
import ua.babka.module.impl.render.AppleEffect;
import ua.babka.module.impl.render.Arrows;
import ua.babka.module.impl.render.BlockOverlay;
import ua.babka.module.impl.render.Cape;
import ua.babka.module.impl.render.Chams;
import ua.babka.module.impl.render.ChinaHat;
import ua.babka.module.impl.render.ChorusRenderer;
import ua.babka.module.impl.render.ClickGui;
import ua.babka.module.impl.render.ClientSounds;
import ua.babka.module.impl.render.Cosmetics;
import ua.babka.module.impl.render.Cubes;
import ua.babka.module.impl.render.CustomModels;
import ua.babka.module.impl.render.FreeLook;
import ua.babka.module.impl.render.FullBright;
import ua.babka.module.impl.render.Ghost;
import ua.babka.module.impl.render.HitBubbles;
import ua.babka.module.impl.render.HitGhost;
import ua.babka.module.impl.render.Interface;
import ua.babka.module.impl.render.InterpolateF5;
import ua.babka.module.impl.render.JumpCircle;
import ua.babka.module.impl.render.KillEffect;
import ua.babka.module.impl.render.KillSound;
import ua.babka.module.impl.render.LineGlyphs;
import ua.babka.module.impl.render.LootTracker;
import ua.babka.module.impl.render.NoRender;
import ua.babka.module.impl.render.NoRenderSphere;
import ua.babka.module.impl.render.Particle;
import ua.babka.module.impl.render.Pets;
import ua.babka.module.impl.render.Predictions;
import ua.babka.module.impl.render.Saturation;
import ua.babka.module.impl.render.ShaderHands;
import ua.babka.module.impl.render.SoulESP;
import ua.babka.module.impl.render.SwingAnimations;
import ua.babka.module.impl.render.Tags;
import ua.babka.module.impl.render.TargetESP;
import ua.babka.module.impl.render.Trails;
import ua.babka.module.impl.render.ViewBobbing;
import ua.babka.module.impl.render.ViewModel;
import ua.babka.module.impl.render.Zoom;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.ThemeSetting;
import ua.babka.ui.component.RotationComponent;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;
import ua.babka.util.animation.SlownessManager;
import ua.babka.util.network.PartyApiClient;

public class ModuleStorage
implements IMinecraft {
    private final List<Module> modules = new ArrayList<Module>();
    private float speedAcceleration;
    private float randomness;

    public void injectRegisterModules() {
        this.modules.addAll(List.of(new FullBright(), new ClickGui(), new Sprint(), new Speed(), new AppleEffect(), new TriggerBot(), new NoRender(), new AttackAura(), new Tags(), new CordsDropper(), new TargetESP(), new NoPush(), new SoulESP(), new Arrows(), new NoJumpDelay(), new DragonFly(), new ElytraHelper(), new AutoTotem(), new ClickPearl(), new UseTracker(), new HitGhost(), new ClientSounds(), new NoFriendDamage(), new ElytraBooster(), new ElytraFakePlayer(), new ItemReplacer(), new ElytraTarget(), new FreeCamera(), new SwingAnimations(), new Predictions(), new TotemLogger(), new AutoTpaccept(), new RPSpoofer(), new AntiBot(), new AutoExplosion(), new DeathCoords(), new AntiThorns(), new PacketCriticals(), new TargetPearl(), new GuiMove(), new ElytraMotion(), new Velocity(), new Pets(), new ViewModel(), new KillEffect(), new AutoTool(), new TapeMouse(), new Ambience(), new BlockOverlay(), new FreeLook(), new Chams(), new Trails(), new FastExp(), new StreamerMode(), new ChinaHat(), new FuntimeHelper(), new AirStuck(), new AutoSwap(), new NoSlow(), new NoWeb(), new FakePlayer(), new Interface(), new AutoEat(), new AutoLeave(), new Cape(), new ServerHelper(), new ScoreboardHealth(), new Ghost(), new GrimGlide(), new PearlClip(), new AutoPotion(), new AutoPilot(), new LockSlot(), new InterpolateF5(), new AutoMessage(), new LineGlyphs(), new HitBubbles(), new JumpCircle(), new Cubes(), new Particle(), new MaceTarget(), new CustomModels(), new Zoom(), new AutoFix(), new Timer(), new NoEntityTrace(), new Cosmetics(), new NoRenderSphere(), new ShaderHands(), new ChorusRenderer(), new ViewBobbing(), new KillSound(), new Saturation(), new AutoJoin(), new LootTracker(), new BaritoneModule()));
        Babka.getInstance().getEventBus().register((Object)this);
    }

    public <T extends Module> T get(String name) {
        String nameNoSpace = name.replace(" ", "");
        return (T)((Module)this.modules.stream().filter(module -> module.getName().equalsIgnoreCase(name) || module.getName().replace(" ", "").equalsIgnoreCase(nameNoSpace)).map(module -> module).findFirst().orElse(null));
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T)((Module)this.modules.stream().filter(module -> clazz.isAssignableFrom(module.getClass())).map(clazz::cast).findFirst().orElse(null));
    }

    public List<Module> get(ModuleCategory category) {
        return this.modules.stream().filter(module -> module.getCategory() == category).collect(Collectors.toList());
    }

    @Subscribe
    private void onGameUpdate(EventGameUpdate e2) {
        AttackAura aura;
        if (ModuleStorage.mc.player == null) {
            return;
        }
        if (!SlownessManager.slowTasksIsEmpty()) {
            SlownessManager.updateSlowTasks();
        }
        if (!SlownessManager.timeTasksIsEmpty()) {
            SlownessManager.updateTimeTasks(false);
        }
        if (!(aura = this.get(AttackAura.class)).isEnabled() || aura.getTarget() == null) {
            if (ModuleStorage.mc.options.getPerspective() == Perspective.THIRD_PERSON_FRONT) {
                aura.lastYaw = ModuleStorage.mc.gameRenderer.getCamera().getYaw() - 180.0f;
                aura.lastPitch = -ModuleStorage.mc.gameRenderer.getCamera().getPitch();
            } else {
                aura.lastYaw = ModuleStorage.mc.gameRenderer.getCamera().getYaw();
                aura.lastPitch = ModuleStorage.mc.gameRenderer.getCamera().getPitch();
            }
            if (aura.rotation.is("Vanilla") || Instance.get(FreeLook.class).isActive()) {
                return;
            }
            this.updateBackwardsOther();
        }
    }

    @Subscribe
    private void onRender(EventHUD ignored) {
        for (Module module : this.getModules()) {
            module.getAnimation().run(module.isEnabled());
            for (Setting setting : module.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    BooleanSetting b2 = (BooleanSetting)setting;
                    b2.getAnimation().run(b2.getValue());
                }
                if (!(setting instanceof ThemeSetting)) continue;
                ThemeSetting t2 = (ThemeSetting)setting;
                t2.getValue().animation.run(1.0f);
            }
        }
    }

    @Subscribe
    private void onKey(EventKeyInput e2) {
        if (e2.getAction() == 0) {
            return;
        }
        for (Module module : this.getModules()) {
            for (Setting setting : module.getSettings()) {
                if (!(setting instanceof BooleanSetting)) continue;
                BooleanSetting b2 = (BooleanSetting)setting;
                if (e2.getKey() != b2.getKey()) continue;
                b2.toggle();
            }
        }
    }

    @Subscribe
    private void onUpdate(EventTick ignored) {
        if (!SlownessManager.timeTasksIsEmpty()) {
            SlownessManager.updateTimeTasks(true);
        }
        if (ModuleStorage.mc.player == null) {
            return;
        }
        PartyApiClient.fetchPartyStateAsync();
        PartyApiClient.fetchInvitesAsync();
        JsonObject j2 = new JsonObject();
        j2.addProperty("player", ModuleStorage.mc.player.getNameForScoreboard());
        j2.addProperty("x", (Number)ModuleStorage.mc.player.getX());
        j2.addProperty("y", (Number)ModuleStorage.mc.player.getY());
        j2.addProperty("z", (Number)ModuleStorage.mc.player.getZ());
        PartyApiClient.postAsync("/party/pos", j2, json -> {});
    }

    private void updateBackwardsOther() {
        this.speedAcceleration = ModuleStorage.mc.player.isGliding() ? (this.speedAcceleration += 0.06f) : (this.speedAcceleration += 0.006f);
        this.get(AttackAura.class).speedAcceleration = 0.0f;
        Rotation angle = new Rotation(ModuleStorage.mc.gameRenderer.getCamera().getYaw(), ModuleStorage.mc.gameRenderer.getCamera().getPitch());
        float deltaYaw = MathHelper.wrapDegrees((float)(angle.getYaw() - ModuleStorage.mc.player.getYaw()));
        float deltaPitch = angle.getPitch() - ModuleStorage.mc.player.getPitch();
        if (ModuleStorage.mc.options.getPerspective() == Perspective.THIRD_PERSON_FRONT) {
            deltaYaw = MathHelper.wrapDegrees((float)(angle.getYaw() - 180.0f - ModuleStorage.mc.player.getYaw()));
            deltaPitch = MathHelper.wrapDegrees((float)(-angle.getPitch() - ModuleStorage.mc.player.getPitch()));
        }
        float smooth = Math.max(this.speedAcceleration, 0.0f);
        float newYaw = ModuleStorage.mc.player.getYaw() + deltaYaw * Math.min(Math.max(smooth, 0.0f), 1.0f);
        float newPitch = ModuleStorage.mc.player.getPitch() + deltaPitch * Math.min(Math.max(smooth / 2.0f, 0.0f), 1.0f);
        Rotation smoothRot = new Rotation(newYaw, newPitch);
        RotationComponent.update(smoothRot, 360.0f, 360.0f, 360.0f, 360.0f, 0, 2, false);
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public float getSpeedAcceleration() {
        return this.speedAcceleration;
    }

    public float getRandomness() {
        return this.randomness;
    }

    public void setSpeedAcceleration(float speedAcceleration) {
        this.speedAcceleration = speedAcceleration;
    }

    public void setRandomness(float randomness) {
        this.randomness = randomness;
    }
}

