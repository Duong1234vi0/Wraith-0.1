/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.DataFixer;
import java.io.File;
import javax.annotation.Nullable;
import lightning.product.H_4757_Q;
import lightning.product.U_2912_j;
import lightning.product.a_3913_L;
import lightning.product.b_2971_z;
import lightning.product.j_3341_s;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.r_1827_u;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Z_4308_L {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final File R_4764_Y;
    protected final DataFixer n_1700_B;

    public Z_4308_L(b_2971_z.n_1700_B levelSave, DataFixer fixer) {
        this.n_1700_B = fixer;
        this.R_4764_Y = levelSave.n_1700_B(H_4757_Q.R_4764_Y).toFile();
        this.R_4764_Y.mkdirs();
    }

    public void n_1700_B(a_3913_L player) {
        try {
            U_2912_j compoundnbt = player.P_1922_E(new U_2912_j());
            File file1 = File.createTempFile(player.F_518_D() + "-", ".dat", this.R_4764_Y);
            r_1827_u.n_1700_B(compoundnbt, file1);
            File file2 = new File(this.R_4764_Y, player.F_518_D() + ".dat");
            File file3 = new File(this.R_4764_Y, player.F_518_D() + ".dat_old");
            j_3341_s.n_1700_B(file2, file1, file3);
        }
        catch (Exception exception) {
            J_1907_R.warn("Failed to save player data for {}", (Object)player.O_1309_Q().getString());
        }
    }

    @Nullable
    public U_2912_j J_1907_R(a_3913_L player) {
        U_2912_j compoundnbt = null;
        try {
            File file1 = new File(this.R_4764_Y, player.F_518_D() + ".dat");
            if (file1.exists() && file1.isFile()) {
                compoundnbt = r_1827_u.n_1700_B(file1);
            }
        }
        catch (Exception exception) {
            J_1907_R.warn("Failed to load player data for {}", (Object)player.O_1309_Q().getString());
        }
        if (compoundnbt != null) {
            int i = compoundnbt.R_4764_Y("DataVersion", 3) ? compoundnbt.w_1484_f("DataVersion") : -1;
            player.u_1723_Y(n_3832_I.n_1700_B(this.n_1700_B, o_1967_f.J_1907_R, compoundnbt, i));
        }
        return compoundnbt;
    }

    public String[] n_1700_B() {
        String[] astring = this.R_4764_Y.list();
        if (astring == null) {
            astring = new String[]{};
        }
        for (int i = 0; i < astring.length; ++i) {
            if (!astring[i].endsWith(".dat")) continue;
            astring[i] = astring[i].substring(0, astring[i].length() - 4);
        }
        return astring;
    }
}

