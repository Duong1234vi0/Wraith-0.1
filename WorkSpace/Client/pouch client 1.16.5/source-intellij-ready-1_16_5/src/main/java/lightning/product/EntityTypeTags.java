/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.TagContainer;
import lightning.product.E_2561_m;
import lightning.product.StaticTagHelper;
import lightning.product.O_4030_c;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.t_5_h;

public final class EntityTypeTags {
    protected static final StaticTagHelper<t_5_h<?>> n_1700_B = O_4030_c.n_1700_B(new g_2336_b("entity_type"), TagContainer::G_564_y);
    public static final r_109_r.J_1907_R<t_5_h<?>> J_1907_R = EntityTypeTags.n_1700_B("skeletons");
    public static final r_109_r.J_1907_R<t_5_h<?>> R_4764_Y = EntityTypeTags.n_1700_B("raiders");
    public static final r_109_r.J_1907_R<t_5_h<?>> G_564_y = EntityTypeTags.n_1700_B("beehive_inhabitors");
    public static final r_109_r.J_1907_R<t_5_h<?>> P_1922_E = EntityTypeTags.n_1700_B("arrows");
    public static final r_109_r.J_1907_R<t_5_h<?>> u_1723_Y = EntityTypeTags.n_1700_B("impact_projectiles");

    private static r_109_r.J_1907_R<t_5_h<?>> n_1700_B(String id) {
        return n_1700_B.n_1700_B(id);
    }

    public static E_2561_m<t_5_h<?>> n_1700_B() {
        return n_1700_B.J_1907_R();
    }

    public static List<? extends r_109_r.J_1907_R<t_5_h<?>>> J_1907_R() {
        return n_1700_B.R_4764_Y();
    }
}


