/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.DebugPackets;
import lightning.product.D_1436_R;
import lightning.product.D_3856_V;
import lightning.product.BlockGetter;
import lightning.product.Attributes;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Position;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3742_W;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.NodeEvaluator;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public abstract class PathNavigation {
    protected final Z_530_i J_1907_R;
    protected final b_4507_u R_4764_Y;
    @Nullable
    protected b_1722_e G_564_y;
    protected double P_1922_E;
    protected int u_1723_Y;
    protected int v_4262_N;
    protected e_2866_D w_1484_f = e_2866_D.n_1700_B;
    protected z_3539_x t_148_a = z_3539_x.NULL_VECTOR;
    protected long s_956_w;
    protected long u_2550_I;
    protected double M_588_G;
    protected float P_4830_p = 0.5f;
    protected boolean h_1847_R;
    protected long Q_4569_t;
    protected NodeEvaluator M_182_A;
    private c_1514_x n_1700_B;
    private int t_1786_h;
    private float multiplayerClientSuggestionProvider = 1.0f;
    private final D_3856_V w_1457_N;
    private boolean Y_601_j;

    public PathNavigation(Z_530_i entityIn, b_4507_u worldIn) {
        this.J_1907_R = entityIn;
        this.R_4764_Y = worldIn;
        int i = u_530_F.R_4764_Y(entityIn.J_1907_R(Attributes.J_1907_R) * 16.0);
        this.w_1457_N = this.n_1700_B(i);
    }

    public void u_1723_Y() {
        this.multiplayerClientSuggestionProvider = 1.0f;
    }

    public void n_1700_B(float multiplier) {
        this.multiplayerClientSuggestionProvider = multiplier;
    }

    public c_1514_x v_4262_N() {
        return this.n_1700_B;
    }

    protected abstract D_3856_V n_1700_B(int var1);

    public void n_1700_B(double speedIn) {
        this.P_1922_E = speedIn;
    }

    public boolean w_1484_f() {
        return this.h_1847_R;
    }

    public void t_148_a() {
        if (this.R_4764_Y.X_933_l() - this.Q_4569_t > 20L) {
            if (this.n_1700_B != null) {
                this.G_564_y = null;
                this.G_564_y = this.n_1700_B(this.n_1700_B, this.t_1786_h);
                this.Q_4569_t = this.R_4764_Y.X_933_l();
                this.h_1847_R = false;
            }
        } else {
            this.h_1847_R = true;
        }
    }

    @Nullable
    public final b_1722_e n_1700_B(double x, double y, double z, int distance) {
        return this.n_1700_B(new c_1514_x(x, y, z), distance);
    }

    @Nullable
    public b_1722_e n_1700_B(Stream<c_1514_x> positionStream, int distance) {
        return this.n_1700_B(positionStream.collect(Collectors.toSet()), 8, false, distance);
    }

    @Nullable
    public b_1722_e n_1700_B(Set<c_1514_x> positions, int distance) {
        return this.n_1700_B(positions, 8, false, distance);
    }

    @Nullable
    public b_1722_e n_1700_B(c_1514_x pos, int p_179680_2_) {
        return this.n_1700_B((Set<c_1514_x>)ImmutableSet.of((Object)pos), 8, false, p_179680_2_);
    }

    @Nullable
    public b_1722_e n_1700_B(N_4263_v entityIn, int p_75494_2_) {
        return this.n_1700_B((Set<c_1514_x>)ImmutableSet.of((Object)entityIn.b_2312_j()), 16, true, p_75494_2_);
    }

    @Nullable
    protected b_1722_e n_1700_B(Set<c_1514_x> positions, int regionOffset, boolean offsetUpward, int distance) {
        if (positions.isEmpty()) {
            return null;
        }
        if (this.J_1907_R.X_2960_b() < 0.0) {
            return null;
        }
        if (!this.J_1907_R()) {
            return null;
        }
        if (this.G_564_y != null && !this.G_564_y.R_4764_Y() && positions.contains(this.n_1700_B)) {
            return this.G_564_y;
        }
        this.R_4764_Y.D_4792_h().n_1700_B("pathfind");
        float f = (float)this.J_1907_R.J_1907_R(Attributes.J_1907_R);
        c_1514_x blockpos = offsetUpward ? this.J_1907_R.b_2312_j().up() : this.J_1907_R.b_2312_j();
        int i = (int)(f + (float)regionOffset);
        PathNavigationRegion region = new PathNavigationRegion(this.R_4764_Y, blockpos.add(-i, -i, -i), blockpos.add(i, i, i));
        b_1722_e path = this.w_1457_N.n_1700_B(region, this.J_1907_R, positions, f, distance, this.multiplayerClientSuggestionProvider);
        this.R_4764_Y.D_4792_h().R_4764_Y();
        if (path != null && path.P_4830_p() != null) {
            this.n_1700_B = path.P_4830_p();
            this.t_1786_h = distance;
            this.w_1457_N();
        }
        return path;
    }

    public boolean n_1700_B(double x, double y, double z, double speedIn) {
        return this.n_1700_B(this.n_1700_B(x, y, z, 1), speedIn);
    }

    public boolean n_1700_B(N_4263_v entityIn, double speedIn) {
        b_1722_e path = this.n_1700_B(entityIn, 1);
        return path != null && this.n_1700_B(path, speedIn);
    }

    public boolean n_1700_B(@Nullable b_1722_e pathentityIn, double speedIn) {
        if (pathentityIn == null) {
            this.G_564_y = null;
            return false;
        }
        if (!pathentityIn.n_1700_B(this.G_564_y)) {
            this.G_564_y = pathentityIn;
        }
        if (this.M_588_G()) {
            return false;
        }
        this.G_564_y();
        if (this.G_564_y.P_1922_E() <= 0) {
            return false;
        }
        this.P_1922_E = speedIn;
        e_2866_D vector3d = this.R_4764_Y();
        this.v_4262_N = this.u_1723_Y;
        this.w_1484_f = vector3d;
        return true;
    }

    @Nullable
    public b_1722_e s_956_w() {
        return this.G_564_y;
    }

    public void n_1700_B() {
        ++this.u_1723_Y;
        if (this.h_1847_R) {
            this.t_148_a();
        }
        if (!this.M_588_G()) {
            if (this.J_1907_R()) {
                this.u_2550_I();
            } else if (this.G_564_y != null && !this.G_564_y.R_4764_Y()) {
                e_2866_D vector3d = this.R_4764_Y();
                e_2866_D vector3d1 = this.G_564_y.n_1700_B(this.J_1907_R);
                if (vector3d.R_4764_Y > vector3d1.R_4764_Y && !this.J_1907_R.M_1641_O() && u_530_F.R_4764_Y(vector3d.J_1907_R) == u_530_F.R_4764_Y(vector3d1.J_1907_R) && u_530_F.R_4764_Y(vector3d.G_564_y) == u_530_F.R_4764_Y(vector3d1.G_564_y)) {
                    this.G_564_y.n_1700_B();
                }
            }
            DebugPackets.n_1700_B(this.R_4764_Y, this.J_1907_R, this.G_564_y, this.P_4830_p);
            if (!this.M_588_G()) {
                e_2866_D vector3d2 = this.G_564_y.n_1700_B(this.J_1907_R);
                c_1514_x blockpos = new c_1514_x(vector3d2);
                this.J_1907_R.A_4115_X().n_1700_B(vector3d2.J_1907_R, this.R_4764_Y.getBlockState(blockpos.down()).v_4262_N() ? vector3d2.R_4764_Y : Z_535_q.n_1700_B((BlockGetter)this.R_4764_Y, blockpos), vector3d2.G_564_y, this.P_1922_E);
            }
        }
    }

    protected void u_2550_I() {
        boolean flag;
        e_2866_D vector3d = this.R_4764_Y();
        this.P_4830_p = this.J_1907_R.C_415_h() > 0.75f ? this.J_1907_R.C_415_h() / 2.0f : 0.75f - this.J_1907_R.C_415_h() / 2.0f;
        c_1514_x vector3i = this.G_564_y.v_4262_N();
        double d0 = Math.abs(this.J_1907_R.O_3598_v() - ((double)vector3i.getX() + 0.5));
        double d1 = Math.abs(this.J_1907_R.X_2960_b() - (double)vector3i.getY());
        double d2 = Math.abs(this.J_1907_R.l_2647_k() - ((double)vector3i.getZ() + 0.5));
        boolean bl = flag = d0 < (double)this.P_4830_p && d2 < (double)this.P_4830_p && d1 < 1.0;
        if (flag || this.J_1907_R.J_1907_R(this.G_564_y.w_1484_f().M_588_G) && this.J_1907_R(vector3d)) {
            this.G_564_y.n_1700_B();
        }
        this.n_1700_B(vector3d);
    }

    private boolean J_1907_R(e_2866_D currentPosition) {
        e_2866_D vector3d3;
        if (this.G_564_y.u_1723_Y() + 1 >= this.G_564_y.P_1922_E()) {
            return false;
        }
        e_2866_D vector3d = e_2866_D.R_4764_Y(this.G_564_y.v_4262_N());
        if (!currentPosition.n_1700_B((Position)vector3d, 2.0)) {
            return false;
        }
        e_2866_D vector3d1 = e_2866_D.R_4764_Y(this.G_564_y.G_564_y(this.G_564_y.u_1723_Y() + 1));
        e_2866_D vector3d2 = vector3d1.G_564_y(vector3d);
        return vector3d2.J_1907_R(vector3d3 = currentPosition.G_564_y(vector3d)) > 0.0;
    }

    protected void n_1700_B(e_2866_D positionVec3) {
        if (this.u_1723_Y - this.v_4262_N > 100) {
            if (positionVec3.v_4262_N(this.w_1484_f) < 2.25) {
                this.Y_601_j = true;
                this.h_1847_R();
            } else {
                this.Y_601_j = false;
            }
            this.v_4262_N = this.u_1723_Y;
            this.w_1484_f = positionVec3;
        }
        if (this.G_564_y != null && !this.G_564_y.R_4764_Y()) {
            c_1514_x vector3i = this.G_564_y.v_4262_N();
            if (vector3i.equals(this.t_148_a)) {
                this.s_956_w += j_3341_s.J_1907_R() - this.u_2550_I;
            } else {
                this.t_148_a = vector3i;
                double d0 = positionVec3.u_1723_Y(e_2866_D.R_4764_Y(this.t_148_a));
                double d = this.M_588_G = this.J_1907_R.l_2995_s() > 0.0f ? d0 / (double)this.J_1907_R.l_2995_s() * 1000.0 : 0.0;
            }
            if (this.M_588_G > 0.0 && (double)this.s_956_w > this.M_588_G * 3.0) {
                this.P_1922_E();
            }
            this.u_2550_I = j_3341_s.J_1907_R();
        }
    }

    private void P_1922_E() {
        this.w_1457_N();
        this.h_1847_R();
    }

    private void w_1457_N() {
        this.t_148_a = z_3539_x.NULL_VECTOR;
        this.s_956_w = 0L;
        this.M_588_G = 0.0;
        this.Y_601_j = false;
    }

    public boolean M_588_G() {
        return this.G_564_y == null || this.G_564_y.R_4764_Y();
    }

    public boolean P_4830_p() {
        return !this.M_588_G();
    }

    public void h_1847_R() {
        this.G_564_y = null;
    }

    protected abstract e_2866_D R_4764_Y();

    protected abstract boolean J_1907_R();

    protected boolean Q_4569_t() {
        return this.J_1907_R.S_980_j() || this.J_1907_R.W_3464_O();
    }

    protected void G_564_y() {
        if (this.G_564_y != null) {
            for (int i = 0; i < this.G_564_y.P_1922_E(); ++i) {
                D_1436_R pathpoint = this.G_564_y.n_1700_B(i);
                D_1436_R pathpoint1 = i + 1 < this.G_564_y.P_1922_E() ? this.G_564_y.n_1700_B(i + 1) : null;
                K_4074_S blockstate = this.R_4764_Y.getBlockState(new c_1514_x(pathpoint.n_1700_B, pathpoint.J_1907_R, pathpoint.R_4764_Y));
                if (!blockstate.n_1700_B(a_3742_W.m_1621_v)) continue;
                this.G_564_y.n_1700_B(i, pathpoint.n_1700_B(pathpoint.n_1700_B, pathpoint.J_1907_R + 1, pathpoint.R_4764_Y));
                if (pathpoint1 == null || pathpoint.J_1907_R < pathpoint1.J_1907_R) continue;
                this.G_564_y.n_1700_B(i + 1, pathpoint.n_1700_B(pathpoint1.n_1700_B, pathpoint.J_1907_R + 1, pathpoint1.R_4764_Y));
            }
        }
    }

    protected abstract boolean n_1700_B(e_2866_D var1, e_2866_D var2, int var3, int var4, int var5);

    public boolean n_1700_B(c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return this.R_4764_Y.getBlockState(blockpos).t_148_a(this.R_4764_Y, blockpos);
    }

    public NodeEvaluator M_182_A() {
        return this.M_182_A;
    }

    public void R_4764_Y(boolean canSwim) {
        this.M_182_A.R_4764_Y(canSwim);
    }

    public boolean t_1786_h() {
        return this.M_182_A.P_1922_E();
    }

    public void J_1907_R(c_1514_x pos) {
        if (this.G_564_y != null && !this.G_564_y.R_4764_Y() && this.G_564_y.P_1922_E() != 0) {
            D_1436_R pathpoint = this.G_564_y.G_564_y();
            e_2866_D vector3d = new e_2866_D(((double)pathpoint.n_1700_B + this.J_1907_R.O_3598_v()) / 2.0, ((double)pathpoint.J_1907_R + this.J_1907_R.X_2960_b()) / 2.0, ((double)pathpoint.R_4764_Y + this.J_1907_R.l_2647_k()) / 2.0);
            if (pos.withinDistance(vector3d, (double)(this.G_564_y.P_1922_E() - this.G_564_y.u_1723_Y()))) {
                this.t_148_a();
            }
        }
    }

    public boolean multiplayerClientSuggestionProvider() {
        return this.Y_601_j;
    }
}


