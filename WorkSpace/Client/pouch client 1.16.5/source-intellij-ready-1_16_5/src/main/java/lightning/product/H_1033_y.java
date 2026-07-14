/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.OptionalDynamic
 */
package lightning.product;

import com.mojang.serialization.Dynamic;
import com.mojang.serialization.OptionalDynamic;
import lightning.product.SharedConstants;

public class H_1033_y {
    private final int n_1700_B;
    private final long J_1907_R;
    private final String R_4764_Y;
    private final int G_564_y;
    private final boolean P_1922_E;

    public H_1033_y(int storageVersion, long lastPlayed, String name, int id, boolean snapshot) {
        this.n_1700_B = storageVersion;
        this.J_1907_R = lastPlayed;
        this.R_4764_Y = name;
        this.G_564_y = id;
        this.P_1922_E = snapshot;
    }

    public static H_1033_y n_1700_B(Dynamic<?> nbt) {
        int i = nbt.get("version").asInt(0);
        long j = nbt.get("LastPlayed").asLong(0L);
        OptionalDynamic optionaldynamic = nbt.get("Version");
        return optionaldynamic.result().isPresent() ? new H_1033_y(i, j, optionaldynamic.get("Name").asString(SharedConstants.n_1700_B().getName()), optionaldynamic.get("Id").asInt(SharedConstants.n_1700_B().getWorldVersion()), optionaldynamic.get("Snapshot").asBoolean(!SharedConstants.n_1700_B().isStable())) : new H_1033_y(i, j, "", 0, false);
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public long J_1907_R() {
        return this.J_1907_R;
    }

    public String R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public boolean P_1922_E() {
        return this.P_1922_E;
    }
}


