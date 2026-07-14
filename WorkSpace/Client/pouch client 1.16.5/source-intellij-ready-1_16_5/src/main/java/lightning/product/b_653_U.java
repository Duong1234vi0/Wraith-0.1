/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.G_401_F;
import lightning.product.PreparableReloadListener;
import lightning.product.Resource;
import lightning.product.ReloadableResourceManager;
import lightning.product.ResourceManager;
import lightning.product.S_2259_B;
import lightning.product.X_1446_C;
import lightning.product.PackResources;
import lightning.product.ReloadInstance;
import lightning.product.g_2336_b;
import lightning.product.h_3939_r;
import lightning.product.i_4221_J;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class b_653_U
implements ReloadableResourceManager {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<String, h_3939_r> J_1907_R = Maps.newHashMap();
    private final List<PreparableReloadListener> R_4764_Y = Lists.newArrayList();
    private final List<PreparableReloadListener> G_564_y = Lists.newArrayList();
    private final Set<String> P_1922_E = Sets.newLinkedHashSet();
    private final List<PackResources> u_1723_Y = Lists.newArrayList();
    private final i_4221_J v_4262_N;

    public b_653_U(i_4221_J type) {
        this.v_4262_N = type;
    }

    public void n_1700_B(PackResources resourcePack) {
        this.u_1723_Y.add(resourcePack);
        for (String s : resourcePack.getResourceNamespaces(this.v_4262_N)) {
            this.P_1922_E.add(s);
            h_3939_r fallbackresourcemanager = this.J_1907_R.get(s);
            if (fallbackresourcemanager == null) {
                fallbackresourcemanager = new h_3939_r(this.v_4262_N, s);
                this.J_1907_R.put(s, fallbackresourcemanager);
            }
            fallbackresourcemanager.n_1700_B(resourcePack);
        }
    }

    @Override
    public Set<String> n_1700_B() {
        return this.P_1922_E;
    }

    @Override
    public Resource n_1700_B(g_2336_b resourceLocationIn) throws IOException {
        ResourceManager iresourcemanager = this.J_1907_R.get(resourceLocationIn.R_4764_Y());
        if (iresourcemanager != null) {
            return iresourcemanager.n_1700_B(resourceLocationIn);
        }
        throw new FileNotFoundException(resourceLocationIn.toString());
    }

    @Override
    public boolean J_1907_R(g_2336_b path) {
        ResourceManager iresourcemanager = this.J_1907_R.get(path.R_4764_Y());
        return iresourcemanager != null ? iresourcemanager.J_1907_R(path) : false;
    }

    @Override
    public List<Resource> R_4764_Y(g_2336_b resourceLocationIn) throws IOException {
        ResourceManager iresourcemanager = this.J_1907_R.get(resourceLocationIn.R_4764_Y());
        if (iresourcemanager != null) {
            return iresourcemanager.R_4764_Y(resourceLocationIn);
        }
        throw new FileNotFoundException(resourceLocationIn.toString());
    }

    @Override
    public Collection<g_2336_b> n_1700_B(String pathIn, Predicate<String> filter) {
        HashSet set = Sets.newHashSet();
        for (h_3939_r fallbackresourcemanager : this.J_1907_R.values()) {
            set.addAll(fallbackresourcemanager.n_1700_B(pathIn, filter));
        }
        ArrayList list = Lists.newArrayList((Iterable)set);
        Collections.sort(list);
        return list;
    }

    private void R_4764_Y() {
        this.J_1907_R.clear();
        this.P_1922_E.clear();
        this.u_1723_Y.forEach(PackResources::close);
        this.u_1723_Y.clear();
    }

    @Override
    public void close() {
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(PreparableReloadListener listener) {
        this.R_4764_Y.add(listener);
        this.G_564_y.add(listener);
    }

    protected ReloadInstance J_1907_R(Executor backgroundExecutor, Executor gameExecutor, List<PreparableReloadListener> listeners, CompletableFuture<X_1446_C> waitingFor) {
        G_401_F iasyncreloader = n_1700_B.isDebugEnabled() ? new G_401_F(this, Lists.newArrayList(listeners), backgroundExecutor, gameExecutor, waitingFor) : S_2259_B.n_1700_B(this, Lists.newArrayList(listeners), backgroundExecutor, gameExecutor, waitingFor);
        this.G_564_y.clear();
        return iasyncreloader;
    }

    @Override
    public ReloadInstance n_1700_B(Executor backgroundExecutor, Executor gameExecutor, CompletableFuture<X_1446_C> waitingFor, List<PackResources> resourcePacks) {
        this.R_4764_Y();
        n_1700_B.info("Reloading ResourceManager: {}", new Supplier[]{() -> resourcePacks.stream().map(PackResources::getName).collect(Collectors.joining(", "))});
        for (PackResources iresourcepack : resourcePacks) {
            try {
                this.n_1700_B(iresourcepack);
            }
            catch (Exception exception) {
                n_1700_B.error("Failed to add resource pack {}", (Object)iresourcepack.getName(), (Object)exception);
                return new J_1907_R(new n_1700_B(iresourcepack, (Throwable)exception));
            }
        }
        return this.J_1907_R(backgroundExecutor, gameExecutor, this.R_4764_Y, waitingFor);
    }

    @Override
    public Stream<PackResources> J_1907_R() {
        return this.u_1723_Y.stream();
    }

    static class J_1907_R
    implements ReloadInstance {
        private final n_1700_B n_1700_B;
        private final CompletableFuture<X_1446_C> J_1907_R;

        public J_1907_R(n_1700_B exception) {
            this.n_1700_B = exception;
            this.J_1907_R = new CompletableFuture();
            this.J_1907_R.completeExceptionally(exception);
        }

        @Override
        public CompletableFuture<X_1446_C> n_1700_B() {
            return this.J_1907_R;
        }

        @Override
        public float J_1907_R() {
            return 0.0f;
        }

        @Override
        public boolean R_4764_Y() {
            return false;
        }

        @Override
        public boolean G_564_y() {
            return true;
        }

        @Override
        public void P_1922_E() {
            throw this.n_1700_B;
        }
    }

    public static class n_1700_B
    extends RuntimeException {
        private final PackResources n_1700_B;

        public n_1700_B(PackResources pack, Throwable throwable) {
            super(pack.getName(), throwable);
            this.n_1700_B = pack;
        }

        public PackResources n_1700_B() {
            return this.n_1700_B;
        }
    }
}


