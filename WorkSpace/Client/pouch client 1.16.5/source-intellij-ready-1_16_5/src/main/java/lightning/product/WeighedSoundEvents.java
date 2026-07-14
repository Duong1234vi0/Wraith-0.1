/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.O_728_b;
import lightning.product.Y_444_s;
import lightning.product.g_2336_b;
import lightning.product.k_4218_M;
import lightning.product.Weighted;
import lightning.product.x_282_a;

public class WeighedSoundEvents
implements Weighted<O_728_b> {
    private final List<Weighted<O_728_b>> n_1700_B = Lists.newArrayList();
    private final Random J_1907_R = new Random();
    private final g_2336_b R_4764_Y;
    @Nullable
    private final x_282_a G_564_y;

    public WeighedSoundEvents(g_2336_b locationIn, @Nullable String subtitleIn) {
        this.R_4764_Y = locationIn;
        this.G_564_y = subtitleIn == null ? null : new F_2904_S(subtitleIn);
    }

    @Override
    public int n_1700_B() {
        int i = 0;
        for (Weighted<O_728_b> isoundeventaccessor : this.n_1700_B) {
            i += isoundeventaccessor.n_1700_B();
        }
        return i;
    }

    public O_728_b R_4764_Y() {
        int i = this.n_1700_B();
        if (!this.n_1700_B.isEmpty() && i != 0) {
            int j = this.J_1907_R.nextInt(i);
            for (Weighted<O_728_b> isoundeventaccessor : this.n_1700_B) {
                if ((j -= isoundeventaccessor.n_1700_B()) >= 0) continue;
                return isoundeventaccessor.J_1907_R();
            }
            return k_4218_M.n_1700_B;
        }
        return k_4218_M.n_1700_B;
    }

    public void n_1700_B(Weighted<O_728_b> accessor) {
        this.n_1700_B.add(accessor);
    }

    @Nullable
    public x_282_a G_564_y() {
        return this.G_564_y;
    }

    @Override
    public void n_1700_B(Y_444_s engine) {
        for (Weighted<O_728_b> isoundeventaccessor : this.n_1700_B) {
            isoundeventaccessor.n_1700_B(engine);
        }
    }

    @Override
    public /* synthetic */ Object J_1907_R() {
        return this.R_4764_Y();
    }
}


