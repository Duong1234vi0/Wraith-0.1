/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3414
 *  net.minecraft.class_7923
 */
package code.alphadlc.utils.client.sound;

import code.alphadlc.features.impl.misc.ClientSounds;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public final class SoundManager
implements QuickImports {
    public static class_3414 OPEN_GUI = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:gui_open"));
    public static class_3414 CLOSE_GUI = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:gui_close"));
    public static class_3414 ENABLE_MODULE = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:module_enable"));
    public static class_3414 DISABLE_MODULE = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:module_disable"));
    public static class_3414 CATEGORY_CLICK = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:category_click"));
    public static class_3414 NOTIF = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:notif"));
    public static class_3414 SETTING_INTERACT = CATEGORY_CLICK;
    public static class_3414 ORTHODOX = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kolokolnia_kill"));
    public static class_3414 KILL1 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill1"));
    public static class_3414 KILL2 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill2"));
    public static class_3414 KILL3 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill3"));
    public static class_3414 KILL4 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill4"));
    public static class_3414 KILL5 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill5"));
    public static class_3414 KILL6 = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:kill6"));
    public static class_3414 CRITICAL = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:critical"));
    public static class_3414 HELLO = class_3414.method_47908((class_2960)class_2960.method_60654((String)"minecraft:hello"));

    public static void init() {
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)OPEN_GUI.comp_3319(), (Object)OPEN_GUI);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)CLOSE_GUI.comp_3319(), (Object)CLOSE_GUI);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)ENABLE_MODULE.comp_3319(), (Object)ENABLE_MODULE);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)DISABLE_MODULE.comp_3319(), (Object)DISABLE_MODULE);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)CATEGORY_CLICK.comp_3319(), (Object)CATEGORY_CLICK);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)NOTIF.comp_3319(), (Object)NOTIF);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)ORTHODOX.comp_3319(), (Object)ORTHODOX);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL1.comp_3319(), (Object)KILL1);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL2.comp_3319(), (Object)KILL2);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL3.comp_3319(), (Object)KILL3);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL4.comp_3319(), (Object)KILL4);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL5.comp_3319(), (Object)KILL5);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)KILL6.comp_3319(), (Object)KILL6);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)CRITICAL.comp_3319(), (Object)CRITICAL);
        class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)HELLO.comp_3319(), (Object)HELLO);
    }

    public static void playSound(class_3414 sound) {
        boolean guiSound;
        if (PlayerInteractionHelper.nullCheck()) {
            return;
        }
        ClientSounds clientSounds = ClientSounds.getInstance();
        boolean bl = guiSound = sound == OPEN_GUI || sound == CLOSE_GUI || sound == CATEGORY_CLICK || sound == SETTING_INTERACT || sound == NOTIF || sound == CRITICAL || sound == HELLO;
        if (guiSound) {
            mc.method_1483().method_4873((class_1113)class_1109.method_4758((class_3414)sound, (float)1.4f));
            return;
        }
        if (clientSounds == null || !clientSounds.isState()) {
            return;
        }
        float volume = clientSounds.getVolume();
        if (volume <= 0.0f) {
            return;
        }
        mc.method_1483().method_4873((class_1113)class_1109.method_4757((class_3414)sound, (float)volume, (float)clientSounds.getPitch()));
    }

    public static float getClientVolume() {
        ClientSounds clientSounds = ClientSounds.getInstance();
        if (clientSounds == null || !clientSounds.isState()) {
            return 0.0f;
        }
        return clientSounds.getVolume();
    }

    private SoundManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
