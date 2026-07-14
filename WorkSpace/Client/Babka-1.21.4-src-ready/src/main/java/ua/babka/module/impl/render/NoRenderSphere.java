package ua.babka.module.impl.render;

import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;

@ModuleInformation(moduleName="NoRenderSphere", moduleDesc="Скрывает PLAYER_HEAD в мире", moduleCategory=ModuleCategory.RENDER)
public final class NoRenderSphere
extends Module {
    public final BooleanSetting hideHeads = new BooleanSetting("Не рендерить головы", true);
}

