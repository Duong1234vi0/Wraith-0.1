/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package mods.baritone.api.api.java.baritone.api.utils;

import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.utils.accessor.IItemStack;

public class BlockOptionalMetaLookup {
    private final ImmutableSet<T_2915_h> blockSet;
    private final ImmutableSet<K_4074_S> blockStateSet;
    private final ImmutableSet<Integer> stackHashes;
    private final BlockOptionalMeta[] boms;

    public BlockOptionalMetaLookup(BlockOptionalMeta ... boms) {
        this.boms = boms;
        HashSet<T_2915_h> blocks = new HashSet<T_2915_h>();
        HashSet<K_4074_S> blockStates = new HashSet<K_4074_S>();
        HashSet<Integer> stacks = new HashSet<Integer>();
        for (BlockOptionalMeta bom : boms) {
            blocks.add(bom.getBlock());
            blockStates.addAll(bom.getAllBlockStates());
            stacks.addAll(bom.stackHashes());
        }
        this.blockSet = ImmutableSet.copyOf(blocks);
        this.blockStateSet = ImmutableSet.copyOf(blockStates);
        this.stackHashes = ImmutableSet.copyOf(stacks);
    }

    public BlockOptionalMetaLookup(T_2915_h ... blocks) {
        this((BlockOptionalMeta[])Stream.of(blocks).map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new));
    }

    public BlockOptionalMetaLookup(List<T_2915_h> blocks) {
        this((BlockOptionalMeta[])blocks.stream().map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new));
    }

    public BlockOptionalMetaLookup(String ... blocks) {
        this((BlockOptionalMeta[])Stream.of(blocks).map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new));
    }

    public boolean has(T_2915_h block) {
        return this.blockSet.contains((Object)block);
    }

    public boolean has(K_4074_S state) {
        return this.blockStateSet.contains((Object)state);
    }

    public boolean has(Z_1993_T stack) {
        int hash = ((IItemStack)stack).getBaritoneHash();
        return this.stackHashes.contains((Object)(hash -= stack.v_4262_N()));
    }

    public List<BlockOptionalMeta> blocks() {
        return Arrays.asList(this.boms);
    }

    public String toString() {
        return String.format("BlockOptionalMetaLookup{%s}", Arrays.toString(this.boms));
    }
}

