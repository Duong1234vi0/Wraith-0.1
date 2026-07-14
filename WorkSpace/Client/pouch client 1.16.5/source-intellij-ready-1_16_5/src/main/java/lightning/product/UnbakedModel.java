/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.ModelState;

public interface UnbakedModel {
    public Collection<g_2336_b> P_1922_E();

    public Collection<T_2910_P> n_1700_B(Function<g_2336_b, UnbakedModel> var1, Set<Pair<String, String>> var2);

    @Nullable
    public S_3826_o n_1700_B(g_2561_p var1, Function<T_2910_P, B_3871_I> var2, ModelState var3, g_2336_b var4);
}


