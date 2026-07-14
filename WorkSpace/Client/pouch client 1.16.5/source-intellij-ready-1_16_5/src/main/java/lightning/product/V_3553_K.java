/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 */
package lightning.product;

import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import lightning.product.TagContainer;
import lightning.product.E_2561_m;
import lightning.product.PreparableReloadListener;
import lightning.product.O_4030_c;
import lightning.product.ResourceManager;
import lightning.product.T_2915_h;
import lightning.product.SerializationTags;
import lightning.product.V_3137_a;
import lightning.product.ProfilerFiller;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.r_109_r;
import lightning.product.s_1340_R;
import lightning.product.Fluid;
import lightning.product.t_5_h;

public class V_3553_K
implements PreparableReloadListener {
    private final s_1340_R<T_2915_h> n_1700_B = new s_1340_R(V_3137_a.q_4610_l::J_1907_R, "tags/blocks", "block");
    private final s_1340_R<q_1613_l> J_1907_R = new s_1340_R(V_3137_a.e_2887_G::J_1907_R, "tags/items", "item");
    private final s_1340_R<Fluid> R_4764_Y = new s_1340_R(V_3137_a.G_624_v::J_1907_R, "tags/fluids", "fluid");
    private final s_1340_R<t_5_h<?>> G_564_y = new s_1340_R(V_3137_a.g_221_o::J_1907_R, "tags/entity_types", "entity_type");
    private TagContainer P_1922_E = TagContainer.n_1700_B;

    public TagContainer J_1907_R() {
        return this.P_1922_E;
    }

    @Override
    public CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> completablefuture = this.n_1700_B.n_1700_B(resourceManager, backgroundExecutor);
        CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> completablefuture1 = this.J_1907_R.n_1700_B(resourceManager, backgroundExecutor);
        CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> completablefuture2 = this.R_4764_Y.n_1700_B(resourceManager, backgroundExecutor);
        CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> completablefuture3 = this.G_564_y.n_1700_B(resourceManager, backgroundExecutor);
        return ((CompletableFuture)CompletableFuture.allOf(completablefuture, completablefuture1, completablefuture2, completablefuture3).thenCompose(stage::markCompleteAwaitingOthers)).thenAcceptAsync(voidIn -> {
            E_2561_m<t_5_h<?>> itagcollection3;
            E_2561_m<Fluid> itagcollection2;
            E_2561_m<q_1613_l> itagcollection1;
            E_2561_m<T_2915_h> itagcollection = this.n_1700_B.n_1700_B((Map)completablefuture.join());
            TagContainer itagcollectionsupplier = TagContainer.n_1700_B(itagcollection, itagcollection1 = this.J_1907_R.n_1700_B((Map)completablefuture1.join()), itagcollection2 = this.R_4764_Y.n_1700_B((Map)completablefuture2.join()), itagcollection3 = this.G_564_y.n_1700_B((Map)completablefuture3.join()));
            Multimap<g_2336_b, g_2336_b> multimap = O_4030_c.J_1907_R(itagcollectionsupplier);
            if (!multimap.isEmpty()) {
                throw new IllegalStateException("Missing required tags: " + multimap.entries().stream().map(tags -> String.valueOf(tags.getKey()) + ":" + String.valueOf(tags.getValue())).sorted().collect(Collectors.joining(",")));
            }
            SerializationTags.n_1700_B(itagcollectionsupplier);
            this.P_1922_E = itagcollectionsupplier;
        }, gameExecutor);
    }
}


