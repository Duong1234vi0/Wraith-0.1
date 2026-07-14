/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;

public class CategoryManager {
    protected final Map<String, VolumeCategoryImpl> categories = new ConcurrentHashMap<String, VolumeCategoryImpl>();

    public void addCategory(VolumeCategoryImpl category) {
        this.categories.put(category.getId(), category);
    }

    @Nullable
    public VolumeCategoryImpl removeCategory(String categoryId) {
        return this.categories.remove(categoryId);
    }

    public Collection<VolumeCategoryImpl> getCategories() {
        return this.categories.values();
    }
}

