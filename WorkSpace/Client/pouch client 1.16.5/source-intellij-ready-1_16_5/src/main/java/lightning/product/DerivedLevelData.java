/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.A_2352_Z;
import lightning.product.I_14_v;
import lightning.product.R_2450_T;
import lightning.product.T_603_v;
import lightning.product.Z_1125_b;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelData;
import lightning.product.WorldData;
import lightning.product.CrashReportCategory;
import net.minecraft.server.G_564_y;

public class DerivedLevelData
implements ServerLevelData {
    private final WorldData n_1700_B;
    private final ServerLevelData J_1907_R;

    public DerivedLevelData(WorldData configuration, ServerLevelData delegate) {
        this.n_1700_B = configuration;
        this.J_1907_R = delegate;
    }

    @Override
    public int J_1907_R() {
        return this.J_1907_R.J_1907_R();
    }

    @Override
    public int R_4764_Y() {
        return this.J_1907_R.R_4764_Y();
    }

    @Override
    public int G_564_y() {
        return this.J_1907_R.G_564_y();
    }

    @Override
    public float w_1484_f() {
        return this.J_1907_R.w_1484_f();
    }

    @Override
    public long P_1922_E() {
        return this.J_1907_R.P_1922_E();
    }

    @Override
    public long u_1723_Y() {
        return this.J_1907_R.u_1723_Y();
    }

    @Override
    public String P_4830_p() {
        return this.n_1700_B.P_4830_p();
    }

    @Override
    public int h_1847_R() {
        return this.J_1907_R.h_1847_R();
    }

    @Override
    public void G_564_y(int time) {
    }

    @Override
    public boolean t_148_a() {
        return this.J_1907_R.t_148_a();
    }

    @Override
    public int Q_4569_t() {
        return this.J_1907_R.Q_4569_t();
    }

    @Override
    public boolean v_4262_N() {
        return this.J_1907_R.v_4262_N();
    }

    @Override
    public int M_182_A() {
        return this.J_1907_R.M_182_A();
    }

    @Override
    public I_14_v t_1786_h() {
        return this.n_1700_B.t_1786_h();
    }

    @Override
    public void n_1700_B(int x) {
    }

    @Override
    public void J_1907_R(int y) {
    }

    @Override
    public void R_4764_Y(int z) {
    }

    @Override
    public void n_1700_B(float angle) {
    }

    @Override
    public void n_1700_B(long time) {
    }

    @Override
    public void J_1907_R(long time) {
    }

    @Override
    public void n_1700_B(c_1514_x spawnPoint, float angle) {
    }

    @Override
    public void J_1907_R(boolean thunderingIn) {
    }

    @Override
    public void P_1922_E(int time) {
    }

    @Override
    public void n_1700_B(boolean isRaining) {
    }

    @Override
    public void u_1723_Y(int time) {
    }

    @Override
    public void n_1700_B(I_14_v type) {
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.n_1700_B();
    }

    @Override
    public boolean multiplayerClientSuggestionProvider() {
        return this.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public boolean w_1457_N() {
        return this.J_1907_R.w_1457_N();
    }

    @Override
    public void R_4764_Y(boolean initializedIn) {
    }

    @Override
    public A_2352_Z s_956_w() {
        return this.n_1700_B.s_956_w();
    }

    @Override
    public T_603_v.R_4764_Y Y_601_j() {
        return this.J_1907_R.Y_601_j();
    }

    @Override
    public void n_1700_B(T_603_v.R_4764_Y serializer) {
    }

    @Override
    public R_2450_T u_2550_I() {
        return this.n_1700_B.u_2550_I();
    }

    @Override
    public boolean M_588_G() {
        return this.n_1700_B.M_588_G();
    }

    @Override
    public Z_1125_b<G_564_y> Y_259_p() {
        return this.J_1907_R.Y_259_p();
    }

    @Override
    public int Q_2552_b() {
        return 0;
    }

    @Override
    public void v_4262_N(int delay) {
    }

    @Override
    public int C_2741_M() {
        return 0;
    }

    @Override
    public void w_1484_f(int chance) {
    }

    @Override
    public void n_1700_B(UUID id) {
    }

    @Override
    public void n_1700_B(CrashReportCategory category) {
        category.n_1700_B("Derived", true);
        this.J_1907_R.n_1700_B(category);
    }
}


