/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.permission;

import lightning.product.B_4088_l;
import mods.voicechat.permission.PermissionType;

public interface Permission {
    public boolean hasPermission(B_4088_l var1);

    public PermissionType getPermissionType();
}

