/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  org.apache.commons.io.IOUtils
 */
package lightning.product;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.r_2139_P;
import lightning.product.ResourcePackFileNotFoundException;
import org.apache.commons.io.IOUtils;

public class H_3999_U
extends r_2139_P {
    public static final Splitter n_1700_B = Splitter.on((char)'/').omitEmptyStrings().limit(3);
    private ZipFile J_1907_R;

    public H_3999_U(File fileIn) {
        super(fileIn);
    }

    private ZipFile n_1700_B() throws IOException {
        if (this.J_1907_R == null) {
            this.J_1907_R = new ZipFile(this.file);
        }
        return this.J_1907_R;
    }

    @Override
    protected InputStream getInputStream(String resourcePath) throws IOException {
        ZipFile zipfile = this.n_1700_B();
        ZipEntry zipentry = zipfile.getEntry(resourcePath);
        if (zipentry == null) {
            throw new ResourcePackFileNotFoundException(this.file, resourcePath);
        }
        return zipfile.getInputStream(zipentry);
    }

    @Override
    public boolean resourceExists(String resourcePath) {
        try {
            return this.n_1700_B().getEntry(resourcePath) != null;
        }
        catch (IOException ioexception) {
            return false;
        }
    }

    @Override
    public Set<String> getResourceNamespaces(i_4221_J type) {
        ZipFile zipfile;
        try {
            zipfile = this.n_1700_B();
        }
        catch (IOException ioexception) {
            return Collections.emptySet();
        }
        Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
        HashSet set = Sets.newHashSet();
        while (enumeration.hasMoreElements()) {
            ArrayList list;
            ZipEntry zipentry = enumeration.nextElement();
            String s = zipentry.getName();
            if (!s.startsWith(type.n_1700_B() + "/") || (list = Lists.newArrayList((Iterable)n_1700_B.split((CharSequence)s))).size() <= 1) continue;
            String s1 = (String)list.get(1);
            if (s1.equals(s1.toLowerCase(Locale.ROOT))) {
                set.add(s1);
                continue;
            }
            this.onIgnoreNonLowercaseNamespace(s1);
        }
        return set;
    }

    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    @Override
    public void close() {
        if (this.J_1907_R != null) {
            IOUtils.closeQuietly((Closeable)this.J_1907_R);
            this.J_1907_R = null;
        }
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        ZipFile zipfile;
        try {
            zipfile = this.n_1700_B();
        }
        catch (IOException ioexception) {
            return Collections.emptySet();
        }
        Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
        ArrayList list = Lists.newArrayList();
        String s = type.n_1700_B() + "/" + namespaceIn + "/";
        String s1 = s + pathIn + "/";
        while (enumeration.hasMoreElements()) {
            String s3;
            String[] astring;
            String s2;
            ZipEntry zipentry = enumeration.nextElement();
            if (zipentry.isDirectory() || (s2 = zipentry.getName()).endsWith(".mcmeta") || !s2.startsWith(s1) || (astring = (s3 = s2.substring(s.length())).split("/")).length < maxDepthIn + 1 || !filterIn.test(astring[astring.length - 1])) continue;
            list.add(new g_2336_b(namespaceIn, s3));
        }
        return list;
    }
}


