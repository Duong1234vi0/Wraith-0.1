/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CharMatcher
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.commons.io.filefilter.DirectoryFileFilter
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.j_3341_s;
import lightning.product.r_2139_P;
import lightning.product.ResourcePackFileNotFoundException;
import lightning.product.s_3109_F;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_885_K
extends r_2139_P {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final boolean J_1907_R = j_3341_s.t_148_a() == j_3341_s.J_1907_R.R_4764_Y;
    private static final CharMatcher R_4764_Y = CharMatcher.is((char)'\\');

    public A_885_K(File folder) {
        super(folder);
    }

    public static boolean n_1700_B(File fileIn, String pathIn) throws IOException {
        String s = fileIn.getCanonicalPath();
        if (J_1907_R) {
            s = R_4764_Y.replaceFrom((CharSequence)s, '/');
        }
        return s.endsWith(pathIn);
    }

    @Override
    protected InputStream getInputStream(String resourcePath) throws IOException {
        File file1 = this.n_1700_B(resourcePath);
        if (file1 == null) {
            throw new ResourcePackFileNotFoundException(this.file, resourcePath);
        }
        return new FileInputStream(file1);
    }

    @Override
    protected boolean resourceExists(String resourcePath) {
        return this.n_1700_B(resourcePath) != null;
    }

    @Nullable
    private File n_1700_B(String p_195776_1_) {
        try {
            File file1 = new File(this.file, p_195776_1_);
            if (file1.isFile() && A_885_K.n_1700_B(file1, p_195776_1_)) {
                return file1;
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return null;
    }

    @Override
    public Set<String> getResourceNamespaces(i_4221_J type) {
        HashSet set = Sets.newHashSet();
        File file1 = new File(this.file, type.n_1700_B());
        File[] afile = file1.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY);
        if (afile != null) {
            for (File file2 : afile) {
                String s = A_885_K.getRelativeString(file1, file2);
                if (s.equals(s.toLowerCase(Locale.ROOT))) {
                    set.add(s.substring(0, s.length() - 1));
                    continue;
                }
                this.onIgnoreNonLowercaseNamespace(s);
            }
        }
        return set;
    }

    @Override
    public void close() {
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        File file1 = new File(this.file, type.n_1700_B());
        ArrayList list = Lists.newArrayList();
        this.n_1700_B(new File(new File(file1, namespaceIn), pathIn), maxDepthIn, namespaceIn, list, pathIn + "/", filterIn);
        return list;
    }

    private void n_1700_B(File p_199546_1_, int p_199546_2_, String p_199546_3_, List<g_2336_b> p_199546_4_, String p_199546_5_, Predicate<String> p_199546_6_) {
        File[] afile = p_199546_1_.listFiles();
        if (afile != null) {
            for (File file1 : afile) {
                if (file1.isDirectory()) {
                    if (p_199546_2_ <= 0) continue;
                    this.n_1700_B(file1, p_199546_2_ - 1, p_199546_3_, p_199546_4_, p_199546_5_ + file1.getName() + "/", p_199546_6_);
                    continue;
                }
                if (file1.getName().endsWith(".mcmeta") || !p_199546_6_.test(file1.getName())) continue;
                try {
                    p_199546_4_.add(new g_2336_b(p_199546_3_, p_199546_5_ + file1.getName()));
                }
                catch (s_3109_F resourcelocationexception) {
                    n_1700_B.error(resourcelocationexception.getMessage());
                }
            }
        }
    }
}


