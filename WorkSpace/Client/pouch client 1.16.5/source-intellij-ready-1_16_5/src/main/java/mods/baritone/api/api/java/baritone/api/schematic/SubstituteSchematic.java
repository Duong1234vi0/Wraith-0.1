/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.AirBlock;
import lightning.product.v_3760_Q;
import mods.baritone.api.api.java.baritone.api.schematic.AbstractSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;

public class SubstituteSchematic
extends AbstractSchematic {
    private final ISchematic schematic;
    private final Map<T_2915_h, List<T_2915_h>> substitutions;
    private final Map<K_4074_S, Map<T_2915_h, K_4074_S>> blockStateCache = new HashMap<K_4074_S, Map<T_2915_h, K_4074_S>>();

    public SubstituteSchematic(ISchematic schematic, Map<T_2915_h, List<T_2915_h>> substitutions) {
        super(schematic.widthX(), schematic.heightY(), schematic.lengthZ());
        this.schematic = schematic;
        this.substitutions = substitutions;
    }

    @Override
    public boolean inSchematic(int x, int y, int z, K_4074_S currentState) {
        return this.schematic.inSchematic(x, y, z, currentState);
    }

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        K_4074_S desired = this.schematic.desiredState(x, y, z, current, approxPlaceable);
        T_2915_h desiredBlock = desired.J_1907_R();
        if (!this.substitutions.containsKey(desiredBlock)) {
            return desired;
        }
        List<T_2915_h> substitutes = this.substitutions.get(desiredBlock);
        if (substitutes.contains(current.J_1907_R()) && !(current.J_1907_R() instanceof AirBlock)) {
            return this.withBlock(desired, current.J_1907_R());
        }
        for (T_2915_h substitute : substitutes) {
            if (substitute instanceof AirBlock) {
                return current.J_1907_R() instanceof AirBlock ? current : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
            }
            for (K_4074_S placeable : approxPlaceable) {
                if (!substitute.equals(placeable.J_1907_R())) continue;
                return this.withBlock(desired, placeable.J_1907_R());
            }
        }
        return substitutes.get(0).multiplayerClientSuggestionProvider();
    }

    private K_4074_S withBlock(K_4074_S state, T_2915_h block) {
        if (this.blockStateCache.containsKey(state) && this.blockStateCache.get(state).containsKey(block)) {
            return this.blockStateCache.get(state).get(block);
        }
        Collection<v_3760_Q<?>> properties = state.J_1907_R().t_1786_h().G_564_y();
        K_4074_S newState = block.multiplayerClientSuggestionProvider();
        for (v_3760_Q<?> property : properties) {
            try {
                newState = this.copySingleProp(state, newState, property);
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        this.blockStateCache.computeIfAbsent(state, s -> new HashMap()).put(block, newState);
        return newState;
    }

    private <T extends Comparable<T>> K_4074_S copySingleProp(K_4074_S fromState, K_4074_S toState, v_3760_Q<T> prop) {
        return (K_4074_S)toState.n_1700_B(prop, fromState.R_4764_Y(prop));
    }
}


