/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_2596
 *  net.minecraft.class_2645
 *  net.minecraft.class_2813
 *  net.minecraft.class_2815
 *  net.minecraft.class_304
 *  net.minecraft.class_3675
 *  net.minecraft.class_408
 *  net.minecraft.class_437
 *  net.minecraft.class_471
 *  net.minecraft.class_490
 *  net.minecraft.class_497
 *  net.minecraft.class_498
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.container.CloseScreenEvent;
import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2813;
import net.minecraft.class_2815;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_471;
import net.minecraft.class_490;
import net.minecraft.class_497;
import net.minecraft.class_498;

public class GuiMove
extends Module {
    public static final SelectSetting mode = new SelectSetting("\u041e\u0431\u0445\u043e\u0434", "\u0420\u0435\u0436\u0438\u043c \u043e\u0431\u0445\u043e\u0434\u0430 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438 \u0432 GUI").value("Vanilla", "Reallyworld", "Funtime", "Holyworld", "SpookyTime", "LonyGrief", "\u041b\u0435\u0433\u0438\u0442").selected("SpookyTime");
    private final List<class_2596<?>> pendingPackets = new ArrayList();
    private final Queue<class_2813> spookyQueue = new LinkedList<class_2813>();
    private final StopWatch holyTimer = new StopWatch();
    private final StopWatch funtimeTimer = new StopWatch();
    private final StopWatch lonyTimer = new StopWatch();
    private int reallyworldTicks;
    private int holyDelay;
    private boolean holyClosing;
    private int spookyClickDelay;
    private boolean spookyMovementLock;
    private boolean spookyHadCursor;
    public int movementBlockTicks;
    public boolean bypassActive;
    private long spookyLastClickMs;

    public static GuiMove getInstance() {
        return Instance.get(GuiMove.class);
    }

    public GuiMove() {
        super("GuiMove", "GuiMove", ModuleCategory.MOVEMENT);
        this.setup(mode);
    }

    public boolean isReallyworld() {
        return this.isState() && mode.isSelected("Reallyworld");
    }

    public boolean isFuntime() {
        return this.isState() && mode.isSelected("Funtime");
    }

    public boolean isHolyworld() {
        return this.isState() && mode.isSelected("Holyworld");
    }

    public boolean isSpookyTime() {
        return this.isState() && mode.isSelected("SpookyTime");
    }

    public boolean isLonyGrief() {
        return this.isState() && mode.isSelected("LonyGrief");
    }

    public boolean isLegit() {
        return this.isState() && mode.isSelected("\u041b\u0435\u0433\u0438\u0442");
    }

    public boolean shouldBlockMovement() {
        return !this.isState() || this.movementBlockTicks > 0;
    }

    public void notifySpookyClick() {
        if (this.isSpookyTime()) {
            this.spookyClickDelay = this.resolveSpookyDelay(null) + 1;
        }
    }

    public void setBypassActive(boolean active) {
        this.bypassActive = active;
    }

    public void beginBypassForSwap() {
        this.beginBypassForSwap(mode.getSelected());
    }

    public void beginBypassForSwap(String bypassMode) {
        this.bypassActive = true;
        switch (bypassMode) {
            case "Funtime": {
                this.funtimeTimer.reset();
                break;
            }
            case "Holyworld": {
                this.holyDelay = 2;
                this.holyClosing = true;
                this.holyTimer.reset();
                break;
            }
            case "Reallyworld": {
                this.reallyworldTicks = 3;
                break;
            }
            case "LonyGrief": {
                this.lonyTimer.reset();
                break;
            }
            case "SpookyTime": {
                this.notifySpookyClick();
            }
        }
    }

    public boolean isBypassMode(String bypassMode) {
        return bypassMode != null && bypassMode.equals(mode.getSelected());
    }

    public void flushSpookyQueue() {
        if (this.spookyQueue.isEmpty() || GuiMove.mc.field_1724 == null) {
            return;
        }
        while (!this.spookyQueue.isEmpty()) {
            class_2813 packet = this.spookyQueue.poll();
            if (packet == null) continue;
            mc.method_1562().method_52787((class_2596)packet);
        }
        this.movementBlockTicks = 0;
    }

    @Override
    public void deactivate() {
        this.flushSpookyQueue();
        this.pendingPackets.clear();
        this.movementBlockTicks = 1;
        this.reallyworldTicks = 0;
        this.holyDelay = 0;
        this.spookyClickDelay = 0;
        this.spookyMovementLock = false;
        this.bypassActive = false;
        this.holyClosing = false;
        super.deactivate();
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_2596<?> class_25962;
        if (!this.isState() || GuiMove.mc.field_1724 == null || this.isLegit()) {
            return;
        }
        if (this.isLonyGrief()) {
            class_2596<?> class_25963;
            if (!this.bypassActive && event.isSend() && (class_25963 = event.getPacket()) instanceof class_2813) {
                class_2813 click = (class_2813)class_25963;
                if (GuiMove.mc.field_1755 instanceof class_490 && !this.spookyMovementLock) {
                    this.pendingPackets.add((class_2596<?>)click);
                    event.cancel();
                }
            }
            return;
        }
        if (this.isSpookyTime()) {
            class_2596<?> class_25964;
            if (!event.isSend() && event.getPacket() instanceof class_2645) {
                event.cancel();
                return;
            }
            if (event.isSend() && (class_25964 = event.getPacket()) instanceof class_2813) {
                class_2813 click = (class_2813)class_25964;
                this.spookyLastClickMs = System.currentTimeMillis();
                if (!this.bypassActive && this.isPlayerMoving() && click.method_12192() != -1) {
                    this.notifySpookyClick(click.method_12195());
                    if (!this.spookyMovementLock && this.enqueueSpooky(click)) {
                        this.movementBlockTicks = 0;
                        event.cancel();
                    }
                }
            }
            return;
        }
        if (!this.usesBypass()) {
            class_2645 screen;
            class_2596<?> class_25965;
            if (!event.isSend() && (class_25965 = event.getPacket()) instanceof class_2645 && (screen = (class_2645)class_25965).method_36148() == 0) {
                event.cancel();
            }
            return;
        }
        if (event.isSend() && (class_25962 = event.getPacket()) instanceof class_2813) {
            class_2813 click = (class_2813)class_25962;
            if ((!this.pendingPackets.isEmpty() || Simulations.hasPlayerMovement()) && InventoryFlowManager.shouldSkipExecution()) {
                this.pendingPackets.add((class_2596<?>)click);
                event.cancel();
            }
        }
    }

    @EventHandler
    public void onClickSlot(ClickSlotEvent event) {
        if (!this.isState() || GuiMove.mc.field_1724 == null) {
            return;
        }
        if (this.isLegit()) {
            if (Simulations.hasPlayerMovement()) {
                event.cancel();
            }
            return;
        }
        if (!this.usesBypass() || this.isSpookyTime() || this.isLonyGrief()) {
            return;
        }
        class_1713 actionType = event.getActionType();
        if ((!this.pendingPackets.isEmpty() || Simulations.hasPlayerMovement()) && (event.getButton() == 1 && actionType != class_1713.field_7791 && actionType != class_1713.field_7795 || actionType == class_1713.field_7793)) {
            event.cancel();
        }
    }

    @EventHandler
    public void onCloseScreen(CloseScreenEvent event) {
        if (!this.isState() || this.isLegit() || !this.usesBypass() || this.pendingPackets.isEmpty()) {
            return;
        }
        if (this.isSpookyTime() || this.isLonyGrief()) {
            return;
        }
        InventoryFlowManager.addTask(() -> {
            this.pendingPackets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
            this.pendingPackets.clear();
            InventoryTask.updateSlots();
        });
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (!this.isState() || GuiMove.mc.field_1724 == null || this.isLegit()) {
            return;
        }
        if (this.isReallyworld() && this.reallyworldTicks > 0) {
            event.inputNone();
            return;
        }
        if (this.isFuntime() && !this.funtimeTimer.finished(190.0)) {
            event.inputNone();
            return;
        }
        if (this.isHolyworld() && this.holyDelay > 0 && !this.holyTimer.finished(222.0)) {
            event.inputNone();
            return;
        }
        if (this.isSpookyTime()) {
            if (System.currentTimeMillis() - this.spookyLastClickMs <= 170L) {
                event.inputNone();
            } else if (this.spookyMovementLock) {
                event.inputNone();
            }
            return;
        }
        if (this.isLonyGrief() && this.bypassActive && !this.lonyTimer.finished(50.0)) {
            event.inputNone();
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.isState() || GuiMove.mc.field_1724 == null) {
            return;
        }
        if (this.isLegit()) {
            if (!InventoryTask.isServerScreen() && InventoryFlowManager.shouldSkipExecution()) {
                InventoryFlowManager.updateMoveKeys();
            }
            return;
        }
        if (this.reallyworldTicks > 0) {
            --this.reallyworldTicks;
        }
        if (this.holyDelay > 0) {
            --this.holyDelay;
        }
        if (this.isSpookyTime()) {
            if (this.spookyHadCursor && this.spookyMovementLock && this.spookyClickDelay > 0) {
                this.flushSpookyQueue();
            }
            this.spookyHadCursor = this.spookyMovementLock;
            if (this.shouldFreezeSpookyClick()) {
                this.notifySpookyClick(null);
            } else if (this.spookyClickDelay > 0) {
                --this.spookyClickDelay;
            }
            this.spookyMovementLock = this.spookyClickDelay > 0;
            ++this.movementBlockTicks;
            if (this.spookyMovementLock) {
                this.movementBlockTicks = 0;
            }
        }
        if (this.isBlockedScreen()) {
            return;
        }
        if (this.shouldPauseMovement()) {
            return;
        }
        this.updateMovementKeys();
        if (this.bypassActive && this.isReallyworld()) {
            this.sendPendingAndClose();
            this.bypassActive = false;
        }
        if (this.bypassActive && this.isFuntime()) {
            mc.method_1507((class_437)new class_490((class_1657)GuiMove.mc.field_1724));
            this.sendPendingAndClose();
            this.bypassActive = false;
            mc.method_1507(null);
        }
        if (this.holyClosing && this.isHolyworld() && this.holyDelay == 0) {
            this.sendPendingAndClose();
            this.holyClosing = false;
            this.bypassActive = false;
        }
        if (this.bypassActive && this.isLonyGrief() && this.lonyTimer.finished(50.0)) {
            this.sendPendingAndClose();
            this.bypassActive = false;
            if (GuiMove.mc.field_1755 != null) {
                mc.method_1507(null);
            }
        }
    }

    private boolean usesBypass() {
        return this.isReallyworld() || this.isFuntime() || this.isHolyworld() || this.isSpookyTime() || this.isLonyGrief();
    }

    private boolean isBlockedScreen() {
        return GuiMove.mc.field_1755 instanceof class_408 || GuiMove.mc.field_1755 instanceof class_498 || GuiMove.mc.field_1755 instanceof class_471 || GuiMove.mc.field_1755 instanceof class_497;
    }

    private boolean shouldPauseMovement() {
        if (this.isReallyworld() && this.reallyworldTicks > 0) {
            return true;
        }
        if (this.isFuntime() && !this.funtimeTimer.finished(100.0)) {
            return true;
        }
        if (this.isHolyworld() && this.holyDelay > 0 && !this.holyTimer.finished(125.0)) {
            return true;
        }
        if (this.isSpookyTime() && this.spookyMovementLock) {
            return true;
        }
        return this.isLonyGrief() && this.bypassActive && !this.lonyTimer.finished(50.0);
    }

    private void updateMovementKeys() {
        class_304[] keys;
        for (class_304 key : keys = new class_304[]{GuiMove.mc.field_1690.field_1894, GuiMove.mc.field_1690.field_1881, GuiMove.mc.field_1690.field_1913, GuiMove.mc.field_1690.field_1849, GuiMove.mc.field_1690.field_1903}) {
            key.method_23481(class_3675.method_15987((long)mc.method_22683().method_4490(), (int)key.method_1429().method_1444()));
        }
    }

    private void sendPendingAndClose() {
        this.pendingPackets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
        this.pendingPackets.clear();
        if (GuiMove.mc.field_1724 != null) {
            mc.method_1562().method_52787((class_2596)new class_2815(GuiMove.mc.field_1724.field_7512.field_7763));
        }
    }

    private boolean isPlayerMoving() {
        return GuiMove.mc.field_1724.field_3913.field_3905 != 0.0f || GuiMove.mc.field_1724.field_3913.field_3907 != 0.0f || this.spookyMovementLock;
    }

    private boolean shouldFreezeSpookyClick() {
        if (!(GuiMove.mc.field_1755 instanceof class_490)) {
            return false;
        }
        if (!this.isPlayerMoving()) {
            return false;
        }
        return !GuiMove.mc.field_1724.field_7512.method_34255().method_7960();
    }

    private int resolveSpookyDelay(class_1713 type) {
        if (type == null) {
            return 2;
        }
        if (type == class_1713.field_7790) {
            return 1;
        }
        return this.spookyClickDelay > 1 ? 2 : 3;
    }

    private void notifySpookyClick(class_1713 type) {
        this.spookyClickDelay = this.resolveSpookyDelay(type) + 1;
    }

    private boolean enqueueSpooky(class_2813 packet) {
        return !this.spookyQueue.contains(packet) && this.spookyQueue.add(packet);
    }

    public List<class_2596<?>> getPendingPackets() {
        return this.pendingPackets;
    }

    public Queue<class_2813> getSpookyQueue() {
        return this.spookyQueue;
    }

    public StopWatch getHolyTimer() {
        return this.holyTimer;
    }

    public StopWatch getFuntimeTimer() {
        return this.funtimeTimer;
    }

    public StopWatch getLonyTimer() {
        return this.lonyTimer;
    }

    public int getReallyworldTicks() {
        return this.reallyworldTicks;
    }

    public int getHolyDelay() {
        return this.holyDelay;
    }

    public boolean isHolyClosing() {
        return this.holyClosing;
    }

    public int getSpookyClickDelay() {
        return this.spookyClickDelay;
    }

    public boolean isSpookyMovementLock() {
        return this.spookyMovementLock;
    }

    public boolean isSpookyHadCursor() {
        return this.spookyHadCursor;
    }

    public int getMovementBlockTicks() {
        return this.movementBlockTicks;
    }

    public boolean isBypassActive() {
        return this.bypassActive;
    }

    public long getSpookyLastClickMs() {
        return this.spookyLastClickMs;
    }
}
