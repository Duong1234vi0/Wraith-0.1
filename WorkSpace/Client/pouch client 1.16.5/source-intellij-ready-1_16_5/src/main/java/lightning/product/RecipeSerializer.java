/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.A_1429_e;
import lightning.product.A_1530_r;
import lightning.product.ShieldDecorationRecipe;
import lightning.product.SingleItemRecipe;
import lightning.product.TippedArrowRecipe;
import lightning.product.H_1440_Y;
import lightning.product.K_1583_J;
import lightning.product.K_1907_m;
import lightning.product.L_4541_a;
import lightning.product.StonecutterRecipe;
import lightning.product.M_996_h;
import lightning.product.R_130_N;
import lightning.product.S_3924_b;
import lightning.product.ShulkerBoxColoring;
import lightning.product.U_1981_c;
import lightning.product.V_2120_N;
import lightning.product.V_3137_a;
import lightning.product.RepairItemRecipe;
import lightning.product.SimpleRecipeSerializer;
import lightning.product.Y_3349_u;
import lightning.product.b_2585_i;
import lightning.product.e_2674_c;
import lightning.product.f_993_z;
import lightning.product.g_2336_b;
import lightning.product.MapExtendingRecipe;
import lightning.product.Recipe;
import lightning.product.q_1576_y;
import lightning.product.r_2924_F;

public interface RecipeSerializer<T extends Recipe<?>> {
    public static final RecipeSerializer<M_996_h> n_1700_B = RecipeSerializer.n_1700_B("crafting_shaped", new M_996_h.n_1700_B());
    public static final RecipeSerializer<U_1981_c> J_1907_R = RecipeSerializer.n_1700_B("crafting_shapeless", new U_1981_c.n_1700_B());
    public static final SimpleRecipeSerializer<R_130_N> R_4764_Y = RecipeSerializer.n_1700_B("crafting_special_armordye", new SimpleRecipeSerializer<R_130_N>(R_130_N::new));
    public static final SimpleRecipeSerializer<r_2924_F> G_564_y = RecipeSerializer.n_1700_B("crafting_special_bookcloning", new SimpleRecipeSerializer<r_2924_F>(r_2924_F::new));
    public static final SimpleRecipeSerializer<K_1907_m> P_1922_E = RecipeSerializer.n_1700_B("crafting_special_mapcloning", new SimpleRecipeSerializer<K_1907_m>(K_1907_m::new));
    public static final SimpleRecipeSerializer<MapExtendingRecipe> u_1723_Y = RecipeSerializer.n_1700_B("crafting_special_mapextending", new SimpleRecipeSerializer<MapExtendingRecipe>(MapExtendingRecipe::new));
    public static final SimpleRecipeSerializer<A_1530_r> v_4262_N = RecipeSerializer.n_1700_B("crafting_special_firework_rocket", new SimpleRecipeSerializer<A_1530_r>(A_1530_r::new));
    public static final SimpleRecipeSerializer<A_1429_e> w_1484_f = RecipeSerializer.n_1700_B("crafting_special_firework_star", new SimpleRecipeSerializer<A_1429_e>(A_1429_e::new));
    public static final SimpleRecipeSerializer<L_4541_a> t_148_a = RecipeSerializer.n_1700_B("crafting_special_firework_star_fade", new SimpleRecipeSerializer<L_4541_a>(L_4541_a::new));
    public static final SimpleRecipeSerializer<TippedArrowRecipe> s_956_w = RecipeSerializer.n_1700_B("crafting_special_tippedarrow", new SimpleRecipeSerializer<TippedArrowRecipe>(TippedArrowRecipe::new));
    public static final SimpleRecipeSerializer<q_1576_y> u_2550_I = RecipeSerializer.n_1700_B("crafting_special_bannerduplicate", new SimpleRecipeSerializer<q_1576_y>(q_1576_y::new));
    public static final SimpleRecipeSerializer<ShieldDecorationRecipe> M_588_G = RecipeSerializer.n_1700_B("crafting_special_shielddecoration", new SimpleRecipeSerializer<ShieldDecorationRecipe>(ShieldDecorationRecipe::new));
    public static final SimpleRecipeSerializer<ShulkerBoxColoring> P_4830_p = RecipeSerializer.n_1700_B("crafting_special_shulkerboxcoloring", new SimpleRecipeSerializer<ShulkerBoxColoring>(ShulkerBoxColoring::new));
    public static final SimpleRecipeSerializer<V_2120_N> h_1847_R = RecipeSerializer.n_1700_B("crafting_special_suspiciousstew", new SimpleRecipeSerializer<V_2120_N>(V_2120_N::new));
    public static final SimpleRecipeSerializer<RepairItemRecipe> Q_4569_t = RecipeSerializer.n_1700_B("crafting_special_repairitem", new SimpleRecipeSerializer<RepairItemRecipe>(RepairItemRecipe::new));
    public static final e_2674_c<Y_3349_u> M_182_A = RecipeSerializer.n_1700_B("smelting", new e_2674_c<Y_3349_u>(Y_3349_u::new, 200));
    public static final e_2674_c<H_1440_Y> t_1786_h = RecipeSerializer.n_1700_B("blasting", new e_2674_c<H_1440_Y>(H_1440_Y::new, 100));
    public static final e_2674_c<f_993_z> multiplayerClientSuggestionProvider = RecipeSerializer.n_1700_B("smoking", new e_2674_c<f_993_z>(f_993_z::new, 100));
    public static final e_2674_c<S_3924_b> w_1457_N = RecipeSerializer.n_1700_B("campfire_cooking", new e_2674_c<S_3924_b>(S_3924_b::new, 100));
    public static final RecipeSerializer<StonecutterRecipe> Y_601_j = RecipeSerializer.n_1700_B("stonecutting", new SingleItemRecipe.n_1700_B<StonecutterRecipe>(StonecutterRecipe::new));
    public static final RecipeSerializer<K_1583_J> Y_259_p = RecipeSerializer.n_1700_B("smithing", new K_1583_J.n_1700_B());

    public T J_1907_R(g_2336_b var1, JsonObject var2);

    public T J_1907_R(g_2336_b var1, b_2585_i var2);

    public void n_1700_B(b_2585_i var1, T var2);

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S n_1700_B(String key, S recipeSerializer) {
        return (S)V_3137_a.n_1700_B(V_3137_a.s_2632_s, key, recipeSerializer);
    }
}


