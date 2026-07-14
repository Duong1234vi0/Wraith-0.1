/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.O_4882_g;
import lightning.product.U_2912_j;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_3005_b;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.ElytraItem;
import lightning.product.k_4231_L;
import lightning.product.k_4690_i;
import lightning.product.CompassItem;
import lightning.product.n_1494_c;
import lightning.product.n_3832_I;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.y_740_d;
import lightning.product.ItemPropertyFunction;

public class O_2592_x {
    private static final Map<g_2336_b, ItemPropertyFunction> n_1700_B = Maps.newHashMap();
    private static final g_2336_b J_1907_R = new g_2336_b("damaged");
    private static final g_2336_b R_4764_Y = new g_2336_b("damage");
    private static final ItemPropertyFunction G_564_y = (p_239434_0_, p_239434_1_, p_239434_2_) -> p_239434_0_.u_1723_Y() ? 1.0f : 0.0f;
    private static final ItemPropertyFunction P_1922_E = (p_239433_0_, p_239433_1_, p_239433_2_) -> u_530_F.n_1700_B((float)p_239433_0_.v_4262_N() / (float)p_239433_0_.w_1484_f(), 0.0f, 1.0f);
    private static final Map<q_1613_l, Map<g_2336_b, ItemPropertyFunction>> u_1723_Y = Maps.newHashMap();

    private static ItemPropertyFunction n_1700_B(g_2336_b id, ItemPropertyFunction propertyGetter) {
        n_1700_B.put(id, propertyGetter);
        return propertyGetter;
    }

    private static void n_1700_B(q_1613_l item, g_2336_b p_239418_1_, ItemPropertyFunction p_239418_2_) {
        u_1723_Y.computeIfAbsent(item, p_239416_0_ -> Maps.newHashMap()).put(p_239418_1_, p_239418_2_);
    }

    @Nullable
    public static ItemPropertyFunction n_1700_B(q_1613_l p_239417_0_, g_2336_b p_239417_1_) {
        ItemPropertyFunction iitempropertygetter;
        if (p_239417_0_.M_588_G() > 0) {
            if (R_4764_Y.equals(p_239417_1_)) {
                return P_1922_E;
            }
            if (J_1907_R.equals(p_239417_1_)) {
                return G_564_y;
            }
        }
        if ((iitempropertygetter = n_1700_B.get(p_239417_1_)) != null) {
            return iitempropertygetter;
        }
        Map<g_2336_b, ItemPropertyFunction> map = u_1723_Y.get(p_239417_0_);
        return map == null ? null : map.get(p_239417_1_);
    }

