/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.combat.IdealHitUtils;
import net.minecraft.class_1294;

public class AutoSprint
extends Module {
    public static int tickStop;
    private MultiSelectSetting settings = new MultiSelectSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442", "\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u044b \u0438\u0433\u0440\u043e\u043a\u0430").value("\u0417\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u0435", "\u0421\u043b\u0435\u043f\u043e\u0442\u0430");
    private BooleanSetting syncWithAura = new BooleanSetting("\u0421\u0438\u043d\u0445 \u0441 Aura", "\u0421\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0438\u0440\u0443\u0435\u0442 \u0430\u0432\u0442\u043e\u0441\u043f\u0440\u0438\u043d\u0442 \u0441\u043e \u0441\u0431\u0440\u043e\u0441\u043e\u043c \u0441\u043f\u0440\u0438\u043d\u0442\u0430 \u0432 Aura").setValue(true);

    public static AutoSprint getInstance() {
        return Instance.get(AutoSprint.class);
    }

    public AutoSprint() {
        super("AutoSprint", "AutoSprint", ModuleCategory.MOVEMENT);
        this.setup(this.settings, this.syncWithAura);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AutoSprint.mc.field_1724 == null) {
            return;
        }
        boolean horizontal = AutoSprint.mc.field_1724.field_5976 && !AutoSprint.mc.field_1724.field_34927;
        boolean sneaking = AutoSprint.mc.field_1724.method_5715() && !AutoSprint.mc.field_1724.method_5681();
        boolean ignoreBlindness = this.settings.isSelected("\u0421\u043b\u0435\u043f\u043e\u0442\u0430") && AutoSprint.mc.field_1724.method_6059(class_1294.field_5919);
        boolean ignoreSlowness = this.settings.isSelected("\u0417\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u0435") && AutoSprint.mc.field_1724.method_6059(class_1294.field_5909);
        boolean shouldStopFromTimer = tickStop > 0 && !ignoreBlindness && !ignoreSlowness;
        boolean syncedWithAura = this.shouldUseAuraSync();
        if (shouldStopFromTimer || sneaking) {
            AutoSprint.mc.field_1690.field_1867.method_23481(false);
            if (AutoSprint.mc.field_1724.method_5624()) {
                AutoSprint.mc.field_1724.method_5728(false);
            }
        } else if (!horizontal && AutoSprint.mc.field_1724.field_6250 > 0.0f) {
            if (syncedWithAura) {
                if (this.canDivineSprint()) {
                    AutoSprint.mc.field_1690.field_1867.method_23481(true);
                } else {
                    AutoSprint.mc.field_1690.field_1867.method_23481(false);
                    if (AutoSprint.mc.field_1724.method_5624()) {
                        AutoSprint.mc.field_1724.method_5728(false);
                    }
                }
            } else if (!AutoSprint.mc.field_1724.method_5624()) {
                AutoSprint.mc.field_1724.method_5728(true);
            }
        } else if (syncedWithAura) {
            AutoSprint.mc.field_1690.field_1867.method_23481(false);
        }
        if (tickStop > 0) {
            --tickStop;
        }
    }

    private boolean shouldUseAuraSync() {
        if (!this.syncWithAura.isValue()) {
            return false;
        }
        AttackAura aura = AttackAura.getInstance();
        return aura != null && aura.isState() && aura.getTarget() != null && aura.isSprintResetEnabled();
    }

    private boolean canDivineSprint() {
        boolean inLiquid;
        if (AutoSprint.mc.field_1724 == null || AutoSprint.mc.field_1687 == null) {
            return false;
        }
        boolean inCobweb = IdealHitUtils.isInCobweb();
        boolean bl = inLiquid = AutoSprint.mc.field_1724.method_5799() || AutoSprint.mc.field_1724.method_5869();
        if (inCobweb || AutoSprint.mc.field_1724.method_31549().field_7479 || inLiquid || AutoSprint.mc.field_1724.method_5765()) {
            return true;
        }
        return AutoSprint.mc.field_1724.field_6017 <= 0.0f && AutoSprint.mc.field_1724.method_24828();
    }

    public boolean shouldKeepSprintInWater() {
        return false;
    }
}
