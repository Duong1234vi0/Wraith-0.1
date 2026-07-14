/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ByteMap
 *  it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongArrayList
 *  it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongList
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.function.LongPredicate;
import lightning.product.C_2693_g;
import lightning.product.G_1698_X;
import lightning.product.L_4831_e;
import lightning.product.m_689_s;
import lightning.product.u_530_F;

public abstract class c_1289_c {
    private final int n_1700_B;
    private final LongLinkedOpenHashSet[] J_1907_R;
    private final Long2ByteMap R_4764_Y;
    private int G_564_y;
    private volatile boolean P_1922_E;

    protected c_1289_c(int levelCount, final int p_i51298_2_, final int p_i51298_3_) {
        if (levelCount >= 254) {
            throw new IllegalArgumentException("Level count must be < 254.");
        }
        this.n_1700_B = levelCount;
        this.J_1907_R = new LongLinkedOpenHashSet[levelCount];
        int i = p_i51298_2_;
        int j = p_i51298_3_;
        if (this.getClass() != C_2693_g.class && this.getClass() != L_4831_e.class) {
            if (this.getClass() == m_689_s.class || this.getClass() == G_1698_X.class) {
                i = Math.max(p_i51298_2_, 2048);
                j = Math.max(p_i51298_3_, 2048);
            }
        } else {
            i = Math.max(p_i51298_2_, 8192);
            j = Math.max(p_i51298_3_, 8192);
        }
        for (int k = 0; k < levelCount; ++k) {
            this.J_1907_R[k] = new LongLinkedOpenHashSet(i, 0.5f){

                protected void rehash(int p_rehash_1_) {
                    if (p_rehash_1_ > p_i51298_2_) {
                        super.rehash(p_rehash_1_);
                    }
                }
            };
        }
        this.R_4764_Y = new Long2ByteOpenHashMap(j, 0.5f){

            protected void rehash(int p_rehash_1_) {
                if (p_rehash_1_ > p_i51298_3_) {
                    super.rehash(p_rehash_1_);
                }
            }
        };
        this.R_4764_Y.defaultReturnValue((byte)-1);
        this.G_564_y = levelCount;
    }

    private int n_1700_B(int level1, int level2) {
        int i = level1;
        if (level1 > level2) {
            i = level2;
        }
        if (i > this.n_1700_B - 1) {
            i = this.n_1700_B - 1;
        }
        return i;
    }

    private void J_1907_R(int maxLevel) {
        int i = this.G_564_y;
        this.G_564_y = maxLevel;
        for (int j = i + 1; j < maxLevel; ++j) {
            if (this.J_1907_R[j].isEmpty()) continue;
            this.G_564_y = j;
            break;
        }
    }

    protected void P_1922_E(long positionIn) {
        int i = this.R_4764_Y.get(positionIn) & 0xFF;
        if (i != 255) {
            int j = this.R_4764_Y(positionIn);
            int k = this.n_1700_B(j, i);
            this.n_1700_B(positionIn, k, this.n_1700_B, true);
            this.P_1922_E = this.G_564_y < this.n_1700_B;
        }
    }

    public void n_1700_B(LongPredicate p_227465_1_) {
        LongArrayList longlist = new LongArrayList();
        this.R_4764_Y.keySet().forEach(arg_0 -> c_1289_c.n_1700_B(p_227465_1_, (LongList)longlist, arg_0));
        longlist.forEach(this::P_1922_E);
    }

    private void n_1700_B(long pos, int level, int maxLevel, boolean removeAll) {
        if (removeAll) {
            this.R_4764_Y.remove(pos);
        }
        this.J_1907_R[level].remove(pos);
        if (this.J_1907_R[level].isEmpty() && this.G_564_y == level) {
            this.J_1907_R(maxLevel);
        }
    }

    private void n_1700_B(long pos, int levelToSet, int updateLevel) {
        this.R_4764_Y.put(pos, (byte)levelToSet);
        this.J_1907_R[updateLevel].add(pos);
        if (this.G_564_y > updateLevel) {
            this.G_564_y = updateLevel;
        }
    }

    protected void u_1723_Y(long worldPos) {
        this.n_1700_B(worldPos, worldPos, this.n_1700_B - 1, false);
    }

