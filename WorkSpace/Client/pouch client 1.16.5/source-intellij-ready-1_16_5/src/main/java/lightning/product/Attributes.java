/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RangedAttribute;
import lightning.product.Attribute;
import lightning.product.V_3137_a;

public class Attributes {
    public static final Attribute n_1700_B = Attributes.n_1700_B("generic.max_health", new RangedAttribute("attribute.name.generic.max_health", 20.0, 1.0, 1024.0).n_1700_B(true));
    public static final Attribute J_1907_R = Attributes.n_1700_B("generic.follow_range", new RangedAttribute("attribute.name.generic.follow_range", 32.0, 0.0, 2048.0));
    public static final Attribute R_4764_Y = Attributes.n_1700_B("generic.knockback_resistance", new RangedAttribute("attribute.name.generic.knockback_resistance", 0.0, 0.0, 1.0));
    public static final Attribute G_564_y = Attributes.n_1700_B("generic.movement_speed", new RangedAttribute("attribute.name.generic.movement_speed", 0.7f, 0.0, 1024.0).n_1700_B(true));
    public static final Attribute P_1922_E = Attributes.n_1700_B("generic.flying_speed", new RangedAttribute("attribute.name.generic.flying_speed", 0.4f, 0.0, 1024.0).n_1700_B(true));
    public static final Attribute u_1723_Y = Attributes.n_1700_B("generic.attack_damage", new RangedAttribute("attribute.name.generic.attack_damage", 2.0, 0.0, 2048.0));
    public static final Attribute v_4262_N = Attributes.n_1700_B("generic.attack_knockback", new RangedAttribute("attribute.name.generic.attack_knockback", 0.0, 0.0, 5.0));
    public static final Attribute w_1484_f = Attributes.n_1700_B("generic.attack_speed", new RangedAttribute("attribute.name.generic.attack_speed", 4.0, 0.0, 1024.0).n_1700_B(true));
    public static final Attribute t_148_a = Attributes.n_1700_B("generic.armor", new RangedAttribute("attribute.name.generic.armor", 0.0, 0.0, 30.0).n_1700_B(true));
    public static final Attribute s_956_w = Attributes.n_1700_B("generic.armor_toughness", new RangedAttribute("attribute.name.generic.armor_toughness", 0.0, 0.0, 20.0).n_1700_B(true));
    public static final Attribute u_2550_I = Attributes.n_1700_B("generic.luck", new RangedAttribute("attribute.name.generic.luck", 0.0, -1024.0, 1024.0).n_1700_B(true));
    public static final Attribute M_588_G = Attributes.n_1700_B("zombie.spawn_reinforcements", new RangedAttribute("attribute.name.zombie.spawn_reinforcements", 0.0, 0.0, 1.0));
    public static final Attribute P_4830_p = Attributes.n_1700_B("horse.jump_strength", new RangedAttribute("attribute.name.horse.jump_strength", 0.7, 0.0, 2.0).n_1700_B(true));

    private static Attribute n_1700_B(String id, Attribute attribute) {
        return V_3137_a.n_1700_B(V_3137_a.l_1233_K, id, attribute);
    }
}


