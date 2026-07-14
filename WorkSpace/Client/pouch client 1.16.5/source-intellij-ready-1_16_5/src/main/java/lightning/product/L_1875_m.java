/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.Attribute;
import lightning.product.Potions;
import lightning.product.MobEffectUtil;
import lightning.product.U_1880_G;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.q_2896_o;
import lightning.product.x_282_a;
import lightning.product.y_528_b;
import net.optifine.Config;
import net.optifine.CustomColors;

public class L_1875_m {
    private static final MutableComponent n_1700_B = new F_2904_S("effect.none").n_1700_B(D_4024_W.w_1484_f);

    public static List<k_2610_C> n_1700_B(Z_1993_T stack) {
        return L_1875_m.n_1700_B(stack.Q_4569_t());
    }

    public static List<k_2610_C> n_1700_B(y_528_b potionIn, Collection<k_2610_C> effects) {
        ArrayList list = Lists.newArrayList();
        list.addAll(potionIn.n_1700_B());
        list.addAll(effects);
        return list;
    }

    public static List<k_2610_C> n_1700_B(@Nullable U_2912_j tag) {
        ArrayList list = Lists.newArrayList();
        list.addAll(L_1875_m.R_4764_Y(tag).n_1700_B());
        L_1875_m.n_1700_B(tag, list);
        return list;
    }

    public static List<k_2610_C> J_1907_R(Z_1993_T itemIn) {
        return L_1875_m.J_1907_R(itemIn.Q_4569_t());
    }

    public static List<k_2610_C> J_1907_R(@Nullable U_2912_j tag) {
        ArrayList list = Lists.newArrayList();
        L_1875_m.n_1700_B(tag, list);
        return list;
    }

    public static void n_1700_B(@Nullable U_2912_j tag, List<k_2610_C> effectList) {
        if (tag != null && tag.R_4764_Y("CustomPotionEffects", 9)) {
            q_2896_o listnbt = tag.G_564_y("CustomPotionEffects", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                U_2912_j compoundnbt = listnbt.n_1700_B(i);
                k_2610_C effectinstance = k_2610_C.J_1907_R(compoundnbt);
                if (effectinstance == null) continue;
                effectList.add(effectinstance);
            }
        }
    }

    public static int R_4764_Y(Z_1993_T itemStackIn) {
        U_2912_j compoundnbt = itemStackIn.Q_4569_t();
        if (compoundnbt != null && compoundnbt.R_4764_Y("CustomPotionColor", 99)) {
            return compoundnbt.w_1484_f("CustomPotionColor");
        }
        return L_1875_m.G_564_y(itemStackIn) == Potions.n_1700_B ? 0xF800F8 : L_1875_m.n_1700_B(L_1875_m.n_1700_B(itemStackIn));
    }

    public static int n_1700_B(y_528_b potionIn) {
        return potionIn == Potions.n_1700_B ? 0xF800F8 : L_1875_m.n_1700_B(potionIn.n_1700_B());
    }

    public static int n_1700_B(Collection<k_2610_C> effects) {
        int i = 3694022;
        if (effects.isEmpty()) {
            return Config.isCustomColors() ? CustomColors.getPotionColor(null, i) : 3694022;
        }
        float f = 0.0f;
        float f1 = 0.0f;
        float f2 = 0.0f;
        int j = 0;
        for (k_2610_C effectinstance : effects) {
            if (!effectinstance.P_1922_E()) continue;
            int k = effectinstance.n_1700_B().u_1723_Y();
            if (Config.isCustomColors()) {
                k = CustomColors.getPotionColor(effectinstance.n_1700_B(), k);
            }
            int l = effectinstance.R_4764_Y() + 1;
            f += (float)(l * (k >> 16 & 0xFF)) / 255.0f;
            f1 += (float)(l * (k >> 8 & 0xFF)) / 255.0f;
            f2 += (float)(l * (k >> 0 & 0xFF)) / 255.0f;
            j += l;
        }
        if (j == 0) {
            return 0;
        }
        f = f / (float)j * 255.0f;
        f1 = f1 / (float)j * 255.0f;
        f2 = f2 / (float)j * 255.0f;
        return (int)f << 16 | (int)f1 << 8 | (int)f2;
    }

    public static y_528_b G_564_y(Z_1993_T itemIn) {
        return L_1875_m.R_4764_Y(itemIn.Q_4569_t());
    }

