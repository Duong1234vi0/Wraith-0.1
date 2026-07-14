/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.S_4169_p;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.x_1356_s;

public class c_817_f
extends S_4169_p {
    private final x_1356_s G_564_y;

    public c_817_f(x_1356_s p_i48115_1_) {
        super("minecraft", "realms", "particular", "maseffects");
        this.G_564_y = p_i48115_1_;
    }

    @Override
    @Nullable
    protected InputStream n_1700_B(i_4221_J type, g_2336_b location) {
        File file1;
        if (type == i_4221_J.n_1700_B && (file1 = this.G_564_y.n_1700_B(location)) != null && file1.exists()) {
            try {
                return new FileInputStream(file1);
            }
            catch (FileNotFoundException fileNotFoundException) {
                // empty catch block
            }
        }
        return super.n_1700_B(type, location);
    }

    @Override
    public boolean resourceExists(i_4221_J type, g_2336_b location) {
        File file1;
        if (type == i_4221_J.n_1700_B && (file1 = this.G_564_y.n_1700_B(location)) != null && file1.exists()) {
            return true;
        }
        return super.resourceExists(type, location);
    }

    @Override
    @Nullable
    protected InputStream n_1700_B(String pathIn) {
        File file1 = this.G_564_y.n_1700_B(pathIn);
        if (file1 != null && file1.exists()) {
            try {
                return new FileInputStream(file1);
            }
            catch (FileNotFoundException fileNotFoundException) {
                // empty catch block
            }
        }
        return super.n_1700_B(pathIn);
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        Collection<g_2336_b> collection = super.getAllResourceLocations(type, namespaceIn, pathIn, maxDepthIn, filterIn);
        collection.addAll(this.G_564_y.n_1700_B(pathIn, namespaceIn, maxDepthIn, filterIn));
        return collection;
    }
}

