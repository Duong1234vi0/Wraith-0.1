/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.Tuple;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.W_2163_m;
import lightning.product.AbstractIllager;
import lightning.product.a_2886_t;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.o_4810_o;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.t_5_h;
import lightning.product.v_3445_Z;
import lightning.product.w_1748_S;

public class WoodlandMansionPieces {
    public static void n_1700_B(b_2085_h p_191152_0_, c_1514_x p_191152_1_, W_2163_m p_191152_2_, List<R_4764_Y> p_191152_3_, Random p_191152_4_) {
        J_1907_R woodlandmansionpieces$grid = new J_1907_R(p_191152_4_);
        P_1922_E woodlandmansionpieces$placer = new P_1922_E(p_191152_0_, p_191152_4_);
        woodlandmansionpieces$placer.n_1700_B(p_191152_1_, p_191152_2_, p_191152_3_, woodlandmansionpieces$grid);
    }

    static class J_1907_R {
        private final Random n_1700_B;
        private final w_1484_f J_1907_R;
        private final w_1484_f R_4764_Y;
        private final w_1484_f[] G_564_y;
        private final int P_1922_E;
        private final int u_1723_Y;

        public J_1907_R(Random randomIn) {
            this.n_1700_B = randomIn;
            int i = 11;
            this.P_1922_E = 7;
            this.u_1723_Y = 4;
            this.J_1907_R = new w_1484_f(11, 11, 5);
            this.J_1907_R.n_1700_B(this.P_1922_E, this.u_1723_Y, this.P_1922_E + 1, this.u_1723_Y + 1, 3);
            this.J_1907_R.n_1700_B(this.P_1922_E - 1, this.u_1723_Y, this.P_1922_E - 1, this.u_1723_Y + 1, 2);
            this.J_1907_R.n_1700_B(this.P_1922_E + 2, this.u_1723_Y - 2, this.P_1922_E + 3, this.u_1723_Y + 3, 5);
            this.J_1907_R.n_1700_B(this.P_1922_E + 1, this.u_1723_Y - 2, this.P_1922_E + 1, this.u_1723_Y - 1, 1);
            this.J_1907_R.n_1700_B(this.P_1922_E + 1, this.u_1723_Y + 2, this.P_1922_E + 1, this.u_1723_Y + 3, 1);
            this.J_1907_R.n_1700_B(this.P_1922_E - 1, this.u_1723_Y - 1, 1);
            this.J_1907_R.n_1700_B(this.P_1922_E - 1, this.u_1723_Y + 2, 1);
            this.J_1907_R.n_1700_B(0, 0, 11, 1, 5);
            this.J_1907_R.n_1700_B(0, 9, 11, 11, 5);
            this.n_1700_B(this.J_1907_R, this.P_1922_E, this.u_1723_Y - 2, b_257_Y.P_1922_E, 6);
            this.n_1700_B(this.J_1907_R, this.P_1922_E, this.u_1723_Y + 3, b_257_Y.P_1922_E, 6);
            this.n_1700_B(this.J_1907_R, this.P_1922_E - 2, this.u_1723_Y - 1, b_257_Y.P_1922_E, 3);
            this.n_1700_B(this.J_1907_R, this.P_1922_E - 2, this.u_1723_Y + 2, b_257_Y.P_1922_E, 3);
            while (this.n_1700_B(this.J_1907_R)) {
            }
            this.G_564_y = new w_1484_f[3];
            this.G_564_y[0] = new w_1484_f(11, 11, 5);
            this.G_564_y[1] = new w_1484_f(11, 11, 5);
            this.G_564_y[2] = new w_1484_f(11, 11, 5);
            this.n_1700_B(this.J_1907_R, this.G_564_y[0]);
            this.n_1700_B(this.J_1907_R, this.G_564_y[1]);
            this.G_564_y[0].n_1700_B(this.P_1922_E + 1, this.u_1723_Y, this.P_1922_E + 1, this.u_1723_Y + 1, 0x800000);
            this.G_564_y[1].n_1700_B(this.P_1922_E + 1, this.u_1723_Y, this.P_1922_E + 1, this.u_1723_Y + 1, 0x800000);
            this.R_4764_Y = new w_1484_f(this.J_1907_R.J_1907_R, this.J_1907_R.R_4764_Y, 5);
            this.n_1700_B();
            this.n_1700_B(this.R_4764_Y, this.G_564_y[2]);
        }

        public static boolean n_1700_B(w_1484_f p_191109_0_, int p_191109_1_, int p_191109_2_) {
            int i = p_191109_0_.n_1700_B(p_191109_1_, p_191109_2_);
            return i == 1 || i == 2 || i == 3 || i == 4;
        }

        public boolean n_1700_B(w_1484_f p_191114_1_, int p_191114_2_, int p_191114_3_, int p_191114_4_, int p_191114_5_) {
            return (this.G_564_y[p_191114_4_].n_1700_B(p_191114_2_, p_191114_3_) & 0xFFFF) == p_191114_5_;
        }

        @Nullable
        public b_257_Y J_1907_R(w_1484_f p_191113_1_, int p_191113_2_, int p_191113_3_, int p_191113_4_, int p_191113_5_) {
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                if (!this.n_1700_B(p_191113_1_, p_191113_2_ + direction.t_148_a(), p_191113_3_ + direction.u_2550_I(), p_191113_4_, p_191113_5_)) continue;
                return direction;
            }
            return null;
        }

        private void n_1700_B(w_1484_f p_191110_1_, int p_191110_2_, int p_191110_3_, b_257_Y p_191110_4_, int p_191110_5_) {
            if (p_191110_5_ > 0) {
                p_191110_1_.n_1700_B(p_191110_2_, p_191110_3_, 1);
                p_191110_1_.n_1700_B(p_191110_2_ + p_191110_4_.t_148_a(), p_191110_3_ + p_191110_4_.u_2550_I(), 0, 1);
                for (int i = 0; i < 8; ++i) {
                    b_257_Y direction = b_257_Y.J_1907_R(this.n_1700_B.nextInt(4));
                    if (direction == p_191110_4_.u_1723_Y() || direction == b_257_Y.u_1723_Y && this.n_1700_B.nextBoolean()) continue;
                    int j = p_191110_2_ + p_191110_4_.t_148_a();
                    int k = p_191110_3_ + p_191110_4_.u_2550_I();
                    if (p_191110_1_.n_1700_B(j + direction.t_148_a(), k + direction.u_2550_I()) != 0 || p_191110_1_.n_1700_B(j + direction.t_148_a() * 2, k + direction.u_2550_I() * 2) != 0) continue;
                    this.n_1700_B(p_191110_1_, p_191110_2_ + p_191110_4_.t_148_a() + direction.t_148_a(), p_191110_3_ + p_191110_4_.u_2550_I() + direction.u_2550_I(), direction, p_191110_5_ - 1);
                    break;
                }
                b_257_Y direction1 = p_191110_4_.v_4262_N();
                b_257_Y direction2 = p_191110_4_.w_1484_f();
                p_191110_1_.n_1700_B(p_191110_2_ + direction1.t_148_a(), p_191110_3_ + direction1.u_2550_I(), 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + direction2.t_148_a(), p_191110_3_ + direction2.u_2550_I(), 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + p_191110_4_.t_148_a() + direction1.t_148_a(), p_191110_3_ + p_191110_4_.u_2550_I() + direction1.u_2550_I(), 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + p_191110_4_.t_148_a() + direction2.t_148_a(), p_191110_3_ + p_191110_4_.u_2550_I() + direction2.u_2550_I(), 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + p_191110_4_.t_148_a() * 2, p_191110_3_ + p_191110_4_.u_2550_I() * 2, 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + direction1.t_148_a() * 2, p_191110_3_ + direction1.u_2550_I() * 2, 0, 2);
                p_191110_1_.n_1700_B(p_191110_2_ + direction2.t_148_a() * 2, p_191110_3_ + direction2.u_2550_I() * 2, 0, 2);
            }
        }

