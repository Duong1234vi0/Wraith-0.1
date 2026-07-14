/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Set;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.u_4256_q;

public class VisGraph {
    private static final int n_1700_B = (int)Math.pow(16.0, 0.0);
    private static final int J_1907_R = (int)Math.pow(16.0, 1.0);
    private static final int R_4764_Y = (int)Math.pow(16.0, 2.0);
    private static final b_257_Y[] G_564_y = b_257_Y.values();
    private final BitSet P_1922_E = new BitSet(4096);
    private static final int[] u_1723_Y = j_3341_s.n_1700_B(new int[1352], p_lambda$static$0_0_ -> {
        boolean i = false;
        int j = 15;
        int k = 0;
        for (int l = 0; l < 16; ++l) {
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < 16; ++j1) {
                    if (l != 0 && l != 15 && i1 != 0 && i1 != 15 && j1 != 0 && j1 != 15) continue;
                    p_lambda$static$0_0_[k++] = VisGraph.n_1700_B(l, i1, j1);
                }
            }
        }
    });
    private int v_4262_N = 4096;

    public void n_1700_B(c_1514_x pos) {
        this.P_1922_E.set(VisGraph.J_1907_R(pos), true);
        --this.v_4262_N;
    }

    private static int J_1907_R(c_1514_x pos) {
        return VisGraph.n_1700_B(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF);
    }

    private static int n_1700_B(int x, int y, int z) {
        return x << 0 | y << 8 | z << 4;
    }

    public u_4256_q n_1700_B() {
        u_4256_q setvisibility = new u_4256_q();
        if (4096 - this.v_4262_N < 256) {
            setvisibility.n_1700_B(true);
        } else if (this.v_4262_N == 0) {
            setvisibility.n_1700_B(false);
        } else {
            for (int i : u_1723_Y) {
                if (this.P_1922_E.get(i)) continue;
                setvisibility.n_1700_B(this.n_1700_B(i));
            }
        }
        return setvisibility;
    }

    private Set<b_257_Y> n_1700_B(int pos) {
        EnumSet<b_257_Y> set = EnumSet.noneOf(b_257_Y.class);
        IntArrayFIFOQueue intpriorityqueue = new IntArrayFIFOQueue(384);
        intpriorityqueue.enqueue(pos);
        this.P_1922_E.set(pos, true);
        while (!intpriorityqueue.isEmpty()) {
            int i = intpriorityqueue.dequeueInt();
            this.n_1700_B(i, set);
            for (b_257_Y direction : G_564_y) {
                int j = this.n_1700_B(i, direction);
                if (j < 0 || this.P_1922_E.get(j)) continue;
                this.P_1922_E.set(j, true);
                intpriorityqueue.enqueue(j);
            }
        }
        return set;
    }

    private void n_1700_B(int pos, Set<b_257_Y> setFacings) {
        int i = pos >> 0 & 0xF;
        if (i == 0) {
            setFacings.add(b_257_Y.P_1922_E);
        } else if (i == 15) {
            setFacings.add(b_257_Y.u_1723_Y);
        }
        int j = pos >> 8 & 0xF;
        if (j == 0) {
            setFacings.add(b_257_Y.n_1700_B);
        } else if (j == 15) {
            setFacings.add(b_257_Y.J_1907_R);
        }
        int k = pos >> 4 & 0xF;
        if (k == 0) {
            setFacings.add(b_257_Y.R_4764_Y);
        } else if (k == 15) {
            setFacings.add(b_257_Y.G_564_y);
        }
    }

    private int n_1700_B(int pos, b_257_Y facing) {
        switch (facing) {
            case n_1700_B: {
                if ((pos >> 8 & 0xF) == 0) {
                    return -1;
                }
                return pos - R_4764_Y;
            }
            case J_1907_R: {
                if ((pos >> 8 & 0xF) == 15) {
                    return -1;
                }
                return pos + R_4764_Y;
            }
            case R_4764_Y: {
                if ((pos >> 4 & 0xF) == 0) {
                    return -1;
                }
                return pos - J_1907_R;
            }
            case G_564_y: {
                if ((pos >> 4 & 0xF) == 15) {
                    return -1;
                }
                return pos + J_1907_R;
            }
            case P_1922_E: {
                if ((pos >> 0 & 0xF) == 0) {
                    return -1;
                }
                return pos - n_1700_B;
            }
            case u_1723_Y: {
                if ((pos >> 0 & 0xF) == 15) {
                    return -1;
                }
                return pos + n_1700_B;
            }
        }
        return -1;
    }
}


