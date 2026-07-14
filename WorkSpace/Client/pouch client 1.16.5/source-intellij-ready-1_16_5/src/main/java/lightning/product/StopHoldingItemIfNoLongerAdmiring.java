/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.A_4919_q;
import lightning.product.A_69_b;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StopHoldingItemIfNoLongerAdmiring<E extends A_69_b>
extends Behavior<E> {
    public StopHoldingItemIfNoLongerAdmiring() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.T_2506_i, (Object)((Object)S_50_d.J_1907_R)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return !((r_4811_B)owner).S_4035_N().n_1700_B() && ((r_4811_B)owner).S_4035_N().J_1907_R() != Items.NoteBlock;
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        A_4919_q.n_1700_B(entityIn, true);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


