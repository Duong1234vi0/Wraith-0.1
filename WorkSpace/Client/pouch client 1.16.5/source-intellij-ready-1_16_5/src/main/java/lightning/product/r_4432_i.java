/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntAVLTreeSet
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntCollection
 *  it.unimi.dsi.fastutil.ints.IntIterator
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntListIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.util.BitSet;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.NonNullList;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.Recipe;
import lightning.product.q_1613_l;

public class r_4432_i {
    public final Int2IntMap n_1700_B = new Int2IntOpenHashMap();

    public void n_1700_B(Z_1993_T stack) {
        if (!(stack.u_1723_Y() || stack.k_2293_S() || stack.Y_601_j())) {
            this.J_1907_R(stack);
        }
    }

    public void J_1907_R(Z_1993_T stack) {
        this.n_1700_B(stack, 64);
    }

    public void n_1700_B(Z_1993_T stack, int minCount) {
        if (!stack.n_1700_B()) {
            int i = r_4432_i.R_4764_Y(stack);
            int j = Math.min(minCount, stack.t_4043_B());
            this.J_1907_R(i, j);
        }
    }

    public static int R_4764_Y(Z_1993_T stack) {
        return V_3137_a.e_2887_G.n_1700_B(stack.J_1907_R());
    }

    private boolean J_1907_R(int packedItem) {
        return this.n_1700_B.get(packedItem) > 0;
    }

    private int n_1700_B(int packedItem, int maximum) {
        int i = this.n_1700_B.get(packedItem);
        if (i >= maximum) {
            this.n_1700_B.put(packedItem, i - maximum);
            return packedItem;
        }
        return 0;
    }

    private void J_1907_R(int packedItem, int amount) {
        this.n_1700_B.put(packedItem, this.n_1700_B.get(packedItem) + amount);
    }

    public boolean n_1700_B(Recipe<?> recipe, @Nullable IntList packedItemList) {
        return this.n_1700_B(recipe, packedItemList, 1);
    }

    public boolean n_1700_B(Recipe<?> recipe, @Nullable IntList packedItemList, int maxAmount) {
        return new n_1700_B(recipe).n_1700_B(maxAmount, packedItemList);
    }

    public int J_1907_R(Recipe<?> recipe, @Nullable IntList packedItemList) {
        return this.n_1700_B(recipe, Integer.MAX_VALUE, packedItemList);
    }

    public int n_1700_B(Recipe<?> recipe, int maxAmount, @Nullable IntList packedItemList) {
        return new n_1700_B(recipe).J_1907_R(maxAmount, packedItemList);
    }

    public static Z_1993_T n_1700_B(int packedItem) {
        return packedItem == 0 ? Z_1993_T.J_1907_R : new Z_1993_T(q_1613_l.J_1907_R(packedItem));
    }

    public void n_1700_B() {
        this.n_1700_B.clear();
    }

    class n_1700_B {
        private final Recipe<?> J_1907_R;
        private final List<b_3278_X> R_4764_Y = Lists.newArrayList();
        private final int G_564_y;
        private final int[] P_1922_E;
        private final int u_1723_Y;
        private final BitSet v_4262_N;
        private final IntList w_1484_f = new IntArrayList();

        public n_1700_B(Recipe<?> recipeIn) {
            this.J_1907_R = recipeIn;
            this.R_4764_Y.addAll(recipeIn.n_1700_B());
            this.R_4764_Y.removeIf(b_3278_X::G_564_y);
            this.G_564_y = this.R_4764_Y.size();
            this.P_1922_E = this.n_1700_B();
            this.u_1723_Y = this.P_1922_E.length;
            this.v_4262_N = new BitSet(this.G_564_y + this.u_1723_Y + this.G_564_y + this.G_564_y * this.u_1723_Y);
            for (int i = 0; i < this.R_4764_Y.size(); ++i) {
                IntList intlist = this.R_4764_Y.get(i).J_1907_R();
                for (int j = 0; j < this.u_1723_Y; ++j) {
                    if (!intlist.contains(this.P_1922_E[j])) continue;
                    this.v_4262_N.set(this.G_564_y(true, j, i));
                }
            }
        }

        public boolean n_1700_B(int maxAmount, @Nullable IntList listIn) {
            boolean flag1;
            if (maxAmount <= 0) {
                return true;
            }
            int i = 0;
            while (this.n_1700_B(maxAmount)) {
                r_4432_i.this.n_1700_B(this.P_1922_E[this.w_1484_f.getInt(0)], maxAmount);
                int j = this.w_1484_f.size() - 1;
                this.R_4764_Y(this.w_1484_f.getInt(j));
                for (int k = 0; k < j; ++k) {
                    this.R_4764_Y((k & 1) == 0, this.w_1484_f.get(k), this.w_1484_f.get(k + 1));
                }
                this.w_1484_f.clear();
                this.v_4262_N.clear(0, this.G_564_y + this.u_1723_Y);
                ++i;
            }
            boolean flag = i == this.G_564_y;
            boolean bl = flag1 = flag && listIn != null;
            if (flag1) {
                listIn.clear();
            }
            this.v_4262_N.clear(0, this.G_564_y + this.u_1723_Y + this.G_564_y);
            int l = 0;
            NonNullList<b_3278_X> list = this.J_1907_R.n_1700_B();
            for (int i1 = 0; i1 < list.size(); ++i1) {
                if (flag1 && ((b_3278_X)list.get(i1)).G_564_y()) {
                    listIn.add(0);
                    continue;
                }
                for (int j1 = 0; j1 < this.u_1723_Y; ++j1) {
                    if (!this.J_1907_R(false, l, j1)) continue;
                    this.R_4764_Y(true, j1, l);
                    r_4432_i.this.J_1907_R(this.P_1922_E[j1], maxAmount);
                    if (!flag1) continue;
                    listIn.add(this.P_1922_E[j1]);
                }
                ++l;
            }
            return flag;
        }

