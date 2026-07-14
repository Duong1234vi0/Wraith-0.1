/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.C_990_G;
import lightning.product.F_1241_B;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BaseFireBlock;
import lightning.product.U_2912_j;
import lightning.product.b_2971_b;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.n_3832_I;
import lightning.product.Packet;
import lightning.product.t_5_h;

public class V_3354_l
extends N_4263_v {
    private static final h_256_u<Optional<c_1514_x>> J_1907_R = C_4114_x.n_1700_B(V_3354_l.class, EntityDataSerializers.P_4830_p);
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(V_3354_l.class, EntityDataSerializers.t_148_a);
    public int n_1700_B;

    public V_3354_l(t_5_h<? extends V_3354_l> p_i50231_1_, b_4507_u world) {
        super(p_i50231_1_, world);
        this.s_2632_s = true;
        this.n_1700_B = this.RealmsWorldOptions.nextInt(100000);
    }

    public V_3354_l(b_4507_u worldIn, double x, double y, double z) {
        this((t_5_h<? extends V_3354_l>)t_5_h.w_1457_N, worldIn);
        this.J_1907_R(x, y, z);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(J_1907_R, Optional.empty());
        this.D_60_a().n_1700_B(R_4764_Y, true);
    }

    @Override
    public void v_() {
        ++this.n_1700_B;
        if (this.O_508_d instanceof e_3591_l) {
            c_1514_x blockpos = this.b_2312_j();
            if (((e_3591_l)this.O_508_d).UploadStatus() != null && this.O_508_d.getBlockState(blockpos).v_4262_N()) {
                this.O_508_d.J_1907_R(blockpos, BaseFireBlock.n_1700_B(this.O_508_d, blockpos));
            }
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        if (this.P_1922_E() != null) {
            compound.n_1700_B("BeamTarget", n_3832_I.n_1700_B(this.P_1922_E()));
        }
        compound.n_1700_B("ShowBottom", this.u_1723_Y());
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        if (compound.R_4764_Y("BeamTarget", 10)) {
            this.n_1700_B(n_3832_I.J_1907_R(compound.M_182_A("BeamTarget")));
        }
        if (compound.R_4764_Y("ShowBottom", 1)) {
            this.n_1700_B(compound.t_1786_h("ShowBottom"));
        }
    }

    @Override
    public boolean C_290_v() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (source.u_2550_I() instanceof b_2971_b) {
            return false;
        }
        if (!this.t_4219_U && !this.O_508_d.Y_259_p) {
            this.Ops();
            if (!source.G_564_y()) {
                this.O_508_d.n_1700_B((N_4263_v)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), 6.0f, F_1241_B.n_1700_B.R_4764_Y);
            }
            this.J_1907_R(source);
        }
        return true;
    }

    @Override
    public void e_1992_r() {
        this.J_1907_R(P_11_z.h_1847_R);
        super.e_1992_r();
    }

    private void J_1907_R(P_11_z source) {
        C_990_G dragonfightmanager;
        if (this.O_508_d instanceof e_3591_l && (dragonfightmanager = ((e_3591_l)this.O_508_d).UploadStatus()) != null) {
            dragonfightmanager.n_1700_B(this, source);
        }
    }

    public void n_1700_B(@Nullable c_1514_x beamTarget) {
        this.D_60_a().J_1907_R(J_1907_R, Optional.ofNullable(beamTarget));
    }

    @Nullable
    public c_1514_x P_1922_E() {
        return this.D_60_a().n_1700_B(J_1907_R).orElse(null);
    }

    public void n_1700_B(boolean showBottom) {
        this.D_60_a().J_1907_R(R_4764_Y, showBottom);
    }

    public boolean u_1723_Y() {
        return this.D_60_a().n_1700_B(R_4764_Y);
    }

    @Override
    public boolean n_1700_B(double distance) {
        return super.n_1700_B(distance) || this.P_1922_E() != null;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}


