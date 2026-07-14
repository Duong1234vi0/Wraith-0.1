/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.C_4114_x;
import lightning.product.BlockStateProperties;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.Fluids;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.DirectionalPlaceContext;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_256_u;
import lightning.product.i_2154_H;
import lightning.product.ConcretePowderBlock;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2789_z;
import lightning.product.n_3832_I;
import lightning.product.BlockTags;
import lightning.product.CrashReportCategory;
import lightning.product.t_2321_d;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.FallingBlock;

public class W_4464_I
extends N_4263_v {
    private K_4074_S P_1922_E = a_3742_W.A_4115_X.multiplayerClientSuggestionProvider();
    public int n_1700_B;
    public boolean J_1907_R = true;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private int w_1484_f = 40;
    private float t_148_a = 2.0f;
    public U_2912_j R_4764_Y;
    protected static final h_256_u<c_1514_x> G_564_y = C_4114_x.n_1700_B(W_4464_I.class, EntityDataSerializers.M_588_G);

    public W_4464_I(t_5_h<? extends W_4464_I> p_i50218_1_, b_4507_u world) {
        super(p_i50218_1_, world);
    }

    public W_4464_I(b_4507_u worldIn, double x, double y, double z, K_4074_S fallingBlockState) {
        this((t_5_h<? extends W_4464_I>)t_5_h.c_3005_b, worldIn);
        this.P_1922_E = fallingBlockState;
        this.s_2632_s = true;
        this.J_1907_R(x, y + (double)((1.0f - this.v_165_F()) / 2.0f), z);
        this.v_4262_N(e_2866_D.n_1700_B);
        this.r_715_M = x;
        this.A_1038_p = y;
        this.i_1637_u = z;
        this.n_1700_B(this.b_2312_j());
    }

    @Override
    public boolean Z_735_d() {
        return false;
    }

    public void n_1700_B(c_1514_x origin) {
        this.l_4537_E.J_1907_R(G_564_y, origin);
    }

    public c_1514_x P_1922_E() {
        return this.l_4537_E.n_1700_B(G_564_y);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(G_564_y, c_1514_x.ZERO);
    }

    @Override
    public boolean C_290_v() {
        return !this.t_4219_U;
    }

    @Override
    public void v_() {
        if (this.P_1922_E.v_4262_N()) {
            this.Ops();
        } else {
            T_2915_h block = this.P_1922_E.J_1907_R();
            if (this.n_1700_B++ == 0) {
                c_1514_x blockpos = this.b_2312_j();
                if (this.O_508_d.getBlockState(blockpos).n_1700_B(block)) {
                    this.O_508_d.n_1700_B(blockpos, false);
                } else if (!this.O_508_d.Y_259_p) {
                    this.Ops();
                    return;
                }
            }
            if (!this.u_744_e()) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.04, 0.0));
            }
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            if (!this.O_508_d.Y_259_p) {
                BlockHitResult blockraytraceresult;
                c_1514_x blockpos1 = this.b_2312_j();
                boolean flag = this.P_1922_E.J_1907_R() instanceof ConcretePowderBlock;
                boolean flag1 = flag && this.O_508_d.getFluidState(blockpos1).n_1700_B(FluidTags.J_1907_R);
                double d0 = this.I_4348_c().v_4262_N();
                if (flag && d0 > 1.0 && (blockraytraceresult = this.O_508_d.n_1700_B(new ClipContext(new e_2866_D(this.r_715_M, this.A_1038_p, this.i_1637_u), this.s_4990_V(), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.J_1907_R, this))).R_4764_Y() != HitResult.n_1700_B.n_1700_B && this.O_508_d.getFluidState(blockraytraceresult.n_1700_B()).n_1700_B(FluidTags.J_1907_R)) {
                    blockpos1 = blockraytraceresult.n_1700_B();
                    flag1 = true;
                }
                if (!this.e_1992_r && !flag1) {
                    if (!(this.O_508_d.Y_259_p || (this.n_1700_B <= 100 || blockpos1.getY() >= 1 && blockpos1.getY() <= 256) && this.n_1700_B <= 600)) {
                        if (this.J_1907_R && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                            this.n_1700_B(block);
                        }
                        this.Ops();
                    }
                } else {
                    K_4074_S blockstate = this.O_508_d.getBlockState(blockpos1);
                    this.v_4262_N(this.I_4348_c().G_564_y(0.7, -0.5, 0.7));
                    if (!blockstate.n_1700_B(a_3742_W.O_2151_c)) {
                        this.Ops();
                        if (!this.u_1723_Y) {
                            boolean flag4;
                            boolean flag2 = blockstate.n_1700_B(new DirectionalPlaceContext(this.O_508_d, blockpos1, b_257_Y.n_1700_B, Z_1993_T.J_1907_R, b_257_Y.J_1907_R));
                            boolean flag3 = FallingBlock.t_148_a(this.O_508_d.getBlockState(blockpos1.down())) && (!flag || !flag1);
                            boolean bl = flag4 = this.P_1922_E.n_1700_B((T_1316_M)this.O_508_d, blockpos1) && !flag3;
                            if (flag2 && flag4) {
                                if (this.P_1922_E.J_1907_R(BlockStateProperties.A_4115_X) && this.O_508_d.getFluidState(blockpos1).n_1700_B() == Fluids.R_4764_Y) {
                                    this.P_1922_E = (K_4074_S)this.P_1922_E.n_1700_B(BlockStateProperties.A_4115_X, true);
                                }
                                if (this.O_508_d.n_1700_B(blockpos1, this.P_1922_E, 3)) {
                                    i_2154_H tileentity;
                                    if (block instanceof FallingBlock) {
                                        ((FallingBlock)block).n_1700_B(this.O_508_d, blockpos1, this.P_1922_E, blockstate, this);
                                    }
                                    if (this.R_4764_Y != null && block instanceof k_2789_z && (tileentity = this.O_508_d.getTileEntity(blockpos1)) != null) {
                                        U_2912_j compoundnbt = tileentity.n_1700_B(new U_2912_j());
                                        for (String s : this.R_4764_Y.G_564_y()) {
                                            Tag inbt = this.R_4764_Y.R_4764_Y(s);
                                            if ("x".equals(s) || "y".equals(s) || "z".equals(s)) continue;
                                            compoundnbt.n_1700_B(s, inbt.R_4764_Y());
                                        }
                                        tileentity.n_1700_B(this.P_1922_E, compoundnbt);
                                        tileentity.J_1907_R();
                                    }
                                } else if (this.J_1907_R && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                                    this.n_1700_B(block);
                                }
                            } else if (this.J_1907_R && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                                this.n_1700_B(block);
                            }
                        } else if (block instanceof FallingBlock) {
                            ((FallingBlock)block).n_1700_B(this.O_508_d, blockpos1, this);
                        }
                    }
                }
            }
            this.v_4262_N(this.I_4348_c().n_1700_B(0.98));
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        int i;
        if (this.v_4262_N && (i = u_530_F.u_1723_Y(distance - 1.0f)) > 0) {
            ArrayList list = Lists.newArrayList(this.O_508_d.n_1700_B((N_4263_v)this, this.i_601_W()));
            boolean flag = this.P_1922_E.n_1700_B(BlockTags.e_4240_b);
            P_11_z damagesource = flag ? P_11_z.t_1786_h : P_11_z.multiplayerClientSuggestionProvider;
            for (N_4263_v entity : list) {
                entity.n_1700_B(damagesource, (float)Math.min(u_530_F.G_564_y((float)i * this.t_148_a), this.w_1484_f));
            }
            if (flag && (double)this.RealmsWorldOptions.nextFloat() < (double)0.05f + (double)i * 0.05) {
                K_4074_S blockstate = t_2321_d.w_1484_f(this.P_1922_E);
                if (blockstate == null) {
                    this.u_1723_Y = true;
                } else {
                    this.P_1922_E = blockstate;
                }
            }
        }
        return false;
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("BlockState", n_3832_I.n_1700_B(this.P_1922_E));
        compound.J_1907_R("Time", this.n_1700_B);
        compound.n_1700_B("DropItem", this.J_1907_R);
        compound.n_1700_B("HurtEntities", this.v_4262_N);
        compound.n_1700_B("FallHurtAmount", this.t_148_a);
        compound.J_1907_R("FallHurtMax", this.w_1484_f);
        if (this.R_4764_Y != null) {
            compound.n_1700_B("TileEntityData", this.R_4764_Y);
        }
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        this.P_1922_E = n_3832_I.R_4764_Y(compound.M_182_A("BlockState"));
        this.n_1700_B = compound.w_1484_f("Time");
        if (compound.R_4764_Y("HurtEntities", 99)) {
            this.v_4262_N = compound.t_1786_h("HurtEntities");
            this.t_148_a = compound.s_956_w("FallHurtAmount");
            this.w_1484_f = compound.w_1484_f("FallHurtMax");
        } else if (this.P_1922_E.n_1700_B(BlockTags.e_4240_b)) {
            this.v_4262_N = true;
        }
        if (compound.R_4764_Y("DropItem", 99)) {
            this.J_1907_R = compound.t_1786_h("DropItem");
        }
        if (compound.R_4764_Y("TileEntityData", 10)) {
            this.R_4764_Y = compound.M_182_A("TileEntityData");
        }
        if (this.P_1922_E.v_4262_N()) {
            this.P_1922_E = a_3742_W.A_4115_X.multiplayerClientSuggestionProvider();
        }
    }

    public b_4507_u u_1723_Y() {
        return this.O_508_d;
    }

    public void n_1700_B(boolean hurtEntitiesIn) {
        this.v_4262_N = hurtEntitiesIn;
    }

    @Override
    public boolean O_4761_U() {
        return false;
    }

    @Override
    public void n_1700_B(CrashReportCategory category) {
        super.n_1700_B(category);
        category.n_1700_B("Immitating BlockState", this.P_1922_E.toString());
    }

    public K_4074_S v_4262_N() {
        return this.P_1922_E;
    }

    @Override
    public boolean J_303_C() {
        return true;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this, T_2915_h.s_956_w(this.v_4262_N()));
    }
}


