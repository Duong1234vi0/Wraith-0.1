/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.M_2935_g;
import lightning.product.SimpleOptionsSubScreen;
import lightning.product.V_4423_d;
import lightning.product.k_2603_m;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;

public class u_2986_h
extends SimpleOptionsSubScreen {
    private static final M_2935_g[] n_1700_B = new M_2935_g[]{M_2935_g.CHAT_VISIBILITY, M_2935_g.CHAT_COLOR, M_2935_g.CHAT_LINKS, M_2935_g.CHAT_LINKS_PROMPT, M_2935_g.CHAT_OPACITY, M_2935_g.ACCESSIBILITY_TEXT_BACKGROUND_OPACITY, M_2935_g.CHAT_SCALE, M_2935_g.LINE_SPACING, M_2935_g.DELAY_INSTANT, M_2935_g.CHAT_WIDTH, M_2935_g.CHAT_HEIGHT_FOCUSED, M_2935_g.CHAT_HEIGHT_UNFOCUSED, M_2935_g.CHAT_BACKGROUND, M_2935_g.CHAT_SHADOW, M_2935_g.NARRATOR, M_2935_g.AUTO_SUGGEST_COMMANDS, M_2935_g.field_244786_G, M_2935_g.REDUCED_DEBUG_INFO};
    private TooltipManager J_1907_R = new TooltipManager(this, new TooltipProviderOptions());

    public u_2986_h(k_2603_m parentScreenIn, V_4423_d gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn, new F_2904_S("options.chat.title"), n_1700_B);
    }
}


