/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;

public class AddCategoryPacket
implements Packet<AddCategoryPacket> {
    public static final g_2336_b ADD_CATEGORY = new g_2336_b("voicechat", "add_category");
    private VolumeCategoryImpl category;

    public AddCategoryPacket() {
    }

    public AddCategoryPacket(VolumeCategoryImpl category) {
        this.category = category;
    }

    public VolumeCategoryImpl getCategory() {
        return this.category;
    }

    @Override
    public g_2336_b getIdentifier() {
        return ADD_CATEGORY;
    }

    @Override
    public AddCategoryPacket fromBytes(b_2585_i buf) {
        this.category = VolumeCategoryImpl.fromBytes(buf);
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        this.category.toBytes(buf);
    }
}

