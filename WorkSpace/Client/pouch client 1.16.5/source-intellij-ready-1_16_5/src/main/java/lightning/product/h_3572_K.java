/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.L_1733_J;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.h_3270_j;
import lightning.product.q_3401_q;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lombok.Generated;
import net.optifine.reflect.Reflector;

public class h_3572_K {
    private boolean n_1700_B;
    private BlockGetter J_1907_R;
    private N_4263_v R_4764_Y;
    private e_2866_D G_564_y = e_2866_D.n_1700_B;
    private final c_1514_x.n_1700_B P_1922_E = new c_1514_x.n_1700_B();
    private final M_1336_P u_1723_Y = new M_1336_P(0.0f, 0.0f, 1.0f);
    private final M_1336_P v_4262_N = new M_1336_P(0.0f, 1.0f, 0.0f);
    private final M_1336_P w_1484_f = new M_1336_P(1.0f, 0.0f, 0.0f);
    private float t_148_a;
    private float s_956_w;
    private final w_3785_E u_2550_I = new w_3785_E(0.0f, 0.0f, 0.0f, 1.0f);
    private boolean M_588_G;
    private boolean P_4830_p;
    private float h_1847_R;
    private float Q_4569_t;

    public void n_1700_B(BlockGetter worldIn, N_4263_v renderViewEntity, boolean thirdPersonIn, boolean thirdPersonReverseIn, float partialTicks) {
        this.n_1700_B = true;
        this.J_1907_R = worldIn;
        this.R_4764_Y = renderViewEntity;
        this.M_588_G = thirdPersonIn;
        this.P_4830_p = thirdPersonReverseIn;
        q_3401_q event = new q_3401_q(new e_2866_D(u_530_F.G_564_y((double)partialTicks, renderViewEntity.r_715_M, renderViewEntity.O_3598_v()), u_530_F.G_564_y((double)partialTicks, renderViewEntity.A_1038_p, renderViewEntity.X_2960_b()) + (double)u_530_F.v_4262_N(partialTicks, this.Q_4569_t, this.h_1847_R), u_530_F.G_564_y((double)partialTicks, renderViewEntity.i_1637_u, renderViewEntity.l_2647_k())), new P_3504_Q(renderViewEntity.R_4764_Y(partialTicks), renderViewEntity.J_1907_R(partialTicks)));
        A_4115_X.n_1700_B(event);
        this.n_1700_B(event.R_4764_Y().t_148_a, event.R_4764_Y().s_956_w);
        this.J_1907_R(event.J_1907_R().J_1907_R, event.J_1907_R().R_4764_Y, event.J_1907_R().G_564_y);
        if (thirdPersonIn) {
            if (thirdPersonReverseIn) {
                this.n_1700_B(this.s_956_w + 180.0f, -this.t_148_a);
            }
            this.n_1700_B(-this.n_1700_B(4.0), 0.0, 0.0);
        } else if (renderViewEntity instanceof r_4811_B && ((r_4811_B)renderViewEntity).z_2372_L()) {
            b_257_Y direction = ((r_4811_B)renderViewEntity).m_3147_m();
            this.n_1700_B(direction != null ? direction.Q_4569_t() - 180.0f : 0.0f, 0.0f);
            this.n_1700_B(0.0, 0.3, 0.0);
        }
    }

    public void n_1700_B() {
        if (this.R_4764_Y != null) {
            this.Q_4569_t = this.h_1847_R;
            this.h_1847_R += (this.R_4764_Y.X_1313_W() - this.h_1847_R) * 0.5f;
        }
    }

    private double n_1700_B(double startingDistance) {
        h_3270_j noRenderCameraClip = new h_3270_j(h_3270_j.n_1700_B.C_2741_M);
        A_4115_X.n_1700_B(noRenderCameraClip);
        if (!noRenderCameraClip.n_1700_B()) {
            for (int i = 0; i < 8; ++i) {
                double d0;
                e_2866_D vector3d1;
                BlockHitResult raytraceresult;
                float f = (i & 1) * 2 - 1;
                float f1 = (i >> 1 & 1) * 2 - 1;
                float f2 = (i >> 2 & 1) * 2 - 1;
                e_2866_D vector3d = this.G_564_y.J_1907_R(f *= 0.1f, f1 *= 0.1f, f2 *= 0.1f);
                if (((HitResult)(raytraceresult = this.J_1907_R.n_1700_B(new ClipContext(vector3d, vector3d1 = new e_2866_D(this.G_564_y.J_1907_R - (double)this.u_1723_Y.n_1700_B() * startingDistance + (double)f + (double)f2, this.G_564_y.R_4764_Y - (double)this.u_1723_Y.J_1907_R() * startingDistance + (double)f1, this.G_564_y.G_564_y - (double)this.u_1723_Y.R_4764_Y() * startingDistance + (double)f2), ClipContext.n_1700_B.G_564_y, ClipContext.J_1907_R.n_1700_B, this.R_4764_Y)))).R_4764_Y() == HitResult.n_1700_B.n_1700_B || !((d0 = raytraceresult.P_1922_E().u_1723_Y(this.G_564_y)) < startingDistance)) continue;
                startingDistance = d0;
            }
        }
        L_1733_J event = new L_1733_J(startingDistance);
        A_4115_X.n_1700_B(event);
        return event.J_1907_R();
    }

