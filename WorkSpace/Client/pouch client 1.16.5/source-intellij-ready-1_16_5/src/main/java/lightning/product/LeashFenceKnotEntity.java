/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_2973_E;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class LeashFenceKnotEntity
extends P_2973_E {
    public LeashFenceKnotEntity(t_5_h<? extends LeashFenceKnotEntity> p_i50223_1_, b_4507_u world) {
        super((t_5_h<? extends P_2973_E>)p_i50223_1_, world);
    }

    public LeashFenceKnotEntity(b_4507_u worldIn, c_1514_x hangingPositionIn) {
        super(t_5_h.q_4610_l, worldIn, hangingPositionIn);
        this.J_1907_R((double)hangingPositionIn.getX() + 0.5, (double)hangingPositionIn.getY() + 0.5, (double)hangingPositionIn.getZ() + 0.5);
        float f = 0.125f;
        float f1 = 0.1875f;
        float f2 = 0.25f;
        this.n_1700_B(new I_4817_s(this.O_3598_v() - 0.1875, this.X_2960_b() - 0.25 + 0.125, this.l_2647_k() - 0.1875, this.O_3598_v() + 0.1875, this.X_2960_b() + 0.25 + 0.125, this.l_2647_k() + 0.1875));
        this.z_1333_t = true;
    }

    @Override
    public void J_1907_R(double x, double y, double z) {
        super.J_1907_R((double)u_530_F.R_4764_Y(x) + 0.5, (double)u_530_F.R_4764_Y(y) + 0.5, (double)u_530_F.R_4764_Y(z) + 0.5);
    }

    @Override
    protected void P_1922_E() {
        this.Q_4569_t((double)this.J_1907_R.getX() + 0.5, (double)this.J_1907_R.getY() + 0.5, (double)this.J_1907_R.getZ() + 0.5);
    }

    @Override
    public void n_1700_B(b_257_Y facingDirectionIn) {
    }

    @Override
    public int v_4262_N() {
        return 9;
    }

    @Override
    public int w_1484_f() {
        return 9;
    }

    @Override
    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return -0.0625f;
    }

    @Override
    public boolean n_1700_B(double distance) {
        return distance < 1024.0;
    }

    @Override
    public void n_1700_B(@Nullable N_4263_v brokenEntity) {
        this.n_1700_B(SoundEvents.PotionTracker, 1.0f, 1.0f);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        if (this.O_508_d.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        boolean flag = false;
        double d0 = 7.0;
        List<Z_530_i> list = this.O_508_d.n_1700_B(Z_530_i.class, new I_4817_s(this.O_3598_v() - 7.0, this.X_2960_b() - 7.0, this.l_2647_k() - 7.0, this.O_3598_v() + 7.0, this.X_2960_b() + 7.0, this.l_2647_k() + 7.0));
        for (Z_530_i mobentity : list) {
            if (mobentity.y_2622_c() != player) continue;
            mobentity.J_1907_R(this, true);
            flag = true;
        }
        if (!flag) {
            this.Ops();
            if (player.C_415_h.G_564_y) {
                for (Z_530_i mobentity1 : list) {
                    if (!mobentity1.n_4915_F() || mobentity1.y_2622_c() != this) continue;
                    mobentity1.n_1700_B(true, false);
                }
            }
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public boolean u_1723_Y() {
        return this.O_508_d.getBlockState(this.J_1907_R).J_1907_R().n_1700_B(BlockTags.G_624_v);
    }

    public static LeashFenceKnotEntity n_1700_B(b_4507_u world, c_1514_x pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        for (LeashFenceKnotEntity leashknotentity : world.n_1700_B(LeashFenceKnotEntity.class, new I_4817_s((double)i - 1.0, (double)j - 1.0, (double)k - 1.0, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0))) {
            if (!leashknotentity.u_2550_I().equals(pos)) continue;
            return leashknotentity;
        }
        LeashFenceKnotEntity leashknotentity1 = new LeashFenceKnotEntity(world, pos);
        world.a_(leashknotentity1);
        leashknotentity1.t_148_a();
        return leashknotentity1;
    }

    @Override
    public void t_148_a() {
        this.n_1700_B(SoundEvents.t_2598_a, 1.0f, 1.0f);
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this, this.f_4016_n(), 0, this.u_2550_I());
    }

    @Override
    public e_2866_D P_1922_E(float partialTicks) {
        return this.P_4830_p(partialTicks).J_1907_R(0.0, 0.2, 0.0);
    }
}



