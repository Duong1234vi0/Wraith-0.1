/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lombok.Generated;

public class a_2587_Z {
    private String n_1700_B = "MCPE";
    private String J_1907_R = "Unknown";
    private int R_4764_Y = 0;
    private String G_564_y = "Unknown";
    private int P_1922_E = 0;
    private int u_1723_Y = 0;
    private long v_4262_N = 0L;
    private String w_1484_f = "";
    private String t_148_a = "Survival";
    private String s_956_w = "";

    public String toString() {
        return String.format("%s - %s (%d/%d) [%s]", this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.t_148_a);
    }

    public String n_1700_B() {
        return String.format("\u00a7a%s \u00a77- \u00a7f%s \u00a77(\u00a7e%d\u00a77/\u00a7e%d\u00a77) \u00a78[\u00a7b%s\u00a78]", this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.t_148_a);
    }

    @Generated
    public String J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public String R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public int G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public String P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public int u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public int v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public long w_1484_f() {
        return this.v_4262_N;
    }

    @Generated
    public String t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public String s_956_w() {
        return this.t_148_a;
    }

    @Generated
    public String u_2550_I() {
        return this.s_956_w;
    }

    @Generated
    public void n_1700_B(String edition) {
        this.n_1700_B = edition;
    }

    @Generated
    public void J_1907_R(String motd) {
        this.J_1907_R = motd;
    }

    @Generated
    public void n_1700_B(int protocolVersion) {
        this.R_4764_Y = protocolVersion;
    }

    @Generated
    public void R_4764_Y(String gameVersion) {
        this.G_564_y = gameVersion;
    }

    @Generated
    public void J_1907_R(int onlinePlayers) {
        this.P_1922_E = onlinePlayers;
    }

    @Generated
    public void R_4764_Y(int maxPlayers) {
        this.u_1723_Y = maxPlayers;
    }

    @Generated
    public void n_1700_B(long serverGuid) {
        this.v_4262_N = serverGuid;
    }

    @Generated
    public void G_564_y(String worldName) {
        this.w_1484_f = worldName;
    }

    @Generated
    public void P_1922_E(String gamemode) {
        this.t_148_a = gamemode;
    }

    @Generated
    public void u_1723_Y(String rawString) {
        this.s_956_w = rawString;
    }
}

