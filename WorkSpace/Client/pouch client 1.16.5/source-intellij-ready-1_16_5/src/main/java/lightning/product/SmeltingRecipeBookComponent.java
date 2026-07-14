/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Set;
import lightning.product.F_2904_S;
import lightning.product.n_1680_G;
import lightning.product.q_1613_l;
import lightning.product.AbstractFurnaceRecipeBookComponent;
import lightning.product.x_282_a;

public class SmeltingRecipeBookComponent
extends AbstractFurnaceRecipeBookComponent {
    private static final x_282_a u_1723_Y = new F_2904_S("gui.recipebook.toggleRecipes.smeltable");

    @Override
    protected x_282_a R_4764_Y() {
        return u_1723_Y;
    }

    @Override
    protected Set<q_1613_l> J_1907_R() {
        return n_1680_G.G_564_y().keySet();
    }
}


