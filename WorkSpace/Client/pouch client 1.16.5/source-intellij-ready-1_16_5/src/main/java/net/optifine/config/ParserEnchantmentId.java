/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.K_1310_v;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import net.optifine.config.IParserInt;
import net.optifine.util.EnchantmentUtils;

public class ParserEnchantmentId
implements IParserInt {
    @Override
    public int parse(String str, int defVal) {
        g_2336_b resourcelocation = new g_2336_b(str);
        K_1310_v enchantment = EnchantmentUtils.getEnchantment(resourcelocation);
        return enchantment == null ? defVal : V_3137_a.z_4693_k.n_1700_B(enchantment);
    }
}

