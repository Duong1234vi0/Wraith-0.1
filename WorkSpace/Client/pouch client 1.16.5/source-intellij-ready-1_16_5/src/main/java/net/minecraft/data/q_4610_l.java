/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import lightning.product.F_491_v;
import lightning.product.U_2912_j;
import lightning.product.a_2886_t;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import net.minecraft.data.n_3318_d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class q_4610_l
implements n_3318_d.n_1700_B {
    private static final Logger n_1700_B = LogManager.getLogger();

    @Override
    public U_2912_j n_1700_B(String p_225371_1_, U_2912_j p_225371_2_) {
        return p_225371_1_.startsWith("data/minecraft/structures/") ? q_4610_l.J_1907_R(p_225371_1_, q_4610_l.n_1700_B(p_225371_2_)) : p_225371_2_;
    }

    private static U_2912_j n_1700_B(U_2912_j nbt) {
        if (!nbt.R_4764_Y("DataVersion", 99)) {
            nbt.J_1907_R("DataVersion", 500);
        }
        return nbt;
    }

    private static U_2912_j J_1907_R(String name, U_2912_j nbt) {
        a_2886_t template = new a_2886_t();
        int i = nbt.w_1484_f("DataVersion");
        int j = 2532;
        if (i < 2532) {
            n_1700_B.warn("SNBT Too old, do not forget to update: " + i + " < 2532: " + name);
        }
        U_2912_j compoundnbt = n_3832_I.n_1700_B(F_491_v.n_1700_B(), o_1967_f.u_1723_Y, nbt, i);
        template.J_1907_R(compoundnbt);
        return template.n_1700_B(new U_2912_j());
    }
}

