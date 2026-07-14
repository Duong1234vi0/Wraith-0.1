/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.onboarding;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.gui.audiodevice.AudioDeviceList;
import mods.voicechat.gui.onboarding.OnboardingScreenBase;

public abstract class DeviceOnboardingScreen
extends OnboardingScreenBase {
    protected AudioDeviceList deviceList;
    protected List<String> micNames;

    public DeviceOnboardingScreen(x_282_a title, @Nullable k_2603_m previous) {
        super(title, previous);
        this.minecraft = MinecraftClient.A_4115_X();
        this.micNames = this.getNames();
        if (this.micNames.isEmpty()) {
            this.minecraft.w_1484_f(() -> this.minecraft.n_1700_B(this.getNextScreen()));
        }
    }

    public abstract List<String> getNames();

    public abstract g_2336_b getIcon();

    public abstract ConfigEntry<String> getConfigEntry();

    @Override
    protected void init() {
        super.init();
        if (this.deviceList != null) {
            this.deviceList.updateSize(this.width, this.contentHeight - this.font.n_1700_B - 20 - 16, this.guiTop + this.font.n_1700_B + 8);
        } else {
            this.deviceList = new AudioDeviceList(this.width, this.contentHeight - this.font.n_1700_B - 20 - 16, this.guiTop + this.font.n_1700_B + 8).setIcon(this.getIcon()).setConfigEntry(this.getConfigEntry());
        }
        this.deviceList.setAudioDevices(this.getNames());
        this.addListener(this.deviceList);
        this.addBackOrCancelButton();
        this.addNextButton();
    }

    @Override
    public abstract k_2603_m getNextScreen();

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.deviceList.render(stack, mouseX, mouseY, partialTicks);
        this.renderTitle(stack, this.title);
    }
}


