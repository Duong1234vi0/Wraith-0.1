/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1542
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.math.task.TaskPriority;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_3532;

public class AutoPilot
extends Module {
    private static final class_310 mc = class_310.method_1551();
    public class_1542 target;
    private float lastYaw;
    private float lastPitch;
    private float targetYaw;
    private float targetPitch;
    Turns rot = new Turns(0.0f, 0.0f);

    public AutoPilot() {
        super("AutoPilot", "AutoPilot", ModuleCategory.MISC);
    }

    public static AutoPilot getInstance() {
        return Instance.get(AutoPilot.class);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (AutoPilot.mc.field_1724 == null || AutoPilot.mc.field_1687 == null || mc.method_1562() == null) {
            this.target = null;
            return;
        }
        this.target = this.findTarget();
        if (this.target != null) {
            double dx = this.target.method_19538().method_10216() - AutoPilot.mc.field_1724.method_19538().method_10216();
            double dy = this.target.method_19538().method_10214() - (AutoPilot.mc.field_1724.method_19538().method_10214() + (double)AutoPilot.mc.field_1724.method_18381(AutoPilot.mc.field_1724.method_18376()));
            double dz = this.target.method_19538().method_10215() - AutoPilot.mc.field_1724.method_19538().method_10215();
            this.targetYaw = (float)(Math.atan2(dz, dx) * 180.0 / Math.PI - 90.0);
            this.targetPitch = (float)(-Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180.0 / Math.PI);
            float maxRotation = 1024.0f;
            float yawDiff = class_3532.method_15393((float)(this.targetYaw - this.lastYaw));
            float yawStep = class_3532.method_15363((float)yawDiff, (float)(-maxRotation), (float)maxRotation);
            this.lastYaw += yawStep;
            float pitchDiff = class_3532.method_15393((float)(this.targetPitch - this.lastPitch));
            float pitchStep = class_3532.method_15363((float)pitchDiff, (float)(-maxRotation), (float)maxRotation);
            this.lastPitch += pitchStep;
            AutoPilot.mc.field_1724.method_36456(this.lastYaw);
            AutoPilot.mc.field_1724.method_36457(this.lastPitch);
            this.rot.setYaw(this.lastYaw);
            this.rot.setPitch(this.lastPitch);
            TurnsConnection.INSTANCE.rotateTo(this.rot, TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
        } else {
            this.lastYaw = AutoPilot.mc.field_1724.method_36454();
            this.lastPitch = AutoPilot.mc.field_1724.method_36455();
        }
    }

    private class_1542 findTarget() {
        List items = AutoPilot.mc.field_1687.method_8390(class_1542.class, AutoPilot.mc.field_1724.method_5829().method_1014(50.0), e -> e.method_5805() && this.isValidItem((class_1542)e)).stream().sorted(Comparator.comparingDouble(e -> AutoPilot.mc.field_1724.method_5858((class_1297)e))).collect(Collectors.toList());
        return items.isEmpty() ? null : (class_1542)items.get(0);
    }

    private boolean isValidItem(class_1542 item) {
        class_1799 stack = item.method_6983();
        return stack.method_7909() == class_1802.field_8849 || stack.method_7909() == class_1802.field_8575 || stack.method_7909() == class_1802.field_8367 || stack.method_7909().toString().contains("_spawn_egg");
    }

    public void onDisable() {
        this.target = null;
        if (AutoPilot.mc.field_1724 != null) {
            mc.method_1562().method_52787((class_2596)new class_2828.class_2830(AutoPilot.mc.field_1724.method_19538().method_10216(), AutoPilot.mc.field_1724.method_19538().method_10214(), AutoPilot.mc.field_1724.method_19538().method_10215(), AutoPilot.mc.field_1724.method_36454(), AutoPilot.mc.field_1724.method_36455(), AutoPilot.mc.field_1724.method_24828(), false));
        }
    }
}
