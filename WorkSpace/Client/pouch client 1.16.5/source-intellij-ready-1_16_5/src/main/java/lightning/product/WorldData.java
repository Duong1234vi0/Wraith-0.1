/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Lifecycle;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4315_y;
import lightning.product.I_14_v;
import lightning.product.R_2450_T;
import lightning.product.U_2912_j;
import lightning.product.ServerLevelData;
import lightning.product.DataPackConfig;
import lightning.product.j_419_j;
import lightning.product.CrashReportCategory;
import lightning.product.r_4097_j;

public interface WorldData {
    public DataPackConfig k_2293_S();

    public void n_1700_B(DataPackConfig var1);

    public boolean q_2307_F();

    public Set<String> Z_875_P();

    public void n_1700_B(String var1, boolean var2);

    default public void n_1700_B(CrashReportCategory category) {
        category.n_1700_B("Known server brands", () -> String.join((CharSequence)", ", this.Z_875_P()));
        category.n_1700_B("Level was modded", () -> Boolean.toString(this.q_2307_F()));
        category.n_1700_B("Level storage version", () -> {
            int i = this.Y_1740_V();
            return String.format("0x%05X - %s", i, this.t_148_a(i));
        });
    }

    default public String t_148_a(int storageVersionId) {
        switch (storageVersionId) {
            case 19132: {
                return "McRegion";
            }
            case 19133: {
                return "Anvil";
            }
        }
        return "Unknown?";
    }

    @Nullable
    public U_2912_j c_3005_b();

    public void n_1700_B(@Nullable U_2912_j var1);

    public ServerLevelData H_2857_Y();

    public B_4315_y A_4115_X();

    public U_2912_j n_1700_B(r_4097_j var1, @Nullable U_2912_j var2);

    public boolean n_1700_B();

    public int Y_1740_V();

    public String P_4830_p();

    public I_14_v t_1786_h();

    public void n_1700_B(I_14_v var1);

    public boolean multiplayerClientSuggestionProvider();

    public R_2450_T u_2550_I();

    public void n_1700_B(R_2450_T var1);

    public boolean M_588_G();

    public void G_564_y(boolean var1);

    public A_2352_Z s_956_w();

    public U_2912_j t_4043_B();

    public U_2912_j x_607_J();

    public void J_1907_R(U_2912_j var1);

    public j_419_j e_4240_b();

    public Lifecycle n_3318_d();
}


