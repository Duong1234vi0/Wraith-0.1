/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.E_4700_p;

public final class Z_749_F
extends Enum<Z_749_F>
implements E_4700_p {
    public static final /* enum */ Z_749_F n_1700_B = new Z_749_F("monster", 70, false, false, 128);
    public static final /* enum */ Z_749_F J_1907_R = new Z_749_F("creature", 10, true, true, 128);
    public static final /* enum */ Z_749_F R_4764_Y = new Z_749_F("ambient", 15, true, false, 128);
    public static final /* enum */ Z_749_F G_564_y = new Z_749_F("water_creature", 5, true, false, 128);
    public static final /* enum */ Z_749_F P_1922_E = new Z_749_F("water_ambient", 20, true, false, 64);
    public static final /* enum */ Z_749_F u_1723_Y = new Z_749_F("misc", -1, true, true, 128);
    public static final Codec<Z_749_F> v_4262_N;
    private static final Map<String, Z_749_F> w_1484_f;
    private final int t_148_a;
    private final boolean s_956_w;
    private final boolean u_2550_I;
    private final String M_588_G;
    private final int P_4830_p = 32;
    private final int h_1847_R;
    private static final /* synthetic */ Z_749_F[] Q_4569_t;

    public static Z_749_F[] values() {
        return (Z_749_F[])Q_4569_t.clone();
    }

    public static Z_749_F valueOf(String name) {
        return Enum.valueOf(Z_749_F.class, name);
    }

    private Z_749_F(String name, int maxNumberOfCreature, boolean isPeacefulCreature, boolean isAnimal, int instantDespawnDistance) {
        this.M_588_G = name;
        this.t_148_a = maxNumberOfCreature;
        this.s_956_w = isPeacefulCreature;
        this.u_2550_I = isAnimal;
        this.h_1847_R = instantDespawnDistance;
    }

    public String J_1907_R() {
        return this.M_588_G;
    }

    @Override
    public String n_1700_B() {
        return this.M_588_G;
    }

    public static Z_749_F n_1700_B(String name) {
        return w_1484_f.get(name);
    }

    public int R_4764_Y() {
        return this.t_148_a;
    }

    public boolean G_564_y() {
        return this.s_956_w;
    }

    public boolean P_1922_E() {
        return this.u_2550_I;
    }

    public int u_1723_Y() {
        return this.h_1847_R;
    }

    public int v_4262_N() {
        return 32;
    }

    private static /* synthetic */ Z_749_F[] w_1484_f() {
        return new Z_749_F[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        Q_4569_t = Z_749_F.w_1484_f();
        v_4262_N = E_4700_p.n_1700_B(Z_749_F::values, Z_749_F::n_1700_B);
        w_1484_f = Arrays.stream(Z_749_F.values()).collect(Collectors.toMap(Z_749_F::J_1907_R, classification -> classification));
    }
}

