/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class Y_408_h
extends Enum<Y_408_h> {
    public static final /* enum */ Y_408_h n_1700_B = new Y_408_h(0, false);
    public static final /* enum */ Y_408_h J_1907_R = new Y_408_h(1, true);
    public static final /* enum */ Y_408_h R_4764_Y = new Y_408_h(2, true);
    private final byte G_564_y;
    private final boolean P_1922_E;
    private static final /* synthetic */ Y_408_h[] u_1723_Y;

    public static Y_408_h[] values() {
        return (Y_408_h[])u_1723_Y.clone();
    }

    public static Y_408_h valueOf(String name) {
        return Enum.valueOf(Y_408_h.class, name);
    }

    private Y_408_h(byte id, boolean interrupts) {
        this.G_564_y = id;
        this.P_1922_E = interrupts;
    }

    public byte n_1700_B() {
        return this.G_564_y;
    }

    public static Y_408_h n_1700_B(byte idIn) {
        for (Y_408_h chattype : Y_408_h.values()) {
            if (idIn != chattype.G_564_y) continue;
            return chattype;
        }
        return n_1700_B;
    }

    public boolean J_1907_R() {
        return this.P_1922_E;
    }

    private static /* synthetic */ Y_408_h[] R_4764_Y() {
        return new Y_408_h[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        u_1723_Y = Y_408_h.R_4764_Y();
    }
}

