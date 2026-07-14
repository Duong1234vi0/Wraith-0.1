/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import lightning.product.ValueObject;
import lightning.product.c_1314_D;

public class PlayerInfo
extends ValueObject
implements c_1314_D {
    @SerializedName(value="name")
    private String n_1700_B;
    @SerializedName(value="uuid")
    private String J_1907_R;
    @SerializedName(value="operator")
    private boolean R_4764_Y;
    @SerializedName(value="accepted")
    private boolean G_564_y;
    @SerializedName(value="online")
    private boolean P_1922_E;

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(String p_230758_1_) {
        this.n_1700_B = p_230758_1_;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public void J_1907_R(String p_230761_1_) {
        this.J_1907_R = p_230761_1_;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    public void n_1700_B(boolean p_230759_1_) {
        this.R_4764_Y = p_230759_1_;
    }

    public boolean G_564_y() {
        return this.G_564_y;
    }

    public void J_1907_R(boolean p_230762_1_) {
        this.G_564_y = p_230762_1_;
    }

    public boolean P_1922_E() {
        return this.P_1922_E;
    }

    public void R_4764_Y(boolean p_230764_1_) {
        this.P_1922_E = p_230764_1_;
    }
}


