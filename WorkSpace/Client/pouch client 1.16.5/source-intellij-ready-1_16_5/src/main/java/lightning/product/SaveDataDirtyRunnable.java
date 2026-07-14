/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SavedData;

public class SaveDataDirtyRunnable
implements Runnable {
    private final SavedData n_1700_B;

    public SaveDataDirtyRunnable(SavedData dataIn) {
        this.n_1700_B = dataIn;
    }

    @Override
    public void run() {
        this.n_1700_B.R_4764_Y();
    }
}


