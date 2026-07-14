/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Attribute;
import lightning.product.u_530_F;

public class RangedAttribute
extends Attribute {
    private final double n_1700_B;
    private final double J_1907_R;

    public RangedAttribute(String attributeName, double defaultValue, double minimumValue, double maximumValue) {
        super(attributeName, defaultValue);
        this.n_1700_B = minimumValue;
        this.J_1907_R = maximumValue;
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
        }
        if (defaultValue < minimumValue) {
            throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
        }
        if (defaultValue > maximumValue) {
            throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
        }
    }

    @Override
    public double n_1700_B(double value) {
        return u_530_F.n_1700_B(value, this.n_1700_B, this.J_1907_R);
    }
}


