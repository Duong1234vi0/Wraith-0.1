/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Objects;
import lightning.product.B_4088_l;
import lightning.product.ClientboundBlockBreakAckPacket;
import lightning.product.BlockHitResult;
import lightning.product.I_14_v;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.StructureBlock;
import lightning.product.U_3554_Q;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.d_338_B;
import lightning.product.e_3591_l;
import lightning.product.CommandBlock;
import lightning.product.i_2154_H;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.JigsawBlock;
import lightning.product.m_3054_I;
import lightning.product.LevelAccessor;
import lightning.product.t_3286_u;
import lightning.product.x_1688_C;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class v_174_f {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public e_3591_l n_1700_B;
    public B_4088_l J_1907_R;
    private I_14_v G_564_y = I_14_v.n_1700_B;
    private I_14_v P_1922_E = I_14_v.n_1700_B;
    private boolean u_1723_Y;
    private int v_4262_N;
    private c_1514_x w_1484_f = c_1514_x.ZERO;
    private int t_148_a;
    private boolean s_956_w;
    private c_1514_x u_2550_I = c_1514_x.ZERO;
    private int M_588_G;
    private int P_4830_p = -1;

    public v_174_f(e_3591_l p_i50702_1_) {
        this.n_1700_B = p_i50702_1_;
    }

    public void n_1700_B(I_14_v type) {
        this.n_1700_B(type, type != this.G_564_y ? this.G_564_y : this.P_1922_E);
    }

    public void n_1700_B(I_14_v p_241820_1_, I_14_v p_241820_2_) {
        this.P_1922_E = p_241820_2_;
        this.G_564_y = p_241820_1_;
        p_241820_1_.n_1700_B(this.J_1907_R.C_415_h);
        this.J_1907_R.v_4262_N();
        this.J_1907_R.J_1907_R.p_178_J().n_1700_B(new d_338_B(d_338_B.n_1700_B.J_1907_R, this.J_1907_R));
        this.n_1700_B.u_1723_Y();
    }

    public I_14_v J_1907_R() {
        return this.G_564_y;
    }

    public I_14_v R_4764_Y() {
        return this.P_1922_E;
    }

    public boolean G_564_y() {
        return this.G_564_y.u_1723_Y();
    }

    public boolean P_1922_E() {
        return this.G_564_y.P_1922_E();
    }

    public void J_1907_R(I_14_v type) {
        if (this.G_564_y == I_14_v.n_1700_B) {
            this.G_564_y = type;
        }
        this.n_1700_B(this.G_564_y);
    }

    public void n_1700_B() {
        ++this.t_148_a;
        if (this.s_956_w) {
            K_4074_S blockstate = this.n_1700_B.getBlockState(this.u_2550_I);
            if (blockstate.v_4262_N()) {
                this.s_956_w = false;
            } else {
                float f = this.n_1700_B(blockstate, this.u_2550_I, this.M_588_G);
                if (f >= 1.0f) {
                    this.s_956_w = false;
                    this.n_1700_B(this.u_2550_I);
                }
            }
        } else if (this.u_1723_Y) {
            K_4074_S blockstate1 = this.n_1700_B.getBlockState(this.w_1484_f);
            if (blockstate1.v_4262_N()) {
                this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), this.w_1484_f, -1);
                this.P_4830_p = -1;
                this.u_1723_Y = false;
            } else {
                this.n_1700_B(blockstate1, this.w_1484_f, this.v_4262_N);
            }
        }
    }

    private float n_1700_B(K_4074_S p_229859_1_, c_1514_x p_229859_2_, int p_229859_3_) {
        int i = this.t_148_a - p_229859_3_;
        float f = p_229859_1_.n_1700_B(this.J_1907_R, this.J_1907_R.O_508_d, p_229859_2_) * (float)(i + 1);
        int j = (int)(f * 10.0f);
        if (j != this.P_4830_p) {
            this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), p_229859_2_, j);
            this.P_4830_p = j;
        }
        return f;
    }

    public void n_1700_B(c_1514_x p_225416_1_, ServerboundPlayerActionPacket.n_1700_B p_225416_2_, b_257_Y p_225416_3_, int p_225416_4_) {
        double d2;
        double d1;
        double d0 = this.J_1907_R.O_3598_v() - ((double)p_225416_1_.getX() + 0.5);
        double d3 = d0 * d0 + (d1 = this.J_1907_R.X_2960_b() - ((double)p_225416_1_.getY() + 0.5) + 1.5) * d1 + (d2 = this.J_1907_R.l_2647_k() - ((double)p_225416_1_.getZ() + 0.5)) * d2;
        if (d3 > 36.0) {
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, false, "too far"));
        } else if (p_225416_1_.getY() >= p_225416_4_) {
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, false, "too high"));
        } else if (p_225416_2_ == ServerboundPlayerActionPacket.n_1700_B.n_1700_B) {
            if (!this.n_1700_B.n_1700_B(this.J_1907_R, p_225416_1_)) {
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, false, "may not interact"));
                return;
            }
            if (this.P_1922_E()) {
                this.n_1700_B(p_225416_1_, p_225416_2_, "creative destroy");
                return;
            }
            if (this.J_1907_R.n_1700_B((b_4507_u)this.n_1700_B, p_225416_1_, this.G_564_y)) {
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, false, "block action restricted"));
                return;
            }
            this.v_4262_N = this.t_148_a;
            float f = 1.0f;
            K_4074_S blockstate = this.n_1700_B.getBlockState(p_225416_1_);
            if (!blockstate.v_4262_N()) {
                blockstate.n_1700_B((b_4507_u)this.n_1700_B, p_225416_1_, this.J_1907_R);
                f = blockstate.n_1700_B(this.J_1907_R, this.J_1907_R.O_508_d, p_225416_1_);
            }
            if (!blockstate.v_4262_N() && f >= 1.0f) {
                this.n_1700_B(p_225416_1_, p_225416_2_, "insta mine");
            } else {
                if (this.u_1723_Y) {
                    this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(this.w_1484_f, this.n_1700_B.getBlockState(this.w_1484_f), ServerboundPlayerActionPacket.n_1700_B.n_1700_B, false, "abort destroying since another started (client insta mine, server disagreed)"));
                }
                this.u_1723_Y = true;
                this.w_1484_f = p_225416_1_.toImmutable();
                int i = (int)(f * 10.0f);
                this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), p_225416_1_, i);
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, true, "actual start of destroying"));
                this.P_4830_p = i;
            }
        } else if (p_225416_2_ == ServerboundPlayerActionPacket.n_1700_B.R_4764_Y) {
            if (p_225416_1_.equals(this.w_1484_f)) {
                int j = this.t_148_a - this.v_4262_N;
                K_4074_S blockstate1 = this.n_1700_B.getBlockState(p_225416_1_);
                if (!blockstate1.v_4262_N()) {
                    float f1 = blockstate1.n_1700_B(this.J_1907_R, this.J_1907_R.O_508_d, p_225416_1_) * (float)(j + 1);
                    if (f1 >= 0.7f) {
                        this.u_1723_Y = false;
                        this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), p_225416_1_, -1);
                        this.n_1700_B(p_225416_1_, p_225416_2_, "destroyed");
                        return;
                    }
                    if (!this.s_956_w) {
                        this.u_1723_Y = false;
                        this.s_956_w = true;
                        this.u_2550_I = p_225416_1_;
                        this.M_588_G = this.v_4262_N;
                    }
                }
            }
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, true, "stopped destroying"));
        } else if (p_225416_2_ == ServerboundPlayerActionPacket.n_1700_B.J_1907_R) {
            this.u_1723_Y = false;
            if (!Objects.equals(this.w_1484_f, p_225416_1_)) {
                R_4764_Y.warn("Mismatch in destroy block pos: " + String.valueOf(this.w_1484_f) + " " + String.valueOf(p_225416_1_));
                this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), this.w_1484_f, -1);
                this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(this.w_1484_f, this.n_1700_B.getBlockState(this.w_1484_f), p_225416_2_, true, "aborted mismatched destroying"));
            }
            this.n_1700_B.n_1700_B(this.J_1907_R.j_276_v(), p_225416_1_, -1);
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_225416_1_, this.n_1700_B.getBlockState(p_225416_1_), p_225416_2_, true, "aborted destroying"));
        }
    }

    public void n_1700_B(c_1514_x p_229860_1_, ServerboundPlayerActionPacket.n_1700_B p_229860_2_, String p_229860_3_) {
        if (this.n_1700_B(p_229860_1_)) {
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_229860_1_, this.n_1700_B.getBlockState(p_229860_1_), p_229860_2_, true, p_229860_3_));
        } else {
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockBreakAckPacket(p_229860_1_, this.n_1700_B.getBlockState(p_229860_1_), p_229860_2_, false, p_229860_3_));
        }
    }

    public boolean n_1700_B(c_1514_x pos) {
        K_4074_S blockstate = this.n_1700_B.getBlockState(pos);
        if (!this.J_1907_R.A_2714_y().J_1907_R().n_1700_B(blockstate, (b_4507_u)this.n_1700_B, pos, this.J_1907_R)) {
            return false;
        }
        i_2154_H tileentity = this.n_1700_B.getTileEntity(pos);
        T_2915_h block = blockstate.J_1907_R();
        if ((block instanceof CommandBlock || block instanceof StructureBlock || block instanceof JigsawBlock) && !this.J_1907_R.ModuleManager()) {
            this.n_1700_B.n_1700_B(pos, blockstate, blockstate, 3);
            return false;
        }
        if (this.J_1907_R.n_1700_B((b_4507_u)this.n_1700_B, pos, this.G_564_y)) {
            return false;
        }
        block.n_1700_B((b_4507_u)this.n_1700_B, pos, blockstate, (a_3913_L)this.J_1907_R);
        boolean flag = this.n_1700_B.n_1700_B(pos, false);
        if (flag) {
            block.n_1700_B((LevelAccessor)this.n_1700_B, pos, blockstate);
        }
        if (this.P_1922_E()) {
            return true;
        }
        Z_1993_T itemstack = this.J_1907_R.A_2714_y();
        Z_1993_T itemstack1 = itemstack.t_148_a();
        boolean flag1 = this.J_1907_R.G_564_y(blockstate);
        itemstack.n_1700_B((b_4507_u)this.n_1700_B, blockstate, pos, this.J_1907_R);
        if (flag && flag1) {
            block.n_1700_B(this.n_1700_B, this.J_1907_R, pos, blockstate, tileentity, itemstack1);
        }
        return true;
    }

    public m_3054_I n_1700_B(B_4088_l player, b_4507_u worldIn, Z_1993_T stack, x_1688_C hand) {
        if (this.G_564_y == I_14_v.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        if (player.p_1458_L().n_1700_B(stack.J_1907_R())) {
            return m_3054_I.R_4764_Y;
        }
        int i = stack.t_4043_B();
        int j = stack.v_4262_N();
        InteractionResultHolder<Z_1993_T> actionresult = stack.n_1700_B(worldIn, (a_3913_L)player, hand);
        Z_1993_T itemstack = actionresult.J_1907_R();
        if (itemstack == stack && itemstack.t_4043_B() == i && itemstack.u_2550_I() <= 0 && itemstack.v_4262_N() == j) {
            return actionresult.n_1700_B();
        }
        if (actionresult.n_1700_B() == m_3054_I.G_564_y && itemstack.u_2550_I() > 0 && !player.Y_601_j()) {
            return actionresult.n_1700_B();
        }
        player.n_1700_B(hand, itemstack);
        if (this.P_1922_E()) {
            itemstack.P_1922_E(i);
            if (itemstack.P_1922_E() && itemstack.v_4262_N() != j) {
                itemstack.J_1907_R(j);
            }
        }
        if (itemstack.n_1700_B()) {
            player.n_1700_B(hand, Z_1993_T.J_1907_R);
        }
        if (!player.Y_601_j()) {
            player.n_1700_B(player.o_1800_r);
        }
        return actionresult.n_1700_B();
    }

    public m_3054_I n_1700_B(B_4088_l playerIn, b_4507_u worldIn, Z_1993_T stackIn, x_1688_C handIn, BlockHitResult blockRaytraceResultIn) {
        m_3054_I actionresulttype;
        c_1514_x blockpos = blockRaytraceResultIn.n_1700_B();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (this.G_564_y == I_14_v.P_1922_E) {
            t_3286_u inamedcontainerprovider = blockstate.J_1907_R(worldIn, blockpos);
            if (inamedcontainerprovider != null) {
                playerIn.n_1700_B(inamedcontainerprovider);
                return m_3054_I.n_1700_B;
            }
            return m_3054_I.R_4764_Y;
        }
        boolean flag = !playerIn.A_2714_y().n_1700_B() || !playerIn.S_4035_N().n_1700_B();
        boolean flag1 = playerIn.z_3000_g() && flag;
        Z_1993_T itemstack = stackIn.t_148_a();
        if (!flag1 && (actionresulttype = blockstate.n_1700_B(worldIn, playerIn, handIn, blockRaytraceResultIn)).n_1700_B()) {
            U_3554_Q.G_624_v.n_1700_B(playerIn, blockpos, itemstack);
            return actionresulttype;
        }
        if (!stackIn.n_1700_B() && !playerIn.p_1458_L().n_1700_B(stackIn.J_1907_R())) {
            m_3054_I actionresulttype1;
            UseOnContext itemusecontext = new UseOnContext(playerIn, handIn, blockRaytraceResultIn);
            if (this.P_1922_E()) {
                int i = stackIn.t_4043_B();
                actionresulttype1 = stackIn.n_1700_B(itemusecontext);
                stackIn.P_1922_E(i);
            } else {
                actionresulttype1 = stackIn.n_1700_B(itemusecontext);
            }
            if (actionresulttype1.n_1700_B()) {
                U_3554_Q.G_624_v.n_1700_B(playerIn, blockpos, itemstack);
            }
            return actionresulttype1;
        }
        return m_3054_I.R_4764_Y;
    }

    public void n_1700_B(e_3591_l serverWorld) {
        this.n_1700_B = serverWorld;
    }
}



