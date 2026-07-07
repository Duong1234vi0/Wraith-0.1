package net.ccbluex.liquidbounce.interfaces;

import net.minecraft.network.chat.TextColor;

public interface ClientTextColorAdditions {
    boolean liquid_bounce$doesBypassingNameProtect();
    TextColor liquid_bounce$withNameProtectionBypass();

    @Deprecated
    /**
     * Please don't use this method, it is only for internal use.
     */
    void liquid_bounce$setBypassingNameProtection(boolean bypassesNameProtect);
}
