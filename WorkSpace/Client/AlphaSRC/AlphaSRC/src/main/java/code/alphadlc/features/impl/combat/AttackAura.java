/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1294
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1420
 *  net.minecraft.class_1431
 *  net.minecraft.class_1439
 *  net.minecraft.class_1531
 *  net.minecraft.class_1588
 *  net.minecraft.class_1657
 *  net.minecraft.class_1675
 *  net.minecraft.class_1713
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1794
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1810
 *  net.minecraft.class_1819
 *  net.minecraft.class_1821
 *  net.minecraft.class_1829
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2868
 *  net.minecraft.class_3532
 *  net.minecraft.class_3966
 *  net.minecraft.class_5134
 *  net.minecraft.class_9362
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.player.GameUpdateEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.PostTickEvent;
import code.alphadlc.events.player.SprintEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.combat.AntiBot;
import code.alphadlc.features.impl.combat.CombatModules;
import code.alphadlc.features.impl.combat.ElytraResolver;
import code.alphadlc.features.impl.combat.PacketCriticals;
import code.alphadlc.features.impl.combat.TpsSync;
import code.alphadlc.features.impl.combat.components.RotationsSystem;
import code.alphadlc.features.impl.combat.components.interpolation.BestPoint;
import code.alphadlc.features.impl.combat.components.interpolation.HitChance;
import code.alphadlc.features.impl.combat.components.interpolation.HumanCps;
import code.alphadlc.features.impl.combat.components.rotations.HolyWorldRotation;
import code.alphadlc.features.impl.combat.components.rotations.LegitRotation;
import code.alphadlc.features.impl.combat.components.rotations.ReallyworldRotation;
import code.alphadlc.features.impl.combat.components.rotations.SlothRotation;
import code.alphadlc.features.impl.movement.AirStuck;
import code.alphadlc.features.impl.movement.ElytraTarget;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.mixins.ClientPlayerEntityAccessor;
import code.alphadlc.mixins.ILivingEntity;
import code.alphadlc.utils.client.FakePlayerManager;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.AuraMixinHelper;
import code.alphadlc.utils.features.aura.FreeLookStorage;
import code.alphadlc.utils.features.aura.RotationStorage;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.combat.IdealHitUtils;
import code.alphadlc.utils.features.combat.LegitSprintReset;
import code.alphadlc.utils.features.combat.PredictUtils;
import code.alphadlc.utils.features.rotate.MultipointUtils;
import code.alphadlc.utils.features.rotate.Rotation;
import code.alphadlc.utils.features.rotate.RotationUtils;
import code.alphadlc.utils.interactions.inv.HotbarUtil;
import code.alphadlc.utils.interactions.inv.SlotSearchResult;
import code.alphadlc.utils.interactions.item.ItemUseHelper;
import code.alphadlc.utils.interactions.simulate.MovingUtil;
import code.alphadlc.utils.math.TimerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1420;
import net.minecraft.class_1431;
import net.minecraft.class_1439;
import net.minecraft.class_1531;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1675;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_1819;
import net.minecraft.class_1821;
import net.minecraft.class_1829;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2815;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import net.minecraft.class_5134;
import net.minecraft.class_9362;

