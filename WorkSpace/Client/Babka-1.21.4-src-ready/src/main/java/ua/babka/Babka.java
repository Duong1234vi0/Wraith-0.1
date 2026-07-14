package ua.babka;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.io.File;
import java.util.Objects;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import ua.babka.combat.IdealHitUtils;
import ua.babka.command.CommandDispatcher;
import ua.babka.command.CommandRepository;
import ua.babka.core.ConfigManager;
import ua.babka.core.ProtectionManager;
import ua.babka.core.TPSGetter;
import ua.babka.event.EventKeyInput;
import ua.babka.event.EventTick;
import ua.babka.friend.FriendRepository;
import ua.babka.integration.discord.Button;
import ua.babka.integration.discord.DiscordIPC;
import ua.babka.macro.MacroRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleStorage;
import ua.babka.script.ScriptManager;
import ua.babka.server.ServerManager;
import ua.babka.server.StaffManager;
import ua.babka.ui.component.ComponentManager;
import ua.babka.ui.drag.DragManager;
import ua.babka.util.StartupLogger;

public class Babka
implements ModInitializer {
    private static Babka instance;
    private String lastRpcServer = "";
    private final EventBus eventBus;
    private final ModuleStorage moduleStorage;
    private final ComponentManager componentManager;
    private final DragManager dragManager;
    private final CommandRepository commandRepository;
    private final MacroRepository macroRepository;
    private final ConfigManager configManager;
    private final CommandDispatcher commandDispatcher;
    private final StaffManager staffManager;
    private final ServerManager serverManager;
    private final TPSGetter tpsGetter;
    private final IdealHitUtils idealHitUtils;
    private final ScriptManager scriptManager;
    private final DiscordIPC discordIPC;
    private String lastTitulServer = "";
    private String image;

    public Babka() {
        instance = this;
        this.eventBus = new EventBus();
        this.eventBus.register((Object)this);
        try {
            this.moduleStorage = new ModuleStorage();
            this.componentManager = new ComponentManager();
            this.dragManager = new DragManager();
            this.macroRepository = new MacroRepository();
            this.configManager = new ConfigManager();
            this.staffManager = new StaffManager();
            this.staffManager.load();
            this.commandRepository = new CommandRepository();
            this.commandDispatcher = new CommandDispatcher();
            this.serverManager = new ServerManager();
            this.tpsGetter = new TPSGetter();
            this.idealHitUtils = new IdealHitUtils();
            this.scriptManager = new ScriptManager();
            this.discordIPC = new DiscordIPC();
        }
        catch (Throwable t2) {
            System.err.println("[babka] FATAL: constructor crashed");
            t2.printStackTrace();
            throw new RuntimeException("[babka] constructor failed", t2);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConfigManager.save("autocfg");
            this.getDragManager().saveDraggables();
            this.getMacroRepository().save();
            FriendRepository.save();
            this.staffManager.save();
            this.discordIPC.stop();
        }));
        File dir = new File("BabkaCleint/configs/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            ProtectionManager.init();
        }
        catch (Throwable t3) {
            System.err.println("[babka] ProtectionManager init failed");
            t3.printStackTrace();
        }
    }

    public static Babka getInstance() {
        return instance == null ? new Babka() : instance;
    }

    public void onInitialize() {
        try {
            this.getModuleStorage().injectRegisterModules();
            this.componentManager.init();
            ConfigManager.save("default");
            this.dragManager.load();
            this.macroRepository.load();
            FriendRepository.load();
            ConfigManager.load("autocfg");
        }
        catch (Throwable t2) {
            System.err.println("[babka] FATAL: onInitialize crashed");
            t2.printStackTrace();
            throw new RuntimeException("[babka] onInitialize failed", t2);
        }
        Thread discordThread = new Thread(() -> {
            boolean connected = this.discordIPC.start(1515714282803695707L);
            if (connected) {
                this.discordIPC.setOnReady(() -> this.discordIPC.updateActivity(info -> info.withDetails("Free Build").withState("In menu").withLargeImage("https://s13.gifyu.com/images/bdqoX.gif").withLargeText("Free Build").withButton1(new Button("Discord", "https://discord.gg/aK6MnMDbpD"))));
            }
            long deadline = System.currentTimeMillis() + 3000L;
            while (!this.discordIPC.isDispatch() && System.currentTimeMillis() < deadline) {
                Thread.yield();
            }
            String mcName = MinecraftClient.getInstance().getSession() != null ? MinecraftClient.getInstance().getSession().getUsername() : "unknown";
            StartupLogger.send(mcName, this.discordIPC.getUser());
        }, "discord-ipc");
        discordThread.setDaemon(true);
        discordThread.start();
    }

    @Subscribe
    private void onTick(EventTick event) {
        String currentServer;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null && mc.player != null && mc.getCurrentServerEntry() != null && mc.getCurrentServerEntry().address.contains("bravo")) {
            String currentAddr = mc.getCurrentServerEntry().address;
            if (!currentAddr.equals(this.lastTitulServer)) {
                this.lastTitulServer = currentAddr;
                Objects.requireNonNull(mc.getNetworkHandler()).sendChatMessage("/titul set 785db");
            }
        } else {
            this.lastTitulServer = "";
        }
        if (!this.discordIPC.isConnected() || !this.discordIPC.isDispatch()) {
            return;
        }
        ServerInfo entry = mc.getCurrentServerEntry();
        String string = currentServer = entry != null ? entry.address : "In menu";
        if (!currentServer.equals(this.lastRpcServer)) {
            this.lastRpcServer = currentServer;
            String serverDisplay = currentServer;
            this.discordIPC.updateActivity(info -> info.withState(serverDisplay));
        }
    }

    @Subscribe
    private void onModuleKeyPressed(EventKeyInput event) {
        for (Module module : this.getModuleStorage().getModules()) {
            if (event.getAction() != 1 || MinecraftClient.getInstance().currentScreen != null || module.getKey() != event.getKey()) continue;
            module.toggle();
        }
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public ModuleStorage getModuleStorage() {
        return this.moduleStorage;
    }

    public ComponentManager getComponentManager() {
        return this.componentManager;
    }

    public DragManager getDragManager() {
        return this.dragManager;
    }

    public CommandRepository getCommandRepository() {
        return this.commandRepository;
    }

    public MacroRepository getMacroRepository() {
        return this.macroRepository;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public CommandDispatcher getCommandDispatcher() {
        return this.commandDispatcher;
    }

    public StaffManager getStaffManager() {
        return this.staffManager;
    }

    public ServerManager getServerManager() {
        return this.serverManager;
    }

    public TPSGetter getTpsGetter() {
        return this.tpsGetter;
    }

    public IdealHitUtils getIdealHitUtils() {
        return this.idealHitUtils;
    }

    public ScriptManager getScriptManager() {
        return this.scriptManager;
    }

    public DiscordIPC getDiscordIPC() {
        return this.discordIPC;
    }
}

