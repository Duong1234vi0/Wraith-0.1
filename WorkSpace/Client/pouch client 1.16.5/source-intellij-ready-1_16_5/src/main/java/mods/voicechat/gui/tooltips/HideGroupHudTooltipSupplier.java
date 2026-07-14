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

public class HideGroupHudTooltipSupplier
implements ImageButton.TooltipSupplier {
    public static final F_2904_S SHOW_GROUP_HUD_ENABLED = new F_2904_S("message.voicechat.show_group_hud.enabled");
    public static final F_2904_S SHOW_GROUP_HUD_DISABLED = new F_2904_S("message.voicechat.show_group_hud.disabled");
    private final k_2603_m screen;

    public HideGroupHudTooltipSupplier(k_2603_m screen) {
        this.screen = screen;
    }

    @Override
    public void onTooltip(ImageButton button, g_221_o matrices, int mouseX, int mouseY) {
        ArrayList<FormattedCharSequence> tooltip = new ArrayList<FormattedCharSequence>();
        if (((Boolean)VoicechatClient.CLIENT_CONFIG.showGroupHud.get()).booleanValue()) {
            tooltip.add(SHOW_GROUP_HUD_ENABLED.u_1723_Y());
        } else {
            tooltip.add(SHOW_GROUP_HUD_DISABLED.u_1723_Y());
        }
        this.screen.renderTooltip(matrices, tooltip, mouseX, mouseY);
    }
}


