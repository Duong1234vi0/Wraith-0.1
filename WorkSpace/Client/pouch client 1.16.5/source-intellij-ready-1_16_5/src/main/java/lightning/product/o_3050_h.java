/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.x_282_a;

public abstract class o_3050_h {
    public boolean n_1700_B(@Nullable o_3050_h other) {
        if (other == null) {
            return false;
        }
        return this == other;
    }

    public abstract String n_1700_B();

    public abstract MutableComponent G_564_y(x_282_a var1);

    public abstract boolean w_1484_f();

    public abstract boolean v_4262_N();

    public abstract J_1907_R t_148_a();

    public abstract D_4024_W P_4830_p();

    public abstract Collection<String> u_1723_Y();

    public abstract J_1907_R s_956_w();

    public abstract n_1700_B u_2550_I();

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("always", 0);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("never", 1);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("hideForOtherTeams", 2);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("hideForOwnTeam", 3);
        private static final Map<String, J_1907_R> v_4262_N;
        public final String P_1922_E;
        public final int u_1723_Y;
        private static final /* synthetic */ J_1907_R[] w_1484_f;

        public static J_1907_R[] values() {
            return (J_1907_R[])w_1484_f.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        @Nullable
        public static J_1907_R n_1700_B(String nameIn) {
            return v_4262_N.get(nameIn);
        }

        private J_1907_R(String nameIn, int idIn) {
            this.P_1922_E = nameIn;
            this.u_1723_Y = idIn;
        }

        public x_282_a n_1700_B() {
            return new F_2904_S("team.visibility." + this.P_1922_E);
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            w_1484_f = lightning.product.o_3050_h$J_1907_R.J_1907_R();
            v_4262_N = Arrays.stream(lightning.product.o_3050_h$J_1907_R.values()).collect(Collectors.toMap(p_199873_0_ -> p_199873_0_.P_1922_E, p_199872_0_ -> p_199872_0_));
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("always", 0);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("never", 1);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("pushOtherTeams", 2);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("pushOwnTeam", 3);
        private static final Map<String, n_1700_B> v_4262_N;
        public final String P_1922_E;
        public final int u_1723_Y;
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        @Nullable
        public static n_1700_B n_1700_B(String nameIn) {
            return v_4262_N.get(nameIn);
        }

        private n_1700_B(String nameIn, int idIn) {
            this.P_1922_E = nameIn;
            this.u_1723_Y = idIn;
        }

        public x_282_a n_1700_B() {
            return new F_2904_S("team.collision." + this.P_1922_E);
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            w_1484_f = lightning.product.o_3050_h$n_1700_B.J_1907_R();
            v_4262_N = Arrays.stream(lightning.product.o_3050_h$n_1700_B.values()).collect(Collectors.toMap(p_199871_0_ -> p_199871_0_.P_1922_E, p_199870_0_ -> p_199870_0_));
        }
    }
}