    protected void n_1700_B(double distanceOffset, double verticalOffset, double horizontalOffset) {
        double d0 = (double)this.u_1723_Y.n_1700_B() * distanceOffset + (double)this.v_4262_N.n_1700_B() * verticalOffset + (double)this.w_1484_f.n_1700_B() * horizontalOffset;
        double d1 = (double)this.u_1723_Y.J_1907_R() * distanceOffset + (double)this.v_4262_N.J_1907_R() * verticalOffset + (double)this.w_1484_f.J_1907_R() * horizontalOffset;
        double d2 = (double)this.u_1723_Y.R_4764_Y() * distanceOffset + (double)this.v_4262_N.R_4764_Y() * verticalOffset + (double)this.w_1484_f.R_4764_Y() * horizontalOffset;
        this.n_1700_B(new e_2866_D(this.G_564_y.J_1907_R + d0, this.G_564_y.R_4764_Y + d1, this.G_564_y.G_564_y + d2));
    }

    protected void n_1700_B(float pitchIn, float yawIn) {
        this.t_148_a = yawIn;
        this.s_956_w = pitchIn;
        this.u_2550_I.n_1700_B(0.0f, 0.0f, 0.0f, 1.0f);
        this.u_2550_I.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-pitchIn));
        this.u_2550_I.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(yawIn));
        this.u_1723_Y.J_1907_R(0.0f, 0.0f, 1.0f);
        this.u_1723_Y.n_1700_B(this.u_2550_I);
        this.v_4262_N.J_1907_R(0.0f, 1.0f, 0.0f);
        this.v_4262_N.n_1700_B(this.u_2550_I);
        this.w_1484_f.J_1907_R(1.0f, 0.0f, 0.0f);
        this.w_1484_f.n_1700_B(this.u_2550_I);
    }

    protected void J_1907_R(double x, double y, double z) {
        this.n_1700_B(new e_2866_D(x, y, z));
    }

    protected void n_1700_B(e_2866_D posIn) {
        this.G_564_y = posIn;
        this.P_1922_E.n_1700_B(posIn.J_1907_R, posIn.R_4764_Y, posIn.G_564_y);
    }

    public e_2866_D J_1907_R() {
        return this.G_564_y;
    }

    public c_1514_x R_4764_Y() {
        return this.P_1922_E;
    }

    public float G_564_y() {
        return this.t_148_a;
    }

    public float P_1922_E() {
        return this.s_956_w;
    }

    public w_3785_E u_1723_Y() {
        return this.u_2550_I;
    }

    public N_4263_v v_4262_N() {
        return this.R_4764_Y;
    }

    public boolean w_1484_f() {
        return this.n_1700_B;
    }

    public boolean t_148_a() {
        return this.M_588_G;
    }

    public FluidState s_956_w() {
        if (!this.n_1700_B) {
            return Fluids.n_1700_B.w_1484_f();
        }
        FluidState fluidstate = this.J_1907_R.getFluidState(this.P_1922_E);
        return !fluidstate.R_4764_Y() && this.G_564_y.R_4764_Y >= (double)((float)this.P_1922_E.getY() + fluidstate.n_1700_B(this.J_1907_R, (c_1514_x)this.P_1922_E)) ? Fluids.n_1700_B.w_1484_f() : fluidstate;
    }

    public K_4074_S u_2550_I() {
        return !this.n_1700_B ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : this.J_1907_R.getBlockState(this.P_1922_E);
    }

    public void J_1907_R(float p_setAnglesInternal_1_, float p_setAnglesInternal_2_) {
        this.s_956_w = p_setAnglesInternal_1_;
        this.t_148_a = p_setAnglesInternal_2_;
    }

    public K_4074_S M_588_G() {
        if (!this.n_1700_B) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        K_4074_S blockstate = this.J_1907_R.getBlockState(this.P_1922_E);
        if (Reflector.IForgeBlockState_getStateAtViewpoint.exists()) {
            blockstate = (K_4074_S)Reflector.call(blockstate, Reflector.IForgeBlockState_getStateAtViewpoint, this.J_1907_R, this.P_1922_E, this.G_564_y);
        }
        return blockstate;
    }

    public final M_1336_P P_4830_p() {
        return this.u_1723_Y;
    }

    public final M_1336_P h_1847_R() {
        return this.v_4262_N;
    }

    public void Q_4569_t() {
        this.J_1907_R = null;
        this.R_4764_Y = null;
        this.n_1700_B = false;
    }

    @Generated
    public boolean M_182_A() {
        return this.P_4830_p;
    }
}


