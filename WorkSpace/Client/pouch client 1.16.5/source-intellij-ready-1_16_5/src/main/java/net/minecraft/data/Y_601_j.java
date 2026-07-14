/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.data;

import java.nio.file.Path;
import lightning.product.FluidTags;
import lightning.product.Fluids;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.Fluid;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.z_4693_k;

public class Y_601_j
extends z_4693_k<Fluid> {
    public Y_601_j(Q_4569_t generatorIn) {
        super(generatorIn, V_3137_a.G_624_v);
    }

    @Override
    protected void J_1907_R() {
        this.n_1700_B(FluidTags.J_1907_R).n_1700_B((Fluid[])new Fluid[]{Fluids.R_4764_Y, Fluids.J_1907_R});
        this.n_1700_B(FluidTags.R_4764_Y).n_1700_B((Fluid[])new Fluid[]{Fluids.P_1922_E, Fluids.G_564_y});
    }

    @Override
    protected Path n_1700_B(g_2336_b id) {
        return this.J_1907_R.J_1907_R().resolve("data/" + id.R_4764_Y() + "/tags/fluids/" + id.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Fluid Tags";
    }
}


