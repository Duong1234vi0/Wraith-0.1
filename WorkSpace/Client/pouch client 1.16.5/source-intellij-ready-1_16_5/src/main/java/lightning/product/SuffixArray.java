/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.Arrays
 *  it.unimi.dsi.fastutil.Swapper
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntComparator
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.Swapper;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparator;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuffixArray<T> {
    private static final boolean J_1907_R = Boolean.parseBoolean(System.getProperty("SuffixArray.printComparisons", "false"));
    private static final boolean R_4764_Y = Boolean.parseBoolean(System.getProperty("SuffixArray.printArray", "false"));
    private static final Logger G_564_y = LogManager.getLogger();
    protected final List<T> n_1700_B = Lists.newArrayList();
    private final IntList P_1922_E = new IntArrayList();
    private final IntList u_1723_Y = new IntArrayList();
    private IntList v_4262_N = new IntArrayList();
    private IntList w_1484_f = new IntArrayList();
    private int t_148_a;

    public void n_1700_B(T p_194057_1_, String p_194057_2_) {
        this.t_148_a = Math.max(this.t_148_a, p_194057_2_.length());
        int i = this.n_1700_B.size();
        this.n_1700_B.add(p_194057_1_);
        this.u_1723_Y.add(this.P_1922_E.size());
        for (int j = 0; j < p_194057_2_.length(); ++j) {
            this.v_4262_N.add(i);
            this.w_1484_f.add(j);
            this.P_1922_E.add((int)p_194057_2_.charAt(j));
        }
        this.v_4262_N.add(i);
        this.w_1484_f.add(p_194057_2_.length());
        this.P_1922_E.add(-1);
    }

    public void n_1700_B() {
        int i = this.P_1922_E.size();
        int[] aint = new int[i];
        final int[] aint1 = new int[i];
        final int[] aint2 = new int[i];
        int[] aint3 = new int[i];
        IntComparator intcomparator = new IntComparator(){

            public int compare(int p_compare_1_, int p_compare_2_) {
                return aint1[p_compare_1_] == aint1[p_compare_2_] ? Integer.compare(aint2[p_compare_1_], aint2[p_compare_2_]) : Integer.compare(aint1[p_compare_1_], aint1[p_compare_2_]);
            }

            public int compare(Integer p_compare_1_, Integer p_compare_2_) {
                return this.compare((int)p_compare_1_, (int)p_compare_2_);
            }
        };
        Swapper swapper = (p_194054_3_, p_194054_4_) -> {
            if (p_194054_3_ != p_194054_4_) {
                int i2 = aint1[p_194054_3_];
                aint1[p_194054_3_] = aint1[p_194054_4_];
                aint1[p_194054_4_] = i2;
                i2 = aint2[p_194054_3_];
                aint2[p_194054_3_] = aint2[p_194054_4_];
                aint2[p_194054_4_] = i2;
                i2 = aint3[p_194054_3_];
                aint3[p_194054_3_] = aint3[p_194054_4_];
                aint3[p_194054_4_] = i2;
            }
        };
        for (int j = 0; j < i; ++j) {
            aint[j] = this.P_1922_E.getInt(j);
        }
        int k1 = 1;
        int k = Math.min(i, this.t_148_a);
        while (k1 * 2 < k) {
            int l = 0;
            while (l < i) {
                aint1[l] = aint[l];
                aint2[l] = l + k1 < i ? aint[l + k1] : -2;
                aint3[l] = l++;
            }
            it.unimi.dsi.fastutil.Arrays.quickSort((int)0, (int)i, (IntComparator)intcomparator, (Swapper)swapper);
            for (int l1 = 0; l1 < i; ++l1) {
                aint[aint3[l1]] = l1 > 0 && aint1[l1] == aint1[l1 - 1] && aint2[l1] == aint2[l1 - 1] ? aint[aint3[l1 - 1]] : l1;
            }
            k1 *= 2;
        }
        IntList intlist1 = this.v_4262_N;
        IntList intlist = this.w_1484_f;
        this.v_4262_N = new IntArrayList(intlist1.size());
        this.w_1484_f = new IntArrayList(intlist.size());
        for (int i1 = 0; i1 < i; ++i1) {
            int j1 = aint3[i1];
            this.v_4262_N.add(intlist1.getInt(j1));
            this.w_1484_f.add(intlist.getInt(j1));
        }
        if (R_4764_Y) {
            this.J_1907_R();
        }
    }

    private void J_1907_R() {
        for (int i = 0; i < this.v_4262_N.size(); ++i) {
            G_564_y.debug("{} {}", (Object)i, (Object)this.n_1700_B(i));
        }
        G_564_y.debug("");
    }

    private String n_1700_B(int p_194059_1_) {
        int i = this.w_1484_f.getInt(p_194059_1_);
        int j = this.u_1723_Y.getInt(this.v_4262_N.getInt(p_194059_1_));
        StringBuilder stringbuilder = new StringBuilder();
        int k = 0;
        while (j + k < this.P_1922_E.size()) {
            int l;
            if (k == i) {
                stringbuilder.append('^');
            }
            if ((l = this.P_1922_E.get(j + k).intValue()) == -1) break;
            stringbuilder.append((char)l);
            ++k;
        }
        return stringbuilder.toString();
    }

    private int n_1700_B(String p_194056_1_, int p_194056_2_) {
        int i = this.u_1723_Y.getInt(this.v_4262_N.getInt(p_194056_2_));
        int j = this.w_1484_f.getInt(p_194056_2_);
        for (int k = 0; k < p_194056_1_.length(); ++k) {
            char c1;
            int l = this.P_1922_E.getInt(i + j + k);
            if (l == -1) {
                return 1;
            }
            char c0 = p_194056_1_.charAt(k);
            if (c0 < (c1 = (char)l)) {
                return -1;
            }
            if (c0 <= c1) continue;
            return 1;
        }
        return 0;
    }

    public List<T> n_1700_B(String p_194055_1_) {
        int i = this.v_4262_N.size();
        int j = 0;
        int k = i;
        while (j < k) {
            int l = j + (k - j) / 2;
            int i1 = this.n_1700_B(p_194055_1_, l);
            if (J_1907_R) {
                G_564_y.debug("comparing lower \"{}\" with {} \"{}\": {}", (Object)p_194055_1_, (Object)l, (Object)this.n_1700_B(l), (Object)i1);
            }
            if (i1 > 0) {
                j = l + 1;
                continue;
            }
            k = l;
        }
        if (j >= 0 && j < i) {
            int i2 = j;
            k = i;
            while (j < k) {
                int j2 = j + (k - j) / 2;
                int j1 = this.n_1700_B(p_194055_1_, j2);
                if (J_1907_R) {
                    G_564_y.debug("comparing upper \"{}\" with {} \"{}\": {}", (Object)p_194055_1_, (Object)j2, (Object)this.n_1700_B(j2), (Object)j1);
                }
                if (j1 >= 0) {
                    j = j2 + 1;
                    continue;
                }
                k = j2;
            }
            int k2 = j;
            IntOpenHashSet intset = new IntOpenHashSet();
            for (int k1 = i2; k1 < k2; ++k1) {
                intset.add(this.v_4262_N.getInt(k1));
            }
            int[] aint = intset.toIntArray();
            Arrays.sort(aint);
            LinkedHashSet set = Sets.newLinkedHashSet();
            for (int l1 : aint) {
                set.add(this.n_1700_B.get(l1));
            }
            return Lists.newArrayList((Iterable)set);
        }
        return Collections.emptyList();
    }
}


