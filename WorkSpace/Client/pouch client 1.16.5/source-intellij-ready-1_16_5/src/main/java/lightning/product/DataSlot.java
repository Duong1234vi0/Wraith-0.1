/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ContainerData;

public abstract class DataSlot {
    private int n_1700_B;

    public static DataSlot n_1700_B(final ContainerData data, final int idx) {
        return new DataSlot(){

            @Override
            public int J_1907_R() {
                return data.n_1700_B(idx);
            }

            @Override
            public void n_1700_B(int value) {
                data.n_1700_B(idx, value);
            }
        };
    }

    public static DataSlot n_1700_B(final int[] data, final int idx) {
        return new DataSlot(){

            @Override
            public int J_1907_R() {
                return data[idx];
            }

            @Override
            public void n_1700_B(int value) {
                data[idx] = value;
            }
        };
    }

    public static DataSlot n_1700_B() {
        return new DataSlot(){
            private int n_1700_B;

            @Override
            public int J_1907_R() {
                return this.n_1700_B;
            }

            @Override
            public void n_1700_B(int value) {
                this.n_1700_B = value;
            }
        };
    }

    public abstract int J_1907_R();

    public abstract void n_1700_B(int var1);

    public boolean R_4764_Y() {
        int i = this.J_1907_R();
        boolean flag = i != this.n_1700_B;
        this.n_1700_B = i;
        return flag;
    }
}


