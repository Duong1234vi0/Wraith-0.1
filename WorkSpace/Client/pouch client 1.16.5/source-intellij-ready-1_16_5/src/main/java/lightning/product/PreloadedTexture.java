/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.P_4645_d;
import lightning.product.ResourceManager;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;

public class PreloadedTexture
extends P_4645_d {
    @Nullable
    private CompletableFuture<P_4645_d.n_1700_B> n_1700_B;

    public PreloadedTexture(ResourceManager resourceManagerIn, g_2336_b locationIn, Executor executorIn) {
        super(locationIn);
        this.n_1700_B = CompletableFuture.supplyAsync(() -> P_4645_d.n_1700_B.n_1700_B(resourceManagerIn, locationIn), executorIn);
    }

    @Override
    protected P_4645_d.n_1700_B n_1700_B(ResourceManager resourceManager) {
        if (this.n_1700_B != null) {
            P_4645_d.n_1700_B simpletexture$texturedata = this.n_1700_B.join();
            this.n_1700_B = null;
            return simpletexture$texturedata;
        }
        return P_4645_d.n_1700_B.n_1700_B(resourceManager, this.R_4764_Y);
    }

    public CompletableFuture<Void> n_1700_B() {
        return this.n_1700_B == null ? CompletableFuture.completedFuture(null) : this.n_1700_B.thenApply(p_215247_0_ -> null);
    }

    @Override
    public void loadTexture(C_3240_x textureManagerIn, ResourceManager resourceManagerIn, g_2336_b resourceLocationIn, Executor executorIn) {
        this.n_1700_B = CompletableFuture.supplyAsync(() -> P_4645_d.n_1700_B.n_1700_B(resourceManagerIn, this.R_4764_Y), j_3341_s.u_1723_Y());
        this.n_1700_B.thenRunAsync(() -> textureManagerIn.n_1700_B(this.R_4764_Y, (c_4477_a)this), PreloadedTexture.n_1700_B(executorIn));
    }

    private static Executor n_1700_B(Executor executorIn) {
        return p_229206_1_ -> executorIn.execute(() -> c_4037_x.n_1700_B(p_229206_1_::run));
    }
}


