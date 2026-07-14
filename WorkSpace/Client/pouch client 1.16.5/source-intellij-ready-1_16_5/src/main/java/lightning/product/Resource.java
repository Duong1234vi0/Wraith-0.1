/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.Closeable;
import java.io.InputStream;
import javax.annotation.Nullable;
import lightning.product.T_335_n;
import lightning.product.g_2336_b;

public interface Resource
extends Closeable {
    public g_2336_b n_1700_B();

    public InputStream J_1907_R();

    @Nullable
    public <T> T n_1700_B(T_335_n<T> var1);

    public String R_4764_Y();
}


