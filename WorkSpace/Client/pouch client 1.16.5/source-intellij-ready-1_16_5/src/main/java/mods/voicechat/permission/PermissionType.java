/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.permission;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;

public enum PermissionType {
    EVERYONE,
    NOONE,
    OPS;


    boolean hasPermission(@Nullable B_4088_l player) {
        switch (this.ordinal()) {
            case 0: {
                return true;
            }
            default: {
                return false;
            }
            case 2: 
        }
        return player != null && player.t_148_a(player.J_1907_R.t_1786_h());
    }
}

