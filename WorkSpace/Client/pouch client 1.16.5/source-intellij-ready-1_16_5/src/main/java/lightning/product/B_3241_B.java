/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ServerFunctionManager;
import lightning.product.TimerCallback;
import lightning.product.U_2912_j;
import lightning.product.Z_1125_b;
import lightning.product.g_2336_b;
import lightning.product.r_3448_Z;
import net.minecraft.server.G_564_y;

public class B_3241_B
implements TimerCallback<G_564_y> {
    private final g_2336_b n_1700_B;

    public B_3241_B(g_2336_b p_i51190_1_) {
        this.n_1700_B = p_i51190_1_;
    }

    @Override
    public void n_1700_B(G_564_y obj, Z_1125_b<G_564_y> manager, long gameTime) {
        ServerFunctionManager functionmanager = obj.RealmsWorldResetDto();
        functionmanager.n_1700_B(this.n_1700_B).ifPresent(p_216316_1_ -> functionmanager.n_1700_B((r_3448_Z)p_216316_1_, functionmanager.G_564_y()));
    }

    public static class n_1700_B
    extends TimerCallback.n_1700_B<G_564_y, B_3241_B> {
        public n_1700_B() {
            super(new g_2336_b("function"), B_3241_B.class);
        }

        @Override
        public void n_1700_B(U_2912_j p_212847_1_, B_3241_B p_212847_2_) {
            p_212847_1_.n_1700_B("Name", p_212847_2_.n_1700_B.toString());
        }

        public B_3241_B J_1907_R(U_2912_j p_212846_1_) {
            g_2336_b resourcelocation = new g_2336_b(p_212846_1_.M_588_G("Name"));
            return new B_3241_B(resourcelocation);
        }

        @Override
        public /* synthetic */ TimerCallback n_1700_B(U_2912_j u_2912_j) {
            return this.J_1907_R(u_2912_j);
        }
    }
}


