/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.AspectRatioEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;

public class AspectRatio
extends Module {
    private final SliderSettings ratioSetting = new SliderSettings("\u0421\u043e\u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u0435", "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u0441\u043e\u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u044f \u0441\u0442\u043e\u0440\u043e\u043d").setValue(1.0f).range(0.1f, 2.0f);

    public AspectRatio() {
        super("AspectRatio", "AspectRatio", ModuleCategory.RENDER);
        this.setup(this.ratioSetting);
    }

    @EventHandler
    public void onAspectRatio(AspectRatioEvent e) {
        e.setRatio(this.ratioSetting.getValue());
        e.cancel();
    }
}
