/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class m_1621_v
extends d_2427_y
implements x_607_J {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private a_408_T G_564_y;
    private Z_1993_T P_1922_E;
    private short u_1723_Y;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public int R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public int G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public a_408_T P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public Z_1993_T u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public short v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public void n_1700_B(int windowId) {
        this.n_1700_B = windowId;
    }

    @Generated
    public void J_1907_R(int slotId) {
        this.J_1907_R = slotId;
    }

    @Generated
    public void R_4764_Y(int mouseButton) {
        this.R_4764_Y = mouseButton;
    }

    @Generated
    public void n_1700_B(a_408_T type) {
        this.G_564_y = type;
    }

    @Generated
    public void n_1700_B(Z_1993_T clickedItemIn) {
        this.P_1922_E = clickedItemIn;
    }

    @Generated
    public void n_1700_B(short actionNumberIn) {
        this.u_1723_Y = actionNumberIn;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof m_1621_v)) {
            return false;
        }
        m_1621_v other = (m_1621_v)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        if (this.R_4764_Y() != other.R_4764_Y()) {
            return false;
        }
        if (this.G_564_y() != other.G_564_y()) {
            return false;
        }
        if (this.v_4262_N() != other.v_4262_N()) {
            return false;
        }
        a_408_T this$type = this.P_1922_E();
        a_408_T other$type = other.P_1922_E();
        if (this$type == null ? other$type != null : !((Object)((Object)this$type)).equals((Object)other$type)) {
            return false;
        }
        Z_1993_T this$clickedItemIn = this.u_1723_Y();
        Z_1993_T other$clickedItemIn = other.u_1723_Y();
        return !(this$clickedItemIn == null ? other$clickedItemIn != null : !this$clickedItemIn.equals(other$clickedItemIn));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof m_1621_v;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        result = result * 59 + this.R_4764_Y();
        result = result * 59 + this.G_564_y();
        result = result * 59 + this.v_4262_N();
        a_408_T $type = this.P_1922_E();
        result = result * 59 + ($type == null ? 43 : ((Object)((Object)$type)).hashCode());
        Z_1993_T $clickedItemIn = this.u_1723_Y();
        result = result * 59 + ($clickedItemIn == null ? 43 : $clickedItemIn.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventWindowClick(windowId=" + this.J_1907_R() + ", slotId=" + this.R_4764_Y() + ", mouseButton=" + this.G_564_y() + ", type=" + String.valueOf((Object)this.P_1922_E()) + ", clickedItemIn=" + String.valueOf(this.u_1723_Y()) + ", actionNumberIn=" + this.v_4262_N() + ")";
    }

    @Generated
    public m_1621_v(int windowId, int slotId, int mouseButton, a_408_T type, Z_1993_T clickedItemIn, short actionNumberIn) {
        this.n_1700_B = windowId;
        this.J_1907_R = slotId;
        this.R_4764_Y = mouseButton;
        this.G_564_y = type;
        this.P_1922_E = clickedItemIn;
        this.u_1723_Y = actionNumberIn;
    }
}

