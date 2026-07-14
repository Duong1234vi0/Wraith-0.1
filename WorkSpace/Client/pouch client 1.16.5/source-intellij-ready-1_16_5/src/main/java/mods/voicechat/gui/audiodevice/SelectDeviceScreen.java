/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.audiodevice;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import mods.voicechat.gui.audiodevice.AudioDeviceList;
import mods.voicechat.gui.widgets.ListScreenBase;

public abstract class SelectDeviceScreen
extends ListScreenBase {
    protected static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_audio_devices.png");
    protected static final x_282_a BACK = new F_2904_S("message.voicechat.back");
    protected static final int HEADER_SIZE = 16;
    protected static final int FOOTER_SIZE = 32;
    protected static final int UNIT_SIZE = 18;
    @Nullable
    protected k_2603_m parent;
    protected AudioDeviceList deviceList;
    protected Button back;
    protected int units;

    public SelectDeviceScreen(x_282_a title, @Nullable k_2603_m parent) {
        super(title, 236, 0);
        this.parent = parent;
    }

    public abstract List<String> getDevices();

    public abstract g_2336_b getIcon();

    public abstract x_282_a getEmptyListComponent();

    public abstract ConfigEntry<String> getConfigEntry();

    @Override
    protected void init() {
        super.init();
        this.guiLeft += 2;
        this.guiTop = 32;
        int minUnits = u_530_F.u_1723_Y(2.2222223f);
        this.units = Math.max(minUnits, (this.height - 16 - 32 - this.guiTop * 2) / 18);
        this.ySize = 16 + this.units * 18 + 32;
        if (this.deviceList != null) {
            this.deviceList.updateSize(this.width, this.units * 18, this.guiTop + 16);
        } else {
            this.deviceList = new AudioDeviceList(this.width, this.units * 18, this.guiTop + 16).setIcon(this.getIcon()).setConfigEntry(this.getConfigEntry());
        }
        this.addListener(this.deviceList);
        this.back = new Button(this.guiLeft + 7, this.guiTop + this.ySize - 20 - 7, this.xSize - 14, 20, BACK, button -> this.minecraft.n_1700_B(this.parent));
        this.addButton(this.back);
        this.deviceList.setAudioDevices(this.getDevices());
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        if (this.isIngame()) {
            this.minecraft.G_624_v().n_1700_B(TEXTURE);
            this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, 16);
            for (int i = 0; i < this.units; ++i) {
                this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * i, 0, 16, this.xSize, 18);
            }
            this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * this.units, 0, 34, this.xSize, 32);
            this.blit(poseStack, this.guiLeft + 10, this.guiTop + 16 + 6 - 2, this.xSize, 0, 12, 12);
        }
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.font.J_1907_R(poseStack, this.title, (float)(this.width / 2 - this.font.n_1700_B((FormattedText)this.title) / 2), (float)(this.guiTop + 5), this.isIngame() ? 0x404040 : D_4024_W.M_182_A.G_564_y());
        if (!this.deviceList.isEmpty()) {
            this.deviceList.render(poseStack, mouseX, mouseY, delta);
        } else {
            SelectDeviceScreen.drawCenteredString(poseStack, this.font, this.getEmptyListComponent(), this.width / 2, this.guiTop + 16 + this.units * 18 / 2 - this.font.n_1700_B / 2, -1);
        }
    }
}


