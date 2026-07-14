/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lightning.product.T_1114_L;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import mods.voicechat.Voicechat;
import mods.voicechat.gui.volume.AdjustVolumeList;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.plugins.CategoryManager;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;

public class ClientCategoryManager
extends CategoryManager {
    protected final Map<String, g_2336_b> images = new ConcurrentHashMap<String, g_2336_b>();

    public ClientCategoryManager() {
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().addCategoryChannel, (client, handler, packet) -> {
            this.addCategory(packet.getCategory());
            Voicechat.LOGGER.debug("Added category {}", packet.getCategory().getId());
        });
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().removeCategoryChannel, (client, handler, packet) -> {
            this.removeCategory(packet.getCategoryId());
            Voicechat.LOGGER.debug("Removed category {}", packet.getCategoryId());
        });
        ClientCompatibilityManager.INSTANCE.onDisconnect(this::clear);
    }

    @Override
    public void addCategory(VolumeCategoryImpl category) {
        super.addCategory(category);
        if (category.getIcon() != null) {
            this.registerImage(category.getId(), this.fromIntArray(category.getIcon()));
        }
        AdjustVolumeList.update();
    }

    @Override
    @Nullable
    public VolumeCategoryImpl removeCategory(String categoryId) {
        VolumeCategoryImpl volumeCategory = super.removeCategory(categoryId);
        this.unRegisterImage(categoryId);
        AdjustVolumeList.update();
        return volumeCategory;
    }

    public void clear() {
        this.categories.keySet().forEach(this::unRegisterImage);
        this.categories.clear();
    }

    private void registerImage(String id, i_2518_W image) {
        g_2336_b resourceLocation = MinecraftClient.A_4115_X().O_508_d().n_1700_B.n_1700_B(id, new T_1114_L(image));
        this.images.put(id, resourceLocation);
    }

    private void unRegisterImage(String id) {
        g_2336_b resourceLocation = this.images.get(id);
        if (resourceLocation != null) {
            MinecraftClient.A_4115_X().O_508_d().n_1700_B.R_4764_Y(resourceLocation);
            this.images.remove(id);
        }
    }

    private i_2518_W fromIntArray(int[][] icon) {
        if (icon.length != 16) {
            throw new IllegalStateException("Icon is not 16x16");
        }
        i_2518_W nativeImage = new i_2518_W(16, 16, true);
        for (int x = 0; x < icon.length; ++x) {
            if (icon[x].length != 16) {
                nativeImage.close();
                throw new IllegalStateException("Icon is not 16x16");
            }
            for (int y = 0; y < icon.length; ++y) {
                nativeImage.n_1700_B(x, y, icon[x][y]);
            }
        }
        return nativeImage;
    }

    public g_2336_b getTexture(String id, g_2336_b defaultImage) {
        return this.images.getOrDefault(id, defaultImage);
    }
}


