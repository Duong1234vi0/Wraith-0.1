/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.Objects;
import lightning.product.x_282_a;
import lombok.Generated;

public class D_3612_q {
    private final String n_1700_B;
    private final x_282_a J_1907_R;
    private final boolean R_4764_Y;

    public D_3612_q(String name, x_282_a prefix, boolean vanished) {
        this.n_1700_B = name;
        this.J_1907_R = prefix;
        this.R_4764_Y = vanished;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof D_3612_q)) {
            return false;
        }
        D_3612_q staff = (D_3612_q)o;
        return Objects.equals(this.n_1700_B, staff.n_1700_B);
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B);
    }

    @Generated
    public String n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public x_282_a J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }
}

