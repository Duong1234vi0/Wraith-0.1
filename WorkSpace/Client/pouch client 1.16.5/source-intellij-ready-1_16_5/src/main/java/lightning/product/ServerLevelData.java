/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.I_14_v;
import lightning.product.T_603_v;
import lightning.product.Z_1125_b;
import lightning.product.WritableLevelData;
import lightning.product.CrashReportCategory;
import net.minecraft.server.G_564_y;

public interface ServerLevelData
extends WritableLevelData {
    public String P_4830_p();

    public void J_1907_R(boolean var1);

    public int M_182_A();

    public void u_1723_Y(int var1);

    public void P_1922_E(int var1);

    public int Q_4569_t();

    @Override
    default public void n_1700_B(CrashReportCategory category) {
        WritableLevelData.super.n_1700_B(category);
        category.n_1700_B("Level name", this::P_4830_p);
        category.n_1700_B("Level game mode", () -> String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", this.t_1786_h().J_1907_R(), this.t_1786_h().n_1700_B(), this.n_1700_B(), this.multiplayerClientSuggestionProvider()));
        category.n_1700_B("Level weather", () -> String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", this.M_182_A(), this.v_4262_N(), this.Q_4569_t(), this.t_148_a()));
    }

    public int h_1847_R();

    public void G_564_y(int var1);

    public int Q_2552_b();

    public void v_4262_N(int var1);

    public int C_2741_M();

    public void w_1484_f(int var1);

    public void n_1700_B(UUID var1);

    public I_14_v t_1786_h();

    public void n_1700_B(T_603_v.R_4764_Y var1);

    public T_603_v.R_4764_Y Y_601_j();

    public boolean w_1457_N();

    public void R_4764_Y(boolean var1);

    public boolean multiplayerClientSuggestionProvider();

    public void n_1700_B(I_14_v var1);

    public Z_1125_b<G_564_y> Y_259_p();

    public void n_1700_B(long var1);

    public void J_1907_R(long var1);
}


