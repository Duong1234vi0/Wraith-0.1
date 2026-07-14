/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 *  org.apache.commons.io.FileUtils
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lightning.product.c_1314_D;
import lightning.product.MinecraftClient;
import lightning.product.GuardedSerializer;
import org.apache.commons.io.FileUtils;

public class RealmsPersistence {
    private static final GuardedSerializer n_1700_B = new GuardedSerializer();

    public static n_1700_B n_1700_B() {
        File file1 = RealmsPersistence.J_1907_R();
        try {
            return n_1700_B.n_1700_B(FileUtils.readFileToString((File)file1, (Charset)StandardCharsets.UTF_8), n_1700_B.class);
        }
        catch (IOException ioexception) {
            return new n_1700_B();
        }
    }

    public static void n_1700_B(n_1700_B p_225187_0_) {
        File file1 = RealmsPersistence.J_1907_R();
        try {
            FileUtils.writeStringToFile((File)file1, (String)n_1700_B.n_1700_B(p_225187_0_), (Charset)StandardCharsets.UTF_8);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private static File J_1907_R() {
        return new File(MinecraftClient.A_4115_X().M_182_A, "realms_persistence.json");
    }

    public static class n_1700_B
    implements c_1314_D {
        @SerializedName(value="newsLink")
        public String n_1700_B;
        @SerializedName(value="hasUnreadNews")
        public boolean J_1907_R;
    }
}



