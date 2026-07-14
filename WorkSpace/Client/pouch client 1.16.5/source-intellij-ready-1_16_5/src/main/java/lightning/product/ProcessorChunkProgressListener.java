/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import lightning.product.ChunkStatus;
import lightning.product.U_3758_m;
import lightning.product.Y_1387_d;
import lightning.product.ChunkProgressListener;

public class ProcessorChunkProgressListener
implements ChunkProgressListener {
    private final ChunkProgressListener n_1700_B;
    private final U_3758_m<Runnable> J_1907_R;

    public ProcessorChunkProgressListener(ChunkProgressListener delegate, Executor executor) {
        this.n_1700_B = delegate;
        this.J_1907_R = U_3758_m.n_1700_B(executor, "progressListener");
    }

    @Override
    public void n_1700_B(Y_1387_d center) {
        this.J_1907_R.n_1700_B(() -> this.n_1700_B.n_1700_B(center));
    }

    @Override
    public void n_1700_B(Y_1387_d chunkPosition, @Nullable ChunkStatus newStatus) {
        this.J_1907_R.n_1700_B(() -> this.n_1700_B.n_1700_B(chunkPosition, newStatus));
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.n_1700_B(this.n_1700_B::n_1700_B);
    }
}


