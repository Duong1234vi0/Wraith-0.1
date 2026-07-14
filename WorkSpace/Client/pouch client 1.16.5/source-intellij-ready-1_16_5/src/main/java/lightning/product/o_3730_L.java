/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import lightning.product.I_2212_R;
import lightning.product.M_2935_g;
import lightning.product.O_4844_u;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.FormattedCharSequence;
import lightning.product.q_2454_w;
import lightning.product.x_282_a;
import net.optifine.config.FloatOptions;
import net.optifine.gui.IOptionControl;

public class o_3730_L
extends O_4844_u
implements q_2454_w,
IOptionControl {
    private final I_2212_R J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;

    public o_3730_L(V_4423_d settings, int xIn, int yIn, int widthIn, int heightIn, I_2212_R optionIn) {
        super(settings, xIn, yIn, widthIn, heightIn, (double)((float)optionIn.normalizeValue(optionIn.get(settings))));
        this.J_1907_R = optionIn;
        this.func_230979_b_();
        this.R_4764_Y = FloatOptions.supportAdjusting(this.J_1907_R);
        this.G_564_y = false;
    }

    @Override
    protected void func_230972_a_() {
        if (!this.G_564_y) {
            double d0 = this.J_1907_R.get(this.n_1700_B);
            double d1 = this.J_1907_R.denormalizeValue(this.sliderValue);
            if (d1 != d0) {
                this.J_1907_R.set(this.n_1700_B, this.J_1907_R.denormalizeValue(this.sliderValue));
                this.n_1700_B.J_1907_R();
            }
        }
    }

    @Override
    protected void func_230979_b_() {
        if (this.G_564_y) {
            double d0 = this.J_1907_R.denormalizeValue(this.sliderValue);
            x_282_a itextcomponent = FloatOptions.getTextComponent(this.J_1907_R, d0);
            if (itextcomponent != null) {
                this.setMessage(itextcomponent);
            }
        } else {
            this.setMessage(this.J_1907_R.func_238334_c_(this.n_1700_B));
        }
    }

    @Override
    public Optional<List<FormattedCharSequence>> n_1700_B() {
        return this.J_1907_R.getOptionValues();
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (this.R_4764_Y) {
            this.G_564_y = true;
        }
        super.onClick(mouseX, mouseY);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        if (this.R_4764_Y) {
            this.G_564_y = true;
        }
        super.onDrag(mouseX, mouseY, dragX, dragY);
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        if (this.G_564_y) {
            this.G_564_y = false;
            this.func_230972_a_();
            this.func_230979_b_();
        }
        super.onRelease(mouseX, mouseY);
    }

    public static int n_1700_B(V_2511_L p_getWidth_0_) {
        return p_getWidth_0_.width;
    }

    public static int J_1907_R(V_2511_L p_getHeight_0_) {
        return p_getHeight_0_.height;
    }

    @Override
    public M_2935_g getControlOption() {
        return this.J_1907_R;
    }
}


