/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class RemoveCategoryPacket
implements Packet<RemoveCategoryPacket> {
    public static final g_2336_b REMOVE_CATEGORY = new g_2336_b("voicechat", "remove_category");
    private String categoryId;

    public RemoveCategoryPacket() {
    }

    public RemoveCategoryPacket(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    @Override
    public g_2336_b getIdentifier() {
        return REMOVE_CATEGORY;
    }

    @Override
    public RemoveCategoryPacket fromBytes(b_2585_i buf) {
        this.categoryId = buf.P_1922_E(16);
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.categoryId, 16);
    }
}

