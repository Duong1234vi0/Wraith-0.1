/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_239
 *  net.minecraft.class_3966
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.math.time.TimerUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_3966;

public class TapeMouse
extends Module
implements QuickImports {
    private final SelectSetting buttonSetting = new SelectSetting("\u041a\u043d\u043e\u043f\u043a\u0430", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043a\u043d\u043e\u043f\u043a\u0443 \u043c\u044b\u0448\u0438").value("\u041b\u0435\u0432\u0430\u044f \u043a\u043d\u043e\u043f\u043a\u0430", "\u041f\u0440\u0430\u0432\u0430\u044f \u043a\u043d\u043e\u043f\u043a\u0430").selected("\u041b\u0435\u0432\u0430\u044f \u043a\u043d\u043e\u043f\u043a\u0430");
    private final SliderSettings delaySetting = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 (\u043c\u0441)", "\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u043c\u0435\u0436\u0434\u0443 \u043a\u043b\u0438\u043a\u0430\u043c\u0438").range(100, 30000).step(50.0f).setValue(450.0f);
    private final TimerUtil timer = new TimerUtil();

    public TapeMouse() {
        super("TapeMouse", "Tape Mouse", ModuleCategory.MISC);
        this.setup(this.buttonSetting, this.delaySetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (!this.state) {
            return;
        }
        if (TapeMouse.mc.field_1724 == null || TapeMouse.mc.field_1687 == null || TapeMouse.mc.field_1755 != null || TapeMouse.mc.field_1761 == null) {
            return;
        }
        long delayMs = (long)this.delaySetting.getValue();
        if (!this.timer.hasTimeElapsed(delayMs)) {
            return;
        }
        if (this.buttonSetting.isSelected("\u041b\u0435\u0432\u0430\u044f \u043a\u043d\u043e\u043f\u043a\u0430")) {
            class_239 class_2392 = TapeMouse.mc.field_1765;
            if (class_2392 instanceof class_3966) {
                class_3966 entityHit = (class_3966)class_2392;
                TapeMouse.mc.field_1761.method_2918((class_1657)TapeMouse.mc.field_1724, entityHit.method_17782());
                TapeMouse.mc.field_1724.method_6104(class_1268.field_5808);
            }
        } else {
            TapeMouse.mc.field_1761.method_2919((class_1657)TapeMouse.mc.field_1724, class_1268.field_5808);
            TapeMouse.mc.field_1724.method_6104(class_1268.field_5808);
        }
        this.timer.resetCounter();
    }
}
