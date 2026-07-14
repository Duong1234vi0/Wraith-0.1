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
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_4388_s;
import lightning.product.AttributeMap;
import lightning.product.F_2904_S;
import lightning.product.MobEffects;
import lightning.product.Attribute;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;
import lightning.product.a_3913_L;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.j_956_y;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public class g_422_i {
    private final Map<Attribute, U_1880_G> n_1700_B = Maps.newHashMap();
    private final j_956_y J_1907_R;
    private final int R_4764_Y;
    @Nullable
    private String G_564_y;

    @Nullable
    public static g_422_i n_1700_B(int potionID) {
        return (g_422_i)V_3137_a.T_2506_i.n_1700_B(potionID);
    }

    public static int n_1700_B(g_422_i potionIn) {
        return V_3137_a.T_2506_i.n_1700_B(potionIn);
    }

    protected g_422_i(j_956_y typeIn, int liquidColorIn) {
        this.J_1907_R = typeIn;
        this.R_4764_Y = liquidColorIn;
    }

    public void n_1700_B(r_4811_B entityLivingBaseIn, int amplifier) {
        if (this == MobEffects.s_956_w) {
            if (entityLivingBaseIn.g_46_E() < entityLivingBaseIn.L_1733_J()) {
                entityLivingBaseIn.n_1700_B(1.0f);
            }
        } else if (this == MobEffects.w_1457_N) {
            if (entityLivingBaseIn.g_46_E() > 1.0f) {
                entityLivingBaseIn.n_1700_B(P_11_z.Q_4569_t, 1.0f);
            }
        } else if (this == MobEffects.Y_601_j) {
            entityLivingBaseIn.n_1700_B(P_11_z.M_182_A, 1.0f);
        } else if (this == MobEffects.t_1786_h && entityLivingBaseIn instanceof a_3913_L) {
            ((a_3913_L)entityLivingBaseIn).C_2741_M(0.005f * (float)(amplifier + 1));
        } else if (this == MobEffects.C_2741_M && entityLivingBaseIn instanceof a_3913_L) {
            if (!entityLivingBaseIn.O_508_d.Y_259_p) {
                ((a_3913_L)entityLivingBaseIn).P_2295_B().n_1700_B(amplifier + 1, 1.0f);
            }
        } else if (!(this == MobEffects.u_1723_Y && !entityLivingBaseIn.I_4477_R() || this == MobEffects.v_4262_N && entityLivingBaseIn.I_4477_R())) {
            if (this == MobEffects.v_4262_N && !entityLivingBaseIn.I_4477_R() || this == MobEffects.u_1723_Y && entityLivingBaseIn.I_4477_R()) {
                entityLivingBaseIn.n_1700_B(P_11_z.Q_4569_t, (float)(6 << amplifier));
            }
        } else {
            entityLivingBaseIn.n_1700_B((float)Math.max(4 << amplifier, 0));
        }
    }

    public void n_1700_B(@Nullable N_4263_v source, @Nullable N_4263_v indirectSource, r_4811_B entityLivingBaseIn, int amplifier, double health) {
        if (!(this == MobEffects.u_1723_Y && !entityLivingBaseIn.I_4477_R() || this == MobEffects.v_4262_N && entityLivingBaseIn.I_4477_R())) {
            if (this == MobEffects.v_4262_N && !entityLivingBaseIn.I_4477_R() || this == MobEffects.u_1723_Y && entityLivingBaseIn.I_4477_R()) {
                int j = (int)(health * (double)(6 << amplifier) + 0.5);
                if (source == null) {
                    entityLivingBaseIn.n_1700_B(P_11_z.Q_4569_t, (float)j);
                } else {
                    entityLivingBaseIn.n_1700_B(P_11_z.R_4764_Y(source, indirectSource), (float)j);
                }
            } else {
                this.n_1700_B(entityLivingBaseIn, amplifier);
            }
        } else {
            int i = (int)(health * (double)(4 << amplifier) + 0.5);
            entityLivingBaseIn.n_1700_B((float)i);
        }
    }

    public boolean n_1700_B(int duration, int amplifier) {
        if (this == MobEffects.s_956_w) {
            int k = 50 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            }
            return true;
        }
        if (this == MobEffects.w_1457_N) {
            int j = 25 >> amplifier;
            if (j > 0) {
                return duration % j == 0;
            }
            return true;
        }
        if (this == MobEffects.Y_601_j) {
            int i = 40 >> amplifier;
            if (i > 0) {
                return duration % i == 0;
            }
            return true;
        }
        return this == MobEffects.t_1786_h;
    }

    public boolean n_1700_B() {
        return false;
    }

    protected String J_1907_R() {
        if (this.G_564_y == null) {
            this.G_564_y = j_3341_s.n_1700_B("effect", V_3137_a.T_2506_i.J_1907_R(this));
        }
        return this.G_564_y;
    }

    public String R_4764_Y() {
        return this.J_1907_R();
    }

    public x_282_a G_564_y() {
        return new F_2904_S(this.R_4764_Y());
    }

    public j_956_y P_1922_E() {
        return this.J_1907_R;
    }

    public int u_1723_Y() {
        return this.R_4764_Y;
    }

    public g_422_i n_1700_B(Attribute attributeIn, String uuid, double amount, U_1880_G.n_1700_B operation) {
        U_1880_G attributemodifier = new U_1880_G(UUID.fromString(uuid), this::R_4764_Y, amount, operation);
        this.n_1700_B.put(attributeIn, attributemodifier);
        return this;
    }

    public Map<Attribute, U_1880_G> v_4262_N() {
        return this.n_1700_B;
    }

    public void n_1700_B(r_4811_B entityLivingBaseIn, AttributeMap attributeMapIn, int amplifier) {
        for (Map.Entry<Attribute, U_1880_G> entry : this.n_1700_B.entrySet()) {
            A_4388_s modifiableattributeinstance = attributeMapIn.n_1700_B(entry.getKey());
            if (modifiableattributeinstance == null) continue;
            modifiableattributeinstance.G_564_y(entry.getValue());
        }
    }

    public void J_1907_R(r_4811_B entityLivingBaseIn, AttributeMap attributeMapIn, int amplifier) {
        for (Map.Entry<Attribute, U_1880_G> entry : this.n_1700_B.entrySet()) {
            A_4388_s modifiableattributeinstance = attributeMapIn.n_1700_B(entry.getKey());
            if (modifiableattributeinstance == null) continue;
            U_1880_G attributemodifier = entry.getValue();
            modifiableattributeinstance.G_564_y(attributemodifier);
            modifiableattributeinstance.R_4764_Y(new U_1880_G(attributemodifier.n_1700_B(), this.R_4764_Y() + " " + amplifier, this.n_1700_B(amplifier, attributemodifier), attributemodifier.R_4764_Y()));
        }
    }

    public double n_1700_B(int amplifier, U_1880_G modifier) {
        return modifier.G_564_y() * (double)(amplifier + 1);
    }

    public boolean w_1484_f() {
        return this.J_1907_R == j_956_y.n_1700_B;
    }

    public g_2336_b t_148_a() {
        g_2336_b registryName = V_3137_a.T_2506_i.J_1907_R(this);
        if (registryName != null) {
            return new g_2336_b("textures/mob_effect/" + registryName.J_1907_R() + ".png");
        }
        return new g_2336_b("textures/mob_effect/unknown.png");
    }
}


