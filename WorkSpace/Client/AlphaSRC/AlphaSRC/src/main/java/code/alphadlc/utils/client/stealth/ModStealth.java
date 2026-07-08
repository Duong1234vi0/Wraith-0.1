/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2817
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 */
package code.alphadlc.utils.client.stealth;

import net.minecraft.class_2596;
import net.minecraft.class_2817;
import net.minecraft.class_2960;
import net.minecraft.class_8710;

public final class ModStealth {
    public static final String MOD_ID = "renderextras";
    public static final String LEGACY_MOD_ID = "alphadlc";
    public static final String CLIENT_BRAND = "vanilla";
    private static final String[] BLOCKED_CHANNEL_FRAGMENTS = new String[]{"alphadlc", "renderextras", "alphadlc", "modlist", "mod_list", "mods_list", "fabric-mod", "fabricloader", "dreamix", "vmbridge"};

    public static boolean shouldBlockOutgoing(class_2596<?> packet) {
        if (!(packet instanceof class_2817)) {
            return false;
        }
        class_2817 customPayload = (class_2817)packet;
        class_8710 payload = customPayload.comp_1647();
        if (payload == null) {
            return false;
        }
        class_8710.class_9154 id = payload.method_56479();
        if (id == null) {
            return false;
        }
        class_2960 identifier = id.comp_2242();
        if (identifier == null) {
            return false;
        }
        return ModStealth.containsBlockedMarker(identifier.toString()) || ModStealth.containsBlockedMarker(payload.getClass().getName());
    }

    private static boolean containsBlockedMarker(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        String lower = value.toLowerCase();
        for (String fragment : BLOCKED_CHANNEL_FRAGMENTS) {
            if (!lower.contains(fragment)) continue;
            return true;
        }
        return false;
    }

    private ModStealth() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
