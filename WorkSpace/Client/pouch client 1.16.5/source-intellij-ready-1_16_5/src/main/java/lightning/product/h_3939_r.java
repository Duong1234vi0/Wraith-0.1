/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.r_1328_I;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class h_3939_r
implements ResourceManager {
    private static final Logger J_1907_R = LogManager.getLogger();
    protected final List<PackResources> n_1700_B = Lists.newArrayList();
    private final i_4221_J R_4764_Y;
    private final String G_564_y;

    public h_3939_r(i_4221_J p_i226096_1_, String p_i226096_2_) {
        this.R_4764_Y = p_i226096_1_;
        this.G_564_y = p_i226096_2_;
    }

    public void n_1700_B(PackResources resourcePack) {
        this.n_1700_B.add(resourcePack);
    }

    @Override
    public Set<String> n_1700_B() {
        return ImmutableSet.of((Object)this.G_564_y);
    }

    @Override
    public Resource n_1700_B(g_2336_b resourceLocationIn) throws IOException {
        this.P_1922_E(resourceLocationIn);
        PackResources iresourcepack = null;
        g_2336_b resourcelocation = h_3939_r.G_564_y(resourceLocationIn);
        for (int i = this.n_1700_B.size() - 1; i >= 0; --i) {
            PackResources iresourcepack1 = this.n_1700_B.get(i);
            if (iresourcepack == null && iresourcepack1.resourceExists(this.R_4764_Y, resourcelocation)) {
                iresourcepack = iresourcepack1;
            }
            if (!iresourcepack1.resourceExists(this.R_4764_Y, resourceLocationIn)) continue;
            InputStream inputstream = null;
            if (iresourcepack != null) {
                inputstream = this.n_1700_B(resourcelocation, iresourcepack);
            }
            return new r_1328_I(iresourcepack1.getName(), resourceLocationIn, this.n_1700_B(resourceLocationIn, iresourcepack1), inputstream);
        }
        throw new FileNotFoundException(resourceLocationIn.toString());
    }

    @Override
    public boolean J_1907_R(g_2336_b path) {
        if (!this.u_1723_Y(path)) {
            return false;
        }
        for (int i = this.n_1700_B.size() - 1; i >= 0; --i) {
            PackResources iresourcepack = this.n_1700_B.get(i);
            if (!iresourcepack.resourceExists(this.R_4764_Y, path)) continue;
            return true;
        }
        return false;
    }

    protected InputStream n_1700_B(g_2336_b location, PackResources resourcePack) throws IOException {
        InputStream inputstream = resourcePack.getResourceStream(this.R_4764_Y, location);
        return J_1907_R.isDebugEnabled() ? new n_1700_B(inputstream, location, resourcePack.getName()) : inputstream;
    }

    private void P_1922_E(g_2336_b location) throws IOException {
        if (!this.u_1723_Y(location)) {
            throw new IOException("Invalid relative path to resource: " + String.valueOf(location));
        }
    }

    private boolean u_1723_Y(g_2336_b p_219541_1_) {
        return !p_219541_1_.J_1907_R().contains("..");
    }

    @Override
    public List<Resource> R_4764_Y(g_2336_b resourceLocationIn) throws IOException {
        this.P_1922_E(resourceLocationIn);
        ArrayList list = Lists.newArrayList();
        g_2336_b resourcelocation = h_3939_r.G_564_y(resourceLocationIn);
        for (PackResources iresourcepack : this.n_1700_B) {
            if (!iresourcepack.resourceExists(this.R_4764_Y, resourceLocationIn)) continue;
            InputStream inputstream = iresourcepack.resourceExists(this.R_4764_Y, resourcelocation) ? this.n_1700_B(resourcelocation, iresourcepack) : null;
            list.add(new r_1328_I(iresourcepack.getName(), resourceLocationIn, this.n_1700_B(resourceLocationIn, iresourcepack), inputstream));
        }
        if (list.isEmpty()) {
            throw new FileNotFoundException(resourceLocationIn.toString());
        }
        return list;
    }

    @Override
    public Collection<g_2336_b> n_1700_B(String pathIn, Predicate<String> filter) {
        ArrayList list = Lists.newArrayList();
        for (PackResources iresourcepack : this.n_1700_B) {
            list.addAll(iresourcepack.getAllResourceLocations(this.R_4764_Y, this.G_564_y, pathIn, Integer.MAX_VALUE, filter));
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public Stream<PackResources> J_1907_R() {
        return this.n_1700_B.stream();
    }

    static g_2336_b G_564_y(g_2336_b location) {
        return new g_2336_b(location.R_4764_Y(), location.J_1907_R() + ".mcmeta");
    }

    static class n_1700_B
    extends FilterInputStream {
        private final String n_1700_B;
        private boolean J_1907_R;

        public n_1700_B(InputStream inputStreamIn, g_2336_b location, String resourcePack) {
            super(inputStreamIn);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            new Exception().printStackTrace(new PrintStream(bytearrayoutputstream));
            this.n_1700_B = "Leaked resource: '" + String.valueOf(location) + "' loaded from pack: '" + resourcePack + "'\n" + String.valueOf(bytearrayoutputstream);
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.J_1907_R = true;
        }

        protected void finalize() throws Throwable {
            if (!this.J_1907_R) {
                J_1907_R.warn(this.n_1700_B);
            }
            super.finalize();
        }
    }
}


