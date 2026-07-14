/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ServerFunctionManager;
import lightning.product.TimerCallback;
import lightning.product.U_2912_j;
import lightning.product.Z_1125_b;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.r_3448_Z;
import net.minecraft.server.G_564_y;

public class I_4764_L
implements TimerCallback<G_564_y> {
    private final g_2336_b n_1700_B;

    public I_4764_L(g_2336_b p_i51189_1_) {
        this.n_1700_B = p_i51189_1_;
    }

    @Override
    public void n_1700_B(G_564_y obj, Z_1125_b<G_564_y> manager, long gameTime) {
        ServerFunctionManager functionmanager = obj.RealmsWorldResetDto();
        r_109_r<r_3448_Z> itag = functionmanager.J_1907_R(this.n_1700_B);
        for (r_3448_Z functionobject : itag.n_1700_B()) {
            functionmanager.n_1700_B(functionobject, functionmanager.G_564_y());
        }
    }

    public static class n_1700_B
    extends TimerCallback.n_1700_B<G_564_y, I_4764_L> {
        public n_1700_B() {
            super(new g_2336_b("function_tag"), I_4764_L.class);
        }

        @Override
        public void n_1700_B(U_2912_j p_212847_1_, I_4764_L p_212847_2_) {
            p_212847_1_.n_1700_B("Name", p_212847_2_.n_1700_B.toString());
        }

        public I_4764_L J_1907_R(U_2912_j p_212846_1_) {
            g_2336_b resourcelocation = new g_2336_b(p_212846_1_.M_588_G("Name"));
            return new I_4764_L(resourcelocation);
        }

        @Override
        public /* synthetic */ TimerCallback n_1700_B(U_2912_j u_2912_j) {
            return this.J_1907_R(u_2912_j);
        }
    }
}


