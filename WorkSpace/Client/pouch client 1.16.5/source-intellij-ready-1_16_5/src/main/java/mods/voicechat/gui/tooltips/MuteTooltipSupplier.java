/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.tooltips;

import java.util.ArrayList;
import lightning.product.F_2904_S;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.widgets.ImageButton;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.MicrophoneActivationType;

public class MuteTooltipSupplier
implements ImageButton.TooltipSupplier {
    public static final F_2904_S MUTE_UNMUTED = new F_2904_S("message.voicechat.mute.disabled");
    public static final F_2904_S MUTE_MUTED = new F_2904_S("message.voicechat.mute.enabled");
    public static final F_2904_S MUTE_DISABLED_PTT = new F_2904_S("message.voicechat.mute.disabled_ptt");
    private k_2603_m screen;
    private ClientPlayerStateManager stateManager;

    public MuteTooltipSupplier(k_2603_m screen, ClientPlayerStateManager stateManager) {
        this.screen = screen;
        this.stateManager = stateManager;
    }

    @Override
    public void onTooltip(ImageButton button, g_221_o matrices, int mouseX, int mouseY) {
        ArrayList<FormattedCharSequence> tooltip = new ArrayList<FormattedCharSequence>();
        if (!MuteTooltipSupplier.canMuteMic()) {
            tooltip.add(MUTE_DISABLED_PTT.u_1723_Y());
        } else if (this.stateManager.isMuted()) {
            tooltip.add(MUTE_MUTED.u_1723_Y());
        } else {
            tooltip.add(MUTE_UNMUTED.u_1723_Y());
        }
        this.screen.renderTooltip(matrices, tooltip, mouseX, mouseY);
    }

    public static boolean canMuteMic() {
        return ((MicrophoneActivationType)((Object)VoicechatClient.CLIENT_CONFIG.microphoneActivationType.get())).equals((Object)MicrophoneActivationType.VOICE);
    }
}


