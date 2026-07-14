/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class i_4221_J
extends Enum<i_4221_J> {
    public static final /* enum */ i_4221_J n_1700_B = new i_4221_J("assets");
    public static final /* enum */ i_4221_J J_1907_R = new i_4221_J("data");
    private final String R_4764_Y;
    private static final /* synthetic */ i_4221_J[] G_564_y;

    public static i_4221_J[] values() {
        return (i_4221_J[])G_564_y.clone();
    }

    public static i_4221_J valueOf(String name) {
        return Enum.valueOf(i_4221_J.class, name);
    }

    private i_4221_J(String directoryNameIn) {
        this.R_4764_Y = directoryNameIn;
    }

    public String n_1700_B() {
        return this.R_4764_Y;
    }

    private static /* synthetic */ i_4221_J[] J_1907_R() {
        return new i_4221_J[]{n_1700_B, J_1907_R};
    }

    static {
        G_564_y = i_4221_J.J_1907_R();
    }
}

