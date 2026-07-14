/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.j_3341_s;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.m_3054_I;
import lightning.product.v_174_f;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class E_389_s
extends v_174_f {
    private boolean R_4764_Y;
    private boolean G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public E_389_s(e_3591_l p_i50709_1_) {
        super(p_i50709_1_);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        ++this.u_1723_Y;
        long i = this.n_1700_B.X_933_l();
        long j = i / 24000L + 1L;
        if (!this.R_4764_Y && this.u_1723_Y > 20) {
            this.R_4764_Y = true;
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_1723_Y, 0.0f));
        }
        boolean bl = this.G_564_y = i > 120500L;
        if (this.G_564_y) {
            ++this.P_1922_E;
        }
        if (i % 24000L == 500L) {
            if (j <= 6L) {
                if (j == 6L) {
                    this.J_1907_R.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_1723_Y, 104.0f));
                } else {
                    this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("demo.day." + j), j_3341_s.J_1907_R);
                }
            }
        } else if (j == 1L) {
            if (i == 100L) {
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_1723_Y, 101.0f));
            } else if (i == 175L) {
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_1723_Y, 102.0f));
            } else if (i == 250L) {
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.u_1723_Y, 103.0f));
            }
        } else if (j == 5L && i % 24000L == 22000L) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("demo.day.warning"), j_3341_s.J_1907_R);
        }
    }

    private void u_1723_Y() {
        if (this.P_1922_E > 100) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("demo.reminder"), j_3341_s.J_1907_R);
            this.P_1922_E = 0;
        }
    }

    @Override
    public void n_1700_B(c_1514_x p_225416_1_, ServerboundPlayerActionPacket.n_1700_B p_225416_2_, b_257_Y p_225416_3_, int p_225416_4_) {
        if (this.G_564_y) {
            this.u_1723_Y();
        } else {
            super.n_1700_B(p_225416_1_, p_225416_2_, p_225416_3_, p_225416_4_);
        }
    }

    @Override
    public m_3054_I n_1700_B(B_4088_l player, b_4507_u worldIn, Z_1993_T stack, x_1688_C hand) {
        if (this.G_564_y) {
            this.u_1723_Y();
            return m_3054_I.R_4764_Y;
        }
        return super.n_1700_B(player, worldIn, stack, hand);
    }

    @Override
    public m_3054_I n_1700_B(B_4088_l playerIn, b_4507_u worldIn, Z_1993_T stackIn, x_1688_C handIn, BlockHitResult blockRaytraceResultIn) {
        if (this.G_564_y) {
            this.u_1723_Y();
            return m_3054_I.R_4764_Y;
        }
        return super.n_1700_B(playerIn, worldIn, stackIn, handIn, blockRaytraceResultIn);
    }
}


