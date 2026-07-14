/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.d_2511_z;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.SimpleParticleType;
import lightning.product.o_3050_h;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;

public abstract class C_3622_I
extends Animal {
    protected static final h_256_u<Byte> multiplayerClientSuggestionProvider = C_4114_x.n_1700_B(C_3622_I.class, EntityDataSerializers.n_1700_B);
    protected static final h_256_u<Optional<UUID>> w_1457_N = C_4114_x.n_1700_B(C_3622_I.class, EntityDataSerializers.Q_4569_t);
    private boolean h_1847_R;

    protected C_3622_I(t_5_h<? extends C_3622_I> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.o_4117_e();
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider, (byte)0);
        this.l_4537_E.n_1700_B(w_1457_N, Optional.empty());
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.y_3417_N() != null) {
            compound.n_1700_B("Owner", this.y_3417_N());
        }
        compound.n_1700_B("Sitting", this.h_1847_R);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        UUID uuid;
        super.J_1907_R(compound);
        if (compound.J_1907_R("Owner")) {
            uuid = compound.n_1700_B("Owner");
        } else {
            String s = compound.M_588_G("Owner");
            uuid = d_2511_z.n_1700_B(this.f_1574_f(), s);
        }
        if (uuid != null) {
            try {
                this.J_1907_R(uuid);
                this.Q_2552_b(true);
            }
            catch (Throwable throwable) {
                this.Q_2552_b(false);
            }
        }
        this.h_1847_R = compound.t_1786_h("Sitting");
        this.C_2741_M(this.h_1847_R);
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return !this.n_4915_F();
    }

    protected void Y_259_p(boolean play) {
        SimpleParticleType iparticledata = ParticleTypes.e_4240_b;
        if (!play) {
            iparticledata = ParticleTypes.B_1668_F;
        }
        for (int i = 0; i < 7; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            this.O_508_d.n_1700_B(iparticledata, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 7) {
            this.Y_259_p(true);
        } else if (id == 6) {
            this.Y_259_p(false);
        } else {
            super.n_1700_B(id);
        }
    }

    public boolean U_3758_B() {
        return (this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider) & 4) != 0;
    }

    public void Q_2552_b(boolean tamed) {
        byte b0 = this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider);
        if (tamed) {
            this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, (byte)(b0 | 4));
        } else {
            this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, (byte)(b0 & 0xFFFFFFFB));
        }
        this.o_4117_e();
    }

    protected void o_4117_e() {
    }

    @Override
    public boolean z_2372_L() {
        return (this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider) & 1) != 0;
    }

    public void C_2741_M(boolean p_233686_1_) {
        byte b0 = this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider);
        if (p_233686_1_) {
            this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, (byte)(b0 | 1));
        } else {
            this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, (byte)(b0 & 0xFFFFFFFE));
        }
    }

    @Nullable
    public UUID y_3417_N() {
        return this.l_4537_E.n_1700_B(w_1457_N).orElse(null);
    }

    public void J_1907_R(@Nullable UUID p_184754_1_) {
        this.l_4537_E.J_1907_R(w_1457_N, Optional.ofNullable(p_184754_1_));
    }

    public void u_1723_Y(a_3913_L player) {
        this.Q_2552_b(true);
        this.J_1907_R(player.w_2705_t());
        if (player instanceof B_4088_l) {
            U_3554_Q.k_2293_S.n_1700_B((B_4088_l)player, this);
        }
    }

    @Nullable
    public r_4811_B A_1306_N() {
        try {
            UUID uuid = this.y_3417_N();
            return uuid == null ? null : this.O_508_d.n_1700_B(uuid);
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    @Override
    public boolean n_1700_B(r_4811_B target) {
        return this.w_1484_f(target) ? false : super.n_1700_B(target);
    }

    public boolean w_1484_f(r_4811_B entityIn) {
        return entityIn == this.A_1306_N();
    }

    public boolean n_1700_B(r_4811_B target, r_4811_B owner) {
        return true;
    }

    @Override
    public o_3050_h L_1362_X() {
        r_4811_B livingentity;
        if (this.U_3758_B() && (livingentity = this.A_1306_N()) != null) {
            return livingentity.L_1362_X();
        }
        return super.L_1362_X();
    }

    @Override
    public boolean Q_4569_t(N_4263_v entityIn) {
        if (this.U_3758_B()) {
            r_4811_B livingentity = this.A_1306_N();
            if (entityIn == livingentity) {
                return true;
            }
            if (livingentity != null) {
                return livingentity.Q_4569_t(entityIn);
            }
        }
        return super.Q_4569_t(entityIn);
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        if (!this.O_508_d.Y_259_p && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.M_588_G) && this.A_1306_N() instanceof B_4088_l) {
            this.A_1306_N().n_1700_B(this.i_789_Q().J_1907_R(), j_3341_s.J_1907_R);
        }
        super.R_4764_Y(cause);
    }

    public boolean D_3612_q() {
        return this.h_1847_R;
    }

    public void k_2293_S(boolean p_233687_1_) {
        this.h_1847_R = p_233687_1_;
    }
}


