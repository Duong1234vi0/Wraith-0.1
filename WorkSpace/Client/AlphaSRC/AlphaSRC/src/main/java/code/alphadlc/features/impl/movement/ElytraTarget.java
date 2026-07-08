/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;

public class ElytraTarget
extends Module {
    private static final double MIN_SPEED_SQ = 0.01;
    private static final double MIN_OFFSET_SQ = 0.01;
    private static final double HEIGHT_FACTOR = 0.5;
    private final SelectSetting priority = new SelectSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0446\u0435\u043b\u0438", "\u041a\u0430\u043a \u0432\u044b\u0431\u0438\u0440\u0430\u0442\u044c \u0446\u0435\u043b\u044c \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435").value("\u041e\u0431\u044b\u0447\u043d\u043e", "\u0421\u043f\u0435\u0440\u0432\u0430 \u044d\u043b\u0438\u0442\u0440\u044b");
    private final BooleanSetting overtake = new BooleanSetting("\u041f\u0435\u0440\u0435\u0433\u043e\u043d", "\u041f\u0435\u0440\u0435\u0433\u043e\u043d \u0446\u0435\u043b\u0438 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435").setValue(true);
    private final BooleanSetting bravoFfa = new BooleanSetting("BravoFFA", "\u0420\u0435\u0436\u0438\u043c \u0434\u043b\u044f Bravo FFA").setValue(false).visible(this.overtake::isValue);
    private final SliderSettings predict = new SliderSettings("\u0421\u0438\u043b\u0430 \u043f\u0440\u0435\u0434\u0438\u043a\u0442\u0430", "\u0421\u0438\u043b\u0430 \u043f\u0440\u0435\u0434\u0438\u043a\u0442\u0430 \u043f\u0435\u0440\u0435\u0433\u043e\u043d\u0430").setValue(3.0f).range(1.0f, 5.0f).step(0.1f);

    public ElytraTarget() {
        super("ElytraTarget", "ElytraTarget", ModuleCategory.MOVEMENT);
        this.setup(this.priority, this.overtake, this.bravoFfa, this.predict);
    }

    public static ElytraTarget getInstance() {
        return Instance.get(ElytraTarget.class);
    }

    public boolean isOvertakeActive() {
        return this.isState() && this.overtake.isValue();
    }

    public boolean isBravoFfa() {
        return this.isOvertakeActive() && this.bravoFfa.isValue();
    }

    public float getPredictStrength() {
        return this.predict.getValue();
    }

    public boolean preferElytraTargets() {
        return this.priority.isSelected("\u0421\u043f\u0435\u0440\u0432\u0430 \u044d\u043b\u0438\u0442\u0440\u044b");
    }

    public boolean shouldTargetEntity(class_1297 entity) {
        if (!this.isOvertakeActive() || !(entity instanceof class_1309)) {
            return false;
        }
        class_1309 living = (class_1309)entity;
        return living.method_6128() && living.field_6017 > 0.0f;
    }

    public boolean isBehindTarget(class_1309 target) {
        if (ElytraTarget.mc.field_1724 == null || target == null) {
            return false;
        }
        class_243 velocity = ElytraTarget.mc.field_1724.method_18798();
        class_243 horizontal = new class_243(velocity.field_1352, 0.0, velocity.field_1350);
        if (horizontal.method_1027() < 0.01) {
            return false;
        }
        horizontal = horizontal.method_1029();
        class_243 eye = ElytraTarget.mc.field_1724.method_33571();
        class_243 aim = target.method_19538().method_1031(0.0, (double)target.method_17682() * 0.5, 0.0).method_1020(eye);
        class_243 aimHorizontal = new class_243(aim.field_1352, 0.0, aim.field_1350);
        if (aimHorizontal.method_1027() < 0.01) {
            return false;
        }
        return horizontal.method_1026(aimHorizontal = aimHorizontal.method_1029()) < 0.35;
    }
}
