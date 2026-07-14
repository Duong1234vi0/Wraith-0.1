/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_3165_y;
import lightning.product.K_4074_S;
import lightning.product.L_1875_m;
import lightning.product.GrassColor;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.k_4467_X;
import lightning.product.DyeableLeatherItem;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.ItemColor;
import lightning.product.v_1669_V;
import lightning.product.w_424_u;
import lightning.product.SpawnEggItem;

public class N_1599_o {
    private final w_424_u<ItemColor> n_1700_B = new w_424_u(32);

    public static N_1599_o n_1700_B(k_4467_X colors) {
        N_1599_o itemcolors = new N_1599_o();
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> color > 0 ? -1 : ((DyeableLeatherItem)((Object)stack.J_1907_R())).d_(stack), Items.t_1509_b, Items.r_2090_h, Items.z_2759_Q, Items.a_1344_X, Items.HoneyBlock);
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> GrassColor.n_1700_B(0.5, 1.0), a_3742_W.Party, a_3742_W.PotionTracker);
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> {
            int[] aint;
            if (color != 1) {
                return -1;
            }
            U_2912_j compoundnbt = stack.J_1907_R("Explosion");
            int[] nArray = aint = compoundnbt != null && compoundnbt.R_4764_Y("Colors", 11) ? compoundnbt.h_1847_R("Colors") : null;
            if (aint != null && aint.length != 0) {
                if (aint.length == 1) {
                    return aint[0];
                }
                int i = 0;
                int j = 0;
                int k = 0;
                for (int l : aint) {
                    i += (l & 0xFF0000) >> 16;
                    j += (l & 0xFF00) >> 8;
                    k += (l & 0xFF) >> 0;
                }
                return (i /= aint.length) << 16 | (j /= aint.length) << 8 | (k /= aint.length);
            }
            return 0x8A8A8A;
        }, Items.FenceGateBlock);
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> color > 0 ? -1 : L_1875_m.R_4764_Y(stack), Items.j_2461_G, Items.g_2492_v, Items.NetherrackBlock);
        for (SpawnEggItem spawneggitem : SpawnEggItem.v_4262_N()) {
            itemcolors.n_1700_B((Z_1993_T stack, int color) -> spawneggitem.n_1700_B(color), spawneggitem);
        }
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> {
            K_4074_S blockstate = ((v_1669_V)stack.J_1907_R()).v_4262_N().multiplayerClientSuggestionProvider();
            return colors.n_1700_B(blockstate, (BlockAndTintGetter)null, (c_1514_x)null, color);
        }, a_3742_W.t_148_a, a_3742_W.u_744_e, a_3742_W.RetryCallException, a_3742_W.U_4087_m, a_3742_W.A_1038_p, a_3742_W.i_1637_u, a_3742_W.Ping, a_3742_W.p_178_J, a_3742_W.RealmsClientConfig, a_3742_W.f_4016_n, a_3742_W.S_4035_N);
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> color == 0 ? L_1875_m.R_4764_Y(stack) : -1, Items.NetherWartBlock);
        itemcolors.n_1700_B((Z_1993_T stack, int color) -> color == 0 ? -1 : G_3165_y.v_4262_N(stack), Items.K_4518_s);
        return itemcolors;
    }

    public int n_1700_B(Z_1993_T stack, int tintIndex) {
        ItemColor iitemcolor = this.n_1700_B.n_1700_B(V_3137_a.e_2887_G.n_1700_B(stack.J_1907_R()));
        return iitemcolor == null ? -1 : iitemcolor.getColor(stack, tintIndex);
    }

    public void n_1700_B(ItemColor itemColor, q_1803_e ... itemsIn) {
        for (q_1803_e iitemprovider : itemsIn) {
            this.n_1700_B.n_1700_B(itemColor, q_1613_l.n_1700_B(iitemprovider.u_1723_Y()));
        }
    }
}



