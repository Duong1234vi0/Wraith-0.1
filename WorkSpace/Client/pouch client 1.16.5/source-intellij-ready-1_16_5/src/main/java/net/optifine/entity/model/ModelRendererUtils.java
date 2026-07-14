/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package net.optifine.entity.model;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import lightning.product.e_4189_z;

public class ModelRendererUtils {
    public static e_4189_z getModelRenderer(Iterator<e_4189_z> iterator, int index) {
        if (iterator == null) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        for (int i = 0; i < index; ++i) {
            if (!iterator.hasNext()) {
                return null;
            }
            e_4189_z e_4189_z2 = iterator.next();
        }
        return !iterator.hasNext() ? null : iterator.next();
    }

    public static e_4189_z getModelRenderer(ImmutableList<e_4189_z> models, int index) {
        if (models == null) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        return index >= models.size() ? null : (e_4189_z)models.get(index);
    }
}