        private boolean n_1700_B(w_1484_f p_191111_1_) {
            boolean flag = false;
            for (int i = 0; i < p_191111_1_.R_4764_Y; ++i) {
                for (int j = 0; j < p_191111_1_.J_1907_R; ++j) {
                    if (p_191111_1_.n_1700_B(j, i) != 0) continue;
                    int k = 0;
                    k += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j + 1, i) ? 1 : 0;
                    k += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j - 1, i) ? 1 : 0;
                    k += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j, i + 1) ? 1 : 0;
                    if ((k += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j, i - 1) ? 1 : 0) >= 3) {
                        p_191111_1_.n_1700_B(j, i, 2);
                        flag = true;
                        continue;
                    }
                    if (k != 2) continue;
                    int l = 0;
                    l += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j + 1, i + 1) ? 1 : 0;
                    l += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j - 1, i + 1) ? 1 : 0;
                    l += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j + 1, i - 1) ? 1 : 0;
                    if ((l += lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191111_1_, j - 1, i - 1) ? 1 : 0) > 1) continue;
                    p_191111_1_.n_1700_B(j, i, 2);
                    flag = true;
                }
            }
            return flag;
        }

        private void n_1700_B() {
            ArrayList list = Lists.newArrayList();
            w_1484_f woodlandmansionpieces$simplegrid = this.G_564_y[1];
            for (int i = 0; i < this.R_4764_Y.R_4764_Y; ++i) {
                for (int j = 0; j < this.R_4764_Y.J_1907_R; ++j) {
                    int k = woodlandmansionpieces$simplegrid.n_1700_B(j, i);
                    int l = k & 0xF0000;
                    if (l != 131072 || (k & 0x200000) != 0x200000) continue;
                    list.add(new Tuple<Integer, Integer>(j, i));
                }
            }
            if (list.isEmpty()) {
                this.R_4764_Y.n_1700_B(0, 0, this.R_4764_Y.J_1907_R, this.R_4764_Y.R_4764_Y, 5);
            } else {
                Tuple tuple = (Tuple)list.get(this.n_1700_B.nextInt(list.size()));
                int l1 = woodlandmansionpieces$simplegrid.n_1700_B((Integer)tuple.n_1700_B(), (Integer)tuple.J_1907_R());
                woodlandmansionpieces$simplegrid.n_1700_B((Integer)tuple.n_1700_B(), (Integer)tuple.J_1907_R(), l1 | 0x400000);
                b_257_Y direction1 = this.J_1907_R(this.J_1907_R, (Integer)tuple.n_1700_B(), (Integer)tuple.J_1907_R(), 1, l1 & 0xFFFF);
                int i2 = (Integer)tuple.n_1700_B() + direction1.t_148_a();
                int i1 = (Integer)tuple.J_1907_R() + direction1.u_2550_I();
                for (int j1 = 0; j1 < this.R_4764_Y.R_4764_Y; ++j1) {
                    for (int k1 = 0; k1 < this.R_4764_Y.J_1907_R; ++k1) {
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(this.J_1907_R, k1, j1)) {
                            this.R_4764_Y.n_1700_B(k1, j1, 5);
                            continue;
                        }
                        if (k1 == (Integer)tuple.n_1700_B() && j1 == (Integer)tuple.J_1907_R()) {
                            this.R_4764_Y.n_1700_B(k1, j1, 3);
                            continue;
                        }
                        if (k1 != i2 || j1 != i1) continue;
                        this.R_4764_Y.n_1700_B(k1, j1, 3);
                        this.G_564_y[2].n_1700_B(k1, j1, 0x800000);
                    }
                }
                ArrayList list1 = Lists.newArrayList();
                for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                    if (this.R_4764_Y.n_1700_B(i2 + direction.t_148_a(), i1 + direction.u_2550_I()) != 0) continue;
                    list1.add(direction);
                }
                if (list1.isEmpty()) {
                    this.R_4764_Y.n_1700_B(0, 0, this.R_4764_Y.J_1907_R, this.R_4764_Y.R_4764_Y, 5);
                    woodlandmansionpieces$simplegrid.n_1700_B((Integer)tuple.n_1700_B(), (Integer)tuple.J_1907_R(), l1);
                } else {
                    b_257_Y direction2 = (b_257_Y)list1.get(this.n_1700_B.nextInt(list1.size()));
                    this.n_1700_B(this.R_4764_Y, i2 + direction2.t_148_a(), i1 + direction2.u_2550_I(), direction2, 4);
                    while (this.n_1700_B(this.R_4764_Y)) {
                    }
                }
            }
        }

        private void n_1700_B(w_1484_f p_191116_1_, w_1484_f p_191116_2_) {
            ArrayList list = Lists.newArrayList();
            for (int i = 0; i < p_191116_1_.R_4764_Y; ++i) {
                for (int j = 0; j < p_191116_1_.J_1907_R; ++j) {
                    if (p_191116_1_.n_1700_B(j, i) != 2) continue;
                    list.add(new Tuple<Integer, Integer>(j, i));
                }
            }
            Collections.shuffle(list, this.n_1700_B);
            int k3 = 10;
            for (Tuple tuple : list) {
                int l;
                int k = (Integer)tuple.n_1700_B();
                if (p_191116_2_.n_1700_B(k, l = ((Integer)tuple.J_1907_R()).intValue()) != 0) continue;
                int i1 = k;
                int j1 = k;
                int k1 = l;
                int l1 = l;
                int i2 = 65536;
                if (p_191116_2_.n_1700_B(k + 1, l) == 0 && p_191116_2_.n_1700_B(k, l + 1) == 0 && p_191116_2_.n_1700_B(k + 1, l + 1) == 0 && p_191116_1_.n_1700_B(k + 1, l) == 2 && p_191116_1_.n_1700_B(k, l + 1) == 2 && p_191116_1_.n_1700_B(k + 1, l + 1) == 2) {
                    j1 = k + 1;
                    l1 = l + 1;
                    i2 = 262144;
                } else if (p_191116_2_.n_1700_B(k - 1, l) == 0 && p_191116_2_.n_1700_B(k, l + 1) == 0 && p_191116_2_.n_1700_B(k - 1, l + 1) == 0 && p_191116_1_.n_1700_B(k - 1, l) == 2 && p_191116_1_.n_1700_B(k, l + 1) == 2 && p_191116_1_.n_1700_B(k - 1, l + 1) == 2) {
                    i1 = k - 1;
                    l1 = l + 1;
                    i2 = 262144;
                } else if (p_191116_2_.n_1700_B(k - 1, l) == 0 && p_191116_2_.n_1700_B(k, l - 1) == 0 && p_191116_2_.n_1700_B(k - 1, l - 1) == 0 && p_191116_1_.n_1700_B(k - 1, l) == 2 && p_191116_1_.n_1700_B(k, l - 1) == 2 && p_191116_1_.n_1700_B(k - 1, l - 1) == 2) {
                    i1 = k - 1;
                    k1 = l - 1;
                    i2 = 262144;
                } else if (p_191116_2_.n_1700_B(k + 1, l) == 0 && p_191116_1_.n_1700_B(k + 1, l) == 2) {
                    j1 = k + 1;
                    i2 = 131072;
                } else if (p_191116_2_.n_1700_B(k, l + 1) == 0 && p_191116_1_.n_1700_B(k, l + 1) == 2) {
                    l1 = l + 1;
                    i2 = 131072;
                } else if (p_191116_2_.n_1700_B(k - 1, l) == 0 && p_191116_1_.n_1700_B(k - 1, l) == 2) {
                    i1 = k - 1;
                    i2 = 131072;
                } else if (p_191116_2_.n_1700_B(k, l - 1) == 0 && p_191116_1_.n_1700_B(k, l - 1) == 2) {
                    k1 = l - 1;
                    i2 = 131072;
                }
                int j2 = this.n_1700_B.nextBoolean() ? i1 : j1;
                int k2 = this.n_1700_B.nextBoolean() ? k1 : l1;
                int l2 = 0x200000;
                if (!p_191116_1_.J_1907_R(j2, k2, 1)) {
                    j2 = j2 == i1 ? j1 : i1;
                    int n = k2 = k2 == k1 ? l1 : k1;
                    if (!p_191116_1_.J_1907_R(j2, k2, 1)) {
                        int n2 = k2 = k2 == k1 ? l1 : k1;
                        if (!p_191116_1_.J_1907_R(j2, k2, 1)) {
                            j2 = j2 == i1 ? j1 : i1;
                            int n3 = k2 = k2 == k1 ? l1 : k1;
                            if (!p_191116_1_.J_1907_R(j2, k2, 1)) {
                                l2 = 0;
                                j2 = i1;
                                k2 = k1;
                            }
                        }
                    }
                }
                for (int i3 = k1; i3 <= l1; ++i3) {
                    for (int j3 = i1; j3 <= j1; ++j3) {
                        if (j3 == j2 && i3 == k2) {
                            p_191116_2_.n_1700_B(j3, i3, 0x100000 | l2 | i2 | k3);
                            continue;
                        }
                        p_191116_2_.n_1700_B(j3, i3, i2 | k3);
                    }
                }
                ++k3;
            }
        }
    }

    static class P_1922_E {
        private final b_2085_h n_1700_B;
        private final Random J_1907_R;
        private int R_4764_Y;
        private int G_564_y;

        public P_1922_E(b_2085_h p_i47361_1_, Random p_i47361_2_) {
            this.n_1700_B = p_i47361_1_;
            this.J_1907_R = p_i47361_2_;
        }

        public void n_1700_B(c_1514_x p_191125_1_, W_2163_m p_191125_2_, List<R_4764_Y> p_191125_3_, J_1907_R p_191125_4_) {
            G_564_y woodlandmansionpieces$placementdata = new G_564_y();
            woodlandmansionpieces$placementdata.J_1907_R = p_191125_1_;
            woodlandmansionpieces$placementdata.n_1700_B = p_191125_2_;
            woodlandmansionpieces$placementdata.R_4764_Y = "wall_flat";
            G_564_y woodlandmansionpieces$placementdata1 = new G_564_y();
            this.n_1700_B(p_191125_3_, woodlandmansionpieces$placementdata);
            woodlandmansionpieces$placementdata1.J_1907_R = woodlandmansionpieces$placementdata.J_1907_R.up(8);
            woodlandmansionpieces$placementdata1.n_1700_B = woodlandmansionpieces$placementdata.n_1700_B;
            woodlandmansionpieces$placementdata1.R_4764_Y = "wall_window";
            if (!p_191125_3_.isEmpty()) {
                // empty if block
            }
            w_1484_f woodlandmansionpieces$simplegrid = p_191125_4_.J_1907_R;
            w_1484_f woodlandmansionpieces$simplegrid1 = p_191125_4_.R_4764_Y;
            this.R_4764_Y = p_191125_4_.P_1922_E + 1;
            this.G_564_y = p_191125_4_.u_1723_Y + 1;
            int i = p_191125_4_.P_1922_E + 1;
            int j = p_191125_4_.u_1723_Y;
            this.n_1700_B(p_191125_3_, woodlandmansionpieces$placementdata, woodlandmansionpieces$simplegrid, b_257_Y.G_564_y, this.R_4764_Y, this.G_564_y, i, j);
            this.n_1700_B(p_191125_3_, woodlandmansionpieces$placementdata1, woodlandmansionpieces$simplegrid, b_257_Y.G_564_y, this.R_4764_Y, this.G_564_y, i, j);
            G_564_y woodlandmansionpieces$placementdata2 = new G_564_y();
            woodlandmansionpieces$placementdata2.J_1907_R = woodlandmansionpieces$placementdata.J_1907_R.up(19);
            woodlandmansionpieces$placementdata2.n_1700_B = woodlandmansionpieces$placementdata.n_1700_B;
            woodlandmansionpieces$placementdata2.R_4764_Y = "wall_window";
            boolean flag = false;
            for (int k = 0; k < woodlandmansionpieces$simplegrid1.R_4764_Y && !flag; ++k) {
                for (int l = woodlandmansionpieces$simplegrid1.J_1907_R - 1; l >= 0 && !flag; --l) {
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(woodlandmansionpieces$simplegrid1, l, k)) continue;
                    woodlandmansionpieces$placementdata2.J_1907_R = woodlandmansionpieces$placementdata2.J_1907_R.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 8 + (k - this.G_564_y) * 8);
                    woodlandmansionpieces$placementdata2.J_1907_R = woodlandmansionpieces$placementdata2.J_1907_R.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), (l - this.R_4764_Y) * 8);
                    this.J_1907_R(p_191125_3_, woodlandmansionpieces$placementdata2);
                    this.n_1700_B(p_191125_3_, woodlandmansionpieces$placementdata2, woodlandmansionpieces$simplegrid1, b_257_Y.G_564_y, l, k, l, k);
                    flag = true;
                }
            }
            this.n_1700_B(p_191125_3_, p_191125_1_.up(16), p_191125_2_, woodlandmansionpieces$simplegrid, woodlandmansionpieces$simplegrid1);
            this.n_1700_B(p_191125_3_, p_191125_1_.up(27), p_191125_2_, woodlandmansionpieces$simplegrid1, (w_1484_f)null);
            if (!p_191125_3_.isEmpty()) {
                // empty if block
            }
            u_1723_Y[] awoodlandmansionpieces$roomcollection = new u_1723_Y[]{new n_1700_B(), new v_4262_N(), new t_148_a()};
            for (int l2 = 0; l2 < 3; ++l2) {
                c_1514_x blockpos = p_191125_1_.up(8 * l2 + (l2 == 2 ? 3 : 0));
                w_1484_f woodlandmansionpieces$simplegrid2 = p_191125_4_.G_564_y[l2];
                w_1484_f woodlandmansionpieces$simplegrid3 = l2 == 2 ? woodlandmansionpieces$simplegrid1 : woodlandmansionpieces$simplegrid;
                String s = l2 == 0 ? "carpet_south_1" : "carpet_south_2";
                String s1 = l2 == 0 ? "carpet_west_1" : "carpet_west_2";
                for (int i1 = 0; i1 < woodlandmansionpieces$simplegrid3.R_4764_Y; ++i1) {
                    for (int j1 = 0; j1 < woodlandmansionpieces$simplegrid3.J_1907_R; ++j1) {
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(j1, i1) != 1) continue;
                        c_1514_x blockpos1 = blockpos.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 8 + (i1 - this.G_564_y) * 8);
                        blockpos1 = blockpos1.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), (j1 - this.R_4764_Y) * 8);
                        p_191125_3_.add(new R_4764_Y(this.n_1700_B, "corridor_floor", blockpos1, p_191125_2_));
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(j1, i1 - 1) == 1 || (woodlandmansionpieces$simplegrid2.n_1700_B(j1, i1 - 1) & 0x800000) == 0x800000) {
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, "carpet_north", blockpos1.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), 1).up(), p_191125_2_));
                        }
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(j1 + 1, i1) == 1 || (woodlandmansionpieces$simplegrid2.n_1700_B(j1 + 1, i1) & 0x800000) == 0x800000) {
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, "carpet_east", blockpos1.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 1).offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), 5).up(), p_191125_2_));
                        }
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(j1, i1 + 1) == 1 || (woodlandmansionpieces$simplegrid2.n_1700_B(j1, i1 + 1) & 0x800000) == 0x800000) {
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, s, blockpos1.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 5).offset(p_191125_2_.n_1700_B(b_257_Y.P_1922_E), 1), p_191125_2_));
                        }
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(j1 - 1, i1) != 1 && (woodlandmansionpieces$simplegrid2.n_1700_B(j1 - 1, i1) & 0x800000) != 0x800000) continue;
                        p_191125_3_.add(new R_4764_Y(this.n_1700_B, s1, blockpos1.offset(p_191125_2_.n_1700_B(b_257_Y.P_1922_E), 1).offset(p_191125_2_.n_1700_B(b_257_Y.R_4764_Y), 1), p_191125_2_));
                    }
                }
                String s2 = l2 == 0 ? "indoors_wall_1" : "indoors_wall_2";
                String s3 = l2 == 0 ? "indoors_door_1" : "indoors_door_2";
                ArrayList list = Lists.newArrayList();
                for (int k1 = 0; k1 < woodlandmansionpieces$simplegrid3.R_4764_Y; ++k1) {
                    for (int l1 = 0; l1 < woodlandmansionpieces$simplegrid3.J_1907_R; ++l1) {
                        boolean flag1;
                        boolean bl = flag1 = l2 == 2 && woodlandmansionpieces$simplegrid3.n_1700_B(l1, k1) == 3;
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(l1, k1) != 2 && !flag1) continue;
                        int i2 = woodlandmansionpieces$simplegrid2.n_1700_B(l1, k1);
                        int j2 = i2 & 0xF0000;
                        int k2 = i2 & 0xFFFF;
                        flag1 = flag1 && (i2 & 0x800000) == 0x800000;
                        list.clear();
                        if ((i2 & 0x200000) == 0x200000) {
                            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                                if (woodlandmansionpieces$simplegrid3.n_1700_B(l1 + direction.t_148_a(), k1 + direction.u_2550_I()) != 1) continue;
                                list.add(direction);
                            }
                        }
                        b_257_Y direction1 = null;
                        if (!list.isEmpty()) {
                            direction1 = (b_257_Y)list.get(this.J_1907_R.nextInt(list.size()));
                        } else if ((i2 & 0x100000) == 0x100000) {
                            direction1 = b_257_Y.J_1907_R;
                        }
                        c_1514_x blockpos3 = blockpos.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 8 + (k1 - this.G_564_y) * 8);
                        blockpos3 = blockpos3.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), -1 + (l1 - this.R_4764_Y) * 8);
                        if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(woodlandmansionpieces$simplegrid3, l1 - 1, k1) && !p_191125_4_.n_1700_B(woodlandmansionpieces$simplegrid3, l1 - 1, k1, l2, k2)) {
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, direction1 == b_257_Y.P_1922_E ? s3 : s2, blockpos3, p_191125_2_));
                        }
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(l1 + 1, k1) == 1 && !flag1) {
                            c_1514_x blockpos2 = blockpos3.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), 8);
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, direction1 == b_257_Y.u_1723_Y ? s3 : s2, blockpos2, p_191125_2_));
                        }
                        if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(woodlandmansionpieces$simplegrid3, l1, k1 + 1) && !p_191125_4_.n_1700_B(woodlandmansionpieces$simplegrid3, l1, k1 + 1, l2, k2)) {
                            c_1514_x blockpos4 = blockpos3.offset(p_191125_2_.n_1700_B(b_257_Y.G_564_y), 7);
                            blockpos4 = blockpos4.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), 7);
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, direction1 == b_257_Y.G_564_y ? s3 : s2, blockpos4, p_191125_2_.n_1700_B(W_2163_m.J_1907_R)));
                        }
                        if (woodlandmansionpieces$simplegrid3.n_1700_B(l1, k1 - 1) == 1 && !flag1) {
                            c_1514_x blockpos5 = blockpos3.offset(p_191125_2_.n_1700_B(b_257_Y.R_4764_Y), 1);
                            blockpos5 = blockpos5.offset(p_191125_2_.n_1700_B(b_257_Y.u_1723_Y), 7);
                            p_191125_3_.add(new R_4764_Y(this.n_1700_B, direction1 == b_257_Y.R_4764_Y ? s3 : s2, blockpos5, p_191125_2_.n_1700_B(W_2163_m.J_1907_R)));
                        }
                        if (j2 == 65536) {
                            this.n_1700_B(p_191125_3_, blockpos3, p_191125_2_, direction1, awoodlandmansionpieces$roomcollection[l2]);
                            continue;
                        }
                        if (j2 == 131072 && direction1 != null) {
                            b_257_Y direction3 = p_191125_4_.J_1907_R(woodlandmansionpieces$simplegrid3, l1, k1, l2, k2);
                            boolean flag2 = (i2 & 0x400000) == 0x400000;
                            this.n_1700_B(p_191125_3_, blockpos3, p_191125_2_, direction3, direction1, awoodlandmansionpieces$roomcollection[l2], flag2);
                            continue;
                        }
                        if (j2 == 262144 && direction1 != null && direction1 != b_257_Y.J_1907_R) {
                            b_257_Y direction2 = direction1.v_4262_N();
                            if (!p_191125_4_.n_1700_B(woodlandmansionpieces$simplegrid3, l1 + direction2.t_148_a(), k1 + direction2.u_2550_I(), l2, k2)) {
                                direction2 = direction2.u_1723_Y();
                            }
                            this.n_1700_B(p_191125_3_, blockpos3, p_191125_2_, direction2, direction1, awoodlandmansionpieces$roomcollection[l2]);
                            continue;
                        }
                        if (j2 != 262144 || direction1 != b_257_Y.J_1907_R) continue;
                        this.n_1700_B(p_191125_3_, blockpos3, p_191125_2_, awoodlandmansionpieces$roomcollection[l2]);
                    }
                }
            }
        }

        private void n_1700_B(List<R_4764_Y> p_191130_1_, G_564_y p_191130_2_, w_1484_f p_191130_3_, b_257_Y p_191130_4_, int p_191130_5_, int p_191130_6_, int p_191130_7_, int p_191130_8_) {
            int i = p_191130_5_;
            int j = p_191130_6_;
            b_257_Y direction = p_191130_4_;
            do {
                if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191130_3_, i + p_191130_4_.t_148_a(), j + p_191130_4_.u_2550_I())) {
                    this.R_4764_Y(p_191130_1_, p_191130_2_);
                    p_191130_4_ = p_191130_4_.v_4262_N();
                    if (i == p_191130_7_ && j == p_191130_8_ && direction == p_191130_4_) continue;
                    this.J_1907_R(p_191130_1_, p_191130_2_);
                    continue;
                }
                if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191130_3_, i + p_191130_4_.t_148_a(), j + p_191130_4_.u_2550_I()) && lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191130_3_, i + p_191130_4_.t_148_a() + p_191130_4_.w_1484_f().t_148_a(), j + p_191130_4_.u_2550_I() + p_191130_4_.w_1484_f().u_2550_I())) {
                    this.G_564_y(p_191130_1_, p_191130_2_);
                    i += p_191130_4_.t_148_a();
                    j += p_191130_4_.u_2550_I();
                    p_191130_4_ = p_191130_4_.w_1484_f();
                    continue;
                }
                if ((i += p_191130_4_.t_148_a()) == p_191130_7_ && (j += p_191130_4_.u_2550_I()) == p_191130_8_ && direction == p_191130_4_) continue;
                this.J_1907_R(p_191130_1_, p_191130_2_);
            } while (i != p_191130_7_ || j != p_191130_8_ || direction != p_191130_4_);
        }

        private void n_1700_B(List<R_4764_Y> p_191123_1_, c_1514_x p_191123_2_, W_2163_m p_191123_3_, w_1484_f p_191123_4_, @Nullable w_1484_f p_191123_5_) {
            for (int i = 0; i < p_191123_4_.R_4764_Y; ++i) {
                for (int j = 0; j < p_191123_4_.J_1907_R; ++j) {
                    boolean flag;
                    c_1514_x lvt_8_3_ = p_191123_2_.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 8 + (i - this.G_564_y) * 8);
                    lvt_8_3_ = lvt_8_3_.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), (j - this.R_4764_Y) * 8);
                    boolean bl = flag = p_191123_5_ != null && lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_5_, j, i);
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j, i) || flag) continue;
                    p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof", lvt_8_3_.up(3), p_191123_3_));
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j + 1, i)) {
                        c_1514_x blockpos1 = lvt_8_3_.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 6);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_front", blockpos1, p_191123_3_));
                    }
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j - 1, i)) {
                        c_1514_x blockpos5 = lvt_8_3_.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 0);
                        blockpos5 = blockpos5.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 7);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_front", blockpos5, p_191123_3_.n_1700_B(W_2163_m.R_4764_Y)));
                    }
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j, i - 1)) {
                        c_1514_x blockpos6 = lvt_8_3_.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 1);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_front", blockpos6, p_191123_3_.n_1700_B(W_2163_m.G_564_y)));
                    }
                    if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j, i + 1)) continue;
                    c_1514_x blockpos7 = lvt_8_3_.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 6);
                    blockpos7 = blockpos7.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 6);
                    p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_front", blockpos7, p_191123_3_.n_1700_B(W_2163_m.J_1907_R)));
                }
            }
            if (p_191123_5_ != null) {
                for (int k = 0; k < p_191123_4_.R_4764_Y; ++k) {
                    for (int i1 = 0; i1 < p_191123_4_.J_1907_R; ++i1) {
                        c_1514_x blockpos3 = p_191123_2_.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 8 + (k - this.G_564_y) * 8);
                        blockpos3 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), (i1 - this.R_4764_Y) * 8);
                        boolean flag1 = lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_5_, i1, k);
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k) || !flag1) continue;
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1 + 1, k)) {
                            c_1514_x blockpos8 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall", blockpos8, p_191123_3_));
                        }
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1 - 1, k)) {
                            c_1514_x blockpos9 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 1);
                            blockpos9 = blockpos9.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 6);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall", blockpos9, p_191123_3_.n_1700_B(W_2163_m.R_4764_Y)));
                        }
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k - 1)) {
                            c_1514_x blockpos10 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 0);
                            blockpos10 = blockpos10.offset(p_191123_3_.n_1700_B(b_257_Y.R_4764_Y), 1);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall", blockpos10, p_191123_3_.n_1700_B(W_2163_m.G_564_y)));
                        }
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k + 1)) {
                            c_1514_x blockpos11 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 6);
                            blockpos11 = blockpos11.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 7);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall", blockpos11, p_191123_3_.n_1700_B(W_2163_m.J_1907_R)));
                        }
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1 + 1, k)) {
                            if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k - 1)) {
                                c_1514_x blockpos12 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                                blockpos12 = blockpos12.offset(p_191123_3_.n_1700_B(b_257_Y.R_4764_Y), 2);
                                p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall_corner", blockpos12, p_191123_3_));
                            }
                            if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k + 1)) {
                                c_1514_x blockpos13 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 8);
                                blockpos13 = blockpos13.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 7);
                                p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall_corner", blockpos13, p_191123_3_.n_1700_B(W_2163_m.J_1907_R)));
                            }
                        }
                        if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1 - 1, k)) continue;
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k - 1)) {
                            c_1514_x blockpos14 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 2);
                            blockpos14 = blockpos14.offset(p_191123_3_.n_1700_B(b_257_Y.R_4764_Y), 1);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall_corner", blockpos14, p_191123_3_.n_1700_B(W_2163_m.G_564_y)));
                        }
                        if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, i1, k + 1)) continue;
                        c_1514_x blockpos15 = blockpos3.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 1);
                        blockpos15 = blockpos15.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 8);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "small_wall_corner", blockpos15, p_191123_3_.n_1700_B(W_2163_m.R_4764_Y)));
                    }
                }
            }
            for (int l = 0; l < p_191123_4_.R_4764_Y; ++l) {
                for (int j1 = 0; j1 < p_191123_4_.J_1907_R; ++j1) {
                    boolean flag2;
                    c_1514_x blockpos4 = p_191123_2_.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 8 + (l - this.G_564_y) * 8);
                    blockpos4 = blockpos4.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), (j1 - this.R_4764_Y) * 8);
                    boolean bl = flag2 = p_191123_5_ != null && lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_5_, j1, l);
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1, l) || flag2) continue;
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 + 1, l)) {
                        c_1514_x blockpos16 = blockpos4.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 6);
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1, l + 1)) {
                            c_1514_x blockpos2 = blockpos16.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 6);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_corner", blockpos2, p_191123_3_));
                        } else if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 + 1, l + 1)) {
                            c_1514_x blockpos18 = blockpos16.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 5);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_inner_corner", blockpos18, p_191123_3_));
                        }
                        if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1, l - 1)) {
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_corner", blockpos16, p_191123_3_.n_1700_B(W_2163_m.G_564_y)));
                        } else if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 + 1, l - 1)) {
                            c_1514_x blockpos19 = blockpos4.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 9);
                            blockpos19 = blockpos19.offset(p_191123_3_.n_1700_B(b_257_Y.R_4764_Y), 2);
                            p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_inner_corner", blockpos19, p_191123_3_.n_1700_B(W_2163_m.J_1907_R)));
                        }
                    }
                    if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 - 1, l)) continue;
                    c_1514_x blockpos17 = blockpos4.offset(p_191123_3_.n_1700_B(b_257_Y.u_1723_Y), 0);
                    blockpos17 = blockpos17.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 0);
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1, l + 1)) {
                        c_1514_x blockpos20 = blockpos17.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 6);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_corner", blockpos20, p_191123_3_.n_1700_B(W_2163_m.J_1907_R)));
                    } else if (lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 - 1, l + 1)) {
                        c_1514_x blockpos21 = blockpos17.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 8);
                        blockpos21 = blockpos21.offset(p_191123_3_.n_1700_B(b_257_Y.P_1922_E), 3);
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_inner_corner", blockpos21, p_191123_3_.n_1700_B(W_2163_m.G_564_y)));
                    }
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1, l - 1)) {
                        p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_corner", blockpos17, p_191123_3_.n_1700_B(W_2163_m.R_4764_Y)));
                        continue;
                    }
                    if (!lightning.product.WoodlandMansionPieces$J_1907_R.n_1700_B(p_191123_4_, j1 - 1, l - 1)) continue;
                    c_1514_x blockpos22 = blockpos17.offset(p_191123_3_.n_1700_B(b_257_Y.G_564_y), 1);
                    p_191123_1_.add(new R_4764_Y(this.n_1700_B, "roof_inner_corner", blockpos22, p_191123_3_.n_1700_B(W_2163_m.R_4764_Y)));
                }
            }
        }

        private void n_1700_B(List<R_4764_Y> p_191133_1_, G_564_y p_191133_2_) {
            b_257_Y direction = p_191133_2_.n_1700_B.n_1700_B(b_257_Y.P_1922_E);
            p_191133_1_.add(new R_4764_Y(this.n_1700_B, "entrance", p_191133_2_.J_1907_R.offset(direction, 9), p_191133_2_.n_1700_B));
            p_191133_2_.J_1907_R = p_191133_2_.J_1907_R.offset(p_191133_2_.n_1700_B.n_1700_B(b_257_Y.G_564_y), 16);
        }

        private void J_1907_R(List<R_4764_Y> p_191131_1_, G_564_y p_191131_2_) {
            p_191131_1_.add(new R_4764_Y(this.n_1700_B, p_191131_2_.R_4764_Y, p_191131_2_.J_1907_R.offset(p_191131_2_.n_1700_B.n_1700_B(b_257_Y.u_1723_Y), 7), p_191131_2_.n_1700_B));
            p_191131_2_.J_1907_R = p_191131_2_.J_1907_R.offset(p_191131_2_.n_1700_B.n_1700_B(b_257_Y.G_564_y), 8);
        }

        private void R_4764_Y(List<R_4764_Y> p_191124_1_, G_564_y p_191124_2_) {
            p_191124_2_.J_1907_R = p_191124_2_.J_1907_R.offset(p_191124_2_.n_1700_B.n_1700_B(b_257_Y.G_564_y), -1);
            p_191124_1_.add(new R_4764_Y(this.n_1700_B, "wall_corner", p_191124_2_.J_1907_R, p_191124_2_.n_1700_B));
            p_191124_2_.J_1907_R = p_191124_2_.J_1907_R.offset(p_191124_2_.n_1700_B.n_1700_B(b_257_Y.G_564_y), -7);
            p_191124_2_.J_1907_R = p_191124_2_.J_1907_R.offset(p_191124_2_.n_1700_B.n_1700_B(b_257_Y.P_1922_E), -6);
            p_191124_2_.n_1700_B = p_191124_2_.n_1700_B.n_1700_B(W_2163_m.J_1907_R);
        }

        private void G_564_y(List<R_4764_Y> p_191126_1_, G_564_y p_191126_2_) {
            p_191126_2_.J_1907_R = p_191126_2_.J_1907_R.offset(p_191126_2_.n_1700_B.n_1700_B(b_257_Y.G_564_y), 6);
            p_191126_2_.J_1907_R = p_191126_2_.J_1907_R.offset(p_191126_2_.n_1700_B.n_1700_B(b_257_Y.u_1723_Y), 8);
            p_191126_2_.n_1700_B = p_191126_2_.n_1700_B.n_1700_B(W_2163_m.G_564_y);
        }

        private void n_1700_B(List<R_4764_Y> p_191129_1_, c_1514_x p_191129_2_, W_2163_m p_191129_3_, b_257_Y p_191129_4_, u_1723_Y p_191129_5_) {
            W_2163_m rotation = W_2163_m.n_1700_B;
            String s = p_191129_5_.n_1700_B(this.J_1907_R);
            if (p_191129_4_ != b_257_Y.u_1723_Y) {
                if (p_191129_4_ == b_257_Y.R_4764_Y) {
                    rotation = rotation.n_1700_B(W_2163_m.G_564_y);
                } else if (p_191129_4_ == b_257_Y.P_1922_E) {
                    rotation = rotation.n_1700_B(W_2163_m.R_4764_Y);
                } else if (p_191129_4_ == b_257_Y.G_564_y) {
                    rotation = rotation.n_1700_B(W_2163_m.J_1907_R);
                } else {
                    s = p_191129_5_.J_1907_R(this.J_1907_R);
                }
            }
            c_1514_x blockpos = a_2886_t.n_1700_B(new c_1514_x(1, 0, 0), q_4099_E.n_1700_B, rotation, 7, 7);
            rotation = rotation.n_1700_B(p_191129_3_);
            blockpos = blockpos.rotate(p_191129_3_);
            c_1514_x blockpos1 = p_191129_2_.add(blockpos.getX(), 0, blockpos.getZ());
            p_191129_1_.add(new R_4764_Y(this.n_1700_B, s, blockpos1, rotation));
        }

        private void n_1700_B(List<R_4764_Y> p_191132_1_, c_1514_x p_191132_2_, W_2163_m p_191132_3_, b_257_Y p_191132_4_, b_257_Y p_191132_5_, u_1723_Y p_191132_6_, boolean p_191132_7_) {
            if (p_191132_5_ == b_257_Y.u_1723_Y && p_191132_4_ == b_257_Y.G_564_y) {
                c_1514_x blockpos13 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos13, p_191132_3_));
            } else if (p_191132_5_ == b_257_Y.u_1723_Y && p_191132_4_ == b_257_Y.R_4764_Y) {
                c_1514_x blockpos12 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                blockpos12 = blockpos12.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 6);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos12, p_191132_3_, q_4099_E.J_1907_R));
            } else if (p_191132_5_ == b_257_Y.P_1922_E && p_191132_4_ == b_257_Y.R_4764_Y) {
                c_1514_x blockpos11 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                blockpos11 = blockpos11.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 6);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos11, p_191132_3_.n_1700_B(W_2163_m.R_4764_Y)));
            } else if (p_191132_5_ == b_257_Y.P_1922_E && p_191132_4_ == b_257_Y.G_564_y) {
                c_1514_x blockpos10 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos10, p_191132_3_, q_4099_E.R_4764_Y));
            } else if (p_191132_5_ == b_257_Y.G_564_y && p_191132_4_ == b_257_Y.u_1723_Y) {
                c_1514_x blockpos9 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos9, p_191132_3_.n_1700_B(W_2163_m.J_1907_R), q_4099_E.J_1907_R));
            } else if (p_191132_5_ == b_257_Y.G_564_y && p_191132_4_ == b_257_Y.P_1922_E) {
                c_1514_x blockpos8 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos8, p_191132_3_.n_1700_B(W_2163_m.J_1907_R)));
            } else if (p_191132_5_ == b_257_Y.R_4764_Y && p_191132_4_ == b_257_Y.P_1922_E) {
                c_1514_x blockpos7 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                blockpos7 = blockpos7.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 6);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos7, p_191132_3_.n_1700_B(W_2163_m.J_1907_R), q_4099_E.R_4764_Y));
            } else if (p_191132_5_ == b_257_Y.R_4764_Y && p_191132_4_ == b_257_Y.u_1723_Y) {
                c_1514_x blockpos6 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                blockpos6 = blockpos6.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 6);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.n_1700_B(this.J_1907_R, p_191132_7_), blockpos6, p_191132_3_.n_1700_B(W_2163_m.G_564_y)));
            } else if (p_191132_5_ == b_257_Y.G_564_y && p_191132_4_ == b_257_Y.R_4764_Y) {
                c_1514_x blockpos5 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                blockpos5 = blockpos5.offset(p_191132_3_.n_1700_B(b_257_Y.R_4764_Y), 8);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.J_1907_R(this.J_1907_R, p_191132_7_), blockpos5, p_191132_3_));
            } else if (p_191132_5_ == b_257_Y.R_4764_Y && p_191132_4_ == b_257_Y.G_564_y) {
                c_1514_x blockpos4 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 7);
                blockpos4 = blockpos4.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 14);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.J_1907_R(this.J_1907_R, p_191132_7_), blockpos4, p_191132_3_.n_1700_B(W_2163_m.R_4764_Y)));
            } else if (p_191132_5_ == b_257_Y.P_1922_E && p_191132_4_ == b_257_Y.u_1723_Y) {
                c_1514_x blockpos3 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 15);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.J_1907_R(this.J_1907_R, p_191132_7_), blockpos3, p_191132_3_.n_1700_B(W_2163_m.J_1907_R)));
            } else if (p_191132_5_ == b_257_Y.u_1723_Y && p_191132_4_ == b_257_Y.P_1922_E) {
                c_1514_x blockpos2 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.P_1922_E), 7);
                blockpos2 = blockpos2.offset(p_191132_3_.n_1700_B(b_257_Y.G_564_y), 6);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.J_1907_R(this.J_1907_R, p_191132_7_), blockpos2, p_191132_3_.n_1700_B(W_2163_m.G_564_y)));
            } else if (p_191132_5_ == b_257_Y.J_1907_R && p_191132_4_ == b_257_Y.u_1723_Y) {
                c_1514_x blockpos1 = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 15);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.R_4764_Y(this.J_1907_R), blockpos1, p_191132_3_.n_1700_B(W_2163_m.J_1907_R)));
            } else if (p_191132_5_ == b_257_Y.J_1907_R && p_191132_4_ == b_257_Y.G_564_y) {
                c_1514_x blockpos = p_191132_2_.offset(p_191132_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
                blockpos = blockpos.offset(p_191132_3_.n_1700_B(b_257_Y.R_4764_Y), 0);
                p_191132_1_.add(new R_4764_Y(this.n_1700_B, p_191132_6_.R_4764_Y(this.J_1907_R), blockpos, p_191132_3_));
            }
        }

        private void n_1700_B(List<R_4764_Y> p_191127_1_, c_1514_x p_191127_2_, W_2163_m p_191127_3_, b_257_Y p_191127_4_, b_257_Y p_191127_5_, u_1723_Y p_191127_6_) {
            int i = 0;
            int j = 0;
            W_2163_m rotation = p_191127_3_;
            q_4099_E mirror = q_4099_E.n_1700_B;
            if (p_191127_5_ == b_257_Y.u_1723_Y && p_191127_4_ == b_257_Y.G_564_y) {
                i = -7;
            } else if (p_191127_5_ == b_257_Y.u_1723_Y && p_191127_4_ == b_257_Y.R_4764_Y) {
                i = -7;
                j = 6;
                mirror = q_4099_E.J_1907_R;
            } else if (p_191127_5_ == b_257_Y.R_4764_Y && p_191127_4_ == b_257_Y.u_1723_Y) {
                i = 1;
                j = 14;
                rotation = p_191127_3_.n_1700_B(W_2163_m.G_564_y);
            } else if (p_191127_5_ == b_257_Y.R_4764_Y && p_191127_4_ == b_257_Y.P_1922_E) {
                i = 7;
                j = 14;
                rotation = p_191127_3_.n_1700_B(W_2163_m.G_564_y);
                mirror = q_4099_E.J_1907_R;
            } else if (p_191127_5_ == b_257_Y.G_564_y && p_191127_4_ == b_257_Y.P_1922_E) {
                i = 7;
                j = -8;
                rotation = p_191127_3_.n_1700_B(W_2163_m.J_1907_R);
            } else if (p_191127_5_ == b_257_Y.G_564_y && p_191127_4_ == b_257_Y.u_1723_Y) {
                i = 1;
                j = -8;
                rotation = p_191127_3_.n_1700_B(W_2163_m.J_1907_R);
                mirror = q_4099_E.J_1907_R;
            } else if (p_191127_5_ == b_257_Y.P_1922_E && p_191127_4_ == b_257_Y.R_4764_Y) {
                i = 15;
                j = 6;
                rotation = p_191127_3_.n_1700_B(W_2163_m.R_4764_Y);
            } else if (p_191127_5_ == b_257_Y.P_1922_E && p_191127_4_ == b_257_Y.G_564_y) {
                i = 15;
                mirror = q_4099_E.R_4764_Y;
            }
            c_1514_x blockpos = p_191127_2_.offset(p_191127_3_.n_1700_B(b_257_Y.u_1723_Y), i);
            blockpos = blockpos.offset(p_191127_3_.n_1700_B(b_257_Y.G_564_y), j);
            p_191127_1_.add(new R_4764_Y(this.n_1700_B, p_191127_6_.G_564_y(this.J_1907_R), blockpos, rotation, mirror));
        }

        private void n_1700_B(List<R_4764_Y> p_191128_1_, c_1514_x p_191128_2_, W_2163_m p_191128_3_, u_1723_Y p_191128_4_) {
            c_1514_x blockpos = p_191128_2_.offset(p_191128_3_.n_1700_B(b_257_Y.u_1723_Y), 1);
            p_191128_1_.add(new R_4764_Y(this.n_1700_B, p_191128_4_.P_1922_E(this.J_1907_R), blockpos, p_191128_3_, q_4099_E.n_1700_B));
        }
    }

    static class t_148_a
    extends v_4262_N {
        private t_148_a() {
        }
    }

    static class w_1484_f {
        private final int[][] n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public w_1484_f(int p_i47358_1_, int p_i47358_2_, int p_i47358_3_) {
            this.J_1907_R = p_i47358_1_;
            this.R_4764_Y = p_i47358_2_;
            this.G_564_y = p_i47358_3_;
            this.n_1700_B = new int[p_i47358_1_][p_i47358_2_];
        }

        public void n_1700_B(int p_191144_1_, int p_191144_2_, int p_191144_3_) {
            if (p_191144_1_ >= 0 && p_191144_1_ < this.J_1907_R && p_191144_2_ >= 0 && p_191144_2_ < this.R_4764_Y) {
                this.n_1700_B[p_191144_1_][p_191144_2_] = p_191144_3_;
            }
        }

        public void n_1700_B(int p_191142_1_, int p_191142_2_, int p_191142_3_, int p_191142_4_, int p_191142_5_) {
            for (int i = p_191142_2_; i <= p_191142_4_; ++i) {
                for (int j = p_191142_1_; j <= p_191142_3_; ++j) {
                    this.n_1700_B(j, i, p_191142_5_);
                }
            }
        }

        public int n_1700_B(int p_191145_1_, int p_191145_2_) {
            return p_191145_1_ >= 0 && p_191145_1_ < this.J_1907_R && p_191145_2_ >= 0 && p_191145_2_ < this.R_4764_Y ? this.n_1700_B[p_191145_1_][p_191145_2_] : this.G_564_y;
        }

        public void n_1700_B(int p_197588_1_, int p_197588_2_, int p_197588_3_, int p_197588_4_) {
            if (this.n_1700_B(p_197588_1_, p_197588_2_) == p_197588_3_) {
                this.n_1700_B(p_197588_1_, p_197588_2_, p_197588_4_);
            }
        }

        public boolean J_1907_R(int p_191147_1_, int p_191147_2_, int p_191147_3_) {
            return this.n_1700_B(p_191147_1_ - 1, p_191147_2_) == p_191147_3_ || this.n_1700_B(p_191147_1_ + 1, p_191147_2_) == p_191147_3_ || this.n_1700_B(p_191147_1_, p_191147_2_ + 1) == p_191147_3_ || this.n_1700_B(p_191147_1_, p_191147_2_ - 1) == p_191147_3_;
        }
    }

    static class v_4262_N
    extends u_1723_Y {
        private v_4262_N() {
        }

        @Override
        public String n_1700_B(Random p_191104_1_) {
            return "1x1_b" + (p_191104_1_.nextInt(4) + 1);
        }

        @Override
        public String J_1907_R(Random p_191099_1_) {
            return "1x1_as" + (p_191099_1_.nextInt(4) + 1);
        }

        @Override
        public String n_1700_B(Random p_191100_1_, boolean p_191100_2_) {
            return p_191100_2_ ? "1x2_c_stairs" : "1x2_c" + (p_191100_1_.nextInt(4) + 1);
        }

        @Override
        public String J_1907_R(Random p_191098_1_, boolean p_191098_2_) {
            return p_191098_2_ ? "1x2_d_stairs" : "1x2_d" + (p_191098_1_.nextInt(5) + 1);
        }

        @Override
        public String R_4764_Y(Random p_191102_1_) {
            return "1x2_se" + (p_191102_1_.nextInt(1) + 1);
        }

        @Override
        public String G_564_y(Random p_191101_1_) {
            return "2x2_b" + (p_191101_1_.nextInt(5) + 1);
        }

        @Override
        public String P_1922_E(Random p_191103_1_) {
            return "2x2_s1";
        }
    }

    static abstract class u_1723_Y {
        private u_1723_Y() {
        }

        public abstract String n_1700_B(Random var1);

        public abstract String J_1907_R(Random var1);

        public abstract String n_1700_B(Random var1, boolean var2);

        public abstract String J_1907_R(Random var1, boolean var2);

        public abstract String R_4764_Y(Random var1);

        public abstract String G_564_y(Random var1);

        public abstract String P_1922_E(Random var1);
    }

    static class G_564_y {
        public W_2163_m n_1700_B;
        public c_1514_x J_1907_R;
        public String R_4764_Y;

        private G_564_y() {
        }
    }

    public static class R_4764_Y
    extends q_1616_l {
        private final String G_564_y;
        private final W_2163_m P_1922_E;
        private final q_4099_E u_1723_Y;

        public R_4764_Y(b_2085_h p_i47355_1_, String p_i47355_2_, c_1514_x p_i47355_3_, W_2163_m p_i47355_4_) {
            this(p_i47355_1_, p_i47355_2_, p_i47355_3_, p_i47355_4_, q_4099_E.n_1700_B);
        }

        public R_4764_Y(b_2085_h p_i47356_1_, String p_i47356_2_, c_1514_x p_i47356_3_, W_2163_m p_i47356_4_, q_4099_E p_i47356_5_) {
            super(StructurePieceType.g_2268_R, 0);
            this.G_564_y = p_i47356_2_;
            this.R_4764_Y = p_i47356_3_;
            this.P_1922_E = p_i47356_4_;
            this.u_1723_Y = p_i47356_5_;
            this.n_1700_B(p_i47356_1_);
        }

        public R_4764_Y(b_2085_h p_i50615_1_, U_2912_j p_i50615_2_) {
            super(StructurePieceType.g_2268_R, p_i50615_2_);
            this.G_564_y = p_i50615_2_.M_588_G("Template");
            this.P_1922_E = W_2163_m.valueOf(p_i50615_2_.M_588_G("Rot"));
            this.u_1723_Y = q_4099_E.valueOf(p_i50615_2_.M_588_G("Mi"));
            this.n_1700_B(p_i50615_1_);
        }

        private void n_1700_B(b_2085_h p_191081_1_) {
            a_2886_t template = p_191081_1_.n_1700_B(new g_2336_b("woodland_mansion/" + this.G_564_y));
            w_1748_S placementsettings = new w_1748_S().n_1700_B(true).n_1700_B(this.P_1922_E).n_1700_B(this.u_1723_Y).n_1700_B(r_4719_P.J_1907_R);
            this.n_1700_B(template, this.R_4764_Y, placementsettings);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Template", this.G_564_y);
            tagCompound.n_1700_B("Rot", this.J_1907_R.G_564_y().name());
            tagCompound.n_1700_B("Mi", this.J_1907_R.R_4764_Y().name());
        }

        @Override
        protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
            if (function.startsWith("Chest")) {
                W_2163_m rotation = this.J_1907_R.G_564_y();
                K_4074_S blockstate = a_3742_W.L_1362_X.multiplayerClientSuggestionProvider();
                if ("ChestWest".equals(function)) {
                    blockstate = (K_4074_S)blockstate.n_1700_B(v_3445_Z.h_1847_R, rotation.n_1700_B(b_257_Y.P_1922_E));
                } else if ("ChestEast".equals(function)) {
                    blockstate = (K_4074_S)blockstate.n_1700_B(v_3445_Z.h_1847_R, rotation.n_1700_B(b_257_Y.u_1723_Y));
                } else if ("ChestSouth".equals(function)) {
                    blockstate = (K_4074_S)blockstate.n_1700_B(v_3445_Z.h_1847_R, rotation.n_1700_B(b_257_Y.G_564_y));
                } else if ("ChestNorth".equals(function)) {
                    blockstate = (K_4074_S)blockstate.n_1700_B(v_3445_Z.h_1847_R, rotation.n_1700_B(b_257_Y.R_4764_Y));
                }
                this.n_1700_B(worldIn, sbb, rand, pos, o_4810_o.Y_1740_V, blockstate);
            } else {
                AbstractIllager abstractillagerentity;
                int b0 = -1;
                switch (function.hashCode()) {
                    case -1505748702: {
                        if (!function.equals("Warrior")) break;
                        b0 = 1;
                        break;
                    }
                    case 2390418: {
                        if (!function.equals("Mage")) break;
                        b0 = 0;
                    }
                }
                switch (b0) {
                    case 0: {
                        abstractillagerentity = t_5_h.C_2741_M.n_1700_B(worldIn.J_1907_R());
                        break;
                    }
                    case 1: {
                        abstractillagerentity = t_5_h.y_1700_S.n_1700_B(worldIn.J_1907_R());
                        break;
                    }
                    default: {
                        return;
                    }
                }
                abstractillagerentity.T_3594_S();
                abstractillagerentity.n_1700_B(pos, 0.0f, 0.0f);
                abstractillagerentity.n_1700_B(worldIn, worldIn.J_1907_R(abstractillagerentity.b_2312_j()), a_3160_D.G_564_y, (V_3157_k)null, null);
                worldIn.n_1700_B(abstractillagerentity);
                worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
            }
        }
    }

    static class n_1700_B
    extends u_1723_Y {
        private n_1700_B() {
        }

        @Override
        public String n_1700_B(Random p_191104_1_) {
            return "1x1_a" + (p_191104_1_.nextInt(5) + 1);
        }

        @Override
        public String J_1907_R(Random p_191099_1_) {
            return "1x1_as" + (p_191099_1_.nextInt(4) + 1);
        }

        @Override
        public String n_1700_B(Random p_191100_1_, boolean p_191100_2_) {
            return "1x2_a" + (p_191100_1_.nextInt(9) + 1);
        }

        @Override
        public String J_1907_R(Random p_191098_1_, boolean p_191098_2_) {
            return "1x2_b" + (p_191098_1_.nextInt(5) + 1);
        }

        @Override
        public String R_4764_Y(Random p_191102_1_) {
            return "1x2_s" + (p_191102_1_.nextInt(2) + 1);
        }

        @Override
        public String G_564_y(Random p_191101_1_) {
            return "2x2_a" + (p_191101_1_.nextInt(4) + 1);
        }

        @Override
        public String P_1922_E(Random p_191103_1_) {
            return "2x2_s1";
        }
    }
}


