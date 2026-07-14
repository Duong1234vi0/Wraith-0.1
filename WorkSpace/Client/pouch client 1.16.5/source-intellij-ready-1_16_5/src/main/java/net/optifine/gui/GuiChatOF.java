/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import lightning.product.J_4417_W;
import lightning.product.h_4412_P;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class GuiChatOF
extends h_4412_P {
    private static final String CMD_RELOAD_SHADERS = "/reloadShaders";
    private static final String CMD_RELOAD_CHUNKS = "/reloadChunks";

    public GuiChatOF(h_4412_P guiChat) {
        super(J_4417_W.n_1700_B(guiChat));
    }

    @Override
    public void sendMessage(String msg) {
        if (this.checkCustomCommand(msg)) {
            this.minecraft.M_588_G.R_4764_Y().n_1700_B(msg);
        } else {
            super.sendMessage(msg);
        }
    }

    private boolean checkCustomCommand(String msg) {
        if (msg == null) {
            return false;
        }
        if ((msg = msg.trim()).equals(CMD_RELOAD_SHADERS)) {
            if (Config.isShaders()) {
                Shaders.uninit();
                Shaders.loadShaderPack();
            }
            return true;
        }
        if (msg.equals(CMD_RELOAD_CHUNKS)) {
            this.minecraft.u_1723_Y.P_1922_E();
            return true;
        }
        return false;
    }
}

