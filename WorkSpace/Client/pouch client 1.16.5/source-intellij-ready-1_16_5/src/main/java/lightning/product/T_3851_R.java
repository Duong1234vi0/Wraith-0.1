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
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;

public class T_3851_R {
    public static final Codec<T_3851_R> n_1700_B = RecordCodecBuilder.create(p_236930_0_ -> p_236930_0_.group((App)Codec.intRange((int)0, (int)256).fieldOf("height").forGetter(T_3851_R::n_1700_B), (App)V_3137_a.q_4610_l.fieldOf("block").orElse((Object)a_3742_W.n_1700_B).forGetter(p_236931_0_ -> p_236931_0_.J_1907_R().J_1907_R())).apply((Applicative)p_236930_0_, T_3851_R::new));
    private final K_4074_S J_1907_R;
    private final int R_4764_Y;
    private int G_564_y;

    public T_3851_R(int p_i45467_1_, T_2915_h layerMaterialIn) {
        this.R_4764_Y = p_i45467_1_;
        this.J_1907_R = layerMaterialIn.multiplayerClientSuggestionProvider();
    }

    public int n_1700_B() {
        return this.R_4764_Y;
    }

    public K_4074_S J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public void n_1700_B(int minY) {
        this.G_564_y = minY;
    }

    public String toString() {
        return (String)(this.R_4764_Y != 1 ? this.R_4764_Y + "*" : "") + String.valueOf(V_3137_a.q_4610_l.J_1907_R(this.J_1907_R.J_1907_R()));
    }
}


