/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import lightning.product.P_3504_Q;
import lightning.product.e_2866_D;

public class O_726_g {
    @SerializedName(value="a")
    private final e_2866_D s_956_w;
    @SerializedName(value="b")
    private final e_2866_D u_2550_I;
    @SerializedName(value="c")
    private final e_2866_D M_588_G;
    @SerializedName(value="d")
    private final P_3504_Q P_4830_p;
    @SerializedName(value="g")
    private final e_2866_D h_1847_R;
    @SerializedName(value="h")
    private final e_2866_D Q_4569_t;
    @SerializedName(value="i")
    private final float M_182_A;
    @SerializedName(value="e")
    private final int t_1786_h;
    @SerializedName(value="f")
    private final int multiplayerClientSuggestionProvider;
    public static final String n_1700_B = "a";
    public static final String J_1907_R = "b";
    public static final String R_4764_Y = "c";
    public static final String G_564_y = "d";
    public static final String P_1922_E = "e";
    public static final String u_1723_Y = "f";
    public static final String v_4262_N = "g";
    public static final String w_1484_f = "h";
    public static final String t_148_a = "i";

    public O_726_g(e_2866_D currentVector, e_2866_D previousVector, e_2866_D targetVector, P_3504_Q velocityDelta, e_2866_D playerDiff, e_2866_D targetDiff, float distance, int hurtTime, int age) {
        this.s_956_w = currentVector;
        this.u_2550_I = previousVector;
        this.M_588_G = targetVector;
        this.P_4830_p = velocityDelta;
        this.h_1847_R = playerDiff;
        this.Q_4569_t = targetDiff;
        this.M_182_A = distance;
        this.t_1786_h = hurtTime;
        this.multiplayerClientSuggestionProvider = age;
    }

    public e_2866_D n_1700_B() {
        return this.s_956_w;
    }

    public e_2866_D J_1907_R() {
        return this.u_2550_I;
    }

    public e_2866_D R_4764_Y() {
        return this.M_588_G;
    }

    public P_3504_Q G_564_y() {
        return this.P_4830_p;
    }

    public e_2866_D P_1922_E() {
        return this.h_1847_R;
    }

    public e_2866_D u_1723_Y() {
        return this.Q_4569_t;
    }

    public float v_4262_N() {
        return this.M_182_A;
    }

    public int w_1484_f() {
        return this.t_1786_h;
    }

    public int t_148_a() {
        return this.multiplayerClientSuggestionProvider;
    }

    public float[] n_1700_B(float totalDeltaYaw, float totalDeltaPitch, float previousDeltaYaw, float previousDeltaPitch) {
        float speed = (float)(Math.sqrt(this.Q_4569_t.J_1907_R * this.Q_4569_t.J_1907_R + this.Q_4569_t.G_564_y * this.Q_4569_t.G_564_y) + Math.sqrt(this.h_1847_R.J_1907_R * this.h_1847_R.J_1907_R + this.h_1847_R.G_564_y * this.h_1847_R.G_564_y));
        return new float[]{totalDeltaYaw, totalDeltaPitch, previousDeltaYaw, previousDeltaPitch, speed, this.M_182_A};
    }

    public float[] s_956_w() {
        return new float[]{this.P_4830_p.t_148_a, this.P_4830_p.s_956_w};
    }
}


