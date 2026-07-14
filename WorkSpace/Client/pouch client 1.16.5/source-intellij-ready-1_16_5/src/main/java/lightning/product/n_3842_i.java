/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Streams
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lightning.product.DebugPackets;
import lightning.product.D_4024_W;
import lightning.product.TestFunction;
import lightning.product.StringTag;
import lightning.product.J_4711_z;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.N_3426_c;
import lightning.product.P_3601_U;
import lightning.product.T_2915_h;
import lightning.product.U_2871_b;
import lightning.product.GameTestBatch;
import lightning.product.W_2163_m;
import lightning.product.Z_1993_T;
import lightning.product.a_2436_g;
import lightning.product.a_2886_t;
import lightning.product.a_3322_s;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.h_355_y;
import lightning.product.j_2644_e;
import lightning.product.j_3341_s;
import lightning.product.GameTestTicker;
import lightning.product.GameTestListener;
import lightning.product.q_2896_o;
import lightning.product.q_4099_E;
import lightning.product.Items;
import lightning.product.s_4514_h;
import lightning.product.u_3096_I;
import lightning.product.x_282_a;
import org.apache.commons.lang3.mutable.MutableInt;

public class n_3842_i {
    public static a_2436_g n_1700_B = new J_4711_z();

    public static void n_1700_B(s_4514_h p_240553_0_, c_1514_x p_240553_1_, GameTestTicker p_240553_2_) {
        p_240553_0_.n_1700_B();
        p_240553_2_.n_1700_B(p_240553_0_);
        p_240553_0_.n_1700_B(new GameTestListener(){

            @Override
            public void n_1700_B(s_4514_h p_225644_1_) {
                n_3842_i.n_1700_B(p_225644_1_, a_3742_W.m_2262_U);
            }

            @Override
            public void J_1907_R(s_4514_h p_225645_1_) {
                n_3842_i.n_1700_B(p_225645_1_, p_225645_1_.u_2550_I() ? a_3742_W.h_3270_j : a_3742_W.U_532_X);
                n_3842_i.n_1700_B(p_225645_1_, j_3341_s.G_564_y(p_225645_1_.s_956_w()));
                n_3842_i.n_1700_B(p_225645_1_);
            }
        });
        p_240553_0_.n_1700_B(p_240553_1_, 2);
    }

    public static Collection<s_4514_h> n_1700_B(Collection<GameTestBatch> p_240552_0_, c_1514_x p_240552_1_, W_2163_m p_240552_2_, e_3591_l p_240552_3_, GameTestTicker p_240552_4_, int p_240552_5_) {
        P_3601_U testexecutor = new P_3601_U(p_240552_0_, p_240552_1_, p_240552_2_, p_240552_3_, p_240552_4_, p_240552_5_);
        testexecutor.J_1907_R();
        return testexecutor.n_1700_B();
    }

    public static Collection<s_4514_h> J_1907_R(Collection<TestFunction> p_240554_0_, c_1514_x p_240554_1_, W_2163_m p_240554_2_, e_3591_l p_240554_3_, GameTestTicker p_240554_4_, int p_240554_5_) {
        return n_3842_i.n_1700_B(n_3842_i.n_1700_B(p_240554_0_), p_240554_1_, p_240554_2_, p_240554_3_, p_240554_4_, p_240554_5_);
    }

    public static Collection<GameTestBatch> n_1700_B(Collection<TestFunction> p_229548_0_) {
        HashMap map = Maps.newHashMap();
        p_229548_0_.forEach(p_229551_1_ -> {
            String s = p_229551_1_.P_1922_E();
            Collection collection = map.computeIfAbsent(s, p_229543_0_ -> Lists.newArrayList());
            collection.add(p_229551_1_);
        });
        return map.keySet().stream().flatMap(p_229550_1_ -> {
            Collection collection = (Collection)map.get(p_229550_1_);
            Consumer<e_3591_l> consumer = u_3096_I.R_4764_Y(p_229550_1_);
            MutableInt mutableint = new MutableInt();
            return Streams.stream((Iterable)Iterables.partition((Iterable)collection, (int)100)).map(p_240551_4_ -> new GameTestBatch(p_229550_1_ + ":" + mutableint.incrementAndGet(), collection, consumer));
        }).collect(Collectors.toList());
    }

    private static void n_1700_B(s_4514_h p_229563_0_) {
        Throwable throwable = p_229563_0_.s_956_w();
        String s = (p_229563_0_.u_2550_I() ? "" : "(optional) ") + p_229563_0_.R_4764_Y() + " failed! " + j_3341_s.G_564_y(throwable);
        n_3842_i.n_1700_B(p_229563_0_.P_1922_E(), p_229563_0_.u_2550_I() ? D_4024_W.P_4830_p : D_4024_W.Q_4569_t, s);
        if (throwable instanceof N_3426_c) {
            N_3426_c testblockposexception = (N_3426_c)throwable;
            n_3842_i.n_1700_B(p_229563_0_.P_1922_E(), testblockposexception.J_1907_R(), testblockposexception.n_1700_B());
        }
        n_1700_B.n_1700_B(p_229563_0_);
    }

