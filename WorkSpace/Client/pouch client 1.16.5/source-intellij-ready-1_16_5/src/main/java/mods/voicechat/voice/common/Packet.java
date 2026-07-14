/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import lightning.product.b_2585_i;

public interface Packet<T extends Packet> {
    public T fromBytes(b_2585_i var1);

    public void toBytes(b_2585_i var1);

    default public long getTTL() {
        return 10000L;
    }
}

