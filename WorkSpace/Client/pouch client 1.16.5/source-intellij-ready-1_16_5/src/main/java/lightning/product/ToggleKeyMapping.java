/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BooleanSupplier;
import lightning.product.D_590_W;
import lightning.product.Q_4113_P;

public class ToggleKeyMapping
extends D_590_W {
    private final BooleanSupplier n_1700_B;

    public ToggleKeyMapping(String descriptionIn, int codeIn, String categoryIn, BooleanSupplier getterIn) {
        super(descriptionIn, Q_4113_P.J_1907_R.n_1700_B, codeIn, categoryIn);
        this.n_1700_B = getterIn;
    }

    @Override
    public void n_1700_B(boolean valueIn) {
        if (this.n_1700_B.getAsBoolean()) {
            if (valueIn) {
                super.n_1700_B(!this.G_564_y());
            }
        } else {
            super.n_1700_B(valueIn);
        }
    }
}


