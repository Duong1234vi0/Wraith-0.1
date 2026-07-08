/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_239
 *  net.minecraft.class_243
 *  net.minecraft.class_3966
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.striking.StrikerConstructor;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.features.combat.LegitSprintReset;
import code.alphadlc.utils.features.combat.TargetFinder;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3966;

public class TriggerBot
extends Module
implements QuickImports {
    private class_1309 target;
    private class_1309 lastTarget;
    private MultiSelectSetting targetType = new MultiSelectSetting("\u0422\u0438\u043f \u0442\u0430\u0440\u0433\u0435\u0442\u0430", "\u0424\u0438\u043b\u044c\u0442\u0440 \u0446\u0435\u043b\u0435\u0439 \u043f\u043e \u0442\u0438\u043f\u0443").value("\u0418\u0433\u0440\u043e\u043a\u0438", "\u041c\u043e\u0431\u044b", "\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0435", "\u0414\u0440\u0443\u0437\u044c\u044f", "\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u043d\u0438").selected("\u0418\u0433\u0440\u043e\u043a\u0438", "\u041c\u043e\u0431\u044b", "\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0435");
    private SliderSettings attackRange = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0443\u0434\u0430\u0440\u0430", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0430\u0442\u0430\u043a\u0438").setValue(3.0f).range(1.0f, 6.0f);
    private MultiSelectSetting attackSetting = new MultiSelectSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", "\u041e\u043f\u0446\u0438\u0438 \u0430\u0442\u0430\u043a\u0438").value("\u0422\u043e\u043b\u044c\u043a\u043e \u043a\u0440\u0438\u0442\u044b", "\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442", "\u041e\u0442\u0436\u0438\u043c\u0430\u0442\u044c \u0449\u0438\u0442", "\u041d\u0435 \u0431\u0438\u0442\u044c \u0435\u0441\u043b\u0438 \u0435\u0448\u044c").selected("\u0422\u043e\u043b\u044c\u043a\u043e \u043a\u0440\u0438\u0442\u044b", "\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442");
    private RadioSetting sprintReset = new RadioSetting("\u0421\u0431\u0440\u043e\u0441 \u0441\u043f\u0440\u0438\u043d\u0442\u0430", "\u0421\u0431\u0440\u043e\u0441 \u0441\u043f\u0440\u0438\u043d\u0442\u0430 \u043f\u0435\u0440\u0435\u0434 \u0443\u0434\u0430\u0440\u043e\u043c", new String[]{"\u041b\u0435\u0433\u0438\u0442\u043d\u043e", "\u041f\u0430\u043a\u0435\u0442\u043d\u043e", "\u041d\u0435 \u0441\u0431\u0440\u0430\u0441\u044b\u0432\u0430\u0442\u044c"}, "\u041b\u0435\u0433\u0438\u0442\u043d\u043e");
    public final BooleanSetting smartCrit = new BooleanSetting("\u0423\u043c\u043d\u044b\u0435 \u043a\u0440\u0438\u0442\u044b", "\u0423\u043c\u043d\u044b\u0435 \u043a\u0440\u0438\u0442\u044b").setValue(true);
    private BooleanSetting targetEsp = new BooleanSetting("Target ESP", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c ESP \u043d\u0430 \u0446\u0435\u043b\u0438").setValue(false);

    public static TriggerBot getInstance() {
        return Instance.get(TriggerBot.class);
    }

    public TriggerBot() {
        super("TriggerBot", "TriggerBot", ModuleCategory.COMBAT);
        this.setup(this.targetType, this.attackRange, this.attackSetting, this.sprintReset, this.smartCrit, this.targetEsp);
    }

    @Override
    public void deactivate() {
        LegitSprintReset.reset();
        this.target = null;
        this.lastTarget = null;
        super.deactivate();
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (this.target != null && "\u041b\u0435\u0433\u0438\u0442\u043d\u043e".equals(this.sprintReset.get())) {
            LegitSprintReset.applyInputReset(event);
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (TriggerBot.mc.field_1724 == null || TriggerBot.mc.field_1687 == null) {
            return;
        }
        switch (e.getType()) {
            case 0: {
                this.target = this.updateTarget();
                break;
            }
            case 2: {
                if (this.target == null) break;
                this.tryAttack();
            }
        }
    }

    private class_1309 updateTarget() {
        if (TriggerBot.mc.field_1755 != null || TriggerBot.mc.field_1761 == null) {
            return null;
        }
        this.target = null;
        class_239 class_2392 = TriggerBot.mc.field_1765;
        if (!(class_2392 instanceof class_3966)) {
            return null;
        }
        class_3966 entityHit = (class_3966)class_2392;
        class_1297 class_12972 = entityHit.method_17782();
        if (!(class_12972 instanceof class_1309)) {
            return null;
        }
        class_1309 living = (class_1309)class_12972;
        TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(this.targetType.getSelected());
        if (!filter.isValid(living)) {
            return null;
        }
        float range = this.attackRange.getValue() + AttackAura.RANGE_MARGIN;
        if (TriggerBot.mc.field_1724.method_5739((class_1297)living) > range) {
            return null;
        }
        this.lastTarget = living;
        return living;
    }

    private void tryAttack() {
        if (this.target == null) {
            return;
        }
        TurnsConnection.INSTANCE.setRotation(null);
        class_243 vec = MathAngle.getClosestVec((class_1297)this.target).method_1020(TriggerBot.mc.field_1724.method_33571());
        float range = this.attackRange.getValue() + AttackAura.RANGE_MARGIN;
        StrikerConstructor.AttackPerpetratorConfigurable config = new StrikerConstructor.AttackPerpetratorConfigurable(this.target, MathAngle.fromVec3d(vec), range, this.attackSetting.getSelected(), AttackAura.aimMode, this.target.method_5829(), this.sprintReset.get(), this.smartCrit.isValue());
        AlphaDLC.getInstance().getAttackPerpetrator().performAttack(config);
    }

    public class_1309 getTarget() {
        return this.target;
    }

    public class_1309 getLastTarget() {
        return this.lastTarget;
    }

    public MultiSelectSetting getTargetType() {
        return this.targetType;
    }

    public SliderSettings getAttackRange() {
        return this.attackRange;
    }

    public MultiSelectSetting getAttackSetting() {
        return this.attackSetting;
    }

    public RadioSetting getSprintReset() {
        return this.sprintReset;
    }

    public BooleanSetting getSmartCrit() {
        return this.smartCrit;
    }

    public BooleanSetting getTargetEsp() {
        return this.targetEsp;
    }
}
