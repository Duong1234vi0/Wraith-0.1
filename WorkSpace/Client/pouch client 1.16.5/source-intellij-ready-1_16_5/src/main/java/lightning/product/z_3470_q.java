/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;
import lightning.product.ObjectSelectionList;
import lightning.product.MinecraftClient;
import lightning.product.o_2488_o;

public abstract class z_3470_q<E extends ObjectSelectionList.n_1700_B<E>>
extends ObjectSelectionList<E> {
    protected z_3470_q(int p_i50516_1_, int p_i50516_2_, int p_i50516_3_, int p_i50516_4_, int p_i50516_5_) {
        super(MinecraftClient.A_4115_X(), p_i50516_1_, p_i50516_2_, p_i50516_3_, p_i50516_4_, p_i50516_5_);
    }

    public void G_564_y(int p_239561_1_) {
        if (p_239561_1_ == -1) {
            this.setSelected(null);
        } else if (super.getItemCount() != 0) {
            this.setSelected((ObjectSelectionList.n_1700_B)this.getEntry(p_239561_1_));
        }
    }

    public void n_1700_B(int p_231400_1_) {
        this.G_564_y(p_231400_1_);
    }

    public void n_1700_B(int p_231401_1_, int p_231401_2_, double p_231401_3_, double p_231401_5_, int p_231401_7_) {
    }

    @Override
    public int getMaxPosition() {
        return 0;
    }

    @Override
    public int getScrollbarPosition() {
        return this.getRowLeft() + this.getRowWidth();
    }

    @Override
    public int getRowWidth() {
        return (int)((double)this.width * 0.6);
    }

    @Override
    public void replaceEntries(Collection<E> entries) {
        super.replaceEntries(entries);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getRowTop(int p_230962_1_) {
        return super.getRowTop(p_230962_1_);
    }

    @Override
    public int getRowLeft() {
        return super.getRowLeft();
    }

    public int n_1700_B(E entry) {
        return super.addEntry(entry);
    }

    public void n_1700_B() {
        this.clearEntries();
    }

    @Override
    public /* synthetic */ int addEntry(o_2488_o.n_1700_B n_1700_B2) {
        return this.n_1700_B((E)((ObjectSelectionList.n_1700_B)n_1700_B2));
    }
}