    static {
        O_2592_x.n_1700_B(new g_2336_b("lefthanded"), (Z_1993_T p_239432_0_, b_4507_u p_239432_1_, r_4811_B p_239432_2_) -> p_239432_2_ != null && p_239432_2_.d_2169_p() != k_4231_L.J_1907_R ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(new g_2336_b("cooldown"), (Z_1993_T p_239431_0_, b_4507_u p_239431_1_, r_4811_B p_239431_2_) -> p_239431_2_ instanceof a_3913_L ? ((a_3913_L)p_239431_2_).p_1458_L().n_1700_B(p_239431_0_.J_1907_R(), 0.0f) : 0.0f);
        O_2592_x.n_1700_B(new g_2336_b("custom_model_data"), (Z_1993_T p_239430_0_, b_4507_u p_239430_1_, r_4811_B p_239430_2_) -> p_239430_0_.h_1847_R() ? (float)p_239430_0_.Q_4569_t().w_1484_f("CustomModelData") : 0.0f);
        O_2592_x.n_1700_B(Items.R_1796_s, new g_2336_b("pull"), (Z_1993_T p_239429_0_, b_4507_u p_239429_1_, r_4811_B p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0f;
            }
            return p_239429_2_.B_2580_P() != p_239429_0_ ? 0.0f : (float)(p_239429_0_.u_2550_I() - p_239429_2_.U_144_f()) / 20.0f;
        });
        O_2592_x.n_1700_B(Items.R_1796_s, new g_2336_b("pulling"), (Z_1993_T p_239428_0_, b_4507_u p_239428_1_, r_4811_B p_239428_2_) -> p_239428_2_ != null && p_239428_2_.Y_601_j() && p_239428_2_.B_2580_P() == p_239428_0_ ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(Items.A_2629_w, new g_2336_b("time"), new ItemPropertyFunction(){
            private double n_1700_B;
            private double J_1907_R;
            private long R_4764_Y;

            @Override
            public float call(Z_1993_T p_call_1_, @Nullable b_4507_u p_call_2_, @Nullable r_4811_B p_call_3_) {
                N_4263_v entity;
                N_4263_v n_4263_v = entity = p_call_3_ != null ? p_call_3_ : p_call_1_.c_3005_b();
                if (entity == null) {
                    return 0.0f;
                }
                if (p_call_2_ == null && entity.O_508_d instanceof k_4690_i) {
                    p_call_2_ = (k_4690_i)entity.O_508_d;
                }
                if (p_call_2_ == null) {
                    return 0.0f;
                }
                double d0 = p_call_2_.G_624_v().P_1922_E() ? (double)p_call_2_.G_564_y(1.0f) : Math.random();
                d0 = this.n_1700_B(p_call_2_, d0);
                return (float)d0;
            }

            private double n_1700_B(b_4507_u p_239438_1_, double p_239438_2_) {
                if (p_239438_1_.X_933_l() != this.R_4764_Y) {
                    this.R_4764_Y = p_239438_1_.X_933_l();
                    double d0 = p_239438_2_ - this.n_1700_B;
                    d0 = u_530_F.R_4764_Y(d0 + 0.5, 1.0) - 0.5;
                    this.J_1907_R += d0 * 0.1;
                    this.J_1907_R *= 0.9;
                    this.n_1700_B = u_530_F.R_4764_Y(this.n_1700_B + this.J_1907_R, 1.0);
                }
                return this.n_1700_B;
            }
        });
        O_2592_x.n_1700_B(Items.X_1303_p, new g_2336_b("angle"), new ItemPropertyFunction(){
            private final n_1700_B n_1700_B = new n_1700_B();
            private final n_1700_B J_1907_R = new n_1700_B();

            @Override
            public float call(Z_1993_T p_call_1_, @Nullable b_4507_u p_call_2_, @Nullable r_4811_B p_call_3_) {
                N_4263_v entity;
                N_4263_v n_4263_v = entity = p_call_3_ != null ? p_call_3_ : p_call_1_.c_3005_b();
                if (entity == null) {
                    return 0.0f;
                }
                if (p_call_2_ == null && entity.O_508_d instanceof k_4690_i) {
                    p_call_2_ = entity.O_508_d;
                }
                if (p_call_2_ == null && entity.O_508_d instanceof c_3005_b) {
                    p_call_2_ = entity.O_508_d;
                }
                c_1514_x blockpos = CompassItem.G_564_y(p_call_1_) ? this.n_1700_B(p_call_2_, p_call_1_.M_182_A()) : this.n_1700_B(p_call_2_);
                long i = p_call_2_.X_933_l();
                if (blockpos != null && !(entity.s_4990_V().R_4764_Y((double)blockpos.getX() + 0.5, entity.s_4990_V().J_1907_R(), (double)blockpos.getZ() + 0.5) < (double)1.0E-5f)) {
                    double d3;
                    boolean flag = p_call_3_ instanceof a_3913_L && ((a_3913_L)p_call_3_).w_1484_f();
                    double d1 = 0.0;
                    if (flag) {
                        d1 = p_call_3_.p_178_J;
                    } else if (entity instanceof y_740_d) {
                        d1 = this.n_1700_B((y_740_d)entity);
                    } else if (entity instanceof n_1494_c) {
                        d1 = 180.0f - ((n_1494_c)entity).n_1700_B(0.5f) / ((float)Math.PI * 2) * 360.0f;
                    } else if (p_call_3_ != null) {
                        d1 = p_call_3_.C_1162_e;
                    }
                    d1 = u_530_F.R_4764_Y(d1 / 360.0, 1.0);
                    double d2 = this.n_1700_B(e_2866_D.n_1700_B(blockpos), entity) / 6.2831854820251465;
                    if (flag) {
                        if (this.n_1700_B.n_1700_B(i)) {
                            this.n_1700_B.n_1700_B(i, 0.5 - (d1 - 0.25));
                        }
                        d3 = d2 + this.n_1700_B.n_1700_B;
                    } else {
                        d3 = 0.5 - (d1 - 0.25 - d2);
                    }
                    return u_530_F.J_1907_R((float)d3, 1.0f);
                }
                if (this.J_1907_R.n_1700_B(i)) {
                    this.J_1907_R.n_1700_B(i, Math.random());
                }
                double d0 = this.J_1907_R.n_1700_B + (double)((float)p_call_1_.hashCode() / 2.14748365E9f);
                return u_530_F.J_1907_R((float)d0, 1.0f);
            }

            @Nullable
            private c_1514_x n_1700_B(b_4507_u p_239444_1_) {
                return p_239444_1_.G_624_v().P_1922_E() ? p_239444_1_.w_1457_N() : null;
            }

            @Nullable
            private c_1514_x n_1700_B(b_4507_u p_239442_1_, U_2912_j p_239442_2_) {
                Optional<f_2392_k<b_4507_u>> optional;
                boolean flag = p_239442_2_.P_1922_E("LodestonePos");
                boolean flag1 = p_239442_2_.P_1922_E("LodestoneDimension");
                if (flag && flag1 && (optional = CompassItem.n_1700_B(p_239442_2_)).isPresent() && p_239442_1_.g_2268_R() == optional.get()) {
                    return n_3832_I.J_1907_R(p_239442_2_.M_182_A("LodestonePos"));
                }
                return null;
            }

            private double n_1700_B(y_740_d p_239441_1_) {
                b_257_Y direction = p_239441_1_.o_2767_H();
                int i = direction.h_1847_R().R_4764_Y() ? 90 * direction.P_1922_E().n_1700_B() : 0;
                return u_530_F.J_1907_R(180 + direction.G_564_y() * 90 + p_239441_1_.Q_4569_t() * 45 + i);
            }

            private double n_1700_B(e_2866_D p_239443_1_, N_4263_v p_239443_2_) {
                return Math.atan2(p_239443_1_.R_4764_Y() - p_239443_2_.l_2647_k(), p_239443_1_.n_1700_B() - p_239443_2_.O_3598_v());
            }
        });
        O_2592_x.n_1700_B(Items.V_2454_J, new g_2336_b("pull"), (Z_1993_T p_239427_0_, b_4507_u p_239427_1_, r_4811_B p_239427_2_) -> {
            if (p_239427_2_ == null) {
                return 0.0f;
            }
            return Z_1630_j.G_564_y(p_239427_0_) ? 0.0f : (float)(p_239427_0_.u_2550_I() - p_239427_2_.U_144_f()) / (float)Z_1630_j.v_4262_N(p_239427_0_);
        });
        O_2592_x.n_1700_B(Items.V_2454_J, new g_2336_b("pulling"), (Z_1993_T p_239426_0_, b_4507_u p_239426_1_, r_4811_B p_239426_2_) -> p_239426_2_ != null && p_239426_2_.Y_601_j() && p_239426_2_.B_2580_P() == p_239426_0_ && !Z_1630_j.G_564_y(p_239426_0_) ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(Items.V_2454_J, new g_2336_b("charged"), (Z_1993_T p_239425_0_, b_4507_u p_239425_1_, r_4811_B p_239425_2_) -> p_239425_2_ != null && Z_1630_j.G_564_y(p_239425_0_) ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(Items.V_2454_J, new g_2336_b("firework"), (Z_1993_T p_239424_0_, b_4507_u p_239424_1_, r_4811_B p_239424_2_) -> p_239424_2_ != null && Z_1630_j.G_564_y(p_239424_0_) && Z_1630_j.n_1700_B(p_239424_0_, Items.FenceBlock) ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(Items.NyliumBlock, new g_2336_b("broken"), (Z_1993_T p_239423_0_, b_4507_u p_239423_1_, r_4811_B p_239423_2_) -> ElytraItem.G_564_y(p_239423_0_) ? 0.0f : 1.0f);
        O_2592_x.n_1700_B(Items.w_2223_C, new g_2336_b("cast"), (Z_1993_T p_239422_0_, b_4507_u p_239422_1_, r_4811_B p_239422_2_) -> {
            boolean flag1;
            if (p_239422_2_ == null) {
                return 0.0f;
            }
            boolean flag = p_239422_2_.A_2714_y() == p_239422_0_;
            boolean bl = flag1 = p_239422_2_.S_4035_N() == p_239422_0_;
            if (p_239422_2_.A_2714_y().J_1907_R() instanceof O_4882_g) {
                flag1 = false;
            }
            return (flag || flag1) && p_239422_2_ instanceof a_3913_L && ((a_3913_L)p_239422_2_).X_2960_b != null ? 1.0f : 0.0f;
        });
        O_2592_x.n_1700_B(Items.NoteBlock, new g_2336_b("blocking"), (Z_1993_T p_239421_0_, b_4507_u p_239421_1_, r_4811_B p_239421_2_) -> p_239421_2_ != null && p_239421_2_.Y_601_j() && p_239421_2_.B_2580_P() == p_239421_0_ ? 1.0f : 0.0f);
        O_2592_x.n_1700_B(Items.P_2605_j, new g_2336_b("throwing"), (Z_1993_T p_239419_0_, b_4507_u p_239419_1_, r_4811_B p_239419_2_) -> p_239419_2_ != null && p_239419_2_.Y_601_j() && p_239419_2_.B_2580_P() == p_239419_0_ ? 1.0f : 0.0f);
    }

    static class n_1700_B {
        private double n_1700_B;
        private double J_1907_R;
        private long R_4764_Y;

        private n_1700_B() {
        }

        private boolean n_1700_B(long p_239448_1_) {
            return this.R_4764_Y != p_239448_1_;
        }

        private void n_1700_B(long p_239449_1_, double p_239449_3_) {
            this.R_4764_Y = p_239449_1_;
            double d0 = p_239449_3_ - this.n_1700_B;
            d0 = u_530_F.R_4764_Y(d0 + 0.5, 1.0) - 0.5;
            this.J_1907_R += d0 * 0.1;
            this.J_1907_R *= 0.8;
            this.n_1700_B = u_530_F.R_4764_Y(this.n_1700_B + this.J_1907_R, 1.0);
        }
    }
}