    private static void n_1700_B(s_4514_h p_229559_0_, T_2915_h p_229559_1_) {
        e_3591_l serverworld = p_229559_0_.P_1922_E();
        c_1514_x blockpos = p_229559_0_.G_564_y();
        c_1514_x blockpos1 = new c_1514_x(-1, -1, -1);
        c_1514_x blockpos2 = a_2886_t.n_1700_B(blockpos.add(blockpos1), q_4099_E.n_1700_B, p_229559_0_.h_1847_R(), blockpos);
        serverworld.J_1907_R(blockpos2, a_3742_W.k_578_l.multiplayerClientSuggestionProvider().n_1700_B(p_229559_0_.h_1847_R()));
        c_1514_x blockpos3 = blockpos2.add(0, 1, 0);
        serverworld.J_1907_R(blockpos3, p_229559_1_.multiplayerClientSuggestionProvider());
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                c_1514_x blockpos4 = blockpos2.add(i, -1, j);
                serverworld.J_1907_R(blockpos4, a_3742_W.H_1883_T.multiplayerClientSuggestionProvider());
            }
        }
    }

    private static void n_1700_B(s_4514_h p_229560_0_, String p_229560_1_) {
        e_3591_l serverworld = p_229560_0_.P_1922_E();
        c_1514_x blockpos = p_229560_0_.G_564_y();
        c_1514_x blockpos1 = new c_1514_x(-1, 1, -1);
        c_1514_x blockpos2 = a_2886_t.n_1700_B(blockpos.add(blockpos1), q_4099_E.n_1700_B, p_229560_0_.h_1847_R(), blockpos);
        serverworld.J_1907_R(blockpos2, a_3742_W.F_489_x.multiplayerClientSuggestionProvider().n_1700_B(p_229560_0_.h_1847_R()));
        K_4074_S blockstate = serverworld.getBlockState(blockpos2);
        Z_1993_T itemstack = n_3842_i.n_1700_B(p_229560_0_.R_4764_Y(), p_229560_0_.u_2550_I(), p_229560_1_);
        h_355_y.n_1700_B((b_4507_u)serverworld, blockpos2, blockstate, itemstack);
    }

    private static Z_1993_T n_1700_B(String p_229546_0_, boolean p_229546_1_, String p_229546_2_) {
        Z_1993_T itemstack = new Z_1993_T(Items.CropBlock);
        q_2896_o listnbt = new q_2896_o();
        StringBuffer stringbuffer = new StringBuffer();
        Arrays.stream(p_229546_0_.split("\\.")).forEach(p_229547_1_ -> stringbuffer.append((String)p_229547_1_).append('\n'));
        if (!p_229546_1_) {
            stringbuffer.append("(optional)\n");
        }
        stringbuffer.append("-------------------\n");
        listnbt.add(StringTag.n_1700_B(stringbuffer.toString() + p_229546_2_));
        itemstack.n_1700_B("pages", listnbt);
        return itemstack;
    }

    private static void n_1700_B(e_3591_l p_229556_0_, D_4024_W p_229556_1_, String p_229556_2_) {
        p_229556_0_.n_1700_B(p_229557_0_ -> true).forEach(p_229544_2_ -> p_229544_2_.n_1700_B((x_282_a)new U_2871_b(p_229556_2_).n_1700_B(p_229556_1_), j_3341_s.J_1907_R));
    }

    public static void n_1700_B(e_3591_l p_229552_0_) {
        DebugPackets.n_1700_B(p_229552_0_);
    }

    private static void n_1700_B(e_3591_l p_229554_0_, c_1514_x p_229554_1_, String p_229554_2_) {
        DebugPackets.n_1700_B(p_229554_0_, p_229554_1_, p_229554_2_, -2130771968, Integer.MAX_VALUE);
    }

    public static void n_1700_B(e_3591_l p_229555_0_, c_1514_x p_229555_1_, GameTestTicker p_229555_2_, int p_229555_3_) {
        p_229555_2_.n_1700_B();
        c_1514_x blockpos = p_229555_1_.add(-p_229555_3_, 0, -p_229555_3_);
        c_1514_x blockpos1 = p_229555_1_.add(p_229555_3_, 0, p_229555_3_);
        c_1514_x.getAllInBox(blockpos, blockpos1).filter(p_229562_1_ -> p_229555_0_.getBlockState((c_1514_x)p_229562_1_).n_1700_B(a_3742_W.l_14_c)).forEach(p_229553_1_ -> {
            j_2644_e structureblocktileentity = (j_2644_e)p_229555_0_.getTileEntity((c_1514_x)p_229553_1_);
            c_1514_x blockpos2 = structureblocktileentity.x_607_J();
            BoundingBox mutableboundingbox = a_3322_s.J_1907_R(structureblocktileentity);
            a_3322_s.n_1700_B(mutableboundingbox, blockpos2.getY(), p_229555_0_);
        });
    }
}


