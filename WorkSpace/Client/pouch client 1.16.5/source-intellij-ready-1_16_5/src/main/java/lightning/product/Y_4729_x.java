/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import lightning.product.M_2935_g;
import lightning.product.Button;
import lightning.product.FormattedCharSequence;
import lightning.product.q_2454_w;
import lightning.product.x_282_a;
import net.optifine.gui.IOptionControl;

public class Y_4729_x
extends Button
implements q_2454_w,
IOptionControl {
    private final M_2935_g n_1700_B;

    public Y_4729_x(int x, int y, int width, int height, M_2935_g enumOptions, x_282_a title, Button.n_1700_B p_i232262_7_) {
        super(x, y, width, height, title, p_i232262_7_);
        this.n_1700_B = enumOptions;
    }

    public M_2935_g J_1907_R() {
        return this.n_1700_B;
    }

    @Override
    public Optional<List<FormattedCharSequence>> n_1700_B() {
        return this.n_1700_B.getOptionValues();
    }

    @Override
    public M_2935_g getControlOption() {
        return this.n_1700_B;
    }
}


