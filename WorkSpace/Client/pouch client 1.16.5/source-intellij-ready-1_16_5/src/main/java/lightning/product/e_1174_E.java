/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class e_1174_E
extends Enum<e_1174_E> {
    public static final /* enum */ e_1174_E n_1700_B = new e_1174_E(lightning.product.e_1174_E$n_1700_B.n_1700_B, 0, 0, "mainhand");
    public static final /* enum */ e_1174_E J_1907_R = new e_1174_E(lightning.product.e_1174_E$n_1700_B.n_1700_B, 1, 5, "offhand");
    public static final /* enum */ e_1174_E R_4764_Y = new e_1174_E(lightning.product.e_1174_E$n_1700_B.J_1907_R, 0, 1, "feet");
    public static final /* enum */ e_1174_E G_564_y = new e_1174_E(lightning.product.e_1174_E$n_1700_B.J_1907_R, 1, 2, "legs");
    public static final /* enum */ e_1174_E P_1922_E = new e_1174_E(lightning.product.e_1174_E$n_1700_B.J_1907_R, 2, 3, "chest");
    public static final /* enum */ e_1174_E u_1723_Y = new e_1174_E(lightning.product.e_1174_E$n_1700_B.J_1907_R, 3, 4, "head");
    private final n_1700_B v_4262_N;
    private final int w_1484_f;
    private final int t_148_a;
    private final String s_956_w;
    private static final /* synthetic */ e_1174_E[] u_2550_I;

    public static e_1174_E[] values() {
        return (e_1174_E[])u_2550_I.clone();
    }

    public static e_1174_E valueOf(String name) {
        return Enum.valueOf(e_1174_E.class, name);
    }

    private e_1174_E(n_1700_B slotTypeIn, int indexIn, int slotIndexIn, String nameIn) {
        this.v_4262_N = slotTypeIn;
        this.w_1484_f = indexIn;
        this.t_148_a = slotIndexIn;
        this.s_956_w = nameIn;
    }

    public n_1700_B n_1700_B() {
        return this.v_4262_N;
    }

    public int J_1907_R() {
        return this.w_1484_f;
    }

    public int R_4764_Y() {
        return this.t_148_a;
    }

    public String G_564_y() {
        return this.s_956_w;
    }

    public static e_1174_E n_1700_B(String targetName) {
        for (e_1174_E equipmentslottype : e_1174_E.values()) {
            if (!equipmentslottype.G_564_y().equals(targetName)) continue;
            return equipmentslottype;
        }
        throw new IllegalArgumentException("Invalid slot '" + targetName + "'");
    }

    public static e_1174_E n_1700_B(n_1700_B slotTypeIn, int slotIndexIn) {
        for (e_1174_E equipmentslottype : e_1174_E.values()) {
            if (equipmentslottype.n_1700_B() != slotTypeIn || equipmentslottype.J_1907_R() != slotIndexIn) continue;
            return equipmentslottype;
        }
        throw new IllegalArgumentException("Invalid slot '" + String.valueOf((Object)slotTypeIn) + "': " + slotIndexIn);
    }

    private static /* synthetic */ e_1174_E[] P_1922_E() {
        return new e_1174_E[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        u_2550_I = e_1174_E.P_1922_E();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.e_1174_E$n_1700_B.n_1700_B();
        }
    }
}

