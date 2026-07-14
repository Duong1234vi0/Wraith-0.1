/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_512_t;
import lightning.product.J_4924_i;
import lightning.product.W_3491_f;
import lightning.product.SmokingRecipeBookComponent;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;

public class SmokerScreen
extends J_4924_i<B_512_t> {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/container/smoker.png");

    public SmokerScreen(B_512_t screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, new SmokingRecipeBookComponent(), inv, titleIn, J_1907_R);
    }
}


