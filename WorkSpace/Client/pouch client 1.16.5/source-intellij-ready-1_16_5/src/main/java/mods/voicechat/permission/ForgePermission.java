/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.permission;

import lightning.product.B_4088_l;
import mods.voicechat.permission.Permission;
import mods.voicechat.permission.PermissionType;

public class ForgePermission
implements Permission {
    private final String node;
    private final PermissionType type;

    public ForgePermission(String node, PermissionType type) {
        this.node = node;
        this.type = type;
    }

    @Override
    public boolean hasPermission(B_4088_l player) {
        return true;
    }

    @Override
    public PermissionType getPermissionType() {
        return this.type;
    }

    public String getNode() {
        return this.node;
    }
}

