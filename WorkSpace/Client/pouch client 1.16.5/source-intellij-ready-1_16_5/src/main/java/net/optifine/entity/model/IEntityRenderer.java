/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.g_2336_b;
import lightning.product.BlockEntityType;
import lightning.product.t_5_h;
import net.optifine.util.Either;

public interface IEntityRenderer {
    public Either<t_5_h, BlockEntityType> getType();

    public void setType(Either<t_5_h, BlockEntityType> var1);

    public g_2336_b getLocationTextureCustom();

    public void setLocationTextureCustom(g_2336_b var1);
}


