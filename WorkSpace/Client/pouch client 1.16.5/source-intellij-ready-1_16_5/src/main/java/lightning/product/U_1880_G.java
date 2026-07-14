/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ThreadLocalRandom
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_1880_G {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final double J_1907_R;
    private final n_1700_B R_4764_Y;
    private final Supplier<String> G_564_y;
    private final UUID P_1922_E;

    public U_1880_G(String nameIn, double amountIn, n_1700_B operationIn) {
        this(u_530_F.n_1700_B((Random)ThreadLocalRandom.current()), () -> nameIn, amountIn, operationIn);
    }

    public U_1880_G(UUID uuid, String nameIn, double amountIn, n_1700_B operationIn) {
        this(uuid, () -> nameIn, amountIn, operationIn);
    }

    public U_1880_G(UUID uuid, Supplier<String> nameIn, double amountIn, n_1700_B operationIn) {
        this.P_1922_E = uuid;
        this.G_564_y = nameIn;
        this.J_1907_R = amountIn;
        this.R_4764_Y = operationIn;
    }

    public UUID n_1700_B() {
        return this.P_1922_E;
    }

    public String J_1907_R() {
        return this.G_564_y.get();
    }

    public n_1700_B R_4764_Y() {
        return this.R_4764_Y;
    }

    public double G_564_y() {
        return this.J_1907_R;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            U_1880_G attributemodifier = (U_1880_G)p_equals_1_;
            return Objects.equals(this.P_1922_E, attributemodifier.P_1922_E);
        }
        return false;
    }

    public int hashCode() {
        return this.P_1922_E.hashCode();
    }

    public String toString() {
        return "AttributeModifier{amount=" + this.J_1907_R + ", operation=" + String.valueOf((Object)this.R_4764_Y) + ", name='" + this.G_564_y.get() + "', id=" + String.valueOf(this.P_1922_E) + "}";
    }

    public U_2912_j P_1922_E() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", this.J_1907_R());
        compoundnbt.n_1700_B("Amount", this.J_1907_R);
        compoundnbt.J_1907_R("Operation", this.R_4764_Y.n_1700_B());
        compoundnbt.n_1700_B("UUID", this.P_1922_E);
        return compoundnbt;
    }

    @Nullable
    public static U_1880_G n_1700_B(U_2912_j nbt) {
        try {
            UUID uuid = nbt.n_1700_B("UUID");
            n_1700_B attributemodifier$operation = lightning.product.U_1880_G$n_1700_B.n_1700_B(nbt.w_1484_f("Operation"));
            return new U_1880_G(uuid, nbt.M_588_G("Name"), nbt.u_2550_I("Amount"), attributemodifier$operation);
        }
        catch (Exception exception) {
            n_1700_B.warn("Unable to create attribute: {}", (Object)exception.getMessage());
            return null;
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(1);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2);
        private static final n_1700_B[] G_564_y;
        private final int P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int id) {
            this.P_1922_E = id;
        }

        public int n_1700_B() {
            return this.P_1922_E;
        }

        public static n_1700_B n_1700_B(int id) {
            if (id >= 0 && id < G_564_y.length) {
                return G_564_y[id];
            }
            throw new IllegalArgumentException("No operation with value " + id);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            u_1723_Y = lightning.product.U_1880_G$n_1700_B.J_1907_R();
            G_564_y = new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }
    }
}

