/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package mods.viaversion.viamcp.gui;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import lightning.product.D_4024_W;
import lightning.product.O_694_j;
import lightning.product.Y_4083_F;
import lightning.product.g_221_o;
import lightning.product.x_282_a;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class VersionSelectScreen
extends O_694_j {
    private ProtocolVersion lastAppliedVersion;

    public VersionSelectScreen(Y_4083_F font, int x, int y, int width, int height, x_282_a title) {
        super(font, x, y, width, height, title);
        this.setText("-1");
        this.setTextColor(D_4024_W.s_956_w.G_564_y());
        this.lastAppliedVersion = ProtocolVersion.getProtocol((int)754);
    }

    public void setVersion(int protocol) {
        ProtocolVersion version;
        this.lastAppliedVersion = version = ProtocolVersion.getProtocol((int)protocol);
        if (!this.getText().trim().equals("-1")) {
            this.setText(version.getName());
        }
    }

    private void applyClosestVersionFromText() {
        String raw = this.getText().trim();
        if (raw.equals("-1")) {
            ProtocolVersion target = ProtocolVersion.getProtocol((int)754);
            if (this.lastAppliedVersion != target) {
                ViaLoadingBase.getInstance().reload(target);
                this.lastAppliedVersion = target;
            }
            this.setTextColor(D_4024_W.s_956_w.G_564_y());
            return;
        }
        ProtocolVersion resolved = ProtocolVersion.getClosest((String)raw);
        ProtocolVersion target = resolved != null ? resolved : ProtocolVersion.getProtocol((int)754);
        this.setText(target.getName());
        if (this.lastAppliedVersion != target) {
            ViaLoadingBase.getInstance().reload(target);
            this.lastAppliedVersion = target;
        }
        this.setTextColor(D_4024_W.M_182_A.G_564_y());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 257 || keyCode == 335) {
            this.applyClosestVersionFromText();
            this.setFocused2(false);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean wasFocused = this.isFocused();
        boolean handled = super.mouseClicked(mouseX, mouseY, button);
        if (wasFocused && !this.isFocused()) {
            this.applyClosestVersionFromText();
        }
        return handled;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        String raw = this.getText().trim();
        if (raw.equals("-1")) {
            this.setTextColor(D_4024_W.s_956_w.G_564_y());
            return;
        }
        ProtocolVersion closest = ProtocolVersion.getClosest((String)raw);
        if (closest == null) {
            this.setTextColor(D_4024_W.P_4830_p.G_564_y());
            return;
        }
        boolean exactMatch = closest.getName().equalsIgnoreCase(this.getText().trim());
        if (exactMatch) {
            if (this.lastAppliedVersion != closest) {
                ViaLoadingBase.getInstance().reload(closest);
                this.lastAppliedVersion = closest;
            }
            this.setTextColor(D_4024_W.M_182_A.G_564_y());
        } else {
            this.setTextColor(D_4024_W.Q_4569_t.G_564_y());
        }
    }
}

