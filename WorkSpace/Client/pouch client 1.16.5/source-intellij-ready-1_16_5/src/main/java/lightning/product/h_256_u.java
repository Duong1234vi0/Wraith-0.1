/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.EntityDataSerializer;

public class h_256_u<T> {
    private final int n_1700_B;
    private final EntityDataSerializer<T> J_1907_R;

    public h_256_u(int idIn, EntityDataSerializer<T> serializerIn) {
        this.n_1700_B = idIn;
        this.J_1907_R = serializerIn;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public EntityDataSerializer<T> J_1907_R() {
        return this.J_1907_R;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            h_256_u dataparameter = (h_256_u)p_equals_1_;
            return this.n_1700_B == dataparameter.n_1700_B;
        }
        return false;
    }

    public int hashCode() {
        return this.n_1700_B;
    }

    public String toString() {
        return "<entity data: " + this.n_1700_B + ">";
    }
}


