/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.events;

import lightning.product.B_4088_l;
import lightning.product.x_607_J;

public class VoiceChatCompatibilityCheckSucceededEvent
implements x_607_J {
    private final B_4088_l player;

    public VoiceChatCompatibilityCheckSucceededEvent(B_4088_l player) {
        this.player = player;
    }

    public B_4088_l getPlayer() {
        return this.player;
    }
}

