/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1685
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_490
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.simulate.ArrowSimulation;
import code.alphadlc.utils.interactions.simulate.PlayerSimulation;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1685;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_490;

public class AutoDodge
extends Module
implements QuickImports {
    private final BooleanSetting allowRotation = new BooleanSetting("\u0420\u0430\u0437\u0440\u0435\u0448\u0438\u0442\u044c \u043f\u043e\u0432\u043e\u0440\u043e\u0442", "\u0420\u0430\u0437\u0440\u0435\u0448\u0438\u0442\u044c \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u043f\u043e\u0432\u043e\u0440\u043e\u0442\u0430 \u0434\u043b\u044f \u0443\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u044f").setValue(false);
    private final BooleanSetting allowJump = new BooleanSetting("\u0420\u0430\u0437\u0440\u0435\u0448\u0438\u0442\u044c \u043f\u0440\u044b\u0436\u043e\u043a", "\u0420\u0430\u0437\u0440\u0435\u0448\u0438\u0442\u044c \u043f\u0440\u044b\u0436\u043e\u043a \u0434\u043b\u044f \u0443\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u044f").setValue(true).visible(() -> this.allowRotation.isValue());
    private final BooleanSetting allowTimer = new BooleanSetting("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0442\u0430\u0439\u043c\u0435\u0440", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0442\u0430\u0439\u043c\u0435\u0440 \u0434\u043b\u044f \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f \u0443\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u044f").setValue(false);
    private final SliderSettings timerSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0442\u0430\u0439\u043c\u0435\u0440\u0430", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0442\u0430\u0439\u043c\u0435\u0440\u0430 \u043f\u0440\u0438 \u0443\u043a\u043b\u043e\u043d\u0435\u043d\u0438\u0438").setValue(2.0f).range(1.0f, 10.0f).step(0.1f).visible(() -> this.allowTimer.isValue());
    private final MultiSelectSetting ignore = new MultiSelectSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c", "\u0423\u0441\u043b\u043e\u0432\u0438\u044f, \u043f\u0440\u0438 \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u043c\u043e\u0434\u0443\u043b\u044c \u043d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442").value("\u041e\u0442\u043a\u0440\u044b\u0442\u044b\u0439 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u0421\u043a\u0430\u0444\u0444\u043e\u043b\u0434");
    private static final double SAFE_DISTANCE_WITH_PADDING = 0.7;
    private static final int MAX_SIMULATION_TICKS = 80;

    public AutoDodge() {
        super("AutoDodge", "AutoDodge", ModuleCategory.COMBAT);
        this.setup(this.allowRotation, this.allowJump, this.allowTimer, this.timerSpeed, this.ignore);
    }

    @Override
    public boolean isState() {
        if (!super.isState()) {
            return false;
        }
        if (PlayerInteractionHelper.nullCheck()) {
            return false;
        }
        if (!this.ignore.isSelected("\u041e\u0442\u043a\u0440\u044b\u0442\u044b\u0439 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c") && AutoDodge.mc.field_1755 instanceof class_490) {
            return false;
        }
        return this.ignore.isSelected("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430") || !AutoDodge.mc.field_1724.method_6115();
    }

    @EventHandler
    public void onInput(InputEvent e) {
        if (!this.isState()) {
            return;
        }
        List<class_1297> arrows = this.findFlyingArrows();
        if (arrows.isEmpty()) {
            return;
        }
        PlayerSimulation simulatedPlayer = PlayerSimulation.fromClientPlayer(PlayerSimulation.SimulatedPlayerInput.fromClientPlayer(AutoDodge.mc.field_1724.field_3913.field_54155));
        HitInfo hitInfo = this.getInflictedHits(simulatedPlayer, arrows, 80, 0.7);
        if (hitInfo == null) {
            return;
        }
        DodgePlan dodgePlan = this.planEvasion(hitInfo);
        if (dodgePlan == null) {
            return;
        }
        if (dodgePlan.forward != 0.0f || dodgePlan.sideways != 0.0f) {
            e.setDirectional(dodgePlan.forward > 0.0f, dodgePlan.forward < 0.0f, dodgePlan.sideways > 0.0f, dodgePlan.sideways < 0.0f);
        }
        if (this.allowRotation.isValue() && dodgePlan.yawChange != null) {
            AutoDodge.mc.field_1724.method_36456(dodgePlan.yawChange.floatValue());
        }
        if (dodgePlan.shouldJump && this.allowRotation.isValue() && this.allowJump.isValue() && AutoDodge.mc.field_1724.method_24828()) {
            e.setJump(true);
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
    }

    private List<class_1297> findFlyingArrows() {
        ArrayList<class_1297> arrows = new ArrayList<class_1297>();
        if (AutoDodge.mc.field_1687 == null) {
            return arrows;
        }
        for (class_1297 entity : AutoDodge.mc.field_1687.method_18112()) {
            if (!(entity instanceof class_1667) && !(entity instanceof class_1665) && !(entity instanceof class_1685) || entity.method_24828()) continue;
            arrows.add(entity);
        }
        return arrows;
    }

    private HitInfo getInflictedHits(PlayerSimulation simulatedPlayer, List<class_1297> arrows, int maxTicks, double hitboxExpansion) {
        ArrayList<ArrowSimulation> simulatedArrows = new ArrayList<ArrowSimulation>();
        for (class_1297 arrow : arrows) {
            class_243 pos = arrow.method_19538();
            class_243 velocity = arrow.method_18798();
            simulatedArrows.add(new ArrowSimulation(AutoDodge.mc.field_1687, pos, velocity, false));
        }
        for (int i = 0; i < maxTicks; ++i) {
            simulatedPlayer.tick();
            for (int arrowIndex = 0; arrowIndex < simulatedArrows.size(); ++arrowIndex) {
                ArrowSimulation arrow = (ArrowSimulation)simulatedArrows.get(arrowIndex);
                if (arrow.inGround) continue;
                class_243 lastPos = arrow.pos;
                arrow.tick();
                class_238 playerHitBox = new class_238(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3).method_1014(hitboxExpansion).method_997(simulatedPlayer.pos);
                if (!playerHitBox.method_993(lastPos, arrow.pos)) continue;
                return new HitInfo(i, arrows.get(arrowIndex), arrow.pos, lastPos, arrow.velocity);
            }
        }
        return null;
    }

    private DodgePlan planEvasion(HitInfo hitInfo) {
        if (hitInfo == null) {
            return null;
        }
        class_243 arrowDir = hitInfo.arrowVelocity.method_1029();
        class_243 playerPos = AutoDodge.mc.field_1724.method_19538();
        class_243 hitPos = hitInfo.hitPos;
        class_243 toPlayer = playerPos.method_1020(hitPos).method_1029();
        class_243 perpendicular = arrowDir.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
        if (perpendicular.method_1027() < 0.1) {
            perpendicular = arrowDir.method_1036(new class_243(1.0, 0.0, 0.0)).method_1029();
        }
        float yaw = AutoDodge.mc.field_1724.method_36454();
        double yawRad = Math.toRadians(yaw);
        class_243 forward = new class_243(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
        class_243 right = new class_243(Math.cos(yawRad), 0.0, Math.sin(yawRad));
        double forwardDot = perpendicular.method_1026(forward);
        double rightDot = perpendicular.method_1026(right);
        float forwardInput = (float)class_3532.method_15350((double)(forwardDot * 2.0), (double)-1.0, (double)1.0);
        float sidewaysInput = (float)class_3532.method_15350((double)(rightDot * 2.0), (double)-1.0, (double)1.0);
        Float yawChange = null;
        if (this.allowRotation.isValue()) {
            double targetYaw = Math.toDegrees(Math.atan2(-perpendicular.field_1352, perpendicular.field_1350));
            yawChange = Float.valueOf((float)class_3532.method_15338((double)targetYaw));
        }
        boolean shouldJump = hitInfo.hitPos.field_1351 < playerPos.field_1351 + 0.5;
        return new DodgePlan(forwardInput, sidewaysInput, yawChange, shouldJump, false);
    }

    private static class HitInfo {
        final int tickDelta;
        final class_1297 arrowEntity;
        final class_243 hitPos;
        final class_243 prevArrowPos;
        final class_243 arrowVelocity;

        HitInfo(int tickDelta, class_1297 arrowEntity, class_243 hitPos, class_243 prevArrowPos, class_243 arrowVelocity) {
            this.tickDelta = tickDelta;
            this.arrowEntity = arrowEntity;
            this.hitPos = hitPos;
            this.prevArrowPos = prevArrowPos;
            this.arrowVelocity = arrowVelocity;
        }
    }

    private static class DodgePlan {
        final float forward;
        final float sideways;
        final Float yawChange;
        final boolean shouldJump;
        final boolean useTimer;

        DodgePlan(float forward, float sideways, Float yawChange, boolean shouldJump, boolean useTimer) {
            this.forward = forward;
            this.sideways = sideways;
            this.yawChange = yawChange;
            this.shouldJump = shouldJump;
            this.useTimer = useTimer;
        }
    }
}
