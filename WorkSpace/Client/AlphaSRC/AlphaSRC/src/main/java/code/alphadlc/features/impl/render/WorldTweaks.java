/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.FogEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.awt.Color;

public class WorldTweaks
extends Module {
    public final MultiSelectSetting modeSetting = new MultiSelectSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u043c\u0438\u0440\u0430", "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u043d\u0430\u0441\u0442\u0440\u043e\u0438\u0442\u044c \u043c\u0438\u0440").value("\u0412\u0440\u0435\u043c\u044f", "\u0422\u0443\u043c\u0430\u043d");
    public final SliderSettings timeSetting = new SliderSettings("\u0412\u0440\u0435\u043c\u044f", "\u0423\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0435\u0442 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0432\u0440\u0435\u043c\u0435\u043d\u0438").setValue(12.0f).range(0, 24).visible(() -> this.modeSetting.isSelected("\u0412\u0440\u0435\u043c\u044f"));
    public final SliderSettings distanceSetting = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0442\u0443\u043c\u0430\u043d\u0430", "\u0423\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0435\u0442 \u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u0442\u0443\u043c\u0430\u043d\u0430").setValue(100.0f).range(20, 200).visible(() -> this.modeSetting.isSelected("\u0422\u0443\u043c\u0430\u043d"));
    public final SliderSettings densitySetting = new SliderSettings("\u041f\u043b\u043e\u0442\u043d\u043e\u0441\u0442\u044c", "\u0423\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0435\u0442 \u043f\u043b\u043e\u0442\u043d\u043e\u0441\u0442\u044c \u0442\u0443\u043c\u0430\u043d\u0430").setValue(98.0f).range(0, 100).visible(() -> this.modeSetting.isSelected("\u0422\u0443\u043c\u0430\u043d"));
    public final ColorSetting fogColor = new ColorSetting("\u0426\u0432\u0435\u0442 \u0442\u0443\u043c\u0430\u043d\u0430", "\u0426\u0432\u0435\u0442 \u0442\u0443\u043c\u0430\u043d\u0430").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB()).visible(() -> this.modeSetting.isSelected("\u0422\u0443\u043c\u0430\u043d"));

    public static WorldTweaks getInstance() {
        return Instance.get(WorldTweaks.class);
    }

    public WorldTweaks() {
        super("WorldTweaks", "WorldTweaks", ModuleCategory.RENDER);
        this.setup(this.modeSetting, this.timeSetting, this.distanceSetting, this.densitySetting, this.fogColor);
    }

    @EventHandler
    public void onFog(FogEvent e) {
        if (this.modeSetting.isSelected("\u0422\u0443\u043c\u0430\u043d")) {
            e.setDistance(this.distanceSetting.getValue());
            e.setDensity(this.densitySetting.getValue());
            e.setColor(this.fogColor.getColor());
            e.cancel();
        }
    }
}
