package ua.babka.module.impl.player;

import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;

@ModuleInformation(moduleName="No Push", moduleDesc="Отключает толкание от игроков", moduleCategory=ModuleCategory.PLAYER)
public class NoPush
extends Module {
    public final ModeListSetting objects = new ModeListSetting("Обьекты", new BooleanSetting("Игроки", true), new BooleanSetting("Блоки", true));
}

