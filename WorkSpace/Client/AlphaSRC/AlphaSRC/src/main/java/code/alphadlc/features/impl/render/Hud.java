/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import java.util.ArrayList;
import java.util.List;

public class Hud
extends Module {
    public static BooleanSetting blur = new BooleanSetting("\u0411\u043b\u044e\u0440", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u0431\u043b\u044e\u0440\u0430").setValue(true);
    public static BooleanSetting shine = new BooleanSetting("\u0411\u043b\u0438\u043a", "\u0410\u043d\u0438\u043c\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f \u043f\u043e\u043b\u043e\u0441\u043a\u0430 \u0431\u043b\u0438\u043a\u0430 \u043d\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u0445 \u0438\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441\u0430").setValue(true);
    public static SliderSettings shineSize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u0431\u043b\u0438\u043a\u0430", "\u0428\u0438\u0440\u0438\u043d\u0430 \u043f\u043e\u043b\u043e\u0441\u043a\u0438 \u0431\u043b\u0438\u043a\u0430").setValue(1.0f).range(0.5f, 2.0f).step(0.05f).visible(() -> shine.isValue());
    public static SliderSettings shineSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u043b\u0438\u043a\u0430", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u0431\u043b\u0438\u043a\u0430").setValue(1.0f).range(0.5f, 2.0f).step(0.05f).visible(() -> shine.isValue());
    public static SliderSettings shineBrightness = new SliderSettings("\u042f\u0440\u043a\u043e\u0441\u0442\u044c \u0431\u043b\u0438\u043a\u0430", "\u042f\u0440\u043a\u043e\u0441\u0442\u044c \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f \u0431\u043b\u0438\u043a\u0430").setValue(0.45f).range(0.1f, 1.0f).step(0.05f).visible(() -> shine.isValue());
    public static MultiSelectSetting shineElements = new MultiSelectSetting("\u0411\u043b\u0438\u043a \u043d\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u0445", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442\u044b, \u043d\u0430 \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u0431\u0443\u0434\u0435\u0442 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c\u0441\u044f \u0431\u043b\u0438\u043a").value("Watermark", "Target Hud", "Potions", "Cooldowns", "Notifications", "Keybinds", "StaffList", "Info", "ArmorBar", "TotemBar", "CustomHotbar", "Inventory").selected("Watermark", "Target Hud", "Potions", "Cooldowns", "Notifications", "Keybinds", "StaffList", "Info", "ArmorBar", "TotemBar", "CustomHotbar", "Inventory").visible(() -> shine.isValue());
    public static SliderSettings setAlpha = new SliderSettings("Set Alpha", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0438\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441\u0430").setValue(1.0f).range(0.1f, 1.0f).step(0.05f);
    public static MultiSelectSetting interfaceSettings = new MultiSelectSetting("\u042d\u043b\u0435\u043c\u0435\u043d\u0442\u044b", "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0438\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441\u0430").value("Watermark", "Target Hud", "Potions", "Cooldowns", "Notifications", "Keybinds", "StaffList", "Info", "ArmorBar", "TotemBar", "CustomHotbar", "Inventory").selected("Watermark", "Target Hud", "Potions", "Notifications", "Info");
    public MultiSelectSetting notificationSettings = new MultiSelectSetting("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u044f\u0442\u044c \u043e\u0431", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435, \u043a\u043e\u0433\u0434\u0430 \u0431\u0443\u0434\u0443\u0442 \u043f\u043e\u044f\u0432\u043b\u044f\u0442\u044c\u0441\u044f \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f").value("\u041f\u0435\u0440\u0435\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u043c\u043e\u0434\u0443\u043b\u0435\u0439", "\u0412\u0445\u043e\u0434\u0435 \u0430\u0434\u043c\u0438\u043d\u0430", "\u0412\u044b\u0445\u043e\u0434\u0435 \u0430\u0434\u043c\u0438\u043d\u0430", "\u041f\u043e\u0434\u043d\u044f\u0442\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u041b\u043e\u043c\u0430\u043d\u0438\u0438 \u0449\u0438\u0442\u0430", "\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043d\u043e\u043c \u0437\u0435\u043b\u044c\u0435", "\u0417\u0430\u043a\u0430\u043d\u0447\u0438\u0432\u0430\u044e\u0449\u0435\u043c\u0441\u044f \u0437\u0435\u043b\u044c\u0435", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430", "\u0421\u043a\u043e\u0440\u043e\u0439 \u043f\u043e\u043b\u043e\u043c\u043a\u0435 \u0431\u0440\u043e\u043d\u0438", "\u0421\u043a\u043e\u0440\u043e\u043c \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u0438 \u0441\u0443\u043d\u0434\u0443\u043a\u0430").selected("\u041f\u0435\u0440\u0435\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u043c\u043e\u0434\u0443\u043b\u0435\u0439", "\u041f\u043e\u0434\u043d\u044f\u0442\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u041b\u043e\u043c\u0430\u043d\u0438\u0438 \u0449\u0438\u0442\u0430", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0438 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430", "\u0421\u043a\u043e\u0440\u043e\u0439 \u043f\u043e\u043b\u043e\u043c\u043a\u0435 \u0431\u0440\u043e\u043d\u0438", "\u0421\u043a\u043e\u0440\u043e\u043c \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u0438 \u0441\u0443\u043d\u0434\u0443\u043a\u0430").visible(() -> interfaceSettings.isSelected("Notifications"));

    public static Hud getInstance() {
        return Instance.get(Hud.class);
    }

    public Hud() {
        super("Hud", ModuleCategory.RENDER);
        this.setup(interfaceSettings, this.notificationSettings, blur, shine, shineSize, shineSpeed, shineBrightness, shineElements, setAlpha);
        this.setState(true);
    }

    @Override
    public void activate() {
        if (!this.isState()) {
            this.setState(true);
        }
    }

    @Override
    public void deactivate() {
        this.setState(true);
    }

    public boolean isHudElementEnabled(String elementName) {
        return interfaceSettings.getList() != null && interfaceSettings.getList().contains(elementName) && interfaceSettings.isSelected(elementName);
    }

    public void setHudElementEnabled(String elementName, boolean enabled) {
        List<String> values = interfaceSettings.getList();
        if (values == null || !values.contains(elementName)) {
            return;
        }
        ArrayList<String> selectedValues = new ArrayList<String>(interfaceSettings.getSelected());
        if (enabled) {
            if (!selectedValues.contains(elementName)) {
                selectedValues.add(elementName);
            }
        } else {
            selectedValues.remove(elementName);
        }
        interfaceSettings.setSelected(selectedValues);
    }
}
