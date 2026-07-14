/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.S_3826_o;
import lightning.product.Z_1993_T;
import lightning.product.d_1062_x;
import lightning.product.ModelManager;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.v_1669_V;

public class ItemModelShaper {
    public final Int2ObjectMap<d_1062_x> n_1700_B = new Int2ObjectOpenHashMap(256);
    private final Int2ObjectMap<S_3826_o> J_1907_R = new Int2ObjectOpenHashMap(256);
    private final ModelManager R_4764_Y;

    public ItemModelShaper(ModelManager modelManager) {
        this.R_4764_Y = modelManager;
    }

    public B_3871_I n_1700_B(q_1803_e itemProvider) {
        return this.n_1700_B(new Z_1993_T(itemProvider));
    }

    public B_3871_I n_1700_B(Z_1993_T stack) {
        S_3826_o ibakedmodel = this.J_1907_R(stack);
        return ibakedmodel == this.R_4764_Y.J_1907_R() && stack.J_1907_R() instanceof v_1669_V ? this.R_4764_Y.R_4764_Y().n_1700_B(((v_1669_V)stack.J_1907_R()).v_4262_N().multiplayerClientSuggestionProvider()) : ibakedmodel.P_1922_E();
    }

    public S_3826_o J_1907_R(Z_1993_T stack) {
        S_3826_o ibakedmodel = this.n_1700_B(stack.J_1907_R());
        return ibakedmodel == null ? this.R_4764_Y.J_1907_R() : ibakedmodel;
    }

    @Nullable
    public S_3826_o n_1700_B(q_1613_l itemIn) {
        return (S_3826_o)this.J_1907_R.get(ItemModelShaper.J_1907_R(itemIn));
    }

    private static int J_1907_R(q_1613_l itemIn) {
        return q_1613_l.n_1700_B(itemIn);
    }

    public void n_1700_B(q_1613_l itemIn, d_1062_x modelLocation) {
        this.n_1700_B.put(ItemModelShaper.J_1907_R(itemIn), (Object)modelLocation);
    }

    public ModelManager n_1700_B() {
        return this.R_4764_Y;
    }

    public void J_1907_R() {
        this.J_1907_R.clear();
        for (Map.Entry entry : this.n_1700_B.entrySet()) {
            this.J_1907_R.put((Integer)entry.getKey(), (Object)this.R_4764_Y.n_1700_B((d_1062_x)entry.getValue()));
        }
    }
}