public class AttackAura
extends Module {
    public static float RANGE_MARGIN = 0.153f;
    public static final RadioSetting aimMode = new RadioSetting("\u041d\u0430\u0432\u043e\u0434\u043a\u0430", "\u041d\u0430\u0432\u043e\u0434\u043a\u0430", new String[]{"Smooth"}, "Smooth");
    public final RadioSetting rotationType = new RadioSetting("\u0420\u043e\u0442\u0430\u0446\u0438\u044f", "\u0420\u043e\u0442\u0430\u0446\u0438\u044f", new String[]{"Smooth", "Snap", "Reallyworld", "Sloth", "HolyWorld", "Legit"}, "Smooth");
    private final BooleanSetting slothHeadRotation = new BooleanSetting("\u041f\u043e\u0432\u043e\u0440\u043e\u0442 \u0413\u043e\u043b\u043e\u0432\u044b", "\u0421\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043a\u0430\u043c\u0435\u0440\u0443 \u0441 \u0440\u043e\u0442\u0430\u0446\u0438\u0435\u0439 Sloth \u043f\u0440\u0438 \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0438 Aura").setValue(false).visible(() -> "Sloth".equals(this.rotationType.get()));
    private final MultiSelectSetting targets = new MultiSelectSetting("\u0422\u0430\u0440\u0433\u0435\u0442\u044b", "\u0422\u0430\u0440\u0433\u0435\u0442\u044b").value("\u0418\u0433\u0440\u043e\u043a\u0438", "\u0413\u043e\u043b\u044b\u0435", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043a\u0438", "\u041c\u0438\u0440\u043d\u044b\u0435", "\u041c\u043e\u0431\u044b").selected("\u0418\u0433\u0440\u043e\u043a\u0438", "\u0413\u043e\u043b\u044b\u0435", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043a\u0438", "\u041c\u043e\u0431\u044b");
    public final SliderSettings attackRange = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0430\u0442\u0430\u043a\u0438", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0430\u0442\u0430\u043a\u0438").setValue(3.0f).range(0.0f, 6.0f).step(0.05f);
    private final SliderSettings aimRange = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430\u0432\u043e\u0434\u043a\u0438", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430\u0432\u043e\u0434\u043a\u0438").setValue(3.0f).range(0.0f, 6.0f).step(0.05f);
    private final SliderSettings elytraAimRange = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0430\u0445", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0430\u0445").setValue(50.0f).range(10.0f, 100.0f).step(0.05f);
    public final BooleanSetting smartCrit = new BooleanSetting("\u0423\u043c\u043d\u044b\u0435 \u043a\u0440\u0438\u0442\u044b", "\u0423\u043c\u043d\u044b\u0435 \u043a\u0440\u0438\u0442\u044b").setValue(true);
    public final RadioSetting sprintReset = new RadioSetting("\u0422\u0438\u043f \u0441\u043f\u0440\u0438\u043d\u0442\u0430", "\u0421\u0431\u0440\u043e\u0441 \u0441\u043f\u0440\u0438\u043d\u0442\u0430 \u043f\u0435\u0440\u0435\u0434 \u0443\u0434\u0430\u0440\u043e\u043c", new String[]{"\u041f\u0430\u043a\u0435\u0442\u043d\u043e", "\u041b\u0435\u0433\u0438\u0442\u043d\u043e"}, "\u041f\u0430\u043a\u0435\u0442\u043d\u043e");
    private final BooleanSetting throughWalls = new BooleanSetting("\u0411\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b", "\u0411\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b").setValue(true);
    private final BooleanSetting unpressShield = new BooleanSetting("\u041e\u0442\u0436\u0438\u043c\u0430\u0442\u044c \u0449\u0438\u0442", "\u041e\u0442\u0436\u0438\u043c\u0430\u0442\u044c \u0449\u0438\u0442").setValue(false);
    private final BooleanSetting breakShield = new BooleanSetting("\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442", "\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442").setValue(true);
    private final BooleanSetting attackOnEating = new BooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u043a\u043e\u0433\u0434\u0430 \u0435\u0448\u044c", "\u041d\u0435 \u0431\u0438\u0442\u044c \u043a\u043e\u0433\u0434\u0430 \u0435\u0448\u044c").setValue(true);
    private final RadioSetting moveFix = new RadioSetting("\u041a\u043e\u0440\u0440\u0435\u043a\u0446\u0438\u044f", "\u041a\u043e\u0440\u0440\u0435\u043a\u0446\u0438\u044f", new String[]{"\u041d\u0435\u0442", "\u0421\u0432\u043e\u0431\u043e\u0434\u043d\u0430\u044f", "\u0421\u0444\u043e\u043a\u0443\u0441\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f"}, "\u041d\u0435\u0442");
    private final RadioSetting priority = new RadioSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442", "\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442", new String[]{"\u041e\u043f\u0442\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435", "\u0423\u0433\u043e\u043b \u043f\u043e\u0432\u043e\u0440\u043e\u0442\u0430", "\u041d\u0438\u043a\u0430\u043a\u043e\u0439"}, "\u041e\u043f\u0442\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439");
    private static final double OPTIMAL_DISTANCE_WEIGHT = 3.0;
    private static final double OPTIMAL_ANGLE_WEIGHT = 2.0;
    private static final double OPTIMAL_HEALTH_WEIGHT = 1.5;
    private static final double ARMOR_HEALTH_DIVISOR = 20.0;
    private static final double ANGLE_BODY_HEIGHT_DIVISOR = 2.0;
    private static final double BLOCKING_BONUS = 2.0;
    private static final double NAKED_PENALTY = 1.5;
    private static final float LOW_HP_THRESHOLD = 8.0f;
    private static final double LOW_HP_BONUS = 1.0;
    private static final double VISIBLE_TARGET_BONUS = 2.0;
    private static final float STICKY_TARGET_RANGE_BONUS = 0.75f;
    private class_1309 target;
    private class_1309 lastTarget;
    private class_241 currentRotations = new class_241(0.0f, 0.0f);
    private class_241 targetRotations = new class_241(0.0f, 0.0f);
    private final TimerUtils attackTimer = new TimerUtils();
    private final ReallyworldRotation reallyworldRotation = new ReallyworldRotation();
    private final SlothRotation slothRotation = new SlothRotation();
    private final HolyWorldRotation holyWorldRotation = new HolyWorldRotation();
    private final LegitRotation legitRotation = new LegitRotation();
    private final TimerUtils backTimer = new TimerUtils();
    private long cps = 0L;
    private int ticksToAttack = 0;
    private int snapAttackAge = -1;
    private boolean snapAttackQueued = false;
    private class_1309 snapAttackTarget = null;
    private int shieldRestoreSlot = -1;
    private float lastYaw = 0.0f;
    private float lastPitch = 0.0f;
    private String lastRotationMode = "";
    private boolean movementCanCrit;
    private float focusMoveYaw = Float.NaN;
    private boolean silentLookReleased = false;
    private boolean gameUpdateRotatedThisTick;
    public static float adjYaw;
    public static float adjPitch;
    public static float otvodkaYaw;
    public static float otvodkaPitch;
    public boolean isRotated;

    public static AttackAura getInstance() {
        return Instance.get(AttackAura.class);
    }

    public AttackAura() {
        super("AttackAura", "AttackAura", ModuleCategory.COMBAT);
        this.setup(this.rotationType, this.slothHeadRotation, this.targets, this.attackRange, this.aimRange, this.elytraAimRange, this.smartCrit, this.sprintReset, this.attackOnEating, this.throughWalls, this.unpressShield, this.breakShield, this.moveFix, this.priority);
    }

    public boolean isSyncTpsEnabled() {
        return TpsSync.getInstance() != null && TpsSync.getInstance().isState();
    }

    private int moveFixIndex() {
        return Arrays.asList(this.moveFix.getOptions()).indexOf(this.moveFix.get());
    }

    public boolean isCombatRotationActive() {
        RotationStorage storage = RotationStorage.instance;
        if (storage != null && storage.isCameraReturning()) {
            return false;
        }
        return this.isState() && this.target != null;
    }

    public boolean hasMoveCorrection() {
        return this.isCombatRotationActive() && this.moveFixIndex() != 0;
    }

    public boolean isCameraReturning() {
        RotationStorage storage = RotationStorage.instance;
        return storage != null && storage.isCameraReturning();
    }

    @EventHandler
    public void onPlayerTick(TickEvent e) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null) {
            return;
        }
        this.lastYaw += 1.0f;
        this.updateTarget();
    }

    @EventHandler
    public void onAttackEntity(AttackEvent event) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null) {
            return;
        }
        class_1297 class_12972 = event.getEntity();
        if (!(class_12972 instanceof class_1309)) {
            return;
        }
        class_1309 living = (class_1309)class_12972;
        if (!this.isValidTarget(living)) {
            return;
        }
        this.target = living;
    }

    @EventHandler
    public void onMoveInput(InputEvent event) {
        this.applyMoveFix(event);
        this.applyKeyboardMoveFix(event);
        if (this.target != null && this.isLegitSprintReset()) {
            LegitSprintReset.applyInputReset(event);
        }
    }

    @EventHandler
    public void onSprint(SprintEvent event) {
        if (!this.isLegitSprintReset() || this.target == null || AttackAura.mc.field_1724 == null) {
            return;
        }
        if (this.shouldAttack() && AttackAura.mc.field_1724.method_5624()) {
            event.setSprinting(false);
        }
    }

    public boolean isSprintResetEnabled() {
        return this.isState();
    }

    public boolean isLegitSprintReset() {
        return "\u041b\u0435\u0433\u0438\u0442\u043d\u043e".equals(this.sprintReset.get());
    }

    public boolean isPacketSprintReset() {
        return "\u041f\u0430\u043a\u0435\u0442\u043d\u043e".equals(this.sprintReset.get());
    }

    public boolean isReallyworldRotation() {
        return "Reallyworld".equals(this.rotationType.get());
    }

    public boolean isHolyWorldRotation() {
        return "HolyWorld".equals(this.rotationType.get());
    }

    public boolean usesSilentRotation() {
        return !this.isHolyWorldRotation();
    }

    private void applyMoveFix(InputEvent event) {
        if (AttackAura.mc.field_1724 == null || this.target == null || this.moveFixIndex() == 0) {
            return;
        }
        if (this.moveFixIndex() == 1) {
            MovingUtil.fixMovementFree(event);
        }
    }

    private void applyKeyboardMoveFix(InputEvent event) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null || this.target == null || this.moveFixIndex() == 0) {
            return;
        }
        if (this.moveFixIndex() == 2) {
            MovingUtil.fixMovementFree(event, AttackAura.mc.field_1724.method_36454(), this.getTargetDirectionYaw());
        }
    }

    private float getTargetDirectionYaw() {
        float raw = RotationUtils.getRotations((class_243)this.target.method_5829().method_1005()).field_1343;
        this.focusMoveYaw = Float.isNaN(this.focusMoveYaw) || Math.abs(class_3532.method_15393((float)(raw - this.focusMoveYaw))) > 90.0f ? raw : class_3532.method_15393((float)(this.focusMoveYaw + class_3532.method_15393((float)(raw - this.focusMoveYaw)) * 0.25f));
        return this.focusMoveYaw;
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent event) {
        if (!this.isState() || !this.isHolyWorldRotation()) {
            return;
        }
        this.holyWorldRotation.onRender(event.getPartialTicks());
    }

    @EventHandler
    private void onGameUpdate(GameUpdateEvent e) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null || this.target == null) {
            return;
        }
        this.gameUpdateRotatedThisTick = true;
        this.rotate();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null) {
            return;
        }
        this.flushShieldRestore();
        if (this.ticksToAttack > 0) {
            --this.ticksToAttack;
        }
        if (!this.isPacketCritAttackPhase()) {
            this.processAttack();
        }
        if (this.target != null && !this.gameUpdateRotatedThisTick) {
            this.rotate();
        }
        this.gameUpdateRotatedThisTick = false;
    }

    private boolean isPacketCritAttackPhase() {
        PacketCriticals packetCriticals = CombatModules.getPacketCriticals();
        return packetCriticals != null && packetCriticals.shouldDeferMainTickAttack();
    }

    @EventHandler
    public void onPost(PostTickEvent e) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1687 == null) {
            return;
        }
        this.updateMovementCanCrit();
        PacketCriticals packetCriticals = CombatModules.getPacketCriticals();
        if (packetCriticals != null && packetCriticals.shouldAttackOnPostTick()) {
            this.processAttack();
        }
    }

    private void updateMovementCanCrit() {
        if (AttackAura.mc.field_1724 == null) {
            this.movementCanCrit = false;
            return;
        }
        AirStuck airStuck = Instance.get(AirStuck.class);
        if (airStuck != null && airStuck.isState()) {
            this.movementCanCrit = true;
            return;
        }
        this.movementCanCrit = !AttackAura.mc.field_1724.method_24828() && AttackAura.mc.field_1724.field_6017 > 0.0f && AttackAura.mc.field_1724.method_18798().field_1351 < 0.0;
    }

    private void processAttack() {
        this.updateTarget();
        if (this.target != null) {
            if ((this.shouldAttack() || this.shouldUseQueuedSnapAttack()) && this.cps <= System.currentTimeMillis()) {
                if (this.attackOnEating.isValue() && this.shouldBlockAttackWhileUsingItem()) {
                    return;
                }
                if (this.isLegitSprintReset() && !LegitSprintReset.prepareForAttack()) {
                    return;
                }
                if (this.isSnapRotationActive() && !this.prepareSnapAttack()) {
                    return;
                }
                this.attack();
                this.resetSnapAttack();
            }
        } else {
            this.cps = System.currentTimeMillis();
            this.backTimer.reset();
            adjPitch = 0.0f;
            adjYaw = 0.0f;
            this.reallyworldRotation.reset();
            this.slothRotation.reset();
            this.holyWorldRotation.reset();
            this.legitRotation.reset();
            this.focusMoveYaw = Float.NaN;
            this.ticksToAttack = 0;
            this.resetSnapAttack();
        }
    }

    public void Rotate() {
        this.rotate();
    }

    private void rotate() {
        RotationsSystem system;
        ElytraResolver elytraResolver = CombatModules.getElytraResolver();
        if (AttackAura.mc.field_1724 != null && elytraResolver != null && elytraResolver.isEscaping() && elytraResolver.getEscapePos() != null) {
            class_243 escapePos = elytraResolver.getEscapePos();
            class_243 eyePos = AttackAura.mc.field_1724.method_33571();
            double diffX = escapePos.field_1352 - eyePos.field_1352;
            double diffY = escapePos.field_1351 - eyePos.field_1351;
            double diffZ = escapePos.field_1350 - eyePos.field_1350;
            double horizontal = Math.sqrt(diffX * diffX + diffZ * diffZ);
            float yaw = (float)(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
            float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, horizontal)));
            float sens = ((Double)AttackAura.mc.field_1690.method_42495().method_41753()).floatValue();
            float gcd = sens * 0.6f + 0.2f;
            gcd = gcd * gcd * gcd * 1.2f;
            yaw -= yaw % gcd;
            pitch -= pitch % gcd;
            RotationStorage.update(new Rotation(yaw, pitch), 180.0f, 180.0f, 25.0f, 20.0f, 1, 1, false);
            return;
        }
        if (this.target == null || this.isCameraReturning()) {
            return;
        }
        if (!this.rotationType.get().equals(this.lastRotationMode)) {
            this.silentLookReleased = false;
            this.releaseSilentLook();
            this.reallyworldRotation.reset();
            this.slothRotation.reset();
            this.holyWorldRotation.reset();
            this.legitRotation.reset();
            this.resetSnapAttack();
            this.silentLookReleased = true;
            this.lastRotationMode = this.rotationType.get();
        }
        if (this.isSnapRotationActive()) {
            this.updateSnapRotation(this.target);
            return;
        }
        if (this.rotationType.get().equals("Smooth")) {
            system = new RotationsSystem(){

                @Override
                public void updateRotations(class_1309 target) {
                    class_243 aimPoint = AttackAura.this.isFallingCritPhase() ? AttackAura.this.getStableBodyPoint(target) : target.method_19538().method_1031(0.0, (double)(target.method_17682() * 0.6f), 0.0);
                    class_243 relativePos = aimPoint.method_1020(AttackAura.this.mc.field_1724.method_33571());
                    if (AttackAura.this.mc.field_1724.method_6128()) {
                        class_243 interpolatedRotation = class_243.method_1030((float)target.method_53829(), (float)target.method_53831());
                        class_243 rotationVector = target.method_5720();
                        class_243 blendedDirection = interpolatedRotation.method_1029().method_35590(rotationVector, interpolatedRotation.method_1033());
                        if (target.method_6128() && CombatModules.isElytraPredictActive()) {
                            relativePos = relativePos.method_1019(blendedDirection.method_1029().method_1021((double)CombatModules.getElytraForward()));
                        }
                    }
                    float yaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(relativePos.field_1350, relativePos.field_1352)) - 90.0));
                    float pitch = (float)(-Math.toDegrees(Math.atan2(relativePos.field_1351, Math.hypot(relativePos.field_1352, relativePos.field_1350))));
                    RotationStorage.update(new Rotation(yaw, pitch), 360.0f, 360.0f, 360.0f, 360.0f, 1, 1, false);
                }
            };
        } else if (this.rotationType.get().equals("Reallyworld")) {
            system = this.reallyworldRotation;
        } else if (this.rotationType.get().equals("Sloth")) {
            system = this.slothRotation;
        } else {
            if (this.rotationType.get().equals("HolyWorld")) {
                this.holyWorldRotation.updateRotations(this.target);
                return;
            }
            if (this.rotationType.get().equals("Legit")) {
                system = this.legitRotation;
            } else {
                final class_241 targetRot = RotationUtils.getRotations(this.getPredictedRotationPoint(this.target, this.target.method_30951(1.0f)));
                system = new RotationsSystem(){

                    @Override
                    public void updateRotations(class_1309 target) {
                        AttackAura.this.currentRotations = new class_241(AttackAura.this.mc.field_1724.method_36454(), AttackAura.this.mc.field_1724.method_36455());
                        RotationStorage.update(new Rotation(targetRot.field_1343, targetRot.field_1342), 360.0f, 360.0f, 360.0f, 360.0f, 1, 1, false);
                    }
                };
            }
        }
        system.updateRotations(this.target);
    }

    private void updateSnapRotation(class_1309 target) {
        class_241 targetRot;
        class_243 point = MultipointUtils.getClosestPoint((class_1297)target);
        if (point == null) {
            point = this.isFallingCritPhase() ? this.getStableBodyPoint(target) : target.method_5829().method_1005();
        }
        class_243 predicted = this.getPredictedRotationPoint(target, point);
        this.targetRotations = targetRot = RotationUtils.getRotations(predicted);
        this.currentRotations = new class_241(AttackAura.mc.field_1724.method_36454(), AttackAura.mc.field_1724.method_36455());
        boolean isAttackSnapTick = AttackAura.mc.field_1724.field_6012 <= this.snapAttackAge;
        boolean critSnapPhase = this.canAttackCritical(target) || IdealHitUtils.isInCritWindow(target);
        float finalYaw = targetRot.field_1343;
        float finalPitch = targetRot.field_1342;
        if (!isAttackSnapTick && !critSnapPhase) {
            finalYaw = FreeLookStorage.getFreeYaw();
            finalPitch = FreeLookStorage.getFreePitch();
        }
        RotationStorage.update(new Rotation(finalYaw, finalPitch), 360.0f, 360.0f, 360.0f, 360.0f, 0, 6, false);
    }

    private boolean isSnapRotationActive() {
        return this.rotationType.get().equals("Snap");
    }

    private boolean prepareSnapAttack() {
        if (!this.canAttackCritical(this.target)) {
            this.resetSnapAttack();
            return false;
        }
        if (!this.snapAttackQueued) {
            this.snapAttackQueued = true;
            this.snapAttackAge = AttackAura.mc.field_1724.field_6012;
            this.snapAttackTarget = this.target;
            this.forceSnapRotation(this.target);
            return this.isSnapAimReadyForAttack();
        }
        if (AttackAura.mc.field_1724.field_6012 > this.snapAttackAge + 1) {
            this.resetSnapAttack();
            return false;
        }
        if (AttackAura.mc.field_1724.field_6012 >= this.snapAttackAge) {
            this.forceSnapRotation(this.target);
            return this.isSnapAimReadyForAttack();
        }
        return false;
    }

    private void forceSnapRotation(class_1309 target) {
        class_241 targetRot;
        class_243 point = MultipointUtils.getClosestPoint((class_1297)target);
        if (point == null) {
            point = this.isFallingCritPhase() ? this.getStableBodyPoint(target) : target.method_5829().method_1005();
        }
        class_243 predicted = this.getPredictedRotationPoint(target, point);
        this.targetRotations = targetRot = RotationUtils.getRotations(predicted);
        this.currentRotations = new class_241(AttackAura.mc.field_1724.method_36454(), AttackAura.mc.field_1724.method_36455());
        float finalYaw = targetRot.field_1343;
        float finalPitch = targetRot.field_1342;
        RotationStorage.update(new Rotation(finalYaw, finalPitch), 360.0f, 360.0f, 360.0f, 360.0f, 0, 6, false);
    }

    private boolean shouldUseQueuedSnapAttack() {
        if (!this.snapAttackQueued || AttackAura.mc.field_1724 == null || this.target == null || this.target != this.snapAttackTarget) {
            return false;
        }
        if (AttackAura.mc.field_1724.field_6012 > this.snapAttackAge + 1) {
            this.resetSnapAttack();
            return false;
        }
        return AttackAura.mc.field_1724.field_6012 >= this.snapAttackAge;
    }

    private boolean isSnapAimReadyForAttack() {
        if (this.target == null || AttackAura.mc.field_1724 == null) {
            return false;
        }
        float aimYaw = AuraMixinHelper.resolveYaw(AttackAura.mc.field_1724.method_36454());
        float aimPitch = AuraMixinHelper.resolvePitch(AttackAura.mc.field_1724.method_36455());
        float yawDiff = Math.abs(class_3532.method_15393((float)(this.targetRotations.field_1343 - aimYaw)));
        float pitchDiff = Math.abs(this.targetRotations.field_1342 - aimPitch);
        boolean onTarget = AttackAura.mc.field_1724.method_6128();
        if (!onTarget) {
            class_3966 result = this.getAttackRaycastResult();
            onTarget = result != null && result.method_17782() == this.target;
        }
        return yawDiff <= 3.0f && pitchDiff <= 2.5f && onTarget;
    }

    private class_3966 getAttackRaycastResult() {
        class_243 lookVec;
        class_243 eyePos = AttackAura.mc.field_1724.method_5836(1.0f);
        if (AuraMixinHelper.isCombatActive() && this.usesSilentRotation()) {
            float yaw = AuraMixinHelper.resolveYaw(AttackAura.mc.field_1724.method_36454());
            float pitch = AuraMixinHelper.resolvePitch(AttackAura.mc.field_1724.method_36455());
            lookVec = class_243.method_1030((float)pitch, (float)yaw);
        } else {
            lookVec = AttackAura.mc.field_1724.method_5828(1.0f);
        }
        float reach = this.attackRange.getValue() * 2.0f;
        class_243 reachVec = eyePos.method_1019(lookVec.method_1021((double)reach));
        return class_1675.method_18075((class_1297)AttackAura.mc.field_1724, (class_243)eyePos, (class_243)reachVec, (class_238)AttackAura.mc.field_1724.method_5829().method_1014((double)reach), ex -> ex != AttackAura.mc.field_1724 && ex.method_5805(), (double)(reach * reach));
    }

    private class_243 getPredictedRotationPoint(class_1309 target, class_243 point) {
        if (AttackAura.mc.field_1724 != null && target != null && AttackAura.mc.field_1724.method_6128() && target.method_6128() && CombatModules.isElytraPredictActive()) {
            return PredictUtils.bypasselytrahacking(target);
        }
        return point;
    }

    private class_243 getStableBodyPoint(class_1309 target) {
        class_238 box = target.method_5829();
        return new class_243(box.method_1005().field_1352, box.field_1322 + box.method_17940() * 0.72, box.method_1005().field_1350);
    }

    private class_1309 findTarget() {
        ArrayList<class_1309> entities = new ArrayList<class_1309>();
        for (class_1297 entity2 : AttackAura.mc.field_1687.method_18112()) {
            class_1309 living;
            if (!(entity2 instanceof class_1309) || !this.isValidTarget(living = (class_1309)entity2, false)) continue;
            entities.add(living);
        }
        if (entities.isEmpty() || !this.isState()) {
            return null;
        }
        if (entities.size() == 1) {
            return (class_1309)entities.getFirst();
        }
        Comparator<class_1309> priorityComparator = this.getPriorityComparator();
        ElytraTarget elytraTarget = ElytraTarget.getInstance();
        if (elytraTarget != null && elytraTarget.isState() && elytraTarget.preferElytraTargets() && AttackAura.mc.field_1724.method_6128()) {
            entities.sort(Comparator.comparing(entity -> !entity.method_6128()).thenComparing(priorityComparator));
        } else {
            entities.sort(priorityComparator);
        }
        return (class_1309)entities.getFirst();
    }

    private Comparator<class_1309> getPriorityComparator() {
        return switch (this.priority.get()) {
            case "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f" -> Comparator.comparingDouble(entity -> AttackAura.mc.field_1724.method_5739((class_1297)entity));
            case "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435" -> Comparator.comparingDouble(this::getTargetHealthScore);
            case "\u0423\u0433\u043e\u043b", "\u0423\u0433\u043e\u043b \u043f\u043e\u0432\u043e\u0440\u043e\u0442\u0430" -> Comparator.comparingDouble(this::getTargetAngleScore);
            case "\u041d\u0438\u043a\u0430\u043a\u043e\u0439" -> Comparator.comparingInt(class_1297::method_5628);
            default -> Comparator.comparingDouble(this::getOptimalTargetScore);
        };
    }

    private double getOptimalTargetScore(class_1309 entity) {
        if (AttackAura.mc.field_1724 == null) {
            return Double.MAX_VALUE;
        }
        double score = (double)AttackAura.mc.field_1724.method_5739((class_1297)entity) * 3.0;
        score += this.getTargetAngleScore(entity) * 2.0;
        score += this.getTargetHealthScore(entity) * 1.5;
        score += this.getTargetProtection(entity);
        if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            if (player.method_6039()) {
                score += 2.0;
            }
            if (player.method_6096() == 0) {
                score -= 1.5;
            }
            if (player.method_6032() <= 8.0f) {
                score -= 1.0;
            }
        }
        if (AttackAura.mc.field_1724.method_6057((class_1297)entity)) {
            score -= 2.0;
        }
        return score;
    }

    private double getTargetHealthScore(class_1309 entity) {
        double health = entity.method_6032() + entity.method_6067();
        if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            health *= 1.0 + this.getTargetProtection((class_1309)player) / 20.0;
        }
        return health;
    }

    private double getTargetAngleScore(class_1309 entity) {
        if (AttackAura.mc.field_1724 == null) {
            return 0.0;
        }
        class_243 eyePos = AttackAura.mc.field_1724.method_33571();
        class_243 bodyCenter = entity.method_19538().method_1031(0.0, (double)entity.method_17682() / 2.0, 0.0);
        class_243 delta = bodyCenter.method_1020(eyePos);
        double targetYaw = Math.toDegrees(Math.atan2(delta.field_1350, delta.field_1352)) - 90.0;
        double targetPitch = -Math.toDegrees(Math.atan2(delta.field_1351, Math.sqrt(delta.field_1352 * delta.field_1352 + delta.field_1350 * delta.field_1350)));
        double yawDiff = class_3532.method_15393((float)((float)(targetYaw - (double)AttackAura.mc.field_1724.method_36454())));
        double pitchDiff = targetPitch - (double)AttackAura.mc.field_1724.method_36455();
        return Math.sqrt(yawDiff * yawDiff + pitchDiff * pitchDiff);
    }

    private double getTargetProtection(class_1309 entity) {
        if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            return player.method_45325(class_5134.field_23724);
        }
        return entity.method_6096();
    }

    private void updateTarget() {
        if (!this.isState()) {
            this.target = null;
            this.releaseSilentLookOnce();
            return;
        }
        if (this.target != null && this.isValidTarget(this.target, true)) {
            this.lastTarget = this.target;
            this.silentLookReleased = false;
            return;
        }
        class_1309 oldTarget = this.target;
        this.target = this.findTarget();
        if (this.target != null) {
            if (oldTarget != this.target) {
                this.resetSnapAttack();
            }
            this.lastTarget = this.target;
            this.silentLookReleased = false;
        } else {
            this.releaseSilentLookOnce();
        }
    }

    private void releaseSilentLookOnce() {
        if (this.silentLookReleased) {
            return;
        }
        if (!this.hasActiveSilentLook()) {
            this.silentLookReleased = true;
            return;
        }
        this.releaseSilentLook();
        this.silentLookReleased = true;
    }

    private boolean hasActiveSilentLook() {
        RotationStorage storage = RotationStorage.instance;
        if (storage != null && storage.isRotating()) {
            return true;
        }
        if (FreeLookStorage.isActive()) {
            return true;
        }
        return TurnsConnection.INSTANCE.getCurrentAngle() != null;
    }

    private void releaseSilentLook() {
        if (this.shouldSyncSlothHeadOnStop()) {
            this.applySlothHeadOnDisable();
        } else {
            this.finishCombatRotation();
        }
    }

    private boolean shouldSyncSlothHeadOnStop() {
        return "Sloth".equals(this.rotationType.get()) && this.slothHeadRotation.isValue();
    }

    private void finishCombatRotation() {
        if (RotationStorage.instance != null) {
            RotationStorage.instance.stopRotation();
        } else {
            FreeLookStorage.setActive(false);
            this.clearSilentRotationState();
        }
    }

    private void applySlothHeadOnDisable() {
        float yaw = this.slothRotation.getLastSentYaw();
        float pitch = this.slothRotation.getLastSentPitch();
        if (RotationStorage.instance != null) {
            RotationStorage.instance.stopWithoutCameraRestore();
        } else {
            FreeLookStorage.setActive(false);
        }
        this.clearSilentRotationState();
        if (AttackAura.mc.field_1724 != null) {
            AttackAura.mc.field_1724.method_36456(yaw);
            AttackAura.mc.field_1724.method_36457(pitch);
            AttackAura.mc.field_1724.field_6241 = yaw;
            AttackAura.mc.field_1724.field_6283 = yaw;
        }
        FreeLookStorage.setFreeYaw(yaw);
        FreeLookStorage.setFreePitch(pitch);
    }

    private void clearSilentRotationState() {
        TurnsConnection.INSTANCE.clear();
        TurnsConnection.INSTANCE.setRotation(null);
    }

    private boolean isFallingCritPhase() {
        return this.movementCanCrit && IdealHitUtils.isDivineCriticalState() || IdealHitUtils.isInCritWindow(this.target) || IdealHitUtils.isNoJumpDelayJumpCritWindow();
    }

    private float getAttackCooldownThreshold() {
        return IdealHitUtils.getAICooldown();
    }

    public boolean isLegitAimCommit() {
        if (AttackAura.mc.field_1724 == null) {
            return true;
        }
        return AttackAura.mc.field_1724.method_7261(1.5f) >= this.getAttackCooldownThreshold();
    }

    private void attack() {
        ElytraResolver elytraResolver;
        class_1657 player;
        ClientPlayerEntityAccessor accessor;
        class_1268 blockedShieldHand = null;
        if (this.unpressShield.isValue() && (blockedShieldHand = this.getBlockingShieldHand()) != null) {
            AttackAura.mc.field_1761.method_2897((class_1657)AttackAura.mc.field_1724);
        }
        boolean sprintStop = false;
        if (this.isPacketSprintReset() && AttackAura.mc.field_1724.field_3944 != null && !AttackAura.mc.field_1724.method_5869() && !AttackAura.mc.field_1724.method_5799() && (accessor = (ClientPlayerEntityAccessor)AttackAura.mc.field_1724).getLastSprinting()) {
            AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)AttackAura.mc.field_1724, class_2848.class_2849.field_12985));
            AttackAura.mc.field_1724.method_5728(false);
            sprintStop = true;
        }
        boolean attacked = false;
        class_1309 class_13092 = this.target;
        if (class_13092 instanceof class_1657 && (player = (class_1657)class_13092).method_6039() && this.breakShield.isValue()) {
            attacked = this.shieldBreak(player);
        }
        if (!attacked) {
            AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)this.target);
        }
        if ((elytraResolver = CombatModules.getElytraResolver()) != null) {
            elytraResolver.onAuraAttack();
        }
        AttackAura.mc.field_1724.method_6104(class_1268.field_5808);
        if ("Sloth".equals(this.rotationType.get())) {
            this.slothRotation.onAttack();
        }
        if (this.isPacketSprintReset() && sprintStop && this.canRestartSprintAfterAttack() && AttackAura.mc.field_1724.field_3944 != null) {
            AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)AttackAura.mc.field_1724, class_2848.class_2849.field_12981));
            AttackAura.mc.field_1724.method_5728(true);
        }
        if (blockedShieldHand != null && !ItemUseHelper.isUsingPriorityItem((class_1657)AttackAura.mc.field_1724)) {
            this.restoreShieldBlocking(blockedShieldHand);
        }
        long cooldown = 467L;
        if (TpsSync.getInstance() != null && TpsSync.getInstance().isState()) {
            cooldown = (long)((float)CombatModules.getAdjustedCooldown(cooldown, true) * 1.1f);
        }
        if (this.rotationType.get().equals("Legit")) {
            cooldown = HumanCps.humanize(cooldown);
        }
        this.cps = System.currentTimeMillis() + cooldown;
        this.ticksToAttack = 10;
        this.attackTimer.reset();
    }

    public boolean canRestartSprintAfterAttack() {
        return AttackAura.mc.field_1724.field_3913.field_3905 > 0.0f && !AttackAura.mc.field_1724.method_6059(class_1294.field_5919) && !AttackAura.mc.field_1724.method_6128() && !AttackAura.mc.field_1724.method_6115() && !AttackAura.mc.field_1724.field_5976 && AttackAura.mc.field_1724.method_7344().method_7586() > 6 && !AttackAura.mc.field_1724.method_5715();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean shieldBreak(class_1657 entity) {
        SlotSearchResult axeSlot = HotbarUtil.getAxe();
        if (!axeSlot.found()) {
            return false;
        }
        int previousSlot = AttackAura.mc.field_1724.method_31548().field_7545;
        if (axeSlot.slot() == previousSlot) {
            AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)entity);
            return true;
        }
        if (axeSlot.isInHotBar()) {
            return this.attackWithDeferredHotbarSlot(entity, axeSlot.slot(), previousSlot);
        }
        if (AttackAura.mc.field_1724.field_7512.field_7763 != 0) {
            return false;
        }
        int swapHotbarSlot = this.findSilentSwapHotbarSlot(previousSlot);
        if (swapHotbarSlot == -1) {
            return false;
        }
        this.swapInventoryIntoHotbar(axeSlot.slot(), swapHotbarSlot);
        boolean attacked = false;
        try {
            attacked = this.attackWithSilentHotbarSlot(entity, swapHotbarSlot, previousSlot);
        }
        finally {
            this.swapInventoryIntoHotbar(axeSlot.slot(), swapHotbarSlot);
        }
        return attacked;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean attackWithSilentHotbarSlot(class_1657 entity, int attackSlot, int previousSlot) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1724.field_3944 == null || AttackAura.mc.field_1761 == null) {
            return false;
        }
        AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(attackSlot));
        try {
            AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)entity);
            boolean bl = true;
            return bl;
        }
        finally {
            AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(previousSlot));
        }
    }

    private boolean attackWithDeferredHotbarSlot(class_1657 entity, int attackSlot, int previousSlot) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1724.field_3944 == null || AttackAura.mc.field_1761 == null) {
            return false;
        }
        AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(attackSlot));
        AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)entity);
        this.shieldRestoreSlot = previousSlot;
        return true;
    }

    private void flushShieldRestore() {
        if (this.shieldRestoreSlot == -1) {
            return;
        }
        if (AttackAura.mc.field_1724 != null && AttackAura.mc.field_1724.field_3944 != null) {
            AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2868(this.shieldRestoreSlot));
        }
        this.shieldRestoreSlot = -1;
    }

    private void swapInventoryIntoHotbar(int inventorySlot, int hotbarSlot) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1761 == null || AttackAura.mc.field_1724.field_3944 == null) {
            return;
        }
        int syncId = AttackAura.mc.field_1724.field_7512.field_7763;
        AttackAura.mc.field_1761.method_2906(syncId, inventorySlot, hotbarSlot, class_1713.field_7791, (class_1657)AttackAura.mc.field_1724);
        AttackAura.mc.field_1724.field_3944.method_52787((class_2596)new class_2815(syncId));
    }

    private int findSilentSwapHotbarSlot(int previousSlot) {
        for (int slot = 8; slot >= 0; --slot) {
            if (slot == previousSlot) continue;
            return slot;
        }
        return previousSlot >= 0 && previousSlot < 9 ? previousSlot : -1;
    }

    private class_1268 getBlockingShieldHand() {
        if (AttackAura.mc.field_1724 == null || !AttackAura.mc.field_1724.method_6039()) {
            return null;
        }
        class_1268 activeHand = AttackAura.mc.field_1724.method_6058();
        if (activeHand == null) {
            return null;
        }
        return this.isShieldStack(AttackAura.mc.field_1724.method_5998(activeHand)) ? activeHand : null;
    }

    private boolean shouldBlockAttackWhileUsingItem() {
        if (AttackAura.mc.field_1724 == null || !AttackAura.mc.field_1724.method_6115()) {
            return false;
        }
        if (this.unpressShield.isValue() && this.getBlockingShieldHand() != null) {
            return false;
        }
        return ItemUseHelper.isPriorityUseItem(AttackAura.mc.field_1724.method_6030());
    }

    private void restoreShieldBlocking(class_1268 hand) {
        if (AttackAura.mc.field_1724 == null || AttackAura.mc.field_1761 == null || hand == null) {
            return;
        }
        if (!this.isShieldStack(AttackAura.mc.field_1724.method_5998(hand)) || AttackAura.mc.field_1724.method_6115()) {
            return;
        }
        AttackAura.mc.field_1761.method_2919((class_1657)AttackAura.mc.field_1724, hand);
    }

    private boolean isShieldStack(class_1799 stack) {
        return !stack.method_7960() && stack.method_7909() instanceof class_1819;
    }

    private boolean isWeapon() {
        class_1792 item = AttackAura.mc.field_1724.method_6047().method_7909();
        return item != class_1802.field_8162 && (item instanceof class_1829 || item instanceof class_1810 || item instanceof class_1743 || item instanceof class_1794 || item instanceof class_1821 || item instanceof class_9362 || item == class_1802.field_49814);
    }

    private boolean isValidTarget(class_1309 entity) {
        return this.isValidTarget(entity, false);
    }

    private boolean isValidTarget(class_1309 entity, boolean sticky) {
        class_243 nearestPoint;
        class_1657 player;
        if (entity == null || entity == AttackAura.mc.field_1724) {
            return false;
        }
        if (!entity.method_5805() || entity.method_6032() <= 0.0f) {
            return false;
        }
        if (entity instanceof class_1531) {
            return false;
        }
        if (entity instanceof class_1439 || entity instanceof class_1420) {
            return false;
        }
        if (entity instanceof class_1657) {
            player = (class_1657)entity;
            if (AntiBot.getInstance().isState() && AntiBot.checkBot((class_1309)player)) {
                return false;
            }
        }
        if (entity instanceof class_1657) {
            player = (class_1657)entity;
            if (!this.targets.isSelected("\u0418\u0433\u0440\u043e\u043a\u0438")) {
                return false;
            }
            if (this.isNaked(player) && !this.targets.isSelected("\u0413\u043e\u043b\u044b\u0435") && !FakePlayerManager.isFakePlayer((class_1297)player)) {
                return false;
            }
            if (player.method_6059(class_1294.field_5905) && !this.targets.isSelected("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043a\u0438")) {
                return false;
            }
            if (FriendUtils.isFriend(entity.method_5477().getString())) {
                return false;
            }
        } else if (entity instanceof class_1296 || entity instanceof class_1431) {
            if (!this.targets.isSelected("\u041c\u0438\u0440\u043d\u044b\u0435")) {
                return false;
            }
        } else if (entity instanceof class_1588) {
            if (!this.targets.isSelected("\u041c\u043e\u0431\u044b")) {
                return false;
            }
        } else {
            return false;
        }
        if ((nearestPoint = BestPoint.getNearestPoint((class_1297)entity)) == null) {
            nearestPoint = MultipointUtils.getClosestPoint((class_1297)entity);
        }
        float maxRange = this.getMaxAimRange() + (sticky ? 0.75f : 0.0f);
        if (AttackAura.mc.field_1724.method_33571().method_1022(nearestPoint) > (double)maxRange) {
            return false;
        }
        return this.throughWalls.isValue() || AttackAura.mc.field_1724.method_6057((class_1297)entity);
    }

    private boolean isNaked(class_1657 player) {
        for (class_1799 armorStack : player.method_5661()) {
            if (armorStack.method_7960()) continue;
            return false;
        }
        return true;
    }

    private boolean shouldAttack() {
        boolean raycastMiss;
        if (AttackAura.mc.field_1724.method_7261(1.5f) < this.getAttackCooldownThreshold()) {
            return false;
        }
        class_3966 result = this.getAttackRaycastResult();
        boolean bl = raycastMiss = result == null || result.method_17782() != this.target;
        if (raycastMiss && !AttackAura.mc.field_1724.method_6128()) {
            return false;
        }
        if (AttackAura.mc.field_1724.method_6128() && this.target.method_6128()) {
            class_243 targetPos = this.target.method_19538().method_1031(0.0, (double)this.target.method_17682() / 2.0, 0.0);
            int predict = CombatModules.isElytraPredictActive() ? CombatModules.getElytraForwardTicks() : 0;
            class_243 predictedPos = PredictUtils.predict(this.target, targetPos, predict);
            if (AttackAura.mc.field_1724.method_33571().method_1022(predictedPos) > 5.0) {
                return false;
            }
        } else if (AttackAura.mc.field_1724.method_6128()) {
            if (AttackAura.mc.field_1724.method_33571().method_1022(this.target.method_5829().method_1005()) > (double)this.attackRange.getValue()) {
                return false;
            }
        } else {
            class_243 checkPoint;
            double distanceCheck = AttackAura.mc.field_1724.method_33571().method_1022(this.target.method_5829().method_1005());
            class_243 class_2432 = checkPoint = distanceCheck > 3.0 ? BestPoint.getNearestPoint((class_1297)this.target) : this.target.method_5829().method_1005();
            if (checkPoint == null) {
                checkPoint = MultipointUtils.getClosestPoint((class_1297)this.target);
            }
            if (AttackAura.mc.field_1724.method_33571().method_1022(checkPoint) > (double)this.attackRange.getValue()) {
                return false;
            }
        }
        if (this.rotationType.get().equals("Legit") && HitChance.get(this.target) < 0.1f) {
            return false;
        }
        return this.shouldAttackMovementCrit();
    }

    private boolean shouldAttackMovementCrit() {
        return this.shouldAttackMovementCrit(this.target);
    }

    private boolean shouldAttackMovementCrit(class_1309 attackTarget) {
        boolean packetCrits;
        if (IdealHitUtils.isJumpBlockedByCeiling()) {
            return true;
        }
        boolean inCobweb = IdealHitUtils.isInCobweb();
        boolean isClimbing = AttackAura.mc.field_1724.method_6101();
        boolean isSwimming = AttackAura.mc.field_1724.method_5681();
        boolean isTouchingWater = AttackAura.mc.field_1724.method_5799();
        boolean bl = packetCrits = CombatModules.getPacketCriticals() != null && CombatModules.getPacketCriticals().isState();
        if (packetCrits) {
            PacketCriticals packetCriticals = CombatModules.getPacketCriticals();
            if (packetCriticals.isOldHolyworldMode() || packetCriticals.isKrystalMode()) {
                if (this.smartCrit.isValue() && !inCobweb && !isSwimming && !isTouchingWater) {
                    return AttackAura.mc.field_1724.method_24828();
                }
                return true;
            }
            if (this.smartCrit.isValue() && !inCobweb && !isSwimming && !isTouchingWater) {
                return AttackAura.mc.field_1724.method_24828();
            }
            return IdealHitUtils.isDivineCriticalState() || IdealHitUtils.canCritical(attackTarget);
        }
        if (this.smartCrit.isValue()) {
            boolean air;
            if (inCobweb || isSwimming || isTouchingWater) {
                return IdealHitUtils.canCritical(attackTarget);
            }
            boolean bl2 = air = AttackAura.mc.field_1690 != null && AttackAura.mc.field_1690.field_1903.method_1434() || !AttackAura.mc.field_1724.method_24828() && !inCobweb && !isClimbing && !isSwimming && !isTouchingWater;
            if (!air) {
                return AttackAura.mc.field_1724.method_24828();
            }
            return IdealHitUtils.canCritical(attackTarget);
        }
        return IdealHitUtils.isDivineCriticalState() && this.movementCanCrit;
    }

    private boolean canAttackCritical(class_1309 attackTarget) {
        return this.shouldAttackMovementCrit(attackTarget);
    }

    public boolean isAboveWater() {
        class_2338 pos = class_2338.method_49638((class_2374)AttackAura.mc.field_1724.method_19538().method_1031(0.0, -0.4, 0.0));
        return !AttackAura.mc.field_1724.method_5869() && AttackAura.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10382);
    }

    public float getAttackCooldown() {
        return class_3532.method_15363((float)((float)((ILivingEntity)AttackAura.mc.field_1724).getLastAttackedTicks() / this.getAttackCooldownProgressPerTick()), (float)0.0f, (float)1.0f);
    }

    public float getAttackCooldownProgressPerTick() {
        return (float)(1.0 / AttackAura.mc.field_1724.method_45325(class_5134.field_23723) * 20.0);
    }

    private float getMaxAimRange() {
        return AttackAura.mc.field_1724.method_6128() ? this.elytraAimRange.getValue() : this.attackRange.getValue() + this.aimRange.getValue();
    }

    @Override
    public void deactivate() {
        LegitSprintReset.reset();
        this.silentLookReleased = false;
        this.releaseSilentLook();
        this.silentLookReleased = true;
        super.deactivate();
        if (this.target != null) {
            this.backTimer.reset();
        }
        this.target = null;
        this.lastTarget = null;
        this.reallyworldRotation.reset();
        this.slothRotation.reset();
        this.holyWorldRotation.reset();
        this.legitRotation.reset();
        this.movementCanCrit = false;
        this.lastRotationMode = "";
        this.focusMoveYaw = Float.NaN;
        this.ticksToAttack = 0;
        this.resetSnapAttack();
        this.flushShieldRestore();
        adjYaw = 0.0f;
        adjPitch = 0.0f;
        otvodkaYaw = 0.0f;
        otvodkaPitch = 0.0f;
        this.isRotated = false;
        this.lastYaw = 0.0f;
        this.lastPitch = 0.0f;
    }

    @Override
    public void activate() {
        super.activate();
        this.silentLookReleased = false;
        this.normalizeRotationType();
        this.reallyworldRotation.reset();
        this.slothRotation.reset();
        this.holyWorldRotation.reset();
        this.legitRotation.reset();
        this.movementCanCrit = false;
        this.lastRotationMode = this.rotationType.get();
        this.ticksToAttack = 0;
        this.resetSnapAttack();
        adjYaw = 0.0f;
        adjPitch = 0.0f;
        otvodkaYaw = 0.0f;
        otvodkaPitch = 0.0f;
        this.isRotated = false;
        if (AttackAura.mc.field_1724 != null) {
            this.currentRotations = new class_241(AttackAura.mc.field_1724.method_36454(), AttackAura.mc.field_1724.method_36455());
            this.lastYaw = AttackAura.mc.field_1724.method_36454();
            this.lastPitch = AttackAura.mc.field_1724.method_36455();
        }
    }

    private void normalizeRotationType() {
        String rotation = this.rotationType.get();
        if ("NoRotate".equals(rotation)) {
            this.rotationType.set("Smooth");
            return;
        }
        if ("Sloth 2".equals(rotation) || "Astrum".equals(rotation)) {
            this.rotationType.set("Sloth");
            return;
        }
        if ("SpookyTimeRotation2".equals(rotation) || "SpookyTime".equals(rotation) || "SpookyTime2".equals(rotation) || "FunTime".equals(rotation) || "FunTime 2".equals(rotation)) {
            this.rotationType.set("Smooth");
            return;
        }
        if (Arrays.asList(this.rotationType.getOptions()).contains(rotation)) {
            return;
        }
        this.rotationType.set("Smooth");
    }

    private void resetSnapAttack() {
        this.snapAttackAge = -1;
        this.snapAttackQueued = false;
        this.snapAttackTarget = null;
    }

    public RadioSetting getSprintReset() {
        return this.sprintReset;
    }

    public class_1309 getTarget() {
        return this.target;
    }

    public class_1309 getLastTarget() {
        return this.lastTarget;
    }

    public class_241 getCurrentRotations() {
        return this.currentRotations;
    }

    public class_241 getTargetRotations() {
        return this.targetRotations;
    }

    public TimerUtils getAttackTimer() {
        return this.attackTimer;
    }
}
