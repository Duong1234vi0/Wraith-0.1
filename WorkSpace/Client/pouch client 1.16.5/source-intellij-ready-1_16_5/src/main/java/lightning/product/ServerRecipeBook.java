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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import lightning.product.B_4088_l;
import lightning.product.G_3474_H;
import lightning.product.StringTag;
import lightning.product.U_1907_s;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.RecipeBookSettings;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.ClientboundRecipePacket;
import lightning.product.q_2896_o;
import lightning.product.s_3109_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerRecipeBook
extends U_1907_s {
    private static final Logger R_4764_Y = LogManager.getLogger();

    public int n_1700_B(Collection<Recipe<?>> recipes, B_4088_l player) {
        ArrayList list = Lists.newArrayList();
        int i = 0;
        for (Recipe<?> irecipe : recipes) {
            g_2336_b resourcelocation = irecipe.u_1723_Y();
            if (this.n_1700_B.contains(resourcelocation) || irecipe.t_148_a()) continue;
            this.n_1700_B(resourcelocation);
            this.G_564_y(resourcelocation);
            list.add(resourcelocation);
            U_3554_Q.u_1723_Y.n_1700_B(player, irecipe);
            ++i;
        }
        this.n_1700_B(ClientboundRecipePacket.n_1700_B.J_1907_R, player, list);
        return i;
    }

    public int J_1907_R(Collection<Recipe<?>> recipes, B_4088_l player) {
        ArrayList list = Lists.newArrayList();
        int i = 0;
        for (Recipe<?> irecipe : recipes) {
            g_2336_b resourcelocation = irecipe.u_1723_Y();
            if (!this.n_1700_B.contains(resourcelocation)) continue;
            this.R_4764_Y(resourcelocation);
            list.add(resourcelocation);
            ++i;
        }
        this.n_1700_B(ClientboundRecipePacket.n_1700_B.R_4764_Y, player, list);
        return i;
    }

    private void n_1700_B(ClientboundRecipePacket.n_1700_B state, B_4088_l player, List<g_2336_b> recipesIn) {
        player.n_1700_B.n_1700_B(new ClientboundRecipePacket(state, recipesIn, Collections.emptyList(), this.J_1907_R()));
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        this.J_1907_R().J_1907_R(compoundnbt);
        q_2896_o listnbt = new q_2896_o();
        for (g_2336_b resourcelocation : this.n_1700_B) {
            listnbt.add(StringTag.n_1700_B(resourcelocation.toString()));
        }
        compoundnbt.n_1700_B("recipes", listnbt);
        q_2896_o listnbt1 = new q_2896_o();
        for (g_2336_b resourcelocation1 : this.J_1907_R) {
            listnbt1.add(StringTag.n_1700_B(resourcelocation1.toString()));
        }
        compoundnbt.n_1700_B("toBeDisplayed", listnbt1);
        return compoundnbt;
    }

    public void n_1700_B(U_2912_j tag, G_3474_H recipeManager) {
        this.n_1700_B(RecipeBookSettings.n_1700_B(tag));
        q_2896_o listnbt = tag.G_564_y("recipes", 8);
        this.n_1700_B(listnbt, this::n_1700_B, recipeManager);
        q_2896_o listnbt1 = tag.G_564_y("toBeDisplayed", 8);
        this.n_1700_B(listnbt1, this::u_1723_Y, recipeManager);
    }

    private void n_1700_B(q_2896_o nbtList, Consumer<Recipe<?>> recipeConsumer, G_3474_H recipeManager) {
        for (int i = 0; i < nbtList.size(); ++i) {
            String s = nbtList.t_148_a(i);
            try {
                g_2336_b resourcelocation = new g_2336_b(s);
                Optional<Recipe<?>> optional = recipeManager.n_1700_B(resourcelocation);
                if (!optional.isPresent()) {
                    R_4764_Y.error("Tried to load unrecognized recipe: {} removed now.", (Object)resourcelocation);
                    continue;
                }
                recipeConsumer.accept(optional.get());
                continue;
            }
            catch (s_3109_F resourcelocationexception) {
                R_4764_Y.error("Tried to load improperly formatted recipe: {} removed now.", (Object)s);
            }
        }
    }

    public void n_1700_B(B_4088_l player) {
        player.n_1700_B.n_1700_B(new ClientboundRecipePacket(ClientboundRecipePacket.n_1700_B.n_1700_B, this.n_1700_B, this.J_1907_R, this.J_1907_R()));
    }
}


