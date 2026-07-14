/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.util.EnumMap;
import java.util.Map;
import lightning.product.I_3887_a;
import lightning.product.U_2912_j;
import lightning.product.b_2585_i;
import lightning.product.j_3341_s;

public final class RecipeBookSettings {
    private static final Map<I_3887_a, Pair<String, String>> n_1700_B = ImmutableMap.of((Object)((Object)I_3887_a.n_1700_B), (Object)Pair.of((Object)"isGuiOpen", (Object)"isFilteringCraftable"), (Object)((Object)I_3887_a.J_1907_R), (Object)Pair.of((Object)"isFurnaceGuiOpen", (Object)"isFurnaceFilteringCraftable"), (Object)((Object)I_3887_a.R_4764_Y), (Object)Pair.of((Object)"isBlastingFurnaceGuiOpen", (Object)"isBlastingFurnaceFilteringCraftable"), (Object)((Object)I_3887_a.G_564_y), (Object)Pair.of((Object)"isSmokerGuiOpen", (Object)"isSmokerFilteringCraftable"));
    private final Map<I_3887_a, n_1700_B> J_1907_R;

    private RecipeBookSettings(Map<I_3887_a, n_1700_B> p_i241892_1_) {
        this.J_1907_R = p_i241892_1_;
    }

    public RecipeBookSettings() {
        this(j_3341_s.n_1700_B(Maps.newEnumMap(I_3887_a.class), p_242153_0_ -> {
            for (I_3887_a recipebookcategory : I_3887_a.values()) {
                p_242153_0_.put(recipebookcategory, new n_1700_B(false, false));
            }
        }));
    }

    public boolean n_1700_B(I_3887_a p_242151_1_) {
        return this.J_1907_R.get((Object)((Object)p_242151_1_)).n_1700_B;
    }

    public void n_1700_B(I_3887_a p_242152_1_, boolean p_242152_2_) {
        this.J_1907_R.get((Object)((Object)p_242152_1_)).n_1700_B = p_242152_2_;
    }

    public boolean J_1907_R(I_3887_a p_242158_1_) {
        return this.J_1907_R.get((Object)((Object)p_242158_1_)).J_1907_R;
    }

    public void J_1907_R(I_3887_a p_242159_1_, boolean p_242159_2_) {
        this.J_1907_R.get((Object)((Object)p_242159_1_)).J_1907_R = p_242159_2_;
    }

    public static RecipeBookSettings n_1700_B(b_2585_i p_242157_0_) {
        EnumMap map = Maps.newEnumMap(I_3887_a.class);
        for (I_3887_a recipebookcategory : I_3887_a.values()) {
            boolean flag = p_242157_0_.readBoolean();
            boolean flag1 = p_242157_0_.readBoolean();
            map.put(recipebookcategory, new n_1700_B(flag, flag1));
        }
        return new RecipeBookSettings(map);
    }

    public void J_1907_R(b_2585_i p_242161_1_) {
        for (I_3887_a recipebookcategory : I_3887_a.values()) {
            n_1700_B recipebookstatus$categorystatus = this.J_1907_R.get((Object)recipebookcategory);
            if (recipebookstatus$categorystatus == null) {
                p_242161_1_.writeBoolean(false);
                p_242161_1_.writeBoolean(false);
                continue;
            }
            p_242161_1_.writeBoolean(recipebookstatus$categorystatus.n_1700_B);
            p_242161_1_.writeBoolean(recipebookstatus$categorystatus.J_1907_R);
        }
    }

    public static RecipeBookSettings n_1700_B(U_2912_j p_242154_0_) {
        EnumMap map = Maps.newEnumMap(I_3887_a.class);
        n_1700_B.forEach((p_242156_2_, p_242156_3_) -> {
            boolean flag = p_242154_0_.t_1786_h((String)p_242156_3_.getFirst());
            boolean flag1 = p_242154_0_.t_1786_h((String)p_242156_3_.getSecond());
            map.put(p_242156_2_, new n_1700_B(flag, flag1));
        });
        return new RecipeBookSettings(map);
    }

    public void J_1907_R(U_2912_j p_242160_1_) {
        n_1700_B.forEach((p_242155_2_, p_242155_3_) -> {
            n_1700_B recipebookstatus$categorystatus = this.J_1907_R.get(p_242155_2_);
            p_242160_1_.n_1700_B((String)p_242155_3_.getFirst(), recipebookstatus$categorystatus.n_1700_B);
            p_242160_1_.n_1700_B((String)p_242155_3_.getSecond(), recipebookstatus$categorystatus.J_1907_R);
        });
    }

    public RecipeBookSettings n_1700_B() {
        EnumMap map = Maps.newEnumMap(I_3887_a.class);
        for (I_3887_a recipebookcategory : I_3887_a.values()) {
            n_1700_B recipebookstatus$categorystatus = this.J_1907_R.get((Object)recipebookcategory);
            map.put(recipebookcategory, recipebookstatus$categorystatus.n_1700_B());
        }
        return new RecipeBookSettings(map);
    }

    public void n_1700_B(RecipeBookSettings p_242150_1_) {
        this.J_1907_R.clear();
        for (I_3887_a recipebookcategory : I_3887_a.values()) {
            n_1700_B recipebookstatus$categorystatus = p_242150_1_.J_1907_R.get((Object)recipebookcategory);
            this.J_1907_R.put(recipebookcategory, recipebookstatus$categorystatus.n_1700_B());
        }
    }

    public boolean equals(Object p_equals_1_) {
        return this == p_equals_1_ || p_equals_1_ instanceof RecipeBookSettings && this.J_1907_R.equals(((RecipeBookSettings)p_equals_1_).J_1907_R);
    }

    public int hashCode() {
        return this.J_1907_R.hashCode();
    }

    static final class n_1700_B {
        private boolean n_1700_B;
        private boolean J_1907_R;

        public n_1700_B(boolean p_i241893_1_, boolean p_i241893_2_) {
            this.n_1700_B = p_i241893_1_;
            this.J_1907_R = p_i241893_2_;
        }

        public n_1700_B n_1700_B() {
            return new n_1700_B(this.n_1700_B, this.J_1907_R);
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof n_1700_B)) {
                return false;
            }
            n_1700_B recipebookstatus$categorystatus = (n_1700_B)p_equals_1_;
            return this.n_1700_B == recipebookstatus$categorystatus.n_1700_B && this.J_1907_R == recipebookstatus$categorystatus.J_1907_R;
        }

        public int hashCode() {
            int i = this.n_1700_B ? 1 : 0;
            return 31 * i + (this.J_1907_R ? 1 : 0);
        }

        public String toString() {
            return "[open=" + this.n_1700_B + ", filtering=" + this.J_1907_R + "]";
        }
    }
}


