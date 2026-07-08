/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1735
 *  net.minecraft.class_1743
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_4050
 *  net.minecraft.class_6880
 */
package code.alphadlc.utils.features.aura.striking;

import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.PacketCriticals;
import code.alphadlc.features.impl.combat.TriggerBot;
import code.alphadlc.mixins.ClientPlayerEntityAccessor;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.striking.StrikerConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.utils.Pressing;
import code.alphadlc.utils.features.aura.utils.RaycastAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.combat.LegitSprintReset;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemUseHelper;
import code.alphadlc.utils.interactions.simulate.PlayerSimulation;
import code.alphadlc.utils.math.time.StopWatch;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_4050;
import net.minecraft.class_6880;

public class StrikeManager
implements QuickImports {
    private final StopWatch attackTimer = new StopWatch();
    private final StopWatch shieldWatch = new StopWatch();
    private final Pressing clickScheduler = new Pressing();
    private int count = 0;
    private class_2848.class_2849 lastSprintCommand = null;

    void onPacket(PacketEvent event) {
        class_2596<?> packet = event.getPacket();
        if (packet instanceof class_2879 || packet instanceof class_2868) {
            this.clickScheduler.recalculate();
        }
    }

    void onUsingItem(UsingItemEvent event) {
        class_1799 useStack;
        if (event.getType() != -1 || StrikeManager.mc.field_1724 == null) {
            return;
        }
        if (!this.shieldWatch.finished(50.0)) {
            return;
        }
        class_1799 class_17992 = useStack = event.getHand() != null ? ItemUseHelper.getStackForHand((class_1657)StrikeManager.mc.field_1724, event.getHand()) : StrikeManager.mc.field_1724.method_6047();
        if (ItemUseHelper.isPriorityUseItem(useStack)) {
            return;
        }
        if (ItemUseHelper.isPriorityUseItem(StrikeManager.mc.field_1724.method_6047()) || ItemUseHelper.isPriorityUseItem(StrikeManager.mc.field_1724.method_6079())) {
            return;
        }
        if (ItemUseHelper.isUsingPriorityItem((class_1657)StrikeManager.mc.field_1724)) {
            return;
        }
        if (!this.shouldBlockItemUseStart()) {
            return;
        }
        event.cancel();
    }

    private boolean shouldBlockItemUseStart() {
        AttackAura aura = AttackAura.getInstance();
        if (aura != null && aura.isState() && aura.getTarget() != null && aura.getTarget().method_5805()) {
            return true;
        }
        TriggerBot triggerBot = TriggerBot.getInstance();
        return triggerBot != null && triggerBot.isState() && triggerBot.getTarget() != null && triggerBot.getTarget().method_5805();
    }

    void handleAttack(StrikerConstructor.AttackPerpetratorConfigurable config) {
        if (!RaycastAngle.rayTrace(config) || !this.canAttack(config, 1)) {
            return;
        }
        String sprintMode = this.resolveSprintMode(config);
        if (sprintMode.equals("\u041b\u0435\u0433\u0438\u0442\u043d\u043e")) {
            if (!LegitSprintReset.prepareForAttack()) {
                return;
            }
            this.preAttackEntity(config);
            this.attackEntity(config);
            return;
        }
        if (this.canAttack(config, 1)) {
            this.preAttackEntity(config);
        }
        if (sprintMode.equals("\u041f\u0430\u043a\u0435\u0442\u043d\u043e")) {
            AttackAura aura;
            ClientPlayerEntityAccessor accessor = (ClientPlayerEntityAccessor)StrikeManager.mc.field_1724;
            boolean wasSprinting = accessor.getLastSprinting();
            if (wasSprinting && !StrikeManager.mc.field_1724.method_5869() && !StrikeManager.mc.field_1724.method_5799() && this.lastSprintCommand != class_2848.class_2849.field_12985) {
                StrikeManager.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)StrikeManager.mc.field_1724, class_2848.class_2849.field_12985));
                this.lastSprintCommand = class_2848.class_2849.field_12985;
                StrikeManager.mc.field_1724.method_5728(false);
            }
            this.attackEntity(config);
            if (wasSprinting && !StrikeManager.mc.field_1724.method_5869() && !StrikeManager.mc.field_1724.method_5799() && this.lastSprintCommand != class_2848.class_2849.field_12981 && (aura = AttackAura.getInstance()) != null && aura.canRestartSprintAfterAttack()) {
                StrikeManager.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)StrikeManager.mc.field_1724, class_2848.class_2849.field_12981));
                this.lastSprintCommand = class_2848.class_2849.field_12981;
                StrikeManager.mc.field_1724.method_5728(true);
            }
        }
    }

    private String resolveSprintMode(StrikerConstructor.AttackPerpetratorConfigurable config) {
        if (config.getSprintResetOverride() != null) {
            return config.getSprintResetOverride();
        }
        AttackAura aura = AttackAura.getInstance();
        if (aura != null && aura.isState()) {
            return switch (aura.getSprintReset().get()) {
                case "\u041f\u0430\u043a\u0435\u0442\u043d\u043e" -> "\u041f\u0430\u043a\u0435\u0442\u043d\u043e";
                case "\u041b\u0435\u0433\u0438\u0442\u043d\u043e" -> "\u041b\u0435\u0433\u0438\u0442\u043d\u043e";
                default -> "\u041f\u0430\u043a\u0435\u0442\u043d\u043e";
            };
        }
        return "\u041b\u0435\u0433\u0438\u0442\u043d\u043e";
    }

    void preAttackEntity(StrikerConstructor.AttackPerpetratorConfigurable config) {
        if (config.isShouldUnPressShield() && StrikeManager.mc.field_1724.method_6115() && StrikeManager.mc.field_1724.method_6030().method_31574(class_1802.field_8255)) {
            StrikeManager.mc.field_1761.method_2897((class_1657)StrikeManager.mc.field_1724);
            this.shieldWatch.reset();
        }
    }

    void attackEntity(StrikerConstructor.AttackPerpetratorConfigurable config) {
        this.attack(config);
        this.breakShield(config);
        this.attackTimer.reset();
        ++this.count;
    }

    private void breakShield(StrikerConstructor.AttackPerpetratorConfigurable config) {
        class_1309 target = config.getTarget();
        Turns angleToPlayer = MathAngle.fromVec3d(StrikeManager.mc.field_1724.method_5829().method_1005().method_1020(target.method_33571()));
        boolean targetOnShield = target.method_6115() && target.method_6030().method_31574(class_1802.field_8255);
        boolean angle = Math.abs(TurnsConnection.computeAngleDifference(target.method_36454(), angleToPlayer.getYaw())) < 90.0f;
        class_1735 axe = InventoryTask.getSlot(slot -> slot.method_7677().method_7909() instanceof class_1743);
        if (config.isShouldBreakShield() && targetOnShield && axe != null && angle && InventoryFlowManager.script.isFinished()) {
            InventoryTask.swapHand(axe, class_1268.field_5808, false);
            InventoryTask.closeScreen(true);
            this.attack(config);
            InventoryTask.swapHand(axe, class_1268.field_5808, false, true);
            InventoryTask.closeScreen(true);
        }
    }

    private void attack(StrikerConstructor.AttackPerpetratorConfigurable config) {
        StrikeManager.mc.field_1761.method_2918((class_1657)StrikeManager.mc.field_1724, (class_1297)config.getTarget());
        StrikeManager.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public boolean canAttack(StrikerConstructor.AttackPerpetratorConfigurable config, int ticks) {
        for (int i = 0; i <= ticks; ++i) {
            if (!this.canCrit(config, i)) continue;
            return true;
        }
        return false;
    }

    public boolean canCrit(StrikerConstructor.AttackPerpetratorConfigurable config, int ticks) {
        boolean useTpsSync;
        if (ItemUseHelper.isUsingPriorityItem((class_1657)StrikeManager.mc.field_1724) && config.isEatAndAttack()) {
            return false;
        }
        AttackAura aura = AttackAura.getInstance();
        boolean bl = useTpsSync = aura != null && aura.isState() && aura.isSyncTpsEnabled();
        if (!this.clickScheduler.isCooldownComplete(useTpsSync, 1)) {
            return false;
        }
        PlayerSimulation simulated = PlayerSimulation.simulateLocalPlayer(ticks);
        boolean noRestrict = !this.hasMovementRestrictions(simulated);
        boolean critState = this.isPlayerInCriticalState(config, simulated, ticks);
        if (config.isOnlyCritical() && noRestrict) {
            return critState;
        }
        if (config.isOnlyCritical() && !noRestrict) {
            return true;
        }
        return true;
    }

    private boolean hasMovementRestrictions(PlayerSimulation simulated) {
        return simulated.hasStatusEffect((class_6880<class_1291>)class_1294.field_5919) || simulated.hasStatusEffect((class_6880<class_1291>)class_1294.field_5902) || PlayerInteractionHelper.isBoxInBlock(simulated.boundingBox.method_1014(-0.001), class_2246.field_10343) || simulated.isSubmergedInWater() || simulated.isInLava() || simulated.isClimbing() || !PlayerInteractionHelper.canChangeIntoPose(class_4050.field_18076, simulated.pos) || simulated.player.method_31549().field_7479;
    }

    private boolean isPlayerInCriticalState(StrikerConstructor.AttackPerpetratorConfigurable config, PlayerSimulation simulated, int ticks) {
        PacketCriticals packetCriticals = PacketCriticals.getInstance();
        if (packetCriticals != null && packetCriticals.isState() && PlayerInteractionHelper.isBoxInBlock(simulated.boundingBox.method_1014(-0.001), class_2246.field_10343)) {
            return !simulated.onGround || simulated.fallDistance > 0.001f;
        }
        boolean fall = simulated.fallDistance > 0.0f;
        boolean fallingDown = simulated.velocity.field_1351 < 0.0;
        return !simulated.onGround && fall && fallingDown;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLastSprintCommand(class_2848.class_2849 lastSprintCommand) {
        this.lastSprintCommand = lastSprintCommand;
    }

    public StopWatch getAttackTimer() {
        return this.attackTimer;
    }

    public StopWatch getShieldWatch() {
        return this.shieldWatch;
    }

    public Pressing getClickScheduler() {
        return this.clickScheduler;
    }

    public int getCount() {
        return this.count;
    }

    public class_2848.class_2849 getLastSprintCommand() {
        return this.lastSprintCommand;
    }
}
