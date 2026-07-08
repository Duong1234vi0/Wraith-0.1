/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1531
 *  net.minecraft.class_1690
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2708
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.EventOnMovePost;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.PlayerTravelEvent;
import code.alphadlc.events.player.PostMotionEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.timer.TimerManager;
import code.alphadlc.utils.input.MoveUtil;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.network.NetworkUtils;
import java.util.Iterator;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1690;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2708;
import net.minecraft.class_2848;

public class Speed
extends Module {
    private SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438").value("\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u0413\u0440\u0438\u043c", "\u0425\u043e\u043b\u0438\u0412\u043e\u0440\u043b\u0434", "\u0424\u0430\u043d\u0422\u0430\u0439\u043c", "RWNew").selected("\u0413\u0440\u0438\u043c");
    private SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438 \u043f\u0435\u0440\u0435\u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f").range(1.0f, 5.0f).setValue(1.5f).visible(() -> this.mode.getSelected().equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439"));
    private SliderSettings grimBaseBoost = new SliderSettings("\u0411\u0443\u0441\u0442", "\u0411\u0430\u0437\u043e\u0432\u044b\u0439 \u0431\u0443\u0441\u0442 \u0437\u0430 \u043e\u0434\u043d\u0443 \u043a\u043e\u043b\u043b\u0438\u0437\u0438\u044e").range(0.01f, 0.25f).setValue(0.08f).step(0.005f).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private SliderSettings grimCollisionRange = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 \u043a\u043e\u043b\u043b\u0438\u0437\u0438\u0439", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430 \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439 \u0434\u043b\u044f Grim").range(0.1f, 2.5f).setValue(0.4f).step(0.01f).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private SliderSettings grimMaxCollisions = new SliderSettings("\u041b\u0438\u043c\u0438\u0442 \u043a\u043e\u043b\u043b\u0438\u0437\u0438\u0439", "\u041c\u0430\u043a\u0441\u0438\u043c\u0443\u043c \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439 \u0434\u043b\u044f \u0443\u0447\u0451\u0442\u0430 \u0431\u0443\u0441\u0442\u0430").range(1.0f, 10.0f).setValue(5.0f).step(1.0f).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private BooleanSetting grimOnlyGround = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", "\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c Grim speed \u0442\u043e\u043b\u044c\u043a\u043e \u043a\u043e\u0433\u0434\u0430 \u0438\u0433\u0440\u043e\u043a \u043d\u0430 \u0437\u0435\u043c\u043b\u0435").setValue(true).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private BooleanSetting grimIncludeBoats = new BooleanSetting("\u0423\u0447\u0438\u0442\u044b\u0432\u0430\u0442\u044c \u043b\u043e\u0434\u043a\u0438", "\u0421\u0447\u0438\u0442\u0430\u0442\u044c \u043b\u043e\u0434\u043a\u0438 \u043a\u0430\u043a \u043a\u043e\u043b\u043b\u0438\u0437\u0438\u0438 \u0434\u043b\u044f Grim").setValue(true).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private BooleanSetting up = new BooleanSetting("\u0423\u0441\u0438\u043b\u0435\u043d\u0438\u0435", "\u0423\u0432\u0435\u043b\u0438\u0447\u0438\u0432\u0430\u0435\u0442 \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f \u0434\u043e \u0446\u0435\u043b\u0438 \u0432 Aura").setValue(true).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c"));
    private SliderSettings strength = new SliderSettings("\u0421\u0438\u043b\u0430", "\u0424\u0430\u043a\u0442\u043e\u0440 \u0443\u043c\u043d\u043e\u0436\u0435\u043d\u0438\u044f \u0434\u043e \u0446\u0435\u043b\u0438").range(1.0f, 6.0f).setValue(1.5f).visible(() -> this.mode.getSelected().equals("\u0413\u0440\u0438\u043c") && this.up.isValue());
    private final SliderSettings bypassDistance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u044f \u043a\u043e\u043b\u043b\u0438\u0437\u0438\u0439").range(0.1f, 2.0f).setValue(0.3f).step(0.01f).visible(() -> this.mode.getSelected().equals("\u0425\u043e\u043b\u0438\u0412\u043e\u0440\u043b\u0434"));
    private final SliderSettings bypassSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0430\u0439\u043f\u0430\u0441\u0430").range(0.1f, 20.0f).setValue(3.1f).step(0.1f).visible(() -> this.mode.getSelected().equals("\u0425\u043e\u043b\u0438\u0412\u043e\u0440\u043b\u0434"));
    private class_243 currentVelocity = class_243.field_1353;
    private int grimTargetCollisions = 0;
    private long lastCollisionCheck = 0L;
    private int ticks = 0;
    private int groundTicks = 0;

    public Speed() {
        super("Speed", "Speed", ModuleCategory.MOVEMENT);
        this.setup(this.mode, this.speed, this.grimBaseBoost, this.grimCollisionRange, this.grimMaxCollisions, this.grimOnlyGround, this.grimIncludeBoats, this.up, this.strength, this.bypassDistance, this.bypassSpeed);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        String modeVal = this.mode.getSelected();
        if (modeVal.equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            Simulations.setVelocity(this.speed.getValue() / 3.0f);
        }
    }

    @EventHandler
    public void onMotion(PlayerTravelEvent e) {
        String modeVal = this.mode.getSelected();
        if (modeVal.equals("\u0424\u0430\u043d\u0422\u0430\u0439\u043c") && !Speed.mc.field_1724.method_5681() && !Speed.mc.field_1724.method_6128() && !Speed.mc.field_1724.method_5715() && Speed.mc.field_1724.method_5829().field_1325 - Speed.mc.field_1724.method_5829().field_1322 < 1.5) {
            float motion = Speed.mc.field_1724.method_6059(class_1294.field_5904) ? 0.32f : 0.28f;
            Simulations.setVelocity(motion);
        }
        if (modeVal.equals("\u0413\u0440\u0438\u043c") && e.isPre() && Simulations.hasPlayerMovement()) {
            this.handleGrimSpeed();
        }
        if (modeVal.equals("\u0425\u043e\u043b\u0438\u0412\u043e\u0440\u043b\u0434") && e.isPre()) {
            if (System.currentTimeMillis() - this.lastCollisionCheck > 100L) {
                this.updateGrimTargetCollisions();
                this.lastCollisionCheck = System.currentTimeMillis();
            }
            if (this.grimTargetCollisions > 0) {
                this.applyGrimTargetBypass();
            }
        }
    }

    @EventHandler
    public void onMovePost(EventOnMovePost event) {
        if (!this.mode.getSelected().equals("RWNew")) {
            return;
        }
        TimerManager.setTimer(1.7f);
        if (this.ticks > 3) {
            double bst = 0.03;
            if (this.ticks % 2 == 0) {
                Speed.mc.field_1724.method_5762(0.0, (double)0.03f, 0.0);
                bst = Speed.mc.field_1724.method_24828() ? 0.085 : 0.03;
            }
            double yaw = Math.toRadians(MoveUtil.getdir());
            double xt = -Math.sin(yaw);
            double zt = Math.cos(yaw);
            if (MoveUtil.getdir() == -1.0f) {
                xt = 0.0;
                zt = 0.0;
            }
            Speed.mc.field_1724.method_5762(xt * bst, 0.0, zt * bst);
        }
        ++this.ticks;
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (!this.mode.getSelected().equals("RWNew")) {
            return;
        }
        this.groundTicks = Speed.mc.field_1724.field_5992 ? ++this.groundTicks : 0;
        if (this.groundTicks >= 1) {
            Speed.mc.field_1724.method_6043();
        }
    }

    @EventHandler
    public void onPostMotion(PostMotionEvent event) {
        if (!this.mode.getSelected().equals("RWNew")) {
            return;
        }
        if (this.ticks % 2 == 0) {
            TimerManager.setTimer(0.3f);
            NetworkUtils.sendSilentPacket(new class_2848((class_1297)Speed.mc.field_1724, class_2848.class_2849.field_12982));
        }
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (!this.mode.getSelected().equals("RWNew")) {
            return;
        }
        if (event.getPacket() instanceof class_2708) {
            if (this.ticks % 2 == 1) {
                ++this.ticks;
            }
            TimerManager.setTimer(1.0f);
        }
    }

    private void handleGrimSpeed() {
        class_1297 ent;
        if (Speed.mc.field_1724 == null || Speed.mc.field_1687 == null) {
            return;
        }
        if (this.grimOnlyGround.isValue() && !Speed.mc.field_1724.method_24828()) {
            return;
        }
        int collisions = 0;
        float box = this.grimCollisionRange.getValue();
        if (AttackAura.getInstance().isState() && AttackAura.getInstance().getTarget() != null && AttackAura.getInstance().getTarget().method_5624() && Speed.mc.field_1724.method_5624() && this.up.isValue()) {
            box = Math.max(box, this.strength.getValue());
        }
        class_238 expandedBox = Speed.mc.field_1724.method_5829().method_1014((double)box);
        int maxCollisions = (int)this.grimMaxCollisions.getValue();
        Iterator iterator = Speed.mc.field_1687.method_18112().iterator();
        while (iterator.hasNext() && ((ent = (class_1297)iterator.next()) == Speed.mc.field_1724 || ent instanceof class_1531 || ent instanceof class_1690 && !this.grimIncludeBoats.isValue() || !(ent instanceof class_1309) && !(ent instanceof class_1690) || !expandedBox.method_994(ent.method_5829()) || ++collisions < maxCollisions)) {
        }
        double[] motion = Simulations.forward(this.grimBaseBoost.getValue() * (float)collisions);
        Speed.mc.field_1724.method_5762(motion[0], 0.0, motion[1]);
    }

    private void updateGrimTargetCollisions() {
        this.grimTargetCollisions = 0;
        if (Speed.mc.field_1724 == null || Speed.mc.field_1687 == null) {
            return;
        }
        float expandValue = this.bypassDistance.getValue();
        class_238 expandedBox = Speed.mc.field_1724.method_5829().method_1014((double)expandValue);
        for (class_1297 ent : Speed.mc.field_1687.method_18112()) {
            if (ent == Speed.mc.field_1724 || ent instanceof class_1531 || !(ent instanceof class_1309) && !(ent instanceof class_1690) || !expandedBox.method_994(ent.method_5829())) continue;
            ++this.grimTargetCollisions;
        }
    }

    private void applyGrimTargetBypass() {
        double maxRange;
        double distance;
        if (this.grimTargetCollisions <= 0) {
            return;
        }
        class_1309 target = AttackAura.getInstance().getTarget();
        if (target != null && target.method_5805() && (distance = Speed.mc.field_1724.method_19538().method_1022(target.method_19538())) <= (maxRange = 3.0)) {
            this.applySpeedBoost((class_1297)target);
            return;
        }
        double maxRange2 = 3.0;
        double maxRangeSq = maxRange2 * maxRange2;
        class_1297 nearest = null;
        double bestSq = Double.MAX_VALUE;
        for (class_1297 ent : Speed.mc.field_1687.method_18112()) {
            double dz;
            double dx;
            double sq;
            if (ent == Speed.mc.field_1724 || ent instanceof class_1531 || !(ent instanceof class_1309) && !(ent instanceof class_1690) || !((sq = (dx = ent.method_23317() - Speed.mc.field_1724.method_23317()) * dx + (dz = ent.method_23321() - Speed.mc.field_1724.method_23321()) * dz) <= maxRangeSq) || !(sq < bestSq)) continue;
            bestSq = sq;
            nearest = ent;
        }
        if (nearest != null) {
            this.applySpeedBoost(nearest);
        }
    }

    private void applySpeedBoost(class_1297 targetEntity) {
        double finalSpeed = (double)this.bypassSpeed.getValue() * 0.01 * (double)this.grimTargetCollisions;
        if (finalSpeed <= 0.0) {
            return;
        }
        double[] direction = this.getDirectionToPoint(Speed.mc.field_1724.method_19538(), targetEntity.method_19538(), finalSpeed);
        Speed.mc.field_1724.method_5762(direction[0], 0.0, direction[1]);
        class_243 currentVel = Speed.mc.field_1724.method_18798();
        this.currentVelocity = new class_243(currentVel.field_1352, currentVel.field_1351, currentVel.field_1350);
    }

    private double[] getDirectionToPoint(class_243 from, class_243 to, double speed) {
        double dx = to.field_1352 - from.field_1352;
        double dz = to.field_1350 - from.field_1350;
        double length = Math.sqrt(dx * dx + dz * dz);
        if (length == 0.0) {
            return new double[]{0.0, 0.0};
        }
        return new double[]{dx / length * speed, dz / length * speed};
    }

    @Override
    public void activate() {
        super.activate();
        this.grimTargetCollisions = 0;
        this.lastCollisionCheck = System.currentTimeMillis();
        this.ticks = 0;
        this.groundTicks = 0;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.grimTargetCollisions = 0;
        this.ticks = 0;
        this.groundTicks = 0;
        TimerManager.reset();
    }
}
