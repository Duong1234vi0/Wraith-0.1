/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;

public interface Packet<T extends Packet<T>> {
    public g_2336_b getIdentifier();

    public T fromBytes(b_2585_i var1);

    public void toBytes(b_2585_i var1);
}

