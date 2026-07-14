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
import mods.voicechat.voice.client.ClientPlayerStateManager;

public class DisableTooltipSupplier
implements ImageButton.TooltipSupplier {
    public static final F_2904_S DISABLE_ENABLED = new F_2904_S("message.voicechat.disable.enabled");
    public static final F_2904_S DISABLE_DISABLED = new F_2904_S("message.voicechat.disable.disabled");
    public static final F_2904_S DISABLE_NO_SPEAKER = new F_2904_S("message.voicechat.disable.no_speaker");
    private final k_2603_m screen;
    private final ClientPlayerStateManager stateManager;

    public DisableTooltipSupplier(k_2603_m screen, ClientPlayerStateManager stateManager) {
        this.screen = screen;
        this.stateManager = stateManager;
    }

    @Override
    public void onTooltip(ImageButton button, g_221_o matrices, int mouseX, int mouseY) {
        ArrayList<FormattedCharSequence> tooltip = new ArrayList<FormattedCharSequence>();
        if (!this.stateManager.canEnable()) {
            tooltip.add(DISABLE_NO_SPEAKER.u_1723_Y());
        } else if (this.stateManager.isDisabled()) {
            tooltip.add(DISABLE_ENABLED.u_1723_Y());
        } else {
            tooltip.add(DISABLE_DISABLED.u_1723_Y());
        }
        this.screen.renderTooltip(matrices, tooltip, mouseX, mouseY);
    }
}


