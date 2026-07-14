/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.GuiEventListener;
import lightning.product.ContainerEventHandler;

public abstract class A_1658_r
extends C_2701_A
implements ContainerEventHandler {
    @Nullable
    private GuiEventListener field_230699_a_;
    private boolean isDragging;

    @Override
    public final boolean isDragging() {
        return this.isDragging;
    }

    @Override
    public final void setDragging(boolean dragging) {
        this.isDragging = dragging;
    }

    @Override
    @Nullable
    public GuiEventListener getListener() {
        return this.field_230699_a_;
    }

    @Override
    public void setListener(@Nullable GuiEventListener listener) {
        this.field_230699_a_ = listener;
    }
}


