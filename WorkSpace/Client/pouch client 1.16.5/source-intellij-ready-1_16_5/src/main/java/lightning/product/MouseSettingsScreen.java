/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.stream.Stream;
import lightning.product.OptionsSubScreen;
import lightning.product.OptionsList;
import lightning.product.F_2904_S;
import lightning.product.M_2935_g;
import lightning.product.Q_4113_P;
import lightning.product.Button;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;

public class MouseSettingsScreen
extends OptionsSubScreen {
    private OptionsList n_1700_B;
    private static final M_2935_g[] J_1907_R = new M_2935_g[]{M_2935_g.SENSITIVITY, M_2935_g.INVERT_MOUSE, M_2935_g.MOUSE_WHEEL_SENSITIVITY, M_2935_g.DISCRETE_MOUSE_SCROLL, M_2935_g.TOUCHSCREEN};

    public MouseSettingsScreen(k_2603_m p_i225929_1_, V_4423_d p_i225929_2_) {
        super(p_i225929_1_, p_i225929_2_, new F_2904_S("options.mouse_settings.title"));
    }

    @Override
    protected void init() {
        this.n_1700_B = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        if (Q_4113_P.n_1700_B()) {
            this.n_1700_B.n_1700_B((M_2935_g[])Stream.concat(Arrays.stream(J_1907_R), Stream.of(M_2935_g.RAW_MOUSE_INPUT)).toArray(M_2935_g[]::new));
        } else {
            this.n_1700_B.n_1700_B(J_1907_R);
        }
        this.children.add(this.n_1700_B);
        this.addButton(new Button(this.width / 2 - 100, this.height - 27, 200, 20, CommonComponents.R_4764_Y, p_223703_1_ -> {
            this.G_564_y.J_1907_R();
            this.minecraft.n_1700_B(this.R_4764_Y);
        }));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.n_1700_B.render(matrixStack, mouseX, mouseY, partialTicks);
        MouseSettingsScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 5, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


