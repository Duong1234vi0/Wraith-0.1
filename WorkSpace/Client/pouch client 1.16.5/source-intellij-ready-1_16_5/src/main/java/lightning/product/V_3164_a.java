/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lightning.product.x_282_a;

public class V_3164_a
extends RuntimeException {
    private final x_282_a n_1700_B;

    public V_3164_a(x_282_a message) {
        super(message.getString(), null, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES);
        this.n_1700_B = message;
    }

    public x_282_a n_1700_B() {
        return this.n_1700_B;
    }
}

