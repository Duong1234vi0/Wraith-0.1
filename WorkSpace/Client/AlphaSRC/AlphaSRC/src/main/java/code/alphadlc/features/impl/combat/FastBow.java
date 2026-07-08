/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3532;

public class FastBow
extends Module {
    private final SliderSettings distanceSetting = new SliderSettings("\u0414\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c", "\u0421\u0438\u043b\u0430 \u043d\u0430\u0442\u044f\u0436\u0435\u043d\u0438\u044f \u043b\u0443\u043a\u0430 \u0438 \u0434\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u043f\u043e\u043b\u0451\u0442\u0430 \u0441\u0442\u0440\u0435\u043b").setValue(50.0f).range(10, 100).step(5.0f);

    public FastBow() {
        super("FastBow", "FastBow", ModuleCategory.COMBAT);
        this.setup(this.distanceSetting);
    }

    @Override
    public void deactivate() {
        if (FastBow.mc.field_1724 != null && FastBow.mc.field_1724.method_6115() && FastBow.isBowStack(FastBow.mc.field_1724.method_6030())) {
            FastBow.mc.field_1761.method_2897((class_1657)FastBow.mc.field_1724);
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (FastBow.mc.field_1724 == null || FastBow.mc.field_1687 == null || FastBow.mc.field_1761 == null || FastBow.mc.field_1755 != null) {
            return;
        }
        if (!FastBow.mc.field_1690.field_1904.method_1434()) {
            return;
        }
        class_1268 bowHand = this.getBowHand();
        if (bowHand == null || !this.hasAmmo(bowHand)) {
            return;
        }
        FastBow.mc.field_1752 = 0;
        if (FastBow.mc.field_1724.method_6115() && FastBow.isBowStack(FastBow.mc.field_1724.method_6030())) {
            if (FastBow.mc.field_1724.method_6048() >= this.getRequiredChargeTicks()) {
                FastBow.mc.field_1761.method_2897((class_1657)FastBow.mc.field_1724);
            }
            return;
        }
        if (!FastBow.mc.field_1724.method_6115()) {
            FastBow.mc.field_1761.method_2919((class_1657)FastBow.mc.field_1724, bowHand);
        }
    }

    private class_1268 getBowHand() {
        if (FastBow.mc.field_1724.method_6047().method_31574(class_1802.field_8102)) {
            return class_1268.field_5808;
        }
        if (FastBow.mc.field_1724.method_6079().method_31574(class_1802.field_8102)) {
            return class_1268.field_5810;
        }
        return null;
    }

    private boolean hasAmmo(class_1268 bowHand) {
        if (FastBow.mc.field_1724.method_31549().field_7477) {
            return true;
        }
        class_1799 bowStack = FastBow.mc.field_1724.method_5998(bowHand);
        return !FastBow.mc.field_1724.method_18808(bowStack).method_7960();
    }

    private int getRequiredChargeTicks() {
        float power = class_3532.method_15363((float)(this.distanceSetting.getValue() / 100.0f), (float)0.1f, (float)1.0f);
        return Math.max(3, Math.round(power * 20.0f));
    }

    private static boolean isBowStack(class_1799 stack) {
        return stack != null && stack.method_31574(class_1802.field_8102);
    }
}
