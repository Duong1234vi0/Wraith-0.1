/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Random;
import lightning.product.C_990_G;
import lightning.product.F_1241_B;
import lightning.product.N_4263_v;
import lightning.product.SpikeConfiguration;
import lightning.product.V_3354_l;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Feature;
import lightning.product.SpikeFeature;

public abstract sealed class F_4543_b
extends Enum<F_4543_b> {
    public static final /* enum */ F_4543_b n_1700_B = new F_4543_b(){

        @Override
        public void n_1700_B(e_3591_l worldIn, C_990_G manager, List<V_3354_l> crystals, int ticks, c_1514_x pos) {
            c_1514_x blockpos = new c_1514_x(0, 128, 0);
            for (V_3354_l endercrystalentity : crystals) {
                endercrystalentity.n_1700_B(blockpos);
            }
            manager.n_1700_B(J_1907_R);
        }
    };
    public static final /* enum */ F_4543_b J_1907_R = new F_4543_b(){

        @Override
        public void n_1700_B(e_3591_l worldIn, C_990_G manager, List<V_3354_l> crystals, int ticks, c_1514_x pos) {
            if (ticks < 100) {
                if (ticks == 0 || ticks == 50 || ticks == 51 || ticks == 52 || ticks >= 95) {
                    worldIn.R_4764_Y(3001, new c_1514_x(0, 128, 0), 0);
                }
            } else {
                manager.n_1700_B(R_4764_Y);
            }
        }
    };
    public static final /* enum */ F_4543_b R_4764_Y = new F_4543_b(){

        @Override
        public void n_1700_B(e_3591_l worldIn, C_990_G manager, List<V_3354_l> crystals, int ticks, c_1514_x pos) {
            boolean flag1;
            int i = 40;
            boolean flag = ticks % 40 == 0;
            boolean bl = flag1 = ticks % 40 == 39;
            if (flag || flag1) {
                int j = ticks / 40;
                List<SpikeFeature.n_1700_B> list = SpikeFeature.n_1700_B(worldIn);
                if (j < list.size()) {
                    SpikeFeature.n_1700_B endspikefeature$endspike = list.get(j);
                    if (flag) {
                        for (V_3354_l endercrystalentity : crystals) {
                            endercrystalentity.n_1700_B(new c_1514_x(endspikefeature$endspike.n_1700_B(), endspikefeature$endspike.G_564_y() + 1, endspikefeature$endspike.J_1907_R()));
                        }
                    } else {
                        int k = 10;
                        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(new c_1514_x(endspikefeature$endspike.n_1700_B() - 10, endspikefeature$endspike.G_564_y() - 10, endspikefeature$endspike.J_1907_R() - 10), new c_1514_x(endspikefeature$endspike.n_1700_B() + 10, endspikefeature$endspike.G_564_y() + 10, endspikefeature$endspike.J_1907_R() + 10))) {
                            worldIn.n_1700_B(blockpos, false);
                        }
                        worldIn.n_1700_B((N_4263_v)null, (float)endspikefeature$endspike.n_1700_B() + 0.5f, (double)endspikefeature$endspike.G_564_y(), (double)((float)endspikefeature$endspike.J_1907_R() + 0.5f), 5.0f, F_1241_B.n_1700_B.R_4764_Y);
                        SpikeConfiguration endspikefeatureconfig = new SpikeConfiguration(true, (List<SpikeFeature.n_1700_B>)ImmutableList.of((Object)endspikefeature$endspike), new c_1514_x(0, 128, 0));
                        Feature.H_2857_Y.J_1907_R(endspikefeatureconfig).n_1700_B(worldIn, worldIn.Y_259_p().t_148_a(), new Random(), new c_1514_x(endspikefeature$endspike.n_1700_B(), 45, endspikefeature$endspike.J_1907_R()));
                    }
                } else if (flag) {
                    manager.n_1700_B(G_564_y);
                }
            }
        }
    };
    public static final /* enum */ F_4543_b G_564_y = new F_4543_b(){

        @Override
        public void n_1700_B(e_3591_l worldIn, C_990_G manager, List<V_3354_l> crystals, int ticks, c_1514_x pos) {
            if (ticks >= 100) {
                manager.n_1700_B(P_1922_E);
                manager.u_1723_Y();
                for (V_3354_l endercrystalentity : crystals) {
                    endercrystalentity.n_1700_B((c_1514_x)null);
                    worldIn.n_1700_B(endercrystalentity, endercrystalentity.O_3598_v(), endercrystalentity.X_2960_b(), endercrystalentity.l_2647_k(), 6.0f, F_1241_B.n_1700_B.n_1700_B);
                    endercrystalentity.Ops();
                }
            } else if (ticks >= 80) {
                worldIn.R_4764_Y(3001, new c_1514_x(0, 128, 0), 0);
            } else if (ticks == 0) {
                for (V_3354_l endercrystalentity1 : crystals) {
                    endercrystalentity1.n_1700_B(new c_1514_x(0, 128, 0));
                }
            } else if (ticks < 5) {
                worldIn.R_4764_Y(3001, new c_1514_x(0, 128, 0), 0);
            }
        }
    };
    public static final /* enum */ F_4543_b P_1922_E = new F_4543_b(){

        @Override
        public void n_1700_B(e_3591_l worldIn, C_990_G manager, List<V_3354_l> crystals, int ticks, c_1514_x pos) {
        }
    };
    private static final /* synthetic */ F_4543_b[] u_1723_Y;

    public static F_4543_b[] values() {
        return (F_4543_b[])u_1723_Y.clone();
    }

    public static F_4543_b valueOf(String name) {
        return Enum.valueOf(F_4543_b.class, name);
    }

    public abstract void n_1700_B(e_3591_l var1, C_990_G var2, List<V_3354_l> var3, int var4, c_1514_x var5);

    private static /* synthetic */ F_4543_b[] n_1700_B() {
        return new F_4543_b[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
    }

    static {
        u_1723_Y = F_4543_b.n_1700_B();
    }
}


