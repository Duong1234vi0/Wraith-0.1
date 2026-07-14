/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lightning.product.b_2585_i;
import lightning.product.ParticleType;

public interface ParticleOptions {
    public ParticleType<?> G_564_y();

    public void n_1700_B(b_2585_i var1);

    public String R_4764_Y();

    @Deprecated
    public static interface n_1700_B<T extends ParticleOptions> {
        public T J_1907_R(ParticleType<T> var1, StringReader var2) throws CommandSyntaxException;

        public T J_1907_R(ParticleType<T> var1, b_2585_i var2);
    }
}


