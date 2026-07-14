/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.datafixers.util.Pair
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import lightning.product.CommandSource;
import lightning.product.E_2561_m;
import lightning.product.PreparableReloadListener;
import lightning.product.Resource;
import lightning.product.P_3504_Q;
import lightning.product.ResourceManager;
import lightning.product.U_2871_b;
import lightning.product.ProfilerFiller;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.r_3448_Z;
import lightning.product.s_1340_R;
import lightning.product.y_2498_m;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_916_l
implements PreparableReloadListener {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final int J_1907_R = "functions/".length();
    private static final int R_4764_Y = ".mcfunction".length();
    private volatile Map<g_2336_b, r_3448_Z> G_564_y = ImmutableMap.of();
    private final s_1340_R<r_3448_Z> P_1922_E = new s_1340_R(this::n_1700_B, "tags/functions", "function");
    private volatile E_2561_m<r_3448_Z> u_1723_Y = E_2561_m.R_4764_Y();
    private final int v_4262_N;
    private final CommandDispatcher<y_2498_m> w_1484_f;

    public Optional<r_3448_Z> n_1700_B(g_2336_b p_240940_1_) {
        return Optional.ofNullable(this.G_564_y.get(p_240940_1_));
    }

    public Map<g_2336_b, r_3448_Z> J_1907_R() {
        return this.G_564_y;
    }

    public E_2561_m<r_3448_Z> R_4764_Y() {
        return this.u_1723_Y;
    }

    public r_109_r<r_3448_Z> J_1907_R(g_2336_b p_240943_1_) {
        return this.u_1723_Y.J_1907_R(p_240943_1_);
    }

    public n_916_l(int p_i232596_1_, CommandDispatcher<y_2498_m> p_i232596_2_) {
        this.v_4262_N = p_i232596_1_;
        this.w_1484_f = p_i232596_2_;
    }

    @Override
    public CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> completablefuture = this.P_1922_E.n_1700_B(resourceManager, backgroundExecutor);
        CompletionStage completablefuture1 = CompletableFuture.supplyAsync(() -> resourceManager.n_1700_B("functions", p_240938_0_ -> p_240938_0_.endsWith(".mcfunction")), backgroundExecutor).thenCompose(p_240933_3_ -> {
            HashMap map = Maps.newHashMap();
            y_2498_m commandsource = new y_2498_m(CommandSource.T_3594_S, e_2866_D.n_1700_B, P_3504_Q.n_1700_B, null, this.v_4262_N, "", U_2871_b.R_4764_Y, null, null);
            for (g_2336_b resourcelocation : p_240933_3_) {
                String s = resourcelocation.J_1907_R();
                g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), s.substring(J_1907_R, s.length() - R_4764_Y));
                map.put(resourcelocation1, CompletableFuture.supplyAsync(() -> {
                    List<String> list = n_916_l.n_1700_B(resourceManager, resourcelocation);
                    return r_3448_Z.n_1700_B(resourcelocation1, this.w_1484_f, commandsource, list);
                }, backgroundExecutor));
            }
            CompletableFuture[] completablefuture2 = map.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completablefuture2).handle((p_240939_1_, p_240939_2_) -> map);
        });
        return ((CompletableFuture)((CompletableFuture)completablefuture.thenCombine(completablefuture1, Pair::of)).thenCompose(stage::markCompleteAwaitingOthers)).thenAcceptAsync(p_240937_1_ -> {
            Map map = (Map)p_240937_1_.getSecond();
            ImmutableMap.Builder builder = ImmutableMap.builder();
            map.forEach((p_240936_1_, p_240936_2_) -> ((CompletableFuture)p_240936_2_.handle((p_240941_2_, p_240941_3_) -> {
                if (p_240941_3_ != null) {
                    n_1700_B.error("Failed to load function {}", p_240936_1_, p_240941_3_);
                } else {
                    builder.put(p_240936_1_, p_240941_2_);
                }
                return null;
            })).join());
            this.G_564_y = builder.build();
            this.u_1723_Y = this.P_1922_E.n_1700_B((Map)p_240937_1_.getFirst());
        }, gameExecutor);
    }

    private static List<String> n_1700_B(ResourceManager p_240934_0_, g_2336_b p_240934_1_) {
        List list;
        block8: {
            Resource iresource = p_240934_0_.n_1700_B(p_240934_1_);
            try {
                list = IOUtils.readLines((InputStream)iresource.J_1907_R(), (Charset)StandardCharsets.UTF_8);
                if (iresource == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (iresource != null) {
                        try {
                            iresource.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (IOException ioexception) {
                    throw new CompletionException(ioexception);
                }
            }
            iresource.close();
        }
        return list;
    }
}


