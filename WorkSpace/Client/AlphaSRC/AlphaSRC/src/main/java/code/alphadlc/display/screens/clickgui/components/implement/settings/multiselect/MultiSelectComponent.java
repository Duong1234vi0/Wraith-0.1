/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings.multiselect;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.SettingDropdownRenderer;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.math.calc.Calculate;
import java.util.List;
import net.minecraft.class_332;

public class MultiSelectComponent
extends AbstractSettingComponent {
    private final MultiSelectSetting setting;
    private final SettingDropdownRenderer dropdown = new SettingDropdownRenderer();

    public MultiSelectComponent(MultiSelectSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 41.0f;
    }

    @Override
    public void prepareLayout(float width) {
        this.width = width;
        this.height = this.dropdown.computeHeight(this.setting.getList().size());
    }

    @Override
    public void collapseDropdown() {
        this.dropdown.close();
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        List<String> values = this.setting.getList();
        String label = this.setting.getName() + " " + this.setting.getSelected().size() + "/" + values.size();
        String panelText = this.buildPanelText();
        this.dropdown.render(context, this.x, this.y, this.width, label, panelText, values, this.setting.getSelected()::contains, mouseX, mouseY);
        this.height = this.dropdown.computeHeight(values.size());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.dropdown.handleClick(mouseX, mouseY, button, this.x, this.y, this.width, this.setting.getList(), value -> {
            if (this.setting.getSelected().contains(value)) {
                this.setting.getSelected().remove(value);
            } else {
                this.setting.getSelected().add((String)value);
            }
            return true;
        });
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }

    private String buildPanelText() {
        List<String> selected = this.setting.getSelected();
        if (selected.isEmpty()) {
            return "\u041d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d\u043e";
        }
        String joined = String.join((CharSequence)", ", selected);
        float maxWidth = this.width - 50.0f;
        FontRenderer font = Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM);
        if (font.getStringWidth(joined) <= maxWidth) {
            return joined;
        }
        String ellipsis = "...";
        while (joined.length() > 1 && font.getStringWidth(joined + ellipsis) > maxWidth) {
            joined = joined.substring(0, joined.length() - 1).trim();
        }
        return joined.isEmpty() ? selected.getFirst() : joined + ellipsis;
    }
}
