/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.B_4088_l;
import lightning.product.G_1455_B;
import lightning.product.Attributes;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.MobEffects;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;

public class ElderGuardian
extends G_1455_B {
    public static final float n_1700_B = t_5_h.multiplayerClientSuggestionProvider.t_148_a() / t_5_h.x_607_J.t_148_a();

    public ElderGuardian(t_5_h<? extends ElderGuardian> type, b_4507_u worldIn) {
        super((t_5_h<? extends G_1455_B>)type, worldIn);
        this.T_3594_S();
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(400);
        }
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return G_1455_B.y_2447_C().n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.u_1723_Y, 8.0).n_1700_B(Attributes.n_1700_B, 80.0);
    }

    @Override
    public int y_4642_Y() {
        return 60;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.S_980_j() ? SoundEvents.l_4627_h : SoundEvents.K_3372_t;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.S_980_j() ? SoundEvents.T_2971_J : SoundEvents.Q_3581_n;
    }

    @Override
    protected SoundEvent u_796_y() {
        return this.S_980_j() ? SoundEvents.m_2262_U : SoundEvents.S_4258_d;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.m_891_U;
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
        int i = 1200;
        if ((this.RealmsWorldResetDto + this.j_276_v()) % 1200 == 0) {
            g_422_i effect = MobEffects.G_564_y;
            List<B_4088_l> list = ((e_3591_l)this.O_508_d).n_1700_B((? super B_4088_l p_210138_1_) -> this.G_564_y((N_4263_v)p_210138_1_) < 2500.0 && p_210138_1_.R_4764_Y.G_564_y());
            int j = 2;
            int k = 6000;
            int l = 1200;
            for (B_4088_l serverplayerentity : list) {
                if (serverplayerentity.J_1907_R(effect) && serverplayerentity.R_4764_Y(effect).R_4764_Y() >= 2 && serverplayerentity.R_4764_Y(effect).J_1907_R() >= 1200) continue;
                serverplayerentity.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_2550_I, this.y_1700_S() ? 0.0f : 1.0f));
                serverplayerentity.n_1700_B(new k_2610_C(effect, 6000, 2));
            }
        }
        if (!this.z_3000_g()) {
            this.n_1700_B(this.b_2312_j(), 16);
        }
    }
}


