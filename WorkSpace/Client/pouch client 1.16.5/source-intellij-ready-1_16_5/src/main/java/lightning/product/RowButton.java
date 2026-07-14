/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.ObjectSelectionList;
import lightning.product.g_221_o;
import lightning.product.z_3470_q;

public abstract class RowButton {
    public final int n_1700_B;
    public final int J_1907_R;
    public final int R_4764_Y;
    public final int G_564_y;

    public RowButton(int p_i51779_1_, int p_i51779_2_, int p_i51779_3_, int p_i51779_4_) {
        this.n_1700_B = p_i51779_1_;
        this.J_1907_R = p_i51779_2_;
        this.R_4764_Y = p_i51779_3_;
        this.G_564_y = p_i51779_4_;
    }

    public void n_1700_B(g_221_o p_237726_1_, int p_237726_2_, int p_237726_3_, int p_237726_4_, int p_237726_5_) {
        int i = p_237726_2_ + this.R_4764_Y;
        int j = p_237726_3_ + this.G_564_y;
        boolean flag = false;
        if (p_237726_4_ >= i && p_237726_4_ <= i + this.n_1700_B && p_237726_5_ >= j && p_237726_5_ <= j + this.J_1907_R) {
            flag = true;
        }
        this.n_1700_B(p_237726_1_, i, j, flag);
    }

    protected abstract void n_1700_B(g_221_o var1, int var2, int var3, boolean var4);

    public int n_1700_B() {
        return this.R_4764_Y + this.n_1700_B;
    }

    public int J_1907_R() {
        return this.G_564_y + this.J_1907_R;
    }

    public abstract void n_1700_B(int var1);

    public static void n_1700_B(g_221_o p_237727_0_, List<RowButton> p_237727_1_, z_3470_q<?> p_237727_2_, int p_237727_3_, int p_237727_4_, int p_237727_5_, int p_237727_6_) {
        for (RowButton listbutton : p_237727_1_) {
            if (p_237727_2_.getRowWidth() <= listbutton.n_1700_B()) continue;
            listbutton.n_1700_B(p_237727_0_, p_237727_3_, p_237727_4_, p_237727_5_, p_237727_6_);
        }
    }

    public static void n_1700_B(z_3470_q<?> p_237728_0_, ObjectSelectionList.n_1700_B<?> p_237728_1_, List<RowButton> p_237728_2_, int p_237728_3_, double p_237728_4_, double p_237728_6_) {
        int i;
        if (p_237728_3_ == 0 && (i = p_237728_0_.getEventListeners().indexOf(p_237728_1_)) > -1) {
            p_237728_0_.n_1700_B(i);
            int j = p_237728_0_.getRowLeft();
            int k = p_237728_0_.getRowTop(i);
            int l = (int)(p_237728_4_ - (double)j);
            int i1 = (int)(p_237728_6_ - (double)k);
            for (RowButton listbutton : p_237728_2_) {
                if (l < listbutton.R_4764_Y || l > listbutton.n_1700_B() || i1 < listbutton.G_564_y || i1 > listbutton.J_1907_R()) continue;
                listbutton.n_1700_B(i);
            }
        }
    }
}


