/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArraySet
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import java.util.Set;
import java.util.stream.Stream;

public class WoodType {
    private static final Set<WoodType> t_148_a = new ObjectArraySet();
    public static final WoodType n_1700_B = WoodType.n_1700_B(new WoodType("oak"));
    public static final WoodType J_1907_R = WoodType.n_1700_B(new WoodType("spruce"));
    public static final WoodType R_4764_Y = WoodType.n_1700_B(new WoodType("birch"));
    public static final WoodType G_564_y = WoodType.n_1700_B(new WoodType("acacia"));
    public static final WoodType P_1922_E = WoodType.n_1700_B(new WoodType("jungle"));
    public static final WoodType u_1723_Y = WoodType.n_1700_B(new WoodType("dark_oak"));
    public static final WoodType v_4262_N = WoodType.n_1700_B(new WoodType("crimson"));
    public static final WoodType w_1484_f = WoodType.n_1700_B(new WoodType("warped"));
    private final String s_956_w;

    protected WoodType(String nameIn) {
        this.s_956_w = nameIn;
    }

    private static WoodType n_1700_B(WoodType woodTypeIn) {
        t_148_a.add(woodTypeIn);
        return woodTypeIn;
    }

    public static Stream<WoodType> n_1700_B() {
        return t_148_a.stream();
    }

    public String J_1907_R() {
        return this.s_956_w;
    }
}


