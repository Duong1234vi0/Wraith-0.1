/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.I_1170_F;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.R_1815_U;
import lightning.product.Potions;
import lightning.product.ParticleOptions;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.ParticleArgument;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.q_2896_o;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.w_1454_v;
import lightning.product.y_528_b;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class B_1132_Q
extends N_4263_v {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final h_256_u<Float> J_1907_R = C_4114_x.n_1700_B(B_1132_Q.class, EntityDataSerializers.R_4764_Y);
    private static final h_256_u<Integer> R_4764_Y = C_4114_x.n_1700_B(B_1132_Q.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> G_564_y = C_4114_x.n_1700_B(B_1132_Q.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<ParticleOptions> P_1922_E = C_4114_x.n_1700_B(B_1132_Q.class, EntityDataSerializers.s_956_w);
    private y_528_b u_1723_Y = Potions.n_1700_B;
    private final List<k_2610_C> v_4262_N = Lists.newArrayList();
    private final Map<N_4263_v, Integer> w_1484_f = Maps.newHashMap();
    private int t_148_a = 600;
    private int s_956_w = 20;
    private int u_2550_I = 20;
    private boolean M_588_G;
    private int P_4830_p;
    private float h_1847_R;
    private float Q_4569_t;
    private r_4811_B M_182_A;
    private UUID t_1786_h;

    public B_1132_Q(t_5_h<? extends B_1132_Q> cloud, b_4507_u world) {
        super(cloud, world);
        this.j_1564_a = true;
        this.n_1700_B(3.0f);
    }

    public B_1132_Q(b_4507_u worldIn, double x, double y, double z) {
        this((t_5_h<? extends B_1132_Q>)t_5_h.n_1700_B, worldIn);
        this.J_1907_R(x, y, z);
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(R_4764_Y, 0);
        this.D_60_a().n_1700_B(J_1907_R, Float.valueOf(0.5f));
        this.D_60_a().n_1700_B(G_564_y, false);
        this.D_60_a().n_1700_B(P_1922_E, ParticleTypes.Y_259_p);
    }

    public void n_1700_B(float radiusIn) {
        if (!this.O_508_d.Y_259_p) {
            this.D_60_a().J_1907_R(J_1907_R, Float.valueOf(radiusIn));
        }
    }

    @Override
    public void g_() {
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        super.g_();
        this.J_1907_R(d0, d1, d2);
    }

    public float P_1922_E() {
        return this.D_60_a().n_1700_B(J_1907_R).floatValue();
    }

    public void n_1700_B(y_528_b potionIn) {
        this.u_1723_Y = potionIn;
        if (!this.M_588_G) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        if (this.u_1723_Y == Potions.n_1700_B && this.v_4262_N.isEmpty()) {
            this.D_60_a().J_1907_R(R_4764_Y, 0);
        } else {
            this.D_60_a().J_1907_R(R_4764_Y, L_1875_m.n_1700_B(L_1875_m.n_1700_B(this.u_1723_Y, this.v_4262_N)));
        }
    }

    public void n_1700_B(k_2610_C effect) {
        this.v_4262_N.add(effect);
        if (!this.M_588_G) {
            this.h_1847_R();
        }
    }

    public int u_1723_Y() {
        return this.D_60_a().n_1700_B(R_4764_Y);
    }

    public void n_1700_B(int colorIn) {
        this.M_588_G = true;
        this.D_60_a().J_1907_R(R_4764_Y, colorIn);
    }

    public ParticleOptions v_4262_N() {
        return this.D_60_a().n_1700_B(P_1922_E);
    }

    public void n_1700_B(ParticleOptions particleData) {
        this.D_60_a().J_1907_R(P_1922_E, particleData);
    }

    protected void n_1700_B(boolean ignoreRadius) {
        this.D_60_a().J_1907_R(G_564_y, ignoreRadius);
    }

    public boolean w_1484_f() {
        return this.D_60_a().n_1700_B(G_564_y);
    }

    public int t_148_a() {
        return this.t_148_a;
    }

    public void J_1907_R(int durationIn) {
        this.t_148_a = durationIn;
    }

    @Override
    public void v_() {
        block23: {
            boolean flag1;
            float f;
            boolean flag;
            block21: {
                ParticleOptions iparticledata;
                block22: {
                    super.v_();
                    flag = this.w_1484_f();
                    f = this.P_1922_E();
                    if (!this.O_508_d.Y_259_p) break block21;
                    iparticledata = this.v_4262_N();
                    if (!flag) break block22;
                    if (!this.RealmsWorldOptions.nextBoolean()) break block23;
                    for (int i = 0; i < 2; ++i) {
                        float f1 = this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
                        float f2 = u_530_F.R_4764_Y(this.RealmsWorldOptions.nextFloat()) * 0.2f;
                        float f3 = u_530_F.J_1907_R(f1) * f2;
                        float f4 = u_530_F.n_1700_B(f1) * f2;
                        if (iparticledata.G_564_y() == ParticleTypes.Y_259_p) {
                            int j = this.RealmsWorldOptions.nextBoolean() ? 0xFFFFFF : this.u_1723_Y();
                            int k = j >> 16 & 0xFF;
                            int l = j >> 8 & 0xFF;
                            int i1 = j & 0xFF;
                            this.O_508_d.J_1907_R(iparticledata, this.O_3598_v() + (double)f3, this.X_2960_b(), this.l_2647_k() + (double)f4, (double)((float)k / 255.0f), (double)((float)l / 255.0f), (float)i1 / 255.0f);
                            continue;
                        }
                        this.O_508_d.J_1907_R(iparticledata, this.O_3598_v() + (double)f3, this.X_2960_b(), this.l_2647_k() + (double)f4, 0.0, 0.0, 0.0);
                    }
                    break block23;
                }
                float f5 = (float)Math.PI * f * f;
                int k1 = 0;
                while ((float)k1 < f5) {
                    float f6 = this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
                    float f7 = u_530_F.R_4764_Y(this.RealmsWorldOptions.nextFloat()) * f;
                    float f8 = u_530_F.J_1907_R(f6) * f7;
                    float f9 = u_530_F.n_1700_B(f6) * f7;
                    if (iparticledata.G_564_y() == ParticleTypes.Y_259_p) {
                        int l1 = this.u_1723_Y();
                        int i2 = l1 >> 16 & 0xFF;
                        int j2 = l1 >> 8 & 0xFF;
                        int j1 = l1 & 0xFF;
                        this.O_508_d.J_1907_R(iparticledata, this.O_3598_v() + (double)f8, this.X_2960_b(), this.l_2647_k() + (double)f9, (double)((float)i2 / 255.0f), (double)((float)j2 / 255.0f), (float)j1 / 255.0f);
                    } else {
                        this.O_508_d.J_1907_R(iparticledata, this.O_3598_v() + (double)f8, this.X_2960_b(), this.l_2647_k() + (double)f9, (0.5 - this.RealmsWorldOptions.nextDouble()) * 0.15, (double)0.01f, (0.5 - this.RealmsWorldOptions.nextDouble()) * 0.15);
                    }
                    ++k1;
                }
                break block23;
            }
            if (this.RealmsWorldResetDto >= this.s_956_w + this.t_148_a) {
                this.Ops();
                return;
            }
            boolean bl = flag1 = this.RealmsWorldResetDto < this.s_956_w;
            if (flag != flag1) {
                this.n_1700_B(flag1);
            }
            if (flag1) {
                return;
            }
            if (this.Q_4569_t != 0.0f) {
                if ((f += this.Q_4569_t) < 0.5f) {
                    this.Ops();
                    return;
                }
                this.n_1700_B(f);
            }
            if (this.RealmsWorldResetDto % 5 == 0) {
                Iterator<Map.Entry<N_4263_v, Integer>> iterator = this.w_1484_f.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<N_4263_v, Integer> entry = iterator.next();
                    if (this.RealmsWorldResetDto < entry.getValue()) continue;
                    iterator.remove();
                }
                ArrayList list = Lists.newArrayList();
                for (k_2610_C effectinstance1 : this.u_1723_Y.n_1700_B()) {
                    list.add(new k_2610_C(effectinstance1.n_1700_B(), effectinstance1.J_1907_R() / 4, effectinstance1.R_4764_Y(), effectinstance1.G_564_y(), effectinstance1.P_1922_E()));
                }
                list.addAll(this.v_4262_N);
                if (list.isEmpty()) {
                    this.w_1484_f.clear();
                } else {
                    List<r_4811_B> list1 = this.O_508_d.n_1700_B(r_4811_B.class, this.i_601_W());
                    if (!list1.isEmpty()) {
                        for (r_4811_B livingentity : list1) {
                            double d1;
                            double d0;
                            double d2;
                            if (this.w_1484_f.containsKey(livingentity) || !livingentity.F_1446_q() || !((d2 = (d0 = livingentity.O_3598_v() - this.O_3598_v()) * d0 + (d1 = livingentity.l_2647_k() - this.l_2647_k()) * d1) <= (double)(f * f))) continue;
                            this.w_1484_f.put(livingentity, this.RealmsWorldResetDto + this.u_2550_I);
                            for (k_2610_C effectinstance : list) {
                                if (effectinstance.n_1700_B().n_1700_B()) {
                                    effectinstance.n_1700_B().n_1700_B(this, this.u_2550_I(), livingentity, effectinstance.R_4764_Y(), 0.5);
                                    continue;
                                }
                                livingentity.n_1700_B(new k_2610_C(effectinstance));
                            }
                            if (this.h_1847_R != 0.0f) {
                                if ((f += this.h_1847_R) < 0.5f) {
                                    this.Ops();
                                    return;
                                }
                                this.n_1700_B(f);
                            }
                            if (this.P_4830_p == 0) continue;
                            this.t_148_a += this.P_4830_p;
                            if (this.t_148_a > 0) continue;
                            this.Ops();
                            return;
                        }
                    }
                }
            }
        }
    }

    public void G_564_y(float radiusOnUseIn) {
        this.h_1847_R = radiusOnUseIn;
    }

    public void u_1723_Y(float radiusPerTickIn) {
        this.Q_4569_t = radiusPerTickIn;
    }

    public void R_4764_Y(int waitTimeIn) {
        this.s_956_w = waitTimeIn;
    }

    public void n_1700_B(@Nullable r_4811_B ownerIn) {
        this.M_182_A = ownerIn;
        this.t_1786_h = ownerIn == null ? null : ownerIn.w_2705_t();
    }

    @Nullable
    public r_4811_B u_2550_I() {
        N_4263_v entity;
        if (this.M_182_A == null && this.t_1786_h != null && this.O_508_d instanceof e_3591_l && (entity = ((e_3591_l)this.O_508_d).J_1907_R(this.t_1786_h)) instanceof r_4811_B) {
            this.M_182_A = (r_4811_B)entity;
        }
        return this.M_182_A;
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        this.RealmsWorldResetDto = compound.w_1484_f("Age");
        this.t_148_a = compound.w_1484_f("Duration");
        this.s_956_w = compound.w_1484_f("WaitTime");
        this.u_2550_I = compound.w_1484_f("ReapplicationDelay");
        this.P_4830_p = compound.w_1484_f("DurationOnUse");
        this.h_1847_R = compound.s_956_w("RadiusOnUse");
        this.Q_4569_t = compound.s_956_w("RadiusPerTick");
        this.n_1700_B(compound.s_956_w("Radius"));
        if (compound.J_1907_R("Owner")) {
            this.t_1786_h = compound.n_1700_B("Owner");
        }
        if (compound.R_4764_Y("Particle", 8)) {
            try {
                this.n_1700_B(ParticleArgument.J_1907_R(new StringReader(compound.M_588_G("Particle"))));
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                n_1700_B.warn("Couldn't load custom particle {}", (Object)compound.M_588_G("Particle"), (Object)commandsyntaxexception);
            }
        }
        if (compound.R_4764_Y("Color", 99)) {
            this.n_1700_B(compound.w_1484_f("Color"));
        }
        if (compound.R_4764_Y("Potion", 8)) {
            this.n_1700_B(L_1875_m.R_4764_Y(compound));
        }
        if (compound.R_4764_Y("Effects", 9)) {
            q_2896_o listnbt = compound.G_564_y("Effects", 10);
            this.v_4262_N.clear();
            for (int i = 0; i < listnbt.size(); ++i) {
                k_2610_C effectinstance = k_2610_C.J_1907_R(listnbt.n_1700_B(i));
                if (effectinstance == null) continue;
                this.n_1700_B(effectinstance);
            }
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        compound.J_1907_R("Age", this.RealmsWorldResetDto);
        compound.J_1907_R("Duration", this.t_148_a);
        compound.J_1907_R("WaitTime", this.s_956_w);
        compound.J_1907_R("ReapplicationDelay", this.u_2550_I);
        compound.J_1907_R("DurationOnUse", this.P_4830_p);
        compound.n_1700_B("RadiusOnUse", this.h_1847_R);
        compound.n_1700_B("RadiusPerTick", this.Q_4569_t);
        compound.n_1700_B("Radius", this.P_1922_E());
        compound.n_1700_B("Particle", this.v_4262_N().R_4764_Y());
        if (this.t_1786_h != null) {
            compound.n_1700_B("Owner", this.t_1786_h);
        }
        if (this.M_588_G) {
            compound.J_1907_R("Color", this.u_1723_Y());
        }
        if (this.u_1723_Y != Potions.n_1700_B && this.u_1723_Y != null) {
            compound.n_1700_B("Potion", V_3137_a.B_1668_F.J_1907_R(this.u_1723_Y).toString());
        }
        if (!this.v_4262_N.isEmpty()) {
            q_2896_o listnbt = new q_2896_o();
            for (k_2610_C effectinstance : this.v_4262_N) {
                listnbt.add(effectinstance.n_1700_B(new U_2912_j()));
            }
            compound.n_1700_B("Effects", listnbt);
        }
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (J_1907_R.equals(key)) {
            this.g_();
        }
        super.n_1700_B(key);
    }

    @Override
    public w_1454_v h_() {
        return w_1454_v.G_564_y;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return R_1815_U.J_1907_R(this.P_1922_E() * 2.0f, 0.5f);
    }
}


