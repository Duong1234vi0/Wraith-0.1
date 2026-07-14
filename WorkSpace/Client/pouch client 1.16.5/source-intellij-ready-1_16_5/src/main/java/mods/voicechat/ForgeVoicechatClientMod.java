/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat;

import lightning.product.A_4115_X;
import mods.voicechat.VoicechatClient;
import mods.voicechat.config.ConfigMigrator;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;

public class ForgeVoicechatClientMod
extends VoicechatClient {
    public ForgeVoicechatClientMod() {
        this.initializeClient();
        A_4115_X.n_1700_B(ClientCompatibilityManager.INSTANCE);
    }

    @Override
    public void initializeConfigs() {
        super.initializeConfigs();
        ConfigMigrator.migrateClientConfig();
    }
}

