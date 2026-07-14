/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_3207_O;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.q_2335_j;
import lightning.product.r_1334_c;
import lightning.product.WolfModel;
import lightning.product.w_2040_b;

public class WolfRenderer
extends r_1334_c<q_2335_j, WolfModel<q_2335_j>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wolf/wolf.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/wolf/wolf_tame.png");
    private static final g_2336_b multiplayerClientSuggestionProvider = new g_2336_b("textures/entity/wolf/wolf_angry.png");

    public WolfRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new WolfModel(), 0.5f);
        this.n_1700_B(new E_3207_O(this));
    }

    @Override
    protected float n_1700_B(q_2335_j livingBase, float partialTicks) {
        return livingBase.V_1176_p();
    }

    @Override
    public void n_1700_B(q_2335_j entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        if (entityIn.h_1640_b()) {
            float f = entityIn.c_3005_b(partialTicks);
            ((WolfModel)this.v_4262_N).n_1700_B(f, f, f);
        }
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entityIn.h_1640_b()) {
            ((WolfModel)this.v_4262_N).n_1700_B(1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public g_2336_b n_1700_B(q_2335_j entity) {
        if (entity.U_3758_B()) {
            return t_1786_h;
        }
        return entity.B_() ? multiplayerClientSuggestionProvider : n_1700_B;
    }
}


