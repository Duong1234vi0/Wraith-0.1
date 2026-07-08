/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1676
 *  net.minecraft.class_1684
 *  net.minecraft.class_1735
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_239
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.EntitySpawnEvent;
import code.alphadlc.events.player.PostMotionEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.render.Prediction;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.rotations.constructor.LinearConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.simulate.PlayerSimulation;
import code.alphadlc.utils.math.script.Script;
import code.alphadlc.utils.math.task.TaskPriority;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1676;
import net.minecraft.class_1684;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_243;

public class TargetPearl
extends Module {
    private final StopWatch stopWatch = new StopWatch();
    private final Script script = new Script();
    private final RadioSetting modeSetting = new RadioSetting("Mode", "When will target pearl work", new String[]{"Bind", "Always"}, "Always");
    private final RadioSetting targetSetting = new RadioSetting("Targets", "Targets for which pearls will be thrown", new String[]{"Aura Target", "All"}, "Aura Target");
    private final BindSetting throwSetting = new BindSetting("Throw", "Throw Key").visible(() -> this.modeSetting.get().equals("Bind"));
    private final SliderSettings distanceSetting = new SliderSettings("Distance", "Target Pearl Trigger Distance").setValue(10.0f).range(5, 15);

    public TargetPearl() {
        super("TargetPearl", "TargetPearl", ModuleCategory.COMBAT);
        this.setup(this.modeSetting, this.targetSetting, this.throwSetting, this.distanceSetting);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        class_1297 class_12972 = e.getEntity();
        if (class_12972 instanceof class_1684) {
            class_1684 pearl = (class_1684)class_12972;
            TargetPearl.mc.field_1687.method_18456().stream().filter(p -> p.method_5739((class_1297)pearl) <= 3.0f).min(Comparator.comparingDouble(p -> p.method_5739((class_1297)pearl))).ifPresent(arg_0 -> ((class_1684)pearl).method_7432(arg_0));
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() == 0) {
            class_1309 auraTarget = AttackAura.getInstance().getTarget();
            if (auraTarget == null) {
                auraTarget = AttackAura.getInstance().getLastTarget();
            }
            class_1309 target = auraTarget;
            class_1735 slot = InventoryTask.getSlot(class_1802.field_8634);
            if (slot == null || !this.stopWatch.finished(1000.0)) {
                return;
            }
            if (this.modeSetting.get().equals("Bind") && !PlayerInteractionHelper.isKey(this.throwSetting)) {
                return;
            }
            if (PlayerInteractionHelper.streamEntities().filter(class_1684.class::isInstance).map(class_1684.class::cast).anyMatch(pearl -> Objects.equals(pearl.method_24921(), TargetPearl.mc.field_1724))) {
                this.stopWatch.reset();
                return;
            }
            Prediction prediction = Prediction.getInstance();
            PlayerInteractionHelper.streamEntities().filter(class_1684.class::isInstance).map(class_1684.class::cast).filter(pearl -> !FriendUtils.isFriend(pearl.method_24921()) && (this.targetSetting.get().equals("All") || target != null && target.equals((Object)pearl.method_24921()))).min(Comparator.comparingDouble(pearl -> TurnsConnection.computeRotationDifference(MathAngle.cameraAngle(), MathAngle.calculateAngle(prediction.calcTrajectory((class_1676)pearl).method_17784())))).ifPresent(pearl -> {
                class_239 targetResult = prediction.calcTrajectory((class_1676)pearl);
                if (targetResult == null || TargetPearl.mc.field_1724.method_19538().method_1022(targetResult.method_17784()) <= (double)this.distanceSetting.getInt()) {
                    return;
                }
                class_243 eyePos = TargetPearl.mc.field_1724.method_33571().method_1019(TargetPearl.mc.field_1724.method_19538().method_1020(PlayerSimulation.simulateLocalPlayer((int)1).pos));
                float yaw = MathAngle.fromVec3d(targetResult.method_17784().method_1020(eyePos)).getYaw();
                IntStream.range(-89, 89).mapToObj(pitch -> new Turns(yaw, pitch)).filter(angle -> {
                    class_239 playerResult = prediction.checkTrajectory(angle.toVector(), (class_1676)new class_1684((class_1937)TargetPearl.mc.field_1687, (class_1309)TargetPearl.mc.field_1724, slot.method_7677()), 1.5);
                    return playerResult != null && playerResult.method_17784().method_1022(targetResult.method_17784()) <= 3.0;
                }).max(Comparator.comparingDouble(Turns::getPitch)).ifPresent(angle -> {
                    TurnsConnection.INSTANCE.rotateTo(new Turns.VecRotation((Turns)angle, angle.toVector()), (class_1309)TargetPearl.mc.field_1724, 1, new TurnsConfig(new LinearConstructor(), true, true), TaskPriority.HIGH_IMPORTANCE_3, this);
                    InventoryFlowManager.unPressMoveKeys();
                    this.script.cleanup().addTickStep(0, () -> {
                        InventoryTask.swapAndUse(class_1802.field_8634, angle, false);
                        InventoryFlowManager.enableMoveKeys();
                    });
                    pearl.method_7432(null);
                    this.stopWatch.reset();
                });
            });
        }
    }

    @EventHandler
    public void onPostMotion(PostMotionEvent e) {
        this.script.update();
    }
}
