/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.TagContainer;
import lightning.product.StaticTagHelper;
import lightning.product.O_4030_c;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.Fluid;

public final class FluidTags {
    protected static final StaticTagHelper<Fluid> n_1700_B = O_4030_c.n_1700_B(new g_2336_b("fluid"), TagContainer::R_4764_Y);
    public static final r_109_r.J_1907_R<Fluid> J_1907_R = FluidTags.n_1700_B("water");
    public static final r_109_r.J_1907_R<Fluid> R_4764_Y = FluidTags.n_1700_B("lava");

    private static r_109_r.J_1907_R<Fluid> n_1700_B(String id) {
        return n_1700_B.n_1700_B(id);
    }

    public static List<? extends r_109_r.J_1907_R<Fluid>> n_1700_B() {
        return n_1700_B.R_4764_Y();
    }
}


