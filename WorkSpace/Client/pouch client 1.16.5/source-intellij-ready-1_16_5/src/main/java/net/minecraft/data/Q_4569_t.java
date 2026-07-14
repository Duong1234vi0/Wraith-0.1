/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Stopwatch
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lightning.product.y_3482_a;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Y_259_p;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Q_4569_t {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Collection<Path> J_1907_R;
    private final Path R_4764_Y;
    private final List<Y_259_p> G_564_y = Lists.newArrayList();

    public Q_4569_t(Path output, Collection<Path> input) {
        this.R_4764_Y = output;
        this.J_1907_R = input;
    }

    public Collection<Path> n_1700_B() {
        return this.J_1907_R;
    }

    public Path J_1907_R() {
        return this.R_4764_Y;
    }

    public void R_4764_Y() throws IOException {
        M_182_A directorycache = new M_182_A(this.R_4764_Y, "cache");
        directorycache.R_4764_Y(this.J_1907_R().resolve("version.json"));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Stopwatch stopwatch1 = Stopwatch.createUnstarted();
        for (Y_259_p idataprovider : this.G_564_y) {
            n_1700_B.info("Starting provider: {}", (Object)idataprovider.n_1700_B());
            stopwatch1.start();
            idataprovider.n_1700_B(directorycache);
            stopwatch1.stop();
            n_1700_B.info("{} finished after {} ms", (Object)idataprovider.n_1700_B(), (Object)stopwatch1.elapsed(TimeUnit.MILLISECONDS));
            stopwatch1.reset();
        }
        n_1700_B.info("All providers took: {} ms", (Object)stopwatch.elapsed(TimeUnit.MILLISECONDS));
        directorycache.n_1700_B();
    }

    public void n_1700_B(Y_259_p provider) {
        this.G_564_y.add(provider);
    }

    static {
        y_3482_a.n_1700_B();
    }
}

