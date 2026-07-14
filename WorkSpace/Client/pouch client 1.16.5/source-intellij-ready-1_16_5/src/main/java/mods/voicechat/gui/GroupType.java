/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui;

import lightning.product.F_2904_S;
import lightning.product.x_282_a;
import mods.voicechat.api.Group;

public enum GroupType {
    NORMAL(new F_2904_S("message.voicechat.group_type.normal"), new F_2904_S("message.voicechat.group_type.normal.description"), Group.Type.NORMAL),
    OPEN(new F_2904_S("message.voicechat.group_type.open"), new F_2904_S("message.voicechat.group_type.open.description"), Group.Type.OPEN),
    ISOLATED(new F_2904_S("message.voicechat.group_type.isolated"), new F_2904_S("message.voicechat.group_type.isolated.description"), Group.Type.ISOLATED);

    private final x_282_a translation;
    private final x_282_a description;
    private final Group.Type type;

    private GroupType(x_282_a translation, x_282_a description, Group.Type type) {
        this.translation = translation;
        this.description = description;
        this.type = type;
    }

    public x_282_a getTranslation() {
        return this.translation;
    }

    public x_282_a getDescription() {
        return this.description;
    }

    public Group.Type getType() {
        return this.type;
    }

    public static GroupType fromType(Group.Type type) {
        for (GroupType groupType : GroupType.values()) {
            if (groupType.getType() != type) continue;
            return groupType;
        }
        return NORMAL;
    }
}

