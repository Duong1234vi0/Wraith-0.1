/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.BoundingBox;
import lightning.product.Tuple;
import lightning.product.U_2912_j;
import lightning.product.V_4572_l;
import lightning.product.W_2163_m;
import lightning.product.Z_1993_T;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.m_1605_o;
import lightning.product.o_4810_o;
import lightning.product.q_1616_l;
import lightning.product.Items;
import lightning.product.r_4719_P;
import lightning.product.t_5_h;
import lightning.product.w_1748_S;
import lightning.product.y_740_d;

public class C_1437_B {
    private static final w_1748_S n_1700_B = new w_1748_S().n_1700_B(true).n_1700_B(r_4719_P.J_1907_R);
    private static final w_1748_S J_1907_R = new w_1748_S().n_1700_B(true).n_1700_B(r_4719_P.G_564_y);
    private static final J_1907_R R_4764_Y = new J_1907_R(){

        @Override
        public void n_1700_B() {
        }

        @Override
        public boolean n_1700_B(b_2085_h p_191086_1_, int p_191086_2_, n_1700_B p_191086_3_, c_1514_x p_191086_4_, List<E_3771_B> p_191086_5_, Random p_191086_6_) {
            if (p_191086_2_ > 8) {
                return false;
            }
            W_2163_m rotation = p_191086_3_.J_1907_R.G_564_y();
            n_1700_B endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, p_191086_3_, p_191086_4_, "base_floor", rotation, true));
            int i = p_191086_6_.nextInt(3);
            if (i == 0) {
                C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 4, -1), "base_roof", rotation, true));
            } else if (i == 1) {
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 0, -1), "second_floor_2", rotation, false));
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 8, -1), "second_roof", rotation, false));
                C_1437_B.n_1700_B(p_191086_1_, P_1922_E, p_191086_2_ + 1, endcitypieces$citytemplate, null, p_191086_5_, p_191086_6_);
            } else if (i == 2) {
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 0, -1), "second_floor_2", rotation, false));
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 4, -1), "third_floor_2", rotation, false));
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-1, 8, -1), "third_roof", rotation, true));
                C_1437_B.n_1700_B(p_191086_1_, P_1922_E, p_191086_2_ + 1, endcitypieces$citytemplate, null, p_191086_5_, p_191086_6_);
            }
            return true;
        }
    };
    private static final List<Tuple<W_2163_m, c_1514_x>> G_564_y = Lists.newArrayList((Object[])new Tuple[]{new Tuple<W_2163_m, c_1514_x>(W_2163_m.n_1700_B, new c_1514_x(1, -1, 0)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.J_1907_R, new c_1514_x(6, -1, 1)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.G_564_y, new c_1514_x(0, -1, 5)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.R_4764_Y, new c_1514_x(5, -1, 6))});
    private static final J_1907_R P_1922_E = new J_1907_R(){

        @Override
        public void n_1700_B() {
        }

        @Override
        public boolean n_1700_B(b_2085_h p_191086_1_, int p_191086_2_, n_1700_B p_191086_3_, c_1514_x p_191086_4_, List<E_3771_B> p_191086_5_, Random p_191086_6_) {
            W_2163_m rotation = p_191086_3_.J_1907_R.G_564_y();
            n_1700_B lvt_8_1_ = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, p_191086_3_, new c_1514_x(3 + p_191086_6_.nextInt(2), -3, 3 + p_191086_6_.nextInt(2)), "tower_base", rotation, true));
            lvt_8_1_ = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, lvt_8_1_, new c_1514_x(0, 7, 0), "tower_piece", rotation, true));
            n_1700_B endcitypieces$citytemplate1 = p_191086_6_.nextInt(3) == 0 ? lvt_8_1_ : null;
            int i = 1 + p_191086_6_.nextInt(3);
            for (int j = 0; j < i; ++j) {
                lvt_8_1_ = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, lvt_8_1_, new c_1514_x(0, 4, 0), "tower_piece", rotation, true));
                if (j >= i - 1 || !p_191086_6_.nextBoolean()) continue;
                endcitypieces$citytemplate1 = lvt_8_1_;
            }
            if (endcitypieces$citytemplate1 != null) {
                for (Tuple<W_2163_m, c_1514_x> tuple : G_564_y) {
                    if (!p_191086_6_.nextBoolean()) continue;
                    n_1700_B endcitypieces$citytemplate2 = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate1, tuple.J_1907_R(), "bridge_end", rotation.n_1700_B(tuple.n_1700_B()), true));
                    C_1437_B.n_1700_B(p_191086_1_, u_1723_Y, p_191086_2_ + 1, endcitypieces$citytemplate2, null, p_191086_5_, p_191086_6_);
                }
                C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, lvt_8_1_, new c_1514_x(-1, 4, -1), "tower_top", rotation, true));
            } else {
                if (p_191086_2_ != 7) {
                    return C_1437_B.n_1700_B(p_191086_1_, w_1484_f, p_191086_2_ + 1, lvt_8_1_, null, p_191086_5_, p_191086_6_);
                }
                C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, lvt_8_1_, new c_1514_x(-1, 4, -1), "tower_top", rotation, true));
            }
            return true;
        }
    };
    private static final J_1907_R u_1723_Y = new J_1907_R(){
        public boolean n_1700_B;

        @Override
        public void n_1700_B() {
            this.n_1700_B = false;
        }

        @Override
        public boolean n_1700_B(b_2085_h p_191086_1_, int p_191086_2_, n_1700_B p_191086_3_, c_1514_x p_191086_4_, List<E_3771_B> p_191086_5_, Random p_191086_6_) {
            W_2163_m rotation = p_191086_3_.J_1907_R.G_564_y();
            int i = p_191086_6_.nextInt(4) + 1;
            n_1700_B endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, p_191086_3_, new c_1514_x(0, 0, -4), "bridge_piece", rotation, true));
            endcitypieces$citytemplate.Q_4569_t = -1;
            int j = 0;
            for (int k = 0; k < i; ++k) {
                if (p_191086_6_.nextBoolean()) {
                    endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(0, j, -4), "bridge_piece", rotation, true));
                    j = 0;
                    continue;
                }
                endcitypieces$citytemplate = p_191086_6_.nextBoolean() ? C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(0, j, -4), "bridge_steep_stairs", rotation, true)) : C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(0, j, -8), "bridge_gentle_stairs", rotation, true));
                j = 4;
            }
            if (!this.n_1700_B && p_191086_6_.nextInt(10 - p_191086_2_) == 0) {
                C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-8 + p_191086_6_.nextInt(8), j, -70 + p_191086_6_.nextInt(10)), "ship", rotation, true));
                this.n_1700_B = true;
            } else if (!C_1437_B.n_1700_B(p_191086_1_, R_4764_Y, p_191086_2_ + 1, endcitypieces$citytemplate, new c_1514_x(-3, j + 1, -11), p_191086_5_, p_191086_6_)) {
                return false;
            }
            endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(4, j, 0), "bridge_end", rotation.n_1700_B(W_2163_m.R_4764_Y), true));
            endcitypieces$citytemplate.Q_4569_t = -1;
            return true;
        }
    };
    private static final List<Tuple<W_2163_m, c_1514_x>> v_4262_N = Lists.newArrayList((Object[])new Tuple[]{new Tuple<W_2163_m, c_1514_x>(W_2163_m.n_1700_B, new c_1514_x(4, -1, 0)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.J_1907_R, new c_1514_x(12, -1, 4)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.G_564_y, new c_1514_x(0, -1, 8)), new Tuple<W_2163_m, c_1514_x>(W_2163_m.R_4764_Y, new c_1514_x(8, -1, 12))});
    private static final J_1907_R w_1484_f = new J_1907_R(){

        @Override
        public void n_1700_B() {
        }

        @Override
        public boolean n_1700_B(b_2085_h p_191086_1_, int p_191086_2_, n_1700_B p_191086_3_, c_1514_x p_191086_4_, List<E_3771_B> p_191086_5_, Random p_191086_6_) {
            W_2163_m rotation = p_191086_3_.J_1907_R.G_564_y();
            n_1700_B endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, p_191086_3_, new c_1514_x(-3, 4, -3), "fat_tower_base", rotation, true));
            endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(0, 4, 0), "fat_tower_middle", rotation, true));
            for (int i = 0; i < 2 && p_191086_6_.nextInt(3) != 0; ++i) {
                endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(0, 8, 0), "fat_tower_middle", rotation, true));
                for (Tuple<W_2163_m, c_1514_x> tuple : v_4262_N) {
                    if (!p_191086_6_.nextBoolean()) continue;
                    n_1700_B endcitypieces$citytemplate1 = C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, tuple.J_1907_R(), "bridge_end", rotation.n_1700_B(tuple.n_1700_B()), true));
                    C_1437_B.n_1700_B(p_191086_1_, u_1723_Y, p_191086_2_ + 1, endcitypieces$citytemplate1, null, p_191086_5_, p_191086_6_);
                }
            }
            C_1437_B.n_1700_B(p_191086_5_, C_1437_B.n_1700_B(p_191086_1_, endcitypieces$citytemplate, new c_1514_x(-2, 8, -2), "fat_tower_top", rotation, true));
            return true;
        }
    };

    private static n_1700_B n_1700_B(b_2085_h p_191090_0_, n_1700_B p_191090_1_, c_1514_x p_191090_2_, String p_191090_3_, W_2163_m p_191090_4_, boolean owerwrite) {
        n_1700_B endcitypieces$citytemplate = new n_1700_B(p_191090_0_, p_191090_3_, p_191090_1_.R_4764_Y, p_191090_4_, owerwrite);
        c_1514_x blockpos = p_191090_1_.n_1700_B.n_1700_B(p_191090_1_.J_1907_R, p_191090_2_, endcitypieces$citytemplate.J_1907_R, c_1514_x.ZERO);
        endcitypieces$citytemplate.n_1700_B(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        return endcitypieces$citytemplate;
    }

    public static void n_1700_B(b_2085_h p_191087_0_, c_1514_x p_191087_1_, W_2163_m p_191087_2_, List<E_3771_B> p_191087_3_, Random p_191087_4_) {
        w_1484_f.n_1700_B();
        R_4764_Y.n_1700_B();
        u_1723_Y.n_1700_B();
        P_1922_E.n_1700_B();
        n_1700_B endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191087_3_, new n_1700_B(p_191087_0_, "base_floor", p_191087_1_, p_191087_2_, true));
        endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191087_3_, C_1437_B.n_1700_B(p_191087_0_, endcitypieces$citytemplate, new c_1514_x(-1, 0, -1), "second_floor_1", p_191087_2_, false));
        endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191087_3_, C_1437_B.n_1700_B(p_191087_0_, endcitypieces$citytemplate, new c_1514_x(-1, 4, -1), "third_floor_1", p_191087_2_, false));
        endcitypieces$citytemplate = C_1437_B.n_1700_B(p_191087_3_, C_1437_B.n_1700_B(p_191087_0_, endcitypieces$citytemplate, new c_1514_x(-1, 8, -1), "third_roof", p_191087_2_, true));
        C_1437_B.n_1700_B(p_191087_0_, P_1922_E, 1, endcitypieces$citytemplate, null, p_191087_3_, p_191087_4_);
    }

    private static n_1700_B n_1700_B(List<E_3771_B> p_189935_0_, n_1700_B p_189935_1_) {
        p_189935_0_.add(p_189935_1_);
        return p_189935_1_;
    }

    private static boolean n_1700_B(b_2085_h p_191088_0_, J_1907_R p_191088_1_, int p_191088_2_, n_1700_B p_191088_3_, c_1514_x p_191088_4_, List<E_3771_B> p_191088_5_, Random p_191088_6_) {
        if (p_191088_2_ > 8) {
            return false;
        }
        ArrayList list = Lists.newArrayList();
        if (p_191088_1_.n_1700_B(p_191088_0_, p_191088_2_, p_191088_3_, p_191088_4_, list, p_191088_6_)) {
            boolean flag = false;
            int i = p_191088_6_.nextInt();
            for (E_3771_B structurepiece : list) {
                structurepiece.Q_4569_t = i;
                E_3771_B structurepiece1 = E_3771_B.n_1700_B(p_191088_5_, structurepiece.v_4262_N());
                if (structurepiece1 == null || structurepiece1.Q_4569_t == p_191088_3_.Q_4569_t) continue;
                flag = true;
                break;
            }
            if (!flag) {
                p_191088_5_.addAll(list);
                return true;
            }
        }
        return false;
    }

    public static class n_1700_B
    extends q_1616_l {
        private final String G_564_y;
        private final W_2163_m P_1922_E;
        private final boolean u_1723_Y;

        public n_1700_B(b_2085_h p_i47214_1_, String p_i47214_2_, c_1514_x p_i47214_3_, W_2163_m p_i47214_4_, boolean overwriteIn) {
            super(StructurePieceType.c_4037_x, 0);
            this.G_564_y = p_i47214_2_;
            this.R_4764_Y = p_i47214_3_;
            this.P_1922_E = p_i47214_4_;
            this.u_1723_Y = overwriteIn;
            this.n_1700_B(p_i47214_1_);
        }

        public n_1700_B(b_2085_h p_i50598_1_, U_2912_j p_i50598_2_) {
            super(StructurePieceType.c_4037_x, p_i50598_2_);
            this.G_564_y = p_i50598_2_.M_588_G("Template");
            this.P_1922_E = W_2163_m.valueOf(p_i50598_2_.M_588_G("Rot"));
            this.u_1723_Y = p_i50598_2_.t_1786_h("OW");
            this.n_1700_B(p_i50598_1_);
        }

        private void n_1700_B(b_2085_h p_191085_1_) {
            a_2886_t template = p_191085_1_.n_1700_B(new g_2336_b("end_city/" + this.G_564_y));
            w_1748_S placementsettings = (this.u_1723_Y ? n_1700_B : J_1907_R).n_1700_B().n_1700_B(this.P_1922_E);
            this.n_1700_B(template, this.R_4764_Y, placementsettings);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Template", this.G_564_y);
            tagCompound.n_1700_B("Rot", this.P_1922_E.name());
            tagCompound.n_1700_B("OW", this.u_1723_Y);
        }

        @Override
        protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
            if (function.startsWith("Chest")) {
                c_1514_x blockpos = pos.down();
                if (sbb.J_1907_R(blockpos)) {
                    V_4572_l.n_1700_B(worldIn, rand, blockpos, o_4810_o.R_4764_Y);
                }
            } else if (function.startsWith("Sentry")) {
                m_1605_o shulkerentity = t_5_h.Ops.n_1700_B(worldIn.J_1907_R());
                shulkerentity.J_1907_R((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
                shulkerentity.w_1484_f(pos);
                worldIn.a_(shulkerentity);
            } else if (function.startsWith("Elytra")) {
                y_740_d itemframeentity = new y_740_d(worldIn.J_1907_R(), pos, this.P_1922_E.n_1700_B(b_257_Y.G_564_y));
                itemframeentity.n_1700_B(new Z_1993_T(Items.NyliumBlock), false);
                worldIn.a_(itemframeentity);
            }
        }
    }

    static interface J_1907_R {
        public void n_1700_B();

        public boolean n_1700_B(b_2085_h var1, int var2, n_1700_B var3, c_1514_x var4, List<E_3771_B> var5, Random var6);
    }
}


