/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.d_2427_y;
import lightning.product.e_2866_D;
import lightning.product.x_607_J;
import lombok.Generated;

public class Q_3581_n
extends d_2427_y
implements x_607_J {
    private e_2866_D n_1700_B;
    private e_2866_D J_1907_R;
    private e_2866_D R_4764_Y;
    private boolean G_564_y;
    private I_4817_s P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private boolean t_148_a;
    private double s_956_w;

    public Q_3581_n(e_2866_D from, e_2866_D to, e_2866_D motion, boolean toGround, boolean isCollidedHorizontal, boolean isCollidedVertical, I_4817_s aabbFrom) {
        this.n_1700_B = from;
        this.J_1907_R = to;
        this.R_4764_Y = motion;
        this.G_564_y = toGround;
        this.w_1484_f = isCollidedHorizontal;
        this.t_148_a = isCollidedVertical;
        this.P_1922_E = aabbFrom;
    }

    @Generated
    public e_2866_D J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public e_2866_D R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public e_2866_D G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public I_4817_s u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    @Generated
    public boolean t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public boolean s_956_w() {
        return this.t_148_a;
    }

    @Generated
    public double u_2550_I() {
        return this.s_956_w;
    }

    @Generated
    public void n_1700_B(e_2866_D from) {
        this.n_1700_B = from;
    }

    @Generated
    public void J_1907_R(e_2866_D to) {
        this.J_1907_R = to;
    }

    @Generated
    public void R_4764_Y(e_2866_D motion) {
        this.R_4764_Y = motion;
    }

    @Generated
    public void J_1907_R(boolean toGround) {
        this.G_564_y = toGround;
    }

    @Generated
    public void n_1700_B(I_4817_s aabbFrom) {
        this.P_1922_E = aabbFrom;
    }

    @Generated
    public void R_4764_Y(boolean ignoreHorizontal) {
        this.u_1723_Y = ignoreHorizontal;
    }

    @Generated
    public void G_564_y(boolean ignoreVertical) {
        this.v_4262_N = ignoreVertical;
    }

    @Generated
    public void P_1922_E(boolean collidedHorizontal) {
        this.w_1484_f = collidedHorizontal;
    }

    @Generated
    public void u_1723_Y(boolean collidedVertical) {
        this.t_148_a = collidedVertical;
    }

    @Generated
    public void n_1700_B(double horizontalMove) {
        this.s_956_w = horizontalMove;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Q_3581_n)) {
            return false;
        }
        Q_3581_n other = (Q_3581_n)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.P_1922_E() != other.P_1922_E()) {
            return false;
        }
        if (this.v_4262_N() != other.v_4262_N()) {
            return false;
        }
        if (this.w_1484_f() != other.w_1484_f()) {
            return false;
        }
        if (this.t_148_a() != other.t_148_a()) {
            return false;
        }
        if (this.s_956_w() != other.s_956_w()) {
            return false;
        }
        if (Double.compare(this.u_2550_I(), other.u_2550_I()) != 0) {
            return false;
        }
        e_2866_D this$from = this.J_1907_R();
        e_2866_D other$from = other.J_1907_R();
        if (this$from == null ? other$from != null : !((Object)this$from).equals(other$from)) {
            return false;
        }
        e_2866_D this$to = this.R_4764_Y();
        e_2866_D other$to = other.R_4764_Y();
        if (this$to == null ? other$to != null : !((Object)this$to).equals(other$to)) {
            return false;
        }
        e_2866_D this$motion = this.G_564_y();
        e_2866_D other$motion = other.G_564_y();
        if (this$motion == null ? other$motion != null : !((Object)this$motion).equals(other$motion)) {
            return false;
        }
        I_4817_s this$aabbFrom = this.u_1723_Y();
        I_4817_s other$aabbFrom = other.u_1723_Y();
        return !(this$aabbFrom == null ? other$aabbFrom != null : !((Object)this$aabbFrom).equals(other$aabbFrom));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Q_3581_n;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.P_1922_E() ? 79 : 97);
        result = result * 59 + (this.v_4262_N() ? 79 : 97);
        result = result * 59 + (this.w_1484_f() ? 79 : 97);
        result = result * 59 + (this.t_148_a() ? 79 : 97);
        result = result * 59 + (this.s_956_w() ? 79 : 97);
        long $horizontalMove = Double.doubleToLongBits(this.u_2550_I());
        result = result * 59 + (int)($horizontalMove >>> 32 ^ $horizontalMove);
        e_2866_D $from = this.J_1907_R();
        result = result * 59 + ($from == null ? 43 : ((Object)$from).hashCode());
        e_2866_D $to = this.R_4764_Y();
        result = result * 59 + ($to == null ? 43 : ((Object)$to).hashCode());
        e_2866_D $motion = this.G_564_y();
        result = result * 59 + ($motion == null ? 43 : ((Object)$motion).hashCode());
        I_4817_s $aabbFrom = this.u_1723_Y();
        result = result * 59 + ($aabbFrom == null ? 43 : ((Object)$aabbFrom).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventMovement(from=" + String.valueOf(this.J_1907_R()) + ", to=" + String.valueOf(this.R_4764_Y()) + ", motion=" + String.valueOf(this.G_564_y()) + ", toGround=" + this.P_1922_E() + ", aabbFrom=" + String.valueOf(this.u_1723_Y()) + ", ignoreHorizontal=" + this.v_4262_N() + ", ignoreVertical=" + this.w_1484_f() + ", collidedHorizontal=" + this.t_148_a() + ", collidedVertical=" + this.s_956_w() + ", horizontalMove=" + this.u_2550_I() + ")";
    }

    @Generated
    public Q_3581_n(e_2866_D from, e_2866_D to, e_2866_D motion, boolean toGround, I_4817_s aabbFrom, boolean ignoreHorizontal, boolean ignoreVertical, boolean collidedHorizontal, boolean collidedVertical, double horizontalMove) {
        this.n_1700_B = from;
        this.J_1907_R = to;
        this.R_4764_Y = motion;
        this.G_564_y = toGround;
        this.P_1922_E = aabbFrom;
        this.u_1723_Y = ignoreHorizontal;
        this.v_4262_N = ignoreVertical;
        this.w_1484_f = collidedHorizontal;
        this.t_148_a = collidedVertical;
        this.s_956_w = horizontalMove;
    }
}

