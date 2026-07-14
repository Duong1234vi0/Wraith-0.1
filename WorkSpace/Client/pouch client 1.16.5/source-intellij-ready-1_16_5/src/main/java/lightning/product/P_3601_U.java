/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lightning.product.TestFunction;
import lightning.product.I_4817_s;
import lightning.product.GameTestBatch;
import lightning.product.W_2163_m;
import lightning.product.a_3322_s;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.j_2644_e;
import lightning.product.GameTestTicker;
import lightning.product.GameTestListener;
import lightning.product.n_3842_i;
import lightning.product.MultipleTestTracker;
import lightning.product.s_4514_h;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class P_3601_U {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final c_1514_x J_1907_R;
    private final e_3591_l R_4764_Y;
    private final GameTestTicker G_564_y;
    private final int P_1922_E;
    private final List<s_4514_h> u_1723_Y = Lists.newArrayList();
    private final Map<s_4514_h, c_1514_x> v_4262_N = Maps.newHashMap();
    private final List<Pair<GameTestBatch, Collection<s_4514_h>>> w_1484_f = Lists.newArrayList();
    private MultipleTestTracker t_148_a;
    private int s_956_w = 0;
    private c_1514_x.n_1700_B u_2550_I;

    public P_3601_U(Collection<GameTestBatch> p_i232555_1_, c_1514_x p_i232555_2_, W_2163_m p_i232555_3_, e_3591_l p_i232555_4_, GameTestTicker p_i232555_5_, int p_i232555_6_) {
        this.u_2550_I = p_i232555_2_.toMutable();
        this.J_1907_R = p_i232555_2_;
        this.R_4764_Y = p_i232555_4_;
        this.G_564_y = p_i232555_5_;
        this.P_1922_E = p_i232555_6_;
        p_i232555_1_.forEach(p_240539_3_ -> {
            ArrayList collection = Lists.newArrayList();
            for (TestFunction testfunctioninfo : p_240539_3_.J_1907_R()) {
                s_4514_h testtracker = new s_4514_h(testfunctioninfo, p_i232555_3_, p_i232555_4_);
                collection.add(testtracker);
                this.u_1723_Y.add(testtracker);
            }
            this.w_1484_f.add((Pair<GameTestBatch, Collection<s_4514_h>>)Pair.of((Object)p_240539_3_, (Object)collection));
        });
    }

    public List<s_4514_h> n_1700_B() {
        return this.u_1723_Y;
    }

    public void J_1907_R() {
        this.n_1700_B(0);
    }

    private void n_1700_B(int p_229477_1_) {
        this.s_956_w = p_229477_1_;
        this.t_148_a = new MultipleTestTracker();
        if (p_229477_1_ < this.w_1484_f.size()) {
            Pair<GameTestBatch, Collection<s_4514_h>> pair = this.w_1484_f.get(this.s_956_w);
            GameTestBatch testbatch = (GameTestBatch)pair.getFirst();
            Collection collection = (Collection)pair.getSecond();
            this.n_1700_B(collection);
            testbatch.n_1700_B(this.R_4764_Y);
            String s = testbatch.n_1700_B();
            n_1700_B.info("Running test batch '" + s + "' (" + collection.size() + " tests)...");
            collection.forEach(p_229483_1_ -> {
                this.t_148_a.n_1700_B((s_4514_h)p_229483_1_);
                this.t_148_a.n_1700_B(new GameTestListener(){

                    @Override
                    public void n_1700_B(s_4514_h p_225644_1_) {
                    }

                    @Override
                    public void J_1907_R(s_4514_h p_225645_1_) {
                        P_3601_U.this.n_1700_B(p_225645_1_);
                    }
                });
                c_1514_x blockpos = this.v_4262_N.get(p_229483_1_);
                n_3842_i.n_1700_B(p_229483_1_, blockpos, this.G_564_y);
            });
        }
    }

    private void n_1700_B(s_4514_h p_229479_1_) {
        if (this.t_148_a.v_4262_N()) {
            this.n_1700_B(this.s_956_w + 1);
        }
    }

    private void n_1700_B(Collection<s_4514_h> p_229480_1_) {
        int i = 0;
        I_4817_s axisalignedbb = new I_4817_s(this.u_2550_I);
        for (s_4514_h testtracker : p_229480_1_) {
            c_1514_x blockpos = new c_1514_x(this.u_2550_I);
            j_2644_e structureblocktileentity = a_3322_s.n_1700_B(testtracker.P_4830_p(), blockpos, testtracker.h_1847_R(), 2, this.R_4764_Y, true);
            I_4817_s axisalignedbb1 = a_3322_s.n_1700_B(structureblocktileentity);
            testtracker.n_1700_B(structureblocktileentity.x_607_J());
            this.v_4262_N.put(testtracker, new c_1514_x(this.u_2550_I));
            axisalignedbb = axisalignedbb.union(axisalignedbb1);
            this.u_2550_I.J_1907_R((int)axisalignedbb1.getXSize() + 5, 0, 0);
            if (i++ % this.P_1922_E != this.P_1922_E - 1) continue;
            this.u_2550_I.J_1907_R(0, 0, (int)axisalignedbb.getZSize() + 6);
            this.u_2550_I.setX(this.J_1907_R.getX());
            axisalignedbb = new I_4817_s(this.u_2550_I);
        }
    }
}


