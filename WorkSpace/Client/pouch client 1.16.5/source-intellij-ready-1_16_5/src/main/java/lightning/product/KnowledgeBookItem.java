/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Optional;
import lightning.product.G_3474_H;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.x_1688_C;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KnowledgeBookItem
extends q_1613_l {
    private static final Logger n_1700_B = LogManager.getLogger();

    public KnowledgeBookItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        U_2912_j compoundnbt = itemstack.Q_4569_t();
        if (!playerIn.C_415_h.G_564_y) {
            playerIn.n_1700_B(handIn, Z_1993_T.J_1907_R);
        }
        if (compoundnbt != null && compoundnbt.R_4764_Y("Recipes", 9)) {
            if (!worldIn.Y_259_p) {
                q_2896_o listnbt = compoundnbt.G_564_y("Recipes", 8);
                ArrayList list = Lists.newArrayList();
                G_3474_H recipemanager = worldIn.T_2506_i().ValueObject();
                for (int i = 0; i < listnbt.size(); ++i) {
                    String s = listnbt.t_148_a(i);
                    Optional<Recipe<?>> optional = recipemanager.n_1700_B(new g_2336_b(s));
                    if (!optional.isPresent()) {
                        n_1700_B.error("Invalid recipe: {}", (Object)s);
                        return InteractionResultHolder.G_564_y(itemstack);
                    }
                    list.add(optional.get());
                }
                playerIn.J_1907_R(list);
                playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
            }
            return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
        }
        n_1700_B.error("Tag not valid: {}", (Object)compoundnbt);
        return InteractionResultHolder.G_564_y(itemstack);
    }
}


