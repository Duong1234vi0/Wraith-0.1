/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1703
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.movement.autoMystGlider.handers;

import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.features.impl.movement.autoMystGlider.parsers.SpookyParser;
import code.alphadlc.features.impl.movement.autoMystGlider.receivers.AutoMystGliderModule;
import code.alphadlc.features.impl.movement.autoMystGlider.states.ConnectionState;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlidePhase;
import code.alphadlc.features.impl.movement.autoMystGlider.states.StealState;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.rotations.constructor.LinearConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.task.TaskPriority;
import java.util.Stack;
import net.minecraft.class_1268;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3965;

public class StealHandler
implements QuickImports {
    final AutoMystGliderModule module;
    final Stack<StealState> deque = new Stack();
    StealState stealState;
    long lastClickTime = -1L;
    long lastQuickMoveTime = -1L;
    long reconnectTime;
    boolean finished = false;

    public StealHandler(AutoMystGliderModule module) {
        this.module = module;
    }

    public void onTick() {
        if (this.deque.isEmpty() && !this.finished && !SpookyParser.isAnarchy(-1)) {
            this.updateChests(4);
        }
        if (!this.finished && this.allChestWasEmpty() && System.currentTimeMillis() - this.reconnectTime > 5000L) {
            this.finished = true;
            ConnectionState connection = this.module.getAutoGliderController().getConnectionHandler().getConnection();
            connection.getStealHandler().onDisconnect();
            connection.hub(GlidePhase.HUB_TO_HOME);
            return;
        }
        if (this.deque.isEmpty()) {
            return;
        }
        this.applyState();
        if (this.stealState == null) {
            return;
        }
        if (!this.isGeneric(this.stealState.getBlockPos())) {
            this.deque.remove(this.stealState);
            this.stealState = null;
            return;
        }
        class_1703 class_17032 = StealHandler.mc.field_1724.field_7512;
        if (class_17032 instanceof class_1707) {
            class_1707 handler = (class_1707)class_17032;
            this.stealGenericHandler(handler);
            if (this.isEmpty(handler)) {
                this.stealState.setEmpty(true);
                this.stealState = null;
                StealHandler.mc.field_1755.method_25419();
            }
        } else if (System.currentTimeMillis() - this.lastClickTime > 800L) {
            this.openCurrentState();
            this.lastClickTime = System.currentTimeMillis();
        }
    }

    private void stealGenericHandler(class_1707 handler) {
        for (class_1735 slot : handler.field_7761) {
            if (slot.field_7871 == StealHandler.mc.field_1724.method_31548() || !slot.method_7681() || System.currentTimeMillis() - this.lastQuickMoveTime <= 150L) continue;
            InventoryTask.clickSlot(slot, 0, class_1713.field_7794, true);
            this.lastQuickMoveTime = System.currentTimeMillis();
            break;
        }
    }

    private void openCurrentState() {
        class_2338 pos = this.stealState.getBlockPos();
        class_243 center = class_243.method_24953((class_2382)pos).method_1031(0.2, 0.375, 0.2);
        class_2350 side = class_2350.method_10142((double)(center.field_1352 - StealHandler.mc.field_1724.method_23317()), (double)(center.field_1351 - StealHandler.mc.field_1724.method_23318()), (double)(center.field_1350 - StealHandler.mc.field_1724.method_23321())).method_10153();
        StealHandler.mc.field_1761.method_2896(StealHandler.mc.field_1724, class_1268.field_5808, new class_3965(center, side, pos, false));
        StealHandler.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public void onRotationUpdate(RotationUpdateEvent event) {
        if (this.stealState == null) {
            return;
        }
        if (!this.isGeneric(this.stealState.getBlockPos())) {
            this.deque.remove(this.stealState);
            this.stealState = null;
            return;
        }
        class_2338 pos = this.stealState.getBlockPos();
        Turns current = TurnsConnection.INSTANCE.getRotation();
        Turns target = MathAngle.calculateAngle(class_243.method_24953((class_2382)pos).method_1031(0.2, 0.375, 0.2));
        Turns delta = MathAngle.calculateDelta(current, target);
        Turns end = new Turns(current.getYaw() + delta.getYaw(), current.getPitch() + delta.getPitch());
        TurnsConnection.INSTANCE.rotateTo(end, new TurnsConfig(new LinearConstructor(), true, false), TaskPriority.HIGH_IMPORTANCE_1, this.module);
    }

    private boolean allChestWasEmpty() {
        if (this.deque.isEmpty()) {
            return false;
        }
        for (StealState state : this.deque) {
            if (state.isEmpty()) continue;
            return false;
        }
        return true;
    }

    private void updateChests(int range) {
        class_2338 base = StealHandler.mc.field_1724.method_24515();
        for (class_2338 pos : class_2338.method_10097((class_2338)base.method_10069(-range, -range, -range), (class_2338)base.method_10069(range, range, range))) {
            class_2680 state = StealHandler.mc.field_1687.method_8320(pos);
            if (!state.method_27852(class_2246.field_10034) && !state.method_27852(class_2246.field_10380) && !state.method_27852(class_2246.field_10443) || !this.deque.stream().noneMatch(s -> s.getBlockPos().equals((Object)pos))) continue;
            this.deque.add(new StealState(pos.method_10062()));
        }
    }

    private void applyState() {
        for (StealState state : this.deque) {
            if (state.isEmpty()) continue;
            this.stealState = state;
            return;
        }
        this.stealState = null;
    }

    private boolean isGeneric(class_2338 pos) {
        class_2680 state = StealHandler.mc.field_1687.method_8320(pos);
        return state.method_27852(class_2246.field_10034) || state.method_27852(class_2246.field_10380) || state.method_27852(class_2246.field_10443);
    }

    private boolean isEmpty(class_1707 handler) {
        return handler.field_7761.stream().filter(s -> s.field_7871 != StealHandler.mc.field_1724.method_31548()).noneMatch(class_1735::method_7681);
    }

    public void onConnect() {
        this.reconnectTime = System.currentTimeMillis();
    }

    public void onDisconnect() {
        this.reload();
    }

    public void reload() {
        this.deque.clear();
        this.stealState = null;
        this.finished = false;
    }
}
