/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1294
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1294;

public class LevitationControl
extends Module {
    SliderSettings up = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0432\u0435\u0440\u0445", "").setValue(0.5f).range(0, 2);
    SliderSettings down = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u043d\u0438\u0437", "").setValue(0.5f).range(0, 2);
    BooleanSetting stuck = new BooleanSetting("\u0417\u0430\u0432\u0438\u0441\u0430\u0442\u044c", "\u0421\u0442\u043e\u043f\u0438\u0442 \u0432\u0430\u0441 \u043d\u0430 \u043c\u0435\u0441\u0442\u0435");

    public LevitationControl() {
        super("LevitationControl", ModuleCategory.PLAYER);
        this.setup(this.up, this.down, this.stuck);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        double test1214;
        if (LevitationControl.mc.field_1724 == null || LevitationControl.mc.field_1687 == null) {
            return;
        }
        if (!LevitationControl.mc.field_1724.method_6059(class_1294.field_5902)) {
            return;
        }
        switch (0) {
            default: 
        }
        double d = LevitationControl.mc.field_1690.field_1903.method_1434() ? (double)this.up.getValue() : (LevitationControl.mc.field_1690.field_1832.method_1434() ? (double)(-this.down.getValue()) : (test1214 = this.stuck.isValue() ? 0.0 : LevitationControl.mc.field_1724.method_18798().field_1351));
        if (Math.abs(LevitationControl.mc.field_1724.method_18798().field_1351 - test1214) > 0.001) {
            LevitationControl.mc.field_1724.method_18800(LevitationControl.mc.field_1724.method_18798().field_1352, test1214, LevitationControl.mc.field_1724.method_18798().field_1350);
        }
    }
}
