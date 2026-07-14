/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package mods.voicechat.gui.widgets;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.GuiEventListener;
import lightning.product.ContainerObjectSelectionList;

public abstract class ListScreenEntryBase<T extends ContainerObjectSelectionList.n_1700_B<T>>
extends ContainerObjectSelectionList.n_1700_B<T> {
    protected final List<GuiEventListener> children = Lists.newArrayList();

    @Override
    public List<? extends GuiEventListener> getEventListeners() {
        return this.children;
    }
}


