/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashBasedTable
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.RecipeCollection;
import lightning.product.S_1134_u;
import lightning.product.U_1907_s;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.n_4974_X;
import lightning.product.v_1669_V;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class c_1070_s
extends U_1907_s {
    private static final Logger R_4764_Y = LogManager.getLogger();
    private Map<n_4974_X, List<RecipeCollection>> G_564_y = ImmutableMap.of();
    private List<RecipeCollection> P_1922_E = ImmutableList.of();

    public void n_1700_B(Iterable<Recipe<?>> p_243196_1_) {
        Map<n_4974_X, List<List<Recipe<?>>>> map = c_1070_s.J_1907_R(p_243196_1_);
        HashMap map1 = Maps.newHashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        map.forEach((p_243197_2_, p_243197_3_) -> {
            List list = map1.put(p_243197_2_, (List)p_243197_3_.stream().map(RecipeCollection::new).peek(arg_0 -> ((ImmutableList.Builder)builder).add(arg_0)).collect(ImmutableList.toImmutableList()));
        });
        n_4974_X.C_2741_M.forEach((p_243199_1_, p_243199_2_) -> {
            List list = map1.put(p_243199_1_, (List)p_243199_2_.stream().flatMap(p_243198_1_ -> ((List)map1.getOrDefault(p_243198_1_, ImmutableList.of())).stream()).collect(ImmutableList.toImmutableList()));
        });
        this.G_564_y = ImmutableMap.copyOf((Map)map1);
        this.P_1922_E = builder.build();
    }

    private static Map<n_4974_X, List<List<Recipe<?>>>> J_1907_R(Iterable<Recipe<?>> p_243201_0_) {
        HashMap map = Maps.newHashMap();
        HashBasedTable table = HashBasedTable.create();
        for (Recipe<?> irecipe : p_243201_0_) {
            if (irecipe.t_148_a()) continue;
            n_4974_X recipebookcategories = c_1070_s.v_4262_N(irecipe);
            String s = irecipe.G_564_y();
            if (s.isEmpty()) {
                map.computeIfAbsent(recipebookcategories, p_243202_0_ -> Lists.newArrayList()).add(ImmutableList.of(irecipe));
                continue;
            }
            List list = (List)table.get((Object)recipebookcategories, (Object)s);
            if (list == null) {
                list = Lists.newArrayList();
                table.put((Object)recipebookcategories, (Object)s, (Object)list);
                map.computeIfAbsent(recipebookcategories, p_202890_0_ -> Lists.newArrayList()).add(list);
            }
            list.add(irecipe);
        }
        return map;
    }

    private static n_4974_X v_4262_N(Recipe<?> recipe) {
        RecipeType<?> irecipetype = recipe.v_4262_N();
        if (irecipetype == RecipeType.n_1700_B) {
            Z_1993_T itemstack = recipe.R_4764_Y();
            S_1134_u itemgroup = itemstack.J_1907_R().w_1457_N();
            if (itemgroup == S_1134_u.J_1907_R) {
                return n_4974_X.J_1907_R;
            }
            if (itemgroup != S_1134_u.t_148_a && itemgroup != S_1134_u.s_956_w) {
                return itemgroup == S_1134_u.G_564_y ? n_4974_X.R_4764_Y : n_4974_X.P_1922_E;
            }
            return n_4974_X.G_564_y;
        }
        if (irecipetype == RecipeType.J_1907_R) {
            if (recipe.R_4764_Y().J_1907_R().Y_259_p()) {
                return n_4974_X.v_4262_N;
            }
            return recipe.R_4764_Y().J_1907_R() instanceof v_1669_V ? n_4974_X.w_1484_f : n_4974_X.t_148_a;
        }
        if (irecipetype == RecipeType.R_4764_Y) {
            return recipe.R_4764_Y().J_1907_R() instanceof v_1669_V ? n_4974_X.u_2550_I : n_4974_X.M_588_G;
        }
        if (irecipetype == RecipeType.G_564_y) {
            return n_4974_X.h_1847_R;
        }
        if (irecipetype == RecipeType.u_1723_Y) {
            return n_4974_X.Q_4569_t;
        }
        if (irecipetype == RecipeType.P_1922_E) {
            return n_4974_X.t_1786_h;
        }
        if (irecipetype == RecipeType.v_4262_N) {
            return n_4974_X.M_182_A;
        }
        Supplier[] supplierArray = new Supplier[2];
        supplierArray[0] = () -> V_3137_a.D_4792_h.J_1907_R(recipe.v_4262_N());
        supplierArray[1] = recipe::u_1723_Y;
        R_4764_Y.warn("Unknown recipe category: {}/{}", supplierArray);
        return n_4974_X.multiplayerClientSuggestionProvider;
    }

    public List<RecipeCollection> n_1700_B() {
        return this.P_1922_E;
    }

    public List<RecipeCollection> n_1700_B(n_4974_X p_202891_1_) {
        return this.G_564_y.getOrDefault((Object)p_202891_1_, Collections.emptyList());
    }
}


