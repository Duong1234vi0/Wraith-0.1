/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;

public class TranslatableFormatException
extends IllegalArgumentException {
    public TranslatableFormatException(F_2904_S component, String message) {
        super(String.format("Error parsing: %s: %s", component, message));
    }

    public TranslatableFormatException(F_2904_S component, int index) {
        super(String.format("Invalid index %d requested for %s", index, component));
    }

    public TranslatableFormatException(F_2904_S component, Throwable cause) {
        super(String.format("Error while parsing: %s", component), cause);
    }
}


