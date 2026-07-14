/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import lightning.product.Q_4113_P;
import lightning.product.V_4423_d;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;

public class PTTKeyHandler {
    private boolean pttKeyDown;
    private boolean whisperKeyDown;

    public PTTKeyHandler() {
        ClientCompatibilityManager.INSTANCE.onKeyboardEvent(this::onKeyboardEvent);
        ClientCompatibilityManager.INSTANCE.onMouseEvent(this::onMouseEvent);
    }

    public void onKeyboardEvent(long window, int key, int scancode) {
        Q_4113_P.n_1700_B whisperKey;
        Q_4113_P.n_1700_B pttKey = ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.RealmsWorldOptions);
        if (pttKey.J_1907_R() != -1 && !pttKey.n_1700_B().equals((Object)Q_4113_P.J_1907_R.R_4764_Y)) {
            this.pttKeyDown = Q_4113_P.n_1700_B(window, pttKey.J_1907_R());
        }
        if ((whisperKey = ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.RealmsWorldResetDto)).J_1907_R() != -1 && !whisperKey.n_1700_B().equals((Object)Q_4113_P.J_1907_R.R_4764_Y)) {
            this.whisperKeyDown = Q_4113_P.n_1700_B(window, whisperKey.J_1907_R());
        }
    }

    public void onMouseEvent(long window, int button, int action, int mods) {
        Q_4113_P.n_1700_B whisperKey;
        Q_4113_P.n_1700_B pttKey = ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.RealmsWorldOptions);
        if (pttKey.J_1907_R() != -1 && pttKey.n_1700_B().equals((Object)Q_4113_P.J_1907_R.R_4764_Y) && pttKey.J_1907_R() == button) {
            boolean bl = this.pttKeyDown = action != 0;
        }
        if ((whisperKey = ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.RealmsWorldResetDto)).J_1907_R() != -1 && whisperKey.n_1700_B().equals((Object)Q_4113_P.J_1907_R.R_4764_Y) && whisperKey.J_1907_R() == button) {
            this.whisperKeyDown = action != 0;
        }
    }

    public boolean isPTTDown() {
        return this.pttKeyDown;
    }

    public boolean isWhisperDown() {
        return this.whisperKeyDown;
    }

    public boolean isAnyDown() {
        return this.pttKeyDown || this.whisperKeyDown;
    }
}


