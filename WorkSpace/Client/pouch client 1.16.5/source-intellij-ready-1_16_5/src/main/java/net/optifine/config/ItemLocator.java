/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import net.optifine.config.IObjectLocator;
import net.optifine.util.ItemUtils;

public class ItemLocator
implements IObjectLocator<q_1613_l> {
    @Override
    public q_1613_l getObject(g_2336_b loc) {
        return ItemUtils.getItem(loc);
    }
}

