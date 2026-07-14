/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.File;
import java.io.IOException;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.r_1827_u;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SavedData {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final String J_1907_R;
    private boolean R_4764_Y;

    public SavedData(String name) {
        this.J_1907_R = name;
    }

    public abstract void n_1700_B(U_2912_j var1);

    public abstract U_2912_j R_4764_Y(U_2912_j var1);

    public void R_4764_Y() {
        this.n_1700_B(true);
    }

    public void n_1700_B(boolean isDirty) {
        this.R_4764_Y = isDirty;
    }

    public boolean G_564_y() {
        return this.R_4764_Y;
    }

    public String P_1922_E() {
        return this.J_1907_R;
    }

    public void n_1700_B(File fileIn) {
        if (this.G_564_y()) {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("data", this.R_4764_Y(new U_2912_j()));
            compoundnbt.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
            try {
                r_1827_u.n_1700_B(compoundnbt, fileIn);
            }
            catch (IOException ioexception) {
                n_1700_B.error("Could not save data {}", (Object)this, (Object)ioexception);
            }
            this.n_1700_B(false);
        }
    }
}


