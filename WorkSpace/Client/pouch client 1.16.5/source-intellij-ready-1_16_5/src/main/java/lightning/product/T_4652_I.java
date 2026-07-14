/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import lightning.product.D_2103_L;
import lightning.product.I_2946_k;
import lightning.product.S_4169_p;
import lightning.product.PackSource;

public class T_4652_I
implements I_2946_k {
    private final S_4169_p n_1700_B = new S_4169_p("minecraft", "particular", "maseffects");

    @Override
    public void n_1700_B(Consumer<D_2103_L> infoConsumer, D_2103_L.n_1700_B infoFactory) {
        D_2103_L resourcepackinfo = D_2103_L.n_1700_B("vanilla", false, () -> this.n_1700_B, infoFactory, D_2103_L.J_1907_R.J_1907_R, PackSource.J_1907_R);
        if (resourcepackinfo != null) {
            infoConsumer.accept(resourcepackinfo);
        }
    }
}


