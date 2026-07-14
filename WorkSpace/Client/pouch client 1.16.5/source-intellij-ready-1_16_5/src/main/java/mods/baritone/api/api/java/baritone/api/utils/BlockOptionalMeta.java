/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableSet
 *  io.netty.util.concurrent.ThreadPerTaskExecutor
 *  javax.annotation.Nonnull
 */
package mods.baritone.api.api.java.baritone.api.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.netty.util.concurrent.ThreadPerTaskExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lightning.product.LootContextParams;
import lightning.product.D_2103_L;
import lightning.product.PackRepository;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.T_4652_I;
import lightning.product.X_1446_C;
import lightning.product.Z_1993_T;
import lightning.product.PackResources;
import lightning.product.b_653_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.f_1402_I;
import lightning.product.f_4186_T;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.k_1471_n;
import lightning.product.o_4810_o;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.v_3760_Q;
import mods.baritone.api.api.java.baritone.api.utils.BlockUtils;
import mods.baritone.api.api.java.baritone.api.utils.accessor.IItemStack;

public final class BlockOptionalMeta {
    private static final Pattern PATTERN = Pattern.compile("^(?<id>.+?)(?:\\[(?<properties>.+?)?\\])?$");
    private final T_2915_h block;
    private final String propertiesDescription;
    private final Set<K_4074_S> blockstates;
    private final Set<Integer> stateHashes;
    private final Set<Integer> stackHashes;
    private static f_4186_T manager;
    private static k_1471_n predicate;
    private static Map<T_2915_h, List<q_1613_l>> drops;

    public BlockOptionalMeta(@Nonnull T_2915_h block) {
        this.block = block;
        this.propertiesDescription = "{}";
        this.blockstates = BlockOptionalMeta.getStates(block, Collections.emptyMap());
        this.stateHashes = BlockOptionalMeta.getStateHashes(this.blockstates);
        this.stackHashes = BlockOptionalMeta.getStackHashes(this.blockstates);
    }

    public BlockOptionalMeta(@Nonnull String selector) {
        Matcher matcher = PATTERN.matcher(selector);
        if (!matcher.find()) {
            throw new IllegalArgumentException("invalid block selector");
        }
        this.block = BlockUtils.stringToBlockRequired(matcher.group("id"));
        String props = matcher.group("properties");
        Map properties = props == null || props.equals("") ? Collections.emptyMap() : BlockOptionalMeta.parseProperties(this.block, props);
        this.propertiesDescription = props == null ? "{}" : "{" + props.replace("=", ":") + "}";
        this.blockstates = BlockOptionalMeta.getStates(this.block, properties);
        this.stateHashes = BlockOptionalMeta.getStateHashes(this.blockstates);
        this.stackHashes = BlockOptionalMeta.getStackHashes(this.blockstates);
    }

    private static <C extends Comparable<C>, P extends v_3760_Q<C>> P castToIProperty(Object value) {
        return (P)((v_3760_Q)value);
    }

