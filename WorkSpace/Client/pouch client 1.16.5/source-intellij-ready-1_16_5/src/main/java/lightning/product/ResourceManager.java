/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.Resource;
import lightning.product.PackResources;
import lightning.product.g_2336_b;

public interface ResourceManager {
    public Set<String> n_1700_B();

    public Resource n_1700_B(g_2336_b var1) throws IOException;

    public boolean J_1907_R(g_2336_b var1);

    public List<Resource> R_4764_Y(g_2336_b var1) throws IOException;

    public Collection<g_2336_b> n_1700_B(String var1, Predicate<String> var2);

    public Stream<PackResources> J_1907_R();

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements ResourceManager {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] J_1907_R;

        public static n_1700_B[] values() {
            return (n_1700_B[])J_1907_R.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        @Override
        public Set<String> n_1700_B() {
            return ImmutableSet.of();
        }

        @Override
        public Resource n_1700_B(g_2336_b resourceLocationIn) throws IOException {
            throw new FileNotFoundException(resourceLocationIn.toString());
        }

        @Override
        public boolean J_1907_R(g_2336_b path) {
            return false;
        }

        @Override
        public List<Resource> R_4764_Y(g_2336_b resourceLocationIn) {
            return ImmutableList.of();
        }

        @Override
        public Collection<g_2336_b> n_1700_B(String pathIn, Predicate<String> filter) {
            return ImmutableSet.of();
        }

        @Override
        public Stream<PackResources> J_1907_R() {
            return Stream.of(new PackResources[0]);
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B};
        }

        static {
            J_1907_R = lightning.product.ResourceManager$n_1700_B.R_4764_Y();
        }
    }
}