    protected void n_1700_B(long fromPos, long toPos, int newLevel, boolean isDecreasing) {
        this.n_1700_B(fromPos, toPos, newLevel, this.R_4764_Y(toPos), this.R_4764_Y.get(toPos) & 0xFF, isDecreasing);
        this.P_1922_E = this.G_564_y < this.n_1700_B;
    }

    private void n_1700_B(long fromPos, long toPos, int newLevel, int previousLevel, int propagationLevel, boolean isDecreasing) {
        if (!this.n_1700_B(toPos)) {
            boolean flag;
            newLevel = u_530_F.n_1700_B(newLevel, 0, this.n_1700_B - 1);
            previousLevel = u_530_F.n_1700_B(previousLevel, 0, this.n_1700_B - 1);
            if (propagationLevel == 255) {
                flag = true;
                propagationLevel = previousLevel;
            } else {
                flag = false;
            }
            int i = isDecreasing ? Math.min(propagationLevel, newLevel) : u_530_F.n_1700_B(this.n_1700_B(toPos, fromPos, newLevel), 0, this.n_1700_B - 1);
            int j = this.n_1700_B(previousLevel, propagationLevel);
            if (previousLevel != i) {
                int k = this.n_1700_B(previousLevel, i);
                if (j != k && !flag) {
                    this.n_1700_B(toPos, j, k, false);
                }
                this.n_1700_B(toPos, i, k);
            } else if (!flag) {
                this.n_1700_B(toPos, j, this.n_1700_B, true);
            }
        }
    }

    protected final void J_1907_R(long fromPos, long toPos, int sourceLevel, boolean isDecreasing) {
        int i = this.R_4764_Y.get(toPos) & 0xFF;
        int j = u_530_F.n_1700_B(this.J_1907_R(fromPos, toPos, sourceLevel), 0, this.n_1700_B - 1);
        if (isDecreasing) {
            this.n_1700_B(fromPos, toPos, j, this.R_4764_Y(toPos), i, true);
        } else {
            int k;
            boolean flag;
            if (i == 255) {
                flag = true;
                k = u_530_F.n_1700_B(this.R_4764_Y(toPos), 0, this.n_1700_B - 1);
            } else {
                k = i;
                flag = false;
            }
            if (j == k) {
                this.n_1700_B(fromPos, toPos, this.n_1700_B - 1, flag ? k : this.R_4764_Y(toPos), i, false);
            }
        }
    }

    protected final boolean J_1907_R() {
        return this.P_1922_E;
    }

    protected final int n_1700_B(int toUpdateCount) {
        if (this.G_564_y >= this.n_1700_B) {
            return toUpdateCount;
        }
        while (this.G_564_y < this.n_1700_B && toUpdateCount > 0) {
            int k;
            --toUpdateCount;
            LongLinkedOpenHashSet longlinkedopenhashset = this.J_1907_R[this.G_564_y];
            long i = longlinkedopenhashset.removeFirstLong();
            int j = u_530_F.n_1700_B(this.R_4764_Y(i), 0, this.n_1700_B - 1);
            if (longlinkedopenhashset.isEmpty()) {
                this.J_1907_R(this.n_1700_B);
            }
            if ((k = this.R_4764_Y.remove(i) & 0xFF) < j) {
                this.n_1700_B(i, k);
                this.n_1700_B(i, k, true);
                continue;
            }
            if (k <= j) continue;
            this.n_1700_B(i, k, this.n_1700_B(this.n_1700_B - 1, k));
            this.n_1700_B(i, this.n_1700_B - 1);
            this.n_1700_B(i, j, false);
        }
        this.P_1922_E = this.G_564_y < this.n_1700_B;
        return toUpdateCount;
    }

    public int R_4764_Y() {
        return this.R_4764_Y.size();
    }

    protected abstract boolean n_1700_B(long var1);

    protected abstract int n_1700_B(long var1, long var3, int var5);

    protected abstract void n_1700_B(long var1, int var3, boolean var4);

    protected abstract int R_4764_Y(long var1);

    protected abstract void n_1700_B(long var1, int var3);

    protected abstract int J_1907_R(long var1, long var3, int var5);

    protected int G_564_y() {
        return this.R_4764_Y.size();
    }

    private static /* synthetic */ void n_1700_B(LongPredicate p_227465_1_, LongList longlist, Long p_lambda$func_227465_a_$0_2_) {
        if (p_227465_1_.test(p_lambda$func_227465_a_$0_2_)) {
            longlist.add(p_lambda$func_227465_a_$0_2_);
        }
    }
}

