/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_2561_m;
import lightning.product.O_4030_c;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.b_2585_i;
import lightning.product.q_1613_l;
import lightning.product.Fluid;
import lightning.product.t_5_h;

public interface TagContainer {
    public static final TagContainer n_1700_B = TagContainer.n_1700_B(E_2561_m.R_4764_Y(), E_2561_m.R_4764_Y(), E_2561_m.R_4764_Y(), E_2561_m.R_4764_Y());

    public E_2561_m<T_2915_h> n_1700_B();

    public E_2561_m<q_1613_l> J_1907_R();

    public E_2561_m<Fluid> R_4764_Y();

    public E_2561_m<t_5_h<?>> G_564_y();

    default public void P_1922_E() {
        O_4030_c.n_1700_B(this);
        a_3742_W.n_1700_B();
    }

    default public void n_1700_B(b_2585_i buffer) {
        this.n_1700_B().n_1700_B(buffer, V_3137_a.q_4610_l);
        this.J_1907_R().n_1700_B(buffer, V_3137_a.e_2887_G);
        this.R_4764_Y().n_1700_B(buffer, V_3137_a.G_624_v);
        this.G_564_y().n_1700_B(buffer, V_3137_a.g_221_o);
    }

    public static TagContainer J_1907_R(b_2585_i buffer) {
        E_2561_m<T_2915_h> itagcollection = E_2561_m.n_1700_B(buffer, V_3137_a.q_4610_l);
        E_2561_m<q_1613_l> itagcollection1 = E_2561_m.n_1700_B(buffer, V_3137_a.e_2887_G);
        E_2561_m<Fluid> itagcollection2 = E_2561_m.n_1700_B(buffer, V_3137_a.G_624_v);
        E_2561_m<t_5_h<?>> itagcollection3 = E_2561_m.n_1700_B(buffer, V_3137_a.g_221_o);
        return TagContainer.n_1700_B(itagcollection, itagcollection1, itagcollection2, itagcollection3);
    }

    public static TagContainer n_1700_B(final E_2561_m<T_2915_h> blockTags, final E_2561_m<q_1613_l> itemTags, final E_2561_m<Fluid> fluidTags, final E_2561_m<t_5_h<?>> entityTypeTags) {
        return new TagContainer(){

            @Override
            public E_2561_m<T_2915_h> n_1700_B() {
                return blockTags;
            }

            @Override
            public E_2561_m<q_1613_l> J_1907_R() {
                return itemTags;
            }

            @Override
            public E_2561_m<Fluid> R_4764_Y() {
                return fluidTags;
            }

            @Override
            public E_2561_m<t_5_h<?>> G_564_y() {
                return entityTypeTags;
            }
        };
    }
}


