/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.tooltips;

import java.util.ArrayList;
import lightning.product.F_2904_S;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import mods.voicechat.gui.widgets.ImageButton;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;

public class RecordingTooltipSupplier
implements ImageButton.TooltipSupplier {
    public static final F_2904_S RECORDING_ENABLED = new F_2904_S("message.voicechat.recording.enabled");
    public static final F_2904_S RECORDING_DISABLED = new F_2904_S("message.voicechat.recording.disabled");
    private final k_2603_m screen;

    public RecordingTooltipSupplier(k_2603_m screen) {
        this.screen = screen;
    }

    @Override
    public void onTooltip(ImageButton button, g_221_o matrices, int mouseX, int mouseY) {
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return;
        }
        ArrayList<FormattedCharSequence> tooltip = new ArrayList<FormattedCharSequence>();
        if (client.getRecorder() == null) {
            tooltip.add(RECORDING_DISABLED.u_1723_Y());
        } else {
            tooltip.add(RECORDING_ENABLED.u_1723_Y());
        }
        this.screen.renderTooltip(matrices, tooltip, mouseX, mouseY);
    }
}


