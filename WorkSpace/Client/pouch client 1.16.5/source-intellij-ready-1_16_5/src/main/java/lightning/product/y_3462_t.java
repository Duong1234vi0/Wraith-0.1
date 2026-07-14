/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public interface y_3462_t {
    public float getAdvance();

    default public float n_1700_B(boolean p_223274_1_) {
        return this.getAdvance() + (p_223274_1_ ? this.u_1723_Y() : 0.0f);
    }

    default public float P_1922_E() {
        return 0.0f;
    }

    default public float u_1723_Y() {
        return 1.0f;
    }

    default public float v_4262_N() {
        return 1.0f;
    }
}

