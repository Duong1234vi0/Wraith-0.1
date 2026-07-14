/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixer;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.SharedConstants;
import lightning.product.U_2912_j;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.r_1827_u;
import lightning.product.SavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_4380_l {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<String, SavedData> J_1907_R = Maps.newHashMap();
    private final DataFixer R_4764_Y;
    private final File G_564_y;

    public s_4380_l(File dataFolder, DataFixer dataFixerIn) {
        this.R_4764_Y = dataFixerIn;
        this.G_564_y = dataFolder;
    }

    private File n_1700_B(String name) {
        return new File(this.G_564_y, name + ".dat");
    }

    public <T extends SavedData> T n_1700_B(Supplier<T> defaultSupplier, String name) {
        T t = this.J_1907_R(defaultSupplier, name);
        if (t != null) {
            return t;
        }
        SavedData t1 = (SavedData)defaultSupplier.get();
        this.n_1700_B(t1);
        return (T)t1;
    }

    @Nullable
    public <T extends SavedData> T J_1907_R(Supplier<T> defaultSupplier, String name) {
        SavedData worldsaveddata = this.J_1907_R.get(name);
        if (worldsaveddata == null && !this.J_1907_R.containsKey(name)) {
            worldsaveddata = this.R_4764_Y(defaultSupplier, name);
            this.J_1907_R.put(name, worldsaveddata);
        }
        return (T)worldsaveddata;
    }

    @Nullable
    private <T extends SavedData> T R_4764_Y(Supplier<T> defaultSupplier, String name) {
        try {
            File file1 = this.n_1700_B(name);
            if (file1.exists()) {
                SavedData t = (SavedData)defaultSupplier.get();
                U_2912_j compoundnbt = this.n_1700_B(name, SharedConstants.n_1700_B().getWorldVersion());
                t.n_1700_B(compoundnbt.M_182_A("data"));
                return (T)t;
            }
        }
        catch (Exception exception) {
            n_1700_B.error("Error loading saved data: {}", (Object)name, (Object)exception);
        }
        return (T)((SavedData)null);
    }

    public void n_1700_B(SavedData data) {
        this.J_1907_R.put(data.P_1922_E(), data);
    }

    public U_2912_j n_1700_B(String name, int worldVersion) throws IOException {
        U_2912_j compoundnbt1;
        File file1 = this.n_1700_B(name);
        try (FileInputStream fileinputstream = new FileInputStream(file1);
             PushbackInputStream pushbackinputstream = new PushbackInputStream(fileinputstream, 2);){
            U_2912_j compoundnbt;
            if (this.n_1700_B(pushbackinputstream)) {
                compoundnbt = r_1827_u.n_1700_B(pushbackinputstream);
            } else {
                try (DataInputStream datainputstream = new DataInputStream(pushbackinputstream);){
                    compoundnbt = r_1827_u.n_1700_B(datainputstream);
                }
            }
            int i = compoundnbt.R_4764_Y("DataVersion", 99) ? compoundnbt.w_1484_f("DataVersion") : 1343;
            compoundnbt1 = n_3832_I.n_1700_B(this.R_4764_Y, o_1967_f.w_1484_f, compoundnbt, i, worldVersion);
        }
        return compoundnbt1;
    }

    private boolean n_1700_B(PushbackInputStream inputStream) throws IOException {
        int j;
        byte[] abyte = new byte[2];
        boolean flag = false;
        int i = inputStream.read(abyte, 0, 2);
        if (i == 2 && (j = (abyte[1] & 0xFF) << 8 | abyte[0] & 0xFF) == 35615) {
            flag = true;
        }
        if (i != 0) {
            inputStream.unread(abyte, 0, i);
        }
        return flag;
    }

    public void n_1700_B() {
        for (SavedData worldsaveddata : this.J_1907_R.values()) {
            if (worldsaveddata == null) continue;
            worldsaveddata.n_1700_B(this.n_1700_B(worldsaveddata.P_1922_E()));
        }
    }
}


