/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.Arrays;
import java.util.HashSet;
import lightning.product.D_590_W;
import lightning.product.Q_4113_P;

public class KeyUtils {
    public static void fixKeyConflicts(D_590_W[] keys, D_590_W[] keysPrio) {
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < keysPrio.length; ++i) {
            D_590_W keybinding = keysPrio[i];
            set.add(keybinding.P_4830_p());
        }
        HashSet<D_590_W> set1 = new HashSet<D_590_W>(Arrays.asList(keys));
        set1.removeAll(Arrays.asList(keysPrio));
        for (D_590_W keybinding1 : set1) {
            String s = keybinding1.P_4830_p();
            if (!set.contains(s)) continue;
            keybinding1.J_1907_R(Q_4113_P.n_1700_B);
        }
    }
}

