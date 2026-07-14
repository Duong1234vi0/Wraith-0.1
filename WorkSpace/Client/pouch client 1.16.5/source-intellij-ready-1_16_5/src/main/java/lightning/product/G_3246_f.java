/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.I_408_V;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public interface G_3246_f {
    public int n_1700_B();

    public void n_1700_B(int var1);

    @Nullable
    public UUID G_564_y();

    public void n_1700_B(@Nullable UUID var1);

    public void P_1922_E();

    default public void a_(U_2912_j nbt) {
        nbt.J_1907_R("AngerTime", this.n_1700_B());
        if (this.G_564_y() != null) {
            nbt.n_1700_B("AngryAt", this.G_564_y());
        }
    }

    default public void n_1700_B(e_3591_l world, U_2912_j nbt) {
        this.n_1700_B(nbt.w_1484_f("AngerTime"));
        if (!nbt.J_1907_R("AngryAt")) {
            this.n_1700_B((UUID)null);
        } else {
            UUID uuid = nbt.n_1700_B("AngryAt");
            this.n_1700_B(uuid);
            N_4263_v entity = world.J_1907_R(uuid);
            if (entity != null) {
                if (entity instanceof Z_530_i) {
                    this.J_1907_R((Z_530_i)entity);
                }
                if (entity.f_4016_n() == t_5_h.g_4106_L) {
                    this.J_1907_R((a_3913_L)entity);
                }
            }
        }
    }

    default public void n_1700_B(e_3591_l p_241359_1_, boolean p_241359_2_) {
        r_4811_B livingentity = this.t_148_a();
        UUID uuid = this.G_564_y();
        if ((livingentity == null || livingentity.Z_2812_M()) && uuid != null && p_241359_1_.J_1907_R(uuid) instanceof Z_530_i) {
            this.A_();
        } else {
            if (livingentity != null && !Objects.equals(uuid, livingentity.w_2705_t())) {
                this.n_1700_B(livingentity.w_2705_t());
                this.P_1922_E();
            }
            if (!(this.n_1700_B() <= 0 || livingentity != null && livingentity.f_4016_n() == t_5_h.g_4106_L && p_241359_2_)) {
                this.n_1700_B(this.n_1700_B() - 1);
                if (this.n_1700_B() == 0) {
                    this.A_();
                }
            }
        }
    }

    default public boolean c_(r_4811_B p_233680_1_) {
        if (!I_408_V.u_1723_Y.test(p_233680_1_)) {
            return false;
        }
        return p_233680_1_.f_4016_n() == t_5_h.g_4106_L && this.a_(p_233680_1_.O_508_d) ? true : p_233680_1_.w_2705_t().equals(this.G_564_y());
    }

    default public boolean a_(b_4507_u p_241357_1_) {
        return p_241357_1_.H_1990_U().J_1907_R(A_2352_Z.e_4240_b) && this.B_() && this.G_564_y() == null;
    }

    default public boolean B_() {
        return this.n_1700_B() > 0;
    }

    default public void n_1700_B(a_3913_L p_233681_1_) {
        if (p_233681_1_.O_508_d.H_1990_U().J_1907_R(A_2352_Z.x_607_J) && p_233681_1_.w_2705_t().equals(this.G_564_y())) {
            this.A_();
        }
    }

    default public void v_4262_N() {
        this.A_();
        this.P_1922_E();
    }

    default public void A_() {
        this.J_1907_R((r_4811_B)null);
        this.n_1700_B((UUID)null);
        this.R_4764_Y(null);
        this.n_1700_B(0);
    }

    public void J_1907_R(@Nullable r_4811_B var1);

    public void J_1907_R(@Nullable a_3913_L var1);

    public void R_4764_Y(@Nullable r_4811_B var1);

    @Nullable
    public r_4811_B t_148_a();
}