    private static Map<v_3760_Q<?>, ?> parseProperties(T_2915_h block, String raw) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (String pair : raw.split(",")) {
            String[] parts = pair.split("=");
            if (parts.length != 2) {
                throw new IllegalArgumentException(String.format("\"%s\" is not a valid property-value pair", pair));
            }
            String rawKey = parts[0];
            String rawValue = parts[1];
            v_3760_Q<?> key = block.t_1786_h().n_1700_B(rawKey);
            Comparable value = (Comparable)((v_3760_Q)BlockOptionalMeta.castToIProperty(key)).J_1907_R(rawValue).orElseThrow(() -> new IllegalArgumentException(String.format("\"%s\" is not a valid value for %s on %s", rawValue, key, block)));
            builder.put(key, (Object)value);
        }
        return builder.build();
    }

    private static Set<K_4074_S> getStates(@Nonnull T_2915_h block, @Nonnull Map<v_3760_Q<?>, ?> properties) {
        return block.t_1786_h().n_1700_B().stream().filter(blockstate -> properties.entrySet().stream().allMatch(entry -> blockstate.R_4764_Y((v_3760_Q)entry.getKey()) == entry.getValue())).collect(Collectors.toSet());
    }

    private static ImmutableSet<Integer> getStateHashes(Set<K_4074_S> blockstates) {
        return ImmutableSet.copyOf((Object[])((Integer[])blockstates.stream().map(Object::hashCode).toArray(Integer[]::new)));
    }

    private static ImmutableSet<Integer> getStackHashes(Set<K_4074_S> blockstates) {
        return ImmutableSet.copyOf((Object[])((Integer[])blockstates.stream().flatMap(state -> BlockOptionalMeta.drops(state.J_1907_R()).stream().map(item -> new Z_1993_T((q_1803_e)item, 1))).map(stack -> ((IItemStack)stack).getBaritoneHash()).toArray(Integer[]::new)));
    }

    public T_2915_h getBlock() {
        return this.block;
    }

    public boolean matches(@Nonnull T_2915_h block) {
        return block == this.block;
    }

    public boolean matches(@Nonnull K_4074_S blockstate) {
        T_2915_h block = blockstate.J_1907_R();
        return block == this.block && this.stateHashes.contains(blockstate.hashCode());
    }

    public boolean matches(Z_1993_T stack) {
        int hash = ((IItemStack)stack).getBaritoneHash();
        return this.stackHashes.contains(hash -= stack.v_4262_N());
    }

    public String toString() {
        return String.format("BlockOptionalMeta{block=%s,properties=%s}", this.block, this.propertiesDescription);
    }

    public K_4074_S getAnyBlockState() {
        if (this.blockstates.size() > 0) {
            return this.blockstates.iterator().next();
        }
        return null;
    }

    public Set<K_4074_S> getAllBlockStates() {
        return this.blockstates;
    }

    public Set<Integer> stackHashes() {
        return this.stackHashes;
    }

    public static f_4186_T getManager() {
        if (manager == null) {
            PackRepository rpl = new PackRepository(D_2103_L::new, new T_4652_I());
            rpl.n_1700_B();
            PackResources thePack = rpl.R_4764_Y().iterator().next().G_564_y();
            b_653_U resourceManager = new b_653_U(i_4221_J.J_1907_R);
            manager = new f_4186_T(predicate);
            resourceManager.n_1700_B(manager);
            try {
                resourceManager.n_1700_B((Executor)new ThreadPerTaskExecutor(Thread::new), (Executor)new ThreadPerTaskExecutor(Thread::new), Collections.singletonList(thePack), CompletableFuture.completedFuture(X_1446_C.n_1700_B)).get();
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        return manager;
    }

    public static k_1471_n getPredicateManager() {
        return predicate;
    }

    private static synchronized List<q_1613_l> drops(T_2915_h b) {
        return drops.computeIfAbsent(b, block -> {
            g_2336_b lootTableLocation = block.P_1922_E();
            if (lootTableLocation == o_4810_o.n_1700_B) {
                return Collections.emptyList();
            }
            ArrayList items = new ArrayList();
            BlockOptionalMeta.getManager().n_1700_B(lootTableLocation).J_1907_R(new q_1704_m.n_1700_B(null).n_1700_B(new Random()).n_1700_B(LootContextParams.u_1723_Y, e_2866_D.J_1907_R(c_1514_x.NULL_VECTOR)).n_1700_B(LootContextParams.t_148_a, Z_1993_T.J_1907_R).J_1907_R(LootContextParams.w_1484_f, null).n_1700_B(LootContextParams.v_4262_N, block.multiplayerClientSuggestionProvider()).n_1700_B(f_1402_I.M_588_G), stack -> items.add(stack.J_1907_R()));
            return items;
        });
    }

    static {
        predicate = new k_1471_n();
        drops = new HashMap<T_2915_h, List<q_1613_l>>();
    }
}


