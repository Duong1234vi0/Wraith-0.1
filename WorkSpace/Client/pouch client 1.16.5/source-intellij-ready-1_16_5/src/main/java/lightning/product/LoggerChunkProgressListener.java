/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.ChunkStatus;
import lightning.product.F_2904_S;
import lightning.product.Y_1387_d;
import lightning.product.j_3341_s;
import lightning.product.ChunkProgressListener;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerChunkProgressListener
implements ChunkProgressListener {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final int J_1907_R;
    private int R_4764_Y;
    private long G_564_y;
    private long P_1922_E = Long.MAX_VALUE;

    public LoggerChunkProgressListener(int radius) {
        int i = radius * 2 + 1;
        this.J_1907_R = i * i;
    }

    @Override
    public void n_1700_B(Y_1387_d center) {
        this.G_564_y = this.P_1922_E = j_3341_s.J_1907_R();
    }

    @Override
    public void n_1700_B(Y_1387_d chunkPosition, @Nullable ChunkStatus newStatus) {
        if (newStatus == ChunkStatus.P_4830_p) {
            ++this.R_4764_Y;
        }
        int i = this.J_1907_R();
        if (j_3341_s.J_1907_R() > this.P_1922_E) {
            this.P_1922_E += 500L;
            n_1700_B.info(new F_2904_S("menu.preparingSpawn", u_530_F.n_1700_B(i, 0, 100)).getString());
        }
    }

    @Override
    public void n_1700_B() {
        n_1700_B.info("Time elapsed: {} ms", (Object)(j_3341_s.J_1907_R() - this.G_564_y));
        this.P_1922_E = Long.MAX_VALUE;
    }

    public int J_1907_R() {
        return u_530_F.G_564_y((float)this.R_4764_Y * 100.0f / (float)this.J_1907_R);
    }
}


