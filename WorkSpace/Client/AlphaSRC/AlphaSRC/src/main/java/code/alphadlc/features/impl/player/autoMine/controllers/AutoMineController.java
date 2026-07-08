/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.player.autoMine.controllers;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.misc.creeperFarm.WalkDirectionUtil;
import code.alphadlc.features.impl.player.autoMine.api.MineApiParser;
import code.alphadlc.features.impl.player.autoMine.controllers.MineController;
import code.alphadlc.features.impl.player.autoMine.controllers.RegionController;
import code.alphadlc.features.impl.player.autoMine.controllers.RepairController;
import code.alphadlc.features.impl.player.autoMine.receivers.AutoMineModule;
import code.alphadlc.features.impl.player.autoMine.state.AutoMineState;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.math.task.TaskPriority;
import net.minecraft.class_10185;
import net.minecraft.class_2338;
import net.minecraft.class_243;

public class AutoMineController
implements QuickImports {
    private final RegionController regionController;
    private final MineController mineController;
    private final MineApiParser apiParser;
    private final RepairController repairController;
    private AutoMineState state = AutoMineState.WAITING_FOR_MINE;
    private long stateStartTime = 0L;
    private long lastCommandTime = 0L;
    static final int MINE_TIME_THRESHOLD = 12;
    static final long ANARCHY_LOAD_DELAY = 3000L;
    static final long WARP_DELAY = 7000L;
    static final long COMMAND_COOLDOWN = 1000L;
    static final double PROXIMITY_RADIUS = 7.0;
    private String currentAnarchy = null;
    private boolean connectMessageSent = false;
    private boolean alreadyOnAnarchy = false;
    private int noTargetTicks = 0;
    static final int NO_TARGET_THRESHOLD = 40;

    public AutoMineController() {
        this.regionController = new RegionController(RegionController.createRegion(new class_2338(-33, 76, -7), new class_2338(-54, 85, -28)));
        this.mineController = new MineController(this.regionController);
        this.apiParser = new MineApiParser();
        this.repairController = new RepairController();
    }

    public void onTick() {
        this.apiParser.fetchAsync();
        switch (this.state) {
            case WAITING_FOR_MINE: {
                this.tickWaitingForMine();
                break;
            }
            case JOINING_ANARCHY: {
                this.tickJoiningAnarchy();
                break;
            }
            case WAITING_ANARCHY_LOAD: {
                this.tickWaitingAnarchyLoad();
                break;
            }
            case WARPING_TO_MINE: {
                this.tickWarpingToMine();
                break;
            }
            case WAITING_WARP: {
                this.tickWaitingWarp();
                break;
            }
            case RUNNING_TO_CENTER: {
                this.tickRunningToCenter();
                break;
            }
            case MINING: {
                this.tickMining();
                break;
            }
            case MINE_FINISHED: {
                this.tickMineFinished();
                break;
            }
            case REPAIRING: {
                this.tickRepairing();
            }
        }
    }

    private void tickWaitingForMine() {
        boolean inRegion;
        if (this.apiParser.isMineReady(12)) {
            String anarchyCmd = this.apiParser.getAnarchyCommand();
            if (anarchyCmd != null) {
                if (this.alreadyOnAnarchy) {
                    this.applyAnarchyLoadDone();
                    return;
                }
                if (!this.connectMessageSent) {
                    String mineType = this.apiParser.getMineType() != null ? this.apiParser.getMineType() : "?";
                    int timeSec = this.apiParser.getTimeSeconds();
                    ChatMessage.brandmessage("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435: " + anarchyCmd + " (" + mineType + "), \u0448\u0430\u0445\u0442\u0430 \u0447\u0435\u0440\u0435\u0437 " + timeSec + " \u0441\u0435\u043a");
                    this.connectMessageSent = true;
                }
                this.setState(AutoMineState.JOINING_ANARCHY);
                return;
            }
        } else {
            this.connectMessageSent = false;
        }
        if (inRegion = this.isPlayerInRegion()) {
            this.regionController.onTick();
            if (this.regionController.getTargetBlock() != null) {
                this.setState(AutoMineState.MINING);
            }
        }
    }

    private void tickJoiningAnarchy() {
        String anarchyCmd = this.apiParser.getAnarchyCommand();
        if (anarchyCmd == null) {
            this.setState(AutoMineState.WAITING_FOR_MINE);
            return;
        }
        if (this.canSendCommand()) {
            this.sendCommand(anarchyCmd);
            this.currentAnarchy = anarchyCmd;
            this.setState(AutoMineState.WAITING_ANARCHY_LOAD);
        }
    }

    private void tickWaitingAnarchyLoad() {
        if (this.getStateElapsed() >= 3000L) {
            this.applyAnarchyLoadDone();
        }
    }

    public void notifyAlreadyConnected() {
        this.alreadyOnAnarchy = true;
        if (this.state == AutoMineState.WAITING_ANARCHY_LOAD) {
            this.applyAnarchyLoadDone();
        }
    }

    private void applyAnarchyLoadDone() {
        if (this.isPlayerInRegion()) {
            this.setState(AutoMineState.MINING);
        } else {
            this.setState(AutoMineState.WARPING_TO_MINE);
        }
    }

    private void tickWarpingToMine() {
        if (this.isPlayerInRegion()) {
            this.setState(AutoMineState.MINING);
            return;
        }
        if (this.canSendCommand()) {
            this.sendCommand("/warp mine");
            this.setState(AutoMineState.WAITING_WARP);
        }
    }

    private void tickWaitingWarp() {
        if (this.getStateElapsed() >= 7000L) {
            this.setState(AutoMineState.RUNNING_TO_CENTER);
        }
    }

    private void tickRunningToCenter() {
        class_243 center = this.regionController.getRegionState().getCenterVec();
        double dist = center.method_1020(AutoMineController.mc.field_1724.method_19538()).method_37267();
        if (dist < 11.0) {
            this.setState(AutoMineState.MINING);
        }
        this.regionController.onTick();
    }

    private void tickMining() {
        if (this.repairController.needsRepair()) {
            this.setState(AutoMineState.REPAIRING);
            this.repairController.startRepair();
            return;
        }
        if (!this.isPlayerNearRegion()) {
            this.setState(AutoMineState.WARPING_TO_MINE);
            return;
        }
        this.regionController.onTick();
        this.mineController.onTick();
        this.noTargetTicks = this.regionController.getTargetBlock() == null ? ++this.noTargetTicks : 0;
        if (this.noTargetTicks >= 40) {
            this.setState(AutoMineState.MINE_FINISHED);
        }
    }

    private void tickRepairing() {
        if (this.repairController.tickRepair()) {
            this.repairController.reset();
            this.setState(AutoMineState.MINING);
        }
    }

    private void tickMineFinished() {
        this.noTargetTicks = 0;
        this.currentAnarchy = null;
        this.connectMessageSent = false;
        this.alreadyOnAnarchy = false;
        this.setState(AutoMineState.WAITING_FOR_MINE);
    }

    public void onInput(InputEvent inputEvent) {
        switch (this.state) {
            case RUNNING_TO_CENTER: {
                class_243 center = this.regionController.getRegionState().getCenterVec();
                class_243 direction = center.method_1020(AutoMineController.mc.field_1724.method_19538());
                float yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
                class_10185 pi = WalkDirectionUtil.fromVec3d(direction, yaw);
                inputEvent.setDirectional(pi.comp_3159(), pi.comp_3160(), pi.comp_3161(), pi.comp_3162());
                break;
            }
            case MINING: {
                this.mineController.onInput(inputEvent);
                break;
            }
        }
    }

    public void onRotationUpdate(RotationUpdateEvent event) {
        switch (this.state) {
            case RUNNING_TO_CENTER: {
                class_243 center = this.regionController.getRegionState().getCenterVec();
                Turns turns = MathAngle.calculateAngle(center);
                TurnsConnection.INSTANCE.rotateTo(turns, new TurnsConfig(this.mineController.getSpAngle(), true, false), TaskPriority.HIGH_IMPORTANCE_1, AlphaDLC.getInstance().getModuleProvider().get(AutoMineModule.class));
                break;
            }
            case REPAIRING: {
                break;
            }
            case MINING: {
                this.mineController.onRotationUpdate(event);
            }
        }
    }

    public void onWorldRender(WorldRenderEvent event) {
        this.regionController.onWorldRender(event);
        if (this.state == AutoMineState.MINING) {
            this.mineController.onWorldRender(event);
        }
    }

    public void onDraw(DrawEvent drawEvent) {
        if (this.state == AutoMineState.MINING) {
            this.mineController.onDraw(drawEvent);
        }
    }

    public void onWorldChange() {
        this.regionController.onWorldChange();
        this.mineController.onWorldChange();
        if (this.state == AutoMineState.WAITING_ANARCHY_LOAD) {
            this.stateStartTime = System.currentTimeMillis();
        }
    }

    private void setState(AutoMineState newState) {
        this.state = newState;
        this.stateStartTime = System.currentTimeMillis();
    }

    private long getStateElapsed() {
        return System.currentTimeMillis() - this.stateStartTime;
    }

    private boolean canSendCommand() {
        return System.currentTimeMillis() - this.lastCommandTime >= 1000L;
    }

    private void sendCommand(String command) {
        if (AutoMineController.mc.field_1724 != null && mc.method_1562() != null) {
            String cmd = command.startsWith("/") ? command.substring(1) : command;
            mc.method_1562().method_45731(cmd);
            this.lastCommandTime = System.currentTimeMillis();
        }
    }

    private boolean isPlayerInRegion() {
        if (AutoMineController.mc.field_1724 == null) {
            return false;
        }
        class_243 playerPos = AutoMineController.mc.field_1724.method_19538();
        class_243 center = this.regionController.getRegionState().getCenterVec();
        double dist = Math.abs(center.method_1020(playerPos).method_37267());
        return dist < 13.0;
    }

    private boolean isPlayerNearRegion() {
        double dx;
        if (AutoMineController.mc.field_1724 == null) {
            return false;
        }
        double px = AutoMineController.mc.field_1724.method_23317();
        double pz = AutoMineController.mc.field_1724.method_23321();
        class_2338 min = this.regionController.getRegionState().getMin();
        class_2338 max = this.regionController.getRegionState().getMax();
        double minX = Math.min(min.method_10263(), max.method_10263());
        double maxX = Math.max(min.method_10263(), max.method_10263());
        double minZ = Math.min(min.method_10260(), max.method_10260());
        double maxZ = Math.max(min.method_10260(), max.method_10260());
        double d = px < minX ? minX - px : (dx = px > maxX ? px - maxX : 0.0);
        double dz = pz < minZ ? minZ - pz : (pz > maxZ ? pz - maxZ : 0.0);
        double dist = Math.sqrt(dx * dx + dz * dz);
        return dist <= 7.0;
    }

    public RegionController getRegionController() {
        return this.regionController;
    }

    public MineController getMineController() {
        return this.mineController;
    }

    public MineApiParser getApiParser() {
        return this.apiParser;
    }

    public RepairController getRepairController() {
        return this.repairController;
    }

    public AutoMineState getState() {
        return this.state;
    }

    public long getStateStartTime() {
        return this.stateStartTime;
    }

    public long getLastCommandTime() {
        return this.lastCommandTime;
    }

    public String getCurrentAnarchy() {
        return this.currentAnarchy;
    }

    public boolean isConnectMessageSent() {
        return this.connectMessageSent;
    }

    public boolean isAlreadyOnAnarchy() {
        return this.alreadyOnAnarchy;
    }

    public int getNoTargetTicks() {
        return this.noTargetTicks;
    }
}
