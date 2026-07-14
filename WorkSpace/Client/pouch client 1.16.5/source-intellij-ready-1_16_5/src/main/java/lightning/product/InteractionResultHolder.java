/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.m_3054_I;

public class InteractionResultHolder<T> {
    private final m_3054_I n_1700_B;
    private final T J_1907_R;

    public InteractionResultHolder(m_3054_I typeIn, T resultIn) {
        this.n_1700_B = typeIn;
        this.J_1907_R = resultIn;
    }

    public m_3054_I n_1700_B() {
        return this.n_1700_B;
    }

    public T J_1907_R() {
        return this.J_1907_R;
    }

    public static <T> InteractionResultHolder<T> n_1700_B(T p_226248_0_) {
        return new InteractionResultHolder<T>(m_3054_I.n_1700_B, p_226248_0_);
    }

    public static <T> InteractionResultHolder<T> J_1907_R(T p_226249_0_) {
        return new InteractionResultHolder<T>(m_3054_I.J_1907_R, p_226249_0_);
    }

    public static <T> InteractionResultHolder<T> R_4764_Y(T p_226250_0_) {
        return new InteractionResultHolder<T>(m_3054_I.R_4764_Y, p_226250_0_);
    }

    public static <T> InteractionResultHolder<T> G_564_y(T p_226251_0_) {
        return new InteractionResultHolder<T>(m_3054_I.G_564_y, p_226251_0_);
    }

    public static <T> InteractionResultHolder<T> n_1700_B(T p_233538_0_, boolean p_233538_1_) {
        return p_233538_1_ ? InteractionResultHolder.n_1700_B(p_233538_0_) : InteractionResultHolder.J_1907_R(p_233538_0_);
    }
}


