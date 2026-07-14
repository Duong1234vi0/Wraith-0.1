/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package net.minecraftforge.client.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_590_W;
import lightning.product.Q_4113_P;
import net.minecraftforge.client.settings.KeyModifier;

public class KeyBindingMap {
    private static final EnumMap<KeyModifier, Map<Q_4113_P.n_1700_B, Collection<D_590_W>>> map = new EnumMap(KeyModifier.class);

    @Nullable
    public D_590_W lookupActive(Q_4113_P.n_1700_B keyCode) {
        D_590_W binding;
        KeyModifier activeModifier = KeyModifier.getActiveModifier();
        if (!activeModifier.matches(keyCode) && (binding = this.getBinding(keyCode, activeModifier)) != null) {
            return binding;
        }
        return this.getBinding(keyCode, KeyModifier.NONE);
    }

    @Nullable
    private D_590_W getBinding(Q_4113_P.n_1700_B keyCode, KeyModifier keyModifier) {
        Collection<D_590_W> bindings = map.get((Object)keyModifier).get(keyCode);
        if (bindings != null) {
            for (D_590_W binding : bindings) {
                if (!binding.isActiveAndMatches(keyCode)) continue;
                return binding;
            }
        }
        return null;
    }

    public List<D_590_W> lookupAll(Q_4113_P.n_1700_B keyCode) {
        ArrayList<D_590_W> matchingBindings = new ArrayList<D_590_W>();
        for (Map<Q_4113_P.n_1700_B, Collection<D_590_W>> bindingsMap : map.values()) {
            Collection<D_590_W> bindings = bindingsMap.get(keyCode);
            if (bindings == null) continue;
            matchingBindings.addAll(bindings);
        }
        return matchingBindings;
    }

    public void addKey(Q_4113_P.n_1700_B keyCode, D_590_W keyBinding) {
        KeyModifier keyModifier = keyBinding.getKeyModifier();
        Map<Q_4113_P.n_1700_B, Collection<D_590_W>> bindingsMap = map.get((Object)keyModifier);
        Collection<D_590_W> bindingsForKey = bindingsMap.get(keyCode);
        if (bindingsForKey == null) {
            bindingsForKey = new ArrayList<D_590_W>();
            bindingsMap.put(keyCode, bindingsForKey);
        }
        bindingsForKey.add(keyBinding);
    }

    public void removeKey(D_590_W keyBinding) {
        KeyModifier keyModifier = keyBinding.getKeyModifier();
        Q_4113_P.n_1700_B keyCode = keyBinding.getKey();
        Map<Q_4113_P.n_1700_B, Collection<D_590_W>> bindingsMap = map.get((Object)keyModifier);
        Collection<D_590_W> bindingsForKey = bindingsMap.get(keyCode);
        if (bindingsForKey != null) {
            bindingsForKey.remove(keyBinding);
            if (bindingsForKey.isEmpty()) {
                bindingsMap.remove(keyCode);
            }
        }
    }

    public void clearMap() {
        for (Map<Q_4113_P.n_1700_B, Collection<D_590_W>> bindings : map.values()) {
            bindings.clear();
        }
    }

    static {
        for (KeyModifier modifier : KeyModifier.values()) {
            map.put(modifier, new HashMap());
        }
    }
}

