/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_238
 */
package code.alphadlc.utils.features.aura.striking;

import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.striking.StrikeManager;
import code.alphadlc.utils.features.aura.warp.Turns;
import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_238;

public class StrikerConstructor
implements QuickImports {
    private final StrikeManager attackHandler = new StrikeManager();

    public void onPacket(PacketEvent event) {
        this.attackHandler.onPacket(event);
    }

    public void onUsingItem(UsingItemEvent event) {
        this.attackHandler.onUsingItem(event);
    }

    public void performAttack(AttackPerpetratorConfigurable configurable) {
        this.attackHandler.handleAttack(configurable);
    }

    public StrikeManager getAttackHandler() {
        return this.attackHandler;
    }

    public static class AttackPerpetratorConfigurable {
        private final class_1309 target;
        private final Turns angle;
        private final float maximumRange;
        private final boolean onlyCritical;
        private final boolean shouldBreakShield;
        private final boolean shouldUnPressShield;
        private final boolean eatAndAttack;
        private final class_238 box;
        private final RadioSetting aimMode;
        private final String sprintResetOverride;
        private final Boolean smartCritsOverride;

        public AttackPerpetratorConfigurable(class_1309 target, Turns angle, float maximumRange, List<String> options, RadioSetting aimMode, class_238 box) {
            this(target, angle, maximumRange, options, aimMode, box, null, null);
        }

        public AttackPerpetratorConfigurable(class_1309 target, Turns angle, float maximumRange, List<String> options, RadioSetting aimMode, class_238 box, String sprintResetOverride) {
            this(target, angle, maximumRange, options, aimMode, box, sprintResetOverride, null);
        }

        public AttackPerpetratorConfigurable(class_1309 target, Turns angle, float maximumRange, List<String> options, RadioSetting aimMode, class_238 box, String sprintResetOverride, Boolean smartCritsOverride) {
            this.target = target;
            this.angle = angle;
            this.maximumRange = maximumRange;
            this.onlyCritical = options.contains("\u0422\u043e\u043b\u044c\u043a\u043e \u043a\u0440\u0438\u0442\u044b");
            this.shouldBreakShield = options.contains("\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442");
            this.shouldUnPressShield = options.contains("\u041e\u0442\u0436\u0438\u043c\u0430\u0442\u044c \u0449\u0438\u0442");
            this.eatAndAttack = options.contains("\u041d\u0435 \u0431\u0438\u0442\u044c \u0435\u0441\u043b\u0438 \u0435\u0448\u044c");
            this.box = box;
            this.aimMode = aimMode;
            this.sprintResetOverride = sprintResetOverride;
            this.smartCritsOverride = smartCritsOverride;
        }

        public class_1309 getTarget() {
            return this.target;
        }

        public Turns getAngle() {
            return this.angle;
        }

        public float getMaximumRange() {
            return this.maximumRange;
        }

        public boolean isOnlyCritical() {
            return this.onlyCritical;
        }

        public boolean isShouldBreakShield() {
            return this.shouldBreakShield;
        }

        public boolean isShouldUnPressShield() {
            return this.shouldUnPressShield;
        }

        public boolean isEatAndAttack() {
            return this.eatAndAttack;
        }

        public class_238 getBox() {
            return this.box;
        }

        public RadioSetting getAimMode() {
            return this.aimMode;
        }

        public String getSprintResetOverride() {
            return this.sprintResetOverride;
        }

        public Boolean getSmartCritsOverride() {
            return this.smartCritsOverride;
        }
    }
}
