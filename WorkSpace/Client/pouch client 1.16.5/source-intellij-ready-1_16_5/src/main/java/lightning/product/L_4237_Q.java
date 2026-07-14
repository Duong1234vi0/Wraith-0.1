/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.S_3779_r;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.k_4690_i;
import lightning.product.ItemOverride;
import lightning.product.o_3047_I;
import lightning.product.r_4811_B;
import lightning.product.UnbakedModel;
import net.optifine.Config;
import net.optifine.ItemOverrideCache;

public class L_4237_Q {
    public static final L_4237_Q n_1700_B = new L_4237_Q();
    private final List<ItemOverride> R_4764_Y = Lists.newArrayList();
    private final List<S_3826_o> G_564_y;
    private ItemOverrideCache P_1922_E;
    public static g_2336_b J_1907_R = null;

    private L_4237_Q() {
        this.G_564_y = Collections.emptyList();
    }

    public L_4237_Q(g_2561_p modelBakeryIn, o_3047_I blockModelIn, Function<g_2336_b, UnbakedModel> modelGetter, List<ItemOverride> itemOverridesIn) {
        this(modelBakeryIn, blockModelIn, modelGetter, modelBakeryIn.G_564_y()::n_1700_B, itemOverridesIn);
    }

    public L_4237_Q(g_2561_p p_i242113_1_, UnbakedModel p_i242113_2_, Function<g_2336_b, UnbakedModel> p_i242113_3_, Function<T_2910_P, B_3871_I> p_i242113_4_, List<ItemOverride> p_i242113_5_) {
        this.G_564_y = p_i242113_5_.stream().map(p_lambda$new$0_4_ -> {
            UnbakedModel iunbakedmodel = (UnbakedModel)p_i242113_3_.apply(p_lambda$new$0_4_.n_1700_B());
            return Objects.equals(iunbakedmodel, p_i242113_2_) ? null : p_i242113_1_.n_1700_B(p_lambda$new$0_4_.n_1700_B(), S_3779_r.n_1700_B, p_i242113_4_);
        }).collect(Collectors.toList());
        Collections.reverse(this.G_564_y);
        for (int i = p_i242113_5_.size() - 1; i >= 0; --i) {
            this.R_4764_Y.add(p_i242113_5_.get(i));
        }
        if (this.R_4764_Y.size() > 65) {
            this.P_1922_E = ItemOverrideCache.make(this.R_4764_Y);
        }
    }

    @Nullable
    public S_3826_o n_1700_B(S_3826_o model, Z_1993_T stack, @Nullable k_4690_i world, @Nullable r_4811_B livingEntity) {
        boolean flag = Config.isCustomItems();
        if (flag) {
            J_1907_R = null;
        }
        if (!this.R_4764_Y.isEmpty()) {
            Integer integer;
            if (this.P_1922_E != null && (integer = this.P_1922_E.getModelIndex(stack, world, livingEntity)) != null) {
                int j = integer;
                if (j >= 0 && j < this.G_564_y.size()) {
                    S_3826_o ibakedmodel1;
                    if (flag) {
                        J_1907_R = this.R_4764_Y.get(j).n_1700_B();
                    }
                    if ((ibakedmodel1 = this.G_564_y.get(j)) != null) {
                        return ibakedmodel1;
                    }
                }
                return model;
            }
            for (int i = 0; i < this.R_4764_Y.size(); ++i) {
                ItemOverride itemoverride = this.R_4764_Y.get(i);
                if (!itemoverride.n_1700_B(stack, world, livingEntity)) continue;
                S_3826_o ibakedmodel = this.G_564_y.get(i);
                if (flag) {
                    J_1907_R = itemoverride.n_1700_B();
                }
                if (this.P_1922_E != null) {
                    this.P_1922_E.putModelIndex(stack, world, livingEntity, i);
                }
                if (ibakedmodel == null) {
                    return model;
                }
                return ibakedmodel;
            }
            if (this.P_1922_E != null) {
                this.P_1922_E.putModelIndex(stack, world, livingEntity, ItemOverrideCache.INDEX_NONE);
            }
        }
        return model;
    }

    public ImmutableList<ItemOverride> n_1700_B() {
        return ImmutableList.copyOf(this.R_4764_Y);
    }
}


