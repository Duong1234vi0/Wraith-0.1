/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.ConfirmLinkScreen;
import lightning.product.M_2935_g;
import lightning.product.SimpleOptionsSubScreen;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;

public class AccessibilityOptionsScreen
extends SimpleOptionsSubScreen {
    private static final M_2935_g[] n_1700_B = new M_2935_g[]{M_2935_g.NARRATOR, M_2935_g.SHOW_SUBTITLES, M_2935_g.ACCESSIBILITY_TEXT_BACKGROUND_OPACITY, M_2935_g.ACCESSIBILITY_TEXT_BACKGROUND, M_2935_g.CHAT_OPACITY, M_2935_g.LINE_SPACING, M_2935_g.DELAY_INSTANT, M_2935_g.AUTO_JUMP, M_2935_g.SNEAK, M_2935_g.SPRINT, M_2935_g.SCREEN_EFFECT_SCALE_SLIDER, M_2935_g.FOV_EFFECT_SCALE_SLIDER};

    public AccessibilityOptionsScreen(k_2603_m parentScreen, V_4423_d settings) {
        super(parentScreen, settings, new F_2904_S("options.accessibility.title"), n_1700_B);
    }

    @Override
    protected void n_1700_B() {
        this.addButton(new Button(this.width / 2 - 155, this.height - 27, 150, 20, new F_2904_S("options.accessibility.link"), p_244738_1_ -> this.minecraft.n_1700_B(new ConfirmLinkScreen(p_244739_1_ -> {
            if (p_244739_1_) {
                j_3341_s.t_148_a().n_1700_B("https://aka.ms/MinecraftJavaAccessibility");
            }
            this.minecraft.n_1700_B(this);
        }, "https://aka.ms/MinecraftJavaAccessibility", true))));
        this.addButton(new Button(this.width / 2 + 5, this.height - 27, 150, 20, CommonComponents.R_4764_Y, p_244737_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }
}


