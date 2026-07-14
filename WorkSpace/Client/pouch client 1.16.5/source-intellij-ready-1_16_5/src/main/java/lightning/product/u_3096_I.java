/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.TestFunction;
import lightning.product.e_3591_l;

public class u_3096_I {
    private static final Collection<TestFunction> n_1700_B = Lists.newArrayList();
    private static final Set<String> J_1907_R = Sets.newHashSet();
    private static final Map<String, Consumer<e_3591_l>> R_4764_Y = Maps.newHashMap();
    private static final Collection<TestFunction> G_564_y = Sets.newHashSet();

    public static Collection<TestFunction> n_1700_B(String p_229530_0_) {
        return n_1700_B.stream().filter(p_229535_1_ -> u_3096_I.n_1700_B(p_229535_1_, p_229530_0_)).collect(Collectors.toList());
    }

    public static Collection<TestFunction> n_1700_B() {
        return n_1700_B;
    }

    public static Collection<String> J_1907_R() {
        return J_1907_R;
    }

    public static boolean J_1907_R(String p_229534_0_) {
        return J_1907_R.contains(p_229534_0_);
    }

    @Nullable
    public static Consumer<e_3591_l> R_4764_Y(String p_229536_0_) {
        return R_4764_Y.get(p_229536_0_);
    }

    public static Optional<TestFunction> G_564_y(String p_229537_0_) {
        return u_3096_I.n_1700_B().stream().filter(p_229531_1_ -> p_229531_1_.n_1700_B().equalsIgnoreCase(p_229537_0_)).findFirst();
    }

    public static TestFunction P_1922_E(String p_229538_0_) {
        Optional<TestFunction> optional = u_3096_I.G_564_y(p_229538_0_);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Can't find the test function for " + p_229538_0_);
        }
        return optional.get();
    }

    private static boolean n_1700_B(TestFunction p_229532_0_, String p_229532_1_) {
        return p_229532_0_.n_1700_B().toLowerCase().startsWith(p_229532_1_.toLowerCase() + ".");
    }

    public static Collection<TestFunction> R_4764_Y() {
        return G_564_y;
    }

    public static void n_1700_B(TestFunction p_240548_0_) {
        G_564_y.add(p_240548_0_);
    }

    public static void G_564_y() {
        G_564_y.clear();
    }
}


