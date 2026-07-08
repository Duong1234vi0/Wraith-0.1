/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.EntityColorEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;

public class SeeInvisible
extends Module {
    private final SliderSettings alphaSetting = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u0430").setValue(0.5f).range(0.1f, 1.0f);

    public SeeInvisible() {
        super("SeeInvisible", "SeeInvisible", ModuleCategory.RENDER);
        this.setup(this.alphaSetting);
    }

    @EventHandler
    public void onEntityColor(EntityColorEvent e) {
        e.setColor(ColorAssist.multAlpha(e.getColor(), this.alphaSetting.getValue()));
        e.cancel();
    }
}
