package ua.babka.module.impl.render;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import ua.babka.Babka;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;

@ModuleInformation(moduleName="Client Sounds", moduleDesc="Создает звук при включении/выключении функции", moduleCategory=ModuleCategory.RENDER)
public class ClientSounds
extends Module {
    private static ClientSounds INSTANCE;
    private final ModeSetting soundMode = new ModeSetting("Мод звука", "Первый", "Первый", "Второй", "Третий");
    private static final Identifier ENABLE_ID;
    private static final Identifier DISABLE_ID;
    private static final Identifier ENABLE_ID1;
    private static final Identifier DISABLE_ID1;
    private static final Identifier ENABLE_ID2;
    private static final Identifier DISABLE_ID2;
    private static final SoundEvent ENABLE_EVENT;
    private static final SoundEvent DISABLE_EVENT;
    private static final SoundEvent ENABLE_EVENT1;
    private static final SoundEvent DISABLE_EVENT1;
    private static final SoundEvent ENABLE_EVENT2;
    private static final SoundEvent DISABLE_EVENT2;
    private static SoundInstance lastSound;

    public ClientSounds() {
        INSTANCE = this;
    }

    public static void play(boolean enabled) {
        if (INSTANCE == null || ClientSounds.INSTANCE.mc.getSoundManager() == null) {
            return;
        }
        if (!INSTANCE.isEnabled() && INSTANCE != Babka.getInstance().getModuleStorage().get(ClientSounds.class)) {
            return;
        }
        if (lastSound != null) {
            ClientSounds.INSTANCE.mc.getSoundManager().stop(lastSound);
        }
        SoundEvent soundToPlay = switch (ClientSounds.INSTANCE.soundMode.getValue()) {
            case "Второй" -> {
                if (enabled) {
                    yield ENABLE_EVENT1;
                }
                yield DISABLE_EVENT1;
            }
            case "Третий" -> {
                if (enabled) {
                    yield ENABLE_EVENT2;
                }
                yield DISABLE_EVENT2;
            }
            default -> enabled ? ENABLE_EVENT : DISABLE_EVENT;
        };
        lastSound = PositionedSoundInstance.master((SoundEvent)soundToPlay, (float)1.0f, (float)1.0f);
        ClientSounds.INSTANCE.mc.getSoundManager().play(lastSound);
    }

    static {
        ENABLE_ID = Identifier.of((String)"mre", (String)"module_enable");
        DISABLE_ID = Identifier.of((String)"mre", (String)"module_disable");
        ENABLE_ID1 = Identifier.of((String)"mre", (String)"module_enable1");
        DISABLE_ID1 = Identifier.of((String)"mre", (String)"module_disable1");
        ENABLE_ID2 = Identifier.of((String)"mre", (String)"module_enable2");
        DISABLE_ID2 = Identifier.of((String)"mre", (String)"module_disable2");
        ENABLE_EVENT = SoundEvent.of((Identifier)ENABLE_ID);
        DISABLE_EVENT = SoundEvent.of((Identifier)DISABLE_ID);
        ENABLE_EVENT1 = SoundEvent.of((Identifier)ENABLE_ID1);
        DISABLE_EVENT1 = SoundEvent.of((Identifier)DISABLE_ID1);
        ENABLE_EVENT2 = SoundEvent.of((Identifier)ENABLE_ID2);
        DISABLE_EVENT2 = SoundEvent.of((Identifier)DISABLE_ID2);
    }
}

