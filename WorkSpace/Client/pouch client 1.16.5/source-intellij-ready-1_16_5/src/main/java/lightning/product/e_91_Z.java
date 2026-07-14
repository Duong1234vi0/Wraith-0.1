/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Predicates
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lightning.product.BlockPattern;
import lightning.product.BlockInWorld;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class e_91_Z {
    private static final Joiner n_1700_B = Joiner.on((String)",");
    private final List<String[]> J_1907_R = Lists.newArrayList();
    private final Map<Character, Predicate<BlockInWorld>> R_4764_Y = Maps.newHashMap();
    private int G_564_y;
    private int P_1922_E;

    private e_91_Z() {
        this.R_4764_Y.put(Character.valueOf(' '), (Predicate<BlockInWorld>)Predicates.alwaysTrue());
    }

    public e_91_Z n_1700_B(String ... aisle) {
        if (!ArrayUtils.isEmpty((Object[])aisle) && !StringUtils.isEmpty((CharSequence)aisle[0])) {
            if (this.J_1907_R.isEmpty()) {
                this.G_564_y = aisle.length;
                this.P_1922_E = aisle[0].length();
            }
            if (aisle.length != this.G_564_y) {
                throw new IllegalArgumentException("Expected aisle with height of " + this.G_564_y + ", but was given one with a height of " + aisle.length + ")");
            }
            for (String s : aisle) {
                if (s.length() != this.P_1922_E) {
                    throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.P_1922_E + ", found one with " + s.length() + ")");
                }
                for (char c0 : s.toCharArray()) {
                    if (this.R_4764_Y.containsKey(Character.valueOf(c0))) continue;
                    this.R_4764_Y.put(Character.valueOf(c0), null);
                }
            }
            this.J_1907_R.add(aisle);
            return this;
        }
        throw new IllegalArgumentException("Empty pattern for aisle");
    }

    public static e_91_Z n_1700_B() {
        return new e_91_Z();
    }

    public e_91_Z n_1700_B(char symbol, Predicate<BlockInWorld> blockMatcher) {
        this.R_4764_Y.put(Character.valueOf(symbol), blockMatcher);
        return this;
    }

    public BlockPattern J_1907_R() {
        return new BlockPattern(this.R_4764_Y());
    }

    private Predicate<BlockInWorld>[][][] R_4764_Y() {
        this.G_564_y();
        Predicate[][][] predicate = (Predicate[][][])Array.newInstance(Predicate.class, this.J_1907_R.size(), this.G_564_y, this.P_1922_E);
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            for (int j = 0; j < this.G_564_y; ++j) {
                for (int k = 0; k < this.P_1922_E; ++k) {
                    predicate[i][j][k] = this.R_4764_Y.get(Character.valueOf(this.J_1907_R.get(i)[j].charAt(k)));
                }
            }
        }
        return predicate;
    }

    private void G_564_y() {
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<Character, Predicate<BlockInWorld>> entry : this.R_4764_Y.entrySet()) {
            if (entry.getValue() != null) continue;
            list.add(entry.getKey());
        }
        if (!list.isEmpty()) {
            throw new IllegalStateException("Predicates for character(s) " + n_1700_B.join((Iterable)list) + " are missing");
        }
    }
}


