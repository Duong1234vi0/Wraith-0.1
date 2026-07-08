/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1293;
import net.minecraft.class_1294;

public class Fullbright
extends Module {
    public final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u0440\u0430\u0431\u043e\u0442\u044b").value("\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435", "\u0413\u0430\u043c\u043c\u0430").selected("\u0413\u0430\u043c\u043c\u0430");
    private double previousGamma = 1.0;

    public static Fullbright getInstance() {
        return Instance.get(Fullbright.class);
    }

    public Fullbright() {
        super("Fullbright", "Fullbright", ModuleCategory.RENDER);
        this.setup(this.modeSetting);
    }

    @Override
    public void activate() {
        super.activate();
        if (mc != null && Fullbright.mc.field_1690 != null) {
            this.previousGamma = (Double)Fullbright.mc.field_1690.method_42473().method_41753();
        }
    }

    @Override
    public void deactivate() {
        if (mc != null && Fullbright.mc.field_1690 != null) {
            Fullbright.mc.field_1690.method_42473().method_41748((Object)this.previousGamma);
        }
        if (Fullbright.mc.field_1724 != null && this.modeSetting.isSelected("\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435")) {
            Fullbright.mc.field_1724.method_6016(class_1294.field_5925);
        }
        super.deactivate();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (Fullbright.mc.field_1724 == null || Fullbright.mc.field_1690 == null) {
            return;
        }
        if (this.modeSetting.isSelected("\u0413\u0430\u043c\u043c\u0430")) {
            Fullbright.mc.field_1690.method_42473().method_41748((Object)10.0);
        } else if (this.modeSetting.isSelected("\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435") && !Fullbright.mc.field_1724.method_6059(class_1294.field_5925)) {
            Fullbright.mc.field_1724.method_6092(new class_1293(class_1294.field_5925, Integer.MAX_VALUE, 0, false, false));
        }
    }
}
