/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.LongSupplier;
import javax.annotation.Nullable;
import lightning.product.InactiveProfiler;
import lightning.product.V_3322_x;
import lightning.product.ProfilerFiller;
import lightning.product.ProfileResults;
import lightning.product.q_1764_n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class W_2800_c {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final LongSupplier J_1907_R = null;
    private final long R_4764_Y = 0L;
    private int G_564_y;
    private final File P_1922_E = null;
    private V_3322_x u_1723_Y;

    public ProfilerFiller n_1700_B() {
        this.u_1723_Y = new q_1764_n(this.J_1907_R, () -> this.G_564_y, false);
        ++this.G_564_y;
        return this.u_1723_Y;
    }

    public void J_1907_R() {
        if (this.u_1723_Y != InactiveProfiler.n_1700_B) {
            ProfileResults iprofileresult = this.u_1723_Y.G_564_y();
            this.u_1723_Y = InactiveProfiler.n_1700_B;
            if (iprofileresult.u_1723_Y() >= this.R_4764_Y) {
                File file1 = new File(this.P_1922_E, "tick-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt");
                iprofileresult.n_1700_B(file1);
                n_1700_B.info("Recorded long tick -- wrote info to: {}", (Object)file1.getAbsolutePath());
            }
        }
    }

    @Nullable
    public static W_2800_c n_1700_B(String p_233524_0_) {
        return null;
    }

    public static ProfilerFiller n_1700_B(ProfilerFiller p_233523_0_, @Nullable W_2800_c p_233523_1_) {
        return p_233523_1_ != null ? ProfilerFiller.n_1700_B(p_233523_1_.n_1700_B(), p_233523_0_) : p_233523_0_;
    }

    private W_2800_c() {
        throw new RuntimeException("Synthetic constructor added by MCP, do not call");
    }
}


