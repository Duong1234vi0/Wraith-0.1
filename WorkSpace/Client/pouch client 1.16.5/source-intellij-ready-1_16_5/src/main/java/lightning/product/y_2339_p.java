/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Objects;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.q_2232_A;

public class y_2339_p {
    private final c_1514_x n_1700_B;
    private final q_2232_A J_1907_R;
    private int R_4764_Y;
    private final Runnable G_564_y;

    public static Codec<y_2339_p> n_1700_B(Runnable p_234150_0_) {
        return RecordCodecBuilder.create(p_234151_1_ -> p_234151_1_.group((App)c_1514_x.CODEC.fieldOf("pos").forGetter(point -> point.n_1700_B), (App)V_3137_a.A_1038_p.fieldOf("type").forGetter(point -> point.J_1907_R), (App)Codec.INT.fieldOf("free_tickets").orElse((Object)0).forGetter(p_234149_0_ -> p_234149_0_.R_4764_Y), (App)RecordCodecBuilder.point((Object)p_234150_0_)).apply((Applicative)p_234151_1_, y_2339_p::new));
    }

    private y_2339_p(c_1514_x posIn, q_2232_A typeIn, int freeTicketsIn, Runnable onChangeIn) {
        this.n_1700_B = posIn.toImmutable();
        this.J_1907_R = typeIn;
        this.R_4764_Y = freeTicketsIn;
        this.G_564_y = onChangeIn;
    }

    public y_2339_p(c_1514_x posIn, q_2232_A typeIn, Runnable onChangeIn) {
        this(posIn, typeIn, typeIn.n_1700_B(), onChangeIn);
    }

    protected boolean n_1700_B() {
        if (this.R_4764_Y <= 0) {
            return false;
        }
        --this.R_4764_Y;
        this.G_564_y.run();
        return true;
    }

    protected boolean J_1907_R() {
        if (this.R_4764_Y >= this.J_1907_R.n_1700_B()) {
            return false;
        }
        ++this.R_4764_Y;
        this.G_564_y.run();
        return true;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y > 0;
    }

    public boolean G_564_y() {
        return this.R_4764_Y != this.J_1907_R.n_1700_B();
    }

    public c_1514_x P_1922_E() {
        return this.n_1700_B;
    }

    public q_2232_A u_1723_Y() {
        return this.J_1907_R;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return p_equals_1_ != null && this.getClass() == p_equals_1_.getClass() ? Objects.equals(this.n_1700_B, ((y_2339_p)p_equals_1_).n_1700_B) : false;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }
}

