/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.input.MoveUtil;

public class DragonFly
extends Module {
    private SliderSettings xzSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e X/Z", "\u0413\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e\u043b\u0451\u0442\u0430").range(0.0f, 2.0f).step(0.1f).setValue(1.0f);
    private SliderSettings ySpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", "\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e\u043b\u0451\u0442\u0430").range(0.0f, 2.0f).step(0.1f).setValue(1.0f);

    public DragonFly() {
        super("DragonFly", "DragonFly", ModuleCategory.MOVEMENT);
        this.setup(this.xzSpeed, this.ySpeed);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (DragonFly.mc.field_1724 == null || DragonFly.mc.field_1687 == null || !DragonFly.mc.field_1724.method_31549().field_7479) {
            return;
        }
        MoveUtil.setMotion(this.xzSpeed.getValue());
        if (DragonFly.mc.field_1690.field_1903.method_1434()) {
            DragonFly.mc.field_1724.method_18800(DragonFly.mc.field_1724.method_18798().field_1352, (double)this.ySpeed.getValue(), DragonFly.mc.field_1724.method_18798().field_1350);
        }
        if (DragonFly.mc.field_1690.field_1832.method_1434()) {
            DragonFly.mc.field_1724.method_18800(DragonFly.mc.field_1724.method_18798().field_1352, (double)(-this.ySpeed.getValue()), DragonFly.mc.field_1724.method_18798().field_1350);
        }
    }
}
