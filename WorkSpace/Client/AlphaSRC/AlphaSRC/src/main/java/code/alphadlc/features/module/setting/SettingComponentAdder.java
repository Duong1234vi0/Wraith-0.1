/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.module.setting;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.BindComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.CheckboxComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.ColorComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.GroupComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.RadioComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.SButtonComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.SectionComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.SliderComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.SwordModelPickerComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.TextComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.multiselect.MultiSelectComponent;
import code.alphadlc.features.impl.render.ItemReplacer;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ButtonSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.GroupSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SectionSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.features.module.setting.implement.TextSetting;
import java.util.List;

public class SettingComponentAdder {
    public void addSettingComponent(List<Setting> settings, List<AbstractSettingComponent> components, Module module) {
        settings.forEach(setting -> {
            AbstractSettingComponent component = null;
            if (setting instanceof BooleanSetting) {
                BooleanSetting booleanSetting = (BooleanSetting)setting;
                component = new CheckboxComponent(booleanSetting);
            }
            if (setting instanceof BindSetting) {
                BindSetting bindSetting = (BindSetting)setting;
                component = new BindComponent(bindSetting);
            }
            if (setting instanceof ColorSetting) {
                ColorSetting colorSetting = (ColorSetting)setting;
                component = new ColorComponent(colorSetting);
            }
            if (setting instanceof TextSetting) {
                TextSetting textSetting = (TextSetting)setting;
                component = new TextComponent(textSetting);
            }
            if (setting instanceof SliderSettings) {
                SliderSettings valueSetting = (SliderSettings)setting;
                component = new SliderComponent(valueSetting);
            }
            if (setting instanceof GroupSetting) {
                GroupSetting groupSetting = (GroupSetting)setting;
                component = new GroupComponent(groupSetting);
            }
            if (setting instanceof ButtonSetting) {
                ButtonSetting buttonSetting = (ButtonSetting)setting;
                component = new SButtonComponent(buttonSetting);
            }
            if (setting instanceof SelectSetting) {
                SelectSetting selectSetting = (SelectSetting)setting;
                component = module instanceof ItemReplacer && selectSetting.getName().startsWith("\u041c\u043e\u0434\u0435\u043b\u044c") ? new SwordModelPickerComponent(selectSetting) : new RadioComponent(selectSetting);
            }
            if (setting instanceof MultiSelectSetting) {
                MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
                component = new MultiSelectComponent(multiSelectSetting);
            }
            if (setting instanceof RadioSetting) {
                RadioSetting radioSetting = (RadioSetting)setting;
                component = new RadioComponent(radioSetting);
            }
            if (setting instanceof SectionSetting) {
                SectionSetting sectionSetting = (SectionSetting)setting;
                component = new SectionComponent(sectionSetting);
            }
            if (component != null) {
                component.setModule(module);
                components.add(component);
            }
        });
    }
}
