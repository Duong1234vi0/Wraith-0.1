/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat;

import lightning.product.A_4115_X;
import mods.voicechat.Voicechat;
import mods.voicechat.config.ConfigMigrator;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public class ForgeVoicechatMod
extends Voicechat {
    public ForgeVoicechatMod() {
        this.initialize();
        A_4115_X.n_1700_B(new ConfigMigrator());
        A_4115_X.n_1700_B(CommonCompatibilityManager.INSTANCE);
    }
}

