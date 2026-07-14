/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ContainerData;

public class SimpleContainerData
implements ContainerData {
    private final int[] n_1700_B;

    public SimpleContainerData(int size) {
        this.n_1700_B = new int[size];
    }

    @Override
    public int n_1700_B(int index) {
        return this.n_1700_B[index];
    }

    @Override
    public void n_1700_B(int index, int value) {
        this.n_1700_B[index] = value;
    }

    @Override
    public int n_1700_B() {
        return this.n_1700_B.length;
    }
}


