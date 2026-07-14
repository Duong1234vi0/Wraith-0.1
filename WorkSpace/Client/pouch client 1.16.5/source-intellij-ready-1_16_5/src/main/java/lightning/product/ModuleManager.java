/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.Skeleton;
import lightning.product.A_4115_X;
import lightning.product.AutoBuy;
import lightning.product.OpenWalls;
import lightning.product.TrashTalk;
import lightning.product.ChorusExploit;
import lightning.product.AnomalyESP;
import lightning.product.Step;
import lightning.product.AutoAnchor;
import lightning.product.TapeMouse;
import lightning.product.D_1410_T;
import lightning.product.AutoTool;
import lightning.product.VoiceChat;
import lightning.product.Arrows;
import lightning.product.AhHelper;
import lightning.product.AntiBot;
import lightning.product.ElytraHelper;
import lightning.product.Party;
import lightning.product.ClickPearl;
import lightning.product.ObjectInfo;
import lightning.product.Nuker;
import lightning.product.PotionTracker;
import lightning.product.ScoreboardHealth;
import lightning.product.AutoSwap;
import lightning.product.F_3698_k;
import lightning.product.NoInteract;
import lightning.product.G_624_v;
import lightning.product.Removals;
import lightning.product.AutoPilot;
import lightning.product.AutoEat;
import lightning.product.AirStuck;
import lightning.product.BlockESP;
import lightning.product.AutoDupe;
import lightning.product.ItemsCooldown;
import lightning.product.SuperFirework;
import lightning.product.AntiAFK;
import lightning.product.InvManager;
import lightning.product.Jesus;
import lightning.product.Prediction;
import lightning.product.ClickFriend;
import lightning.product.LeaveTracker;
import lightning.product.Timer;
import lightning.product.AutoJump;
import lightning.product.Spammer;
import lightning.product.ItemPhysics;
import lightning.product.Flight;
import lightning.product.NoJumpDelay;
import lightning.product.InventoryPlus;
import lightning.product.Phase;
import lightning.product.BlockFly;
import lightning.product.Velocity;
import lightning.product.PearlLogger;
import lightning.product.EntityESP;
import lightning.product.TargetStrafe;
import lightning.product.AimAssist;
import lightning.product.TotemPop;
import lightning.product.ItemRelease;
import lightning.product.AutoTrade;
import lightning.product.NoPush;
import lightning.product.Interface;
import lightning.product.ArmorDurability;
import lightning.product.TargetPearl;
import lightning.product.WorldParticles;
import lightning.product.ToggleSounds;
import lightning.product.FlagDetector;
import lightning.product.Blink;
import lightning.product.HitEffect;
import lightning.product.AutoExplosion;
import lightning.product.CrystalOptimizer;
import lightning.product.Trails;
import lightning.product.NoEntityTrace;
import lightning.product.U_3758_B;
import lightning.product.BotAutoCollector;
import lightning.product.MoveHelper;
import lightning.product.V_983_n;
import lightning.product.HighJump;
import lightning.product.AutoTrap;
import lightning.product.Tracers;
import lightning.product.ItemScroller;
import lightning.product.AutoCrystal;
import lightning.product.DistantAlpha;
import lightning.product.Module;
import lightning.product.X_812_G;
import lightning.product.Particles;
import lightning.product.Y_1740_V;
import lightning.product.SeeInvisibles;
import lightning.product.ItemRadius;
import lightning.product.GuiMove;
import lightning.product.ClientSpoof;
import lightning.product.Bots;
import lightning.product.BaritoneSettings;
import lightning.product.MinecraftAccess;
import lightning.product.Emotions;
import lightning.product.AutoLeave;
import lightning.product.ElytraJump;
import lightning.product.BetterMinecraft;
import lightning.product.ElytraMotion;
import lightning.product.AutoFarm;
import lightning.product.Surround;
import lightning.product.FullBright;
import lightning.product.c_892_d;
import lightning.product.Glint;
import lightning.product.LogoutSpots;
import lightning.product.ChestStealer;
import lightning.product.BlockOverlay;
import lightning.product.FreeCam;
import lightning.product.CrystalESP;
import lightning.product.f_2247_K;
import lightning.product.WaterSpeed;
import lightning.product.ElytraResolver;
import lightning.product.ThirdPerson;
import lightning.product.Crosshair;
import lightning.product.Setting;
import lightning.product.i_4434_b;
import lightning.product.NoWeb;
import lightning.product.AutoAccept;
import lightning.product.TriggerBot;
import lightning.product.ItemHelper;
import lightning.product.Sprint;
import lightning.product.TPLoot;
import lightning.product.Ambience;
import lightning.product.NameProtect;
import lightning.product.AutoFish;
import lightning.product.ViewModel;
import lightning.product.UseTracker;
import lightning.product.Trajectory;
import lightning.product.AutoArmor;
import lightning.product.ServerHelper;
import lightning.product.ClientBootstrap;
import lightning.product.ExtendedTab;
import lightning.product.BooleanSetting;
import lightning.product.LevitationControl;
import lightning.product.LockSlot;
import lightning.product.p_863_D;
import lightning.product.Tags;
import lightning.product.JumpCircle;
import lightning.product.Strafe;
import lightning.product.ShulkerPreview;
import lightning.product.PacketCriticals;
import lightning.product.AutoPotion;
import lightning.product.EcSaver;
import lightning.product.AutoContract;
import lightning.product.AttackAura;
import lightning.product.r_4217_P;
import lightning.product.AutoRespawn;
import lightning.product.SRPSpoof;
import lightning.product.Spider;
import lightning.product.s_4054_j;
import lightning.product.s_4447_V;
import lightning.product.BoatNoClip;
import lightning.product.t_1595_x;
import lightning.product.AutoJoiner;
import lightning.product.SantaHat;
import lightning.product.RegionExploit;
import lightning.product.NoSlow;
import lightning.product.Chams;
import lightning.product.AntiSurround;
import lightning.product.Cosmetics;
import lightning.product.FireworkESP;
import lightning.product.Speed;
import lightning.product.KillEffect;
import lightning.product.DeathCoords;
import lightning.product.w_2099_r;
import lightning.product.w_2223_C;
import lightning.product.Particular;
import lightning.product.FastPlace;
import lightning.product.NoFriendDamage;
import lightning.product.AirJump;
import lightning.product.x_555_z;
import lightning.product.HoleFill;
import lightning.product.AutoLes;
import lightning.product.AutoTotem;
import lightning.product.FastBreak;
import lightning.product.NoFall;
import lightning.product.y_4642_Y;
import lightning.product.AspectRatio;
import lightning.product.AutoSoup;
import lightning.product.AutoDuel;
import lightning.product.NoServerDesync;
import lombok.Generated;

