/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.GuiEventListener;
import lightning.product.MinecraftClient;
import lightning.product.ContainerEventHandler;
import lightning.product.o_2488_o;

public abstract class ContainerObjectSelectionList<E extends n_1700_B<E>>
extends o_2488_o<E> {
    public ContainerObjectSelectionList(MinecraftClient p_i51139_1_, int p_i51139_2_, int p_i51139_3_, int p_i51139_4_, int p_i51139_5_, int p_i51139_6_) {
        super(p_i51139_1_, p_i51139_2_, p_i51139_3_, p_i51139_4_, p_i51139_5_, p_i51139_6_);
    }

    @Override
    public boolean changeFocus(boolean focus) {
        boolean flag = super.changeFocus(focus);
        if (flag) {
            this.ensureVisible((n_1700_B)this.getListener());
        }
        return flag;
    }

    @Override
    protected boolean isSelectedItem(int index) {
        return false;
    }

    public static abstract class n_1700_B<E extends n_1700_B<E>>
    extends o_2488_o.n_1700_B<E>
    implements ContainerEventHandler {
        @Nullable
        private GuiEventListener field_214380_a;
        private boolean field_214381_b;

        @Override
        public boolean isDragging() {
            return this.field_214381_b;
        }

        @Override
        public void setDragging(boolean dragging) {
            this.field_214381_b = dragging;
        }

        @Override
        public void setListener(@Nullable GuiEventListener listener) {
            this.field_214380_a = listener;
        }

        @Override
        @Nullable
        public GuiEventListener getListener() {
            return this.field_214380_a;
        }
    }
}



