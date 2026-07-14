/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.DataFixer;
import java.io.File;
import lightning.product.Hotbar;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.r_1827_u;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotbarManager {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final File J_1907_R;
    private final DataFixer R_4764_Y;
    private final Hotbar[] G_564_y = new Hotbar[9];
    private boolean P_1922_E;

    public HotbarManager(File dataPath, DataFixer dataFixerIn) {
        this.J_1907_R = new File(dataPath, "hotbar.nbt");
        this.R_4764_Y = dataFixerIn;
        for (int i = 0; i < 9; ++i) {
            this.G_564_y[i] = new Hotbar();
        }
    }

    private void J_1907_R() {
        try {
            U_2912_j compoundnbt = r_1827_u.J_1907_R(this.J_1907_R);
            if (compoundnbt == null) {
                return;
            }
            if (!compoundnbt.R_4764_Y("DataVersion", 99)) {
                compoundnbt.J_1907_R("DataVersion", 1343);
            }
            compoundnbt = n_3832_I.n_1700_B(this.R_4764_Y, o_1967_f.G_564_y, compoundnbt, compoundnbt.w_1484_f("DataVersion"));
            for (int i = 0; i < 9; ++i) {
                this.G_564_y[i].n_1700_B(compoundnbt.G_564_y(String.valueOf(i), 10));
            }
        }
        catch (Exception exception) {
            n_1700_B.error("Failed to load creative mode options", (Throwable)exception);
        }
    }

    public void n_1700_B() {
        try {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
            for (int i = 0; i < 9; ++i) {
                compoundnbt.n_1700_B(String.valueOf(i), this.n_1700_B(i).n_1700_B());
            }
            r_1827_u.J_1907_R(compoundnbt, this.J_1907_R);
        }
        catch (Exception exception) {
            n_1700_B.error("Failed to save creative mode options", (Throwable)exception);
        }
    }

    public Hotbar n_1700_B(int index) {
        if (!this.P_1922_E) {
            this.J_1907_R();
            this.P_1922_E = true;
        }
        return this.G_564_y[index];
    }
}


