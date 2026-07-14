/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;

public interface Clearable {
    public void C_2741_M();

    public static void n_1700_B(@Nullable Object object) {
        if (object instanceof Clearable) {
            ((Clearable)object).C_2741_M();
        }
    }
}


