/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicLike
 */
package lightning.product;

import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicLike;
import lightning.product.A_2352_Z;
import lightning.product.I_14_v;
import lightning.product.R_2450_T;
import lightning.product.DataPackConfig;

public final class B_4315_y {
    private final String n_1700_B;
    private final I_14_v J_1907_R;
    private final boolean R_4764_Y;
    private final R_2450_T G_564_y;
    private final boolean P_1922_E;
    private final A_2352_Z u_1723_Y;
    private final DataPackConfig v_4262_N;

    public B_4315_y(String worldName, I_14_v gameType, boolean hardcoreEnabled, R_2450_T difficulty, boolean commandsAllowed, A_2352_Z gameRules, DataPackConfig datapackCodec) {
        this.n_1700_B = worldName;
        this.J_1907_R = gameType;
        this.R_4764_Y = hardcoreEnabled;
        this.G_564_y = difficulty;
        this.P_1922_E = commandsAllowed;
        this.u_1723_Y = gameRules;
        this.v_4262_N = datapackCodec;
    }

    public static B_4315_y n_1700_B(Dynamic<?> dynamic, DataPackConfig codec) {
        I_14_v gametype = I_14_v.n_1700_B(dynamic.get("GameType").asInt(0));
        return new B_4315_y(dynamic.get("LevelName").asString(""), gametype, dynamic.get("hardcore").asBoolean(false), dynamic.get("Difficulty").asNumber().map(dimensionTypeID -> R_2450_T.n_1700_B(dimensionTypeID.byteValue())).result().orElse(R_2450_T.R_4764_Y), dynamic.get("allowCommands").asBoolean(gametype == I_14_v.R_4764_Y), new A_2352_Z((DynamicLike<?>)dynamic.get("GameRules")), codec);
    }

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public I_14_v J_1907_R() {
        return this.J_1907_R;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    public R_2450_T G_564_y() {
        return this.G_564_y;
    }

    public boolean P_1922_E() {
        return this.P_1922_E;
    }

    public A_2352_Z u_1723_Y() {
        return this.u_1723_Y;
    }

    public DataPackConfig v_4262_N() {
        return this.v_4262_N;
    }

    public B_4315_y n_1700_B(I_14_v gameType) {
        return new B_4315_y(this.n_1700_B, gameType, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N);
    }

    public B_4315_y n_1700_B(R_2450_T difficulty) {
        return new B_4315_y(this.n_1700_B, this.J_1907_R, this.R_4764_Y, difficulty, this.P_1922_E, this.u_1723_Y, this.v_4262_N);
    }

    public B_4315_y n_1700_B(DataPackConfig datapackCodec) {
        return new B_4315_y(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, datapackCodec);
    }

    public B_4315_y w_1484_f() {
        return new B_4315_y(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y.J_1907_R(), this.v_4262_N);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return this.w_1484_f();
    }
}


