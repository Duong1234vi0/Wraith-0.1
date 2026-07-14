/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.RecipeCollection;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.ResourceManager;
import lightning.product.Z_1993_T;
import lightning.product.MutableSearchTree;

public class SearchRegistry
implements ResourceManagerReloadListener {
    public static final n_1700_B<Z_1993_T> n_1700_B = new n_1700_B();
    public static final n_1700_B<Z_1993_T> J_1907_R = new n_1700_B();
    public static final n_1700_B<RecipeCollection> R_4764_Y = new n_1700_B();
    private final Map<n_1700_B<?>, MutableSearchTree<?>> G_564_y = Maps.newHashMap();

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        for (MutableSearchTree<?> imutablesearchtree : this.G_564_y.values()) {
            imutablesearchtree.J_1907_R();
        }
    }

    public <T> void n_1700_B(n_1700_B<T> key, MutableSearchTree<T> value) {
        this.G_564_y.put(key, value);
    }

    public <T> MutableSearchTree<T> n_1700_B(n_1700_B<T> key) {
        return this.G_564_y.get(key);
    }

    public static class n_1700_B<T> {
    }
}


