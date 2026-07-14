/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSyntaxException
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lightning.product.CraftingContainer;
import lightning.product.NonNullList;
import lightning.product.Q_4863_g;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;

public class M_996_h
implements Q_4863_g {
    private final int n_1700_B;
    private final int J_1907_R;
    private final NonNullList<b_3278_X> R_4764_Y;
    private final Z_1993_T G_564_y;
    private final g_2336_b P_1922_E;
    private final String u_1723_Y;

    public M_996_h(g_2336_b idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<b_3278_X> recipeItemsIn, Z_1993_T recipeOutputIn) {
        this.P_1922_E = idIn;
        this.u_1723_Y = groupIn;
        this.n_1700_B = recipeWidthIn;
        this.J_1907_R = recipeHeightIn;
        this.R_4764_Y = recipeItemsIn;
        this.G_564_y = recipeOutputIn;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.P_1922_E;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.n_1700_B;
    }

    @Override
    public String G_564_y() {
        return this.u_1723_Y;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return this.G_564_y;
    }

    @Override
    public NonNullList<b_3278_X> n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width >= this.n_1700_B && height >= this.J_1907_R;
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        for (int i = 0; i <= inv.G_564_y() - this.n_1700_B; ++i) {
            for (int j = 0; j <= inv.R_4764_Y() - this.J_1907_R; ++j) {
                if (this.n_1700_B(inv, i, j, true)) {
                    return true;
                }
                if (!this.n_1700_B(inv, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean n_1700_B(CraftingContainer craftingInventory, int width, int height, boolean p_77573_4_) {
        for (int i = 0; i < craftingInventory.G_564_y(); ++i) {
            for (int j = 0; j < craftingInventory.R_4764_Y(); ++j) {
                int k = i - width;
                int l = j - height;
                b_3278_X ingredient = b_3278_X.n_1700_B;
                if (k >= 0 && l >= 0 && k < this.n_1700_B && l < this.J_1907_R) {
                    ingredient = p_77573_4_ ? this.R_4764_Y.get(this.n_1700_B - k - 1 + l * this.n_1700_B) : this.R_4764_Y.get(k + l * this.n_1700_B);
                }
                if (ingredient.n_1700_B(craftingInventory.s_956_w(i + j * craftingInventory.G_564_y()))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        return this.R_4764_Y().t_148_a();
    }

    public int P_1922_E() {
        return this.n_1700_B;
    }

    public int s_956_w() {
        return this.J_1907_R;
    }

    private static NonNullList<b_3278_X> n_1700_B(String[] pattern, Map<String, b_3278_X> keys, int patternWidth, int patternHeight) {
        NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B(patternWidth * patternHeight, b_3278_X.n_1700_B);
        HashSet set = Sets.newHashSet(keys.keySet());
        set.remove(" ");
        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String s = pattern[i].substring(j, j + 1);
                b_3278_X ingredient = keys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }
                set.remove(s);
                nonnulllist.set(j + patternWidth * i, ingredient);
            }
        }
        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + String.valueOf(set));
        }
        return nonnulllist;
    }

    @VisibleForTesting
    static String[] n_1700_B(String ... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int i1 = 0; i1 < toShrink.length; ++i1) {
            String s = toShrink[i1];
            i = Math.min(i, M_996_h.n_1700_B(s));
            int j1 = M_996_h.J_1907_R(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }
                ++l;
                continue;
            }
            l = 0;
        }
        if (toShrink.length == l) {
            return new String[0];
        }
        String[] astring = new String[toShrink.length - l - k];
        for (int k1 = 0; k1 < astring.length; ++k1) {
            astring[k1] = toShrink[k1 + k].substring(i, j + 1);
        }
        return astring;
    }

    private static int n_1700_B(String str) {
        int i;
        for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
        }
        return i;
    }

    private static int J_1907_R(String str) {
        int i;
        for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
        }
        return i;
    }

    private static String[] n_1700_B(JsonArray jsonArr) {
        String[] astring = new String[jsonArr.size()];
        if (astring.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        }
        if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }
        for (int i = 0; i < astring.length; ++i) {
            String s = i_4431_W.n_1700_B(jsonArr.get(i), "pattern[" + i + "]");
            if (s.length() > 3) {
                throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
            }
            if (i > 0 && astring[0].length() != s.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
            }
            astring[i] = s;
        }
        return astring;
    }

    private static Map<String, b_3278_X> J_1907_R(JsonObject json) {
        HashMap map = Maps.newHashMap();
        for (Map.Entry entry : json.entrySet()) {
            if (((String)entry.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put((String)entry.getKey(), b_3278_X.n_1700_B((JsonElement)entry.getValue()));
        }
        map.put(" ", b_3278_X.n_1700_B);
        return map;
    }

    public static Z_1993_T n_1700_B(JsonObject object) {
        String s = i_4431_W.u_1723_Y(object, "item");
        q_1613_l item = V_3137_a.e_2887_G.J_1907_R(new g_2336_b(s)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + s + "'"));
        if (object.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        }
        int i = i_4431_W.n_1700_B(object, "count", 1);
        return new Z_1993_T(item, i);
    }

    public static class n_1700_B
    implements RecipeSerializer<M_996_h> {
        public M_996_h n_1700_B(g_2336_b recipeId, JsonObject json) {
            String s = i_4431_W.n_1700_B(json, "group", "");
            Map<String, b_3278_X> map = M_996_h.J_1907_R(i_4431_W.M_588_G(json, "key"));
            String[] astring = M_996_h.n_1700_B(M_996_h.n_1700_B(i_4431_W.P_4830_p(json, "pattern")));
            int i = astring[0].length();
            int j = astring.length;
            NonNullList<b_3278_X> nonnulllist = M_996_h.n_1700_B(astring, map, i, j);
            Z_1993_T itemstack = M_996_h.n_1700_B(i_4431_W.M_588_G(json, "result"));
            return new M_996_h(recipeId, s, i, j, nonnulllist, itemstack);
        }

        public M_996_h n_1700_B(g_2336_b recipeId, b_2585_i buffer) {
            int i = buffer.u_1723_Y();
            int j = buffer.u_1723_Y();
            String s = buffer.P_1922_E(Short.MAX_VALUE);
            NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B(i * j, b_3278_X.n_1700_B);
            for (int k = 0; k < nonnulllist.size(); ++k) {
                nonnulllist.set(k, b_3278_X.J_1907_R(buffer));
            }
            Z_1993_T itemstack = buffer.u_2550_I();
            return new M_996_h(recipeId, s, i, j, nonnulllist, itemstack);
        }

        @Override
        public void n_1700_B(b_2585_i buffer, M_996_h recipe) {
            buffer.G_564_y(recipe.n_1700_B);
            buffer.G_564_y(recipe.J_1907_R);
            buffer.n_1700_B(recipe.u_1723_Y);
            for (b_3278_X ingredient : recipe.R_4764_Y) {
                ingredient.n_1700_B(buffer);
            }
            buffer.n_1700_B(recipe.G_564_y);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, b_2585_i b_2585_i2) {
            return this.n_1700_B(g_2336_b2, b_2585_i2);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, JsonObject jsonObject) {
            return this.n_1700_B(g_2336_b2, jsonObject);
        }
    }
}


