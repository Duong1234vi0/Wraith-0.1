/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_742
 *  net.minecraft.class_8685
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.features.impl.render.customcape.CapeSyncManager;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import net.minecraft.class_1297;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_742;
import net.minecraft.class_8685;

public class CustomCape
extends Module {
    public static final class_2960 CAPE_TEXTURE = class_2960.method_60655((String)"alphadlc", (String)"textures/cape.png");

    public static CustomCape getInstance() {
        return Instance.get(CustomCape.class);
    }

    public CustomCape() {
        super("CustomCape", "CustomCape", ModuleCategory.RENDER);
        this.setState(true);
    }

    public boolean shouldApply(class_742 player) {
        if (!this.isState() || player == null) {
            return false;
        }
        class_310 client = class_310.method_1551();
        if (client.field_1724 != null && client.field_1724.method_5667().equals(player.method_5667())) {
            return true;
        }
        CapeSyncManager syncManager = CapeSyncManager.getInstance();
        if (syncManager != null && syncManager.hasRemoteCape(player.method_5667())) {
            return true;
        }
        return FriendUtils.isFriend((class_1297)player);
    }

    public static class_8685 withCustomCape(class_8685 original) {
        if (original == null) {
            return null;
        }
        return new class_8685(original.comp_1626(), original.comp_1911(), CAPE_TEXTURE, original.comp_1628(), original.comp_1629(), original.comp_1630());
    }
}
