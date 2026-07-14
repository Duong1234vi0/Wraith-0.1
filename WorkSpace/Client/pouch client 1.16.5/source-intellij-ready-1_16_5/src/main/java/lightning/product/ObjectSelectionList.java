/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MinecraftClient;
import lightning.product.o_2488_o;

public abstract class ObjectSelectionList<E extends o_2488_o.n_1700_B<E>>
extends o_2488_o<E> {
    private boolean n_1700_B;

    public ObjectSelectionList(MinecraftClient mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
    }

    @Override
    public boolean changeFocus(boolean focus) {
        if (!this.n_1700_B && this.getItemCount() == 0) {
            return false;
        }
        boolean bl = this.n_1700_B = !this.n_1700_B;
        if (this.n_1700_B && this.getSelected() == null && this.getItemCount() > 0) {
            this.moveSelection(o_2488_o.J_1907_R.J_1907_R);
        } else if (this.n_1700_B && this.getSelected() != null) {
            this.func_241574_n_();
        }
        return this.n_1700_B;
    }

    public static abstract class n_1700_B<E extends n_1700_B<E>>
    extends o_2488_o.n_1700_B<E> {
        @Override
        public boolean changeFocus(boolean focus) {
            return false;
        }
    }
}



