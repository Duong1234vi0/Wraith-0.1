/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import lightning.product.X_1446_C;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.z_3539_x;

public class TicketType<T> {
    private final String t_148_a;
    private final Comparator<T> s_956_w;
    private final long u_2550_I;
    public static final TicketType<X_1446_C> n_1700_B = TicketType.n_1700_B("start", (p_219486_0_, p_219486_1_) -> 0);
    public static final TicketType<X_1446_C> J_1907_R = TicketType.n_1700_B("dragon", (p_219485_0_, p_219485_1_) -> 0);
    public static final TicketType<Y_1387_d> R_4764_Y = TicketType.n_1700_B("player", Comparator.comparingLong(Y_1387_d::n_1700_B));
    public static final TicketType<Y_1387_d> G_564_y = TicketType.n_1700_B("forced", Comparator.comparingLong(Y_1387_d::n_1700_B));
    public static final TicketType<Y_1387_d> P_1922_E = TicketType.n_1700_B("light", Comparator.comparingLong(Y_1387_d::n_1700_B));
    public static final TicketType<c_1514_x> u_1723_Y = TicketType.n_1700_B("portal", z_3539_x::compareTo, 300);
    public static final TicketType<Integer> v_4262_N = TicketType.n_1700_B("post_teleport", Integer::compareTo, 5);
    public static final TicketType<Y_1387_d> w_1484_f = TicketType.n_1700_B("unknown", Comparator.comparingLong(Y_1387_d::n_1700_B), 1);

    public static <T> TicketType<T> n_1700_B(String nameIn, Comparator<T> comparator) {
        return new TicketType<T>(nameIn, comparator, 0L);
    }

    public static <T> TicketType<T> n_1700_B(String nameIn, Comparator<T> comparator, int lifespanIn) {
        return new TicketType<T>(nameIn, comparator, lifespanIn);
    }

    protected TicketType(String nameIn, Comparator<T> comparator, long lifespanIn) {
        this.t_148_a = nameIn;
        this.s_956_w = comparator;
        this.u_2550_I = lifespanIn;
    }

    public String toString() {
        return this.t_148_a;
    }

    public Comparator<T> n_1700_B() {
        return this.s_956_w;
    }

    public long J_1907_R() {
        return this.u_2550_I;
    }
}


