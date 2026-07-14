/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.T_335_n;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;

public interface PackResources
extends AutoCloseable {
    public InputStream getRootResourceStream(String var1) throws IOException;

    public InputStream getResourceStream(i_4221_J var1, g_2336_b var2) throws IOException;

    public Collection<g_2336_b> getAllResourceLocations(i_4221_J var1, String var2, String var3, int var4, Predicate<String> var5);

    public boolean resourceExists(i_4221_J var1, g_2336_b var2);

    public Set<String> getResourceNamespaces(i_4221_J var1);

    @Nullable
    public <T> T getMetadata(T_335_n<T> var1) throws IOException;

    public String getName();

    @Override
    public void close();
}


