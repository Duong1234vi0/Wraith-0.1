/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.permission;

import mods.voicechat.permission.ForgePermission;
import mods.voicechat.permission.Permission;
import mods.voicechat.permission.PermissionManager;
import mods.voicechat.permission.PermissionType;

public class ForgePermissionManager
extends PermissionManager {
    @Override
    public Permission createPermissionInternal(String modId, String node, PermissionType type) {
        return new ForgePermission(modId + "." + node, type);
    }
}

