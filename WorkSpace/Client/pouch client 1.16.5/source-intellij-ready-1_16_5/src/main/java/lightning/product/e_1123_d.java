/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FenceBlock;
import lightning.product.E_3771_B;
import lightning.product.BlockGetter;
import lightning.product.J_3017_d;
import lightning.product.WallTorchBlock;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.MinecartChest;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.j_4336_h;
import lightning.product.o_4810_o;
import lightning.product.q_2896_o;
import lightning.product.t_5_h;
import lightning.product.w_4059_h;
import lightning.product.w_801_N;
import lightning.product.z_1753_f;

public class e_1123_d {
    private static R_4764_Y n_1700_B(List<E_3771_B> p_189940_0_, Random p_189940_1_, int p_189940_2_, int p_189940_3_, int p_189940_4_, @Nullable b_257_Y p_189940_5_, int p_189940_6_, j_4336_h.J_1907_R p_189940_7_) {
        int i = p_189940_1_.nextInt(100);
        if (i >= 80) {
            BoundingBox mutableboundingbox = J_1907_R.n_1700_B(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
            if (mutableboundingbox != null) {
                return new J_1907_R(p_189940_6_, mutableboundingbox, p_189940_5_, p_189940_7_);
            }
        } else if (i >= 70) {
            BoundingBox mutableboundingbox1 = P_1922_E.n_1700_B(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
            if (mutableboundingbox1 != null) {
                return new P_1922_E(p_189940_6_, mutableboundingbox1, p_189940_5_, p_189940_7_);
            }
        } else {
            BoundingBox mutableboundingbox2 = n_1700_B.n_1700_B(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);
            if (mutableboundingbox2 != null) {
                return new n_1700_B(p_189940_6_, p_189940_1_, mutableboundingbox2, p_189940_5_, p_189940_7_);
            }
        }
        return null;
    }

    private static R_4764_Y n_1700_B(E_3771_B p_189938_0_, List<E_3771_B> p_189938_1_, Random p_189938_2_, int p_189938_3_, int p_189938_4_, int p_189938_5_, b_257_Y p_189938_6_, int p_189938_7_) {
        if (p_189938_7_ > 8) {
            return null;
        }
        if (Math.abs(p_189938_3_ - p_189938_0_.v_4262_N().n_1700_B) <= 80 && Math.abs(p_189938_5_ - p_189938_0_.v_4262_N().R_4764_Y) <= 80) {
            j_4336_h.J_1907_R mineshaftstructure$type = ((R_4764_Y)p_189938_0_).n_1700_B;
            R_4764_Y mineshaftpieces$piece = e_1123_d.n_1700_B(p_189938_1_, p_189938_2_, p_189938_3_, p_189938_4_, p_189938_5_, p_189938_6_, p_189938_7_ + 1, mineshaftstructure$type);
            if (mineshaftpieces$piece != null) {
                p_189938_1_.add(mineshaftpieces$piece);
                mineshaftpieces$piece.n_1700_B(p_189938_0_, p_189938_1_, p_189938_2_);
            }
            return mineshaftpieces$piece;
        }
        return null;
    }

    public static class J_1907_R
    extends R_4764_Y {
        private final b_257_Y J_1907_R;
        private final boolean R_4764_Y;

        public J_1907_R(b_2085_h p_i50454_1_, U_2912_j p_i50454_2_) {
            super(StructurePieceType.J_1907_R, p_i50454_2_);
            this.R_4764_Y = p_i50454_2_.t_1786_h("tf");
            this.J_1907_R = b_257_Y.J_1907_R(p_i50454_2_.w_1484_f("D"));
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("tf", this.R_4764_Y);
            tagCompound.J_1907_R("D", this.J_1907_R.G_564_y());
        }

        public J_1907_R(int p_i50455_1_, BoundingBox p_i50455_2_, @Nullable b_257_Y p_i50455_3_, j_4336_h.J_1907_R p_i50455_4_) {
            super(StructurePieceType.J_1907_R, p_i50455_1_, p_i50455_4_);
            this.J_1907_R = p_i50455_3_;
            this.h_1847_R = p_i50455_2_;
            this.R_4764_Y = p_i50455_2_.P_1922_E() > 3;
        }

        public static BoundingBox n_1700_B(List<E_3771_B> listIn, Random rand, int x, int y, int z, b_257_Y facing) {
            BoundingBox mutableboundingbox = new BoundingBox(x, y, z, x, y + 3 - 1, z);
            if (rand.nextInt(4) == 0) {
                mutableboundingbox.P_1922_E += 4;
            }
            switch (facing) {
                default: {
                    mutableboundingbox.n_1700_B = x - 1;
                    mutableboundingbox.G_564_y = x + 3;
                    mutableboundingbox.R_4764_Y = z - 4;
                    break;
                }
                case G_564_y: {
                    mutableboundingbox.n_1700_B = x - 1;
                    mutableboundingbox.G_564_y = x + 3;
                    mutableboundingbox.u_1723_Y = z + 3 + 1;
                    break;
                }
                case P_1922_E: {
                    mutableboundingbox.n_1700_B = x - 4;
                    mutableboundingbox.R_4764_Y = z - 1;
                    mutableboundingbox.u_1723_Y = z + 3;
                    break;
                }
                case u_1723_Y: {
                    mutableboundingbox.G_564_y = x + 3 + 1;
                    mutableboundingbox.R_4764_Y = z - 1;
                    mutableboundingbox.u_1723_Y = z + 3;
                }
            }
            return E_3771_B.n_1700_B(listIn, mutableboundingbox) != null ? null : mutableboundingbox;
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            int i = this.w_1484_f();
            switch (this.J_1907_R) {
                default: {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.P_1922_E, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.u_1723_Y, i);
                    break;
                }
                case G_564_y: {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.P_1922_E, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.u_1723_Y, i);
                    break;
                }
                case P_1922_E: {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.P_1922_E, i);
                    break;
                }
                case u_1723_Y: {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, b_257_Y.u_1723_Y, i);
                }
            }
            if (this.R_4764_Y) {
                if (rand.nextBoolean()) {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R + 3 + 1, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                }
                if (rand.nextBoolean()) {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + 3 + 1, this.h_1847_R.R_4764_Y + 1, b_257_Y.P_1922_E, i);
                }
                if (rand.nextBoolean()) {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + 3 + 1, this.h_1847_R.R_4764_Y + 1, b_257_Y.u_1723_Y, i);
                }
                if (rand.nextBoolean()) {
                    e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R + 3 + 1, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                }
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.n_1700_B(p_230383_1_, p_230383_5_)) {
                return false;
            }
            K_4074_S blockstate = this.J_1907_R();
            if (this.R_4764_Y) {
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y - 1, this.h_1847_R.J_1907_R + 3 - 1, this.h_1847_R.u_1723_Y, P_4830_p, P_4830_p, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.G_564_y, this.h_1847_R.J_1907_R + 3 - 1, this.h_1847_R.u_1723_Y - 1, P_4830_p, P_4830_p, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.P_1922_E - 2, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y - 1, this.h_1847_R.P_1922_E, this.h_1847_R.u_1723_Y, P_4830_p, P_4830_p, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.P_1922_E - 2, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.G_564_y, this.h_1847_R.P_1922_E, this.h_1847_R.u_1723_Y - 1, P_4830_p, P_4830_p, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R + 3, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.G_564_y - 1, this.h_1847_R.J_1907_R + 3, this.h_1847_R.u_1723_Y - 1, P_4830_p, P_4830_p, false);
            } else {
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y - 1, this.h_1847_R.P_1922_E, this.h_1847_R.u_1723_Y, P_4830_p, P_4830_p, false);
                this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.G_564_y, this.h_1847_R.P_1922_E, this.h_1847_R.u_1723_Y - 1, P_4830_p, P_4830_p, false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B + 1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y - 1, this.h_1847_R.P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.G_564_y - 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y + 1, this.h_1847_R.P_1922_E);
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.G_564_y - 1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y - 1, this.h_1847_R.P_1922_E);
            for (int i = this.h_1847_R.n_1700_B; i <= this.h_1847_R.G_564_y; ++i) {
                for (int j = this.h_1847_R.R_4764_Y; j <= this.h_1847_R.u_1723_Y; ++j) {
                    if (!this.n_1700_B((BlockGetter)p_230383_1_, i, this.h_1847_R.J_1907_R - 1, j, p_230383_5_).v_4262_N() || !this.n_1700_B(p_230383_1_, i, this.h_1847_R.J_1907_R - 1, j, p_230383_5_)) continue;
                    this.n_1700_B(p_230383_1_, blockstate, i, this.h_1847_R.J_1907_R - 1, j, p_230383_5_);
                }
            }
            return true;
        }

        private void n_1700_B(WorldGenLevel p_189923_1_, BoundingBox p_189923_2_, int p_189923_3_, int p_189923_4_, int p_189923_5_, int p_189923_6_) {
            if (!this.n_1700_B((BlockGetter)p_189923_1_, p_189923_3_, p_189923_6_ + 1, p_189923_5_, p_189923_2_).v_4262_N()) {
                this.n_1700_B(p_189923_1_, p_189923_2_, p_189923_3_, p_189923_4_, p_189923_5_, p_189923_3_, p_189923_6_, p_189923_5_, this.J_1907_R(), P_4830_p, false);
            }
        }
    }

    public static class P_1922_E
    extends R_4764_Y {
        public P_1922_E(int p_i50449_1_, BoundingBox p_i50449_2_, b_257_Y p_i50449_3_, j_4336_h.J_1907_R p_i50449_4_) {
            super(StructurePieceType.G_564_y, p_i50449_1_, p_i50449_4_);
            this.n_1700_B(p_i50449_3_);
            this.h_1847_R = p_i50449_2_;
        }

        public P_1922_E(b_2085_h p_i50450_1_, U_2912_j p_i50450_2_) {
            super(StructurePieceType.G_564_y, p_i50450_2_);
        }

        public static BoundingBox n_1700_B(List<E_3771_B> listIn, Random rand, int x, int y, int z, b_257_Y facing) {
            BoundingBox mutableboundingbox = new BoundingBox(x, y - 5, z, x, y + 3 - 1, z);
            switch (facing) {
                default: {
                    mutableboundingbox.G_564_y = x + 3 - 1;
                    mutableboundingbox.R_4764_Y = z - 8;
                    break;
                }
                case G_564_y: {
                    mutableboundingbox.G_564_y = x + 3 - 1;
                    mutableboundingbox.u_1723_Y = z + 8;
                    break;
                }
                case P_1922_E: {
                    mutableboundingbox.n_1700_B = x - 8;
                    mutableboundingbox.u_1723_Y = z + 3 - 1;
                    break;
                }
                case u_1723_Y: {
                    mutableboundingbox.G_564_y = x + 8;
                    mutableboundingbox.u_1723_Y = z + 3 - 1;
                }
            }
            return E_3771_B.n_1700_B(listIn, mutableboundingbox) != null ? null : mutableboundingbox;
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            int i = this.w_1484_f();
            b_257_Y direction = this.t_148_a();
            if (direction != null) {
                switch (direction) {
                    default: {
                        e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                        break;
                    }
                    case G_564_y: {
                        e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                        break;
                    }
                    case P_1922_E: {
                        e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y, b_257_Y.P_1922_E, i);
                        break;
                    }
                    case u_1723_Y: {
                        e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y, b_257_Y.u_1723_Y, i);
                    }
                }
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.n_1700_B(p_230383_1_, p_230383_5_)) {
                return false;
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5, 0, 2, 7, 1, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 7, 2, 2, 8, P_4830_p, P_4830_p, false);
            for (int i = 0; i < 5; ++i) {
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, P_4830_p, P_4830_p, false);
            }
            return true;
        }
    }

    public static class n_1700_B
    extends R_4764_Y {
        private final boolean J_1907_R;
        private final boolean R_4764_Y;
        private boolean G_564_y;
        private final int P_1922_E;

        public n_1700_B(b_2085_h p_i50456_1_, U_2912_j p_i50456_2_) {
            super(StructurePieceType.n_1700_B, p_i50456_2_);
            this.J_1907_R = p_i50456_2_.t_1786_h("hr");
            this.R_4764_Y = p_i50456_2_.t_1786_h("sc");
            this.G_564_y = p_i50456_2_.t_1786_h("hps");
            this.P_1922_E = p_i50456_2_.w_1484_f("Num");
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("hr", this.J_1907_R);
            tagCompound.n_1700_B("sc", this.R_4764_Y);
            tagCompound.n_1700_B("hps", this.G_564_y);
            tagCompound.J_1907_R("Num", this.P_1922_E);
        }

        public n_1700_B(int p_i47140_1_, Random p_i47140_2_, BoundingBox p_i47140_3_, b_257_Y p_i47140_4_, j_4336_h.J_1907_R p_i47140_5_) {
            super(StructurePieceType.n_1700_B, p_i47140_1_, p_i47140_5_);
            this.n_1700_B(p_i47140_4_);
            this.h_1847_R = p_i47140_3_;
            this.J_1907_R = p_i47140_2_.nextInt(3) == 0;
            this.R_4764_Y = !this.J_1907_R && p_i47140_2_.nextInt(23) == 0;
            this.P_1922_E = this.t_148_a().h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? p_i47140_3_.u_1723_Y() / 5 : p_i47140_3_.G_564_y() / 5;
        }

        public static BoundingBox n_1700_B(List<E_3771_B> p_175814_0_, Random rand, int x, int y, int z, b_257_Y facing) {
            int i;
            BoundingBox mutableboundingbox = new BoundingBox(x, y, z, x, y + 3 - 1, z);
            for (i = rand.nextInt(3) + 2; i > 0; --i) {
                int j = i * 5;
                switch (facing) {
                    default: {
                        mutableboundingbox.G_564_y = x + 3 - 1;
                        mutableboundingbox.R_4764_Y = z - (j - 1);
                        break;
                    }
                    case G_564_y: {
                        mutableboundingbox.G_564_y = x + 3 - 1;
                        mutableboundingbox.u_1723_Y = z + j - 1;
                        break;
                    }
                    case P_1922_E: {
                        mutableboundingbox.n_1700_B = x - (j - 1);
                        mutableboundingbox.u_1723_Y = z + 3 - 1;
                        break;
                    }
                    case u_1723_Y: {
                        mutableboundingbox.G_564_y = x + j - 1;
                        mutableboundingbox.u_1723_Y = z + 3 - 1;
                    }
                }
                if (E_3771_B.n_1700_B(p_175814_0_, mutableboundingbox) == null) break;
            }
            return i > 0 ? mutableboundingbox : null;
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            block24: {
                int i = this.w_1484_f();
                int j = rand.nextInt(4);
                b_257_Y direction = this.t_148_a();
                if (direction != null) {
                    switch (direction) {
                        default: {
                            if (j <= 1) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y - 1, direction, i);
                                break;
                            }
                            if (j == 2) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y, b_257_Y.P_1922_E, i);
                                break;
                            }
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y, b_257_Y.u_1723_Y, i);
                            break;
                        }
                        case G_564_y: {
                            if (j <= 1) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.u_1723_Y + 1, direction, i);
                                break;
                            }
                            if (j == 2) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.u_1723_Y - 3, b_257_Y.P_1922_E, i);
                                break;
                            }
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.u_1723_Y - 3, b_257_Y.u_1723_Y, i);
                            break;
                        }
                        case P_1922_E: {
                            if (j <= 1) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y, direction, i);
                                break;
                            }
                            if (j == 2) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                                break;
                            }
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                            break;
                        }
                        case u_1723_Y: {
                            if (j <= 1) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y, direction, i);
                                break;
                            }
                            if (j == 2) {
                                e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y - 3, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                                break;
                            }
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y - 3, this.h_1847_R.J_1907_R - 1 + rand.nextInt(3), this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                        }
                    }
                }
                if (i >= 8) break block24;
                if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.G_564_y) {
                    int i1 = this.h_1847_R.n_1700_B + 3;
                    while (i1 + 3 <= this.h_1847_R.G_564_y) {
                        int j1 = rand.nextInt(5);
                        if (j1 == 0) {
                            e_1123_d.n_1700_B(componentIn, listIn, rand, i1, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i + 1);
                        } else if (j1 == 1) {
                            e_1123_d.n_1700_B(componentIn, listIn, rand, i1, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i + 1);
                        }
                        i1 += 5;
                    }
                } else {
                    int k = this.h_1847_R.R_4764_Y + 3;
                    while (k + 3 <= this.h_1847_R.u_1723_Y) {
                        int l = rand.nextInt(5);
                        if (l == 0) {
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R, k, b_257_Y.P_1922_E, i + 1);
                        } else if (l == 1) {
                            e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R, k, b_257_Y.u_1723_Y, i + 1);
                        }
                        k += 5;
                    }
                }
            }
        }

        @Override
        protected boolean n_1700_B(WorldGenLevel worldIn, BoundingBox structurebb, Random randomIn, int x, int y, int z, g_2336_b loot) {
            c_1514_x blockpos = new c_1514_x(this.n_1700_B(x, z), this.n_1700_B(y), this.J_1907_R(x, z));
            if (structurebb.J_1907_R(blockpos) && worldIn.getBlockState(blockpos).v_4262_N() && !worldIn.getBlockState(blockpos.down()).v_4262_N()) {
                K_4074_S blockstate = (K_4074_S)a_3742_W.Y_776_s.multiplayerClientSuggestionProvider().n_1700_B(w_4059_h.Q_4569_t, randomIn.nextBoolean() ? w_801_N.n_1700_B : w_801_N.J_1907_R);
                this.n_1700_B(worldIn, blockstate, x, y, z, structurebb);
                MinecartChest chestminecartentity = new MinecartChest(worldIn.J_1907_R(), (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5);
                chestminecartentity.n_1700_B(loot, randomIn.nextLong());
                worldIn.a_(chestminecartentity);
                return true;
            }
            return false;
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.n_1700_B(p_230383_1_, p_230383_5_)) {
                return false;
            }
            boolean i = false;
            int j = 2;
            boolean k = false;
            int l = 2;
            int i1 = this.P_1922_E * 5 - 1;
            K_4074_S blockstate = this.J_1907_R();
            this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, 0, 2, 1, i1, P_4830_p, P_4830_p, false);
            this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.8f, 0, 2, 0, 2, 2, i1, P_4830_p, P_4830_p, false, false);
            if (this.R_4764_Y) {
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.6f, 0, 0, 0, 2, 1, i1, a_3742_W.y_1700_S.multiplayerClientSuggestionProvider(), P_4830_p, false, true);
            }
            for (int j1 = 0; j1 < this.P_1922_E; ++j1) {
                int k2;
                int k1 = 2 + j1 * 5;
                this.n_1700_B(p_230383_1_, p_230383_5_, 0, 0, k1, 2, 2, p_230383_4_);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 0, 2, k1 - 1);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 2, 2, k1 - 1);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 0, 2, k1 + 1);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.1f, 2, 2, k1 + 1);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.05f, 0, 2, k1 - 2);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.05f, 2, 2, k1 - 2);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.05f, 0, 2, k1 + 2);
                this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0.05f, 2, 2, k1 + 2);
                if (p_230383_4_.nextInt(100) == 0) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 2, 0, k1 - 1, o_4810_o.Y_259_p);
                }
                if (p_230383_4_.nextInt(100) == 0) {
                    this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, 0, 0, k1 + 1, o_4810_o.Y_259_p);
                }
                if (!this.R_4764_Y || this.G_564_y) continue;
                int l1 = this.n_1700_B(0);
                int i2 = k1 - 1 + p_230383_4_.nextInt(3);
                int j2 = this.n_1700_B(1, i2);
                c_1514_x blockpos = new c_1514_x(j2, l1, k2 = this.J_1907_R(1, i2));
                if (!p_230383_5_.J_1907_R(blockpos) || !this.n_1700_B(p_230383_1_, 1, 0, i2, p_230383_5_)) continue;
                this.G_564_y = true;
                p_230383_1_.n_1700_B(blockpos, a_3742_W.j_306_t.multiplayerClientSuggestionProvider(), 2);
                i_2154_H tileentity = p_230383_1_.getTileEntity(blockpos);
                if (!(tileentity instanceof SpawnerBlockEntity)) continue;
                ((SpawnerBlockEntity)tileentity).v_4262_N().n_1700_B(t_5_h.t_148_a);
            }
            for (int l2 = 0; l2 <= 2; ++l2) {
                for (int i3 = 0; i3 <= i1; ++i3) {
                    int k3 = -1;
                    K_4074_S blockstate3 = this.n_1700_B((BlockGetter)p_230383_1_, l2, -1, i3, p_230383_5_);
                    if (!blockstate3.v_4262_N() || !this.n_1700_B(p_230383_1_, l2, -1, i3, p_230383_5_)) continue;
                    int l3 = -1;
                    this.n_1700_B(p_230383_1_, blockstate, l2, -1, i3, p_230383_5_);
                }
            }
            if (this.J_1907_R) {
                K_4074_S blockstate1 = (K_4074_S)a_3742_W.Y_776_s.multiplayerClientSuggestionProvider().n_1700_B(w_4059_h.Q_4569_t, w_801_N.n_1700_B);
                for (int j3 = 0; j3 <= i1; ++j3) {
                    K_4074_S blockstate2 = this.n_1700_B((BlockGetter)p_230383_1_, 1, -1, j3, p_230383_5_);
                    if (blockstate2.v_4262_N() || !blockstate2.t_148_a(p_230383_1_, new c_1514_x(this.n_1700_B(1, j3), this.n_1700_B(-1), this.J_1907_R(1, j3)))) continue;
                    float f = this.n_1700_B(p_230383_1_, 1, 0, j3, p_230383_5_) ? 0.7f : 0.9f;
                    this.n_1700_B(p_230383_1_, p_230383_5_, p_230383_4_, f, 1, 0, j3, blockstate1);
                }
            }
            return true;
        }

        private void n_1700_B(WorldGenLevel p_189921_1_, BoundingBox p_189921_2_, int p_189921_3_, int p_189921_4_, int p_189921_5_, int p_189921_6_, int p_189921_7_, Random p_189921_8_) {
            if (this.n_1700_B((BlockGetter)p_189921_1_, p_189921_2_, p_189921_3_, p_189921_7_, p_189921_6_, p_189921_5_)) {
                K_4074_S blockstate = this.J_1907_R();
                K_4074_S blockstate1 = this.R_4764_Y();
                this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_4_, p_189921_5_, p_189921_3_, p_189921_6_ - 1, p_189921_5_, (K_4074_S)blockstate1.n_1700_B(FenceBlock.M_182_A, true), P_4830_p, false);
                this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_7_, p_189921_4_, p_189921_5_, p_189921_7_, p_189921_6_ - 1, p_189921_5_, (K_4074_S)blockstate1.n_1700_B(FenceBlock.h_1847_R, true), P_4830_p, false);
                if (p_189921_8_.nextInt(4) == 0) {
                    this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_6_, p_189921_5_, p_189921_3_, p_189921_6_, p_189921_5_, blockstate, P_4830_p, false);
                    this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_7_, p_189921_6_, p_189921_5_, p_189921_7_, p_189921_6_, p_189921_5_, blockstate, P_4830_p, false);
                } else {
                    this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_6_, p_189921_5_, p_189921_7_, p_189921_6_, p_189921_5_, blockstate, P_4830_p, false);
                    this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_8_, 0.05f, p_189921_3_ + 1, p_189921_6_, p_189921_5_ - 1, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.R_4764_Y));
                    this.n_1700_B(p_189921_1_, p_189921_2_, p_189921_8_, 0.05f, p_189921_3_ + 1, p_189921_6_, p_189921_5_ + 1, (K_4074_S)a_3742_W.C_1269_X.multiplayerClientSuggestionProvider().n_1700_B(WallTorchBlock.P_4830_p, b_257_Y.G_564_y));
                }
            }
        }

        private void n_1700_B(WorldGenLevel p_189922_1_, BoundingBox p_189922_2_, Random p_189922_3_, float p_189922_4_, int p_189922_5_, int p_189922_6_, int p_189922_7_) {
            if (this.n_1700_B(p_189922_1_, p_189922_5_, p_189922_6_, p_189922_7_, p_189922_2_)) {
                this.n_1700_B(p_189922_1_, p_189922_2_, p_189922_3_, p_189922_4_, p_189922_5_, p_189922_6_, p_189922_7_, a_3742_W.y_1700_S.multiplayerClientSuggestionProvider());
            }
        }
    }

    static abstract class R_4764_Y
    extends E_3771_B {
        protected j_4336_h.J_1907_R n_1700_B;

        public R_4764_Y(StructurePieceType structurePieceTypeIn, int componentTypeIn, j_4336_h.J_1907_R typeIn) {
            super(structurePieceTypeIn, componentTypeIn);
            this.n_1700_B = typeIn;
        }

        public R_4764_Y(StructurePieceType structurePieceTypeIn, U_2912_j nbt) {
            super(structurePieceTypeIn, nbt);
            this.n_1700_B = j_4336_h.J_1907_R.n_1700_B(nbt.w_1484_f("MST"));
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            tagCompound.J_1907_R("MST", this.n_1700_B.ordinal());
        }

        protected K_4074_S J_1907_R() {
            switch (this.n_1700_B) {
                default: {
                    return a_3742_W.h_1847_R.multiplayerClientSuggestionProvider();
                }
                case J_1907_R: 
            }
            return a_3742_W.w_1457_N.multiplayerClientSuggestionProvider();
        }

        protected K_4074_S R_4764_Y() {
            switch (this.n_1700_B) {
                default: {
                    return a_3742_W.h_2848_I.multiplayerClientSuggestionProvider();
                }
                case J_1907_R: 
            }
            return a_3742_W.AutoPilot.multiplayerClientSuggestionProvider();
        }

        protected boolean n_1700_B(BlockGetter blockReaderIn, BoundingBox boundsIn, int xStartIn, int xEndIn, int p_189918_5_, int zIn) {
            for (int i = xStartIn; i <= xEndIn; ++i) {
                if (!this.n_1700_B(blockReaderIn, i, p_189918_5_ + 1, zIn, boundsIn).v_4262_N()) continue;
                return false;
            }
            return true;
        }
    }

    public static class G_564_y
    extends R_4764_Y {
        private final List<BoundingBox> J_1907_R = Lists.newLinkedList();

        public G_564_y(int p_i47137_1_, Random p_i47137_2_, int p_i47137_3_, int p_i47137_4_, j_4336_h.J_1907_R typeIn) {
            super(StructurePieceType.R_4764_Y, p_i47137_1_, typeIn);
            this.n_1700_B = typeIn;
            this.h_1847_R = new BoundingBox(p_i47137_3_, 50, p_i47137_4_, p_i47137_3_ + 7 + p_i47137_2_.nextInt(6), 54 + p_i47137_2_.nextInt(6), p_i47137_4_ + 7 + p_i47137_2_.nextInt(6));
        }

        public G_564_y(b_2085_h templateManagerIn, U_2912_j nbt) {
            super(StructurePieceType.R_4764_Y, nbt);
            q_2896_o listnbt = nbt.G_564_y("Entrances", 11);
            for (int i = 0; i < listnbt.size(); ++i) {
                this.J_1907_R.add(new BoundingBox(listnbt.u_1723_Y(i)));
            }
        }

        @Override
        public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
            int k;
            int i = this.w_1484_f();
            int j = this.h_1847_R.P_1922_E() - 3 - 1;
            if (j <= 0) {
                j = 1;
            }
            for (k = 0; k < this.h_1847_R.G_564_y() && (k += rand.nextInt(this.h_1847_R.G_564_y())) + 3 <= this.h_1847_R.G_564_y(); k += 4) {
                R_4764_Y mineshaftpieces$piece = e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + k, this.h_1847_R.J_1907_R + rand.nextInt(j) + 1, this.h_1847_R.R_4764_Y - 1, b_257_Y.R_4764_Y, i);
                if (mineshaftpieces$piece == null) continue;
                BoundingBox mutableboundingbox = mineshaftpieces$piece.v_4262_N();
                this.J_1907_R.add(new BoundingBox(mutableboundingbox.n_1700_B, mutableboundingbox.J_1907_R, this.h_1847_R.R_4764_Y, mutableboundingbox.G_564_y, mutableboundingbox.P_1922_E, this.h_1847_R.R_4764_Y + 1));
            }
            for (k = 0; k < this.h_1847_R.G_564_y() && (k += rand.nextInt(this.h_1847_R.G_564_y())) + 3 <= this.h_1847_R.G_564_y(); k += 4) {
                R_4764_Y mineshaftpieces$piece1 = e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B + k, this.h_1847_R.J_1907_R + rand.nextInt(j) + 1, this.h_1847_R.u_1723_Y + 1, b_257_Y.G_564_y, i);
                if (mineshaftpieces$piece1 == null) continue;
                BoundingBox mutableboundingbox1 = mineshaftpieces$piece1.v_4262_N();
                this.J_1907_R.add(new BoundingBox(mutableboundingbox1.n_1700_B, mutableboundingbox1.J_1907_R, this.h_1847_R.u_1723_Y - 1, mutableboundingbox1.G_564_y, mutableboundingbox1.P_1922_E, this.h_1847_R.u_1723_Y));
            }
            for (k = 0; k < this.h_1847_R.u_1723_Y() && (k += rand.nextInt(this.h_1847_R.u_1723_Y())) + 3 <= this.h_1847_R.u_1723_Y(); k += 4) {
                R_4764_Y mineshaftpieces$piece2 = e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.n_1700_B - 1, this.h_1847_R.J_1907_R + rand.nextInt(j) + 1, this.h_1847_R.R_4764_Y + k, b_257_Y.P_1922_E, i);
                if (mineshaftpieces$piece2 == null) continue;
                BoundingBox mutableboundingbox2 = mineshaftpieces$piece2.v_4262_N();
                this.J_1907_R.add(new BoundingBox(this.h_1847_R.n_1700_B, mutableboundingbox2.J_1907_R, mutableboundingbox2.R_4764_Y, this.h_1847_R.n_1700_B + 1, mutableboundingbox2.P_1922_E, mutableboundingbox2.u_1723_Y));
            }
            for (k = 0; k < this.h_1847_R.u_1723_Y() && (k += rand.nextInt(this.h_1847_R.u_1723_Y())) + 3 <= this.h_1847_R.u_1723_Y(); k += 4) {
                R_4764_Y structurepiece = e_1123_d.n_1700_B(componentIn, listIn, rand, this.h_1847_R.G_564_y + 1, this.h_1847_R.J_1907_R + rand.nextInt(j) + 1, this.h_1847_R.R_4764_Y + k, b_257_Y.u_1723_Y, i);
                if (structurepiece == null) continue;
                BoundingBox mutableboundingbox3 = structurepiece.v_4262_N();
                this.J_1907_R.add(new BoundingBox(this.h_1847_R.G_564_y - 1, mutableboundingbox3.J_1907_R, mutableboundingbox3.R_4764_Y, this.h_1847_R.G_564_y, mutableboundingbox3.P_1922_E, mutableboundingbox3.u_1723_Y));
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            if (this.n_1700_B(p_230383_1_, p_230383_5_)) {
                return false;
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y, this.h_1847_R.J_1907_R, this.h_1847_R.u_1723_Y, a_3742_W.s_956_w.multiplayerClientSuggestionProvider(), P_4830_p, true);
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R + 1, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y, Math.min(this.h_1847_R.J_1907_R + 3, this.h_1847_R.P_1922_E), this.h_1847_R.u_1723_Y, P_4830_p, P_4830_p, false);
            for (BoundingBox mutableboundingbox : this.J_1907_R) {
                this.n_1700_B(p_230383_1_, p_230383_5_, mutableboundingbox.n_1700_B, mutableboundingbox.P_1922_E - 2, mutableboundingbox.R_4764_Y, mutableboundingbox.G_564_y, mutableboundingbox.P_1922_E, mutableboundingbox.u_1723_Y, P_4830_p, P_4830_p, false);
            }
            this.n_1700_B(p_230383_1_, p_230383_5_, this.h_1847_R.n_1700_B, this.h_1847_R.J_1907_R + 4, this.h_1847_R.R_4764_Y, this.h_1847_R.G_564_y, this.h_1847_R.P_1922_E, this.h_1847_R.u_1723_Y, P_4830_p, false);
            return true;
        }

        @Override
        public void n_1700_B(int x, int y, int z) {
            super.n_1700_B(x, y, z);
            for (BoundingBox mutableboundingbox : this.J_1907_R) {
                mutableboundingbox.n_1700_B(x, y, z);
            }
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            q_2896_o listnbt = new q_2896_o();
            for (BoundingBox mutableboundingbox : this.J_1907_R) {
                listnbt.add(mutableboundingbox.w_1484_f());
            }
            tagCompound.n_1700_B("Entrances", listnbt);
        }
    }
}



