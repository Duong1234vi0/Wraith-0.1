/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  org.apache.commons.lang3.mutable.MutableObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import lightning.product.StructureFeature;
import lightning.product.PoolElementStructurePiece;
import lightning.product.Pools;
import lightning.product.I_4817_s;
import lightning.product.BoundingBox;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.EmptyPoolElement;
import lightning.product.g_2336_b;
import lightning.product.StructurePoolElement;
import lightning.product.JigsawBlock;
import lightning.product.WritableRegistry;
import lightning.product.r_4097_j;
import lightning.product.s_1395_c;
import lightning.product.JigsawConfiguration;
import lightning.product.x_268_Y;
import lightning.product.y_3814_I;
import lightning.product.BooleanOp;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class E_2181_N {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static void n_1700_B(r_4097_j p_242837_0_, JigsawConfiguration p_242837_1_, R_4764_Y p_242837_2_, z_1753_f p_242837_3_, b_2085_h p_242837_4_, c_1514_x p_242837_5_, List<? super PoolElementStructurePiece> p_242837_6_, Random p_242837_7_, boolean p_242837_8_, boolean p_242837_9_) {
        StructureFeature.P_1922_E();
        WritableRegistry<X_2241_P> mutableregistry = p_242837_0_.J_1907_R(V_3137_a.V_1446_Y);
        W_2163_m rotation = W_2163_m.n_1700_B(p_242837_7_);
        X_2241_P jigsawpattern = p_242837_1_.R_4764_Y().get();
        StructurePoolElement jigsawpiece = jigsawpattern.n_1700_B(p_242837_7_);
        PoolElementStructurePiece abstractvillagepiece = p_242837_2_.create(p_242837_4_, jigsawpiece, p_242837_5_, jigsawpiece.G_564_y(), rotation, jigsawpiece.n_1700_B(p_242837_4_, p_242837_5_, rotation));
        BoundingBox mutableboundingbox = abstractvillagepiece.v_4262_N();
        int i = (mutableboundingbox.G_564_y + mutableboundingbox.n_1700_B) / 2;
        int j = (mutableboundingbox.u_1723_Y + mutableboundingbox.R_4764_Y) / 2;
        int k = p_242837_9_ ? p_242837_5_.getY() + p_242837_3_.J_1907_R(i, j, z_2963_s.n_1700_B.n_1700_B) : p_242837_5_.getY();
        int l = mutableboundingbox.J_1907_R + abstractvillagepiece.G_564_y();
        abstractvillagepiece.n_1700_B(0, k - l, 0);
        p_242837_6_.add(abstractvillagepiece);
        if (p_242837_1_.J_1907_R() > 0) {
            int i1 = 80;
            I_4817_s axisalignedbb = new I_4817_s(i - 80, k - 80, j - 80, i + 80 + 1, k + 80 + 1, j + 80 + 1);
            n_1700_B jigsawmanager$assembler = new n_1700_B(mutableregistry, p_242837_1_.J_1907_R(), p_242837_2_, p_242837_3_, p_242837_4_, p_242837_6_, p_242837_7_);
            jigsawmanager$assembler.w_1484_f.addLast(new J_1907_R(abstractvillagepiece, (MutableObject<s_1395_c>)new MutableObject((Object)x_268_Y.n_1700_B(x_268_Y.n_1700_B(axisalignedbb), x_268_Y.n_1700_B(I_4817_s.toImmutable(mutableboundingbox)), BooleanOp.P_1922_E)), k + 80, 0));
            while (!jigsawmanager$assembler.w_1484_f.isEmpty()) {
                J_1907_R jigsawmanager$entry = jigsawmanager$assembler.w_1484_f.removeFirst();
                jigsawmanager$assembler.n_1700_B(jigsawmanager$entry.n_1700_B, jigsawmanager$entry.J_1907_R, jigsawmanager$entry.R_4764_Y, jigsawmanager$entry.G_564_y, p_242837_8_);
            }
        }
    }

    public static void n_1700_B(r_4097_j p_242838_0_, PoolElementStructurePiece p_242838_1_, int p_242838_2_, R_4764_Y p_242838_3_, z_1753_f p_242838_4_, b_2085_h p_242838_5_, List<? super PoolElementStructurePiece> p_242838_6_, Random p_242838_7_) {
        WritableRegistry<X_2241_P> mutableregistry = p_242838_0_.J_1907_R(V_3137_a.V_1446_Y);
        n_1700_B jigsawmanager$assembler = new n_1700_B(mutableregistry, p_242838_2_, p_242838_3_, p_242838_4_, p_242838_5_, p_242838_6_, p_242838_7_);
        jigsawmanager$assembler.w_1484_f.addLast(new J_1907_R(p_242838_1_, (MutableObject<s_1395_c>)new MutableObject((Object)x_268_Y.n_1700_B), 0, 0));
        while (!jigsawmanager$assembler.w_1484_f.isEmpty()) {
            J_1907_R jigsawmanager$entry = jigsawmanager$assembler.w_1484_f.removeFirst();
            jigsawmanager$assembler.n_1700_B(jigsawmanager$entry.n_1700_B, jigsawmanager$entry.J_1907_R, jigsawmanager$entry.R_4764_Y, jigsawmanager$entry.G_564_y, false);
        }
    }

    public static interface R_4764_Y {
        public PoolElementStructurePiece create(b_2085_h var1, StructurePoolElement var2, c_1514_x var3, int var4, W_2163_m var5, BoundingBox var6);
    }

    static final class n_1700_B {
        private final V_3137_a<X_2241_P> n_1700_B;
        private final int J_1907_R;
        private final R_4764_Y R_4764_Y;
        private final z_1753_f G_564_y;
        private final b_2085_h P_1922_E;
        private final List<? super PoolElementStructurePiece> u_1723_Y;
        private final Random v_4262_N;
        private final Deque<J_1907_R> w_1484_f = Queues.newArrayDeque();

        private n_1700_B(V_3137_a<X_2241_P> p_i242005_1_, int p_i242005_2_, R_4764_Y p_i242005_3_, z_1753_f p_i242005_4_, b_2085_h p_i242005_5_, List<? super PoolElementStructurePiece> p_i242005_6_, Random p_i242005_7_) {
            this.n_1700_B = p_i242005_1_;
            this.J_1907_R = p_i242005_2_;
            this.R_4764_Y = p_i242005_3_;
            this.G_564_y = p_i242005_4_;
            this.P_1922_E = p_i242005_5_;
            this.u_1723_Y = p_i242005_6_;
            this.v_4262_N = p_i242005_7_;
        }

        private void n_1700_B(PoolElementStructurePiece p_236831_1_, MutableObject<s_1395_c> p_236831_2_, int p_236831_3_, int p_236831_4_, boolean p_236831_5_) {
            StructurePoolElement jigsawpiece = p_236831_1_.J_1907_R();
            c_1514_x blockpos = p_236831_1_.R_4764_Y();
            W_2163_m rotation = p_236831_1_.n_1700_B();
            X_2241_P.n_1700_B jigsawpattern$placementbehaviour = jigsawpiece.R_4764_Y();
            boolean flag = jigsawpattern$placementbehaviour == X_2241_P.n_1700_B.J_1907_R;
            MutableObject<s_1395_c> mutableobject = new MutableObject<s_1395_c>();
            BoundingBox mutableboundingbox = p_236831_1_.v_4262_N();
            int i = mutableboundingbox.J_1907_R;
            block0: for (a_2886_t.J_1907_R template$blockinfo : jigsawpiece.n_1700_B(this.P_1922_E, blockpos, rotation, this.v_4262_N)) {
                b_257_Y direction = JigsawBlock.w_1484_f(template$blockinfo.J_1907_R);
                c_1514_x blockpos1 = template$blockinfo.n_1700_B;
                c_1514_x blockpos2 = blockpos1.offset(direction);
                int j = blockpos1.getY() - i;
                int k = -1;
                g_2336_b resourcelocation = new g_2336_b(template$blockinfo.R_4764_Y.M_588_G("pool"));
                Optional<X_2241_P> optional = this.n_1700_B.J_1907_R(resourcelocation);
                if (optional.isPresent() && (optional.get().R_4764_Y() != 0 || Objects.equals(resourcelocation, Pools.n_1700_B.n_1700_B()))) {
                    g_2336_b resourcelocation1 = optional.get().n_1700_B();
                    Optional<X_2241_P> optional1 = this.n_1700_B.J_1907_R(resourcelocation1);
                    if (optional1.isPresent() && (optional1.get().R_4764_Y() != 0 || Objects.equals(resourcelocation1, Pools.n_1700_B.n_1700_B()))) {
                        StructurePoolElement jigsawpiece1;
                        int l;
                        MutableObject<s_1395_c> mutableobject1;
                        boolean flag1 = mutableboundingbox.J_1907_R(blockpos2);
                        if (flag1) {
                            mutableobject1 = mutableobject;
                            l = i;
                            if (mutableobject.getValue() == null) {
                                mutableobject.setValue((Object)x_268_Y.n_1700_B(I_4817_s.toImmutable(mutableboundingbox)));
                            }
                        } else {
                            mutableobject1 = p_236831_2_;
                            l = p_236831_3_;
                        }
                        ArrayList list = Lists.newArrayList();
                        if (p_236831_4_ != this.J_1907_R) {
                            list.addAll(optional.get().J_1907_R(this.v_4262_N));
                        }
                        list.addAll(optional1.get().J_1907_R(this.v_4262_N));
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext() && (jigsawpiece1 = (StructurePoolElement)iterator.next()) != EmptyPoolElement.J_1907_R) {
                            for (W_2163_m rotation1 : W_2163_m.J_1907_R(this.v_4262_N)) {
                                List<a_2886_t.J_1907_R> list1 = jigsawpiece1.n_1700_B(this.P_1922_E, c_1514_x.ZERO, rotation1, this.v_4262_N);
                                BoundingBox mutableboundingbox1 = jigsawpiece1.n_1700_B(this.P_1922_E, c_1514_x.ZERO, rotation1);
                                int i1 = p_236831_5_ && mutableboundingbox1.P_1922_E() <= 16 ? list1.stream().mapToInt(p_242841_2_ -> {
                                    if (!mutableboundingbox1.J_1907_R(p_242841_2_.n_1700_B.offset(JigsawBlock.w_1484_f(p_242841_2_.J_1907_R)))) {
                                        return 0;
                                    }
                                    g_2336_b resourcelocation2 = new g_2336_b(p_242841_2_.R_4764_Y.M_588_G("pool"));
                                    Optional<X_2241_P> optional2 = this.n_1700_B.J_1907_R(resourcelocation2);
                                    Optional<Integer> optional3 = optional2.flatMap(p_242843_1_ -> this.n_1700_B.J_1907_R(p_242843_1_.n_1700_B()));
                                    int k3 = optional2.map(p_242842_1_ -> p_242842_1_.n_1700_B(this.P_1922_E)).orElse(0);
                                    int l3 = optional3.map(p_242840_1_ -> p_242840_1_.n_1700_B(this.P_1922_E)).orElse(0);
                                    return Math.max(k3, l3);
                                }).max().orElse(0) : 0;
                                for (a_2886_t.J_1907_R template$blockinfo1 : list1) {
                                    int i3;
                                    int i2;
                                    if (!JigsawBlock.n_1700_B(template$blockinfo, template$blockinfo1)) continue;
                                    c_1514_x blockpos3 = template$blockinfo1.n_1700_B;
                                    c_1514_x blockpos4 = new c_1514_x(blockpos2.getX() - blockpos3.getX(), blockpos2.getY() - blockpos3.getY(), blockpos2.getZ() - blockpos3.getZ());
                                    BoundingBox mutableboundingbox2 = jigsawpiece1.n_1700_B(this.P_1922_E, blockpos4, rotation1);
                                    int j1 = mutableboundingbox2.J_1907_R;
                                    X_2241_P.n_1700_B jigsawpattern$placementbehaviour1 = jigsawpiece1.R_4764_Y();
                                    boolean flag2 = jigsawpattern$placementbehaviour1 == X_2241_P.n_1700_B.J_1907_R;
                                    int k1 = blockpos3.getY();
                                    int l1 = j - k1 + JigsawBlock.w_1484_f(template$blockinfo.J_1907_R).s_956_w();
                                    if (flag && flag2) {
                                        i2 = i + l1;
                                    } else {
                                        if (k == -1) {
                                            k = this.G_564_y.J_1907_R(blockpos1.getX(), blockpos1.getZ(), z_2963_s.n_1700_B.n_1700_B);
                                        }
                                        i2 = k - k1;
                                    }
                                    int j2 = i2 - j1;
                                    BoundingBox mutableboundingbox3 = mutableboundingbox2.J_1907_R(0, j2, 0);
                                    c_1514_x blockpos5 = blockpos4.add(0, j2, 0);
                                    if (i1 > 0) {
                                        int k2 = Math.max(i1 + 1, mutableboundingbox3.P_1922_E - mutableboundingbox3.J_1907_R);
                                        mutableboundingbox3.P_1922_E = mutableboundingbox3.J_1907_R + k2;
                                    }
                                    if (x_268_Y.R_4764_Y((s_1395_c)mutableobject1.getValue(), x_268_Y.n_1700_B(I_4817_s.toImmutable(mutableboundingbox3).shrink(0.25)), BooleanOp.R_4764_Y)) continue;
                                    mutableobject1.setValue((Object)x_268_Y.J_1907_R((s_1395_c)mutableobject1.getValue(), x_268_Y.n_1700_B(I_4817_s.toImmutable(mutableboundingbox3)), BooleanOp.P_1922_E));
                                    int j3 = p_236831_1_.G_564_y();
                                    int l2 = flag2 ? j3 - l1 : jigsawpiece1.G_564_y();
                                    PoolElementStructurePiece abstractvillagepiece = this.R_4764_Y.create(this.P_1922_E, jigsawpiece1, blockpos5, l2, rotation1, mutableboundingbox3);
                                    if (flag) {
                                        i3 = i + j;
                                    } else if (flag2) {
                                        i3 = i2 + k1;
                                    } else {
                                        if (k == -1) {
                                            k = this.G_564_y.J_1907_R(blockpos1.getX(), blockpos1.getZ(), z_2963_s.n_1700_B.n_1700_B);
                                        }
                                        i3 = k + l1 / 2;
                                    }
                                    p_236831_1_.n_1700_B(new y_3814_I(blockpos2.getX(), i3 - j + j3, blockpos2.getZ(), l1, jigsawpattern$placementbehaviour1));
                                    abstractvillagepiece.n_1700_B(new y_3814_I(blockpos1.getX(), i3 - k1 + l2, blockpos1.getZ(), -l1, jigsawpattern$placementbehaviour));
                                    this.u_1723_Y.add(abstractvillagepiece);
                                    if (p_236831_4_ + 1 > this.J_1907_R) continue block0;
                                    this.w_1484_f.addLast(new J_1907_R(abstractvillagepiece, mutableobject1, l, p_236831_4_ + 1));
                                    continue block0;
                                }
                            }
                        }
                        continue;
                    }
                    n_1700_B.warn("Empty or none existent fallback pool: {}", (Object)resourcelocation1);
                    continue;
                }
                n_1700_B.warn("Empty or none existent pool: {}", (Object)resourcelocation);
            }
        }
    }

    static final class J_1907_R {
        private final PoolElementStructurePiece n_1700_B;
        private final MutableObject<s_1395_c> J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        private J_1907_R(PoolElementStructurePiece p_i232042_1_, MutableObject<s_1395_c> p_i232042_2_, int p_i232042_3_, int p_i232042_4_) {
            this.n_1700_B = p_i232042_1_;
            this.J_1907_R = p_i232042_2_;
            this.R_4764_Y = p_i232042_3_;
            this.G_564_y = p_i232042_4_;
        }
    }
}


