/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1743
 *  net.minecraft.class_1802
 *  net.minecraft.class_1821
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.features.combat;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.CombatModules;
import code.alphadlc.features.impl.combat.PacketCriticals;
import code.alphadlc.features.impl.combat.TriggerBot;
import code.alphadlc.mixins.IEntity;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1743;
import net.minecraft.class_1802;
import net.minecraft.class_1821;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3532;

public final class IdealHitUtils
implements QuickImports {
    private static final int WATER_CRIT_INTENT_TICKS = 8;
    private static final int WATER_CRIT_CONTACT_TICKS = 10;
    private static final double WATER_CRIT_MIN_UPWARD_VELOCITY = 0.05;
    private static int lastWaterContactAge = Integer.MIN_VALUE;
    private static int lastWaterCritIntentAge = Integer.MIN_VALUE;

    public static float getAICooldown() {
        if (IdealHitUtils.mc.field_1724.method_6047().method_7909() == class_1802.field_8162) {
            return 0.9f;
        }
        if (IdealHitUtils.mc.field_1724.method_6047().method_7909() instanceof class_1743 || IdealHitUtils.mc.field_1724.method_6047().method_7909() instanceof class_1821) {
            return 0.95f;
        }
        return 0.93f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean canAIFall() {
        class_2338 posWater = class_2338.method_49638((class_2374)IdealHitUtils.mc.field_1724.method_19538().method_1031(0.0, (double)-0.4f, 0.0));
        if (IdealHitUtils.mc.field_1687.method_8320(posWater).method_27852(class_2246.field_10382)) {
            return true;
        }
        if (IdealHitUtils.getBlock(0.0, 3.0, 0.0) == class_2246.field_10124 && IdealHitUtils.getBlock(0.0, 2.0, 0.0) == class_2246.field_10124) {
            if (IdealHitUtils.getBlock(0.0, 1.0, 0.0) == class_2246.field_10124) return true;
        }
        if (IdealHitUtils.mc.field_1724.field_6017 < (IdealHitUtils.getBlock(0.0, 2.0, 0.0) != class_2246.field_10124 ? 0.08f : 0.6f)) return true;
        if (!(IdealHitUtils.mc.field_1724.field_6017 > 1.2f)) return false;
        return true;
    }

    public static boolean canCritical(class_1309 target) {
        return IdealHitUtils.canCriticalInternal(target, true);
    }

    public static boolean canStrictCritical(class_1309 target) {
        return IdealHitUtils.canCriticalInternal(target, false);
    }

    private static boolean canCriticalInternal(class_1309 target, boolean allowSmartCrit) {
        boolean smartCrit;
        IdealHitUtils.updateWaterCritState();
        boolean packetCrits = CombatModules.getPacketCriticals() != null && CombatModules.getPacketCriticals().isState();
        PacketCriticals packetCriticals = CombatModules.getPacketCriticals();
        boolean hasSlowFalling = IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5906);
        boolean inCobweb = IdealHitUtils.isInCobweb();
        AttackAura aura = CombatModules.getAura();
        TriggerBot triggerBot = TriggerBot.getInstance();
        boolean bl = aura != null && aura.isState() ? aura.smartCrit.isValue() : (triggerBot != null && triggerBot.isState() ? triggerBot.smartCrit.isValue() : (smartCrit = aura != null && aura.smartCrit.isValue()));
        if (packetCrits && inCobweb) {
            return true;
        }
        if (packetCrits && packetCriticals != null && packetCriticals.isOldHolyworldMode()) {
            return true;
        }
        if (packetCrits && packetCriticals != null && packetCriticals.isKrystalMode() && (inCobweb || IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5909))) {
            return true;
        }
        if (packetCrits && hasSlowFalling) {
            return IdealHitUtils.mc.field_1724.method_18798().field_1351 < 0.0 && IdealHitUtils.mc.field_1724.field_6017 > 0.0f;
        }
        if (IdealHitUtils.isTryingWaterCrit()) {
            return IdealHitUtils.isWaterCritWindow();
        }
        if (IdealHitUtils.isNoJumpDelayCeilingCritIntent()) {
            return IdealHitUtils.isNoJumpDelayCeilingCritWindow();
        }
        if (IdealHitUtils.isNoJumpDelayJumpCritIntent() && IdealHitUtils.isNoJumpDelayJumpCritWindow()) {
            return true;
        }
        if (IdealHitUtils.cannotPerformCrit()) {
            return true;
        }
        if (IdealHitUtils.isInCritWindow(target)) {
            return true;
        }
        if (allowSmartCrit && smartCrit) {
            if (IdealHitUtils.mc.field_1690 != null && IdealHitUtils.mc.field_1690.field_1903.method_1434()) {
                return false;
            }
            return IdealHitUtils.mc.field_1724.method_24828();
        }
        return false;
    }

    public static boolean isInCritWindow() {
        return IdealHitUtils.isInCritWindow(null);
    }

    public static boolean isInCritWindow(class_1309 target) {
        if (IdealHitUtils.mc.field_1724 == null) {
            return false;
        }
        if (IdealHitUtils.mc.field_1724.method_24828() || IdealHitUtils.mc.field_1724.method_6101() || IdealHitUtils.mc.field_1724.method_5799() || IdealHitUtils.mc.field_1724.method_5869() || IdealHitUtils.mc.field_1724.method_6128() || IdealHitUtils.mc.field_1724.method_5765()) {
            return false;
        }
        if (IdealHitUtils.mc.field_1724.field_6017 <= 0.001f) {
            return false;
        }
        if (IdealHitUtils.mc.field_1724.method_18798().field_1351 > (double)0.02f) {
            return false;
        }
        if (target == null) {
            return true;
        }
        AttackAura aura = CombatModules.getAura();
        float attackRange = aura != null ? aura.attackRange.getValue() : 3.0f;
        return IdealHitUtils.mc.field_1724.method_5739((class_1297)target) <= attackRange + 0.75f;
    }

    private static boolean isNoJumpDelayCeilingCritIntent() {
        return CombatModules.getNoJumpDelay() != null && CombatModules.getNoJumpDelay().isState() && IdealHitUtils.mc.field_1690 != null && IdealHitUtils.mc.field_1690.field_1903.method_1434() && IdealHitUtils.hasLowCeilingForJumpCrit();
    }

    private static boolean isNoJumpDelayJumpCritIntent() {
        return CombatModules.getNoJumpDelay() != null && CombatModules.getNoJumpDelay().isState() && IdealHitUtils.mc.field_1690 != null && IdealHitUtils.mc.field_1690.field_1903.method_1434();
    }

    private static boolean isNoJumpDelayCeilingCritWindow() {
        return IdealHitUtils.mc.field_1724 != null && !IdealHitUtils.mc.field_1724.method_24828() && IdealHitUtils.mc.field_1724.method_18798().field_1351 <= 0.01 && !IdealHitUtils.mc.field_1724.method_5799() && !IdealHitUtils.mc.field_1724.method_5869() && !IdealHitUtils.mc.field_1724.method_5771() && !IdealHitUtils.mc.field_1724.method_6101() && !IdealHitUtils.mc.field_1724.method_5765() && !IdealHitUtils.mc.field_1724.method_31549().field_7479;
    }

    public static boolean isNoJumpDelayJumpCritWindow() {
        return IdealHitUtils.mc.field_1724 != null && IdealHitUtils.mc.field_1687 != null && CombatModules.getNoJumpDelay() != null && CombatModules.getNoJumpDelay().isState() && IdealHitUtils.mc.field_1690 != null && IdealHitUtils.mc.field_1690.field_1903.method_1434() && !IdealHitUtils.mc.field_1724.method_24828() && IdealHitUtils.mc.field_1724.field_6017 > 0.0f && IdealHitUtils.mc.field_1724.method_18798().field_1351 < 0.0 && !IdealHitUtils.mc.field_1724.method_5799() && !IdealHitUtils.mc.field_1724.method_5869() && !IdealHitUtils.mc.field_1724.method_5771() && !IdealHitUtils.mc.field_1724.method_6101() && !IdealHitUtils.mc.field_1724.method_5765() && !IdealHitUtils.mc.field_1724.method_31549().field_7479 && !IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5902) && !IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5906) && !IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5919) && !IdealHitUtils.mc.field_1724.method_6128() && !IdealHitUtils.isInCobweb();
    }

    private static boolean hasLowCeilingForJumpCrit() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1687 == null) {
            return false;
        }
        class_238 box = IdealHitUtils.mc.field_1724.method_5829().method_1011(0.03);
        class_238 headBox = new class_238(box.field_1323, box.field_1325, box.field_1321, box.field_1320, box.field_1325 + 0.32, box.field_1324);
        for (class_2338 pos : class_2338.method_10094((int)class_3532.method_15357((double)headBox.field_1323), (int)class_3532.method_15357((double)headBox.field_1322), (int)class_3532.method_15357((double)headBox.field_1321), (int)class_3532.method_15357((double)headBox.field_1320), (int)class_3532.method_15357((double)headBox.field_1325), (int)class_3532.method_15357((double)headBox.field_1324))) {
            class_2680 state = IdealHitUtils.mc.field_1687.method_8320(pos);
            if (state.method_26215() || state.method_26220((class_1922)IdealHitUtils.mc.field_1687, pos).method_1110()) continue;
            return true;
        }
        return false;
    }

    public static boolean canPacketCrit() {
        return IdealHitUtils.isInCobweb() || IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5906);
    }

    private static void updateWaterCritState() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1687 == null) {
            lastWaterContactAge = Integer.MIN_VALUE;
            lastWaterCritIntentAge = Integer.MIN_VALUE;
            return;
        }
        boolean nearWaterSurface = IdealHitUtils.isNearWaterSurface();
        if (!nearWaterSurface) {
            return;
        }
        lastWaterContactAge = IdealHitUtils.mc.field_1724.field_6012;
        if (IdealHitUtils.isWaterCritIntentState()) {
            lastWaterCritIntentAge = IdealHitUtils.mc.field_1724.field_6012;
        }
    }

    private static boolean isWaterCritIntentState() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1690 == null) {
            return false;
        }
        return IdealHitUtils.mc.field_1690.field_1903.method_1434() && !IdealHitUtils.mc.field_1724.method_24828() && !IdealHitUtils.mc.field_1724.method_5869() && IdealHitUtils.mc.field_1724.method_18798().field_1351 > 0.05;
    }

    public static boolean isTryingWaterCrit() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1690 == null || !IdealHitUtils.mc.field_1690.field_1903.method_1434()) {
            return false;
        }
        return IdealHitUtils.mc.field_1724.field_6012 - lastWaterCritIntentAge <= 8 && IdealHitUtils.mc.field_1724.field_6012 - lastWaterContactAge <= 10;
    }

    public static boolean isWaterCritWindow() {
        return IdealHitUtils.mc.field_1724 != null && !IdealHitUtils.mc.field_1724.method_24828() && !IdealHitUtils.mc.field_1724.method_5799() && !IdealHitUtils.mc.field_1724.method_5869() && IdealHitUtils.mc.field_1724.field_6017 > 0.0f && IdealHitUtils.mc.field_1724.method_18798().field_1351 < 0.0;
    }

    private static boolean isNearWaterSurface() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1687 == null) {
            return false;
        }
        class_2338 below = class_2338.method_49638((class_2374)IdealHitUtils.mc.field_1724.method_19538().method_1031(0.0, (double)-0.4f, 0.0));
        return IdealHitUtils.mc.field_1724.method_5799() || IdealHitUtils.mc.field_1724.method_5869() || IdealHitUtils.mc.field_1687.method_8320(below).method_27852(class_2246.field_10382);
    }

    private static boolean cannotPerformCrit() {
        double effectiveJumpHeight = IdealHitUtils.mc.field_1724.method_49476();
        class_243 jumpVec = new class_243(0.0, effectiveJumpHeight, 0.0);
        class_243 allowedMovement = ((IEntity)IdealHitUtils.mc.field_1724).invokeAdjustMovementForCollisions(jumpVec);
        boolean cobweb = IdealHitUtils.isInCobweb();
        class_2338 posWater = class_2338.method_49638((class_2374)IdealHitUtils.mc.field_1724.method_19538().method_1031(0.0, (double)(IdealHitUtils.mc.field_1724.method_17682() / 2.0f), 0.0));
        return IdealHitUtils.mc.field_1724.method_5771() || IdealHitUtils.mc.field_1724.method_6101() || IdealHitUtils.mc.field_1687.method_8320(posWater).method_27852(class_2246.field_10382) || IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5902) || IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5906) || IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5919) || cobweb || IdealHitUtils.mc.field_1724.method_6128() || IdealHitUtils.mc.field_1724.method_5765() || IdealHitUtils.mc.field_1724.method_31549().field_7479 || IdealHitUtils.mc.field_1724.method_5799() || allowedMovement.field_1351 < (double)IdealHitUtils.mc.field_1724.method_49476() - 0.5 && IdealHitUtils.mc.field_1724.method_24828();
    }

    public static boolean isInCobweb() {
        class_238 box = IdealHitUtils.mc.field_1724.method_5829();
        for (class_2338 pos : class_2338.method_10094((int)class_3532.method_15357((double)box.field_1323), (int)class_3532.method_15357((double)box.field_1322), (int)class_3532.method_15357((double)box.field_1321), (int)class_3532.method_15357((double)box.field_1320), (int)class_3532.method_15357((double)box.field_1325), (int)class_3532.method_15357((double)box.field_1324))) {
            if (!IdealHitUtils.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10343)) continue;
            return true;
        }
        return false;
    }

    public static class_2248 getBlock(double x, double y, double z) {
        return IdealHitUtils.mc.field_1687.method_8320(IdealHitUtils.mc.field_1724.method_24515().method_10069((int)x, (int)y, (int)z)).method_26204();
    }

    public static boolean isJumpBlockedByCeiling() {
        if (IdealHitUtils.mc.field_1724 == null || IdealHitUtils.mc.field_1687 == null) {
            return false;
        }
        if (IdealHitUtils.mc.field_1724.method_5799() || IdealHitUtils.mc.field_1724.method_5869() || IdealHitUtils.mc.field_1724.method_6101() || IdealHitUtils.mc.field_1724.method_31549().field_7479) {
            return false;
        }
        return IdealHitUtils.mc.field_1724.method_24828() && IdealHitUtils.hasBlockAboveHead() && !IdealHitUtils.canMoveUpAfterJump();
    }

    public static boolean isDivineCriticalState() {
        if (IdealHitUtils.mc.field_1724 == null) {
            return false;
        }
        return IdealHitUtils.mc.field_1724.field_6017 > 0.0f && !IdealHitUtils.mc.field_1724.method_24828() && !IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5902) && !IdealHitUtils.mc.field_1724.method_6059(class_1294.field_5919) && !IdealHitUtils.mc.field_1724.method_5799() && !IdealHitUtils.mc.field_1724.method_5869() && !IdealHitUtils.mc.field_1724.method_5771() && !IdealHitUtils.mc.field_1724.method_6101() && !IdealHitUtils.mc.field_1724.method_5765() && !IdealHitUtils.mc.field_1724.method_31549().field_7479 && !IdealHitUtils.mc.field_1724.method_6128();
    }

    private static boolean hasBlockAboveHead() {
        class_238 box = IdealHitUtils.mc.field_1724.method_5829();
        class_238 headBox = new class_238(box.field_1323, box.field_1325, box.field_1321, box.field_1320, box.field_1325 + 2.5, box.field_1324);
        for (class_2338 pos : class_2338.method_10094((int)class_3532.method_15357((double)headBox.field_1323), (int)class_3532.method_15357((double)headBox.field_1322), (int)class_3532.method_15357((double)headBox.field_1321), (int)class_3532.method_15357((double)headBox.field_1320), (int)class_3532.method_15357((double)headBox.field_1325), (int)class_3532.method_15357((double)headBox.field_1324))) {
            class_2680 state = IdealHitUtils.mc.field_1687.method_8320(pos);
            if (state.method_26215() || state.method_26220((class_1922)IdealHitUtils.mc.field_1687, pos).method_1110()) continue;
            return true;
        }
        return false;
    }

    private static boolean canMoveUpAfterJump() {
        double upHeight = (double)IdealHitUtils.mc.field_1724.method_17682() + 0.2;
        class_238 upBox = IdealHitUtils.mc.field_1724.method_5829().method_989(0.0, upHeight, 0.0);
        for (class_2338 pos : class_2338.method_10094((int)class_3532.method_15357((double)upBox.field_1323), (int)class_3532.method_15357((double)upBox.field_1322), (int)class_3532.method_15357((double)upBox.field_1321), (int)class_3532.method_15357((double)upBox.field_1320), (int)class_3532.method_15357((double)upBox.field_1325), (int)class_3532.method_15357((double)upBox.field_1324))) {
            class_2680 state = IdealHitUtils.mc.field_1687.method_8320(pos);
            if (state.method_26215() || state.method_26220((class_1922)IdealHitUtils.mc.field_1687, pos).method_1110()) continue;
            return false;
        }
        return true;
    }

    public static boolean findFall(float fallDistance) {
        class_243 rotationVec = IdealHitUtils.mc.field_1724.method_5720();
        double tempVelocityX = IdealHitUtils.mc.field_1724.method_18798().field_1352;
        double tempVelocityY = IdealHitUtils.mc.field_1724.method_18798().field_1351;
        double tempVelocityZ = IdealHitUtils.mc.field_1724.method_18798().field_1350;
        float n = class_3532.method_15362((float)(IdealHitUtils.mc.field_1724.method_36455() * ((float)Math.PI / 180)));
        n = (float)((double)(n * n) * Math.min(rotationVec.method_1033() / 0.4, 1.0));
        class_243 vec3d = new class_243(tempVelocityX, tempVelocityY, tempVelocityZ).method_1031(0.0, 0.08 * (-1.0 + (double)n * 0.75), 0.0);
        tempVelocityY = vec3d.field_1351 * (double)0.98f;
        return tempVelocityY < (double)fallDistance;
    }

    private IdealHitUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