    public static y_528_b R_4764_Y(@Nullable U_2912_j tag) {
        return tag == null ? Potions.n_1700_B : y_528_b.n_1700_B(tag.M_588_G("Potion"));
    }

    public static Z_1993_T n_1700_B(Z_1993_T itemIn, y_528_b potionIn) {
        g_2336_b resourcelocation = V_3137_a.B_1668_F.J_1907_R(potionIn);
        if (potionIn == Potions.n_1700_B) {
            itemIn.R_4764_Y("Potion");
        } else {
            itemIn.M_182_A().n_1700_B("Potion", resourcelocation.toString());
        }
        return itemIn;
    }

    public static Z_1993_T n_1700_B(Z_1993_T itemIn, Collection<k_2610_C> effects) {
        if (effects.isEmpty()) {
            return itemIn;
        }
        U_2912_j compoundnbt = itemIn.M_182_A();
        q_2896_o listnbt = compoundnbt.G_564_y("CustomPotionEffects", 9);
        for (k_2610_C effectinstance : effects) {
            listnbt.add(effectinstance.n_1700_B(new U_2912_j()));
        }
        compoundnbt.n_1700_B("CustomPotionEffects", listnbt);
        return itemIn;
    }

    public static void n_1700_B(Z_1993_T itemIn, List<x_282_a> lores, float durationFactor) {
        List<k_2610_C> list = L_1875_m.n_1700_B(itemIn);
        ArrayList list1 = Lists.newArrayList();
        if (list.isEmpty()) {
            lores.add(n_1700_B);
        } else {
            for (k_2610_C effectinstance : list) {
                F_2904_S iformattabletextcomponent = new F_2904_S(effectinstance.v_4262_N());
                g_422_i effect = effectinstance.n_1700_B();
                Map<Attribute, U_1880_G> map = effect.v_4262_N();
                if (!map.isEmpty()) {
                    for (Map.Entry<Attribute, U_1880_G> entry : map.entrySet()) {
                        U_1880_G attributemodifier = entry.getValue();
                        U_1880_G attributemodifier1 = new U_1880_G(attributemodifier.J_1907_R(), effect.n_1700_B(effectinstance.R_4764_Y(), attributemodifier), attributemodifier.R_4764_Y());
                        list1.add(new Pair((Object)entry.getKey(), (Object)attributemodifier1));
                    }
                }
                if (effectinstance.R_4764_Y() > 0) {
                    iformattabletextcomponent = new F_2904_S("potion.withAmplifier", iformattabletextcomponent, new F_2904_S("potion.potency." + effectinstance.R_4764_Y()));
                }
                if (effectinstance.J_1907_R() > 20) {
                    iformattabletextcomponent = new F_2904_S("potion.withDuration", iformattabletextcomponent, MobEffectUtil.n_1700_B(effectinstance, durationFactor));
                }
                lores.add(iformattabletextcomponent.n_1700_B(effect.P_1922_E().n_1700_B()));
            }
        }
        if (!list1.isEmpty()) {
            lores.add(U_2871_b.R_4764_Y);
            lores.add(new F_2904_S("potion.whenDrank").n_1700_B(D_4024_W.u_1723_Y));
            for (Pair pair : list1) {
                U_1880_G attributemodifier2 = (U_1880_G)pair.getSecond();
                double d0 = attributemodifier2.G_564_y();
                double d1 = attributemodifier2.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R && attributemodifier2.R_4764_Y() != U_1880_G.n_1700_B.R_4764_Y ? attributemodifier2.G_564_y() : attributemodifier2.G_564_y() * 100.0;
                if (d0 > 0.0) {
                    lores.add(new F_2904_S("attribute.modifier.plus." + attributemodifier2.R_4764_Y().n_1700_B(), Z_1993_T.R_4764_Y.format(d1), new F_2904_S(((Attribute)pair.getFirst()).R_4764_Y())).n_1700_B(D_4024_W.s_956_w));
                    continue;
                }
                if (!(d0 < 0.0)) continue;
                lores.add(new F_2904_S("attribute.modifier.take." + attributemodifier2.R_4764_Y().n_1700_B(), Z_1993_T.R_4764_Y.format(d1 *= -1.0), new F_2904_S(((Attribute)pair.getFirst()).R_4764_Y())).n_1700_B(D_4024_W.P_4830_p));
            }
        }
    }
}


