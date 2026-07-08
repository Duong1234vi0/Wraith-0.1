/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2625
 *  net.minecraft.class_332
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_476
 *  net.minecraft.class_7439
 *  net.minecraft.class_8242
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.impl.render.WardenHelper;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.Optional;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2625;
import net.minecraft.class_332;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_476;
import net.minecraft.class_7439;
import net.minecraft.class_8242;

public class AutoWarden
extends Module {
    private final TextSetting signName = new TextSetting("\u0422\u0430\u0431\u043b\u0438\u0447\u043a\u0430", "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0442\u0430\u0431\u043b\u0438\u0447\u043a\u0438 \u043d\u0430 \u0441\u0443\u043d\u0434\u0443\u043a\u0435 \u0441 \u043f\u0440\u0438\u043f\u0430\u0441\u0430\u043c\u0438").setText("warden");
    private final TextSetting wardenHomeName = new TextSetting("\u0425\u043e\u043c \u0412\u0430\u0440\u0434\u0435\u043d\u0430", "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 clan home \u043a \u0432\u0430\u0440\u0434\u0435\u043d\u0443").setText("warden");
    private final TextSetting resourceHomeName = new TextSetting("\u0425\u043e\u043c \u0420\u0435\u0441\u0443\u0440\u0441\u043e\u0432", "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 clan home \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443 \u0440\u0435\u0441\u0443\u0440\u0441\u043e\u0432").setText("base");
    private final TextSetting anarchyNumber = new TextSetting("\u0410\u043d\u0430\u0440\u0445\u0438\u044f", "\u041d\u043e\u043c\u0435\u0440 \u0430\u043d\u0430\u0440\u0445\u0438\u0438 (an1, an2...)").setText("1");
    private final SliderSettings foodAmount = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u0435\u0434\u044b", "\u0421\u043a\u043e\u043b\u044c\u043a\u043e \u0437\u043e\u043b\u043e\u0442\u044b\u0445 \u043c\u043e\u0440\u043a\u043e\u0432\u043e\u043a \u0431\u0440\u0430\u0442\u044c \u0441 \u0441\u043e\u0431\u043e\u0439").setValue(8.0f).range(4, 16);
    private final SliderSettings maxChestTimer = new SliderSettings("\u041c\u0430\u043a\u0441. \u0442\u0430\u0439\u043c\u0435\u0440", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0442\u0430\u0439\u043c\u0435\u0440 \u0441\u0443\u043d\u0434\u0443\u043a\u0430 (\u0441\u0435\u043a)").setValue(90.0f).range(30, 180);
    private final BooleanSetting autoRestart = new BooleanSetting("\u0410\u0432\u0442\u043e \u0440\u0435\u0441\u0442\u0430\u0440\u0442", "\u041f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u043a \u043f\u043e\u0441\u043b\u0435 \u0441\u043c\u0435\u0440\u0442\u0438").setValue(true);
    private State currentState = State.IDLE;
    private final StopWatch stateTimer = new StopWatch();
    private final StopWatch commandCooldown = new StopWatch();
    private final StopWatch lootDelay = new StopWatch();
    private final StopWatch dumpDelay = new StopWatch();
    private final StopWatch scanTimer = new StopWatch();
    private final StopWatch moveTimer = new StopWatch();
    private final StopWatch supplyCooldown = new StopWatch();
    private final StopWatch invisCooldown = new StopWatch();
    private final StopWatch eatCooldown = new StopWatch();
    private boolean pendingInvisUse = false;
    private boolean pendingFoodUse = false;
    private class_243 preTpPosition = null;
    private static final double TP_DISTANCE_THRESHOLD = 10.0;
    private static final long COMMAND_COOLDOWN_MS = 2200L;
    private static final long TP_WAIT_MS = 5000L;
    private static final long TP_VERIFY_TIMEOUT_MS = 10000L;
    private static final long DARENA_WAIT_MS = 3000L;
    private static final long LOOT_DELAY_MS = 80L;
    private static final long DEAD_RESPAWN_MS = 3000L;
    private static final long HUB_WAIT_MS = 5000L;
    private static final long REJOIN_WAIT_MS = 7000L;
    private static final long MOVE_COOLDOWN_MS = 12000L;
    private static final int MIN_DARENA_TIMER_SEC = 15;
    private static final String TEMP_HOME_NAME = "wdtmp";
    private class_2338 targetChestPos = null;
    private int targetChestRemainingSeconds = 0;
    private long targetChestFoundTime = 0L;
    private boolean baritoneWalking = false;
    private boolean darenaItemFound = false;
    private boolean suppliesCollected = false;
    private int lootedSlots = 0;
    private int totalItemsLooted = 0;
    private int cyclesCompleted = 0;
    private int supplyTakeCount = 0;
    private class_2338 patrolOrigin = null;
    private int patrolIndex = 0;
    private static final int PATROL_RADIUS = 25;
    private static final int[][] PATROL_OFFSETS = new int[][]{{25, 0}, {25, 25}, {0, 25}, {-25, 25}, {-25, 0}, {-25, -25}, {0, -25}, {25, -25}};

    public AutoWarden() {
        super("AutoWarden", "AutoWarden", ModuleCategory.MISC);
        this.setup(this.signName, this.wardenHomeName, this.resourceHomeName, this.anarchyNumber, this.foodAmount, this.maxChestTimer, this.autoRestart);
    }

    @Override
    public void activate() {
        super.activate();
        this.currentState = State.GOING_TO_SUPPLY_CHEST;
        this.resetAll();
        ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0417\u0430\u043f\u0443\u0449\u0435\u043d. \u0418\u0434\u0443 \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443 \u043f\u0440\u0438\u043f\u0430\u0441\u043e\u0432...");
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.stopBaritone();
        this.currentState = State.IDLE;
        ChatMessage.brandmessage("\u00a7c[AutoWarden] \u041e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d.");
    }

    private void resetAll() {
        this.stateTimer.reset();
        this.commandCooldown.reset();
        this.lootDelay.reset();
        this.dumpDelay.reset();
        this.scanTimer.reset();
        this.moveTimer.reset();
        this.supplyCooldown.reset();
        this.invisCooldown.reset();
        this.eatCooldown.reset();
        this.pendingInvisUse = false;
        this.pendingFoodUse = false;
        this.targetChestPos = null;
        this.targetChestRemainingSeconds = 0;
        this.targetChestFoundTime = 0L;
        this.preTpPosition = null;
        this.baritoneWalking = false;
        this.darenaItemFound = false;
        this.suppliesCollected = false;
        this.lootedSlots = 0;
        this.totalItemsLooted = 0;
        this.cyclesCompleted = 0;
        this.supplyTakeCount = 0;
        this.patrolOrigin = null;
        this.patrolIndex = 0;
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AutoWarden.mc.field_1724 == null || AutoWarden.mc.field_1687 == null) {
            return;
        }
        if (AutoWarden.mc.field_1724.method_6032() <= 0.0f) {
            this.handleDeath();
            return;
        }
        if (AutoWarden.mc.field_1755 == null) {
            this.tickInvisAndEat();
        }
        switch (this.currentState.ordinal()) {
            case 0: {
                break;
            }
            case 25: {
                this.handleDeadWaiting();
                break;
            }
            case 1: {
                this.handleGoingToSupplyChest();
                break;
            }
            case 2: {
                this.handleOpeningSupplyChest();
                break;
            }
            case 3: {
                this.handleTakingSupplies();
                break;
            }
            case 4: {
                this.handleDrinkingInvis();
                break;
            }
            case 5: {
                this.handleTeleportToWarden();
                break;
            }
            case 6: {
                this.handleWaitingWardenTp();
                break;
            }
            case 7: {
                this.handleSearchingChests();
                break;
            }
            case 8: {
                this.handleWalkingToChest();
                break;
            }
            case 9: {
                this.handleSettingHomeAtChest();
                break;
            }
            case 10: {
                this.handleTeleportToDarena();
                break;
            }
            case 11: {
                this.handleWaitingDarena();
                break;
            }
            case 12: {
                this.handleBrowsingDarena();
                break;
            }
            case 13: {
                this.handleWaitingTimer();
                break;
            }
            case 14: {
                this.handleTeleportBackToChest();
                break;
            }
            case 15: {
                this.handleWaitingChestTp();
                break;
            }
            case 16: {
                this.handleLootingChest();
                break;
            }
            case 17: {
                this.handleTeleportToBase();
                break;
            }
            case 18: {
                this.handleWaitingBaseTp();
                break;
            }
            case 19: {
                this.handleDumpingLoot();
                break;
            }
            case 20: {
                this.handleDeletingTempHome();
                break;
            }
            case 21: {
                this.handleFleeingWarden();
                break;
            }
            case 22: {
                this.handleWaitingHub();
                break;
            }
            case 23: {
                this.handleRejoiningAnarchy();
                break;
            }
            case 24: {
                this.handleWaitingRejoin();
                break;
            }
            case 26: {
                this.handleWaitingAtChest();
            }
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (e.getType() != PacketEvent.Type.RECEIVE) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_7439) {
            class_7439 gameMessage = (class_7439)class_25962;
            String msg = gameMessage.comp_763().getString().toLowerCase();
            if (msg.contains("\u0432\u0430\u0440\u0434\u0435\u043d") && (msg.contains("\u0430\u0433\u0440") || msg.contains("\u043e\u0431\u043d\u0430\u0440\u0443\u0436") || msg.contains("\u043d\u0430\u043f\u0430\u043b")) && this.currentState != State.FLEEING_WARDEN && this.currentState != State.WAITING_HUB && this.currentState != State.REJOINING_ANARCHY && this.currentState != State.IDLE) {
                ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0412\u0430\u0440\u0434\u0435\u043d \u0437\u0430\u0430\u0433\u0440\u0438\u043b\u0441\u044f! \u0411\u0435\u0433\u0443 \u0432 \u0445\u0430\u0431...");
                this.stopBaritone();
                this.currentState = State.FLEEING_WARDEN;
                this.stateTimer.reset();
            }
            if (msg.contains("[baritone]") && (msg.contains("goal reached") || msg.contains("done"))) {
                this.baritoneWalking = false;
            }
        }
    }

    private void handleDeath() {
        if (this.currentState != State.DEAD_WAITING) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0423\u043c\u0435\u0440! \u0416\u0434\u0443 \u0440\u0435\u0441\u043f\u0430\u0432\u043d...");
            this.stopBaritone();
            this.currentState = State.DEAD_WAITING;
            this.stateTimer.reset();
        }
    }

    private void handleDeadWaiting() {
        if (AutoWarden.mc.field_1724.method_6032() > 0.0f && this.stateTimer.finished(3000.0)) {
            if (this.autoRestart.isValue()) {
                ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0420\u0435\u0441\u043f\u0430\u0432\u043d. \u0418\u0434\u0443 \u043a \u043f\u0440\u0438\u043f\u0430\u0441\u0430\u043c...");
                this.currentState = State.GOING_TO_SUPPLY_CHEST;
                this.stateTimer.reset();
            } else {
                this.setState(false);
            }
        }
    }

    private void handleGoingToSupplyChest() {
        class_2338 supplyChest = this.findSupplyChest();
        if (supplyChest != null) {
            double dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)supplyChest));
            if (dist <= 9.0) {
                this.stopBaritone();
                this.baritoneWalking = false;
                this.currentState = State.OPENING_SUPPLY_CHEST;
                this.stateTimer.reset();
            } else if (!this.baritoneWalking) {
                this.baritoneGoto(supplyChest);
                this.baritoneWalking = true;
            }
        } else if (this.supplyCooldown.every(5000.0)) {
            this.sendCommand("/clan home " + this.resourceHomeName.getText());
            this.stateTimer.reset();
        }
    }

    private void handleOpeningSupplyChest() {
        class_2338 supplyChest = this.findSupplyChest();
        if (supplyChest == null) {
            this.currentState = State.GOING_TO_SUPPLY_CHEST;
            return;
        }
        if (AutoWarden.mc.field_1755 instanceof class_476) {
            this.currentState = State.TAKING_SUPPLIES;
            this.suppliesCollected = false;
            this.supplyTakeCount = 0;
            this.stateTimer.reset();
            return;
        }
        if (!this.hasLineOfSight(supplyChest)) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0421\u0443\u043d\u0434\u0443\u043a \u0437\u0430 \u0441\u0442\u0435\u043d\u043e\u0439, \u0438\u0449\u0443 \u0441\u043d\u043e\u0432\u0430...");
            this.currentState = State.GOING_TO_SUPPLY_CHEST;
            return;
        }
        if (this.commandCooldown.every(600.0)) {
            this.interactWithBlock(supplyChest);
        }
    }

    private void handleTakingSupplies() {
        boolean needFood;
        class_437 class_4372 = AutoWarden.mc.field_1755;
        if (!(class_4372 instanceof class_476)) {
            if (this.suppliesCollected) {
                this.currentState = State.DRINKING_INVIS;
                this.stateTimer.reset();
            } else {
                this.currentState = State.OPENING_SUPPLY_CHEST;
                this.stateTimer.reset();
            }
            return;
        }
        class_476 screen = (class_476)class_4372;
        if (!this.stateTimer.finished(300.0)) {
            return;
        }
        int invInvisCount = this.countInvisInInventory();
        int invFoodCount = this.countFoodInInventory();
        boolean needInvis = invInvisCount < 1;
        boolean bl = needFood = invFoodCount < (int)this.foodAmount.getValue();
        if (!needInvis && !needFood) {
            AutoWarden.mc.field_1724.method_7346();
            this.suppliesCollected = true;
            this.currentState = State.DRINKING_INVIS;
            this.stateTimer.reset();
            return;
        }
        if (!this.lootDelay.finished(120.0)) {
            return;
        }
        class_1707 handler = (class_1707)screen.method_17577();
        for (class_1735 slot : handler.field_7761) {
            if (slot.field_7871 == AutoWarden.mc.field_1724.method_31548() || !slot.method_7681()) continue;
            class_1799 stack = slot.method_7677();
            if (needInvis && this.isInvisPotion(stack)) {
                InventoryTask.clickSlot(slot, 0, class_1713.field_7794, true);
                this.lootDelay.reset();
                ++this.supplyTakeCount;
                return;
            }
            if (!needFood || stack.method_7909() != class_1802.field_8071) continue;
            InventoryTask.clickSlot(slot, 0, class_1713.field_7794, true);
            this.lootDelay.reset();
            ++this.supplyTakeCount;
            return;
        }
        AutoWarden.mc.field_1724.method_7346();
        this.suppliesCollected = true;
        this.currentState = State.DRINKING_INVIS;
        this.stateTimer.reset();
    }

    private void handleDrinkingInvis() {
        int invSlot;
        if (AutoWarden.mc.field_1724.method_6059(class_1294.field_5905)) {
            this.currentState = State.TELEPORTING_TO_WARDEN;
            this.stateTimer.reset();
            return;
        }
        if (!this.commandCooldown.finished(600.0)) {
            return;
        }
        int slot = this.findInvisPotionHotbar();
        if (slot == -1 && (invSlot = this.findInvisPotionInventory()) != -1) {
            InventoryTask.swapSlots(invSlot, 0);
            this.commandCooldown.reset();
            return;
        }
        if (slot != -1) {
            AutoWarden.mc.field_1724.method_31548().field_7545 = slot;
            AutoWarden.mc.field_1761.method_2919((class_1657)AutoWarden.mc.field_1724, class_1268.field_5808);
            this.commandCooldown.reset();
        } else {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u041d\u0435\u0442 \u0437\u0435\u043b\u044c\u044f \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u0438!");
            this.currentState = State.GOING_TO_SUPPLY_CHEST;
            this.stateTimer.reset();
        }
    }

    private void handleTeleportToWarden() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.preTpPosition = AutoWarden.mc.field_1724.method_19538();
        this.sendCommand("/clan home " + this.wardenHomeName.getText());
        this.currentState = State.WAITING_WARDEN_TP;
        this.stateTimer.reset();
    }

    private void handleWaitingWardenTp() {
        double moved;
        if (this.preTpPosition != null && AutoWarden.mc.field_1724 != null && (moved = AutoWarden.mc.field_1724.method_19538().method_1025(this.preTpPosition)) >= 100.0) {
            this.preTpPosition = null;
            this.currentState = State.SEARCHING_CHESTS;
            this.stateTimer.reset();
            ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041f\u0440\u0438\u0431\u044b\u043b \u043a \u0432\u0430\u0440\u0434\u0435\u043d\u0443. \u0418\u0449\u0443 \u0441\u0443\u043d\u0434\u0443\u043a\u0438...");
            return;
        }
        if (this.stateTimer.finished(10000.0)) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0422\u041f \u043d\u0435 \u0441\u0440\u0430\u0431\u043e\u0442\u0430\u043b, \u043f\u043e\u0432\u0442\u043e\u0440\u044f\u044e...");
            this.currentState = State.TELEPORTING_TO_WARDEN;
            this.stateTimer.reset();
        }
    }

    private void handleSearchingChests() {
        int maxTimer;
        Optional<WardenHelper.ChestSnapshot> best;
        WardenHelper wardenHelper = this.getWardenHelper();
        if (wardenHelper == null || !wardenHelper.isState()) {
            if (this.stateTimer.finished(3000.0)) {
                ChatMessage.brandmessage("\u00a7c[AutoWarden] WardenHelper \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d!");
                this.stateTimer.reset();
            }
            return;
        }
        if (this.patrolOrigin == null) {
            this.patrolOrigin = AutoWarden.mc.field_1724.method_24515();
            this.patrolIndex = 0;
        }
        if ((best = wardenHelper.getClosestChestUnder(maxTimer = (int)this.maxChestTimer.getValue())).isPresent()) {
            WardenHelper.ChestSnapshot snapshot = best.get();
            this.targetChestPos = snapshot.pos();
            this.targetChestRemainingSeconds = snapshot.remainingSeconds();
            this.targetChestFoundTime = System.currentTimeMillis();
            this.stopBaritone();
            this.baritoneWalking = false;
            ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041d\u0430\u0439\u0434\u0435\u043d \u0441\u0443\u043d\u0434\u0443\u043a! \u0422\u0430\u0439\u043c\u0435\u0440: " + this.formatTime(this.targetChestRemainingSeconds) + " | \u0418\u0434\u0443 \u043a \u043d\u0435\u043c\u0443...");
            this.currentState = State.WALKING_TO_CHEST;
            this.stateTimer.reset();
            return;
        }
        if (!this.baritoneWalking || this.stateTimer.finished(12000.0)) {
            this.stopBaritone();
            this.baritoneWalking = false;
            int[] offset = PATROL_OFFSETS[this.patrolIndex % PATROL_OFFSETS.length];
            int rx = offset[0] + (int)(Math.random() * 6.0 - 3.0);
            int rz = offset[1] + (int)(Math.random() * 6.0 - 3.0);
            class_2338 patrolTarget = this.patrolOrigin.method_10069(rx, 0, rz);
            ++this.patrolIndex;
            this.baritoneGoto(patrolTarget);
            this.baritoneWalking = true;
            this.stateTimer.reset();
        }
    }

    private void handleWalkingToChest() {
        double dist;
        Optional<WardenHelper.ChestSnapshot> better;
        if (this.targetChestPos == null) {
            this.currentState = State.SEARCHING_CHESTS;
            return;
        }
        WardenHelper wardenHelper = this.getWardenHelper();
        if (wardenHelper != null && wardenHelper.isState() && (better = wardenHelper.getClosestChestUnder((int)this.maxChestTimer.getValue())).isPresent()) {
            WardenHelper.ChestSnapshot snap = better.get();
            if (!snap.pos().equals((Object)this.targetChestPos)) {
                boolean muchCloser;
                double currentDist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)this.targetChestPos));
                double newDist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)snap.pos()));
                boolean betterTimer = snap.remainingSeconds() < this.targetChestRemainingSeconds;
                boolean bl = muchCloser = newDist < currentDist * 0.4;
                if (betterTimer || muchCloser) {
                    this.targetChestPos = snap.pos();
                    this.targetChestRemainingSeconds = snap.remainingSeconds();
                    this.targetChestFoundTime = System.currentTimeMillis();
                    this.stopBaritone();
                    this.baritoneWalking = false;
                    ChatMessage.brandmessage("\u00a7e[AutoWarden] \u041d\u0430\u0448\u0451\u043b \u043b\u0443\u0447\u0448\u0438\u0439 \u0441\u0443\u043d\u0434\u0443\u043a! \u0422\u0430\u0439\u043c\u0435\u0440: " + this.formatTime(this.targetChestRemainingSeconds));
                }
            } else {
                Optional<WardenHelper.ChestSnapshot> updatedSnap = wardenHelper.getChestSnapshot(this.targetChestPos);
                updatedSnap.ifPresent(s -> {
                    this.targetChestRemainingSeconds = s.remainingSeconds();
                    this.targetChestFoundTime = System.currentTimeMillis();
                });
            }
        }
        if ((dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)this.targetChestPos))) <= 6.25) {
            this.stopBaritone();
            this.baritoneWalking = false;
            this.currentState = State.SETTING_HOME_AT_CHEST;
            this.stateTimer.reset();
        } else if (!this.baritoneWalking) {
            this.baritoneGoto(this.targetChestPos);
            this.baritoneWalking = true;
        }
        if (this.stateTimer.finished(35000.0)) {
            this.stopBaritone();
            this.baritoneWalking = false;
            this.currentState = State.SEARCHING_CHESTS;
            this.stateTimer.reset();
            this.targetChestPos = null;
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u041d\u0435 \u0441\u043c\u043e\u0433 \u0434\u043e\u0439\u0442\u0438 \u0434\u043e \u0441\u0443\u043d\u0434\u0443\u043a\u0430. \u0418\u0449\u0443 \u0434\u0430\u043b\u044c\u0448\u0435...");
        }
    }

    private void handleSettingHomeAtChest() {
        int remaining = this.getCurrentChestRemaining();
        if (remaining < 15 && remaining > 0) {
            ChatMessage.brandmessage("\u00a7e[AutoWarden] \u0422\u0430\u0439\u043c\u0435\u0440 " + this.formatTime(remaining) + " \u2014 \u0436\u0434\u0443 \u0443 \u0441\u0443\u043d\u0434\u0443\u043a\u0430...");
            this.stopBaritone();
            this.baritoneWalking = false;
            this.currentState = State.WAITING_AT_CHEST;
            this.stateTimer.reset();
            return;
        }
        if (remaining <= 0) {
            this.currentState = State.LOOTING_CHEST;
            this.lootedSlots = 0;
            this.stateTimer.reset();
            return;
        }
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.sendCommand("/clan sethome wdtmp");
        this.commandCooldown.reset();
        this.currentState = State.TELEPORTING_TO_DARENA;
        this.stateTimer.reset();
        ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0425\u043e\u043c \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d. \u041b\u0435\u0447\u0443 \u043d\u0430 \u0434\u0430\u0440\u0435\u043d\u0443...");
    }

    private void handleWaitingAtChest() {
        if (this.targetChestPos == null) {
            this.currentState = State.SEARCHING_CHESTS;
            return;
        }
        int remaining = this.getCurrentChestRemaining();
        if (remaining <= 0) {
            this.currentState = State.LOOTING_CHEST;
            this.lootedSlots = 0;
            this.stateTimer.reset();
            ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0422\u0430\u0439\u043c\u0435\u0440 \u0438\u0441\u0442\u0451\u043a! \u041b\u0443\u0442\u0430\u044e \u0441\u0443\u043d\u0434\u0443\u043a...");
            return;
        }
        double dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)this.targetChestPos));
        if (dist > 6.25 && !this.baritoneWalking) {
            this.baritoneGoto(this.targetChestPos);
            this.baritoneWalking = true;
        } else if (dist <= 6.25) {
            this.stopBaritone();
            this.baritoneWalking = false;
        }
    }

    private void handleTeleportToDarena() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.sendCommand("/darena");
        this.commandCooldown.reset();
        this.currentState = State.WAITING_DARENA;
        this.stateTimer.reset();
    }

    private void handleWaitingDarena() {
        if (AutoWarden.mc.field_1755 instanceof class_476) {
            this.currentState = State.BROWSING_DARENA;
            this.darenaItemFound = false;
            this.stateTimer.reset();
            return;
        }
        if (this.stateTimer.finished(8000.0)) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] /darena \u043d\u0435 \u043e\u0442\u043a\u0440\u044b\u043b\u0441\u044f. \u041f\u043e\u0432\u0442\u043e\u0440\u044f\u044e...");
            this.currentState = State.TELEPORTING_TO_DARENA;
            this.stateTimer.reset();
        }
    }

    private void handleBrowsingDarena() {
        class_437 class_4372 = AutoWarden.mc.field_1755;
        if (!(class_4372 instanceof class_476)) {
            if (this.darenaItemFound) {
                this.currentState = State.WAITING_TIMER;
                this.stateTimer.reset();
                ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041d\u0430 \u0441\u043c\u043e\u0442\u0440\u043e\u0432\u043e\u0439. \u0416\u0434\u0443 \u0442\u0430\u0439\u043c\u0435\u0440 \u0441\u0443\u043d\u0434\u0443\u043a\u0430...");
            } else if (this.stateTimer.finished(3000.0)) {
                ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0418\u0433\u043b\u043e\u0431\u0440\u044e\u0445 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d. \u041f\u043e\u0432\u0442\u043e\u0440\u044f\u044e /darena...");
                this.currentState = State.TELEPORTING_TO_DARENA;
                this.stateTimer.reset();
            }
            return;
        }
        class_476 screen = (class_476)class_4372;
        if (!this.stateTimer.finished(400.0)) {
            return;
        }
        class_1707 handler = (class_1707)screen.method_17577();
        for (class_1735 slot : handler.field_7761) {
            String name;
            class_1799 stack;
            if (slot.field_7871 == AutoWarden.mc.field_1724.method_31548() || !slot.method_7681() || (stack = slot.method_7677()).method_7909() != class_1802.field_8323 || !(name = stack.method_7964().getString().toLowerCase()).contains("\u0441\u043c\u043e\u0442\u0440")) continue;
            InventoryTask.clickSlot(slot, 0, class_1713.field_7790, true);
            this.darenaItemFound = true;
            this.stateTimer.reset();
            ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041d\u0430\u0436\u0430\u043b \u043d\u0430 \u0438\u0433\u043b\u043e\u0431\u0440\u044e\u0445! \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043d\u0430 \u0441\u043c\u043e\u0442\u0440\u043e\u0432\u0443\u044e...");
            return;
        }
        if (this.stateTimer.finished(5000.0)) {
            AutoWarden.mc.field_1724.method_7346();
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0418\u0433\u043b\u043e\u0431\u0440\u044e\u0445 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 /darena!");
            this.currentState = State.TELEPORTING_TO_DARENA;
            this.stateTimer.reset();
        }
    }

    private void handleWaitingTimer() {
        int remaining = this.getCurrentChestRemaining();
        if (remaining <= 8 && remaining > 0) {
            this.currentState = State.TELEPORTING_BACK_TO_CHEST;
            this.stateTimer.reset();
            ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0422\u0430\u0439\u043c\u0435\u0440 ~8\u0441! \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u044e\u0441\u044c \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443...");
            return;
        }
        if (remaining <= 0 && this.stateTimer.finished(10000.0)) {
            this.currentState = State.TELEPORTING_BACK_TO_CHEST;
            this.stateTimer.reset();
        }
    }

    private void handleTeleportBackToChest() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.preTpPosition = AutoWarden.mc.field_1724.method_19538();
        this.sendCommand("/clan home wdtmp");
        this.commandCooldown.reset();
        this.currentState = State.WAITING_CHEST_TP;
        this.stateTimer.reset();
    }

    private void handleWaitingChestTp() {
        double moved;
        if (this.preTpPosition != null && AutoWarden.mc.field_1724 != null && (moved = AutoWarden.mc.field_1724.method_19538().method_1025(this.preTpPosition)) >= 100.0) {
            this.preTpPosition = null;
            if (this.stateTimer.finished(800.0)) {
                this.currentState = State.LOOTING_CHEST;
                this.stateTimer.reset();
                this.lootedSlots = 0;
            }
            return;
        }
        if (this.stateTimer.finished(10000.0)) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0422\u041f \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443 \u043d\u0435 \u0441\u0440\u0430\u0431\u043e\u0442\u0430\u043b!");
            this.currentState = State.TELEPORTING_BACK_TO_CHEST;
            this.stateTimer.reset();
        }
    }

    private void handleLootingChest() {
        if (this.targetChestPos == null) {
            this.currentState = State.SEARCHING_CHESTS;
            this.stateTimer.reset();
            return;
        }
        class_437 class_4372 = AutoWarden.mc.field_1755;
        if (class_4372 instanceof class_476) {
            class_476 screen = (class_476)class_4372;
            if (!this.lootDelay.finished(80.0)) {
                return;
            }
            class_1707 handler = (class_1707)screen.method_17577();
            for (class_1735 slot : handler.field_7761) {
                if (slot.field_7871 == AutoWarden.mc.field_1724.method_31548() || !slot.method_7681()) continue;
                InventoryTask.clickSlot(slot, 0, class_1713.field_7794, false);
                ++this.lootedSlots;
                ++this.totalItemsLooted;
                this.lootDelay.reset();
                return;
            }
            AutoWarden.mc.field_1724.method_7346();
            if (this.lootedSlots == 0) {
                ChatMessage.brandmessage("\u00a7e[AutoWarden] \u0421\u0443\u043d\u0434\u0443\u043a \u043f\u0443\u0441\u0442. \u041f\u0440\u043e\u0434\u043e\u043b\u0436\u0430\u044e \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435...");
                this.targetChestPos = null;
                this.currentState = State.SEARCHING_CHESTS;
                this.stateTimer.reset();
            } else {
                ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0421\u0443\u043d\u0434\u0443\u043a \u0437\u0430\u043b\u0443\u0442\u0430\u043d! (" + this.lootedSlots + " \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432)");
                this.currentState = State.TELEPORTING_TO_BASE;
                this.stateTimer.reset();
            }
        } else {
            if (!this.hasLineOfSight(this.targetChestPos)) {
                double dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)this.targetChestPos));
                if (dist > 9.0 && !this.baritoneWalking) {
                    this.baritoneGoto(this.targetChestPos);
                    this.baritoneWalking = true;
                }
                return;
            }
            if (this.stateTimer.finished(500.0)) {
                this.interactWithBlock(this.targetChestPos);
                this.stateTimer.reset();
            }
        }
    }

    private void handleTeleportToBase() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.preTpPosition = AutoWarden.mc.field_1724.method_19538();
        this.sendCommand("/clan home " + this.resourceHomeName.getText());
        this.commandCooldown.reset();
        this.currentState = State.WAITING_BASE_TP;
        this.stateTimer.reset();
    }

    private void handleWaitingBaseTp() {
        double moved;
        if (this.preTpPosition != null && AutoWarden.mc.field_1724 != null && (moved = AutoWarden.mc.field_1724.method_19538().method_1025(this.preTpPosition)) >= 100.0) {
            this.preTpPosition = null;
            this.currentState = State.DUMPING_LOOT;
            this.stateTimer.reset();
            return;
        }
        if (this.stateTimer.finished(10000.0)) {
            ChatMessage.brandmessage("\u00a7c[AutoWarden] \u0422\u041f \u043d\u0430 \u0431\u0430\u0437\u0443 \u043d\u0435 \u0441\u0440\u0430\u0431\u043e\u0442\u0430\u043b, \u043f\u043e\u0432\u0442\u043e\u0440\u044f\u044e...");
            this.currentState = State.TELEPORTING_TO_BASE;
            this.stateTimer.reset();
        }
    }

    private void handleDumpingLoot() {
        class_2338 dumpChest = this.findDumpChest();
        if (dumpChest == null) {
            if (this.stateTimer.finished(5000.0)) {
                ChatMessage.brandmessage("\u00a7c[AutoWarden] \u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0441\u0443\u043d\u0434\u0443\u043a \u0434\u043b\u044f \u0441\u0431\u0440\u043e\u0441\u0430 \u043b\u0443\u0442\u0430!");
                this.currentState = State.DELETING_TEMP_HOME;
                this.stateTimer.reset();
            }
            return;
        }
        double dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)dumpChest));
        if (dist > 9.0) {
            if (!this.baritoneWalking) {
                this.baritoneGoto(dumpChest);
                this.baritoneWalking = true;
            }
            return;
        }
        this.stopBaritone();
        this.baritoneWalking = false;
        class_437 class_4372 = AutoWarden.mc.field_1755;
        if (class_4372 instanceof class_476) {
            class_476 screen = (class_476)class_4372;
            if (!this.dumpDelay.finished(100.0)) {
                return;
            }
            class_1707 handler = (class_1707)screen.method_17577();
            boolean dumpedSomething = false;
            for (class_1735 slot : handler.field_7761) {
                class_1799 stack;
                if (slot.field_7871 != AutoWarden.mc.field_1724.method_31548() || !slot.method_7681() || (stack = slot.method_7677()).method_7909() == class_1802.field_8071 || this.isInvisPotion(stack)) continue;
                InventoryTask.clickSlot(slot, 0, class_1713.field_7794, false);
                this.dumpDelay.reset();
                dumpedSomething = true;
                break;
            }
            if (!dumpedSomething) {
                AutoWarden.mc.field_1724.method_7346();
                ++this.cyclesCompleted;
                this.currentState = State.DELETING_TEMP_HOME;
                this.stateTimer.reset();
                ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041b\u0443\u0442 \u0441\u0431\u0440\u043e\u0448\u0435\u043d! \u0426\u0438\u043a\u043b " + this.cyclesCompleted + " \u0437\u0430\u0432\u0435\u0440\u0448\u0451\u043d.");
            }
        } else {
            if (!this.hasLineOfSight(dumpChest)) {
                this.baritoneGoto(dumpChest);
                this.baritoneWalking = true;
                return;
            }
            if (this.commandCooldown.every(700.0)) {
                this.interactWithBlock(dumpChest);
            }
        }
    }

    private void handleDeletingTempHome() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.sendCommand("/clan delhome wdtmp");
        this.commandCooldown.reset();
        this.targetChestPos = null;
        this.patrolOrigin = null;
        this.patrolIndex = 0;
        this.currentState = State.GOING_TO_SUPPLY_CHEST;
        this.stateTimer.reset();
        ChatMessage.brandmessage("\u00a7a[AutoWarden] \u0426\u0438\u043a\u043b \u0437\u0430\u0432\u0435\u0440\u0448\u0451\u043d. \u041d\u0430\u0447\u0438\u043d\u0430\u044e \u0437\u0430\u043d\u043e\u0432\u043e...");
    }

    private void handleFleeingWarden() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.sendCommand("/hub");
        this.commandCooldown.reset();
        this.currentState = State.WAITING_HUB;
        this.stateTimer.reset();
    }

    private void handleWaitingHub() {
        if (this.stateTimer.finished(5000.0)) {
            this.currentState = State.REJOINING_ANARCHY;
            this.stateTimer.reset();
        }
    }

    private void handleRejoiningAnarchy() {
        if (!this.commandCooldown.finished(2200.0)) {
            return;
        }
        this.sendCommand("/an" + this.anarchyNumber.getText());
        this.commandCooldown.reset();
        this.currentState = State.WAITING_REJOIN;
        this.stateTimer.reset();
        ChatMessage.brandmessage("\u00a7a[AutoWarden] \u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0436\u0443 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e " + this.anarchyNumber.getText());
    }

    private void handleWaitingRejoin() {
        if (this.stateTimer.finished(7000.0)) {
            this.currentState = State.GOING_TO_SUPPLY_CHEST;
            this.stateTimer.reset();
        }
    }

    private WardenHelper getWardenHelper() {
        return AlphaDLC.getInstance().getModuleProvider().get(WardenHelper.class);
    }

    private int getCurrentChestRemaining() {
        Optional<WardenHelper.ChestSnapshot> snap;
        if (this.targetChestPos == null) {
            return 0;
        }
        long elapsed = (System.currentTimeMillis() - this.targetChestFoundTime) / 1000L;
        int remaining = (int)((long)this.targetChestRemainingSeconds - elapsed);
        WardenHelper helper = this.getWardenHelper();
        if (helper != null && helper.isState() && (snap = helper.getChestSnapshot(this.targetChestPos)).isPresent()) {
            remaining = snap.get().remainingSeconds();
        }
        return Math.max(0, remaining);
    }

    private void tickInvisAndEat() {
        if (AutoWarden.mc.field_1724 == null) {
            return;
        }
        boolean needsInvis = this.needsInvisPotion();
        if (this.pendingInvisUse) {
            this.pendingInvisUse = false;
            int slot = this.findInvisPotionHotbar();
            if (slot != -1) {
                AutoWarden.mc.field_1724.method_31548().field_7545 = slot;
                AutoWarden.mc.field_1761.method_2919((class_1657)AutoWarden.mc.field_1724, class_1268.field_5808);
                this.invisCooldown.reset();
            }
            return;
        }
        if (needsInvis && this.invisCooldown.finished(1500.0)) {
            int slot = this.findInvisPotionHotbar();
            if (slot != -1) {
                AutoWarden.mc.field_1724.method_31548().field_7545 = slot;
                AutoWarden.mc.field_1761.method_2919((class_1657)AutoWarden.mc.field_1724, class_1268.field_5808);
                this.invisCooldown.reset();
            } else {
                int invSlot = this.findInvisPotionInventory();
                if (invSlot != -1) {
                    InventoryTask.swapSlots(invSlot, 0);
                    this.pendingInvisUse = true;
                }
            }
            return;
        }
        if (this.pendingFoodUse) {
            this.pendingFoodUse = false;
            int slot = this.findFoodHotbar();
            if (slot != -1) {
                AutoWarden.mc.field_1724.method_31548().field_7545 = slot;
                AutoWarden.mc.field_1761.method_2919((class_1657)AutoWarden.mc.field_1724, class_1268.field_5808);
                this.eatCooldown.reset();
            }
            return;
        }
        int foodLevel = AutoWarden.mc.field_1724.method_7344().method_7586();
        if (foodLevel <= 17 && this.eatCooldown.finished(2000.0)) {
            int slot = this.findFoodHotbar();
            if (slot != -1) {
                AutoWarden.mc.field_1724.method_31548().field_7545 = slot;
                AutoWarden.mc.field_1761.method_2919((class_1657)AutoWarden.mc.field_1724, class_1268.field_5808);
                this.eatCooldown.reset();
            } else {
                int invSlot = this.findFoodInventory();
                if (invSlot != -1) {
                    InventoryTask.swapSlots(invSlot, 0);
                    this.pendingFoodUse = true;
                }
            }
        }
    }

    private boolean needsInvisPotion() {
        if (this.currentState == State.IDLE || this.currentState == State.DEAD_WAITING || this.currentState == State.GOING_TO_SUPPLY_CHEST || this.currentState == State.OPENING_SUPPLY_CHEST || this.currentState == State.TAKING_SUPPLIES || this.currentState == State.DRINKING_INVIS || this.currentState == State.FLEEING_WARDEN || this.currentState == State.WAITING_HUB || this.currentState == State.REJOINING_ANARCHY || this.currentState == State.WAITING_REJOIN) {
            return false;
        }
        class_1293 invis = AutoWarden.mc.field_1724.method_6112(class_1294.field_5905);
        return invis == null || invis.method_5584() < 1200;
    }

    private boolean hasLineOfSight(class_2338 pos) {
        class_243 target;
        if (AutoWarden.mc.field_1724 == null || AutoWarden.mc.field_1687 == null) {
            return false;
        }
        class_243 eyes = AutoWarden.mc.field_1724.method_33571();
        class_3965 result = AutoWarden.mc.field_1687.method_17742(new class_3959(eyes, target = class_243.method_24953((class_2382)pos), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)AutoWarden.mc.field_1724));
        if (result == null) {
            return true;
        }
        class_2338 hitPos = result.method_17777();
        return hitPos.equals((Object)pos);
    }

    private class_2338 findSupplyChest() {
        class_2338 playerPos = AutoWarden.mc.field_1724.method_24515();
        String targetSign = this.signName.getText() != null ? this.signName.getText().toLowerCase() : "warden";
        class_2338 closest = null;
        double closestDist = Double.MAX_VALUE;
        for (int x = -8; x <= 8; ++x) {
            for (int y = -4; y <= 4; ++y) {
                for (int z = -8; z <= 8; ++z) {
                    double dist;
                    class_2338 pos = playerPos.method_10069(x, y, z);
                    if (!AutoWarden.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10034) || !this.hasAdjacentSign(pos, targetSign) || !((dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)pos))) < closestDist)) continue;
                    closestDist = dist;
                    closest = pos;
                }
            }
        }
        return closest;
    }

    private boolean hasAdjacentSign(class_2338 chestPos, String text) {
        class_2338[] signPositions;
        for (class_2338 signPos : signPositions = new class_2338[]{chestPos.method_10084(), chestPos.method_10095(), chestPos.method_10072(), chestPos.method_10078(), chestPos.method_10067(), chestPos.method_10084().method_10095(), chestPos.method_10084().method_10072(), chestPos.method_10084().method_10078(), chestPos.method_10084().method_10067()}) {
            class_2625 sign;
            class_2586 be = AutoWarden.mc.field_1687.method_8321(signPos);
            if (!(be instanceof class_2625) || !this.signContainsText(sign = (class_2625)be, text)) continue;
            return true;
        }
        return false;
    }

    private boolean signContainsText(class_2625 sign, String text) {
        class_8242 front = sign.method_49853();
        for (int i = 0; i < 4; ++i) {
            if (!front.method_49859(i, false).getString().toLowerCase().contains(text)) continue;
            return true;
        }
        class_8242 back = sign.method_49854();
        for (int i = 0; i < 4; ++i) {
            if (!back.method_49859(i, false).getString().toLowerCase().contains(text)) continue;
            return true;
        }
        return false;
    }

    private class_2338 findDumpChest() {
        class_2338 playerPos = AutoWarden.mc.field_1724.method_24515();
        String targetSign = this.signName.getText() != null ? this.signName.getText().toLowerCase() : "warden";
        class_2338 closest = null;
        double closestDist = Double.MAX_VALUE;
        for (int x = -8; x <= 8; ++x) {
            for (int y = -4; y <= 4; ++y) {
                for (int z = -8; z <= 8; ++z) {
                    double dist;
                    class_2338 pos = playerPos.method_10069(x, y, z);
                    if (!AutoWarden.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10034) || this.hasAdjacentSign(pos, targetSign) || !((dist = AutoWarden.mc.field_1724.method_19538().method_1025(class_243.method_24953((class_2382)pos))) < closestDist)) continue;
                    closestDist = dist;
                    closest = pos;
                }
            }
        }
        return closest;
    }

    private boolean isInvisPotion(class_1799 stack) {
        if (stack.method_7909() != class_1802.field_8574 && stack.method_7909() != class_1802.field_8436 && stack.method_7909() != class_1802.field_8150) {
            return false;
        }
        String name = stack.method_7964().getString().toLowerCase();
        return name.contains("\u043d\u0435\u0432\u0438\u0434\u0438\u043c") || name.contains("invisib");
    }

    private int countInvisInInventory() {
        int count = 0;
        for (int i = 0; i < 36; ++i) {
            if (!this.isInvisPotion(AutoWarden.mc.field_1724.method_31548().method_5438(i))) continue;
            ++count;
        }
        return count;
    }

    private int countFoodInInventory() {
        int count = 0;
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = AutoWarden.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8071) continue;
            count += stack.method_7947();
        }
        return count;
    }

    private int findInvisPotionHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (!this.isInvisPotion(AutoWarden.mc.field_1724.method_31548().method_5438(i))) continue;
            return i;
        }
        return -1;
    }

    private int findInvisPotionInventory() {
        for (int i = 9; i < 36; ++i) {
            if (!this.isInvisPotion(AutoWarden.mc.field_1724.method_31548().method_5438(i))) continue;
            return i;
        }
        return -1;
    }

    private int findFoodHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoWarden.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_8071) continue;
            return i;
        }
        return -1;
    }

    private int findFoodInventory() {
        for (int i = 9; i < 36; ++i) {
            if (AutoWarden.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_8071) continue;
            return i;
        }
        return -1;
    }

    private void interactWithBlock(class_2338 pos) {
        if (AutoWarden.mc.field_1761 == null || AutoWarden.mc.field_1724 == null) {
            return;
        }
        class_243 center = class_243.method_24953((class_2382)pos).method_1031(0.2, 0.375, 0.2);
        class_2350 side = class_2350.method_10147((float)((float)(center.field_1352 - AutoWarden.mc.field_1724.method_23317())), (float)((float)(center.field_1351 - AutoWarden.mc.field_1724.method_23318())), (float)((float)(center.field_1350 - AutoWarden.mc.field_1724.method_23321()))).method_10153();
        AutoWarden.mc.field_1761.method_2896(AutoWarden.mc.field_1724, class_1268.field_5808, new class_3965(center, side, pos, false));
        AutoWarden.mc.field_1724.method_6104(class_1268.field_5808);
    }

    private void baritoneGoto(class_2338 pos) {
        if (!this.moveTimer.finished(800.0)) {
            return;
        }
        this.sendCommand("#goto " + pos.method_10263() + " " + pos.method_10264() + " " + pos.method_10260());
        this.moveTimer.reset();
    }

    private void stopBaritone() {
        this.sendCommand("#stop");
    }

    private void sendCommand(String command) {
        if (AutoWarden.mc.field_1724 == null || AutoWarden.mc.field_1724.field_3944 == null) {
            return;
        }
        if (Network.isPvp()) {
            return;
        }
        AutoWarden.mc.field_1724.field_3944.method_45729(command);
    }

    private String formatTime(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (AutoWarden.mc.field_1724 == null || AutoWarden.mc.field_1687 == null || this.currentState == State.IDLE) {
            return;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        FontRenderer font = Fonts.getSize(11);
        FontRenderer fontBold = Fonts.getSize(12);
        float x = 4.0f;
        float y = (float)mc.method_22683().method_4502() / 2.0f - 60.0f;
        float padding = 4.0f;
        float lineHeight = 12.0f;
        String stateName = this.getStateDisplayName();
        String timerText = this.targetChestPos != null ? this.formatTime(this.getCurrentChestRemaining()) : "--:--";
        String itemsText = String.valueOf(this.totalItemsLooted);
        String cyclesText = String.valueOf(this.cyclesCompleted);
        String lootText = String.valueOf(this.lootedSlots);
        String[] lines = new String[]{"\u00a7fAutoWarden", "\u00a77\u0421\u0442\u0430\u0434\u0438\u044f: \u00a7e" + stateName, "\u00a77\u0422\u0430\u0439\u043c\u0435\u0440: \u00a7a" + timerText, "\u00a77\u041b\u0443\u0442 (\u0441\u0435\u0439\u0447\u0430\u0441): \u00a7b" + lootText, "\u00a77\u041b\u0443\u0442 (\u0432\u0441\u0435\u0433\u043e): \u00a7b" + itemsText, "\u00a77\u0426\u0438\u043a\u043b\u044b: \u00a7d" + cyclesText};
        float maxWidth = 0.0f;
        for (String line : lines) {
            float w = font.getStringWidth(line);
            if (!(w > maxWidth)) continue;
            maxWidth = w;
        }
        float boxWidth = maxWidth + padding * 2.0f;
        float boxHeight = (float)lines.length * lineHeight + padding * 2.0f;
        rectangle.render(ShapeProperties.create(matrix, x, y, boxWidth, boxHeight).round(5.0f).color(-1442840576).build());
        float textY = y + padding;
        for (int i = 0; i < lines.length; ++i) {
            FontRenderer f = i == 0 ? fontBold : font;
            f.drawString(matrix, lines[i], x + padding, textY, -1);
            textY += lineHeight;
        }
    }

    private String getStateDisplayName() {
        return switch (this.currentState.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> "\u041e\u0436\u0438\u0434\u0430\u043d\u0438\u0435";
            case 1 -> "\u0418\u0434\u0443 \u043a \u043f\u0440\u0438\u043f\u0430\u0441\u0430\u043c";
            case 2 -> "\u041e\u0442\u043a\u0440\u044b\u0432\u0430\u044e \u0441\u0443\u043d\u0434\u0443\u043a";
            case 3 -> "\u0411\u0435\u0440\u0443 \u043f\u0440\u0438\u043f\u0430\u0441\u044b";
            case 4 -> "\u041f\u044c\u044e \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c";
            case 5 -> "\u0422\u041f \u043a \u0432\u0430\u0440\u0434\u0435\u043d\u0443";
            case 6 -> "\u0416\u0434\u0443 \u0422\u041f";
            case 7 -> "\u041f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435";
            case 8 -> "\u0418\u0434\u0443 \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443";
            case 9 -> "\u0421\u0442\u0430\u0432\u043b\u044e \u0445\u043e\u043c";
            case 26 -> "\u0416\u0434\u0443 \u0443 \u0441\u0443\u043d\u0434\u0443\u043a\u0430 " + this.formatTime(this.getCurrentChestRemaining());
            case 10 -> "\u0422\u041f \u043d\u0430 /darena";
            case 11 -> "\u0416\u0434\u0443 /darena";
            case 12 -> "\u0418\u0449\u0443 \u0438\u0433\u043b\u043e\u0431\u0440\u044e\u0445";
            case 13 -> "\u0416\u0434\u0443 \u0442\u0430\u0439\u043c\u0435\u0440";
            case 14 -> "\u0422\u041f \u043a \u0441\u0443\u043d\u0434\u0443\u043a\u0443";
            case 15 -> "\u0416\u0434\u0443 \u0422\u041f";
            case 16 -> "\u041b\u0443\u0442\u0430\u044e \u0441\u0443\u043d\u0434\u0443\u043a";
            case 17 -> "\u0422\u041f \u043d\u0430 \u0431\u0430\u0437\u0443";
            case 18 -> "\u0416\u0434\u0443 \u0422\u041f";
            case 19 -> "\u0421\u043a\u0438\u0434\u044b\u0432\u0430\u044e \u043b\u0443\u0442";
            case 20 -> "\u0423\u0434\u0430\u043b\u044f\u044e \u0445\u043e\u043c";
            case 21 -> "\u0411\u0435\u0433\u0443 \u0432 \u0445\u0430\u0431!";
            case 22 -> "\u0416\u0434\u0443 \u0432 \u0445\u0430\u0431\u0435";
            case 23 -> "\u0417\u0430\u0445\u043e\u0436\u0443 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e";
            case 24 -> "\u0416\u0434\u0443 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u044f";
            case 25 -> "\u0423\u043c\u0435\u0440, \u0436\u0434\u0443...";
        };
    }

    private static enum State {
        IDLE,
        GOING_TO_SUPPLY_CHEST,
        OPENING_SUPPLY_CHEST,
        TAKING_SUPPLIES,
        DRINKING_INVIS,
        TELEPORTING_TO_WARDEN,
        WAITING_WARDEN_TP,
        SEARCHING_CHESTS,
        WALKING_TO_CHEST,
        SETTING_HOME_AT_CHEST,
        TELEPORTING_TO_DARENA,
        WAITING_DARENA,
        BROWSING_DARENA,
        WAITING_TIMER,
        TELEPORTING_BACK_TO_CHEST,
        WAITING_CHEST_TP,
        LOOTING_CHEST,
        TELEPORTING_TO_BASE,
        WAITING_BASE_TP,
        DUMPING_LOOT,
        DELETING_TEMP_HOME,
        FLEEING_WARDEN,
        WAITING_HUB,
        REJOINING_ANARCHY,
        WAITING_REJOIN,
        DEAD_WAITING,
        WAITING_AT_CHEST;

    }
}
