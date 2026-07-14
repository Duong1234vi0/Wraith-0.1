/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_4924_i;
import lightning.product.W_3491_f;
import lightning.product.W_4989_Q;
import lightning.product.g_2336_b;
import lightning.product.BlastingRecipeBookComponent;
import lightning.product.x_282_a;

public class BlastFurnaceScreen
extends J_4924_i<W_4989_Q> {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/container/blast_furnace.png");

    public BlastFurnaceScreen(W_4989_Q screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, new BlastingRecipeBookComponent(), inv, titleIn, J_1907_R);
    }
}