        private int[] n_1700_B() {
            IntAVLTreeSet intcollection = new IntAVLTreeSet();
            for (b_3278_X ingredient : this.R_4764_Y) {
                intcollection.addAll((IntCollection)ingredient.J_1907_R());
            }
            IntIterator intiterator = intcollection.iterator();
            while (intiterator.hasNext()) {
                if (r_4432_i.this.J_1907_R(intiterator.nextInt())) continue;
                intiterator.remove();
            }
            return intcollection.toIntArray();
        }

        private boolean n_1700_B(int amount) {
            int i = this.u_1723_Y;
            for (int j = 0; j < i; ++j) {
                if (r_4432_i.this.n_1700_B.get(this.P_1922_E[j]) < amount) continue;
                this.n_1700_B(false, j);
                while (!this.w_1484_f.isEmpty()) {
                    int k1;
                    int k = this.w_1484_f.size();
                    boolean flag = (k & 1) == 1;
                    int l = this.w_1484_f.getInt(k - 1);
                    if (!flag && !this.J_1907_R(l)) break;
                    int i1 = flag ? this.G_564_y : i;
                    for (int j1 = 0; j1 < i1; ++j1) {
                        if (this.J_1907_R(flag, j1) || !this.n_1700_B(flag, l, j1) || !this.J_1907_R(flag, l, j1)) continue;
                        this.n_1700_B(flag, j1);
                        break;
                    }
                    if ((k1 = this.w_1484_f.size()) != k) continue;
                    this.w_1484_f.removeInt(k1 - 1);
                }
                if (this.w_1484_f.isEmpty()) continue;
                return true;
            }
            return false;
        }

        private boolean J_1907_R(int p_194091_1_) {
            return this.v_4262_N.get(this.G_564_y(p_194091_1_));
        }

        private void R_4764_Y(int p_194096_1_) {
            this.v_4262_N.set(this.G_564_y(p_194096_1_));
        }

        private int G_564_y(int p_194094_1_) {
            return this.G_564_y + this.u_1723_Y + p_194094_1_;
        }

        private boolean n_1700_B(boolean p_194093_1_, int p_194093_2_, int p_194093_3_) {
            return this.v_4262_N.get(this.G_564_y(p_194093_1_, p_194093_2_, p_194093_3_));
        }

        private boolean J_1907_R(boolean p_194100_1_, int p_194100_2_, int p_194100_3_) {
            return p_194100_1_ != this.v_4262_N.get(1 + this.G_564_y(p_194100_1_, p_194100_2_, p_194100_3_));
        }

        private void R_4764_Y(boolean p_194089_1_, int p_194089_2_, int p_194089_3_) {
            this.v_4262_N.flip(1 + this.G_564_y(p_194089_1_, p_194089_2_, p_194089_3_));
        }

        private int G_564_y(boolean p_194095_1_, int p_194095_2_, int p_194095_3_) {
            int i = p_194095_1_ ? p_194095_2_ * this.G_564_y + p_194095_3_ : p_194095_3_ * this.G_564_y + p_194095_2_;
            return this.G_564_y + this.u_1723_Y + this.G_564_y + 2 * i;
        }

        private void n_1700_B(boolean p_194088_1_, int p_194088_2_) {
            this.v_4262_N.set(this.R_4764_Y(p_194088_1_, p_194088_2_));
            this.w_1484_f.add(p_194088_2_);
        }

        private boolean J_1907_R(boolean p_194101_1_, int p_194101_2_) {
            return this.v_4262_N.get(this.R_4764_Y(p_194101_1_, p_194101_2_));
        }

        private int R_4764_Y(boolean p_194099_1_, int p_194099_2_) {
            return (p_194099_1_ ? 0 : this.G_564_y) + p_194099_2_;
        }

        public int J_1907_R(int p_194102_1_, @Nullable IntList list) {
            int i = 0;
            int j = Math.min(p_194102_1_, this.J_1907_R()) + 1;
            while (true) {
                int k;
                if (this.n_1700_B(k = (i + j) / 2, null)) {
                    if (j - i <= 1) {
                        if (k > 0) {
                            this.n_1700_B(k, list);
                        }
                        return k;
                    }
                    i = k;
                    continue;
                }
                j = k;
            }
        }

        private int J_1907_R() {
            int i = Integer.MAX_VALUE;
            for (b_3278_X ingredient : this.R_4764_Y) {
                int j = 0;
                IntListIterator intListIterator = ingredient.J_1907_R().iterator();
                while (intListIterator.hasNext()) {
                    int k = (Integer)intListIterator.next();
                    j = Math.max(j, r_4432_i.this.n_1700_B.get(k));
                }
                if (i <= 0) continue;
                i = Math.min(i, j);
            }
            return i;
        }
    }
}


