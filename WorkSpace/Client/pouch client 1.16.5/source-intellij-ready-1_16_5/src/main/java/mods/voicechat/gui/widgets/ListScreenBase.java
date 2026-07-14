/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import lightning.product.g_221_o;
import lightning.product.x_282_a;
import mods.voicechat.gui.VoiceChatScreenBase;

public abstract class ListScreenBase
extends VoiceChatScreenBase {
    private Runnable postRender;

    public ListScreenBase(x_282_a title, int xSize, int ySize) {
        super(title, xSize, ySize);
    }

    @Override
    public void render(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        if (this.postRender != null) {
            this.postRender.run();
            this.postRender = null;
        }
    }

    public void postRender(Runnable postRender) {
        this.postRender = postRender;
    }
}

