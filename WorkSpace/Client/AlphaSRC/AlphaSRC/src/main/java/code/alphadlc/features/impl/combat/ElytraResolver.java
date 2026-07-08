/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.inv.HotbarUtil;
import code.alphadlc.utils.interactions.inv.SlotSearchResult;
import java.util.ArrayList;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class ElytraResolver
extends Module {
    private static final float MIN_HEIGHT = 4.0f;
    private static final long RETREAT_TIMEOUT_MS = 50L;
    private static final double MIN_DIR_SQ = 1.0E-4;
    private final SliderSettings approachDistance = new SliderSettings("\u0414\u043e\u043b\u0435\u0442", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e\u043b\u0451\u0442\u0430").setValue(3.0f).range(1.5f, 6.0f).step(0.1f);
    private final SliderSettings retreatDistance = new SliderSettings("\u041e\u0442\u043b\u0435\u0442", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0442\u043b\u0451\u0442\u0430").setValue(8.0f).range(4.0f, 16.0f).step(0.5f);
    private final SliderSettings retreatAngle = new SliderSettings("\u0423\u0433\u043e\u043b \u043e\u0442\u043b\u0435\u0442\u0430", "\u0423\u0433\u043e\u043b \u043e\u0442\u043b\u0451\u0442\u0430").setValue(50.0f).range(0.0f, 90.0f).step(1.0f);
    private final SelectSetting retreatMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u043e\u0442\u043b\u0435\u0442\u0430", "\u041d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043e\u0442\u043b\u0451\u0442\u0430").value("\u0412 \u0441\u0442\u043e\u0440\u043e\u043d\u0443", "\u0412\u0432\u0435\u0440\u0445", "\u0414\u0438\u0430\u0433\u043e\u043d\u0430\u043b\u044c");
    private final BooleanSetting alternateSides = new BooleanSetting("\u0427\u0435\u0440\u0435\u0434\u043e\u0432\u0430\u0442\u044c \u0441\u0442\u043e\u0440\u043e\u043d\u044b", "\u0427\u0435\u0440\u0435\u0434\u0443\u0435\u0442 \u0441\u0442\u043e\u0440\u043e\u043d\u0443 \u043e\u0442\u043b\u0451\u0442\u0430").setValue(true);
    private final SliderSettings escapeDistance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0442\u043b\u0435\u0442\u0430 (Aura)", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0442\u043b\u0451\u0442\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435").setValue(6.0f).range(4.0f, 8.0f).step(0.1f);
    private final BooleanSetting autoFirework = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u0410\u0432\u0442\u043e-\u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u043f\u0440\u0438 \u043e\u0442\u043b\u0451\u0442\u0435 Aura").setValue(true);
    private ResolveState state = ResolveState.APPROACHING;
    private class_243 retreatDirection;
    private long stateSince;
    private class_1309 trackedTarget;
    private boolean escaping;
    private class_243 escapePos;
    private long escapeStartTime;
    private int returnFireworkTicks = -1;
    private class_243 lastEscapeDirection;
    private boolean alternateFlip;

    public ElytraResolver() {
        super("ElytraResolver", "Elytra Resolver", ModuleCategory.MOVEMENT);
        this.setup(this.approachDistance, this.retreatDistance, this.retreatAngle, this.retreatMode, this.alternateSides, this.escapeDistance, this.autoFirework);
    }

    public static ElytraResolver getInstance() {
        return Instance.get(ElytraResolver.class);
    }

    public class_243 resolve(class_1309 target, class_243 point) {
        if (!this.isState() || ElytraResolver.mc.field_1724 == null || target == null || point == null) {
            this.resetApproach();
            return point;
        }
        if (!ElytraResolver.mc.field_1724.method_6128()) {
            this.resetApproach();
            return point;
        }
        if (target != this.trackedTarget) {
            this.trackedTarget = target;
            this.state = ResolveState.APPROACHING;
            this.retreatDirection = null;
            this.stateSince = System.currentTimeMillis();
        }
        double distance = ElytraResolver.mc.field_1724.method_33571().method_1022(point);
        if (this.state == ResolveState.APPROACHING && distance <= (double)this.approachDistance.getValue()) {
            this.beginRetreat();
        } else if (this.state == ResolveState.RETREATING) {
            boolean timedOut;
            boolean farEnough = distance >= (double)this.retreatDistance.getValue();
            boolean bl = timedOut = System.currentTimeMillis() - this.stateSince > 50L;
            if (farEnough || timedOut) {
                this.beginApproach();
            }
        }
        if (this.state == ResolveState.APPROACHING) {
            return point;
        }
        if (this.retreatDirection == null || this.retreatDirection.method_1027() < 1.0E-4) {
            this.retreatDirection = this.buildRetreatDirection(point, ElytraResolver.mc.field_1724.method_19538());
        }
        return point.method_1019(this.retreatDirection.method_1021((double)this.retreatDistance.getValue()));
    }

    public void onAttack(class_1309 target) {
        if (target != null) {
            if (target != this.trackedTarget) {
                this.trackedTarget = target;
            }
            if (this.state == ResolveState.APPROACHING) {
                this.beginRetreat();
            }
        }
        this.onAuraAttack();
    }

    @EventHandler
    public void onUpdate(TickEvent event) {
        if (ElytraResolver.mc.field_1724 == null || ElytraResolver.mc.field_1687 == null) {
            return;
        }
        if (this.returnFireworkTicks > 0) {
            --this.returnFireworkTicks;
            if (this.returnFireworkTicks == 0) {
                if (this.autoFirework.isValue()) {
                    this.useFirework();
                }
                this.returnFireworkTicks = -1;
            }
        }
        if (!this.escaping || this.escapePos == null) {
            return;
        }
        double dist = ElytraResolver.mc.field_1724.method_19538().method_1022(this.escapePos);
        long elapsed = System.currentTimeMillis() - this.escapeStartTime;
        if (dist < 2.0 || elapsed > 1000L) {
            this.escaping = false;
            this.escapePos = null;
            if (this.autoFirework.isValue()) {
                this.returnFireworkTicks = 2;
            }
        }
    }

    public void onAuraAttack() {
        if (!this.isState()) {
            return;
        }
        if (ElytraResolver.mc.field_1724 == null || !ElytraResolver.mc.field_1724.method_6128()) {
            return;
        }
        this.escapePos = this.calculateSmartEscape(ElytraResolver.mc.field_1724.method_19538(), this.escapeDistance.getValue());
        if (this.escapePos == null) {
            return;
        }
        this.escaping = true;
        this.escapeStartTime = System.currentTimeMillis();
        if (this.autoFirework.isValue()) {
            this.useFirework();
        }
    }

    public boolean isEscaping() {
        return this.escaping;
    }

    public class_243 getEscapePos() {
        return this.escapePos;
    }

    private void beginApproach() {
        this.state = ResolveState.APPROACHING;
        this.retreatDirection = null;
        this.stateSince = System.currentTimeMillis();
    }

    private void beginRetreat() {
        this.state = ResolveState.RETREATING;
        this.retreatDirection = null;
        this.stateSince = System.currentTimeMillis();
    }

    private void resetApproach() {
        this.state = ResolveState.APPROACHING;
        this.trackedTarget = null;
        this.retreatDirection = null;
        this.stateSince = 0L;
    }

    private class_243 buildRetreatDirection(class_243 aimPoint, class_243 playerPos) {
        class_243 away = playerPos.method_1020(aimPoint);
        away = new class_243(away.field_1352, 0.0, away.field_1350);
        if (away.method_1027() < 1.0E-4) {
            class_243 look = ElytraResolver.mc.field_1724.method_5828(1.0f);
            away = new class_243(look.field_1352, 0.0, look.field_1350);
        }
        if (away.method_1027() < 1.0E-4) {
            away = new class_243(0.0, 0.0, 1.0);
        }
        away = away.method_1029();
        double angle = this.retreatAngle.getValue();
        if (this.alternateSides.isValue()) {
            angle = this.alternateFlip ? angle : -angle;
            this.alternateFlip = !this.alternateFlip;
        }
        double rad = Math.toRadians(angle);
        double x = away.field_1352 * Math.cos(rad) - away.field_1350 * Math.sin(rad);
        double z = away.field_1352 * Math.sin(rad) + away.field_1350 * Math.cos(rad);
        double y = switch (this.retreatMode.getSelected()) {
            case "\u0412\u0432\u0435\u0440\u0445" -> 1.0;
            case "\u0414\u0438\u0430\u0433\u043e\u043d\u0430\u043b\u044c" -> 0.5;
            default -> 0.0;
        };
        return new class_243(x, y, z).method_1029();
    }

    private class_243 calculateSmartEscape(class_243 origin, float dist) {
        class_243 back;
        class_243 velocity = ElytraResolver.mc.field_1724.method_18798();
        class_243 horizontalVelocity = new class_243(velocity.field_1352, 0.0, velocity.field_1350);
        if (horizontalVelocity.method_1027() > 0.01) {
            back = horizontalVelocity.method_1029().method_1021(-1.0);
        } else {
            float yawRad = (float)Math.toRadians(ElytraResolver.mc.field_1724.method_36454());
            back = new class_243(Math.sin(yawRad), 0.0, -Math.cos(yawRad));
        }
        class_243 up = new class_243(0.0, 1.0, 0.0);
        class_243 left = new class_243(-back.field_1350, 0.0, back.field_1352);
        class_243 right = new class_243(back.field_1350, 0.0, -back.field_1352);
        ArrayList<class_243> directions = new ArrayList<class_243>();
        directions.add(back);
        directions.add(back.method_1019(up).method_1029());
        directions.add(back.method_1019(up.method_1021(0.5)).method_1029());
        directions.add(back.method_1019(left.method_1021(0.5)).method_1029());
        directions.add(back.method_1019(right.method_1021(0.5)).method_1029());
        directions.add(up);
        class_243 best = null;
        double bestScore = -1.7976931348623157E308;
        for (class_243 dir : directions) {
            double score;
            class_3959 context;
            class_3965 result;
            class_243 candidate = origin.method_1019(dir.method_1021((double)dist));
            if (candidate.field_1351 - (double)ElytraResolver.mc.field_1687.method_31607() < 4.0 || (result = ElytraResolver.mc.field_1687.method_17742(context = new class_3959(origin, candidate, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)ElytraResolver.mc.field_1724))).method_17783() != class_239.class_240.field_1333 || !((score = dir.method_1026(back) * 30.0 + dir.field_1351 * 25.0) > bestScore)) continue;
            bestScore = score;
            best = candidate;
        }
        if (best != null) {
            this.lastEscapeDirection = best.method_1020(origin).method_1029();
        }
        return best;
    }

    private void useFirework() {
        if (ElytraResolver.mc.field_1724 == null || ElytraResolver.mc.field_1761 == null) {
            return;
        }
        class_1268 hand = HotbarUtil.getHand(class_1802.field_8639);
        if (hand != null) {
            ElytraResolver.mc.field_1761.method_2919((class_1657)ElytraResolver.mc.field_1724, hand);
            ElytraResolver.mc.field_1724.method_6104(hand);
            return;
        }
        SlotSearchResult result = HotbarUtil.findItemInHotBar(class_1802.field_8639);
        if (!result.found() || !result.isInHotBar()) {
            return;
        }
        int previous = ElytraResolver.mc.field_1724.method_31548().field_7545;
        HotbarUtil.switchToSilent(result.slot());
        ElytraResolver.mc.field_1761.method_2919((class_1657)ElytraResolver.mc.field_1724, class_1268.field_5808);
        ElytraResolver.mc.field_1724.method_6104(class_1268.field_5808);
        HotbarUtil.switchToSilent(previous);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.resetApproach();
        this.escaping = false;
        this.escapePos = null;
        this.returnFireworkTicks = -1;
        this.lastEscapeDirection = null;
    }

    private static enum ResolveState {
        APPROACHING,
        RETREATING;

    }
}