public class ModuleManager
implements MinecraftAccess {
    private final List<Module> h_1847_R = new CopyOnWriteArrayList<Module>();
    public AttackAura n_1700_B;
    public TriggerBot J_1907_R;
    public t_1595_x R_4764_Y;
    public ViewModel G_564_y;
    public Removals P_1922_E;
    public AspectRatio u_1723_Y;
    public Ambience v_4262_N;
    public F_3698_k w_1484_f;
    public SuperFirework t_148_a;
    public ElytraResolver s_956_w;
    public OpenWalls u_2550_I;
    public Flight M_588_G;
    public GuiMove P_4830_p;

    @w_2223_C
    public void n_1700_B() {
        if (G_624_v.t_148_a.J_1907_R()) {
            this.n_1700_B = new AttackAura();
            this.n_1700_B(this.n_1700_B);
            this.n_1700_B(new TargetPearl());
            this.n_1700_B(new AutoTotem());
            this.n_1700_B(new AntiBot());
            this.n_1700_B(new AutoExplosion());
            this.n_1700_B(new NoFriendDamage());
            this.n_1700_B(new AutoSwap());
            this.n_1700_B(new NoEntityTrace());
            this.n_1700_B(new s_4054_j());
            this.n_1700_B(new NoServerDesync());
            this.n_1700_B(new r_4217_P());
            this.n_1700_B(new Velocity());
            this.n_1700_B(new s_4447_V());
            this.n_1700_B(new AimAssist());
            this.n_1700_B(new PacketCriticals());
            this.n_1700_B(new AutoTrap());
            this.J_1907_R = new TriggerBot();
            this.n_1700_B(this.J_1907_R);
            this.n_1700_B(new AutoCrystal());
            this.n_1700_B(new AutoAnchor());
            this.n_1700_B(new AntiSurround());
            this.n_1700_B(new Surround());
            this.n_1700_B(new HoleFill());
            this.n_1700_B(new Sprint());
            this.n_1700_B(new NoSlow());
            this.n_1700_B(new AutoJump());
            this.n_1700_B(new FreeCam());
            this.n_1700_B(new AirStuck());
            this.n_1700_B(new AirJump());
            this.n_1700_B(new Speed());
            this.n_1700_B(new Jesus());
            this.n_1700_B(new WaterSpeed());
            this.n_1700_B(new NoJumpDelay());
            this.n_1700_B(new Step());
            this.n_1700_B(new NoPush());
            this.P_4830_p = new GuiMove();
            this.n_1700_B(this.P_4830_p);
            this.n_1700_B(new Timer());
            this.n_1700_B(new Strafe());
            this.n_1700_B(new MoveHelper());
            this.n_1700_B(new ElytraJump());
            this.n_1700_B(new NoWeb());
            this.M_588_G = new Flight();
            this.n_1700_B(this.M_588_G);
            this.w_1484_f = new F_3698_k();
            this.n_1700_B(this.w_1484_f);
            this.t_148_a = new SuperFirework();
            this.n_1700_B(this.t_148_a);
            this.s_956_w = new ElytraResolver();
            this.n_1700_B(this.s_956_w);
            this.n_1700_B(new ElytraMotion());
            this.n_1700_B(new HighJump());
            this.n_1700_B(new Phase());
            this.n_1700_B(new Blink());
            this.n_1700_B(new TargetStrafe());
            this.n_1700_B(new BoatNoClip());
            this.n_1700_B(new NoFall());
            this.n_1700_B(new c_892_d());
            this.n_1700_B(new Spider());
            this.n_1700_B(new BlockFly());
            this.n_1700_B(new ClickPearl());
            this.n_1700_B(new AutoEat());
            this.n_1700_B(new AutoSoup());
            this.n_1700_B(new AutoPilot());
            this.n_1700_B(new AutoFish());
            this.n_1700_B(new AutoFarm());
            this.n_1700_B(new AutoRespawn());
            this.n_1700_B(new AntiAFK());
            this.n_1700_B(new ItemsCooldown());
            this.n_1700_B(new ItemScroller());
            this.n_1700_B(new AutoPotion());
            this.n_1700_B(new AutoTool());
            this.n_1700_B(new LockSlot());
            this.n_1700_B(new FastBreak());
            this.n_1700_B(new AutoArmor());
            this.n_1700_B(new AutoBuy());
            this.n_1700_B(new AutoLeave());
            this.n_1700_B(new AutoLes());
            this.n_1700_B(new Nuker());
            this.n_1700_B(new AutoJoiner());
            this.n_1700_B(new ChestStealer());
            this.n_1700_B(new NoInteract());
            this.n_1700_B(new ItemRelease());
            this.n_1700_B(new FastPlace());
            this.n_1700_B(new PearlLogger());
            this.n_1700_B(new InvManager());
            this.n_1700_B(new ChorusExploit());
            this.n_1700_B(new LevitationControl());
            this.n_1700_B(new RegionExploit());
            this.n_1700_B(new CrystalOptimizer());
            this.n_1700_B(new AutoTrade());
            this.n_1700_B(new AutoDupe());
            this.n_1700_B(new BaritoneSettings());
            this.v_4262_N = new Ambience();
            this.n_1700_B(this.v_4262_N);
            this.G_564_y = new ViewModel();
            this.n_1700_B(this.G_564_y);
            this.R_4764_Y = new t_1595_x();
            this.n_1700_B(this.R_4764_Y);
            this.P_1922_E = new Removals();
            this.n_1700_B(this.P_1922_E);
            this.u_1723_Y = new AspectRatio();
            this.n_1700_B(this.u_1723_Y);
            this.n_1700_B(new ArmorDurability());
            this.n_1700_B(new ExtendedTab());
            this.n_1700_B(new ItemPhysics());
            this.n_1700_B(new SantaHat());
            this.n_1700_B(new ThirdPerson());
            this.n_1700_B(new Prediction());
            this.n_1700_B(new Tags());
            this.n_1700_B(new Arrows());
            this.n_1700_B(new AnomalyESP());
            this.n_1700_B(new Trajectory());
            this.n_1700_B(new BlockOverlay());
            this.n_1700_B(new SRPSpoof());
            this.n_1700_B(new FullBright());
            this.n_1700_B(new Interface());
            this.n_1700_B(new Tracers());
            this.n_1700_B(new Trails());
            this.n_1700_B(new SeeInvisibles());
            this.n_1700_B(new ShulkerPreview());
            this.n_1700_B(new f_2247_K());
            this.n_1700_B(new w_2099_r());
            this.n_1700_B(new EntityESP());
            this.n_1700_B(new CrystalESP());
            this.n_1700_B(new FireworkESP());
            this.n_1700_B(new Skeleton());
            this.n_1700_B(new Crosshair());
            this.n_1700_B(new BetterMinecraft());
            this.n_1700_B(new DistantAlpha());
            this.n_1700_B(new Emotions());
            this.n_1700_B(new Glint());
            this.n_1700_B(new Particular());
            this.n_1700_B(new LogoutSpots());
            this.n_1700_B(new ItemRadius());
            this.n_1700_B(new ElytraHelper());
            this.n_1700_B(new AutoAccept());
            this.n_1700_B(new ItemHelper());
            this.n_1700_B(new ClickFriend());
            this.n_1700_B(new EcSaver());
            this.n_1700_B(new NameProtect());
            this.n_1700_B(new VoiceChat());
            this.n_1700_B(new PotionTracker());
            this.n_1700_B(new Party());
            this.u_2550_I = new OpenWalls();
            this.n_1700_B(this.u_2550_I);
            this.n_1700_B(new UseTracker());
            this.n_1700_B(new LeaveTracker());
            this.n_1700_B(new X_812_G());
            this.n_1700_B(new ToggleSounds());
            this.n_1700_B(new Spammer());
            this.n_1700_B(new ScoreboardHealth());
            this.n_1700_B(new InventoryPlus());
            this.n_1700_B(new DeathCoords());
            this.n_1700_B(new FlagDetector());
            this.n_1700_B(new TapeMouse());
            this.n_1700_B(new AutoContract());
            this.n_1700_B(new AutoDuel());
            this.n_1700_B(new ServerHelper());
            this.n_1700_B(new V_983_n());
            this.n_1700_B(new ClientSpoof());
            this.n_1700_B(new BlockESP());
            this.n_1700_B(new x_555_z());
            this.n_1700_B(new Particles());
            this.n_1700_B(new JumpCircle());
            this.n_1700_B(new WorldParticles());
            this.n_1700_B(new Chams());
            this.n_1700_B(new TotemPop());
            this.n_1700_B(new KillEffect());
            this.n_1700_B(new HitEffect());
            this.n_1700_B(new Cosmetics());
            this.n_1700_B(new ObjectInfo());
            this.n_1700_B(new AhHelper());
            this.n_1700_B(new TrashTalk());
            this.n_1700_B(new Bots());
            this.n_1700_B(new BotAutoCollector());
            this.n_1700_B(new TPLoot());
            this.h_1847_R.sort(Comparator.comparing(Module::G_564_y));
            A_4115_X.n_1700_B(this);
        }
    }

    private void n_1700_B(Module module) {
        this.h_1847_R.add(module);
    }

    public Module n_1700_B(Class<? extends Module> classModule) {
        for (Module module : this.h_1847_R) {
            if (module == null || module.getClass() != classModule) continue;
            return module;
        }
        return null;
    }

    public Optional<Module> n_1700_B(String name) {
        return this.h_1847_R.stream().filter(module -> module.G_564_y().equalsIgnoreCase(name)).findFirst();
    }

    @Y_1740_V
    private void n_1700_B(i_4434_b e) {
        if (y_4642_Y.R_4764_Y()) {
            return;
        }
        if (e.n_1700_B() == 344) {
            c_3005_b.n_1700_B(ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider());
        }
        for (Module module : this.h_1847_R) {
            if (module.v_4262_N() == e.n_1700_B() && (module.t_148_a() == Module.n_1700_B.n_1700_B && !e.J_1907_R() || module.t_148_a() == Module.n_1700_B.J_1907_R)) {
                module.R_4764_Y();
            }
            for (Setting<?> setting : module.u_2550_I()) {
                boolean isToggleMode;
                BooleanSetting booleanSetting;
                if (!(setting instanceof BooleanSetting) || (booleanSetting = (BooleanSetting)setting).u_2550_I() != e.n_1700_B()) continue;
                boolean bl = isToggleMode = booleanSetting.M_588_G() == Module.n_1700_B.n_1700_B;
                if (isToggleMode && e.J_1907_R()) continue;
                boolean newValue = booleanSetting.t_148_a() == false;
                booleanSetting.n_1700_B((Boolean)newValue);
                if (Module.P_4830_p() || !booleanSetting.P_4830_p()) continue;
                p_863_D.n_1700_B(ToggleSounds.P_1922_E(newValue));
                if (!D_1410_T.G_564_y.t_148_a().booleanValue()) continue;
                U_3758_B.n_1700_B(newValue ? "J" : "K", "\u00ab" + booleanSetting.n_1700_B() + "\u00bb" + (newValue ? " \u0432\u043a\u043b\u044e\u0447\u0435\u043d\u043e!" : " \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e!"), -1);
            }
        }
    }

    public AttackAura J_1907_R() {
        return this.n_1700_B;
    }

    public F_3698_k R_4764_Y() {
        return this.w_1484_f;
    }

    public SuperFirework G_564_y() {
        return this.t_148_a;
    }

    public ElytraResolver P_1922_E() {
        return this.s_956_w;
    }

    @Generated
    public List<Module> u_1723_Y() {
        return this.h_1847_R;
    }

    @Generated
    public TriggerBot v_4262_N() {
        return this.J_1907_R;
    }

    @Generated
    public t_1595_x w_1484_f() {
        return this.R_4764_Y;
    }

    @Generated
    public ViewModel t_148_a() {
        return this.G_564_y;
    }

    @Generated
    public Removals s_956_w() {
        return this.P_1922_E;
    }

    @Generated
    public AspectRatio u_2550_I() {
        return this.u_1723_Y;
    }

    @Generated
    public Ambience M_588_G() {
        return this.v_4262_N;
    }

    @Generated
    public OpenWalls P_4830_p() {
        return this.u_2550_I;
    }

    @Generated
    public Flight h_1847_R() {
        return this.M_588_G;
    }

    @Generated
    public GuiMove Q_4569_t() {
        return this.P_4830_p;
    }
}



