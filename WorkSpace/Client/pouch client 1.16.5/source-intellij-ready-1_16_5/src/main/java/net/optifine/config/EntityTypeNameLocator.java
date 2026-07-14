/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.N_4263_v;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import net.optifine.config.IObjectLocator;
import net.optifine.util.EntityTypeUtils;

public class EntityTypeNameLocator
implements IObjectLocator<String> {
    @Override
    public String getObject(g_2336_b loc) {
        t_5_h entitytype = EntityTypeUtils.getEntityType(loc);
        return entitytype == null ? null : entitytype.u_1723_Y();
    }

    public static String getEntityTypeName(N_4263_v entity) {
        return entity.f_4016_n().u_1723_Y();
    }
}

