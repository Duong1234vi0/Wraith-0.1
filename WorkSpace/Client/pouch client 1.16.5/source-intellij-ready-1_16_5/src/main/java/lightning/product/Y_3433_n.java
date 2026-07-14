/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_908_R;
import lightning.product.StringTag;
import lightning.product.LongArrayTag;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.U_2912_j;
import lightning.product.IntArrayTag;
import lightning.product.a_969_m;
import lightning.product.ByteArrayTag;
import lightning.product.TagType;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.IntTag;
import lightning.product.EndTag;

public class Y_3433_n {
    private static final TagType<?>[] n_1700_B = new TagType[]{EndTag.n_1700_B, L_3985_e.n_1700_B, a_969_m.n_1700_B, IntTag.n_1700_B, q_2567_I.n_1700_B, T_2717_K.J_1907_R, D_908_R.J_1907_R, ByteArrayTag.n_1700_B, StringTag.n_1700_B, q_2896_o.n_1700_B, U_2912_j.J_1907_R, IntArrayTag.n_1700_B, LongArrayTag.n_1700_B};

    public static TagType<?> n_1700_B(int id) {
        return id >= 0 && id < n_1700_B.length ? n_1700_B[id] : TagType.n_1700_B(id);
    }
}


