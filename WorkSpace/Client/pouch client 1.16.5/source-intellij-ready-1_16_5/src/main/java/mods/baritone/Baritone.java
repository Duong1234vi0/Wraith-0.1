/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lightning.product.MinecraftClient;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.api.api.java.baritone.api.behavior.IBehavior;
import mods.baritone.api.api.java.baritone.api.event.listener.IEventBus;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.behavior.InventoryBehavior;
import mods.baritone.behavior.LookBehavior;
import mods.baritone.behavior.PathingBehavior;
import mods.baritone.behavior.WaypointBehavior;
import mods.baritone.cache.WorldProvider;
import mods.baritone.command.manager.CommandManager;
import mods.baritone.event.GameEventHandler;
import mods.baritone.process.BackfillProcess;
import mods.baritone.process.BuilderProcess;
import mods.baritone.process.CustomGoalProcess;
import mods.baritone.process.ExploreProcess;
import mods.baritone.process.FarmProcess;
import mods.baritone.process.FollowProcess;
import mods.baritone.process.GetToBlockProcess;
import mods.baritone.process.InventoryPauserProcess;
import mods.baritone.process.MineProcess;
import mods.baritone.selection.SelectionManager;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.GuiClick;
import mods.baritone.utils.InputOverrideHandler;
import mods.baritone.utils.PathingControlManager;
import mods.baritone.utils.player.BaritonePlayerContext;

public class Baritone
implements IBaritone {
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    private final MinecraftClient mc;
    private final Path directory;
    private final GameEventHandler gameEventHandler;
    private final PathingBehavior pathingBehavior;
    private final LookBehavior lookBehavior;
    private final InventoryBehavior inventoryBehavior;
    private final InputOverrideHandler inputOverrideHandler;
    private final FollowProcess followProcess;
    private final MineProcess mineProcess;
    private final GetToBlockProcess getToBlockProcess;
    private final CustomGoalProcess customGoalProcess;
    private final BuilderProcess builderProcess;
    private final ExploreProcess exploreProcess;
    private final FarmProcess farmProcess;
    private final InventoryPauserProcess inventoryPauserProcess;
    private final PathingControlManager pathingControlManager;
    private final SelectionManager selectionManager;
    private final CommandManager commandManager;
    private final IPlayerContext playerContext;
    private final WorldProvider worldProvider;
    public BlockStateInterface bsi;

    Baritone(MinecraftClient mc) {
        this.mc = mc;
        this.gameEventHandler = new GameEventHandler(this);
        this.directory = mc.M_182_A.toPath().resolve("baritone");
        if (!Files.exists(this.directory, new LinkOption[0])) {
            try {
                Files.createDirectories(this.directory, new FileAttribute[0]);
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        this.playerContext = new BaritonePlayerContext(this, mc);
        this.lookBehavior = this.registerBehavior(LookBehavior::new);
        this.pathingBehavior = this.registerBehavior(PathingBehavior::new);
        this.inventoryBehavior = this.registerBehavior(InventoryBehavior::new);
        this.inputOverrideHandler = this.registerBehavior(InputOverrideHandler::new);
        this.registerBehavior(WaypointBehavior::new);
        this.pathingControlManager = new PathingControlManager(this);
        this.followProcess = this.registerProcess(FollowProcess::new);
        this.mineProcess = this.registerProcess(MineProcess::new);
        this.customGoalProcess = this.registerProcess(CustomGoalProcess::new);
        this.getToBlockProcess = this.registerProcess(GetToBlockProcess::new);
        this.builderProcess = this.registerProcess(BuilderProcess::new);
        this.exploreProcess = this.registerProcess(ExploreProcess::new);
        this.farmProcess = this.registerProcess(FarmProcess::new);
        this.inventoryPauserProcess = this.registerProcess(InventoryPauserProcess::new);
        this.registerProcess(BackfillProcess::new);
        this.worldProvider = new WorldProvider(this);
        this.selectionManager = new SelectionManager(this);
        this.commandManager = new CommandManager(this);
    }

    public void registerBehavior(IBehavior behavior) {
        this.gameEventHandler.registerEventListener(behavior);
    }

    public <T extends IBehavior> T registerBehavior(Function<Baritone, T> constructor) {
        IBehavior behavior = (IBehavior)constructor.apply(this);
        this.registerBehavior(behavior);
        return (T)behavior;
    }

    public <T extends IBaritoneProcess> T registerProcess(Function<Baritone, T> constructor) {
        IBaritoneProcess behavior = (IBaritoneProcess)constructor.apply(this);
        this.pathingControlManager.registerProcess(behavior);
        return (T)behavior;
    }

    @Override
    public PathingControlManager getPathingControlManager() {
        return this.pathingControlManager;
    }

    @Override
    public InputOverrideHandler getInputOverrideHandler() {
        return this.inputOverrideHandler;
    }

    @Override
    public CustomGoalProcess getCustomGoalProcess() {
        return this.customGoalProcess;
    }

    @Override
    public GetToBlockProcess getGetToBlockProcess() {
        return this.getToBlockProcess;
    }

    @Override
    public IPlayerContext getPlayerContext() {
        return this.playerContext;
    }

    @Override
    public FollowProcess getFollowProcess() {
        return this.followProcess;
    }

    @Override
    public BuilderProcess getBuilderProcess() {
        return this.builderProcess;
    }

    public InventoryBehavior getInventoryBehavior() {
        return this.inventoryBehavior;
    }

    @Override
    public LookBehavior getLookBehavior() {
        return this.lookBehavior;
    }

    @Override
    public ExploreProcess getExploreProcess() {
        return this.exploreProcess;
    }

    @Override
    public MineProcess getMineProcess() {
        return this.mineProcess;
    }

    @Override
    public FarmProcess getFarmProcess() {
        return this.farmProcess;
    }

    public InventoryPauserProcess getInventoryPauserProcess() {
        return this.inventoryPauserProcess;
    }

    @Override
    public PathingBehavior getPathingBehavior() {
        return this.pathingBehavior;
    }

    @Override
    public SelectionManager getSelectionManager() {
        return this.selectionManager;
    }

    @Override
    public WorldProvider getWorldProvider() {
        return this.worldProvider;
    }

    @Override
    public IEventBus getGameEventHandler() {
        return this.gameEventHandler;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public void openClick() {
        new Thread(() -> {
            try {
                Thread.sleep(100L);
                this.mc.execute(() -> this.mc.n_1700_B(new GuiClick()));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }).start();
    }

    public Path getDirectory() {
        return this.directory;
    }

    public static Settings settings() {
        return BaritoneAPI.getSettings();
    }

    public static Executor getExecutor() {
        return threadPool;
    }
}


