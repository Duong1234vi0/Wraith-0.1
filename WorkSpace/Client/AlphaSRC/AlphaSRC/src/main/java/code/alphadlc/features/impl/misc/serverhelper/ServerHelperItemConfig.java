/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 */
package code.alphadlc.features.impl.misc.serverhelper;

import java.util.List;
import net.minecraft.class_1792;

public record ServerHelperItemConfig(class_1792 item, String displayName, List<String> loreParams) {
    public ServerHelperItemConfig(class_1792 item, String displayName) {
        this(item, displayName, List.of());
    }
}
