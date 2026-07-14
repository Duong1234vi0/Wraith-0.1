/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Slot;
import lightning.product.a_408_T;
import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class M_766_z
extends d_2427_y
implements x_607_J {
    private Slot n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private a_408_T G_564_y;

    @Generated
    public Slot J_1907_R() {
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
    public void n_1700_B(Slot slotIn) {
        this.n_1700_B = slotIn;
    }

    @Generated
    public void n_1700_B(int slotId) {
        this.J_1907_R = slotId;
    }

    @Generated
    public void J_1907_R(int mouseButton) {
        this.R_4764_Y = mouseButton;
    }

    @Generated
    public void n_1700_B(a_408_T type) {
        this.G_564_y = type;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof M_766_z)) {
            return false;
        }
        M_766_z other = (M_766_z)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (this.R_4764_Y() != other.R_4764_Y()) {
            return false;
        }
        if (this.G_564_y() != other.G_564_y()) {
            return false;
        }
        Slot this$slotIn = this.J_1907_R();
        Slot other$slotIn = other.J_1907_R();
        if (this$slotIn == null ? other$slotIn != null : !this$slotIn.equals(other$slotIn)) {
            return false;
        }
        a_408_T this$type = this.P_1922_E();
        a_408_T other$type = other.P_1922_E();
        return !(this$type == null ? other$type != null : !((Object)((Object)this$type)).equals((Object)other$type));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof M_766_z;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.R_4764_Y();
        result = result * 59 + this.G_564_y();
        Slot $slotIn = this.J_1907_R();
        result = result * 59 + ($slotIn == null ? 43 : $slotIn.hashCode());
        a_408_T $type = this.P_1922_E();
        result = result * 59 + ($type == null ? 43 : ((Object)((Object)$type)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventHandleMouseClick(slotIn=" + String.valueOf(this.J_1907_R()) + ", slotId=" + this.R_4764_Y() + ", mouseButton=" + this.G_564_y() + ", type=" + String.valueOf((Object)this.P_1922_E()) + ")";
    }

    @Generated
    public M_766_z(Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        this.n_1700_B = slotIn;
        this.J_1907_R = slotId;
        this.R_4764_Y = mouseButton;
        this.G_564_y = type;
    }
}


