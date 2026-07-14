/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.server;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.net.AddCategoryPacket;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.RemoveCategoryPacket;
import mods.voicechat.plugins.CategoryManager;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;
import mods.voicechat.voice.server.Server;
import net.minecraft.server.G_564_y;

public class ServerCategoryManager
extends CategoryManager {
    private final Server server;

    public ServerCategoryManager(Server server) {
        this.server = server;
    }

    public void onPlayerCompatibilityCheckSucceeded(B_4088_l player) {
        Voicechat.LOGGER.debug("Synchronizing {} volume categories with {}", this.categories.size(), player.O_1309_Q().getString());
        for (VolumeCategoryImpl category : this.getCategories()) {
            this.broadcastAddCategory(this.server.getServer(), category);
        }
    }

    @Override
    public void addCategory(VolumeCategoryImpl category) {
        super.addCategory(category);
        Voicechat.LOGGER.debug("Synchronizing volume category {} with all players", category.getId());
        this.broadcastAddCategory(this.server.getServer(), category);
    }

    @Override
    @Nullable
    public VolumeCategoryImpl removeCategory(String categoryId) {
        VolumeCategoryImpl volumeCategory = super.removeCategory(categoryId);
        Voicechat.LOGGER.debug("Removing volume category {} for all players", categoryId);
        this.broadcastRemoveCategory(this.server.getServer(), categoryId);
        return volumeCategory;
    }

    private void broadcastAddCategory(G_564_y server, VolumeCategoryImpl category) {
        AddCategoryPacket packet = new AddCategoryPacket(category);
        server.p_178_J().w_1457_N().forEach(p -> NetManager.sendToClient(p, packet));
    }

    private void broadcastRemoveCategory(G_564_y server, String categoryId) {
        RemoveCategoryPacket packet = new RemoveCategoryPacket(categoryId);
        server.p_178_J().w_1457_N().forEach(p -> NetManager.sendToClient(p, packet));
    }
}

