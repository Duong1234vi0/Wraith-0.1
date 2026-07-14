package ua.babka.module.impl.render;

import net.minecraft.client.gui.screen.Screen;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.render.ClickGuiScreen;
import ua.babka.module.setting.ModeSetting;
import ua.babka.ui.component.CsgoClickGui;
import ua.babka.ui.component.Panel;

@ModuleInformation(moduleName="Click Gui", moduleDesc="Графическое меню настроек", moduleCategory=ModuleCategory.RENDER, moduleKeybind=344)
public class ClickGui
extends Module {
    public final ModeSetting guiType = new ModeSetting("Тип GUI", "Panel", "Panel", "CS:GO");
    private ClickGuiScreen panelFrame;
    private CsgoClickGui csgoFrame;

    @Override
    public void onEnable() {
        if (this.guiType.is("CS:GO")) {
            if (this.csgoFrame == null) {
                this.csgoFrame = new CsgoClickGui();
            }
            this.mc.setScreen((Screen)this.csgoFrame);
            this.csgoFrame.getOpenAnimation().setValue(0.0f);
            this.csgoFrame.getOpenAnimation().setStartValue(0.0f);
        } else {
            if (this.panelFrame == null) {
                this.panelFrame = new ClickGuiScreen();
            }
            this.panelFrame.getSearchField().text = "";
            this.panelFrame.getSearchField().clearFocus();
            this.mc.setScreen((Screen)this.panelFrame);
            for (Panel panel : this.panelFrame.getPanels()) {
                panel.getAnimationAlpha().setValue(0.0f);
                panel.getAnimationAlpha().setStartValue(0.0f);
                panel.getAnimationAlpha().reset();
            }
        }
        this.toggle();
    }
}

