/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class j_2129_E {
    private static final List<Long> n_1700_B = new ArrayList<Long>();
    private static int J_1907_R = 5;

    public static void n_1700_B() {
        long c = System.currentTimeMillis();
        n_1700_B.add(c);
        n_1700_B.removeIf(aLong -> aLong + 1000L < System.currentTimeMillis());
        J_1907_R = Math.max(n_1700_B.size(), 4);
    }

    @Generated
    public static int J_1907_R() {
        return J_1907_R;
    }
}

